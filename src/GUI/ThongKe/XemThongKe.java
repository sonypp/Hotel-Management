package GUI.ThongKe;



//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.general.DefaultPieDataset;
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
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;

public class XemThongKe extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnl_xemThongKe;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField tf_chonNam;
	private JTextField tf_chonThang;

	public XemThongKe() {
		setBounds(0, 0, 1251, 835);
		setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
		
        pnl_xemThongKe = new JPanel();
		pnl_xemThongKe.setBounds(0, 0, 1251, 835);
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
		
		JLabel lbl_soLieuDoanhThu = new JLabel("702,500 VNĐ");
		lbl_soLieuDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_soLieuDoanhThu.setForeground(new Color(0, 128, 128));
		lbl_soLieuDoanhThu.setBounds(10, 71, 283, 80);
		lbl_soLieuDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnl_phanTichDoanhThu.add(lbl_soLieuDoanhThu);
		
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
		
		JLabel lbl_rightTienPhong = new JLabel("700,000 VNĐ");
		lbl_rightTienPhong.setForeground(new Color(255, 255, 255));
		lbl_rightTienPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_rightTienPhong.setBounds(142, 162, 112, 34);
		pnl_pTichTongDoanhThu.add(lbl_rightTienPhong);
		
		JLabel lbl_rightTienDVu = new JLabel("2,500 VNĐ");
		lbl_rightTienDVu.setForeground(new Color(255, 255, 255));
		lbl_rightTienDVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_rightTienDVu.setBounds(140, 200, 144, 39);
		pnl_pTichTongDoanhThu.add(lbl_rightTienDVu);
		
		JLabel lbl_rightTienPhuThu = new JLabel("0 VNĐ");
		lbl_rightTienPhuThu.setForeground(new Color(255, 255, 255));
		lbl_rightTienPhuThu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_rightTienPhuThu.setBounds(143, 243, 155, 34);
		pnl_pTichTongDoanhThu.add(lbl_rightTienPhuThu);
		
		JLabel lbl_rightTienGiamGia = new JLabel("0 VNĐ");
		lbl_rightTienGiamGia.setForeground(new Color(255, 255, 255));
		lbl_rightTienGiamGia.setBounds(143, 281, 141, 36);
		pnl_pTichTongDoanhThu.add(lbl_rightTienGiamGia);
		lbl_rightTienGiamGia.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel pnl_frmNhapNgayThang1 = new JPanel();
		pnl_frmNhapNgayThang1.setBackground(new Color(255, 255, 255));
		pnl_frmNhapNgayThang1.setBounds(10, 145, 448, 188);
		pnl_xemThongKe.add(pnl_frmNhapNgayThang1);
		pnl_frmNhapNgayThang1.setLayout(null);
		
		JLabel lblNewLabel_13 = new JLabel("Từ ngày");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_13.setBounds(10, 10, 129, 29);
		pnl_frmNhapNgayThang1.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Đến ngày");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_14.setBounds(10, 51, 77, 29);
		pnl_frmNhapNgayThang1.add(lblNewLabel_14);
		
		textField = new JTextField();
		textField.setBounds(139, 12, 299, 29);
		pnl_frmNhapNgayThang1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(139, 49, 299, 29);
		pnl_frmNhapNgayThang1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(179, 299, 196, 137);
		pnl_frmNhapNgayThang1.add(lblNewLabel_3);
		
		JPanel panel_frmNhapNgayThang2 = new JPanel();
		panel_frmNhapNgayThang2.setBackground(new Color(255, 255, 255));
		panel_frmNhapNgayThang2.setBounds(468, 145, 447, 188);
		pnl_xemThongKe.add(panel_frmNhapNgayThang2);
		panel_frmNhapNgayThang2.setLayout(null);
		
		JLabel lblNewLabel_13_1 = new JLabel("Từ ngày");
		lblNewLabel_13_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_13_1.setBounds(10, 10, 129, 29);
		panel_frmNhapNgayThang2.add(lblNewLabel_13_1);
		
		JLabel lblNewLabel_14_1 = new JLabel("Đến ngày");
		lblNewLabel_14_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_14_1.setBounds(10, 52, 77, 29);
		panel_frmNhapNgayThang2.add(lblNewLabel_14_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(149, 54, 288, 29);
		panel_frmNhapNgayThang2.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(149, 12, 288, 29);
		panel_frmNhapNgayThang2.add(textField_3);
		
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
		SpringLayout sl_pnl_chonNam = new SpringLayout();
		pnl_chonNam.setLayout(sl_pnl_chonNam);
		
		JLabel lbl_chonNam = new JLabel("Chọn năm");
		sl_pnl_chonNam.putConstraint(SpringLayout.NORTH, lbl_chonNam, 12, SpringLayout.NORTH, pnl_chonNam);
		sl_pnl_chonNam.putConstraint(SpringLayout.WEST, lbl_chonNam, 10, SpringLayout.WEST, pnl_chonNam);
		sl_pnl_chonNam.putConstraint(SpringLayout.SOUTH, lbl_chonNam, -10, SpringLayout.SOUTH, pnl_chonNam);
		sl_pnl_chonNam.putConstraint(SpringLayout.EAST, lbl_chonNam, 130, SpringLayout.WEST, pnl_chonNam);
		lbl_chonNam.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnl_chonNam.add(lbl_chonNam);
		
		tf_chonNam = new JTextField();
		sl_pnl_chonNam.putConstraint(SpringLayout.NORTH, tf_chonNam, -2, SpringLayout.NORTH, lbl_chonNam);
		sl_pnl_chonNam.putConstraint(SpringLayout.WEST, tf_chonNam, 104, SpringLayout.EAST, lbl_chonNam);
		sl_pnl_chonNam.putConstraint(SpringLayout.SOUTH, tf_chonNam, 0, SpringLayout.SOUTH, lbl_chonNam);
		sl_pnl_chonNam.putConstraint(SpringLayout.EAST, tf_chonNam, -10, SpringLayout.EAST, pnl_chonNam);
		pnl_chonNam.add(tf_chonNam);
		tf_chonNam.setColumns(10);
		
		JPanel pnl_bieuDoNam = new JPanel();
		sl_pnl_thongKeNam.putConstraint(SpringLayout.NORTH, pnl_bieuDoNam, 4, SpringLayout.SOUTH, pnl_chonNam);
		sl_pnl_thongKeNam.putConstraint(SpringLayout.WEST, pnl_bieuDoNam, 0, SpringLayout.WEST, pnl_thongKeNam);
		sl_pnl_thongKeNam.putConstraint(SpringLayout.SOUTH, pnl_bieuDoNam, -10, SpringLayout.SOUTH, pnl_thongKeNam);
		sl_pnl_thongKeNam.putConstraint(SpringLayout.EAST, pnl_bieuDoNam, 664, SpringLayout.WEST, pnl_thongKeNam);
		pnl_thongKeNam.add(pnl_bieuDoNam);
		
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
		SpringLayout sl_pnl_chonThang = new SpringLayout();
		pnl_chonThang.setLayout(sl_pnl_chonThang);
		
		JLabel lbl_chonThang = new JLabel("Chọn tháng");
		sl_pnl_chonThang.putConstraint(SpringLayout.NORTH, lbl_chonThang, 10, SpringLayout.NORTH, pnl_chonThang);
		sl_pnl_chonThang.putConstraint(SpringLayout.WEST, lbl_chonThang, 10, SpringLayout.WEST, pnl_chonThang);
		sl_pnl_chonThang.putConstraint(SpringLayout.SOUTH, lbl_chonThang, -10, SpringLayout.SOUTH, pnl_chonThang);
		sl_pnl_chonThang.putConstraint(SpringLayout.EAST, lbl_chonThang, 105, SpringLayout.WEST, pnl_chonThang);
		lbl_chonThang.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnl_chonThang.add(lbl_chonThang);
		
		tf_chonThang = new JTextField();
		sl_pnl_chonThang.putConstraint(SpringLayout.NORTH, tf_chonThang, 0, SpringLayout.NORTH, lbl_chonThang);
		sl_pnl_chonThang.putConstraint(SpringLayout.WEST, tf_chonThang, 65, SpringLayout.EAST, lbl_chonThang);
		sl_pnl_chonThang.putConstraint(SpringLayout.SOUTH, tf_chonThang, 0, SpringLayout.SOUTH, lbl_chonThang);
		sl_pnl_chonThang.putConstraint(SpringLayout.EAST, tf_chonThang, -10, SpringLayout.EAST, pnl_chonThang);
		tf_chonThang.setColumns(10);
		pnl_chonThang.add(tf_chonThang);
		
		JPanel pnl_bieuDoThang = new JPanel();
		sl_pnl_thongKeThang.putConstraint(SpringLayout.NORTH, pnl_bieuDoThang, 6, SpringLayout.SOUTH, pnl_chonThang);
		sl_pnl_thongKeThang.putConstraint(SpringLayout.WEST, pnl_bieuDoThang, 0, SpringLayout.WEST, pnl_thongKeThang);
		sl_pnl_thongKeThang.putConstraint(SpringLayout.SOUTH, pnl_bieuDoThang, -10, SpringLayout.SOUTH, pnl_thongKeThang);
		sl_pnl_thongKeThang.putConstraint(SpringLayout.EAST, pnl_bieuDoThang, 0, SpringLayout.EAST, pnl_thongKeThang);
		pnl_thongKeThang.add(pnl_bieuDoThang);
		
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
		
		JLabel lbl_tongDoanhThuPhong = new JLabel("702,500 VNĐ");
		lbl_tongDoanhThuPhong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_tongDoanhThuPhong.setBounds(20, 51, 226, 39);
		pnl_tongDoanhThuPhong.add(lbl_tongDoanhThuPhong);
		
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
		
		JLabel lbl_tongDoanhThuDVu = new JLabel("702,500 VNĐ");
		lbl_tongDoanhThuDVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_tongDoanhThuDVu.setBounds(20, 51, 227, 40);
		pnl_tongDoanhThuDVu.add(lbl_tongDoanhThuDVu);
		
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
						
						JLabel lbl_tongDoanhThu = new JLabel("702,500 VNĐ");
						lbl_tongDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 16));
						lbl_tongDoanhThu.setBounds(20, 51, 266, 35);
						pnl_tongDoanhThu.add(lbl_tongDoanhThu);
						
						JLabel lbl_tieuDePhu_1 = new JLabel("Doanh thu tháng trước");
						lbl_tieuDePhu_1.setBackground(new Color(255, 0, 0));
						lbl_tieuDePhu_1.setForeground(new Color(128, 0, 0));
						lbl_tieuDePhu_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
						lbl_tieuDePhu_1.setBounds(20, 90, 266, 35);
						pnl_tongDoanhThu.add(lbl_tieuDePhu_1);
		
	}
}
