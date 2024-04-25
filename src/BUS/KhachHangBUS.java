package BUS;

import DAO.Database;
import DTO.KhachHangDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class KhachHangBUS {
    private final Database db;

    public KhachHangBUS() {
        db = new Database();
    }

    public ResultSet getKhachHang() {
        String query = "select * from KHACHHANG where XuLy = 0";
        return db.getList(query);
    }

    public int GetKhachHangCount() {
        String query = "select count(*) from KHACHHANG";
        return db.executeNonQueryGetInteger(query);
    }

    public void addKhachHang(String makh, String tenkh, String cmnd, int gioitinh, String sdt, String quequan, String quoctich, Date ngaysinh) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ns = dateFormat.format(ngaysinh);
        String query = "insert into KHACHHANG values ('" + makh + "',N'" + tenkh + "', '" + cmnd + "', " + gioitinh + ", '" + sdt + "', N'" + quequan + "', N'" + quoctich + "', '" + ns + "', 0)";
        db.executeNonQuery(query);
    }

    public ResultSet findKhachHang(String makh, String tenkh, String cmnd, int gioitinh, String sdt, String quequan, String quoctich, Date ngaysinhtu, Date ngaysinhden) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String query = "select * from KHACHHANG where ";
        if (!makh.isEmpty()) {
            query += "maKH like '%" + makh + "%' and ";
        }
        if (!tenkh.isEmpty()) {
            query += "tenKH like N'%" + tenkh + "%' and ";
        }
        if (!cmnd.isEmpty()) {
            query += "CMND like N'%" + cmnd + "%' and ";
        }
        if (gioitinh != -1) {
            query += "gioiTinh = " + gioitinh + " and ";
        }
        if (!sdt.isEmpty()) {
            query += "SDT like N'%" + sdt + "%' and ";
        }
        if (!quequan.isEmpty()) {
            query += "queQuan like N'%" + quequan + "%' and ";
        }
        if (!quoctich.isEmpty()) {
            query += "quocTich like N'%" + quoctich + "%' and ";
        }
        if (ngaysinhtu != null) {
            String ngaysinhtuStr = dateFormat.format(ngaysinhtu);
            query += "ngaySinh >= '" + ngaysinhtuStr + "' and ";
        }
        if (ngaysinhden != null) {
            String ngaysinhdenStr = dateFormat.format(ngaysinhden);
            query += "ngaySinh <= '" + ngaysinhdenStr + "' and ";
        }
        query += "XuLy = 0";
        return db.getList(query);
    }

    public void deleteKhachHang(String makh) {
        String query = "update KHACHHANG set XuLy = 1 where maKH = '" + makh + "'";
        db.executeNonQuery(query);
    }

    public void UpdateKhachHang(String maKH, String tenKH, String cmnd, String gioiTinh, String sdt, String queQuan, String quocTich, String ngaySinh) {
        String query = "update KHACHHANG set tenKH = N'" + tenKH + "', CMND = '" + cmnd + "', gioiTinh = " + gioiTinh + ", SDT = '" + sdt + "', queQuan = N'" + queQuan + "', quocTich = N'" + quocTich + "', ngaySinh = '" + ngaySinh + "' where maKH = '" + maKH + "'";
        db.executeNonQuery(query);
    }

    public List<KhachHangDTO> GetDSKH() {
        String query = "select * from KHACHHANG";
        return db.getListKH_DTO(query);
    }

    public int GetCountAllKH() {
        String query = "Select Count(maKH)+1 from KhachHang";
        return db.executeNonQueryGetInteger(query);
    }

    public void InsertKhachHang(String maKH, String tenKh, String CMND, String gioiTinh, String sDT, String queQuan, String quocTich, String ngaySinh) {
        String query = "insert into KhachHang values ('" + maKH + "',N'" + tenKh + "','" + CMND + "'," + gioiTinh + ",'" + sDT + "',N'" + queQuan + "',N'" + quocTich + "','" + ngaySinh + "',0)";
        db.executeNonQuery(query);
    }

    public int SoLanThue(String maKH) {
        String query = "select count(chiTietthue.maKH) from CHITIETTHUE, KHACHHANG where CHITIETTHUE.maKH = KHACHHANG.maKH and Chitietthue.maKH = '" + maKH + "'";
        return db.executeNonQueryGetInteger(query);
    }
}
