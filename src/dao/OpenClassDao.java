/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.OpenClass;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Van Hai
 */
public interface OpenClassDao {
    List<OpenClass> getAmountOfMonth(int month, int year);
    List<OpenClass> getAmountOfYear(int year);
    List<OpenClass> getAmountBetweenTime(LocalDate from, LocalDate to);
}
