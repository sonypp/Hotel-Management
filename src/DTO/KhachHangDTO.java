package DTO;

import java.util.Date;

public class KhachHangDTO {
    private String maKH;
    private String tenKH;
    private String cMND;
    private int gioiTinh; // 0 là nam 1 là nữ
    private String sDT;
    private String queQuan;
    private String quocTich;
    private Date ngaySinh;
    private int xuLy;

    public KhachHangDTO() {
    }

    public KhachHangDTO(String maKH, String tenKH, String cMND, int gioiTinh, String sDT, String queQuan, String quocTich, Date ngaySinh, int xuLy) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.cMND = cMND;
        this.gioiTinh = gioiTinh;
        this.sDT = sDT;
        this.queQuan = queQuan;
        this.quocTich = quocTich;
        this.ngaySinh = ngaySinh;
        this.xuLy = xuLy;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getCMND() {
        return cMND;
    }

    public void setCMND(String cMND) {
        this.cMND = cMND;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSDT() {
        return sDT;
    }

    public void setSDT(String sDT) {
        this.sDT = sDT;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }
}
