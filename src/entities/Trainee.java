/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Van Hai
 */
// Trainee: hoc vien
public class Trainee {
    protected int id;
    protected String fullName;
    protected String phone;
    protected String addRess;
    protected String email;
    protected LocalDate dateOfBirth;
    protected String fbLink;
    protected String currentWorking;
    protected int type;
    protected String status;

    public Trainee() {
        
    }

    public Trainee(int id, String fullName, String phone, String addRess, String email, LocalDate dateOfBirth, String fbLink, String currentWorking, int type) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.addRess = addRess;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.fbLink = fbLink;
        this.currentWorking = currentWorking;
        this.type = type;
    }

    public Trainee(int id, String fullName, String phone, String addRess, String email, LocalDate dateOfBirth, String fbLink, String currentWorking) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.addRess = addRess;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.fbLink = fbLink;
        this.currentWorking = currentWorking;
    }
    
    public Trainee(int id) {
        this.id = id;
    }

    public Trainee(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
    
    public Trainee(String fullName) {
        this.fullName = fullName;
    }
    
    public Trainee(String fullName, String status) {
        this.fullName = fullName;
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddRess() {
        return addRess;
    }

    public void setAddRess(String addRess) {
        this.addRess = addRess;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    

    public String getFbLink() {
        return fbLink;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    public String getCurrentWorking() {
        return currentWorking;
    }

    public void setCurrentWorking(String currentWorking) {
        this.currentWorking = currentWorking;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null ||!(obj instanceof Trainee)){
            return false;
        }
        
        Trainee student = (Trainee) obj;
        return student.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    public void loadData() {
        System.out.println("Trainee{" + "id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", addRess=" + addRess + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", fbLink=" + fbLink + ", currentWorking=" + currentWorking + ", type=" + type + ", status=" + status + '}');
    }
    
}
