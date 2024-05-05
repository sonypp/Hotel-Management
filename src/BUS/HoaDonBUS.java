package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import DAO.Database;
import DTO.HoaDonDTO;
import GUI.DichVu.QLDVPanel;

public class HoaDonBUS {
	private final Database db;
	public HoaDonBUS() {
        db = new Database();
    }
	
	public int TongTienDV() {
        String query = "SELECT SUM(SoLuong*giaDV) FROM HOADON, CHITIETTHUEDICHVU " +
                       "WHERE HOADON.maCTT = CHITIETTHUEDICHVU.maCTT";
        return db.executeNonQueryGetInteger(query);
	}
	
	public int TongTienPhong() {
	    String query = "SELECT SUM(giaThue) FROM HOADON, CHITIETTHUEPHONG " +
	                   "WHERE HOADON.maCTT = CHITIETTHUEPHONG.maCTT";
	    return db.executeNonQueryGetInteger(query);
	}
	
	public int TongDoanhThu() {
	    String query = "SELECT SUM(TongTien) AS TongTien FROM " +
	                   "(SELECT HOADON.maHD, HOADON.maCTT, tenNV, " +
	                   "CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END AS TongTienPhong, " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END AS TongTienDV, " +
	                   "giamGia, phuThu, " +
	                   "SUM(CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END + " +
	                   "((CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END) * phuThu / 100) - " +
	                   "((CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END) * giamGia / 100)) AS TongTien, " +
	                   "ngayThanhToan, phuongThucThanhToan " +
	                   "FROM HOADON " +
	                   "INNER JOIN NHANVIEN ON HOADON.maNV = NHANVIEN.maNV " +
	                   "LEFT JOIN (SELECT maHD, SUM(giaThue) AS tienPhong FROM CHITIETTHUEPHONG, HOADON " +
	                   "WHERE HOADON.maCTT = CHITIETTHUEPHONG.maCTT GROUP BY HOADON.maHD) AS TIENPHONG " +
	                   "ON HOADON.maHD = TIENPHONG.maHD " +
	                   "LEFT JOIN (SELECT maHD, SUM(giaDV * SoLuong) AS tienDichVu FROM CHITIETTHUEDICHVU, HOADON " +
	                   "WHERE HOADON.maCTT = CHITIETTHUEDICHVU.maCTT GROUP BY HOADON.maHD) AS TIENDICHVU " +
	                   "ON HOADON.maHD = TIENDICHVU.maHD " +
	                   "GROUP BY HOADON.maHD, HOADON.maCTT, tenNV, TIENPHONG.tienPhong, TIENDICHVU.tienDichVu, " +
	                   "giamGia, phuThu, ngayThanhToan, phuongThucThanhToan) AS HoaDon";
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongPhuThu() {
	    String query = "SELECT SUM((TongTienPhong + TongTienDV) * phuThu / 100) AS TongTien " +
	                   "FROM (SELECT HOADON.maHD, HOADON.maCTT, tenNV, " +
	                   "CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END AS TongTienPhong, " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END AS TongTienDV, " +
	                   "giamGia, phuThu, " +
	                   "SUM(CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END + " +
	                   "((CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END) * phuThu / 100) - " +
	                   "((CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END) * giamGia / 100)) AS TongTien, " +
	                   "ngayThanhToan, phuongThucThanhToan " +
	                   "FROM HOADON " +
	                   "INNER JOIN NHANVIEN ON HOADON.maNV = NHANVIEN.maNV " +
	                   "LEFT JOIN (SELECT maHD, SUM(giaThue) AS tienPhong FROM CHITIETTHUEPHONG, HOADON " +
	                   "WHERE HOADON.maCTT = CHITIETTHUEPHONG.maCTT GROUP BY HOADON.maHD) AS TIENPHONG " +
	                   "ON HOADON.maHD = TIENPHONG.maHD " +
	                   "LEFT JOIN (SELECT maHD, SUM(giaDV * SoLuong) AS tienDichVu FROM CHITIETTHUEDICHVU, HOADON " +
	                   "WHERE HOADON.maCTT = CHITIETTHUEDICHVU.maCTT GROUP BY HOADON.maHD) AS TIENDICHVU " +
	                   "ON HOADON.maHD = TIENDICHVU.maHD " +
	                   "GROUP BY HOADON.maHD, HOADON.maCTT, tenNV, TIENPHONG.tienPhong, TIENDICHVU.tienDichVu, " +
	                   "giamGia, phuThu, ngayThanhToan, phuongThucThanhToan) AS HoaDon";
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongGiamGia() {
	    String query = "SELECT SUM((TongTienPhong + TongTienDV) * giamGia / 100) AS TongTien " +
	                   "FROM (SELECT HOADON.maHD, HOADON.maCTT, tenNV, " +
	                   "CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END AS TongTienPhong, " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END AS TongTienDV, " +
	                   "giamGia, phuThu, " +
	                   "SUM(CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END + " +
	                   "((CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END) * phuThu / 100) - " +
	                   "((CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
	                   "CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END) * giamGia / 100)) AS TongTien, " +
	                   "ngayThanhToan, phuongThucThanhToan " +
	                   "FROM HOADON " +
	                   "INNER JOIN NHANVIEN ON HOADON.maNV = NHANVIEN.maNV " +
	                   "LEFT JOIN (SELECT maHD, SUM(giaThue) AS tienPhong FROM CHITIETTHUEPHONG, HOADON " +
	                   "WHERE HOADON.maCTT = CHITIETTHUEPHONG.maCTT GROUP BY HOADON.maHD) AS TIENPHONG " +
	                   "ON HOADON.maHD = TIENPHONG.maHD " +
	                   "LEFT JOIN (SELECT maHD, SUM(giaDV * SoLuong) AS tienDichVu FROM CHITIETTHUEDICHVU, HOADON " +
	                   "WHERE HOADON.maCTT = CHITIETTHUEDICHVU.maCTT GROUP BY HOADON.maHD) AS TIENDICHVU " +
	                   "ON HOADON.maHD = TIENDICHVU.maHD " +
	                   "GROUP BY HOADON.maHD, HOADON.maCTT, tenNV, TIENPHONG.tienPhong, TIENDICHVU.tienDichVu, " +
	                   "giamGia, phuThu, ngayThanhToan, phuongThucThanhToan) AS HoaDon";
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongLoaiPhong(String fromNgay, String toNgay) {
	    String query = "SELECT COUNT(maCTT) AS TongPhong FROM CHITIETTHUEPHONG, PHONG " +
	                   "WHERE PHONG.maP = CHITIETTHUEPHONG.maP " +
	                   "AND CAST(CHITIETTHUEPHONG.ngayThue AS DATE) >= '" + fromNgay + "' " +
	                   "AND CAST(CHITIETTHUEPHONG.ngayThue AS DATE) <= '" + toNgay + "'";
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongLoaiPhongVip(String fromNgay, String toNgay) {
	    String query = "SELECT COUNT(maCTT) AS TongPhong FROM CHITIETTHUEPHONG, PHONG " +
	                   "WHERE PHONG.maP = CHITIETTHUEPHONG.maP " +
	                   "AND CAST(ngayThue AS DATE) >= '" + fromNgay + "' " +
	                   "AND CAST(ngayThue AS DATE) <= '" + toNgay + "' " +
	                   "AND loaiP = 0";
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongLoaiPhongThuong(String fromNgay, String toNgay) {
	    String query = "SELECT COUNT(maCTT) AS TongPhong FROM CHITIETTHUEPHONG, PHONG " +
	                   "WHERE PHONG.maP = CHITIETTHUEPHONG.maP " +
	                   "AND CAST(ngayThue AS DATE) >= '" + fromNgay + "' " +
	                   "AND CAST(ngayThue AS DATE) <= '" + toNgay + "' " +
	                   "AND loaiP = 1";
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongLoaiPhongDon(String fromNgay, String toNgay) {
	    String query = "select count(maCTT) as TongPhong from CHITIETTHUEPHONG, PHONG where PHONG.maP = CHITIETTHUEPHONG.maP "
	                 + "and cast(ngayThue as date) >= '" + fromNgay + "' and cast(ngayThue as date) <= '" + toNgay + "' and chiTietLoaiP = 0";
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongLoaiPhongDoi(String fromNgay, String toNgay) {
	    String query = "select count(maCTT) as TongPhong from CHITIETTHUEPHONG, PHONG where PHONG.maP = CHITIETTHUEPHONG.maP "
	                 + "and cast(ngayThue as date) >= '" + fromNgay + "' and cast(ngayThue as date) <= '" + toNgay + "' and chiTietLoaiP = 1";
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongLoaiPhongGiaDinh(String fromNgay, String toNgay) {
	    String query = "select count(maCTT) as TongPhong from CHITIETTHUEPHONG, PHONG where PHONG.maP = CHITIETTHUEPHONG.maP "
	                 + "and cast(ngayThue as date) >= '" + fromNgay + "' and cast(ngayThue as date) <= '" + toNgay + "' and chiTietLoaiP = 2";
	    return db.executeNonQueryGetInteger(query);
	}
	
	public int TongTienPhongTrongMotNgay(String day, String month, String year) {
	    String query = "select case when SUM(TongTienPhong) is not null then SUM(TongTienPhong) else 0 end as TP from (select HOADON.maHD, HOADON.maCTT, tenNV, " +
	            "case when tienPhong is not null then tienPhong else 0 end as TongTienPhong, " +
	            "case when tienDichVu is not null then tienDichVu else 0 end as TongTienDV, giamGia, phuThu, " +
	            "SUM(case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end + (case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end)*phuThu/100-(case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end)*giamGia/100) as TongTien,ngayThanhToan, phuongThucThanhToan " +
	            "from HOADON inner join NHANVIEN on HOADON.maNV = NHANVIEN.maNV left join (select maHD, sum(giaThue) as tienPhong from CHITIETTHUEPHONG, HOADON where HOADON.maCTT = CHITIETTHUEPHONG.maCTT " +
	            "group by HOADON.maHD) as TIENPHONG on HOADON.maHD = TIENPHONG.maHD left join " +
	            "(select maHD, sum(giaDV*SoLuong) as tienDichVu from CHITIETTHUEDICHVU, HOADON where HOADON.maCTT = CHITIETTHUEDICHVU.maCTT " +
	            "group by HOADON.maHD) as TIENDICHVU on HOADON.maHD = TIENDICHVU.maHD " +
	            "group by HOADON.maHD, HOADON.maCTT, tenNV, TIENPHONG.tienPhong, TIENDICHVU.tienDichVu, giamGia, phuThu, ngayThanhToan, phuongThucThanhToan) as TBHoaDon " +
	            "where Year(ngayThanhToan) = " + year + " and Month(ngayThanhToan) = " + month + " and Day(ngayThanhToan) = " + day;
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongTienDichVuTrongMotNgay(String day, String month, String year) {
	    String query = "select case when SUM(TongTienDV) is not null then SUM(TongTienDV) else 0 end as TP from (select HOADON.maHD, HOADON.maCTT, tenNV, " +
	            "case when tienPhong is not null then tienPhong else 0 end as TongTienPhong, " +
	            "case when tienDichVu is not null then tienDichVu else 0 end as TongTienDV, giamGia, phuThu, " +
	            "SUM(case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end + (case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end)*phuThu/100-(case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end)*giamGia/100) as TongTien,ngayThanhToan, phuongThucThanhToan " +
	            "from HOADON inner join NHANVIEN on HOADON.maNV = NHANVIEN.maNV left join (select maHD, sum(giaThue) as tienPhong from CHITIETTHUEPHONG, HOADON where HOADON.maCTT = CHITIETTHUEPHONG.maCTT " +
	            "group by HOADON.maHD) as TIENPHONG on HOADON.maHD = TIENPHONG.maHD left join " +
	            "(select maHD, sum(giaDV*SoLuong) as tienDichVu from CHITIETTHUEDICHVU, HOADON where HOADON.maCTT = CHITIETTHUEDICHVU.maCTT " +
	            "group by HOADON.maHD) as TIENDICHVU on HOADON.maHD = TIENDICHVU.maHD " +
	            "group by HOADON.maHD, HOADON.maCTT, tenNV, TIENPHONG.tienPhong, TIENDICHVU.tienDichVu, giamGia, phuThu, ngayThanhToan, phuongThucThanhToan) as TBHoaDon " +
	            "where Year(ngayThanhToan) = " + year + " and Month(ngayThanhToan) = " + month + " and Day(ngayThanhToan) = " + day;
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongTienPhongTrongMotThang(String month, String year) {
	    String query = "select case when SUM(TongTienPhong) is not null then SUM(TongTienPhong) else 0 end as TP from (select HOADON.maHD, HOADON.maCTT, tenNV, " +
	            "case when tienPhong is not null then tienPhong else 0 end as TongTienPhong, " +
	            "case when tienDichVu is not null then tienDichVu else 0 end as TongTienDV, giamGia, phuThu, " +
	            "SUM(case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end + " +
	            "(case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end)*phuThu/100-" +
	            "(case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end)*giamGia/100) as TongTien, " +
	            "ngayThanhToan, phuongThucThanhToan " +
	            "from HOADON inner join NHANVIEN on HOADON.maNV = NHANVIEN.maNV left join (select maHD, sum(giaThue) as tienPhong from CHITIETTHUEPHONG, HOADON where HOADON.maCTT = CHITIETTHUEPHONG.maCTT " +
	            "group by HOADON.maHD) as TIENPHONG on HOADON.maHD = TIENPHONG.maHD left join " +
	            "(select maHD, sum(giaDV*SoLuong) as tienDichVu from CHITIETTHUEDICHVU, HOADON where HOADON.maCTT = CHITIETTHUEDICHVU.maCTT " +
	            "group by HOADON.maHD) as TIENDICHVU on HOADON.maHD = TIENDICHVU.maHD " +
	            "group by HOADON.maHD, HOADON.maCTT, tenNV, TIENPHONG.tienPhong, TIENDICHVU.tienDichVu, giamGia, phuThu, ngayThanhToan, phuongThucThanhToan) as TBHoaDon " +
	            "where Year(ngayThanhToan) = " + year + " and Month(ngayThanhToan) = " + month;
	    return db.executeNonQueryGetInteger(query);
	}

	public int TongTienDichVuTrongMotThang(String month, String year) {
	    String query = "select case when SUM(TongTienDV) is not null then SUM(TongTienDV) else 0 end as TP from (select HOADON.maHD, HOADON.maCTT, tenNV, " +
	            "case when tienPhong is not null then tienPhong else 0 end as TongTienPhong, " +
	            "case when tienDichVu is not null then tienDichVu else 0 end as TongTienDV, giamGia, phuThu, " +
	            "SUM(case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end + " +
	            "(case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end)*phuThu/100-" +
	            "(case when tienPhong is not null then tienPhong else 0 end + case when tienDichVu is not null then tienDichVu else 0 end)*giamGia/100) as TongTien, " +
	            "ngayThanhToan, phuongThucThanhToan " +
	            "from HOADON inner join NHANVIEN on HOADON.maNV = NHANVIEN.maNV left join (select maHD, sum(giaThue) as tienPhong from CHITIETTHUEPHONG, HOADON where HOADON.maCTT = CHITIETTHUEPHONG.maCTT " +
	            "group by HOADON.maHD) as TIENPHONG on HOADON.maHD = TIENPHONG.maHD left join " +
	            "(select maHD, sum(giaDV*SoLuong) as tienDichVu from CHITIETTHUEDICHVU, HOADON where HOADON.maCTT = CHITIETTHUEDICHVU.maCTT " +
	            "group by HOADON.maHD) as TIENDICHVU on HOADON.maHD = TIENDICHVU.maHD " +
	            "group by HOADON.maHD, HOADON.maCTT, tenNV, TIENPHONG.tienPhong, TIENDICHVU.tienDichVu, giamGia, phuThu, ngayThanhToan, phuongThucThanhToan) as TBHoaDon " +
	            "where Year(ngayThanhToan) = " + year + " and Month(ngayThanhToan) = " + month;
	    return db.executeNonQueryGetInteger(query);
	}

}
