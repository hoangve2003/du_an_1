/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import service.IThongKeService;
import service.implement.ThongKeService;
import viewmodel.ThongKeChiNhanh;
import viewmodel.ThongKeSanPhamBanChay;
import viewmodel.ThongKeTheoThoiGianViewModel;

/**
 *
 * @author trant
 */
public class ThongKe extends javax.swing.JPanel implements Runnable {

    private IThongKeService thongKeService;
    private DefaultTableModel modelTableDoanhThuThoiGian;
    private DefaultTableModel modelTableSanPhamBanChay;
    private DefaultTableModel modelTableThongKeChiNhanh;

    public ThongKe(TaiKhoanAdmin admin, TaiKhoanNguoiDung nguoiDung) {
        initComponents();
        thongKeService = new ThongKeService();
        modelTableDoanhThuThoiGian = new DefaultTableModel();
        modelTableSanPhamBanChay = new DefaultTableModel();
        modelTableThongKeChiNhanh = new DefaultTableModel();
        Thread loadThongKeSp = new Thread(this);
        loadThongKeSp.start();
        Thread loadThongKeChiNhanh = new Thread(new Runnable() {
            @Override
            public void run() {
                showSanPhamBanChay(thongKeService.getAllSanPhamBanChay());
            }
        });
        loadThongKeChiNhanh.start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlThongKeDoanhThu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKeTheoThoiGian = new javax.swing.JTable();
        dateChooserStart = new com.toedter.calendar.JDateChooser();
        dateChooserEnd = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnLocDoanhThu = new javax.swing.JButton();
        pnlBieuDoDoanhThuThoiGian = new javax.swing.JPanel();
        lblCanhBaoLoc = new javax.swing.JLabel();
        pnlSanPhamBanChay = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPhamBanChay = new javax.swing.JTable();
        pnlBieuDoSpBanChay = new javax.swing.JPanel();
        pnlDoanhThuChiNhanh = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDoanhThuChiNhanh = new javax.swing.JTable();
        pnlBieuDoDoanhThuChiNhanh = new javax.swing.JPanel();

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(0, 0, 0)));

        pnlThongKeDoanhThu.setBackground(new java.awt.Color(255, 255, 255));

        tblThongKeTheoThoiGian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thời gian", "SL đơn hàng", "Tổng tiền hàng", "Tổng chiết khấu", "Tổng doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongKeTheoThoiGian.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblThongKeTheoThoiGian);
        if (tblThongKeTheoThoiGian.getColumnModel().getColumnCount() > 0) {
            tblThongKeTheoThoiGian.getColumnModel().getColumn(0).setResizable(false);
            tblThongKeTheoThoiGian.getColumnModel().getColumn(1).setResizable(false);
            tblThongKeTheoThoiGian.getColumnModel().getColumn(2).setResizable(false);
            tblThongKeTheoThoiGian.getColumnModel().getColumn(3).setResizable(false);
            tblThongKeTheoThoiGian.getColumnModel().getColumn(4).setResizable(false);
        }

        dateChooserStart.setBorder(null);
        dateChooserStart.setDateFormatString("yyyy-MM-dd");

        dateChooserEnd.setBorder(null);
        dateChooserEnd.setDateFormatString("yyyy-MM-dd");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel4.setText("Ngày bắt đầu");

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel5.setText("Ngày kết thúc");

        btnLocDoanhThu.setText("Lọc");
        btnLocDoanhThu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLocDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocDoanhThuActionPerformed(evt);
            }
        });

        pnlBieuDoDoanhThuThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        pnlBieuDoDoanhThuThoiGian.setLayout(new java.awt.BorderLayout());

        lblCanhBaoLoc.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblCanhBaoLoc.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout pnlThongKeDoanhThuLayout = new javax.swing.GroupLayout(pnlThongKeDoanhThu);
        pnlThongKeDoanhThu.setLayout(pnlThongKeDoanhThuLayout);
        pnlThongKeDoanhThuLayout.setHorizontalGroup(
            pnlThongKeDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(pnlThongKeDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongKeDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBieuDoDoanhThuThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThongKeDoanhThuLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooserEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnLocDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCanhBaoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 281, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlThongKeDoanhThuLayout.setVerticalGroup(
            pnlThongKeDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongKeDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongKeDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dateChooserEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateChooserStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLocDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCanhBaoLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBieuDoDoanhThuThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Doanh thu theo thời gian", pnlThongKeDoanhThu);

        pnlSanPhamBanChay.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPhamBanChay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản phẩm", "Tên sản phẩm", "SL bán ra", "Tổng Tiền hàng", "Tổng Doanh thu", "SL đơn hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamBanChay.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblSanPhamBanChay);
        if (tblSanPhamBanChay.getColumnModel().getColumnCount() > 0) {
            tblSanPhamBanChay.getColumnModel().getColumn(0).setResizable(false);
            tblSanPhamBanChay.getColumnModel().getColumn(1).setResizable(false);
            tblSanPhamBanChay.getColumnModel().getColumn(2).setResizable(false);
            tblSanPhamBanChay.getColumnModel().getColumn(3).setResizable(false);
            tblSanPhamBanChay.getColumnModel().getColumn(4).setResizable(false);
            tblSanPhamBanChay.getColumnModel().getColumn(5).setResizable(false);
        }

        pnlBieuDoSpBanChay.setBackground(new java.awt.Color(255, 255, 255));
        pnlBieuDoSpBanChay.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pnlSanPhamBanChayLayout = new javax.swing.GroupLayout(pnlSanPhamBanChay);
        pnlSanPhamBanChay.setLayout(pnlSanPhamBanChayLayout);
        pnlSanPhamBanChayLayout.setHorizontalGroup(
            pnlSanPhamBanChayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamBanChayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBieuDoSpBanChay, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
        );
        pnlSanPhamBanChayLayout.setVerticalGroup(
            pnlSanPhamBanChayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamBanChayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBieuDoSpBanChay, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Sản phẩm bán chạy", pnlSanPhamBanChay);

        pnlDoanhThuChiNhanh.setBackground(new java.awt.Color(255, 255, 255));

        tblDoanhThuChiNhanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã chi nhánh", "SL đơn hàng", "Tổng chiết khấu", "Tổng Doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThuChiNhanh.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblDoanhThuChiNhanh);
        if (tblDoanhThuChiNhanh.getColumnModel().getColumnCount() > 0) {
            tblDoanhThuChiNhanh.getColumnModel().getColumn(0).setResizable(false);
            tblDoanhThuChiNhanh.getColumnModel().getColumn(1).setResizable(false);
            tblDoanhThuChiNhanh.getColumnModel().getColumn(2).setResizable(false);
            tblDoanhThuChiNhanh.getColumnModel().getColumn(3).setResizable(false);
        }

        pnlBieuDoDoanhThuChiNhanh.setBackground(new java.awt.Color(255, 255, 255));
        pnlBieuDoDoanhThuChiNhanh.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pnlDoanhThuChiNhanhLayout = new javax.swing.GroupLayout(pnlDoanhThuChiNhanh);
        pnlDoanhThuChiNhanh.setLayout(pnlDoanhThuChiNhanhLayout);
        pnlDoanhThuChiNhanhLayout.setHorizontalGroup(
            pnlDoanhThuChiNhanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuChiNhanhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBieuDoDoanhThuChiNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
        );
        pnlDoanhThuChiNhanhLayout.setVerticalGroup(
            pnlDoanhThuChiNhanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuChiNhanhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBieuDoDoanhThuChiNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Doanh thu theo chi nhánh", pnlDoanhThuChiNhanh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void run() {
        showDoanhThuTheoChiNhanh(thongKeService.getAllThongKeChiNhanh());
    }
    private void btnLocDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocDoanhThuActionPerformed
        if (dateChooserStart.getDate() != null && dateChooserEnd != null) {
            lblCanhBaoLoc.setText("");
            showDoanhThuTheoThoiGian(dateChooserStart.getDate(), dateChooserEnd.getDate());
        } else {
            lblCanhBaoLoc.setText("Vui lòng chọn thời gian");
        }

    }//GEN-LAST:event_btnLocDoanhThuActionPerformed

    private void showChartDoanhThuTheoThoiGian(List<ThongKeTheoThoiGianViewModel> listThongKe) {
        pnlBieuDoDoanhThuThoiGian.removeAll();
        pnlBieuDoDoanhThuThoiGian.revalidate();
        pnlBieuDoDoanhThuThoiGian.repaint();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeTheoThoiGianViewModel thoiGian : listThongKe) {
            String date = dateFormat.format(thoiGian.getNgay());
            dataset.setValue(thoiGian.getTongTienHang().subtract(thoiGian.getTongTienChietKhau()),
                    "Tổng tiền hàng", date);
        }
        JFreeChart jFreeChart = ChartFactory.createBarChart("THỐNG KÊ DOANH THU THEO THỜI GIAN", null, "TỔNG DOANH THU",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = jFreeChart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.ORANGE);
        plot.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        pnlBieuDoDoanhThuThoiGian.add(chartPanel);
        pnlBieuDoDoanhThuThoiGian.revalidate();
        pnlBieuDoDoanhThuThoiGian.repaint();

    }

    private void showDoanhThuTheoThoiGian(Date start, Date end) {
        modelTableDoanhThuThoiGian.setRowCount(0);
        modelTableDoanhThuThoiGian = (DefaultTableModel) tblThongKeTheoThoiGian.getModel();
        List<ThongKeTheoThoiGianViewModel> thongKeView = thongKeService.getAllThongKeByDate(start, end);
        for (ThongKeTheoThoiGianViewModel thongKeTheoThoiGianViewModel : thongKeView) {
            modelTableDoanhThuThoiGian.addRow(thongKeTheoThoiGianViewModel.getThongKeTheoThoiGian());
        }
        showChartDoanhThuTheoThoiGian(thongKeView);

    }

    private void showChartSanPhamBanChay(List<ThongKeSanPhamBanChay> listSpBanChay) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeSanPhamBanChay spBanChay : listSpBanChay) {
            dataset.setValue(spBanChay.getSoLuongbanRa(), "Số lượng bán",
                    spBanChay.getMaSp() + " - " + spBanChay.getTenSP());
        }

        JFreeChart jFreeChart = ChartFactory.createBarChart("TOP SẢN PHẨM BÁN CHẠY", null, "SỐ LƯỢNG BÁN RA",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = jFreeChart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.ORANGE);
        p.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        pnlBieuDoSpBanChay.add(chartPanel);
        pnlBieuDoSpBanChay.revalidate();
        pnlBieuDoSpBanChay.repaint();
    }

    private void showSanPhamBanChay(List<ThongKeSanPhamBanChay> listSpBanChay) {
        modelTableSanPhamBanChay = (DefaultTableModel) tblSanPhamBanChay.getModel();
        modelTableSanPhamBanChay.setRowCount(0);
        for (ThongKeSanPhamBanChay spBanChay : listSpBanChay) {
            modelTableSanPhamBanChay.addRow(spBanChay.getThongKeSanPhamBanChay());
        }
        showChartSanPhamBanChay(listSpBanChay);

    }

    private void showChartDoanhThuChiNhanh(List<ThongKeChiNhanh> listThongKeChiNhanh) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ThongKeChiNhanh chiNhanh : listThongKeChiNhanh) {
            dataset.setValue(chiNhanh.getTongDoanhThu(), "Tổng doanh thu", chiNhanh.getMaChiNhanh());
        }

        JFreeChart jFreeChart = ChartFactory.createBarChart("DOANH THU THEO TỪNG CHI NHÁNH", null, "TỔNG DOANH THU",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = jFreeChart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.ORANGE);
        p.setBackgroundPaint(Color.WHITE);
        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        pnlBieuDoDoanhThuChiNhanh.add(chartPanel);
        pnlBieuDoDoanhThuChiNhanh.revalidate();
        pnlBieuDoDoanhThuChiNhanh.repaint();
    }

    private void showDoanhThuTheoChiNhanh(List<ThongKeChiNhanh> listThongKeChiNhanh) {
        modelTableThongKeChiNhanh = (DefaultTableModel) tblDoanhThuChiNhanh.getModel();
        modelTableThongKeChiNhanh.setRowCount(0);
        for (ThongKeChiNhanh chiNhanh : listThongKeChiNhanh) {
            modelTableThongKeChiNhanh.addRow(chiNhanh.getThongKeChiNhanh());
        }
        showChartDoanhThuChiNhanh(listThongKeChiNhanh);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLocDoanhThu;
    private com.toedter.calendar.JDateChooser dateChooserEnd;
    private com.toedter.calendar.JDateChooser dateChooserStart;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCanhBaoLoc;
    private javax.swing.JPanel pnlBieuDoDoanhThuChiNhanh;
    private javax.swing.JPanel pnlBieuDoDoanhThuThoiGian;
    private javax.swing.JPanel pnlBieuDoSpBanChay;
    private javax.swing.JPanel pnlDoanhThuChiNhanh;
    private javax.swing.JPanel pnlSanPhamBanChay;
    private javax.swing.JPanel pnlThongKeDoanhThu;
    private javax.swing.JTable tblDoanhThuChiNhanh;
    private javax.swing.JTable tblSanPhamBanChay;
    private javax.swing.JTable tblThongKeTheoThoiGian;
    // End of variables declaration//GEN-END:variables

}
