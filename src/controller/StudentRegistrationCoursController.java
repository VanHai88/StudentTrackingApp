/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.StudentRegistrationCours;
import java.time.LocalDate;
import java.util.List;
import service.StudentRegistrationCoursService;
import service.imp.StudentRegistrationCoursServiceImp;

/**
 *
 * @author Van Hai
 */
public class StudentRegistrationCoursController {
    private StudentRegistrationCoursService srcs;

    public StudentRegistrationCoursController() {
        srcs = new StudentRegistrationCoursServiceImp();
    }
    
    public List<StudentRegistrationCours> getAmountOfMonth(int month, int year) {
        return srcs.getAmountOfMonth(month, year);
    }
    
    public List<StudentRegistrationCours> getAmountOfYearth(int year) {
        return srcs.getAmountOfYearth(year);
    }
    
    public List<StudentRegistrationCours> getAmountBetweenTime(LocalDate from, LocalDate to) {
        return srcs.getAmountBetweenTime(from, to);
    }
}
