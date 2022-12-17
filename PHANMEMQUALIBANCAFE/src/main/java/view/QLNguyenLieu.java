/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import domainmodel.ChiNhanh;
import domainmodel.NguyenLieu;
import domainmodel.NhanVien;
import domainmodel.PhieuKiemKe;
import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.IChiNhanh;
import service.IChiTietPhieuKiemKe;
import service.INguyenLieu;
import service.IPhieuKiemKe;
import service.implement.ChiNhanhSevice;
import service.implement.ChiTietPhieuKiemKeService;
import service.implement.NguyenLieuService;
import service.implement.PhieuKiemKeService;
import viewmodel.ChiNhanhVM_Long;
import viewmodel.ChiNhanhViewModel_Hoang;
import viewmodel.ChiNhanhViewModel_Long;
import viewmodel.ChiTietPhieuKiemKeViewModel_Long;
import viewmodel.NguyenLieuDangSuDung;
import viewmodel.NguyenLieuViewModel_Long;
import viewmodel.NhanVienViewModel_Hoang;

import viewmodel.PhieuKiemKeViewModel_Long;

/**
 *
 * @author trant
 */
public class QLNguyenLieu extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private List<NguyenLieuViewModel_Long> lstnl = new ArrayList<>();
    private INguyenLieu nguyenlieuS = new NguyenLieuService();

    private DefaultTableModel dtm1 = new DefaultTableModel();
    private List<ChiTietPhieuKiemKeViewModel_Long> lstChitietPhieuKiemKe = new ArrayList<>();
    private IChiTietPhieuKiemKe chiTietPKKS = new ChiTietPhieuKiemKeService();

    private DefaultTableModel dtm2 = new DefaultTableModel();
    private List<PhieuKiemKeViewModel_Long> lstPKK = new ArrayList<>();
    private IPhieuKiemKe pKKeSevice = new PhieuKiemKeService();

    private DefaultComboBoxModel<PhieuKiemKeViewModel_Long> modelComBoNguyenLieu;
    private DefaultComboBoxModel<ChiNhanhVM_Long> modelComBoChiNhanh;
    private DefaultComboBoxModel<NguyenLieuViewModel_Long> modelComBoNguyenlieu1;
    private IChiNhanh cnS = new ChiNhanhSevice();
private DefaultComboBoxModel<PhieuKiemKeViewModel_Long> modelComBoNguyenLieu2;
    private DefaultComboBoxModel<NhanVienViewModel_Hoang> modelComBoNhanVien;

    private List<ChiNhanhVM_Long> lstCN = new ArrayList<>();
    
    
     private DefaultTableModel dtmHT = new DefaultTableModel();
//    
//     private DefaultComboBoxModel<NhanVienViewModel_Hoang> modelComBoNhanVien;

    /**
     * Creates new form QLNguyenLieu
     */
    public QLNguyenLieu(TaiKhoanAdmin admin, TaiKhoanNguoiDung nguoiDung) {
        initComponents();
        tblBang.setModel(dtm);
        String[] header = {"ID", "Mã", "Tên", "Số Lượng Tồn", "Hạn Sử Dụng", "Đơn Vị Tính"};
        dtm.setColumnIdentifiers(header);
        tblBang.getColumnModel().getColumn(0).setMinWidth(0);
        tblBang.getColumnModel().getColumn(0).setMaxWidth(0);
        lstnl = nguyenlieuS.getAll();
        
        
         tblBang1.setModel(dtmHT);
        String[] headerHT = {"ID", "Mã", "Tên", "Số Lượng Tồn", "Hạn Sử Dụng", "Đơn Vị Tính"};
        dtmHT.setColumnIdentifiers(header);
        tblBang1.getColumnModel().getColumn(0).setMinWidth(0);
        tblBang1.getColumnModel().getColumn(0).setMaxWidth(0);
        lstnl = nguyenlieuS.getAll();

        tblBangCTKK.setModel(dtm1);
        String[] hearder = {"Số Lượng Tồn", "Số Lượng Thực Tế", "Chênh Lệch", "Lí Do", "Phiếu Kiểm kê","Mã Nguyên Liệu"};
        dtm1.setColumnIdentifiers(hearder);
//           tblBangCTKK.getColumnModel().getColumn(0).setMinWidth(0);
//        tblBangCTKK.getColumnModel().getColumn(0).setMaxWidth(0);
        lstChitietPhieuKiemKe = chiTietPKKS.getAllChiTietHoaDon();

        tblBangPkk.setModel(dtm2);
        String[] hearderKK = {"id", "Mã", "Ngaỳ Kiểm Kê", "Trạng Thái", "Nhân Viên"};
        dtm2.setColumnIdentifiers(hearderKK);
            tblBangPkk.getColumnModel().getColumn(0).setMinWidth(0);
        tblBangPkk.getColumnModel().getColumn(0).setMaxWidth(0);
        lstPKK = pKKeSevice.getAllPKK();

//        lstChiNhanhVM = cnS.getAll();
        modelComBoNguyenLieu = (DefaultComboBoxModel) new DefaultComboBoxModel<>(pKKeSevice.getAllPKKcbb().toArray());
        cbbpkk.setModel((DefaultComboBoxModel) modelComBoNguyenLieu);

        modelComBoChiNhanh = (DefaultComboBoxModel) new DefaultComboBoxModel<>(cnS.getAllConHD().toArray());
        cbbCN.setModel((DefaultComboBoxModel) modelComBoChiNhanh);

        modelComBoNguyenlieu1 = (DefaultComboBoxModel) new DefaultComboBoxModel<>(nguyenlieuS.getAll().toArray());
        cbbNL.setModel((DefaultComboBoxModel) modelComBoNguyenlieu1);

        modelComBoNhanVien = (DefaultComboBoxModel) new DefaultComboBoxModel<>(pKKeSevice.getAllNV().toArray());
        cbbNV.setModel((DefaultComboBoxModel) modelComBoNhanVien);
        
        
        modelComBoChiNhanh = (DefaultComboBoxModel) new DefaultComboBoxModel<>(cnS.getAllConHD().toArray());
        cbbCNPKK.setModel((DefaultComboBoxModel) modelComBoChiNhanh);

        show(lstnl);
        ShowKK(lstPKK);
        ShowCTKK(lstChitietPhieuKiemKe);
        show1(lstnl);

    }

    private void ShowKK(List<PhieuKiemKeViewModel_Long> lst) {
        dtm2.setRowCount(0);
        for (PhieuKiemKeViewModel_Long pk : lst) {
            dtm2.addRow(new Object[]{pk.getId(), pk.getMa(), pk.getNgayKiemKe(), pk.getTrangThai() == 1 ? "Xác Nhận" : "Tạm", pk.getMaNhanVien()});
        }
    }

    private void ShowCTKK(List<ChiTietPhieuKiemKeViewModel_Long> lstct) {
        dtm1.setRowCount(0);
        for (ChiTietPhieuKiemKeViewModel_Long ct : lstct) {
            dtm1.addRow(new Object[]{ct.getSoLuongTon(), ct.getSoLuongChenhlech(), ct.getSoLuongTon() - ct.getSoLuongChenhlech(), ct.getLiDo(), ct.getIdPhieuKiem(), ct.getIdNguyenLieu()});
        }
    }

    private void show(List<NguyenLieuViewModel_Long> lst) {
        dtm.setRowCount(0);
        for (NguyenLieuViewModel_Long nl : lst) {
            dtm.addRow(new Object[]{nl.getId(),nl.getMa(),nl.getTen(),nl.getSoLuongTon(),nl.getHanSuDung(),nl.getDonVitinh()});
        }
    }

    private boolean checkMaSp(String maSp) {
        if (nguyenlieuS.getChiNhanh(maSp) != null) {
            JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
            return false;
        } else {
            return true;
        }
    }
    
     private void show1(List<NguyenLieuViewModel_Long> lst) {
        dtmHT.setRowCount(0);
        for (NguyenLieuViewModel_Long nl : lst) {
            dtmHT.addRow(new Object[]{nl.getId(),nl.getMa(),nl.getTen(),nl.getSoLuongTon(),nl.getHanSuDung(),nl.getDonVitinh()});
        }
    }
    private boolean checkFormEmpty(JTextField ma, JTextField ten) {
        if (ma.getText().isBlank() || ten.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được trống");
            return false;
        } else {
            return true;
        }
    }

    public void fill(int index, List<NguyenLieuViewModel_Long> lst) {
        NguyenLieuViewModel_Long nl = lst.get(index);
        txtMa.setText(nl.getMa());
        txtTen.setText(nl.getTen());
        txtDVT.setText(nl.getDonVitinh());
        txtSLT.setText(String.valueOf(nl.getSoLuongTon()));
        txtHSD.setDate(nl.getHanSuDung());
//        txtHSD.setText(String.valueOf(nl.getHanSuDung()));

    }
    
     public boolean checkChu(String mark) {
        Pattern regexInt = Pattern.compile("[a-zA-Z]+");
        Pattern regexDouble = Pattern.compile("[a-zA-Z]+");
        if (!regexInt.matcher(mark).find() && !regexDouble.matcher(mark).find()) {
            JOptionPane.showMessageDialog(this, "Nhập chữ");
            return false;
        } else {
            return true;
        }
    }
    
     public void fillHT(int index, List<NguyenLieuViewModel_Long> lst) {
        NguyenLieuViewModel_Long nl = lst.get(index);
        txtMa.setText(nl.getMa());
        txtTen.setText(nl.getTen());
        txtDVT.setText(nl.getDonVitinh());
        txtSLT.setText(String.valueOf(nl.getSoLuongTon()));
//        txtHSD.setText(String.valueOf(nl.getHanSuDung()));

    }
    
     private boolean checkFormEmptyCTPhieu(JTextField ma, JTextArea ten) {
        if (ma.getText().isBlank() || ten.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được trống");
            return false;
        } else {
            return true;
        }
    }

    public void fillPKK(int indexPkk, List<PhieuKiemKeViewModel_Long> lst) {
        PhieuKiemKeViewModel_Long pkk = lst.get(indexPkk);
        txtMaPKK.setText(pkk.getMa());
        txtNgayPkk.setDate(pkk.getNgayKiemKe());
        
//        txtNgayPKK.setText(String.valueOf(pkk.getNgayKiemKe()));
    }

    private void loadCBBPKK(List<ChiTietPhieuKiemKeViewModel_Long> lst) {
        dtm1 = (DefaultTableModel) tblBangCTKK.getModel();
        dtm1.setRowCount(0);
        for (ChiTietPhieuKiemKeViewModel_Long x : lst) {
            dtm1.addRow(new Object[]{x.getIdNguyenLieu(), x.getIdPhieuKiem(), x.getSoLuong(), x.getSoLuongChenhlech(), x.getSoLuongThucTe(), x.getLiDo(), x.getSoLuongTon()});
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDVT = new javax.swing.JTextField();
        txtSLT = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbbCN = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        txtHSD = new com.toedter.calendar.JDateChooser();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBang1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSLTT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLiDo = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBangCTKK = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbbpkk = new javax.swing.JComboBox<>();
        cbbNL = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaPKK = new javax.swing.JTextField();
        rdoOK = new javax.swing.JRadioButton();
        rdoNo = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBangPkk = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        cbbNV = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtNgayPkk = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        cbbCNPKK = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        lblTT = new javax.swing.JLabel();

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel2.setBackground(new java.awt.Color(225, 218, 197));

        jLabel3.setText("ID");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("Tên");

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBang);

        jButton1.setBackground(new java.awt.Color(108, 83, 54));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Plus_48px.png"))); // NOI18N
        jButton1.setText("THÊM");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(108, 83, 54));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/available_updates_48px.png"))); // NOI18N
        jButton2.setText("SỬA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Mã");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setText("Hạn Sử Dụng");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setText("Đơn Vị Tính");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setText("Số Lượng Tồn");

        txtDVT.setBackground(new java.awt.Color(225, 218, 197));
        txtDVT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtDVT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDVTActionPerformed(evt);
            }
        });

        txtSLT.setBackground(new java.awt.Color(225, 218, 197));
        txtSLT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtSLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLTActionPerformed(evt);
            }
        });

        txtTen.setBackground(new java.awt.Color(225, 218, 197));
        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtMa.setBackground(new java.awt.Color(225, 218, 197));
        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setText("QUẢN LÝ NGUYÊN LIỆU");

        cbbCN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCNActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setText("Chi Nhánh");

        txtHSD.setDateFormatString("dd-MM-yyyy");
        txtHSD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtHSD.setIcon(null);

        tblBang1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBang1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblBang1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Bảng Nguyên Liệu ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Bảng Nguyên Liệu Theo Chi Nhánh");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(446, 446, 446)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel16)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(97, 97, 97)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel14))
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel15)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSLT, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(cbbCN, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHSD, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addGap(847, 847, 847))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel9)))
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSLT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtHSD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(cbbCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(357, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(305, 305, 305))))
        );

        jTabbedPane1.addTab("Nguyên Liệu", jPanel2);

        jPanel1.setBackground(new java.awt.Color(225, 218, 197));

        jLabel4.setText("Số Lượng Thực Tế");

        jLabel8.setText("Lí Do");

        txtLiDo.setColumns(20);
        txtLiDo.setRows(5);
        jScrollPane2.setViewportView(txtLiDo);

        jButton4.setBackground(new java.awt.Color(108, 83, 54));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Plus_48px.png"))); // NOI18N
        jButton4.setText("Thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tblBangCTKK.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblBangCTKK);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("Chi Tiết Phiếu");

        cbbpkk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbpkk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbpkkActionPerformed(evt);
            }
        });

        cbbNL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbNL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNLItemStateChanged(evt);
            }
        });

        jLabel17.setText("Nguyên Liệu");

        jLabel18.setText("Phiếu Kiểm Kê");

        jPanel3.setBackground(new java.awt.Color(225, 218, 197));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setText("Phiếu Kiểm Kê");

        jLabel11.setText("Mã");

        jLabel12.setText("Ngày Kiểm Kê");

        jLabel13.setText("Trạng Thái");

        buttonGroup1.add(rdoOK);
        rdoOK.setText("Xác Nhận");

        buttonGroup1.add(rdoNo);
        rdoNo.setText("Tạm");

        tblBangPkk.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBangPkk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangPkkMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblBangPkk);

        jButton5.setBackground(new java.awt.Color(108, 83, 54));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_20px.png"))); // NOI18N
        jButton5.setText("Thêm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        cbbNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton6.setBackground(new java.awt.Color(108, 83, 54));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_30px.png"))); // NOI18N
        jButton6.setText("Xóa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel19.setText("Nhân Viên");

        txtNgayPkk.setDateFormatString("dd-MM-yyyy");
        txtNgayPkk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNgayPkk.setIcon(null);

        jButton3.setBackground(new java.awt.Color(108, 83, 54));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/replay_20px.png"))); // NOI18N
        jButton3.setText("Sửa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 208, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(172, 172, 172))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel13))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel11)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(rdoOK)
                                    .addGap(27, 27, 27)
                                    .addComponent(rdoNo))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(cbbNV, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(75, 75, 75)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNgayPkk, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addComponent(txtMaPKK, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaPKK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addComponent(txtNgayPkk, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoOK)
                        .addComponent(rdoNo)
                        .addComponent(jLabel13))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbbNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton7.setBackground(new java.awt.Color(108, 83, 54));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_microsoft_excel_30px.png"))); // NOI18N
        jButton7.setText("Xuất Excel");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        cbbCNPKK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbCNPKK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCNPKKActionPerformed(evt);
            }
        });

        jLabel21.setText("Chi Nhánh");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbCNPKK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbNL, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbbpkk, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtSLTT, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(43, 43, 43)
                                                .addComponent(jLabel8)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(219, 219, 219)
                                        .addComponent(jLabel2))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTT)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(678, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbCNPKK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(txtSLTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel18)
                                            .addComponent(cbbpkk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbbNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTT, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 231, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phiếu kiểm kê", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2001, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        int row = tblBang.getSelectedRow();
        fill(row, lstnl);

    }//GEN-LAST:event_tblBangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (checkFormEmpty(txtMa, txtDVT) && checkVuotquakituKhuVuc(txtMa.getText()) && checkChu(txtTen.getText()) && checkMaSp(txtMa.getText())) {
//            String ngS = txtHSD.getText();
//            java.sql.Date ng = java.sql.Date.valueOf(ngS);

            NguyenLieu nl = new NguyenLieu();
            nl.setMa(txtMa.getText());
            nl.setSoLuongTon(Float.valueOf(txtSLT.getText()));
            nl.setDonViTinh(txtDVT.getText());
            
//            nl.setHanSuDung(ng);
            ChiNhanhVM_Long cn1 = new ChiNhanhVM_Long();
            ChiNhanhVM_Long cn = (ChiNhanhVM_Long) cbbCN.getSelectedItem();

            nguyenlieuS.insertNguyenLieu(txtMa.getText(), txtTen.getText(), txtHSD.getDate(), txtDVT.getText(), Float.valueOf(txtSLT.getText()), String.valueOf(cn.getId()));
            modelComBoNguyenlieu1 = (DefaultComboBoxModel) new DefaultComboBoxModel<>(nguyenlieuS.getAll().toArray());
            cbbNL.setModel((DefaultComboBoxModel) modelComBoNguyenlieu1);

            modelComBoChiNhanh = (DefaultComboBoxModel) new DefaultComboBoxModel<>(cnS.getAll().toArray());
            cbbCN.setModel((DefaultComboBoxModel) modelComBoChiNhanh);

            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadCBBPKK(chiTietPKKS.getCYPKKbyPKK(((PhieuKiemKeViewModel_Long) cbbpkk.getSelectedItem()).getId()));
            lstnl = nguyenlieuS.getAll();
            
            show(lstnl);
              show1(lstnl);

        } else {
           
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int row = tblBang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng click vào Bảng");
        } else {
            if (checkFormEmpty(txtMa, txtTen)) {
                if (txtMa.getText().equals(tblBang.getValueAt(row, 1).toString())) {
//                    String ngS = txtHSD.getText();
//                    java.sql.Date ng = java.sql.Date.valueOf(ngS);
//                    nguyenlieuS.update(tblBang.getValueAt(row, 0).toString(), txtMa.getText(), txtTen.getText(), txtDVT.getText()instanceof , txtSLT.getText(), ng);
                    nguyenlieuS.update(tblBang.getValueAt(row, 0).toString(), txtMa.getText(), txtTen.getText(), txtDVT.getText(), Float.valueOf(txtSLT.getText()), txtHSD.getDate());
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    show(nguyenlieuS.getAll());
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbbCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCNActionPerformed
    
        dtmHT.setRowCount(0);

        modelComBoNguyenLieu = (DefaultComboBoxModel) new DefaultComboBoxModel<>(
                nguyenlieuS.getAllNguyenLieuByChiNhanh(((ChiNhanhVM_Long) modelComBoChiNhanh.getSelectedItem()).getId()).toArray());
//                cbbNguyenLieu.setModel((DefaultComboBoxModel) modelComBoNguyenLieu); 
//        cbbNguyenLieu.setModel((DefaultComboBoxModel) modelComBoNguyenLieu); 
// loadTablePhieuNhap(phieuNhapSevice.getAllPhieuNhapByChiNhanh(((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId()));
        ChiNhanhVM_Long cn = (ChiNhanhVM_Long) cbbCN.getSelectedItem();
        show1(nguyenlieuS.getAllNguyenLieuByChiNhanh(cn.getId()));
      

// TODO add your handling code here:
    }//GEN-LAST:event_cbbCNActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
if(checkFormEmptyCTPhieu(txtSLTT, txtLiDo)){
        PhieuKiemKeViewModel_Long pk = (PhieuKiemKeViewModel_Long) cbbpkk.getSelectedItem();
        NguyenLieuViewModel_Long nl = (NguyenLieuViewModel_Long) cbbNL.getSelectedItem();
        chiTietPKKS.insertNguyenLieu(Float.valueOf(txtSLTT.getText()), txtLiDo.getText(), String.valueOf(nl.getId()), String.valueOf(pk.getId()));
        JOptionPane.showMessageDialog(this, "Thêm thành công");
        lstChitietPhieuKiemKe = chiTietPKKS.getAllChiTietHoaDon();
        ShowCTKK(lstChitietPhieuKiemKe);
        // TODO add your handling code here:
}else{
      
}
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbbpkkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbpkkActionPerformed

//        loadCBBPKK(chiTietPKKS.getCYPKKbyPKK(cbbpkk.getSelectedItem()));
        //                 int count = 0;
        //        if (cbbpkk.getItemCount() <= 0) {
        //            JOptionPane.showMessageDialog(this, "Vui lòng bổ sung nguyên liệu");
        //        } else {
        //            NguyenLieuDangSuDung nguyenLieu = (NguyenLieuDangSuDung) modelComBoNguyenLieu.getSelectedItem();
        //            for (int i = 0; i < tblDinhLuong.getRowCount(); i++) {
        //                if (nguyenLieu.getId().equalsIgnoreCase(tblDinhLuong.getValueAt(i, 0).toString())) {
        //                    count++;
        //                    break;
        //                }
        //            }
        //            if (count == 0) {
        //                modelTableDinhLuong.addRow(new Object[]{nguyenLieu.getId(), nguyenLieu.getMa(), nguyenLieu.getTen(), 1});
        //            }
        //        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbpkkActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     
//            String ngkkS = txtNgayPKK.getText();
//            java.sql.Date ngkk = java.sql.Date.valueOf(ngkkS);
//            pKKeSevice.insertBan(txtMaPKK.getText(), ngkk, 1);




            NhanVienViewModel_Hoang nv = (NhanVienViewModel_Hoang) cbbNV.getSelectedItem();

            int trangThai;
            if (rdoOK.isSelected()) {
                trangThai = 1;
            } else {
                trangThai = 0;
            }
            pKKeSevice.insertPKK(txtMaPKK.getText(), txtNgayPkk.getDate(), trangThai, nv);
            modelComBoNguyenLieu = (DefaultComboBoxModel) new DefaultComboBoxModel<>(pKKeSevice.getAllPKK().toArray());
            cbbpkk.setModel((DefaultComboBoxModel) modelComBoNguyenLieu);
            modelComBoNguyenlieu1 = (DefaultComboBoxModel) new DefaultComboBoxModel<>(nguyenlieuS.getAll().toArray());
            cbbNL.setModel((DefaultComboBoxModel) modelComBoNguyenlieu1);
            JOptionPane.showMessageDialog(this, "Thêm thành công");

//             addCbKhuVucByChiNhah(((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId());
//            load_KhuVuc(iKhuVucService.getAllKhuVucByChiNhanh(((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId()));
//addCbKhuVucByChiNhah(modelComBoNguyenLieu.getSelectedItem().get)
//   loadCBBPKK(chiTietPKKS.getCYPKKbyPKK(((PhieuKiemKeViewModel_Long) cbbpkk.getSelectedItem()).getId()));
            lstPKK = pKKeSevice.getAllPKK();
            ShowKK(lstPKK);

       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int row1 = tblBangPkk.getSelectedRow();
        pKKeSevice.deletePKK(tblBangPkk.getValueAt(row1, 0).toString());
        modelComBoNguyenLieu = (DefaultComboBoxModel) new DefaultComboBoxModel<>(pKKeSevice.getAllPKK().toArray());
        cbbpkk.setModel((DefaultComboBoxModel) modelComBoNguyenLieu);
        JOptionPane.showMessageDialog(this, "Xoá tc");

        lstPKK = pKKeSevice.getAllPKK();
        lstChitietPhieuKiemKe = chiTietPKKS.getAllChiTietHoaDon();
        ShowKK(lstPKK);
        ShowCTKK(lstChitietPhieuKiemKe);
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tblBangPkkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangPkkMouseClicked
        int row1 = tblBangPkk.getSelectedRow();
        fillPKK(row1, lstPKK);// TODO add your handling code here:
    }//GEN-LAST:event_tblBangPkkMouseClicked

    private void txtDVTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDVTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDVTActionPerformed

    private void txtSLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLTActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
 FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelJTableExporter = null;
        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\PC\\Desktop\\Test");
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChosser = excelFileChooser.showSaveDialog(null);
        if (excelChosser == JFileChooser.APPROVE_OPTION) {

            try {
                excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");
                for (int i = 0; i < dtm1.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i);
                    for (int j = 0; j < dtm1.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(dtm1.getValueAt(i, j).toString());
                    }
                }
                excelFOU = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFOU);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(this, "Exported");
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            } finally {
                try {
                    if (excelBOU != null) {
                        excelBOU.close();
                    }
                    if (excelFOU != null) {
                        excelFOU.close();
                    }
                    if (excelJTableExporter != null) {
                        excelJTableExporter.close();
                    }
                } catch (IOException ex) {
                }
            }

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tblBang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBang1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBang1MouseClicked

    private void cbbCNPKKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCNPKKActionPerformed

//        int row = tblBang1.getSelectedRow();
      

   
        dtmHT.setRowCount(0);

        modelComBoNguyenLieu2 = (DefaultComboBoxModel) new DefaultComboBoxModel<>(
                nguyenlieuS.getAllNguyenLieuByChiNhanh(((ChiNhanhVM_Long) modelComBoChiNhanh.getSelectedItem()).getId()).toArray());
        cbbNL.setModel((DefaultComboBoxModel) modelComBoNguyenLieu2); 
//                cbbNguyenLieu.setModel((DefaultComboBoxModel) modelComBoNguyenLieu); 
//        cbbNguyenLieu.setModel((DefaultComboBoxModel) modelComBoNguyenLieu); 
// loadTablePhieuNhap(phieuNhapSevice.getAllPhieuNhapByChiNhanh(((ChiNhanhViewModel_Hoang) comboChiNhanh.getSelectedItem()).getId()));
        ChiNhanhVM_Long cn = (ChiNhanhVM_Long) cbbCN.getSelectedItem();
        show1(nguyenlieuS.getAllNguyenLieuByChiNhanh(cn.getId()));
     
       
      
      
       
            // TODO add your handling code here:
    }//GEN-LAST:event_cbbCNPKKActionPerformed

    private void cbbNLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNLItemStateChanged
  for (NguyenLieuViewModel_Long nvView : nguyenlieuS.getAll()) {
            if (nvView.getTen().equals(cbbNL.getSelectedItem().toString())) {
//                lblHienThi.setText(nvView.getHoTen());
                lblTT.setText(String.valueOf(nvView.getSoLuongTon()));
                break;
            }
            
        }          // TODO add your handling code here:
    }//GEN-LAST:event_cbbNLItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 int row = tblBangPkk.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng click vào Bảng");
        } else {
            
                if (txtMaPKK.getText().equals(tblBangPkk.getValueAt(row, 1).toString())) {
                    PhieuKiemKe mauView = new PhieuKiemKe();
                    mauView.setMa(tblBangPkk.getValueAt(row, 1).toString());
//                    String ngS = txtNgayKC.getText();
//            Date ng = Date.valueOf(ngS);
                    int trangThai;
            if (rdoOK.isSelected()) {
                trangThai = 1;
            } else {
                trangThai = 0;
            }
//                    chiNhanhS.update(mauView, txtMa.getText(), txtThanhPho.getText(), txtQuocGia.getText(), trangThai, txtNgayKC.getDate());
pKKeSevice.update(mauView, txtMaPKK.getText(), trangThai, txtNgayPkk.getDate());
    modelComBoNguyenLieu = (DefaultComboBoxModel) new DefaultComboBoxModel<>(pKKeSevice.getAllPKKcbb().toArray());
        cbbpkk.setModel((DefaultComboBoxModel) modelComBoNguyenLieu);
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    lstPKK = pKKeSevice.getAllPKK();
                    
//                    show(chiNhanhS.getAll());
ShowKK(lstPKK);
                }
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbCN;
    private javax.swing.JComboBox<String> cbbCNPKK;
    private javax.swing.JComboBox<String> cbbNL;
    private javax.swing.JComboBox<String> cbbNV;
    private javax.swing.JComboBox<String> cbbpkk;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTT;
    private javax.swing.JRadioButton rdoNo;
    private javax.swing.JRadioButton rdoOK;
    private javax.swing.JTable tblBang;
    private javax.swing.JTable tblBang1;
    private javax.swing.JTable tblBangCTKK;
    private javax.swing.JTable tblBangPkk;
    private javax.swing.JTextField txtDVT;
    private com.toedter.calendar.JDateChooser txtHSD;
    private javax.swing.JTextArea txtLiDo;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaPKK;
    private com.toedter.calendar.JDateChooser txtNgayPkk;
    private javax.swing.JTextField txtSLT;
    private javax.swing.JTextField txtSLTT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
