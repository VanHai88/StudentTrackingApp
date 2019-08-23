/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.ClassHasStudent;
import entities.GClass;
import entities.Trainee;
import java.util.List;

/**
 *
 * @author Van Hai
 */
public interface ClassHasStudentService {
    List<ClassHasStudent> ClassHasStudentList(Trainee trainee);
    ClassHasStudent getAllClassHasStudent(Trainee trainee, GClass gClass);
    int[] addClassHasStudent(Trainee trainee, List<GClass> gClassList, List<String> statusList);
    int[] editClassHasStudent(Trainee trainee, List<GClass> gClassList, List<String> statusList);
    int delClassHasStudent(Trainee trainee, GClass gClass);
}
