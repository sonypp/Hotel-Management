package GUI.KhachHang;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.Insets;

public class PanelCus extends JPanel {
	private int width = 1251;
	private int height = 835;

	private static final long serialVersionUID = 1L;
	private JPanel panelThuocTinh;
	private JPanel panelTitle;
	private JButton btnNhapMoi;
	private JLabel label_Title;
	private JLabel lblIDKH;
	private JTextField txtIDKH;
	private JLabel lblTenKH;
	private JTextField txtTenKH;
	private JLabel lblCMND;
	private JTextField txtCMND;
	private JLabel lblGioiTinh;
	private JComboBox<String> cbBoxGioiTinh;
	private JLabel lblPhone;
	private JTextField txtPhone;
	private JLabel lblQueQuan;
	private JTextField txtQueQuan;
	private JScrollPane scrollPaneDSKH;
	private JTable tableDSKH;
	private JButton btnTimKiem;

	/**
	 * Create the panel.
	 */
	public PanelCus() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(192, 192, 192)));
		setBounds(new Rectangle(0, 0, this.width, this.height));
		setBackground(new Color(245, 245, 245));
		setBounds(0, 0, 1251, 835);
		setLayout(null);
		
		panelThuocTinh = new JPanel();
		panelThuocTinh.setBackground(new Color(255, 255, 255));
		panelThuocTinh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panelThuocTinh.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelThuocTinh.setAutoscrolls(true);
		panelThuocTinh.setBounds(25, 91, 1200, 158);
		add(panelThuocTinh,BorderLayout.CENTER);
		panelThuocTinh.setLayout(null);
		
		JPanel panelThuocTinh_input = new JPanel();
		panelThuocTinh_input.setBackground(new Color(255, 255, 255));
		panelThuocTinh_input.setBounds(new Rectangle(0, 6, 1200, 98));
		panelThuocTinh.add(panelThuocTinh_input);
		
		lblIDKH = new JLabel("Mã Khách Hàng");
		lblIDKH.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtIDKH = new JTextField();
		txtIDKH.setMargin(new Insets(2, 11, 2, 6));
		txtIDKH.setBackground(new Color(240, 248, 255));
		txtIDKH.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIDKH.setColumns(15);
		
		lblTenKH = new JLabel("Tên Khách Hàng");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtTenKH = new JTextField();
		txtTenKH.setBackground(new Color(240, 248, 255));
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenKH.setColumns(15);
		
		lblCMND = new JLabel("Chứng Minh Nhân Dân");
		lblCMND.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtCMND = new JTextField();
		txtCMND.setBackground(new Color(240, 248, 255));
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCMND.setColumns(15);
		panelThuocTinh_input.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 18));
		panelThuocTinh_input.add(lblIDKH);
		panelThuocTinh_input.add(txtIDKH);
		panelThuocTinh_input.add(lblTenKH);
		panelThuocTinh_input.add(txtTenKH);
		panelThuocTinh_input.add(lblCMND);
		panelThuocTinh_input.add(txtCMND);
		
		lblGioiTinh = new JLabel("Giới Tính");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblGioiTinh);
		
		String[] optionGioiTinh = {"Không xác định","Nam","Nữ"};
		cbBoxGioiTinh = new JComboBox<>(optionGioiTinh);
		cbBoxGioiTinh.setBackground(new Color(240, 248, 255));
		cbBoxGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbBoxGioiTinh.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		panelThuocTinh_input.add(cbBoxGioiTinh);
		
		lblPhone = new JLabel("Số Điện Thoại");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBackground(new Color(240, 248, 255));
		txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(txtPhone);
		txtPhone.setColumns(15);
		
		lblQueQuan = new JLabel("Quê Quán");
		lblQueQuan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblQueQuan);
		
		txtQueQuan = new JTextField();
		txtQueQuan.setBackground(new Color(240, 248, 255));
		txtQueQuan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(txtQueQuan);
		txtQueQuan.setColumns(20);
		
		JPanel panelThuocTinh_btn = new JPanel();
		panelThuocTinh_btn.setBounds(925, 112, 251, 40);
		panelThuocTinh_btn.setBackground(new Color(255, 255, 255));
		panelThuocTinh.add(panelThuocTinh_btn);
		panelThuocTinh_btn.setLayout(new GridLayout(1, 2, 20, 5));
		
		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBorder(UIManager.getBorder("Button.border"));
		panelThuocTinh_btn.add(btnTimKiem);
		btnTimKiem.setForeground(new Color(240, 255, 255));
		btnTimKiem.setBackground(new Color(100, 149, 237));
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnNhapMoi = new JButton("Nhập Mới");
		btnNhapMoi.setBackground(new Color(255, 255, 255));
		btnNhapMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelThuocTinh_btn.add(btnNhapMoi);
		
		panelTitle = new JPanel();
		panelTitle.setForeground(new Color(250, 128, 114));
		panelTitle.setBackground(new Color(245, 245, 245));
		panelTitle.setBounds(25, 30, 1200, 68);
		add(panelTitle);
		panelTitle.setLayout(new GridLayout(0, 1, 1, 0));
		
		label_Title = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		label_Title.setBackground(new Color(43, 92, 138));
		label_Title.setForeground(new Color(51, 138, 196));
		label_Title.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Title.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelTitle.add(label_Title);
		
		JLabel label_Sub = new JLabel("Vui lòng bấm vào bảng danh sách để chỉnh sửa");
		label_Sub.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelTitle.add(label_Sub);
		
		JPanel panelDSKH = new JPanel();
		panelDSKH.setBackground(new Color(255, 255, 255));
		panelDSKH.setBounds(25, 261, 1200, 526);
		add(panelDSKH);
		panelDSKH.setLayout(null);
		
		JPanel panelDSKH_btn = new JPanel();
		panelDSKH_btn.setBackground(new Color(255, 255, 255));
		panelDSKH_btn.setBounds(25, 16, 1150, 40);
		panelDSKH.add(panelDSKH_btn);
		panelDSKH_btn.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDSKH = new JLabel("DANH SÁCH KHÁCH HÀNG");
		lblDSKH.setVerticalAlignment(SwingConstants.TOP);
		lblDSKH.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblDSKH.setForeground(new Color(0, 0, 0));
		lblDSKH.setBackground(new Color(70, 130, 180));
		panelDSKH_btn.add(lblDSKH, BorderLayout.WEST);
		
		JButton btnSuaKH = new JButton("Sửa Khách Hàng");
		btnSuaKH.setForeground(new Color(255, 255, 255));
		btnSuaKH.setBackground(new Color(169, 169, 169));
		btnSuaKH.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelDSKH_btn.add(btnSuaKH, BorderLayout.EAST);
		
		
		tableDSKH = new JTable();
		tableDSKH.setSurrendersFocusOnKeystroke(true);
		tableDSKH.setFillsViewportHeight(true);
		tableDSKH.setShowHorizontalLines(true);
		tableDSKH.setCellSelectionEnabled(true);
		tableDSKH.setColumnSelectionAllowed(true);
		tableDSKH.setSelectionBackground(new Color(100, 149, 237));
		tableDSKH.setSelectionForeground(new Color(255, 255, 255));
		tableDSKH.setRowHeight(30);
		tableDSKH.setRowMargin(5);
		tableDSKH.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tableDSKH.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tableDSKH.setAutoCreateRowSorter(true);
		tableDSKH.setGridColor(new Color(192, 192, 192));
		tableDSKH.setBackground(new Color(240, 248, 255));
		tableDSKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableDSKH.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "Hai Duong", "123456789", "Nam"},
				{"2", "Bich Phuong", "147258369", "N\u1EEF"},
			},
			new String[] {
				"STT", "T\u00EAn Kh\u00E1ch H\u00E0ng", "CMND", "Gi\u1EDBi t\u00EDnh"
			}
		));
		tableDSKH.getColumnModel().getColumn(0).setPreferredWidth(28);
		tableDSKH.getColumnModel().getColumn(0).setMinWidth(28);
		
        
		scrollPaneDSKH = new JScrollPane(tableDSKH);
		scrollPaneDSKH.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDSKH.setForeground(new Color(255, 255, 255));
		scrollPaneDSKH.setBounds(25, 68, 1150, 419);
		panelDSKH.add(scrollPaneDSKH);
		scrollPaneDSKH.setBackground(SystemColor.window);
		scrollPaneDSKH.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		
	}
}
