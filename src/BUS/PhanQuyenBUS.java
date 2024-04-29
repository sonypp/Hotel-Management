package BUS;

import DAO.Database;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhanQuyenBUS {
    private final Database db;

    public PhanQuyenBUS() {
        db = new Database();
    }

    public ResultSet GetListPQ() {
        String query = "select * from PhanQuyen";
        return db.getList(query);
    }

    public void ThemPhanQuyen(String maPQ, String tenPQ) {
        String query = "insert into PhanQuyen values ('" + maPQ + "',N'" + tenPQ + "')";
        db.executeNonQuery(query);
    }
    public void suaPQcuaNV(String maNV, int chucVu) throws SQLException {
        String query = "UPDATE NHANVIEN SET chucVu = ? WHERE maNV = ?";
        Object[] param = {chucVu, maNV};
        db.executeNonQuery(query, param);
    }

    public int GetCountPQ() {
        String query = "select count(MaPQ) + 1 from PhanQuyen";
        return db.executeNonQueryGetInteger(query);
    }
    public void CapNhatTrangThai(String tenPQ, String maChucNang, boolean check) {
    	String query = "";
    	Object[] params = {};
    	String maPQ = "";
		try {
			var result = db.executeQuery("SELECT * FROM PHANQUYEN WHERE tenPQ = N'" + tenPQ + "'");
			if(result.next())
			{
				maPQ = result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (check) {
        	if (!maPQ.equals(""))
        	{
	            query = "IF NOT EXISTS (SELECT 1 FROM CHITIETCHUCNANG WHERE maPQ = ? AND maChucNang = ?) " +
	                    "BEGIN " +
	                    "INSERT INTO CHITIETCHUCNANG (maPQ, maChucNang) VALUES (?, ?) " +
	                    "END";
	            params = new Object[] {maPQ, maChucNang, maPQ, maChucNang};
        	}
        } else {
            query = "DELETE FROM CHITIETCHUCNANG WHERE maPQ = ? AND maChucNang = ?";
            params = new Object[] {maPQ, maChucNang};
        }
        db.executeNonQuery(query, params);
    }



}
