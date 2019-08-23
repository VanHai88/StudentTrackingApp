/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp;

import dao.ClassDao;
import entities.GClass;
import java.sql.Connection;
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
public class ClassDaoImp implements ClassDao{
    private final ConnectDbLibrary connectDbLibrary;
    private Statement st;
    private ResultSet rs;

    public ClassDaoImp() {
        connectDbLibrary = new ConnectDbLibrary();
    }
    
    @Override
    public List<GClass> getAllClass() {
        Connection conn = connectDbLibrary.getConnectMySQL();
        String sql = "SELECT ID ,NAME_CLASS, DATE_BEGIN,\n" +
                    "CASE\n" +
                    "	WHEN CHS.CLASSES_ID = CLASSES.ID THEN SOHOCVIEN\n" +
                    "    ELSE 0\n" +
                    "END AS SOLUONG\n" +
                    "FROM CLASSES\n" +
                    "left JOIN\n" +
                    "(\n" +
                    "	select CLASSES_ID, COUNT(CLASSES_ID) AS SOHOCVIEN\n" +
                    "	FROM classes_has_students\n" +
                    "	group by CLASSES_ID\n" +
                    ") CHS ON CHS.CLASSES_ID = CLASSES.ID";
        
        List<GClass> listClasses = new ArrayList<>();
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                GClass classes = new GClass(rs.getInt("ID"),
                                            rs.getString("NAME_CLASS"),
                                            rs.getInt("SOLUONG"),
                                            DateUtils.getLDateFromsDate(rs.getDate("DATE_BEGIN")), "Đang Diễn Ra");
                
                listClasses.add(classes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listClasses;
    }

    public static void main(String[] args) {
        ClassDaoImp cdi = new ClassDaoImp();
        List<GClass> classes = cdi.getAllClass();
        for(GClass c : classes){
            System.out.println("a: "+c.getId()+"\nb: "+c.getNameClass()+"\nS: "+c.getNumberSudent()+"\nc: "+c.getDATE_BEGIN()+"\nd: "+c.getStatus());
        }
    }
}
