/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Trainee;
import java.util.List;
import service.TraineeService;
import service.imp.TraineeServiceImp;

/**
 *
 * @author Van Hai
 */
public class TraineeController {
    private final TraineeService traineeService;

    public TraineeController() {
        traineeService = new TraineeServiceImp();
    }
    
    public int CountTrainee() {
        return traineeService.CountTrainee();
    }
    
     public List<Trainee> getStatusTrainees(int id) {
         return traineeService.trainees(id);
     }
     
    public List<Trainee> getLimitTrainess(int offset, int rowcount) {
        return traineeService.getLimitTrainess(offset, rowcount);
    }
   
}
