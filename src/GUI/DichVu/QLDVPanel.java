package GUI.DichVu;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.w3c.dom.events.EventTarget;

import BUS.DichVuBUS;
import DTO.DichVuDTO;

import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class QLDVPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfMaDV, tfTenDV, tf_frmMaDV, tf_frmTenDV, tf_frmGiaDV;
	public static int[] lengthColumn = {120,300,220,250};
	private JTable tbDV;
	private DichVuBUS dichVuBUS;
	private JComboBox cbbGiaDV, cbb_frmLoaiDV;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLuu;
	private JButton btnHuy;
	private JComboBox cbbLoaiDV;
	private JButton btnTimkiem;
	private JButton btnLamMoi;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public QLDVPanel() {
		dichVuBUS = new DichVuBUS();
		setBackground(new Color(245, 245, 245));
		setBounds(0, 0, 1251, 735);
		setLayout(null);
		
		JLabel lbQLDV = new JLabel("QUẢN LÍ DỊCH VỤ");
		lbQLDV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbQLDV.setForeground(new Color(0, 128, 255));
		lbQLDV.setBounds(10, 10, 299, 28);
		add(lbQLDV);
		
		JLabel lbQLDV_1 = new JLabel("Vui lòng bấm vào danh sách dịch vụ để chỉnh sửa");
		lbQLDV_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbQLDV_1.setBounds(10, 38, 299, 20);
		add(lbQLDV_1);
		
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBackground(new Color(255, 255, 255));
		pnTimKiem.setBounds(10, 68, 1231, 111);
		add(pnTimKiem);
		pnTimKiem.setLayout(null);
		
		DefaultComboBoxModel<String> model_loaiDichVu = new DefaultComboBoxModel();
		model_loaiDichVu.addElement("");
		model_loaiDichVu.addElement("Ăn uống");
		model_loaiDichVu.addElement("Chăm sóc sắc đẹp");
		model_loaiDichVu.addElement("Tổ chức tiệc");
		model_loaiDichVu.addElement("Giải trí");
		
		DefaultComboBoxModel model_giaDichVu = new DefaultComboBoxModel();
		model_giaDichVu.addElement("");
		model_giaDichVu.addElement("Dưới 50.000 VND");
		model_giaDichVu.addElement("Từ 50.000 VND đến 100.000 VND");
		model_giaDichVu.addElement("Từ 100.000 VND đến 200.000 VND");
		model_giaDichVu.addElement("Từ 200.000 VND đến 500.000 VND");
		model_giaDichVu.addElement("Từ 500.000 VND đến 1.000.000 VND");
		model_giaDichVu.addElement("Trên 1.000.000 VND");
		
		btnTimkiem = new JButton("Tìm kiếm");
		btnTimkiem.setForeground(new Color(255, 255, 255));
		btnTimkiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTimkiem.setBackground(new Color(0, 128, 128));
		btnTimkiem.setBounds(906, 59, 152, 39);
		pnTimKiem.add(btnTimkiem);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setForeground(new Color(255, 255, 255));
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoi.setBackground(new Color(147, 112, 219));
		btnLamMoi.setBounds(1068, 59, 152, 39);
		pnTimKiem.add(btnLamMoi);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 8, 1220, 39);
		pnTimKiem.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		JLabel lbMaDV = new JLabel("Mã dịch vụ:");
		panel.add(lbMaDV);
		lbMaDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfMaDV = new JTextField();
		panel.add(tfMaDV);
		tfMaDV.setColumns(10);
		
		JLabel lbTenDV = new JLabel("Tên dịch vụ:");
		panel.add(lbTenDV);
		lbTenDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfTenDV = new JTextField();
		panel.add(tfTenDV);
		tfTenDV.setColumns(10);
		
		JLabel lbLoaiDV = new JLabel("Loại dịch vụ:");
		panel.add(lbLoaiDV);
		lbLoaiDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
				cbbLoaiDV = new JComboBox(model_loaiDichVu);
				cbbLoaiDV.setPreferredSize(new Dimension(150, 30));
				panel.add(cbbLoaiDV);
				cbbLoaiDV.setSelectedIndex(0);
				
				JLabel lbGiaDV = new JLabel("Giá dịch vụ:");
				panel.add(lbGiaDV);
				lbGiaDV.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
				cbbGiaDV = new JComboBox(model_giaDichVu);
				cbbGiaDV.setPreferredSize(new Dimension(260, 30));
				panel.add(cbbGiaDV);
		
		JPanel pnTableDV = new JPanel();
		pnTableDV.setBackground(new Color(255, 255, 255));
		pnTableDV.setBounds(10, 189, 814, 636);
		add(pnTableDV);
		pnTableDV.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("DANH SÁCH DỊCH VỤ");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(6, 9, 288, 37);
		pnTableDV.add(lblNewLabel_1);
		
		JScrollPane scrptbDV = new JScrollPane();
		scrptbDV.setBounds(6, 47, 798, 592);
		pnTableDV.add(scrptbDV);
		
		tbDV = new JTable() {
			@Override
			protected JTableHeader createDefaultTableHeader() {
				return new JTableHeader(columnModel) {
					@Override
					public void setResizingColumn(TableColumn resizingColumn) {
						// Disable resizing column
					}
				};
			}
		};
		tbDV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbDV.setFont(UIManager.getFont("Table.font"));
		loadTableDichVu();
		
		// Lấy đối tượng đầu tiên trong bảng, đó là tiêu đề
        JTableHeader header = tbDV.getTableHeader();

        // Đặt màu nền cho tiêu đề bảng
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setBackground(Color.cyan); // Màu nền tiêu đề bảng
        header.setDefaultRenderer(headerRenderer);

		scrptbDV.setViewportView(tbDV);
		
		JPanel pnSuaThongTin = new JPanel();
		pnSuaThongTin.setBackground(new Color(255, 255, 255));
		pnSuaThongTin.setForeground(new Color(255, 255, 255));
		pnSuaThongTin.setBounds(834, 189, 407, 636);
		add(pnSuaThongTin);
		SpringLayout sl_pnSuaThongTin = new SpringLayout();
		pnSuaThongTin.setLayout(sl_pnSuaThongTin);
		
		JLabel lbMaDV_1 = new JLabel("Mã dịch vụ:");
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, lbMaDV_1, 43, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, lbMaDV_1, 10, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, lbMaDV_1, 66, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, lbMaDV_1, 83, SpringLayout.WEST, pnSuaThongTin);
		lbMaDV_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnSuaThongTin.add(lbMaDV_1);
		
		tf_frmMaDV = new JTextField();
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, tf_frmMaDV, 76, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, tf_frmMaDV, 10, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, tf_frmMaDV, 106, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, tf_frmMaDV, 397, SpringLayout.WEST, pnSuaThongTin);
		pnSuaThongTin.add(tf_frmMaDV);
		tf_frmMaDV.setEditable(false);
		tf_frmMaDV.setColumns(10);
		
		JLabel lbTenDV_1 = new JLabel("Tên dịch vụ");
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, lbTenDV_1, 122, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, lbTenDV_1, 10, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, lbTenDV_1, 145, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, lbTenDV_1, 83, SpringLayout.WEST, pnSuaThongTin);
		lbTenDV_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnSuaThongTin.add(lbTenDV_1);
		
		tf_frmTenDV = new JTextField();
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, tf_frmTenDV, 155, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, tf_frmTenDV, 10, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, tf_frmTenDV, 185, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, tf_frmTenDV, 397, SpringLayout.WEST, pnSuaThongTin);
		tf_frmTenDV.setColumns(10);
		pnSuaThongTin.add(tf_frmTenDV);
		
		JLabel lbLoaiDV_1 = new JLabel("Loại dịch vụ:");
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, lbLoaiDV_1, 210, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, lbLoaiDV_1, 10, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, lbLoaiDV_1, 233, SpringLayout.NORTH, pnSuaThongTin);
		lbLoaiDV_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnSuaThongTin.add(lbLoaiDV_1);
		
		JLabel lbGiaDV_1 = new JLabel("Giá dịch vụ:");
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, lbGiaDV_1, 305, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, lbGiaDV_1, 10, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, lbGiaDV_1, 328, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, lbGiaDV_1, 83, SpringLayout.WEST, pnSuaThongTin);
		lbGiaDV_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnSuaThongTin.add(lbGiaDV_1);
		
		DefaultComboBoxModel model_frmLoaiDV = new DefaultComboBoxModel();
		model_frmLoaiDV.addElement("");
		model_frmLoaiDV.addElement("Ăn uống");
		model_frmLoaiDV.addElement("Chăm sóc sắc đẹp");
		model_frmLoaiDV.addElement("Tổ chức tiệc");
		model_frmLoaiDV.addElement("Giải trí");
		
		cbb_frmLoaiDV = new JComboBox(model_frmLoaiDV);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, cbb_frmLoaiDV, 282, SpringLayout.NORTH, pnSuaThongTin);
		cbb_frmLoaiDV.setFont(new Font("Arial", Font.BOLD, 12));
		cbb_frmLoaiDV.setEditable(false);
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, cbb_frmLoaiDV, 252, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, cbb_frmLoaiDV, 10, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, cbb_frmLoaiDV, 397, SpringLayout.WEST, pnSuaThongTin);
		pnSuaThongTin.add(cbb_frmLoaiDV);
		
		btnThem = new JButton("Thêm");
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, btnThem, 392, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, btnThem, 10, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, btnThem, 436, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, btnThem, 133, SpringLayout.WEST, pnSuaThongTin);
		btnThem.setBackground(new Color(0, 100, 0));
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnSuaThongTin.add(btnThem);
		
		btnSua = new JButton("Sửa");
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, btnSua, 392, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, btnSua, 143, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, btnSua, 436, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, btnSua, 266, SpringLayout.WEST, pnSuaThongTin);
		btnSua.setBackground(new Color(255, 215, 0));
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnSuaThongTin.add(btnSua);
		
		btnXoa = new JButton("Xóa");
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, btnXoa, 392, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, btnXoa, 274, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, btnXoa, 436, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, btnXoa, 397, SpringLayout.WEST, pnSuaThongTin);
		btnXoa.setBackground(new Color(139, 0, 0));
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnSuaThongTin.add(btnXoa);
		
		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, btnLuu, 446, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, btnLuu, 10, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, btnLuu, 490, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, btnLuu, 133, SpringLayout.WEST, pnSuaThongTin);
		btnLuu.setBackground(new Color(192, 192, 192));
		btnLuu.setForeground(new Color(255, 255, 255));
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnSuaThongTin.add(btnLuu);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setEnabled(false);
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, btnHuy, 446, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, btnHuy, 143, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, btnHuy, 490, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, btnHuy, 266, SpringLayout.WEST, pnSuaThongTin);
		btnHuy.setBackground(new Color(128, 128, 128));
		btnHuy.setForeground(new Color(255, 255, 255));
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 12));
		pnSuaThongTin.add(btnHuy);
		
		JLabel lblNewLabel_2 = new JLabel("THÔNG TIN CHI TIẾT");
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 10, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, lblNewLabel_2, 9, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 47, SpringLayout.NORTH, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, lblNewLabel_2, 305, SpringLayout.WEST, pnSuaThongTin);
		pnSuaThongTin.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		tf_frmGiaDV = new JTextField();
		sl_pnSuaThongTin.putConstraint(SpringLayout.NORTH, tf_frmGiaDV, 6, SpringLayout.SOUTH, lbGiaDV_1);
		sl_pnSuaThongTin.putConstraint(SpringLayout.WEST, tf_frmGiaDV, 10, SpringLayout.WEST, pnSuaThongTin);
		sl_pnSuaThongTin.putConstraint(SpringLayout.SOUTH, tf_frmGiaDV, 36, SpringLayout.SOUTH, lbGiaDV_1);
		sl_pnSuaThongTin.putConstraint(SpringLayout.EAST, tf_frmGiaDV, -10, SpringLayout.EAST, pnSuaThongTin);
		tf_frmGiaDV.setColumns(10);
		pnSuaThongTin.add(tf_frmGiaDV);
		
		dichVuFunction();
		
	}
	
	public void initComponent() {}
	
	public void dichVuFunction() {
		addDichVu();
		deleteDichVu();
		updateDichVu();
		filterDichVu();
		resetFormDV();
		resetFilterDV();
	}
	
	public void addDichVu() {
		btnThem.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				String maDV = dichVuBUS.initMaDV();
				String tenDV = tf_frmTenDV.getText().trim();
				String loaiDV = (String) cbb_frmLoaiDV.getItemAt(cbb_frmLoaiDV.getSelectedIndex());		
				String giaDVStr = tf_frmGiaDV.getText().trim();

				int giaDV;
				try {
					giaDV = Integer.parseInt(giaDVStr);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(QLDVPanel.this, "Sai định dạng giá dịch vụ!");
					return;
				}
				tf_frmMaDV.setText(maDV);
				String result =  dichVuBUS.addDichVu(maDV, tenDV, loaiDV, giaDV);
				JOptionPane.showMessageDialog(QLDVPanel.this, result);;
				
				String success = "Thêm dịch vụ thành công !"; 
				if(success.equals(result)) {
					resetForm();
				}
				loadTableDichVu();
			}
		});
	}
	
	public void deleteDichVu() {
	    btnXoa.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            int selectedRow = tbDV.getSelectedRow();
	            if(selectedRow == -1) {
	                JOptionPane.showMessageDialog(QLDVPanel.this, "Chưa có dòng nào được chọn !");
	            } else {
	                int confirm = JOptionPane.showConfirmDialog(QLDVPanel.this, "Xác nhận xóa dịch vụ ?", null, JOptionPane.YES_NO_OPTION);
	                if (confirm == JOptionPane.YES_OPTION) {
	                    String maDV = (String) tbDV.getValueAt(selectedRow, 0);
	                    String result = dichVuBUS.deleteDichVu(maDV);
	                    JOptionPane.showMessageDialog(QLDVPanel.this, result);
	                    
	                    tf_frmMaDV.setText("");
						tf_frmTenDV.setText("");
						cbb_frmLoaiDV.setSelectedIndex(0);
						tf_frmGiaDV.setText(String.valueOf(""));

						btnThem.setEnabled(true);
						btnLuu.setEnabled(false);
						btnHuy.setEnabled(false);
	                    
	                    loadTableDichVu();
	                }
	            }
	        }
	    });
	}

	
	public void updateDichVu() {
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int selectedRow = tbDV.getSelectedRow();
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(QLDVPanel.this, "Chưa có dòng nào được chọn !");
				} else {
					String maDV = (String) tbDV.getValueAt(selectedRow, 0);
					String tenDV = (String) tbDV.getValueAt(selectedRow, 1);
					String loaiDV = (String) tbDV.getValueAt(selectedRow, 2);
					int giaDV = (int) tbDV.getValueAt(selectedRow, 3);
			
					tf_frmMaDV.setText(maDV);
					tf_frmTenDV.setText(tenDV);
					cbb_frmLoaiDV.setSelectedItem(loaiDV);
					tf_frmGiaDV.setText(String.valueOf(giaDV));

					btnThem.setEnabled(false);
					btnLuu.setEnabled(true);
					btnHuy.setEnabled(true);
				}
			}
		});
		
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub	
				String maDV_update = tf_frmMaDV.getText();
				String tenDV_update = tf_frmTenDV.getText();
				String loaiDV_update = (String) cbb_frmLoaiDV.getSelectedItem();
				String giaDVStr_update = tf_frmGiaDV.getText();
				
				
				int giaDV_update;
				try {
					giaDV_update = Integer.parseInt(giaDVStr_update);
				} catch (NumberFormatException e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(QLDVPanel.this, "Gía dịch vụ phải là số nguyên!");
					return;
				}
				
				String result = dichVuBUS.updateDichVu(maDV_update, tenDV_update, loaiDV_update, giaDV_update);
				JOptionPane.showMessageDialog(QLDVPanel.this, result);
				String success = "Sửa dịch vụ thành công !";
				if(success.equals(result)) {
					resetForm();
				}
				
				loadTableDichVu();
				btnThem.setEnabled(true);
				btnLuu.setEnabled(false);
				btnHuy.setEnabled(false);
			}
		});
	}
	
	public void loadTableDichVu() {
		List<DichVuDTO> listDV = new ArrayList<DichVuDTO>();
		listDV = dichVuBUS.getListDichVu();
		
		DefaultTableModel dtm = new DefaultTableModel(
				new Object[][] {},
				new String[] {"Mã dịch vụ","Tên dịch vụ","Loại dịch vụ","Giá dịch vụ"}) {
			 @Override
	            public boolean isCellEditable(int row, int column) {
	                // Không cho phép chỉnh sửa bất kỳ ô nào
	                return false;
	            }
		};
	
		tbDV.setModel(dtm);
		TableColumnModel columnModelDSNV = tbDV.getColumnModel();
		for (int i = 0; i < lengthColumn.length; i++) {
			columnModelDSNV.getColumn(i).setPreferredWidth(lengthColumn[i]);
		}
		
		for(int i=0; i<listDV.size(); i++) {
			DichVuDTO dichVu = listDV.get(i);
			Object[] row = {dichVu.getMaDV(), 
							dichVu.getTenDV(), 
							dichVu.getLoaiDV(), 
							dichVu.getGiaDV()
							};
			dtm.addRow(row);
		}
		
		tbDV.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            int selectedRow = tbDV.getSelectedRow();
	            if (selectedRow != -1) {
	                // Lấy dữ liệu từ dòng được chọn
	                String maDV = (String) tbDV.getValueAt(selectedRow, 0);
	                String tenDV = (String) tbDV.getValueAt(selectedRow, 1);
	                String loaiDV = (String) tbDV.getValueAt(selectedRow, 2);
	                int giaDV = (int) tbDV.getValueAt(selectedRow, 3);

	                // Đổ dữ liệu vào các JTextField tương ứng
	                tf_frmMaDV.setText(maDV);
	                tf_frmTenDV.setText(tenDV);
	                cbb_frmLoaiDV.setSelectedItem(loaiDV);
	                tf_frmGiaDV.setText(String.valueOf(giaDV));
	                btnThem.setEnabled(false);
	            }
	        }
	    });
	}
	
	public void filterDichVu() {
		btnTimkiem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<DichVuDTO> listDV = new ArrayList<DichVuDTO>();
				String filterMaDV = tfMaDV.getText();
				String filterTenDV = tfTenDV.getText();
				String filterLoaiDV = (String) cbbLoaiDV.getSelectedItem();
				String filterGiaDV =  (String) cbbGiaDV.getSelectedItem();
				
				listDV = dichVuBUS.filterDichVu(filterMaDV, filterTenDV, filterLoaiDV, filterGiaDV);
				DefaultTableModel dtm = new DefaultTableModel(
						new Object[][] {},
						new String[] {"Mã dịch vụ","Tên dịch vụ","Loại dịch vụ","Giá dịch vụ"});
				
				tbDV.setModel(dtm);
				for(int i=0; i<listDV.size(); i++) {
					DichVuDTO dichVu = listDV.get(i);
					Object[] row = {dichVu.getMaDV(), 
									dichVu.getTenDV(), 
									dichVu.getLoaiDV(), 
									dichVu.getGiaDV()
									};
					dtm.addRow(row);
				}
				
				TableColumnModel columnModelDSNV = tbDV.getColumnModel();
				for (int i = 0; i < lengthColumn.length; i++) {
					columnModelDSNV.getColumn(i).setPreferredWidth(lengthColumn[i]);
				}
			}
		});	
	}
	
	public void resetForm() {
	    tf_frmMaDV.setText("");
	    tf_frmTenDV.setText("");
	    tf_frmGiaDV.setText("");
	    cbb_frmLoaiDV.setSelectedIndex(0);
	}

	public void resetFilter() {
	    tfMaDV.setText("");
	    tfTenDV.setText("");
	    cbbLoaiDV.setSelectedIndex(0);
	    cbbGiaDV.setSelectedIndex(0);
	}

	public void resetFormDV() {
	    btnHuy.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            resetForm();
	            btnHuy.setEnabled(false);
	            btnThem.setEnabled(true);
	            btnLuu.setEnabled(false);
	            loadTableDichVu();
	        }
	    });
	}

	public void resetFilterDV() {
	    btnLamMoi.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            resetFilter();
	            resetForm();
	            btnThem.setEnabled(true);
	            btnLuu.setEnabled(false);
	            btnHuy.setEnabled(false);
	            loadTableDichVu();
	        }
	    });
	}

	
	public static void main(String[] args) {
		 
	}

	
	
	
}
