/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp;

import dao.SkillDao;
import entities.Skill;
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
public class SkillDaoImp implements SkillDao{
    
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    private final ConnectDbLibrary connectDbLibrary;
    private final Connection conn;

    public SkillDaoImp() {
        this.connectDbLibrary = new ConnectDbLibrary();
        this.conn = connectDbLibrary.getConnectMySQL();
    }

    @Override
    public List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<>();
        String sql = "SELECT * FROM skills";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getInt("ID"));
                skill.setTech(rs.getString("TECH"));
                skills.add(skill);
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

        return skills;
    }
    
}
