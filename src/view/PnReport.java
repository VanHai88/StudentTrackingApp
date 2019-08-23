/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.PnReportButtomEnum;
import controller.OpenClassController;
import controller.StudentRegistrationCoursController;
import entities.OpenClass;
import entities.StudentRegistrationCours;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Van Hai
 */
public class PnReport extends javax.swing.JPanel {

    private StudentRegistrationCoursController srcc;
    private OpenClassController openClassController;
    private Color checkColor;
    private List<StudentRegistrationCours> srcList;
    private List<OpenClass> openClasses;

    public PnReport() {
        srcc = new StudentRegistrationCoursController();
        openClassController = new OpenClassController();
        srcList = new ArrayList<>();
        openClasses = new ArrayList<>();
        initComponents();
        initWindowContents();
        initComps();
        initEvent();
    }

    public void initWindowContents() {
        btMonth.setBackground(new Color(0, 204, 204));
        callToReportMonth();
        btCheck.setVisible(false);
    }

    public void initComps() {
        initPncerter();
    }

    public void initEvent() {
        initPnTopEvent();
        initPnTime();
        initBtSearch();
    }

    public void initPnTopEvent() {
        initClickButtonEvent();
        initHoverButtonEvent();
    }
    
    public void initPnTime(){
        initBtCheckEvent();
    }
    
    public void initBtCheckEvent(){
        btCheck.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(btCheck.isEnabled()){
                    
                }
            }
            
        });
    }

    public void initClickButtonEvent() {
        Component[] comps = pnTop.getComponents();
        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        resetButtonColor();
                        button.setBackground(new Color(0, 204, 204));
                        checkColor = button.getBackground();
                        PnReportButtomEnum text = PnReportButtomEnum.convertToEnum(button.getName());
                        makeAChoice(text);
                    }

                });
            }
        }
    }

    public void initHoverButtonEvent() {
        Component[] comps = pnTop.getComponents();
        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        checkColor = button.getBackground();
                        button.setBackground(new Color(0, 204, 204));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        button.setBackground(checkColor);
                    }
                });
            }
        }
    }

    public void resetButtonColor() {
        Component[] comps = pnTop.getComponents();
        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setBackground(new Color(255, 255, 102));
            }
        }
    }

    public void initPncerter() {
        createChartCylinder(pnLeft);
        createChartCircle(pnRight);
    }

    public void initBtSearch() {
        btSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PnReportButtomEnum text = PnReportButtomEnum.convertToEnum(btSearch.getName());
                makeAChoice(text);
            }
        });
    }

    public void makeAChoice(PnReportButtomEnum text) {
        pnCenter.removeAll();
        pnLeft.removeAll();
        pnRight.removeAll();
        switch (text) {
            case REPORTMONTH:
                callToReportMonth();
                break;
            case REPORTYEAR:
                ycYearch.setEnabled(true);
                mcMonth.setEnabled(false);
                dcFrom.setEnabled(false);
                dcTo.setEnabled(false);
                pnCenter.add(pnChart);
                createChartCylinder(pnLeft);
                createChartCircle(pnRight);
                btSearch.setName("REPORTYEAR");
                break;
            case REPORTBETWEENTIME:
                dcFrom.setEnabled(true);
                dcTo.setEnabled(true);
                ycYearch.setEnabled(false);
                mcMonth.setEnabled(false);
                createChartLine(pnCenter);
                btSearch.setName("REPORTBETWEENTIME");
                break;
            case LISTCOURS:
                dcFrom.setEnabled(true);
                dcTo.setEnabled(true);
                ycYearch.setEnabled(false);
                mcMonth.setEnabled(false);
                break;
        }
        revalidate();
        repaint();
    }

    public void callToReportMonth() {
        ycYearch.setEnabled(true);
        mcMonth.setEnabled(true);
        dcFrom.setEnabled(false);
        dcTo.setEnabled(false);
        createChartCylinder(pnCenter);
        btSearch.setName("REPORTMONTH");
    }

    public void createChartCylinder(JPanel panel) {
        int yearch = ycYearch.getYear();
        int month = mcMonth.getMonth() + 1;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if (mcMonth.isEnabled()) {
            srcList = srcc.getAmountOfMonth(month, yearch);
        } else {
            srcList = srcc.getAmountOfYearth(yearch);
        }

        for (StudentRegistrationCours src : srcList) {
            dataset.setValue(src.getAmount(), src.getNameCours(), src.getNameCours());
        }

        JFreeChart chart = ChartFactory.createBarChart3D("Thống Kê Số Lượng Học Viên Đăng Ký Khóa Học", "Khóa học", "Số Lượng", dataset);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartPanel cp = new ChartPanel(chart);
        cp.setPreferredSize(new Dimension(500, 500));
        panel.add(cp, BorderLayout.CENTER);
        panel.validate();
    }

    public void createChartCircle(JPanel panel) {
        int yearch = ycYearch.getYear();
        openClasses = openClassController.getAmountOfYear(yearch);

        DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();

        for (OpenClass openClass : openClasses) {
            pieDataset.setValue(openClass.getNameCours(), openClass.getAmount());
        }

        JFreeChart chart = ChartFactory.createPieChart("Thống Kê Số Khóa Học Được Mở", pieDataset, true, true, true);

        ChartPanel cp = new ChartPanel(chart);
        cp.setPreferredSize(new Dimension(500, 500));
        panel.add(cp, BorderLayout.CENTER);
        panel.validate();
    }

    public void createChartLine(JPanel panel) {
        LocalDate from = utils.DateUtils.getLocalDate(dcFrom.getDate());
        LocalDate to = utils.DateUtils.getLocalDate(dcTo.getDate());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (dcFrom.isEnabled()) {
            srcList = srcc.getAmountBetweenTime(from, to);
            openClasses = openClassController.getAmountBetweenTime(from, to);
        }
        
        for (StudentRegistrationCours src : srcList) {
            dataset.setValue(src.getAmount(), "Học Viên", src.getNameCours());
        }
        for (OpenClass src : openClasses) {
            dataset.setValue(src.getAmount(), "Lớp Học", src.getNameCours());
        }

        JFreeChart chart = ChartFactory.createLineChart("Thống Kê Tổng Quang Khóa Học!!", "Khóa học", "Số Lượng", dataset);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartPanel cp = new ChartPanel(chart);
        cp.setPreferredSize(new Dimension(500, 500));
        panel.add(cp, BorderLayout.CENTER);
        panel.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTop = new javax.swing.JPanel();
        btMonth = new javax.swing.JButton();
        btYearth = new javax.swing.JButton();
        btbetween = new javax.swing.JButton();
        btListCours = new javax.swing.JButton();
        pnCenter = new javax.swing.JPanel();
        pnChart = new javax.swing.JPanel();
        pnLeft = new javax.swing.JPanel();
        pnRight = new javax.swing.JPanel();
        pnTime = new javax.swing.JPanel();
        pnTimeLeft = new javax.swing.JPanel();
        lbTime1 = new javax.swing.JLabel();
        dcFrom = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dcTo = new com.toedter.calendar.JDateChooser();
        pnTimeRight = new javax.swing.JPanel();
        btCheck = new javax.swing.JButton();
        mcMonth = new com.toedter.calendar.JMonthChooser();
        ycYearch = new com.toedter.calendar.JYearChooser();
        btSearch = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        pnTop.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 255, 51), new java.awt.Color(153, 255, 51)), javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 255, 51))));

        btMonth.setBackground(new java.awt.Color(255, 255, 102));
        btMonth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btMonth.setForeground(new java.awt.Color(0, 51, 204));
        btMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/diagram.png"))); // NOI18N
        btMonth.setText("Thống Kê Theo Tháng");
        btMonth.setBorderPainted(false);
        btMonth.setFocusPainted(false);
        btMonth.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btMonth.setName("REPORTMONTH"); // NOI18N
        btMonth.setPreferredSize(new java.awt.Dimension(240, 100));
        btMonth.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btMonth.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnTop.add(btMonth);

        btYearth.setBackground(new java.awt.Color(255, 255, 102));
        btYearth.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btYearth.setForeground(new java.awt.Color(0, 51, 204));
        btYearth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bars-chart.png"))); // NOI18N
        btYearth.setText("Thống Kê Theo Năm");
        btYearth.setBorderPainted(false);
        btYearth.setFocusPainted(false);
        btYearth.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btYearth.setName("REPORTYEAR"); // NOI18N
        btYearth.setPreferredSize(new java.awt.Dimension(240, 100));
        btYearth.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btYearth.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnTop.add(btYearth);

        btbetween.setBackground(new java.awt.Color(255, 255, 102));
        btbetween.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btbetween.setForeground(new java.awt.Color(0, 51, 204));
        btbetween.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graph.png"))); // NOI18N
        btbetween.setText("Thống Kê Theo Khoảng Thời Gian");
        btbetween.setBorderPainted(false);
        btbetween.setFocusPainted(false);
        btbetween.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btbetween.setName("REPORTBETWEENTIME"); // NOI18N
        btbetween.setPreferredSize(new java.awt.Dimension(270, 100));
        btbetween.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btbetween.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnTop.add(btbetween);

        btListCours.setBackground(new java.awt.Color(255, 255, 102));
        btListCours.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btListCours.setForeground(new java.awt.Color(0, 51, 204));
        btListCours.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/analytics.png"))); // NOI18N
        btListCours.setText("Danh Sách Đăng Ký Khóa Học");
        btListCours.setBorderPainted(false);
        btListCours.setFocusPainted(false);
        btListCours.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btListCours.setName("LISTCOURS"); // NOI18N
        btListCours.setPreferredSize(new java.awt.Dimension(240, 100));
        btListCours.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btListCours.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnTop.add(btListCours);

        add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnCenter.setPreferredSize(new java.awt.Dimension(100, 500));
        pnCenter.setLayout(new java.awt.BorderLayout());

        pnChart.setLayout(new java.awt.BorderLayout());

        pnLeft.setLayout(new java.awt.BorderLayout());
        pnChart.add(pnLeft, java.awt.BorderLayout.LINE_START);

        pnRight.setLayout(new java.awt.BorderLayout());
        pnChart.add(pnRight, java.awt.BorderLayout.CENTER);

        pnCenter.add(pnChart, java.awt.BorderLayout.CENTER);

        add(pnCenter, java.awt.BorderLayout.PAGE_END);

        pnTime.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thời Gian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 204))); // NOI18N
        pnTime.setPreferredSize(new java.awt.Dimension(150, 50));
        pnTime.setLayout(new java.awt.BorderLayout());

        pnTimeLeft.setPreferredSize(new java.awt.Dimension(480, 50));

        lbTime1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTime1.setText("From:");
        pnTimeLeft.add(lbTime1);

        dcFrom.setDateFormatString("dd/MM/yyyy");
        dcFrom.setMinimumSize(new java.awt.Dimension(100, 40));
        dcFrom.setPreferredSize(new java.awt.Dimension(400, 30));
        pnTimeLeft.add(dcFrom);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("To:");
        jLabel4.setPreferredSize(new java.awt.Dimension(37, 17));
        pnTimeLeft.add(jLabel4);

        dcTo.setDateFormatString("dd/MM/yyyy");
        dcTo.setPreferredSize(new java.awt.Dimension(400, 30));
        pnTimeLeft.add(dcTo);

        pnTime.add(pnTimeLeft, java.awt.BorderLayout.LINE_START);

        pnTimeRight.setPreferredSize(new java.awt.Dimension(100, 50));

        btCheck.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btCheck.setForeground(new java.awt.Color(51, 204, 0));
        btCheck.setText("V");
        btCheck.setPreferredSize(new java.awt.Dimension(40, 40));
        pnTimeRight.add(btCheck);

        mcMonth.setPreferredSize(new java.awt.Dimension(120, 30));
        pnTimeRight.add(mcMonth);

        ycYearch.setPreferredSize(new java.awt.Dimension(100, 30));
        pnTimeRight.add(ycYearch);

        btSearch.setBackground(new java.awt.Color(153, 255, 0));
        btSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSearch.setForeground(new java.awt.Color(255, 255, 255));
        btSearch.setText("Search");
        btSearch.setBorderPainted(false);
        btSearch.setFocusPainted(false);
        btSearch.setPreferredSize(new java.awt.Dimension(150, 40));
        pnTimeRight.add(btSearch);

        pnTime.add(pnTimeRight, java.awt.BorderLayout.CENTER);

        add(pnTime, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCheck;
    private javax.swing.JButton btListCours;
    private javax.swing.JButton btMonth;
    private javax.swing.JButton btSearch;
    private javax.swing.JButton btYearth;
    private javax.swing.JButton btbetween;
    private com.toedter.calendar.JDateChooser dcFrom;
    private com.toedter.calendar.JDateChooser dcTo;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbTime1;
    private com.toedter.calendar.JMonthChooser mcMonth;
    private javax.swing.JPanel pnCenter;
    private javax.swing.JPanel pnChart;
    private javax.swing.JPanel pnLeft;
    private javax.swing.JPanel pnRight;
    private javax.swing.JPanel pnTime;
    private javax.swing.JPanel pnTimeLeft;
    private javax.swing.JPanel pnTimeRight;
    private javax.swing.JPanel pnTop;
    private com.toedter.calendar.JYearChooser ycYearch;
    // End of variables declaration//GEN-END:variables
}
