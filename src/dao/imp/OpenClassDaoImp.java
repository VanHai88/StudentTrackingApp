/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp;

import dao.OpenClassDao;
import entities.OpenClass;
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
public class OpenClassDaoImp implements OpenClassDao {

    private final ConnectDbLibrary connectDbLibrary;
    private ResultSet rs;
    private PreparedStatement pst;
    private final Connection conn;

    public OpenClassDaoImp() {
        connectDbLibrary = new ConnectDbLibrary();
        conn = connectDbLibrary.getConnectMySQL();
    }

    @Override
    public List<OpenClass> getAmountOfMonth(int month, int year) {
        List<OpenClass> openClassList = new ArrayList<>();
        String sql = "SELECT courses.NAME_COURSES, COUNT(classes.ID) AS AMOUNT\n"
                + "FROM classes\n"
                + "LEFT JOIN courses ON classes.COURSE_ID = courses.ID\n"
                + "WHERE MONTH(classes.DATE_BEGIN) = ? AND year(classes.DATE_BEGIN) = ?\n"
                + "group by classes.COURSE_ID";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, month);
            pst.setInt(2, year);
            rs = pst.executeQuery();
            while (rs.next()) {
                OpenClass openClass = new OpenClass(rs.getInt("AMOUNT"),
                        rs.getString("courses.NAME_COURSES"));
                openClassList.add(openClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRegistrationCoursDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return openClassList;
    }

    @Override
    public List<OpenClass> getAmountOfYear(int year) {
        List<OpenClass> openClassList = new ArrayList<>();
        String sql = "SELECT courses.NAME_COURSES, COUNT(classes.ID) AS AMOUNT\n"
                + "FROM classes\n"
                + "LEFT JOIN courses ON classes.COURSE_ID = courses.ID\n"
                + "WHERE year(classes.DATE_BEGIN) = ?\n"
                + "group by classes.COURSE_ID";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, year);
            rs = pst.executeQuery();
            while (rs.next()) {
                OpenClass openClass = new OpenClass(rs.getInt("AMOUNT"),
                        rs.getString("courses.NAME_COURSES"));
                openClassList.add(openClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRegistrationCoursDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return openClassList;
    }

    @Override
    public List<OpenClass> getAmountBetweenTime(LocalDate from, LocalDate to) {
        List<OpenClass> openClassList = new ArrayList<>();
        String sql = "SELECT courses.NAME_COURSES, COUNT(classes.ID) AS AMOUNT\n"
                + "FROM classes\n"
                + "LEFT JOIN courses ON classes.COURSE_ID = courses.ID\n"
                + "WHERE classes.DATE_BEGIN BETWEEN ? AND ?\n"
                + "group by classes.COURSE_ID;";

        try {
            pst = conn.prepareStatement(sql);
            pst.setDate(1, (Date) DateUtils.getDateateFromsLD(from));
            pst.setDate(2, (Date) DateUtils.getDateateFromsLD(to));
            rs = pst.executeQuery();
            while (rs.next()) {
                OpenClass openClass = new OpenClass(rs.getInt("AMOUNT"),
                        rs.getString("courses.NAME_COURSES"));
                openClassList.add(openClass);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRegistrationCoursDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return openClassList;
    }
}
