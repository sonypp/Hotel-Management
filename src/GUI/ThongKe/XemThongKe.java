package GUI.ThongKe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SpringLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import BUS.HoaDonBUS;

import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class XemThongKe extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnl_xemThongKe;
	private JDateChooser datefromLP;
	private JDateChooser datetoLP;
	private JDateChooser datefromCTLP;
	private JDateChooser datetoCTLP;
	private JPanel pnl_bieuDoThang;
	 Font fontTitle = new Font("Segoe UI", Font.BOLD, 10);
     Font font = new Font("Segoe UI", Font.PLAIN, 9);
     HoaDonBUS hd = new HoaDonBUS();
	private JLabel lbTongDoanhThuDichVu;
	private JLabel lbTongTienDichVuPhanTich;
	private JLabel lbTongDoanhThuPhong;
	private JLabel lbTongTienPhongPhanTich;
	private JLabel lbTongDoanhThu;
	private JLabel lbTongDoanhThuPhanTich;
	private JLabel lbTongPhuThuPhanTich;
	private JLabel lbTongGiamGiaThanhToan;
	private JDateChooser dateThang;
	private JDateChooser dateNam;

	public XemThongKe() {
		setBounds(0, 0, 1251, 835);
		setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
		
        pnl_xemThongKe = new JPanel();
		pnl_xemThongKe.setBounds(10, 0, 1251, 835);
		pnl_xemThongKe.setBackground(new Color(245, 245, 245));
		pnl_xemThongKe.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_xemThongKe.setLayout(null);
		add(pnl_xemThongKe);
		Border leftBorder_1 = BorderFactory.createMatteBorder(0,8,0,0,Color.ORANGE);
		Border leftBorder_2 = BorderFactory.createMatteBorder(0,8,0,0,Color.ORANGE);
		Border leftBorder_3 = BorderFactory.createMatteBorder(0,8,0,0,Color.ORANGE);
		
		JPanel pnl_pTichTongDoanhThu = new JPanel();
		pnl_pTichTongDoanhThu.setForeground(new Color(0, 0, 0));
		pnl_pTichTongDoanhThu.setBackground(new Color(0, 128, 128));
		pnl_pTichTongDoanhThu.setBounds(925, 10, 302, 323);
		pnl_xemThongKe.add(pnl_pTichTongDoanhThu);
		pnl_pTichTongDoanhThu.setLayout(null);
		
		JPanel pnl_phanTichDoanhThu = new JPanel();
		pnl_phanTichDoanhThu.setForeground(new Color(47, 79, 79));
		pnl_phanTichDoanhThu.setBackground(new Color(255, 255, 255));
		pnl_phanTichDoanhThu.setBounds(0, 0, 302, 161);
		pnl_pTichTongDoanhThu.add(pnl_phanTichDoanhThu);
		pnl_phanTichDoanhThu.setLayout(null);
		
		JLabel lbl_tieuDePTich = new JLabel("PHÂN TÍCH TỔNG DOANH THU");
		lbl_tieuDePTich.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tieuDePTich.setForeground(new Color(0, 128, 128));
		lbl_tieuDePTich.setBounds(10, 10, 279, 80);
		lbl_tieuDePTich.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnl_phanTichDoanhThu.add(lbl_tieuDePTich);
		
		lbTongDoanhThuPhanTich = new JLabel("702,500 VNĐ");
		lbTongDoanhThuPhanTich.setHorizontalAlignment(SwingConstants.CENTER);
		lbTongDoanhThuPhanTich.setForeground(new Color(0, 128, 128));
		lbTongDoanhThuPhanTich.setBounds(10, 71, 283, 80);
		lbTongDoanhThuPhanTich.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnl_phanTichDoanhThu.add(lbTongDoanhThuPhanTich);
		
		JLabel lbl_leftTienPhong = new JLabel("Tổng tiền phòng");
		lbl_leftTienPhong.setForeground(new Color(255, 255, 255));
		lbl_leftTienPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_leftTienPhong.setBounds(10, 162, 112, 34);
		pnl_pTichTongDoanhThu.add(lbl_leftTienPhong);
		
		JLabel lbl_leftTienDVu = new JLabel("Tổng tiền dịch vụ");
		lbl_leftTienDVu.setForeground(new Color(255, 255, 255));
		lbl_leftTienDVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_leftTienDVu.setBounds(10, 206, 127, 27);
		pnl_pTichTongDoanhThu.add(lbl_leftTienDVu);
		
		JLabel lbl_leftTienPhuThu = new JLabel("Tổng phụ thu");
		lbl_leftTienPhuThu.setForeground(new Color(255, 255, 255));
		lbl_leftTienPhuThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_leftTienPhuThu.setBounds(10, 243, 112, 34);
		pnl_pTichTongDoanhThu.add(lbl_leftTienPhuThu);
		
		JLabel lbl_leftTienGiamGia = new JLabel("Tổng tiền giảm giá");
		lbl_leftTienGiamGia.setForeground(new Color(255, 255, 255));
		lbl_leftTienGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_leftTienGiamGia.setBounds(10, 285, 127, 28);
		pnl_pTichTongDoanhThu.add(lbl_leftTienGiamGia);
		
		lbTongTienPhongPhanTich = new JLabel("700,000 VNĐ");
		lbTongTienPhongPhanTich.setForeground(new Color(255, 255, 255));
		lbTongTienPhongPhanTich.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbTongTienPhongPhanTich.setBounds(142, 162, 112, 34);
		pnl_pTichTongDoanhThu.add(lbTongTienPhongPhanTich);
		
		lbTongTienDichVuPhanTich = new JLabel("2,500 VNĐ");
		lbTongTienDichVuPhanTich.setForeground(new Color(255, 255, 255));
		lbTongTienDichVuPhanTich.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbTongTienDichVuPhanTich.setBounds(140, 200, 144, 39);
		pnl_pTichTongDoanhThu.add(lbTongTienDichVuPhanTich);
		
		lbTongPhuThuPhanTich = new JLabel("0 VNĐ");
		lbTongPhuThuPhanTich.setForeground(new Color(255, 255, 255));
		lbTongPhuThuPhanTich.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbTongPhuThuPhanTich.setBounds(143, 243, 155, 34);
		pnl_pTichTongDoanhThu.add(lbTongPhuThuPhanTich);
		
		lbTongGiamGiaThanhToan = new JLabel("0 VNĐ");
		lbTongGiamGiaThanhToan.setForeground(new Color(255, 255, 255));
		lbTongGiamGiaThanhToan.setBounds(143, 281, 141, 36);
		pnl_pTichTongDoanhThu.add(lbTongGiamGiaThanhToan);
		lbTongGiamGiaThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel pnl_frmNhapNgayThang1 = new JPanel();
		pnl_frmNhapNgayThang1.setBackground(new Color(255, 255, 255));
		pnl_frmNhapNgayThang1.setBounds(10, 145, 448, 188);
		pnl_xemThongKe.add(pnl_frmNhapNgayThang1);
		pnl_frmNhapNgayThang1.setLayout(null);
		
		JLabel lblNewLabel_13 = new JLabel("Từ ngày");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_13.setBounds(10, 63, 77, 29);
		pnl_frmNhapNgayThang1.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Đến ngày");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_14.setBounds(10, 104, 77, 29);
		pnl_frmNhapNgayThang1.add(lblNewLabel_14);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(179, 299, 196, 137);
		pnl_frmNhapNgayThang1.add(lblNewLabel_3);
		
		datefromLP = new JDateChooser();
		datefromLP.getCalendarButton().setHideActionText(true);
		datefromLP.getCalendarButton().setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		datefromLP.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		datefromLP.getCalendarButton().setBackground((Color) null);
		datefromLP.setPreferredSize(new Dimension(150, 30));
		datefromLP.setForeground((Color) null);
		datefromLP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		datefromLP.setDateFormatString("dd/MM/yyyy");
		datefromLP.setBackground((Color) null);
		datefromLP.setBounds(86, 64, 129, 30);
		pnl_frmNhapNgayThang1.add(datefromLP);
		
		datetoLP = new JDateChooser();
		datetoLP.getCalendarButton().setHideActionText(true);
		datetoLP.getCalendarButton().setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		datetoLP.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		datetoLP.getCalendarButton().setBackground((Color) null);
		datetoLP.setPreferredSize(new Dimension(150, 30));
		datetoLP.setForeground((Color) null);
		datetoLP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		datetoLP.setDateFormatString("dd/MM/yyyy");
		datetoLP.setBackground((Color) null);
		datetoLP.setBounds(86, 102, 129, 30);
		pnl_frmNhapNgayThang1.add(datetoLP);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(225, 10, 213, 168);
		pnl_frmNhapNgayThang1.add(panel);
		
		    
	        
		JPanel panel_frmNhapNgayThang2 = new JPanel();
		
		
		panel_frmNhapNgayThang2.setBackground(new Color(255, 255, 255));
		panel_frmNhapNgayThang2.setBounds(468, 145, 447, 188);
		pnl_xemThongKe.add(panel_frmNhapNgayThang2);
		panel_frmNhapNgayThang2.setLayout(null);
		
		JLabel lblNewLabel_13_1 = new JLabel("Từ ngày");
		lblNewLabel_13_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_13_1.setBounds(10, 67, 129, 29);
		panel_frmNhapNgayThang2.add(lblNewLabel_13_1);
		
		JLabel lblNewLabel_14_1 = new JLabel("Đến ngày");
		lblNewLabel_14_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_14_1.setBounds(10, 106, 77, 29);
		panel_frmNhapNgayThang2.add(lblNewLabel_14_1);
		
		datefromCTLP = new JDateChooser();
		datefromCTLP.getCalendarButton().setHideActionText(true);
		datefromCTLP.getCalendarButton().setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		datefromCTLP.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		datefromCTLP.getCalendarButton().setBackground((Color) null);
		datefromCTLP.setPreferredSize(new Dimension(150, 30));
		datefromCTLP.setForeground((Color) null);
		datefromCTLP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		datefromCTLP.setDateFormatString("dd/MM/yyyy");
		datefromCTLP.setBackground((Color) null);
		datefromCTLP.setBounds(87, 66, 137, 30);
		panel_frmNhapNgayThang2.add(datefromCTLP);
		
		datetoCTLP = new JDateChooser();
		datetoCTLP.getCalendarButton().setHideActionText(true);
		datetoCTLP.getCalendarButton().setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		datetoCTLP.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		datetoCTLP.getCalendarButton().setBackground((Color) null);
		datetoCTLP.setPreferredSize(new Dimension(150, 30));
		datetoCTLP.setForeground((Color) null);
		datetoCTLP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		datetoCTLP.setDateFormatString("dd/MM/yyyy");
		datetoCTLP.setBackground((Color) null);
		datetoCTLP.setBounds(87, 105, 137, 30);
		panel_frmNhapNgayThang2.add(datetoCTLP);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(234, 10, 203, 168);
		panel_frmNhapNgayThang2.add(panel_1);
		
	
	    // panel_1.add(chartPanelCTLP, BorderLayout.CENTER);
		
		JPanel pnl_thongKeNam = new JPanel();
		pnl_thongKeNam.setBounds(563, 343, 664, 455);
		pnl_xemThongKe.add(pnl_thongKeNam);
		SpringLayout sl_pnl_thongKeNam = new SpringLayout();
		pnl_thongKeNam.setLayout(sl_pnl_thongKeNam);
		
		JPanel pnl_chonNam = new JPanel();
		sl_pnl_thongKeNam.putConstraint(SpringLayout.NORTH, pnl_chonNam, 0, SpringLayout.NORTH, pnl_thongKeNam);
		sl_pnl_thongKeNam.putConstraint(SpringLayout.WEST, pnl_chonNam, 0, SpringLayout.WEST, pnl_thongKeNam);
		sl_pnl_thongKeNam.putConstraint(SpringLayout.SOUTH, pnl_chonNam, 45, SpringLayout.NORTH, pnl_thongKeNam);
		sl_pnl_thongKeNam.putConstraint(SpringLayout.EAST, pnl_chonNam, 664, SpringLayout.WEST, pnl_thongKeNam);
		pnl_thongKeNam.add(pnl_chonNam);
		pnl_chonNam.setLayout(null);
		
		JLabel lbl_chonNam = new JLabel("Chọn năm");
		lbl_chonNam.setBounds(10, 12, 120, 23);
		lbl_chonNam.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnl_chonNam.add(lbl_chonNam);
		
		JPanel pnl_bieuDoNam = new JPanel();
		sl_pnl_thongKeNam.putConstraint(SpringLayout.NORTH, pnl_bieuDoNam, 4, SpringLayout.SOUTH, pnl_chonNam);
		
		dateNam = new JDateChooser();
		dateNam.getCalendarButton().setHideActionText(true);
		dateNam.getCalendarButton().setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		dateNam.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateNam.getCalendarButton().setBackground((Color) null);
		dateNam.setPreferredSize(new Dimension(150, 30));
		dateNam.setForeground((Color) null);
		dateNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateNam.setDateFormatString("yyyy");
		dateNam.setBackground((Color) null);
		dateNam.setBounds(100, 10, 554, 30);
		pnl_chonNam.add(dateNam);
		sl_pnl_thongKeNam.putConstraint(SpringLayout.WEST, pnl_bieuDoNam, 0, SpringLayout.WEST, pnl_thongKeNam);
		sl_pnl_thongKeNam.putConstraint(SpringLayout.SOUTH, pnl_bieuDoNam, -10, SpringLayout.SOUTH, pnl_thongKeNam);
		sl_pnl_thongKeNam.putConstraint(SpringLayout.EAST, pnl_bieuDoNam, 664, SpringLayout.WEST, pnl_thongKeNam);
		pnl_thongKeNam.add(pnl_bieuDoNam);
		pnl_bieuDoNam.setLayout(new BoxLayout(pnl_bieuDoNam, BoxLayout.X_AXIS));
		
		JPanel pnl_thongKeThang = new JPanel();
		pnl_thongKeThang.setBounds(10, 343, 543, 455);
		pnl_xemThongKe.add(pnl_thongKeThang);
		SpringLayout sl_pnl_thongKeThang = new SpringLayout();
		pnl_thongKeThang.setLayout(sl_pnl_thongKeThang);
		
		JPanel pnl_chonThang = new JPanel();
		sl_pnl_thongKeThang.putConstraint(SpringLayout.NORTH, pnl_chonThang, 0, SpringLayout.NORTH, pnl_thongKeThang);
		sl_pnl_thongKeThang.putConstraint(SpringLayout.WEST, pnl_chonThang, 0, SpringLayout.WEST, pnl_thongKeThang);
		sl_pnl_thongKeThang.putConstraint(SpringLayout.SOUTH, pnl_chonThang, 46, SpringLayout.NORTH, pnl_thongKeThang);
		sl_pnl_thongKeThang.putConstraint(SpringLayout.EAST, pnl_chonThang, 543, SpringLayout.WEST, pnl_thongKeThang);
		pnl_thongKeThang.add(pnl_chonThang);
		pnl_chonThang.setLayout(null);
		
		JLabel lbl_chonThang = new JLabel("Chọn tháng");
		lbl_chonThang.setBounds(10, 10, 95, 26);
		lbl_chonThang.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnl_chonThang.add(lbl_chonThang);
		
		pnl_bieuDoThang = new JPanel();
		
		sl_pnl_thongKeThang.putConstraint(SpringLayout.NORTH, pnl_bieuDoThang, 6, SpringLayout.SOUTH, pnl_chonThang);
		
		dateThang = new JDateChooser();
		dateThang.setBounds(115, 10, 425, 30);
		dateThang.getCalendarButton().setHideActionText(true);
		dateThang.getCalendarButton().setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		dateThang.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateThang.getCalendarButton().setBackground((Color) null);
		dateThang.setPreferredSize(new Dimension(150, 30));
		dateThang.setForeground((Color) null);
		dateThang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateThang.setDateFormatString("MM/yyyy");
		dateThang.setBackground((Color) null);
		pnl_chonThang.add(dateThang);
		sl_pnl_thongKeThang.putConstraint(SpringLayout.WEST, pnl_bieuDoThang, 0, SpringLayout.WEST, pnl_thongKeThang);
		sl_pnl_thongKeThang.putConstraint(SpringLayout.SOUTH, pnl_bieuDoThang, -10, SpringLayout.SOUTH, pnl_thongKeThang);
		sl_pnl_thongKeThang.putConstraint(SpringLayout.EAST, pnl_bieuDoThang, 0, SpringLayout.EAST, pnl_thongKeThang);
		pnl_thongKeThang.add(pnl_bieuDoThang);
		pnl_bieuDoThang.setLayout(new BorderLayout(0, 0));
	//	pnl_bieuDoThang.add(chartPanel);
		
		JPanel pnl_tongDoanhThuPhong = new JPanel();
		pnl_tongDoanhThuPhong.setBounds(619, 10, 296, 125);
		pnl_xemThongKe.add(pnl_tongDoanhThuPhong);
		pnl_tongDoanhThuPhong.setBackground(new Color(255, 255, 255));
		pnl_tongDoanhThuPhong.setBorder(leftBorder_3);
		pnl_tongDoanhThuPhong.setLayout(null);
		
		JLabel lbl_tieuDeChinh3 = new JLabel("Tổng doanh thu phòng");
		lbl_tieuDeChinh3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_tieuDeChinh3.setBounds(20, 10, 333, 31);
		pnl_tongDoanhThuPhong.add(lbl_tieuDeChinh3);
		
		lbTongDoanhThuPhong = new JLabel("702,500 VNĐ");
		lbTongDoanhThuPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTongDoanhThuPhong.setBounds(20, 51, 226, 39);
		pnl_tongDoanhThuPhong.add(lbTongDoanhThuPhong);
		
		JLabel lbl_tieuDePhu3 = new JLabel("Doanh thu tháng trước");
		lbl_tieuDePhu3.setForeground(new Color(128, 0, 0));
		lbl_tieuDePhu3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_tieuDePhu3.setBackground(Color.RED);
		lbl_tieuDePhu3.setBounds(20, 90, 290, 35);
		pnl_tongDoanhThuPhong.add(lbl_tieuDePhu3);
		
		JPanel pnl_tongDoanhThuDVu = new JPanel();
		pnl_tongDoanhThuDVu.setBounds(313, 10, 296, 125);
		pnl_xemThongKe.add(pnl_tongDoanhThuDVu);
		pnl_tongDoanhThuDVu.setBackground(new Color(255, 255, 255));
		pnl_tongDoanhThuDVu.setBorder(leftBorder_2);
		pnl_tongDoanhThuDVu.setLayout(null);
		
		JLabel lbl_tieuDeChinh2 = new JLabel("Tổng doanh thu dịch vụ");
		lbl_tieuDeChinh2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_tieuDeChinh2.setBounds(20, 10, 227, 31);
		pnl_tongDoanhThuDVu.add(lbl_tieuDeChinh2);
		
		lbTongDoanhThuDichVu = new JLabel("702,500 VNĐ");
		lbTongDoanhThuDichVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTongDoanhThuDichVu.setBounds(20, 51, 227, 40);
		pnl_tongDoanhThuDVu.add(lbTongDoanhThuDichVu);
		
		JLabel lbl_tieuDePhu2 = new JLabel("Doanh thu tháng trước");
		lbl_tieuDePhu2.setForeground(new Color(128, 0, 0));
		lbl_tieuDePhu2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_tieuDePhu2.setBackground(Color.RED);
		lbl_tieuDePhu2.setBounds(20, 92, 227, 33);
		pnl_tongDoanhThuDVu.add(lbl_tieuDePhu2);
				
						JPanel pnl_tongDoanhThu = new JPanel();
						pnl_tongDoanhThu.setBounds(10, 10, 296, 125);
						pnl_xemThongKe.add(pnl_tongDoanhThu);
						pnl_tongDoanhThu.setBackground(new Color(255, 255, 255));
						pnl_tongDoanhThu.setBorder(new LineBorder(new Color(178, 34, 34)));
						pnl_tongDoanhThu.setBorder(leftBorder_1);
						pnl_tongDoanhThu.setLayout(null);
						
						JLabel lbl_tieuDeChinh1 = new JLabel("Tổng doanh thu");
						lbl_tieuDeChinh1.setFont(new Font("Tahoma", Font.BOLD, 18));
						lbl_tieuDeChinh1.setBounds(20, 10, 266, 31);
						pnl_tongDoanhThu.add(lbl_tieuDeChinh1);
						
						lbTongDoanhThu = new JLabel("702,500 VNĐ");
						lbTongDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 16));
						lbTongDoanhThu.setBounds(20, 51, 266, 35);
						pnl_tongDoanhThu.add(lbTongDoanhThu);
						
						JLabel lbl_tieuDePhu_1 = new JLabel("Doanh thu tháng trước");
						lbl_tieuDePhu_1.setBackground(new Color(255, 0, 0));
						lbl_tieuDePhu_1.setForeground(new Color(128, 0, 0));
						lbl_tieuDePhu_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
						lbl_tieuDePhu_1.setBounds(20, 90, 266, 35);
						//Init();

						
		
	}

    private void Init()
    {
    	DecimalFormat formatter = new DecimalFormat("###,###0 VNĐ");
        String currentDate = LocalDate.now().toString();
        
        // Example usage
        String tongTienDV = formatter.format(hd.TongTienDV());
        String tongTienPhong = formatter.format(hd.TongTienPhong());
        String tongDoanhThu = formatter.format(hd.TongDoanhThu());
        String tongPhuThu = formatter.format(hd.TongPhuThu());
        String tongGiamGia = formatter.format(hd.TongGiamGia());

        lbTongDoanhThuDichVu.setText(tongTienDV);
        lbTongTienDichVuPhanTich.setText(tongTienDV);
        lbTongDoanhThuPhong.setText(tongTienPhong);
        lbTongTienPhongPhanTich.setText(tongTienPhong);

        lbTongDoanhThu.setText(tongDoanhThu);
        lbTongDoanhThuPhanTich.setText(tongDoanhThu);

        lbTongPhuThuPhanTich.setText(tongPhuThu);
        lbTongGiamGiaThanhToan.setText(tongGiamGia);

        BieuDoPhongLoad BDPPanel = new BieuDoPhongLoad(currentDate, currentDate);
        BieuDoLoaiPhongLoad BDLPPanel = new BieuDoLoaiPhongLoad(currentDate, currentDate);
        LocalDate parsedDate = LocalDate.parse(currentDate);
        int month = parsedDate.getMonthValue();
        int year = parsedDate.getYear();
        BieuDoThongKeThangLoad BDTKTPanel = new BieuDoThongKeThangLoad(month, year);
        BieuDoThongKeNamLoad BDTKNPanel = new BieuDoThongKeNamLoad(year);
    }
    
}
