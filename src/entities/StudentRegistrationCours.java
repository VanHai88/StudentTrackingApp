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
public class StudentRegistrationCours {
    private int amount;
    private String nameCours;

    public StudentRegistrationCours(int amount, String nameCours) {
        this.amount = amount;
        this.nameCours = nameCours;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNameCours() {
        return nameCours;
    }

    public void setNameCours(String nameCours) {
        this.nameCours = nameCours;
    }

    @Override
    public String toString() {
        return "StudentRegistrationCours{" + "amount=" + amount + ", nameCours=" + nameCours + '}';
    }
}
