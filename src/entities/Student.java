/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author Van Hai
 */
public class Student extends Trainee{
    
    private int yearth;
    private String schoolName;

    public Student(int id, String fullName, String phone, String addRess, String email, LocalDate dateOfBirth, String fbLink, String currentWorking, int yearth, String schoolName, int type) {
        super(id, fullName, phone, addRess, email, dateOfBirth, fbLink, currentWorking, type);
        this.yearth = yearth;
        this.schoolName = schoolName;
    }

    public Student(int id, String fullName, String phone, String addRess, String email, LocalDate dateOfBirth, String fbLink, String currentWorking, int yearth, String schoolName) {
        super(id, fullName, phone, addRess, email, dateOfBirth, fbLink, currentWorking);
        this.yearth = yearth;
        this.schoolName = schoolName;
    }

    public Student(int id) {
        super(id);
    }

    public Student(String fullName) {
        super(fullName);
    }
    
    public int getYearth() {
        return yearth;
    }

    public void setYearth(int yearth) {
        this.yearth = yearth;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        super.loadData();
        return "Student{" + "yearth=" + yearth + ", schoolName=" + schoolName + '}';
    }
    
    
}
