package GUI.HoaDon;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import BUS.HoaDonBUS;
import DAO.Database;
import DTO.ChiTietThueDTO;
import DTO.ChiTietThueDichVuDTO;
import DTO.ChiTietThuePhongDTO;
import DTO.DichVuDTO;
import DTO.HoaDonDTO;
import GUI.DichVu.QLDVPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpringLayout;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import java.awt.Font;

public class QuanLiHoaDon extends JPanel {

	private static final long serialVersionUID = 1L;
	private HoaDonBUS hoaDonBUS;
	private JPanel pnl_quanLyHoaDon;
	private JTable tbl_danhSachHoaDon;
	private JPanel pnl_phuThu;
	private JTextField tf_maHoaDon;
	private JTextField tf_tenNhanVien;
	private JTextField tf_maChiTietThue;
	private JTextField tf_giamGia;
	private JTextField tf_phuThu;
	private JButton btn_InHoaDon;
	private JButton btn_timKiem;
	private JComboBox cb_phuongThucTT;
	private JComboBox cb_tongTien;
	private JComboBox cb_tongTienPhong;
	private JComboBox cb_tongTienDVu;
	private JButton btn_lamMoi;
	private JDateChooser dateNgayBatDau;
	private JDateChooser dateNgayKetThuc;

	
	public QuanLiHoaDon() {
		hoaDonBUS = new HoaDonBUS();
		
		setBounds(0, 0, 1251, 835);
		setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

		pnl_quanLyHoaDon = new JPanel();
		pnl_quanLyHoaDon.setBackground(new Color(245, 245, 245));
		pnl_quanLyHoaDon.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnl_quanLyHoaDon.setBounds(0, 0, 1251, 835);
		pnl_quanLyHoaDon.setLayout(null);
		add(pnl_quanLyHoaDon);
				
		JPanel pnl_danhSachHoaDon = new JPanel();
		pnl_danhSachHoaDon.setBackground(new Color(255, 255, 255));
		JPanel pnl_nhapHoaDon = new JPanel();
		pnl_nhapHoaDon.setBackground(new Color(240, 240, 240));
		pnl_danhSachHoaDon.setBounds(10, 356, 1227, 489);
		pnl_quanLyHoaDon.add(pnl_danhSachHoaDon);
		pnl_danhSachHoaDon.setLayout(null);
			
		JLabel lbl_danhSachHoaDon = new JLabel("DANH SÁCH HÓA ĐƠN");
		lbl_danhSachHoaDon.setForeground(new Color(0, 128, 128));
		lbl_danhSachHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_danhSachHoaDon.setBounds(10, 10, 339, 44);
		pnl_danhSachHoaDon.add(lbl_danhSachHoaDon);
		
		String data[][] = { { "1", "HD1234567890", "CTT1234567890", "Phan Phúc Vinh", "500,000 VND", "0 VND", "0%", "0%", "500,000 VND", "24/03/2024" }, 
				 			{ "1", "HD1234567890", "CTT1234567890", "Phan Phúc Vinh", "500,000 VND", "0 VND", "0%", "0%", "500,000 VND", "24/03/2024" }, 
				 			{ "1", "HD1234567890", "CTT1234567890", "Phan Phúc Vinh", "500,000 VND", "0 VND", "0%", "0%", "500,000 VND", "24/03/2024" }};
	    
		String column[] = { "STT", "MÃ HÓA ĐƠN", "MÃ CHI TIẾT THUẾ", "NHÂN VIÊN LẬP HÓA ĐƠN", "TIỀN PHÒNG", "TIỀN DỊCH VỤ", "GIẢM GIÁ", "PHỤ THU", "TỔNG TIỀN", "NGÀY THANH TOÁN"};
     
		tbl_danhSachHoaDon = new JTable(data,column);
		int[] columnWidths = {50, 200, 200, 250, 150, 150, 100, 100, 150, 200}; // Độ rộng mong muốn cho từng cột
		int columnIndex = 0;
		for (int width : columnWidths) {
		    TableColumn columnSize = tbl_danhSachHoaDon.getColumnModel().getColumn(columnIndex++);
		    columnSize.setPreferredWidth(width);
		}

		// Thiết lập độ cao cho từng dòng trong bảng
		tbl_danhSachHoaDon.setRowHeight(30); // Độ cao mong muốn cho mỗi dòng
		
		JTableHeader tableHeader = tbl_danhSachHoaDon.getTableHeader();
		tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30));
		tableHeader.setBackground(Color.GRAY);
		tableHeader.setForeground(Color.white);
		
		Font headerFont = new Font("Arial", Font.BOLD, 14); // Tạo font mới: Arial, đậm, kích thước 14
		tableHeader.setFont(headerFont); 
		
		// Tùy chỉnh các tính chất khác của bảng
		tbl_danhSachHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbl_danhSachHoaDon.setShowGrid(false);
		
		JScrollPane scrp_danhSachHoaDon = new JScrollPane(tbl_danhSachHoaDon);
		scrp_danhSachHoaDon.setBounds(10, 53, 1217, 324);
		pnl_danhSachHoaDon.add(scrp_danhSachHoaDon);
		
		JButton button = new JButton("New button");
		scrp_danhSachHoaDon.setColumnHeaderView(button);
		
		btn_InHoaDon = new JButton("In hóa đơn");
		btn_InHoaDon.setBounds(1089, 10, 128, 37);
		pnl_danhSachHoaDon.add(btn_InHoaDon);
		btn_InHoaDon.setBackground(new Color(123, 104, 238));
		btn_InHoaDon.setForeground(new Color(255, 255, 255));
		btn_InHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		pnl_nhapHoaDon.setBounds(10, 92, 1227, 254);
		pnl_quanLyHoaDon.add(pnl_nhapHoaDon);
		pnl_nhapHoaDon.setLayout(new GridLayout(3, 4, 0, 0));
		
		JPanel pnl_frmDong1 = new JPanel();
		pnl_nhapHoaDon.add(pnl_frmDong1);
		pnl_frmDong1.setLayout(new GridLayout(1, 4, 0, 0));
		
		JPanel pnl_maHoaDon = new JPanel();
		pnl_maHoaDon.setBackground(new Color(255, 255, 255));
		pnl_frmDong1.add(pnl_maHoaDon);
		SpringLayout sl_pnl_maHoaDon = new SpringLayout();
		pnl_maHoaDon.setLayout(sl_pnl_maHoaDon);
		
		JLabel lbl_maHoaDon = new JLabel("Mã hóa đơn");
		sl_pnl_maHoaDon.putConstraint(SpringLayout.NORTH, lbl_maHoaDon, 19, SpringLayout.NORTH, pnl_maHoaDon);
		sl_pnl_maHoaDon.putConstraint(SpringLayout.WEST, lbl_maHoaDon, 10, SpringLayout.WEST, pnl_maHoaDon);
		sl_pnl_maHoaDon.putConstraint(SpringLayout.SOUTH, lbl_maHoaDon, -10, SpringLayout.SOUTH, pnl_maHoaDon);
		sl_pnl_maHoaDon.putConstraint(SpringLayout.EAST, lbl_maHoaDon, 106, SpringLayout.WEST, pnl_maHoaDon);
		lbl_maHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_maHoaDon.add(lbl_maHoaDon);
		
		tf_maHoaDon = new JTextField();
		sl_pnl_maHoaDon.putConstraint(SpringLayout.WEST, tf_maHoaDon, 6, SpringLayout.EAST, lbl_maHoaDon);
		sl_pnl_maHoaDon.putConstraint(SpringLayout.SOUTH, tf_maHoaDon, -27, SpringLayout.SOUTH, pnl_maHoaDon);
		sl_pnl_maHoaDon.putConstraint(SpringLayout.EAST, tf_maHoaDon, 190, SpringLayout.EAST, lbl_maHoaDon);
		pnl_maHoaDon.add(tf_maHoaDon);
		tf_maHoaDon.setColumns(10);
		
		JPanel pnl_maChiTiet = new JPanel();
		pnl_maChiTiet.setBackground(new Color(255, 255, 255));
		pnl_frmDong1.add(pnl_maChiTiet);
		SpringLayout sl_pnl_maChiTiet = new SpringLayout();
		pnl_maChiTiet.setLayout(sl_pnl_maChiTiet);
		
		JLabel lbl_maChiTietThue = new JLabel("Mã chi tiết thuê");
		sl_pnl_maChiTiet.putConstraint(SpringLayout.NORTH, lbl_maChiTietThue, 24, SpringLayout.NORTH, pnl_maChiTiet);
		sl_pnl_maChiTiet.putConstraint(SpringLayout.WEST, lbl_maChiTietThue, 10, SpringLayout.WEST, pnl_maChiTiet);
		sl_pnl_maChiTiet.putConstraint(SpringLayout.SOUTH, lbl_maChiTietThue, -10, SpringLayout.SOUTH, pnl_maChiTiet);
		lbl_maChiTietThue.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_maChiTiet.add(lbl_maChiTietThue);
		
		tf_maChiTietThue = new JTextField();
		sl_pnl_maChiTiet.putConstraint(SpringLayout.EAST, lbl_maChiTietThue, -6, SpringLayout.WEST, tf_maChiTietThue);
		sl_pnl_maChiTiet.putConstraint(SpringLayout.NORTH, tf_maChiTietThue, 41, SpringLayout.NORTH, pnl_maChiTiet);
		sl_pnl_maChiTiet.putConstraint(SpringLayout.WEST, tf_maChiTietThue, 116, SpringLayout.WEST, pnl_maChiTiet);
		sl_pnl_maChiTiet.putConstraint(SpringLayout.EAST, tf_maChiTietThue, -10, SpringLayout.EAST, pnl_maChiTiet);
		pnl_maChiTiet.add(tf_maChiTietThue);
		tf_maChiTietThue.setColumns(10);
		
		JPanel pnl_ngayThue = new JPanel();
		pnl_ngayThue.setBackground(new Color(255, 255, 255));
		pnl_frmDong1.add(pnl_ngayThue);
		SpringLayout sl_pnl_ngayThue = new SpringLayout();
		pnl_ngayThue.setLayout(sl_pnl_ngayThue);
		
		JPanel pnl_ngayTra = new JPanel();
		pnl_ngayTra.setBackground(new Color(255, 255, 255));
		pnl_frmDong1.add(pnl_ngayTra);
		SpringLayout sl_pnl_ngayTra = new SpringLayout();
		pnl_ngayTra.setLayout(sl_pnl_ngayTra);
		
		JLabel lbl_ngayTra = new JLabel("đến ngày");
		sl_pnl_ngayTra.putConstraint(SpringLayout.NORTH, lbl_ngayTra, 20, SpringLayout.NORTH, pnl_ngayTra);
		sl_pnl_ngayTra.putConstraint(SpringLayout.WEST, lbl_ngayTra, 10, SpringLayout.WEST, pnl_ngayTra);
		sl_pnl_ngayTra.putConstraint(SpringLayout.SOUTH, lbl_ngayTra, 0, SpringLayout.SOUTH, pnl_ngayTra);
		sl_pnl_ngayTra.putConstraint(SpringLayout.EAST, lbl_ngayTra, -220, SpringLayout.EAST, pnl_ngayTra);
		lbl_ngayTra.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_ngayTra.add(lbl_ngayTra);
		
		dateNgayKetThuc = new JDateChooser();
		sl_pnl_ngayTra.putConstraint(SpringLayout.WEST, dateNgayKetThuc, 6, SpringLayout.EAST, lbl_ngayTra);
		sl_pnl_ngayTra.putConstraint(SpringLayout.SOUTH, dateNgayKetThuc, -24, SpringLayout.SOUTH, pnl_ngayTra);
		sl_pnl_ngayTra.putConstraint(SpringLayout.EAST, dateNgayKetThuc, -32, SpringLayout.EAST, pnl_ngayTra);
		pnl_ngayTra.add(dateNgayKetThuc);
		
		JPanel pnl_frmDong2 = new JPanel();
		pnl_nhapHoaDon.add(pnl_frmDong2);
		pnl_frmDong2.setLayout(new GridLayout(1, 4, 0, 0));
		
		JPanel pnl_tenNhanVien = new JPanel();
		pnl_tenNhanVien.setBackground(new Color(255, 255, 255));
		pnl_frmDong2.add(pnl_tenNhanVien);
		SpringLayout sl_pnl_tenNhanVien = new SpringLayout();
		pnl_tenNhanVien.setLayout(sl_pnl_tenNhanVien);
		
		JLabel lbl_tenNhanVien = new JLabel("Tên nhân viên");
		sl_pnl_tenNhanVien.putConstraint(SpringLayout.NORTH, lbl_tenNhanVien, 16, SpringLayout.NORTH, pnl_tenNhanVien);
		sl_pnl_tenNhanVien.putConstraint(SpringLayout.WEST, lbl_tenNhanVien, 10, SpringLayout.WEST, pnl_tenNhanVien);
		sl_pnl_tenNhanVien.putConstraint(SpringLayout.SOUTH, lbl_tenNhanVien, -9, SpringLayout.SOUTH, pnl_tenNhanVien);
		sl_pnl_tenNhanVien.putConstraint(SpringLayout.EAST, lbl_tenNhanVien, 106, SpringLayout.WEST, pnl_tenNhanVien);
		lbl_tenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_tenNhanVien.add(lbl_tenNhanVien);
		
		tf_tenNhanVien = new JTextField();
		sl_pnl_tenNhanVien.putConstraint(SpringLayout.NORTH, tf_tenNhanVien, 21, SpringLayout.NORTH, lbl_tenNhanVien);
		sl_pnl_tenNhanVien.putConstraint(SpringLayout.WEST, tf_tenNhanVien, 6, SpringLayout.EAST, lbl_tenNhanVien);
		sl_pnl_tenNhanVien.putConstraint(SpringLayout.EAST, tf_tenNhanVien, 190, SpringLayout.EAST, lbl_tenNhanVien);
		tf_tenNhanVien.setColumns(10);
		pnl_tenNhanVien.add(tf_tenNhanVien);
		
		JPanel pnl_giamGia = new JPanel();
		pnl_giamGia.setBackground(new Color(255, 255, 255));
		pnl_frmDong2.add(pnl_giamGia);
		SpringLayout sl_pnl_giamGia = new SpringLayout();
		pnl_giamGia.setLayout(sl_pnl_giamGia);
		
		JLabel lbl_giamGia = new JLabel("Giảm giá");
		sl_pnl_giamGia.putConstraint(SpringLayout.WEST, lbl_giamGia, 10, SpringLayout.WEST, pnl_giamGia);
		sl_pnl_giamGia.putConstraint(SpringLayout.EAST, lbl_giamGia, 106, SpringLayout.WEST, pnl_giamGia);
		lbl_giamGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_giamGia.add(lbl_giamGia);
		
		tf_giamGia = new JTextField();
		sl_pnl_giamGia.putConstraint(SpringLayout.WEST, tf_giamGia, 23, SpringLayout.EAST, lbl_giamGia);
		sl_pnl_giamGia.putConstraint(SpringLayout.EAST, tf_giamGia, -10, SpringLayout.EAST, pnl_giamGia);
		sl_pnl_giamGia.putConstraint(SpringLayout.NORTH, lbl_giamGia, 1, SpringLayout.NORTH, tf_giamGia);
		sl_pnl_giamGia.putConstraint(SpringLayout.NORTH, tf_giamGia, 34, SpringLayout.NORTH, pnl_giamGia);
		tf_giamGia.setColumns(10);
		pnl_giamGia.add(tf_giamGia);
		
		pnl_phuThu = new JPanel();
		pnl_phuThu.setBackground(new Color(255, 255, 255));
		pnl_frmDong2.add(pnl_phuThu);
		SpringLayout sl_pnl_phuThu = new SpringLayout();
		pnl_phuThu.setLayout(sl_pnl_phuThu);
		
		JLabel lbl_phuThu = new JLabel("Phụ thu");
		sl_pnl_phuThu.putConstraint(SpringLayout.NORTH, lbl_phuThu, 25, SpringLayout.NORTH, pnl_phuThu);
		sl_pnl_phuThu.putConstraint(SpringLayout.WEST, lbl_phuThu, 10, SpringLayout.WEST, pnl_phuThu);
		sl_pnl_phuThu.putConstraint(SpringLayout.SOUTH, lbl_phuThu, -21, SpringLayout.SOUTH, pnl_phuThu);
		lbl_phuThu.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_phuThu.add(lbl_phuThu);
		
		tf_phuThu = new JTextField();
		sl_pnl_phuThu.putConstraint(SpringLayout.EAST, lbl_phuThu, -24, SpringLayout.WEST, tf_phuThu);
		sl_pnl_phuThu.putConstraint(SpringLayout.WEST, tf_phuThu, 142, SpringLayout.WEST, pnl_phuThu);
		sl_pnl_phuThu.putConstraint(SpringLayout.NORTH, tf_phuThu, 36, SpringLayout.NORTH, pnl_phuThu);
		sl_pnl_phuThu.putConstraint(SpringLayout.EAST, tf_phuThu, -10, SpringLayout.EAST, pnl_phuThu);
		tf_phuThu.setColumns(10);
		pnl_phuThu.add(tf_phuThu);
		
		JPanel pnl_phuongThucTT = new JPanel();
		pnl_phuongThucTT.setBackground(new Color(255, 255, 255));
		pnl_frmDong2.add(pnl_phuongThucTT);
		SpringLayout sl_pnl_phuongThucTT = new SpringLayout();
		pnl_phuongThucTT.setLayout(sl_pnl_phuongThucTT);
		
		JLabel lbl_phuongThucTT = new JLabel("Phương thức TT");
		sl_pnl_phuongThucTT.putConstraint(SpringLayout.NORTH, lbl_phuongThucTT, 23, SpringLayout.NORTH, pnl_phuongThucTT);
		sl_pnl_phuongThucTT.putConstraint(SpringLayout.WEST, lbl_phuongThucTT, 10, SpringLayout.WEST, pnl_phuongThucTT);
		sl_pnl_phuongThucTT.putConstraint(SpringLayout.SOUTH, lbl_phuongThucTT, -21, SpringLayout.SOUTH, pnl_phuongThucTT);
		sl_pnl_phuongThucTT.putConstraint(SpringLayout.EAST, lbl_phuongThucTT, 141, SpringLayout.WEST, pnl_phuongThucTT);
		lbl_phuongThucTT.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_phuongThucTT.add(lbl_phuongThucTT);
		
		DefaultComboBoxModel model_phuongThucTT = new DefaultComboBoxModel();
		model_phuongThucTT.addElement("");
		model_phuongThucTT.addElement("Tiền mặt");
		model_phuongThucTT.addElement("Chuyển khoản");
		
		cb_phuongThucTT = new JComboBox(model_phuongThucTT);
		sl_pnl_phuongThucTT.putConstraint(SpringLayout.NORTH, cb_phuongThucTT, 11, SpringLayout.NORTH, lbl_phuongThucTT);
		sl_pnl_phuongThucTT.putConstraint(SpringLayout.WEST, cb_phuongThucTT, 6, SpringLayout.EAST, lbl_phuongThucTT);
		sl_pnl_phuongThucTT.putConstraint(SpringLayout.EAST, cb_phuongThucTT, -10, SpringLayout.EAST, pnl_phuongThucTT);
		pnl_phuongThucTT.add(cb_phuongThucTT);
		
		JPanel pnl_frmDong3 = new JPanel();
		pnl_nhapHoaDon.add(pnl_frmDong3);
		pnl_frmDong3.setLayout(new GridLayout(1, 4, 0, 0));
		
		JPanel pnl_tongTien = new JPanel();
		pnl_tongTien.setBackground(new Color(255, 255, 255));
		pnl_frmDong3.add(pnl_tongTien);
		SpringLayout sl_pnl_tongTien = new SpringLayout();
		pnl_tongTien.setLayout(sl_pnl_tongTien);
		
		JLabel lbl_tongTien = new JLabel("Tổng tiền");
		sl_pnl_tongTien.putConstraint(SpringLayout.NORTH, lbl_tongTien, 23, SpringLayout.NORTH, pnl_tongTien);
		sl_pnl_tongTien.putConstraint(SpringLayout.WEST, lbl_tongTien, 10, SpringLayout.WEST, pnl_tongTien);
		sl_pnl_tongTien.putConstraint(SpringLayout.SOUTH, lbl_tongTien, -8, SpringLayout.SOUTH, pnl_tongTien);
		sl_pnl_tongTien.putConstraint(SpringLayout.EAST, lbl_tongTien, 110, SpringLayout.WEST, pnl_tongTien);
		lbl_tongTien.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_tongTien.add(lbl_tongTien);
		
		DefaultComboBoxModel model_tongTien = new DefaultComboBoxModel();
		model_tongTien.addElement("");
		model_tongTien.addElement("Dưới 50.000 VND");
		model_tongTien.addElement("Từ 50.000 VND đến 100.000 VND");
		model_tongTien.addElement("Từ 100.000 VND đến 200.000 VND");
		model_tongTien.addElement("Từ 200.000 VND đến 500.000 VND");
		model_tongTien.addElement("Từ 500.000 VND đến 1.000.000 VND");
		model_tongTien.addElement("Trên 1.000.000 VND");
		
		cb_tongTien = new JComboBox(model_tongTien);
		sl_pnl_tongTien.putConstraint(SpringLayout.NORTH, cb_tongTien, 17, SpringLayout.NORTH, lbl_tongTien);
		sl_pnl_tongTien.putConstraint(SpringLayout.WEST, cb_tongTien, 6, SpringLayout.EAST, lbl_tongTien);
		sl_pnl_tongTien.putConstraint(SpringLayout.EAST, cb_tongTien, 186, SpringLayout.EAST, lbl_tongTien);
		pnl_tongTien.add(cb_tongTien);
		
		JPanel pnl_tongTienPhong = new JPanel();
		pnl_tongTienPhong.setBackground(new Color(255, 255, 255));
		pnl_frmDong3.add(pnl_tongTienPhong);
		SpringLayout sl_pnl_tongTienPhong = new SpringLayout();
		pnl_tongTienPhong.setLayout(sl_pnl_tongTienPhong);
		
		JLabel lbl_tongTienPhong = new JLabel("Tổng tiền phòng");
		sl_pnl_tongTienPhong.putConstraint(SpringLayout.NORTH, lbl_tongTienPhong, 35, SpringLayout.NORTH, pnl_tongTienPhong);
		sl_pnl_tongTienPhong.putConstraint(SpringLayout.WEST, lbl_tongTienPhong, 10, SpringLayout.WEST, pnl_tongTienPhong);
		sl_pnl_tongTienPhong.putConstraint(SpringLayout.SOUTH, lbl_tongTienPhong, -15, SpringLayout.SOUTH, pnl_tongTienPhong);
		lbl_tongTienPhong.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_tongTienPhong.add(lbl_tongTienPhong);
		
		DefaultComboBoxModel model_tongTienPhong = new DefaultComboBoxModel();
		model_tongTienPhong.addElement("");
		model_tongTienPhong.addElement("Dưới 50.000 VND");
		model_tongTienPhong.addElement("Từ 50.000 VND đến 100.000 VND");
		model_tongTienPhong.addElement("Từ 100.000 VND đến 200.000 VND");
		model_tongTienPhong.addElement("Từ 200.000 VND đến 500.000 VND");
		model_tongTienPhong.addElement("Từ 500.000 VND đến 1.000.000 VND");
		model_tongTienPhong.addElement("Trên 1.000.000 VND");
		
		cb_tongTienPhong = new JComboBox(model_tongTienPhong);
		sl_pnl_tongTienPhong.putConstraint(SpringLayout.NORTH, cb_tongTienPhong, 43, SpringLayout.NORTH, pnl_tongTienPhong);
		sl_pnl_tongTienPhong.putConstraint(SpringLayout.WEST, cb_tongTienPhong, 123, SpringLayout.WEST, pnl_tongTienPhong);
		sl_pnl_tongTienPhong.putConstraint(SpringLayout.EAST, cb_tongTienPhong, -10, SpringLayout.EAST, pnl_tongTienPhong);
		sl_pnl_tongTienPhong.putConstraint(SpringLayout.EAST, lbl_tongTienPhong, -6, SpringLayout.WEST, cb_tongTienPhong);
		pnl_tongTienPhong.add(cb_tongTienPhong);
		
		JPanel pnl_tongTienDichVu = new JPanel();
		pnl_tongTienDichVu.setBackground(new Color(255, 255, 255));
		pnl_frmDong3.add(pnl_tongTienDichVu);
		SpringLayout sl_pnl_tongTienDichVu = new SpringLayout();
		pnl_tongTienDichVu.setLayout(sl_pnl_tongTienDichVu);
		
		JLabel lbl_tongTienDVu = new JLabel("Tổng tiền dịch vụ");
		sl_pnl_tongTienDichVu.putConstraint(SpringLayout.NORTH, lbl_tongTienDVu, 32, SpringLayout.NORTH, pnl_tongTienDichVu);
		sl_pnl_tongTienDichVu.putConstraint(SpringLayout.WEST, lbl_tongTienDVu, 10, SpringLayout.WEST, pnl_tongTienDichVu);
		sl_pnl_tongTienDichVu.putConstraint(SpringLayout.SOUTH, lbl_tongTienDVu, -10, SpringLayout.SOUTH, pnl_tongTienDichVu);
		sl_pnl_tongTienDichVu.putConstraint(SpringLayout.EAST, lbl_tongTienDVu, 136, SpringLayout.WEST, pnl_tongTienDichVu);
		lbl_tongTienDVu.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_tongTienDichVu.add(lbl_tongTienDVu);
		
		DefaultComboBoxModel model_tongTienDVu = new DefaultComboBoxModel();
		model_tongTienDVu.addElement("");
		model_tongTienDVu.addElement("Dưới 50.000 VND");
		model_tongTienDVu.addElement("Từ 50.000 VND đến 100.000 VND");
		model_tongTienDVu.addElement("Từ 100.000 VND đến 200.000 VND");
		model_tongTienDVu.addElement("Từ 200.000 VND đến 500.000 VND");
		model_tongTienDVu.addElement("Từ 500.000 VND đến 1.000.000 VND");
		model_tongTienDVu.addElement("Trên 1.000.000 VND");
		
		cb_tongTienDVu = new JComboBox(model_tongTienDVu);
		sl_pnl_tongTienDichVu.putConstraint(SpringLayout.NORTH, cb_tongTienDVu, 12, SpringLayout.NORTH, lbl_tongTienDVu);
		sl_pnl_tongTienDichVu.putConstraint(SpringLayout.WEST, cb_tongTienDVu, 6, SpringLayout.EAST, lbl_tongTienDVu);
		sl_pnl_tongTienDichVu.putConstraint(SpringLayout.EAST, cb_tongTienDVu, -10, SpringLayout.EAST, pnl_tongTienDichVu);
		pnl_tongTienDichVu.add(cb_tongTienDVu);
		
		JPanel pnl_TimKiemLamMoi = new JPanel();
		pnl_TimKiemLamMoi.setBackground(new Color(255, 255, 255));
		pnl_frmDong3.add(pnl_TimKiemLamMoi);
		SpringLayout sl_pnl_TimKiemLamMoi = new SpringLayout();
		pnl_TimKiemLamMoi.setLayout(sl_pnl_TimKiemLamMoi);
		
		btn_timKiem = new JButton("Tìm kiếm");
		sl_pnl_TimKiemLamMoi.putConstraint(SpringLayout.WEST, btn_timKiem, 10, SpringLayout.WEST, pnl_TimKiemLamMoi);
		btn_timKiem.setForeground(new Color(255, 255, 255));
		btn_timKiem.setBackground(new Color(255, 165, 0));
		sl_pnl_TimKiemLamMoi.putConstraint(SpringLayout.NORTH, btn_timKiem, 42, SpringLayout.NORTH, pnl_TimKiemLamMoi);
		sl_pnl_TimKiemLamMoi.putConstraint(SpringLayout.SOUTH, btn_timKiem, -10, SpringLayout.SOUTH, pnl_TimKiemLamMoi);
		btn_timKiem.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_TimKiemLamMoi.add(btn_timKiem);
		
		btn_lamMoi = new JButton("Làm mới");
		sl_pnl_TimKiemLamMoi.putConstraint(SpringLayout.EAST, btn_timKiem, -36, SpringLayout.WEST, btn_lamMoi);
		sl_pnl_TimKiemLamMoi.putConstraint(SpringLayout.SOUTH, btn_lamMoi, 0, SpringLayout.SOUTH, btn_timKiem);
		sl_pnl_TimKiemLamMoi.putConstraint(SpringLayout.NORTH, btn_lamMoi, 0, SpringLayout.NORTH, btn_timKiem);
		sl_pnl_TimKiemLamMoi.putConstraint(SpringLayout.WEST, btn_lamMoi, 171, SpringLayout.WEST, pnl_TimKiemLamMoi);
		sl_pnl_TimKiemLamMoi.putConstraint(SpringLayout.EAST, btn_lamMoi, -10, SpringLayout.EAST, pnl_TimKiemLamMoi);
		btn_lamMoi.setForeground(new Color(255, 255, 255));
		btn_lamMoi.setBackground(new Color(192, 192, 192));
		btn_lamMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_TimKiemLamMoi.add(btn_lamMoi);
		
		JLabel lbl_quanLyHoaDon = new JLabel("QUẢN LÝ HÓA ĐƠN");
		lbl_quanLyHoaDon.setForeground(new Color(255, 128, 64));
		lbl_quanLyHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_quanLyHoaDon.setToolTipText("");
		lbl_quanLyHoaDon.setBounds(10, 10, 238, 41);
		pnl_quanLyHoaDon.add(lbl_quanLyHoaDon);
		
		JLabel lbl_tieuDe = new JLabel("Vui lòng bấm vào bảng danh sách hóa đơn để xem chi tiết");
		lbl_tieuDe.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lbl_tieuDe.setForeground(new Color(0, 0, 0));
		lbl_tieuDe.setBounds(10, 41, 471, 41);
		pnl_quanLyHoaDon.add(lbl_tieuDe);
		
		dateNgayBatDau = new JDateChooser();
		sl_pnl_ngayThue.putConstraint(SpringLayout.NORTH, dateNgayBatDau, -42, SpringLayout.SOUTH, pnl_ngayThue);
		sl_pnl_ngayThue.putConstraint(SpringLayout.SOUTH, dateNgayBatDau, -23, SpringLayout.SOUTH, pnl_ngayThue);
		sl_pnl_ngayThue.putConstraint(SpringLayout.EAST, dateNgayBatDau, -10, SpringLayout.EAST, pnl_ngayThue);
		pnl_ngayThue.add(dateNgayBatDau);
		
		JLabel lbl_ngayThue_1 = new JLabel("Ngày TT: từ ngày");
		sl_pnl_ngayThue.putConstraint(SpringLayout.NORTH, lbl_ngayThue_1, 25, SpringLayout.NORTH, pnl_ngayThue);
		sl_pnl_ngayThue.putConstraint(SpringLayout.SOUTH, lbl_ngayThue_1, -10, SpringLayout.SOUTH, pnl_ngayThue);
		sl_pnl_ngayThue.putConstraint(SpringLayout.WEST, dateNgayBatDau, 6, SpringLayout.EAST, lbl_ngayThue_1);
		sl_pnl_ngayThue.putConstraint(SpringLayout.WEST, lbl_ngayThue_1, 10, SpringLayout.WEST, pnl_ngayThue);
		sl_pnl_ngayThue.putConstraint(SpringLayout.EAST, lbl_ngayThue_1, 119, SpringLayout.WEST, pnl_ngayThue);
		lbl_ngayThue_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnl_ngayThue.add(lbl_ngayThue_1);
		
		functionHoaDon();
	}
	
	public void functionHoaDon() {
		loadTableHoaDon();
		filterHoaDon();
		resetFilter();
		billExportPDF();
	}
	
	public void loadTableHoaDon() {
	    int num = 1;
	    ResultSet listHoaDon = hoaDonBUS.getListHoaDon();
	    
	    DefaultTableModel dtm = new DefaultTableModel(
	            new Object[][] {},
	            new String[] {"STT", "Mã hoá đơn", "Mã chi tiết thuê", "Nhân viên lập phiếu", "Tiền phòng", "Tiền dịch vụ", "Giảm giá", "Phụ thu", "Tổng tiền", "Ngày TT", "Phương thức TT"}) {
	                @Override
	                public boolean isCellEditable(int row, int column) {
	                // Không cho phép chỉnh sửa bất kỳ ô nào
	                    return false;
	                }
	    };
	    
	    tbl_danhSachHoaDon.setModel(dtm);
	    try {
	        while(listHoaDon.next()) {
	            HoaDonDTO hd = new HoaDonDTO();
	            ChiTietThueDTO ctt = new ChiTietThueDTO();
	            ChiTietThuePhongDTO cttp = new ChiTietThuePhongDTO();
	            ChiTietThueDichVuDTO cttdv = new ChiTietThueDichVuDTO();
	            int tongTienHoaDon = 0;
	            
	            hd.setMaHD(listHoaDon.getString(1));
	            hd.setMaCTT(listHoaDon.getString(2));
	            ctt.setMaNV(listHoaDon.getString(3));
	            cttp.setGiaThue(listHoaDon.getInt(4));
	            cttdv.setGiaDV(listHoaDon.getInt(5));
	            hd.setGiamGia(listHoaDon.getInt(6));
	            hd.setPhuThu(listHoaDon.getInt(7));
	            tongTienHoaDon = (int) listHoaDon.getFloat(8);
	            hd.setNgayThanhToan(listHoaDon.getDate(9));
	            hd.setPhuongThucThanhToan(listHoaDon.getInt(10));
	            
	            // Định dạng ngày thanh toán theo chuỗi "dd-MM-yyyy"
	            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	            String ngayThanhToanFormatted = dateFormat.format(hd.getNgayThanhToan());
	            
	            Object[] row = {
	                num++,
	                hd.getMaHD(),
	                hd.getMaCTT(),
	                ctt.getMaNV(),
	                cttp.getGiaThue(),
	                cttdv.getGiaDV(),
	                hd.getGiamGia(),
	                hd.getPhuThu(),
	                tongTienHoaDon,
	                ngayThanhToanFormatted, // Sử dụng ngày đã định dạng
	                hd.getPhuongThucThanhToan() == 0 ? "Tiền mặt" : "Chuyển khoản"
	            };
	            dtm.addRow(row);
	        }
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	public void filterHoaDon() {
		btn_timKiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String maHD = tf_maHoaDon.getText();
				String maCTT = tf_maChiTietThue.getText();
				String tenNV = tf_tenNhanVien.getText();
				String tienPhong = (String) cb_tongTienPhong.getSelectedItem();
				String tienDV = (String) cb_tongTienDVu.getSelectedItem();
				String giamGia = tf_giamGia.getText();
				String phuThu = tf_phuThu.getText();
				String tongTien = (String) cb_tongTien.getSelectedItem();
				String phuongThucTT = (String) cb_phuongThucTT.getSelectedItem();
				Date ngayBatDau = dateNgayBatDau.getDate();
				Date ngayKetThuc = dateNgayKetThuc.getDate();
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		            
				ResultSet filteredList = hoaDonBUS.filterListHoaDon(maHD, maCTT, ngayBatDau, ngayKetThuc, tenNV, giamGia, phuThu, phuongThucTT, tongTien, tienPhong, tienDV);
				int num = 1;
			    
			    DefaultTableModel dtm = new DefaultTableModel(
			            new Object[][] {},
			            new String[] {"STT", "Mã hoá đơn", "Mã chi tiết thuê", "Nhân viên lập phiếu", "Tiền phòng", "Tiền dịch vụ", "Giảm giá", "Phụ thu", "Tổng tiền", "Ngày TT", "Phương thức TT"}) {
			                @Override
			                public boolean isCellEditable(int row, int column) {
			                // Không cho phép chỉnh sửa bất kỳ ô nào
			                    return false;
			                }
			    };
			    
			    tbl_danhSachHoaDon.setModel(dtm);
			    try {
			        while(filteredList.next()) {
			            HoaDonDTO hd = new HoaDonDTO();
			            ChiTietThueDTO ctt = new ChiTietThueDTO();
			            ChiTietThuePhongDTO cttp = new ChiTietThuePhongDTO();
			            ChiTietThueDichVuDTO cttdv = new ChiTietThueDichVuDTO();
			            int tongTienHoaDon = 0;
			            
			            hd.setMaHD(filteredList.getString(1));
			            hd.setMaCTT(filteredList.getString(2));
			            ctt.setMaNV(filteredList.getString(3));
			            cttp.setGiaThue(filteredList.getInt(4));
			            cttdv.setGiaDV(filteredList.getInt(5));
			            hd.setGiamGia(filteredList.getInt(6));
			            hd.setPhuThu(filteredList.getInt(7));
			            tongTienHoaDon = (int) filteredList.getFloat(8);
			            hd.setNgayThanhToan(filteredList.getDate(9));
			            hd.setPhuongThucThanhToan(filteredList.getInt(10));
			            
			            // Định dạng ngày thanh toán theo chuỗi "dd-MM-yyyy"
			            SimpleDateFormat dateFormatFiltered = new SimpleDateFormat("dd-MM-yyyy");
			            String ngayThanhToanFormatted = dateFormatFiltered.format(hd.getNgayThanhToan());
			            
			            Object[] row = {
			                num++,
			                hd.getMaHD(),
			                hd.getMaCTT(),
			                ctt.getMaNV(),
			                cttp.getGiaThue(),
			                cttdv.getGiaDV(),
			                hd.getGiamGia(),
			                hd.getPhuThu(),
			                tongTienHoaDon,
			                ngayThanhToanFormatted, // Sử dụng ngày đã định dạng
			                hd.getPhuongThucThanhToan() == 0 ? "Tiền mặt" : "Chuyển khoản"
			            };
			            dtm.addRow(row);
			        }
			    } catch (SQLException e1) {
			        // TODO Auto-generated catch block
			        e1.printStackTrace();
			    }
			}
		});
	}
	
	public void resetFilter() {
		btn_lamMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tf_maHoaDon.setText("");
				tf_maChiTietThue.setText("");
				tf_tenNhanVien.setText("");
				tf_giamGia.setText("");
				tf_phuThu.setText("");
				cb_phuongThucTT.setSelectedIndex(0);
				cb_tongTien.setSelectedIndex(0);
				cb_tongTienDVu.setSelectedIndex(0);
				cb_tongTienPhong.setSelectedIndex(0);
				dateNgayBatDau.setDate(null);
				dateNgayKetThuc.setDate(null);
				
				loadTableHoaDon();
			}
		});
	}
	
	public void billExportPDF() {
	    btn_InHoaDon.addActionListener(new ActionListener() {
	        
	        @Override
	        public void actionPerformed(ActionEvent evt) {
	            // TODO Auto-generated method stub
	            int selectedRow = tbl_danhSachHoaDon.getSelectedRow();
	            if(selectedRow == -1) {
	                JOptionPane.showMessageDialog(QuanLiHoaDon.this, "Vui lòng chọn hóa đơn để in !");
	            } else {
	            	String maCTT = tbl_danhSachHoaDon.getValueAt(selectedRow, 2).toString();
                    try {
                    	String query = "SELECT " +
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
                                "    HOADON.phuongThucThanhToan, " +
                                "    DICHVU.tenDV, " +
                                "    DICHVU.loaiDV, " +
                                "	 DICHVU.giaDV, " +
                                "    CHITIETTHUEDICHVU.ngaySuDung, " +
                                "    CHITIETTHUEDICHVU.SoLuong, " +
                                "	 PHONG.tenP, " +
                                "	 CHITIETTHUEPHONG.loaiHinhThue, " +
                                "	 CHITIETTHUEPHONG.ngayThue, " +
                                "	 CHITIETTHUEPHONG.ngayCheckOut " +
                           
                                "FROM HOADON " +
                                "    INNER JOIN CHITIETTHUE ON HOADON.maCTT = CHITIETTHUE.maCTT " +
                                "    INNER JOIN NHANVIEN ON CHITIETTHUE.maNV = NHANVIEN.maNV " +
                                "    INNER JOIN CHITIETTHUEDICHVU ON CHITIETTHUE.maCTT = CHITIETTHUEDICHVU.maCTT " +
                                "    INNER JOIN DICHVU ON CHITIETTHUEDICHVU.maDV = DICHVU.maDV " +
                                "	 INNER JOIN CHITIETTHUEPHONG ON CHITIETTHUE.maCTT = CHITIETTHUEPHONG.maCTT " +
                                "	 INNER JOIN PHONG ON CHITIETTHUEPHONG.maP = PHONG.maP " +
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
                                "        ) AS TIENDICHVU ON HOADON.maHD = TIENDICHVU.maHD " +
                                "WHERE CHITIETTHUE.maCTT = '" + maCTT + "' " +
                                "GROUP BY HOADON.maHD, HOADON.maCTT, NHANVIEN.tenNV, TIENPHONG.tienPhong, TIENDICHVU.tienDichVu, HOADON.giamGia, HOADON.phuThu, HOADON.ngayThanhToan, HOADON.phuongThucThanhToan, " +
                                "DICHVU.tenDV, DICHVU.loaiDV, DICHVU.giaDV, CHITIETTHUEDICHVU.ngaySuDung, CHITIETTHUEDICHVU.SoLuong, " +
                                "PHONG.tenP, CHITIETTHUEPHONG.loaiHinhThue, CHITIETTHUEPHONG.ngayThue, CHITIETTHUEPHONG.ngayCheckOut; ";
                    
                    	Database db = new Database();
                        ResultSet rs = db.getList(query);
                        	                        
                        // Thêm thông tin nhân viên lập hóa đơn và ngày lập hóa đơn từ kết quả truy vấn
                        while (rs.next()) {
                            String maHD = rs.getString("maHD");
                            String tenNV = rs.getString("tenNV");
                            Date ngayThanhToan = rs.getDate("ngayThanhToan");
                            String tenDV = rs.getString("tenDV");
                            String loaiDV = rs.getString("loaiDV");
                            int soLuong = rs.getInt("SoLuong");
                            int donGia = rs.getInt("giaDV");
                            int tongTienDV = rs.getInt("TongTienDV");
                            int tongTienHD = rs.getInt("TongTien");
                            String tenPhong = rs.getString("tenP");
                            String loaiHinhThue = rs.getInt("loaiHinhThue") == 0 ? "Theo giờ" : "Theo ngày";
                            Timestamp ngayThue = rs.getTimestamp("ngayThue");
                            Timestamp ngayCheckOut = rs.getTimestamp("ngayCheckOut");
                            int tongTienPhong = rs.getInt("TongTienPhong");
                    	
                            Document document = new Document();
                            try {
                            	// Tạo một đối tượng PdfWriter để ghi tài liệu vào một OutputStream
                            	PdfWriter.getInstance(document, new FileOutputStream(maHD + ".pdf"));
	                    
                            	// Mở tài liệu để bắt đầu viết
                            	document.open();
	                    
			                    // Khởi tạo một phông chữ từ một font có sẵn trên hệ thống Windows
			                    BaseFont bf = BaseFont.createFont("C://Windows//Fonts//Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			                    com.itextpdf.text.Font font = new com.itextpdf.text.Font(bf, 12);
			                    com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.BOLD);
			                    
			                    Paragraph hotelName = new Paragraph("KHÁCH SẠN LUXURY", boldFont);
			                    Paragraph address = new Paragraph("Địa chỉ : 273 An Dương Vương, Phường 3, Quận 5, TP HCM", boldFont);
			                    Paragraph hotline = new Paragraph("Hotline Booking : 0987654321", boldFont);
			                    Paragraph fax = new Paragraph("FAX : 0987654321", boldFont);
			                    Paragraph spacePara1 = new Paragraph("\n\n");
			                    
			                    hotelName.setAlignment(Element.ALIGN_CENTER);
			                    address.setAlignment(Element.ALIGN_CENTER);
			                    hotline.setAlignment(Element.ALIGN_CENTER);
			                    fax.setAlignment(Element.ALIGN_CENTER);
			                    
			                    document.add(hotelName);
			                    document.add(address);
			                    document.add(hotline);
			                    document.add(fax);
			                    document.add(spacePara1);

			                    // Tạo tài liệu PDF
	                            Paragraph spacePara2 = new Paragraph("\n", font);
		                        Paragraph titleHoaDon = new Paragraph("THÔNG TIN HÓA ĐƠN", boldFont);
		                        Paragraph paraMaCTT = new Paragraph("Mã chi tiết thuê: " + maCTT, font);
		                        Paragraph paraTenNV = new Paragraph("Nhân viên lập hóa đơn: " + tenNV, font);
		                        Paragraph paraNgayTT = new Paragraph("Ngày lập hóa đơn: " + ngayThanhToan.toString(), font);
		                        Paragraph paraTongTienHD = new Paragraph("Tổng tiền hóa đơn: " + tongTienHD, font);
		            
		                        document.add(titleHoaDon);
		                        document.add(paraMaCTT);
		                        document.add(paraTenNV);
		                        document.add(paraNgayTT);
		                        document.add(paraTongTienHD);
		                        document.add(spacePara2);
		                        
		                        Paragraph spacePara3 = new Paragraph("\n", font);
		                        Paragraph titleDichVu = new Paragraph("THÔNG TIN THUÊ DỊCH VỤ", boldFont);
		                        Paragraph paraTenDV = new Paragraph("Mã chi tiết thuê: " + maCTT, font);
		                        Paragraph paraLoaiDV = new Paragraph("Loại dịch vụ: " + loaiDV, font);
		                        Paragraph paraNgayTTDV = new Paragraph("Ngày lập hóa đơn: " + ngayThanhToan.toString(), font);
		                        Paragraph paraSoLuong = new Paragraph("Số lượng: " + soLuong, font);
		                        Paragraph paraDonGia = new Paragraph("Đơn giá: " + donGia, font);
		                        Paragraph paraTongTienDV = new Paragraph("Tổng tiền dịch vụ: " + tongTienDV , font);
		                       
		                        document.add(titleDichVu);
		                        document.add(paraTenDV);
		                        document.add(paraLoaiDV);
		                        document.add(paraNgayTTDV);
		                        document.add(paraSoLuong);
		                        document.add(paraDonGia);
		                        document.add(paraTongTienDV);
		                        document.add(spacePara3);
		                        
		                        Paragraph titleThuePhong = new Paragraph("THÔNG TIN THUÊ PHÒNG", boldFont);
		                        Paragraph paraTenPhong = new Paragraph("Tên phòng: " + tenPhong, font);
		                        Paragraph paraLoaiHinhThue = new Paragraph("Loại hình thuê: " + loaiHinhThue, font);
		                        Paragraph paraNgayThue = new Paragraph("Ngày thuê: " + ngayThue, font);
		                        Paragraph paraNgayCheckOut = new Paragraph("Ngày checkout: " + ngayCheckOut, font);
		                        Paragraph paraTongTienPhong = new Paragraph("Tổng tiền phòng: " + tongTienPhong, font);
		                        
		                        document.add(titleThuePhong);
		                        document.add(paraTenPhong);
		                        document.add(paraLoaiHinhThue);
		                        document.add(paraNgayThue);
		                        document.add(paraNgayCheckOut);
		                        document.add(paraTongTienPhong);
                            } catch (Exception e) {
                            	e.printStackTrace();
							}
                            // Đóng tài liệu
    	                    document.close();
    		                JOptionPane.showMessageDialog(QuanLiHoaDon.this, "In hóa đơn thành công.");
                        }
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
	    });
	}
}
