/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 *
 * @author Van Hai
 */
public class Main implements Job{
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("Hurray !!! My Scheduler is running !!");
        System.out.println("The Time now is "+ new Date());
    }
    
}
