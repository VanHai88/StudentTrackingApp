/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imp;

import dao.StudentRegistrationCoursDao;
import dao.imp.StudentRegistrationCoursDaoImp;
import entities.StudentRegistrationCours;
import java.time.LocalDate;
import java.util.List;
import service.StudentRegistrationCoursService;

/**
 *
 * @author Van Hai
 */
public class StudentRegistrationCoursServiceImp implements StudentRegistrationCoursService{
    private StudentRegistrationCoursDao srcd;

    public StudentRegistrationCoursServiceImp() {
        srcd = new StudentRegistrationCoursDaoImp();
    }
    
    @Override
    public List<StudentRegistrationCours>getAmountOfMonth(int month, int year) {
        return srcd.getAmountOfMonth(month, year);
    }

    @Override
    public List<StudentRegistrationCours> getAmountOfYearth(int year) {
        return srcd.getAmountOfYearth(year);
    }

    @Override
    public List<StudentRegistrationCours> getAmountBetweenTime(LocalDate from, LocalDate to) {
        return srcd.getAmountBetweenTime(from, to);
    }
    
}
