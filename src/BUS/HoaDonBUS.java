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
	private Database db;
	private String query = "WITH PhongThue AS (" +
		    "SELECT " +
		    "    CTP.maCTT," +
		    "    SUM(P.giaP * DATEDIFF(day, CTP.ngayThue, CTP.ngayTra)) AS TongTienPhong " +
		    "FROM " +
		    "    CHITIETTHUEPHONG CTP INNER JOIN PHONG P ON CTP.maP = P.maP " +
		    "GROUP BY CTP.maCTT " +
		    ")" +
		    "" +
		    "UPDATE " +
		    "    CHITIETTHUEPHONG " +	
		    "SET " +
		    "    giaThue = PhongThue.TongTienPhong " +
		    "FROM (" +
		    "    SELECT " +
		    "        CTP.maCTT," +
		    "        SUM(P.giaP * DATEDIFF(day, CTP.ngayThue, CTP.ngayTra)) AS TongTienPhong " +
		    "    FROM " +
		    "        CHITIETTHUEPHONG CTP INNER JOIN PHONG P ON CTP.maP = P.maP " +
		    "    GROUP BY CTP.maCTT ) AS PhongThue " +
		    "   WHERE " +
		    "       CHITIETTHUEPHONG.maCTT = PhongThue.maCTT;" +
		    "" +
		    "SELECT " +
		    "    HOADON.maHD, " +
		    "    HOADON.maCTT, " +
		    "    NHANVIEN.tenNV, " +
		    "    CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END AS TongTienPhong, " +
		    "    CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END AS TongTienDV, " +
		    "    HOADON.giamGia, " +
		    "    HOADON.phuThu, " +
		    "    CAST(" +
		    "        SUM(" +
		    "            CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
		    "            CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END + " +
		    "            ( " +
		    "                CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
		    "                CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END " +
		    "            ) * HOADON.phuThu / 100 - " +
		    "            ( " +
		    "                CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + " +
		    "                CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END " +
		    "            ) * HOADON.giamGia / 100 " +
		    "        ) AS numeric(18, 2) " +
		    "    ) AS TongTien, " +
		    "    HOADON.ngayThanhToan, " +
		    "    HOADON.phuongThucThanhToan " +
		    "FROM HOADON " +
		    "    INNER JOIN CHITIETTHUE ON HOADON.maCTT = CHITIETTHUE.maCTT " +
		    "    INNER JOIN NHANVIEN ON CHITIETTHUE.maNV = NHANVIEN.maNV " +
		    "    LEFT JOIN ( " +
		    "        SELECT " +
		    "            maHD, SUM(giaThue * DATEDIFF(day, ngayThue, ngayTra)) AS tienPhong " +
		    "        FROM " +
		    "            CHITIETTHUEPHONG " +
		    "            INNER JOIN HOADON ON CHITIETTHUEPHONG.maCTT = HOADON.maCTT " +
		    "        GROUP BY maHD " +
		    "        ) AS TIENPHONG ON HOADON.maHD = TIENPHONG.maHD " +
		    "    LEFT JOIN ( " +
		    "        SELECT " +
		    "            maHD, SUM(giaDV * SoLuong) AS tienDichVu " +
		    "        FROM " +
		    "            CHITIETTHUEDICHVU " +
		    "            INNER JOIN HOADON ON CHITIETTHUEDICHVU.maCTT = HOADON.maCTT " +
		    "        GROUP BY maHD " +
		    "        ) AS TIENDICHVU ON HOADON.maHD = TIENDICHVU.maHD ";

	public HoaDonBUS() {
		db = new Database();
	}
	
	public ResultSet getListHoaDon() {
		return db.getList(this.query + "GROUP BY HOADON.maHD, HOADON.maCTT, NHANVIEN.tenNV, TIENPHONG.tienPhong, TIENDICHVU.tienDichVu, HOADON.giamGia, HOADON.phuThu, HOADON.ngayThanhToan, HOADON.phuongThucThanhToan;");
	}
	
	public int[] queryPriceWithMinAndMax(String txtPrice) {
	    int minGia = 0;
	    int maxGia = Integer.MAX_VALUE;

	    try {
	        if (txtPrice.contains("Dưới")) {
	            // Trường hợp "Dưới 50.000 VND"
	            String minGiaStr = txtPrice.replaceAll("[^\\d]", "");
	            maxGia = Integer.parseInt(minGiaStr) - 1; // Trừ 1 đơn vị để đảm bảo giá trị dưới
	        } else if (txtPrice.contains("Trên")) {
	            // Trường hợp "Trên 1.000.000 VND"
	            String maxGiaStr = txtPrice.replaceAll("[^\\d]", "");
	            minGia = Integer.parseInt(maxGiaStr) + 1; // Cộng 1 đơn vị để đảm bảo giá trị trên
	        } else {
	            // Trường hợp "50.000 đến 100.000" (có thể giữ nguyên mã cũ)
	            String[] parts = txtPrice.split(" đến ");
	            String minGiaDVStr = parts[0].replaceAll("[^\\d]", ""); // Lấy phần số đầu tiên và loại bỏ ký tự không phải số
	            String maxGiaDVStr = parts[1].replaceAll("[^\\d]", ""); // Lấy phần số thứ hai và loại bỏ ký tự không phải số
	            
	            minGia = Integer.parseInt(minGiaDVStr);
	            maxGia = Integer.parseInt(maxGiaDVStr);
	        }
	    } catch (NumberFormatException e) {
	        // Xử lý ngoại lệ trong trường hợp không thể chuyển đổi chuỗi thành số nguyên
	        e.printStackTrace();
	    }

	    return new int[]{minGia, maxGia};
	}

	public ResultSet filterListHoaDon(String maHD, String maCTT, Date ngayBatDau, Date ngayKetThuc, String tenNhanVien, String giamGia, String phuThu, String phuongThucTT, String TongTien, String TongTienPhong, String TongTienDV) {
		int giamGiaInt = 0;
		int phuThuInt = 0;
		try {
			if(!giamGia.isEmpty()) {
				giamGiaInt = Integer.parseInt(giamGia);
			}
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Giá giảm phải là một số nguyên !");
		}
		
		try {
			if(!phuThu.isEmpty()) {
				phuThuInt = Integer.parseInt(phuThu);
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Gía phụ thu phải là số nguyên!");
		}
		
		String query = this.query + "WHERE HOADON.xuLy = 0 ";
		if(!maHD.isEmpty()) {
			query +=  " AND HOADON.maHD LIKE '%" + maHD + "%'";
		}
		if(!maCTT.isEmpty()) {
			query +=  " AND HOADON.maCTT LIKE '%" + maCTT + "%'";
		}
		if(!tenNhanVien.isEmpty()) {
			query +=  " AND NHANVIEN.tenNV LIKE '%" + tenNhanVien + "%'";
		}
		if(!(giamGiaInt == 0)) {
			query += " AND HOADON.giamGia = " + giamGiaInt;
		}
		if(!(phuThuInt == 0)) {
			query += " AND HOADON.phuThu = " + phuThuInt;
		}
		if (ngayBatDau != null) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        query += " AND HOADON.ngayThanhToan >= '" + sdf.format(ngayBatDau) + "'";
	    }
	    if (ngayKetThuc != null) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        query += " AND HOADON.ngayThanhToan <= '" + sdf.format(ngayKetThuc) + "'";
	    }
		if(!phuongThucTT.isEmpty()) {
			if(phuongThucTT.equals("Tiền mặt")) {
				query += " AND HOADON.phuongThucThanhToan = 0";
			}
			else {
				query += " AND HOADON.phuongThucThanhToan = 1";
			}
		}
		if (!TongTienPhong.isEmpty()) {
			int[] arrGiaDV = queryPriceWithMinAndMax(TongTienPhong);
            int giaDVMin = arrGiaDV[0];
            int giaDVMax = arrGiaDV[1];
            
            if (giaDVMin == 0 && giaDVMax == Integer.MAX_VALUE) {
                // Không có giá trị tối thiểu hoặc tối đa, không cần thêm điều kiện
            } else if (giaDVMin == 0) {
                query += " AND TIENPHONG.tienPhong < " + giaDVMax;
            } else if (giaDVMax == Integer.MAX_VALUE) {
                query += " AND TIENPHONG.tienPhong > " + giaDVMin;
            } else {
                query += " AND TIENPHONG.tienPhong BETWEEN " + giaDVMin + " AND " +  giaDVMax;
            }
        }
		if(!TongTienDV.isEmpty()) {
			int[] arrGiaDV = queryPriceWithMinAndMax(TongTienDV);
            int giaDVMin = arrGiaDV[0];
            int giaDVMax = arrGiaDV[1];
            
            if (giaDVMin == 0 && giaDVMax == Integer.MAX_VALUE) {
                // Không có giá trị tối thiểu hoặc tối đa, không cần thêm điều kiện
            } else if (giaDVMin == 0) {
                query += " AND TIENDICHVU.tienDichVu < " + giaDVMax;
            } else if (giaDVMax == Integer.MAX_VALUE) {
                query += " AND TIENDICHVU.tienDichVu > " + giaDVMin;
            } else {
                query += " AND TIENDICHVU.tienDichVu BETWEEN " + giaDVMin + " AND " +  giaDVMax;
            }
		}
		if(!TongTien.isEmpty()) {
		    int[] arrTongTien = queryPriceWithMinAndMax(TongTien);
		    int tongTienMin = arrTongTien[0];
		    int tongTienMax = arrTongTien[1];
		    
		    if (tongTienMin == 0 && tongTienMax == Integer.MAX_VALUE) {
		        // Không có giá trị tối thiểu hoặc tối đa, không cần thêm điều kiện
		    } else if (tongTienMin == 0) {
		        query += " AND (CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + \r\n"
		        		+ "    CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END + \r\n"
		        		+ "    ( \r\n"
		        		+ "        CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + \r\n"
		        		+ "        CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END \r\n"
		        		+ "    ) * HOADON.phuThu / 100 - \r\n"
		        		+ "    ( \r\n"
		        		+ "        CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + \r\n"
		        		+ "        CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END \r\n"
		        		+ "    ) * HOADON.giamGia / 100) < " + tongTienMax;
		    } else if (tongTienMax == Integer.MAX_VALUE) {
		        query += " AND (CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + \r\n"
		        		+ "    CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END + \r\n"
		        		+ "    ( \r\n"
		        		+ "        CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + \r\n"
		        		+ "        CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END \r\n"
		        		+ "    ) * HOADON.phuThu / 100 - \r\n"
		        		+ "    ( \r\n"
		        		+ "        CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + \r\n"
		        		+ "        CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END \r\n"
		        		+ "    ) * HOADON.giamGia / 100) > " + tongTienMin;
		    } else {
		        query += " AND (CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + \r\n"
		        		+ "    CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END + \r\n"
		        		+ "    ( \r\n"
		        		+ "        CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + \r\n"
		        		+ "        CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END \r\n"
		        		+ "    ) * HOADON.phuThu / 100 - \r\n"
		        		+ "    ( \r\n"
		        		+ "        CASE WHEN tienPhong IS NOT NULL THEN tienPhong ELSE 0 END + \r\n"
		        		+ "        CASE WHEN tienDichVu IS NOT NULL THEN tienDichVu ELSE 0 END \r\n"
		        		+ "    ) * HOADON.giamGia / 100) BETWEEN " + tongTienMin + " AND " +  tongTienMax;
		    }
		}

		query += " GROUP BY HOADON.maHD, HOADON.maCTT, NHANVIEN.tenNV, TIENPHONG.tienPhong, TIENDICHVU.tienDichVu, HOADON.giamGia, HOADON.phuThu, HOADON.ngayThanhToan, HOADON.phuongThucThanhToan;";
		return db.getList(query);
	}
	
	public void queryComboboxStr(String objStr, String query) {
		int[] arrNotIsEmpty = queryPriceWithMinAndMax(objStr);
		int giaMin = arrNotIsEmpty[0];
		int giaMax = arrNotIsEmpty[1];
		
		if (giaMin == 0 && giaMax == Integer.MAX_VALUE) {
            // Không có giá trị tối thiểu hoặc tối đa, không cần thêm điều kiện
        } else if (giaMin == 0) {
            query += " AND " + objStr + " < " + giaMax;
        } else if (giaMax == Integer.MAX_VALUE) {
            query += " AND " + objStr + " > " + giaMin;
        } else {
            query += " AND " + objStr + " BETWEEN " + giaMin + " AND " +  giaMax;
        }
	}
	
	public ResultSet getHoaDon(String maHD)
	{
	    String query = "SELECT HoaDon.maHD, HoaDon.maCTT, NhanVien.tenNV, HoaDon.ngayThanhToan\r\n"
	    		+ "FROM HoaDon\r\n"
	    		+ "JOIN NhanVien ON HoaDon.maNV = NhanVien.maNV\r\n"
	    		+ "JOIN ChiTietThue ON HoaDon.maCTT = ChiTietThue.maCTT\r\n"
	    		+ "WHERE HoaDon.maHD = '\"" + maHD + "\'\r\n"
	    		+ "";
	    return db.getList(query);
	}
	
	public ResultSet getThuePhong(String maHD)
	{
		String query = "select tenP, loaiHinhThue, ngayThue, ngayCheckOut, giaThue from CHITIETTHUEPHONG, PHONG, HOADON \r\nwhere CHITIETTHUEPHONG.maCTT = HOADON.maCTT and CHITIETTHUEPHONG.maP = Phong.maP and maHD = '" + maHD + "'";
	    return db.getList(query);
	}
	public ResultSet getDichVu(String maHD)
	{
		String query = "select tenDV, loaiDV, ngaySuDung, SoLuong, ChiTietThueDichVu.giaDV, (SoLuong*ChiTietThueDichVu.giaDV) as Tong from CHITIETTHUEDICHVU, DICHVU, HOADON \r\nwhere CHITIETTHUEDICHVU.maCTT = HOADON.maCTT and CHITIETTHUEDICHVU.maDV = DICHVU.maDV and maHD = '" + maHD + "'";
	    return db.getList(query);
	}
	
	public void ThemHoaDon(String maHD, String maCTT, String giamGia, String phuThu, String ngayThanhToan, String pttt)
	{
	    String query = String.format("insert into HOADON values('{0}','{1}',{2},{3},'{4}',{5},0)", maHD, maCTT, giamGia, phuThu, ngayThanhToan, pttt);
	    db.executeNonQuery(query);
	}
	
	public int SoLuongHD(String dateNow)
	{
	    String query = "select COUNT(MaHD) + 1 from HOADON where cast(ngayThanhToan as date) = '" + dateNow + "'";
	    return db.executeNonQueryGetInteger(query);
	}

}
