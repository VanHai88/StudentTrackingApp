/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Lecturer;
import java.util.List;

/**
 *
 * @author Van Hai
 */
public interface LecturerDao {
    List<Lecturer> getAllLecturers();
    Lecturer getLecturer(int id);
    int save(Lecturer lecturer);
    int Edit(Lecturer lecturer);
}
