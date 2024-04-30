package BUS;

import DAO.Database;
import DTO.PhongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongBUS {
    private final Database db;
    

    public PhongBUS() {
        db = new Database();
    }

    public ResultSet getListPhong() {
        String query = "select * from PHONG where XuLy = 0";
        return db.getList(query);
    }

    public List<PhongDTO> getListPhong_DTO() {
        String query = "select * from PHONG where xuLY = 0";
        return db.getListPhongDTO(query);
    }

    public int getCountPhong() {
        String query = "select COUNT(MaP) from PHONG";
        return db.executeNonQueryGetInteger(query);
    }

    public void ThemPhong(String maP, String tenP, String loaiP, String giaP, String chiTietLoaiP, String tinhTrang, String hienTrang) {
        String query = String.format("insert into PHONG values ('%s',N'%s',%s,%s,%s,%s,%s,%s)", maP, tenP, loaiP, giaP, chiTietLoaiP, tinhTrang, hienTrang, 0);
        db.executeNonQuery(query);
    }

    public void SuaPhong(String maP, String tenP, String loaiP, String giaP, String chiTietLoaiP, String hienTrang) {
        String query = String.format("UPDATE PHONG SET tenP = N'%s', loaiP = '%s', giaP = '%s', chiTietLoaiP = '%s', hienTrang = '%s' WHERE maP = '%s'", tenP, loaiP, giaP, chiTietLoaiP, hienTrang, maP);
        db.executeNonQuery(query);
    }
    

    public void XoaPhong(String maP) {
        String query = "Update PHONG set xuLy = 1 where maP = '" + maP + "'";
        db.executeNonQuery(query);
    }

    public void SuaTinhTrang(String maP, String tinhTrang) {
        String query = String.format("Update PHONG set tinhTrang = %s where maP = '%s'", tinhTrang, maP);
        db.executeNonQuery(query);
    }
    public List<PhongDTO> FindPhong(String maP, String tenP, int loaiP, int chitietloaiP, int giaPhongMin, int giaPhongMax, int tinhtrang, int hientrang)
    {
    	ArrayList<Object> notNullObj = new ArrayList<>();
    	String query = "select * from PHONG where ";
    	if (!maP.isEmpty())
    	{
    		query += "maP LIKE  ? and ";
    		notNullObj.add(maP);
    	}
    	if (!tenP.isEmpty())
    	{
    		query += "tenP LIKE  ? and ";
    		notNullObj.add(tenP);
    			
    	}
    	if (loaiP != -1)
    	{
    		query += "loaiP = ? and ";
    		notNullObj.add(loaiP);
    	}
    	if (chitietloaiP != -1)
    	{
    		query += "chiTietLoaiP = ? and ";
    		notNullObj.add(chitietloaiP);
    	}
    	if (tinhtrang != -1)
    	{
    		query += "tinhTrang = ? and ";
    		notNullObj.add(loaiP);
    	}
    	if (giaPhongMin != 0)
    	{
    		query += "giaP >= ? and ";
    		notNullObj.add(giaPhongMin);
    	}
    	if (giaPhongMax != 0)
    	{
    		query += "giaP <= ? and ";
    		notNullObj.add(giaPhongMax);
    	}
    	if (hientrang != -1)
    	{
    		query+= "hienTrang = ? and ";
    		notNullObj.add(hientrang);
    	}
    	query += "xuLy = 0";
    	System.out.println(query);
    	return db.getListPhongDTO(query, notNullObj.toArray());
    }
    
    public void donPhong(String maP)
    {
    	var query = "UPDATE PHONG SET tinhtrang = 0 WHERE maP = ?";
    	db.executeNonQuery(query, new Object[]{maP});
    }
    
    public List<PhongDTO> getValidRooms(String ngayThue, String ngayTra, int loaiP, int ctlp, int tinhtrang, int hientrang, int giaTu, int giaDen) {
        String query = "SELECT room.* " +
                       "FROM CHITIETTHUEPHONG AS cttp " +
                       "JOIN CHITIETTHUE AS ctt ON cttp.MaCTT = ctt.MaCTT " +
                       "JOIN PHONG AS room ON cttp.MaP = room.MaP " +
                       "WHERE ctt.XuLy = 0 " +
                       "  AND ctt.TinhTrangXuLy = 0 " +
                       "  AND cttp.NgayThue <= '" + ngayThue + "' " +
                       "  AND (cttp.NgayTra >= '" + ngayTra + "' OR cttp.NgayTra IS NULL) " +
                       "  AND room.TinhTrang = 0 " +
                       "  AND NOT EXISTS (" +
                       "      SELECT 1 " +
                       "      FROM CHITIETTHUEPHONG AS cttph " +
                       "      JOIN CHITIETTHUE AS ctt ON cttph.MaCTT = ctt.MaCTT " +
                       "      WHERE cttph.MaP = room.MaP " +
                       "        AND ctt.XuLy = 0 " +
                       "        AND ctt.TinhTrangXuLy = 0 " +
                       "        AND cttph.NgayThue <= '" + ngayThue + "' " +
                       "        AND (cttph.NgayTra >= '" + ngayTra + "' OR cttph.NgayTra IS NULL) " +
                       "  ) ";
        if(loaiP != -1)
        	query += "  AND (room.LoaiP = " + loaiP + ") ";
        if(ctlp != -1)
            query += "  AND (room.ChiTietLoaiP = " + ctlp + ") ";
        if(tinhtrang != -1)
            query += "  AND (room.TinhTrang = " + tinhtrang + ") ";
        if(hientrang != -1)
            query += "  AND (room.HienTrang = " + hientrang + ") ";
        query += "  AND (room.GiaP >= " + giaTu + " AND room.GiaP <= " + giaDen + ")";

        return db.getListPhongDTO(query);
    }



}
