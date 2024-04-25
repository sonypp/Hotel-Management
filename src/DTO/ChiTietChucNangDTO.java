package DTO;

public class ChiTietChucNangDTO {
    private String taiKhoan;
    private String maChucNang;

    public ChiTietChucNangDTO() {

    }

    public ChiTietChucNangDTO(String taiKhoan, String maChucNang) {
        this.taiKhoan = taiKhoan;
        this.maChucNang = maChucNang;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMaChucNang() {
        return maChucNang;
    }

    public void setMaChucNang(String maChucNang) {
        this.maChucNang = maChucNang;
    }
}
