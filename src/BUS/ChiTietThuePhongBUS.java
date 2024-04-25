package BUS;

import java.util.List;
import DAO.Database;
import DTO.ChiTietThuePhongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietThuePhongBUS {
    private Database db;

    public ChiTietThuePhongBUS() {
        db = new Database();
    }

    public List<ChiTietThuePhongDTO> GetDSListCTTP(String maCTT) {
        String query = "select * from CHITIETTHUEPHONG where maCTT = '" + maCTT + "'";
        return db.getListCTTP_DTO(query);
    }

    public List<ChiTietThuePhongDTO> GetDSListCTTP() {
        String query = "select * from CHITIETTHUEPHONG";
        return db.getListCTTP_DTO(query);
    }

    public void InsertCTTP(boolean check, String maCTT, String maP, String ngayThue, String ngayTra, String loaiHinhThue, String giaThue) {
        String query;
        if (check) {
            query = String.format("insert into CHITIETTHUEPHONG values ('%s','%s','%s','%s',null,%s,%s,0)", maCTT, maP, ngayThue, ngayTra, loaiHinhThue, giaThue);
        } else {
            query = String.format("insert into CHITIETTHUEPHONG values ('%s','%s','%s',null,null,%s,%s,0)", maCTT, maP, ngayThue, loaiHinhThue, giaThue);
        }
        db.executeNonQuery(query);
    }

    public void DeleteCTTP(String maCTT, String maP, String ngayThue) {
        String query = String.format("delete ChiTietThuePhong where maCTT = '%s' and maP = '%s' and ngayThue = '%s'", maCTT, maP, ngayThue);
        db.executeNonQuery(query);
    }

    public void UpdateTinhTrang(String maCTT, String maP, String ngayThue, String tinhTrang) {
        String query = String.format("update ChiTietThuePhong set tinhTrang = %s where maCTT = '%s' and maP = '%s' and ngayThue = '%s'", tinhTrang, maCTT, maP, ngayThue);
        db.executeNonQuery(query);
    }

    public void UpdateCheckOut(boolean check, String maCTT, String maP, String ngayThue, String ngayCheckOut, String giaThue) {
        String query;
        if (check) {
            query = String.format("update ChiTietThuePhong set ngayCheckOut = '%s' where maCTT = '%s' and maP = '%s' and ngayThue = '%s'", ngayCheckOut, maCTT, maP, ngayThue);
        } else {
            query = String.format("update ChiTietThuePhong set ngayCheckOut = '%s', ngayTra = '%s', giaThue = %s where maCTT = '%s' and maP = '%s' and ngayThue = '%s'", ngayCheckOut, ngayCheckOut, giaThue, maCTT, maP, ngayThue);
        }
        db.executeNonQuery(query);
    }

    public ResultSet GetInfoRoom(String maP) {
        String query = "select TOP 1 CHITIETTHUE.maCTT, tenKH, ngayTra from KHACHHANG, CHITIETTHUE, CHITIETTHUEPHONG "
                + "where KHACHHANG.maKH = CHITIETTHUE.maKH "
                + "and CHITIETTHUE.maCTT = CHITIETTHUEPHONG.maCTT "
                + "and CHITIETTHUE.tinhTrangXuLy = 0 and maP = '" + maP + "' and CHITIETTHUEPHONG.tinhTrang = 1 "
                + "order by ngayThue asc";
        return db.getList(query);
    }
}
