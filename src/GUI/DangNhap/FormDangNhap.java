package GUI.DangNhap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import BUS.ChucNangBUS;
import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DTO.ChiTietChucNangDTO;
import DTO.DSNhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.Home.HomeForm;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JPasswordField;

public class FormDangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtusername;
	private JPasswordField txtPW;
	private boolean isEyeIcon1 = true;
	public TaiKhoanBUS tkBUS = new TaiKhoanBUS();
	public ChucNangBUS cnBUS = new ChucNangBUS();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    try {
	        UIManager.setLookAndFeel(new FlatMacLightLaf());
	    } catch (UnsupportedLookAndFeelException e) {
	        e.printStackTrace();
	    }
	    EventQueue.invokeLater(() -> {
	        try {
	        	FormDangNhap frame = new FormDangNhap();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}
	
	private void homeForm_Closing()
	{
		int option = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc chắn muốn thoát?", "", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            this.setVisible(true);
        }
	}
	
	private void hideThis()
	{
		this.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public FormDangNhap() {
		setFont(new Font("Dialog", Font.BOLD, 20));
		this.setTitle("ĐĂNG NHẬP HỆ THỐNG KHÁCH SẠN");
		setName("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1108, 570);
		JPanel contentPane = new JPanel();
		contentPane.setName("");
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 30));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Thông tin đăng nhập");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(741, 11, 284, 71);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(664, 92, 144, 52);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(664, 154, 144, 33);
		contentPane.add(lblNewLabel_2);
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtusername.getText();
				String password = new String(txtPW.getPassword());
				
     			StringBuilder sb = new StringBuilder();				
				if(username.equals(""))
				{ 
					sb.append("Tên đăng nhập không được trống \n");
				}
				if(password.equals(""))
				{
					sb.append("Mật khẩu không được trống \n");
				}
				if(sb.length() > 0)
				{
					JOptionPane.showMessageDialog(btnLogin, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
					return;
				}
				TaiKhoanDTO tk = tkBUS.GetTK(username);
				
				if(tk.getMaNV() == null)
				{
					JOptionPane.showMessageDialog(btnLogin, "Tên đăng nhập không tồn tại " , "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
				else if(tk.getTinhTrang() == 1)
				{
					JOptionPane.showMessageDialog(btnLogin, "Tài khoản đã bị khóa ", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
				else if(!password.equals(tk.getMatKhau()))
				{
					JOptionPane.showMessageDialog(btnLogin, "Sai mật khẩu", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					
					var nvBUS = new NhanVienBUS();
					HomeForm.nhanVien = nvBUS.getNVDTO(tk.getMaNV());
					HomeForm hf = new HomeForm();
					hf.addWindowListener(new WindowAdapter() {
					    @Override
					    public void windowClosing(WindowEvent e) {
					        homeForm_Closing();
					    }
					});

					hf.setVisible(true);
					hideThis();
				}
			}
			
			
		});
		btnLogin.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		btnLogin.setBorder(new LineBorder(null, 1, true));
		URL urliconlogin = FormDangNhap.class.getResource("iconlogin.png");
		btnLogin.setIcon(new ImageIcon(urliconlogin));
		btnLogin.setBackground(new Color(0, 128, 0));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setBounds(664, 197, 420, 38);
		contentPane.add(btnLogin);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		contentPane.add(panel);
		
		JButton btnChangePW = new JButton("Đổi mật khẩu");
		btnChangePW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FormDoiMatKhau dmk = new FormDoiMatKhau();
				dmk.setVisible(true);
			}
		});
		btnChangePW.setBackground(new Color(255, 255, 0));
		btnChangePW.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnChangePW.setBounds(664, 245, 211, 45);
		contentPane.add(btnChangePW);
		
		JButton btnForgotPW = new JButton("Quên mật khẩu");
		btnForgotPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FormQuenMatKhau qmk = new FormQuenMatKhau();
				qmk.setVisible(true);
			}
		});
		btnForgotPW.setBackground(new Color(255, 0, 128));
		btnForgotPW.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnForgotPW.setBounds(880, 245, 204, 45);
		contentPane.add(btnForgotPW);
		
		txtusername = new JTextField();
		txtusername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtusername.setBounds(825, 102, 259, 33);
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		JButton btneye1 = new JButton("");
		btneye1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEyeIcon1 = !isEyeIcon1;
				URL newIconUrl = isEyeIcon1 ? FormDoiMatKhau.class.getResource("iconeye.png") : FormDoiMatKhau.class.getResource("hidden.png");
				btneye1.setIcon(new ImageIcon(newIconUrl));
				if (isEyeIcon1) {
		            txtPW.setEchoChar('●'); // Ẩn password
		        } else {
		            txtPW.setEchoChar((char) 0); // Hiển thị password
		        }
			}
		});
		btneye1.setBackground(new Color(255, 255, 255));
		URL urlIconeye = FormDangNhap.class.getResource("iconeye.png");
		btneye1.setIcon(new ImageIcon(urlIconeye));
		btneye1.setBounds(1046, 154, 38, 33);
		btneye1.setBorder(null);
		contentPane.add(btneye1);  
		
		
		JLabel lblLoginImage = new JLabel("");
		lblLoginImage.setBounds(10, 10, 631, 492);
		URL urlPNGlogin = FormDangNhap.class.getResource("login.png");
		lblLoginImage.setIcon(new ImageIcon(urlPNGlogin));
		contentPane.add(lblLoginImage);

		
		Image login = Toolkit.getDefaultToolkit().createImage(urlPNGlogin);
		this.setIconImage(login);
		
		txtPW = new JPasswordField();
		txtPW.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPW.setBounds(825, 154, 221, 33);
		contentPane.add(txtPW);
	}
	
}
