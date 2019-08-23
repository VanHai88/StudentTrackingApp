/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Student;
import java.util.List;
import service.imp.StudentServiceImp;
import service.StudentService;

/**
 *
 * @author Van Hai
 */
public class StudentController {
    private final StudentService studentService;

    public StudentController() {
        studentService = new StudentServiceImp();
    }
    
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
    
    public int addStudent(Student student) {
        return studentService.addStudent(student);
    }
    
    public int DelStudent(Student student) {
        return studentService.DelStudent(student);
    }

    public int editStudent(Student student) {
        return studentService.editStudent(student);
    }
   
    public int[] addStudentList(List<Student> students) {
        return studentService.addStudentList(students);
    }
}
