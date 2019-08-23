/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.ConnectDbLibrary;
import dao.StudentDao;
import entities.Student;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import utils.DateUtils;

/**
 *
 * @author Van Hai
 */
public class StudentDaoImp implements StudentDao{
    private final ConnectDbLibrary connectDbLibrary;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    private final Connection conn;

    public StudentDaoImp() {
        connectDbLibrary = new ConnectDbLibrary();
        conn = connectDbLibrary.getConnectMySQL();
    }
    
    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        List<Student> listStudent = new ArrayList<>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Student student = new Student(rs.getInt("ID"),
                                              rs.getString("FULLNAME"),
                                              rs.getString("PHONE"),
                                              rs.getString("ADDRESS"),
                                              rs.getString("EMAIL"),
                                              DateUtils.getLDateFromsDate(rs.getDate("DATE_OF_BIRTH")),
                                              rs.getString("FBLINK"),
                                              rs.getString("CURRENT_WORKING"),
                                              rs.getInt("YEARTH"),
                                              rs.getString("SCHOOL_NAME"), 1);
                listStudent.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listStudent;
    }

    @Override
    public int addStudent(Student student) {
        int id = 0;
        String sql ="INSERT INTO STUDENTS(FULLNAME, PHONE, ADDRESS, EMAIL, DATE_OF_BIRTH, FBLINK, YEARTH,SCHOOL_NAME, CURRENT_WORKING)\n" +
                    "VALUES(?, ?, ?, ?, ?,?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, student.getFullName());
            pst.setString(2, student.getPhone());
            pst.setString(3, student.getAddRess());
            pst.setString(4, student.getEmail());
            pst.setDate(5, (Date) DateUtils.getDateateFromsLD(student.getDateOfBirth()));
            pst.setString(6, student.getFbLink());
            pst.setInt(7, student.getYearth());
            pst.setString(8, student.getSchoolName());
            pst.setString(9, student.getCurrentWorking());
            
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
    public int DelStudent(Student student) {
        
        int result = 0;
        String sql = "DELETE FROM students WHERE ID = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, student.getId());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int editStudent(Student student) {
        int result = 0;
        String sql = "UPDATE STUDENTS\n" +
                    "SET FULLNAME = ? , PHONE = ?, ADDRESS = ?, EMAIL = ?, DATE_OF_BIRTH = ?, FBLINK = ?, YEARTH = ?,SCHOOL_NAME = ?, CURRENT_WORKING = ?\n" +
                    "WHERE ID = ?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, student.getFullName());
            pst.setString(2, student.getPhone());
            pst.setString(3, student.getAddRess());
            pst.setString(4, student.getEmail());
            pst.setDate(5, (Date) DateUtils.getDateateFromsLD(student.getDateOfBirth()));
            pst.setString(6, student.getFbLink());
            pst.setInt(7, student.getYearth());
            pst.setString(8, student.getSchoolName());
            pst.setString(9, student.getCurrentWorking());
            pst.setInt(10, student.getId());
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int[] addStudentList(List<Student> students) {
        int[] result = null;
        String sql ="INSERT INTO STUDENTS(FULLNAME, PHONE, ADDRESS, EMAIL, DATE_OF_BIRTH, FBLINK, YEARTH,SCHOOL_NAME, CURRENT_WORKING)\n" +
                    "VALUES(?, ?, ?, ?, ?,?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql);
                for(Student student : students){
                    pst.setString(1, student.getFullName());
                    pst.setString(2, student.getPhone());
                    pst.setString(3, student.getAddRess());
                    pst.setString(4, student.getEmail());
                    pst.setDate(5, (Date) DateUtils.getDateateFromsLD(student.getDateOfBirth()));
                    pst.setString(6, student.getFbLink());
                    pst.setInt(7, student.getYearth());
                    pst.setString(8, student.getSchoolName());
                    pst.setString(9, student.getCurrentWorking());
                    pst.addBatch();
                }
                result = pst.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImp.class.getName()).log(Level.SEVERE, null, ex);
       }
        return result;
    }
}
