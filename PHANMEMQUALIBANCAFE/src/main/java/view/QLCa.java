/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import domainmodel.Ca;
import domainmodel.HoatDongCa;
import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.CaRepo;
import service.IBanHangService;
import service.ICa;
import service.implement.BanHangService;
import service.implement.CaService;
import viewmodel.CaViewModel_Quan;
import viewmodel.ChiNhanhViewModel_Hoang;
import viewmodel.NhanVienViewModel_Van;

/**
 *
 * @author trant
 */
public class QLCa extends javax.swing.JPanel implements Runnable {

    private CaRepo caRepo;
    private ICa caService;
    private IBanHangService banHangService;
    private TaiKhoanAdmin _admin;
    private TaiKhoanNguoiDung _nguoiDung;
    private DefaultTableModel modelTableNhanVien;
    private DefaultTableModel modelTableCa;
    private DefaultComboBoxModel<ChiNhanhViewModel_Hoang> modelComboChiNhanh;
    ChiNhanhViewModel_Hoang _chiNhanhNguoiDung;
    private Ca caIsRunning = null;

    public QLCa(TaiKhoanAdmin admin, TaiKhoanNguoiDung nguoiDung) {
        initComponents();
        _admin = admin;
        _nguoiDung = nguoiDung;
        caRepo = new CaRepo();
        caService = new CaService();
        banHangService = new BanHangService();
        modelTableNhanVien = new DefaultTableModel();
        modelTableCa = new DefaultTableModel();
        modelTableNhanVien = (DefaultTableModel) tblNhanVien.getModel();
        modelTableCa = (DefaultTableModel) tblCa.getModel();
        btnKhoiPhucCa.setEnabled(false);
        Thread loadData = new Thread(this);
        loadData.start();
        try {
            loadData.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(QLCa.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkTimeOfCa();

    }

    private void checkTimeOfCa() {
        Thread notificationCloseCa = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean stop = false;
                while (stop == false) {
                    if (caIsRunning != null) {
                        try {
                            Thread.sleep(1000);
                            LocalTime timeNow = LocalTime.now();
                            LocalTime timeEndCa = caIsRunning.getGioKetThuc();
                            int valueCompare = timeNow.compareTo(timeEndCa);
                            if (valueCompare >= 0) {
                                stop = true;
                                JOptionPane.showMessageDialog(pnlQLCa, "Đã hết ca");
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QLCa.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        stop = true;
                    }
                }
            }
        });
        notificationCloseCa.start();
    }

    @Override
    public void run() {
        if (_admin != null) {
            modelComboChiNhanh = (DefaultComboBoxModel) new DefaultComboBoxModel<>(banHangService.getAllChiNhanh().toArray());
            cboChiNhanh.setModel((DefaultComboBoxModel) modelComboChiNhanh);
            showNhanVienToTable(caService.getNhanVienByChiNhanh(
                    ((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getElementAt(0)).getId())
            );
            caIsRunning = caRepo.getCaRunningOfChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId());
            if (caIsRunning != null) {
                lblCaDangHoatDong.setText(caIsRunning.getMa() + " đang hoạt động");
            } else {
                lblCaDangHoatDong.setText("Chưa mở ca");
            }

        } else {
            cboChiNhanh.setVisible(false);
            _chiNhanhNguoiDung = banHangService.getChiNhanhbyTaiKhoan(_nguoiDung.getId());
            showNhanVienToTable(caService.getNhanVienByChiNhanh(_chiNhanhNguoiDung.getId()));
            caIsRunning = caRepo.getCaRunningOfChiNhanh(_chiNhanhNguoiDung.getId());
            if (caIsRunning != null) {
                lblCaDangHoatDong.setText(caIsRunning.getMa() + " đang hoạt động");
            } else {
                lblCaDangHoatDong.setText("Chưa mở ca");
            }
        }
        showCaToTable(caService.getAllCaDangSuDung());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dLogMoCa = new javax.swing.JDialog();
        pnlMoCa = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTienKetDauCa = new javax.swing.JTextField();
        btnMoCaHuyBo = new javax.swing.JButton();
        btnXacNhanMoCa = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblCanhBaoTienDauCa = new javax.swing.JLabel();
        dLongDongCa = new javax.swing.JDialog();
        pnlDongCa = new javax.swing.JPanel();
        txtTienThucTeTrongKet = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnXacNhanDongCa = new javax.swing.JButton();
        btnDongCaHuyBo = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lblChenhLech = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblTienKetDauCa = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblTienBanGiao = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblCanhBaoTienThucTe = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlQLCa = new javax.swing.JPanel();
        btnMoCa = new javax.swing.JButton();
        btnDongCa = new javax.swing.JButton();
        pnlCa = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCa = new javax.swing.JTable();
        pnlNhanVien = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        cboChiNhanh = new javax.swing.JComboBox<>();
        pnlCRUDCa = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaCa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnXoaCa = new javax.swing.JButton();
        btnSuaCa = new javax.swing.JButton();
        btnThemCa = new javax.swing.JButton();
        txtGioBatDau = new javax.swing.JTextField();
        txtPhutBatDau = new javax.swing.JTextField();
        txtGioKetThuc = new javax.swing.JTextField();
        txtPhutKetThuc = new javax.swing.JTextField();
        lblCanhBaoGioBatDau = new javax.swing.JLabel();
        lblCanhBaoGioKetThuc = new javax.swing.JLabel();
        lblCanhBaoMa = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnCapNhatCaNhanVien = new javax.swing.JButton();
        lblCaDangHoatDong = new javax.swing.JLabel();
        rdoDangSuDung = new javax.swing.JRadioButton();
        rdoDaXoa = new javax.swing.JRadioButton();
        btnKhoiPhucCa = new javax.swing.JButton();
        lbliconGoiyCapNhatCaNv = new javax.swing.JLabel();
        lblGoiYCapNhatCaNv = new javax.swing.JLabel();

        dLogMoCa.setUndecorated(true);
        dLogMoCa.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                dLogMoCaWindowLostFocus(evt);
            }
        });

        pnlMoCa.setBackground(new java.awt.Color(225, 218, 197));
        pnlMoCa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 0), 2));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("Tiền két đầu ca ( Tiền mặt )");

        txtTienKetDauCa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKetDauCaKeyReleased(evt);
            }
        });

        btnMoCaHuyBo.setBackground(new java.awt.Color(255, 0, 0));
        btnMoCaHuyBo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnMoCaHuyBo.setForeground(new java.awt.Color(255, 255, 255));
        btnMoCaHuyBo.setText("Hủy bỏ");
        btnMoCaHuyBo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMoCaHuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoCaHuyBoActionPerformed(evt);
            }
        });

        btnXacNhanMoCa.setBackground(new java.awt.Color(0, 153, 0));
        btnXacNhanMoCa.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnXacNhanMoCa.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhanMoCa.setText("Xác nhận mở ca");
        btnXacNhanMoCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXacNhanMoCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanMoCaActionPerformed(evt);
            }
        });

        jLabel8.setText("VND");

        lblCanhBaoTienDauCa.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout pnlMoCaLayout = new javax.swing.GroupLayout(pnlMoCa);
        pnlMoCa.setLayout(pnlMoCaLayout);
        pnlMoCaLayout.setHorizontalGroup(
            pnlMoCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMoCaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMoCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMoCaLayout.createSequentialGroup()
                        .addComponent(btnXacNhanMoCa, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMoCaHuyBo, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                    .addGroup(pnlMoCaLayout.createSequentialGroup()
                        .addGroup(pnlMoCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(pnlMoCaLayout.createSequentialGroup()
                                .addComponent(txtTienKetDauCa, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel8)))
                        .addGap(47, 47, 47)
                        .addComponent(lblCanhBaoTienDauCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMoCaLayout.setVerticalGroup(
            pnlMoCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMoCaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMoCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKetDauCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(lblCanhBaoTienDauCa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMoCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhanMoCa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoCaHuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        dLogMoCa.getContentPane().add(pnlMoCa, java.awt.BorderLayout.CENTER);

        dLongDongCa.setUndecorated(true);
        dLongDongCa.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                dLongDongCaWindowLostFocus(evt);
            }
        });

        pnlDongCa.setBackground(new java.awt.Color(225, 218, 197));
        pnlDongCa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 2));

        txtTienThucTeTrongKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienThucTeTrongKetKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel6.setText("Tiền thực tế trong két ( tiền mặt ):");

        jLabel11.setText("VND");

        btnXacNhanDongCa.setBackground(new java.awt.Color(0, 153, 0));
        btnXacNhanDongCa.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnXacNhanDongCa.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhanDongCa.setText("Xác nhận đóng ca");
        btnXacNhanDongCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXacNhanDongCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanDongCaActionPerformed(evt);
            }
        });

        btnDongCaHuyBo.setBackground(new java.awt.Color(255, 0, 0));
        btnDongCaHuyBo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnDongCaHuyBo.setForeground(new java.awt.Color(255, 255, 255));
        btnDongCaHuyBo.setText("Hủy bỏ");
        btnDongCaHuyBo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDongCaHuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongCaHuyBoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel15.setText("Chênh lệch :");

        lblChenhLech.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblChenhLech.setText("0");
        lblChenhLech.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel17.setText("VND");

        jLabel18.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel18.setText("Tiền két đầu ca :");

        lblTienKetDauCa.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblTienKetDauCa.setText("0");
        lblTienKetDauCa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel20.setText("VND");

        jLabel21.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel21.setText("Tiền bàn giao ( tiền mặt ) :");

        lblTienBanGiao.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblTienBanGiao.setText("0");
        lblTienBanGiao.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel23.setText("VND");

        lblCanhBaoTienThucTe.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout pnlDongCaLayout = new javax.swing.GroupLayout(pnlDongCa);
        pnlDongCa.setLayout(pnlDongCaLayout);
        pnlDongCaLayout.setHorizontalGroup(
            pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDongCaLayout.createSequentialGroup()
                .addComponent(btnXacNhanDongCa, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDongCaHuyBo, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
            .addGroup(pnlDongCaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDongCaLayout.createSequentialGroup()
                        .addGroup(pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDongCaLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(140, 140, 140)
                                .addComponent(lblTienKetDauCa, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20))
                            .addGroup(pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlDongCaLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(164, 164, 164)
                                    .addComponent(lblChenhLech, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel17))
                                .addGroup(pnlDongCaLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTienThucTeTrongKet, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel11))))
                        .addGap(18, 18, 18)
                        .addComponent(lblCanhBaoTienThucTe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlDongCaLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(72, 72, 72)
                        .addComponent(lblTienBanGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDongCaLayout.setVerticalGroup(
            pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDongCaLayout.createSequentialGroup()
                .addGroup(pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDongCaLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(lblCanhBaoTienThucTe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDongCaLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(lblTienKetDauCa)
                            .addComponent(jLabel18))
                        .addGap(26, 26, 26)
                        .addGroup(pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTienThucTeTrongKet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addGroup(pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDongCaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17))
                    .addGroup(pnlDongCaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblChenhLech)
                            .addComponent(jLabel15))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienBanGiao)
                    .addComponent(jLabel23)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(pnlDongCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXacNhanDongCa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDongCaHuyBo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        dLongDongCa.getContentPane().add(pnlDongCa, java.awt.BorderLayout.CENTER);

        pnlQLCa.setBackground(new java.awt.Color(225, 218, 197));

        btnMoCa.setBackground(new java.awt.Color(108, 83, 54));
        btnMoCa.setFont(new java.awt.Font("sansserif", 1, 48)); // NOI18N
        btnMoCa.setForeground(new java.awt.Color(255, 255, 255));
        btnMoCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/openCa48px.png"))); // NOI18N
        btnMoCa.setText("Mở ca");
        btnMoCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMoCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoCaActionPerformed(evt);
            }
        });

        btnDongCa.setBackground(new java.awt.Color(108, 83, 54));
        btnDongCa.setFont(new java.awt.Font("sansserif", 1, 48)); // NOI18N
        btnDongCa.setForeground(new java.awt.Color(255, 255, 255));
        btnDongCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/CloseCa48px.png"))); // NOI18N
        btnDongCa.setText("Đóng ca");
        btnDongCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDongCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongCaActionPerformed(evt);
            }
        });

        pnlCa.setBackground(new java.awt.Color(225, 218, 197));
        pnlCa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách ca trong ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 14))); // NOI18N

        tblCa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chọn", "Id", "Mã ca", "Giờ bắt đầu", "Giờ kết thúc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCa.getTableHeader().setReorderingAllowed(false);
        tblCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCa);
        if (tblCa.getColumnModel().getColumnCount() > 0) {
            tblCa.getColumnModel().getColumn(0).setMinWidth(50);
            tblCa.getColumnModel().getColumn(0).setMaxWidth(50);
            tblCa.getColumnModel().getColumn(1).setMinWidth(0);
            tblCa.getColumnModel().getColumn(1).setMaxWidth(0);
            tblCa.getColumnModel().getColumn(2).setResizable(false);
            tblCa.getColumnModel().getColumn(3).setResizable(false);
            tblCa.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout pnlCaLayout = new javax.swing.GroupLayout(pnlCa);
        pnlCa.setLayout(pnlCaLayout);
        pnlCaLayout.setHorizontalGroup(
            pnlCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        pnlCaLayout.setVerticalGroup(
            pnlCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pnlNhanVien.setBackground(new java.awt.Color(225, 218, 197));
        pnlNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên theo chi nhánh", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 14))); // NOI18N

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Mã nhân viên", "Tên nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.getTableHeader().setReorderingAllowed(false);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(0).setMinWidth(0);
            tblNhanVien.getColumnModel().getColumn(0).setMaxWidth(0);
            tblNhanVien.getColumnModel().getColumn(1).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/location_10px.png"))); // NOI18N
        jLabel10.setText("Chi nhánh");

        cboChiNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChiNhanhActionPerformed(evt);
            }
        });

        pnlCRUDCa.setBackground(new java.awt.Color(225, 218, 197));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Mã ca :");

        txtMaCa.setBackground(new java.awt.Color(225, 218, 197));
        txtMaCa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtMaCa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaCaKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Giờ bắt đầu :");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Giờ kết thúc :");

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText(":");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(":");

        btnXoaCa.setBackground(new java.awt.Color(108, 83, 54));
        btnXoaCa.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnXoaCa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_30px.png"))); // NOI18N
        btnXoaCa.setText("XÓA CA");
        btnXoaCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCaActionPerformed(evt);
            }
        });

        btnSuaCa.setBackground(new java.awt.Color(108, 83, 54));
        btnSuaCa.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnSuaCa.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sync_30px.png"))); // NOI18N
        btnSuaCa.setText("CẬP NHẬT CA");
        btnSuaCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSuaCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCaActionPerformed(evt);
            }
        });

        btnThemCa.setBackground(new java.awt.Color(108, 83, 54));
        btnThemCa.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnThemCa.setForeground(new java.awt.Color(255, 255, 255));
        btnThemCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_20px.png"))); // NOI18N
        btnThemCa.setText("THÊM CA TRONG NGÀY");
        btnThemCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCaActionPerformed(evt);
            }
        });

        txtGioBatDau.setBackground(new java.awt.Color(225, 218, 197));
        txtGioBatDau.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        txtGioBatDau.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGioBatDau.setBorder(javax.swing.BorderFactory.createTitledBorder("Giờ"));
        txtGioBatDau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGioBatDauKeyReleased(evt);
            }
        });

        txtPhutBatDau.setBackground(new java.awt.Color(225, 218, 197));
        txtPhutBatDau.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        txtPhutBatDau.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPhutBatDau.setBorder(javax.swing.BorderFactory.createTitledBorder("Phút"));
        txtPhutBatDau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhutBatDauKeyReleased(evt);
            }
        });

        txtGioKetThuc.setBackground(new java.awt.Color(225, 218, 197));
        txtGioKetThuc.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        txtGioKetThuc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGioKetThuc.setBorder(javax.swing.BorderFactory.createTitledBorder("Giờ"));
        txtGioKetThuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGioKetThucKeyReleased(evt);
            }
        });

        txtPhutKetThuc.setBackground(new java.awt.Color(225, 218, 197));
        txtPhutKetThuc.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        txtPhutKetThuc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPhutKetThuc.setBorder(javax.swing.BorderFactory.createTitledBorder("Phút"));
        txtPhutKetThuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhutKetThucKeyReleased(evt);
            }
        });

        lblCanhBaoGioBatDau.setForeground(new java.awt.Color(255, 51, 51));

        lblCanhBaoGioKetThuc.setForeground(new java.awt.Color(255, 51, 51));

        lblCanhBaoMa.setForeground(new java.awt.Color(255, 51, 51));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dauSao.png"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dauSao.png"))); // NOI18N

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dauSao.png"))); // NOI18N

        javax.swing.GroupLayout pnlCRUDCaLayout = new javax.swing.GroupLayout(pnlCRUDCa);
        pnlCRUDCa.setLayout(pnlCRUDCaLayout);
        pnlCRUDCaLayout.setHorizontalGroup(
            pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                        .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                                .addGap(213, 213, 213)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPhutBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel25))
                            .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                                .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCRUDCaLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel24)
                                        .addGap(106, 106, 106)))
                                .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                                        .addComponent(txtGioKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPhutKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtMaCa, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCanhBaoGioBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCanhBaoGioKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCanhBaoMa, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                        .addGap(0, 62, Short.MAX_VALUE))
                    .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnThemCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoaCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaCa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlCRUDCaLayout.setVerticalGroup(
            pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                            .addComponent(jLabel24)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4))
                        .addComponent(txtMaCa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCanhBaoMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCanhBaoGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel7)
                                .addComponent(txtGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPhutBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel25)))
                .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCRUDCaLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCanhBaoGioKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtPhutKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCRUDCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGioKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCRUDCaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addGap(79, 79, 79)))
                .addComponent(btnThemCa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoaCa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSuaCa)
                .addContainerGap())
        );

        btnCapNhatCaNhanVien.setBackground(new java.awt.Color(108, 83, 54));
        btnCapNhatCaNhanVien.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnCapNhatCaNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatCaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sync_30px.png"))); // NOI18N
        btnCapNhatCaNhanVien.setText("CẬP NHẬT CA NHÂN VIÊN");
        btnCapNhatCaNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapNhatCaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatCaNhanVienActionPerformed(evt);
            }
        });

        lblCaDangHoatDong.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblCaDangHoatDong.setForeground(new java.awt.Color(255, 0, 0));
        lblCaDangHoatDong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaDangHoatDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/notification_40px.png"))); // NOI18N
        lblCaDangHoatDong.setText("Chưa mở ca");

        buttonGroup1.add(rdoDangSuDung);
        rdoDangSuDung.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        rdoDangSuDung.setSelected(true);
        rdoDangSuDung.setText("Đang sử dụng");
        rdoDangSuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDangSuDungActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaXoa);
        rdoDaXoa.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        rdoDaXoa.setText("Đã xóa");
        rdoDaXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaXoaActionPerformed(evt);
            }
        });

        btnKhoiPhucCa.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnKhoiPhucCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/replay_20px.png"))); // NOI18N
        btnKhoiPhucCa.setText("khôi phục ca");
        btnKhoiPhucCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKhoiPhucCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucCaActionPerformed(evt);
            }
        });

        lbliconGoiyCapNhatCaNv.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbliconGoiyCapNhatCaNv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/recomment 10px.png"))); // NOI18N
        lbliconGoiyCapNhatCaNv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbliconGoiyCapNhatCaNvMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbliconGoiyCapNhatCaNvMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlQLCaLayout = new javax.swing.GroupLayout(pnlQLCa);
        pnlQLCa.setLayout(pnlQLCaLayout);
        pnlQLCaLayout.setHorizontalGroup(
            pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQLCaLayout.createSequentialGroup()
                .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlQLCaLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoDangSuDung)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDaXoa)
                        .addGap(37, 37, 37)
                        .addComponent(btnKhoiPhucCa, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlQLCaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(pnlNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlQLCaLayout.createSequentialGroup()
                                .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCapNhatCaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlQLCaLayout.createSequentialGroup()
                                        .addComponent(lbliconGoiyCapNhatCaNv)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblGoiYCapNhatCaNv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlQLCaLayout.createSequentialGroup()
                                .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnMoCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCaDangHoatDong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDongCa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlCRUDCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pnlQLCaLayout.setVerticalGroup(
            pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlQLCaLayout.createSequentialGroup()
                .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(cboChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlQLCaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoDangSuDung)
                            .addComponent(rdoDaXoa)))
                    .addGroup(pnlQLCaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnKhoiPhucCa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlQLCaLayout.createSequentialGroup()
                        .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlQLCaLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btnCapNhatCaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbliconGoiyCapNhatCaNv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblGoiYCapNhatCaNv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlQLCaLayout.createSequentialGroup()
                                .addComponent(pnlCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(pnlQLCaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlQLCaLayout.createSequentialGroup()
                                .addComponent(lblCaDangHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(btnMoCa, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDongCa, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlCRUDCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addComponent(pnlNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlQLCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlQLCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboChiNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChiNhanhActionPerformed
        showNhanVienToTable(caService.getNhanVienByChiNhanh(
                ((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId())
        );
        for (int i = 0; i < tblCa.getRowCount(); i++) {
            tblCa.setValueAt(false, i, 0);
        }
        caIsRunning = caRepo.getCaRunningOfChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId());
        if (caIsRunning != null) {
            lblCaDangHoatDong.setText(caIsRunning.getMa() + " đang hoạt động");
        } else {
            lblCaDangHoatDong.setText("Chưa mở ca");
        }
        checkTimeOfCa();
        tblCa.clearSelection();
        txtMaCa.setText("");
        txtGioBatDau.setText("");
        txtGioKetThuc.setText("");
        txtPhutBatDau.setText("");
        txtPhutKetThuc.setText("");

    }//GEN-LAST:event_cboChiNhanhActionPerformed

    private void btnMoCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoCaActionPerformed
        int row = tblCa.getSelectedRow();
        if (!lblCaDangHoatDong.getText().equalsIgnoreCase("Chưa mở ca")) {
            JOptionPane.showMessageDialog(this, "Đang có ca hoạt động, không thể mở thêm");
        } else {
            if (row != -1) {
                LocalTime timeNow = LocalTime.now();
                Integer gioMoCa = Integer.parseInt(tblCa.getValueAt(row, 3).toString().substring(0, 2));
                Integer phutMoCa = Integer.parseInt(tblCa.getValueAt(row, 3).toString().substring(3, 5));
                Integer gioDongCa = Integer.parseInt(tblCa.getValueAt(row, 4).toString().substring(0, 2));
                Integer phutDongCa = Integer.parseInt(tblCa.getValueAt(row, 4).toString().substring(3, 5));
                LocalTime timeMoCa = LocalTime.of(gioMoCa, phutMoCa);
                LocalTime timeDongCa = LocalTime.of(gioDongCa, phutDongCa);
                int valueCompareMoCa = timeNow.compareTo(timeMoCa);
                int valueCompareDongCa = timeNow.compareTo(timeDongCa);
                System.out.println(timeMoCa);
                System.out.println(timeNow);
                if (valueCompareMoCa >= 0 && valueCompareDongCa <= 0) {
                    dLogMoCa.setVisible(true);
                    dLogMoCa.setSize(720, 350);
                    dLogMoCa.setLocationRelativeTo(null);

                } else {
                    JOptionPane.showMessageDialog(this, "Chưa đến giờ, không thể mở ca");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ca muốn mở");
            }
        }
    }//GEN-LAST:event_btnMoCaActionPerformed

    private void btnDongCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongCaActionPerformed
        if (_admin != null) {
            caIsRunning = caRepo.getCaRunningOfChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId());
            HoatDongCa activity = caRepo.getHoatDongCaRunningOfChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId());
            if (caIsRunning != null) {
                dLongDongCa.setVisible(true);
                dLongDongCa.setSize(740, 340);
                dLongDongCa.setLocationRelativeTo(null);
                lblTienKetDauCa.setText(String.valueOf(activity.getTienDauCa()));
            } else {
                JOptionPane.showMessageDialog(this, "Không có ca nào đang hoạt động");
            }
        } else {
            caIsRunning = caRepo.getCaRunningOfChiNhanh(_chiNhanhNguoiDung.getId());
            HoatDongCa activity = caRepo.getHoatDongCaRunningOfChiNhanh(_chiNhanhNguoiDung.getId());
            if (caIsRunning != null) {
                dLongDongCa.setVisible(true);
                dLongDongCa.setSize(740, 340);
                dLongDongCa.setLocationRelativeTo(null);
                lblTienKetDauCa.setText(String.valueOf(activity.getTienDauCa()));
            } else {
                JOptionPane.showMessageDialog(this, "Không có ca nào đang hoạt động");
            }
        }

    }//GEN-LAST:event_btnDongCaActionPerformed

    private void dLogMoCaWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dLogMoCaWindowLostFocus
        dLogMoCa.setVisible(false);
    }//GEN-LAST:event_dLogMoCaWindowLostFocus

    private void dLongDongCaWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dLongDongCaWindowLostFocus
        dLongDongCa.setVisible(false);

    }//GEN-LAST:event_dLongDongCaWindowLostFocus

    private void btnThemCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCaActionPerformed
        if (!txtMaCa.getText().isBlank() && !txtGioBatDau.getText().isBlank() && !txtGioKetThuc.getText().isBlank()) {
            if (caService.checkExistedOfMaCa(txtMaCa.getText())) {
                LocalTime gioBatDau = LocalTime.of(Integer.parseInt(txtGioBatDau.getText()),
                        Integer.parseInt(txtPhutBatDau.getText()));
                LocalTime gioKetThuc = LocalTime.of(Integer.parseInt(txtGioKetThuc.getText()),
                        Integer.parseInt(txtPhutKetThuc.getText()));
                CaViewModel_Quan caView = new CaViewModel_Quan();
                caView.setMa(txtMaCa.getText());
                caView.setGioBD(gioBatDau);
                caView.setGioKT(gioKetThuc);
                String id = caService.insertCa(caView);
                if (id != null) {
                    JOptionPane.showMessageDialog(this, "Thêm ca làm việc thành công");
                    showCaToTable(caService.getAllCaDangSuDung());
                    clearFormCRUD();
                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mã ca này đã được sử dụng");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ Mã ca và giờ");
        }

    }//GEN-LAST:event_btnThemCaActionPerformed

    private void btnXoaCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCaActionPerformed
        int row = tblCa.getSelectedRow();
        if (row != -1) {
            if (caIsRunning != null) {
                JOptionPane.showMessageDialog(this, "Vui lòng đóng ca hiện tại");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa ca này", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    caService.changeStateOfCa(tblCa.getValueAt(row, 1).toString());
                    JOptionPane.showMessageDialog(this, "Xóa ca thành công");
                    showCaToTable(caService.getAllCaDangSuDung());
                    clearFormCRUD();
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ca muốn xóa");
        }
    }//GEN-LAST:event_btnXoaCaActionPerformed

    private void clearFormCRUD() {
        txtMaCa.setText("");
        txtGioBatDau.setText("");
        txtGioKetThuc.setText("");
        txtPhutBatDau.setText("");
        txtGioKetThuc.setText("");
    }
    private void btnSuaCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCaActionPerformed
        int row = tblCa.getSelectedRow();
        if (row != -1) {
            if (caIsRunning != null) {
                JOptionPane.showMessageDialog(this, "Vui lòng đóng ca hiện tại");
            } else {
                if (!txtMaCa.getText().isBlank() && !txtGioBatDau.getText().isBlank() && !txtGioKetThuc.getText().isBlank()) {
                    LocalTime gioBatDau = LocalTime.of(Integer.parseInt(txtGioBatDau.getText()),
                            Integer.parseInt(txtPhutBatDau.getText()));
                    LocalTime gioKetThuc = LocalTime.of(Integer.parseInt(txtGioKetThuc.getText()),
                            Integer.parseInt(txtPhutKetThuc.getText()));
                    CaViewModel_Quan caView = new CaViewModel_Quan();
                    caView.setId(tblCa.getValueAt(row, 1).toString());
                    caView.setMa(txtMaCa.getText());
                    caView.setGioBD(gioBatDau);
                    caView.setGioKT(gioKetThuc);
                    caService.updateCa(caView);
                    JOptionPane.showMessageDialog(this, "Cập nhật ca thành công");
                    showCaToTable(caService.getAllCaDangSuDung());
                    clearFormCRUD();
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ Mã ca và giờ");
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ca muốn cập nhật");
        }
    }//GEN-LAST:event_btnSuaCaActionPerformed

    private void txtGioBatDauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGioBatDauKeyReleased
        if (!caService.checkHourOfCa(txtGioBatDau.getText())) {
            txtGioBatDau.setText("");
            lblCanhBaoGioBatDau.setText("Thời gian không hợp lệ");
        } else {
            lblCanhBaoGioBatDau.setText("");
        }
    }//GEN-LAST:event_txtGioBatDauKeyReleased

    private void txtPhutBatDauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhutBatDauKeyReleased
        if (!caService.checkMinuteOfCa(txtPhutBatDau.getText())) {
            txtPhutBatDau.setText("");
            lblCanhBaoGioBatDau.setText("Thời gian không hợp lệ");
        } else {
            lblCanhBaoGioBatDau.setText("");
        }
    }//GEN-LAST:event_txtPhutBatDauKeyReleased

    private void txtGioKetThucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGioKetThucKeyReleased
        if (!caService.checkHourOfCa(txtGioKetThuc.getText()) || Integer.parseInt(txtGioKetThuc.getText()) == 0) {
            txtGioKetThuc.setText("");
            lblCanhBaoGioKetThuc.setText("Thời gian không hợp lệ");
        } else {
            lblCanhBaoGioKetThuc.setText("");
        }
    }//GEN-LAST:event_txtGioKetThucKeyReleased

    private void txtPhutKetThucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhutKetThucKeyReleased
        if (!caService.checkMinuteOfCa(txtPhutKetThuc.getText())) {
            txtPhutKetThuc.setText("");
            lblCanhBaoGioKetThuc.setText("Thời gian không hợp lệ");
        } else {
            lblCanhBaoGioKetThuc.setText("");
        }
    }//GEN-LAST:event_txtPhutKetThucKeyReleased

    private void txtMaCaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaCaKeyReleased
        if (txtMaCa.getText().length() > 5) {
            lblCanhBaoMa.setText("Tối đa 5 kí tự");
            txtMaCa.setText("");
        } else {
            if (caService.checkExistedOfMaCa(txtMaCa.getText())) {
                lblCanhBaoMa.setText("");
            } else {
                lblCanhBaoMa.setText("Mã này đã được sử dụng");
                txtMaCa.setText("");

            }
        }
    }//GEN-LAST:event_txtMaCaKeyReleased

    private void btnCapNhatCaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatCaNhanVienActionPerformed
        int rowNhanVien = tblNhanVien.getSelectedRow();
        Set<String> idCaSelected = new HashSet<>();
        if (rowNhanVien != -1) {
            for (int i = 0; i < tblCa.getRowCount(); i++) {
                if (tblCa.getValueAt(i, 0).equals(true)) {
                    idCaSelected.add(tblCa.getValueAt(i, 1).toString());
                }
            }
            caService.addCaToNhanVien(tblNhanVien.getValueAt(rowNhanVien, 0).toString(), idCaSelected);
            JOptionPane.showMessageDialog(this, "Cập nhật ca cho nhân viên thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 nhân viên");
        }
    }//GEN-LAST:event_btnCapNhatCaNhanVienActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        for (int i = 0; i < tblCa.getRowCount(); i++) {
            tblCa.setValueAt(false, i, 0);
        }
        int row = tblNhanVien.getSelectedRow();
        if (row != -1) {
            Set<CaViewModel_Quan> caOfNhanVien = caService.getCaOfNhanVien(tblNhanVien.getValueAt(row, 0).toString());
            if (caOfNhanVien.isEmpty()) {
                for (int i = 0; i < tblCa.getRowCount(); i++) {
                    tblCa.setValueAt(false, i, 0);
                }
            } else {
                for (CaViewModel_Quan caNhanVien : caOfNhanVien) {
                    for (int i = 0; i < tblCa.getRowCount(); i++) {
                        if (caNhanVien.getId().equals(tblCa.getValueAt(i, 1).toString())) {
                            tblCa.setValueAt(true, i, 0);
                            break;
                        }
                    }
                }
            }
        }

    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void tblCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCaMouseClicked
        int row = tblCa.getSelectedRow();
        if (row != -1) {
            txtMaCa.setText(tblCa.getValueAt(row, 2).toString());
            txtGioBatDau.setText(tblCa.getValueAt(row, 3).toString().substring(0, 2));
            txtPhutBatDau.setText(tblCa.getValueAt(row, 3).toString().substring(3, 5));
            txtGioKetThuc.setText(tblCa.getValueAt(row, 4).toString().substring(0, 2));
            txtPhutKetThuc.setText(tblCa.getValueAt(row, 4).toString().substring(3, 5));
            lblCanhBaoMa.setText("");
            lblCanhBaoGioBatDau.setText("");
            lblCanhBaoGioKetThuc.setText("");
        }
    }//GEN-LAST:event_tblCaMouseClicked

    private void rdoDangSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDangSuDungActionPerformed
        showCaToTable(caService.getAllCaDangSuDung());
        btnMoCa.setEnabled(true);
        btnDongCa.setEnabled(true);
        btnThemCa.setEnabled(true);
        btnXoaCa.setEnabled(true);
        btnSuaCa.setEnabled(true);
        btnCapNhatCaNhanVien.setEnabled(true);
        btnKhoiPhucCa.setEnabled(false);
    }//GEN-LAST:event_rdoDangSuDungActionPerformed

    private void rdoDaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaXoaActionPerformed
        showCaToTable(caService.getAllCaDaXoa());
        btnMoCa.setEnabled(false);
        btnDongCa.setEnabled(false);
        btnThemCa.setEnabled(false);
        btnXoaCa.setEnabled(false);
        btnSuaCa.setEnabled(false);
        btnCapNhatCaNhanVien.setEnabled(false);
        btnKhoiPhucCa.setEnabled(true);
    }//GEN-LAST:event_rdoDaXoaActionPerformed

    private void btnKhoiPhucCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucCaActionPerformed
        int row = tblCa.getSelectedRow();
        if (row != -1) {
            caService.changeStateOfCa(tblCa.getValueAt(row, 1).toString());
            JOptionPane.showMessageDialog(this, "Khôi phục ca thành công");
            showCaToTable(caService.getAllCaDaXoa());
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ca cần khôi phục");
        }
    }//GEN-LAST:event_btnKhoiPhucCaActionPerformed

    private void lbliconGoiyCapNhatCaNvMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbliconGoiyCapNhatCaNvMouseEntered
        lblGoiYCapNhatCaNv.setText("Chọn nhân viên và ca muốn áp dụng");
    }//GEN-LAST:event_lbliconGoiyCapNhatCaNvMouseEntered

    private void lbliconGoiyCapNhatCaNvMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbliconGoiyCapNhatCaNvMouseExited
        lblGoiYCapNhatCaNv.setText("");
    }//GEN-LAST:event_lbliconGoiyCapNhatCaNvMouseExited

    private void btnXacNhanMoCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanMoCaActionPerformed
        int row = tblCa.getSelectedRow();
        if (row != -1 && !txtTienKetDauCa.getText().isBlank()) {
            lblCanhBaoTienDauCa.setText("");
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTimeNow = LocalDateTime.now();
            String strDateTimeNow = dateTimeFormat.format(dateTimeNow);
            LocalDateTime timeOpenCa = LocalDateTime.parse(strDateTimeNow, dateTimeFormat);
            if (_admin != null) {
                caRepo.insertHoatDongCaOfChiNhanh(tblCa.getValueAt(row, 1).toString(),
                        ((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId(),
                        timeOpenCa, Float.parseFloat(txtTienKetDauCa.getText()));
            } else {
                caRepo.insertHoatDongCaOfChiNhanh(tblCa.getValueAt(row, 1).toString(),
                        _chiNhanhNguoiDung.getId(), timeOpenCa, Float.parseFloat(txtTienKetDauCa.getText()));
            }
            JOptionPane.showMessageDialog(this, "Mở ca thành công");
            if (_admin != null) {
                caIsRunning = caRepo.getCaRunningOfChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId());
            } else {
                caIsRunning = caRepo.getCaRunningOfChiNhanh(_chiNhanhNguoiDung.getId());
            }
            lblCaDangHoatDong.setText(caIsRunning.getMa() + " Đang hoạt động");
            checkTimeOfCa();
        } else {
            lblCanhBaoTienDauCa.setText("Nhập số tiền");
        }

    }//GEN-LAST:event_btnXacNhanMoCaActionPerformed

    private void btnMoCaHuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoCaHuyBoActionPerformed
        txtTienKetDauCa.setText("");
        dLogMoCa.setVisible(false);
    }//GEN-LAST:event_btnMoCaHuyBoActionPerformed

    private void txtTienKetDauCaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKetDauCaKeyReleased
        if (!banHangService.checkSo(txtTienKetDauCa.getText())) {
            lblCanhBaoTienDauCa.setText("Số tiền không hợp lệ");
            txtTienKetDauCa.setText("");
        } else {
            lblCanhBaoTienDauCa.setText("");
        }
    }//GEN-LAST:event_txtTienKetDauCaKeyReleased

    private void txtTienThucTeTrongKetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienThucTeTrongKetKeyReleased
        if (!banHangService.checkSo(txtTienThucTeTrongKet.getText())) {
            lblCanhBaoTienThucTe.setText("Số tiền không hợp lệ");
            txtTienThucTeTrongKet.setText("");
        } else {
            float tienChenhLech = Float.parseFloat(txtTienThucTeTrongKet.getText())
                    - Float.parseFloat(lblTienKetDauCa.getText());
            lblChenhLech.setText(String.valueOf(tienChenhLech));
            lblTienBanGiao.setText(txtTienThucTeTrongKet.getText());
            lblCanhBaoTienThucTe.setText("");
        }
    }//GEN-LAST:event_txtTienThucTeTrongKetKeyReleased

    private void btnDongCaHuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongCaHuyBoActionPerformed
        lblTienKetDauCa.setText("0");
        txtTienThucTeTrongKet.setText("");
        lblChenhLech.setText("0");
        lblTienBanGiao.setText("0");
        dLongDongCa.setVisible(false);
    }//GEN-LAST:event_btnDongCaHuyBoActionPerformed

    private void btnXacNhanDongCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanDongCaActionPerformed
        HoatDongCa activity = null;
        if (_admin != null) {
            activity = caRepo.getHoatDongCaRunningOfChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId());
        } else {
            activity = caRepo.getHoatDongCaRunningOfChiNhanh(_chiNhanhNguoiDung.getId());
        }

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        String strDateTimeNow = dateTimeFormat.format(dateTimeNow);
        LocalDateTime timeCloseCa = LocalDateTime.parse(strDateTimeNow, dateTimeFormat);
        caRepo.updateHoatDongRunning(activity.getId(), Float.parseFloat(lblTienBanGiao.getText()),
                timeCloseCa);
        lblCaDangHoatDong.setText("Chưa mở ca");
        JOptionPane.showMessageDialog(this, "Đóng ca thành công");
        caIsRunning = null;
    }//GEN-LAST:event_btnXacNhanDongCaActionPerformed

    private void showNhanVienToTable(Set<NhanVienViewModel_Van> setNhanVienView) {
        modelTableNhanVien.setRowCount(0);
        for (NhanVienViewModel_Van nv : setNhanVienView) {
            modelTableNhanVien.addRow(new Object[]{nv.getIdNhanVien(),
                nv.getMaNhanVien(), nv.getHoTen()});
        }
    }

    private void showCaToTable(List<CaViewModel_Quan> setCaView) {
        modelTableCa.setRowCount(0);
        for (CaViewModel_Quan caView : setCaView) {
            modelTableCa.addRow(new Object[]{false, caView.getId(),
                caView.getMa(), caView.getGioBD(), caView.getGioKT()});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatCaNhanVien;
    private javax.swing.JButton btnDongCa;
    private javax.swing.JButton btnDongCaHuyBo;
    private javax.swing.JButton btnKhoiPhucCa;
    private javax.swing.JButton btnMoCa;
    private javax.swing.JButton btnMoCaHuyBo;
    private javax.swing.JButton btnSuaCa;
    private javax.swing.JButton btnThemCa;
    private javax.swing.JButton btnXacNhanDongCa;
    private javax.swing.JButton btnXacNhanMoCa;
    private javax.swing.JButton btnXoaCa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChiNhanh;
    private javax.swing.JDialog dLogMoCa;
    private javax.swing.JDialog dLongDongCa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCaDangHoatDong;
    private javax.swing.JLabel lblCanhBaoGioBatDau;
    private javax.swing.JLabel lblCanhBaoGioKetThuc;
    private javax.swing.JLabel lblCanhBaoMa;
    private javax.swing.JLabel lblCanhBaoTienDauCa;
    private javax.swing.JLabel lblCanhBaoTienThucTe;
    private javax.swing.JLabel lblChenhLech;
    private javax.swing.JLabel lblGoiYCapNhatCaNv;
    private javax.swing.JLabel lblTienBanGiao;
    private javax.swing.JLabel lblTienKetDauCa;
    private javax.swing.JLabel lbliconGoiyCapNhatCaNv;
    private javax.swing.JPanel pnlCRUDCa;
    private javax.swing.JPanel pnlCa;
    private javax.swing.JPanel pnlDongCa;
    private javax.swing.JPanel pnlMoCa;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlQLCa;
    private javax.swing.JRadioButton rdoDaXoa;
    private javax.swing.JRadioButton rdoDangSuDung;
    private javax.swing.JTable tblCa;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtGioBatDau;
    private javax.swing.JTextField txtGioKetThuc;
    private javax.swing.JTextField txtMaCa;
    private javax.swing.JTextField txtPhutBatDau;
    private javax.swing.JTextField txtPhutKetThuc;
    private javax.swing.JTextField txtTienKetDauCa;
    private javax.swing.JTextField txtTienThucTeTrongKet;
    // End of variables declaration//GEN-END:variables
}
