/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.NonStudent;
import java.util.List;

/**
 *
 * @author Van Hai
 */
public interface NonStudentDao {
    List<NonStudent> getAllNonStudents();
    int ediNonStudent(NonStudent nonStudent);
    int addNonStudent(NonStudent nonStudent);
    int[] addListNonStudent(List<NonStudent> nonStudents);
    int DelNonStudent(NonStudent nonStudent);
}
