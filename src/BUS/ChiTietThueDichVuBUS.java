package BUS;

import java.util.List;
import DAO.Database;
import DTO.ChiTietThueDichVuDTO;

public class ChiTietThueDichVuBUS {
    private Database db;

    public ChiTietThueDichVuBUS() {
        db = new Database();
    }

    public List<ChiTietThueDichVuDTO> GetListChiTietDichVu(String maCTT) {
        String query = "select * from ChiTietThueDichVu where maCTT = '" + maCTT + "'";
        return db.getListCTTDV_DTO(query);
    }

    public void DeleteCTTDV(String maCTT, String maDV, String ngaySuDung) {
        String query = String.format("delete CHITIETTHUEDICHVU where maCTT = '%s' and maDV = '%s' and ngaySuDung = '%s'", maCTT, maDV, ngaySuDung);
        db.executeNonQuery(query);
    }

    public void SuaSoLuongCTTDV(String maCTT, String maDV, String ngaySuDung, String soLuong) {
        String query = String.format("update CHITIETTHUEDICHVU set soLuong = %s where maCTT = '%s' and maDV = '%s' and ngaySuDung = '%s'", soLuong, maCTT, maDV, ngaySuDung);
        db.executeNonQuery(query);
    }

    public void ThemCTTDV(String maCTT, String maDV, String ngaySuDung, String soLuong, String giaDV) {
        String query = String.format("insert into CHITIETTHUEDICHVU values ('%s','%s','%s',%s,%s)", maCTT, maDV, ngaySuDung, soLuong, giaDV);
        db.executeNonQuery(query);
    }

    public void SuaSoLuong(String maCTT, String maDV, String ngaySuDung, String soLuong) {
        String query = String.format("update CHITIETTHUEDICHVU set soLuong = soLuong + %s where maCTT = '%s' and maDV = '%s' and ngaySuDung = '%s'", soLuong, maCTT, maDV, ngaySuDung);
        db.executeNonQuery(query);
    }
}
