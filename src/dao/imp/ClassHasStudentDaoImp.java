/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp;

import dao.ClassHasStudentDao;
import entities.ClassHasStudent;
import entities.GClass;
import entities.Trainee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.ConnectDbLibrary;

/**
 *
 * @author Van Hai
 */
public class ClassHasStudentDaoImp implements ClassHasStudentDao{

    private ConnectDbLibrary connectDbLibrary;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst;
    private final Connection conn;

    public ClassHasStudentDaoImp() {
        connectDbLibrary = new ConnectDbLibrary();
        conn = connectDbLibrary.getConnectMySQL();
    }
    
    @Override
    public int delClassHasStudent(Trainee trainee, GClass gClass) {
        int result = 0;
        String sql = "";
        if(gClass == null){
            sql = "DELETE FROM classes_has_students WHERE STUDENT_ID = ? AND TYPE_STUDENT = ?";
        }else{
            sql = "DELETE FROM classes_has_students WHERE STUDENT_ID = ? AND TYPE_STUDENT = ? AND CLASSES_ID = ?";
        }
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, trainee.getId());
            pst.setInt(2, trainee.getType());
            if(gClass != null){
                pst.setInt(3, gClass.getId());
            }
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int[] addClassHasStudent(Trainee trainee, List<GClass> gClassList, List<String> statusList) {
        int[] result = null;
        String sql = "INSERT INTO classes_has_students(STUDENT_ID, CLASSES_ID, TYPE_STUDENT, STATUS_STUDENT)\n" +
                     "VALUES(?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);
           
                int i = 0;
                for(GClass gClass : gClassList){
                    pst.setInt(1, trainee.getId());
                    pst.setInt(2, gClass.getId());
                    pst.setInt(3, trainee.getType());
                    pst.setString(4, statusList.get(i));
                    pst.addBatch();
                    i++;
                    
                }
                
                result = pst.executeBatch();
           
        } catch (SQLException ex) {
            System.out.println("Lớp học Đã tồn tại"); 
       }
        return result;
    }

    @Override
    public ClassHasStudent getAllClassHasStudent(Trainee trainee, GClass gClass) {
        
        String sql = "SELECT * FROM classes_has_students WHERE STUDENT_ID = ? AND TYPE_STUDENT = ? AND CLASSES_ID = ?";
       
       ClassHasStudent chs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, trainee.getId());
            pst.setInt(2, trainee.getType());
            pst.setInt(3, gClass.getId());
            
            rs = pst.executeQuery();
            while(rs.next()){
                chs = new ClassHasStudent(rs.getInt("STUDENT_ID"),
                                                                rs.getInt("TYPE_STUDENT"),
                                                                rs.getString("STATUS_STUDENT"),
                                                                rs.getInt("CLASSES_ID"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chs;
    }

    @Override
    public List<ClassHasStudent> ClassHasStudentList(Trainee trainee) {
       
        String sql = "SELECT * FROM classes_has_students WHERE STUDENT_ID = ? AND TYPE_STUDENT = ?";
       
        List<ClassHasStudent> classHasStudents = new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, trainee.getId());
            pst.setInt(2, trainee.getType());
            
            rs = pst.executeQuery();
            while(rs.next()){
                ClassHasStudent chs = new ClassHasStudent(rs.getInt("STUDENT_ID"),
                                                                rs.getInt("TYPE_STUDENT"),
                                                                rs.getString("STATUS_STUDENT"),
                                                                rs.getInt("CLASSES_ID"));
                classHasStudents.add(chs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classHasStudents;
    }

    @Override
    public int[] editClassHasStudent(Trainee trainee, List<GClass> gClassList, List<String> statusList) {
        int[] result = null;
        String sql = "UPDATE classes_has_students SET CLASSES_ID = ?, STATUS_STUDENT =? WHERE STUDENT_ID = ? AND TYPE_STUDENT = ?";
        try {
            
            pst = conn.prepareStatement(sql);
                int i = 0;
                for(GClass gClass : gClassList){
                    System.out.println("IDSTUDENT: "+trainee.getId()+" IDCLASS: "+gClass.getId()+" TYPE: "+trainee.getType()+" STATUS: "+statusList.get(i));
                    pst.setInt(1, gClass.getId());
                    pst.setString(2, statusList.get(i));
                    pst.setInt(3, trainee.getId());
                    pst.setInt(4, trainee.getType());
                    pst.addBatch();
                    i++;
                }
                result = pst.executeBatch();
                
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
       }
        return result;
    }

}
