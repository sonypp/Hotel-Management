package BUS;

import DAO.Database;
import DTO.DSNhanVienDTO;
import DTO.NhanVienDTO;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class NhanVienBUS {
    private final Database db;

    public NhanVienBUS() {
        db = new Database();
    }

    public List<NhanVienDTO> getAllNhanVien() {
        String query = "select * from NhanVien where XuLy = 0";
        return db.getListNV_DTO(query);
    }
    public List<DSNhanVienDTO> getAllDSNV() {
        String query = "SELECT NHANVIEN.maNV, NHANVIEN.tenNV, NHANVIEN.chucVu, TAIKHOAN.taiKhoan, " +
                "CASE WHEN TAIKHOAN.tinhTrang IS NULL THEN -1 ELSE TAIKHOAN.tinhTrang END AS tinhTrang " +
                "FROM NHANVIEN LEFT JOIN TAIKHOAN ON NHANVIEN.maNV = TAIKHOAN.maNV WHERE TAIKHOAN.xuLy = 0";
        return db.getListDSNV_DTO(query);
    }

    public int getNhanVienCount() {
        String query = "select count(*) from NhanVien";
        return db.executeNonQueryGetInteger(query);
    }
    public void addNhanVien(String maNV, String tenNV, int gioiTinh, int soNgayPhep, int chucVu, java.util.Date ngaySinh, java.util.Date ngayVaoLam, String email, int luong1Ngay) {
        String query = "insert into NhanVien values(?,?,?,?,?,?,?,?,?,?)";
        Object[] param = {maNV,tenNV,gioiTinh,soNgayPhep,chucVu,new Date(ngaySinh.getTime()),new Date(ngayVaoLam.getTime()),email,luong1Ngay,0};
        db.executeNonQuery(query,param);
    }

    public List<NhanVienDTO> findNhanVien(String maNV, String tenNV, String email, int gioiTinh, int chucVu, java.util.Date ngaySinhfrom, java.util.Date ngaySinhto, java.util.Date ngayVaoLamfrom, java.util.Date ngayVaoLamto, int luong1Ngaymin, int luong1Ngaymax, int soNgayPhepmin, int soNgayPhepmax) {
        ArrayList<Object> notNullObj = new ArrayList<>();
        String query = "select * from NhanVien where ";
    if (!email.isEmpty()) {
        query += "email like ? and ";
        notNullObj.add(email);
    }
    if (gioiTinh != -1){
        query += "gioiTinh = ? and ";
        notNullObj.add(gioiTinh);
    }
    if (chucVu != -1){
        query += "chucVu = ? and ";
        notNullObj.add(chucVu);
    }
    if (ngaySinhfrom != null){
        query += "ngaySinh >= ? and ";
        notNullObj.add(new Date(ngaySinhfrom.getTime()));
    }
    if (ngaySinhto != null){
        query += "ngaySinh <= ? and ";
        notNullObj.add(new Date(ngaySinhto.getTime()));
    }
    if (ngayVaoLamfrom != null){
        query += "ngayVaoLam >= ? and ";
        notNullObj.add(new Date(ngayVaoLamfrom.getTime()));
    }
    if (ngayVaoLamto != null){
        query += "ngayVaoLam <= ? and ";
        notNullObj.add(new Date(ngayVaoLamto.getTime()));
    }
    if (luong1Ngaymin != 0) {
        query += "luong1Ngay >= ? and ";
        notNullObj.add(luong1Ngaymin);
    }
    if (luong1Ngaymax != 0) {
        query += "luong1Ngay <= ? and ";
        notNullObj.add(luong1Ngaymax);
    }
    if (soNgayPhepmin != 0) {
        query += "soNgayPhep >= ? and ";
        notNullObj.add(soNgayPhepmin);
    }
    if (soNgayPhepmax != 0) {
        query += "soNgayPhep <= ? and ";
        notNullObj.add(soNgayPhepmax);
    }
    if (!maNV.isEmpty()) {
        query += "maNV like ? and";
        notNullObj.add(maNV);
    }
    if (!tenNV.isEmpty()) {
        query += "tenNV like ? and";
        notNullObj.add(tenNV);
    }
    query += " xuLy = 0";

        System.out.println(query);
        return db.getListNV_DTO(query,notNullObj.toArray());
    }
    public List<DSNhanVienDTO> timKiemNV(String maNV, String tenNV) {
        ArrayList<Object> params = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT NHANVIEN.maNV, NHANVIEN.tenNV, NHANVIEN.chucVu, TAIKHOAN.taiKhoan, TAIKHOAN.tinhTrang FROM NHANVIEN LEFT JOIN TAIKHOAN ON NHANVIEN.maNV = TAIKHOAN.maNV WHERE NHANVIEN.xuLy = 0");

        if (!maNV.isEmpty()) {
            query.append(" AND NHANVIEN.maNV LIKE ?");
            params.add(maNV);
        }
        if (!tenNV.isEmpty()) {
            query.append(" OR NHANVIEN.tenNV LIKE ?");
            params.add(tenNV);
        }

        return db.getListDSNV_DTO(query.toString(), params.toArray());
    }
    public void deleteNhanVien(String maNV) {
        String query = "update NhanVien set xuLy = 1 where maNV = ?";
        Object[] param = {maNV};
        db.executeNonQuery(query,param);
    }

    public void updateNhanVien(String maNV, String tenNV, int gioiTinh, int soNgayPhep, int chucVu, java.util.Date ngaySinh, java.util.Date ngayVaoLam, String email, int luong1Ngay) {
        ArrayList<Object> notNullObj = new ArrayList<>();
        String query = "update NhanVien set ";
        if (!tenNV.isEmpty()){
            query += "tenNV = ? , ";
            notNullObj.add(tenNV);
        }
        if (gioiTinh != -1){
            query += "gioiTinh = ? , ";
            notNullObj.add(gioiTinh);
        }
        if (soNgayPhep != -1){
            query += "soNgayPhep = ? , ";
            notNullObj.add(soNgayPhep);
        }
        if (chucVu != -1){
            query += "chucVu = ? , ";
            notNullObj.add(chucVu);
        }
        if (ngaySinh != null){
            query += "ngaySinh = ? , ";
            notNullObj.add(new Date(ngaySinh.getTime()));
        }
        if (ngayVaoLam != null){
            query += "ngayVaoLam = ? , ";
            notNullObj.add(new Date(ngayVaoLam.getTime()));
        }
        if (!email.isEmpty()) {
            query += "email = ? , ";
            notNullObj.add(email);
        }
        if (luong1Ngay != -1) {
            query += "luong1Ngay = ? , ";
            notNullObj.add(luong1Ngay);
        }
        query += "xuLy = 0"
                + "where maNV = ?";
        notNullObj.add(maNV);

        db.executeNonQuery(query,notNullObj.toArray());
    }
}
