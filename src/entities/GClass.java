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
public class GClass {
    private int id;
    private String nameClass;
    private int numberSudent;
    private LocalDate DATE_BEGIN;
    private String status;

    public GClass() {
    }

    public GClass(int id, String nameClass, int numberSudent, LocalDate DATE_BEGIN, String status) {
        this.id = id;
        this.nameClass = nameClass;
        this.numberSudent = numberSudent;
        this.DATE_BEGIN = DATE_BEGIN;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public int getNumberSudent() {
        return numberSudent;
    }

    public void setNumberSudent(int numberSudent) {
        this.numberSudent = numberSudent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDATE_BEGIN() {
        return DATE_BEGIN;
    }

    public void setDATE_BEGIN(LocalDate DATE_BEGIN) {
        this.DATE_BEGIN = DATE_BEGIN;
    }

    @Override
    public String toString() {
        return nameClass;
    }
    
    
}
