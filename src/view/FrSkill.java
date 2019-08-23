package view;

import entities.GClass;
import entities.LecturerHasSkill;
import entities.Skill;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import model.LecturerHasSkillTable;
import service.imp.LecturerHasSkillServiceImp;
import service.imp.SkillServiceImp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Van Hai
 */
public class FrSkill extends javax.swing.JFrame {
    private SkillServiceImp skillServiceImp;
    private LecturerHasSkillServiceImp lecturerHasSkillServiceImp;
    private LecturerHasSkillTable lecturerHasSkillTable;
    private int id;
    
    public FrSkill(int id) {
        initComponents();
        this.id = id;
        skillServiceImp = new SkillServiceImp();
        lecturerHasSkillServiceImp = new LecturerHasSkillServiceImp();
        lecturerHasSkillTable = new LecturerHasSkillTable(tbLecturerHasClass);
        loadData();
        lecturerHasSkillTable.loadTable();
        initData();
    }
    
    public void loadData(){
        List<LecturerHasSkill> hasSkillTables = lecturerHasSkillServiceImp.getAllSkills(id);
        lecturerHasSkillTable.loadData(hasSkillTables);
    }
    
    public void initData(){
        List<Skill> skills = skillServiceImp.getAllSkills();
        Skill[] skill = skills.toArray(new Skill[skills.size()]);
        
        ComboBoxModel KkillModel = new DefaultComboBoxModel<>(skill);
        cbbSkill.setModel(KkillModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTop = new javax.swing.JPanel();
        cbbSkill = new javax.swing.JComboBox<>();
        tfExp = new javax.swing.JTextField();
        btAddSkill = new javax.swing.JButton();
        pnCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLecturerHasClass = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnTop.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));

        cbbSkill.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbSkill.setPreferredSize(new java.awt.Dimension(250, 35));
        pnTop.add(cbbSkill);

        tfExp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfExp.setPreferredSize(new java.awt.Dimension(250, 35));
        pnTop.add(tfExp);

        btAddSkill.setBackground(new java.awt.Color(102, 255, 51));
        btAddSkill.setForeground(new java.awt.Color(255, 255, 255));
        btAddSkill.setText("Thêm Kỹ Năng");
        btAddSkill.setBorderPainted(false);
        btAddSkill.setFocusPainted(false);
        btAddSkill.setPreferredSize(new java.awt.Dimension(150, 35));
        pnTop.add(btAddSkill);

        getContentPane().add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnCenter.setLayout(new java.awt.BorderLayout());

        tbLecturerHasClass.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbLecturerHasClass);

        pnCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new FrSkill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddSkill;
    private javax.swing.JComboBox<String> cbbSkill;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnTop;
    private javax.swing.JTable tbLecturerHasClass;
    private javax.swing.JTextField tfExp;
    // End of variables declaration//GEN-END:variables
}
