package view;

import domainmodel.ChiNhanh;
import domainmodel.KhachHang;
import domainmodel.NhaCungCap;
import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
import service.IKhachHang;
import service.IKhuyenMai;
import service.INhaCungCap;
import service.implement.KhachHangService;
import service.implement.KhuyenMaiService;
import service.implement.NhaCungCapService;
import viewmodel.ChiNhanhView;
import viewmodel.KhachHangView;
import viewmodel.NhaCungCapView;

/**
 *
 * @author trant
 */
public class QLDoiTac extends javax.swing.JPanel {

    List<NhaCungCapView> nhaCungCapViews;
    List<KhachHangView> khachHangViews;
    IKhachHang iKhachHang;
    INhaCungCap iNhaCungCap;
    IKhuyenMai iKhuyenMai;
    private final String tenChucVu;
    private final ChiNhanh cn;
//    public QLDoiTac(TaiKhoanAdmin admin, TaiKhoanNguoiDung nguoiDung) {
//        
//    }

    public QLDoiTac(String tenChucVu) {
        this.tenChucVu = tenChucVu;
        this.cn = null;
        initComponents();
        init();
        khachHangViews = iKhachHang.getAllKhachHang();
        fillKHToTable();
    }

    public QLDoiTac(String tenChucVu, ChiNhanh cn) {
        this.tenChucVu = tenChucVu;
        this.cn = cn;
        initComponents();
        init();
        cbbFilterChiNhanh.setVisible(false);
        khachHangViews = iKhachHang.getAllKHByChiNhanh(cn);
        fillKHToTable();
    }

    private void init() {
        //
        rdoNam.setSelected(true);
        rdoAllNCC.setSelected(true);
        rdoAllKH.setSelected(true);
        txtSearchKH.setForeground(Color.GRAY);
        txtSearchNCC.setForeground(Color.GRAY);
        
        tblKhachHang.getTableHeader().setDefaultRenderer(new CustomHeader());
        tblKhachHang.getTableHeader().setPreferredSize(new Dimension(0, 30));
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        
        tblNhaCungCap.getTableHeader().setDefaultRenderer(new CustomHeader());
        tblNhaCungCap.getTableHeader().setPreferredSize(new Dimension(0, 30));
        jScrollPane2.getViewport().setBackground(Color.WHITE);
        //
        iKhuyenMai = new KhuyenMaiService();
        iNhaCungCap = new NhaCungCapService();
        iKhachHang = new KhachHangService();
        //
        nhaCungCapViews = iNhaCungCap.getAllNhaCungCap();
        cbbFilterChiNhanh.setModel(new DefaultComboBoxModel(concatenate(new Object[]{"- Tất cả chi nhánh -"}, iKhuyenMai.getAllChiNhanhON().toArray())));// viet lai repo ChiNhanh thi sua doan nay
        fillNCCToTable();
    }

    //// GENERAL
    // getData to validate
    private Object[] getDataFormControls(int type) { // type = 0 lay data NCC, type != 0 lay data KH
        if (type == 0) {
            return new Object[]{
                txtMaNCC.getText().trim(), txtTenNCC.getText().trim(), cbbTrangThaiNCC.getSelectedIndex()
            };
        } else {
            return new Object[]{txtMaKH.getText().trim(), txtHoTenKH.getText().trim(), txtSdtKH.getText().trim(),
                txtThanhPhoKH.getText().trim(), txtQuocGiaKH.getText().trim(), txtDiemTichLuy.getText().trim(),
                cbbTrangThaiKH.getSelectedIndex()
            };
        }
    }

    // reset con trols
    private void resetControls(int type) {
        if (type == 0) {// type = 0 reset NCC, type != 0 reset KH
            txtMaNCC.setText("");
            txtTenNCC.setText("");
            cbbTrangThaiNCC.setSelectedIndex(0);
        } else {
            txtMaKH.setText("");
            txtHoTenKH.setText("");
            txtSdtKH.setText("");
            txtThanhPhoKH.setText("");
            txtQuocGiaKH.setText("");
            txtDiemTichLuy.setText("");
            rdoNam.setSelected(true);
            cbbTrangThaiKH.setSelectedIndex(0);
        }
    }

    // Add to Cbb
    private Object[] concatenate(Object[] a, Object[] b) {
        Collection<Object> result = new ArrayList<Object>(a.length + b.length);
        result.addAll(Arrays.asList(a));
        result.addAll(Arrays.asList(b));
        return result.toArray();
    }

    //// NHA CUNG CAP
    /// VIEW
    // fill table
    private void fillNCCToTable() {
        DefaultTableModel modelNCC = (DefaultTableModel) tblNhaCungCap.getModel();
        modelNCC.setRowCount(0);
        if (!nhaCungCapViews.isEmpty()) {
            for (NhaCungCapView nccv : nhaCungCapViews) {
                modelNCC.addRow(nccv.toDataRow());
            }
        }
    }

    // fill controls
    private void fillNCCToControls() {
        int row = tblNhaCungCap.getSelectedRow();
        if (row != -1) {
            String id = tblNhaCungCap.getValueAt(row, 0) + "";
            NhaCungCapView nccv = iNhaCungCap.getNCCById(id);
            if (nccv != null) {
                txtMaNCC.setText(nccv.getMa());
                txtTenNCC.setText(nccv.getTen());
                if (nccv.getTrangThai() == 1) {
                    cbbTrangThaiNCC.setSelectedIndex(1);
                } else {
                    cbbTrangThaiNCC.setSelectedIndex(2);
                }
            }
        }
    }

    // search and filter NCC
    private void searchAndFilterNCC(String search, int trangThai) {
        if (search.isBlank()) {
            if (trangThai == -1) {
                nhaCungCapViews = iNhaCungCap.getAllNhaCungCap();
            } else {
                nhaCungCapViews = iNhaCungCap.getAllNCCByTrangThai(trangThai);
            }
        } else {
            if (trangThai == -1) {
                if (iNhaCungCap.getAllNCCByMa(search).isEmpty()) {
                    nhaCungCapViews = iNhaCungCap.getAllNCCByName(search);
                } else {
                    nhaCungCapViews = iNhaCungCap.getAllNCCByMa(search);
                }
            } else {
                if (iNhaCungCap.getAllNCCByTrangThaiAndMa(trangThai, search).isEmpty()) {
                    nhaCungCapViews = iNhaCungCap.getAllNCCByTrangThaiAndName(trangThai, search);
                } else {
                    nhaCungCapViews = iNhaCungCap.getAllNCCByTrangThaiAndMa(trangThai, search);
                }
            }
        }
    }

    // get data NCC
    private NhaCungCap getNCCFromControls() {
        return new NhaCungCap(null, txtMaNCC.getText().trim().toUpperCase(),
                txtTenNCC.getText().trim(),
                cbbTrangThaiNCC.getSelectedIndex() == 1 ? 1 : 0);
//                cbbTrangThaiNCC.getSelectedIndex() == 0 ? -1 : (cbbTrangThaiNCC.getSelectedIndex() == 1 ? 1 : 0));
    }

    /// CUD
    private void addNCC() {
        if (iNhaCungCap.validateDataInput(getDataFormControls(0)).isBlank()) {
            JOptionPane.showMessageDialog(null, iNhaCungCap.addNhaCungCap(getNCCFromControls()));
            int trangThai = rdoAllNCC.isSelected() ? -1 : (rdoOnNCC.isSelected() ? 1 : 0);
            searchAndFilterNCC("", trangThai);
            fillNCCToTable();
            resetControls(0);
        } else {
            JOptionPane.showMessageDialog(null, iNhaCungCap.validateDataInput(getDataFormControls(0)));
        }
    }

    private void updateNCC() { // add và update chua hien ra thong bao trung ma
        int row = tblNhaCungCap.getSelectedRow();
        if (row != -1) {
            if (iNhaCungCap.validateDataInput(getDataFormControls(0)).isBlank()) {
                boolean check = false; // so sanh 2 gia tri maNCC tren table va control, tru thi update cac truong khac ngoai tru maNCC, false thi update ca maNCC nhung phai check trung
                String maNCCTable = tblNhaCungCap.getValueAt(row, 1) + "";
                String maNCCControls = txtMaNCC.getText().trim();
                if (maNCCTable.equalsIgnoreCase(maNCCControls)) {
                    check = true;
                }
                String id = tblNhaCungCap.getValueAt(row, 0) + "";
                NhaCungCap ncc = getNCCFromControls();
                JOptionPane.showMessageDialog(null, iNhaCungCap.updateNhaCungCap(id, ncc, check));
                int trangThai = rdoAllNCC.isSelected() ? -1 : (rdoOnNCC.isSelected() ? 1 : 0);
                Color color = txtSearchNCC.getForeground();
                if (!color.equals(Color.GRAY)) {
                    String search = txtSearchNCC.getText().trim();
                    searchAndFilterNCC(search, trangThai);
                } else {
                    searchAndFilterNCC("", trangThai);
                }
                fillNCCToTable();
            } else {
                JOptionPane.showMessageDialog(null, iNhaCungCap.validateDataInput(getDataFormControls(0)));
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ban can chon 1 hang trong bang truoc!");
        }
    }

    private void deleteNCC() {
        int row = tblNhaCungCap.getSelectedRow();
        if (row != -1) {
            String id = tblNhaCungCap.getValueAt(row, 0) + "";
            JOptionPane.showMessageDialog(null, iNhaCungCap.deleteNhaCungCap(id));
            int trangThai = rdoAllNCC.isSelected() ? -1 : (rdoOnNCC.isSelected() ? 1 : 0);
            Color color = txtSearchNCC.getForeground();
            if (!color.equals(Color.GRAY)) {
                String search = txtSearchNCC.getText().trim();
                searchAndFilterNCC(search, trangThai);
            } else {
                searchAndFilterNCC("", trangThai);
            }
            fillNCCToTable();
            resetControls(0);
        } else {
            JOptionPane.showMessageDialog(null, "Ban can chon 1 hang trong bang truoc!");
        }
    }

    // Export Excel
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
                for (int i = 0; i < tblNhaCungCap.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i);
                    for (int j = 0; j < tblNhaCungCap.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(tblNhaCungCap.getValueAt(i, j).toString());
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

    //// KHACH HANG
    /// View
    // fill table
    private void fillKHToTable() {
        DefaultTableModel modelKH = (DefaultTableModel) tblKhachHang.getModel();
        modelKH.setRowCount(0);
        if (khachHangViews != null) {
            for (KhachHangView khv : khachHangViews) {
                modelKH.addRow(khv.toDataRow());
            }
        }
    }

    // fill controls
    private void fillKHToControls() {
        int row = tblKhachHang.getSelectedRow();
        String id = tblKhachHang.getValueAt(row, 0) + "";
        KhachHangView khv = iKhachHang.getKHById(id);
        if (khv != null) {
            txtMaKH.setText(khv.getMa());
            if (khv.getDiemTichLuy() != null) {
                txtDiemTichLuy.setText(khv.getDiemTichLuy() + "");
            } else {
                txtDiemTichLuy.setText("0");
            }
            txtHoTenKH.setText(khv.getHoTen());
            txtSdtKH.setText(khv.getSdt());
            txtThanhPhoKH.setText(khv.getThanhPho());
            txtQuocGiaKH.setText(khv.getQuocGia());
            if (khv.getGioiTinh().equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }

            if (khv.getTrangThai() != null) {
                if (khv.getTrangThai() == 1) {
                    cbbTrangThaiKH.setSelectedIndex(1);
                } else {
                    cbbTrangThaiKH.setSelectedIndex(2);
                }
            }
        }
    }

    // search and filter
    private void searchAndFilterKH(String search, int trangThai) {
        if (tenChucVu.equalsIgnoreCase("Ông chủ")) {
            int type = cbbFilterChiNhanh.getSelectedIndex();
            if (type == 0) { // 0 = all, !=0 theo chi nhanh
                if (search.isBlank()) {
                    if (trangThai == -1) {
                        khachHangViews = iKhachHang.getAllKhachHang();
                    } else {
                        khachHangViews = iKhachHang.getAllKHByTrangThai(trangThai);
                    }
                } else {
                    if (trangThai == -1) {
                        if (iKhachHang.getAllKHByMa(search).isEmpty()) {
                            if (iKhachHang.getAllKHByName(search).isEmpty()) {
                                khachHangViews = iKhachHang.getAllKHBySDT(search);
                            } else {
                                khachHangViews = iKhachHang.getAllKHByName(search);
                            }
                        } else {
                            khachHangViews = iKhachHang.getAllKHByMa(search);
                        }
                    } else {
                        if (iKhachHang.getAllKHByMaAndTrangThai(trangThai, search).isEmpty()) {
                            if (iKhachHang.getAllKHByNameAndTrangThai(trangThai, search).isEmpty()) {
                                khachHangViews = iKhachHang.getAllKHBySDTAndTrangThai(trangThai, search);
                            } else {
                                khachHangViews = iKhachHang.getAllKHByNameAndTrangThai(trangThai, search);
                            }
                        } else {
                            khachHangViews = iKhachHang.getAllKHByMaAndTrangThai(trangThai, search);
                        }
                    }
                }
            } else {
                ChiNhanhView cnv = (ChiNhanhView) cbbFilterChiNhanh.getSelectedItem();
                String id = cnv.getId();
                ChiNhanh cn = iKhuyenMai.getChiNhanhById(id);
                if (search.isBlank()) {
                    if (trangThai == -1) {
                        khachHangViews = iKhachHang.getAllKHByChiNhanh(cn);
                    } else {
                        khachHangViews = iKhachHang.getAllKHByChiNhanhAndTrangThai(cn, trangThai);
                    }
                } else {
                    if (trangThai == -1) {
                        if (iKhachHang.getAllKHByChiNhanhAndMa(cn, search).isEmpty()) {
                            if (iKhachHang.getAllKHByChiNhanhAndName(cn, search).isEmpty()) {
                                khachHangViews = iKhachHang.getAllKHByChiNhanhAndSDT(cn, search);
                            } else {
                                khachHangViews = iKhachHang.getAllKHByChiNhanhAndName(cn, search);
                            }
                        } else {
                            khachHangViews = iKhachHang.getAllKHByChiNhanhAndMa(cn, search);
                        }
                    } else {
                        if (iKhachHang.getAllKHByChiNhanhAndTrangThaiAndMa(cn, trangThai, search).isEmpty()) {
                            if (iKhachHang.getAllKHByChiNhanhAndTrangThaiAndName(cn, trangThai, search).isEmpty()) {
                                khachHangViews = iKhachHang.getAllKHByChiNhanhAndTrangThaiAndSDT(cn, trangThai, search);
                            } else {
                                khachHangViews = iKhachHang.getAllKHByChiNhanhAndTrangThaiAndName(cn, trangThai, search);
                            }
                        } else {
                            khachHangViews = iKhachHang.getAllKHByChiNhanhAndTrangThaiAndMa(cn, trangThai, search);
                        }
                    }
                }
            }
        } else {
            if (search.isBlank()) {
                if (trangThai == -1) {
                    khachHangViews = iKhachHang.getAllKHByChiNhanh(cn);
                } else {
                    khachHangViews = iKhachHang.getAllKHByChiNhanhAndTrangThai(cn, trangThai);
                }
            } else {
                if (trangThai == -1) {
                    if (iKhachHang.getAllKHByChiNhanhAndMa(cn, search).isEmpty()) {
                        if (iKhachHang.getAllKHByChiNhanhAndName(cn, search).isEmpty()) {
                            khachHangViews = iKhachHang.getAllKHByChiNhanhAndSDT(cn, search);
                        } else {
                            khachHangViews = iKhachHang.getAllKHByChiNhanhAndName(cn, search);
                        }
                    } else {
                        khachHangViews = iKhachHang.getAllKHByChiNhanhAndMa(cn, search);
                    }
                } else {
                    if (iKhachHang.getAllKHByChiNhanhAndTrangThaiAndMa(cn, trangThai, search).isEmpty()) {
                        if (iKhachHang.getAllKHByChiNhanhAndTrangThaiAndName(cn, trangThai, search).isEmpty()) {
                            khachHangViews = iKhachHang.getAllKHByChiNhanhAndTrangThaiAndSDT(cn, trangThai, search);
                        } else {
                            khachHangViews = iKhachHang.getAllKHByChiNhanhAndTrangThaiAndName(cn, trangThai, search);
                        }
                    } else {
                        khachHangViews = iKhachHang.getAllKHByChiNhanhAndTrangThaiAndMa(cn, trangThai, search);
                    }
                }
            }
        }
    }

    // get data KH
    private KhachHang getKHFromControls() {
        return new KhachHang(null, txtMaKH.getText().trim().toUpperCase(), txtHoTenKH.getText().trim(),
                txtQuocGiaKH.getText().trim(), txtThanhPhoKH.getText().trim(), txtSdtKH.getText().trim(),
                rdoNam.isSelected() ? "Nam" : "Nữ",
                cbbTrangThaiKH.getSelectedIndex() == 1 ? 1 : 0,
                //                cbbTrangThaiKH.getSelectedIndex() == 0 ? -1 : (cbbTrangThaiNCC.getSelectedIndex() == 1 ? 1 : 0), // thay -1 bang null thi lai loi, chua hieu lam
                Integer.parseInt(txtDiemTichLuy.getText().trim()), null
        );
    }

    /// CUD
    private void addKH() {

    }

    private void updateKH() {
        int row = tblKhachHang.getSelectedRow();
        if (row != -1) {
            if (iKhachHang.validateDataInput(getDataFormControls(1)).isBlank()) {
                boolean check = false; // so sanh 2 gia tri maNCC tren table va control, tru thi update cac truong khac ngoai tru maNCC, false thi update ca maNCC nhung phai check trung
                String maKHTable = tblKhachHang.getValueAt(row, 1) + "";
                String maKHControls = txtMaKH.getText().trim();
                if (maKHTable.equalsIgnoreCase(maKHControls)) {
                    check = true;
                }
                String id = tblKhachHang.getValueAt(row, 0) + "";
                KhachHang kh = getKHFromControls();
                JOptionPane.showMessageDialog(null, iKhachHang.updateKhachHang(id, kh, check));
                // fill dung voi che do hien tai
                int trangThai = rdoAllKH.isSelected() ? -1 : (rdoOnKH.isSelected() ? 1 : 0);
                Color color = txtSearchKH.getForeground();
                if (!color.equals(Color.GRAY)) {
                    String search = txtSearchKH.getText().trim();
                    searchAndFilterKH(search, trangThai);
                } else {
                    searchAndFilterKH("", trangThai);
                }
                fillKHToTable();
            } else {
                JOptionPane.showMessageDialog(null, iKhachHang.validateDataInput(getDataFormControls(1)));
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ban can chon 1 hang trong bang truoc!");
        }
    }

    private void deleteKH() {
        int row = tblKhachHang.getSelectedRow();
        if (row != -1) {
            String id = tblKhachHang.getValueAt(row, 0) + "";
            JOptionPane.showMessageDialog(null, iKhachHang.deleteKhachHang(id));
            int trangThai = rdoAllKH.isSelected() ? -1 : (rdoOnKH.isSelected() ? 1 : 0);
            Color color = txtSearchKH.getForeground();
            if (!color.equals(Color.GRAY)) {
                String search = txtSearchKH.getText().trim();
                searchAndFilterKH(search, trangThai);
            } else {
                searchAndFilterKH("", trangThai);
            }
            fillKHToTable();
            resetControls(1);
        } else {
            JOptionPane.showMessageDialog(null, "Ban can chon 1 hang trong bang truoc!");
        }
    }

    private void exportExcelKH() {
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
                for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
                    XSSFRow excelRow = excelSheet.createRow(i);
                    for (int j = 0; j < tblKhachHang.getColumnCount(); j++) {
                        XSSFCell excelCell = excelRow.createCell(j);
                        excelCell.setCellValue(tblKhachHang.getValueAt(i, j).toString());
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
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        txtSearchKH = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtMaKH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtHoTenKH = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtThanhPhoKH = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtQuocGiaKH = new javax.swing.JTextField();
        cbbTrangThaiKH = new javax.swing.JComboBox<>();
        txtSdtKH = new javax.swing.JTextField();
        txtDiemTichLuy = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnUpdateKH = new javax.swing.JButton();
        btnXoaKH = new javax.swing.JButton();
        btnExcelKH = new javax.swing.JButton();
        rdoAllKH = new javax.swing.JRadioButton();
        rdoOnKH = new javax.swing.JRadioButton();
        rdoOffKH = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        cbbFilterChiNhanh = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhaCungCap = new javax.swing.JTable();
        txtMaNCC = new javax.swing.JTextField();
        txtTenNCC = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbbTrangThaiNCC = new javax.swing.JComboBox<>();
        btnAddNCC = new javax.swing.JButton();
        btnUpdateNCC = new javax.swing.JButton();
        txtSearchNCC = new javax.swing.JTextField();
        btnXoaNCC = new javax.swing.JButton();
        btnExcelNCC = new javax.swing.JButton();
        rdoAllNCC = new javax.swing.JRadioButton();
        rdoOnNCC = new javax.swing.JRadioButton();
        rdoOffNCC = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(228, 212, 189));

        jPanel1.setBackground(new java.awt.Color(228, 212, 189));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(108, 83, 54)), "Danh sách và thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(108, 83, 54))); // NOI18N

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã KH", "Họ tên", "Giới tính", "Số điện thoại", "Thành phố", "Quốc gia", "Trạng thái", "Điểm tích lũy"
            }
        ));
        tblKhachHang.setRowHeight(25);
        tblKhachHang.setSelectionBackground(new java.awt.Color(220, 204, 186));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setMinWidth(0);
            tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblKhachHang.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        txtSearchKH.setText("Tìm theo mã, tên, sdt...");
        txtSearchKH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchKHCaretUpdate(evt);
            }
        });
        txtSearchKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchKHFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchKHFocusLost(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(228, 212, 189));

        jLabel5.setText("Mã KH:");

        jLabel7.setText("Họ tên:");

        jLabel9.setText("Giới tính:");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel8.setText("Thành phố:");

        jLabel10.setText("Quốc gia:");

        cbbTrangThaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Chọn trạng thái -", "Còn hoạt động", "Ngừng hoạt động" }));

        jLabel6.setText("Điểm tích lũy:");

        jLabel16.setText("Số điện thoại:");

        jLabel17.setText("Trạng thái:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtThanhPhoKH, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtQuocGiaKH)
                    .addComponent(txtSdtKH, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiemTichLuy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTrangThaiKH, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtDiemTichLuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel7)
                    .addComponent(txtHoTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTrangThaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuocGiaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhPhoKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(228, 212, 189));

        btnUpdateKH.setBackground(new java.awt.Color(108, 83, 54));
        btnUpdateKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateKH.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sync_30px.png"))); // NOI18N
        btnUpdateKH.setText("Cập Nhật KH");
        btnUpdateKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateKHActionPerformed(evt);
            }
        });

        btnXoaKH.setBackground(new java.awt.Color(108, 83, 54));
        btnXoaKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaKH.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_30px.png"))); // NOI18N
        btnXoaKH.setText("Xóa KH");
        btnXoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKHActionPerformed(evt);
            }
        });

        btnExcelKH.setBackground(new java.awt.Color(108, 83, 54));
        btnExcelKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExcelKH.setForeground(new java.awt.Color(255, 255, 255));
        btnExcelKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_microsoft_excel_30px.png"))); // NOI18N
        btnExcelKH.setText("Xuất  Excel");
        btnExcelKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExcelKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdateKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnXoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExcelKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        buttonGroup3.add(rdoAllKH);
        rdoAllKH.setText("Tất cả");
        rdoAllKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoAllKHActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoOnKH);
        rdoOnKH.setText("Còn hoạt động");
        rdoOnKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoOnKHActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoOffKH);
        rdoOffKH.setText("Ngừng hoạt động");
        rdoOffKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoOffKHActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Chế độ quản lý:");

        cbbFilterChiNhanh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbFilterChiNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbFilterChiNhanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbFilterChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(98, 98, 98)
                                        .addComponent(rdoAllKH)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoOnKH)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoOffKH))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbbFilterChiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoAllKH)
                    .addComponent(rdoOnKH)
                    .addComponent(rdoOffKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(228, 212, 189));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(108, 83, 54)), "Danh sách và thông tin nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(108, 83, 54))); // NOI18N

        tblNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã  NCC", "Tên NCC", "Trạng thái"
            }
        ));
        tblNhaCungCap.setRowHeight(25);
        tblNhaCungCap.setSelectionBackground(new java.awt.Color(220, 204, 186));
        tblNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhaCungCap);
        if (tblNhaCungCap.getColumnModel().getColumnCount() > 0) {
            tblNhaCungCap.getColumnModel().getColumn(0).setMinWidth(0);
            tblNhaCungCap.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblNhaCungCap.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jLabel2.setText("Mã NCC:");

        jLabel3.setText("Tên NCC:");

        jLabel4.setText("Trạng thái:");

        cbbTrangThaiNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Chọn trạng thái -", "Còn hoạt động", "Ngừng hoạt động" }));

        btnAddNCC.setBackground(new java.awt.Color(108, 83, 54));
        btnAddNCC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnAddNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_20px.png"))); // NOI18N
        btnAddNCC.setText("Thêm  NCC");
        btnAddNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNCCActionPerformed(evt);
            }
        });

        btnUpdateNCC.setBackground(new java.awt.Color(108, 83, 54));
        btnUpdateNCC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sync_30px.png"))); // NOI18N
        btnUpdateNCC.setText("Cập Nhật NCC");
        btnUpdateNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateNCCActionPerformed(evt);
            }
        });

        txtSearchNCC.setText("Tìm theo mã, tên…");
        txtSearchNCC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchNCCCaretUpdate(evt);
            }
        });
        txtSearchNCC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchNCCFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchNCCFocusLost(evt);
            }
        });

        btnXoaNCC.setBackground(new java.awt.Color(108, 83, 54));
        btnXoaNCC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_30px.png"))); // NOI18N
        btnXoaNCC.setText("Xóa NCC");
        btnXoaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNCCActionPerformed(evt);
            }
        });

        btnExcelNCC.setBackground(new java.awt.Color(108, 83, 54));
        btnExcelNCC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExcelNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnExcelNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_microsoft_excel_30px.png"))); // NOI18N
        btnExcelNCC.setText("Xuất  Excel");
        btnExcelNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelNCCActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoAllNCC);
        rdoAllNCC.setText("Tất cả");
        rdoAllNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoAllNCCActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoOnNCC);
        rdoOnNCC.setText("Còn hoạt động");
        rdoOnNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoOnNCCActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoOffNCC);
        rdoOffNCC.setText("Ngừng hoạt động");
        rdoOffNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoOffNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbbTrangThaiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpdateNCC))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addComponent(btnExcelNCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(rdoAllNCC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoOnNCC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdoOffNCC))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSearchNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTrangThaiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(btnExcelNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoAllNCC)
                    .addComponent(rdoOnNCC)
                    .addComponent(rdoOffNCC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        fillKHToControls();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnUpdateKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateKHActionPerformed
        updateKH();
    }//GEN-LAST:event_btnUpdateKHActionPerformed

    private void btnXoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKHActionPerformed
        deleteKH();
    }//GEN-LAST:event_btnXoaKHActionPerformed

    private void btnExcelKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelKHActionPerformed
        exportExcelKH();
    }//GEN-LAST:event_btnExcelKHActionPerformed

    private void tblNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhaCungCapMouseClicked
        fillNCCToControls();
    }//GEN-LAST:event_tblNhaCungCapMouseClicked

    private void btnAddNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNCCActionPerformed
        addNCC();
    }//GEN-LAST:event_btnAddNCCActionPerformed

    private void btnUpdateNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNCCActionPerformed
        updateNCC();
    }//GEN-LAST:event_btnUpdateNCCActionPerformed

    private void btnXoaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNCCActionPerformed
        deleteNCC();
    }//GEN-LAST:event_btnXoaNCCActionPerformed

    private void btnExcelNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelNCCActionPerformed
        exportExcelNCC();
    }//GEN-LAST:event_btnExcelNCCActionPerformed

    private void txtSearchNCCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchNCCCaretUpdate
        int trangThai = rdoAllNCC.isSelected() ? -1 : (rdoOnNCC.isSelected() ? 1 : 0);
        Color color = txtSearchNCC.getForeground();
        if (!color.equals(Color.GRAY)) {
            String search = txtSearchNCC.getText().trim();
            searchAndFilterNCC(search, trangThai);
            fillNCCToTable();
        }
    }//GEN-LAST:event_txtSearchNCCCaretUpdate

    private void rdoAllNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoAllNCCActionPerformed
        int trangThai = rdoAllNCC.isSelected() ? -1 : (rdoOnNCC.isSelected() ? 1 : 0);
        if (txtSearchNCC.getForeground().equals(Color.BLACK)) {
            String search = txtSearchNCC.getText().trim();
            searchAndFilterNCC(search, trangThai);
        } else {
            searchAndFilterNCC("", trangThai);
        }
        fillNCCToTable();
    }//GEN-LAST:event_rdoAllNCCActionPerformed

    private void rdoOnNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoOnNCCActionPerformed
        rdoAllNCCActionPerformed(evt);
    }//GEN-LAST:event_rdoOnNCCActionPerformed

    private void rdoOffNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoOffNCCActionPerformed
        rdoAllNCCActionPerformed(evt);
    }//GEN-LAST:event_rdoOffNCCActionPerformed

    private void cbbFilterChiNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbFilterChiNhanhActionPerformed
        if (txtSearchKH.getForeground().equals(Color.GRAY)) {
            rdoAllKH.setSelected(true);
            searchAndFilterKH("", -1);
        } else {
            int trangThai = rdoAllKH.isSelected() ? -1 : (rdoOnKH.isSelected() ? 1 : 0);
            String search = txtSearchKH.getText().trim();
            searchAndFilterKH(search, trangThai);
        }
        fillKHToTable();

    }//GEN-LAST:event_cbbFilterChiNhanhActionPerformed

    private void txtSearchKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchKHCaretUpdate
        int trangThai = rdoAllKH.isSelected() ? -1 : (rdoOnKH.isSelected() ? 1 : 0);
        Color color = txtSearchKH.getForeground();
        if (!color.equals(Color.GRAY)) {
            String search = txtSearchKH.getText().trim();
            searchAndFilterKH(search, trangThai);
            fillKHToTable();
        }
    }//GEN-LAST:event_txtSearchKHCaretUpdate

    private void rdoAllKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoAllKHActionPerformed
        int trangThai = rdoAllKH.isSelected() ? -1 : (rdoOnKH.isSelected() ? 1 : 0);
        if (txtSearchKH.getForeground().equals(Color.BLACK)) {
            String search = txtSearchKH.getText().trim();
            searchAndFilterKH(search, trangThai);
        } else {
            searchAndFilterKH("", trangThai);
        }
        fillKHToTable();
    }//GEN-LAST:event_rdoAllKHActionPerformed

    private void rdoOnKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoOnKHActionPerformed
        rdoAllKHActionPerformed(evt);
    }//GEN-LAST:event_rdoOnKHActionPerformed

    private void rdoOffKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoOffKHActionPerformed
        rdoAllKHActionPerformed(evt);
    }//GEN-LAST:event_rdoOffKHActionPerformed

    private void txtSearchKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchKHFocusGained
        if (txtSearchKH.getForeground().equals(Color.GRAY)) {
            txtSearchKH.setText("");
            txtSearchKH.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtSearchKHFocusGained

    private void txtSearchNCCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchNCCFocusGained
        if (txtSearchNCC.getForeground().equals(Color.GRAY)) {
            txtSearchNCC.setText("");
            txtSearchNCC.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_txtSearchNCCFocusGained

    private void txtSearchKHFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchKHFocusLost
        if (txtSearchKH.getText().isBlank()) {
            txtSearchKH.setText("Tìm theo mã, tên, sdt...");
            txtSearchKH.setForeground(Color.GRAY);
            int trangThai = rdoAllKH.isSelected() ? -1 : (rdoOnKH.isSelected() ? 1 : 0);
            searchAndFilterKH("", trangThai);
            fillKHToTable();
        }
    }//GEN-LAST:event_txtSearchKHFocusLost

    private void txtSearchNCCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchNCCFocusLost
        if (txtSearchNCC.getText().isBlank()) {
            txtSearchNCC.setText("Tìm theo mã, tên...");
            txtSearchNCC.setForeground(Color.GRAY);
            int trangThai = rdoAllNCC.isSelected() ? -1 : (rdoOnNCC.isSelected() ? 1 : 0);
            searchAndFilterNCC("", trangThai);
            fillNCCToTable();
        }
    }//GEN-LAST:event_txtSearchNCCFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNCC;
    private javax.swing.JButton btnExcelKH;
    private javax.swing.JButton btnExcelNCC;
    private javax.swing.JButton btnUpdateKH;
    private javax.swing.JButton btnUpdateNCC;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JButton btnXoaNCC;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbbFilterChiNhanh;
    private javax.swing.JComboBox<String> cbbTrangThaiKH;
    private javax.swing.JComboBox<String> cbbTrangThaiNCC;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoAllKH;
    private javax.swing.JRadioButton rdoAllNCC;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoOffKH;
    private javax.swing.JRadioButton rdoOffNCC;
    private javax.swing.JRadioButton rdoOnKH;
    private javax.swing.JRadioButton rdoOnNCC;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblNhaCungCap;
    private javax.swing.JTextField txtDiemTichLuy;
    private javax.swing.JTextField txtHoTenKH;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtQuocGiaKH;
    private javax.swing.JTextField txtSdtKH;
    private javax.swing.JTextField txtSearchKH;
    private javax.swing.JTextField txtSearchNCC;
    private javax.swing.JTextField txtTenNCC;
    private javax.swing.JTextField txtThanhPhoKH;
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
