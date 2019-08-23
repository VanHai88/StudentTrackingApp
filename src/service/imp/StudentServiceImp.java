/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imp;

import dao.imp.StudentDaoImp;
import dao.StudentDao;
import entities.Student;
import entities.Trainee;
import java.util.List;
import service.StudentService;

/**
 *
 * @author Van Hai
 */
public class StudentServiceImp implements StudentService{
    private StudentDao studentDao;

    public StudentServiceImp() {
        studentDao = new StudentDaoImp();
    }
    
    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public int DelStudent(Student student) {
        return studentDao.DelStudent(student);
    }

    @Override
    public int editStudent(Student student) {
        return studentDao.editStudent(student);
    }

    @Override
    public int[] addStudentList(List<Student> students) {
        return studentDao.addStudentList(students);
    }
    
}
