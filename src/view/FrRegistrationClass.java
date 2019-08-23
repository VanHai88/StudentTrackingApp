/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ClassController;
import controller.ClassHasStudentController;
import entities.ClassHasStudent;
import entities.GClass;
import entities.Trainee;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

/**
 *
 * @author Van Hai
 */
public class FrRegistrationClass extends javax.swing.JFrame {
    private ClassController classController;
    private Trainee trainee;
    private ClassHasStudentController hasStudentController;
    /**
     * Creates new form FrRegistrationClass
     */

    public FrRegistrationClass(Trainee trainee) {
        classController  = new ClassController();
        hasStudentController = new ClassHasStudentController();
        this.trainee = trainee;
        initComponents();
        initData();
        initcomps();
        initEvent();
    }
    
    public void initcomps(){
       showPnInsertClass();
       if(pnLeft.getComponents().length == 0){
           pnTop.setVisible(false);
           pnCenter.setVisible(false);
       }
    }
    
    public void initEvent(){
        initPnTopEvent();
        initPnCenterEvent();
        initPnBottomEvent();
    }
    
    
    public void initData(){
        List<GClass> gClasseList = classController.getAllClass();
        GClass[] classes = gClasseList.toArray(new GClass[gClasseList.size()]);
        
        ComboBoxModel gclassModel = new DefaultComboBoxModel<>(classes);
        cbbClass.setModel(gclassModel);
    }
    
    public void initPnTopEvent(){
        initbtUpadteOrSave();
    }
    
    public void initbtUpadteOrSave(){
        btUpdateOrSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Component[] components = pnLeft.getComponents();
                List<GClass> gClassList = new ArrayList<>();
                        List<String> statusList = new ArrayList<>();
                for(Component component : components){
                    if(component instanceof JPanel){
                        JPanel panel = (JPanel) component;
                        Component[] comps = panel.getComponents();
                        GClass gClass = null;
                        String status = "";
                        
                        for(Component comp : comps){
                            if(comp instanceof JComboBox){
                                JComboBox comboBox = (JComboBox) comp;
                                if(comboBox.getName().equals("className")){
                                    gClass = (GClass) comboBox.getSelectedItem();
                                    gClassList.add(gClass);
                                }else if(comboBox.getName().equals("statusName")){
                                    status = (String) comboBox.getSelectedItem();
                                    statusList.add(status);
                                }
                            }
                        }
                        
                    }
                }
                    updateOrSave(gClassList, statusList);
                }
        });
    }

    public void updateOrSave(List<GClass> gClassList, List<String> statusList){
        if(btUpdateOrSave.getName().equals("update")){
            int[] result = hasStudentController.editClassHasStudent(trainee, gClassList, statusList);
                if(result.length > 0){
                    JOptionPane.showMessageDialog(null, "Cập Nhật Lớp lớp học Thành Công!!");
                }
        }else{
            int[] result  = hasStudentController.addClassHasStudent(trainee, gClassList, statusList);
                if(result.length > 0){
                    JOptionPane.showMessageDialog(null, "Đăng Ký Lớp Thành Công "+result.length+" lớp học!!");
                }
        }
    }
    
    public void initPnCenterEvent(){
        initCbbClassEvent();
    }
    
    public void initCbbClassEvent(){
        Component[] components = pnLeft.getComponents();
                for(Component component : components){
                    if(component instanceof JPanel){
                        JPanel panel = (JPanel) component;
                        Component[] comps = panel.getComponents();
                        for(Component comp : comps){
                            if(comp instanceof JComboBox){
                                JComboBox comboBox = (JComboBox) comp;
                                if(comboBox.getName().equals("className")){
                                    comboBox.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            GClass gClass = (GClass) comboBox.getSelectedItem();
                                            
                                            List<String> nameClassList = new ArrayList<>();
                                            nameClassList.add(gClass.getNameClass());
                                            
                                            ClassHasStudent chs = hasStudentController.getAllClassHasStudent(trainee, gClass);
                                            if(chs != null){
                                                JOptionPane.showMessageDialog(null, "Lớp Học Đã Được Đăng Ký!!");
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
    }
    
    public void initPnBottomEvent(){
        initBtAddEvent();
    }
    
    public void initBtAddEvent(){
        btAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Bạn Có Đồng Ý Thêm Một Lớp Học Mới!!", "Thêm Lớp Học", JOptionPane.YES_NO_OPTION)){
                    if(btAdd.getName().equals("open")){
                       pnLeft.removeAll();
                       createPnInsertClass(0,"");
                       initCbbClassEvent();
                       btUpdateOrSave.setText("Lưu Dữu Liệu");
                       btUpdateOrSave.setName("save");
                       pnTop.setVisible(true);
                       btAdd.setName("new");
                       revalidate();
                       repaint();
                    }else{
                        createPnInsertClass(0,"");
                        initCbbClassEvent();
                        btUpdateOrSave.setText("Lưu Dữu Liệu");
                        btUpdateOrSave.setName("save");
                        pnTop.setVisible(true);
                    }
                    
                }
            }
            
        });
    }
    
    public void showPnInsertClass(){
        List<ClassHasStudent> classHasStudents = hasStudentController.ClassHasStudentList(trainee);
        pnLeft.removeAll();
        for(ClassHasStudent chs : classHasStudents){
            createPnInsertClass(chs.getClassID(), chs.getStatusStudent());
        }
        revalidate();
        repaint();
    }
    
    public void createPnInsertClass(int idClass, String status){
        pnCenter.setVisible(true);
        pnInsertClass = new javax.swing.JPanel();
        cbbClass = new javax.swing.JComboBox<>();
        cbbStatus = new javax.swing.JComboBox<>();
       // btCancel = new javax.swing.JButton();
        pnInsertClass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 0)));
        pnInsertClass.setPreferredSize(new java.awt.Dimension(480, 185));

        initData();
        cbbClass.setName("className");
        cbbStatus.setName("statusName");
        cbbStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang Theo Học", "Tạm Dừng", "Đã Hoàn Thành" }));
        if(idClass > 0){
            cbbClass.setSelectedIndex(idClass-1);
            cbbStatus.setSelectedItem(status);
        }
        
        javax.swing.GroupLayout pnInsertClassLayout = new javax.swing.GroupLayout(pnInsertClass);
        pnInsertClass.setLayout(pnInsertClassLayout);
        pnInsertClassLayout.setHorizontalGroup(
            pnInsertClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInsertClassLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnInsertClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnInsertClassLayout.setVerticalGroup(
            pnInsertClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInsertClassLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cbbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(cbbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pnLeft.add(pnInsertClass);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTop = new javax.swing.JPanel();
        btUpdateOrSave = new javax.swing.JButton();
        pnCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnLeft = new javax.swing.JPanel();
        pnInsertClass = new javax.swing.JPanel();
        cbbClass = new javax.swing.JComboBox<>();
        cbbStatus = new javax.swing.JComboBox<>();
        pnBottom = new javax.swing.JPanel();
        btAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(167, 189));

        pnTop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 255, 0), new java.awt.Color(102, 255, 0), new java.awt.Color(102, 255, 0), new java.awt.Color(102, 255, 0)));
        pnTop.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        btUpdateOrSave.setBackground(new java.awt.Color(102, 255, 51));
        btUpdateOrSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btUpdateOrSave.setForeground(new java.awt.Color(255, 255, 255));
        btUpdateOrSave.setText("Cập Nhật Dữ Liệu");
        btUpdateOrSave.setBorderPainted(false);
        btUpdateOrSave.setFocusPainted(false);
        btUpdateOrSave.setName("update"); // NOI18N
        btUpdateOrSave.setPreferredSize(new java.awt.Dimension(150, 35));
        pnTop.add(btUpdateOrSave);

        getContentPane().add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnCenter.setLayout(new java.awt.BorderLayout());

        pnLeft.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 0), null), javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        pnLeft.setLayout(new java.awt.GridLayout(0, 1, 10, 10));

        pnInsertClass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 0)));
        pnInsertClass.setName(""); // NOI18N
        pnInsertClass.setPreferredSize(new java.awt.Dimension(480, 185));
        pnInsertClass.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 30));

        cbbClass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbClass.setName("className"); // NOI18N
        cbbClass.setPreferredSize(new java.awt.Dimension(400, 40));
        pnInsertClass.add(cbbClass);

        cbbStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang Theo Học", "Tạm Dừng", "Đã Hoàn Thành" }));
        cbbStatus.setName("statusName"); // NOI18N
        cbbStatus.setPreferredSize(new java.awt.Dimension(400, 40));
        pnInsertClass.add(cbbStatus);

        pnLeft.add(pnInsertClass);

        jScrollPane1.setViewportView(pnLeft);

        pnCenter.add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(pnCenter, java.awt.BorderLayout.CENTER);

        pnBottom.setPreferredSize(new java.awt.Dimension(661, 45));
        pnBottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        btAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btAdd.setText("Thêm Lớp Học");
        btAdd.setBorderPainted(false);
        btAdd.setFocusPainted(false);
        btAdd.setName("open"); // NOI18N
        btAdd.setPreferredSize(new java.awt.Dimension(150, 35));
        pnBottom.add(btAdd);

        getContentPane().add(pnBottom, java.awt.BorderLayout.PAGE_END);

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
            java.util.logging.Logger.getLogger(FrRegistrationClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrRegistrationClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrRegistrationClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrRegistrationClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new FrRegistrationClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btUpdateOrSave;
    private javax.swing.JComboBox<String> cbbClass;
    private javax.swing.JComboBox<String> cbbStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnInsertClass;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnTop;
    // End of variables declaration//GEN-END:variables
}
