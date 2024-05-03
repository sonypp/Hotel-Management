package GUI.Home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Dimension;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.toedter.calendar.JCalendar;

import BUS.ChucNangBUS;
import DTO.NhanVienDTO;

import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;

import GUI.DangNhap.FormDangNhap;
import GUI.DangNhap.FormQuenMatKhau;
import GUI.DichVu.QLDVPanel;
import GUI.HoaDon.QuanLiHoaDon;
import GUI.KhachHang.PanelCus;
import GUI.NhanVien.PanelStaff;
import GUI.PhanQuyen.PanelPosition;
import GUI.Phong.QLPHONGPanel;
import GUI.QuanLyDatPhong.BookingList;
import GUI.QuanLyDatPhong.BookingNew;
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
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.SystemColor;

public class HomeForm extends JFrame {
	
	public static NhanVienDTO nhanVien;
	public ChucNangBUS cnBUS = new ChucNangBUS();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnManHinhChinh;
	private JButton btnQLPhng;
	private JButton btnQLDichvu;
	private JButton btnQLNV;
	private JButton btnQLPhanQuyen;
	private JButton btnDatPhongMoi;
	private JButton btnQLHoaDon;
	private JButton btnThongKe;
	private JButton btnQLTienIch;
	private JButton btnDSDatPhong;
	private JButton btnQLKhachHang;
	public JPanel panelChinh = new JPanel();
	
	private URL urlMHC = HomeForm.class.getResource("MHC.png");
	private URL urlQLP = HomeForm.class.getResource("bedroom.png");
	private URL urlQLKH = HomeForm.class.getResource("client.png");
	private URL urlQLDV = HomeForm.class.getResource("dichvu24.png");
	private URL urlQLNV = HomeForm.class.getResource("nhanvien.png");
	private URL urlQLPQ = HomeForm.class.getResource("phanquyen.png");
	private URL urlDPM = HomeForm.class.getResource("add.png");
	private URL urlHD = HomeForm.class.getResource("HoaDon.png");
	private URL urlTK = HomeForm.class.getResource("thongKe.png");
	private URL urlTI = HomeForm.class.getResource("tienIch.png");
	private URL urlDSDP = HomeForm.class.getResource("DSDatPhong.png");
	private URL urlhotel = HomeForm.class.getResource("hotel.png");
	private URL urlXinChao = HomeForm.class.getResource("XinChao.png");
	private URL urlHa1 = HomeForm.class.getResource("ha1.png");
	private URL urlHa2 = HomeForm.class.getResource("ha2.png");
	private URL urlHa3 = HomeForm.class.getResource("ha3.png");
	private URL urlHa4 = HomeForm.class.getResource("ha4.png");

	private JLabel lblXinChao;

	
	public HomeForm() {
		setBackground(new Color(255, 255, 255));
		setFont(new Font("Dialog", Font.BOLD, 24));
		setTitle("PHẦN MỀM QUẢN LÝ KHÁCH SẠN ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(10, 73, 237, 776);
		panelMenu.setBackground(new Color(255, 255, 255));
		contentPane.add(panelMenu);
		
		btnQLKhachHang = new JButton("Quản lý khách hàng                            ");
		btnQLKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		btnQLKhachHang.setBackground(new Color(255, 255, 255));
		
		panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
		panelMenu.add(Box.createVerticalStrut(10));
		
		

		
		btnManHinhChinh = new JButton("Màn hình chính                                     ");
		btnManHinhChinh.setFocusPainted(false);
		btnManHinhChinh.setHorizontalAlignment(SwingConstants.LEFT);
		btnManHinhChinh.setBackground(Color.GRAY);
		btnManHinhChinh.setIcon(new ImageIcon(urlMHC));
		btnManHinhChinh.setBorder(null);
		panelMenu.add(btnManHinhChinh);
		btnManHinhChinh.setHorizontalAlignment(SwingConstants.LEFT);

		
		btnManHinhChinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					btnManHinhChinh.setFocusPainted(false);
					QLPHONGPanel QLPhong = new QLPHONGPanel();
					RefreshButton();
					btnManHinhChinh.setBackground(Color.GRAY);
				  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
				  	
				  	panelChinh.revalidate(); // Cập nhật lại panelChinh
				  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnManHinhChinh.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	            	if(btnManHinhChinh.getBackground() == Color.GRAY)
	            	{
	            		return;
	            	}
	                btnManHinhChinh.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	if(btnManHinhChinh.getBackground() == Color.GRAY)
	            	{
	            		return;
	            	}
	                btnManHinhChinh.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
	            }
	        });
		
		
		btnQLPhng = new JButton("Quản lý phòng                                    ");
		btnQLPhng.setHorizontalAlignment(SwingConstants.LEFT);
		btnQLPhng.setBackground(new Color(255, 255, 255));
		btnQLPhng.setBorder(null);
		btnQLPhng.setIcon(new ImageIcon(urlQLP));
		
		btnQLPhng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					btnQLPhng.setFocusPainted(false);
					QLPHONGPanel QLPhong = new QLPHONGPanel();
					RefreshButton();
					btnQLPhng.setBackground(Color.GRAY);
				  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
				  	panelChinh.add(QLPhong); // Thêm QLPHONGPanel vào panelChinh
				  	panelChinh.revalidate(); // Cập nhật lại panelChinh
				  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnQLPhng.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(btnQLPhng.getBackground() == Color.GRAY)
            	{
            		return;
            	}
              
                btnQLPhng.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(btnQLPhng.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLPhng.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
            }
        });
		
		btnQLTienIch = new JButton("Quản lý tiện ích                                   ");
		btnQLTienIch.setHorizontalAlignment(SwingConstants.LEFT);
		btnQLTienIch.setIcon(new ImageIcon(urlTI));
		btnQLTienIch.setBackground(new Color(255, 255, 255));
		btnQLTienIch.setBorder(null);
		btnQLTienIch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQLTienIch.setFocusPainted(false);
				QLTienIch QLTI = new QLTienIch();
				RefreshButton();
				btnQLTienIch.setBackground(Color.GRAY);
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(QLTI); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnQLTienIch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(btnQLTienIch.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLTienIch.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(btnQLTienIch.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLTienIch.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
            }
        });

		
				
				
				btnQLDichvu = new JButton("Quản lý dịch vụ                                   ");
				
					btnQLDichvu.setHorizontalAlignment(SwingConstants.LEFT);
					btnQLDichvu.setIcon(new ImageIcon(urlQLDV));
					btnQLDichvu.setBackground(new Color(255, 255, 255));
					btnQLDichvu.setBorder(null);
					btnQLDichvu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btnQLDichvu.setFocusPainted(false);;
							QLDVPanel QLDV = new QLDVPanel();
							RefreshButton();
							btnQLDichvu.setBackground(Color.GRAY);
						  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
						  	panelChinh.add(QLDV); // Thêm QLPHONGPanel vào panelChinh
						  	panelChinh.revalidate(); // Cập nhật lại panelChinh
						  	panelChinh.repaint(); // Vẽ lại panelChinh
						}
					});
					
					btnQLDichvu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(btnQLDichvu.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLDichvu.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(btnQLDichvu.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLDichvu.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
            }
        });

		btnQLKhachHang.setIcon(new ImageIcon(urlQLKH));
		btnQLKhachHang.setBorder(null);

		
		panelChinh.setBackground(new Color(255, 255, 255));
		panelChinh.setForeground(new Color(0, 255, 255));
		panelChinh.setBounds(253, 73, 1310, 822);
		contentPane.add(panelChinh);
		panelChinh.setLayout(null);
		
		JPanel panelLich = new JPanel();
		panelLich.setBounds(10, 316, 263, 445);
		panelChinh.add(panelLich);
		
		JCalendar calendar = new JCalendar();
		panelLich.setLayout(new BorderLayout());


		panelLich.add(calendar, BorderLayout.CENTER);
		
		JPanel panelMaps = new JPanel();
		panelMaps.setBounds(283, 315, 1017, 446);
		panelChinh.add(panelMaps);
		
		JLabel lblHinhAnh = new JLabel("");
		lblHinhAnh.setBounds(10, 10, 1283, 295);
		panelChinh.add(lblHinhAnh);
		
		JLabel lblHa1 = new JLabel("New label");
		lblHa1.setBounds(10, 10, 275, 295);
		lblHa1.setIcon(new ImageIcon(urlHa1));
		panelChinh.add(lblHa1);
		
		JLabel lblHa2 = new JLabel("New label");
		lblHa2.setBounds(283, 10, 304, 295);
		lblHa2.setIcon(new ImageIcon(urlHa2));
		panelChinh.add(lblHa2);
		
		JLabel lblHa3 = new JLabel("New label");
		lblHa3.setBounds(593, 10, 304, 295);
		lblHa3.setIcon(new ImageIcon(urlHa3));
		panelChinh.add(lblHa3);
		
		JLabel lblHa4 = new JLabel("New label");
		lblHa4.setBounds(907, 10, 393, 295);
		lblHa4.setIcon(new ImageIcon(urlHa4));
		panelChinh.add(lblHa4);
		lblXinChao = new JLabel("Xin chào ");
		lblXinChao.setBackground(new Color(240, 240, 240));
		lblXinChao.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblXinChao.setBounds(1069, 10, 388, 40);
		contentPane.add(lblXinChao);
		
		JLabel lblNewLabel_1 = new JLabel("LUXURY HOTEL");
		lblNewLabel_1.setBounds(10, 10, 237, 64);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(new ImageIcon(urlhotel));
		
		JLabel lbliconXinChao = new JLabel("");
		lbliconXinChao.setBounds(1474, 10, 56, 40);
		lbliconXinChao.setIcon(new ImageIcon(urlXinChao));
		contentPane.add(lbliconXinChao);
		
		
		
		btnQLKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQLKhachHang.setFocusPainted(false);
				PanelCus QLKH = new PanelCus();
				RefreshButton();
				btnQLKhachHang.setBackground(Color.GRAY);
				panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
				panelChinh.add(QLKH); // Thêm QLPHONGPanel vào panelChinh
				panelChinh.revalidate(); // Cập nhật lại panelChinh
				panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		
		btnQLKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(btnQLKhachHang.getBackground() == Color.GRAY)
            	{
            		return;
            	}
               
                btnQLKhachHang.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(btnQLKhachHang.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLKhachHang.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
            }
        });
		
		
		btnQLNV = new JButton("Quản lý nhân viên                                  ");
		btnQLNV.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnQLNV.setIcon(new ImageIcon(urlQLNV));
		btnQLNV.setBackground(new Color(255, 255, 255));
		btnQLNV.setBorder(null);
		btnQLNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQLNV.setFocusPainted(false);
				PanelStaff QLNV = new PanelStaff();
				RefreshButton();
				btnQLNV.setBackground(Color.GRAY);
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(QLNV); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnQLNV.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(btnQLNV.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLNV.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(btnQLNV.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLNV.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
            }
        });

		
		btnQLPhanQuyen = new JButton("Quản lý phân quyền                            ");
		btnQLPhanQuyen.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnQLPhanQuyen.setIcon(new ImageIcon(urlQLPQ));
		btnQLPhanQuyen.setBackground(new Color(255, 255, 255));
		btnQLPhanQuyen.setBorder(null);
		btnQLPhanQuyen.setPreferredSize(new Dimension(237, 50));
		btnQLPhanQuyen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQLPhanQuyen.setFocusPainted(false);
				PanelPosition QLPQ = new PanelPosition();
				RefreshButton();
				btnQLPhanQuyen.setBackground(Color.GRAY);
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(QLPQ); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		
		btnQLPhanQuyen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(btnQLPhanQuyen.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLPhanQuyen.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(btnQLPhanQuyen.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLPhanQuyen.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
            }
        });

		
		
		btnDSDatPhong = new JButton("Danh sách đặt phòng                           ");
		btnDSDatPhong.setHorizontalAlignment(SwingConstants.LEFT);
		btnDSDatPhong.setIcon(new ImageIcon(urlDSDP));
		btnDSDatPhong.setBackground(new Color(255, 255, 255));
		btnDSDatPhong.setBorder(null);
		btnDSDatPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDSDatPhong.setFocusPainted(false);
				BookingList DSDatPhong = new BookingList();
				RefreshButton();
				btnDSDatPhong.setBackground(Color.GRAY);
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(DSDatPhong); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnDSDatPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(btnDSDatPhong.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnDSDatPhong.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(btnDSDatPhong.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnDSDatPhong.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
            }
        });
		
		btnDatPhongMoi = new JButton("Đặt phòng mới                                     ");
		btnDatPhongMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnDatPhongMoi.setIcon(new ImageIcon(urlDPM));
		btnDatPhongMoi.setBackground(new Color(255, 255, 255));
		btnDatPhongMoi.setBorder(null);
		btnDatPhongMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDatPhongMoi.setFocusPainted(false);
				BookingNew PhieuDatPhong = new BookingNew();
				RefreshButton();
				btnDatPhongMoi.setBackground(Color.GRAY);
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(PhieuDatPhong); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnDatPhongMoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(btnDatPhongMoi.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnDatPhongMoi.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(btnDatPhongMoi.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnDatPhongMoi.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
            }
        });

		
		
		btnThongKe = new JButton("Thống kê                                             ");
		btnThongKe.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnThongKe.setIcon(new ImageIcon(urlTK));
		btnThongKe.setBackground(new Color(255, 255, 255));
		btnThongKe.setBorder(null);
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnThongKe.setFocusPainted(false);
				XemThongKe TK = new XemThongKe();
				RefreshButton();
				btnThongKe.setBackground(Color.GRAY);
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(TK); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnThongKe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(btnThongKe.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnThongKe.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(btnThongKe.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnThongKe.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
            }
        });
		
		btnQLHoaDon = new JButton("Quản lý hóa đơn                                 ");
		btnQLHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
		btnQLHoaDon.setIcon(new ImageIcon(urlHD));
		btnQLHoaDon.setBackground(new Color(255, 255, 255));
		btnQLHoaDon.setBorder(null);
		btnQLHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQLHoaDon.setFocusPainted(false);
				QuanLiHoaDon QLHD = new QuanLiHoaDon();
				RefreshButton();
				btnQLHoaDon.setBackground(Color.GRAY);
			  	panelChinh.removeAll(); // Xóa các thành phần cũ trong panelChinh
			  	panelChinh.add(QLHD); // Thêm QLPHONGPanel vào panelChinh
			  	panelChinh.revalidate(); // Cập nhật lại panelChinh
			  	panelChinh.repaint(); // Vẽ lại panelChinh
			}
		});
		btnQLHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(btnQLHoaDon.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLHoaDon.setBackground(Color.lightGray); // Thay đổi màu nền khi di chuột vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(btnQLHoaDon.getBackground() == Color.GRAY)
            	{
            		return;
            	}
                btnQLHoaDon.setBackground(null); // Khôi phục màu nền khi di chuột ra khỏi nút
            }
        });

		
			
		if (nhanVien != null) {
		    int chucVu = nhanVien.getChucVu();
		    String strchucVu = String.valueOf(chucVu);
		    ResultSet ChucNang = cnBUS.QuyenQuanLy(strchucVu);
		    panelMenu.add(Box.createVerticalStrut(10));
		    
		    try {
		    	while(ChucNang.next()) {
		    		var maCN = ChucNang.getString(1);
		    		if(maCN.equals("1"))
		    		{
		    			panelMenu.add(btnQLPhng);
		    			panelMenu.add(Box.createVerticalStrut(10));
		    			panelMenu.add(btnQLTienIch);
		    			panelMenu.add(Box.createVerticalStrut(10));
		    		}
		    		if(maCN.equals("2"))
		    		{
		    			panelMenu.add(btnQLDichvu); 
		    			panelMenu.add(Box.createVerticalStrut(10));
		    		}
		    		
		    		if(maCN.equals("3"))
		    		{
		    			panelMenu.add(btnQLKhachHang);
		    			panelMenu.add(Box.createVerticalStrut(10));
		    		}
		    		 	
	    			if(maCN.equals("4"))
	    			{
	    				panelMenu.add(btnQLNV);
	    				panelMenu.add(Box.createVerticalStrut(10));
	    			}
	    				
	    			if(maCN.equals("5"))
	    			{
	    				panelMenu.add(btnQLPhanQuyen);
	    				panelMenu.add(Box.createVerticalStrut(10));
	    			}
	    				
	    			if(maCN.equals("6"))
	    			{
	    				panelMenu.add(btnDatPhongMoi);
	    				panelMenu.add(Box.createVerticalStrut(10));
	    				panelMenu.add(btnDSDatPhong);
	    				panelMenu.add(Box.createVerticalStrut(10));
	    			}
	    				
	    			if(maCN.equals("7"))
	    			{
	    				panelMenu.add(btnQLHoaDon);
	    				panelMenu.add(Box.createVerticalStrut(10));
	    			}
	    				
	    			if(maCN.equals("8"))
	    			{
	    				panelMenu.add(btnThongKe);
	    				panelMenu.add(Box.createVerticalStrut(10));
	    			}
	    				
		    	}
		    } catch (SQLException e1) {
		    	// TODO Auto-generated catch block
		    	e1.printStackTrace();
		    }
		    ResultSet tenChucVu = cnBUS.getTenPQ(strchucVu);
		    String ten = nhanVien.getTenNV();
		    try {
				if (tenChucVu.next()) {
				    String tenPQ = tenChucVu.getString("tenPQ");
				    lblXinChao.setText("Xin chào " + tenPQ + " " + ten);
				    lblXinChao.setHorizontalAlignment(SwingConstants.RIGHT); // Đặt căn chỉnh từ phải qua trái

				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
		
		
	}
	private void RefreshButton() {
		 btnManHinhChinh.setBackground(null);
		 btnQLPhng.setBackground(null);
		 btnQLDichvu.setBackground(null);
		 btnQLNV.setBackground(null);
		 btnQLPhanQuyen.setBackground(null);
		 btnDatPhongMoi.setBackground(null);
		 btnQLHoaDon.setBackground(null);
		 btnThongKe.setBackground(null);
		 btnQLTienIch.setBackground(null);
		 btnDSDatPhong.setBackground(null);
		 btnQLKhachHang.setBackground(null);
	}
	
	
}
	
