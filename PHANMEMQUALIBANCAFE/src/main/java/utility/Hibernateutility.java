/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import domainmodel.Ban;
import domainmodel.Ca;
import domainmodel.ChiNhanh;
import domainmodel.ChiTietHoaDon;
import domainmodel.ChiTietPhieuKiemKe;
import domainmodel.ChiTietPhieuNhap;
import domainmodel.ChiTietPhieuTra;
import domainmodel.ChiTietSP;
import domainmodel.ChucVu;
import domainmodel.HoaDonBanHang;
import domainmodel.HoatDongCa;
import domainmodel.IdChiTietHoaDon;
import domainmodel.IdChiTietPhieuKiemKe;
import domainmodel.IdChiTietPhieuNhap;
import domainmodel.IdChiTietPhieuTra;
import domainmodel.IdChiTietSP;
import domainmodel.KhachHang;
import domainmodel.KhuVuc;
import domainmodel.KhuyenMai;
import domainmodel.NguyenLieu;
import domainmodel.NhaCungCap;
import domainmodel.NhanVien;
import domainmodel.PhieuKiemKe;
import domainmodel.PhieuNhapHang;
import domainmodel.PhieuTraHang;
import domainmodel.SanPham;
import domainmodel.TaiKhoanAdmin;
import domainmodel.TaiKhoanNguoiDung;
import domainmodel.ThuongHieu;
import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author trant
 */
public class Hibernateutility {

    private static SessionFactory _factory;

    static {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Ban.class);
        config.addAnnotatedClass(Ca.class);
        config.addAnnotatedClass(ChiNhanh.class);
        config.addAnnotatedClass(ChiTietHoaDon.class);
        config.addAnnotatedClass(ChiTietPhieuKiemKe.class);
        config.addAnnotatedClass(ChiTietPhieuNhap.class);
        config.addAnnotatedClass(ChiTietPhieuTra.class);
        config.addAnnotatedClass(ChiTietSP.class);
        config.addAnnotatedClass(ChucVu.class);
        config.addAnnotatedClass(HoaDonBanHang.class);
        config.addAnnotatedClass(IdChiTietHoaDon.class);
        config.addAnnotatedClass(IdChiTietPhieuKiemKe.class);
        config.addAnnotatedClass(IdChiTietPhieuNhap.class);
        config.addAnnotatedClass(IdChiTietPhieuTra.class);
        config.addAnnotatedClass(IdChiTietSP.class);
        config.addAnnotatedClass(KhachHang.class);
        config.addAnnotatedClass(KhuVuc.class);
        config.addAnnotatedClass(KhuyenMai.class);
        config.addAnnotatedClass(NguyenLieu.class);
        config.addAnnotatedClass(NhaCungCap.class);
        config.addAnnotatedClass(NhanVien.class);
        config.addAnnotatedClass(PhieuKiemKe.class);
        config.addAnnotatedClass(PhieuNhapHang.class);
        config.addAnnotatedClass(PhieuTraHang.class);
        config.addAnnotatedClass(SanPham.class);
        config.addAnnotatedClass(TaiKhoanAdmin.class);
        config.addAnnotatedClass(TaiKhoanNguoiDung.class);
        config.addAnnotatedClass(ThuongHieu.class);
        config.addAnnotatedClass(HoatDongCa.class);

        _factory = config.buildSessionFactory();

    }

    public static SessionFactory getFactory() {
        return _factory;
    }

}
