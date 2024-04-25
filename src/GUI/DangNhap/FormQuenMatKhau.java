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
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FormQuenMatKhau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JTextField txtCode;
	private JPanel contentPane;
	private JPasswordField txtNewPW;
	private JPasswordField txtRePW;
	private boolean isEyeIcon1 = true;
	private boolean isEyeIcon2 = true;

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
		lblNewLabel.setBounds(792, 11, 311, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(526, 90, 188, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nhập mã xác nhận");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(526, 167, 220, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mật khẩu mới");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(526, 240, 188, 36);
		contentPane.add(lblNewLabel_3);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUserName.setBounds(735, 88, 368, 38);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCode.setBounds(735, 167, 266, 36);
		contentPane.add(txtCode);
		txtCode.setColumns(10);
		
		JButton btnConfim = new JButton("Xác nhận");
		
		btnConfim.setBackground(new Color(0, 128, 255));
		btnConfim.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnConfim.setBounds(735, 372, 368, 38);
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
		btnLogin.setBounds(735, 419, 182, 47);
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
		btnChangePW.setBounds(921, 419, 182, 47);
		contentPane.add(btnChangePW);
		
		JLabel lblfogot = new JLabel("New label");
		URL urlfogot = FormQuenMatKhau.class.getResource("fogot.png");
		lblfogot.setIcon(new ImageIcon(urlfogot));
		lblfogot.setBounds(24, 38, 472, 370);
		contentPane.add(lblfogot);
		
		JLabel lblNewLabel_5 = new JLabel("Nhập lại mật khẩu");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5.setBounds(526, 308, 188, 30);
		contentPane.add(lblNewLabel_5);
		
		JButton btnSendCode = new JButton("Gửi mã");
		btnSendCode.setBackground(new Color(128, 128, 255));
	
		btnSendCode.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSendCode.setBounds(999, 169, 104, 34);
		contentPane.add(btnSendCode);
		
		JButton btneye1 = new JButton("");
		btneye1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEyeIcon1 = !isEyeIcon1;
				URL newIconUrl = isEyeIcon1 ? FormDoiMatKhau.class.getResource("iconeye.png") : FormDoiMatKhau.class.getResource("hidden.png");
				btneye1.setIcon(new ImageIcon(newIconUrl));
			}
		});
		btneye1.setBackground(new Color(255, 255, 255));
		URL eye1 = FormQuenMatKhau.class.getResource("iconeye.png");
		btneye1.setIcon(new ImageIcon(eye1));
		btneye1.setBounds(1053, 244, 50, 36);
		contentPane.add(btneye1);
		
		JButton btneye2= new JButton("");
		btneye2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEyeIcon2 = !isEyeIcon2;
				URL newIconUrl = isEyeIcon2 ? FormDoiMatKhau.class.getResource("iconeye.png") : FormDoiMatKhau.class.getResource("hidden.png");
				btneye2.setIcon(new ImageIcon(newIconUrl));
			}
		});
		btneye2.setBackground(new Color(255, 255, 255));
		btneye2.setIcon(new ImageIcon(eye1));
		btneye2.setBounds(1053, 308, 50, 37);
		contentPane.add(btneye2);
		
		txtNewPW = new JPasswordField();
		txtNewPW.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNewPW.setBounds(735, 246, 319, 36);
		contentPane.add(txtNewPW);
		
		txtRePW = new JPasswordField();
		txtRePW.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtRePW.setBounds(735, 308, 319, 37);
		contentPane.add(txtRePW);
	}
}
