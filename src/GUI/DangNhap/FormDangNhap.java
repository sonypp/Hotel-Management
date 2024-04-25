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
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JPasswordField;

public class FormDangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JPasswordField txtPW;
	private boolean isEyeIcon1 = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDangNhap frame = new FormDangNhap();
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
	public FormDangNhap() {
		setFont(new Font("Dialog", Font.BOLD, 20));
		this.setTitle("ĐĂNG NHẬP HỆ THỐNG KHÁCH SẠN");
		setName("");
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		lblNewLabel.setBounds(519, 0, 284, 89);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(383, 103, 144, 52);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(383, 180, 144, 33);
		contentPane.add(lblNewLabel_2);
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUserName.getText();
				String password = new String(txtPW.getPassword());
				
     			StringBuilder sb = new StringBuilder();				
				if(username.equals(""))
				{
					sb.append("Bạn chưa nhập tên đăng nhập \n");
				}
				if(password.equals(""))
				{
					sb.append("Bạn chưa nhập mật khẩu \n");
				}
				if(sb.length() > 0)
				{
					JOptionPane.showMessageDialog(btnLogin, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(username.equals("ThuanChan") && password.equals("123456"))
				{
					JOptionPane.showMessageDialog(btnLogin, "Đăng nhập thành công");
				}
				else
				{
					JOptionPane.showConfirmDialog(btnLogin, "Tên đăng nhập hoặc mật khẩu không tồn tại", "Failure", JOptionPane.ERROR_MESSAGE);
				}
					
				
			}
			
			
		});
		btnLogin.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		btnLogin.setBorder(new LineBorder(null, 1, true));
		URL urliconlogin = FormDangNhap.class.getResource("iconlogin.png");
		btnLogin.setIcon(new ImageIcon(urliconlogin));
		btnLogin.setBackground(new Color(0, 128, 255));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setBounds(537, 279, 406, 45);
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
		btnChangePW.setBounds(537, 329, 199, 45);
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
		btnForgotPW.setBounds(741, 329, 202, 45);
		contentPane.add(btnForgotPW);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUserName.setBounds(537, 110, 406, 33);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JButton btneye1 = new JButton("");
		btneye1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEyeIcon1 = !isEyeIcon1;
				URL newIconUrl = isEyeIcon1 ? FormDoiMatKhau.class.getResource("iconeye.png") : FormDoiMatKhau.class.getResource("hidden.png");
				btneye1.setIcon(new ImageIcon(newIconUrl));
			}
		});
		btneye1.setBackground(new Color(255, 255, 255));
		URL urlIconeye = FormDangNhap.class.getResource("iconeye.png");
		btneye1.setIcon(new ImageIcon(urlIconeye));
		btneye1.setBounds(905, 180, 38, 33);
		contentPane.add(btneye1);
		
		
		JLabel lblLoginImage = new JLabel("New label");
		lblLoginImage.setBounds(10, 50, 358, 433);
		URL urlPNGlogin = FormDangNhap.class.getResource("login.png");
		lblLoginImage.setIcon(new ImageIcon(urlPNGlogin));
		contentPane.add(lblLoginImage);

		
		Image login = Toolkit.getDefaultToolkit().createImage(urlPNGlogin);
		this.setIconImage(login);
		
		txtPW = new JPasswordField();
		txtPW.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPW.setBounds(537, 180, 369, 33);
		contentPane.add(txtPW);
	}
}
