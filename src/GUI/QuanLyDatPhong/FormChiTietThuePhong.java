package GUI.QuanLyDatPhong;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import BUS.*;
import DTO.*;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormChiTietThuePhong extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tbRoom;
	
	DefaultTableModel dt;
	ChiTietThuePhongBUS cttp = new ChiTietThuePhongBUS();
	String maCTT;
	FormChiTietThue frmCTT;
	
	private void btnSelectRoom_Click() {
		FormSelectRoom frm = new FormSelectRoom(dt);
    	var popupFrame = new JFrame("Thêm phòng");
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popupFrame.setSize(1260, 900);
		popupFrame.getContentPane().add(frm);
		popupFrame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
    	popupFrame.setVisible(true);
    	frm.setVisible(true);
    	var timer = new Timer(100, new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if (frm.DialogResult == JOptionPane.OK_OPTION) {
    		        PhongDTO phong = (PhongDTO) frm.arr[0];
    		        try {
    		            dt.addRow(new Object[]{
    		                    phong.getMaP(),
    		                    phong.getTenP(),
    		                    "Đang xử lý",
    		                    frm.arr[1],
    		                    Date.parse(frm.arr[2].toString()),
    		                    Date.parse(frm.arr[3].toString()),
    		                    "",
    		                    Integer.parseInt(frm.arr[4].toString())
    		            });
    		            popupFrame.dispose();
    					((javax.swing.Timer) e.getSource()).stop();
    		        } catch (Exception ex) {
    		            dt.addRow(new Object[]{
    		                    phong.getMaP(),
    		                    phong.getTenP(),
    		                    "Đang xử lý",
    		                    frm.arr[1],
    		                    Date.parse(frm.arr[2].toString()),
    		                    frm.arr[3].toString(),
    		                    "",
    		                    Integer.parseInt(frm.arr[4].toString())
    		            });
    		            popupFrame.dispose();
    					((javax.swing.Timer) e.getSource()).stop();
    		        }
    		        HienThiDanhSachPhongThue();
    		    }
    		}
    	});
    	timer.start();
	}

	private void HienThiDanhSachPhongThue() {
	    DefaultTableModel tbRoomModel = (DefaultTableModel) tbRoom.getModel();
	    tbRoomModel.setRowCount(0);
	    int stt = 0;
	    int total = 0;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    for (int i = 0; i < dt.getRowCount(); i++) {
	        try {
	            tbRoomModel.addRow(new Object[]{
	                    ++stt,
	                    dt.getValueAt(i, 0),
	                    dt.getValueAt(i, 1),
	                    dt.getValueAt(i, 2),
	                    dt.getValueAt(i, 3),
	                    dateFormat.format(Date.parse(dt.getValueAt(i, 4).toString())).toString(),
	                    dateFormat.format(Date.parse(dt.getValueAt(i, 5).toString())).toString(),
	                    dt.getValueAt(i, 6),
	                    Integer.parseInt(dt.getValueAt(i, 7).toString())
	            });
	        } catch (Exception ex) {
	            tbRoomModel.addRow(new Object[]{
	                    stt,
	                    dt.getValueAt(i, 0),
	                    dt.getValueAt(i, 1),
	                    dt.getValueAt(i, 2),
	                    dt.getValueAt(i, 3),
	                    dateFormat.format(Date.parse(dt.getValueAt(i, 4).toString())),
	                    dt.getValueAt(i, 5),
	                    dt.getValueAt(i, 6),
	                    "Chưa tính"
	            });
	        }
	    }
	    for (int i = 0; i < dt.getRowCount(); i++) {
	        if (!dt.getValueAt(i, 3).toString().toUpperCase().equals("KHÁC")) {
	            total += Integer.parseInt(dt.getValueAt(i, 7).toString());
	        }
	    }
	    tbRoom.clearSelection();
	}
	
	private void btnSave_Click() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < dt.getRowCount(); i++) {
            String rentalType = dt.getValueAt(i, 3).toString().toUpperCase();
            String startDate = dt.getValueAt(i, 4).toString().trim();
            String endDate = dt.getValueAt(i, 5).toString().trim();
            String cost = dt.getValueAt(i, 7).toString();
            String rentalOption = "2"; // Default value for "OTHER"
            if (rentalType.equals("THEO NGÀY")) {
                rentalOption = "0";
            } else if (rentalType.equals("THEO GIỜ")) {
                rentalOption = "1";
            }
            try {
                Date parsedStartDate = dateFormat.parse(startDate);
                Date parsedEndDate = dateFormat.parse(endDate);
                cttp.InsertCTTP(
                    rentalOption.equals("0") || rentalOption.equals("1"),
                    maCTT.trim(),
                    dt.getValueAt(i, 0).toString().trim(),
                    dateFormat.format(parsedStartDate),
                    dateFormat.format(parsedEndDate),
                    rentalOption,
                    cost
                );
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(null, "Thêm phòng mới cho phiếu thuê này thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        frmCTT.renderAll();
    }

	/**
	 * Create the panel.
	 */
	public FormChiTietThuePhong(String maCTT, FormChiTietThue frmCTT) {
		setBounds(0, 0, 920, 815);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách phòng đang xử lý");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBackground(new Color(128, 128, 128));
		lblNewLabel.setBounds(0, 0, 910, 47);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 71, 910, 596);
		add(scrollPane);
		
        var tableRoomColumn = new String[]{
                "STT", "MÃ PHÒNG", "TÊN PHÒNG", "TÌNH TRẠNG", "LOẠI HÌNH THUÊ", "NGÀY THUÊ", "NGÀY TRẢ", "NGÀY CHECKOUT", "GIÁ PHÒNG", "XÓA"
        };
        String data[][] = {};
        var model = new DefaultTableModel(data, tableRoomColumn);
        tbRoom = new JTable(model);
        tbRoom.setRowHeight(30);
        
        // Customize the table header
        JTableHeader tableHeader = tbRoom.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30));
        tableHeader.setBackground(Color.MAGENTA);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setReorderingAllowed(false);
        
        // Set font style and alignment for column headers
        Font headerFont = new Font("Arial", Font.BOLD, 14);
        tableHeader.setFont(headerFont);
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tbRoom.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setVerticalAlignment(SwingConstants.CENTER);
        headerRenderer.setText("<html><font color='white'>Name</font></html>"); // Change 'Name' to your column header text

        // Customize other properties of the table
        tbRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tbRoom.setShowGrid(false);
        tbRoom.setDefaultEditor(Object.class, null);
        tbRoom.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int rowIndex = tbRoom.getSelectedRow();
                    int columnIndex = tbRoom.getSelectedColumn();
                    if (columnIndex == 9 && rowIndex >= 0) {
                        int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phòng thuê này", "Cảnh báo", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION) {
                            DefaultTableModel model = (DefaultTableModel) tbRoom.getModel();
                            model.removeRow(rowIndex);
                            HienThiDanhSachPhongThue();
                        } else {
                            tbRoom.clearSelection();
                        }
                    }
                }
            }
        });
        
        scrollPane.setViewportView(tbRoom);
		
		JButton btnChonPhong = new JButton("Chọn phòng");
		btnChonPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnSelectRoom_Click();
			}
		});
		btnChonPhong.setBackground(new Color(0, 255, 0));
		btnChonPhong.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnChonPhong.setBounds(480, 689, 182, 41);
		add(btnChonPhong);
		
		JButton btnLuuTrangThai = new JButton("Lưu trạng thái");
		btnLuuTrangThai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnSave_Click();
			}
		});
		btnLuuTrangThai.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLuuTrangThai.setBackground(new Color(255, 128, 0));
		btnLuuTrangThai.setBounds(685, 689, 182, 41);
		add(btnLuuTrangThai);
		
		this.frmCTT = frmCTT;
		dt = new DefaultTableModel();
		dt.addColumn("MaP");
		dt.addColumn("TenP");
		dt.addColumn("TinhTrang");
		dt.addColumn("LoaiHinhThue");
		dt.addColumn("NgayThue");
		dt.addColumn("NgayTra");
		dt.addColumn("NgayCheckOut");
		dt.addColumn("GiaThuc");
		this.maCTT = maCTT;
		HienThiDanhSachPhongThue();
	}

}
