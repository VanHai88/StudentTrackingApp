/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ClassController;
import entities.GClass;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Van Hai
 */
public class PnClass extends javax.swing.JPanel {
    private ClassController classController;
    private int cout = 0;
    
    public PnClass() {
        classController = new ClassController();
        initComponents();
        initComps();
        initEvent();
    }
    
    public void initComps(){
        initPnClass();
    }
    
    public void initPnClass(){
        List<GClass> listClass = classController.getAllClass();
        for(GClass gClass : listClass){
            JPanel pn = createPnClass(gClass.getId(),gClass.getNameClass(),gClass.getDATE_BEGIN()+"",gClass.getNumberSudent()+"");
            add(pn);
        }
    }
    
    public JPanel createPnClass(int id, String NameClass, String dateBegin, String NumberStudent){
        JPanel pnStatusClass = new JPanel();
        lbNameClass = new javax.swing.JLabel();
        lbdateBegin = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbNumberStudent = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        
        pnStatusClass.setName(id+"");

        setBackground(new java.awt.Color(153, 255, 153));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 40, 20));

        pnStatusClass.setPreferredSize(new java.awt.Dimension(233, 131));

        lbNameClass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNameClass.setText(NameClass+":");

        lbdateBegin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbdateBegin.setText(dateBegin);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Số Lượng:");

        lbNumberStudent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNumberStudent.setText(NumberStudent+" hv");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tình Trạng:");

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbStatus.setText("Đang diễn ra");

        javax.swing.GroupLayout pnStatusClassLayout = new javax.swing.GroupLayout(pnStatusClass);
        pnStatusClass.setLayout(pnStatusClassLayout);
        pnStatusClassLayout.setHorizontalGroup(
            pnStatusClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnStatusClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnStatusClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNameClass)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(pnStatusClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbdateBegin)
                    .addComponent(lbNumberStudent)
                    .addComponent(lbStatus))
                .addGap(36, 36, 36))
        );
        pnStatusClassLayout.setVerticalGroup(
            pnStatusClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnStatusClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnStatusClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNameClass)
                    .addComponent(lbdateBegin))
                .addGap(31, 31, 31)
                .addGroup(pnStatusClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbNumberStudent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(pnStatusClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbStatus))
                .addContainerGap())
        );

        return pnStatusClass;
    }
    
    public void initEvent(){
        initPnNumberClassEvent();
    }
    
    public void initPnNumberClassEvent(){
        onMouseEnterpnStatusClass();
    }
    
    private void onMouseEnterpnStatusClass(){
        Component[] comps = this.getComponents();
        for(Component comp : comps){
            if(comp instanceof JPanel){
                JPanel jPanel = (JPanel) comp;
                jPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        jPanel.setBorder(new LineBorder(new Color(204, 204, 204), 8));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        jPanel.setBorder(BorderFactory.createEmptyBorder());
                    }
                    
                    @Override
                        public void mousePressed(MouseEvent e) {
                            int id = Integer.valueOf(jPanel.getName());
                            FrRandomStudent frRandomStudent = new FrRandomStudent(id);
                            frRandomStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frRandomStudent.setVisible(true);
                        }
                });
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSClass = new javax.swing.JPanel();
        lbNameClass = new javax.swing.JLabel();
        lbdateBegin = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbNumberStudent = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 153));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pnSClass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 8));
        pnSClass.setPreferredSize(new java.awt.Dimension(233, 131));

        lbNameClass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNameClass.setText("JAVA01:");

        lbdateBegin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbdateBegin.setText("09/07/2019");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Số Lượng:");

        lbNumberStudent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNumberStudent.setText("12 hv");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tình Trạng:");

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbStatus.setText("Đang diễn ra");

        javax.swing.GroupLayout pnSClassLayout = new javax.swing.GroupLayout(pnSClass);
        pnSClass.setLayout(pnSClassLayout);
        pnSClassLayout.setHorizontalGroup(
            pnSClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNameClass)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(pnSClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbdateBegin)
                    .addComponent(lbNumberStudent)
                    .addComponent(lbStatus))
                .addGap(36, 36, 36))
        );
        pnSClassLayout.setVerticalGroup(
            pnSClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNameClass)
                    .addComponent(lbdateBegin))
                .addGap(31, 31, 31)
                .addGroup(pnSClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbNumberStudent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnSClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbStatus))
                .addContainerGap())
        );

        add(pnSClass);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbNameClass;
    private javax.swing.JLabel lbNumberStudent;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbdateBegin;
    private javax.swing.JPanel pnSClass;
    // End of variables declaration//GEN-END:variables
}
