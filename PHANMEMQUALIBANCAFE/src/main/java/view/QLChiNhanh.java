/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import domainmodel.ChiNhanh;
import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import service.*;
import domainmodel.ChiNhanh;
import domainmodel.KhuVuc;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.IChiNhanh;
import service.implement.ChiNhanhSevice;
import service.implement.KhuVucService;
import viewmodel.ChiNhanhVM_Long;
import viewmodel.ChiNhanhViewModel_Long;
import viewmodel.KhuVucViewModel;
import viewmodel.NhanVienVM_Long;

/**
 *
 * @author MSII
 */
public class QLChiNhanh extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private List<ChiNhanhVM_Long> lstcn = new ArrayList<>();
    private List<NhanVienVM_Long> lstnv = new ArrayList<>();
    private IChiNhanh chiNhanhS = new ChiNhanhSevice();
    private DefaultComboBoxModel<KhuVucViewModel> modelComBokv;
    private IKhuVucService cnS = new KhuVucService();
     private DefaultTableModel dtmNV= new DefaultTableModel();
    /**
     * Creates new form QLChiNhanh1
     */
    public QLChiNhanh(TaiKhoanAdmin admin, TaiKhoanNguoiDung nguoiDung) {
        initComponents();

        tblBang.setModel(dtm);
        String[] header = {"ID","Mã", "Quốc Gia", "Thành Phố", "Ngày Kiểm Kê", "Điểm", "Đổi Điểm", "Trạng Thái"};
        dtm.setColumnIdentifiers(header);
        tblBang.getColumnModel().getColumn(0).setMinWidth(0);
        tblBang.getColumnModel().getColumn(0).setMaxWidth(0);
        lstcn = chiNhanhS.getAll();
        
        tblNV.setModel(dtmNV);
        String[] header1 = {"Mã","Tên", "Chi Nhánh","Trạng Thái"};
        dtmNV.setColumnIdentifiers(header1);
        lstnv = chiNhanhS.getAllNV();

        modelComBokv = (DefaultComboBoxModel) new DefaultComboBoxModel<>(cnS.getAllChiNhanh().toArray());
        show(lstcn);
        showNV(lstnv);
    }

    
    
    
     private void showNV(List<NhanVienVM_Long> lst) {
        dtmNV.setRowCount(0);
        for (NhanVienVM_Long cn : lst) {
//            dtm.addRow(new Object[]{cn.getMa(), cn.getQuocGia(), cn.getThanhPho(), cn.getNgayKhaiTruong(), cn.getGiaTriDiem(), cn.getGiaTriDoiDiem(),cn.getTrangThai()==1?"Ðang hoat dong":"DungHoatDong"
dtmNV.addRow(new Object[]{cn.getMa(),cn.getTen(),cn.getIdcn(),cn.getTrangThai()==0?"Đã Nghỉ":"Còn Làm"});
           
        }
    }
    
    
    private void show(List<ChiNhanhVM_Long> lst) {
        dtm.setRowCount(0);
        for (ChiNhanhVM_Long cn : lst) {
            dtm.addRow(new Object[]{cn.getId(),cn.getMa(), cn.getQuocGia(), cn.getThanhPho(), cn.getNgayKhaiTruong(), cn.getGiaTriDiem(), cn.getGiaTriDoiDiem(),cn.getTrangThai()==1?"Ðang Hoạt Động":"Dừng Hoạt Động"
            });
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
    
    
      private boolean checkFormEmptyDiem(JTextField ma, JTextField ten) {
        if (ma.getText().isBlank() || ten.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Không được trống");
            return false;
        } else {
            return true;
        }
    }
      
      
      

    public void fill(int index, List<ChiNhanhVM_Long> lst) {
        ChiNhanhVM_Long cn = lst.get(index);
        txtMa.setText(cn.getMa());
        txtThanhPho.setText(cn.getThanhPho());
        txtQuocGia.setText(cn.getQuocGia());
        if (cn.getTrangThai() != null) {
                if (cn.getTrangThai() == 1) {
                    rdoDHD.setSelected(true);
                } else {
                    rdoDungHD.setSelected(true);
                }
            }
        txtDiem.setText(String.valueOf(cn.getGiaTriDiem()));
        txtDoiDiem.setText(String.valueOf(cn.getGiaTriDoiDiem()));
        txtNgayKC.setDate(cn.getNgayKhaiTruong());
//        txtNgayKC.setText(String.valueOf(cn.getNgayKhaiTruong()));
    }

    private boolean checkMaSp(String maSp) {
        if (chiNhanhS.getChiNhanh(maSp) != null) {
            JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
            return true;
        } else {
            return false;
        }
    }

    public boolean checkChu(String mark) {
        Pattern regexInt = Pattern.compile("[a-zA-Z]+");
        Pattern regexDouble = Pattern.compile("[a-zA-Z]+");
        if (!regexInt.matcher(mark).find() && !regexDouble.matcher(mark).find()) {
            JOptionPane.showMessageDialog(this, "chu ban oi");
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtQuocGia = new javax.swing.JTextField();
        txtThanhPho = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtDoiDiem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdoDHD = new javax.swing.JRadioButton();
        rdoDungHD = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNV = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtNgayKC = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(225, 218, 197));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý chi nhánh");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Mã");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Ngày Khai Chương");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Thành Phố");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Trang Thái");

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
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Plus_48px.png"))); // NOI18N
        jButton1.setText("THÊM ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(108, 83, 54));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sync_30px.png"))); // NOI18N
        jButton2.setText("SỬA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtQuocGia.setBackground(new java.awt.Color(225, 218, 197));
        txtQuocGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtThanhPho.setBackground(new java.awt.Color(225, 218, 197));
        txtThanhPho.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        txtMa.setBackground(new java.awt.Color(225, 218, 197));
        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jButton3.setBackground(new java.awt.Color(108, 83, 54));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_30px.png"))); // NOI18N
        jButton3.setText("XÓA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(108, 83, 54));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_microsoft_excel_30px.png"))); // NOI18N
        jButton4.setText("Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Thanh Toán Bằng Điểm");

        txtDoiDiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtDoiDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDoiDiemActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Tỷ Lệ Quy Đổi Điểm Thưởng");

        txtDiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiemActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(204, 255, 153));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 255));
        jLabel2.setText("= 1 Điểm");
        jLabel2.setOpaque(true);

        jLabel9.setBackground(new java.awt.Color(255, 255, 102));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 255));
        jLabel9.setText("1 Điểm =");
        jLabel9.setOpaque(true);

        jLabel11.setText("VND");

        jLabel12.setText("VND");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 255, 204));
        jLabel14.setText("Ví dụ: 100.000 VND =1 điểm. Có nghĩa là: Cứ 100.000 đồng doanh thu");
        jLabel14.setOpaque(true);

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 255, 204));
        jLabel15.setText("nhận là 10 điểm");
        jLabel15.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtDoiDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(1, 1, 1)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDoiDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(31, 31, 31))
        );

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Quốc Gia");

        buttonGroup1.add(rdoDHD);
        rdoDHD.setText("Đang Hoạt Động");

        buttonGroup1.add(rdoDungHD);
        rdoDungHD.setText("Dừng Hoạt Động");
        rdoDungHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDungHDActionPerformed(evt);
            }
        });

        tblNV.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblNV);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Nhân viên");

        txtNgayKC.setDateFormatString("dd-MM-yyyy");
        txtNgayKC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNgayKC.setIcon(null);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Bảng Chi Nhánh");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(137, 137, 137)
                                    .addComponent(jLabel3))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(33, 33, 33)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtNgayKC, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(54, 54, 54)
                                            .addComponent(rdoDHD)
                                            .addGap(28, 28, 28)
                                            .addComponent(rdoDungHD)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel13)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jButton1)
                                .addGap(31, 31, 31)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(321, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel4)
                                .addGap(31, 31, 31))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayKC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rdoDHD)
                            .addComponent(rdoDungHD))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 214, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        int row = tblBang.getSelectedRow();
        fill(row, lstcn);// TODO add your handling code here:
    }//GEN-LAST:event_tblBangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        JOptionPane.showConfirmDialog(jButton1, "Them");
        if (checkFormEmpty(txtMa, txtQuocGia) && !checkMaSp(txtMa.getText()) && checkChu(txtQuocGia.getText()) && checkChu(txtThanhPho.getText()) && checkFormEmptyDiem(txtDiem, txtDoiDiem)) {
//            String ngS = txtNgayKC.getText();
//            Date ng = Date.valueOf(ngS);
             int trangThai;
            if (rdoDHD.isSelected()) {
                trangThai = 1;
            } else {
                trangThai = 0;
            }
            chiNhanhS.insertChiNhanh(txtMa.getText(), txtQuocGia.getText(), txtThanhPho.getText(), txtNgayKC.getDate(), Float.valueOf(txtDiem.getText()), Float.valueOf(txtDoiDiem.getText()), trangThai);
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            lstcn = chiNhanhS.getAll();
            show(chiNhanhS.getAll());
        } else {
          
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int row = tblBang.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng click vào Bảng");
        } else {
            if (checkFormEmpty(txtMa, txtQuocGia)) {
                if (txtMa.getText().equals(tblBang.getValueAt(row, 1).toString())) {
                    ChiNhanh mauView = new ChiNhanh();
                    mauView.setMa(tblBang.getValueAt(row, 1).toString());
//                    String ngS = txtNgayKC.getText();
//            Date ng = Date.valueOf(ngS);
                    int trangThai;
            if (rdoDHD.isSelected()) {
                trangThai = 1;
            } else {
                trangThai = 0;
            }
                    chiNhanhS.update(mauView, txtMa.getText(), txtThanhPho.getText(), txtQuocGia.getText(), trangThai, txtNgayKC.getDate());
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    lstcn = chiNhanhS.getAll();
                    show(chiNhanhS.getAll());
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

//        String ma = lstcn.get(tblBang.getSelectedRow()).getMa();
//        chiNhanhS.deleteMauSac(txtMa.getText());
//        lstcn.remove(tblBang.getSelectedRow());
//        JOptionPane.showMessageDialog(this, "Xóa Thành Công");
//        lstcn = chiNhanhS.getAll();
//        show(lstcn);


  int row = tblBang.getSelectedRow();
//        ibanService.deleteBan(tblBan.getValueAt(row, 0).toString());
chiNhanhS.deleteCN(tblBang.getValueAt(row, 0).toString());
        JOptionPane.showMessageDialog(this, "Xoa Thành Công");
//        load_Ban_By_KhuVuc(ibanService.getAllBanByKhuVuc(((KhuVucViewModel) comboKhuVuc.getSelectedItem()).getIdKhuVuc()));
show(chiNhanhS.getAll());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtDoiDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDoiDiemActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtDoiDiemActionPerformed

    private void txtDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiemActionPerformed

    private void rdoDungHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDungHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoDungHDActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
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
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i);
                    for (int j = 0; j < dtm.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(dtm.getValueAt(i, j).toString());
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
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoDHD;
    private javax.swing.JRadioButton rdoDungHD;
    private javax.swing.JTable tblBang;
    private javax.swing.JTable tblNV;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtDoiDiem;
    private javax.swing.JTextField txtMa;
    private com.toedter.calendar.JDateChooser txtNgayKC;
    private javax.swing.JTextField txtQuocGia;
    private javax.swing.JTextField txtThanhPho;
    // End of variables declaration//GEN-END:variables
}
