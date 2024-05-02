package BUS;

import java.util.List;
import java.sql.Date;
import DAO.Database;
import DTO.ChiTietThueDTO;
import DTO.PhongDTO;

public class ChiTietThueBUS {
    private Database db;

    public ChiTietThueBUS() {
        db = new Database();
    }

    public List<ChiTietThueDTO> getDSChiTietThue() {
        String query = "Select * from CHITIETTHUE where xuLy = 0";
        return db.getListCTT_DTO(query);
    }

    public int GetCountAll(String dateNow) {
        String query = "Select COUNT(MaCTT)+1 from chiTietThue where cast(ngayLapPhieu as date) = '" + dateNow + "'";
        return db.executeNonQueryGetInteger(query);
    }

    public void InsertCTT(String maCTT, String maKH, String maNV, String ngayLapPhieu, String tienDatCoc) {
        String query = String.format("insert into CHITIETTHUE values('%s','%s','%s','%s',%d,0,0)", maCTT, maKH, maNV, ngayLapPhieu, tienDatCoc);
        db.executeNonQuery(query);
    }

    public ChiTietThueDTO getChiTietThue(String mactt) {
        String query = "Select * from CHITIETTHUE where maCTT='" + mactt + "'";
        return db.getCTT_DTO(query);
    }

    public void DeleteCTT(String maCTT) {
        String query = "Update ChiTietThue set xuLy = 1 where maCTT = '" + maCTT + "'";
        db.executeNonQuery(query);
    }

    public void SuaTinhTrangXuLy(String maCTT) {
        String query = "Update ChiTietThue set tinhTrangXuLy = 1 where maCTT = '" + maCTT + "'";
        db.executeNonQuery(query);
    }
}
