/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Trainee;
import java.util.List;


/**
 *
 * @author Van Hai
 */
public interface TraineeDao {
    int CountTrainee();
    List<Trainee> getStatusTrainees(int id);
    List<Trainee> getLimitTrainess(int offset, int rowcount);
    
}
