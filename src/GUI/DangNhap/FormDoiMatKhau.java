package GUI.DangNhap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
		lblNewLabel.setBounds(776, 11, 223, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(537, 85, 159, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu cũ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(537, 142, 142, 34);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mật khẩu mới");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(537, 207, 159, 34);
		contentPane.add(lblNewLabel_3);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUserName.setBounds(706, 85, 402, 36);
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
					JOptionPane.showMessageDialog(btnConfirm, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(username.equals("ThuanChan") && OldPW.equals("123456"))
				{
					JOptionPane.showMessageDialog(btnConfirm, "Đổi mật khẩu thành công");
				}
				else
				{
					JOptionPane.showConfirmDialog(btnConfirm, "Tên đăng nhập hoặc mật khẩu không tồn tại", "Failure", JOptionPane.ERROR_MESSAGE);
				}
					
				
			}
			
		});
		btnConfirm.setBackground(new Color(0, 0, 255));
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnConfirm.setBounds(706, 278, 402, 36);
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
		btnLogin.setBounds(706, 316, 204, 36);
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
		txtFogotPW.setBounds(906, 316, 204, 36);
		contentPane.add(txtFogotPW);
		
		JLabel lblCW = new JLabel("New label");
		URL urlCW = FormDoiMatKhau.class.getResource("change.png");
		lblCW.setIcon(new ImageIcon(urlCW));
		lblCW.setBounds(25, 56, 448, 399);
		contentPane.add(lblCW);
		
		JButton btneye1 = new JButton("");
		btneye1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEyeIcon1 = !isEyeIcon1;
				URL newIconUrl = isEyeIcon1 ? FormDoiMatKhau.class.getResource("iconeye.png") : FormDoiMatKhau.class.getResource("hidden.png");
				btneye1.setIcon(new ImageIcon(newIconUrl));
			}
		});
		URL urleye1 = FormDoiMatKhau.class.getResource("iconeye.png");
		btneye1.setIcon(new ImageIcon(urleye1));
	
		btneye1.setBackground(Color.WHITE);
		btneye1.setBounds(1068, 142, 36, 36);
		contentPane.add(btneye1);
		
		JButton btneye2 = new JButton("");
		btneye2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isEyeIcon2 = !isEyeIcon2;
				URL newIconUrl = isEyeIcon2 ? FormDoiMatKhau.class.getResource("iconeye.png") : FormDoiMatKhau.class.getResource("hidden.png");
				btneye2.setIcon(new ImageIcon(newIconUrl));
			}
		});
		URL urleye2 = FormDoiMatKhau.class.getResource("iconeye.png");
		btneye2.setIcon(new ImageIcon(urleye2));
		btneye2.setBackground(Color.WHITE);
		btneye2.setBounds(1068, 210, 36, 36);
		contentPane.add(btneye2);
		
		txtOldPW = new JPasswordField();
		txtOldPW.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtOldPW.setBounds(703, 146, 368, 34);
		contentPane.add(txtOldPW);
		
		txtNewPW = new JPasswordField();
		txtNewPW.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtNewPW.setBounds(706, 213, 365, 34);
		contentPane.add(txtNewPW);
	}
}
