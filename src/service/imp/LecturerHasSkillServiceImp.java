/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imp;

import dao.LecturerHasSkillDao;
import dao.imp.LecturerHasSkillDaoImp;
import entities.LecturerHasSkill;
import java.util.List;
import service.LecturerHasSkillService;

/**
 *
 * @author Van Hai
 */
public class LecturerHasSkillServiceImp implements LecturerHasSkillService{
    private LecturerHasSkillDao lecturerHasSkillDao;

    public LecturerHasSkillServiceImp() {
        lecturerHasSkillDao = new LecturerHasSkillDaoImp();
    }
    
    @Override
    public List<LecturerHasSkill> getAllSkills(int id) {
        return lecturerHasSkillDao.getAllSkills(id);
    }
    
}
