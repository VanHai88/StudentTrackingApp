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
public class NonStudent extends Trainee{

    public NonStudent(int id, String fullName, String phone, String addRess, String email, LocalDate dateOfBirth, String fbLink, String currentWorking, int type) {
        super(id, fullName, phone, addRess, email, dateOfBirth, fbLink, currentWorking, type);
    }

    public NonStudent(int id) {
        super(id);
    }
    
}
