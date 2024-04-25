package BUS;

import DAO.Database;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanBUS {
    private Database db;

    public TaiKhoanBUS() {
        db = new Database();
    }

    public List<TaiKhoanDTO> GetListTaiKhoan() {
        String query = "SELECT * FROM TAIKHOAN";
        return db.getListTK_DTO(query);
    }

    public TaiKhoanDTO GetTK(String taiKhoan) {
        TaiKhoanDTO tk = new TaiKhoanDTO();
        for (TaiKhoanDTO x : GetListTaiKhoan()) {
            if (x.getTaiKhoan().equals(taiKhoan)) {
                tk = x;
                return x;
            }
        }
        return tk;
    }

    public TaiKhoanDTO GetTKNV(String maNV) {
        TaiKhoanDTO tk = new TaiKhoanDTO();
        for (TaiKhoanDTO x : GetListTaiKhoan()) {
            if (x.getMaNV().equals(maNV)) {
                tk = x;
                return x;
            }
        }
        return tk;
    }

    public void ThemTaiKhoan(String taiKhoan, String maNV, String maPQ, String matKhau, String tinhTrang) {
        String query = String.format("insert into taikhoan values ('%s','%s','%s','%s',%s,0)", taiKhoan, maNV, maPQ, matKhau, tinhTrang);
        db.executeNonQuery(query);
    }

    public void XoaTaiKhoan(String taiKhoan) {
        String query = "delete TaiKhoan where taiKhoan = '" + taiKhoan + "'";
        db.executeNonQuery(query);
    }

    public void SuaPhanQuyen(String taiKhoan, String phanQuyen) {
        String query = "update TaiKhoan set maPQ = '" + phanQuyen + "' where taiKhoan = '" + taiKhoan + "'";
        db.executeNonQuery(query);
    }

    public void KhoaTaiKhoan(String taiKhoan) {
        String query = "update TaiKhoan set tinhTrang = 1 where taiKhoan = '" + taiKhoan + "'";
        db.executeNonQuery(query);
    }

    public void MoKhoaTaiKhoan(String taiKhoan) {
        String query = "update TaiKhoan set tinhTrang = 0 where taiKhoan = '" + taiKhoan + "'";
        db.executeNonQuery(query);
    }

    public void SuaMatKhau(String taiKhoan, String matKhau) {
        String query = "update TaiKhoan set matKhau = '" + matKhau + "' where taiKhoan = '" + taiKhoan + "'";
        db.executeNonQuery(query);
    }
}
