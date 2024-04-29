package BUS;

import DAO.Database;
import DTO.DSNhanVienDTO;
import DTO.TaiKhoanDTO;

import java.sql.Connection;
import java.sql.SQLException;
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
    
    public void TaoTaiKhoan(String taiKhoan, String maNV, String matKhau, int tinhTrang, int XuLy) {
        if (taiKhoan != null && !taiKhoan.isEmpty() && maNV != null && !maNV.isEmpty()) {
            String query = String.format("INSERT INTO TaiKhoan (taiKhoan, maNV, matKhau, tinhTrang, xuLy) VALUES ('%s', '%s', '%s', '0', '0')", taiKhoan, maNV, maNV);

            // Thực thi câu lệnh SQL
            db.executeNonQuery(query);
        } else {
            // Xử lý khi các tham số không hợp lệ
            System.out.println("Tên tài khoản hoặc mã nhân viên không hợp lệ.");
        }
    }

    public void SuaTaiKhoan(String taiKhoan, String newTaiKhoan) {
        // Kiểm tra xem tài khoản cũ có tồn tại không
        TaiKhoanDTO existingTK = GetTK(taiKhoan);
        if (existingTK.getTaiKhoan() != null) {
            // Kiểm tra xem tài khoản mới đã tồn tại chưa
            TaiKhoanDTO duplicateTK = GetTK(newTaiKhoan);
            if (duplicateTK.getTaiKhoan() == null || newTaiKhoan.equals(taiKhoan)) {
                // Cập nhật tên tài khoản
                String query = String.format("UPDATE TaiKhoan SET taiKhoan = '%s' WHERE taiKhoan = '%s'",
                                             newTaiKhoan, taiKhoan);
                db.executeNonQuery(query);
            } else {
                System.out.println("Tài khoản mới đã tồn tại trong cơ sở dữ liệu.");
            }
        } else {
            // Tài khoản không tồn tại, thông báo cho người dùng
            System.out.println("Tài khoản cần sửa không tồn tại trong cơ sở dữ liệu.");
        }
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
