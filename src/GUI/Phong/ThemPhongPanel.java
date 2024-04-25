package GUI.Phong;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import BUS.PhongBUS;
import BUS.TienIchBUS;
import DTO.PhongDTO;
import DTO.TienIchDTO;

import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;

public class ThemPhongPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaPhong;
	private JTextField txtTenphong;
	private final ButtonGroup buttongroupCTLP = new ButtonGroup();
	private JTextField txtGiaPhong;
	private JTextField txtTinhTrang;
	private JTextField tfTimKiemTienIch;
	private JTable tbHienThiTienIchHienCo;
	private JTable tbTienIchPhong;
	private JLabel errLoiTenPhong;
	private JLabel errLoiGiaPhong;
	private JPanel pnttphongmoi;
	private JLabel errSoLg;
	private PhongBUS phongBUS = new PhongBUS();
	private TienIchBUS tienichBUS = new TienIchBUS();
	private List<TienIchDTO> list;
	private JSpinner spinnerSoLg;
	private JPanel pnLayoutBtn;
	private JButton btnAddRoom;
	private JButton btnUpdateRoom;
	private JButton btnDeleteRoom;
	private JSpinner spinnerSoLg_1;
	private ButtonGroup buttongroupLoaiPhong;
	private JRadioButton rbtnVip;
	private JRadioButton rbtnThuong;
	private JComboBox cbbHienTrang;
	private List<String> temp = new ArrayList<String>();
	private JRadioButton rbtnPDon;
	private JRadioButton rbtnPDoi;
	private JRadioButton rbtnPGD;
	private PhongDTO phongDTO;
	private JPanel pnTienIch;
	private JPanel panel;
	private JComponent btnXoaTienIch;
	private JComponent btnSuaTienIch;
	private JComponent lblNewLabel_1;
	private JComponent btnHuy;
	public ThemPhongPanel(int action, PhongDTO phongDTO)
	{
		this(action);
		this.phongDTO = phongDTO;
		setUI(action);
		
	}
	/**
	 * @wbp.parser.constructor
	 */
	public ThemPhongPanel(int action) {
		setBackground(new Color(192, 192, 192));
		setBounds(54, 30, 1149, 788);
		setLayout(null);

		JPanel pnTop = new JPanel();
		pnTop.setBackground(new Color(108, 108, 255));
		pnTop.setBounds(0, 0, 1143, 65);
		add(pnTop);
		pnTop.setLayout(null);

		JLabel lbltextThemPhong = new JLabel("Thêm phòng mới");
		lbltextThemPhong.setForeground(new Color(255, 255, 255));
		lbltextThemPhong.setFont(new Font("Tahoma", Font.BOLD, 19));
		lbltextThemPhong.setBounds(22, 10, 350, 45);
		pnTop.add(lbltextThemPhong);

		pnttphongmoi = new JPanel();
		pnttphongmoi.setBounds(10, 75, 448, 636);
		add(pnttphongmoi);
		pnttphongmoi.setLayout(null);

		JLabel lbttextTieude = new JLabel("THÔNG TIN PHÒNG MỚI");
		lbttextTieude.setForeground(new Color(0, 0, 0));
		lbttextTieude.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbttextTieude.setHorizontalAlignment(SwingConstants.CENTER);
		lbttextTieude.setBounds(73, 10, 301, 53);
		pnttphongmoi.add(lbttextTieude);

		JLabel lbltextMaphong = new JLabel("Mã phòng:");
		lbltextMaphong.setBounds(10, 78, 151, 30);
		pnttphongmoi.add(lbltextMaphong);

		txtMaPhong = new JTextField();
		txtMaPhong.setEditable(false);
		txtMaPhong.setBounds(10, 118, 428, 30);
		pnttphongmoi.add(txtMaPhong);
		txtMaPhong.setColumns(10);
		txtMaPhong.setText(createMaPhong());

		JLabel lbltextTenphong = new JLabel("Tên phòng:");
		lbltextTenphong.setBounds(10, 158, 151, 30);
		pnttphongmoi.add(lbltextTenphong);

		txtTenphong = new JTextField();
		txtTenphong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtTenphong.getText().trim().length() == 0) {
					errLoiTenPhong.setVisible(true);
				} else {
					errLoiTenPhong.setVisible(false);
				}
			}
		});

		txtTenphong.setColumns(10);
		txtTenphong.setBounds(10, 197, 428, 30);

		pnttphongmoi.add(txtTenphong);

		JLabel lblLoaiPhong = new JLabel("Loại phòng:");
		lblLoaiPhong.setBounds(10, 250, 151, 30);
		pnttphongmoi.add(lblLoaiPhong);

		rbtnVip = new JRadioButton("Phòng Vip");
		rbtnVip.setSelected(true);
		rbtnVip.setBounds(6, 286, 155, 21);
		pnttphongmoi.add(rbtnVip);

		rbtnThuong = new JRadioButton("Phòng Thường");
		rbtnThuong.setBounds(176, 286, 151, 21);
		pnttphongmoi.add(rbtnThuong);
		buttongroupLoaiPhong = new ButtonGroup();
		buttongroupLoaiPhong.add(rbtnVip);
		buttongroupLoaiPhong.add(rbtnThuong);

		JLabel lblChiTitLoaiPhong = new JLabel("Chi Tiết Loại phòng:");
		lblChiTitLoaiPhong.setBounds(10, 313, 151, 30);
		pnttphongmoi.add(lblChiTitLoaiPhong);

		rbtnPDon = new JRadioButton("Phòng đơn");
		rbtnPDon.setSelected(true);
		buttongroupCTLP.add(rbtnPDon);
		rbtnPDon.setBounds(6, 349, 87, 21);
		pnttphongmoi.add(rbtnPDon);

		rbtnPDoi = new JRadioButton("Phòng đôi");
		buttongroupCTLP.add(rbtnPDoi);
		rbtnPDoi.setBounds(121, 349, 87, 21);
		pnttphongmoi.add(rbtnPDoi);

		rbtnPGD = new JRadioButton("Phòng gia đình");
		buttongroupCTLP.add(rbtnPGD);
		rbtnPGD.setBounds(240, 349, 134, 21);
		pnttphongmoi.add(rbtnPGD);

		JLabel lbGiaPhong = new JLabel("Giá phòng:");
		lbGiaPhong.setBounds(10, 386, 151, 30);
		pnttphongmoi.add(lbGiaPhong);

		txtGiaPhong = new JTextField();
		txtGiaPhong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtGiaPhong.getText().trim().length() == 0) {
					errLoiGiaPhong.setVisible(true);
				} else {
					errLoiGiaPhong.setVisible(false);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
					e.consume(); // Không cho phép nhập ký tự này
				}
			}
		});
		txtGiaPhong.setColumns(10);
		txtGiaPhong.setBounds(10, 426, 428, 30);
		pnttphongmoi.add(txtGiaPhong);

		JLabel lblTinhtrang = new JLabel("Tình trạng:");
		lblTinhtrang.setBounds(10, 470, 151, 30);
		pnttphongmoi.add(lblTinhtrang);

		txtTinhTrang = new JTextField();
		txtTinhTrang.setEditable(false);
		txtTinhTrang.setText("Trống");
		txtTinhTrang.setColumns(10);
		txtTinhTrang.setBounds(10, 510, 428, 30);
		pnttphongmoi.add(txtTinhTrang);

		JLabel lblHinTrng = new JLabel("Hiện trạng:");
		lblHinTrng.setBounds(10, 550, 151, 30);
		pnttphongmoi.add(lblHinTrng);

		errLoiTenPhong = new JLabel("*Tên phòng không được để trống");
		errLoiTenPhong.setForeground(new Color(255, 0, 0));
		errLoiTenPhong.setBounds(10, 229, 428, 21);
		errLoiTenPhong.setVisible(false);
		pnttphongmoi.add(errLoiTenPhong);

		errLoiGiaPhong = new JLabel("*Giá phòng phải là số và không được để trống");
		errLoiGiaPhong.setForeground(Color.RED);
		errLoiGiaPhong.setBounds(10, 457, 428, 21);
		errLoiGiaPhong.setVisible(false);
		pnttphongmoi.add(errLoiGiaPhong);

		cbbHienTrang = new JComboBox();
		cbbHienTrang.setModel(new DefaultComboBoxModel(new String[] { "Mới ", "Cũ" }));
		cbbHienTrang.setBounds(10, 593, 428, 26);
		pnttphongmoi.add(cbbHienTrang);

		pnTienIch = new JPanel();
		pnTienIch.setBounds(489, 76, 633, 318);
		add(pnTienIch);
		pnTienIch.setLayout(null);

		JButton btnThemTienIch = new JButton("Thêm TIện Ích");
		btnThemTienIch.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if ((int) (spinnerSoLg.getValue()) == 0) {
					JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int rowSelected = tbHienThiTienIchHienCo.getSelectedRow();
					var value = (String) tbHienThiTienIchHienCo.getValueAt(rowSelected, 1);
					if (action == 0)
					{
					var count = spinnerSoLg.getValue();
					DefaultTableModel modeltbTienIch = (DefaultTableModel) tbTienIchPhong.getModel();

					if (modeltbTienIch.getRowCount() == 0) {
						modeltbTienIch.addRow(new Object[] { 1,
								(String) tbHienThiTienIchHienCo.getValueAt(rowSelected, 1),
								(String) tbHienThiTienIchHienCo.getValueAt(rowSelected, 2), spinnerSoLg.getValue() });
					} else {
						boolean flag = false;
						for (int row = 0; row < modeltbTienIch.getRowCount(); row++) {
							if (modeltbTienIch.getValueAt(row, 1) == value) {
								modeltbTienIch.setValueAt(
										(int) modeltbTienIch.getValueAt(row, 3) + (int) spinnerSoLg.getValue(), row, 3);
								flag = true;
							}

						}
						if (flag == false) {
							modeltbTienIch.addRow(new Object[] { modeltbTienIch.getRowCount() + 1,
									(String) tbHienThiTienIchHienCo.getValueAt(rowSelected, 1),
									(String) tbHienThiTienIchHienCo.getValueAt(rowSelected, 2),
									spinnerSoLg.getValue() });
						}
					}
					}
					if (action == 1)
					{	
						var listTIPhong = tienichBUS.getListTienIchPhong(txtMaPhong.getText());
						var tbTI = (DefaultTableModel) tbTienIchPhong.getModel();
						var maP = txtMaPhong.getText();
						boolean check = false;
						try {
							while(listTIPhong.next())
							{
								if (listTIPhong.getString(1).equalsIgnoreCase(value.toString().trim()))
								{
									check = true;
									break;
								}
							}
							if (check)
							{
								int soLg = Integer.parseInt(listTIPhong.getString(3)) + Integer.parseInt(spinnerSoLg.getValue().toString());
								String soLgString = String.valueOf(soLg);
								tienichBUS.SuaTienIchPhong(txtMaPhong.getText(), value.toString().trim(), soLgString.trim());
								var TIcount = tbTI.getRowCount();
								HienThiTIPhong(maP);
							}
							else
							{
								tienichBUS.ThemTienIchPhong(txtMaPhong.getText(), value, spinnerSoLg.getValue().toString());
								HienThiTIPhong(maP);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				
				}
					spinnerSoLg.setValue(0);
			}
		});
		btnThemTienIch.setBackground(new Color(0, 255, 64));
		btnThemTienIch.setForeground(new Color(255, 255, 255));
		btnThemTienIch.setBounds(455, 283, 153, 21);
		pnTienIch.add(btnThemTienIch);

		spinnerSoLg = new JSpinner();
		spinnerSoLg.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if ((int) (spinnerSoLg.getValue()) > 0) {
					errSoLg.setVisible(false);
				} else
					errSoLg.setVisible(true);
			}
		});
		spinnerSoLg.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinnerSoLg.setBounds(94, 282, 58, 24);
		pnTienIch.add(spinnerSoLg);

		JLabel lblNewLabel = new JLabel("Số lượng:");
		lblNewLabel.setBounds(10, 278, 77, 30);
		pnTienIch.add(lblNewLabel);

		tfTimKiemTienIch = new JTextField();
		tfTimKiemTienIch.setBounds(10, 30, 221, 19);
		pnTienIch.add(tfTimKiemTienIch);
		tfTimKiemTienIch.setColumns(10);

		JLabel lbTextTienIch = new JLabel("Nhập tiện ích cần tìm kiếm");
		lbTextTienIch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbTextTienIch.setBounds(10, 7, 221, 13);
		pnTienIch.add(lbTextTienIch);

		JButton btnTkiem = new JButton("Tìm kiếm");
		btnTkiem.setBounds(241, 29, 85, 21);
		pnTienIch.add(btnTkiem);

		JScrollPane scrptbTienIch = new JScrollPane();
		scrptbTienIch.setEnabled(false);
		scrptbTienIch.setBounds(10, 62, 611, 208);
		pnTienIch.add(scrptbTienIch);

		tbHienThiTienIchHienCo = new JTable() {
			@Override
			public TableCellEditor getCellEditor(int row, int column) {
				return null;
			}
		};
		tbHienThiTienIchHienCo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHienThiTienIchHienCo.setSurrendersFocusOnKeystroke(true);
		tbHienThiTienIchHienCo.setFillsViewportHeight(true);
		tbHienThiTienIchHienCo.setShowHorizontalLines(true);
		tbHienThiTienIchHienCo.setCellSelectionEnabled(false);
		tbHienThiTienIchHienCo.setColumnSelectionAllowed(false);
		tbHienThiTienIchHienCo.setRowSelectionAllowed(true);
		tbHienThiTienIchHienCo.setSelectionBackground(new Color(100, 149, 237));
		tbHienThiTienIchHienCo.setSelectionForeground(new Color(255, 255, 255));
		tbHienThiTienIchHienCo.setRowHeight(30);
		tbHienThiTienIchHienCo.setRowMargin(5);
		tbHienThiTienIchHienCo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tbHienThiTienIchHienCo.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tbHienThiTienIchHienCo.setAutoCreateRowSorter(true);
		tbHienThiTienIchHienCo.setGridColor(new Color(192, 192, 192));
		tbHienThiTienIchHienCo.setBackground(new Color(240, 248, 255));
		tbHienThiTienIchHienCo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbHienThiTienIchHienCo.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "STT", "MÃ TIỆN ÍCH", "TÊN TIỆN ÍCH" }));
		tbHienThiTienIchHienCo.getTableHeader().setReorderingAllowed(false);
		scrptbTienIch.setViewportView(tbHienThiTienIchHienCo);

		errSoLg = new JLabel("*Số lượng phải lớn hơn 0");
		errSoLg.setFont(new Font("Arial", Font.ITALIC, 12));
		errSoLg.setForeground(new Color(255, 0, 0));
		errSoLg.setBounds(164, 288, 162, 16);
		pnTienIch.add(errSoLg);

		panel = new JPanel();
		panel.setBounds(489, 409, 633, 302);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrptbTienIch_1 = new JScrollPane();
		scrptbTienIch_1.setBounds(6, 5, 621, 229);
		panel.add(scrptbTienIch_1);

		tbTienIchPhong = new JTable();
		tbTienIchPhong.setSurrendersFocusOnKeystroke(true);
		tbTienIchPhong.setFillsViewportHeight(true);
		tbTienIchPhong.setShowHorizontalLines(true);
		tbTienIchPhong.setCellSelectionEnabled(false);
		tbTienIchPhong.setColumnSelectionAllowed(false);
		tbTienIchPhong.setRowSelectionAllowed(true);
		tbTienIchPhong.setSelectionBackground(new Color(100, 149, 237));
		tbTienIchPhong.setSelectionForeground(new Color(255, 255, 255));
		tbTienIchPhong.setRowHeight(30);
		tbTienIchPhong.setRowMargin(5);
		tbTienIchPhong.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tbTienIchPhong.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tbTienIchPhong.setAutoCreateRowSorter(true);
		tbTienIchPhong.setGridColor(new Color(192, 192, 192));
		tbTienIchPhong.setBackground(new Color(240, 248, 255));
		tbTienIchPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbTienIchPhong.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "MÃ TIỆN ÍCH", "TÊN TIỆN ÍCH", "SỐ lƯỢNG" }));

		scrptbTienIch_1.setViewportView(tbTienIchPhong);

		lblNewLabel_1 = new JLabel("Số lượng:");
		lblNewLabel_1.setBounds(16, 242, 77, 30);
		panel.add(lblNewLabel_1);

		spinnerSoLg_1 = new JSpinner();
		spinnerSoLg_1.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerSoLg_1.setBounds(100, 246, 58, 24);
		panel.add(spinnerSoLg_1);

		btnSuaTienIch = new JButton("Sửa tiện ích");
		btnSuaTienIch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				var selectedRow = tbTienIchPhong.getSelectedRow();
				var value = tbTienIchPhong.getValueAt(selectedRow, 1);
				DefaultTableModel model = (DefaultTableModel) tbTienIchPhong.getModel();
				model.setValueAt(spinnerSoLg_1.getValue(), selectedRow, 3);
			}
		});
		btnSuaTienIch.setBackground(new Color(158, 62, 255));
		btnSuaTienIch.setBounds(262, 244, 176, 26);
		panel.add(btnSuaTienIch);

		btnXoaTienIch = new JButton("Xóa tiện ích");
		if(action == 0)
		{
		btnXoaTienIch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				var selectedRow = tbTienIchPhong.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn tiện ích cần xóa", "Thông báo",
							JOptionPane.CANCEL_OPTION);
				} else if (selectedRow != -1 && action == 0){
					DefaultTableModel model = (DefaultTableModel) tbTienIchPhong.getModel();
					System.out.print("Vo 0");
					model.removeRow(selectedRow);
				}
			}

		});
		}
		if (action == 1)
		{
			btnXoaTienIch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				var selectedRow = tbTienIchPhong.getSelectedRow();
				if (selectedRow == -1) {
					
					JOptionPane.showMessageDialog(null, "Vui lòng chọn tiện ích cần xóa", "Thông báo",
							JOptionPane.CANCEL_OPTION);
				} else
				{
					DefaultTableModel model = (DefaultTableModel) tbTienIchPhong.getModel();
					var maTI = tbTienIchPhong.getValueAt(tbTienIchPhong.getSelectedRow(), 1);
					temp.add(maTI.toString());
					System.out.print(temp.size());
					model.removeRow(selectedRow);
				}
			}

		});
		};
		btnXoaTienIch.setBackground(new Color(158, 62, 255));
		btnXoaTienIch.setBounds(458, 246, 169, 26);
		panel.add(btnXoaTienIch);

		pnLayoutBtn = new JPanel();
		pnLayoutBtn.setBackground(new Color(192, 192, 192));
		pnLayoutBtn.setBounds(489, 725, 633, 39);
		add(pnLayoutBtn);
		pnLayoutBtn.setLayout(null);
		GridBagLayout gbl_pnLayoutBtn = new GridBagLayout();
		gbl_pnLayoutBtn.columnWidths = new int[] { 84, 124, 112, 112, 35, 76, 0, 0 };
		gbl_pnLayoutBtn.rowHeights = new int[] { 31, 0 };
		gbl_pnLayoutBtn.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnLayoutBtn.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnLayoutBtn.setLayout(gbl_pnLayoutBtn);

		btnUpdateRoom = new JButton("Sửa phòng");
		btnUpdateRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.print("Solg la"+ temp.size());
				var maPhong = txtMaPhong.getText();
				var tenPhong = txtTenphong.getText().trim();
				var loaiPhong = (rbtnVip.isSelected()) ? "0" : "1";
				var chitietloaiphong = (rbtnPDon.isSelected()) ? "0" : (rbtnPDoi.isSelected()) ? "1" : "2";
				var giaPhong = txtGiaPhong.getText().trim();
				String formattedString = giaPhong;
				String numericString = formattedString.replaceAll("[^\\d]", ""); // Remove non-numeric characters
				String giaPhongString = (String) numericString;
				var hienTrang = cbbHienTrang.getSelectedIndex();
				String hienTrangString = String.valueOf(hienTrang);
				phongBUS.SuaPhong(maPhong, tenPhong, loaiPhong, giaPhongString, chitietloaiphong, "0");
				
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đổi thông tin không", "Thông báo",
						JOptionPane.YES_NO_OPTION);

				if (choice == JOptionPane.YES_OPTION) {
					phongBUS.SuaPhong(maPhong, tenPhong, loaiPhong, giaPhongString, chitietloaiphong, "0");
					for (int row = 0; row < tbTienIchPhong.getRowCount(); row++) {
						tienichBUS.SuaTienIchPhong(maPhong, tbTienIchPhong.getValueAt(row, 1).toString(),
						tbTienIchPhong.getValueAt(row, 3).toString());
					}
					for (int i = 0; i < temp.size(); i++) {
						System.out.print("Here");
						tienichBUS.XoaTienIchPhong(maPhong, temp.get(i));
					}
					QLPHONGPanel.DisposeFrame();
					QLPHONGPanel.initComponent();
				} else if (choice == JOptionPane.NO_OPTION) {
					JOptionPane.getRootFrame().dispose();
				}

			}
		});
		btnUpdateRoom.setBounds(213, 0, 112, 31);
		btnUpdateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnAddRoom = new JButton("Thêm phòng");
		btnAddRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtTenphong.getText().trim().length() == 0 || txtGiaPhong.getText().trim().length() == 0) {

					JOptionPane.showMessageDialog(null, "Không được để trống tên phòng hoặc giá phòng", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				} else {
					var maPhong = txtMaPhong.getText();
					var tenPhong = txtTenphong.getText().trim();
					var loaiPhong = (rbtnVip.isSelected()) ? "0" : "1";
					var chitietloaiphong = (rbtnPDon.isSelected()) ? "0" : (rbtnPDoi.isSelected()) ? "1" : "2";
					var giaPhong = txtGiaPhong.getText().trim();
					phongBUS.ThemPhong(maPhong, tenPhong, loaiPhong, giaPhong, chitietloaiphong, "0", "0");
					for (int row = 0; row < tbTienIchPhong.getRowCount(); row++) {
						tienichBUS.ThemTienIchPhong(maPhong, tbTienIchPhong.getValueAt(row, 1).toString(),
								tbTienIchPhong.getValueAt(row, 3).toString());
					}
					JOptionPane.showMessageDialog(null, "Thêm phòng thành công", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.getRootFrame().addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {

							QLPHONGPanel.DisposeFrame();
							QLPHONGPanel.initComponent();
						}
					});
				}
			}
		});
		btnAddRoom.setBounds(84, 0, 124, 31);
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddRoom.setBackground(new Color(0, 255, 64));
		btnAddRoom.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAddRoom.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_btnAddRoom = new GridBagConstraints();
		gbc_btnAddRoom.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddRoom.gridx = 1;
		gbc_btnAddRoom.gridy = 0;
		pnLayoutBtn.add(btnAddRoom, gbc_btnAddRoom);
		btnUpdateRoom.setForeground(Color.WHITE);
		btnUpdateRoom.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnUpdateRoom.setBackground(new Color(255, 128, 64));
		GridBagConstraints gbc_btnUpdateRoom = new GridBagConstraints();
		gbc_btnUpdateRoom.insets = new Insets(0, 0, 0, 5);
		gbc_btnUpdateRoom.gridx = 2;
		gbc_btnUpdateRoom.gridy = 0;
		pnLayoutBtn.add(btnUpdateRoom, gbc_btnUpdateRoom);

		btnDeleteRoom = new JButton("Xóa phòng");
		btnDeleteRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				var option = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa phòng không?","Thông báo",JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION)
				{
					phongBUS.XoaPhong(txtMaPhong.getText());
					JOptionPane.showMessageDialog(null, "Xóa phòng thành công","Thông báo",JOptionPane.CLOSED_OPTION);
					QLPHONGPanel.DisposeFrame();
					QLPHONGPanel.initComponent();
				}
			}
		});
		btnDeleteRoom.setBounds(330, 0, 112, 31);
		btnDeleteRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteRoom.setForeground(Color.WHITE);
		btnDeleteRoom.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnDeleteRoom.setBackground(new Color(255, 0, 0));
		GridBagConstraints gbc_btnDeleteRoom = new GridBagConstraints();
		gbc_btnDeleteRoom.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteRoom.gridx = 3;
		gbc_btnDeleteRoom.gridy = 0;
		pnLayoutBtn.add(btnDeleteRoom, gbc_btnDeleteRoom);

		btnHuy = new JButton("Đóng");
		btnHuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				QLPHONGPanel.DisposeFrame();
			}
		});
		btnHuy.setBounds(447, 0, 76, 31);
		btnHuy.setForeground(Color.WHITE);
		btnHuy.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnHuy.setBackground(new Color(148, 148, 148));
		GridBagConstraints gbc_btnHuy = new GridBagConstraints();
		gbc_btnHuy.insets = new Insets(0, 0, 0, 5);
		gbc_btnHuy.gridx = 4;
		gbc_btnHuy.gridy = 0;
		pnLayoutBtn.add(btnHuy, gbc_btnHuy);
		
		if(action != 2)
		{
			setUI(action);
		}
	}
	
	public void setUI(int action)
	{
		switch (action) {
		// Add room
		case 0:
			btnUpdateRoom.setVisible(false);
			btnDeleteRoom.setVisible(false);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.gridx = 3;
			gbc.gridy = 0;
			pnLayoutBtn.add(btnAddRoom, gbc);
			cbbHienTrang.setEnabled(false);
			createMaPhong();
			break;
		case 1:
			btnAddRoom.setVisible(false);
			GridBagConstraints gbc1 = new GridBagConstraints();
			gbc1.insets = new Insets(5, 5, 5, 5);
			gbc1.gridx = 2;
			gbc1.gridy = 0;
			pnLayoutBtn.add(btnDeleteRoom, gbc1);
			gbc1.gridx = 3;
			gbc1.gridy = 0;
			pnLayoutBtn.add(btnUpdateRoom, gbc1);
			cbbHienTrang.setEnabled(true);
			createMaPhong();
			break;
		case 2:
			txtMaPhong.setText(phongDTO.getMaP());
			txtTenphong.setEditable(false);
			txtTenphong.setText(phongDTO.getTenP());
			rbtnVip.setEnabled(false);
			rbtnThuong.setEnabled(false);
			if(phongDTO.getLoaiP() == 0) rbtnVip.setSelected(true); else rbtnThuong.setSelected(true);
			rbtnPDon.setEnabled(false);
			rbtnPDoi.setEnabled(false);
			rbtnPGD.setEnabled(false);
			if(phongDTO.getChiTietLoaiP() == 0)
				rbtnPDon.setSelected(true);
			else if (phongDTO.getChiTietLoaiP() == 1)
				rbtnPDoi.setSelected(true);
			else
				rbtnPGD.setSelected(true);
			txtGiaPhong.setEditable(false);
			txtGiaPhong.setText(phongDTO.getGiaP() + " VNĐ");
			txtTinhTrang.setEditable(false);
			if(phongDTO.getTinhTrang() == 0)
				txtTinhTrang.setText("Trống");
			else if(phongDTO.getTinhTrang() == 1)
				txtTinhTrang.setText("Đang có khách");
			else
				txtTinhTrang.setText("Chưa dọn phòng");
			cbbHienTrang.setEnabled(false);
			cbbHienTrang.setSelectedIndex(phongDTO.getHienTrang());
			pnTienIch.setVisible(false);
			panel.setBounds(489, 76, 633, 318);
			btnXoaTienIch.setVisible(false);
			btnSuaTienIch.setVisible(false);
			lblNewLabel_1.setVisible(false);
			spinnerSoLg_1.setVisible(false);
			btnAddRoom.setVisible(false);
			btnUpdateRoom.setVisible(false);
			btnDeleteRoom.setVisible(false);
			btnHuy.setVisible(false);
			HienThiTIPhong(phongDTO.getMaP());
			break;
		}
		
		HienThiListTienIchHienCo("");
	}

	public String createMaPhong() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		Calendar calendar = Calendar.getInstance();
		String ngaytaophong = new String();
		ngaytaophong = dateFormat.format(calendar.getTime());
		return String.format("P%s%04d", ngaytaophong, phongBUS.getCountPhong() + 1);

	}

	private void HienThiListTienIchHienCo(String search) {
		DefaultTableModel model = (DefaultTableModel) tbHienThiTienIchHienCo.getModel();
		model.setRowCount(0);
		list = tienichBUS.getAllTienIch();
		int k = 0;
		for (TienIchDTO tienIch : list) {
			if (tienIch.getTenTI().toUpperCase().contains(search.toUpperCase())) {
				++k;
				model.addRow(new Object[] { k, tienIch.getMaTI(), tienIch.getTenTI() });
			}
		}
		tbHienThiTienIchHienCo.clearSelection();
	}

	public void ChiTietPhong(Object[] thongtinPhong) throws SQLException {
		txtMaPhong.setText((String) thongtinPhong[1]);
		txtTenphong.setText((String) thongtinPhong[2]);
		rbtnVip.setSelected(thongtinPhong[3].toString().equalsIgnoreCase("vip") ? true : false);
		rbtnThuong.setSelected(thongtinPhong[3].toString().equalsIgnoreCase("thường") ? true : false);
		txtGiaPhong.setText(thongtinPhong[4].toString());
		rbtnPDon.setSelected(thongtinPhong[5].toString().equalsIgnoreCase("phòng đơn") ? true : false);
		rbtnPDoi.setSelected(thongtinPhong[5].toString().equalsIgnoreCase("phòng đôi") ? true : false);
		rbtnPGD.setSelected(thongtinPhong[5].toString().equalsIgnoreCase("phòng gia đình") ? true : false);
		cbbHienTrang.setSelectedIndex(thongtinPhong[7].toString().equalsIgnoreCase("mới") ? 0 : 1);
		var listTIPhong = tienichBUS.getListTienIchPhong((String) thongtinPhong[1]);
	
		int k = 0;
		while (listTIPhong.next()) {
			DefaultTableModel model = (DefaultTableModel) tbTienIchPhong.getModel();
			var maTI = listTIPhong.getString(1);
			var tenTI = listTIPhong.getString(2);
			var soLg = listTIPhong.getString(3);
			++k;
			model.addRow(new Object[] { k, maTI, tenTI, soLg });
		}
	}
	public void HienThiTIPhong(String maP)
	{
		DefaultTableModel model = (DefaultTableModel) tbTienIchPhong.getModel();
		model.setRowCount(0);
		var dsTIPhong = tienichBUS.getListTienIchPhong(maP);
		int k = 0 ;
		try {
			while (dsTIPhong.next())
			{
				k++;
				model.addRow(new Object[] {
						k,
						dsTIPhong.getString(1),
						dsTIPhong.getString(2),
						dsTIPhong.getString(3)
				});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
