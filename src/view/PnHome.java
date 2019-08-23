/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.PnHomeEnum;
import controller.CourseController;
import controller.TraineeController;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Van Hai
 */
public class PnHome extends javax.swing.JPanel {

    private CourseController courseController;
    private TraineeController traineeController;

    public PnHome() {
        courseController = new CourseController();
        traineeController = new TraineeController();
        initComponents();
        intComps();
        initEvent();
    }

    public void initEvent() {
        initPnHomeEvent();
    }

    public void initPnHomeEvent() {
        Component[] comps = this.getComponents();
        for (Component comp : comps) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        setLocationPannel(panel, 40);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        setLocationPannel(panel, 0);
                    }

                });
            }
        }
    }

    public void setLocationPannel(JPanel panel, int x) {
        PnHomeEnum text = PnHomeEnum.covertToEnum(panel.getName());
        switch (text) {
            case PNCOURSENAME:
                panel.setLocation(-5, 521 - x);
                break;
            case PNSTUDENTNAME:
                panel.setLocation(275, 521 - x);
                break;
            case PNLECTURER:
                panel.setLocation(555, 521 - x);
                break;
            case PNREPORT:
                panel.setLocation(835, 521 - x);
                break;
        }
    }

    public void intComps() {
        initPnCourse();
        initPnStudent();
    }

    public void initPnCourse() {
        int number = courseController.countCourse();
        lbNumberCourse.setText(number + "");
    }

    public void initPnStudent() {
        int number = traineeController.CountTrainee();
        lbNumberStudent.setText(number + "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnCourse = new javax.swing.JPanel();
        lbNumberCourse = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnStudent = new javax.swing.JPanel();
        lbNumberStudent = new javax.swing.JLabel();
        lbName4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnLecturer = new javax.swing.JPanel();
        lbNumberStudent1 = new javax.swing.JLabel();
        lbName5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnReport = new javax.swing.JPanel();
        lbName6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnCourse.setBackground(new java.awt.Color(255, 255, 255));
        pnCourse.setName("PNCOURSENAME"); // NOI18N
        pnCourse.setPreferredSize(new java.awt.Dimension(278, 222));
        pnCourse.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbNumberCourse.setFont(new java.awt.Font("Berlin Sans FB", 1, 36)); // NOI18N
        lbNumberCourse.setForeground(new java.awt.Color(255, 255, 255));
        lbNumberCourse.setText("100");
        pnCourse.add(lbNumberCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 80, -1));

        lbName.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Lớp Học");
        lbName.setPreferredSize(new java.awt.Dimension(100, 22));
        pnCourse.add(lbName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 150, 40));

        jLabel3.setBackground(new java.awt.Color(255, 255, 153));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/class8.jpg"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        pnCourse.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 300, 320));

        add(pnCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 521, 280, 220));

        pnStudent.setBackground(new java.awt.Color(255, 255, 255));
        pnStudent.setName("PNSTUDENTNAME"); // NOI18N
        pnStudent.setPreferredSize(new java.awt.Dimension(278, 222));
        pnStudent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbNumberStudent.setFont(new java.awt.Font("Berlin Sans FB", 1, 36)); // NOI18N
        lbNumberStudent.setForeground(new java.awt.Color(255, 255, 255));
        lbNumberStudent.setText("100");
        pnStudent.add(lbNumberStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 80, -1));

        lbName4.setBackground(new java.awt.Color(255, 255, 255));
        lbName4.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        lbName4.setForeground(new java.awt.Color(0, 0, 255));
        lbName4.setText("Học Viên");
        lbName4.setPreferredSize(new java.awt.Dimension(100, 22));
        pnStudent.add(lbName4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 150, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroudClass4.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 130));
        pnStudent.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, 360, 220));

        add(pnStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 521, 280, 220));

        pnLecturer.setBackground(new java.awt.Color(102, 255, 102));
        pnLecturer.setName("PNLECTURER"); // NOI18N
        pnLecturer.setPreferredSize(new java.awt.Dimension(278, 222));
        pnLecturer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbNumberStudent1.setFont(new java.awt.Font("Berlin Sans FB", 1, 36)); // NOI18N
        lbNumberStudent1.setForeground(new java.awt.Color(255, 255, 255));
        lbNumberStudent1.setText("100");
        pnLecturer.add(lbNumberStudent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 80, -1));

        lbName5.setBackground(new java.awt.Color(255, 255, 255));
        lbName5.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        lbName5.setForeground(new java.awt.Color(255, 255, 255));
        lbName5.setText("Giảng Viên");
        lbName5.setPreferredSize(new java.awt.Dimension(100, 22));
        pnLecturer.add(lbName5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 180, 40));

        jLabel4.setBackground(new java.awt.Color(102, 255, 51));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BLecture (2).png"))); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        pnLecturer.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-160, -70, 470, 360));

        add(pnLecturer, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 521, 280, 220));

        pnReport.setBackground(new java.awt.Color(102, 102, 255));
        pnReport.setName("PNREPORT"); // NOI18N
        pnReport.setPreferredSize(new java.awt.Dimension(278, 222));
        pnReport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbName6.setBackground(new java.awt.Color(255, 255, 255));
        lbName6.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        lbName6.setForeground(new java.awt.Color(0, 0, 255));
        lbName6.setText("Thống Kê");
        lbName6.setPreferredSize(new java.awt.Dimension(100, 22));
        pnReport.add(lbName6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 180, 40));

        jLabel5.setBackground(new java.awt.Color(102, 255, 51));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.jpg"))); // NOI18N
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        pnReport.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -210, 370, 460));

        add(pnReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 521, 280, 220));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backGroud1.jpg"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 750));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbName4;
    private javax.swing.JLabel lbName5;
    private javax.swing.JLabel lbName6;
    private javax.swing.JLabel lbNumberCourse;
    private javax.swing.JLabel lbNumberStudent;
    private javax.swing.JLabel lbNumberStudent1;
    private javax.swing.JPanel pnCourse;
    private javax.swing.JPanel pnLecturer;
    private javax.swing.JPanel pnReport;
    private javax.swing.JPanel pnStudent;
    // End of variables declaration//GEN-END:variables
}
