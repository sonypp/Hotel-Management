package DAO;

import java.util.Date;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DTO.*;

public class Database {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	String serverName = "VINH\\SQLEXPRESS";
	String dbName = "QLKSJAVA";
	public Database() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://" + serverName + ":1433;databaseName=" + dbName
					+ ";encrypt=true;trustServerCertificate=true";
			String userName = "sa";
			String password = "123456789";
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet getList(String query) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void executeNonQuery(String query) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hàm dùng để tự động gán dữ liệu vào câu truy vấn dựa theo kiểu dữ liệu của
	 * phần tử chứa trong params. Không có kiểu trả về (phù hợp cho Update/Insert
	 * SQL)
	 * 
	 * @param query  Câu truy vấn chứa các ? có thứ tự
	 * @param params Mảng dữ liệu có thứ tự để thêm vào câu truy vấn hoàn chỉnh
	 *
	 */
	public void executeNonQuery(String query, Object[] params) {
		try {
			pstmt = conn.prepareStatement(query);
			int i = 1; /* Thứ tự vị trí của dấu ? được thêm dữ liệu vào */
			for (Object obj : params) {
				/* Lấy ra tên kiểu dữ liệu của phần tử đang xét */
				switch (obj.getClass().getSimpleName()) {
				/*
				 * Xác định được kiểu dữ liệu đang xét, vị trí (dấu ?) của giá trị phần tử được
				 * thêm vào
				 */
				case "Integer": {
					/* Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu int */
					pstmt.setInt(i, (int) obj);
					break;
				}
				case "String": {
					/*
					 * Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu nvarchar/varchar (phần
					 * này thì code tự động biết để thêm N'' cho câu lệnh sql)
					 */
					pstmt.setString(i, (String) obj);
					break;
				}
				case "Date": {
					/* Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu Date */
					pstmt.setTimestamp(i, new Timestamp(((Date) obj).getTime()));
					break;
				}
				}
				i++; /* Tiếp tục xét đến vị trí (dấu ?) tiếp theo cần thêm giá trị */
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hàm dùng để tự động gán dữ liệu vào câu truy vấn dựa theo kiểu dữ liệu của
	 * phần tử chứa trong params. Có kiểu trả về (phù hợp cho Select SQL)
	 * 
	 * @param query  Câu truy vấn chứa các ? có thứ tự
	 * @param params Mảng dữ liệu có thứ tự để thêm vào câu truy vấn hoàn chỉnh
	 *
	 */
	public ResultSet executeQuery(String query, Object[] params) {
		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			for (Object obj : params) {
				switch (obj.getClass().getSimpleName()) {
				case "Integer": {
					pstmt.setInt(i, (int) obj);
					break;
				}
				case "String": {
					pstmt.setString(i, (String) "%" + obj + "%");
					break;
				}
				case "Date": {
					pstmt.setTimestamp(i, new Timestamp(((Date) obj).getTime()));
					break;
				}
				}
				i++;
			}
			return pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet executeQuery(String query) {
        
        try {
        	pstmt = conn.prepareStatement(query);
			return pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
	public int executeNonQueryGetInteger(String query) {
		int num = 0;
		try {
			stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				num = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * ___ : kiểu dữ liệu thuộc package DTO toList___ : chuyển từ ResultSet sang
	 * List<T> phù hợp getList___(query) : truy vấn trả về List<T> với câu query
	 * hoàn chỉnh getList___(query,paramss): truy vấn trả về List<T> với câu query,
	 * kèm giá trị truyền vào
	 */

	/* PHONGDTO */
	public List<PhongDTO> toListPhongDTO(ResultSet rs) throws SQLException {
		List<PhongDTO> list = new ArrayList<>();
		while (rs.next()) {
			PhongDTO phong = new PhongDTO();
			phong.setMaP(rs.getString(1));
			phong.setTenP(rs.getString(2));
			phong.setLoaiP(rs.getInt(3));
			phong.setGiaP(rs.getInt(4));
			phong.setChiTietLoaiP(rs.getInt(5));
			phong.setTinhTrang(rs.getInt(6));
			phong.setHienTrang(rs.getInt(7));
			phong.setXuLy(rs.getInt(8));
			list.add(phong);
		}
		return list;
	}

	public List<PhongDTO> getListPhongDTO(String query) {
		List<PhongDTO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			list = toListPhongDTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<PhongDTO> getListPhongDTO(String query, Object[] params) {
		List<PhongDTO> list = new ArrayList<>();
		try {
			rs = executeQuery(query, params);
			list = toListPhongDTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* NHANVIENDTO */
	public List<NhanVienDTO> toListNV_DTO(ResultSet rs) throws SQLException {
		List<NhanVienDTO> list = new ArrayList<>();
		while (rs.next()) {
			NhanVienDTO nv = new NhanVienDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
					rs.getTimestamp(6), rs.getTimestamp(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
			list.add(nv);
		}
		return list;
	}

	public List<NhanVienDTO> getListNV_DTO(String query) {
		List<NhanVienDTO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			list = toListNV_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<NhanVienDTO> getListNV_DTO(String query, Object[] params) {
		List<NhanVienDTO> list = new ArrayList<>();
		try {
			rs = executeQuery(query, params);
			list = toListNV_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/*DSNHANVIENDTO*/
	public List<DSNhanVienDTO> toListDSNV_DTO(ResultSet rs) throws SQLException {
        List<DSNhanVienDTO> list = new ArrayList<>();
        while (rs.next()) {
            DSNhanVienDTO nv = new DSNhanVienDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
            );
            list.add(nv);
        }
        return list;
    }

    public List<DSNhanVienDTO> getListDSNV_DTO(String query) {
        List<DSNhanVienDTO> list = new ArrayList<>();
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            list = toListDSNV_DTO(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DSNhanVienDTO> getListDSNV_DTO(String query, Object[] params) {
        List<DSNhanVienDTO> list = new ArrayList<>();
        try {
            rs = executeQuery(query,params);
            list = toListDSNV_DTO(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

	/* KHACHHANGDTO */
	public List<KhachHangDTO> toListKH_DTO(ResultSet rs) throws SQLException {
		List<KhachHangDTO> list = new ArrayList<>();
		while (rs.next()) {
			KhachHangDTO kh = new KhachHangDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
					rs.getString(5), rs.getString(6), rs.getString(7), rs.getTimestamp(8), rs.getInt(9));
			list.add(kh);
		}
		return list;
	}

	public List<KhachHangDTO> getListKH_DTO(String query) {
		List<KhachHangDTO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			list = toListKH_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<KhachHangDTO> getListKH_DTO(String query, Object[] params) {
		List<KhachHangDTO> list = new ArrayList<>();
		try {
			rs = executeQuery(query, params);
			list = toListKH_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* DICHVUDTO */
	public List<DichVuDTO> toListDV_DTO(ResultSet rs) throws SQLException {
		List<DichVuDTO> list = new ArrayList<>();
		while (rs.next()) {
			DichVuDTO dv = new DichVuDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			list.add(dv);
		}
		return list;
	}

	public List<DichVuDTO> getListDV_DTO(String query) {
		List<DichVuDTO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			list = toListDV_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<DichVuDTO> getListDV_DTO(String query, Object[] params) {
		List<DichVuDTO> list = new ArrayList<>();
		try {
			rs = executeQuery(query, params);
			list = toListDV_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* CHITIETTHUEDICHVUDTO */
	public List<ChiTietThueDichVuDTO> toListCTTDV_DTO(ResultSet rs) throws SQLException {
		List<ChiTietThueDichVuDTO> list = new ArrayList<>();
		while (rs.next()) {
			ChiTietThueDichVuDTO dv = new ChiTietThueDichVuDTO(rs.getString(1), rs.getString(2), rs.getTimestamp(3),
					rs.getInt(4), rs.getInt(5));
			list.add(dv);
		}
		return list;
	}

	public List<ChiTietThueDichVuDTO> getListCTTDV_DTO(String query) {
		List<ChiTietThueDichVuDTO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			list = toListCTTDV_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ChiTietThueDichVuDTO> getListCTTDV_DTO(String query, Object[] params) {
		List<ChiTietThueDichVuDTO> list = new ArrayList<>();
		try {
			rs = executeQuery(query, params);
			list = toListCTTDV_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* CHITIETTHUEPHONGDTO */
	public List<ChiTietThuePhongDTO> toListCTTP_DTO(ResultSet rs) throws SQLException {
		List<ChiTietThuePhongDTO> list = new ArrayList<>();
		while (rs.next()) {
			ChiTietThuePhongDTO cttp = new ChiTietThuePhongDTO(rs.getString(1), rs.getString(2), rs.getTimestamp(3),
					rs.getTimestamp(4), rs.getTimestamp(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
			list.add(cttp);
		}
		return list;
	}

	public List<ChiTietThuePhongDTO> getListCTTP_DTO(String query) {
		List<ChiTietThuePhongDTO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			list = toListCTTP_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ChiTietThuePhongDTO> getListCTTP_DTO(String query, Object[] params) {
		List<ChiTietThuePhongDTO> list = new ArrayList<>();
		try {
			rs = executeQuery(query, params);
			list = toListCTTP_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	 /*TAIKHOANDTO*/
	 public List<TaiKhoanDTO> toListTK_DTO(ResultSet rs) throws SQLException {
        List<TaiKhoanDTO> list = new ArrayList<>();
            while (rs.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );
                list.add(tk);
            }
        return list;
    }

	public List<TaiKhoanDTO> getListTK_DTO(String query) {
		List<TaiKhoanDTO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			list = toListTK_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<TaiKhoanDTO> getListTK_DTO(String query, Object[] params) {
		List<TaiKhoanDTO> list = new ArrayList<>();
		try {
			rs = executeQuery(query, params);
			list = toListTK_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* CHITIETTHUEDTO */
	public List<ChiTietThueDTO> toListCTT_DTO(ResultSet rs) throws SQLException {
		List<ChiTietThueDTO> list = new ArrayList<>();
		while (rs.next()) {
			ChiTietThueDTO ctt = new ChiTietThueDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4),
					rs.getInt(5), rs.getInt(6), rs.getInt(7));
			list.add(ctt);
		}
		return list;
	}

	public List<ChiTietThueDTO> getListCTT_DTO(String query) {
		List<ChiTietThueDTO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			list = toListCTT_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ChiTietThueDTO> getListCTT_DTO(String query, Object[] params) {
		List<ChiTietThueDTO> list = new ArrayList<>();
		try {
			rs = executeQuery(query, params);
			list = toListCTT_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/* TIENICH */
	public List<TienIchDTO> toListTI_DTO(ResultSet rs) throws SQLException {
		List<TienIchDTO> list = new ArrayList<>();
		while (rs.next()) {
			TienIchDTO ctt = new TienIchDTO(rs.getString(1), rs.getString(2), rs.getInt(3));
			list.add(ctt);
		}
		return list;
	}

	public List<TienIchDTO> getListTI_DTO(String query) {
		List<TienIchDTO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			list = toListTI_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<TienIchDTO> getListTI_DTO(String query, Object[] params) {
		List<TienIchDTO> list = new ArrayList<>();
		try {
			rs = executeQuery(query, params);
			list = toListTI_DTO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ChiTietThueDTO getCTT_DTO(String query) {
		ChiTietThueDTO ctt = null;
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			if (rs.next()) {
				ctt = new ChiTietThueDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ctt;
	}
}