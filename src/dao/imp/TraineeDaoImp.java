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
import dao.TraineeDao;
import entities.Student;
import entities.Trainee;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import utils.DateUtils;

/**
 *
 * @author Van Hai
 */
public class TraineeDaoImp implements TraineeDao{
    private ConnectDbLibrary connectDbLibrary;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst;

    public TraineeDaoImp() {
        connectDbLibrary = new ConnectDbLibrary();
    }
    
    
    @Override
    public int CountTrainee() {
        Connection conn = connectDbLibrary.getConnectMySQL();
        String sql = "SELECT count(TEMP.ID)\n" +
                    "FROM\n" +
                    "(SELECT ID\n" +
                    "FROM STUDENTS\n" +
                    "UNION ALL\n" +
                    "SELECT ID\n" +
                    "FROM NON_STUDENTS) AS TEMP;";
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TraineeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<Trainee> getStatusTrainees(int id) {
        Connection conn = connectDbLibrary.getConnectMySQL();
        String sql = "SELECT FULLNAME, STATUS_STUDENT\n" +
                    "FROM students\n" +
                    "LEFT JOIN classes_has_students ON classes_has_students.STUDENT_ID = students.ID\n" +
                    "WHERE classes_has_students.CLASSES_ID = ?";
        
        List<Trainee> trainees = new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                Trainee trainee = new Trainee(rs.getString("FULLNAME"), rs.getString("STATUS_STUDENT"));
                trainees.add(trainee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TraineeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trainees;
        
    }

    @Override
    public List<Trainee> getLimitTrainess(int offset, int rowcount) {
        Connection conn = connectDbLibrary.getConnectMySQL();
        String sql = "SELECT ID, FULLNAME, PHONE, ADDRESS, EMAIL, DATE_OF_BIRTH, FBLINK, CURRENT_WORKING, YEARTH, SCHOOL_NAME, 1 AS TYPE\n" +
                    "FROM students\n" +
                    "union\n" +
                    "SELECT ID, FULLNAME, PHONE, ADDRESS, EMAIL, DATE_OF_BIRTH, FBLINK, CURRENT_WORKING, 0 AS YEARTH, 'NULL' AS SCHOOL_NAME, 2 AS TYPE\n" +
                    "FROM non_students\n" +
                    "LIMIT ?, ?";
        
        List<Trainee> trainees = new ArrayList<>();
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, offset);
            pst.setInt(2, rowcount);
            rs = pst.executeQuery();
            while(rs.next()){
                Trainee trainee = new Student(rs.getInt("ID"),
                                              rs.getString("FULLNAME"),
                                              rs.getString("PHONE"),
                                              rs.getString("ADDRESS"),
                                              rs.getString("EMAIL"),
                                              DateUtils.getLDateFromsDate(rs.getDate("DATE_OF_BIRTH")),
                                              rs.getString("FBLINK"),
                                              rs.getString("CURRENT_WORKING"),
                                              rs.getInt("YEARTH"),
                                              rs.getString("SCHOOL_NAME"),
                                              rs.getInt("TYPE"));
                trainees.add(trainee);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TraineeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return trainees;
    }
}
