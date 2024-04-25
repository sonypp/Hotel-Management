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

    public int GetCountPQ() {
        String query = "select count(MaPQ) + 1 from PhanQuyen";
        return db.executeNonQueryGetInteger(query);
    }
}
