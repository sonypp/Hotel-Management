package DTO;

import java.util.Date;

public class HoaDonDTO {
    private String maHD;
    private String maCTT;
    private int giamGia;
    private int phuThu;
    private Date ngayThanhToan;
    private int phuongThucThanhToan; // 0 là tiền mặt, 1 là chuyển khoản,...
    private int xuLy; // 0 là chưa xóa, 1 là đã xóa

    public HoaDonDTO() {
    }

    public HoaDonDTO(String maHD, String maCTT, int giamGia, int phuThu, Date ngayThanhToan, int phuongThucThanhToan, int xuLy) {
        this.maHD = maHD;
        this.maCTT = maCTT;
        this.giamGia = giamGia;
        this.phuThu = phuThu;
        this.ngayThanhToan = ngayThanhToan;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.xuLy = xuLy;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaCTT() {
        return maCTT;
    }

    public void setMaCTT(String maCTT) {
        this.maCTT = maCTT;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public int getPhuThu() {
        return phuThu;
    }

    public void setPhuThu(int phuThu) {
        this.phuThu = phuThu;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(int phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }
}
