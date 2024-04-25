package GUI.PhanQuyen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.DropMode;
import javax.swing.ListSelectionModel;

public class PanelPosition extends JPanel {

	private int width = 1251;
	private int height = 835;

	private static final long serialVersionUID = 1L;
	private JPanel panelTitle;
	private JLabel label_Title;
	private JScrollPane scrollPaneDSNV;
	private JPanel panelQLTK_Title;
	private JLabel labelQLTK_Title;
	private JLabel labelQLTK_Sub;
	private JTextField txtTimKiem;
	private JPanel panelQLQTC;
	private JScrollPane scrollPaneDSCN;
	private JPanel panelQLQTC_Title;
	private JLabel labelQLQTC_Title;
	private JLabel labelQLQTC_Sub;
	private JComboBox<String> cbBoxViTri;
	private JButton btnThemViTri;
	private JButton btnTimKiem;
	private JTable tableDSNV;
	private JTable tableDSCN;
	private JButton btnLuuTrangThai;

	/**
	 * Create the panel.
	 */
	public PanelPosition() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(192, 192, 192)));
		setBounds(new Rectangle(0, 0, this.width, this.height));
		setBackground(new Color(245, 245, 245));
		setBounds(0, 0, 1251, 835);
		setLayout(null);
		
		
		
		panelTitle = new JPanel();
		panelTitle.setForeground(new Color(250, 128, 114));
		panelTitle.setBackground(new Color(245, 245, 245));
		panelTitle.setBounds(25, 30, 1200, 68);
		add(panelTitle);
		panelTitle.setLayout(new GridLayout(0, 1, 1, 0));
		
		label_Title = new JLabel("QUẢN LÝ PHÂN QUYỀN");
		label_Title.setBackground(new Color(0, 0, 0));
		label_Title.setForeground(new Color(70, 130, 180));
		label_Title.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Title.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelTitle.add(label_Title);
		
		JLabel label_Sub = new JLabel("Vui lòng bấm vào bảng danh sách để chỉnh sửa");
		label_Sub.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelTitle.add(label_Sub);
		
		JPanel panelQLTK = new JPanel();
		panelQLTK.setBackground(new Color(255, 255, 255));
		panelQLTK.setBounds(25, 98, 681, 673);
		add(panelQLTK);
		panelQLTK.setLayout(null);
		
		
		tableDSNV = new JTable();
		tableDSNV.setDoubleBuffered(true);
		tableDSNV.setAutoCreateRowSorter(true);
		tableDSNV.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tableDSNV.setFillsViewportHeight(true);
		tableDSNV.setShowHorizontalLines(true);
		tableDSNV.setCellSelectionEnabled(true);
		tableDSNV.setColumnSelectionAllowed(true);
		tableDSNV.setSelectionBackground(new Color(112, 128, 144));
		tableDSNV.setSelectionForeground(new Color(255, 255, 255));
		tableDSNV.setRowHeight(30);
		tableDSNV.setRowMargin(5);
		tableDSNV.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tableDSNV.setGridColor(new Color(192, 192, 192));
		tableDSNV.setBackground(new Color(245, 255, 250));
		tableDSNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableDSNV.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"STT", "Mã nhân viên", "Tên nhân viên", "Tài khoản", "Tình trạng", "Tạo", "Sửa"
			}
		));
		tableDSNV.getColumnModel().getColumn(0).setPreferredWidth(28);
		tableDSNV.getColumnModel().getColumn(0).setMinWidth(28);
		
        
		scrollPaneDSNV = new JScrollPane(tableDSNV);
		scrollPaneDSNV.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDSNV.setForeground(new Color(255, 255, 255));
		scrollPaneDSNV.setBounds(28, 242, 624, 343);
		panelQLTK.add(scrollPaneDSNV);
		scrollPaneDSNV.setBackground(SystemColor.window);
		scrollPaneDSNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panelQLTK_TimKiem = new JPanel();
		panelQLTK_TimKiem.setBorder(new TitledBorder(null, "Nh\u1EADp m\u00E3/t\u00EAn nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panelQLTK_TimKiem.setBackground(Color.WHITE);
		panelQLTK_TimKiem.setBounds(28, 113, 624, 70);
		panelQLTK.add(panelQLTK_TimKiem);
		panelQLTK_TimKiem.setLayout(null);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiem.setBackground(new Color(245, 255, 250));
		txtTimKiem.setBounds(6, 29, 455, 35);
		txtTimKiem.setPreferredSize(new Dimension(68, 50));
		panelQLTK_TimKiem.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setLocation(483, 29);
		btnTimKiem.setSize(new Dimension(135, 35));
		panelQLTK_TimKiem.add(btnTimKiem);
		btnTimKiem.setForeground(new Color(240, 255, 255));
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBorder(UIManager.getBorder("Button.border"));
		btnTimKiem.setBackground(new Color(0, 128, 128));
		
		panelQLTK_Title = new JPanel();
		panelQLTK_Title.setForeground(new Color(250, 128, 114));
		panelQLTK_Title.setBackground(new Color(255, 255, 255));
		panelQLTK_Title.setBounds(28, 16, 624, 76);
		panelQLTK.add(panelQLTK_Title);
		panelQLTK_Title.setLayout(new GridLayout(0, 1, 1, 0));
		
		labelQLTK_Title = new JLabel("QUẢN LÝ TÀI KHOẢN");
		labelQLTK_Title.setForeground(new Color(0, 128, 0));
		labelQLTK_Title.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelQLTK_Title.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		labelQLTK_Title.setBackground(Color.BLACK);
		panelQLTK_Title.add(labelQLTK_Title);
		
		labelQLTK_Sub = new JLabel("Vui lòng tạo tài khoản cho nhân viên");
		labelQLTK_Sub.setVerticalAlignment(SwingConstants.TOP);
		labelQLTK_Sub.setHorizontalAlignment(SwingConstants.LEFT);
		labelQLTK_Sub.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelQLTK_Title.add(labelQLTK_Sub);
		
		JLabel lblDSTK = new JLabel("DANH SÁCH NHÂN VIÊN");
		lblDSTK.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSTK.setForeground(new Color(112, 128, 144));
		lblDSTK.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDSTK.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblDSTK.setBackground(Color.BLACK);
		lblDSTK.setBounds(28, 206, 624, 33);
		panelQLTK.add(lblDSTK);
		
		panelQLQTC = new JPanel();
		panelQLQTC.setLayout(null);
		panelQLQTC.setBackground(Color.WHITE);
		panelQLQTC.setBounds(718, 98, 507, 673);
		add(panelQLQTC);
		
		
		panelQLQTC_Title = new JPanel();
		panelQLQTC_Title.setForeground(new Color(250, 128, 114));
		panelQLQTC_Title.setBackground(Color.WHITE);
		panelQLQTC_Title.setBounds(28, 16, 454, 76);
		panelQLQTC.add(panelQLQTC_Title);
		panelQLQTC_Title.setLayout(new GridLayout(0, 1, 1, 0));
		
		labelQLQTC_Title = new JLabel("QUẢN LÝ QUYỀN TRUY CẬP");
		labelQLQTC_Title.setForeground(new Color(100, 149, 237));
		labelQLQTC_Title.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelQLQTC_Title.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		labelQLQTC_Title.setBackground(Color.BLACK);
		panelQLQTC_Title.add(labelQLQTC_Title);
		
		labelQLQTC_Sub = new JLabel("Vui lòng chọn chức năng cho quyền bên dưới");
		labelQLQTC_Sub.setVerticalAlignment(SwingConstants.TOP);
		labelQLQTC_Sub.setHorizontalAlignment(SwingConstants.LEFT);
		labelQLQTC_Sub.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelQLQTC_Title.add(labelQLQTC_Sub);
		
		JPanel panelDSVT = new JPanel();
		panelDSVT.setLayout(null);
		panelDSVT.setBackground(Color.WHITE);
		panelDSVT.setBounds(28, 104, 454, 81);
		panelQLQTC.add(panelDSVT);
		
		String[] optionViTri = {};
		cbBoxViTri = new JComboBox<>(optionViTri);
		cbBoxViTri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbBoxViTri.setBounds(10, 39, 280, 36);
		panelDSVT.add(cbBoxViTri);
		
		btnThemViTri = new JButton("Thêm Vị Trí");
		btnThemViTri.setSize(new Dimension(135, 35));
		btnThemViTri.setForeground(new Color(255, 255, 255));
		btnThemViTri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemViTri.setBorder(UIManager.getBorder("Button.border"));
		btnThemViTri.setBackground(new Color(135, 206, 250));
		btnThemViTri.setBounds(314, 40, 123, 35);
		panelDSVT.add(btnThemViTri);
		
		JLabel lblDSVT_Title = new JLabel("DANH SÁCH VỊ TRÍ");
		lblDSVT_Title.setHorizontalAlignment(SwingConstants.LEFT);
		lblDSVT_Title.setForeground(new Color(112, 128, 144));
		lblDSVT_Title.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDSVT_Title.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblDSVT_Title.setBackground(Color.BLACK);
		lblDSVT_Title.setBounds(0, 6, 454, 33);
		panelDSVT.add(lblDSVT_Title);
		
		JLabel lblDSCN_Title = new JLabel("DANH SÁCH CHỨC NĂNG");
		lblDSCN_Title.setHorizontalAlignment(SwingConstants.LEFT);
		lblDSCN_Title.setForeground(new Color(112, 128, 144));
		lblDSCN_Title.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDSCN_Title.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblDSCN_Title.setBackground(Color.BLACK);
		lblDSCN_Title.setBounds(28, 208, 454, 33);
		panelQLQTC.add(lblDSCN_Title);
		
        tableDSCN = new JTable();
        tableDSCN.setGridColor(new Color(211, 211, 211));
        tableDSCN.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableDSCN.setRequestFocusEnabled(false);
        tableDSCN.setSelectionForeground(new Color(0, 0, 0));
        tableDSCN.setSelectionBackground(new Color(135, 206, 250));
        tableDSCN.setBackground(new Color(245, 245, 245));
        tableDSCN.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"STT", "M\u00E3 ch\u1EE9c n\u0103ng", "T\u00EAn ch\u1EE9c n\u0103ng", "Check"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		Integer.class, Integer.class, String.class, Boolean.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        tableDSCN.setShowHorizontalLines(true);
        tableDSCN.setRowMargin(5);
        tableDSCN.setRowHeight(30);
		scrollPaneDSCN = new JScrollPane(tableDSCN);
		scrollPaneDSCN.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDSCN.setForeground(new Color(105, 105, 105));
		scrollPaneDSCN.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPaneDSCN.setBackground(SystemColor.window);
		scrollPaneDSCN.setBounds(28, 242, 454, 342);
		panelQLQTC.add(scrollPaneDSCN);
		
		btnLuuTrangThai = new JButton("Lưu Trạng Thái");
		btnLuuTrangThai.setSize(new Dimension(135, 35));
		btnLuuTrangThai.setForeground(Color.WHITE);
		btnLuuTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLuuTrangThai.setBorder(UIManager.getBorder("Button.border"));
		btnLuuTrangThai.setBackground(UIManager.getColor("Button.default.pressedBackground"));
		btnLuuTrangThai.setBounds(337, 595, 145, 35);
		panelQLQTC.add(btnLuuTrangThai);
		
	}
}
