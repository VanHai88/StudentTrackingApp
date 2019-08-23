/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.CrudEnum;
import controller.LecturerController;
import entities.Lecturer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.LecturerTable;
import service.imp.LecturerServiceImp;

/**
 *
 * @author Van Hai
 */
public class PnLecturer extends javax.swing.JPanel {

    private LecturerTable lecturerTable;
    private LecturerController lecturerController;
    private FrInsertLecturer frInsertLecturer;
    private int id = 0;

    public PnLecturer() {
        initComponents();
        lecturerController = new LecturerController();
        lecturerTable = new LecturerTable(tbLecturer);
        loadData();
        lecturerTable.loadTable();
        initEvent();
    }

    public void loadData() {
        List<Lecturer> lecturers = lecturerController.getAllLecturers();
        lecturerTable.loadData(lecturers);
    }

    public void initEvent() {
        initTbLecturerEvent();
        initBtADDEvent();

    }

    public void initBtADDEvent() {
        btADD.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                id = 0;
                if (id == 0) {
                    frInsertLecturer = new FrInsertLecturer(lecturerTable, id);
                    frInsertLecturer.setVisible(true);
                    frInsertLecturer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }

        });
    }

    public void initTbLecturerEvent() {
        tbLecturer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectRow = tbLecturer.getSelectedRow();
                int selectCol = tbLecturer.getSelectedColumn();
                id = (int) tbLecturer.getValueAt(selectRow, 0);
                isSelecting(selectRow, selectCol, id);
                isSelectInfor(selectRow, selectCol, id);
            }
        });
    }

    public void isSelecting(int selectRow, int selectCol, int id) {
        Object object = tbLecturer.getValueAt(selectRow, selectCol);
        if (object instanceof JButton) {
            JButton button = (JButton) object;
            CrudEnum text = CrudEnum.covertToEnum(button.getName());
            switch (text) {
                case EDIT:
                    FrInsertLecturer frInsertLecturer = new FrInsertLecturer(lecturerTable, id);
                    frInsertLecturer.setVisible(true);
                    frInsertLecturer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    break;
                case DELETE:

                    break;
            }
        }
    }

    public void isSelectInfor(int selectRow, int selectCol, int id) {
        Object object = tbLecturer.getValueAt(selectRow, selectCol);
        if (!(object instanceof JButton)) {
            isSelecting(selectRow, selectCol, id);
            FrInforLecturer frInforLecturer = new FrInforLecturer(id);
            frInforLecturer.setVisible(true);
            frInforLecturer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTop = new javax.swing.JPanel();
        pnLeft = new javax.swing.JPanel();
        tfSearch = new javax.swing.JTextField();
        btADD = new javax.swing.JButton();
        pnRight = new javax.swing.JPanel();
        btRepot = new javax.swing.JButton();
        btImporting = new javax.swing.JButton();
        pnCenter = new javax.swing.JPanel();
        scLecturer = new javax.swing.JScrollPane();
        tbLecturer = new javax.swing.JTable();
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

        btADD.setBackground(new java.awt.Color(102, 255, 51));
        btADD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btADD.setForeground(new java.awt.Color(255, 255, 255));
        btADD.setText("+ Thêm Mới");
        btADD.setBorderPainted(false);
        btADD.setFocusPainted(false);
        btADD.setName("ADD"); // NOI18N
        btADD.setPreferredSize(new java.awt.Dimension(120, 40));
        pnLeft.add(btADD);

        pnTop.add(pnLeft, java.awt.BorderLayout.LINE_START);

        pnRight.setPreferredSize(new java.awt.Dimension(500, 50));
        pnRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

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

        tbLecturer.setAutoCreateRowSorter(true);
        tbLecturer.setModel(new javax.swing.table.DefaultTableModel(
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
        tbLecturer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbLecturer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLecturerMouseClicked(evt);
            }
        });
        scLecturer.setViewportView(tbLecturer);

        pnCenter.add(scLecturer, java.awt.BorderLayout.CENTER);

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

    private void tbLecturerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLecturerMouseClicked

    }//GEN-LAST:event_tbLecturerMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btADD;
    private javax.swing.JButton btImporting;
    private javax.swing.JButton btRepot;
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
    private javax.swing.JScrollPane scLecturer;
    private javax.swing.JTable tbLecturer;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables

}
