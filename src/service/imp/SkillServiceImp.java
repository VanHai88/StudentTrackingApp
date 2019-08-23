/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imp;

import dao.SkillDao;
import dao.imp.SkillDaoImp;
import entities.Skill;
import java.util.List;
import service.SkillService;

/**
 *
 * @author Van Hai
 */
public class SkillServiceImp implements SkillService{
    private SkillDao skillDao;

    public SkillServiceImp() {
        skillDao = new SkillDaoImp();
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillDao.getAllSkills();
    }
    
    
}
