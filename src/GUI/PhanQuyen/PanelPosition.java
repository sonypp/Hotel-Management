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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import BUS.ChucNangBUS;
import BUS.NhanVienBUS;
import BUS.PhanQuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.DSNhanVienDTO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.DangNhap.FormDangNhap;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.event.ItemEvent;


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
	public PhanQuyenBUS pqBUS = new PhanQuyenBUS();
	public ChucNangBUS cnBUS = new ChucNangBUS();
	public NhanVienBUS nvBUS = new NhanVienBUS();
	public DSNhanVienDTO nvDTO = new DSNhanVienDTO();
	public TaiKhoanBUS tkBUS = new TaiKhoanBUS();
	public TaiKhoanDTO tkDTO = new TaiKhoanDTO();

	public PanelPosition(){
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
		
		
		tableDSNV = new JTable()
				{
					@Override
					public TableCellEditor getCellEditor(int row, int column)
					{
						return null;
					}
				};
	

		tableDSNV.getTableHeader().setReorderingAllowed(false);
		
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
		tableDSNV.setBackground(new Color(255, 255, 255));
		tableDSNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableDSNV.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"STT", "Mã nhân viên", "Tên nhân viên", "Chức vụ", "Tài khoản", "Tình trạng", "Tạo", "Sửa", "Xóa"
			}
		));
		tableDSNV.getColumnModel().getColumn(0).setPreferredWidth(28);
		tableDSNV.getColumnModel().getColumn(0).setMinWidth(28);
		
		tableDSNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = tableDSNV.columnAtPoint(e.getPoint());
		        int row = tableDSNV.rowAtPoint(e.getPoint());
		        
			       
			     if(row != -1) {  
				        String maNV = tableDSNV.getValueAt(row, 1).toString();
			            String tenNV = tableDSNV.getValueAt(row, 2).toString();
			            String pq = tableDSNV.getValueAt(row, 3).toString();
			            String TT = tableDSNV.getValueAt(row, 5).toString();
			            
			            
			            if(column == 6) 
				        {
				        	if(TT.equals("Chưa thiết lập")) 
				            {
				            	
		    				    	String newTaiKhoan = JOptionPane.showInputDialog(null, "Nhập tài khoản mới\nMã nhân viên: " + maNV + "\nTên nhân viên: " + tenNV + "\nThêm tài khoản", JOptionPane.PLAIN_MESSAGE);
		    				    	if (newTaiKhoan != null && !newTaiKhoan.isEmpty()) 
		    				    	{
		    				    		
		    				    		tkBUS.TaoTaiKhoan(newTaiKhoan, maNV, maNV, 0, 0);
		    				    		DefaultTableModel model = (DefaultTableModel) tableDSNV.getModel();
		    				    		TT = "Đâ cấp tài khoản";
		    	                        model.setValueAt(newTaiKhoan, row, 4);
		    	                        model.setValueAt(TT, row, 5);
		    				    	}
		    						
		    				    	
		    				  
		    				}
		    				else if (TT.equals("Đang hoạt động"))
		    				{
		    					JOptionPane.showMessageDialog(null, "Nhân viên đã có tài khoản.");
		    				}
		    				else 
		    				{
		    					JOptionPane.showMessageDialog(null, "Nhân viên này đã bị khóa tài khoản");
		    				}
				            	
				        }
				    
				        if (column == 7) 
				        {
				        	String tenTK = tableDSNV.getValueAt(row, 4).toString();
				        	JPopupMenu popupMenu = new JPopupMenu();
				            JMenuItem menuItemKhoaTaiKhoan = new JMenuItem("Khóa tài khoản");
				            JMenuItem menuItemMoKhoa = new JMenuItem("Mở khóa");
				            JMenuItem menuItemPhanQuyen = new JMenuItem("Phân quyền");
				            
				            menuItemKhoaTaiKhoan.addActionListener(new ActionListener() {
				                public void actionPerformed(ActionEvent e) {
				                	String TT = tableDSNV.getValueAt(row, 5).toString();
				                  
				                    tkBUS.KhoaTaiKhoan(tenTK);
					                DefaultTableModel model = (DefaultTableModel) tableDSNV.getModel();
					                TT = "Đã khóa";
			    	                model.setValueAt(TT, row, 5);
			    	                JOptionPane.showMessageDialog(null, "Khóa tài khoản thành công");
				    				
				                    
			                 }
			                });
				            menuItemMoKhoa.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent e) {
									String TT = tableDSNV.getValueAt(row, 5).toString();
									
									tkBUS.MoKhoaTaiKhoan(tenTK);
									DefaultTableModel model = (DefaultTableModel) tableDSNV.getModel();
					                TT = "Đang hoạt động";
			    	                model.setValueAt(TT, row, 5);
			    	                JOptionPane.showMessageDialog(null, "Mở khóa tài khoản thành công");
									
								}
				            	
				            });
				            menuItemPhanQuyen.addActionListener(new ActionListener() {
				                public void actionPerformed(ActionEvent e) {
				                	String maNV = tableDSNV.getValueAt(row, 1).toString();
				                    String tenNV = tableDSNV.getValueAt(row, 2).toString();
				                    String tenTK = tableDSNV.getValueAt(row, 4).toString();
				                    
				                        JPanel panel = new JPanel();
				                        JTextField txtTK = new JTextField(tenTK);
				                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				                        JLabel lblTaiKhoan = new JLabel("Tài khoản: ");
				                        panel.add(lblTaiKhoan);
				                        panel.add(txtTK);				                        JLabel lblPhanQuyen = new JLabel("Phân quyền: ");
				                        panel.add(lblPhanQuyen);
				                        cbBoxViTri.setSelectedItem(pq);
				                        panel.add(cbBoxViTri);
				                        int options = JOptionPane.showOptionDialog(null, panel, "", JOptionPane.OK_CANCEL_OPTION, 
				                        											JOptionPane.PLAIN_MESSAGE, null, null, null);
				                        if (options == JOptionPane.OK_OPTION)
				                        {
				                        	String taiKhoan = tableDSNV.getValueAt(row, 4).toString();
				                        	String newTaiKhoan = txtTK.getText();
				                        	String matKhau = tkDTO.getMatKhau();
				                        	int tinhTrang = tkDTO.getTinhTrang();
				                        	
				                        	tkBUS.SuaTaiKhoan(taiKhoan, newTaiKhoan);
				                        	
					                    	DefaultTableModel model = (DefaultTableModel) tableDSNV.getModel();
					                    	model.setValueAt(newTaiKhoan, row, 4);
					                    	
					                    	int viTri = cbBoxViTri.getSelectedIndex();
					                        try {
												pqBUS.suaPQcuaNV(maNV, viTri);
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
					                        
					                        var dsnv = nvBUS.getAllDSNV();
					                        setTBDanhSachNV(dsnv);
				                        }
				                }
				            });
		            if(TT.equals("Đang hoạt động"))
		            {
		            	popupMenu.add(menuItemKhoaTaiKhoan);
			            popupMenu.add(menuItemPhanQuyen);
			            popupMenu.show(e.getComponent(), e.getX(), e.getY());
		            }
		            if(TT.equals("Đã khóa"))
		            {
			            popupMenu.add(menuItemMoKhoa);
			            popupMenu.show(e.getComponent(), e.getX(), e.getY());
		            }
		            if(TT.equals("Chưa thiết lập"))
		            {
				        JOptionPane.showMessageDialog(null, "Bạn phải tạo tài khoản trước");
		            }
				}
		        }
			     
			if(column == 8) {
				String TT = tableDSNV.getValueAt(row, 5).toString();
				if(TT.equals("Chưa thiết lập"))
	            {
			        JOptionPane.showMessageDialog(null, "Bạn phải tạo tài khoản trước");
			        return;
	            }
				int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này không?", "Xác nhận xóa tài khoản", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					String taiKhoan = tableDSNV.getValueAt(row, 4).toString();
					
					DefaultTableModel model = (DefaultTableModel) tableDSNV.getModel();
			        model.removeRow(row);
			        
			        tkBUS.XoaTaiKhoan(taiKhoan);
			        
			        JOptionPane.showMessageDialog(null, "Đã xóa tài khoản thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			}
			});
		
        
		scrollPaneDSNV = new JScrollPane(tableDSNV);
		scrollPaneDSNV.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDSNV.setForeground(new Color(255, 255, 255));
		scrollPaneDSNV.setBounds(10, 242, 661, 343);
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
		txtTimKiem.setBackground(new Color(255, 255, 255));
		txtTimKiem.setBounds(6, 29, 455, 35);
		txtTimKiem.setPreferredSize(new Dimension(68, 50));
		panelQLTK_TimKiem.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtTimKiem.getText();
				
				
				if(keyword.equals("")) {
					setTBDanhSachNV( nvBUS.getAllDSNV());
				}
				else {
					List<DSNhanVienDTO> dsnv = nvBUS.timKiemNV(keyword, keyword);
					setTBDanhSachNV(dsnv);
				}
				
			}
			});
		
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
		
		
		btnThemViTri = new JButton("Thêm Vị Trí");
		btnThemViTri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newViTri = JOptionPane.showInputDialog(null, "Nhập vị trí mới", "Thêm vị trí", JOptionPane.PLAIN_MESSAGE);
				if (newViTri != null && !newViTri.isEmpty()) {
					String maPQ = String.valueOf(cbBoxViTri.getItemCount());
		            pqBUS.ThemPhanQuyen(maPQ, newViTri);
		            cbBoxViTri.addItem(newViTri);
				}
				
			}
		});
		
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
		scrollPaneDSCN.setBackground(new Color(255, 255, 255));
		scrollPaneDSCN.setBounds(28, 243, 454, 342);
		panelQLQTC.add(scrollPaneDSCN);
		
		btnLuuTrangThai = new JButton("Lưu Trạng Thái");
		btnLuuTrangThai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu trạng thái không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
			    if (option == JOptionPane.YES_OPTION) {
			        luuTrangThaiChucNang();
			    } else {
			        // Xử lý trường hợp người dùng chọn "Không"
			    }
			}
		});
		btnLuuTrangThai.setSize(new Dimension(135, 35));
		btnLuuTrangThai.setForeground(new Color(255, 255, 255));
		btnLuuTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLuuTrangThai.setBorder(UIManager.getBorder("Button.border"));
		btnLuuTrangThai.setBackground(new Color(0, 128, 0));
		btnLuuTrangThai.setBounds(337, 595, 145, 35);
		panelQLQTC.add(btnLuuTrangThai);
		
		cbBoxViTri = new JComboBox<>();
		cbBoxViTri.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				resetChucNang();
			}
		});
		cbBoxViTri.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbBoxViTri.setBounds(10, 39, 277, 36);   
		panelDSVT.add(cbBoxViTri);
		
		ResultSet data = pqBUS.GetListPQ();
		try {
			while(data.next())
			{
				var tenPQ = data.getString("tenPQ");
				cbBoxViTri.addItem(tenPQ);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		var dsnv = nvBUS.getAllDSNV();
		
		resetChucNang();
		setTBDanhSachNV(dsnv);
	}
	class ButtonRenderer extends JButton implements TableCellRenderer {
	    public ButtonRenderer() {
	        setOpaque(true);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        if (value instanceof Component) {
	            return (Component) value;
	        }
	        return this;
	    }
	}
	private void setTBDanhSachNV(List<DSNhanVienDTO> dsnv) {
	    try {
	        int stt = 1;
	        DefaultTableModel model = (DefaultTableModel) tableDSNV.getModel();
	        model.setRowCount(0);
	        
	        for (DSNhanVienDTO nv : dsnv) {
	            String maNV = nv.getMaNV();
	            String tenNV = nv.getTenNV();
	            String taiKhoan = nv.getTaiKhoan();
	            String chucVu = nv.getchucVu();
	          
	            int tinhTrang = nv.gettinhTrang();
	   
	            String TT ;
	            if(tinhTrang == -1) {
	            	TT = "Chưa thiết lập";
	            }
	            else if (tinhTrang == 1) {
	            	TT = "Đã khóa";
	            }
	            else {
	            	TT = "Đang hoạt động";
	            }
	         

	            JButton btnTao = new JButton("");
	            
	           
	            btnTao.setBackground(new Color(255,255,255));
	            URL urlTao = PanelPosition.class.getResource("plus.png");
	    		btnTao.setIcon(new ImageIcon(urlTao));
	    		JButton btnSua = new JButton("");
	    		
	    		btnSua.addActionListener(new ActionListener() {
	    		    public void actionPerformed(ActionEvent e) {
	    		        System.out.println("Here");
	    		    }
	    		});
	    		btnSua.setBackground(new Color(255, 255, 255));
	            URL urlSua = PanelPosition.class.getResource("sua.png");
	    		btnSua.setIcon(new ImageIcon(urlSua));
	    		JButton btnXoa = new JButton("");
	    		btnXoa.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				
	    			}
	    		});
	            btnXoa.setBackground(new Color(255, 255, 255));
	            URL urlXoa = PanelPosition.class.getResource("xoa.png");
	            btnXoa.setIcon(new ImageIcon(urlXoa));
	            btnTao.setBorderPainted(false);
	            btnSua.setBorderPainted(false);
	            btnXoa.setBorderPainted(false);

	            Object[] rowData = new Object[] { stt++, maNV, tenNV, cbBoxViTri.getItemAt(Integer.valueOf(chucVu)), taiKhoan, TT, btnTao, btnSua, btnXoa };
	            model.addRow(rowData);
    
	        }
	        var taoCell = new ButtonRenderer();
	        taoCell.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				System.out.print("Here");
    			}
    		}
    		);
	        tableDSNV.getColumnModel().getColumn(6).setCellRenderer(taoCell);
            tableDSNV.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
            tableDSNV.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
            
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void LayTuDSNV() {
		String keyword = txtTimKiem.getText();
		List<DSNhanVienDTO> NVphuhop = nvBUS.timKiemNV(keyword, keyword);
		
	}


	private void resetChucNang()
	{
		ResultSet data = cnBUS.GetTBChucNang(String.valueOf(cbBoxViTri.getSelectedIndex()));
		try {
			var stt = 1;
			var model = (DefaultTableModel)tableDSCN.getModel();
			model.setRowCount(0);
			while(data.next())
			{
				var maCN = data.getString("maChucNang");
				var tenCN = data.getString("tenChucNang");
				boolean check = (data.getString("Action").equals("true"))? true : false;
				Object[] objects = new Object[4];
				objects[0] = stt;
				objects[1] = maCN;
				objects[2] = tenCN;
				objects[3] = check;
				model.addRow(objects);
				stt++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void luuTrangThaiChucNang() {
	    // Lấy số hàng của bảng
	    int rowCount = tableDSCN.getRowCount();

	    
	    // Lặp qua từng hàng của bảng
	    for (int i = 0; i < rowCount; i++) {
	        // Lấy giá trị của cột "Check" (cột 3) ở hàng i
	        boolean isChecked = (boolean) tableDSCN.getValueAt(i, 3);

        	 String maCN = (String) tableDSCN.getValueAt(i, 1); 
        	 String tenPQ = (String) cbBoxViTri.getSelectedItem();
        	 pqBUS.CapNhatTrangThai(tenPQ, maCN, isChecked);
	    }
	}
	
}	
	