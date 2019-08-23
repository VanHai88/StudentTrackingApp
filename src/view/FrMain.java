
package view;

import common.ImageEnum;
import common.FrMainButtonEnum;
import common.PnHomeEnum;
import common.StaticData;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import utils.ImageUtils;

/**
 *
 * @author Van Hai
 */
public class FrMain extends javax.swing.JFrame {
    private PnHome pnHome;
    private PnStudent pnStudent;
    private PnReport pnReport;
    private PnLecturer pnLecturer;
    private Color checkColor;
    /**
     * Creates new form FrMain
     */
    public FrMain() {
        pnHome = new PnHome();
        pnStudent  = new PnStudent();
        pnReport = new PnReport();
        pnLecturer = new PnLecturer();
        initComponents();
        this.setIconImage(ImageUtils.createImage(ImageEnum.DESKTOP.getPath(), getClass()));
        this.setTitle(StaticData.TITLE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        this.setResizable(false);
        initWindowContents();
        initEvents();
    }
    
    public void initWindowContents(){
        
        pnCenter.add(pnHome);
        btHome.setBackground(Color.YELLOW);
        
    }

    public void initEvents(){
        initPnLeftEvent();
        initPnHomeEvent();
    }
    
    public void initPnHomeEvent(){
        Component[] comps = pnHome.getComponents();
        for(Component comp : comps){
            if(comp instanceof JPanel){
                JPanel panel = (JPanel) comp;
                 panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        pnCenter.removeAll();
                        PnHomeEnum name = PnHomeEnum.covertToEnum(panel.getName());
                        switch(name){
                            case PNCOURSENAME:
                                resetButtonColor();
                                btClasses.setBackground(Color.YELLOW);
                                PnClass pnClass = new PnClass();
                                pnCenter.add(pnClass);
                                break;
                            case PNSTUDENTNAME:
                                resetButtonColor();
                                btStudents.setBackground(Color.YELLOW);
                                pnCenter.add(pnStudent);
                                break;
                            case PNLECTURER:
                                resetButtonColor();
                                btLecturers.setBackground(Color.YELLOW);
                                pnCenter.add(pnLecturer);
                                break;
                            case PNREPORT:
                                resetButtonColor();
                                btReport.setBackground(Color.YELLOW);
                                pnCenter.add(pnReport);
                                break;
                        }
                        revalidate();
                        repaint();
                    }
                });
            }
        }
    }
    
    public void initPnLeftEvent(){
        initClickPnButtonEvent();
        onMouseEnterPnButton();
    }
    
    public void initClickPnButtonEvent(){
        Component[] comps = pnButton.getComponents();
        for(Component comp : comps){
            if(comp instanceof JButton){
                JButton button = (JButton) comp;
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        resetButtonColor();
                        button.setBackground(Color.YELLOW);
                        checkColor = button.getBackground();
                        pnCenter.removeAll();
                        FrMainButtonEnum text = FrMainButtonEnum.covertToEnum(button.getName());
                        switch(text){
                            case HOMEPAGE:
                                pnCenter.add(pnHome);
                                break;
                            case CLASSPAGE:
                                PnClass pnClass = new PnClass();
                                pnCenter.add(pnClass);
                                break;
                            case STUDENTPAGE:
                               pnCenter.add(pnStudent);
                               break;
                            case LECTURERPAGE:
                                pnCenter.add(pnLecturer);
                                break;
                            case REPORT:
                                pnCenter.add(pnReport);
                                break;
                        }
                        revalidate();
                        repaint();
                    }
                    
                });
            }
        }
    }
    
    public void onMouseEnterPnButton(){
        Component[] comps  = pnButton.getComponents();
        for(Component comp : comps){
            if(comp instanceof JButton){
                JButton button = (JButton) comp;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        checkColor = button.getBackground();
                        button.setBackground(Color.YELLOW);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        button.setBackground(checkColor);
                    }
                });
            }
        }
    }
    
    public void resetButtonColor(){
        Component[] comps = pnButton.getComponents();
        for(Component comp : comps){
            if(comp instanceof JButton){
                JButton button = (JButton) comp;
                button.setBackground(new Color(153, 255, 51));
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLeft = new javax.swing.JPanel();
        pnTop = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnButton = new javax.swing.JPanel();
        btHome = new javax.swing.JButton();
        btClasses = new javax.swing.JButton();
        btStudents = new javax.swing.JButton();
        btLecturers = new javax.swing.JButton();
        btReport = new javax.swing.JButton();
        pnBottom = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnCenter = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnLeft.setPreferredSize(new java.awt.Dimension(250, 525));
        pnLeft.setLayout(new java.awt.BorderLayout());

        pnTop.setBackground(new java.awt.Color(255, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mortarboard.png"))); // NOI18N
        jLabel1.setText("Quản Lý Học Viên");
        pnTop.add(jLabel1);

        pnLeft.add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnButton.setBackground(new java.awt.Color(0, 0, 0));
        pnButton.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        btHome.setBackground(new java.awt.Color(153, 255, 51));
        btHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/desktop.png"))); // NOI18N
        btHome.setText("Màn Hình Chính");
        btHome.setBorderPainted(false);
        btHome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btHome.setFocusPainted(false);
        btHome.setName("HOMEPAGE"); // NOI18N
        btHome.setPreferredSize(new java.awt.Dimension(225, 80));
        pnButton.add(btHome);

        btClasses.setBackground(new java.awt.Color(153, 255, 51));
        btClasses.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btClasses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sourses.png"))); // NOI18N
        btClasses.setText("Quản Lý Lớp Học");
        btClasses.setBorderPainted(false);
        btClasses.setFocusPainted(false);
        btClasses.setName("CLASSPAGE"); // NOI18N
        btClasses.setPreferredSize(new java.awt.Dimension(225, 80));
        pnButton.add(btClasses);

        btStudents.setBackground(new java.awt.Color(153, 255, 51));
        btStudents.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btStudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/student.png"))); // NOI18N
        btStudents.setText("Quản Lý Học Viên");
        btStudents.setBorderPainted(false);
        btStudents.setFocusPainted(false);
        btStudents.setName("STUDENTPAGE"); // NOI18N
        btStudents.setPreferredSize(new java.awt.Dimension(225, 80));
        pnButton.add(btStudents);

        btLecturers.setBackground(new java.awt.Color(153, 255, 51));
        btLecturers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btLecturers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lecture.png"))); // NOI18N
        btLecturers.setText("Quản Lý Giảng Viên");
        btLecturers.setBorderPainted(false);
        btLecturers.setFocusPainted(false);
        btLecturers.setName("LECTURERPAGE"); // NOI18N
        btLecturers.setPreferredSize(new java.awt.Dimension(225, 80));
        pnButton.add(btLecturers);

        btReport.setBackground(new java.awt.Color(153, 255, 51));
        btReport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        btReport.setText("Thống Kê Dữ Liệu");
        btReport.setBorderPainted(false);
        btReport.setFocusPainted(false);
        btReport.setName("REPORT"); // NOI18N
        btReport.setPreferredSize(new java.awt.Dimension(225, 80));
        pnButton.add(btReport);

        pnLeft.add(pnButton, java.awt.BorderLayout.CENTER);

        pnBottom.setBackground(new java.awt.Color(0, 0, 0));
        pnBottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banner 10.png"))); // NOI18N
        pnBottom.add(jLabel2);

        pnLeft.add(pnBottom, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(pnLeft, java.awt.BorderLayout.LINE_START);

        pnCenter.setLayout(new java.awt.BorderLayout());
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
                if ("windown".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClasses;
    private javax.swing.JButton btHome;
    private javax.swing.JButton btLecturers;
    private javax.swing.JButton btReport;
    private javax.swing.JButton btStudents;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnButton;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnTop;
    // End of variables declaration//GEN-END:variables
}
