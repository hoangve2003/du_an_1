/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.IBanHangService;
import service.implement.ChiTietSpService;
import service.IChiTietSpService;
import service.ISanPhamService;
import service.implement.BanHangService;
import service.implement.SanPhamService;
import viewmodel.ChiNhanhViewModel_Hoang;
import viewmodel.ChiTietSPViewModel;
import viewmodel.NguyenLieuDangSuDung;
import viewmodel.SanPhamViewModel;

/**
 *
 * @author trant
 */
public class QLSanPham extends javax.swing.JPanel implements Runnable {

    /**
     * Creates new form QLSanPham
     */
    private DefaultTableModel modelTableSanPham;
    private DefaultTableModel modelTableDinhLuong;
    private ISanPhamService sanPhamService;
    private IChiTietSpService chiTietSPService;
    private DefaultComboBoxModel<NguyenLieuDangSuDung> modelComBoNguyenLieu;
    private DefaultComboBoxModel<ChiNhanhViewModel_Hoang> modelComBoChiNhanh;
    private IBanHangService banHangService;
    private byte[] _arrAvatar;
    private ImageIcon defaultAvatar;
    TaiKhoanAdmin _admin;
    TaiKhoanNguoiDung _nguoiDung;

    public QLSanPham(TaiKhoanAdmin admin, TaiKhoanNguoiDung nguoiDung) {
        initComponents();
        _admin = admin;
        _nguoiDung = nguoiDung;
        banHangService = new BanHangService();
        sanPhamService = new SanPhamService();
        chiTietSPService = new ChiTietSpService();
        modelTableSanPham = new DefaultTableModel();
        modelTableDinhLuong = new DefaultTableModel();
        modelTableDinhLuong = (DefaultTableModel) tblDinhLuong.getModel();
        modelTableSanPham = (DefaultTableModel) tblSanPham.getModel();
        Thread loadData = new Thread(this);
        loadData.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlLeft = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaSp = new javax.swing.JTextField();
        txtTenSp = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        lblAnh = new javax.swing.JLabel();
        lbUpload = new javax.swing.JLabel();
        btnThemSP = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblDinhLuong = new javax.swing.JTable();
        cboNguyenLieu = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        rdoDangBan = new javax.swing.JRadioButton();
        rdoDaXoa = new javax.swing.JRadioButton();
        lblChiNhanh = new javax.swing.JLabel();
        cboChiNhanh = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(225, 218, 197));

        pnlLeft.setBackground(new java.awt.Color(225, 218, 197));
        pnlLeft.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(108, 83, 54))); // NOI18N
        pnlLeft.setForeground(new java.awt.Color(108, 83, 54));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(108, 83, 54));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Mã Sản Phẩm");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(108, 83, 54));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Tên Sản Phẩm");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(108, 83, 54));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Giá bán");

        txtMaSp.setBackground(new java.awt.Color(225, 218, 197));
        txtMaSp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtTenSp.setBackground(new java.awt.Color(225, 218, 197));
        txtTenSp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtGiaBan.setBackground(new java.awt.Color(225, 218, 197));
        txtGiaBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        lblAnh.setOpaque(true);

        lbUpload.setBackground(new java.awt.Color(225, 218, 197));
        lbUpload.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbUpload.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUpload.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/upload_to_ftp_25px.png"))); // NOI18N
        lbUpload.setText("Upload");
        lbUpload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbUpload.setOpaque(true);
        lbUpload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbUploadMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbUploadMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbUploadMouseExited(evt);
            }
        });

        btnThemSP.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/Plus_48px.png"))); // NOI18N
        btnThemSP.setText("Thêm mới");
        btnThemSP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng định lượng nguyên liệu"));

        tblDinhLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Mã nguyên liệu", "Tên nguyên liệu", "Định lượng(gam)", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDinhLuong.getTableHeader().setReorderingAllowed(false);
        tblDinhLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDinhLuongMouseClicked(evt);
            }
        });
        tblDinhLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDinhLuongKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblDinhLuong);
        if (tblDinhLuong.getColumnModel().getColumnCount() > 0) {
            tblDinhLuong.getColumnModel().getColumn(0).setMinWidth(0);
            tblDinhLuong.getColumnModel().getColumn(0).setMaxWidth(0);
            tblDinhLuong.getColumnModel().getColumn(1).setResizable(false);
            tblDinhLuong.getColumnModel().getColumn(2).setResizable(false);
            tblDinhLuong.getColumnModel().getColumn(3).setResizable(false);
            tblDinhLuong.getColumnModel().getColumn(4).setMinWidth(35);
            tblDinhLuong.getColumnModel().getColumn(4).setMaxWidth(35);
        }

        cboNguyenLieu.setBorder(null);
        cboNguyenLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNguyenLieuActionPerformed(evt);
            }
        });

        jLabel5.setText("Chọn nguyên liệu");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNguyenLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLeftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbUpload)
                .addGap(58, 58, 58))
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlLeftLayout.createSequentialGroup()
                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLeftLayout.createSequentialGroup()
                                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlLeftLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                    .addGroup(pnlLeftLayout.createSequentialGroup()
                                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(25, 25, 25)
                                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTenSp)
                                            .addComponent(txtMaSp))))
                                .addGap(115, 115, 115)
                                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnThemSP))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlLeftLayout.createSequentialGroup()
                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pnlRight.setBackground(new java.awt.Color(225, 218, 197));
        pnlRight.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(108, 83, 54))); // NOI18N
        pnlRight.setForeground(new java.awt.Color(108, 83, 54));

        btnCapNhat.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/available_updates_48px.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/waste_48px.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Mã", "Tên sản phẩm", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.getTableHeader().setReorderingAllowed(false);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setMinWidth(0);
            tblSanPham.getColumnModel().getColumn(0).setMaxWidth(0);
            tblSanPham.getColumnModel().getColumn(1).setMinWidth(40);
            tblSanPham.getColumnModel().getColumn(1).setMaxWidth(40);
            tblSanPham.getColumnModel().getColumn(2).setResizable(false);
            tblSanPham.getColumnModel().getColumn(3).setResizable(false);
        }

        buttonGroup1.add(rdoDangBan);
        rdoDangBan.setSelected(true);
        rdoDangBan.setText("Đang bán");
        rdoDangBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDangBanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaXoa);
        rdoDaXoa.setText("Đã xóa");
        rdoDaXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdoDangBan)
                .addGap(34, 34, 34)
                .addComponent(rdoDaXoa)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoDangBan)
                    .addComponent(rdoDaXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlRightLayout.createSequentialGroup()
                        .addComponent(btnCapNhat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblChiNhanh.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblChiNhanh.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/location_10px.png"))); // NOI18N
        lblChiNhanh.setText("Chi nhánh");

        cboChiNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChiNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblChiNhanh))
                    .addComponent(cboChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void run() {
        if (_admin != null) {
            cboChiNhanh.setVisible(true);
            modelComBoChiNhanh = (DefaultComboBoxModel) new DefaultComboBoxModel<>(banHangService.getAllChiNhanh().toArray());
            cboChiNhanh.setModel((DefaultComboBoxModel) modelComBoChiNhanh);
            modelComBoNguyenLieu = (DefaultComboBoxModel) new DefaultComboBoxModel<>(
                    sanPhamService.getAllNguyenLieuByChiNhanh(((ChiNhanhViewModel_Hoang) modelComBoChiNhanh.getSelectedItem()).getId()).toArray());
            cboNguyenLieu.setModel((DefaultComboBoxModel) modelComBoNguyenLieu);
            ShowSanPhamToTable(sanPhamService.getAllSanPhamDangBanByChiNhanh(((ChiNhanhViewModel_Hoang) modelComBoChiNhanh.getSelectedItem()).getId()));
        } else {
            cboChiNhanh.setVisible(false);
            modelComBoNguyenLieu = (DefaultComboBoxModel) new DefaultComboBoxModel<>(
                    sanPhamService.getAllNguyenLieuByChiNhanh(banHangService.getChiNhanhbyTaiKhoan(_nguoiDung.getId()).getId()).toArray());
            cboNguyenLieu.setModel((DefaultComboBoxModel) modelComBoNguyenLieu);
            ShowSanPhamToTable(sanPhamService.getAllSanPhamDangBanByChiNhanh(banHangService.getChiNhanhbyTaiKhoan(_nguoiDung.getId()).getId()));

        }
        Image image = new ImageIcon(getClass().getClassLoader().getResource("icon/add-image.png")).getImage();
        defaultAvatar = new ImageIcon(image.getScaledInstance(187, 186, Image.SCALE_SMOOTH));
        lblAnh.setIcon(defaultAvatar);
    }

    private void ShowSanPhamToTable(List<SanPhamViewModel> list) {
        modelTableSanPham.setRowCount(0);
        for (SanPhamViewModel sp : list) {
            modelTableSanPham.addRow(new Object[]{sp.getIdSp(), sp.getMaSp(),
                sp.getTenSp(), sp.getGiaBan()});
        }
    }
    private void lbUploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUploadMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();//return file selected-gán path cho File
            Image image = new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(187, 186, Image.SCALE_SMOOTH);
            lblAnh.setIcon(new ImageIcon(image));//tao đối tượng ImageIcon(lấy path file truyền vào)rồi seticon cho label
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                _arrAvatar = new byte[baos.toByteArray().length];
                _arrAvatar = baos.toByteArray();

            } catch (IOException ex) {

            }

        }

    }//GEN-LAST:event_lbUploadMouseClicked

    private void lbUploadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUploadMouseEntered
        lbUpload.setForeground(Color.GREEN);
    }//GEN-LAST:event_lbUploadMouseEntered

    private void lbUploadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUploadMouseExited
        lbUpload.setForeground(Color.BLACK);
    }//GEN-LAST:event_lbUploadMouseExited

    private void showChiTietSanPham(String idSp) {
        modelTableDinhLuong.setRowCount(0);
        SanPhamViewModel spView = sanPhamService.getSanPhamById(idSp);
        Set<ChiTietSPViewModel> chiTietView = chiTietSPService.getChiTietSpByIdSanPham(idSp);
        for (ChiTietSPViewModel ctView : chiTietView) {
            modelTableDinhLuong.addRow(new Object[]{ctView.getIdNguyenLieu(), ctView.getMaNguyenLieu(),
                ctView.getTenNguyenLieu(), ctView.getDinhLuong()});
        }
        _arrAvatar = spView.getAvatar();
        if (spView.getAvatar() != null) {
            Image image = new ImageIcon(_arrAvatar).getImage().getScaledInstance(187, 186, Image.SCALE_SMOOTH);
            lblAnh.setIcon(new ImageIcon(image));
        } else {
            lblAnh.setIcon(defaultAvatar);
        }

    }

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = tblSanPham.getSelectedRow();
        showChiTietSanPham(tblSanPham.getValueAt(row, 0).toString());

    }//GEN-LAST:event_tblSanPhamMouseClicked


    private void cboNguyenLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNguyenLieuActionPerformed
        int count = 0;
        if (cboNguyenLieu.getItemCount() <= 0) {
            JOptionPane.showMessageDialog(jPanel4, "Vui lòng bổ sung nguyên liệu");
        } else {
            NguyenLieuDangSuDung nguyenLieu = (NguyenLieuDangSuDung) modelComBoNguyenLieu.getSelectedItem();
            for (int i = 0; i < tblDinhLuong.getRowCount(); i++) {
                if (nguyenLieu.getId().equalsIgnoreCase(tblDinhLuong.getValueAt(i, 0).toString())) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                modelTableDinhLuong.addRow(new Object[]{nguyenLieu.getId(), nguyenLieu.getMa(), nguyenLieu.getTen(), 1});
            }
        }
    }//GEN-LAST:event_cboNguyenLieuActionPerformed

    public boolean checkNumber(String mark) {
        Pattern regexInt = Pattern.compile("^[0-9]+$");
        Pattern regexDouble = Pattern.compile("^[0-9]+(\\.)[0-9]+$");
        if (!regexInt.matcher(mark).find() && !regexDouble.matcher(mark).find()) {
            return false;
        } else {
            return true;
        }
    }

    private void tblDinhLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDinhLuongKeyReleased
        int row = tblDinhLuong.getSelectedRow();
        String soLuong = tblDinhLuong.getValueAt(row, 3).toString();
        if (!checkNumber(soLuong)) {
            JOptionPane.showMessageDialog(jPanel4, "Số lượng không hợp lệ !");
            tblDinhLuong.setValueAt(1, row, 3);
        }
    }//GEN-LAST:event_tblDinhLuongKeyReleased

    private void tblDinhLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDinhLuongMouseClicked
        int row = tblDinhLuong.getSelectedRow();
        int column = tblDinhLuong.getSelectedColumn();
        if (column == 4) {
            if (tblDinhLuong.getSelectedColumn() == 4) {
                if (tblDinhLuong.getValueAt(row, 4).equals(true)) {
                    modelTableDinhLuong.removeRow(row);
                }
            }
        }


    }//GEN-LAST:event_tblDinhLuongMouseClicked

    private boolean checkEmpty() {
        if (txtMaSp.getText().isBlank() || txtTenSp.getText().isBlank() || txtGiaBan.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Dữ liệu trống");
            return false;
        } else if (tblDinhLuong.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm định lượng");
            return false;
        }
        return true;
    }

    private boolean checkMaSp(String maSp) {
        if (sanPhamService.getSanPhamByMa(maSp) != null) {
            JOptionPane.showMessageDialog(pnlLeft, "Mã sản phẩm đã từng được sử dụng");
            return true;
        } else {
            return false;
        }
    }

    private void clearForm() {
        cboNguyenLieu.setSelectedIndex(0);

    }
    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        if (checkEmpty() && checkNumber(txtGiaBan.getText()) && !checkMaSp(txtMaSp.getText())) {
            sanPhamService.insertSanPham(txtMaSp.getText(), txtTenSp.getText(), Float.parseFloat(txtGiaBan.getText()), _arrAvatar);
            for (int i = 0; i < tblDinhLuong.getRowCount(); i++) {
                chiTietSPService.insertChiTietSanPham(Float.parseFloat(tblDinhLuong.getValueAt(i, 3).toString()),
                        sanPhamService.getSanPhamByMa(txtMaSp.getText()).getIdSp(), tblDinhLuong.getValueAt(i, 0).toString());
            }
            rdoDangBanActionPerformed(evt);
            clearForm();
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
        }
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        int row = tblSanPham.getSelectedRow();
        if (tblDinhLuong.getRowCount() > 0 && row != -1) {
            if (tblSanPham.getValueAt(row, 2) != null && tblSanPham.getValueAt(row, 3) != null) {
                sanPhamService.UpdateSanPham(
                        tblSanPham.getValueAt(row, 0).toString(),
                        tblSanPham.getValueAt(row, 1).toString(),
                        tblSanPham.getValueAt(row, 2).toString(),
                        Float.parseFloat(tblSanPham.getValueAt(row, 3).toString()),
                        _arrAvatar);
                chiTietSPService.deleteChiTietSpByIdSp(tblSanPham.getValueAt(row, 0).toString());
                for (int i = 0; i < tblDinhLuong.getRowCount(); i++) {
                    chiTietSPService.insertChiTietSanPham(Float.parseFloat(tblDinhLuong.getValueAt(i, 3).toString()),
                            tblSanPham.getValueAt(row, 0).toString(), tblDinhLuong.getValueAt(i, 0).toString());
                }
                rdoDangBanActionPerformed(evt);
                clearForm();
                JOptionPane.showMessageDialog(this, "Cập nhật phẩm thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Dữ liệu trống");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm và thêm định lượng");
        }

    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");
        } else {
            sanPhamService.deleteSanPham(tblSanPham.getValueAt(row, 0).toString());
            rdoDangBanActionPerformed(evt);
            clearForm();
            JOptionPane.showMessageDialog(this, "Xóa phẩm thành công");
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void rdoDangBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDangBanActionPerformed
        clearForm();
        btnXoa.setEnabled(true);
        btnCapNhat.setEnabled(true);
        btnThemSP.setEnabled(true);
        ShowSanPhamToTable(sanPhamService.getAllSanPhamDangBanByChiNhanh(((ChiNhanhViewModel_Hoang) modelComBoChiNhanh.getSelectedItem()).getId()));
    }//GEN-LAST:event_rdoDangBanActionPerformed

    private void rdoDaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaXoaActionPerformed
        clearForm();
        btnXoa.setEnabled(false);
        btnCapNhat.setEnabled(false);
        btnThemSP.setEnabled(false);
        ShowSanPhamToTable(sanPhamService.getAllSanPhamDaXoaByChiNhanh(((ChiNhanhViewModel_Hoang) modelComBoChiNhanh.getSelectedItem()).getId()));
    }//GEN-LAST:event_rdoDaXoaActionPerformed

    private void cboChiNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChiNhanhActionPerformed
        modelTableDinhLuong.setRowCount(0);
        lblAnh.setIcon(defaultAvatar);
        txtMaSp.setText("");
        txtTenSp.setText("");
        txtGiaBan.setText("");
        modelComBoNguyenLieu = (DefaultComboBoxModel) new DefaultComboBoxModel<>(
                sanPhamService.getAllNguyenLieuByChiNhanh(((ChiNhanhViewModel_Hoang) modelComBoChiNhanh.getSelectedItem()).getId()).toArray());
        cboNguyenLieu.setModel((DefaultComboBoxModel) modelComBoNguyenLieu);
        rdoDangBan.setSelected(true);
        ShowSanPhamToTable(sanPhamService.getAllSanPhamDangBanByChiNhanh(((ChiNhanhViewModel_Hoang) modelComBoChiNhanh.getSelectedItem()).getId()));

    }//GEN-LAST:event_cboChiNhanhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChiNhanh;
    private javax.swing.JComboBox<String> cboNguyenLieu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbUpload;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblChiNhanh;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JRadioButton rdoDaXoa;
    private javax.swing.JRadioButton rdoDangBan;
    private javax.swing.JTable tblDinhLuong;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextField txtTenSp;
    // End of variables declaration//GEN-END:variables
}
