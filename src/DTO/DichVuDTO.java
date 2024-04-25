package DTO;

public class DichVuDTO {
    private String maDV;
    private String tenDV;
    private String loaiDV; // 0 là Dịch vụ ăn uống 1 là Dịch vụ chăm sóc sắc đẹp 2 là Dịch vụ giải trí, 3 là Dịch vụ tổ chức tiệc
    private int giaDV;
    private int xuLy;

    public DichVuDTO() {
    }

    public DichVuDTO(String maDV, String tenDV, String loaiDV, int giaDV, int xuLy) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.loaiDV = loaiDV;
        this.giaDV = giaDV;
        this.xuLy = xuLy;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getLoaiDV() {
        return loaiDV;
    }

    public void setLoaiDV(String loaiDV) {
        this.loaiDV = loaiDV;
    }

    public int getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(int giaDV) {
        this.giaDV = giaDV;
    }

    public int getXuLy() {
        return xuLy;
    }

    public void setXuLy(int xuLy) {
        this.xuLy = xuLy;
    }
}

