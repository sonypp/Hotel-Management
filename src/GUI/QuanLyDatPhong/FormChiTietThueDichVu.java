package GUI.QuanLyDatPhong;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MouseInputAdapter;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import BUS.*;
import DTO.*;
import GUI.Home.HomeForm;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FormChiTietThueDichVu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textBox1;
	private JTable tbDichVu;
	private JTable tbDSDV;
	
	String maCTT;
	FormChiTietThue frmCTT;
	DichVuBUS dichvu = new DichVuBUS();
	private JComboBox cbbDichVu;
	
	public FormChiTietThueDichVu(String maCTT, FormChiTietThue frmCTT)
	{
		this();
		this.maCTT = maCTT;
		this.frmCTT = frmCTT;
		ListDichVu();
		ListDichVuDangThue();
		for (int i = 0; i < tbDichVu.getColumnCount(); i++) {
		    tbDichVu.getColumnModel().getColumn(i).setCellEditor(null);
		}
		tbDichVu.clearSelection();
		for (int i = 0; i < tbDSDV.getColumnCount(); i++) {
			tbDSDV.getColumnModel().getColumn(i).setCellEditor(null);
		}
		tbDSDV.clearSelection();
	}
	
	private void ListDichVuDangThue()
	{
		var model = (DefaultTableModel) tbDichVu.getModel();
		var cttdvList = new ChiTietThueDichVuBUS().GetListChiTietDichVu(maCTT);
		var stt = 1;
		for(var cttdv : cttdvList)
		{
			var listDV = dichvu.getListDichVu();
			String ten = "";
			for(var dv : listDV)
			{
				if(dv.getMaDV().equals(cttdv.getMaDV()))
				{
					ten = dv.getTenDV();
					break;
				}
			}
			model.addRow(new Object [] {stt++, cttdv.getMaDV(), ten, cttdv.getNgaySuDung(), cttdv.getSoLuong(), cttdv.getGiaDV(), cttdv.getSoLuong() * cttdv.getGiaDV()});
		}
	}
	
	private void ListDichVu() {
	    DefaultTableModel model = new DefaultTableModel();
	    tbDSDV.setModel(model);
	    model.addColumn("MÃ DỊCH VỤ");
	    model.addColumn("TÊN DỊCH VỤ");
	    model.addColumn("LOẠI DỊCH VỤ");
	    model.addColumn("GIÁ DỊCH VỤ");
	    model.addColumn("THÊM");

	    ResultSet rs = dichvu.getListDichVuCTPhieuThue(textBox1.getText().trim(), cbbDichVu.getSelectedItem().toString());
	    try {
	        while (rs.next()) {
	            String madv = rs.getString("madv");
	            String tendv = rs.getString("tendv");
	            String loaidv = rs.getString("loaidv");
	            String giadv = rs.getString("giadv");

	            model.addRow(new Object[]{madv, tendv, loaidv, giadv});
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private void ClickDichVu(String madv, String tendv, String giadv) {
	    int i = 0;
	    int j = 0;
	    for (int row = 0; row < tbDichVu.getRowCount(); row++) {
	        Object col1 = tbDichVu.getValueAt(row, 1);
	        if (col1 != null && col1.toString().equals(madv)) {
	            j = row;
	            i = 1;
	            break;
	        }
	    }

	    if (i == 1) {
	        int currentQuantity = Integer.parseInt(tbDichVu.getValueAt(j, 4).toString());
	        tbDichVu.setValueAt(currentQuantity + 1, j, 4);
	        BigDecimal dg = new BigDecimal(tbDichVu.getValueAt(j, 5).toString());
	        BigDecimal sl = new BigDecimal(tbDichVu.getValueAt(j, 4).toString());
	        tbDichVu.setValueAt(dg.multiply(sl), j, 6);
	    } else {
	        int STT = tbDichVu.getRowCount() + 1;
	        String ngaydv = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	        String soluong = "1";
	        ((DefaultTableModel) tbDichVu.getModel()).addRow(new Object[]{STT, madv, tendv, ngaydv, soluong, giadv, giadv});
	    }
	}

	private void buttonRounded2_Click() {
	    if (tbDichVu.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ thuê cho khách", "Thông báo", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    for (int i = 0; i < tbDichVu.getRowCount(); i++) {
	        String maDV = tbDichVu.getValueAt(i, 1).toString();
	        String ngaySuDung = tbDichVu.getValueAt(i, 3).toString().trim();
	        String soLuong = tbDichVu.getValueAt(i, 4).toString();
	        String giaDV = tbDichVu.getValueAt(i, 5).toString();
	        ChiTietThueDichVuBUS cttDV = new ChiTietThueDichVuBUS();
	        var cttdvList = cttDV.GetListChiTietDichVu(maCTT);
	        boolean flag = false;
	        System.out.println(maDV);
	        for(var cttdv : cttdvList)
	        {
	        	System.out.println(cttdv.getMaDV());
	        	if(cttdv.getMaDV().equals(maDV))
	        	{
	        		flag = true;
	        		break;
	        	}
	        }
	        if(flag)
	        {
	        	cttDV.SuaSoLuong(maCTT, maDV, ngaySuDung, soLuong);
	        }else
	        {
	        	try {
					cttDV.ThemCTTDV(maCTT, maDV, ngaySuDung, soLuong, giaDV);
				} catch (SQLServerException e) {
					cttDV.SuaSoLuong(maCTT, maDV, ngaySuDung, soLuong);
				}
	        }
	        JOptionPane.showMessageDialog(null, "Thêm dịch vụ thuê cho khách thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	        frmCTT.renderAll();
	    }
	}
	
	
	private void textBox1_MouseDown()
	{
	    if (textBox1.getText().trim().equals("Nhập Mã/Tên dịch vụ cần tìm"))
	    {
	        textBox1.setText("");
	    }
	}

	private void textBox1_MouseLeave()
	{
	    if (textBox1.getText().trim().length() == 0)
	    {
	        textBox1.setText("Nhập Mã/Tên dịch vụ cần tìm");
	    }
	}

	/**
	 * Create the panel.
	 */
	public FormChiTietThueDichVu() {
		setBounds(0, 0, 920, 815);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách dịch vụ hiện có");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 247, 34);
		add(lblNewLabel);
		
		textBox1 = new JTextField();
		textBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListDichVu();
			}
		});
		textBox1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textBox1_MouseDown();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textBox1_MouseLeave();
			}
		});
		textBox1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textBox1.setText("Nhập Mã/Tên dịch vụ cần tìm");
		textBox1.setToolTipText("");
		textBox1.setBounds(10, 52, 365, 34);
		add(textBox1);
		textBox1.setColumns(10);
		
		cbbDichVu = new JComboBox();
		cbbDichVu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ListDichVu();
			}
		});
		cbbDichVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbDichVu.setModel(new DefaultComboBoxModel(new String[] {"", "Ăn uống", "Chăm sóc sắc đẹp", "Tổ chức tiệc", "Giải trí"}));
		cbbDichVu.setSelectedIndex(0);
		cbbDichVu.setBounds(396, 52, 144, 34);
		add(cbbDichVu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 373, 900, 300);
		add(scrollPane);
		
		var tableRoomColumn = new String[]{
                "STT", "MÃ DỊCH VỤ", "TÊN DỊCH VỤ", "NGÀY SỬ DỤNG", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN", "SỬA", "XÓA"
        };
		String data[][] = {};
		var model = new DefaultTableModel(data, tableRoomColumn);
		tbDichVu = new JTable(model);
		tbDichVu.setRowHeight(30);
		
		// Customize the table header
		JTableHeader tableHeader = tbDichVu.getTableHeader();
		tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30));
		tableHeader.setBackground(Color.MAGENTA);
		tableHeader.setForeground(Color.WHITE);
		tableHeader.setReorderingAllowed(false);
		
		// Set font style and alignment for column headers
		Font headerFont = new Font("Arial", Font.BOLD, 14);
		tableHeader.setFont(headerFont);
		DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tbDichVu.getTableHeader().getDefaultRenderer();
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		headerRenderer.setVerticalAlignment(SwingConstants.CENTER);
		headerRenderer.setText("<html><font color='white'>Name</font></html>"); // Change 'Name' to your column header text
		
		// Customize other properties of the table
		tbDichVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tbDichVu.setShowGrid(false);
		tbDichVu.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(tbDichVu);

        tbDichVu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int row = tbDichVu.rowAtPoint(e.getPoint());
                int column = tbDichVu.columnAtPoint(e.getPoint());

                if (row >= 0 && column >= 0) {
                    if (column == 7) {
                        formInput frmInput = new formInput();
                        frmInput.setVisible(true);
                        new Timer(100, new ActionListener() {
                    		@Override
                    		public void actionPerformed(ActionEvent e) {
                    			if (frmInput.getDialogResult() == JOptionPane.YES_OPTION) {
                    				System.out.print("Here");
                                    int soLuong = frmInput.getNumber();
                                    var dg = Integer.valueOf(tbDichVu.getValueAt(row, 5).toString());
                                    tbDichVu.setValueAt(soLuong, row, 4);
                                    tbDichVu.setValueAt(dg * soLuong, row, 6);
                                    JOptionPane.showMessageDialog(null, "Sửa dịch vụ này thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                    var source = (Timer)e.getSource();
            	                    source.stop();
                                }
                    		}
                        }).start();
                    } else if (column == 8) {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Có chắc xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            int[] selectedRows = tbDichVu.getSelectedRows();
                            for (int i = selectedRows.length - 1; i >= 0; i--) {
                                ((DefaultTableModel) tbDichVu.getModel()).removeRow(selectedRows[i]);
                            }
                            for (int i = 0; i < tbDichVu.getRowCount(); i++) {
                                tbDichVu.setValueAt(i + 1, i, 0);
                            }
                        }
                    }
                }
            }
        });


		
		JButton btnLuuTrangThai = new JButton("Lưu trạng thái");
		btnLuuTrangThai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonRounded2_Click();
			}
		});
		btnLuuTrangThai.setForeground(new Color(255, 255, 255));
		btnLuuTrangThai.setBackground(new Color(0, 128, 0));
		btnLuuTrangThai.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLuuTrangThai.setBounds(630, 684, 223, 40);
		add(btnLuuTrangThai);
		
		JLabel lblNewLabel_1 = new JLabel("Danh sách dịch vụ đang xử lý");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 329, 316, 34);
		add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 96, 900, 223);
		add(scrollPane_1);

		var tableRoomColumn1 = new String[]{
                "TÊN DỊCH VỤ", "LOẠI DỊCH VỤ", "GIÁ", "THÊM"
        };
        var model1 = new DefaultTableModel(data, tableRoomColumn1);
        tbDSDV = new JTable(model1);
        tbDSDV.setRowHeight(30);
        tbDSDV.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbDSDV.rowAtPoint(e.getPoint());
                int col = tbDSDV.columnAtPoint(e.getPoint());
                
                if (row >= 0 && col >= 0) {
                    if (col == 4) {
                    	String ten = tbDSDV.getValueAt(row, 1).toString();
                    	var dvBUS = new DichVuBUS();
                    	var dv = dvBUS.getListDichVu().stream()
                    		.filter(tmp -> tmp.getTenDV().equals(ten))
                    		.findFirst().orElse(null);
                        ClickDichVu(dv.getMaDV(), dv.getTenDV(), String.valueOf(dv.getGiaDV()));
                    }
                }
            }
        });
        
        // Customize the table header
        JTableHeader tableHeader1 = tbDSDV.getTableHeader();
        tableHeader1.setPreferredSize(new Dimension(tableHeader1.getWidth(), 30));
        tableHeader1.setBackground(Color.MAGENTA);
        tableHeader1.setForeground(Color.WHITE);
        tableHeader1.setReorderingAllowed(false);
        
        // Set font style and alignment for column headers
        Font headerFont1 = new Font("Arial", Font.BOLD, 14);
        tableHeader1.setFont(headerFont1);
        DefaultTableCellRenderer headerRenderer1 = (DefaultTableCellRenderer) tbDSDV.getTableHeader().getDefaultRenderer();
        headerRenderer1.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer1.setVerticalAlignment(SwingConstants.CENTER);
        headerRenderer1.setText("<html><font color='white'>Name</font></html>"); // Change 'Name' to your column header text

        // Customize other properties of the table
        tbDSDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tbDSDV.setShowGrid(false);
        tbDSDV.setDefaultEditor(Object.class, null);
        scrollPane_1.setViewportView(tbDSDV);
	}
}
