/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp;

import dao.NonStudentDao;
import entities.NonStudent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.ConnectDbLibrary;
import utils.DateUtils;

/**
 *
 * @author Van Hai
 */
public class NonStudentDaoImp implements NonStudentDao{
    private final ConnectDbLibrary connectDbLibrary;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    private final Connection conn;

    public NonStudentDaoImp() {
        connectDbLibrary = new ConnectDbLibrary();
        conn = connectDbLibrary.getConnectMySQL();
    }
    
    @Override
    public List<NonStudent> getAllNonStudents() {
        String sql = "SELECT * FROM non_students";
        List<NonStudent> listNonStudents = new ArrayList<>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                NonStudent nonStudent = new NonStudent(rs.getInt("ID"),
                                              rs.getString("FULLNAME"),
                                              rs.getString("PHONE"),
                                              rs.getString("ADDRESS"),
                                              rs.getString("EMAIL"),
                                              DateUtils.getLDateFromsDate(rs.getDate("DATE_OF_BIRTH")),
                                              rs.getString("FBLINK"),
                                              rs.getString("CURRENT_WORKING"), 2);
                listNonStudents.add(nonStudent);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNonStudents;
    }

    @Override
    public int addNonStudent(NonStudent nonStudent) {
        int id = 0;
        String sql ="INSERT INTO non_students(FULLNAME, PHONE, ADDRESS, EMAIL, DATE_OF_BIRTH, FBLINK, CURRENT_WORKING)\n" +
                    "VALUES(?, ?, ?, ?, ?,?, ?)";
        try {
            pst = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, nonStudent.getFullName());
            pst.setString(2, nonStudent.getPhone());
            pst.setString(3, nonStudent.getAddRess());
            pst.setString(4, nonStudent.getEmail());
            pst.setDate(5, (Date) DateUtils.getDateateFromsLD(nonStudent.getDateOfBirth()));
            pst.setString(6, nonStudent.getFbLink());
            pst.setString(7, nonStudent.getCurrentWorking());
            
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }

    @Override
    public int ediNonStudent(NonStudent nonStudent) {
         int result = 0;
        String sql = "UPDATE NON_STUDENTS\n" +
                    "SET FULLNAME = ? , PHONE = ?, ADDRESS = ?, EMAIL = ?, DATE_OF_BIRTH = ?, FBLINK = ?, CURRENT_WORKING = ?\n" +
                    "WHERE ID = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, nonStudent.getFullName());
            pst.setString(2, nonStudent.getPhone());
            pst.setString(3, nonStudent.getAddRess());
            pst.setString(4, nonStudent.getEmail());
            pst.setDate(5, (Date) DateUtils.getDateateFromsLD(nonStudent.getDateOfBirth()));
            pst.setString(6, nonStudent.getFbLink());
            pst.setString(7, nonStudent.getCurrentWorking());
            pst.setInt(8, nonStudent.getId());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NonStudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int DelNonStudent(NonStudent nonStudent) {
        int result = 0;
        String sql = "DELETE FROM non_students WHERE ID = ?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, nonStudent.getId());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int[] addListNonStudent(List<NonStudent> nonStudents) {
        int[] result = null;
        String sql ="INSERT INTO non_students(FULLNAME, PHONE, ADDRESS, EMAIL, DATE_OF_BIRTH, FBLINK, CURRENT_WORKING)\n" +
                    "VALUES(?, ?, ?, ?, ?,?, ?)";
        
        try {
            pst = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            for(NonStudent  nonStudent : nonStudents){
                pst.setString(1, nonStudent.getFullName());
                pst.setString(2, nonStudent.getPhone());
                pst.setString(3, nonStudent.getAddRess());
                pst.setString(4, nonStudent.getEmail());
                pst.setDate(5, (Date) DateUtils.getDateateFromsLD(nonStudent.getDateOfBirth()));
                pst.setString(6, nonStudent.getFbLink());
                pst.setString(7, nonStudent.getCurrentWorking());
                pst.addBatch();
            }
            
            result = pst.executeBatch();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
