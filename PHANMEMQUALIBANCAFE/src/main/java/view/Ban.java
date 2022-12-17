/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import repository.BanRepository;
import repository.KhuVucRepository;
import service.implement.BanService;
import service.IBanService;
import service.IKhuVucService;
import service.implement.KhuVucService;
import viewmodel.BanViewModel;
import viewmodel.ChiNhanhViewModel_Hoang;
import viewmodel.KhuVucViewModel;

/**
 *
 * @author trant
 */
public class Ban extends javax.swing.JPanel {
    
    private DefaultTableModel defaultTableModel;
    private DefaultComboBoxModel<KhuVucViewModel> comboKhuVuc;
    private DefaultComboBoxModel<ChiNhanhViewModel_Hoang> comboChiNhanh;
    private IBanService ibanService;
    private IKhuVucService iKhuVucService;
    private KhuVucRepository khuVucRepo;
    private BanRepository banRepo;
    private TaiKhoanAdmin taiKhoanAdmin;
    private TaiKhoanNguoiDung taiKhoanNguoiDung;
    
    KhuVucViewModel kvView = new KhuVucViewModel();
    BanViewModel banView = new BanViewModel();
    List<KhuVucViewModel> listkvView = new ArrayList<>();
    List<BanViewModel> listBanView = new ArrayList<>();
    List<ChiNhanhViewModel_Hoang> listcnView = new ArrayList<>();
    
    public Ban(TaiKhoanAdmin admin, TaiKhoanNguoiDung nguoiDung) {
        initComponents();
        banRepo = new BanRepository();
        ibanService = new BanService();
        iKhuVucService = new KhuVucService();
        khuVucRepo = new KhuVucRepository();
        taiKhoanAdmin = admin;
        taiKhoanNguoiDung = nguoiDung;
        if (taiKhoanAdmin != null) {
            cboChiNhanh.setVisible(true);
            comboChiNhanh = (DefaultComboBoxModel) new DefaultComboBoxModel<>(iKhuVucService.getAllChiNhanh().toArray());
            cboChiNhanh.setModel((DefaultComboBoxModel) comboChiNhanh);
            comboKhuVuc = (DefaultComboBoxModel) new DefaultComboBoxModel<>(
                    iKhuVucService.getAllKhuVucByChiNhanh(((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId()).toArray());
            loadData(((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId());
        } else {
            cboChiNhanh.setVisible(false);
            comboKhuVuc = (DefaultComboBoxModel) new DefaultComboBoxModel<>(
                    iKhuVucService.getAllKhuVucByChiNhanh(ibanService.getChiNhanhByTaiKhoan(nguoiDung.getId()).getId()).toArray());
            loadData(ibanService.getChiNhanhByTaiKhoan(nguoiDung.getId()).getId());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Ban = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoBan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBan = new javax.swing.JTable();
        btnThemBan = new javax.swing.JButton();
        cbKhuVuc = new javax.swing.JComboBox<>();
        makhuvuc = new javax.swing.JLabel();
        lblHienThi = new javax.swing.JLabel();
        btnXoaBan = new javax.swing.JButton();
        KhuVuc = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaKhuVuc = new javax.swing.JTextField();
        btnThemKhuVuc = new javax.swing.JButton();
        btnCapNhatKhuVuc = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhuVuc = new javax.swing.JTable();
        cbTrangThaiKhuVuc = new javax.swing.JComboBox<>();
        cboChiNhanh = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(225, 218, 197));

        Ban.setBackground(new java.awt.Color(225, 218, 197));
        Ban.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(108, 83, 54))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(108, 83, 54));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Số bàn :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(108, 83, 54));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Khu Vực :");

        txtSoBan.setBackground(new java.awt.Color(225, 218, 197));
        txtSoBan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        tblBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Số Bàn", "Khu Vực"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBan.getTableHeader().setReorderingAllowed(false);
        tblBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBan);
        if (tblBan.getColumnModel().getColumnCount() > 0) {
            tblBan.getColumnModel().getColumn(0).setMinWidth(0);
            tblBan.getColumnModel().getColumn(0).setMaxWidth(0);
            tblBan.getColumnModel().getColumn(1).setResizable(false);
            tblBan.getColumnModel().getColumn(2).setResizable(false);
        }

        btnThemBan.setBackground(new java.awt.Color(108, 83, 54));
        btnThemBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemBan.setForeground(new java.awt.Color(225, 218, 197));
        btnThemBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_20px.png"))); // NOI18N
        btnThemBan.setText("Thêm");
        btnThemBan.setBorderPainted(false);
        btnThemBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemBan.setFocusPainted(false);
        btnThemBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemBanActionPerformed(evt);
            }
        });

        cbKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKhuVucActionPerformed(evt);
            }
        });

        btnXoaBan.setBackground(new java.awt.Color(108, 83, 54));
        btnXoaBan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoaBan.setForeground(new java.awt.Color(225, 218, 197));
        btnXoaBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_30px.png"))); // NOI18N
        btnXoaBan.setText("Xóa");
        btnXoaBan.setBorderPainted(false);
        btnXoaBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaBan.setFocusPainted(false);
        btnXoaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BanLayout = new javax.swing.GroupLayout(Ban);
        Ban.setLayout(BanLayout);
        BanLayout.setHorizontalGroup(
            BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanLayout.createSequentialGroup()
                .addGroup(BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addGroup(BanLayout.createSequentialGroup()
                        .addGroup(BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoBan)
                            .addComponent(cbKhuVuc, 0, 180, Short.MAX_VALUE))
                        .addGap(137, 137, 137)
                        .addComponent(lblHienThi)
                        .addGap(18, 18, 18)
                        .addGroup(BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemBan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(makhuvuc)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        BanLayout.setVerticalGroup(
            BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanLayout.createSequentialGroup()
                .addGroup(BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BanLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnThemBan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaBan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(BanLayout.createSequentialGroup()
                        .addGroup(BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BanLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(txtSoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BanLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BanLayout.createSequentialGroup()
                                .addGap(0, 32, Short.MAX_VALUE)
                                .addGroup(BanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(makhuvuc)
                                    .addComponent(lblHienThi))
                                .addGap(26, 26, 26))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        KhuVuc.setBackground(new java.awt.Color(225, 218, 197));
        KhuVuc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12)), "Khu Vực", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(108, 83, 54))); // NOI18N
        KhuVuc.setForeground(new java.awt.Color(108, 83, 54));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(108, 83, 54));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Trạng Thái :");

        txtMaKhuVuc.setBackground(new java.awt.Color(225, 218, 197));
        txtMaKhuVuc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        btnThemKhuVuc.setBackground(new java.awt.Color(108, 83, 54));
        btnThemKhuVuc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemKhuVuc.setForeground(new java.awt.Color(225, 218, 197));
        btnThemKhuVuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_20px.png"))); // NOI18N
        btnThemKhuVuc.setText("Thêm");
        btnThemKhuVuc.setBorderPainted(false);
        btnThemKhuVuc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemKhuVuc.setFocusPainted(false);
        btnThemKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhuVucActionPerformed(evt);
            }
        });

        btnCapNhatKhuVuc.setBackground(new java.awt.Color(108, 83, 54));
        btnCapNhatKhuVuc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhatKhuVuc.setForeground(new java.awt.Color(225, 218, 197));
        btnCapNhatKhuVuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sync_30px.png"))); // NOI18N
        btnCapNhatKhuVuc.setText("Cập Nhật");
        btnCapNhatKhuVuc.setBorderPainted(false);
        btnCapNhatKhuVuc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapNhatKhuVuc.setFocusPainted(false);
        btnCapNhatKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatKhuVucActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(108, 83, 54));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Mã :");

        tblKhuVuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Mã", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuVuc.getTableHeader().setReorderingAllowed(false);
        tblKhuVuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuVucMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhuVuc);
        if (tblKhuVuc.getColumnModel().getColumnCount() > 0) {
            tblKhuVuc.getColumnModel().getColumn(0).setMinWidth(0);
            tblKhuVuc.getColumnModel().getColumn(0).setMaxWidth(0);
            tblKhuVuc.getColumnModel().getColumn(1).setResizable(false);
            tblKhuVuc.getColumnModel().getColumn(2).setResizable(false);
        }

        cbTrangThaiKhuVuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang hoạt động", "Dừng hoạt động" }));
        cbTrangThaiKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTrangThaiKhuVucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout KhuVucLayout = new javax.swing.GroupLayout(KhuVuc);
        KhuVuc.setLayout(KhuVucLayout);
        KhuVucLayout.setHorizontalGroup(
            KhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhuVucLayout.createSequentialGroup()
                .addGroup(KhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(KhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbTrangThaiKhuVuc, 0, 190, Short.MAX_VALUE)
                    .addComponent(txtMaKhuVuc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(KhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCapNhatKhuVuc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThemKhuVuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        );
        KhuVucLayout.setVerticalGroup(
            KhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhuVucLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(KhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(KhuVucLayout.createSequentialGroup()
                        .addGroup(KhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(KhuVucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTrangThaiKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(KhuVucLayout.createSequentialGroup()
                        .addComponent(btnThemKhuVuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhatKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cboChiNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChiNhanhActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/location_10px.png"))); // NOI18N
        jLabel4.setText("Chi nhánh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(KhuVuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(KhuVuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Ban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
      private void addComboKhuVucByChiNhah(String idChiNhanh) {
        listkvView = iKhuVucService.getAllKhuVucByChiNhanh(idChiNhanh);
        comboKhuVuc = (DefaultComboBoxModel) (new DefaultComboBoxModel<>(listkvView.toArray()));
        cbKhuVuc.setModel((DefaultComboBoxModel) comboKhuVuc);
    }
    
    private void load_KhuVuc_By_ChiNhanh(List<KhuVucViewModel> list) {
        defaultTableModel = (DefaultTableModel) tblKhuVuc.getModel();
        defaultTableModel.setRowCount(0);
        for (KhuVucViewModel x : list) {
            defaultTableModel.addRow(x.getDataKhuVuc());
        }
    }
    
    private void load_Ban_By_KhuVuc(List<BanViewModel> list) {
        defaultTableModel = (DefaultTableModel) tblBan.getModel();
        defaultTableModel.setRowCount(0);
        for (BanViewModel x : list) {
            defaultTableModel.addRow(x.getDataBan());
        }
        
    }
    
    private void loadData(String idChiNhanh) {
        load_KhuVuc_By_ChiNhanh(iKhuVucService.getAllKhuVucByChiNhanh(idChiNhanh));
        addComboKhuVucByChiNhah(idChiNhanh);
        if (cbKhuVuc.getItemCount() > 0) {
            load_Ban_By_KhuVuc(ibanService.getAllBanByKhuVuc(((KhuVucViewModel) comboKhuVuc.getSelectedItem()).getIdKhuVuc()));
        } else {
            defaultTableModel = (DefaultTableModel) tblBan.getModel();
            defaultTableModel.setRowCount(0);
        }
        
    }
    
    private void fillDataToFormBan(int row) {
        txtSoBan.setText(tblBan.getValueAt(row, 1).toString());
        comboKhuVuc.setSelectedItem((findbyName(tblBan.getModel().getValueAt(row, 2).toString())));
    }
    
    private KhuVucViewModel findbyName(String MaKV) {
        for (int i = 0; i < comboKhuVuc.getSize(); i++) {
            KhuVucViewModel kv = comboKhuVuc.getElementAt(i);
            if (MaKV.equalsIgnoreCase(kv.getMakhuvuc())) {
                return kv;
            }
        }
        return null;
    }
    
    private void fillDataToFormKhuVuc(int row) {
        txtMaKhuVuc.setText(tblKhuVuc.getValueAt(row, 1).toString());
        if (tblKhuVuc.getValueAt(row, 2).toString().equalsIgnoreCase("Dừng hoạt động")) {
            cbTrangThaiKhuVuc.setSelectedIndex(1);
        } else {
            cbTrangThaiKhuVuc.setSelectedIndex(0);
        }
        
    }
    

    private void btnThemKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhuVucActionPerformed
        if (checkFormEmptyBan(txtMaKhuVuc) && checkVuotquakituKhuVuc(txtMaKhuVuc.getText())
                && checkMaKhuVuc(txtMaKhuVuc.getText())) {
            if (taiKhoanAdmin != null) {
                iKhuVucService.insertKhuVucToChiNhanh(txtMaKhuVuc.getText(),
                        ((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId());
                JOptionPane.showMessageDialog(this, "Thêm Khu Vực thành công");
                addComboKhuVucByChiNhah(((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId());
                load_KhuVuc_By_ChiNhanh(iKhuVucService.getAllKhuVucByChiNhanh(
                        ((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId()));
            } else {
                iKhuVucService.insertKhuVucToChiNhanh(txtMaKhuVuc.getText(),
                        ibanService.getChiNhanhByTaiKhoan(taiKhoanNguoiDung.getId()).getId());
                JOptionPane.showMessageDialog(this, "Thêm Khu Vực thành công");
                addComboKhuVucByChiNhah(
                        ((ChiNhanhViewModel_Hoang) ibanService.getChiNhanhByTaiKhoan(taiKhoanNguoiDung.getId())).getId());
                load_KhuVuc_By_ChiNhanh(iKhuVucService.getAllKhuVucByChiNhanh(
                        ((ChiNhanhViewModel_Hoang) ibanService.getChiNhanhByTaiKhoan(taiKhoanNguoiDung.getId())).getId()));
                
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Thêm khu vực thất bại");
        }

    }//GEN-LAST:event_btnThemKhuVucActionPerformed

    private void btnCapNhatKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatKhuVucActionPerformed
        int row = tblKhuVuc.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khu vực trên bảng");
        } else {
            if (checkFormEmpty1(txtMaKhuVuc)) {
                KhuVucViewModel khuVucViewModel = new KhuVucViewModel();
                khuVucViewModel.setIdKhuVuc(tblKhuVuc.getValueAt(row, 0).toString());
                khuVucViewModel.setMakhuvuc(tblKhuVuc.getValueAt(row, 1).toString());
//                if (cbTrangThaiKhuVuc.getSelectedIndex() == 1) {
//                    khuVucViewModel.setTrangthai(0);
//                } else {
//                    khuVucViewModel.setTrangthai(1);
//                }
                iKhuVucService.updateKhuVuc(khuVucViewModel, txtMaKhuVuc.getText(),cbTrangThaiKhuVuc.getSelectedIndex() == 1 ? 0 : 1);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công  ");
                if (taiKhoanAdmin != null) {
                    load_KhuVuc_By_ChiNhanh(iKhuVucService.getAllKhuVucByChiNhanh(
                            ((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId()));
                } else {
                    load_KhuVuc_By_ChiNhanh(iKhuVucService.getAllKhuVucByChiNhanh(
                            ((ChiNhanhViewModel_Hoang) ibanService.getChiNhanhByTaiKhoan(taiKhoanNguoiDung.getId())).getId()));
                }
                
            }
        }
    }//GEN-LAST:event_btnCapNhatKhuVucActionPerformed

    private void btnThemBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemBanActionPerformed
        if (checkFormEmptyBan(txtSoBan) && checkNumber(txtSoBan.getText())
                && checkVuotquakituBan(txtSoBan.getText()) && checkSoBan(Integer.parseInt(txtSoBan.getText()))) {
            KhuVucViewModel kvView = (KhuVucViewModel) comboKhuVuc.getSelectedItem();
            ibanService.insertBan(Integer.parseInt(txtSoBan.getText()), kvView);
            JOptionPane.showMessageDialog(this, "Thêm Bàn thành công");
            load_Ban_By_KhuVuc(ibanService.getAllBanByKhuVuc(((KhuVucViewModel) comboKhuVuc.getSelectedItem()).getIdKhuVuc()));
        }
    }//GEN-LAST:event_btnThemBanActionPerformed

    private void cbKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKhuVucActionPerformed
        load_Ban_By_KhuVuc(ibanService.getAllBanByKhuVuc(((KhuVucViewModel) comboKhuVuc.getSelectedItem()).getIdKhuVuc()));
    }//GEN-LAST:event_cbKhuVucActionPerformed

    private void cbTrangThaiKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTrangThaiKhuVucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTrangThaiKhuVucActionPerformed

    private void tblBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBanMouseClicked
        int row = tblBan.getSelectedRow();
        fillDataToFormBan(row);
    }//GEN-LAST:event_tblBanMouseClicked

    private void tblKhuVucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuVucMouseClicked
        int row = tblKhuVuc.getSelectedRow();
        fillDataToFormKhuVuc(row);
    }//GEN-LAST:event_tblKhuVucMouseClicked

    private void btnXoaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaBanActionPerformed
        int row = tblBan.getSelectedRow();
        ibanService.deleteBan(tblBan.getValueAt(row, 0).toString());
        JOptionPane.showMessageDialog(this, "Xoa Thành Công");
        load_Ban_By_KhuVuc(ibanService.getAllBanByKhuVuc(((KhuVucViewModel) comboKhuVuc.getSelectedItem()).getIdKhuVuc()));
    }//GEN-LAST:event_btnXoaBanActionPerformed

    private void cboChiNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChiNhanhActionPerformed
        comboKhuVuc = (DefaultComboBoxModel) new DefaultComboBoxModel<>(
                iKhuVucService.getAllKhuVucByChiNhanh(((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId()).toArray());
        loadData(((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId());
    }//GEN-LAST:event_cboChiNhanhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Ban;
    private javax.swing.JPanel KhuVuc;
    private javax.swing.JButton btnCapNhatKhuVuc;
    private javax.swing.JButton btnThemBan;
    private javax.swing.JButton btnThemKhuVuc;
    private javax.swing.JButton btnXoaBan;
    private javax.swing.JComboBox<KhuVucViewModel> cbKhuVuc;
    private javax.swing.JComboBox<String> cbTrangThaiKhuVuc;
    private javax.swing.JComboBox<String> cboChiNhanh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHienThi;
    private javax.swing.JLabel makhuvuc;
    private javax.swing.JTable tblBan;
    private javax.swing.JTable tblKhuVuc;
    private javax.swing.JTextField txtMaKhuVuc;
    private javax.swing.JTextField txtSoBan;
    // End of variables declaration//GEN-END:variables
private boolean checkFormEmptyBan(JTextField soban) {
        if (soban.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được trống");
            return false;
        } else {
            return true;
        }
    }
    
    private boolean checkNumber(String num) {
        Pattern regexInt = Pattern.compile("^[0-9]+$");
        if (!regexInt.matcher(num).find()) {
            JOptionPane.showMessageDialog(this, "Số không hợp lệ(0-9)", "Number Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    
    private boolean checkVuotquakituBan(String soban) {
        Pattern regex = Pattern.compile("^\\w{1,5}+$");
        if (!regex.matcher(soban).find()) {
            JOptionPane.showMessageDialog(this, "Tối đa 5 kí tự", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    
    private boolean checkVuotquakituKhuVuc(String makv) {
        Pattern regex = Pattern.compile("^\\w{1,5}+$");
        if (!regex.matcher(makv).find()) {
            JOptionPane.showMessageDialog(this, "Tối đa 5 kí tự", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    
    private boolean checkFormEmpty1(JTextField ma) {
        if (ma.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được trống");
            return false;
        } else {
            return true;
        }
    }
    
    private boolean checkSoBan(Integer soBan) {
        domainmodel.Ban ban = banRepo.getBanFormSoBan(soBan);
        if (ban != null) {
            JOptionPane.showMessageDialog(this, "Số bàn này đã tồn tại");
            return false;
        }
        return true;
    }
    
    private boolean checkMaKhuVuc(String maKhuVuc) {
        domainmodel.KhuVuc khuVuc = khuVucRepo.getKhuVucFromMa(maKhuVuc);
        if (khuVuc != null) {
            JOptionPane.showMessageDialog(this, "Mã khu vực này đã tồn tại");
            return false;
        }
        return true;
    }
}
