/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Student;
import java.util.List;

/**
 *
 * @author Van Hai
 */
public interface StudentDao {
    List<Student> getAllStudents();
    int addStudent(Student student);
    int[] addStudentList(List<Student> students);
    int editStudent(Student student);
    int DelStudent(Student student);
}
