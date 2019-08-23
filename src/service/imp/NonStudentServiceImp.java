/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imp;

import dao.NonStudentDao;
import dao.imp.NonStudentDaoImp;
import entities.NonStudent;
import java.util.List;
import service.NonStudentService;

/**
 *
 * @author Van Hai
 */
public class NonStudentServiceImp implements NonStudentService{
    private NonStudentDao nonStudentDao;

    public NonStudentServiceImp() {
        nonStudentDao = new NonStudentDaoImp();
    }
    
    @Override
    public List<NonStudent> getAllNonStudents() {
        return nonStudentDao.getAllNonStudents();
    }

    @Override
    public int addNonStudent(NonStudent nonStudent) {
        return nonStudentDao.addNonStudent(nonStudent);
    }

    @Override
    public int ediNonStudent(NonStudent nonStudent) {
        return nonStudentDao.ediNonStudent(nonStudent);
    }

    @Override
    public int DelNonStudent(NonStudent nonStudent) {
        return nonStudentDao.DelNonStudent(nonStudent);
    }

    @Override
    public int[] addListNonStudent(List<NonStudent> nonStudents) {
        return nonStudentDao.addListNonStudent(nonStudents);
    }
    
}
