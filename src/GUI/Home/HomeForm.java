package GUI.Home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;


import GUI.DichVu.QLDVPanel;
import GUI.HoaDon.QuanLiHoaDon;
import GUI.KhachHang.PanelCus;
import GUI.NhanVien.PanelStaff;
import GUI.PhanQuyen.PanelPosition;
import GUI.Phong.QLPHONGPanel;
import GUI.QuanLyDatPhong.DanhSachThuePhong;
import GUI.QuanLyDatPhong.TaoPhieuDatPhong;
import GUI.ThongKe.XemThongKe;
import GUI.TienIch.QLTienIch;

import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSplitPane;

public class HomeForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 */
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new FlatMacLightLaf());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeForm frame = new HomeForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 10, 237, 839);
		panelMenu.setBackground(new Color(205, 205, 205));
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JButton btnQLKhachHang = new JButton("Quản lý khách hàng");
		btnQLKhachHang.setBounds(39, 230, 148, 39);
		panelMenu.add(btnQLKhachHang);
		
		JButton btnManHinhChinh = new JButton("Màn hình chính");
		btnManHinhChinh.setBounds(39, 32, 148, 39);
		panelMenu.add(btnManHinhChinh);
		
		JButton btnQLPhng = new JButton("Quản lý phòng");
		JPanel panelChinh = new JPanel();
		panelChinh.setBackground(new Color(255, 255, 255));
		panelChinh.setForeground(new Color(0, 255, 255));
		panelChinh.setBounds(252, 10, 1288, 835);
		contentPane.add(panelChinh);
		panelChinh.setLayout(null);
		btnQLPhng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					QLPHONGPanel QLPhong = new QLPHONGPanel();
					
				  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
				  	panelChinh.add(QLPhong); // Thêm QLPHONGPanel vào panelChinh
				  	panelChinh.revalidate(); // Cập nhật lại panelChinh
				  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		
		btnQLKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelCus QLKH = new PanelCus();
				
				panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
				panelChinh.add(QLKH); // Thêm QLPHONGPanel vào panelChinh
				panelChinh.revalidate(); // Cập nhật lại panelChinh
				panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		

		btnQLPhng.setBounds(39, 82, 148, 39);
		panelMenu.add(btnQLPhng);
		
		JButton btnQLDichvu = new JButton("Quản lý dịch vụ");
		btnQLDichvu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QLDVPanel QLDV = new QLDVPanel();
				
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(QLDV); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnQLDichvu.setBounds(39, 181, 148, 39);
		panelMenu.add(btnQLDichvu);
		
		JButton btnQLNV = new JButton("Quản lý nhân viên");
		btnQLNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelStaff QLNV = new PanelStaff();
				
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(QLNV); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnQLNV.setBounds(39, 279, 148, 39);
		panelMenu.add(btnQLNV);
		
		JButton btnQLPhanQuyen = new JButton("Quản lý phân quyền");
		btnQLPhanQuyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelPosition QLPQ = new PanelPosition();
				
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(QLPQ); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnQLPhanQuyen.setBounds(39, 330, 148, 39);
		panelMenu.add(btnQLPhanQuyen);
		
		JButton btnDatPhongMoi = new JButton("Đặt phòng mới");
		btnDatPhongMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaoPhieuDatPhong PhieuDatPhong = new TaoPhieuDatPhong();
				
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(PhieuDatPhong); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnDatPhongMoi.setBounds(39, 379, 148, 39);
		panelMenu.add(btnDatPhongMoi);
		
		JButton btnQLHoaDon = new JButton("Quản lý hóa đơn");
		btnQLHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLiHoaDon QLHD = new QuanLiHoaDon();
				
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(QLHD); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnQLHoaDon.setBounds(39, 481, 148, 39);
		panelMenu.add(btnQLHoaDon);
		
		JButton btnThongKe = new JButton("Thống kê");
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XemThongKe TK = new XemThongKe();
				
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(TK); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnThongKe.setBounds(39, 532, 148, 39);
		panelMenu.add(btnThongKe);
		
		JButton btnQLTienIch = new JButton("Quản lý tiện ích");
		btnQLTienIch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QLTienIch QLTI = new QLTienIch();
				
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(QLTI); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnQLTienIch.setBounds(39, 131, 148, 39);
		panelMenu.add(btnQLTienIch);
		
		JButton btnDSDatPhong = new JButton("Danh sách đặt phòng");
		btnDSDatPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DanhSachThuePhong DSDatPhong = new DanhSachThuePhong();
				
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(DSDatPhong); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnDSDatPhong.setBounds(39, 430, 148, 39);
		panelMenu.add(btnDSDatPhong);
		
	}
}
