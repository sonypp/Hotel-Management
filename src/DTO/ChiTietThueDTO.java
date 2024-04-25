package DTO;

import java.util.Date;

public class ChiTietThueDTO {
    private String maCTT;
    private String maKH;
    private String maNV;
    private Date ngayLapPhieu;
    private int tienDatCoc;
    private int tinhTrangXuLy; // 0 là Chưa xử lý, 1 là Đã xử lý
    private int xuLy; // 0 là chưa xóa, 1 là đã xóa

    public ChiTietThueDTO() {
    }

    public ChiTietThueDTO(String maCTT, String maKH, String maNV, Date ngayLapPhieu, int tienDatCoc, int tinhTrangXuLy, int xuLy) {
        this.maCTT = maCTT;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayLapPhieu = ngayLapPhieu;
        this.tienDatCoc = tienDatCoc;
        this.tinhTrangXuLy = tinhTrangXuLy;
        this.xuLy = xuLy;
    }

    public String getMaCTT() {
        return maCTT;
    }

    public void setMaCTT(String maCTT) {
        this.maCTT = maCTT;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayLapPhieu() {
        return ngayLapPhieu;
    }

    public void setNgayLapPhieu(Date ngayLapPhieu) {
        this.ngayLapPhieu = ngayLapPhieu;
    }

    public int getTienDatCoc() {
        return tienDatCoc;
    }

    public void setTienDatCoc(int tienDatCoc) {
        this.tienDatCoc = tienDatCoc;
    }

    public int getTinhTrangXuLy() {
        return tinhTrangXuLy;
    }

    public void setTinhTrangXuLy(int tinhTrangXuLy) {
        this.tinhTrangXuLy = tinhTrangXuLy;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }
}

