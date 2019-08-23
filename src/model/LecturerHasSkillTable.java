/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.ImageEnum;
import entities.Lecturer;
import entities.LecturerHasSkill;
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
public class LecturerHasSkillTable extends AbstractTableModel{
    private JTable table;
    private List<LecturerHasSkill> lecturerHasSkills;
    private JButton btEdit;
    private JButton btDelete;
    
    private String[] cols = {"Công Nghệ","Kinh Nghiệm(Năm)","",""};

    public LecturerHasSkillTable(JTable tbSkill) {
        this.table = tbSkill;
        tbSkill.setDefaultRenderer(Object.class, new Rander());
    }
    
    
     
    @Override
    public int getRowCount() {
        return lecturerHasSkills.size();
    }

    @Override
    public int getColumnCount() {
        return  cols.length;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 1){
            return Integer.class;
        }
        
        return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        LecturerHasSkill lecturerHasSkill = lecturerHasSkills.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = lecturerHasSkill.getTech();
                break;
            case 1:
                value = lecturerHasSkill.getExp();
                break;
            case 2:
                btEdit = new JButton();
                btEdit.setName("EDIT");
                btEdit.setIcon(ImageUtils.createImageIcon(StudentTable.class,ImageEnum.EDIT.getPath(), 20, 20));
                value = btEdit;
                break;
            case 3:
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
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.setRowHeight(28);
        table.getTableHeader().setReorderingAllowed(false); // khong cho phep di chuyen col
        table.getTableHeader().setResizingAllowed(false); // khong cho phep thay doi kich co col
        ((JComponent)(table.getDefaultRenderer(Boolean.class))).setOpaque(true);
    }
    
    public void firedTarget(LecturerHasSkill lecturerHasSkill) {
        lecturerHasSkills.add(lecturerHasSkill);
        fireTableDataChanged();
    }
    
    public void loadData(List<LecturerHasSkill> lecturerHasSkills){
        this.lecturerHasSkills = lecturerHasSkills;
    }
}
