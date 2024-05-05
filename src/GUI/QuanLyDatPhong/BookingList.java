package GUI.QuanLyDatPhong;



import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.toedter.calendar.JDateChooser;

import BUS.*;
import DTO.ChiTietThueDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import GUI.Home.HomeForm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookingList extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txt_MaCTT;
	private JTextField txt_MaKH;
	private JTextField txt_TenKH;
	private JTextField txt_MaNV;
	private JTextField tf_tenNhanVien;
	private JTable tbRoom;
	private DefaultTableModel tbRoomModel;
	
	KhachHangBUS khachHang = new KhachHangBUS();
	NhanVienBUS nhanVien = new NhanVienBUS();
	ChiTietThueBUS chiTietThue = new ChiTietThueBUS();
	private JDateChooser dateTime_NgayLap;
	
	public void HienThiDSChiTietThue() {
	    AtomicInteger stt = new AtomicInteger(0); // Initialize AtomicInteger to 0
	    var cttAll = chiTietThue.getDSChiTietThue().stream()
	            .filter(ctt -> ctt.getXuLy() == 0)
	            .sorted(Comparator.comparing(ChiTietThueDTO::getTinhTrangXuLy).thenComparing(ChiTietThueDTO::getNgayLapPhieu))
	            .map(ctt -> {
	                KhachHangDTO kh = khachHang.GetDSKH().stream()
	                        .filter(k -> k.getMaKH().equals(ctt.getMaKH()))
	                        .findFirst().orElse(null);

	                NhanVienDTO nv = nhanVien.getAllNhanVien().stream()
	                        .filter(n -> n.getMaNV().equals(ctt.getMaNV()))
	                        .findFirst().orElse(null);

	                return new Object[] {
	                    stt.incrementAndGet(), // Increment stt and get the incremented value
	                    ctt.getMaCTT(),
	                    ctt.getMaKH(),
	                    kh != null ? kh.getTenKH() : null,
	                    ctt.getMaNV(),
	                    nv != null ? nv.getTenNV() : null,
	                    ctt.getNgayLapPhieu().toString(),
	                    ctt.getTienDatCoc(),
	                    ctt.getTinhTrangXuLy() == 0 ? "Chưa xử lý" : "Đã xử lý",
	                };
	            })
	            .collect(Collectors.toList());
	    tbRoomModel.setRowCount(0); // Clear the existing rows

	    for (Object[] ctt : cttAll) {
	        tbRoomModel.addRow(ctt);
	    }
	    tbRoom.clearSelection();
	}
	
	private void btnView_Click() {
	    if (tbRoom.getSelectedRows().length > 0) {
	        String status = tbRoom.getValueAt(tbRoom.getSelectedRows()[0], 8).toString();
	        String maCTT = tbRoom.getValueAt(tbRoom.getSelectedRows()[0], 1).toString();
	        
	        FormChiTietThue frmBookingNew;
	        if (status.equals("Chưa xử lý")) {
	            frmBookingNew = new FormChiTietThue(0, maCTT);
	        } else {
	            frmBookingNew = new FormChiTietThue(1, maCTT);
	        }
	        
	        HomeForm form = (HomeForm) getParentForm(this);
	        JPanel pnContent = form.panelChinh;
	        disposeThis();
	        pnContent.add(frmBookingNew);
	        frmBookingNew.setVisible(true);
	    } else {
	        JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu thuê muốn xem chi tiết", "Thông báo", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void disposeThis() {
		this.setVisible(false);
	}

	private Container getParentForm(Container container) {
	    if (container instanceof HomeForm) {
	        return container;
	    }
	    return getParentForm(container.getParent());
	}


	
	/**
	 * Create the panel.
	 */
	public BookingList() {
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
        pnl_frmDong1.setLayout(new GridLayout(0, 5, 0, 0));
        
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
        
        txt_MaCTT = new JTextField();
        sl_pnl_maChiTietThue.putConstraint(SpringLayout.NORTH, txt_MaCTT, 9, SpringLayout.NORTH, pnl_maChiTietThue);
        sl_pnl_maChiTietThue.putConstraint(SpringLayout.WEST, txt_MaCTT, 6, SpringLayout.EAST, lbl_maChiTietThue);
        sl_pnl_maChiTietThue.putConstraint(SpringLayout.EAST, txt_MaCTT, -10, SpringLayout.EAST, pnl_maChiTietThue);
        pnl_maChiTietThue.add(txt_MaCTT);
        txt_MaCTT.setColumns(10);

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
        
        txt_MaKH = new JTextField();
        sl_pnl_maKhachHang.putConstraint(SpringLayout.NORTH, txt_MaKH, -1, SpringLayout.NORTH, lbl_maKhachHang);
        sl_pnl_maKhachHang.putConstraint(SpringLayout.WEST, txt_MaKH, 6, SpringLayout.EAST, lbl_maKhachHang);
        sl_pnl_maKhachHang.putConstraint(SpringLayout.EAST, txt_MaKH, -10, SpringLayout.EAST, pnl_maKhachHang);
        txt_MaKH.setColumns(10);
        pnl_maKhachHang.add(txt_MaKH);
        
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
        
        txt_TenKH = new JTextField();
        sl_pnl_tenKhachHang.putConstraint(SpringLayout.NORTH, txt_TenKH, 9, SpringLayout.NORTH, pnl_tenKhachHang);
        sl_pnl_tenKhachHang.putConstraint(SpringLayout.WEST, txt_TenKH, 6, SpringLayout.EAST, lbl_tenKhachHang);
        sl_pnl_tenKhachHang.putConstraint(SpringLayout.EAST, txt_TenKH, -10, SpringLayout.EAST, pnl_tenKhachHang);
        txt_TenKH.setColumns(10);
        pnl_tenKhachHang.add(txt_TenKH);
        
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
        
        txt_MaNV = new JTextField();
        sl_pnl_maNhanVien.putConstraint(SpringLayout.NORTH, txt_MaNV, -1, SpringLayout.NORTH, lbl_maNhanVien);
        sl_pnl_maNhanVien.putConstraint(SpringLayout.WEST, txt_MaNV, 6, SpringLayout.EAST, lbl_maNhanVien);
        sl_pnl_maNhanVien.putConstraint(SpringLayout.EAST, txt_MaNV, -10, SpringLayout.EAST, pnl_maNhanVien);
        txt_MaNV.setColumns(10);
        pnl_maNhanVien.add(txt_MaNV);
        
        JPanel pnl_tenNhanVien = new JPanel();
        pnl_frmDong1.add(pnl_tenNhanVien);
        pnl_tenNhanVien.setBackground(new Color(255, 255, 255));
        SpringLayout sl_pnl_tenNhanVien = new SpringLayout();
        pnl_tenNhanVien.setLayout(sl_pnl_tenNhanVien);
        
        JLabel lbl_tenNhanVIen = new JLabel("Tên nhân viên");
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.EAST, lbl_tenNhanVIen, 115, SpringLayout.WEST, pnl_tenNhanVien);
        lbl_tenNhanVIen.setFont(new Font("Tahoma", Font.BOLD, 12));
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.NORTH, lbl_tenNhanVIen, 10, SpringLayout.NORTH, pnl_tenNhanVien);
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.WEST, lbl_tenNhanVIen, 10, SpringLayout.WEST, pnl_tenNhanVien);
        pnl_tenNhanVien.add(lbl_tenNhanVIen);
        
        tf_tenNhanVien = new JTextField();
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.NORTH, tf_tenNhanVien, -1, SpringLayout.NORTH, lbl_tenNhanVIen);
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.WEST, tf_tenNhanVien, 6, SpringLayout.EAST, lbl_tenNhanVIen);
        sl_pnl_tenNhanVien.putConstraint(SpringLayout.EAST, tf_tenNhanVien, -10, SpringLayout.EAST, pnl_tenNhanVien);
        tf_tenNhanVien.setColumns(10);
        pnl_tenNhanVien.add(tf_tenNhanVien);
        
        JPanel pnl_frmDong2 = new JPanel();
        pnl_nhapThuePhong.add(pnl_frmDong2);
        pnl_frmDong2.setLayout(new GridLayout(0, 5, 0, 0));
        
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
        
        JComboBox cb_TienCoc = new JComboBox();
        sl_pnl_tienCoc.putConstraint(SpringLayout.NORTH, cb_TienCoc, 8, SpringLayout.NORTH, pnl_tienCoc);
        sl_pnl_tienCoc.putConstraint(SpringLayout.WEST, cb_TienCoc, 6, SpringLayout.EAST, lbl_tienCoc);
        sl_pnl_tienCoc.putConstraint(SpringLayout.EAST, cb_TienCoc, 190, SpringLayout.EAST, lbl_tienCoc);
        pnl_tienCoc.add(cb_TienCoc);
        
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
        
        JComboBox cb_TinhTrangXuLy = new JComboBox();
        sl_pnl_tinhTrang.putConstraint(SpringLayout.NORTH, cb_TinhTrangXuLy, -2, SpringLayout.NORTH, lbl_tinhTrang);
        sl_pnl_tinhTrang.putConstraint(SpringLayout.WEST, cb_TinhTrangXuLy, 6, SpringLayout.EAST, lbl_tinhTrang);
        sl_pnl_tinhTrang.putConstraint(SpringLayout.EAST, cb_TinhTrangXuLy, -10, SpringLayout.EAST, pnl_tinhTrang);
        pnl_tinhTrang.add(cb_TinhTrangXuLy);
        
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
        
        dateTime_NgayLap = new JDateChooser();
        sl_pnl_ngayLapPhieu.putConstraint(SpringLayout.NORTH, dateTime_NgayLap, -2, SpringLayout.NORTH, lbl_ngayLapPhieu);
        sl_pnl_ngayLapPhieu.putConstraint(SpringLayout.WEST, dateTime_NgayLap, 26, SpringLayout.EAST, lbl_ngayLapPhieu);
        sl_pnl_ngayLapPhieu.putConstraint(SpringLayout.EAST, dateTime_NgayLap, -10, SpringLayout.EAST, pnl_ngayLapPhieu);
        pnl_ngayLapPhieu.add(dateTime_NgayLap);
        
        JPanel pnl_tenNhanVien_1 = new JPanel();
        pnl_tenNhanVien_1.setBackground(Color.WHITE);
        pnl_frmDong2.add(pnl_tenNhanVien_1);
        pnl_tenNhanVien_1.setLayout(new SpringLayout());
        
        JPanel pnl_tenNhanVien_2 = new JPanel();
        pnl_tenNhanVien_2.setBackground(Color.WHITE);
        pnl_frmDong2.add(pnl_tenNhanVien_2);
        SpringLayout sl_pnl_tenNhanVien_2 = new SpringLayout();
        pnl_tenNhanVien_2.setLayout(sl_pnl_tenNhanVien_2);
        
        JButton btnReset = new JButton("Làm mới");
        sl_pnl_tenNhanVien_2.putConstraint(SpringLayout.WEST, btnReset, 133, SpringLayout.WEST, pnl_tenNhanVien_2);
        sl_pnl_tenNhanVien_2.putConstraint(SpringLayout.SOUTH, btnReset, -10, SpringLayout.SOUTH, pnl_tenNhanVien_2);
        sl_pnl_tenNhanVien_2.putConstraint(SpringLayout.EAST, btnReset, -10, SpringLayout.EAST, pnl_tenNhanVien_2);
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnReset.setBackground(new Color(123, 104, 238));
        pnl_tenNhanVien_2.add(btnReset);
        
        JButton btnSearch = new JButton("Tìm kiếm");
        sl_pnl_tenNhanVien_2.putConstraint(SpringLayout.WEST, btnSearch, 10, SpringLayout.WEST, pnl_tenNhanVien_2);
        sl_pnl_tenNhanVien_2.putConstraint(SpringLayout.SOUTH, btnSearch, -10, SpringLayout.SOUTH, pnl_tenNhanVien_2);
        sl_pnl_tenNhanVien_2.putConstraint(SpringLayout.EAST, btnSearch, -20, SpringLayout.WEST, btnReset);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSearch.setBackground(new Color(147, 112, 219));
        pnl_tenNhanVien_2.add(btnSearch);
        
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
        
        String column[] = {"STT","MÃ CT THUÊ", "MÃ KHÁCH HÀNG", "TÊN KHÁCH HÀNG", "MÃ NHÂN VIÊN", "NHÂN VIÊN LẬP PHIẾU", "NGÀY LẬP PHIẾU", "TIỀN ĐẶT CỌC", "TÌNH TRẠNG", "XÓA"};
        String data[][] = {};
        
        tbRoomModel = new DefaultTableModel(data, column);
        
        tbRoom = new JTable(tbRoomModel);
        int[] columnWidths = {30, 120, 120, 180, 120, 150, 100, 100, 100}; 
        int columnIndex = 0;
        for (int width : columnWidths) {
            TableColumn columnSize = tbRoom.getColumnModel().getColumn(columnIndex++);
            columnSize.setPreferredWidth(width);
        }
        tbRoom.setRowHeight(30);
        
        // Customize the table header
        JTableHeader tableHeader = tbRoom.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30));
        tableHeader.setBackground(Color.MAGENTA);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setReorderingAllowed(false);
        
        // Set font style and alignment for column headers
        Font headerFont = new Font("Arial", Font.BOLD, 14);
        tableHeader.setFont(headerFont);
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tbRoom.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setVerticalAlignment(SwingConstants.CENTER);
        headerRenderer.setText("<html><font color='white'>Name</font></html>"); // Change 'Name' to your column header text

        // Customize other properties of the table
        tbRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tbRoom.setShowGrid(false);
		
        
        JScrollPane scrp_chiTietThuePhong = new JScrollPane(tbRoom);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.NORTH, scrp_chiTietThuePhong, 6, SpringLayout.SOUTH, lbl_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.WEST, scrp_chiTietThuePhong, 10, SpringLayout.WEST, pnl_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.SOUTH, scrp_chiTietThuePhong, 386, SpringLayout.SOUTH, lbl_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.EAST, scrp_chiTietThuePhong, 1221, SpringLayout.WEST, pnl_chiTietThuePhong);
        pnl_chiTietThuePhong.add(scrp_chiTietThuePhong);
	
        JButton btnView = new JButton("Xem chi tiết");
        btnView.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		btnView_Click();
        	}
        });
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.NORTH, btnView, 6, SpringLayout.SOUTH, scrp_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.SOUTH, btnView, -65, SpringLayout.SOUTH, pnl_chiTietThuePhong);
        sl_pnl_chiTietThuePhong.putConstraint(SpringLayout.EAST, btnView, 0, SpringLayout.EAST, scrp_chiTietThuePhong);
        btnView.setBackground(new Color(147, 112, 219));
        btnView.setForeground(new Color(255, 255, 255));
        btnView.setFont(new Font("Tahoma", Font.BOLD, 12));
        

        pnl_chiTietThuePhong.add(btnView);
        
        tbRoom.clearSelection();
        HienThiDSChiTietThue();
        dateTime_NgayLap.setDateFormatString(" ");
	}
}

