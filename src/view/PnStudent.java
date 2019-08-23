/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ClassHasStudentController;
import controller.NonStudentController;
import controller.StudentController;
import controller.TraineeController;
import dao.StudentDao;
import entities.NonStudent;
import entities.Student;
import entities.Trainee;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.StudentTable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.DateUtils;

/**
 *
 * @author Van Hai
 */
public class PnStudent extends javax.swing.JPanel {
    private FrInsertStudent frInsertStudent;
    private StudentTable studentTable;
    private Trainee trainee;
    private TraineeController traineeController;
    private StudentController studentController; 
    private NonStudentController nonStudentController;
    private ClassHasStudentController classHasStudentController;
    private JLabel lbPage;
    private final String CURRENT_DIRECTORY ="D:\\nam4\\JAVA\\File_test";
    int row = -1;
    double page = 0;
    double offset = 0;
    double rowcount = 8;
    int locationPage = 1;
    
    public PnStudent() {
        initComponents();
        traineeController = new TraineeController();
        studentController = new StudentController();
        nonStudentController = new NonStudentController();
        classHasStudentController = new ClassHasStudentController();
        studentTable = new StudentTable(tbStudent);
        studentTable.loadTable();
        initComps();
        initEvent();
        initWindowContents();
    }
    
    public void initComps(){
        initPnBottom();
    }
    
    public void initWindowContents(){
        Component[] components = pnPageCenter.getComponents();
            for(Component component : components){
                if(component instanceof JLabel){
                        JLabel label = (JLabel) component;
                        if(Integer.valueOf(label.getText()) == 1){
                            label.setFont(new Font("tahoma", Font.BOLD, 18));
                            label.setForeground(Color.red);
                         }
                }
            }
        lbNext.setName("1");
        lbBack.setVisible(false);
        lbFirstPage.setVisible(false);
        cbStudent.setSelected(true);
    }
    
    public void initPnBottom(){
        createPage();
    }
    
    public void createPage(){
        int record = traineeController.CountTrainee();
        page = record / rowcount;
        for(int i = 1; i <= Math.ceil(page); i++){
            lbPage = new JLabel();
            lbPage.setText(i+"");
            lbPage.setFont(new Font("tahoma", Font.PLAIN, 14));
            lbPage.setForeground(Color.BLACK);
            pnPageCenter.add(lbPage);
        }
    }

    public void initEvent(){
        initPnTopEvent();
        initPnBottomEvent();
    }
    
    public void initPnTopEvent(){
        initClickBtInsertEvent();
        initTfShearchEvent();
        initCbStudentEvent();
        initClickBtExport();
        initClickBtInport();
    }
    
    public void initClickBtInsertEvent(){
        btInsert.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                trainee = null;
                if(trainee == null){
                    newFrInsertStudent();
                }
            }
        });
    }
    
    public void initTfShearchEvent(){
        tfSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                TableRowSorter sorter = new TableRowSorter(tbStudent.getModel());
                tbStudent.setRowSorter(sorter);
                
               // ArrayList<RowFilter<AbstractTableModel, Object>> filters = new ArrayList<>();
                String ShearchName = tfSearch.getText();
                
                
                RowFilter<AbstractTableModel, Object> filterName = RowFilter.regexFilter("(?i)"+ShearchName, 1);
               // filters.add(filterName);
                sorter.setRowFilter(filterName);
                
               
            }
            }
        );
    }
    
    public void initCbStudentEvent(){
        cbStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                
                TableRowSorter sorter = new TableRowSorter(tbStudent.getModel());
                tbStudent.setRowSorter(sorter);
                
                boolean checkStudent = cbStudent.isSelected();
                System.out.println("test: "+ checkStudent);
                 if(checkStudent){
                    RowFilter<AbstractTableModel, Object> fiterCheckStudent = RowFilter.regexFilter(String.valueOf(checkStudent), 9);
                    sorter.setRowFilter(fiterCheckStudent);
                    //filters.add(fiterCheckStudent);
                }
                //RowFilter<AbstractTableModel, Object> andFilter = RowFilter.andFilter(filters);
                //sorter.setRowFilter(andFilter);
            }
            
        });
    }
    
    public void initClickBtInport(){
        btImporting.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                importingExcel();
            }
        });
    }
    
    public void initClickBtExport(){
        btRepot.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exportExcel();
            }
        });
    }
    
    public void exportExcel(){
        TableModel model = tbStudent.getModel();

        JFileChooser chooser = new JFileChooser(CURRENT_DIRECTORY);
        chooser.getSelectedFile();
        int i = chooser.showSaveDialog(chooser);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String EXCEL_FILE_LOCATION = file + ".xls";
            WritableWorkbook mySecondWbook = null;
        try {
            mySecondWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));
            WritableSheet myFirstSheet = mySecondWbook.createSheet("Sheet 1", 0);

            WritableCellFormat cFormat = new WritableCellFormat();
            WritableFont font = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
            cFormat.setFont(font);
            Label label;
            
            for (int j = 0; j < model.getColumnCount(); j++) {
                Label column = new Label(j, 0, model.getColumnName(j),cFormat);
                myFirstSheet.addCell(column);
            }
            
            for (int j=0;j<model.getRowCount();j++) {
                for (int k=0;k<tbStudent.getColumnCount();k++) {
                    if(!(model.getValueAt(j,k) instanceof JButton)){
                        String val = String.valueOf(model.getValueAt(j,k));
                        label = new Label(k, j+1, val);
                        myFirstSheet.addCell(label);
                    }
                }
            }
            
            mySecondWbook.write();
            mySecondWbook.close();
            JOptionPane.showMessageDialog(null, "Xuất dữ liệu thành công", "Message", JOptionPane.INFORMATION_MESSAGE);
            File readFile = chooser.getSelectedFile();
            File tempFile = new File(readFile.getPath()+".xls");
            Desktop.getDesktop().open(tempFile);
            } catch (WriteException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi!", "Message", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi!", "Message", JOptionPane.INFORMATION_MESSAGE);
            } finally {
                
            }
        }
        else {
            System.out.println("K export dc");
        }
    }
    
    public void importingExcel(){
        
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        HSSFWorkbook excelImportToJTable = null;
        String defaultCurrentDirectoryPath = "D:\\nam4\\JAVA\\File_test";
        JFileChooser excelFileChooser = new JFileChooser(defaultCurrentDirectoryPath);
        excelFileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                List<Student> students = new ArrayList<>();
                List<NonStudent> nonStudents = new ArrayList<>();
                
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelImportToJTable = new HSSFWorkbook(excelFIS);
                HSSFSheet excelSheet = excelImportToJTable.getSheetAt(0);
 
                for (int row = 0; row <= excelSheet.getLastRowNum(); row++) {
                    HSSFRow excelRow = excelSheet.getRow(row);
 
                    HSSFCell excelFullName = excelRow.getCell(0);
                    HSSFCell excelPhone = excelRow.getCell(1);
                    HSSFCell excelAddress = excelRow.getCell(2);
                    HSSFCell excelEmail = excelRow.getCell(3); 
                    HSSFCell excelDateOfBirth = excelRow.getCell(4);
                    HSSFCell excelLinkFB = excelRow.getCell(5);
                    HSSFCell excelyearth = excelRow.getCell(6);
                    HSSFCell excelSchool = excelRow.getCell(7);
                    HSSFCell excelwordKing = excelRow.getCell(8);
                    HSSFCell excelType = excelRow.getCell(9);
                 
                    String fullName = excelFullName.getStringCellValue();
                    String phone = String.valueOf((int)excelPhone.getNumericCellValue());
                    String addRess = excelAddress.getStringCellValue();
                    String gmail = excelEmail.getStringCellValue();
                    LocalDate dateOfBirth = DateUtils.getLocalDate(excelDateOfBirth.getDateCellValue());
                    String linkFB = excelLinkFB.getStringCellValue();
                    int yearth = (int)excelyearth.getNumericCellValue();
                    String school = excelSchool.getStringCellValue();
                    String wordking = excelwordKing.getStringCellValue();
                    int type = (int) excelType.getNumericCellValue();
                    
                    if(type == 1){
                        Student student = new Student(0, fullName, phone, addRess, gmail, dateOfBirth, linkFB, wordking, yearth, school, type);
                        students.add(student);
                    } else {
                        NonStudent nonStudent = new NonStudent(0, fullName, phone, addRess, gmail, dateOfBirth, linkFB, wordking, type);
                        nonStudents.add(nonStudent);
                    }
                }
                
                int[] resultStudent = studentController.addStudentList(students);
                int[] resultNonStudent = nonStudentController.addListNonStudent(nonStudents);
                int result = resultStudent.length + resultNonStudent.length;
                if(result > 0){
                    JOptionPane.showMessageDialog(null, "Import Thành Công !!.....");
                }
                
            } catch (IOException iOException) {
                JOptionPane.showMessageDialog(null, iOException.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelImportToJTable != null) {
                        excelImportToJTable.close();
                    }
                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(null, iOException.getMessage());
                }
            }
        }
    
    }
    
    public void initPnBottomEvent(){
       initPnpageLeftCenter();
       initPnPageCenter();
       initPnpageRightCenter();
   } 
    
    public void initPnpageLeftCenter(){
       Component[] comps = pnPageLeft.getComponents();
        for(Component comp : comps){
            if(comp instanceof JLabel){
                JLabel label = (JLabel) comp;
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        resetLable();
                       
                            if(label.getText().equals("<") && (locationPage > 1)){
                            nextBackPage(label, "-");
                            }
                            if(label.getText().equals("<<") && (locationPage > 1)){
                                Component[] components = pnPageCenter.getComponents();
                                for(Component component : components){
                                    if(component instanceof JLabel){
                                        JLabel label1 = (JLabel) component;
                                        if( Integer.valueOf(label1.getText()) == 1){
                                            label1.setFont(new Font("tahoma", Font.BOLD, 18));
                                            label1.setForeground(Color.RED);
                                        }
                                    }
                                }
                                    firstLastPage(label, 1);
                            }
                        if(locationPage == 1){
                            start();
                        }else{
                            startEndPage();  
                        }
                   }
                });
            }
        }
   }
    
    public void initPnPageCenter(){
        Component[] comps = pnPageCenter.getComponents();
        for(Component comp : comps){
            if(comp instanceof JLabel){
                JLabel label = (JLabel) comp;
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        resetLable();
                        locationPage = Integer.valueOf(label.getText());
                        lbNext.setName(locationPage+"");
                        lbBack.setName(locationPage+"");
                        label.setFont(new Font("tahoma", Font.BOLD, 18));
                        label.setForeground(Color.red);
                        transferPage(locationPage);
                        if(locationPage == 1){
                            start();
                        }
                        if(locationPage != 1 && locationPage != (int)Math.ceil(page)){
                            startEndPage();
                        }
                        if(locationPage == (int)Math.ceil(page)){
                            end();
                        }
                   }
                });
            }
        }
    }
    
    public void initPnpageRightCenter(){
        Component[] comps = pnPageRight.getComponents();
        for(Component comp : comps){
            if(comp instanceof JLabel){
                JLabel label = (JLabel) comp;
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        resetLable();
                        
                        if(label.getText().equals(">") &&(locationPage > 0) && (locationPage < Math.ceil(page))){
                            nextBackPage(label, "+");
                        }
                        if(label.getText().equals(">>") &&(locationPage > 0) && (locationPage < Math.ceil(page))){
                            int lpage = (int)Math.ceil(page);
                            firstLastPage(label, lpage);
                            lbPage.setFont(new Font("tahoma", Font.BOLD, 18));
                            lbPage.setForeground(Color.RED);
                        }
                        if(locationPage == (int)Math.ceil(page)){
                            end();
                        }else{
                            startEndPage();
                        }
                    }
                });
            }
        }
    }
    
//    public void pageInGoogle(){
//        if(locationPage){
//            
//        }
//    }
    
   public void nextBackPage(JLabel labelpage, String operator){
        if(operator.equals("-")){
            locationPage = Integer.valueOf(labelpage.getName()) - 1;
            labelpage.setName(locationPage+"");
            lbNext.setName(locationPage+"");
            lbLastPage.setName(locationPage+"");
        }else{
            locationPage = Integer.valueOf(labelpage.getName()) + 1;
            labelpage.setName(locationPage+"");
            lbBack.setName(locationPage+"");
            lbFirstPage.setName(locationPage+"");
        }
                            
        labelpage.setFont(new Font("tahoma", Font.BOLD, 14));
        labelpage.setForeground(Color.red);
        Component[] components = pnPageCenter.getComponents();
            for(Component component : components){
                if(component instanceof JLabel){
                    JLabel label = (JLabel) component;
                    if(Integer.valueOf(label.getText()) == locationPage){
                        label.setFont(new Font("tahoma", Font.BOLD, 18));
                        label.setForeground(Color.RED);
                    }
                }
            }
        transferPage(locationPage);
   }
   
   public void firstLastPage(JLabel lablePage, int page){
        if(page == 1){
            locationPage = page;
            lablePage.setName(locationPage+"");
            lbNext.setName(locationPage+"");
            lbLastPage.setName(locationPage+"");
        }else{
            locationPage = page;
            lablePage.setName(locationPage+"");
            lbBack.setName(locationPage+"");
            lbFirstPage.setName(locationPage+"");
        }
        lablePage.setFont(new Font("tahoma", Font.BOLD, 14));
        lablePage.setForeground(Color.red);                           
        transferPage(locationPage);
   }
   
   public void start(){
       lbBack.setVisible(false);
        lbFirstPage.setVisible(false);
        lbNext.setVisible(true);
        lbLastPage.setVisible(true);
   }
   
    public void startEndPage(){
        lbBack.setVisible(true);
        lbFirstPage.setVisible(true);
        lbNext.setVisible(true);
        lbLastPage.setVisible(true);
    }
    
    public void end(){
        lbBack.setVisible(true);
        lbFirstPage.setVisible(true);
        lbNext.setVisible(false);
        lbLastPage.setVisible(false);
    }
    
    
   
    public void transferPage(int locationPage){
        offset = (locationPage - 1)*rowcount;
        studentTable.loadPage(offset, rowcount);
    }
    
   public void resetLable(){
        Component[] comps = pnBottom.getComponents();
        for(Component  comp : comps){
            if(comp instanceof JPanel){
                JPanel panel = (JPanel) comp;
                Component[] components = panel.getComponents();
                for(Component component : components){
                    if(component instanceof JLabel){
                        JLabel label = (JLabel) component;
                        label.setFont(new Font("tahoma", Font.PLAIN, 14));
                        label.setForeground(Color.BLACK);
                    }
                }
            }
        }
    } 
    
   public void getStudent(){
        row = tbStudent.getSelectedRow();
        String id = tbStudent.getValueAt(row, 0).toString();
        String name = tbStudent.getValueAt(row, 1).toString();
        tfSearch.setText(id+" - "+name);
    }
    
    public void newFrInsertStudent(){
        frInsertStudent = new FrInsertStudent(tbStudent ,trainee, studentTable, row);
        frInsertStudent.setVisible(true);
        frInsertStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTop = new javax.swing.JPanel();
        pnLeft = new javax.swing.JPanel();
        tfSearch = new javax.swing.JTextField();
        cbStudent = new javax.swing.JCheckBox();
        pnRight = new javax.swing.JPanel();
        btInsert = new javax.swing.JButton();
        btRepot = new javax.swing.JButton();
        btImporting = new javax.swing.JButton();
        pnCenter = new javax.swing.JPanel();
        scStudent = new javax.swing.JScrollPane();
        tbStudent = new javax.swing.JTable();
        pnBottom = new javax.swing.JPanel();
        pnPageLeft = new javax.swing.JPanel();
        lbFirstPage = new javax.swing.JLabel();
        lbBack = new javax.swing.JLabel();
        pnPageCenter = new javax.swing.JPanel();
        pnPageRight = new javax.swing.JPanel();
        lbNext = new javax.swing.JLabel();
        lbLastPage = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        pnTop.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(10, 1, 10, 1)));
        pnTop.setForeground(new java.awt.Color(204, 204, 204));
        pnTop.setMinimumSize(new java.awt.Dimension(400, 55));
        pnTop.setPreferredSize(new java.awt.Dimension(800, 70));
        pnTop.setLayout(new java.awt.BorderLayout());

        pnLeft.setPreferredSize(new java.awt.Dimension(400, 50));
        pnLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 5));

        tfSearch.setPreferredSize(new java.awt.Dimension(250, 40));
        tfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSearchActionPerformed(evt);
            }
        });
        pnLeft.add(tfSearch);

        cbStudent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbStudent.setText("Sinh Viên");
        pnLeft.add(cbStudent);

        pnTop.add(pnLeft, java.awt.BorderLayout.LINE_START);

        pnRight.setPreferredSize(new java.awt.Dimension(500, 50));
        pnRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        btInsert.setBackground(new java.awt.Color(102, 255, 51));
        btInsert.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btInsert.setForeground(new java.awt.Color(255, 255, 255));
        btInsert.setText("+ Thêm Mới");
        btInsert.setBorderPainted(false);
        btInsert.setFocusPainted(false);
        btInsert.setPreferredSize(new java.awt.Dimension(120, 40));
        pnRight.add(btInsert);

        btRepot.setBackground(new java.awt.Color(102, 255, 51));
        btRepot.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btRepot.setForeground(new java.awt.Color(255, 255, 255));
        btRepot.setText("Xuất báo cáo");
        btRepot.setBorderPainted(false);
        btRepot.setFocusPainted(false);
        btRepot.setPreferredSize(new java.awt.Dimension(120, 40));
        pnRight.add(btRepot);

        btImporting.setBackground(new java.awt.Color(102, 255, 51));
        btImporting.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btImporting.setForeground(new java.awt.Color(255, 255, 255));
        btImporting.setText("Nhập Excel");
        btImporting.setBorderPainted(false);
        btImporting.setFocusPainted(false);
        btImporting.setPreferredSize(new java.awt.Dimension(120, 40));
        pnRight.add(btImporting);

        pnTop.add(pnRight, java.awt.BorderLayout.CENTER);

        add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 1, 1));
        pnCenter.setLayout(new java.awt.BorderLayout());

        tbStudent.setAutoCreateRowSorter(true);
        tbStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbStudent.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStudentMouseClicked(evt);
            }
        });
        scStudent.setViewportView(tbStudent);

        pnCenter.add(scStudent, java.awt.BorderLayout.CENTER);

        add(pnCenter, java.awt.BorderLayout.CENTER);

        pnBottom.setLayout(new java.awt.BorderLayout());

        pnPageLeft.setPreferredSize(new java.awt.Dimension(300, 40));
        pnPageLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));

        lbFirstPage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbFirstPage.setText("<<");
        pnPageLeft.add(lbFirstPage);

        lbBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbBack.setText("<");
        pnPageLeft.add(lbBack);

        pnBottom.add(pnPageLeft, java.awt.BorderLayout.LINE_START);

        pnPageCenter.setPreferredSize(new java.awt.Dimension(20, 20));
        pnPageCenter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        pnBottom.add(pnPageCenter, java.awt.BorderLayout.CENTER);

        pnPageRight.setPreferredSize(new java.awt.Dimension(300, 20));
        pnPageRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));

        lbNext.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNext.setText(">");
        pnPageRight.add(lbNext);

        lbLastPage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbLastPage.setText(">>");
        pnPageRight.add(lbLastPage);

        pnBottom.add(pnPageRight, java.awt.BorderLayout.LINE_END);

        add(pnBottom, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void tfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchActionPerformed
    }//GEN-LAST:event_tfSearchActionPerformed

    private void tbStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStudentMouseClicked
        int column = tbStudent.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tbStudent.getRowHeight();
        
        if(row < tbStudent.getRowCount() && row >=0 && column < tbStudent.getColumnCount() && column >= 0){
            Object value = tbStudent.getValueAt(row, column);
            if(value instanceof JButton){
                ((JButton) value).doClick();
                JButton button = (JButton) value;
                if(button.getName().equals("edit")){
                    row = tbStudent.getSelectedRow();
                    int id = (int) tbStudent.getValueAt(row, 0);
                    trainee = studentTable.getItem(row ,id);
                    newFrInsertStudent();
                }
                if(button.getName().equals("delete")){
                   row = tbStudent.getSelectedRow();
                   int id = (int) tbStudent.getValueAt(row, 0);
                   trainee = studentTable.getItem(row ,id);
                   if(trainee.getType() == 1){
                       Trainee student = new Student(trainee.getId());
                        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Suy Nghi Ky Chua??", "!!", JOptionPane.YES_NO_OPTION)){
                            int result = classHasStudentController.delClassHasStudent(trainee, null);
                            if(studentTable.delItem(row, student) > 0 && result > 0){
                                JOptionPane.showMessageDialog(null, "Xoá thành công");
                            }
                        }
                   }else{
                       Trainee nonStudent = new NonStudent(trainee.getId());
                        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Suy Nghi Ky Chua??", "!!", JOptionPane.YES_NO_OPTION)){
                            int result = classHasStudentController.delClassHasStudent(trainee, null);
                            if(studentTable.delItem(row, nonStudent) > 0 && result > 0){
                                JOptionPane.showMessageDialog(null, "Xoá thành công");
                            }
                        }
                   }
                }
            }
        }
    }//GEN-LAST:event_tbStudentMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btImporting;
    private javax.swing.JButton btInsert;
    private javax.swing.JButton btRepot;
    private javax.swing.JCheckBox cbStudent;
    private javax.swing.JLabel lbBack;
    private javax.swing.JLabel lbFirstPage;
    private javax.swing.JLabel lbLastPage;
    private javax.swing.JLabel lbNext;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnPageCenter;
    private javax.swing.JPanel pnPageLeft;
    private javax.swing.JPanel pnPageRight;
    private javax.swing.JPanel pnRight;
    private javax.swing.JPanel pnTop;
    private javax.swing.JScrollPane scStudent;
    private javax.swing.JTable tbStudent;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables

}
