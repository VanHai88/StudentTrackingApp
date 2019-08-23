/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.Trainee;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import utils.ImageUtils;

/**
 *
 * @author Van Hai
 */
public abstract class RdThread<T> extends Thread{
    private long selectionTime;
    private List<Trainee> arrNames;
    private String name = "";
    private Random rdName = new Random();
    private JButton button;
    private String[] arrImages;
    private JLabel lbMoney;
    private Class<T> tclass;
    private int WIDTH;
    private int HEIGHT;

    public RdThread(long selectionTime, List<Trainee> arrNames, JButton button, String[] arrImages, JLabel lbMoney, Class<T> tclass, int WIDTH, int HEIGHT) {
        this.selectionTime = selectionTime;
        this.arrNames = arrNames;
        this.button = button;
        this.arrImages = arrImages;
        this.lbMoney = lbMoney;
        this.tclass = tclass;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    @Override
    public void run() {
        rdNames();
    }
    
    private void rdNames(){
        final long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start < selectionTime){
        try {
            sleep(500);
            rdMoney();
            name = arrNames.get(rdName.nextInt(arrNames.size())).getFullName();
            System.out.println("name1: "+name);
            System.out.println("size2: "+arrNames.size());
            for(int i = 0; i < arrNames.size(); i++){
                if(arrNames.get(i).getFullName().equals(name)){
                    arrNames.remove(i);
                    System.out.println("size4: "+arrNames.size());
                }
            }
            transfer(name);
            if(arrNames.size() == 0){
                    break;
            }
        } catch (InterruptedException ex) {
            System.out.println("");
        }
    }
    }
    
    private void rdMoney(){
        String path = "";
        path = "/images/"+arrImages[rdName.nextInt(arrImages.length)]+".png";
        lbMoney.setIcon(ImageUtils.createImageIcon(tclass, path, WIDTH, HEIGHT));
    }
    
    abstract void transfer(String name);
}
