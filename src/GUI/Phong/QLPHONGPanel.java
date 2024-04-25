package GUI.Phong;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import BUS.PhongBUS;
import DTO.PhongDTO;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QLPHONGPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldMP;
	private JTextField textFieldTenPhong;
	private JComboBox cbbLoaiphong;
	private JComboBox cbbCtlp;
	private JComboBox cbbTinhTrang;
	private JSpinner spinnerGiaPhongTu;
	private JSpinner spinnerGiaPhongDen;
	private JComboBox cbbHientrang;
	static PhongBUS phong = new PhongBUS();
	static List<PhongDTO> list;
	private static JTable tbQLPhong;
	private static JFrame popupFrame;
	
	/**
	 * Create the panel.
	 */
	public QLPHONGPanel() {
		
		setBackground(new Color(233, 233, 233));
		setBounds(0, 0, 1251, 835);
		setLayout(null);

		JLabel lbQLPhong = new JLabel("Quản lý phòng");
		lbQLPhong.setForeground(new Color(0, 0, 255));
		lbQLPhong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbQLPhong.setBounds(10, 10, 178, 84);
		add(lbQLPhong);

		JLabel lbLuuy = new JLabel("Vui lòng bấm bảng danh sách phòng để chỉnh sửa");
		lbLuuy.setForeground(new Color(0, 0, 255));
		lbLuuy.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lbLuuy.setBounds(10, 96, 498, 33);
		add(lbLuuy);
		JPanel panelTimkiem = new JPanel();
		panelTimkiem.setBounds(10, 132, 1231, 109);
		panelTimkiem.setForeground(new Color(255, 0, 128));
		panelTimkiem.setBackground(new Color(255, 255, 255));
		add(panelTimkiem);

		JLabel lbMaphong = new JLabel("Mã phòng:");
		lbMaphong.setBounds(10, 10, 71, 24);

		textFieldMP = new JTextField();
		textFieldMP.setBounds(85, 12, 119, 19);
		textFieldMP.setBackground(new Color(255, 255, 255));
		textFieldMP.setColumns(10);
		panelTimkiem.setLayout(null);
		panelTimkiem.add(lbMaphong);
		panelTimkiem.add(textFieldMP);

		JLabel lbTnhTrng = new JLabel("Tình trạng:");
		lbTnhTrng.setBounds(951, 10, 71, 24);
		panelTimkiem.add(lbTnhTrng);

		JLabel lbTenPhong = new JLabel("Tên phòng:");
		lbTenPhong.setBounds(233, 10, 81, 24);
		panelTimkiem.add(lbTenPhong);

		textFieldTenPhong = new JTextField();
		textFieldTenPhong.setColumns(10);
		textFieldTenPhong.setBackground(new Color(255, 255, 255));
		textFieldTenPhong.setBounds(313, 13, 119, 19);
		panelTimkiem.add(textFieldTenPhong);

		JLabel lbHinTrng = new JLabel("Hiện trạng:");
		lbHinTrng.setBounds(451, 61, 71, 24);
		panelTimkiem.add(lbHinTrng);

		cbbHientrang = new JComboBox();
		cbbHientrang.setBackground(new Color(255, 255, 255));
		cbbHientrang.setModel(new DefaultComboBoxModel(new String[] { "Mới" }));
		cbbHientrang.setBounds(532, 63, 118, 21);
		panelTimkiem.add(cbbHientrang);

		JLabel lbLoaiphong = new JLabel("Loại phòng:");
		lbLoaiphong.setBounds(451, 10, 71, 24);
		panelTimkiem.add(lbLoaiphong);

		cbbLoaiphong = new JComboBox();
		cbbLoaiphong.setBackground(new Color(255, 255, 255));
		cbbLoaiphong.setModel(new DefaultComboBoxModel(new String[] {"", "Vip", "Thường"}));
		cbbLoaiphong.setBounds(532, 13, 106, 21);
		panelTimkiem.add(cbbLoaiphong);

		JLabel lbCtlp = new JLabel("Chi tiết loại phòng:");
		lbCtlp.setBounds(648, 10, 119, 24);
		panelTimkiem.add(lbCtlp);

		cbbCtlp = new JComboBox();
		cbbCtlp.setBackground(new Color(255, 255, 255));
		cbbCtlp.setModel(new DefaultComboBoxModel(new String[] {"", "Phòng đơn", "Phòng đôi", "Phòng gia đình"}));
		cbbCtlp.setBounds(769, 12, 128, 21);
		panelTimkiem.add(cbbCtlp);

		JLabel lblGiPhng = new JLabel("Giá phòng từ':");
		lblGiPhng.setBounds(10, 61, 81, 24);
		panelTimkiem.add(lblGiPhng);
		
		cbbTinhTrang = new JComboBox();
		cbbTinhTrang.setModel(new DefaultComboBoxModel(new String[] {"", "Trống", "Đang được thuê", "Chưa dọn phòng"}));
		cbbTinhTrang.setBackground(Color.WHITE);
		cbbTinhTrang.setBounds(1020, 12, 118, 21);
		panelTimkiem.add(cbbTinhTrang);
		
		JLabel lblGiPhngT = new JLabel("Giá phòng đến:");
		lblGiPhngT.setBounds(233, 61, 95, 24);
		panelTimkiem.add(lblGiPhngT);
		
		spinnerGiaPhongTu = new JSpinner();
		spinnerGiaPhongTu.setModel(new SpinnerNumberModel(0, 0, 5000000, 1000));
		spinnerGiaPhongTu.setBounds(98, 60, 106, 26);
		panelTimkiem.add(spinnerGiaPhongTu);
		
		spinnerGiaPhongDen = new JSpinner();
		spinnerGiaPhongDen.setModel(new SpinnerNumberModel(0, 0, 5000000, 1000));
		spinnerGiaPhongDen.setBounds(326, 60, 106, 26);
		panelTimkiem.add(spinnerGiaPhongDen);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				var maP = textFieldMP.getText();
				var  tenP = textFieldTenPhong.getText();
				var hienTrang = cbbHientrang.getSelectedIndex() - 1;
				var tinhTrang = cbbTinhTrang.getSelectedIndex() - 1;
				var loaiP = cbbLoaiphong.getSelectedIndex() -1 ;
				System.out.print(loaiP);
				var ctlp = cbbCtlp.getSelectedIndex() - 1;
				var giaPMin =(int)spinnerGiaPhongTu.getValue();
				var giaPMax = (int)spinnerGiaPhongDen.getValue();
				List<PhongDTO> DSPhong = phong.FindPhong(maP, tenP,loaiP, ctlp, giaPMin, giaPMax ,tinhTrang, hienTrang);
				HienThiPhong(DSPhong,tbQLPhong);
				Reload();
				
			}
		});
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTimKiem.setBackground(new Color(0, 128, 64));
		btnTimKiem.setBounds(977, 60, 161, 32);
		panelTimkiem.add(btnTimKiem);
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				Reload();
				initComponent();
			}
		});
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLamMoi.setBackground(Color.YELLOW);
		btnLamMoi.setBounds(776, 60, 161, 32);
		panelTimkiem.add(btnLamMoi);

		JPanel pnDSPhong = new JPanel();
		pnDSPhong.setBackground(new Color(255, 255, 255));
		pnDSPhong.setBackground(new Color(255, 255, 255));
		pnDSPhong.setBounds(10, 264, 1231, 561);
		add(pnDSPhong);
		pnDSPhong.setLayout(null);

		JLabel lbtextQLPHONG = new JLabel("Danh sách quản lý phòng khách sạn");
		lbtextQLPHONG.setForeground(new Color(0, 0, 255));
		lbtextQLPHONG.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lbtextQLPHONG.setBounds(10, 10, 357, 39);
		pnDSPhong.add(lbtextQLPHONG);
		JButton btnThemPhong = new JButton("Thêm phòng mới");
		btnThemPhong.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				popupFrame = new JFrame("Popup Frame");
				popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				popupFrame.setSize(1132, 800);
				ThemPhongPanel themphongpanel = new ThemPhongPanel(0);
				popupFrame.getContentPane().add(themphongpanel);
				popupFrame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
				popupFrame.setVisible(true);
//				popupFrame.addWindowFocusListener(new WindowAdapter() {
//			            @Override
//			            public void windowLostFocus(WindowEvent e) {
//			                popupFrame.dispose(); // Tắt frame khi mất focus
//			            }
//			        });

			}
		});
		btnThemPhong.setForeground(new Color(255, 255, 255));
		btnThemPhong.setBackground(new Color(0, 128, 64));
		btnThemPhong.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThemPhong.setBounds(811, 10, 161, 32);
		pnDSPhong.add(btnThemPhong);

		JButton btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var selectedRow = tbQLPhong.getSelectedRow();
				if (selectedRow == -1) JOptionPane.showMessageDialog(null,"Vui lòng chọn phòng cần chỉnh sửa","Thông báo",JOptionPane.OK_OPTION);
				else
				{
				popupFrame = new JFrame("Popup Frame");
				popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				popupFrame.setSize(1132, 800);
				ThemPhongPanel themphongpanel = new ThemPhongPanel(1);
				popupFrame.getContentPane().add(themphongpanel);
				popupFrame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
				popupFrame.setVisible(true);
//				popupFrame.addWindowFocusListener(new WindowAdapter() {
//		            @Override
//		            public void windowLostFocus(WindowEvent e) {
//		                popupFrame.dispose(); // Tắt frame khi mất focus
//		            }
//		        });
				Object[] thongtinPhong = new Object[tbQLPhong.getColumnCount()];
				for (int i = 0; i < tbQLPhong.getColumnCount(); i++)
				{
					thongtinPhong[i] = tbQLPhong.getValueAt(selectedRow, i);
				}
				try {
					themphongpanel.ChiTietPhong(thongtinPhong);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnXemChiTiet.setForeground(new Color(255, 255, 255));
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXemChiTiet.setBackground(new Color(255, 255, 0));
		btnXemChiTiet.setBounds(1030, 10, 161, 32);
		pnDSPhong.add(btnXemChiTiet);
		
		JScrollPane scrptbPHONG = new JScrollPane();
		scrptbPHONG.setBounds(10, 92, 1215, 463);
		pnDSPhong.add(scrptbPHONG);
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		tbQLPhong = new JTable() {
			@Override
			public TableCellEditor getCellEditor(int row, int column) 
			{
			return null;
			}
		};
		tbQLPhong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbQLPhong.setSurrendersFocusOnKeystroke(true);
		tbQLPhong.setFillsViewportHeight(true);
		tbQLPhong.setShowHorizontalLines(true);
		tbQLPhong.setCellSelectionEnabled(false);
		tbQLPhong.setColumnSelectionAllowed(false);
		tbQLPhong.setRowSelectionAllowed(true);
		tbQLPhong.setSelectionBackground(new Color(100, 149, 237));
		tbQLPhong.setSelectionForeground(new Color(255, 255, 255));
		tbQLPhong.setRowHeight(30);
		tbQLPhong.setRowMargin(5);
		tbQLPhong.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tbQLPhong.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tbQLPhong.setAutoCreateRowSorter(true);
		tbQLPhong.setGridColor(new Color(192, 192, 192));
		tbQLPhong.setBackground(new Color(240, 248, 255));
		tbQLPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbQLPhong.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"STT", "M\u00E3 ph\u00F2ng", "T\u00EAn ph\u00F2ng", "Lo\u1EA1i ph\u00F2ng", "Gi\u00E1 ph\u00F2ng", "Chi ti\u1EBFt lo\u1EA1i ph\u00F2ng", "T\u00ECnh tr\u1EA1ng", "Hi\u1EC7n tr\u1EA1ng"
			}
		));
		scrptbPHONG.setViewportView(tbQLPhong);
		
		initComponent();
	}

	public static void initComponent() {
		list = phong.getListPhong_DTO();
		HienThiPhong(list,tbQLPhong);
		
	}
	
	private static void HienThiPhong(List<PhongDTO> list,JTable tbPhong) {
	    DefaultTableModel model = (DefaultTableModel) tbPhong.getModel();
	    model.setRowCount(0);
	    int k = 0;
	    for (PhongDTO phong : list) {
	        String loaiP = "";
	        String chiTietLoaiP = "";
	        String tinhTrang = "";
	        String hienTrang = "";

	        if (phong.getLoaiP() == 0) {
	            loaiP = "Vip";
	        } else {
	            loaiP = "Thường";
	        }

	        if (phong.getChiTietLoaiP() == 0) {
	            chiTietLoaiP = "Phòng đơn";
	        } else if (phong.getChiTietLoaiP() == 1) {
	            chiTietLoaiP = "Phòng đôi";
	        } else {
	            chiTietLoaiP = "Phòng gia đình";
	        }

	        if (phong.getTinhTrang() == 0) {
	            tinhTrang = "Trống";
	        } else if (phong.getTinhTrang() == 1) {
	            tinhTrang = "Đang được thuê";
	        } else {
	            tinhTrang = "Chưa dọn phòng";
	        }

	        if (phong.getHienTrang() == 0) {
	            hienTrang = "Mới";
	        } else {
	            hienTrang = "Cũ";
	        }

	        model.addRow(new Object[]{
	            ++k,
	            phong.getMaP(),
	            phong.getTenP(),
	            loaiP,
	            String.format("%,d VNĐ", phong.getGiaP()),
	            chiTietLoaiP,
	            tinhTrang,
	            hienTrang
	        });
	    }
	    tbPhong.clearSelection();
	}
	public static void DisposeFrame()
	{
		popupFrame.dispose();
	}
	public void Reload()
	{
		textFieldMP.setText(null);
		textFieldTenPhong.setText(null);
		cbbCtlp.setSelectedIndex(0);
		cbbLoaiphong.setSelectedIndex(0);
		cbbTinhTrang.setSelectedIndex(0);
		cbbHientrang.setSelectedIndex(0);
		spinnerGiaPhongTu.setValue(0);
		spinnerGiaPhongDen.setValue(0);
	}
}
