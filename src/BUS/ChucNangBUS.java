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
        String query = "select ChucNang.maChucNang, tenChucNang, case when TB.maChucNang is not null then 'true' else 'false' end as [Action]\r\nfrom CHUCNANG left join (select PHANQUYEN.maPQ, PHANQUYEN.tenPQ, CHHITIETCHUCNANG.maChucNang from CHHITIETCHUCNANG, PHANQUYEN\r\nwhere CHHITIETCHUCNANG.maPQ = PHANQUYEN.maPQ and PhanQuyen.maPQ = '" + maPQ + "') as TB on CHUCNANG.maChucNang = TB.maChucNang";
        return db.getList(query);
    }

    public void XoaChucNang(String maPQ, String maChucNang) {
        String query = "delete CHHITIETCHUCNANG where maPQ = '" + maPQ + "' and maChucNang = '" + maChucNang + "'";
        db.executeNonQuery(query);
    }

    public void ThemChucNang(String maPQ, String maChucNang) {
        String query = "insert into CHHITIETCHUCNANG values ('" + maPQ + "','" + maChucNang + "')";
        db.executeNonQuery(query);
    }
}
