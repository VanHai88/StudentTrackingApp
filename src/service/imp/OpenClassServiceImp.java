/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imp;

import dao.OpenClassDao;
import dao.imp.OpenClassDaoImp;
import entities.OpenClass;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Van Hai
 */
public class OpenClassServiceImp implements OpenClassDao{
    private OpenClassDao classDao;

    public OpenClassServiceImp() {
        classDao = new OpenClassDaoImp();
    }
    
    public List<OpenClass> getAmountOfMonth(int month, int year){
        return  classDao.getAmountOfMonth(month, year);
    }

    @Override
    public List<OpenClass> getAmountOfYear(int year) {
        return classDao.getAmountOfYear(year);
    }

    @Override
    public List<OpenClass> getAmountBetweenTime(LocalDate from, LocalDate to) {
        return classDao.getAmountBetweenTime(from, to);
    }
            
}
