package GUI.QuanLyDatPhong;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;

public class PhieuDatPhong extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfMaThue;
	private JTextField tfNVLP;
	private JTextField tfNLP;
	private JTextField tfTTXL;
	private JTextField textField;
	private JTextField tfMaKH;
	private JTextField tfHTKH;
	private JTextField tfCMND;
	private JTextField tfSDT;
	private JDateChooser dateNgaySinh;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfDuong;
	private JTextField tfPhuong;
	private JTextField tfQuan;
	private JTextField textField_1;
	public JTable table;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public PhieuDatPhong() {
		setBounds(0, 0, 1251, 835);
		setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        
        JLabel lbHeader = new JLabel("Tạo phiếu đặt phòng");
        lbHeader.setForeground(new Color(255, 91, 13));
        lbHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
        lbHeader.setBounds(10, 10, 278, 41);
        add(lbHeader);
        
        JPanel pnTTPhieuThue = new JPanel();
        pnTTPhieuThue.setBounds(10, 61, 1231, 133);
        add(pnTTPhieuThue);
        pnTTPhieuThue.setLayout(null);
        
        JLabel lbtextttpt = new JLabel("Thông tin phiếu thuê");
        lbtextttpt.setForeground(new Color(0, 108, 217));
        lbtextttpt.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbtextttpt.setBounds(6, 6, 279, 37);
        pnTTPhieuThue.add(lbtextttpt);
        
        JLabel lblNewLabel = new JLabel("Mã chi tiết thuê");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel.setBounds(6, 42, 172, 21);
        pnTTPhieuThue.add(lblNewLabel);
        
        tfMaThue = new JTextField();
        tfMaThue.setEnabled(false);
        tfMaThue.setBounds(6, 75, 172, 26);
        pnTTPhieuThue.add(tfMaThue);
        tfMaThue.setColumns(10);
        
        JLabel lb = new JLabel("Nhân viên làm phiếu");
        lb.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lb.setBounds(242, 42, 172, 21);
        pnTTPhieuThue.add(lb);
        
        tfNVLP = new JTextField();
        tfNVLP.setEnabled(false);
        tfNVLP.setColumns(10);
        tfNVLP.setBounds(242, 75, 172, 26);
        pnTTPhieuThue.add(tfNVLP);
        
        JLabel lblNgyLpPhiu = new JLabel("Ngày lập phiếu");
        lblNgyLpPhiu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNgyLpPhiu.setBounds(503, 42, 172, 21);
        pnTTPhieuThue.add(lblNgyLpPhiu);
        
        tfNLP = new JTextField();
        tfNLP.setEnabled(false);
        tfNLP.setColumns(10);
        tfNLP.setBounds(503, 75, 172, 26);
        pnTTPhieuThue.add(tfNLP);
        
        JLabel lblNewLabel_1_1 = new JLabel("Tình trạng xử lý");
        lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(740, 42, 172, 21);
        pnTTPhieuThue.add(lblNewLabel_1_1);
        
        tfTTXL = new JTextField();
        tfTTXL.setEditable(false);
        tfTTXL.setText("Đang xử lý");
        tfTTXL.setColumns(10);
        tfTTXL.setBounds(740, 75, 172, 26);
        pnTTPhieuThue.add(tfTTXL);
        
        JLabel lblNewLabel_1 = new JLabel("Tiền đặt cọc");
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_1.setBounds(993, 42, 172, 21);
        pnTTPhieuThue.add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(993, 75, 172, 26);
        pnTTPhieuThue.add(textField);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 206, 1231, 219);
        add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("Thông tin khách hàng");
        lblNewLabel_2.setForeground(new Color(0, 108, 217));
        lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNewLabel_2.setBounds(6, 0, 182, 35);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Mã khách hàng");
        lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3.setBounds(6, 35, 162, 24);
        panel.add(lblNewLabel_3);
        
        tfMaKH = new JTextField();
        tfMaKH.setEnabled(false);
        tfMaKH.setBounds(6, 71, 162, 26);
        panel.add(tfMaKH);
        tfMaKH.setColumns(10);
        
        JLabel lblNewLabel_3_1 = new JLabel("Họ tên khách hàng");
        lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_1.setBounds(247, 35, 162, 24);
        panel.add(lblNewLabel_3_1);
        
        tfHTKH = new JTextField();
        tfHTKH.setEnabled(false);
        tfHTKH.setColumns(10);
        tfHTKH.setBounds(247, 71, 162, 26);
        panel.add(tfHTKH);
        
        JLabel lblNewLabel_3_2 = new JLabel("CMND/CCCD");
        lblNewLabel_3_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_2.setBounds(509, 35, 162, 24);
        panel.add(lblNewLabel_3_2);
        
        tfCMND = new JTextField();
        tfCMND.setColumns(10);
        tfCMND.setBounds(509, 71, 162, 26);
        panel.add(tfCMND);
        
        JLabel lblNewLabel_3_3 = new JLabel("Số điện thoại");
        lblNewLabel_3_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_3.setBounds(746, 35, 162, 24);
        panel.add(lblNewLabel_3_3);
        
        tfSDT = new JTextField();
        tfSDT.setEnabled(false);
        tfSDT.setColumns(10);
        tfSDT.setBounds(746, 71, 162, 26);
        panel.add(tfSDT);
        
        JLabel lblNewLabel_3_4 = new JLabel("Ngày sinh");
        lblNewLabel_3_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_4.setBounds(1001, 35, 162, 24);
        panel.add(lblNewLabel_3_4);
        
        dateNgaySinh = new JDateChooser();
        dateNgaySinh.setDateFormatString("dd/MM/yyyy");
        dateNgaySinh.setEnabled(false);
        dateNgaySinh.setBounds(1001, 71, 162, 26);
        panel.add(dateNgaySinh);
        
        JLabel lblNewLabel_3_5 = new JLabel("Giới tính");
        lblNewLabel_3_5.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5.setBounds(6, 121, 162, 24);
        panel.add(lblNewLabel_3_5);
        
        JRadioButton rdbtnGT0 = new JRadioButton("Nam");
        rdbtnGT0.setSelected(true);
        buttonGroup.add(rdbtnGT0);
        rdbtnGT0.setBounds(6, 169, 58, 20);
        panel.add(rdbtnGT0);
        
        JRadioButton rdbtnGT1 = new JRadioButton("Nữ");
        buttonGroup.add(rdbtnGT1);
        rdbtnGT1.setBounds(90, 169, 58, 20);
        panel.add(rdbtnGT1);
        
        JLabel lblNewLabel_3_5_1 = new JLabel("Địa chỉ");
        lblNewLabel_3_5_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1.setBounds(247, 126, 162, 24);
        panel.add(lblNewLabel_3_5_1);
        
        JLabel lblNewLabel_3_5_1_1 = new JLabel("Đường:");
        lblNewLabel_3_5_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1_1.setBounds(247, 169, 58, 24);
        panel.add(lblNewLabel_3_5_1_1);
        
        tfDuong = new JTextField();
        tfDuong.setBounds(303, 167, 67, 26);
        panel.add(tfDuong);
        tfDuong.setColumns(10);
        
        JLabel lblNewLabel_3_5_1_2 = new JLabel("Phường/Thôn:");
        lblNewLabel_3_5_1_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1_2.setBounds(380, 169, 99, 24);
        panel.add(lblNewLabel_3_5_1_2);
        
        tfPhuong = new JTextField();
        tfPhuong.setColumns(10);
        tfPhuong.setBounds(486, 167, 67, 26);
        panel.add(tfPhuong);
        
        JLabel lblNewLabel_3_5_1_2_1 = new JLabel("Quận/Huyện:");
        lblNewLabel_3_5_1_2_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1_2_1.setBounds(553, 169, 100, 24);
        panel.add(lblNewLabel_3_5_1_2_1);
        
        tfQuan = new JTextField();
        tfQuan.setColumns(10);
        tfQuan.setBounds(651, 167, 67, 26);
        panel.add(tfQuan);
        
        JLabel lblNewLabel_3_5_1_2_1_1 = new JLabel("Quốc tịch:");
        lblNewLabel_3_5_1_2_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1_2_1_1.setBounds(956, 166, 76, 24);
        panel.add(lblNewLabel_3_5_1_2_1_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(1042, 166, 135, 26);
        panel.add(textField_1);
        
        JLabel lblNewLabel_3_5_1_2_1_2 = new JLabel("Tỉnh/Thành:");
        lblNewLabel_3_5_1_2_1_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1_2_1_2.setBounds(722, 169, 100, 24);
        panel.add(lblNewLabel_3_5_1_2_1_2);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(812, 167, 105, 26);
        panel.add(textField_2);
        
        JPanel pnTable = new JPanel();
        pnTable.setBounds(10, 441, 1231, 302);
        add(pnTable);
        pnTable.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 66, 1219, 230);
        pnTable.add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"STT","Mã phòng","Tình trạng","Loại hình thuê","Ngày thuê","Ngày trả","Ngày Checkout","Giá phòng","Xóa"
        	}
        ));
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel_4 = new JLabel("Danh sách phòng đang xử lý");
        lblNewLabel_4.setForeground(new Color(0, 108, 217));
        lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblNewLabel_4.setBounds(6, 16, 289, 34);
        pnTable.add(lblNewLabel_4);
        
        JButton btnThemPhong = new JButton("Thêm Phòng");
        btnThemPhong.setBackground(new Color(77, 77, 255));
        btnThemPhong.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnThemPhong.setBounds(865, 771, 159, 41);
        add(btnThemPhong);
        
        JButton btnLuuPhieuThue = new JButton("Lưu Phiếu Thuê");
        btnLuuPhieuThue.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLuuPhieuThue.setBackground(new Color(77, 77, 255));
        btnLuuPhieuThue.setBounds(1046, 771, 159, 41);
        add(btnLuuPhieuThue);
        
        JLabel lbTongTien = new JLabel("Tổng tiền");
        lbTongTien.setFont(new Font("Segoe UI", Font.ITALIC, 17));
        lbTongTien.setBounds(673, 771, 159, 41);
        add(lbTongTien);
	}
}
