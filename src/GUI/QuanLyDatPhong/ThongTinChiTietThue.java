package GUI.QuanLyDatPhong;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ThongTinChiTietThue extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtHoTenKhachHang;
	private JTextField txtCMND;
	private JTextField txtSoDienThoai;
	private JTextField textField_2;
	private JTextField txtNgayLapPhieu;
	private JTextField txtTienDatCoc;
	private JTextField txtTinhTrangXuly;
	private JTable tbDSPhongKhachThue;
	private JTable tbDSDichVuKhachThue;

	/**
	 * Create the panel.
	 */
	public ThongTinChiTietThue() {
		setBounds(0, 0, 1251, 835);
		setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 27, 373, 798);
        add(panel);
        panel.setLayout(null);
        
        JPanel pnChiTietThue = new JPanel();
        pnChiTietThue.setBackground(new Color(255, 255, 255));
        pnChiTietThue.setBorder(new LineBorder(new Color(255, 169, 128), 8));
        pnChiTietThue.setBounds(0, 0, 373, 94);
        panel.add(pnChiTietThue);
        pnChiTietThue.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Chi tiết thuê");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_1.setBounds(10, 10, 170, 34);
        pnChiTietThue.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Thông tin phiếu thuê");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
        lblNewLabel_1_1.setBounds(10, 50, 170, 34);
        pnChiTietThue.add(lblNewLabel_1_1);
        
        JPanel pnThuePhong = new JPanel();
        pnThuePhong.setLayout(null);
        pnThuePhong.setBorder(new LineBorder(new Color(255, 169, 128), 8));
        pnThuePhong.setBackground(Color.WHITE);
        pnThuePhong.setBounds(0, 85, 373, 94);
        panel.add(pnThuePhong);
        
        JLabel lbChiTietThue = new JLabel("Thuê phòng");
        lbChiTietThue.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lbChiTietThue.setBounds(10, 10, 170, 34);
        pnThuePhong.add(lbChiTietThue);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Thuê phòng mới cho khách hàng");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
        lblNewLabel_1_1_1.setBounds(10, 50, 257, 34);
        pnThuePhong.add(lblNewLabel_1_1_1);
        
        JPanel pnThueDichVu = new JPanel();
        pnThueDichVu.setLayout(null);
        pnThueDichVu.setBorder(new LineBorder(new Color(255, 169, 128), 8));
        pnThueDichVu.setBackground(Color.WHITE);
        pnThueDichVu.setBounds(0, 174, 373, 94);
        panel.add(pnThueDichVu);
        
        JLabel lblThuDchV = new JLabel("Thuê dịch vụ");
        lblThuDchV.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblThuDchV.setBounds(10, 10, 170, 34);
        pnThueDichVu.add(lblThuDchV);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Thuê dịch vụ mới khách hàng");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
        lblNewLabel_1_1_1_1.setBounds(10, 50, 257, 34);
        pnThueDichVu.add(lblNewLabel_1_1_1_1);
        
        JLabel lblNewLabel_2 = new JLabel("Thông tin phiếu thuê");
        lblNewLabel_2.setForeground(new Color(255, 112, 43));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_2.setBounds(0, 269, 373, 43);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Mã chi tiết thuê:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(10, 322, 157, 28);
        panel.add(lblNewLabel_3);
        
        JLabel lblN = new JLabel("Họ tên khách hàng:");
        lblN.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN.setBounds(13, 391, 144, 36);
        panel.add(lblN);
        
        JComboBox cbbMaChiTietThue = new JComboBox();
        cbbMaChiTietThue.setBounds(10, 360, 353, 21);
        panel.add(cbbMaChiTietThue);
        
        txtHoTenKhachHang = new JTextField();
        txtHoTenKhachHang.setBounds(10, 425, 353, 19);
        panel.add(txtHoTenKhachHang);
        txtHoTenKhachHang.setColumns(10);
        
        JLabel lblCmndcccd = new JLabel("CMND/CCCD");
        lblCmndcccd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCmndcccd.setBounds(13, 454, 144, 43);
        panel.add(lblCmndcccd);
        
        txtCMND = new JTextField();
        txtCMND.setColumns(10);
        txtCMND.setBounds(10, 489, 353, 19);
        panel.add(txtCMND);
        
        JLabel lblN_1_1 = new JLabel("Số điện thoại:");
        lblN_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN_1_1.setBounds(13, 526, 144, 36);
        panel.add(lblN_1_1);
        
        txtSoDienThoai = new JTextField();
        txtSoDienThoai.setColumns(10);
        txtSoDienThoai.setBounds(13, 556, 350, 19);
        panel.add(txtSoDienThoai);
        
        JLabel lblN_1_2 = new JLabel("Họ tên nhân viên lập phiếu:");
        lblN_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN_1_2.setBounds(13, 582, 228, 36);
        panel.add(lblN_1_2);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(13, 612, 350, 19);
        panel.add(textField_2);
        
        txtNgayLapPhieu = new JTextField();
        txtNgayLapPhieu.setColumns(10);
        txtNgayLapPhieu.setBounds(13, 660, 350, 19);
        panel.add(txtNgayLapPhieu);
        
        JLabel lblN_1_2_1 = new JLabel("Ngày lập phiếu");
        lblN_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN_1_2_1.setBounds(10, 628, 228, 33);
        panel.add(lblN_1_2_1);
        
        txtTienDatCoc = new JTextField();
        txtTienDatCoc.setColumns(10);
        txtTienDatCoc.setBounds(10, 717, 350, 19);
        panel.add(txtTienDatCoc);
        
        JLabel lblN_1_2_1_1 = new JLabel("Tiền đặt cọc");
        lblN_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN_1_2_1_1.setBounds(10, 684, 228, 36);
        panel.add(lblN_1_2_1_1);
        
        txtTinhTrangXuly = new JTextField();
        txtTinhTrangXuly.setColumns(10);
        txtTinhTrangXuly.setBounds(10, 769, 350, 19);
        panel.add(txtTinhTrangXuly);
        
        JLabel lblN_1_2_1_1_1 = new JLabel("Tình trạng xử lý");
        lblN_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN_1_2_1_1_1.setBounds(10, 738, 228, 36);
        panel.add(lblN_1_2_1_1_1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(411, 27, 830, 798);
        add(panel_1);
        panel_1.setLayout(null);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 5, 810, 363);
        panel_1.add(panel_2);
        panel_2.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Danh sách phòng khách thuê");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel.setBounds(10, 10, 267, 33);
        panel_2.add(lblNewLabel);
        
        JScrollPane scrpDSPhongKhachThue = new JScrollPane();
        scrpDSPhongKhachThue.setBounds(10, 50, 790, 303);
        panel_2.add(scrpDSPhongKhachThue);
        
        tbDSPhongKhachThue = new JTable();
        tbDSPhongKhachThue.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"STT","Mã phòng","Tên phòng","Tình trạng","Loại hình thuê","Ngày thuê","Ngày trả","Ngày checkout","Giá phòng","Sửa","Xóa"
        	}
        ));
        scrpDSPhongKhachThue.setViewportView(tbDSPhongKhachThue);
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setLayout(null);
        panel_2_1.setBounds(10, 382, 810, 363);
        panel_1.add(panel_2_1);
        
        JLabel lblDanhSchDch = new JLabel("Danh sách dịch vụ khách thuê");
        lblDanhSchDch.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblDanhSchDch.setBounds(10, 10, 267, 33);
        panel_2_1.add(lblDanhSchDch);
        
        JScrollPane scrpDanhSachDichVuKhachThue = new JScrollPane();
        scrpDanhSachDichVuKhachThue.setBounds(10, 50, 790, 303);
        panel_2_1.add(scrpDanhSachDichVuKhachThue);
        
        tbDSDichVuKhachThue = new JTable();
        tbDSDichVuKhachThue.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"STT","Mã dịch vụ","Tên dịch vụ","Ngày sử dụng","Số lượng","Đơn giản","Thành tiền","Sửa","Xóa"
        	}
        ));
        scrpDanhSachDichVuKhachThue.setViewportView(tbDSDichVuKhachThue);
        
        JLabel lbTongTien = new JLabel("New label");
        lbTongTien.setForeground(new Color(255, 0, 0));
        lbTongTien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        lbTongTien.setBounds(325, 755, 142, 33);
        panel_1.add(lbTongTien);
        
        JButton btnThanhToan = new JButton("Thanh Toán");
        btnThanhToan.setBackground(new Color(87, 87, 255));
        btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnThanhToan.setBounds(441, 755, 186, 33);
        panel_1.add(btnThanhToan);
        
        JButton btnInPhieuThue = new JButton("In Phiếu Thuê");
        btnInPhieuThue.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnInPhieuThue.setBackground(new Color(87, 87, 255));
        btnInPhieuThue.setBounds(637, 755, 167, 33);
        panel_1.add(btnInPhieuThue);
	}
}
