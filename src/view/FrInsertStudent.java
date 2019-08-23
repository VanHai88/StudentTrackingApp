
package view;

import entities.NonStudent;
import entities.Student;
import entities.Trainee;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.StudentTable;
import utils.DateUtils;

/**
 *
 * @author Van Hai
 */
public class FrInsertStudent extends javax.swing.JFrame {
    private StudentTable studentTable;
    private int row = -1;
    private Trainee trainee;
    private JTable tbStudent;
    private FrRegistrationClass frRegistrationClass;
    
    public FrInsertStudent(JTable tbStudent, Trainee trainee ,StudentTable studentTable, int row) {
        this.trainee = trainee;
        this.studentTable = studentTable;
        this.tbStudent = tbStudent;
        this.row = row;
        initComponents();
        this.setLocationRelativeTo(null);
        initEvent();
        initComp();
        initWindowContents();
    }
    
    public void initWindowContents(){
        lbErrorPhone.setVisible(false);
        lbErrorEmail.setVisible(false);
        rdMale.setSelected(true);
    }
    
    public void initComp(){
        initshowinforStudent();
    }
    
    public void initshowinforStudent(){
        pncbStudent.setVisible(false);
        btRegistration.setVisible(false);
        if(trainee != null){
            btRegistration.setVisible(true);
            setInforStudent();           
        }
    }
    
    public void initEvent(){
        initPnTopEvent();
        initPnCenter();
    }
    
    public void initPnTopEvent(){
        initBtSaveEvent();
        initBtRegistrationEvent();
    }
    
    public void initBtSaveEvent(){
        btSaveData.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               if(tfFullname.getText().equals("") || (dcDateOfBirth.getDate() == null) || tfPhone.getText().equals("") ||
                       tfEmail.getText().equals("") || tfLinkFB.getText().equals("") || taAddRess.getText().equals("")){
                   JOptionPane.showMessageDialog(null, "Phải nhập đầy đủ thông tin!!");
               }else{
                    if(trainee == null){
                        addInforStudent();
                    }else{
                        editInforStudent();
                    }
               }
            }
        });
    }
    
    public void initBtRegistrationEvent(){
        btRegistration.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                frRegistrationClass = new FrRegistrationClass(trainee);
                frRegistrationClass.setVisible(true);
                frRegistrationClass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
            
        });
    }
    
    public void initPnCenter(){
        initClickCbStudentEvent();
        initCheckInputInforSEvent();
    }
    
    public void initClickCbStudentEvent(){
        cbStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(cbStudent.isSelected()){
                    pncbStudent.setVisible(false);
                }else{
                    pncbStudent.setVisible(true);
                }
            }
            
        });
    }
    
    public void initCheckInputInforSEvent(){
        intitTfPhoeEvent();
        initTfEmailEvent();
    }
    
    public void intitTfPhoeEvent(){
        tfPhone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String phone = tfPhone.getText();
                if(!checkPhone(phone)){
                    lbErrorPhone.setVisible(true);
                }else{
                    lbErrorPhone.setVisible(false);
                }
            }
            
        });
    }
    
    public void initTfEmailEvent(){
        tfEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String email = tfEmail.getText();
                if(!checkEmail(email)){
                    lbErrorEmail.setVisible(true);
                }else{
                    lbErrorEmail.setVisible(false);
                }
            }
            
        });
    }
    
    public boolean checkPhone(String phone){
        return phone.matches("^[0-9]*$");
    }
    
    public boolean checkEmail(String dateOfBirth){
        return dateOfBirth.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    }
    
    private void addInforStudent(){
        String fullName = tfFullname.getText();
        LocalDate lcDateOfBirth = utils.DateUtils.getLocalDate(dcDateOfBirth.getDate());
        String phone = tfPhone.getText();
        String addRess = taAddRess.getText();
        String email = tfEmail.getText();
        String linkFB = tfLinkFB.getText();
        String wordKing = tfCurrentWorking.getText(); 
        int yearth = Integer.valueOf((String)cbbYearth.getSelectedItem());
        String School = tfSchooll.getText();
        Trainee student = new Student(0, fullName, phone, addRess, email, lcDateOfBirth, linkFB, wordKing, yearth, School, 1);
        Trainee nonStudent = new NonStudent(0, fullName, phone, addRess, email, lcDateOfBirth, linkFB, wordKing, 2);
        if(cbStudent.isSelected()){
            if(studentTable.addItem(student) > 0){
                JOptionPane.showMessageDialog(null, "Thêm Thành Công!!");
            }
        }else{
            if(studentTable.addItem(nonStudent) > 0){
                JOptionPane.showMessageDialog(null, "Thêm Thành Công!!");
            }
        }
        this.setVisible(false);
    }
    
    public void resetInforStudent(){
        tfFullname.setText("");
        tfPhone.setText("");
        tfEmail.setText("");
        tfLinkFB.setText("");
        taAddRess.setText("");
        tfCurrentWorking.setText("");
        cbStudent.setSelected(false);
        cbbYearth.setSelectedIndex(0);
        tfSchooll.setText("");
    }
    
    private void editInforStudent(){
        int id = Integer.valueOf(tfID.getName());
        String fullName = tfFullname.getText();
        LocalDate lcDateOfBirth = utils.DateUtils.getLocalDate(dcDateOfBirth.getDate());
        String phone = tfPhone.getText();
        String addRess = taAddRess.getText();
        String email = tfEmail.getText();
        String linkFB = tfLinkFB.getText();
        String wordKing = tfCurrentWorking.getText();
        row = tbStudent.getSelectedRow();
        
        if(cbStudent.isSelected()){
            int yearth = Integer.valueOf((String)cbbYearth.getSelectedItem());
            String School = tfSchooll.getText();
            Trainee student = new Student(id, fullName, phone, addRess, email, lcDateOfBirth, linkFB, wordKing, yearth, School, 1);
            if(studentTable.editItem(row, student) > 0){
                JOptionPane.showMessageDialog(null, "Sửa thành công");
            }
        }else{
            Trainee nonStudent = new NonStudent(id, fullName, phone, addRess, email, lcDateOfBirth, linkFB, wordKing, 2);
            if(studentTable.editItem(row,nonStudent) > 0){
                JOptionPane.showMessageDialog(null, "Sửa Thành Công!!");
            }
        }
        
    }
    
    public void setInforStudent(){
        int id = trainee.getId();
        String fullName = trainee.getFullName();
        Date DateOfBirth = DateUtils.getDateateFromsLD(trainee.getDateOfBirth());
        String email = trainee.getEmail();
        String linhFB = trainee.getFbLink();
        String addRess = trainee.getAddRess();
        String working = trainee.getCurrentWorking();
        String phone =trainee.getPhone();
        int yearth = 0;
        String Schooll ="";
        if(trainee instanceof Student){
            Student student = (Student) trainee;
            yearth =  student.getYearth();
            Schooll = student.getSchoolName();
        }
        cbStudent.setVisible(false);
        if(trainee.getType() == 1){
            cbStudent.setSelected(true);
            pncbStudent.setVisible(true);
        }
        
        tfID.setName(id+"");
        tfFullname.setText(fullName);
        dcDateOfBirth.setDate(DateOfBirth);
        tfPhone.setText(phone);
        tfEmail.setText(email);
        tfLinkFB.setText(linhFB);
        taAddRess.setText(addRess);
        tfCurrentWorking.setText(working);
        cbbYearth.setSelectedIndex(yearth-1);
        tfSchooll.setText(Schooll);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgrGender = new javax.swing.ButtonGroup();
        pnTop = new javax.swing.JPanel();
        pnRight = new javax.swing.JPanel();
        btSaveData = new javax.swing.JButton();
        pnLeft = new javax.swing.JPanel();
        btRegistration = new javax.swing.JButton();
        pnCenter = new javax.swing.JPanel();
        pnleftStudent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfFullname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dcDateOfBirth = new com.toedter.calendar.JDateChooser();
        tfPhone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rdMale = new javax.swing.JRadioButton();
        rdFemale = new javax.swing.JRadioButton();
        lbErrorPhone = new javax.swing.JLabel();
        lbErrorEmail = new javax.swing.JLabel();
        pnStudentRight = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tfLinkFB = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAddRess = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        tfCurrentWorking = new javax.swing.JTextField();
        cbStudent = new javax.swing.JCheckBox();
        pncbStudent = new javax.swing.JPanel();
        cbbYearth = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfSchooll = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnTop.setLayout(new java.awt.BorderLayout());

        pnRight.setPreferredSize(new java.awt.Dimension(500, 80));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 20);
        flowLayout1.setAlignOnBaseline(true);
        pnRight.setLayout(flowLayout1);

        btSaveData.setBackground(new java.awt.Color(0, 255, 0));
        btSaveData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btSaveData.setForeground(new java.awt.Color(255, 255, 255));
        btSaveData.setText("Lưu Dữ Liệu");
        btSaveData.setBorderPainted(false);
        btSaveData.setFocusPainted(false);
        btSaveData.setPreferredSize(new java.awt.Dimension(150, 40));
        pnRight.add(btSaveData);

        pnTop.add(pnRight, java.awt.BorderLayout.LINE_END);

        pnLeft.setPreferredSize(new java.awt.Dimension(100, 80));
        pnLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));

        btRegistration.setBackground(new java.awt.Color(0, 255, 0));
        btRegistration.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btRegistration.setForeground(new java.awt.Color(255, 255, 255));
        btRegistration.setText("Đăng Ký Lớp Học");
        btRegistration.setBorderPainted(false);
        btRegistration.setFocusPainted(false);
        btRegistration.setPreferredSize(new java.awt.Dimension(250, 40));
        pnLeft.add(btRegistration);

        pnTop.add(pnLeft, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnCenter.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10), javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Học Viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14)))); // NOI18N
        pnCenter.setLayout(new java.awt.BorderLayout());

        pnleftStudent.setPreferredSize(new java.awt.Dimension(480, 718));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mã học viên: ");

        tfID.setEditable(false);
        tfID.setBackground(new java.awt.Color(204, 204, 204));
        tfID.setText("####");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Họ Và Tên(*): ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Ngày Sinh(*):");

        dcDateOfBirth.setDateFormatString("dd/MM/yyyy");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Số Điện Thoại(*): ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Email(*): ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Giới Tính:");

        btgrGender.add(rdMale);
        rdMale.setText("Nam");

        btgrGender.add(rdFemale);
        rdFemale.setText("Nữ");

        lbErrorPhone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbErrorPhone.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorPhone.setText("Số Điện Không hợp lệ!!");

        lbErrorEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbErrorEmail.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorEmail.setText("Email không hợp lệ");

        javax.swing.GroupLayout pnleftStudentLayout = new javax.swing.GroupLayout(pnleftStudent);
        pnleftStudent.setLayout(pnleftStudentLayout);
        pnleftStudentLayout.setHorizontalGroup(
            pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnleftStudentLayout.createSequentialGroup()
                .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnleftStudentLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbErrorPhone)
                            .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnleftStudentLayout.createSequentialGroup()
                                    .addComponent(rdMale)
                                    .addGap(43, 43, 43)
                                    .addComponent(rdFemale))
                                .addComponent(lbErrorEmail))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnleftStudentLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 36, Short.MAX_VALUE)
                        .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dcDateOfBirth, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                            .addComponent(tfID)
                            .addComponent(tfFullname))))
                .addGap(20, 20, 20))
        );
        pnleftStudentLayout.setVerticalGroup(
            pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnleftStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(54, 54, 54)
                .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(tfFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(dcDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbErrorPhone)
                .addGap(22, 22, 22)
                .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbErrorEmail)
                .addGap(14, 14, 14)
                .addGroup(pnleftStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(rdMale)
                    .addComponent(rdFemale))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pnCenter.add(pnleftStudent, java.awt.BorderLayout.LINE_START);

        pnStudentRight.setPreferredSize(new java.awt.Dimension(480, 481));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("LinhkFB(*): ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Địa Chỉ(*):");

        taAddRess.setColumns(20);
        taAddRess.setLineWrap(true);
        taAddRess.setRows(5);
        taAddRess.setTabSize(4);
        jScrollPane1.setViewportView(taAddRess);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Công Việc(*): ");

        cbStudent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbStudent.setText("Sinh Viên");

        cbbYearth.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbYearth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Năm:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Trường(*): ");

        javax.swing.GroupLayout pncbStudentLayout = new javax.swing.GroupLayout(pncbStudent);
        pncbStudent.setLayout(pncbStudentLayout);
        pncbStudentLayout.setHorizontalGroup(
            pncbStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pncbStudentLayout.createSequentialGroup()
                .addGroup(pncbStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(pncbStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfSchooll)
                    .addComponent(cbbYearth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pncbStudentLayout.setVerticalGroup(
            pncbStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pncbStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pncbStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(cbbYearth, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(pncbStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(tfSchooll, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnStudentRightLayout = new javax.swing.GroupLayout(pnStudentRight);
        pnStudentRight.setLayout(pnStudentRightLayout);
        pnStudentRightLayout.setHorizontalGroup(
            pnStudentRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnStudentRightLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnStudentRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pncbStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnStudentRightLayout.createSequentialGroup()
                        .addComponent(cbStudent)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnStudentRightLayout.createSequentialGroup()
                        .addGroup(pnStudentRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnStudentRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfCurrentWorking, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnStudentRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                .addComponent(tfLinkFB)))))
                .addContainerGap())
        );
        pnStudentRightLayout.setVerticalGroup(
            pnStudentRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnStudentRightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnStudentRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(tfLinkFB, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pnStudentRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(pnStudentRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfCurrentWorking, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(cbStudent)
                .addGap(18, 18, 18)
                .addComponent(pncbStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pnCenter.add(pnStudentRight, java.awt.BorderLayout.CENTER);

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
            java.util.logging.Logger.getLogger(FrInsertStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrInsertStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrInsertStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrInsertStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               //new FrInsertStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRegistration;
    private javax.swing.JButton btSaveData;
    private javax.swing.ButtonGroup btgrGender;
    private javax.swing.JCheckBox cbStudent;
    private javax.swing.JComboBox<String> cbbYearth;
    private com.toedter.calendar.JDateChooser dcDateOfBirth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbErrorEmail;
    private javax.swing.JLabel lbErrorPhone;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnRight;
    private javax.swing.JPanel pnStudentRight;
    private javax.swing.JPanel pnTop;
    private javax.swing.JPanel pncbStudent;
    private javax.swing.JPanel pnleftStudent;
    private javax.swing.JRadioButton rdFemale;
    private javax.swing.JRadioButton rdMale;
    private javax.swing.JTextArea taAddRess;
    private javax.swing.JTextField tfCurrentWorking;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFullname;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfLinkFB;
    private javax.swing.JTextField tfPhone;
    private javax.swing.JTextField tfSchooll;
    // End of variables declaration//GEN-END:variables
}
