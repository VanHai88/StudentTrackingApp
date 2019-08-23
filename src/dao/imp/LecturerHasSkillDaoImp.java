/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.imp;

import dao.LecturerHasSkillDao;
import entities.LecturerHasSkill;
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
public class LecturerHasSkillDaoImp implements LecturerHasSkillDao{
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    private final ConnectDbLibrary connectDbLibrary;
    private final Connection conn;

    public LecturerHasSkillDaoImp() {
        this.connectDbLibrary = new ConnectDbLibrary();
        this.conn = connectDbLibrary.getConnectMySQL();
    }

    @Override
    public List<LecturerHasSkill> getAllSkills(int id) {
        List<LecturerHasSkill> lecturerHasSkills = new ArrayList<>();
        String sql = "SELECT skills.TECH, lecturers_has_skills.EXPY\n" +
                    "FROM skills\n" +
                    "LEFT JOIN lecturers_has_skills ON skills.ID =  lecturers_has_skills.SKILLS_ID\n" +
                    "WHERE LECTURERS_ID = ?;";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                LecturerHasSkill lecturerHasSkill = new LecturerHasSkill();
                lecturerHasSkill.setTech(rs.getString("skills.TECH"));
                lecturerHasSkill.setExp(rs.getInt("lecturers_has_skills.EXPY"));
                lecturerHasSkills.add(lecturerHasSkill);
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

        return lecturerHasSkills;
    }
    
}
