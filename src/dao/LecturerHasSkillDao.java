/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.LecturerHasSkill;
import java.util.List;

/**
 *
 * @author Van Hai
 */
public interface LecturerHasSkillDao {
    List<LecturerHasSkill> getAllSkills(int id);
}
