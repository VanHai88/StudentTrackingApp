/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.NonStudent;
import java.util.List;
import service.NonStudentService;
import service.imp.NonStudentServiceImp;

/**
 *
 * @author Van Hai
 */
public class NonStudentController {
    private NonStudentService nonStudentService;

    public NonStudentController() {
        nonStudentService = new NonStudentServiceImp();
    }
    
    public List<NonStudent> getAllNonStudents(){
        return nonStudentService.getAllNonStudents();
    }
    
    public int addNonStudent(NonStudent nonStudent) {
        return nonStudentService.addNonStudent(nonStudent);
    }
    
    public int ediNonStudent(NonStudent nonStudent) {
        return nonStudentService.ediNonStudent(nonStudent);
    }
    
    public int DelNonStudent(NonStudent nonStudent) {
        return nonStudentService.DelNonStudent(nonStudent);
    }
    
    public int[] addListNonStudent(List<NonStudent> nonStudents) {
        return nonStudentService.addListNonStudent(nonStudents);
    }
}
