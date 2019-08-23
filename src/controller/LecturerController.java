/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Lecturer;
import java.util.List;
import service.LecturerService;
import service.imp.LecturerServiceImp;

/**
 *
 * @author Van Hai
 */
public class LecturerController {
    private LecturerService lecturerService;

    public LecturerController() {
        lecturerService = new LecturerServiceImp();
    }
    
    public List<Lecturer> getAllLecturers() {
        return lecturerService.getAllLecturers();
    }
    
    public static void main(String[] args) {
        LecturerController controller = new LecturerController();
    }
    
    public int save(Lecturer lecturer) {
        return lecturerService.save(lecturer);
    }
    
    public int edit(Lecturer lecturer) {
        return lecturerService.Edit(lecturer);
    }
    
    public Lecturer getLecturer(int id) {
        return lecturerService.getLecturer(id);
    }
}
