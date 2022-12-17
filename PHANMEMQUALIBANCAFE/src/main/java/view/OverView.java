/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import domainmodel.ChiNhanh;
import domainmodel.NhanVien;
import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import service.ILogin;
import service.implement.LoginSerVice;
import viewmodel.ChiNhanhViewModel_Hoang;
import viewmodel.NguyenLieuViewModel_Hoang;

public class OverView extends javax.swing.JFrame {

    TaiKhoanAdmin _admin;
    TaiKhoanNguoiDung _nguoiDung;
    private String chucVu;
    ILogin logService = new LoginSerVice();
    private DefaultComboBoxModel<ChiNhanhViewModel_Hoang> comboModel = new DefaultComboBoxModel();
    Color colorEntered;
    Ban _ban;
    BanHang _banHang;
    KhuyenMai _khuyenMai;
    QLCa _qlCa;
    QLChiNhanh _qlChiNhanh;
    QLDoiTac _qlDoiTac;
    QLGiaoDich _qlgiaoDich;
    QLNguoiDung _qlNguoiDung;
    QLNguyenLieu _qlnguyenLieu;
    QLNhanVien _qlNhanVien;
    QLSanPham _qlSanPham;
    ThongKe _thongKe;
    NhanVien nv = null;

    public OverView(TaiKhoanAdmin admin, TaiKhoanNguoiDung nguoiDung) {
        initComponents();
        _admin = admin;
        _nguoiDung = nguoiDung;
        colorEntered = new Color(212, 182, 73);
        _banHang = new BanHang(admin, nguoiDung);
        _ban = new Ban(admin, nguoiDung);
//        _khuyenMai = new KhuyenMai(admin, nguoiDung);
        _qlCa = new QLCa(admin, nguoiDung);
        _qlChiNhanh = new QLChiNhanh(admin, nguoiDung);
//        _qlDoiTac = new QLDoiTac(admin, nguoiDung);
        _qlgiaoDich = new QLGiaoDich(admin, nguoiDung);
        _qlNguoiDung = new QLNguoiDung(admin, nguoiDung);
        _qlnguyenLieu = new QLNguyenLieu(admin, nguoiDung);
//        _qlNhanVien = new QLNhanVien(admin, nguoiDung);
        _qlSanPham = new QLSanPham(admin, nguoiDung);
        _thongKe = new ThongKe(admin, nguoiDung);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setUndecorated(true);

        if (admin != null) {
            lblTenTaiKhoan.setText(admin.getTenTK());
            chucVu = "Ông chủ";
            lblChiNhanh.setText("Center");
        } else if (nguoiDung != null) {
            nv = logService.getNhanVienbyTaiKhoan(nguoiDung.getId());
            lblTenTaiKhoan.setText(nguoiDung.getTenTK());
            chucVu = logService.getChucVubyIdNhanVien(nv.getId()).getTen();
            lblChiNhanh.setText(logService.getChiNhanhByNhanVien(nv.getId()).getMa());
        }

        // Khuyen Mai
        if (nv != null) {
            _khuyenMai = new KhuyenMai(chucVu, nv.getChiNhanh());
        } else {
            _khuyenMai = new KhuyenMai(chucVu);
        }
        // Doi tac
        if (nv != null) {
            _qlDoiTac = new QLDoiTac(chucVu, nv.getChiNhanh());
        } else {
            _qlDoiTac = new QLDoiTac(chucVu);
        }
        // Nhan vien
        if (nv != null) {
            _qlNhanVien = new QLNhanVien(chucVu, nv.getChiNhanh());
        } else {
            _qlNhanVien = new QLNhanVien(chucVu);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLeft = new javax.swing.JPanel();
        banHang = new javax.swing.JPanel();
        lblBanHang = new javax.swing.JLabel();
        qlChiNhanh = new javax.swing.JPanel();
        lblQlCNhanh = new javax.swing.JLabel();
        QLGiaoDich = new javax.swing.JPanel();
        lblQlGiaoDich = new javax.swing.JLabel();
        QLNguyenLieu = new javax.swing.JPanel();
        lblQlNguyenLieu = new javax.swing.JLabel();
        QLSanPham = new javax.swing.JPanel();
        lblQlSanPham = new javax.swing.JLabel();
        QLNhanVien = new javax.swing.JPanel();
        lblQlNhanVien = new javax.swing.JLabel();
        khuyenMai = new javax.swing.JPanel();
        lblKhuyenMai = new javax.swing.JLabel();
        ban = new javax.swing.JPanel();
        lblBan = new javax.swing.JLabel();
        QLCa = new javax.swing.JPanel();
        lblQlca = new javax.swing.JLabel();
        thongKe = new javax.swing.JPanel();
        lblThongKe = new javax.swing.JLabel();
        QLDoiTac = new javax.swing.JPanel();
        lblQlDoiTac = new javax.swing.JLabel();
        QLNguoiDung = new javax.swing.JPanel();
        lblQlNguoiDung = new javax.swing.JLabel();
        pnlLeft1 = new javax.swing.JPanel();
        banHang1 = new javax.swing.JPanel();
        lblBanHang1 = new javax.swing.JLabel();
        qlChiNhanh1 = new javax.swing.JPanel();
        lblQlNguoiDung1 = new javax.swing.JLabel();
        QLGiaoDich1 = new javax.swing.JPanel();
        lblQlGiaoDich1 = new javax.swing.JLabel();
        QLNguyenLieu1 = new javax.swing.JPanel();
        lblQlNguyenLieu1 = new javax.swing.JLabel();
        QLSanPham1 = new javax.swing.JPanel();
        lblQlSanPham1 = new javax.swing.JLabel();
        QLNhanVien1 = new javax.swing.JPanel();
        lblQlNhanVien1 = new javax.swing.JLabel();
        khuyenMai1 = new javax.swing.JPanel();
        lblKhuyenMai1 = new javax.swing.JLabel();
        ban1 = new javax.swing.JPanel();
        lblBan1 = new javax.swing.JLabel();
        QLCa1 = new javax.swing.JPanel();
        lblQlca1 = new javax.swing.JLabel();
        thongKe1 = new javax.swing.JPanel();
        lblThongKe1 = new javax.swing.JLabel();
        QLDoiTac1 = new javax.swing.JPanel();
        lblQlDoiTac1 = new javax.swing.JLabel();
        QLNguoiDung1 = new javax.swing.JPanel();
        lblQlChiNhanh1 = new javax.swing.JLabel();
        lblBanHang2 = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTenTaiKhoan = new javax.swing.JLabel();
        lblChiNhanh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnlLeft.setPreferredSize(new java.awt.Dimension(200, 527));
        pnlLeft.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        banHang.setBackground(new java.awt.Color(255, 255, 255));
        banHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        banHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                banHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                banHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                banHangMouseExited(evt);
            }
        });
        banHang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBanHang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBanHang.setForeground(new java.awt.Color(102, 51, 0));
        lblBanHang.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/buying_35px.png"))); // NOI18N
        lblBanHang.setText(" Bán hàng");
        banHang.add(lblBanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(banHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 200, 60));

        qlChiNhanh.setBackground(new java.awt.Color(255, 255, 255));
        qlChiNhanh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        qlChiNhanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                qlChiNhanhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                qlChiNhanhMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                qlChiNhanhMouseExited(evt);
            }
        });
        qlChiNhanh.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlCNhanh.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlCNhanh.setForeground(new java.awt.Color(102, 51, 0));
        lblQlCNhanh.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/online_store_35px.png"))); // NOI18N
        lblQlCNhanh.setText(" Quản lý chi nhánh");
        qlChiNhanh.add(lblQlCNhanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(qlChiNhanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 200, 60));

        QLGiaoDich.setBackground(new java.awt.Color(255, 255, 255));
        QLGiaoDich.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLGiaoDich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLGiaoDichMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLGiaoDichMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLGiaoDichMouseExited(evt);
            }
        });
        QLGiaoDich.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlGiaoDich.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlGiaoDich.setForeground(new java.awt.Color(102, 51, 0));
        lblQlGiaoDich.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/receipt_35px.png"))); // NOI18N
        lblQlGiaoDich.setText(" Quản lý giao dịch");
        QLGiaoDich.add(lblQlGiaoDich, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(QLGiaoDich, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 200, 60));

        QLNguyenLieu.setBackground(new java.awt.Color(255, 255, 255));
        QLNguyenLieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLNguyenLieuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLNguyenLieuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLNguyenLieuMouseExited(evt);
            }
        });
        QLNguyenLieu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlNguyenLieu.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlNguyenLieu.setForeground(new java.awt.Color(102, 51, 0));
        lblQlNguyenLieu.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/flour_35px.png"))); // NOI18N
        lblQlNguyenLieu.setText(" Quản lý nguyên liệu");
        QLNguyenLieu.add(lblQlNguyenLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(QLNguyenLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 200, 60));

        QLSanPham.setBackground(new java.awt.Color(255, 255, 255));
        QLSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLSanPhamMouseExited(evt);
            }
        });
        QLSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlSanPham.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlSanPham.setForeground(new java.awt.Color(102, 51, 0));
        lblQlSanPham.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/coffee_to_go_35px.png"))); // NOI18N
        lblQlSanPham.setText(" Quản lý sản phẩm");
        QLSanPham.add(lblQlSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(QLSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 200, 60));

        QLNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        QLNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLNhanVienMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLNhanVienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLNhanVienMouseExited(evt);
            }
        });
        QLNhanVien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlNhanVien.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlNhanVien.setForeground(new java.awt.Color(102, 51, 0));
        lblQlNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/management_35px.png"))); // NOI18N
        lblQlNhanVien.setText(" Quản lý nhân viên");
        QLNhanVien.add(lblQlNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(QLNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 200, 60));

        khuyenMai.setBackground(new java.awt.Color(255, 255, 255));
        khuyenMai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        khuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khuyenMaiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                khuyenMaiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                khuyenMaiMouseExited(evt);
            }
        });
        khuyenMai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblKhuyenMai.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblKhuyenMai.setForeground(new java.awt.Color(102, 51, 0));
        lblKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/sale_35px.png"))); // NOI18N
        lblKhuyenMai.setText(" Khuyến mại");
        khuyenMai.add(lblKhuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(khuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 200, 60));

        ban.setBackground(new java.awt.Color(255, 255, 255));
        ban.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                banMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                banMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                banMouseExited(evt);
            }
        });
        ban.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBan.setForeground(new java.awt.Color(102, 51, 0));
        lblBan.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/grid_35px.png"))); // NOI18N
        lblBan.setText(" Bàn");
        ban.add(lblBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(ban, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 200, 60));

        QLCa.setBackground(new java.awt.Color(255, 255, 255));
        QLCa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLCaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLCaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLCaMouseExited(evt);
            }
        });
        QLCa.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlca.setForeground(new java.awt.Color(102, 51, 0));
        lblQlca.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/business_35px.png"))); // NOI18N
        lblQlca.setText(" Quản lý ca");
        QLCa.add(lblQlca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(QLCa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 200, 60));

        thongKe.setBackground(new java.awt.Color(255, 255, 255));
        thongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        thongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongKeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                thongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                thongKeMouseExited(evt);
            }
        });
        thongKe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThongKe.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblThongKe.setForeground(new java.awt.Color(102, 51, 0));
        lblThongKe.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/analytics_35px.png"))); // NOI18N
        lblThongKe.setText(" Thống kê");
        thongKe.add(lblThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(thongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 200, 60));

        QLDoiTac.setBackground(new java.awt.Color(255, 255, 255));
        QLDoiTac.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLDoiTac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLDoiTacMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLDoiTacMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLDoiTacMouseExited(evt);
            }
        });
        QLDoiTac.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlDoiTac.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlDoiTac.setForeground(new java.awt.Color(102, 51, 0));
        lblQlDoiTac.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/handshake_35px.png"))); // NOI18N
        lblQlDoiTac.setText(" Quản lý đối tác");
        QLDoiTac.add(lblQlDoiTac, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(QLDoiTac, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 200, 60));

        QLNguoiDung.setBackground(new java.awt.Color(255, 255, 255));
        QLNguoiDung.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLNguoiDungMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLNguoiDungMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLNguoiDungMouseExited(evt);
            }
        });
        QLNguoiDung.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlNguoiDung.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlNguoiDung.setForeground(new java.awt.Color(102, 51, 0));
        lblQlNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/account_35px.png"))); // NOI18N
        lblQlNguoiDung.setText(" Quản lý người dùng");
        QLNguoiDung.add(lblQlNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        pnlLeft.add(QLNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 200, 60));

        pnlLeft1.setBackground(new java.awt.Color(255, 255, 255));
        pnlLeft1.setPreferredSize(new java.awt.Dimension(200, 527));
        pnlLeft1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        banHang1.setBackground(new java.awt.Color(255, 255, 255));
        banHang1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        banHang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                banHang1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                banHang1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                banHang1MouseExited(evt);
            }
        });
        banHang1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBanHang1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBanHang1.setForeground(new java.awt.Color(102, 51, 0));
        lblBanHang1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/buying_35px.png"))); // NOI18N
        lblBanHang1.setText("Bán hàng");
        banHang1.add(lblBanHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, -1));

        pnlLeft1.add(banHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 200, 60));

        qlChiNhanh1.setBackground(new java.awt.Color(255, 255, 255));
        qlChiNhanh1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        qlChiNhanh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                qlChiNhanh1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                qlChiNhanh1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                qlChiNhanh1MouseExited(evt);
            }
        });
        qlChiNhanh1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlNguoiDung1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlNguoiDung1.setForeground(new java.awt.Color(102, 51, 0));
        lblQlNguoiDung1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/account_35px.png"))); // NOI18N
        lblQlNguoiDung1.setText("Quản lý người dùng");
        qlChiNhanh1.add(lblQlNguoiDung1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 180, -1));

        pnlLeft1.add(qlChiNhanh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 200, 60));

        QLGiaoDich1.setBackground(new java.awt.Color(255, 255, 255));
        QLGiaoDich1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLGiaoDich1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLGiaoDich1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLGiaoDich1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLGiaoDich1MouseExited(evt);
            }
        });
        QLGiaoDich1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlGiaoDich1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlGiaoDich1.setForeground(new java.awt.Color(102, 51, 0));
        lblQlGiaoDich1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/receipt_35px.png"))); // NOI18N
        lblQlGiaoDich1.setText("Quản lý giao dịch");
        QLGiaoDich1.add(lblQlGiaoDich1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, -1));

        pnlLeft1.add(QLGiaoDich1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 200, 60));

        QLNguyenLieu1.setBackground(new java.awt.Color(255, 255, 255));
        QLNguyenLieu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLNguyenLieu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLNguyenLieu1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLNguyenLieu1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLNguyenLieu1MouseExited(evt);
            }
        });
        QLNguyenLieu1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlNguyenLieu1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlNguyenLieu1.setForeground(new java.awt.Color(102, 51, 0));
        lblQlNguyenLieu1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/flour_35px.png"))); // NOI18N
        lblQlNguyenLieu1.setText("Quản lý nguyên liệu");
        QLNguyenLieu1.add(lblQlNguyenLieu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 180, -1));

        pnlLeft1.add(QLNguyenLieu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 200, 60));

        QLSanPham1.setBackground(new java.awt.Color(255, 255, 255));
        QLSanPham1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLSanPham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLSanPham1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLSanPham1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLSanPham1MouseExited(evt);
            }
        });
        QLSanPham1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlSanPham1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlSanPham1.setForeground(new java.awt.Color(102, 51, 0));
        lblQlSanPham1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/coffee_to_go_35px.png"))); // NOI18N
        lblQlSanPham1.setText("Quản lý sản phẩm");
        QLSanPham1.add(lblQlSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 170, -1));

        pnlLeft1.add(QLSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 200, 60));

        QLNhanVien1.setBackground(new java.awt.Color(255, 255, 255));
        QLNhanVien1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLNhanVien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLNhanVien1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLNhanVien1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLNhanVien1MouseExited(evt);
            }
        });
        QLNhanVien1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlNhanVien1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlNhanVien1.setForeground(new java.awt.Color(102, 51, 0));
        lblQlNhanVien1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/management_35px.png"))); // NOI18N
        lblQlNhanVien1.setText("Quản lý nhân viên");
        QLNhanVien1.add(lblQlNhanVien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, -1));

        pnlLeft1.add(QLNhanVien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 200, 60));

        khuyenMai1.setBackground(new java.awt.Color(255, 255, 255));
        khuyenMai1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        khuyenMai1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khuyenMai1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                khuyenMai1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                khuyenMai1MouseExited(evt);
            }
        });
        khuyenMai1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblKhuyenMai1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblKhuyenMai1.setForeground(new java.awt.Color(102, 51, 0));
        lblKhuyenMai1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/sale_35px.png"))); // NOI18N
        lblKhuyenMai1.setText("Khuyến mại");
        khuyenMai1.add(lblKhuyenMai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, -1));

        pnlLeft1.add(khuyenMai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 200, 60));

        ban1.setBackground(new java.awt.Color(255, 255, 255));
        ban1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ban1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ban1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ban1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ban1MouseExited(evt);
            }
        });
        ban1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBan1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBan1.setForeground(new java.awt.Color(102, 51, 0));
        lblBan1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/grid_35px.png"))); // NOI18N
        lblBan1.setText("Bàn");
        ban1.add(lblBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, -1));

        pnlLeft1.add(ban1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 200, 60));

        QLCa1.setBackground(new java.awt.Color(255, 255, 255));
        QLCa1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLCa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLCa1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLCa1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLCa1MouseExited(evt);
            }
        });
        QLCa1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlca1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlca1.setForeground(new java.awt.Color(102, 51, 0));
        lblQlca1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/business_35px.png"))); // NOI18N
        lblQlca1.setText("Quản lý ca");
        QLCa1.add(lblQlca1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, -1));

        pnlLeft1.add(QLCa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 200, 60));

        thongKe1.setBackground(new java.awt.Color(255, 255, 255));
        thongKe1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        thongKe1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongKe1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                thongKe1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                thongKe1MouseExited(evt);
            }
        });
        thongKe1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThongKe1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblThongKe1.setForeground(new java.awt.Color(102, 51, 0));
        lblThongKe1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/analytics_35px.png"))); // NOI18N
        lblThongKe1.setText("Thống kê");
        thongKe1.add(lblThongKe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, -1));

        pnlLeft1.add(thongKe1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 200, 60));

        QLDoiTac1.setBackground(new java.awt.Color(255, 255, 255));
        QLDoiTac1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLDoiTac1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLDoiTac1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLDoiTac1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLDoiTac1MouseExited(evt);
            }
        });
        QLDoiTac1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlDoiTac1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlDoiTac1.setForeground(new java.awt.Color(102, 51, 0));
        lblQlDoiTac1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/handshake_35px.png"))); // NOI18N
        lblQlDoiTac1.setText("Quản lý đối tác");
        QLDoiTac1.add(lblQlDoiTac1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, -1));

        pnlLeft1.add(QLDoiTac1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 200, 60));

        QLNguoiDung1.setBackground(new java.awt.Color(255, 255, 255));
        QLNguoiDung1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QLNguoiDung1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLNguoiDung1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QLNguoiDung1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QLNguoiDung1MouseExited(evt);
            }
        });
        QLNguoiDung1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQlChiNhanh1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblQlChiNhanh1.setForeground(new java.awt.Color(102, 51, 0));
        lblQlChiNhanh1.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/online_store_35px.png"))); // NOI18N
        lblQlChiNhanh1.setText("Quản lý chi nhánh");
        QLNguoiDung1.add(lblQlChiNhanh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, -1));

        pnlLeft1.add(QLNguoiDung1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 200, 60));

        lblBanHang2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        lblBanHang2.setForeground(new java.awt.Color(102, 51, 0));
        lblBanHang2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBanHang2.setText("DANH MỤC");
        pnlLeft1.add(lblBanHang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 40));

        pnlLeft.add(pnlLeft1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(pnlLeft, java.awt.BorderLayout.LINE_START);

        pnlMain.setBackground(new java.awt.Color(225, 218, 197));
        pnlMain.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pnlHeader.setBackground(new java.awt.Color(108, 83, 54));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1387, 100));

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("The coffee family");

        btnDangXuat.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/icons8_Logout_Rounded_30px.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseExited(evt);
            }
        });
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(108, 83, 54));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/location_30px.png"))); // NOI18N

        lblTenTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTenTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lblTenTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/account_30px.png"))); // NOI18N
        lblTenTaiKhoan.setText(" Tên tài khoản");

        lblChiNhanh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblChiNhanh.setForeground(new java.awt.Color(255, 255, 255));
        lblChiNhanh.setText("Chi nhánh");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenTaiKhoan)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblChiNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTenTaiKhoan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblChiNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnDangXuat)
                .addContainerGap())
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        setSize(new java.awt.Dimension(1403, 884));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void banHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banHangMouseClicked
        BanHang bh = new BanHang(_admin, _nguoiDung);
        setMainPanel(bh);
    }//GEN-LAST:event_banHangMouseClicked

    private void banHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banHangMouseEntered
        banHang.setBackground(colorEntered);
    }//GEN-LAST:event_banHangMouseEntered

    private void banHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banHangMouseExited
        banHang.setBackground(Color.WHITE);
    }//GEN-LAST:event_banHangMouseExited

    private void qlChiNhanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qlChiNhanhMouseClicked

        if (chucVu.equalsIgnoreCase("Ông chủ")) {
            QLChiNhanh qlChiNhanh = new QLChiNhanh(_admin, _nguoiDung);
            setMainPanel(qlChiNhanh);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_qlChiNhanhMouseClicked

    private void qlChiNhanhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qlChiNhanhMouseEntered
        qlChiNhanh.setBackground(colorEntered);
    }//GEN-LAST:event_qlChiNhanhMouseEntered

    private void qlChiNhanhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qlChiNhanhMouseExited
        qlChiNhanh.setBackground(Color.WHITE);
    }//GEN-LAST:event_qlChiNhanhMouseExited

    private void QLGiaoDichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLGiaoDichMouseClicked

        if (chucVu.equalsIgnoreCase("Ông chủ") || chucVu.equalsIgnoreCase("Quản lý")) {
            QLGiaoDich gd = new QLGiaoDich(_admin, _nguoiDung);
            setMainPanel(gd);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_QLGiaoDichMouseClicked

    private void QLGiaoDichMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLGiaoDichMouseEntered
        QLGiaoDich.setBackground(colorEntered);
    }//GEN-LAST:event_QLGiaoDichMouseEntered

    private void QLGiaoDichMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLGiaoDichMouseExited
        QLGiaoDich.setBackground(Color.WHITE);
    }//GEN-LAST:event_QLGiaoDichMouseExited

    private void QLNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguyenLieuMouseClicked

        if (chucVu.equalsIgnoreCase("Ông chủ")) {
            QLNguyenLieu qlnl = new QLNguyenLieu(_admin, _nguoiDung);
            setMainPanel(qlnl);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_QLNguyenLieuMouseClicked

    private void QLNguyenLieuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguyenLieuMouseEntered
        QLNguyenLieu.setBackground(colorEntered);
    }//GEN-LAST:event_QLNguyenLieuMouseEntered

    private void QLNguyenLieuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguyenLieuMouseExited
        QLNguyenLieu.setBackground(Color.WHITE);
    }//GEN-LAST:event_QLNguyenLieuMouseExited

    private void QLSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLSanPhamMouseClicked

        if (chucVu.equalsIgnoreCase("Ông chủ") || chucVu.equalsIgnoreCase("Quản lý")) {
            QLSanPham qlsp = new QLSanPham(_admin, _nguoiDung);
            setMainPanel(qlsp);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_QLSanPhamMouseClicked

    private void QLSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLSanPhamMouseEntered
        QLSanPham.setBackground(colorEntered);
    }//GEN-LAST:event_QLSanPhamMouseEntered

    private void QLSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLSanPhamMouseExited
        QLSanPham.setBackground(Color.WHITE);
    }//GEN-LAST:event_QLSanPhamMouseExited

    private void QLNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNhanVienMouseClicked
        if (chucVu.equalsIgnoreCase("Ông chủ") || chucVu.equalsIgnoreCase("Quản lý")) {
            if (nv != null) {
                _qlNhanVien = new QLNhanVien(chucVu, nv.getChiNhanh());
            } else {
                _qlNhanVien = new QLNhanVien(chucVu);
            }
            setMainPanel(_qlNhanVien);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_QLNhanVienMouseClicked

    private void QLNhanVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNhanVienMouseEntered
        QLNhanVien.setBackground(colorEntered);
    }//GEN-LAST:event_QLNhanVienMouseEntered

    private void QLNhanVienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNhanVienMouseExited
        QLNhanVien.setBackground(Color.WHITE);
    }//GEN-LAST:event_QLNhanVienMouseExited

    private void khuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenMaiMouseClicked

        if (chucVu.equalsIgnoreCase("Ông chủ") || chucVu.equalsIgnoreCase("Quản lý")) {
            if (nv != null) {
                _khuyenMai = new KhuyenMai(chucVu, nv.getChiNhanh());
            } else {
                _khuyenMai = new KhuyenMai(chucVu);
            }
            setMainPanel(_khuyenMai);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_khuyenMaiMouseClicked

    private void khuyenMaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenMaiMouseEntered
        khuyenMai.setBackground(colorEntered);
    }//GEN-LAST:event_khuyenMaiMouseEntered

    private void khuyenMaiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenMaiMouseExited
        khuyenMai.setBackground(Color.WHITE);
    }//GEN-LAST:event_khuyenMaiMouseExited

    private void banMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banMouseClicked

        if (chucVu.equalsIgnoreCase("Ông chủ") || chucVu.equalsIgnoreCase("Quản lý")) {
            Ban ban = new Ban(_admin, _nguoiDung);
            setMainPanel(ban);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_banMouseClicked

    private void banMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banMouseEntered
        ban.setBackground(colorEntered);
    }//GEN-LAST:event_banMouseEntered

    private void banMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banMouseExited
        ban.setBackground(Color.WHITE);
    }//GEN-LAST:event_banMouseExited

    private void QLCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLCaMouseClicked

        if (chucVu.equalsIgnoreCase("Ông chủ") || chucVu.equalsIgnoreCase("Quản lý")) {
            QLCa ca = new QLCa(_admin, _nguoiDung);
            setMainPanel(ca);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_QLCaMouseClicked

    private void QLCaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLCaMouseEntered
        QLCa.setBackground(colorEntered);
    }//GEN-LAST:event_QLCaMouseEntered

    private void QLCaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLCaMouseExited
        QLCa.setBackground(Color.WHITE);
    }//GEN-LAST:event_QLCaMouseExited

    private void thongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongKeMouseClicked
        if (chucVu.equalsIgnoreCase("Ông chủ") || chucVu.equalsIgnoreCase("Quản lý")) {
            ThongKe tk = new ThongKe(_admin, _nguoiDung);
            setMainPanel(tk);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_thongKeMouseClicked

    private void thongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongKeMouseEntered
        thongKe.setBackground(colorEntered);
    }//GEN-LAST:event_thongKeMouseEntered

    private void thongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongKeMouseExited
        thongKe.setBackground(Color.WHITE);
    }//GEN-LAST:event_thongKeMouseExited

    private void QLDoiTacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLDoiTacMouseClicked

        if (chucVu.equalsIgnoreCase("Ông chủ") || chucVu.equalsIgnoreCase("Quản lý")) {
            if (nv != null) {
                _qlDoiTac = new QLDoiTac(chucVu, nv.getChiNhanh());
            } else {
                _qlDoiTac = new QLDoiTac(chucVu);
            }
            setMainPanel(_qlDoiTac);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_QLDoiTacMouseClicked

    private void QLDoiTacMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLDoiTacMouseEntered
        QLDoiTac.setBackground(colorEntered);
    }//GEN-LAST:event_QLDoiTacMouseEntered

    private void QLDoiTacMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLDoiTacMouseExited
        QLDoiTac.setBackground(Color.WHITE);
    }//GEN-LAST:event_QLDoiTacMouseExited

    private void QLNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguoiDungMouseClicked
        if (chucVu.equalsIgnoreCase("Ông chủ")) {
            QLNguoiDung ng = new QLNguoiDung(_admin, _nguoiDung);
            setMainPanel(ng);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không đủ thẩm quyền");
        }
    }//GEN-LAST:event_QLNguoiDungMouseClicked

    private void QLNguoiDungMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguoiDungMouseEntered
        QLNguoiDung.setBackground(colorEntered);
    }//GEN-LAST:event_QLNguoiDungMouseEntered

    private void QLNguoiDungMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguoiDungMouseExited
        QLNguoiDung.setBackground(Color.WHITE);
    }//GEN-LAST:event_QLNguoiDungMouseExited

    private void banHang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banHang1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_banHang1MouseClicked

    private void banHang1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banHang1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_banHang1MouseEntered

    private void banHang1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banHang1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_banHang1MouseExited

    private void qlChiNhanh1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qlChiNhanh1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_qlChiNhanh1MouseClicked

    private void qlChiNhanh1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qlChiNhanh1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_qlChiNhanh1MouseEntered

    private void qlChiNhanh1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qlChiNhanh1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_qlChiNhanh1MouseExited

    private void QLGiaoDich1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLGiaoDich1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_QLGiaoDich1MouseClicked

    private void QLGiaoDich1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLGiaoDich1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_QLGiaoDich1MouseEntered

    private void QLGiaoDich1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLGiaoDich1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_QLGiaoDich1MouseExited

    private void QLNguyenLieu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguyenLieu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_QLNguyenLieu1MouseClicked

    private void QLNguyenLieu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguyenLieu1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_QLNguyenLieu1MouseEntered

    private void QLNguyenLieu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguyenLieu1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_QLNguyenLieu1MouseExited

    private void QLSanPham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLSanPham1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_QLSanPham1MouseClicked

    private void QLSanPham1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLSanPham1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_QLSanPham1MouseEntered

    private void QLSanPham1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLSanPham1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_QLSanPham1MouseExited

    private void QLNhanVien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNhanVien1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_QLNhanVien1MouseClicked

    private void QLNhanVien1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNhanVien1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_QLNhanVien1MouseEntered

    private void QLNhanVien1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNhanVien1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_QLNhanVien1MouseExited

    private void khuyenMai1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenMai1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_khuyenMai1MouseClicked

    private void khuyenMai1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenMai1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_khuyenMai1MouseEntered

    private void khuyenMai1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khuyenMai1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_khuyenMai1MouseExited

    private void ban1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ban1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ban1MouseClicked

    private void ban1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ban1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ban1MouseEntered

    private void ban1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ban1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ban1MouseExited

    private void QLCa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLCa1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_QLCa1MouseClicked

    private void QLCa1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLCa1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_QLCa1MouseEntered

    private void QLCa1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLCa1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_QLCa1MouseExited

    private void thongKe1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongKe1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_thongKe1MouseClicked

    private void thongKe1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongKe1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_thongKe1MouseEntered

    private void thongKe1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongKe1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_thongKe1MouseExited

    private void QLDoiTac1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLDoiTac1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_QLDoiTac1MouseClicked

    private void QLDoiTac1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLDoiTac1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_QLDoiTac1MouseEntered

    private void QLDoiTac1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLDoiTac1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_QLDoiTac1MouseExited

    private void QLNguoiDung1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguoiDung1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_QLNguoiDung1MouseClicked

    private void QLNguoiDung1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguoiDung1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_QLNguoiDung1MouseEntered

    private void QLNguoiDung1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNguoiDung1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_QLNguoiDung1MouseExited

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed

        Login log = new Login();
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            log.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnDangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseEntered
        btnDangXuat.setBackground(Color.RED);
        Color cl = new Color(225, 218, 197);
        btnDangXuat.setForeground(cl);
    }//GEN-LAST:event_btnDangXuatMouseEntered

    private void btnDangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseExited
        Color cl = new Color(225, 218, 197);
        btnDangXuat.setBackground(cl);
        btnDangXuat.setForeground(Color.RED);
    }//GEN-LAST:event_btnDangXuatMouseExited

    private void setMainPanel(Component component) {
        pnlMain.removeAll();
        pnlMain.add(component);
        pnlMain.repaint();
        pnlMain.revalidate();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel QLCa;
    private javax.swing.JPanel QLCa1;
    private javax.swing.JPanel QLDoiTac;
    private javax.swing.JPanel QLDoiTac1;
    private javax.swing.JPanel QLGiaoDich;
    private javax.swing.JPanel QLGiaoDich1;
    private javax.swing.JPanel QLNguoiDung;
    private javax.swing.JPanel QLNguoiDung1;
    private javax.swing.JPanel QLNguyenLieu;
    private javax.swing.JPanel QLNguyenLieu1;
    private javax.swing.JPanel QLNhanVien;
    private javax.swing.JPanel QLNhanVien1;
    private javax.swing.JPanel QLSanPham;
    private javax.swing.JPanel QLSanPham1;
    private javax.swing.JPanel ban;
    private javax.swing.JPanel ban1;
    private javax.swing.JPanel banHang;
    private javax.swing.JPanel banHang1;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel khuyenMai;
    private javax.swing.JPanel khuyenMai1;
    private javax.swing.JLabel lblBan;
    private javax.swing.JLabel lblBan1;
    private javax.swing.JLabel lblBanHang;
    private javax.swing.JLabel lblBanHang1;
    private javax.swing.JLabel lblBanHang2;
    private javax.swing.JLabel lblChiNhanh;
    private javax.swing.JLabel lblKhuyenMai;
    private javax.swing.JLabel lblKhuyenMai1;
    private javax.swing.JLabel lblQlCNhanh;
    private javax.swing.JLabel lblQlChiNhanh1;
    private javax.swing.JLabel lblQlDoiTac;
    private javax.swing.JLabel lblQlDoiTac1;
    private javax.swing.JLabel lblQlGiaoDich;
    private javax.swing.JLabel lblQlGiaoDich1;
    private javax.swing.JLabel lblQlNguoiDung;
    private javax.swing.JLabel lblQlNguoiDung1;
    private javax.swing.JLabel lblQlNguyenLieu;
    private javax.swing.JLabel lblQlNguyenLieu1;
    private javax.swing.JLabel lblQlNhanVien;
    private javax.swing.JLabel lblQlNhanVien1;
    private javax.swing.JLabel lblQlSanPham;
    private javax.swing.JLabel lblQlSanPham1;
    private javax.swing.JLabel lblQlca;
    private javax.swing.JLabel lblQlca1;
    private javax.swing.JLabel lblTenTaiKhoan;
    private javax.swing.JLabel lblThongKe;
    private javax.swing.JLabel lblThongKe1;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlLeft1;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel qlChiNhanh;
    private javax.swing.JPanel qlChiNhanh1;
    private javax.swing.JPanel thongKe;
    private javax.swing.JPanel thongKe1;
    // End of variables declaration//GEN-END:variables
}
