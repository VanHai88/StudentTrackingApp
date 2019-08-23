/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp;

import dao.LecturerDao;
import entities.Lecturer;
import entities.Student;
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
public class LecturerDaoImp implements LecturerDao{
    
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    private final ConnectDbLibrary connectDbLibrary;
    private final Connection conn;

    public LecturerDaoImp() {
        this.connectDbLibrary = new ConnectDbLibrary();
        this.conn = connectDbLibrary.getConnectMySQL();
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        String sql = "SELECT * FROM lecturers";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Lecturer lecturer = new Lecturer();
                lecturer.setId(rs.getInt("ID"));
                lecturer.setFullName(rs.getString("NAME_LECTURERS"));
                lecturer.setDateOfBirth(DateUtils.getLDateFromsDate(rs.getDate("DATE_OF_BIRTH")));
                lecturer.setAddRess(rs.getString("ADDRESS"));
                lecturer.setJobDescription(rs.getString("JOB_DESCRIPTION"));
                lecturer.setWage(rs.getDouble("WAGE"));
                lecturer.setStatusLecturers(rs.getString("STATUS_LECTURERS"));
                lecturer.setEmail(rs.getString("EMAIL"));
                lecturer.setPathFile(rs.getString("AVATAR"));
                lecturer.setEvaluate(rs.getInt("EVALUATE"));
                lecturers.add(lecturer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }

        return lecturers;
    }

    @Override
    public int save(Lecturer lecturer) {
        int studentId = 0;
        final String sql = "INSERT INTO lecturers(NAME_LECTURERS, JOB_DESCRIPTION,"
                + "WAGE, ADDRESS, DATE_OF_BIRTH, STATUS_LECTURERS, EMAIL, AVATAR, EVALUATE)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, lecturer.getFullName());
            pst.setString(2, lecturer.getJobDescription());
            pst.setDouble(3, lecturer.getWage());
            pst.setString(4, lecturer.getAddRess());
            pst.setDate(5, (Date) DateUtils.getDateateFromsLD(lecturer.getDateOfBirth()));
            pst.setString(6, lecturer.getStatusLecturers());
            pst.setString(7, lecturer.getEmail());
            pst.setString(8, lecturer.getPathFile());
            pst.setInt(9, lecturer.getEvaluate());

            int save = pst.executeUpdate();
            if (save > 0) {
                // Lấy mã sinh viên vừa mới thêm vào database
                rs = pst.getGeneratedKeys();
                while (rs.next()) {
                    studentId = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println("xx: " + ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
            }
        }
        return studentId;
    }

    @Override
    public int Edit(Lecturer lecturer) {
        int id = 0;
        String sql ="INSERT INTO STUDENTS(NAME_LECTURERS, JOB_DESCRIPTION, WAGE, ADDRESS, DATE_OF_BIRTH, STATUS_LECTURERS, EMAIL, AVATAR, EVALUATE)\n" +
                    "VALUES(?, ?, ?, ?, ?,?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, lecturer.getFullName());
            pst.setString(2, lecturer.getJobDescription());
            pst.setDouble(3, lecturer.getWage());
            pst.setString(4, lecturer.getAddRess());
            pst.setDate(5, (Date) DateUtils.getDateateFromsLD(lecturer.getDateOfBirth()));
            pst.setString(6, lecturer.getStatusLecturers());
            pst.setString(7, lecturer.getEmail());
            pst.setString(8, lecturer.getPathFile());
            pst.setInt(9, lecturer.getEvaluate());
            
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
    public Lecturer getLecturer(int id) {
        String sql = "SELECT * FROM lecturers WHERE id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Lecturer lecturer = new Lecturer();
                lecturer.setId(rs.getInt("ID"));
                lecturer.setFullName(rs.getString("NAME_LECTURERS"));
                lecturer.setDateOfBirth(DateUtils.getLDateFromsDate(rs.getDate("DATE_OF_BIRTH")));
                lecturer.setAddRess(rs.getString("ADDRESS"));
                lecturer.setJobDescription(rs.getString("JOB_DESCRIPTION"));
                lecturer.setWage(rs.getDouble("WAGE"));
                lecturer.setStatusLecturers(rs.getString("STATUS_LECTURERS"));
                lecturer.setEmail(rs.getString("EMAIL"));
                lecturer.setPathFile(rs.getString("AVATAR"));
                lecturer.setEvaluate(rs.getInt("EVALUATE"));
                return lecturer;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
            }
        }

        return null;
    }
    
}
