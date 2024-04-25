package GUI.QuanLyDatPhong;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class ThuePhong extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tbDanhSachPhongDangXuLy;

	/**
	 * Create the panel.
	 */
	public ThuePhong() {
		setBounds(411, 27, 830, 798);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách phòng đang xử lý");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBackground(new Color(128, 128, 128));
		lblNewLabel.setBounds(0, 0, 830, 47);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 71, 830, 650);
		add(scrollPane);
		
		tbDanhSachPhongDangXuLy = new JTable();
		tbDanhSachPhongDangXuLy.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT","Mã phòng","Tên phòng","Tình trạng","Loại hình thuê","Ngày thuê","Ngày trả","Ngày Checkout","Giá phòng","Xóa"
			}
		));
		scrollPane.setViewportView(tbDanhSachPhongDangXuLy);
		
		JButton btnChonPhong = new JButton("Chọn phòng");
		btnChonPhong.setBackground(new Color(0, 255, 0));
		btnChonPhong.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnChonPhong.setBounds(413, 731, 182, 41);
		add(btnChonPhong);
		
		JButton btnLuuTrangThai = new JButton("Lưu trạng thái");
		btnLuuTrangThai.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLuuTrangThai.setBackground(new Color(255, 128, 0));
		btnLuuTrangThai.setBounds(618, 731, 182, 41);
		add(btnLuuTrangThai);

	}

}
