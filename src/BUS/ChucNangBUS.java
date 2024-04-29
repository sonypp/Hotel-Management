package BUS;

import DAO.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChucNangBUS {
    private final Database db;

    public ChucNangBUS() {
        db = new Database();
    }

    public ResultSet GetTBChucNang(String maPQ) {
        String query = "select ChucNang.maChucNang, tenChucNang, case when TB.maChucNang is not null then 'true' else 'false' end as [Action]\r\nfrom CHUCNANG left join (select PHANQUYEN.maPQ, PHANQUYEN.tenPQ, CHITIETCHUCNANG.maChucNang from CHITIETCHUCNANG, PHANQUYEN\r\nwhere CHITIETCHUCNANG.maPQ = PHANQUYEN.maPQ and PhanQuyen.maPQ = '" + maPQ + "') as TB on CHUCNANG.maChucNang = TB.maChucNang";
        return db.getList(query);
    }

    public void XoaChucNang(String maPQ, String maChucNang) {
        String query = "delete CHITIETCHUCNANG where maPQ = '" + maPQ + "' and maChucNang = '" + maChucNang + "'";
        db.executeNonQuery(query);
    }

    public void ThemChucNang(String maPQ, String maChucNang) {
        String query = "insert into CHITIETCHUCNANG values ('" + maPQ + "','" + maChucNang + "')";
        db.executeNonQuery(query);
    }
    public ResultSet PhanQuyenQuanLy(String taiKhoan) {
        String query = "SELECT NHANVIEN.chucVu AS maPQ, CHUCNANG.maChucNang " +
                       "FROM TAIKHOAN " +
                       "JOIN NHANVIEN ON TAIKHOAN.maNV = NHANVIEN.maNV " +
                       "JOIN PHANQUYEN ON NHANVIEN.chucVu = PHANQUYEN.maPQ " +
                       "JOIN CHITIETCHUCNANG ON PHANQUYEN.maPQ = CHITIETCHUCNANG.maPQ " +
                       "JOIN CHUCNANG ON CHITIETCHUCNANG.maChucNang = CHUCNANG.maChucNang " +
                       "WHERE TAIKHOAN.taiKhoan = '" + taiKhoan + "'";
        ResultSet rs = db.getList(query);
        try {
            while (rs.next()) {
                String maPQ = rs.getString("maPQ");
                String maChucNang = rs.getString("maChucNang");
                // Thực hiện các hành động cần thiết với maPQ và maChucNang như lưu vào danh sách, kiểm tra, v.v.
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChucNangBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
		return rs;
    }

}
