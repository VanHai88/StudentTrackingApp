/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.StudentRegistrationCours;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Van Hai
 */
public interface StudentRegistrationCoursService {
    List<StudentRegistrationCours> getAmountOfMonth(int month, int year);
    List<StudentRegistrationCours> getAmountOfYearth(int year);
    List<StudentRegistrationCours> getAmountBetweenTime(LocalDate from, LocalDate to);
}
