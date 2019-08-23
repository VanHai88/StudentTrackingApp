/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Van Hai
 */
public class ClassHasStudent {
    private int studentID;
    private int typeStudent;
    private String statusStudent;
    private int classID;

    public ClassHasStudent(int studentID, int typeStudent, String statusStudent, int classID) {
        this.studentID = studentID;
        this.typeStudent = typeStudent;
        this.statusStudent = statusStudent;
        this.classID = classID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getTypeStudent() {
        return typeStudent;
    }

    public void setTypeStudent(int typeStudent) {
        this.typeStudent = typeStudent;
    }

    public String getStatusStudent() {
        return statusStudent;
    }

    public void setStatusStudent(String statusStudent) {
        this.statusStudent = statusStudent;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }
    
    
}
