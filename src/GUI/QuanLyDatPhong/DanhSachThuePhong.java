package GUI.QuanLyDatPhong;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSplitPane;

public class DanhSachThuePhong extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tf_maChiTietThue;
	private JTextField tf_maKhachHang;
	private JTextField tf_tenKhachHang;
	private JTextField tf_maNhanVien;
	private JTextField tf_tenNhanVien;
	private JTable tbl_chiTietThuePhong;
	
	/**
	 * Create the panel.
	 */
	public DanhSachThuePhong() {
		setBounds(0, 0, 1251, 835);
		setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        
        JPanel pnl_danhSachDatPhong = new JPanel();
        pnl_danhSachDatPhong.setBackground(new Color(245, 245, 245));
        pnl_danhSachDatPhong.setBounds(0, 0, 1251, 835);
        add(pnl_danhSachDatPhong);
        pnl_danhSachDatPhong.setLayout(null);
        
        JLabel lbl_danhSachThuePhong = new JLabel("Danh Sách Thuê Phòng");
        lbl_danhSachThuePhong.setForeground(new Color(30, 144, 255));
        lbl_danhSachThuePhong.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_danhSachThuePhong.setBounds(10, 10, 315, 41);
        pnl_danhSachDatPhong.add(lbl_danhSachThuePhong);
        
        JLabel lbl_tieuDePhu = new JLabel("Vui lòng chọn phòng khách hàng muốn thuê");
        lbl_tieuDePhu.setForeground(Color.BLACK);
        lbl_tieuDePhu.setFont(new Font("Tahoma", Font.ITALIC, 15));
        lbl_tieuDePhu.setBounds(10, 42, 355, 41);
        pnl_danhSachDatPhong.add(lbl_tieuDePhu);
        
        JPanel pnl_nhapThuePhong = new JPanel();
        pnl_nhapThuePhong.setBounds(10, 93, 1231, 197);
        pnl_danhSachDatPhong.add(pnl_nhapThuePhong);
        pnl_nhapThuePhong.setLayout(new GridLayout(2, 1, 0, 0));
        
        JPanel pnl_frmDong1 = new JPanel();
        pnl_nhapThuePhong.add(pnl_frmDong1);
        pnl_frmDong1.setLayout(new GridLayout(1, 4, 0, 0));
        
        JPanel pnl_maChiTietThue = new JPanel();
        pnl_maChiTietThue.setBackground(new Color(255, 255, 255));
        pnl_frmDong1.add(pnl_maChiTietThue);
        SpringLayout sl_pnl_maChiTietThue = new SpringLayout();
        pnl_maChiTietThue.setLayout(sl_pnl_maChiTietThue);
        
        JLabel lbl_maChiTietThue = new JLabel("Mã chi tiết thuê");
        sl_pnl_maChiTietThue.putConstraint(SpringLayout.NORTH, lbl_maChiTietThue, 10, SpringLayout.NORTH, pnl_maChiTietThue);
        sl_pnl_maChiTietThue.putConstraint(SpringLayout.WEST, lbl_maChiTietThue, 10, SpringLayout.WEST, pnl_maChiTietThue);
        sl_pnl_maChiTietThue.putConstraint(SpringLayout.SOUTH, lbl_maChiTietThue, 29, SpringLayout.NORTH, pnl_maChiTietThue);
        sl_pnl_maChiTietThue.putConstraint(SpringLayout.EAST, lbl_maChiTietThue, -182, SpringLayout.EAST, pnl_maChiTietThue);
        lbl_maChiTietThue.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnl_maChiTietThue.add(lbl_maChiTietThue);
        
        tf_maChiTietThue = new JTextField();
        sl_pnl_maChiTietThue.putConstraint(SpringLayout.NORTH, tf_maChiTietThue, 9, SpringLayout.NORTH, pnl_maChiTietThue);
        sl_pnl_maChiTietThue.putConstraint(SpringLayout.WEST, tf_maChiTietThue, 6, SpringLayout.EAST, lbl_maChiTietThue);
        sl_pnl_maChiTietThue.putConstraint(SpringLayout.EAST, tf_maChiTietThue, -10, SpringLayout.EAST, pnl_maChiTietThue);
        pnl_maChiTietThue.add(tf_maChiTietThue);
        tf_maChiTietThue.setColumns(10);

        JPanel pnl_maKhachHang = new JPanel();
        pnl_maKhachHang.setBackground(new Color(255, 255, 255));
        pnl_frmDong1.add(pnl_maKhachHang);
        SpringLayout sl_pnl_maKhachHang = new SpringLayout();
        pnl_maKhachHang.setLayout(sl_pnl_maKhachHang);
        
        JLabel lbl_maKhachHang = new JLabel("Mã khách hàng ");
        sl_pnl_maKhachHang.putConstraint(SpringLayout.EAST, lbl_maKhachHang, 114, SpringLayout.WEST, pnl_maKhachHang);
        lbl_maKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        sl_pnl_maKhachHang.putConstraint(SpringLayout.NORTH, lbl_maKhachHang, 10, SpringLayout.NORTH, pnl_maKhachHang);
        sl_pnl_maKhachHang.putConstraint(SpringLayout.WEST, lbl_maKhachHang, 10, SpringLayout.WEST, pnl_maKhachHang);
        pnl_maKhachHang.add(lbl_maKhachHang);
        
        tf_maKhachHang = new JTextField();
        sl_pnl_maKhachHang.putConstraint(SpringLayout.NORTH, tf_maKhachHang, -1, SpringLayout.NORTH, lbl_maKhachHang);
        sl_pnl_maKhachHang.putConstraint(SpringLayout.WEST, tf_maKhachHang, 6, SpringLayout.EAST, lbl_maKhachHang);
        sl_pnl_maKhachHang.putConstraint(SpringLayout.EAST, tf_maKhachHang, -10, SpringLayout.EAST, pnl_maKhachHang);
        tf_maKhachHang.setColumns(10);
        pnl_maKhachHang.add(tf_maKhachHang);
        
        JPanel pnl_tenKhachHang = new JPanel();
        pnl_tenKhachHang.setBackground(new Color(255, 255, 255));
        pnl_frmDong1.add(pnl_tenKhachHang);
        SpringLayout sl_pnl_tenKhachHang = new SpringLayout();
        pnl_tenKhachHang.setLayout(sl_pnl_tenKhachHang);
        
        JLabel lbl_tenKhachHang = new JLabel("Tên khách hàng");
        sl_pnl_tenKhachHang.putConstraint(SpringLayout.EAST, lbl_tenKhachHang, 121, SpringLayout.WEST, pnl_tenKhachHang);
        lbl_tenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
        sl_pnl_tenKhachHang.putConstraint(SpringLayout.NORTH, lbl_tenKhachHang, 10, SpringLayout.NORTH, pnl_tenKhachHang);
        sl_pnl_tenKhachHang.putConstraint(SpringLayout.WEST, lbl_tenKhachHang, 10, SpringLayout.WEST, pnl_tenKhachHang);
        pnl_tenKhachHang.add(lbl_tenKhachHang);
        
        tf_tenKhachHang = new JTextField();
        sl_pnl_tenKhachHang.putConstraint(SpringLayout.NORTH, tf_tenKhachHang, 9, SpringLayout.NORTH, pnl_tenKhachHang);
        sl_pnl_tenKhachHang.putConstraint(SpringLayout.WEST, tf_tenKhachHang, 6, SpringLayout.EAST, lbl_tenKhachHang);
        sl_pnl_tenKhachHang.putConstraint(SpringLayout.EAST, tf_tenKhachHang, -10, SpringLayout.EAST, pnl_tenKhachHang);
        tf_tenKhachHang.setColumns(10);
        pnl_tenKhachHang.add(tf_tenKhachHang);
        
        JPanel pnl_maNhanVien = new JPanel();
        pnl_maNhanVien.setBackground(new Color(255, 255, 255));
        pnl_frmDong1.add(pnl_maNhanVien);
        SpringLayout sl_pnl_maNhanVien = new SpringLayout();
        pnl_maNhanVien.setLayout(sl_pnl_maNhanVien);
        
        JLabel lbl_maNhanVien = new JLabel("Mã nhân viên");
        sl_pnl_maNhanVien.putConstraint(SpringLayout.WEST, lbl_maNhanVien, 10, SpringLayout.WEST, pnl_maNhanVien);
        sl_pnl_maNhanVien.putConstraint(SpringLayout.EAST, lbl_maNhanVien, -197, SpringLayout.EAST, pnl_maNhanVien);
        lbl_maNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
        sl_pnl_maNhanVien.putConstraint(SpringLayout.NORTH, lbl_maNhanVien, 10, SpringLayout.NORTH, pnl_maNhanVien);
        pnl_maNhanVien.add(lbl_maNhanVien);
        
        tf_maNhanVien = new JTextField();
        sl_pnl_maNhanVien.putConstraint(SpringLayout.NORTH, tf_maNhanVien, -1, SpringLayout.NORTH, lbl_maNhanVien);
        sl_pnl_maNhanVien.putConstraint(SpringLayout.WEST, tf_maNhanVien, 6, SpringLayout.EAST, lbl_maNhanVien);
        sl_pnl_maNhanVien.putConstraint(SpringLayout.EAST, tf_maNhanVien, -10, SpringLayout.EAST, pnl_maNhanVien);
        tf_maNhanVien.setColumns(10);
        pnl_maNhanVien.add(tf_maNhanVien);
        
        JPanel pnl_frmDong2 = new JPanel();
        pnl_nhapThuePhong.add(pnl_frmDong2);
        pnl_frmDong2.setLayout(new GridLayout(1, 5, 0, 0));
        
        JPanel pnl_tienCoc = new JPanel();
        pnl_tienCoc.setBackground(new Color(255, 255, 255));
        pnl_frmDong2.add(pnl_tienCoc);
        SpringLayout sl_pnl_tienCoc = new SpringLayout();
        pnl_tienCoc.setLayout(sl_pnl_tienCoc);
        
        JLabel lbl_tienCoc = new JLabel("Tiền cọc");
        sl_pnl_tienCoc.putConstraint(SpringLayout.NORTH, lbl_tienCoc, 10, SpringLayout.NORTH, pnl_tienCoc);
        sl_pnl_tienCoc.putConstraint(SpringLayout.WEST, lbl_tienCoc, 10, SpringLayout.WEST, pnl_tienCoc);
        sl_pnl_tienCoc.putConstraint(SpringLayout.EAST, lbl_tienCoc, 107, SpringLayout.WEST, pnl_tienCoc);
        lbl_tienCoc.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnl_tienCoc.add(lbl_tienCoc);
        
        JComboBox cb_tienCoc = new JComboBox();
        sl_pnl_tienCoc.putConstraint(SpringLayout.NORTH, cb_tienCoc, 8, SpringLayout.NORTH, pnl_tienCoc);
        sl_pnl_tienCoc.putConstraint(SpringLayout.WEST, cb_tienCoc, 6, SpringLayout.EAST, lbl_tienCoc);
        sl_pnl_tienCoc.putConstraint(SpringLayout.EAST, cb_tienCoc, 190, SpringLayout.EAST, lbl_tienCoc);
        pnl_tienCoc.add(cb_tienCoc);
        
        JPanel pnl_tinhTrang = new JPanel();
        pnl_tinhTrang.setBackground(new Color(255, 255, 255));
        pnl_frmDong2.add(pnl_tinhTrang);
        SpringLayout sl_pnl_tinhTrang = new SpringLayout();
        pnl_tinhTrang.setLayout(sl_pnl_tinhTrang);
        
        JLabel lbl_tinhTrang = new JLabel("Tình trạng xử lí");
        sl_pnl_tinhTrang.putConstraint(SpringLayout.EAST, lbl_tinhTrang, 121, SpringLayout.WEST, pnl_tinhTrang);
        lbl_tinhTrang.setFont(new Font("Tahoma", Font.BOLD, 12));
        sl_pnl_tinhTrang.putConstraint(SpringLayout.NORTH, lbl_tinhTrang, 10, SpringLayout.NORTH, pnl_tinhTrang);
        sl_pnl_tinhTrang.putConstraint(SpringLayout.WEST, lbl_tinhTrang, 10, SpringLayout.WEST, pnl_tinhTrang);
        pnl_tinhTrang.add(lbl_tinhTrang);
        
        JComboBox cb_tinhTrang = new JComboBox();
        sl_pnl_tinhTrang.putConstraint(SpringLayout.NORTH, cb_tinhTrang, -2, SpringLayout.NORTH, lbl_tinhTrang);
        sl_pnl_tinhTrang.putConstraint(SpringLayout.WEST, cb_tinhTrang, 6, SpringLayout.EAST, lbl_tinhTrang);
        sl_pnl_tinhTrang.putConstraint(SpringLayout.EAST, cb_tinhTrang, -10, SpringLayout.EAST, pnl_tinhTrang);
        pnl_tinhTrang.add(cb_tinhTrang);
        
        JPanel pnl_ngayLapPhieu = new JPanel();
        pnl_ngayLapPhieu.setBackground(new Color(255, 255, 255));
        pnl_frmDong2.add(pnl_ngayLapPhieu);
        SpringLayout sl_pnl_ngayLapPhieu = new SpringLayout();
        pnl_ngayLapPhieu.setLayout(sl_pnl_ngayLapPhieu);
        
        JLabel lbl_ngayLapPhieu = new JLabel("Ngày lập phiếu");
        lbl_ngayLapPhieu.setFont(new Font("Tahoma", Font.BOLD, 12));
        sl_pnl_ngayLapPhieu.putConstraint(SpringLayout.NORTH, lbl_ngayLapPhieu, 10, SpringLayout.NORTH, pnl_ngayLapPhieu);
        sl_pnl_ngayLapPhieu.putConstraint(SpringLayout.WEST, lbl_ngayLapPhieu, 10, SpringLayout.WEST, pnl_ngayLapPhieu);
        pnl_ngayLapPhieu.add(lbl_ngayLapPhieu);
        
        JComboBox cb_ngayLapPhieu = new JComboBox();
        sl_pnl_ngayLapPhieu.putConstraint(SpringLayout.NORTH, cb_ngayLapPhieu, -2, SpringLayout.NORTH, lbl_ngayLapPhieu);
        sl_pnl_ngayLapPhieu.putConstraint(SpringLayout.WEST, cb_ngayLapPhieu, 26, SpringLayout.EAST, lbl_ngayLapPhieu);
        sl_pnl_ngayLapPhieu.putConstraint(SpringLayout.EAST, cb_ngayLapPhieu, -10, SpringLayout.EAST, pnl_ngayLapPhieu);
        pnl_ngayLapPhieu.add(cb_ngayLapPhieu);
        
        JPanel pnl_tenNhanVien = new JPanel();
        pnl_tenNhanVien.setBackground(new Color(255, 255, 255));
        pnl_frmDong2.add(pnl_tenNhanVien);
        SpringLayout sl_pnl_tenNhanVien = new SpringLayout();
        pnl_tenNhanVien.setLayout(sl_pnl_tenNhanVien);
        
        JLabel lbl_tenNhanVIen = new JLabel("Tên nhân viên");
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.EAST, lbl_tenNhanVIen, 115, SpringLayout.WEST, pnl_tenNhanVien);
        lbl_tenNhanVIen.setFont(new Font("Tahoma", Font.BOLD, 12));
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.NORTH, lbl_tenNhanVIen, 10, SpringLayout.NORTH, pnl_tenNhanVien);
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.WEST, lbl_tenNhanVIen, 10, SpringLayout.WEST, pnl_tenNhanVien);
        pnl_tenNhanVien.add(lbl_tenNhanVIen);
        
        JButton btn_timKiem = new JButton("Tìm kiếm");
        btn_timKiem.setForeground(new Color(255, 255, 255));
        btn_timKiem.setBackground(new Color(123, 104, 238));
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.SOUTH, btn_timKiem, -10, SpringLayout.SOUTH, pnl_tenNhanVien);
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.EAST, btn_timKiem, -155, SpringLayout.EAST, pnl_tenNhanVien);
        btn_timKiem.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnl_tenNhanVien.add(btn_timKiem);
        
        JButton btn_lamMoi = new JButton("Làm mới");
        btn_lamMoi.setBackground(new Color(147, 112, 219));
        btn_lamMoi.setForeground(new Color(255, 255, 255));
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.NORTH, btn_lamMoi, 0, SpringLayout.NORTH, btn_timKiem);
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.WEST, btn_lamMoi, 36, SpringLayout.EAST, btn_timKiem);
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.SOUTH, btn_lamMoi, -10, SpringLayout.SOUTH, pnl_tenNhanVien);
        btn_lamMoi.setFont(new Font("Tahoma", Font.BOLD, 12));
        pnl_tenNhanVien.add(btn_lamMoi);
        
        tf_tenNhanVien = new JTextField();
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.NORTH, btn_timKiem, 27, SpringLayout.SOUTH, tf_tenNhanVien);
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.NORTH, tf_tenNhanVien, -1, SpringLayout.NORTH, lbl_tenNhanVIen);
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.WEST, tf_tenNhanVien, 6, SpringLayout.EAST, lbl_tenNhanVIen);
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.EAST, tf_tenNhanVien, -10, SpringLayout.EAST, pnl_tenNhanVien);
        tf_tenNhanVien.setColumns(10);
        pnl_tenNhanVien.add(tf_tenNhanVien);
        
        JPanel pnl_chiTietThuePhong = new JPanel();
        pnl_chiTietThuePhong.setBackground(new Color(255, 255, 255));
        pnl_chiTietThuePhong.setBounds(10, 300, 1231, 525);
        pnl_danhSachDatPhong.add(pnl_chiTietThuePhong);
        SpringLayout sl_pnl_chiTietThuePhong = new SpringLayout();
        pnl_chiTietThuePhong.setLayout(sl_pnl_chiTietThuePhong);
        
        JLabel lbl_chiTietThuePhong = new JLabel("DANH SÁCH CHI TIẾT THUÊ");
        lbl_chiTietThuePhong.setForeground(new Color(128, 128, 0));
        lbl_chiTietThuePhong.setFont(new Font("Tahoma", Font.BOLD, 20));
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.NORTH, lbl_chiTietThuePhong, 10, SpringLayout.NORTH, pnl_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.WEST, lbl_chiTietThuePhong, 10, SpringLayout.WEST, pnl_chiTietThuePhong);
        pnl_chiTietThuePhong.add(lbl_chiTietThuePhong);
        
        String column[] = {"STT","MÃ CT THUÊ", "MÃ KHÁCH HÀNG", "TÊN KHÁCH HÀNG", "MÃ NHÂN VIÊN", "NHÂN VIÊN LẬP PHIẾU", "NGÀY LẬP PHIẾU", "TIỀN ĐẶT CỌC", "TÌNH TRẠNG"};
        String data[][] = {};
        
        
        tbl_chiTietThuePhong = new JTable(data, column);
        int[] columnWidths = {30, 120, 120, 180, 120, 150, 100, 100, 100}; // Độ rộng mong muốn cho từng cột
		int columnIndex = 0;
		for (int width : columnWidths) {
		    TableColumn columnSize = tbl_chiTietThuePhong.getColumnModel().getColumn(columnIndex++);
		    columnSize.setPreferredWidth(width);
		}
		tbl_chiTietThuePhong.setRowHeight(30);
		
		JTableHeader tableHeader = tbl_chiTietThuePhong.getTableHeader();
		tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30));
		tableHeader.setBackground(Color.BLACK);
		tableHeader.setForeground(Color.white);
		
		Font headerFont = new Font("Arial", Font.BOLD, 14); // Tạo font mới: Arial, đậm, kích thước 14
		tableHeader.setFont(headerFont); 
		
		// Tùy chỉnh các tính chất khác của bảng
		tbl_chiTietThuePhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbl_chiTietThuePhong.setShowGrid(false);
		
        
        JScrollPane scrp_chiTietThuePhong = new JScrollPane(tbl_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.NORTH, scrp_chiTietThuePhong, 6, SpringLayout.SOUTH, lbl_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.WEST, scrp_chiTietThuePhong, 10, SpringLayout.WEST, pnl_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.SOUTH, scrp_chiTietThuePhong, 437, SpringLayout.SOUTH, lbl_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.EAST, scrp_chiTietThuePhong, 1221, SpringLayout.WEST, pnl_chiTietThuePhong);
        pnl_chiTietThuePhong.add(scrp_chiTietThuePhong);
	
        JButton btn_xemChiTiet = new JButton("Xem chi tiết");
        btn_xemChiTiet.setBackground(new Color(147, 112, 219));
        btn_xemChiTiet.setForeground(new Color(255, 255, 255));
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.NORTH, btn_xemChiTiet, 6, SpringLayout.SOUTH, scrp_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.SOUTH, btn_xemChiTiet, -14, SpringLayout.SOUTH, pnl_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.EAST, btn_xemChiTiet, -10, SpringLayout.EAST, pnl_chiTietThuePhong);
        btn_xemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 12));
        

        pnl_chiTietThuePhong.add(btn_xemChiTiet);
        
	}
}

