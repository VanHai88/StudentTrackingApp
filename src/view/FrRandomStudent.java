
package view;

import controller.TraineeController;
import entities.Trainee;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.Timer;
import utils.ImageUtils;

/**
 *
 * @author Van Hai
 */
public class FrRandomStudent extends javax.swing.JFrame {
    private List<Trainee> arrNames = new ArrayList<>();
    private Thread threadName;
    private String addName;
    private Timer timer;
    private JButton btName;
    private int id;
    private TraineeController traineeController;
    
    private String[] arrImages = {"10k","20k","500k","50k","5k"}; 
    
    public FrRandomStudent(int id) {
        traineeController = new TraineeController();
        this.id = id;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(709, 632);
        lbMoney.setIcon(ImageUtils.createImageIcon(FrRandomStudent.class,"/images/money.png", 675, 165));
        initComps();
        initEvents();
    }
    
    private void initComps(){
        loadData();
        addJbutton();
    }
    
    private void initEvents(){
       initEventsStart();
       initEnventShuffle();
    }
    
    private void initEventsStart(){
        btStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                List<Trainee> arrTemp = new ArrayList<>();
                arrTemp.addAll(arrNames);
                System.out.println("arrTemp "+arrTemp.size());
                if(threadName == null){
                    threadName = new RdThread(10000, arrTemp, btName, arrImages, lbMoney, FrRandomStudent.class, 675, 165) {
                        @Override
                        void transfer(String name) {
                            defaultButton();
                            Component[] comps = pnCenter.getComponents();
                            for(Component comp : comps){
                                if(comp instanceof JButton){
                                    JButton button = (JButton) comp;
                                    if(button.getText().equals(name)){
                                        button.setBackground(Color.red);
                                        button.setIcon(ImageUtils.createImageIcon(FrRandomStudent.class,"/images/haha.png", 20, 20));
                                    }
                                }
                            }
                        }
                    };
                    threadName.start();
                }else{
                    threadName.resume();
                    threadName = null;
                }
            }
        });
    }
    
    private void initEnventShuffle(){
        btShuffle.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pnCenter.removeAll();
                Collections.shuffle(arrNames);
                addJbutton();
                revalidate();
                repaint();
            }
        });
    }
    
    private void defaultButton(){
        Component[] comps = pnCenter.getComponents();
        for(Component comp : comps){
            JButton button = (JButton) comp;
            button.setBackground(new JButton().getBackground());
            button.setIcon(ImageUtils.createImageIcon(FrRandomStudent.class,"/images/heart.png", 20, 20));
            button.setBorder(null);
        }
    }
    
    private List<Trainee> loadData(){
        List<Trainee> trainees = traineeController.getStatusTrainees(id);
        arrNames.addAll(trainees);
        return arrNames;
    }
    
    private void addJbutton(){
        for(int i = 0; i < arrNames.size(); i++){
            btName = new JButton();
            String checkStatus = arrNames.get(i).getStatus();
            if(checkStatus.equals("Tạm Dừng")){
                btName.setText("<html><span style = 'color: red'><strike>"+arrNames.get(i).getFullName()+"</strike></span><html");
            }else{
                btName.setText(arrNames.get(i).getFullName());
            }
            
            
            btName.setPreferredSize(new Dimension(150, 80));
            btName.setIcon(ImageUtils.createImageIcon(FrRandomStudent.class,"/images/heart.png", 20, 20));
            pnCenter.add(btName);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTop = new javax.swing.JPanel();
        btStart = new javax.swing.JButton();
        btShuffle = new javax.swing.JButton();
        pnBottom = new javax.swing.JPanel();
        lbMoney = new javax.swing.JLabel();
        pnCenter = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnTop.setPreferredSize(new java.awt.Dimension(722, 120));
        pnTop.setLayout(new java.awt.GridLayout(1, 0, 400, 0));

        btStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/start64.png"))); // NOI18N
        btStart.setBorderPainted(false);
        btStart.setFocusPainted(false);
        pnTop.add(btStart);

        btShuffle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shuffle.png"))); // NOI18N
        btShuffle.setBorderPainted(false);
        btShuffle.setFocusPainted(false);
        pnTop.add(btShuffle);

        getContentPane().add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnBottom.setLayout(new java.awt.BorderLayout());

        lbMoney.setPreferredSize(new java.awt.Dimension(100, 185));
        pnBottom.add(lbMoney, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnBottom, java.awt.BorderLayout.PAGE_END);

        pnCenter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnCenter.setPreferredSize(new java.awt.Dimension(100, 288));
        pnCenter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        getContentPane().add(pnCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrRandomStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrRandomStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrRandomStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrRandomStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new FrRandomStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btShuffle;
    private javax.swing.JButton btStart;
    private javax.swing.JLabel lbMoney;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnTop;
    // End of variables declaration//GEN-END:variables
}
