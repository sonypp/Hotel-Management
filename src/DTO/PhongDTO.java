package DTO;

public class PhongDTO {
    private String maP;
    private String tenP;
    private int loaiP; // 0 là VIP, 1 là Thường
    private int giaP;
    private int chiTietLoaiP; // 0 là Phòng đơn, 1 là Phòng đôi, 2 là Phòng gia đình
    private int tinhTrang; // 0 là Phòng trống, 1 là Phòng có khách, 2 là Phòng chưa được dọn
    private int hienTrang; // 0 là mới, 1 là cũ
    private int xuLy; // 0 là chưa xóa, 1 là đã xóa

    public PhongDTO() {}

    public PhongDTO(String maP, String tenP, int loaiP, int giaP, int chiTietLoaiP, int tinhTrang, int hienTrang, int xuLy) {
        this.maP = maP;
        this.tenP = tenP;
        this.loaiP = loaiP;
        this.giaP = giaP;
        this.chiTietLoaiP = chiTietLoaiP;
        this.tinhTrang = tinhTrang;
        this.hienTrang = hienTrang;
        this.xuLy = xuLy;
    }

    public String getMaP() {
        return maP;
    }

    public void setMaP(String maP) {
        this.maP = maP;
    }

    public String getTenP() {
        return tenP;
    }

    public void setTenP(String tenP) {
        this.tenP = tenP;
    }

    public int getLoaiP() {
        return loaiP;
    }

    public void setLoaiP(int loaiP) {
        this.loaiP = loaiP;
    }

    public int getGiaP() {
        return giaP;
    }

    public void setGiaP(int giaP) {
        this.giaP = giaP;
    }

    public int getChiTietLoaiP() {
        return chiTietLoaiP;
    }

    public void setChiTietLoaiP(int chiTietLoaiP) {
        this.chiTietLoaiP = chiTietLoaiP;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getHienTrang() {
        return hienTrang;
    }

    public void setHienTrang(int hienTrang) {
        this.hienTrang = hienTrang;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }
}
