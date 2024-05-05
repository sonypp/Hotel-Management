package DTO;

import java.util.Date;

public class ChiTietThuePhongDTO {
    private String maCTT;
    private String maP;
    private Date ngayThue;
    private Date ngayTra;
    private Date ngayCheckOut;
    private int giaThue;
    private int loaiHinhThue; // 0 là thuê theo ngày, 1 là thuê theo giờ, 2 là khác (Chưa xác định ngày trả)
    private int tinhTrang; // 0 là chưa xử lý, 1 là đã lấy phòng, 2 là đã trả phòng

    public ChiTietThuePhongDTO() {
    }

    public ChiTietThuePhongDTO(String maCTT, String maP, Date ngayThue, Date ngayTra, Date ngayCheckOut, int loaiHinhThue, int giaThue, int tinhTrang) {
        this.maCTT = maCTT;
        this.maP = maP;
        this.ngayThue = ngayThue;
        this.ngayTra = ngayTra;
        this.ngayCheckOut = ngayCheckOut;
        this.giaThue = giaThue;
        this.loaiHinhThue = loaiHinhThue;
        this.tinhTrang = tinhTrang;
    }

    public String getMaCTT() {
        return maCTT;
    }

    public void setMaCTT(String maCTT) {
        this.maCTT = maCTT;
    }

    public String getMaP() {
        return maP;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public Date getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(Date ngayThue) {
        this.ngayThue = ngayThue;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public Date getNgayCheckOut() {
        return ngayCheckOut;
    }

    public void setNgayCheckOut(Date ngayCheckOut) {
        this.ngayCheckOut = ngayCheckOut;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getLoaiHinhThue() {
        return loaiHinhThue;
    }

    public void setLoaiHinhThue(int loaiHinhThue) {
        this.loaiHinhThue = loaiHinhThue;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}

