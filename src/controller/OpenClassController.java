/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.OpenClass;
import java.time.LocalDate;
import java.util.List;
import service.imp.OpenClassServiceImp;

/**
 *
 * @author Van Hai
 */
public class OpenClassController {
    private OpenClassServiceImp classService;

    public OpenClassController() {
        classService = new OpenClassServiceImp();
    }

    public List<OpenClass> getAmountOfMonth(int month, int year){
        return  classService.getAmountOfMonth(month, year);
    }
    
    public List<OpenClass> getAmountOfYear(int year) {
        return classService.getAmountOfYear(year);
    }
    
    public List<OpenClass> getAmountBetweenTime(LocalDate from, LocalDate to) {
        return classService.getAmountBetweenTime(from, to);
    }
}
