/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.ImageEnum;
import controller.LecturerController;
import entities.Lecturer;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import utils.ImageUtils;

/**
 *
 * @author Van Hai
 */
public class LecturerTable extends AbstractTableModel{
    private JTable table;
    private List<Lecturer> lecturers;
    private JButton btEdit;
    private JButton btDelete;
    
    private String[] cols = {"ID", "Họ tên", "Ngày Sinh", "Địa Chỉ","Lớp Phụ Trách", "Lương", "Tình trạng", "Email","",""};

    public LecturerTable(JTable tbLecturer) {
        this.table = tbLecturer;
        tbLecturer.setDefaultRenderer(Object.class, new Rander());
    }
    
    
     
    @Override
    public int getRowCount() {
        return lecturers.size();
    }

    @Override
    public int getColumnCount() {
        return  cols.length;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 5){
            return Double.class;
        }
        
        return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Lecturer lecturer = lecturers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = lecturer.getId();
                break;
            case 1:
                value = lecturer.getFullName();
                break;
            case 2:
                final LocalDate ldate = lecturer.getDateOfBirth();
                value = ldate != null ? ldate : "--/--/--";
                break;
            case 3:
                value = lecturer.getAddRess();
                break;
            case 4:
                value = lecturer.getJobDescription();
                break;
            case 5:
                value = lecturer.getWage();
                break;
            case 6:
                value = lecturer.getStatusLecturers();
                break;
            case 7:
                value = lecturer.getEmail();
                break;
            case 8:
                btEdit = new JButton();
                btEdit.setName("EDIT");
                btEdit.setIcon(ImageUtils.createImageIcon(StudentTable.class,ImageEnum.EDIT.getPath(), 20, 20));
                value = btEdit;
                break;
            case 9:
                btDelete = new JButton();
                btDelete.setName("DELETE");
                btDelete.setIcon(ImageUtils.createImageIcon(StudentTable.class,ImageEnum.DELETE.getPath(), 20, 20));
                value = btDelete;
                break;
        }
        return value;
    }
    
    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
    
    public void loadTable(){
        table.setModel(this);
        cssForTable();
    }
    
    private void cssForTable() {
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(125);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(168);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(168);
        table.getColumnModel().getColumn(8).setPreferredWidth(40);
        table.getColumnModel().getColumn(9).setPreferredWidth(40);
        table.setRowHeight(28);
        table.getTableHeader().setReorderingAllowed(false); // khong cho phep di chuyen col
        table.getTableHeader().setResizingAllowed(false); // khong cho phep thay doi kich co col
        ((JComponent)(table.getDefaultRenderer(Boolean.class))).setOpaque(true);
    }
    
    public void firedTarget(Lecturer lecturer) {
        lecturers.add(lecturer);
        fireTableDataChanged();
    }
    
    public void loadData(List<Lecturer> lecturers){
        this.lecturers = lecturers;
    }
}
