/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.LecturerController;
import entities.Lecturer;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.LecturerTable;
import utils.DateUtils;
import utils.ImageUtils;
import utils.StringUtils;

/**
 *
 * @author Van Hai
 */
public class FrInsertLecturer extends javax.swing.JFrame {
    private LecturerController lecturerController;
    private final String CURRENT_DIRECTORY ="D:\\nam3\\img2019\\minishop\\aosomi";
    private int IMAGE_WIDTH = 138;
    private int IMAGE_HEIGHT = 141;
    private File file;
    private LecturerTable lecturerTable;
    private int id;
    
    public FrInsertLecturer(LecturerTable lecturerTable, int id) {
        lecturerController = new LecturerController();
        this.lecturerTable = lecturerTable;
        this.id = id;
        initComponents();
        initEvent();
        initWindowContents();
    }
    
    public void initWindowContents(){
        btUndertake.setVisible(false);
        lbErrorEmail.setVisible(false);
        lbErrorWage.setVisible(false);
       
        if(id != 0){
            Lecturer lecturer =lecturerController.getLecturer(id);
            setLecturerInfor(lecturer);
            btUndertake.setVisible(true);
        }
    }

    public void initEvent(){
        btSaveDataEvent();
        btUploadEvent();
        initBtundertakeEvent();
    }
    
    public void btSaveDataEvent(){
        btSaveData.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Lecturer lecturer = getLecturerInfor();
                int lastLecturerId = lecturerController.save(lecturer);
                if(lastLecturerId > 0){
                    JOptionPane.showMessageDialog(null, "Thêm Giảng Viên Mới Thành Công!!");
                    lecturer.setId(lastLecturerId);
                    lecturerTable.firedTarget(lecturer);
                    FrInsertLecturer.this.setVisible(false);
                }
            }
        });
    }
    
    public void btUploadEvent(){
        btUpload.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //hien thi dialogue windowns de chon file
                JFileChooser fc = new JFileChooser(CURRENT_DIRECTORY);
                if(JFileChooser.APPROVE_OPTION == fc.showDialog(null, StringUtils.UPLOAD)){
                    file = fc.getSelectedFile();
                    if(file.getName().matches("[\\w-]+[.]{1}(?:png|jpg|jpeg|gif)")){
                        lbImage.setIcon(ImageUtils.createImageIcon(file.getAbsolutePath(),IMAGE_WIDTH, IMAGE_HEIGHT));
                    }
                }
            }
            
        });
    }
    
    public void initBtundertakeEvent(){
        btUndertake.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                FrSkill frSkill = new FrSkill(id);
                frSkill.setVisible(true);
                frSkill.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
            
        });
    }
    
    public void setLecturerInfor(Lecturer lecturer){
        NumberFormat formatter = new DecimalFormat();
        tfFullname.setText(lecturer.getFullName());
        dcDateOfBirth.setDate(DateUtils.getDateateFromsLD(lecturer.getDateOfBirth()));
        tfEmail.setText(lecturer.getEmail());
        taAddRess.setText(lecturer.getAddRess());
        tfWage.setText(formatter.format(lecturer.getWage())+"");
        cbbEvaluate.setSelectedIndex(lecturer.getEvaluate()-1);
        lbImage.setIcon(ImageUtils.createImageIcon(lecturer.getPathFile(),IMAGE_WIDTH, IMAGE_HEIGHT));
    }
    
    public Lecturer getLecturerInfor(){
        Lecturer lecturer = new Lecturer();
        lecturer.setFullName(tfFullname.getText());
        lecturer.setDateOfBirth(DateUtils.getLocalDate(dcDateOfBirth.getDate()));
        lecturer.setEmail(tfEmail.getText());
        lecturer.setAddRess(taAddRess.getText());
        lecturer.setWage(Double.valueOf(tfWage.getText()));
        lecturer.setEvaluate(Integer.valueOf(cbbEvaluate.getSelectedItem().toString()));
        lecturer.setPathFile(file.getAbsolutePath().toString());
        return lecturer;
    }
    
    public void resetInfor(){
        tfFullname.setText("");
        dcDateOfBirth.setDate(null);
        tfEmail.setText("");
        taAddRess.setText("");
        tfWage.setText("");
        cbbEvaluate.setSelectedItem("");
        lbImage.setIcon(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTop = new javax.swing.JPanel();
        pnLeft = new javax.swing.JPanel();
        btUndertake = new javax.swing.JButton();
        pnRight = new javax.swing.JPanel();
        btSaveData = new javax.swing.JButton();
        pnCenter = new javax.swing.JPanel();
        pnInforLeft = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfFullname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dcDateOfBirth = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        lbErrorEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAddRess = new javax.swing.JTextArea();
        pnInforRight = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tfWage = new javax.swing.JTextField();
        lbImage = new javax.swing.JLabel();
        btUpload = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbbEvaluate = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        lbErrorWage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnTop.setLayout(new java.awt.BorderLayout());

        pnLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));

        btUndertake.setBackground(new java.awt.Color(0, 255, 0));
        btUndertake.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btUndertake.setForeground(new java.awt.Color(255, 255, 255));
        btUndertake.setText("Kỹ Năng");
        btUndertake.setBorderPainted(false);
        btUndertake.setFocusPainted(false);
        btUndertake.setPreferredSize(new java.awt.Dimension(250, 40));
        pnLeft.add(btUndertake);

        pnTop.add(pnLeft, java.awt.BorderLayout.LINE_START);

        pnRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 20));

        btSaveData.setBackground(new java.awt.Color(0, 255, 0));
        btSaveData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btSaveData.setForeground(new java.awt.Color(255, 255, 255));
        btSaveData.setText("Lưu Dữ Liệu");
        btSaveData.setBorderPainted(false);
        btSaveData.setFocusPainted(false);
        btSaveData.setPreferredSize(new java.awt.Dimension(150, 40));
        pnRight.add(btSaveData);

        pnTop.add(pnRight, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnCenter.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10), javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Giảng Viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14)))); // NOI18N
        pnCenter.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã Giảng viên: ");

        tfID.setEditable(false);
        tfID.setBackground(new java.awt.Color(204, 204, 204));
        tfID.setText("####");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Họ Và Tên(*): ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Ngày Sinh(*):");

        dcDateOfBirth.setDateFormatString("dd/MM/yyyy");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Email(*): ");

        lbErrorEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbErrorEmail.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorEmail.setText("Email không hợp lệ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Địa Chỉ(*):");

        taAddRess.setColumns(20);
        taAddRess.setLineWrap(true);
        taAddRess.setRows(5);
        taAddRess.setTabSize(4);
        jScrollPane1.setViewportView(taAddRess);

        javax.swing.GroupLayout pnInforLeftLayout = new javax.swing.GroupLayout(pnInforLeft);
        pnInforLeft.setLayout(pnInforLeftLayout);
        pnInforLeftLayout.setHorizontalGroup(
            pnInforLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInforLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInforLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pnInforLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addGroup(pnInforLeftLayout.createSequentialGroup()
                        .addComponent(lbErrorEmail)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tfID)
                    .addComponent(tfFullname)
                    .addComponent(dcDateOfBirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfEmail))
                .addContainerGap())
        );
        pnInforLeftLayout.setVerticalGroup(
            pnInforLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInforLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInforLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnInforLeftLayout.createSequentialGroup()
                        .addGroup(pnInforLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(49, 49, 49)
                        .addGroup(pnInforLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addComponent(jLabel3))
                    .addComponent(dcDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(pnInforLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbErrorEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnInforLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnCenter.add(pnInforLeft, java.awt.BorderLayout.LINE_START);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Lương(*):");

        lbImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0), 2));

        btUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/upload.png"))); // NOI18N
        btUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUploadActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Đánh Giá:");

        cbbEvaluate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbEvaluate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        cbbEvaluate.setPreferredSize(new java.awt.Dimension(6, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Hình Ảnh:");

        lbErrorWage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbErrorWage.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorWage.setText("Dữ Liệu không hợp lệ");

        javax.swing.GroupLayout pnInforRightLayout = new javax.swing.GroupLayout(pnInforRight);
        pnInforRight.setLayout(pnInforRightLayout);
        pnInforRightLayout.setHorizontalGroup(
            pnInforRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInforRightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInforRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pnInforRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfWage)
                    .addComponent(cbbEvaluate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnInforRightLayout.createSequentialGroup()
                        .addGroup(pnInforRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbErrorWage)
                            .addGroup(pnInforRightLayout.createSequentialGroup()
                                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnInforRightLayout.setVerticalGroup(
            pnInforRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInforRightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInforRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(tfWage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbErrorWage)
                .addGap(20, 20, 20)
                .addGroup(pnInforRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbEvaluate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(pnInforRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(11, 11, 11))
        );

        pnCenter.add(pnInforRight, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btUploadActionPerformed

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
            java.util.logging.Logger.getLogger(FrInsertLecturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrInsertLecturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrInsertLecturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrInsertLecturer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new FrInsertLecturer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSaveData;
    private javax.swing.JButton btUndertake;
    private javax.swing.JButton btUpload;
    private javax.swing.JComboBox<String> cbbEvaluate;
    private com.toedter.calendar.JDateChooser dcDateOfBirth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbErrorEmail;
    private javax.swing.JLabel lbErrorWage;
    private javax.swing.JLabel lbImage;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnInforLeft;
    private javax.swing.JPanel pnInforRight;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnRight;
    private javax.swing.JPanel pnTop;
    private javax.swing.JTextArea taAddRess;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFullname;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfWage;
    // End of variables declaration//GEN-END:variables
}
