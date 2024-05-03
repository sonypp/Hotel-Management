package GUI.QuanLyDatPhong;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;

public class ThueDichVu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private JTable tbDichVu;

	/**
	 * Create the panel.
	 */
	public ThueDichVu() {
		setBounds(411, 27, 830, 798);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách dịch vụ hiện có");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 247, 34);
		add(lblNewLabel);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setToolTipText("");
		txtTimKiem.setBounds(10, 52, 365, 34);
		add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JComboBox cbbDichVu = new JComboBox();
		cbbDichVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbDichVu.setModel(new DefaultComboBoxModel(new String[] {"Ăn uống", "Chăm sóc sắc đẹp", "Tổ chức tiệc", "Giải trí"}));
		cbbDichVu.setBounds(396, 52, 144, 34);
		add(cbbDichVu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 288, 799, 413);
		add(scrollPane);
		
		tbDichVu = new JTable();
		tbDichVu.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT","Mã dịch vụ","Tên dịch vụ","Ngày sử dụng","Số lượng","Đơn giá","Thành tiền","Sửa","Xóa"
			}
		));
		scrollPane.setViewportView(tbDichVu);
		
		JButton btnLuuTrangThai = new JButton("Lưu trạng thái");
		btnLuuTrangThai.setForeground(new Color(255, 255, 255));
		btnLuuTrangThai.setBackground(new Color(0, 128, 0));
		btnLuuTrangThai.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLuuTrangThai.setBounds(550, 725, 223, 40);
		add(btnLuuTrangThai);
		
		JLabel lblNewLabel_1 = new JLabel("Danh sách dịch vụ đang xử lý");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 230, 316, 34);
		add(lblNewLabel_1);
	}

}
