package GUI.DangNhap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import GUI.Home.HomeForm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FormDoiMatKhau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtOldPW;
	private JPasswordField txtNewPW;
	private boolean isEyeIcon1 = true;
	private boolean isEyeIcon2 = true;
	public TaiKhoanBUS tkBUS = new TaiKhoanBUS(); 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDoiMatKhau frame = new FormDoiMatKhau();
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
	public FormDoiMatKhau() {
		setTitle("ĐỔI MẬT KHẨU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1174, 560);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đổi mật khẩu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(892, 10, 223, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(674, 62, 159, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu cũ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(674, 111, 142, 34);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mật khẩu mới");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(674, 171, 159, 34);
		contentPane.add(lblNewLabel_3);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUserName.setBounds(867, 63, 283, 36);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JButton btnConfirm = new JButton("Xác nhận");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUserName.getText();
				String OldPW = new String(txtOldPW.getPassword());
				String NewPW = new String(txtNewPW.getPassword());
				
     			StringBuilder sb = new StringBuilder();				
				if(username.equals(""))
				{
					sb.append("Bạn chưa nhập tên đăng nhập \n");
				}
				if(OldPW.equals(""))
				{
					sb.append("Bạn chưa nhập mật khẩu \n");
				}
				if(NewPW.equals(""))
				{
					sb.append("Bạn chưa nhập mật khẩu mới \n");
				}
				if(sb.length() > 0)
				{
					JOptionPane.showMessageDialog(btnConfirm, sb.toString(), "Thông báo", JOptionPane.ERROR_MESSAGE);
					return;
				}
				TaiKhoanDTO tk = tkBUS.GetTK(username);
				if(tk.getMaNV() == null)
				{
					JOptionPane.showMessageDialog(btnConfirm, "Tên đăng nhập không tồn tại ", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
				else if(!OldPW.equals(tk.getMatKhau()))
				{
					JOptionPane.showMessageDialog(btnConfirm, "Mật khẩu cũ không đúng", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
				else if(tk.getTinhTrang() == 1)
				{
					JOptionPane.showMessageDialog(btnConfirm, "Tài khoản đã bị khóa, không thể đổi mật khẩu", "Thông báo", JOptionPane.ERROR_MESSAGE);					
				}
				else
				{
					tkBUS.SuaMatKhau(username, NewPW);
					JOptionPane.showMessageDialog(btnConfirm, "Đổi mật khẩu thành công");
				}
					
				
			}
			
		});
		btnConfirm.setBackground(new Color(0, 128, 0));
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnConfirm.setBounds(852, 220, 298, 36);
		contentPane.add(btnConfirm);
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FormDangNhap dn = new FormDangNhap();
				dn.setVisible(true);
			}
		});
		btnLogin.setBackground(new Color(255, 255, 0));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setBounds(743, 266, 204, 36);
		contentPane.add(btnLogin);
		
		JButton txtFogotPW = new JButton("Quên mật khẩu");
		txtFogotPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FormQuenMatKhau qmk = new FormQuenMatKhau();
				qmk.setVisible(true);
			}
		});
		txtFogotPW.setBackground(new Color(255, 0, 128));
		txtFogotPW.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtFogotPW.setBounds(957, 266, 193, 36);
		contentPane.add(txtFogotPW);
		
		JLabel lblCW = new JLabel("l");
		URL urlCW = FormDoiMatKhau.class.getResource("change.png");
		lblCW.setIcon(new ImageIcon(urlCW));
		lblCW.setBounds(10, 0, 654, 523);
		contentPane.add(lblCW);
		
		JButton btneye1 = new JButton("");
		btneye1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEyeIcon1 = !isEyeIcon1;
				URL newIconUrl = isEyeIcon1 ? FormDoiMatKhau.class.getResource("iconeye.png") : FormDoiMatKhau.class.getResource("hidden.png");
				btneye1.setIcon(new ImageIcon(newIconUrl));
				if (isEyeIcon1) {
		            txtOldPW.setEchoChar('●'); // Ẩn password
		        } else {
		            txtOldPW.setEchoChar((char) 0); // Hiển thị password
		        }
			}
		});
		URL urleye1 = FormDoiMatKhau.class.getResource("iconeye.png");
		btneye1.setIcon(new ImageIcon(urleye1));
	
		btneye1.setBackground(Color.WHITE);
		btneye1.setBounds(1114, 109, 36, 36);
		btneye1.setBorder(null);
		contentPane.add(btneye1);
		
		JButton btneye2 = new JButton("");
		btneye2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEyeIcon2 = !isEyeIcon2;
				URL newIconUrl = isEyeIcon2 ? FormDoiMatKhau.class.getResource("iconeye.png") : FormDoiMatKhau.class.getResource("hidden.png");
				btneye2.setIcon(new ImageIcon(newIconUrl));
				if (isEyeIcon2) {
		            txtNewPW.setEchoChar('●'); // Ẩn password
		        } else {
		            txtNewPW.setEchoChar((char) 0); // Hiển thị password
		        }
			}
		});
		URL urleye2 = FormDoiMatKhau.class.getResource("iconeye.png");
		btneye2.setIcon(new ImageIcon(urleye2));
		btneye2.setBackground(Color.WHITE);
		btneye2.setBounds(1114, 171, 36, 36);
		btneye2.setBorder(null);
		contentPane.add(btneye2);
		
		txtOldPW = new JPasswordField();
		txtOldPW.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtOldPW.setBounds(867, 111, 236, 34);
		contentPane.add(txtOldPW);
		
		txtNewPW = new JPasswordField();
		txtNewPW.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNewPW.setBounds(867, 171, 241, 34);
		contentPane.add(txtNewPW);
	}
}
