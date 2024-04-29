package DTO;

import java.util.Date;

public class NhanVienDTO {
    private String maNV;
    private String tenNV;
    private int gioiTinh; // 0 là nam 1 là nữ
    private int soNgayPhep;
    private int chucVu; // 0 là Quản lý, 1 là lễ tân    
    private Date ngaySinh;
    private Date ngayVaoLam;
    private String email;
    private int luong1Ngay;
    private int xuLy; // 0 là chưa xóa, 1 là đã xóa
    
    public static String[] header = {"Mã Nhân Viên","Tên Nhân Viên","Giới Tính","Ngày Sinh","Ngày Vào Làm","Chức Vụ","Số Ngày Nghỉ Phép","Lương 1 Ngày","Email"};

    public NhanVienDTO() {
    }

    public NhanVienDTO(String maNV, String tenNV, int gioiTinh, int soNgayPhep, int chucVu, Date ngaySinh, Date ngayVaoLam, String email, int luong1Ngay, int xuLy) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.soNgayPhep = soNgayPhep;
        this.chucVu = chucVu;
        this.ngaySinh = ngaySinh;
        this.ngayVaoLam = ngayVaoLam;
        this.email = email;
        this.luong1Ngay = luong1Ngay;
        this.xuLy = xuLy;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getSoNgayPhep() {
        return soNgayPhep;
    }

    public void setSoNgayPhep(int soNgayPhep) {
        this.soNgayPhep = soNgayPhep;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLuong1Ngay() {
        return luong1Ngay;
    }

    public void setLuong1Ngay(int luong1Ngay) {
        this.luong1Ngay = luong1Ngay;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }
}
