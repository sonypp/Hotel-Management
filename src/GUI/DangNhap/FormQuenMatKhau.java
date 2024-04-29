package GUI.DangNhap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import com.sun.net.httpserver.HttpServer;


import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FormQuenMatKhau extends JFrame {


	private static final long serialVersionUID = 1L;
	private JTextField txtusername;
	private JTextField txtCode;
	private JPanel contentPane;
	private JTextField txtEmail;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormQuenMatKhau frame = new FormQuenMatKhau();
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
	public FormQuenMatKhau() {
		setTitle("QUÊN MẬT KHẨU");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1169, 566);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quên mật khẩu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(841, 10, 237, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(662, 66, 188, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nhập mã xác nhận");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(662, 190, 188, 36);
		contentPane.add(lblNewLabel_2);
		
		txtusername = new JTextField();
		txtusername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtusername.setBounds(872, 67, 273, 36);
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCode.setBounds(872, 191, 169, 36);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		JButton btnConfim = new JButton("Xác nhận");
		
		btnConfim.setBackground(new Color(0, 128, 0));
		btnConfim.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnConfim.setBounds(872, 236, 273, 38);
		contentPane.add(btnConfim);
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FormDangNhap dn = new FormDangNhap();
				dn.setVisible(true);
			}
		});
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(255, 255, 0));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setBounds(819, 284, 152, 47);
		contentPane.add(btnLogin);
		
		JButton btnChangePW = new JButton("Đổi mật khẩu");
		btnChangePW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FormDoiMatKhau qmk = new FormDoiMatKhau();
				qmk.setVisible(true);
			}
		});
		btnChangePW.setBackground(new Color(255, 128, 128));
		btnChangePW.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnChangePW.setBounds(976, 284, 169, 47);
		contentPane.add(btnChangePW);
		
		JLabel lblfogot = new JLabel("");
		URL urlfogot = FormQuenMatKhau.class.getResource("fogot.png");
		lblfogot.setIcon(new ImageIcon(urlfogot));
		lblfogot.setBounds(24, 10, 589, 509);
		contentPane.add(lblfogot);
		
		JButton btnSendCode = new JButton("Gửi mã");
		btnSendCode.setBackground(new Color(128, 128, 255));
	
		btnSendCode.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSendCode.setBounds(1041, 192, 104, 34);
		contentPane.add(btnSendCode);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail.setBounds(698, 123, 152, 34);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(872, 127, 273, 36);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		
	
	}	
	}

