/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.ClassHasStudent;
import entities.GClass;
import entities.Trainee;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import service.ClassHasStudentService;
import service.imp.ClassHasStudentServiceImp;

/**
 *
 * @author Van Hai
 */
public class ClassHasStudentController {
    private ClassHasStudentService classHasStudentService;

    public ClassHasStudentController() {
        classHasStudentService = new ClassHasStudentServiceImp();
    }
    
    public int delClassHasStudent(Trainee trainee, GClass gClass) {
        return classHasStudentService.delClassHasStudent(trainee, gClass);
    }
    
    public int[] addClassHasStudent(Trainee trainee, List<GClass> gClassList, List<String> statusList) {
        return classHasStudentService.addClassHasStudent(trainee, gClassList, statusList);
    }
    
    public ClassHasStudent getAllClassHasStudent(Trainee trainee, GClass gClass){
        return classHasStudentService.getAllClassHasStudent(trainee, gClass);
    }
    
    public List<ClassHasStudent> ClassHasStudentList(Trainee trainee) {
        return classHasStudentService.ClassHasStudentList(trainee);
    }
    
    public int[] editClassHasStudent(Trainee trainee, List<GClass> gClassList, List<String> statusList) {
        return classHasStudentService.editClassHasStudent(trainee, gClassList, statusList);
    }
}
