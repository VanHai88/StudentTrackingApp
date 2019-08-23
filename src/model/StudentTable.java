/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.ImageEnum;
import controller.NonStudentController;
import controller.StudentController;
import controller.TraineeController;
import entities.NonStudent;
import entities.Student;
import entities.Trainee;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import utils.ImageUtils;

/**
 *
 * @author Van Hai
 */
public class StudentTable extends AbstractTableModel{

    private List<Trainee> TraineeList;
    private String [] arrClos = {"ID", "Họ Tên","SĐT","Địa Chỉ","Email","Ngày Sinh","Công Việc","Năm Học","Trường Học","Sinh Viên","",""};
    private JTable table;
    private TraineeController traineeController;
    private StudentController studentController;
    private NonStudentController nonStudentController;
    private JButton btEdit;
    private JButton btDelete;
    
    public StudentTable(JTable tbStudent) {
        this.table = tbStudent;
        traineeController = new TraineeController();
        studentController = new StudentController();
        nonStudentController = new NonStudentController();
        tbStudent.setDefaultRenderer(Object.class, new Rander());
        loadData();
    }
    
    public void loadData(){
        TraineeList = traineeController.getLimitTrainess(0, 8);
    }
    
    @Override
    public int getRowCount() {
        return TraineeList.size();
    }

    @Override
    public int getColumnCount() {
        return arrClos.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 0){
            return Integer.class;
        }
        
        if(columnIndex == 9){
            return  Boolean.class;
        }
        return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Trainee obj = TraineeList.get(rowIndex);
        switch(columnIndex){
            case 0:
                value = obj.getId();
                break;
            case 1:
                value = obj.getFullName();
                break;
            case 2:
                value = obj.getPhone();
                break;
            case 3:
                value = obj.getAddRess();
                break;
            case 4:
                value = obj.getEmail();
                break;
            case 5:
                value = obj.getDateOfBirth();
                break;
            case 6:
                if(obj.getCurrentWorking() != null){
                    value = obj.getCurrentWorking();
                }else{
                    value = "không";
                }
                break;
            case 7:
                if(obj.getType() == 1){
                    if(obj instanceof Student){
                        Student student  = (Student) obj;
                        value = student.getYearth();
                    }
                    
                }else{
                    value = "0";
                }
                break;
            case 8:
                if(obj.getType() == 1){
                    if(obj instanceof Student){
                        Student student  = (Student) obj;
                        value = student.getSchoolName();
                    }
                }else{
                    value = "không";
                }
                break;
                
            case 9:
                if(obj.getType() == 1){
                    value = true;
                }else{
                    value = false;
                }
                
                break;
            case 10:
                btEdit = new JButton();
                btEdit.setName("edit");
                btEdit.setIcon(ImageUtils.createImageIcon(StudentTable.class,ImageEnum.EDIT.getPath(), 20, 20));
                value = btEdit;
                break;
            case 11:
                btDelete = new JButton();
                btDelete.setName("delete");
                btDelete.setIcon(ImageUtils.createImageIcon(StudentTable.class,ImageEnum.DELETE.getPath(), 20, 20));
                value = btDelete;
                break;
        }
        return value;
    }

    @Override
    public String getColumnName(int column) {
        return arrClos[column];
    }
    
    public void loadTable(){
        table.setModel(this);
        cssForTable();
    }

    private void cssForTable() {
        table.getColumnModel().getColumn(0).setPreferredWidth(46);
        table.getColumnModel().getColumn(1).setPreferredWidth(125);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(168);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(60);
        table.getColumnModel().getColumn(8).setPreferredWidth(100);
        table.getColumnModel().getColumn(9).setPreferredWidth(60);
        table.getColumnModel().getColumn(10).setPreferredWidth(40);
        table.getColumnModel().getColumn(11).setPreferredWidth(40);
        table.setRowHeight(28);
        table.getTableHeader().setReorderingAllowed(false); // khong cho phep di chuyen col
        table.getTableHeader().setResizingAllowed(false); // khong cho phep thay doi kich co col
        ((JComponent)(table.getDefaultRenderer(Boolean.class))).setOpaque(true);
    }
    
     public int addItem(Trainee trainee) {
         int result_id = 1;
         if(trainee instanceof Student){
            Student student = (Student) trainee;
            result_id = studentController.addStudent(student);
            student.setId(result_id);
            TraineeList.add(student);
         }else{
             NonStudent nonStudent = (NonStudent) trainee;
             result_id = nonStudentController.addNonStudent(nonStudent);
             nonStudent.setId(result_id);
             TraineeList.add(nonStudent);
         }
         this.fireTableDataChanged();// hien thi khi them
         table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true)); // scroll tai vi tri them
         return result_id;
    }

    public int delItem(int row, Trainee trainee) {
        int result = 1;
        if(trainee instanceof Student){
            Student student = (Student) trainee;
            result = studentController.DelStudent(student);
        }else{
            NonStudent nonStudent = (NonStudent) trainee;
            result = nonStudentController.DelNonStudent(nonStudent);
        }
        int rowModel = table.convertRowIndexToModel(row);
        TraineeList.remove(rowModel);
        fireTableStructureChanged();
        return result;
    }

    public int editItem(int row,Trainee trainee) {
        int result = 1;
        int rowModel = table.convertRowIndexToModel(row);
        if(trainee instanceof Student){
            Student student = (Student) trainee;
            studentController.editStudent(student);
            TraineeList.set(rowModel, student);
        }else{
            NonStudent nonStudent = (NonStudent) trainee;
            nonStudentController.ediNonStudent(nonStudent);
            TraineeList.set(rowModel, nonStudent);
        }
        
        fireTableRowsUpdated(rowModel, rowModel);
        table.setRowSelectionInterval(rowModel, rowModel);
        return result;
    }
    
    // cap nhat lai model de lay rowcout

    public Trainee getItem(int row, int id) {
        int rowModel = table.convertRowIndexToModel(row);
        Trainee trainee = TraineeList.get(rowModel);
        return trainee;
    }

    public void loadPage(double offset, double rowcount) {
        table.setModel(new DefaultTableModel());
        TraineeList = traineeController.getLimitTrainess((int)offset, (int)rowcount);
        table.setModel(this);
    }

}
