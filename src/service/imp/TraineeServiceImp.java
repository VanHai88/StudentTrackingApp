/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.imp;

import dao.TraineeDao;
import dao.imp.TraineeDaoImp;
import entities.Trainee;
import java.util.List;
import service.TraineeService;

/**
 *
 * @author Van Hai
 */
public class TraineeServiceImp implements TraineeService{
    private TraineeDao traineeDao;

    public TraineeServiceImp() {
        traineeDao = new TraineeDaoImp();
    }
    
    
    @Override
    public int CountTrainee() {
        return traineeDao.CountTrainee();
    }

    @Override
    public List<Trainee> trainees(int id) {
        return traineeDao.getStatusTrainees(id);
    }

    @Override
    public List<Trainee> getLimitTrainess(int offset, int rowcount) {
        return traineeDao.getLimitTrainess(offset, rowcount);
    }
    
}
