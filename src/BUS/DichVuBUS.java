package BUS;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;

import DAO.Database;
import DTO.DichVuDTO;

public class DichVuBUS {
	private Database db;
	
	public DichVuBUS() {
		db = new Database();
	}
	
	public List<DichVuDTO> getListDichVu(){
		String query = "SELECT * FROM DICHVU WHERE xuLy = 0";
		return db.getListDV_DTO(query);
	}
	
	public String initMaDV() {
        int num = 0;
		String query = "SELECT COUNT(DICHVU.maDV) FROM DICHVU";
		
		try {
			ResultSet rs = db.getList(query);
			
			while(rs.next()) {
				num = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		// 1. Tạo mã ngày tháng hiện tại nếu cần
        String dateString = new SimpleDateFormat("ddMMyy").format(new Date());
        
        
        // 2. Tạo mã số thứ tự
        String sequenceNumber = String.format("%04d", num);

        // 3. Tạo mã cuối cùng
        String generatedID = "DV" + dateString + sequenceNumber;

        return generatedID;
    }
	
	public String addDichVu(String maDV, String tenDV, String loaiDV, int giaDV) {
	    if(tenDV.isEmpty() || loaiDV.isEmpty() || giaDV <= 0)  {
	        return "Vui lòng điền đầy đủ thông tin !";
	    }

	    String query = "INSERT INTO DICHVU VALUES ('" + maDV + "', N'" + tenDV + "', N'" + loaiDV + "', " + giaDV + ", 0)";
	    
	    db.executeNonQuery(query);
	    
	    return "Thêm dịch vụ thành công !";    
	}
	
	public String deleteDichVu(String maDV) {
		String query = String.format("UPDATE DICHVU SET xuLy = 1 WHERE maDV = '%s'", maDV);
		db.executeNonQuery(query);
		return "Xóa dịch vụ thành công !";
	}
	
	public String updateDichVu(String maDV, String tenDV, String loaiDV, int giaDV) {
	    if(tenDV.isEmpty() || loaiDV.isEmpty() || giaDV <= 0) {
	    	return "Vui lòng điền đầy đủ thông tin !";
	    }
	    
	    String query = "UPDATE DICHVU SET tenDV = N'" + tenDV + "', loaiDV = N'" + loaiDV + "', giaDV = " + giaDV + ", xuLy = 0 WHERE maDV = '" + maDV + "';"; 
	    db.executeNonQuery(query);
	    return "Sửa dịch vụ thành công !";
	}
	
	public int[] queryPriceWithMinAndMax(String txtPrice) {
	    int minGiaDV = 0;
	    int maxGiaDV = Integer.MAX_VALUE;

	    try {
	        if (txtPrice.contains("Dưới")) {
	            // Trường hợp "Dưới 50.000 VND"
	            String minGiaStr = txtPrice.replaceAll("[^\\d]", "");
	            maxGiaDV = Integer.parseInt(minGiaStr) - 1; // Trừ 1 đơn vị để đảm bảo giá trị dưới
	        } else if (txtPrice.contains("Trên")) {
	            // Trường hợp "Trên 1.000.000 VND"
	            String maxGiaStr = txtPrice.replaceAll("[^\\d]", "");
	            minGiaDV = Integer.parseInt(maxGiaStr) + 1; // Cộng 1 đơn vị để đảm bảo giá trị trên
	        } else {
	            // Trường hợp "50.000 đến 100.000" (có thể giữ nguyên mã cũ)
	            String[] parts = txtPrice.split(" đến ");
	            String minGiaDVStr = parts[0].replaceAll("[^\\d]", ""); // Lấy phần số đầu tiên và loại bỏ ký tự không phải số
	            String maxGiaDVStr = parts[1].replaceAll("[^\\d]", ""); // Lấy phần số thứ hai và loại bỏ ký tự không phải số
	            
	            minGiaDV = Integer.parseInt(minGiaDVStr);
	            maxGiaDV = Integer.parseInt(maxGiaDVStr);
	        }
	    } catch (NumberFormatException e) {
	        // Xử lý ngoại lệ trong trường hợp không thể chuyển đổi chuỗi thành số nguyên
	        e.printStackTrace();
	    }

	    return new int[]{minGiaDV, maxGiaDV};
	}
	
	public List<DichVuDTO> filterDichVu(String maDV, String tenDV, String loaiDV, String giaDV) {
	    List<DichVuDTO> filteredList = new ArrayList<>();
	    try {
	        String query = "SELECT * FROM DICHVU WHERE xuLy = 0";

	        if (!maDV.isEmpty()) {
	            query += " AND maDV LIKE '%" + maDV + "%'";
	        }
	        if (!tenDV.isEmpty()) {
	            query += " AND tenDV LIKE N'%" + tenDV + "%'";
	        }
	        if (!loaiDV.isEmpty()) {
	        	System.out.println(loaiDV);
	            query += " AND loaiDV = N'" + loaiDV + "'";
	        }
	        if (!giaDV.isEmpty()) {
	            int[] arrGiaDV = queryPriceWithMinAndMax(giaDV);
	            int giaDVMin = arrGiaDV[0];
	            int giaDVMax = arrGiaDV[1];
	            
	            if (giaDVMin == 0 && giaDVMax == Integer.MAX_VALUE) {
	                // Không có giá trị tối thiểu hoặc tối đa, không cần thêm điều kiện
	            } else if (giaDVMin == 0) {
	                query += " AND giaDV < " + giaDVMax;
	            } else if (giaDVMax == Integer.MAX_VALUE) {
	                query += " AND giaDV > " + giaDVMin;
	            } else {
	                query += " AND giaDV BETWEEN " + giaDVMin + " AND " +  giaDVMax;
	            }
	        }

	        filteredList = db.getListDV_DTO(query);
	        
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return filteredList;
	}
	
	public ResultSet getListDichVuCTPhieuThue(String key, String loaiDV) {
	    String query;
	    if (key.equals("Nhập Mã/Tên dịch vụ cần tìm")) {
	        query = "select maDV,tenDV,loaiDV,giaDV from DichVu where XuLy = 0";
	        if (loaiDV.trim().equals("Ăn uống")) {
	            query += " AND loaiDV = N'Ăn uống'";
	        } else if (loaiDV.trim().equals("Chăm sóc sắc đẹp")) {
	            query += " AND loaiDV = N'Chăm sóc sắc đẹp'";
	        } else if (loaiDV.trim().equals("Tổ chức tiệc")) {
	            query += " AND loaiDV = N'Tổ chức tiệc'";
	        } else if (loaiDV.trim().equals("Giải trí")) {
	            query += " AND loaiDV = N'Giải trí'";
	        }
	    } else {
	        query = "select maDV,tenDV,loaiDV,giaDV from DichVu where XuLy = 0 AND ( maDV LIKE N'%" + key + "%' or tendv LIKE N'%" + key + "%')";
	        if (loaiDV.trim().equals("Ăn uống")) {
	            query += " AND loaiDV = N'Ăn uống'";
	        } else if (loaiDV.trim().equals("Chăm sóc sắc đẹp")) {
	            query += " AND loaiDV = N'Chăm sóc sắc đẹp'";
	        } else if (loaiDV.trim().equals("Tổ chức tiệc")) {
	            query += " AND loaiDV = N'Tổ chức tiệc'";
	        } else if (loaiDV.trim().equals("Giải trí")) {
	            query += " AND loaiDV = N'Giải trí'";
	        }
	    }
	    ResultSet rs = db.getList(query);
	    return rs;
	}

}
