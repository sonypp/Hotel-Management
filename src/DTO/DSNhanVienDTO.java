package DTO;

public class DSNhanVienDTO {
    private String maNV;
    private String tenNV;
    private String taiKhoan;
    private String chucVu;
    private int tinhTrang; // Tình trạng xử lý

    // Constructor
    public DSNhanVienDTO() {}
    public DSNhanVienDTO(String maNV, String tenNV, String chucVu, String taiKhoan, int tinhTrang) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.taiKhoan = taiKhoan;
        this.chucVu = chucVu;
        this.tinhTrang = tinhTrang;
    }

	// Getter và setter cho các thuộc tính
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

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
    public String getchucVu()
    {
    	return chucVu;
    }

    public void setchucVu()
    {
    	this.chucVu = chucVu;
    }
    public int gettinhTrang() {
        return tinhTrang;
    }

    public void settinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
