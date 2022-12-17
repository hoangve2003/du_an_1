/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import domainmodel.ChiNhanh;
import domainmodel.ChucVu;
import domainmodel.NhanVien;
import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.IKhuyenMai;
import service.INhanVien;
import service.implement.KhuyenMaiService;
import service.implement.NhanVienService;
import viewmodel.ChiNhanhView;
import viewmodel.ChucVuView;
import viewmodel.NhanVienView;

/**
 *
 * @author trant
 */
public class QLNhanVien extends javax.swing.JPanel {
    
    List<NhanVienView> nhanVienViews;
    INhanVien iNhanVien;
    IKhuyenMai iKhuyenMai;
    byte[] avatar = null;
    ImageIcon defaultAvatar;
    private final String tenChucVu;
    private final ChiNhanh cn;

//    public QLNhanVien(TaiKhoanAdmin admin, TaiKhoanNguoiDung nguoiDung) {
//        initComponents();
//    }
    public QLNhanVien(String tenChucVu) {
        this.tenChucVu = tenChucVu;
        this.cn = null;
        initComponents();
        init();
        cbbChiNhanh.setVisible(true);
        nhanVienViews = iNhanVien.getAllNhanVien();
        cbbFilterChiNhanh.setModel(new DefaultComboBoxModel(concatenate(new Object[]{"- Tất cả chi nhánh -"}, iKhuyenMai.getAllChiNhanh().toArray())));
        fillNVToTable();
    }
    
    public QLNhanVien(String tenChucVu, ChiNhanh cn) {
        this.tenChucVu = tenChucVu;
        this.cn = cn;
        initComponents();
        init();
        lbChonChiNhanh.setVisible(false);
        cbbChiNhanh.setVisible(false);
        cbbFilterChiNhanh.setModel(new DefaultComboBoxModel(new Object[]{toChiNhanhView(cn)}));
        nhanVienViews = iNhanVien.getAllNVByChiNhanh(cn);
        fillNVToTable();
    }
    
    private void init() {
        iNhanVien = new NhanVienService();
        iKhuyenMai = new KhuyenMaiService();
        
        rdoNam.setSelected(true);
        rdoAllNV.setSelected(true);
        txtSearchNV.setForeground(Color.GRAY);
        cbbChucVu.setModel(new DefaultComboBoxModel(iNhanVien.getAllChucVu().toArray()));
//        cbbFilterChiNhanh.setSelectedIndex(0);
        cbbChiNhanh.setModel(new DefaultComboBoxModel(iKhuyenMai.getAllChiNhanhON().toArray()));

//        cbbFilterChiNhanh.setModel(new DefaultComboBoxModel(concatenate(new Object[]{"- Tat ca chi nhanh -"}, iKhuyenMai.getAllChiNhanhON().toArray())));
        Image image = new ImageIcon(getClass().getClassLoader().getResource("icon/add-image.png")).getImage();
        defaultAvatar = new ImageIcon(image.getScaledInstance(150, 200, Image.SCALE_SMOOTH));
        lblAnhNV.setIcon(defaultAvatar);
        
        tblNhanVien.getTableHeader().setDefaultRenderer(new CustomHeader());
        tblNhanVien.getTableHeader().setPreferredSize(new Dimension(0, 30));
        jScrollPane1.getViewport().setBackground(Color.WHITE);
    }
    
    private Object[] concatenate(Object[] a, Object[] b) {
        Collection<Object> result = new ArrayList<Object>(a.length + b.length);
        result.addAll(Arrays.asList(a));
        result.addAll(Arrays.asList(b));
        return result.toArray();
    }
    
    private ChiNhanhView toChiNhanhView(ChiNhanh cn) {
        return new ChiNhanhView(cn.getId(), cn.getMa());
    }

    // VIEW
    private void fillNVToTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        if (!nhanVienViews.isEmpty()) {
            for (NhanVienView nvv : nhanVienViews) {
                model.addRow(nvv.toDataRow());
            }
        }
    }
    
    private void fillNVToControls(int row) {
        String id = tblNhanVien.getValueAt(row, 0) + "";
        NhanVienView nvv = iNhanVien.getNhanVienById(id);
        if (nvv != null) {
            txtMaNV.setText(nvv.getMa());
            txtHoTenNV.setText(nvv.getHoTen());
            txtSDT.setText(nvv.getSdt());
            txtThanhPho.setText(nvv.getThanhPho());
            txtQuocGia.setText(nvv.getQuocGia());
            
            if (nvv.getLuong() != null) {
                txtLuong.setText(nvv.getLuong() + "");
            } else {
                txtLuong.setText("0.0");
            }
            
            if (nvv.getGioTinh() != null) {
                if (nvv.getGioTinh().equalsIgnoreCase("Nam")) {
                    rdoNam.setSelected(true);
                } else {
                    rdoNu.setSelected(true);
                }
            }
            
            if (nvv.getTrangThai() != null) {
                if (nvv.getTrangThai() == 1) {
                    cbbTrangThai.setSelectedIndex(1);
                } else {
                    cbbTrangThai.setSelectedIndex(2);
                }
            }
            
            if (tenChucVu.equalsIgnoreCase("Ông chủ")) {
                if (nvv.getChiNhanh() != null) {
                    cbbChiNhanh.setSelectedIndex(indexChiNhanh(tblNhanVien.getValueAt(row, 8) + ""));
                }
            }
            if (nvv.getChucVu() != null) {
                cbbChucVu.setSelectedIndex(indexChucVu(tblNhanVien.getValueAt(row, 9) + ""));
            }
            // load anh nv
            if (nvv.getAvatar() != null) {
                Image image = new ImageIcon(nvv.getAvatar()).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
                lblAnhNV.setIcon(new ImageIcon(image));
            } else {
                lblAnhNV.setIcon(defaultAvatar);
            }
//            cbbChucVu.setSelectedItem(nvv.getChucVu()); // bi loi k chay dc, du co cast ve doi tuong roi, chua hieu sai o dau
//            cbbChiNhanh.setSelectedItem(nvv.getChiNhanh()));
        }
    }
    
    private int indexChiNhanh(String maCN) {
        List<ChiNhanhView> cnvs = iKhuyenMai.getAllChiNhanhON();
        if (!cnvs.isEmpty()) {
            for (int i = 0; i < cnvs.size(); i++) {
                if (cnvs.get(i).getMa().equalsIgnoreCase(maCN)) {
                    return i;
                }
            }
        }
        return 0;
    }
    
    private int indexChucVu(String tenCV) {
        List<ChucVuView> cvvs = iNhanVien.getAllChucVu();
        if (!cvvs.isEmpty()) {
            for (int i = 0; i < cvvs.size(); i++) {
                if (cvvs.get(i).getTen().equalsIgnoreCase(tenCV)) {
                    return i;
                }
            }
        }
        return 0;
    }
    
    private void resetControls() {
        txtMaNV.setText("");
        txtHoTenNV.setText("");
        txtSDT.setText("");
        txtThanhPho.setText("");
        txtQuocGia.setText("");
        txtLuong.setText("");
        rdoNam.setSelected(true);
        cbbTrangThai.setSelectedIndex(0);
        cbbChucVu.setSelectedIndex(0);
        cbbChiNhanh.setSelectedIndex(0);
    }
    
    private Object[] getDataFormControls() {
        return new Object[]{txtMaNV.getText().trim(), txtHoTenNV.getText().trim(),
            txtSDT.getText().trim(), txtThanhPho.getText().trim(), txtQuocGia.getText().trim(),
            txtLuong.getText().trim(), cbbTrangThai.getSelectedIndex()
        };
    }
    
    private NhanVien getNVFromControls() {
        if (tenChucVu.equalsIgnoreCase("Ông chủ")) {
            int type = cbbFilterChiNhanh.getSelectedIndex();
            if (type == 0) { // hien cbb chi nhanh de chon them nhan vien vao chi nhanh nao
                ChiNhanh chiNhanh = iKhuyenMai.getChiNhanhById(((ChiNhanhView) cbbChiNhanh.getSelectedItem()).getId());
                ChucVu chucVu = iNhanVien.getChucVuById(((ChucVuView) cbbChucVu.getSelectedItem()).getId());
                return new NhanVien(null, txtMaNV.getText().trim(), txtHoTenNV.getText().trim(), txtQuocGia.getText().trim(),
                        txtThanhPho.getText().trim(), txtSDT.getText().trim(), rdoNam.isSelected() ? "Nam" : "Nữ",
                        Float.parseFloat(txtLuong.getText().trim()), cbbTrangThai.getSelectedIndex() == 1 ? 1 : 0,
                        avatar, chiNhanh, chucVu);
            } else {
                return new NhanVien(null, txtMaNV.getText().trim(), txtHoTenNV.getText().trim(), txtQuocGia.getText().trim(),
                        txtThanhPho.getText().trim(), txtSDT.getText().trim(), rdoNam.isSelected() ? "Nam" : "Nữ",
                        Float.parseFloat(txtLuong.getText().trim()), cbbTrangThai.getSelectedIndex() == 1 ? 1 : 0,
                        avatar, iKhuyenMai.getChiNhanhById(((ChiNhanhView) cbbFilterChiNhanh.getSelectedItem()).getId()),
                        iNhanVien.getChucVuById(((ChucVuView) cbbChucVu.getSelectedItem()).getId())
                );
            }
        } else { // theo chi nhanh cu the voi chuc vu la quan ly
            return new NhanVien(null, txtMaNV.getText().trim(), txtHoTenNV.getText().trim(), txtQuocGia.getText().trim(),
                    txtThanhPho.getText().trim(), txtSDT.getText().trim(), rdoNam.isSelected() ? "Nam" : "Nữ",
                    Float.parseFloat(txtLuong.getText().trim()), cbbTrangThai.getSelectedIndex() == 1 ? 1 : 0,
                    avatar, cn,
                    iNhanVien.getChucVuById(((ChucVuView) cbbChucVu.getSelectedItem()).getId())
            );
        }
        
    }
    
    private void searchAndFilterNV(String search, int trangThai) {
        if (tenChucVu.equalsIgnoreCase("Ông chủ")) {
            int type = cbbFilterChiNhanh.getSelectedIndex();
            if (type == 0) { // 0 = all, !=0 theo chi nhanh
                if (search.isBlank()) {
                    if (trangThai == -1) {
                        nhanVienViews = iNhanVien.getAllNhanVien();
                    } else {
                        nhanVienViews = iNhanVien.getAllNVByTrangThai(trangThai);
                    }
                } else {
                    if (trangThai == -1) {
                        if (iNhanVien.getAllNVByMa(search).isEmpty()) {
                            if (iNhanVien.getAllNVByName(search).isEmpty()) {
                                nhanVienViews = iNhanVien.getAllNVBySDT(search);
                            } else {
                                nhanVienViews = iNhanVien.getAllNVByName(search);
                            }
                        } else {
                            nhanVienViews = iNhanVien.getAllNVByMa(search);
                        }
                    } else {
                        if (iNhanVien.getAllNVByMaAndTrangThai(trangThai, search).isEmpty()) {
                            if (iNhanVien.getAllNVByNameAndTrangThai(trangThai, search).isEmpty()) {
                                nhanVienViews = iNhanVien.getAllNVBySDTAndTrangThai(trangThai, search);
                            } else {
                                nhanVienViews = iNhanVien.getAllNVByNameAndTrangThai(trangThai, search);
                            }
                        } else {
                            nhanVienViews = iNhanVien.getAllNVByMaAndTrangThai(trangThai, search);
                        }
                    }
                }
            } else {
                ChiNhanhView cnv = (ChiNhanhView) cbbFilterChiNhanh.getSelectedItem();
                String id = cnv.getId();
                ChiNhanh cn = iKhuyenMai.getChiNhanhById(id);
                if (search.isBlank()) {
                    if (trangThai == -1) {
                        nhanVienViews = iNhanVien.getAllNVByChiNhanh(cn);
                    } else {
                        nhanVienViews = iNhanVien.getAllNVByChiNhanhAndTrangThai(cn, trangThai);
                    }
                } else {
                    if (trangThai == -1) {
                        if (iNhanVien.getAllNVByChiNhanhAndMa(cn, search).isEmpty()) {
                            if (iNhanVien.getAllNVByChiNhanhAndName(cn, search).isEmpty()) {
                                nhanVienViews = iNhanVien.getAllNVByChiNhanhAndSDT(cn, search);
                            } else {
                                nhanVienViews = iNhanVien.getAllNVByChiNhanhAndName(cn, search);
                            }
                        } else {
                            nhanVienViews = iNhanVien.getAllNVByChiNhanhAndMa(cn, search);
                        }
                    } else {
                        if (iNhanVien.getAllNVByChiNhanhAndTrangThaiAndMa(cn, trangThai, search).isEmpty()) {
                            if (iNhanVien.getAllNVByChiNhanhAndTrangThaiAndName(cn, trangThai, search).isEmpty()) {
                                nhanVienViews = iNhanVien.getAllNVByChiNhanhAndTrangThaiAndSDT(cn, trangThai, search);
                            } else {
                                nhanVienViews = iNhanVien.getAllNVByChiNhanhAndTrangThaiAndName(cn, trangThai, search);
                            }
                        } else {
                            nhanVienViews = iNhanVien.getAllNVByChiNhanhAndTrangThaiAndMa(cn, trangThai, search);
                        }
                    }
                }
            }
        } else {
            if (search.isBlank()) {
                if (trangThai == -1) {
                    nhanVienViews = iNhanVien.getAllNVByChiNhanh(cn);
                } else {
                    nhanVienViews = iNhanVien.getAllNVByChiNhanhAndTrangThai(cn, trangThai);
                }
            } else {
                if (trangThai == -1) {
                    if (iNhanVien.getAllNVByChiNhanhAndMa(cn, search).isEmpty()) {
                        if (iNhanVien.getAllNVByChiNhanhAndName(cn, search).isEmpty()) {
                            nhanVienViews = iNhanVien.getAllNVByChiNhanhAndSDT(cn, search);
                        } else {
                            nhanVienViews = iNhanVien.getAllNVByChiNhanhAndName(cn, search);
                        }
                    } else {
                        nhanVienViews = iNhanVien.getAllNVByChiNhanhAndMa(cn, search);
                    }
                } else {
                    if (iNhanVien.getAllNVByChiNhanhAndTrangThaiAndMa(cn, trangThai, search).isEmpty()) {
                        if (iNhanVien.getAllNVByChiNhanhAndTrangThaiAndName(cn, trangThai, search).isEmpty()) {
                            nhanVienViews = iNhanVien.getAllNVByChiNhanhAndTrangThaiAndSDT(cn, trangThai, search);
                        } else {
                            nhanVienViews = iNhanVien.getAllNVByChiNhanhAndTrangThaiAndName(cn, trangThai, search);
                        }
                    } else {
                        nhanVienViews = iNhanVien.getAllNVByChiNhanhAndTrangThaiAndMa(cn, trangThai, search);
                    }
                }
            }
        }
    }

    // CUD
    private void addNhanVien() { // chi them nhan vien o cac chi nhanh dang hoat dong
        if (iNhanVien.validateDataInput(getDataFormControls()).isBlank()) {
            JOptionPane.showMessageDialog(null, iNhanVien.addNhanVien(getNVFromControls()));
            int trangThai = rdoAllNV.isSelected() ? -1 : (rdoNVOn.isSelected() ? 1 : 0);
            searchAndFilterNV("", trangThai);
            fillNVToTable();
//            if(iNhanVien.addNhanVien(getNVFromControls()). equalsIgnoreCase("Them thanh cong!")){
            resetControls();
//            }   
        } else {
            JOptionPane.showMessageDialog(null, iNhanVien.validateDataInput(getDataFormControls()));
        }
    }
    
    private void updateNhanVien() {
        int row = tblNhanVien.getSelectedRow();
        if (row != -1) {
            if (iNhanVien.validateDataInput(getDataFormControls()).isBlank()) {
                boolean check = false; // so sanh 2 gia tri maNV tren table va control, tru thi update cac truong khac ngoai tru maNV, false thi update ca maNCC nhung phai check trung
                String maNVTable = tblNhanVien.getValueAt(row, 1) + "";
                String maNVControls = txtMaNV.getText().trim();
                if (maNVTable.equalsIgnoreCase(maNVControls)) {
                    check = true;
                }
                String id = tblNhanVien.getValueAt(row, 0) + "";
                if (tenChucVu.equalsIgnoreCase("Ông chủ")) {
                    JOptionPane.showMessageDialog(null, iNhanVien.updateNhanVienByAdmin(id, getNVFromControls(), check));
//                    JOptionPane.showConfirmDialog(null, getNVFromControls().getChiNhanh().getMa());
//                    JOptionPane.showConfirmDialog(null, iKhuyenMai.getChiNhanhById(getNVFromControls().getChiNhanh().getId()).getMa());
                } else {
                    JOptionPane.showMessageDialog(null, iNhanVien.updateNhanVien(id, getNVFromControls(), check));
                }
                int trangThai = rdoAllNV.isSelected() ? -1 : (rdoNVOn.isSelected() ? 1 : 0);
                if (txtSearchNV.getForeground().equals(Color.BLACK)) {
                    String search = txtSearchNV.getText().trim();
                    searchAndFilterNV(search, trangThai);
                } else {
                    searchAndFilterNV("", trangThai);
                }
                System.out.println(nhanVienViews.size());
                fillNVToTable();
            } else {
                JOptionPane.showMessageDialog(null, iNhanVien.validateDataInput(getDataFormControls()));
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Ban can chon 1 hang trong bang truoc!");
        }
    }
    
    private void deleteNhaVien() {
        int row = tblNhanVien.getSelectedRow();
        if (row != -1) {
            String id = tblNhanVien.getValueAt(row, 0) + "";
            JOptionPane.showMessageDialog(null, iNhanVien.deleteNhanVien(id));
            int trangThai = rdoAllNV.isSelected() ? -1 : (rdoNVOn.isSelected() ? 1 : 0);
            if (txtSearchNV.getForeground().equals(Color.BLACK)) {
                String search = txtSearchNV.getText().trim();
                searchAndFilterNV(search, trangThai);
            } else {
                searchAndFilterNV("", trangThai);
            }
            fillNVToTable();
            resetControls();
        } else {
            JOptionPane.showMessageDialog(null, "Ban can chon 1 hang trong bang truoc!");
        }
    }
    
    private void exportExcelNCC() {
        FileOutputStream excelFOU = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelJTableExporter = null;
        JFileChooser excelFileChooser = new JFileChooser(".\\src");
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChosser = excelFileChooser.showSaveDialog(null);
        if (excelChosser == JFileChooser.APPROVE_OPTION) {
            
            try {
                excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet");
                for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i);
                    for (int j = 0; j < tblNhanVien.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(tblNhanVien.getValueAt(i, j).toString());
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
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        btnAddNhanVien = new javax.swing.JButton();
        btnUpdateNV = new javax.swing.JButton();
        btnDeleteNV = new javax.swing.JButton();
        btnExecl = new javax.swing.JButton();
        txtSearchNV = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblAnhNV = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtThanhPho = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        lbChonChiNhanh = new javax.swing.JLabel();
        cbbChiNhanh = new javax.swing.JComboBox<>();
        btnChonAnhNV = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtHoTenNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtQuocGia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbbChucVu = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        txtSDT = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cbbFilterChiNhanh = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        rdoAllNV = new javax.swing.JRadioButton();
        rdoNVOn = new javax.swing.JRadioButton();
        rdoNVOff = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(228, 212, 189));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã nhân viên", "Họ tên", "Giới tính", "Số điện thoại", "Thành phố", "Quốc gia", "Lương", "Chi nhánh", "Chức vụ", "Trạng thái"
            }
        ));
        tblNhanVien.setRowHeight(25);
        tblNhanVien.setSelectionBackground(new java.awt.Color(220, 204, 186));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(0).setMinWidth(0);
            tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblNhanVien.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnAddNhanVien.setBackground(new java.awt.Color(108, 83, 54));
        btnAddNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnAddNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/add_20px.png"))); // NOI18N
        btnAddNhanVien.setText("Thêm");
        btnAddNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNhanVienActionPerformed(evt);
            }
        });

        btnUpdateNV.setBackground(new java.awt.Color(108, 83, 54));
        btnUpdateNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateNV.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateNV.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/icons8_sync_30px.png"))); // NOI18N
        btnUpdateNV.setText("Cập Nhật");
        btnUpdateNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateNVActionPerformed(evt);
            }
        });

        btnDeleteNV.setBackground(new java.awt.Color(108, 83, 54));
        btnDeleteNV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDeleteNV.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteNV.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/icons8_remove_30px.png"))); // NOI18N
        btnDeleteNV.setText("Xóa ");
        btnDeleteNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteNVActionPerformed(evt);
            }
        });

        btnExecl.setBackground(new java.awt.Color(108, 83, 54));
        btnExecl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExecl.setForeground(new java.awt.Color(255, 255, 255));
        btnExecl.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/icons8_microsoft_excel_30px.png"))); // NOI18N
        btnExecl.setText("Xuất Excel");
        btnExecl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExeclActionPerformed(evt);
            }
        });

        txtSearchNV.setText("Tìm theo mã, tên, sdt...");
        txtSearchNV.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchNVCaretUpdate(evt);
            }
        });
        txtSearchNV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchNVFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchNVFocusLost(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(228, 212, 189));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(108, 83, 54)), "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(108, 83, 54))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(108, 83, 54), 5));

        lblAnhNV.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblAnhNV, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblAnhNV, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jLabel2.setText("Mã nhân viên:");

        jLabel5.setText("Thành phố:");

        jLabel8.setText("Lương:");

        lbChonChiNhanh.setText("Chi nhánh:");

        cbbChiNhanh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnChonAnhNV.setBackground(new java.awt.Color(108, 83, 54));
        btnChonAnhNV.setForeground(new java.awt.Color(255, 255, 255));
        btnChonAnhNV.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("icon/upload_to_ftp_25px.png"))); // NOI18N
        btnChonAnhNV.setText("Chọn ảnh");
        btnChonAnhNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhNVActionPerformed(evt);
            }
        });

        jLabel3.setText("Họ tên:");

        jLabel4.setText("Quốc gia:");

        jLabel10.setText("Chức vụ:");

        cbbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Số điện thoại:");

        jLabel7.setText("Giới tính:");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel11.setText("Trạng thái:");

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Chọn trạng thái -", "Đang làm", "Đã nghỉ" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtThanhPho)
                            .addComponent(txtLuong)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNu))
                            .addComponent(cbbTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(70, 70, 70)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(18, 18, 18)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbChonChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbbChiNhanh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbChucVu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQuocGia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoTenNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnChonAnhNV)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtHoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addComponent(cbbChucVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbChonChiNhanh)
                            .addComponent(jLabel11)
                            .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChonAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(108, 83, 54));

        jPanel2.setBackground(new java.awt.Color(108, 83, 54));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 212, 189)), "Chế độ quản lý", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(228, 212, 189))); // NOI18N

        cbbFilterChiNhanh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbFilterChiNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbFilterChiNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbFilterChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbFilterChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(108, 83, 54));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(228, 212, 189)), "Trạng thái", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(228, 212, 189))); // NOI18N

        buttonGroup2.add(rdoAllNV);
        rdoAllNV.setForeground(new java.awt.Color(255, 255, 255));
        rdoAllNV.setText("Tất cả");
        rdoAllNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoAllNVActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoNVOn);
        rdoNVOn.setForeground(new java.awt.Color(255, 255, 255));
        rdoNVOn.setText("Đang làm");
        rdoNVOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNVOnActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoNVOff);
        rdoNVOff.setForeground(new java.awt.Color(255, 255, 255));
        rdoNVOff.setText("Đã nghỉ");
        rdoNVOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNVOffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoAllNV)
                    .addComponent(rdoNVOn)
                    .addComponent(rdoNVOff))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdoAllNV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoNVOn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoNVOff)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearchNV, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateNV)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteNV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExecl)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearchNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdateNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExecl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNhanVienActionPerformed
        addNhanVien();
    }//GEN-LAST:event_btnAddNhanVienActionPerformed

    private void btnUpdateNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNVActionPerformed
        updateNhanVien();
    }//GEN-LAST:event_btnUpdateNVActionPerformed

    private void btnDeleteNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteNVActionPerformed
        deleteNhaVien();
    }//GEN-LAST:event_btnDeleteNVActionPerformed

    private void btnExeclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExeclActionPerformed
        exportExcelNCC();
    }//GEN-LAST:event_btnExeclActionPerformed

    private void btnChonAnhNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhNVActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();//return file selected-gán path cho File
            Image image = new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(lblAnhNV.getWidth(), lblAnhNV.getHeight(), Image.SCALE_SMOOTH);
            lblAnhNV.setIcon(new ImageIcon(image));//tao đối tượng ImageIcon(lấy path file truyền vào)rồi seticon cho label
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                avatar = new byte[baos.toByteArray().length];
                avatar = baos.toByteArray();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnChonAnhNVActionPerformed

    private void cbbFilterChiNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbFilterChiNhanhActionPerformed
//        if()
        if (cbbFilterChiNhanh.getSelectedIndex() == 0) {
            lbChonChiNhanh.setVisible(true);
            cbbChiNhanh.setVisible(true);
        } else {
            lbChonChiNhanh.setVisible(false);
            cbbChiNhanh.setVisible(false);
        }
//        txtSearchNV.setText("Tìm theo mã, tên, sdt...");
        if (txtSearchNV.getForeground().equals(Color.GRAY)) {
            rdoAllNV.setSelected(true);
            searchAndFilterNV("", -1);
        } else {
            int trangThai = rdoAllNV.isSelected() ? -1 : (rdoNVOn.isSelected() ? 1 : 0);
            String search = txtSearchNV.getText().trim();
            searchAndFilterNV(search, trangThai);
        }
        fillNVToTable();
    }//GEN-LAST:event_cbbFilterChiNhanhActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int row = tblNhanVien.getSelectedRow();
        if (row != -1) {
            fillNVToControls(row);
            if((tblNhanVien.getValueAt(row, 10)+"").equalsIgnoreCase("Đang làm")) {
                btnDeleteNV.setEnabled(true);
            }else {
                btnDeleteNV.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void txtSearchNVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchNVCaretUpdate
        int trangThai = rdoAllNV.isSelected() ? -1 : (rdoNVOn.isSelected() ? 1 : 0);
        Color color = txtSearchNV.getForeground();
        if (!color.equals(Color.GRAY)) {
            String search = txtSearchNV.getText().trim();
            searchAndFilterNV(search, trangThai);
            fillNVToTable();
        }
    }//GEN-LAST:event_txtSearchNVCaretUpdate

    private void rdoAllNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoAllNVActionPerformed
        int trangThai = rdoAllNV.isSelected() ? -1 : (rdoNVOn.isSelected() ? 1 : 0);
        Color color = txtSearchNV.getForeground();
        if (color.equals(Color.BLACK)) {
            String search = txtSearchNV.getText().trim();
            searchAndFilterNV(search, trangThai);
        } else {
            searchAndFilterNV("", trangThai);
        }
        fillNVToTable();
    }//GEN-LAST:event_rdoAllNVActionPerformed

    private void rdoNVOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNVOnActionPerformed
        rdoAllNVActionPerformed(evt);
    }//GEN-LAST:event_rdoNVOnActionPerformed

    private void rdoNVOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNVOffActionPerformed
        rdoAllNVActionPerformed(evt);
    }//GEN-LAST:event_rdoNVOffActionPerformed

    private void txtSearchNVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchNVFocusGained
        Color color = txtSearchNV.getForeground();
        if (color.equals(Color.GRAY)) {
            txtSearchNV.setText("");
            txtSearchNV.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtSearchNVFocusGained

    private void txtSearchNVFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchNVFocusLost
        if (txtSearchNV.getText().isBlank()) {
            txtSearchNV.setText("Tìm theo mã, tên, sdt...");
            txtSearchNV.setForeground(Color.GRAY);
            int trangThai = rdoAllNV.isSelected() ? -1 : (rdoNVOn.isSelected() ? 1 : 0);
            searchAndFilterNV("", trangThai);
            fillNVToTable();
        }
    }//GEN-LAST:event_txtSearchNVFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNhanVien;
    private javax.swing.JButton btnChonAnhNV;
    private javax.swing.JButton btnDeleteNV;
    private javax.swing.JButton btnExecl;
    private javax.swing.JButton btnUpdateNV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbChiNhanh;
    private javax.swing.JComboBox<String> cbbChucVu;
    private javax.swing.JComboBox<String> cbbFilterChiNhanh;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbChonChiNhanh;
    private javax.swing.JLabel lblAnhNV;
    private javax.swing.JRadioButton rdoAllNV;
    private javax.swing.JRadioButton rdoNVOff;
    private javax.swing.JRadioButton rdoNVOn;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtHoTenNV;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtQuocGia;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearchNV;
    private javax.swing.JTextField txtThanhPho;
    // End of variables declaration//GEN-END:variables
private class CustomHeader extends DefaultTableCellRenderer {
        
        public CustomHeader() {
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            com.setFont(new Font("Segoe UI", Font.BOLD, 12));
            com.setBackground(new Color(108, 83, 54));
            com.setForeground(Color.WHITE);
            return com;
        }
    }
}
