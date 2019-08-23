/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp;

import dao.CourseDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.ConnectDbLibrary;

/**
 *
 * @author Van Hai
 */
public class CoursesDaoImp implements CourseDao{
    private final ConnectDbLibrary connectDbLibrary;
    private Statement st;
    private ResultSet rs;

    public CoursesDaoImp() {
        connectDbLibrary = new ConnectDbLibrary();
    }

    @Override
    public int countCourse() {
        Connection conn = connectDbLibrary.getConnectMySQL();
        String sql = "SELECT COUNT(*)\n" +
                     "FROM courses";
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursesDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
