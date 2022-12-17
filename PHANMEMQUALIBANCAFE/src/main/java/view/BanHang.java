/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import domainmodel.Ca;
import domainmodel.NhanVien;
import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.CaRepo;
import service.IBanHangService;
import service.implement.BanHangService;
import viewmodel.Area;
import viewmodel.ChiNhanhViewModel_Hoang;
import viewmodel.KhuyenMaiDangHoatDong;
import viewmodel.ProductForSale;
import viewmodel.Table;
import viewmodel.ThemKhachViewModel;

/**
 *
 * @author trant
 */
public class BanHang extends javax.swing.JPanel implements Runnable {

    Ca caIsRunning = null;
    private CaRepo caRepo;
    private IBanHangService banHangService;
    private List<ItemSanPham> listItemSanPham;
    private DefaultTableModel modelTableChonSp;
    private DefaultComboBoxModel<Area> modelComboArea;
    private DefaultComboBoxModel<ChiNhanhViewModel_Hoang> modelComboChiNhanh;
    private List<ItemBan> listItemBan;
    ThemKhachViewModel _khach = null;
    private boolean _ConfirmCloseThanhToan = false;
    private boolean _ConfirmCloseThemKhach = false;
    TaiKhoanAdmin _admin;
    TaiKhoanNguoiDung _nguoiDung;
    ChiNhanhViewModel_Hoang _chiNhanhNguoiDung;

    public BanHang(TaiKhoanAdmin admin, TaiKhoanNguoiDung nguoiDung) {
        initComponents();
        _admin = admin;
        _nguoiDung = nguoiDung;
        caRepo = new CaRepo();
        listItemSanPham = new ArrayList<>();
        listItemBan = new ArrayList<>();
        hideInfoKhach();
        modelTableChonSp = new DefaultTableModel();
        modelTableChonSp = (DefaultTableModel) tblSpChon.getModel();
        banHangService = new BanHangService();
        Thread loadData = new Thread(this);
        loadData.start();
        try {
            loadData.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
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
                                btnThanhToan.setEnabled(false);
                                btnOpenFormAddGuest.setEnabled(false);
                                lblStateMoCa.setText("Chưa mở ca");

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
        if (_nguoiDung != null) {
            _chiNhanhNguoiDung = banHangService.getChiNhanhbyTaiKhoan(_nguoiDung.getId());
            modelComboArea = (DefaultComboBoxModel) new DefaultComboBoxModel<>(banHangService.
                    getAllKhuVucByChiNhanh(_chiNhanhNguoiDung.getId()).toArray());
            cboChiNhanh.setVisible(false);
            showProductForSale(banHangService.getAllProductForSaleByChiNhanh(_chiNhanhNguoiDung.getId()));
            showKhuyenMai(_chiNhanhNguoiDung.getId());
            caIsRunning = caRepo.getCaRunningOfChiNhanh(_chiNhanhNguoiDung.getId());
            if (caIsRunning != null) {
                btnThanhToan.setEnabled(true);
                btnOpenFormAddGuest.setEnabled(true);
                lblStateMoCa.setText("");
            } else {
                btnThanhToan.setEnabled(false);
                btnOpenFormAddGuest.setEnabled(false);
                lblStateMoCa.setText("Chưa mở ca");
            }
        } else {
            cboChiNhanh.setVisible(true);
            modelComboChiNhanh = (DefaultComboBoxModel) new DefaultComboBoxModel<>(banHangService.getAllChiNhanh().toArray());
            cboChiNhanh.setModel((DefaultComboBoxModel) modelComboChiNhanh);
            modelComboArea = (DefaultComboBoxModel) new DefaultComboBoxModel<>(banHangService.
                    getAllKhuVucByChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId()).toArray());
            showProductForSale(banHangService.getAllProductForSaleByChiNhanh(
                    ((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getElementAt(0)).getId())
            );
            showKhuyenMai(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId());
            caIsRunning = caRepo.getCaRunningOfChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getElementAt(0)).getId());
            if (caIsRunning != null) {
                btnThanhToan.setEnabled(true);
                btnOpenFormAddGuest.setEnabled(true);
                lblStateMoCa.setText("");
            } else {
                btnThanhToan.setEnabled(false);
                btnOpenFormAddGuest.setEnabled(false);
                lblStateMoCa.setText("Chưa mở ca");

            }
        }
        cboKhuVuc.setModel((DefaultComboBoxModel) modelComboArea);
        setEventClickForItemSanPham();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        DlogTraTien = new javax.swing.JDialog();
        pnlTraTien = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblDiemTichLuy = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTienTichLuy = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboDungDiem = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        btnTraTien = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lblSoTienCanTra = new javax.swing.JLabel();
        lblCloseThanhToan = new javax.swing.JLabel();
        lblCanhBaoTien = new javax.swing.JLabel();
        DlogThemKhach = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cboQuocGia = new javax.swing.JComboBox<>();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtTenKhach = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtThanhPho = new javax.swing.JTextField();
        txtMaKhach = new javax.swing.JTextField();
        btnAddGuest = new javax.swing.JButton();
        lblCanhBaoMaKhach = new javax.swing.JLabel();
        lblCanhBaoSdtKhach = new javax.swing.JLabel();
        lblCloseThemKhach = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblCanhBaoTenKhach = new javax.swing.JLabel();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnlLeft = new javax.swing.JPanel();
        pnlTimSanPham = new javax.swing.JPanel();
        txtTimSp = new javax.swing.JTextField();
        btnTimSp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlListSp = new javax.swing.JPanel();
        pnlSPChon = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSpChon = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlBan = new javax.swing.JPanel();
        cboKhuVuc = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboChiNhanh = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pnlHoaDon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblTienCanThanhToan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSoBan = new javax.swing.JLabel();
        pnlTimKhach = new javax.swing.JPanel();
        txtTimKhach = new javax.swing.JTextField();
        btnOpenFormAddGuest = new javax.swing.JButton();
        btnTimKhach = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtKhuyenMai = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtHienThiKhach = new javax.swing.JTextArea();
        lblStateMoCa = new javax.swing.JLabel();

        DlogTraTien.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlogTraTien.setUndecorated(true);
        DlogTraTien.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                DlogTraTienWindowLostFocus(evt);
            }
        });

        pnlTraTien.setBackground(new java.awt.Color(228, 212, 189));
        pnlTraTien.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Điểm tích lũy :");

        lblDiemTichLuy.setBackground(new java.awt.Color(228, 212, 189));
        lblDiemTichLuy.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblDiemTichLuy.setText("0");
        lblDiemTichLuy.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        lblDiemTichLuy.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel8.setText("=");

        lblTienTichLuy.setBackground(new java.awt.Color(228, 212, 189));
        lblTienTichLuy.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblTienTichLuy.setText("0");
        lblTienTichLuy.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        lblTienTichLuy.setOpaque(true);

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel10.setText("VND");

        cboDungDiem.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        cboDungDiem.setText("Xác nhận dùng điểm");
        cboDungDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDungDiemActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Nhập tiền khách đưa :");

        txtTienKhachDua.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel12.setText("Tiền thừa :");

        lblTienThua.setBackground(new java.awt.Color(228, 212, 189));
        lblTienThua.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblTienThua.setText("0");
        lblTienThua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        lblTienThua.setOpaque(true);

        btnTraTien.setBackground(new java.awt.Color(108, 83, 54));
        btnTraTien.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        btnTraTien.setForeground(new java.awt.Color(255, 255, 255));
        btnTraTien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/payGate.png"))); // NOI18N
        btnTraTien.setText("Trả tiền");
        btnTraTien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTraTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraTienActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Số tiền cần trả :");

        lblSoTienCanTra.setBackground(new java.awt.Color(228, 212, 189));
        lblSoTienCanTra.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblSoTienCanTra.setText("0");
        lblSoTienCanTra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        lblSoTienCanTra.setOpaque(true);

        lblCloseThanhToan.setBackground(new java.awt.Color(228, 212, 189));
        lblCloseThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_cancel_30px.png"))); // NOI18N
        lblCloseThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCloseThanhToan.setOpaque(true);
        lblCloseThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseThanhToanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCloseThanhToanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCloseThanhToanMouseExited(evt);
            }
        });

        lblCanhBaoTien.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblCanhBaoTien.setForeground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout pnlTraTienLayout = new javax.swing.GroupLayout(pnlTraTien);
        pnlTraTien.setLayout(pnlTraTienLayout);
        pnlTraTienLayout.setHorizontalGroup(
            pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTraTienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTraTienLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTraTienLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(76, 76, 76)
                                .addComponent(lblSoTienCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(338, 338, 338))
                            .addGroup(pnlTraTienLayout.createSequentialGroup()
                                .addGroup(pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(pnlTraTienLayout.createSequentialGroup()
                                        .addGap(213, 213, 213)
                                        .addComponent(lblDiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(26, 26, 26)
                                .addComponent(lblTienTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(78, 78, 78)
                                .addComponent(cboDungDiem))))
                    .addGroup(pnlTraTienLayout.createSequentialGroup()
                        .addGroup(pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTraTienLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlTraTienLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                    .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCanhBaoTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(138, 138, 138)))
                        .addComponent(btnTraTien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTraTienLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCloseThanhToan))
        );
        pnlTraTienLayout.setVerticalGroup(
            pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTraTienLayout.createSequentialGroup()
                .addComponent(lblCloseThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblDiemTichLuy)
                    .addComponent(lblTienTichLuy)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(cboDungDiem))
                .addGap(38, 38, 38)
                .addGroup(pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblSoTienCanTra, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTraTien, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTraTienLayout.createSequentialGroup()
                        .addGroup(pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCanhBaoTien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(pnlTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lblTienThua))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout DlogTraTienLayout = new javax.swing.GroupLayout(DlogTraTien.getContentPane());
        DlogTraTien.getContentPane().setLayout(DlogTraTienLayout);
        DlogTraTienLayout.setHorizontalGroup(
            DlogTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 876, Short.MAX_VALUE)
            .addGroup(DlogTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlTraTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DlogTraTienLayout.setVerticalGroup(
            DlogTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
            .addGroup(DlogTraTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlTraTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DlogThemKhach.setUndecorated(true);
        DlogThemKhach.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                DlogThemKhachWindowLostFocus(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(228, 212, 189));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 51, 0), new java.awt.Color(0, 153, 153), new java.awt.Color(102, 0, 102), new java.awt.Color(255, 0, 0)));

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel14.setText("Mã khách hàng :");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel17.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel17.setText("Tên khách hàng :");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel18.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel18.setText("Quốc gia :");
        jLabel18.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel19.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel19.setText("Thành phố :");
        jLabel19.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel20.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel20.setText("Số điện thoại :");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel21.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel21.setText("Giới tính :");
        jLabel21.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cboQuocGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua", "Barbuda", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia", "Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Côte d’Ivoire", "Croatia", "Cuba", "Cyprus", "Czechia", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "DR Congo", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Holy See", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts & Nevis", "Saint Lucia", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "St. Vincent & Grenadines", "State of Palestine", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" }));
        cboQuocGia.setSelectedIndex(193);

        buttonGroup2.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup2.add(rdoNu);
        rdoNu.setText("Nữ");

        txtTenKhach.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtSdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtThanhPho.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtMaKhach.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        btnAddGuest.setBackground(new java.awt.Color(108, 83, 54));
        btnAddGuest.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnAddGuest.setForeground(new java.awt.Color(255, 255, 255));
        btnAddGuest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Plus_48px.png"))); // NOI18N
        btnAddGuest.setText("Thêm khách");
        btnAddGuest.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddGuest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddGuestActionPerformed(evt);
            }
        });

        lblCanhBaoMaKhach.setBackground(new java.awt.Color(228, 212, 189));
        lblCanhBaoMaKhach.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblCanhBaoMaKhach.setForeground(new java.awt.Color(255, 0, 0));
        lblCanhBaoMaKhach.setOpaque(true);

        lblCanhBaoSdtKhach.setBackground(new java.awt.Color(228, 212, 189));
        lblCanhBaoSdtKhach.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblCanhBaoSdtKhach.setForeground(new java.awt.Color(255, 51, 51));
        lblCanhBaoSdtKhach.setOpaque(true);

        lblCloseThemKhach.setBackground(new java.awt.Color(228, 212, 189));
        lblCloseThemKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_cancel_30px.png"))); // NOI18N
        lblCloseThemKhach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCloseThemKhach.setOpaque(true);
        lblCloseThemKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseThemKhachMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCloseThemKhachMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCloseThemKhachMouseExited(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dauSao.png"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dauSao.png"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dauSao.png"))); // NOI18N

        lblCanhBaoTenKhach.setBackground(new java.awt.Color(228, 212, 189));
        lblCanhBaoTenKhach.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblCanhBaoTenKhach.setForeground(new java.awt.Color(255, 51, 51));
        lblCanhBaoTenKhach.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(339, 339, 339)
                .addComponent(btnAddGuest)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(rdoNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNu))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCanhBaoMaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblCanhBaoSdtKhach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel19)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCanhBaoTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCloseThemKhach))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblCloseThemKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCanhBaoMaKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCanhBaoSdtKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblCanhBaoTenKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(28, 28, 28)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jLabel22)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(btnAddGuest, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout DlogThemKhachLayout = new javax.swing.GroupLayout(DlogThemKhach.getContentPane());
        DlogThemKhach.getContentPane().setLayout(DlogThemKhachLayout);
        DlogThemKhachLayout.setHorizontalGroup(
            DlogThemKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DlogThemKhachLayout.setVerticalGroup(
            DlogThemKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        pnlLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnlLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLeftMouseClicked(evt);
            }
        });

        pnlTimSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnlTimSanPham.setBorder(null);

        txtTimSp.setForeground(new java.awt.Color(204, 204, 204));
        txtTimSp.setText("Nhập tên sản phẩm cần tìm");
        txtTimSp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimSp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimSpMouseClicked(evt);
            }
        });
        txtTimSp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimSpKeyReleased(evt);
            }
        });

        btnTimSp.setBackground(new java.awt.Color(255, 255, 255));
        btnTimSp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_20px.png"))); // NOI18N
        btnTimSp.setBorder(null);
        btnTimSp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlTimSanPhamLayout = new javax.swing.GroupLayout(pnlTimSanPham);
        pnlTimSanPham.setLayout(pnlTimSanPhamLayout);
        pnlTimSanPhamLayout.setHorizontalGroup(
            pnlTimSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTimSp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimSp, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
        );
        pnlTimSanPhamLayout.setVerticalGroup(
            pnlTimSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimSanPhamLayout.createSequentialGroup()
                .addGroup(pnlTimSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimSp)
                    .addComponent(btnTimSp, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane1.setBorder(null);

        pnlListSp.setBackground(new java.awt.Color(255, 255, 255));
        pnlListSp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(0, 153, 153)));
        pnlListSp.setLayout(new java.awt.GridLayout(0, 7, 10, 10));
        jScrollPane1.setViewportView(pnlListSp);

        pnlSPChon.setBackground(new java.awt.Color(255, 255, 255));
        pnlSPChon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(102, 153, 0)));

        jScrollPane7.setBorder(null);

        tblSpChon.setBorder(null);
        tblSpChon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã", "Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền", "Gỡ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSpChon.setGridColor(new java.awt.Color(255, 255, 255));
        tblSpChon.getTableHeader().setReorderingAllowed(false);
        tblSpChon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSpChonMouseClicked(evt);
            }
        });
        tblSpChon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblSpChonKeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(tblSpChon);
        if (tblSpChon.getColumnModel().getColumnCount() > 0) {
            tblSpChon.getColumnModel().getColumn(0).setMinWidth(0);
            tblSpChon.getColumnModel().getColumn(0).setMaxWidth(0);
            tblSpChon.getColumnModel().getColumn(1).setMinWidth(60);
            tblSpChon.getColumnModel().getColumn(1).setMaxWidth(60);
            tblSpChon.getColumnModel().getColumn(2).setResizable(false);
            tblSpChon.getColumnModel().getColumn(3).setResizable(false);
            tblSpChon.getColumnModel().getColumn(4).setResizable(false);
            tblSpChon.getColumnModel().getColumn(5).setResizable(false);
            tblSpChon.getColumnModel().getColumn(6).setMinWidth(30);
            tblSpChon.getColumnModel().getColumn(6).setMaxWidth(30);
        }

        javax.swing.GroupLayout pnlSPChonLayout = new javax.swing.GroupLayout(pnlSPChon);
        pnlSPChon.setLayout(pnlSPChonLayout);
        pnlSPChonLayout.setHorizontalGroup(
            pnlSPChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPChonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSPChonLayout.setVerticalGroup(
            pnlSPChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
        );

        jScrollPane2.setBorder(null);

        pnlBan.setBackground(new java.awt.Color(255, 255, 255));
        pnlBan.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 153)));
        pnlBan.setLayout(new java.awt.GridLayout(0, 8, 10, 10));
        jScrollPane2.setViewportView(pnlBan);

        cboKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhuVucActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/gray.png"))); // NOI18N
        jLabel2.setText("Bàn Trống");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new_moon_10px.png"))); // NOI18N
        jLabel3.setText("Đã hết");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/green.png"))); // NOI18N
        jLabel4.setText("Đang chọn");

        cboChiNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChiNhanhActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/location_10px.png"))); // NOI18N
        jLabel7.setText("Chi nhánh");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/area10px.png"))); // NOI18N
        jLabel9.setText("Khu vực");

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlLeftLayout.createSequentialGroup()
                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlSPChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlTimSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLeftLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboChiNhanh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(cboKhuVuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)))))
                .addContainerGap())
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLeftLayout.createSequentialGroup()
                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnlTimSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSPChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlHoaDon.setBackground(new java.awt.Color(228, 212, 189));
        pnlHoaDon.setPreferredSize(new java.awt.Dimension(400, 754));
        pnlHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHoaDonMouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tạm tính"));

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel16.setText("Tổng tiền :");

        lblTongTien.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTongTien.setText("0");

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel13.setText("Số tiền cần thanh toán :");

        lblTienCanThanhToan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTienCanThanhToan.setText("0");

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setText("Bàn số :");

        lblSoBan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblSoBan.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienCanThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblSoBan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblSoBan))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblTongTien))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblTienCanThanhToan))
                .addGap(99, 99, 99))
        );

        pnlTimKhach.setBackground(new java.awt.Color(255, 255, 255));
        pnlTimKhach.setBorder(null);

        txtTimKhach.setForeground(new java.awt.Color(204, 204, 204));
        txtTimKhach.setText("Nhập số điện thoại cần tìm");
        txtTimKhach.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKhach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKhachMouseClicked(evt);
            }
        });
        txtTimKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKhachKeyReleased(evt);
            }
        });

        btnOpenFormAddGuest.setBackground(new java.awt.Color(255, 255, 255));
        btnOpenFormAddGuest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_20px.png"))); // NOI18N
        btnOpenFormAddGuest.setBorder(null);
        btnOpenFormAddGuest.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOpenFormAddGuest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFormAddGuestActionPerformed(evt);
            }
        });

        btnTimKhach.setBackground(new java.awt.Color(255, 255, 255));
        btnTimKhach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_20px.png"))); // NOI18N
        btnTimKhach.setBorder(null);
        btnTimKhach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTimKhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKhachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKhachLayout = new javax.swing.GroupLayout(pnlTimKhach);
        pnlTimKhach.setLayout(pnlTimKhachLayout);
        pnlTimKhachLayout.setHorizontalGroup(
            pnlTimKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKhachLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnTimKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKhach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOpenFormAddGuest, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        pnlTimKhachLayout.setVerticalGroup(
            pnlTimKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKhachLayout.createSequentialGroup()
                .addGroup(pnlTimKhachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenFormAddGuest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThanhToan.setBackground(new java.awt.Color(108, 83, 54));
        btnThanhToan.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/atm_60px.png"))); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setBorder(null);
        btnThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtKhuyenMai.setEditable(false);
        txtKhuyenMai.setColumns(20);
        txtKhuyenMai.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        txtKhuyenMai.setForeground(new java.awt.Color(255, 102, 102));
        txtKhuyenMai.setRows(5);
        txtKhuyenMai.setBorder(javax.swing.BorderFactory.createTitledBorder("Các khuyến mãi hiện có"));
        jScrollPane3.setViewportView(txtKhuyenMai);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sale_60px.png"))); // NOI18N

        jScrollPane4.setBorder(null);

        txtHienThiKhach.setEditable(false);
        txtHienThiKhach.setBackground(new java.awt.Color(228, 212, 189));
        txtHienThiKhach.setColumns(20);
        txtHienThiKhach.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        txtHienThiKhach.setRows(3);
        txtHienThiKhach.setBorder(null);
        jScrollPane4.setViewportView(txtHienThiKhach);

        lblStateMoCa.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHoaDonLayout.createSequentialGroup()
                        .addGroup(pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3)
                            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(pnlTimKhach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHoaDonLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblStateMoCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))))
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addComponent(pnlTimKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStateMoCa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(pnlHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setEventClickForItemSanPham() {
        for (ItemSanPham item : listItemSanPham) {
            item.getLblAvatarSp().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (banHangService.checkDinhLuongPhaChex3(item.getId())) {
                        addProductForSaleToTable(item);
                        upTotalItemSanPham(item);
                        totalMoney();
                        totalMoneyAfterSale();
                    } else {
                        JOptionPane.showMessageDialog(pnlLeft, "Số lượng nguyên liệu có thể không đủ");
                    }

                }

            });
        }
    }

    private void upTotalItemSanPham(ItemSanPham item) {
        int soLuong = 0;
        double thanhTien = 0;
        if (tblSpChon.getRowCount() > 0) {
            for (int i = 0; i < tblSpChon.getRowCount(); i++) {
                if (item.getId().equalsIgnoreCase(tblSpChon.getValueAt(i, 0).toString())) {
                    soLuong = Integer.parseInt(tblSpChon.getValueAt(i, 4).toString());
                    soLuong++;
                    tblSpChon.setValueAt(soLuong, i, 4);
                    thanhTien = Double.parseDouble(tblSpChon.getValueAt(i, 4).toString())
                            * Double.valueOf(tblSpChon.getValueAt(i, 3).toString());
                    tblSpChon.setValueAt(new BigDecimal(thanhTien), i, 5);
                    break;
                }
            }
        }
    }

    private void totalMoney() {
        double tongTien = 0;
        for (int i = 0; i < tblSpChon.getRowCount(); i++) {
            tongTien += Double.valueOf(tblSpChon.getValueAt(i, 5).toString());
        }
        lblTongTien.setText(String.valueOf(new BigDecimal(tongTien)));

    }

    private void totalMoneyAfterSale() {
        double soTienChietKhau = 0;
        double soTienPhaiTra = 0;
        double tongTienPhaiTra = 0;
        for (int i = 0; i < tblSpChon.getRowCount(); i++) {
            KhuyenMaiDangHoatDong kmView = banHangService.getKhuyenMaibySanPham(tblSpChon.getValueAt(i, 0).toString());
            if (kmView != null && kmView.getGiaTriChietKhau() != null) {
                soTienChietKhau = (Double.parseDouble(tblSpChon.getValueAt(i, 3).toString())
                        / 100) * kmView.getGiaTriChietKhau();
                soTienPhaiTra = Double.parseDouble(tblSpChon.getValueAt(i, 3).toString()) - soTienChietKhau;
                tongTienPhaiTra += soTienPhaiTra * Integer.parseInt(tblSpChon.getValueAt(i, 4).toString());
            } else {
                tongTienPhaiTra += Double.parseDouble(tblSpChon.getValueAt(i, 3).toString())
                        * Integer.parseInt(tblSpChon.getValueAt(i, 4).toString());
            }
        }
        lblTienCanThanhToan.setText(String.valueOf(new BigDecimal(tongTienPhaiTra)));
    }

    private void addProductForSaleToTable(ItemSanPham item) {
        int soLuong = 0;
        boolean check = false;
        BigDecimal thanhTien = new BigDecimal(item.getLblGiaSp().getText());
        for (int i = 0; i < tblSpChon.getRowCount(); i++) {
            if (item.getId().equalsIgnoreCase(tblSpChon.getValueAt(i, 0).toString())) {
                check = true;
                break;
            }
        }
        if (check != true) {
            modelTableChonSp.addRow(new Object[]{item.getId(), item.getMa(), item.getLblTenSp().getText(),
                item.getLblGiaSp().getText(), 0, thanhTien});
        }
    }

    private void showProductForSale(List<ProductForSale> listProductForSale) {
        pnlListSp.removeAll();
        pnlListSp.revalidate();
        pnlListSp.repaint();
        listItemSanPham.clear();
        if (listProductForSale != null) {
            for (ProductForSale product : listProductForSale) {
                ItemSanPham item = new ItemSanPham();
                item.setId(product.getIdSp());
                item.setMa(product.getMaSp());
                if (product.getTrangThai() != null) {
                    item.setTrangThai(product.getTrangThai());
                }
                if (product.getTenSp() != null) {
                    item.getLblTenSp().setText(product.getTenSp());
                }
                if (product.getTenKhuyenMai() != null) {
                    item.getLblTenKhuyenMai().setText(product.getTenKhuyenMai());
                    item.getLblSaleSp().setEnabled(true);
                } else {
                    item.getLblSaleSp().setEnabled(false);
                }
                if (product.getGiaBan() != null) {
                    item.getLblGiaSp().setText(String.valueOf(product.getGiaBan()));
                }
                if (product.getAvatar() != null) {
                    Image image = new ImageIcon(product.getAvatar()).getImage().
                            getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                    item.getLblAvatarSp().setIcon(new ImageIcon(image));
                }
                pnlListSp.add(item);
                pnlListSp.revalidate();
                pnlListSp.repaint();
                listItemSanPham.add(item);

            }
        }

    }

    private void showKhuyenMai(String idChiNhanh) {
        List<KhuyenMaiDangHoatDong> listKmActive = banHangService.getAllKhuyenMaiByChiNhanh(idChiNhanh);
        String allKhuyenMai = "";
        for (KhuyenMaiDangHoatDong km : listKmActive) {
            allKhuyenMai += "- " + km.getTenKhuyenMai() + " : " + km.getMoTa() + "\n";
        }
        txtKhuyenMai.setText(allKhuyenMai);
    }
    private void txtTimSpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimSpMouseClicked
        txtTimSp.setForeground(Color.BLACK);
        txtTimSp.setText("");
    }//GEN-LAST:event_txtTimSpMouseClicked

    private void txtTimKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKhachMouseClicked
        txtTimKhach.setForeground(Color.BLACK);
        txtTimKhach.setText("");
        hideInfoKhach();

    }//GEN-LAST:event_txtTimKhachMouseClicked

    private void setHoverOfSearch() {
        Color color = new Color(225, 225, 225);
        txtTimSp.setText("Nhập tên sản phẩm cần tìm");
        txtTimKhach.setText("Nhập số điện thoại cần tìm");
        txtTimSp.setForeground(color);
        txtTimKhach.setForeground(color);
    }
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        setHoverOfSearch();
    }//GEN-LAST:event_formMouseClicked


    private void tblSpChonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSpChonKeyReleased
        int row = tblSpChon.getSelectedRow();
        double thanhTien = 0;
        if (!banHangService.checkSo(tblSpChon.getValueAt(row, 4).toString())) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
            tblSpChon.setValueAt(1, row, 4);
        } else {
            if (Integer.parseInt(tblSpChon.getValueAt(row, 4).toString()) == 0) {
                modelTableChonSp.removeRow(row);
            } else {
                thanhTien = Double.parseDouble(tblSpChon.getValueAt(row, 4).toString()) * Double.valueOf(tblSpChon.getValueAt(row, 3).toString());
                tblSpChon.setValueAt(new BigDecimal(thanhTien), row, 5);
                totalMoney();
                totalMoneyAfterSale();
            }
        }
    }//GEN-LAST:event_tblSpChonKeyReleased

    private void pnlLeftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLeftMouseClicked
        setHoverOfSearch();
    }//GEN-LAST:event_pnlLeftMouseClicked

    private void pnlHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHoaDonMouseClicked
        setHoverOfSearch();
    }//GEN-LAST:event_pnlHoaDonMouseClicked

    private void resetPanelBanAndShow() {
        pnlBan.removeAll();
        pnlBan.revalidate();
        pnlBan.repaint();
        if (cboKhuVuc.getItemCount() > 0) {
            Area area = (Area) modelComboArea.getSelectedItem();
            showTableOfArea(area);
        }

    }

    private void addEventMouseClickToItemBan(ItemBan itemBan) {
        itemBan.getLblAvatar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                itemBan.changeStateConfirm();
                if (itemBan.isConfirm() == true && itemBan.getTrangThai() == 0) {
                    lblSoBan.setText(itemBan.getLblSoBan().getText());
                } else if (itemBan.getTrangThai() == 1) {
                    int select = JOptionPane.showConfirmDialog(pnlBan, "Bạn muốn reset trạng thái bàn", "Reset trạng thái", JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.YES_OPTION) {
                        banHangService.updateTrangThaiBanBySoBan(Integer.parseInt(itemBan.getLblSoBan().getText()));
                        resetPanelBanAndShow();
                    }
                } else {
                    lblSoBan.setText("-");
                }

            }
        });
        itemBan.getLblSoBan().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                itemBan.changeStateConfirm();
                if (itemBan.isConfirm() == true && itemBan.getTrangThai() == 0) {
                    lblSoBan.setText(itemBan.getLblSoBan().getText());
                } else if (itemBan.getTrangThai() == 1) {
                    int select = JOptionPane.showConfirmDialog(pnlBan, "Bạn muốn reset trạng thái bàn", "Reset trạng thái", JOptionPane.YES_NO_OPTION);
                    if (select == JOptionPane.YES_OPTION) {
                        banHangService.updateTrangThaiBanBySoBan(Integer.parseInt(itemBan.getLblSoBan().getText()));
                        resetPanelBanAndShow();
                    }
                } else {
                    lblSoBan.setText("-");
                }
            }
        });
    }

    private void showTableOfArea(Area area) {
        List<Table> listTable = banHangService.getAllBanByKhuVuc(area);
        if (!listTable.isEmpty()) {
            for (Table table : listTable) {
                ItemBan itemBan = new ItemBan();
                itemBan.getLblSoBan().setText(table.getSoBan().toString());
                itemBan.setTrangThai(table.getTrangThaiBan());
                if (itemBan.getTrangThai() != 0) {
                    itemBan.getLblSoBan().setBackground(Color.red);
                    itemBan.getLblAvatar().setBackground(Color.red);
                }
                listItemBan.add(itemBan);
                pnlBan.add(itemBan);
                pnlBan.revalidate();
                pnlBan.repaint();
                addEventMouseClickToItemBan(itemBan);

            }
        } else {
            JOptionPane.showMessageDialog(pnlBan, "Khu vực này không có bàn");
        }
    }


    private void cboKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhuVucActionPerformed
        if (cboKhuVuc.getItemCount() > 0) {
            resetPanelBanAndShow();
        }
    }//GEN-LAST:event_cboKhuVucActionPerformed

    private void tblSpChonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSpChonMouseClicked
        int row = tblSpChon.getSelectedRow();
        int column = tblSpChon.getSelectedColumn();
        if (column == 6) {
            boolean remove = (boolean) tblSpChon.getValueAt(row, column);
            if (remove == true) {
                modelTableChonSp.removeRow(row);
                totalMoney();
                totalMoneyAfterSale();
            }
        }


    }//GEN-LAST:event_tblSpChonMouseClicked

    private void hideInfoKhach() {
        txtHienThiKhach.setText("");
        _khach = null;
    }


    private void btnTimKhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKhachActionPerformed
        _khach = banHangService.getKhachHangBySdt(txtTimKhach.getText());
        if (_khach != null) {
            txtHienThiKhach.setText("Mã khách hàng : " + _khach.getMa() + "\n" + "Tên khách hàng : " + _khach.getHoTen() + "\n"
                    + "Số điện thoại : " + _khach.getSdt());

        } else {
            JOptionPane.showMessageDialog(this, "Thông tin không tồn tại. Vui lòng kiểm tra lại !");
            hideInfoKhach();
        }
    }//GEN-LAST:event_btnTimKhachActionPerformed

    private void txtTimKhachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKhachKeyReleased
        if (txtTimKhach.getText().isBlank()) {
            hideInfoKhach();
        }
    }//GEN-LAST:event_txtTimKhachKeyReleased

    private void setTraTien() {
        double tienTichLuy = 0;
        int diemTichLuy = 0;
        if (_khach != null) {
            diemTichLuy = _khach.getDiemTichLuy();
            tienTichLuy = _khach.getDiemTichLuy() * banHangService.getGiaTriDiem();
            lblDiemTichLuy.setText(String.valueOf(diemTichLuy));
            lblTienTichLuy.setText(String.valueOf(new BigDecimal(tienTichLuy)));

        }
        lblSoTienCanTra.setText(lblTienCanThanhToan.getText());
        if (cboDungDiem.isSelected()) {
            txtTienKhachDua.setText("");
            lblTienThua.setText("0");
            double tienCanThanhToan = Double.parseDouble(lblSoTienCanTra.getText());
            float giaTriDiem = banHangService.getGiaTriDiem();
            if (tienTichLuy > tienCanThanhToan) {
                double tienConLai = tienTichLuy - tienCanThanhToan;
                int diemConLai = (int) (tienConLai / giaTriDiem);
                lblSoTienCanTra.setText("0");
                lblDiemTichLuy.setText(String.valueOf(diemConLai));
                lblTienTichLuy.setText(String.valueOf(new BigDecimal(diemConLai * giaTriDiem)));
            } else {
                tienCanThanhToan = tienCanThanhToan - tienTichLuy;
                lblSoTienCanTra.setText(String.valueOf(new BigDecimal(tienCanThanhToan)));
                lblDiemTichLuy.setText("0");
                lblTienTichLuy.setText("0");
            }
        } else {
            lblSoTienCanTra.setText(lblTienCanThanhToan.getText());
            lblDiemTichLuy.setText(String.valueOf(diemTichLuy));
            lblTienTichLuy.setText(String.valueOf(new BigDecimal(tienTichLuy)));
        }

    }
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (tblSpChon.getRowCount() > 0 && !lblSoBan.getText().equalsIgnoreCase("-")) {
            DlogTraTien.setVisible(true);
            DlogTraTien.setSize(900, 340);
            DlogTraTien.setLocationRelativeTo(null);
            setTraTien();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng sản phẩm và Số bàn");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void clearFormTraTien() {
        lblDiemTichLuy.setText("0");
        lblTienTichLuy.setText("0");
        lblSoTienCanTra.setText("0");
        lblTienThua.setText("0");
    }
    private void lblCloseThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseThanhToanMouseClicked
        _ConfirmCloseThanhToan = true;
        if (_ConfirmCloseThanhToan == true) {
            DlogTraTien.setVisible(false);
        }
    }//GEN-LAST:event_lblCloseThanhToanMouseClicked

    private void DlogTraTienWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DlogTraTienWindowLostFocus
        if (_ConfirmCloseThanhToan == true) {
            DlogTraTien.setVisible(false);
            _ConfirmCloseThanhToan = false;
        } else {
            JOptionPane.showMessageDialog(DlogTraTien, "Vui lòng đóng giao diện thanh toán");
            DlogTraTien.setVisible(true);
            setTraTien();
        }
    }//GEN-LAST:event_DlogTraTienWindowLostFocus

    private void lblCloseThanhToanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseThanhToanMouseEntered
        lblCloseThanhToan.setBackground(Color.red);
    }//GEN-LAST:event_lblCloseThanhToanMouseEntered

    private void lblCloseThanhToanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseThanhToanMouseExited

        Color color = new Color(228, 212, 189);
        lblCloseThanhToan.setBackground(color);
    }//GEN-LAST:event_lblCloseThanhToanMouseExited

    private void cboDungDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDungDiemActionPerformed
        setTraTien();
    }//GEN-LAST:event_cboDungDiemActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased

        if (banHangService.checkSo(txtTienKhachDua.getText())) {
            double tienThua = Double.parseDouble(txtTienKhachDua.getText()) - Double.parseDouble(lblSoTienCanTra.getText());
            lblTienThua.setText(String.valueOf(new BigDecimal(tienThua)));
        } else {
            lblCanhBaoTien.setText("Số tiền không hợp lệ");
            txtTienKhachDua.setText("");
        }
        if (txtTienKhachDua.getText().isBlank()) {
            lblTienThua.setText("0");
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void btnTraTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraTienActionPerformed
        if (txtTienKhachDua.getText().isBlank()) {
            lblCanhBaoTien.setText("Vui lòng nhập tiền khách đưa");
        } else if (Double.parseDouble(txtTienKhachDua.getText()) < Double.parseDouble(lblSoTienCanTra.getText())) {
            lblCanhBaoTien.setText("Số tiền chưa đủ thanh toán");
        } else {
            lblCanhBaoTien.setText("");
            LocalDateTime timeNow = LocalDateTime.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String strTimeNow = dateFormat.format(timeNow);
            LocalDateTime ngayTaoHD = LocalDateTime.parse(strTimeNow, dateFormat);
            String idHoaDon = null;
            if (_nguoiDung != null) {
                NhanVien nhanVien = banHangService.getNhanVienbyTaiKhoan(_nguoiDung.getId());
                idHoaDon = banHangService.inserHoaDon(banHangService.autoGenMaHoaDon(), ngayTaoHD,
                        nhanVien.getId(), Integer.parseInt(lblSoBan.getText()));
            } else {
                idHoaDon = banHangService.inserHoaDon(banHangService.autoGenMaHoaDon(), ngayTaoHD,
                        null, Integer.parseInt(lblSoBan.getText()));
            }

            for (int i = 0; i < tblSpChon.getRowCount(); i++) {
                banHangService.insertChiTietHoaDon(tblSpChon.getValueAt(i, 0).toString(),
                        idHoaDon, Integer.parseInt(tblSpChon.getValueAt(i, 4).toString()),
                        new BigDecimal(lblTongTien.getText()), new BigDecimal(lblSoTienCanTra.getText()));
                banHangService.updateNguyenLieuAfterSellSanPham(tblSpChon.
                        getValueAt(i, 0).toString(), Integer.parseInt(tblSpChon.getValueAt(i, 4).toString()));
            }
            if (_khach != null) {
                Integer diemTichLuyHienTai = Integer.parseInt(lblDiemTichLuy.getText());
                if (Float.parseFloat(lblTienCanThanhToan.getText()) > banHangService.getGiaTriDoiDiem()) {
                    double diemMuaHang = Float.parseFloat(lblTienCanThanhToan.getText()) / banHangService.getGiaTriDoiDiem();
                    Integer diemUpdate = diemTichLuyHienTai + (int) Math.floor(diemMuaHang);
                    banHangService.updateDiemKhachHang(_khach.getId(), diemUpdate);
                } else {
                    banHangService.updateDiemKhachHang(_khach.getId(), Integer.parseInt(lblDiemTichLuy.getText()));
                }
            }
            banHangService.updateTrangThaiBanBySoBan(Integer.parseInt(lblSoBan.getText()));
            _ConfirmCloseThanhToan = true;
            if (_ConfirmCloseThanhToan == true) {
                DlogTraTien.setVisible(false);
            }
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
            resetPanelBanAndShow();
            clearFormTraTien();

        }

    }//GEN-LAST:event_btnTraTienActionPerformed

    private void cboChiNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChiNhanhActionPerformed
        modelComboArea = (DefaultComboBoxModel) new DefaultComboBoxModel<>(banHangService.
                getAllKhuVucByChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId()).toArray());
        cboKhuVuc.setModel((DefaultComboBoxModel) modelComboArea);
        modelTableChonSp.setRowCount(0);
        resetPanelBanAndShow();
        showProductForSale(banHangService.getAllProductForSaleByChiNhanh(
                ((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId())
        );
        setEventClickForItemSanPham();
        showKhuyenMai(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId());
        Ca caRunning = caRepo.getCaRunningOfChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId());
        if (caRunning != null) {
            btnThanhToan.setEnabled(true);
            btnOpenFormAddGuest.setEnabled(true);
            lblStateMoCa.setText("");
        } else {
            btnThanhToan.setEnabled(false);
            btnOpenFormAddGuest.setEnabled(false);
            lblStateMoCa.setText("Chưa mở ca");
        }
        caIsRunning = caRepo.getCaRunningOfChiNhanh(((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId());
        checkTimeOfCa();
    }//GEN-LAST:event_cboChiNhanhActionPerformed

    private void btnOpenFormAddGuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFormAddGuestActionPerformed
        DlogThemKhach.setVisible(true);
        DlogThemKhach.setSize(900, 360);
        DlogThemKhach.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnOpenFormAddGuestActionPerformed

    private boolean checkEmptyFormAddGuest() {
        boolean ok = true;
        if (txtMaKhach.getText().isBlank()) {
            lblCanhBaoMaKhach.setText("Thông tin bắt buộc");
            ok = false;
        } else {
            lblCanhBaoMaKhach.setText("");
        }
        if (txtSdt.getText().isBlank()) {
            lblCanhBaoSdtKhach.setText("Thông tin bắt buộc");
            ok = false;
        } else {
            lblCanhBaoSdtKhach.setText("");
        }
        if (txtTenKhach.getText().isBlank()) {
            lblCanhBaoTenKhach.setText("Thông tin bắt buộc");
            ok = false;
        } else {
            lblCanhBaoTenKhach.setText("");
        }
        return ok;
    }

    private boolean checkFormatSdt(String sdt) {
        if (!banHangService.checkFormatSdt(sdt)) {
            lblCanhBaoSdtKhach.setText("Sdt không hợp lệ(0xxxxxxxxx)");
            return false;
        } else {
            lblCanhBaoSdtKhach.setText("");
            return true;
        }

    }

    private boolean checkMaKhach(String maKhach) {
        if (banHangService.checkMaKhach(maKhach)) {
            lblCanhBaoMaKhach.setText("Mã khách đã tồn tại");
            return false;
        } else {
            lblCanhBaoMaKhach.setText("");
            return true;
        }
    }

    private boolean checkExistedSdt(String sdt) {
        if (banHangService.getKhachHangBySdt(sdt) != null) {
            lblCanhBaoSdtKhach.setText("Sdt này đã được sử dụng");
            return false;
        } else {
            lblCanhBaoSdtKhach.setText("");
            return true;
        }
    }
    private void btnAddGuestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddGuestActionPerformed
        if (checkEmptyFormAddGuest() && checkMaKhach(txtMaKhach.getText())
                && checkFormatSdt(txtSdt.getText()) && checkExistedSdt(txtSdt.getText())) {
            ThemKhachViewModel khachView = new ThemKhachViewModel();
            khachView.setMa(txtMaKhach.getText());
            khachView.setSdt(txtSdt.getText());
            khachView.setHoTen(txtTenKhach.getText());
            khachView.setGioiTinh(rdoNam.isSelected() ? "Nam" : "Nữ");
            khachView.setQuocGia(cboQuocGia.getSelectedItem().toString());
            if (!txtThanhPho.getText().isBlank()) {
                khachView.setThanhPho(txtThanhPho.getText());
            }
            banHangService.insertKhachHang(khachView);
            _ConfirmCloseThemKhach = true;
            if (_ConfirmCloseThemKhach == true) {
                DlogThemKhach.setVisible(false);
            }
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
        }
    }//GEN-LAST:event_btnAddGuestActionPerformed

    private void lblCloseThemKhachMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseThemKhachMouseEntered
        lblCloseThemKhach.setBackground(Color.red);
    }//GEN-LAST:event_lblCloseThemKhachMouseEntered

    private void lblCloseThemKhachMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseThemKhachMouseExited
        Color color = new Color(228, 212, 189);
        lblCloseThemKhach.setBackground(color);
    }//GEN-LAST:event_lblCloseThemKhachMouseExited

    private void DlogThemKhachWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_DlogThemKhachWindowLostFocus
        if (_ConfirmCloseThemKhach == true) {
            DlogThemKhach.setVisible(false);
            _ConfirmCloseThemKhach = false;
        } else {
            JOptionPane.showMessageDialog(DlogThemKhach, "Vui lòng đóng giao diện hiện tại");
            DlogThemKhach.setVisible(true);

        }
    }//GEN-LAST:event_DlogThemKhachWindowLostFocus

    private void lblCloseThemKhachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseThemKhachMouseClicked
        _ConfirmCloseThemKhach = true;
        if (_ConfirmCloseThemKhach == true) {
            DlogThemKhach.setVisible(false);
        }
    }//GEN-LAST:event_lblCloseThemKhachMouseClicked

    private void txtTimSpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSpKeyReleased
        if (_nguoiDung != null) {
            if (!txtTimSp.getText().isBlank()) {
                showProductForSale(banHangService.searchProductForSaleByTenSpAndChiNhanh(
                        txtTimSp.getText(), _chiNhanhNguoiDung.getId())
                );
                setEventClickForItemSanPham();
            } else {
                showProductForSale(banHangService.getAllProductForSaleByChiNhanh(_chiNhanhNguoiDung.getId()));
                setEventClickForItemSanPham();
            }

        } else {
            if (!txtTimSp.getText().isBlank()) {
                showProductForSale(banHangService.searchProductForSaleByTenSpAndChiNhanh(
                        txtTimSp.getText(), ((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId())
                );
                setEventClickForItemSanPham();
            } else {
                showProductForSale(banHangService.getAllProductForSaleByChiNhanh(
                        ((ChiNhanhViewModel_Hoang) modelComboChiNhanh.getSelectedItem()).getId())
                );
                setEventClickForItemSanPham();
            }

        }
    }//GEN-LAST:event_txtTimSpKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DlogThemKhach;
    private javax.swing.JDialog DlogTraTien;
    private javax.swing.JButton btnAddGuest;
    private javax.swing.JButton btnOpenFormAddGuest;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKhach;
    private javax.swing.JButton btnTimSp;
    private javax.swing.JButton btnTraTien;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboChiNhanh;
    private javax.swing.JCheckBox cboDungDiem;
    private javax.swing.JComboBox<String> cboKhuVuc;
    private javax.swing.JComboBox<String> cboQuocGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblCanhBaoMaKhach;
    private javax.swing.JLabel lblCanhBaoSdtKhach;
    private javax.swing.JLabel lblCanhBaoTenKhach;
    private javax.swing.JLabel lblCanhBaoTien;
    private javax.swing.JLabel lblCloseThanhToan;
    private javax.swing.JLabel lblCloseThemKhach;
    private javax.swing.JLabel lblDiemTichLuy;
    private javax.swing.JLabel lblSoBan;
    private javax.swing.JLabel lblSoTienCanTra;
    private javax.swing.JLabel lblStateMoCa;
    private javax.swing.JLabel lblTienCanThanhToan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTienTichLuy;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlBan;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlListSp;
    private javax.swing.JPanel pnlSPChon;
    private javax.swing.JPanel pnlTimKhach;
    private javax.swing.JPanel pnlTimSanPham;
    private javax.swing.JPanel pnlTraTien;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblSpChon;
    private javax.swing.JTextArea txtHienThiKhach;
    private javax.swing.JTextArea txtKhuyenMai;
    private javax.swing.JTextField txtMaKhach;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenKhach;
    private javax.swing.JTextField txtThanhPho;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKhach;
    private javax.swing.JTextField txtTimSp;
    // End of variables declaration//GEN-END:variables

}
