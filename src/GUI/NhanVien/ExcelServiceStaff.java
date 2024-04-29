package GUI.NhanVien;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import DTO.NhanVienDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExcelServiceStaff {

	private JFileChooser fileChooser;
	private String filePath;
	private List<NhanVienDTO> data;
	private FileInputStream in;
	private FileOutputStream out;
	private XSSFWorkbook workbook;

	public ExcelServiceStaff(){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("File Excel .xlsx","xlsx");
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);

		filePath = new String();
	}

	public void setData(List<NhanVienDTO> data) {
		this.data = data;
	}

	public List<NhanVienDTO> getData() {
		return data;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void chooseFile(){
		int result = fileChooser.showOpenDialog(null);
		// Kiểm tra xem người dùng đã chọn file hay không
		if (result == JFileChooser.APPROVE_OPTION) {
			// Lấy đường dẫn của file đã chọn
			setFilePath(fileChooser.getSelectedFile().getAbsolutePath() + ".xlsx");
		}
	}

	public void readFile(){
		data = new ArrayList<>();
		int i = -1,j = -1;
		try{
			in = new FileInputStream(new File(getFilePath()));
			workbook = new XSSFWorkbook(in);

			for(Sheet sheet : workbook) {
				StringBuilder errSheet = new StringBuilder();
				int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
				Set<String> ListMaNV = new LinkedHashSet<>();

				//Kiểm tra sự tồn tại của header để chọn index dòng bắt đầu
				Row header = sheet.getRow(0);
				boolean isHeader = true;
				int cellCount = header.getLastCellNum();
				while (isHeader && j < cellCount - 1){
					if (header.getCell(++j).getCellType() != CellType.STRING){
						isHeader = false;
					}
				}
				if (isHeader) {
					i = 1;
					ListMaNV.add("");
				} else {
					i = 0;
				}

				//Kiểm tra ID
				j = 0; //Cột mã NV
				for (int id = i; id <= rowCount && errSheet.length() == 0; id++) {
					Row row = sheet.getRow(id);
					Cell maNVCell = row.getCell(j);
					String maNV = null;
					if (maNVCell.getCellType() == CellType.STRING) {
						//Lấy ra mã NV sau add vào set, add thành công nghĩa là id không bị trùng
						maNV = row.getCell(j).getStringCellValue();
						if (!ListMaNV.add(maNV)){
							//Xảy ra trùng id, lập tức báo lỗi và ngừng đọc file
							errSheet.append(String.format(
									"Vui lòng kiểm tra lại dữ liệu trong file!\n" +
											"Đã có mã nhân viên %s bị trùng!",maNV));
						}
					} else {
						errSheet.append(String.format("Kiểm tra kiểu dữ liệu tại %c%d trong file [String]\n", j + 1 + 64, i + 1));
					}
				}

				//Kiểm tra lỗi đọc file lần 1: phát hiện id trùng trong file, lỗi đọc id
				if (errSheet.length() != 0){
					JOptionPane.showMessageDialog(null,errSheet.toString(),"Lỗi định dạng dữ liệu",JOptionPane.ERROR_MESSAGE);
					break;
				}

				//Kiểm tra dữ liệu của các cột tiếp theo
				//Lấy ra mảng id đã được đọc từ trước
				String[] maNV = ListMaNV.toArray(new String[0]);
				for (; i <= rowCount && errSheet.length() == 0; i++) {
					StringBuilder errMessage = new StringBuilder();
					Row row = sheet.getRow(i);
					j = 0; //Lưu số cột sau cột mã Nhân Viên


					Cell tenNVCell = row.getCell(++j);
					String tenNV = null;
					if (tenNVCell.getCellType() == CellType.STRING) {
						tenNV = row.getCell(j).getStringCellValue();
					} else {
						errMessage.append(String.format("Kiểm tra kiểu dữ liệu tại %c%d trong file [String]\n", j + 1 + 64, i + 1));
					}

					Cell gioiTinhCell = row.getCell(++j);
					int gioiTinh = 0;
					if (gioiTinhCell.getCellType() == CellType.NUMERIC) {
						gioiTinh = Double.valueOf(row.getCell(j).getNumericCellValue()).intValue();
					} else {
						errMessage.append(String.format("Kiểm tra kiểu dữ liệu tại %c%d trong file [Numberic]\n", j + 1 + 64, i + 1));
					}

					Cell ngaySinhCell = row.getCell(++j);
					Date ngaySinh = null;
					if (ngaySinhCell.getCellType() == CellType.NUMERIC) {
						ngaySinh = row.getCell(j).getDateCellValue();
					} else {
						errMessage.append(String.format("Kiểm tra kiểu dữ liệu tại %c%d trong file [Date dd/MM/yyyy]\n", j + 1 + 64, i + 1));
					}

					Cell ngayVaoLamCell = row.getCell(++j);
					Date ngayVaoLam = null;
					if (ngayVaoLamCell.getCellType() == CellType.NUMERIC) {
						ngayVaoLam = row.getCell(j).getDateCellValue();
					} else {
						errMessage.append(String.format("Kiểm tra kiểu dữ liệu tại %c%d trong file [Date dd/MM/yyyy]\n", j + 1 + 64, i + 1));
					}

					Cell chucVuCell = row.getCell(++j);
					int chucVu = 0;
					if (chucVuCell.getCellType() == CellType.NUMERIC) {
						chucVu = Double.valueOf(row.getCell(j).getNumericCellValue()).intValue();
					} else {
						errMessage.append(String.format("Kiểm tra kiểu dữ liệu tại %c%d trong file [Numberic]\n", j + 1 + 64, i + 1));
					}

					Cell soNgayPhepCell = row.getCell(++j);
					int soNgayPhep = 0;
					if (soNgayPhepCell.getCellType() == CellType.NUMERIC) {
						soNgayPhep = Double.valueOf(row.getCell(j).getNumericCellValue()).intValue();
					} else {
						errMessage.append(String.format("Kiểm tra kiểu dữ liệu tại %c%d trong file [Numberic]\n", j + 1 + 64, i + 1));
					}

					Cell luong1NgayCell = row.getCell(++j);
					int luong1Ngay = 0;
					if (luong1NgayCell.getCellType() == CellType.NUMERIC) {
						luong1Ngay = Double.valueOf(row.getCell(j).getNumericCellValue()).intValue();
					} else {
						errMessage.append(String.format("Kiểm tra kiểu dữ liệu tại %c%d trong file [Numberic]\n", j + 1 + 64, i + 1));
					}

					Cell emailCell = row.getCell(++j);
					String email = null;
					if (emailCell.getCellType() == CellType.STRING) {
						email = row.getCell(j).getStringCellValue();
					} else {
						errMessage.append(String.format("Kiểm tra kiểu dữ liệu tại %c%d trong file [String]\n", j + 1 + 64, i + 1));
					}

					//Sau khi kiểm tra các lỗi đọc dữ liệu
					String errRow = errMessage.toString();
					if (errRow.isEmpty()){
						//Nếu không có lỗi, thực hiện thêm vào data
						NhanVienDTO nhanVien = new NhanVienDTO(maNV[i], tenNV, gioiTinh, soNgayPhep, chucVu, ngaySinh, ngayVaoLam, email, luong1Ngay, 0);
						data.add(nhanVien);
					} else {
						//Nếu có, add vào lỗi đọc file để ngưng quá trình đọc dữ liệu
						errSheet.append(errRow);

					}
				}

				//Có lỗi đọc file xảy ra, show cho người dùng
				if (errSheet.length() != 0){
					JOptionPane.showMessageDialog(null,errSheet.toString(),"Lỗi định dạng dữ liệu",JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (FileNotFoundException fnfe) {
			JOptionPane.showMessageDialog(null,"Không tìm thấy file","Lỗi File",JOptionPane.ERROR_MESSAGE);
		} catch (IOException IOe){
			throw new RuntimeException();
		}
	}

	public void writeFile() {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet( "Danh sách nhân viên");
		//Tạo phần header
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
		Row headerRow = spreadsheet.createRow(0);

		for (int i = 0; i < NhanVienDTO.header.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(NhanVienDTO.header[i]);
			cell.setCellStyle(headerCellStyle);
			spreadsheet.setColumnWidth(i,PanelStaff.lengthColumn[i+1]*40);
		}
		CellStyle cellStyle = workbook.createCellStyle();

		// Thiết lập định dạng ngày tháng
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

		//Tạo phần thông tin
		int j = 1;
		for (NhanVienDTO nv : data) {
			Row dataRow = spreadsheet.createRow(j++);
			dataRow.createCell(0).setCellValue(nv.getMaNV());
			dataRow.createCell(1).setCellValue(nv.getTenNV());
			dataRow.createCell(2).setCellValue(nv.getGioiTinh());
			Cell ngaySinhCell = dataRow.createCell(3);
			ngaySinhCell.setCellValue(nv.getNgaySinh());
			ngaySinhCell.setCellStyle(cellStyle);
			Cell ngayVaoLamCell = dataRow.createCell(4);
			ngayVaoLamCell.setCellValue(nv.getNgayVaoLam());
			ngayVaoLamCell.setCellStyle(cellStyle);
			dataRow.createCell(5).setCellValue(nv.getChucVu());
			dataRow.createCell(6).setCellValue(nv.getSoNgayPhep());
			dataRow.createCell(7).setCellValue(nv.getLuong1Ngay());
			dataRow.createCell(8).setCellValue(nv.getEmail());
		}

		try {
			out = new FileOutputStream(getFilePath());
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Lỗi mở file excel!","Lỗi File",JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	public boolean isReadFileSuccess(){
		return !data.isEmpty();
	}

	public static void main(String[] args) {
		ExcelServiceStaff ser = new ExcelServiceStaff();
		ser.setFilePath("D:/HotelManagement/DSNV.xlsx");
		ser.readFile();
		for (NhanVienDTO nv: ser.getData()) {
			System.out.println(nv.getMaNV());
		}
	}

}
