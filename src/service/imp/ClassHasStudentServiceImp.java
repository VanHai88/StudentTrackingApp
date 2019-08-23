/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imp;

import dao.ClassHasStudentDao;
import dao.imp.ClassHasStudentDaoImp;
import entities.ClassHasStudent;
import entities.GClass;
import entities.Trainee;
import java.util.List;
import service.ClassHasStudentService;

/**
 *
 * @author Van Hai
 */
public class ClassHasStudentServiceImp implements ClassHasStudentService{
    private ClassHasStudentDao classHasStudentDao;

    public ClassHasStudentServiceImp() {
        classHasStudentDao = new ClassHasStudentDaoImp();
    }
    
    @Override
    public int delClassHasStudent(Trainee trainee, GClass gClass) {
        return classHasStudentDao.delClassHasStudent(trainee, gClass);
    }

    @Override
    public int[] addClassHasStudent(Trainee trainee, List<GClass> gClassList, List<String> statusList) {
        return classHasStudentDao.addClassHasStudent(trainee, gClassList, statusList);
    }

    @Override
    public ClassHasStudent getAllClassHasStudent(Trainee trainee, GClass gClass){
        return classHasStudentDao.getAllClassHasStudent(trainee, gClass);
    }

    @Override
    public List<ClassHasStudent> ClassHasStudentList(Trainee trainee) {
        return classHasStudentDao.ClassHasStudentList(trainee);
    }

    @Override
    public int[] editClassHasStudent(Trainee trainee, List<GClass> gClassList, List<String> statusList) {
        return classHasStudentDao.editClassHasStudent(trainee, gClassList, statusList);
    }
    
}
