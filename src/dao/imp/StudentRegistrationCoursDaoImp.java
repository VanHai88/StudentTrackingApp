/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp;

import dao.StudentRegistrationCoursDao;
import entities.StudentRegistrationCours;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class StudentRegistrationCoursDaoImp implements StudentRegistrationCoursDao {

    private final ConnectDbLibrary connectDbLibrary;
    private ResultSet rs;
    private PreparedStatement pst;
    private final Connection conn;

    public StudentRegistrationCoursDaoImp() {
        connectDbLibrary = new ConnectDbLibrary();
        conn = connectDbLibrary.getConnectMySQL();
    }

    @Override
    public List<StudentRegistrationCours> getAmountOfMonth(int month, int year) {
        List<StudentRegistrationCours> srcList = new ArrayList<>();
        String sql = "SELECT courses.NAME_COURSES, COUNT(STUDENT_ID) AS AMOUNT\n"
                + "FROM classes_has_students\n"
                + "LEFT JOIN classes ON classes_has_students.CLASSES_ID = classes.ID\n"
                + "LEFT JOIN courses ON courses.ID = classes.COURSE_ID\n"
                + "WHERE MONTH(classes_has_students.REGISTRATION_TIME) = ? AND year(classes_has_students.REGISTRATION_TIME) = ?\n"
                + "group by courses.NAME_COURSES, courses.ID";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, month);
            pst.setInt(2, year);
            rs = pst.executeQuery();
            while (rs.next()) {
                StudentRegistrationCours src = new StudentRegistrationCours(rs.getInt("AMOUNT"),
                        rs.getString("courses.NAME_COURSES"));
                srcList.add(src);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRegistrationCoursDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return srcList;
    }

    @Override
    public List<StudentRegistrationCours> getAmountOfYearth(int year) {
        List<StudentRegistrationCours> srcList = new ArrayList<>();
        String sql = "SELECT courses.NAME_COURSES, COUNT(STUDENT_ID) AS AMOUNT\n"
                + "FROM classes_has_students\n"
                + "LEFT JOIN classes ON classes_has_students.CLASSES_ID = classes.ID\n"
                + "LEFT JOIN courses ON courses.ID = classes.COURSE_ID\n"
                + "WHERE year(classes_has_students.REGISTRATION_TIME) = ?\n"
                + "group by courses.NAME_COURSES, courses.ID";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, year);
            rs = pst.executeQuery();
            while (rs.next()) {
                StudentRegistrationCours src = new StudentRegistrationCours(rs.getInt("AMOUNT"),
                        rs.getString("courses.NAME_COURSES"));
                srcList.add(src);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRegistrationCoursDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return srcList;
    }

    @Override
    public List<StudentRegistrationCours> getAmountBetweenTime(LocalDate from, LocalDate to) {
        List<StudentRegistrationCours> srcList = new ArrayList<>();
        String sql = "SELECT courses.NAME_COURSES, COUNT(STUDENT_ID) AS AMOUNT\n"
                + "FROM classes_has_students\n"
                + "LEFT JOIN classes ON classes_has_students.CLASSES_ID = classes.ID\n"
                + "LEFT JOIN courses ON courses.ID = classes.COURSE_ID\n"
                + "WHERE classes_has_students.REGISTRATION_TIME BETWEEN ? AND ?\n"
                + "group by courses.NAME_COURSES, courses.ID";

        try {
            pst = conn.prepareStatement(sql);
            pst.setDate(1, (Date) DateUtils.getDateateFromsLD(from));
            pst.setDate(2, (Date) DateUtils.getDateateFromsLD(to));
            rs = pst.executeQuery();
            while (rs.next()) {
                StudentRegistrationCours src = new StudentRegistrationCours(rs.getInt("AMOUNT"),
                        rs.getString("courses.NAME_COURSES"));
                srcList.add(src);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRegistrationCoursDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return srcList;
    }

}
