/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imp;

import dao.ClassDao;
import dao.imp.ClassDaoImp;
import entities.GClass;
import java.util.List;
import service.ClassService;

/**
 *
 * @author Van Hai
 */
public class ClassServiceImp implements ClassService{
    private ClassDao classDao;

    public ClassServiceImp() {
        classDao = new ClassDaoImp();
    }

    @Override
    public List<GClass> getAllClass() {
        return classDao.getAllClass();
    }
    
    
}
