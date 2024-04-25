package GUI.TienIch;


import BUS.TienIchBUS;
import DTO.TienIchDTO;

import java.awt.Color;

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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class QLTienIch extends JPanel {

	private static final long serialVersionUID = 1L;
	private static int[] lengthColumn = {50,150,680};
	private JTextField tfTimKiem;
	private JTextField tfTenTienIch;
	private JTable tbTienIch;
	private JButton btnTimKiem;
	private JButton btnLuuTienIch;
	private JButton btnHuyTienIch;
	private JButton btnDeleteTienIch;
	private JButton btnSuaTienIch;
	private JButton btnAddTienIch;
	private JTextField tfMaTienIch;
	private DefaultTableModel modeltbTienIch;
	private TienIchBUS busTienIch;
	private JLabel lblKetQuaTimKiem;
	private JLabel lblChucNangHienTai;
	private JLabel errTenTI;
	/**
	 * -1: không có chức năng
	 * 0: xem
	 * 1: thêm
	 * 2: sửa
	 * 3: xóa
	 * */
	private int chucNangHienTai = 0;
	/**
	 * Create the panel.
	 */
	public QLTienIch() {
		setBackground(new Color(245, 245, 245));
		setBounds(0, 0, 1251, 835);
		setLayout(null);
		
		JLabel lbDanhSachTienIch = new JLabel("DANH SÁCH TIỆN ÍCH");
		lbDanhSachTienIch.setBackground(new Color(135, 206, 235));
		lbDanhSachTienIch.setForeground(new Color(65, 105, 225));
		lbDanhSachTienIch.setFont(new Font("Tahoma", Font.BOLD, 22));
		lbDanhSachTienIch.setBounds(41, 37, 365, 42);
		add(lbDanhSachTienIch);
		
		JPanel panelDSTI = new JPanel();
		panelDSTI.setBackground(new Color(255, 255, 255));
		panelDSTI.setBounds(41, 91, 778, 693);
		add(panelDSTI);
		panelDSTI.setLayout(null);
		
		JScrollPane scrpTbTienIch = new JScrollPane();
		scrpTbTienIch.setBounds(46, 86, 685, 550);
		panelDSTI.add(scrpTbTienIch);

		modeltbTienIch = new DefaultTableModel();
		modeltbTienIch.addColumn("STT");
		modeltbTienIch.addColumn("Mã Tiện Ích");
		modeltbTienIch.addColumn("Tên Tiện Ích");

		tbTienIch = new JTable(modeltbTienIch) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Không cho phép chỉnh sửa ô nào
				return false;
			}
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
		tbTienIch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbTienIch.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModeltbTienIch = tbTienIch.getColumnModel();
		for (int i = 0; i < lengthColumn.length; i++) {
			columnModeltbTienIch.getColumn(i).setPreferredWidth(lengthColumn[i]);
		}
		updateViewTable();
		tbTienIch.setBackground(new Color(240, 248, 255));
		scrpTbTienIch.setViewportView(tbTienIch);
		tbTienIch.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = tbTienIch.getSelectedRow();
					if (selectedRow != -1) {
						setChucNangHienTai(0);
						tfMaTienIch.setText((String) modeltbTienIch.getValueAt(selectedRow,1));
						tfTenTienIch.setText((String) modeltbTienIch.getValueAt(selectedRow,2));
					}
				}
			}
		});

		JPanel panelTimKiem = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTimKiem.getLayout();
		flowLayout.setHgap(8);
		panelTimKiem.setBackground(new Color(255, 255, 255));
		panelTimKiem.setBounds(46, 17, 685, 38);
		panelDSTI.add(panelTimKiem);
		
		JLabel lblTimKiem = new JLabel("Nhập mã / tên cần tìm:");
		panelTimKiem.add(lblTimKiem);
		lblTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfTimKiem = new JTextField();
		tfTimKiem.setPreferredSize(new Dimension(150, 30));
		panelTimKiem.add(tfTimKiem);
		tfTimKiem.setColumns(18);
		
		btnTimKiem = new JButton("Tìm");
		btnTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfTimKiem.getText().trim().isEmpty()){
					List<TienIchDTO> result = busTienIch.findTienIch(tfTimKiem.getText().trim());
					setTableData(result);
					lblKetQuaTimKiem.setText("Đã lọc theo kết quả tìm kiếm");
				}else{
					JOptionPane.showMessageDialog(null,"Chưa có dữ liệu để tìm","Thông báo",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnTimKiem.setForeground(new Color(255, 255, 255));
		btnTimKiem.setBackground(new Color(30, 144, 255));
		panelTimKiem.add(btnTimKiem);
		
		JButton btnLamMoi = new JButton("Làm Mới");
		btnLamMoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfTimKiem.setText("");
				lblKetQuaTimKiem.setText("");
				tfMaTienIch.setText("");
				tfTenTienIch.setText("");
				setChucNangHienTai(-1);
			}
		});
		panelTimKiem.add(btnLamMoi);
		
		lblKetQuaTimKiem = new JLabel("");
		lblKetQuaTimKiem.setHorizontalAlignment(SwingConstants.TRAILING);
		lblKetQuaTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblKetQuaTimKiem.setForeground(new Color(128, 128, 128));
		lblKetQuaTimKiem.setBounds(461, 641, 270, 20);
		panelDSTI.add(lblKetQuaTimKiem);
		
		JPanel panelChiTiet = new JPanel();
		panelChiTiet.setBackground(new Color(255, 255, 255));
		panelChiTiet.setBounds(831, 91, 398, 441);
		add(panelChiTiet);
		panelChiTiet.setLayout(null);
		
		JLabel lbTTCT = new JLabel("Thông tin chi tiết");
		lbTTCT.setHorizontalAlignment(SwingConstants.CENTER);
		lbTTCT.setForeground(new Color(100, 149, 237));
		lbTTCT.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbTTCT.setBounds(112, 22, 174, 32);
		panelChiTiet.add(lbTTCT);
		
		JLabel lblNewLabel = new JLabel("Mã tiện ích:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(38, 78, 114, 27);
		panelChiTiet.add(lblNewLabel);
		
		tfMaTienIch = new JTextField();
		tfMaTienIch.setFocusable(false);
		tfMaTienIch.setEnabled(false);
		tfMaTienIch.setEditable(false);
		tfMaTienIch.setBackground(new Color(240, 248, 255));
		tfMaTienIch.setBounds(35, 113, 335, 32);
		panelChiTiet.add(tfMaTienIch);
		tfMaTienIch.setColumns(10);
		
		JLabel lbTenTienIch = new JLabel("Tên tiện ích");

		lbTenTienIch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbTenTienIch.setBounds(38, 157, 114, 27);
		panelChiTiet.add(lbTenTienIch);
		
		tfTenTienIch = new JTextField();
		tfTenTienIch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				validInput();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validInput();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validInput();
			}

			private void validInput() {
				String text = tfTenTienIch.getText().trim();
				if (text.isEmpty()) {
					errTenTI.setText("* Không được để trống");
				} else if (text.matches(".*\\d.*")) {
					errTenTI.setText("* Không được nhập số");
				} else {
					errTenTI.setText(""); // Xóa thông báo lỗi nếu dữ liệu hợp lệ,dữ liệu đúng
				}
			}
		});
		tfTenTienIch.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getChucNangHienTai() == -1){
					setChucNangHienTai(1);
					tfMaTienIch.setText(createID());
					errTenTI.setText("* Không được để trống");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

			}

		});
		tfTenTienIch.setColumns(10);
		tfTenTienIch.setBounds(35, 187, 335, 32);
		panelChiTiet.add(tfTenTienIch);
		
		JLabel lblChucNang = new JLabel("Chức năng:");
		lblChucNang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblChucNang.setBounds(38, 280, 86, 27);
		panelChiTiet.add(lblChucNang);
		
		JPanel panelChucNang = new JPanel();
		panelChucNang.setBackground(new Color(255, 255, 255));
		panelChucNang.setBounds(38, 319, 252, 43);
		panelChiTiet.add(panelChucNang);
		panelChucNang.setLayout(new GridLayout(0, 3, 5, 0));
		
		btnAddTienIch = new JButton("Thêm");
		btnAddTienIch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getChucNangHienTai() == 1){
					if (!errTenTI.getText().isEmpty()){
						JOptionPane.showMessageDialog(null,"Vui lòng thực hiện đầy đủ yêu cầu","Cảnh Báo",JOptionPane.WARNING_MESSAGE);
					} else if (JOptionPane.showConfirmDialog(null,"Xác nhận thêm tiện ích mới","Xác nhận",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						busTienIch.addTienIch(tfMaTienIch.getText().trim(),tfTenTienIch.getText().trim());
						tfMaTienIch.setText("");
						tfTenTienIch.setText("");
						setChucNangHienTai(-1);
					}
				}

			}
		});
		btnAddTienIch.setForeground(new Color(255, 255, 255));
		panelChucNang.add(btnAddTienIch);
		btnAddTienIch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAddTienIch.setBackground(new Color(50, 205, 50));
		
		btnSuaTienIch = new JButton("Sửa");
		btnSuaTienIch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setChucNangHienTai(2);
			}
		});
		btnSuaTienIch.setForeground(new Color(245, 245, 245));
		panelChucNang.add(btnSuaTienIch);
		btnSuaTienIch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSuaTienIch.setBackground(new Color(96, 96, 96));
		
		btnDeleteTienIch = new JButton("Xóa");
		btnDeleteTienIch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setChucNangHienTai(3);
				if (JOptionPane.showConfirmDialog(null,"Bạn muốn xóa tiện ích này?","Xác Nhận",JOptionPane.YES_NO_OPTION )== JOptionPane.YES_OPTION){
					busTienIch.deleteTienIch(tfMaTienIch.getText().trim());
					tfTenTienIch.setText("");
					tfMaTienIch.setText("");
					setChucNangHienTai(-1);
				}
			}
		});
		btnDeleteTienIch.setForeground(new Color(245, 245, 245));
		panelChucNang.add(btnDeleteTienIch);
		btnDeleteTienIch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDeleteTienIch.setBackground(new Color(255, 99, 71));
		
		JPanel panelXacNhan = new JPanel();
		panelXacNhan.setBackground(new Color(255, 255, 255));
		panelXacNhan.setBounds(296, 319, 74, 98);
		panelChiTiet.add(panelXacNhan);
		panelXacNhan.setLayout(new GridLayout(2, 0, 0, 8));
		
		btnLuuTienIch = new JButton("Lưu");
		btnLuuTienIch.setEnabled(false);
		btnLuuTienIch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getChucNangHienTai() == 2 && errTenTI.getText().isEmpty()){
					busTienIch.updateTienIch(tfMaTienIch.getText().trim(),tfTenTienIch.getText().trim());
					tfMaTienIch.setText("");
					tfTenTienIch.setText("");
					setChucNangHienTai(-1);
				} else {
					JOptionPane.showMessageDialog(null,"Vui lòng thực hiện đầy đủ yêu cầu","Thông báo",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnLuuTienIch.setForeground(new Color(255, 255, 255));
		panelXacNhan.add(btnLuuTienIch);
		btnLuuTienIch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLuuTienIch.setBackground(new Color(30, 144, 255));
		
		btnHuyTienIch = new JButton("Hủy");
		btnHuyTienIch.setEnabled(false);
		btnHuyTienIch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfTenTienIch.setText("");
				tfMaTienIch.setText("");
				setChucNangHienTai(-1);
				tbTienIch.clearSelection();
			}
		});
		panelXacNhan.add(btnHuyTienIch);
		btnHuyTienIch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHuyTienIch.setBackground(Color.WHITE);
		
		errTenTI = new JLabel("");
		errTenTI.setForeground(new Color(220, 20, 60));
		errTenTI.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		errTenTI.setHorizontalAlignment(SwingConstants.TRAILING);
		errTenTI.setBounds(38, 217, 332, 20);
		panelChiTiet.add(errTenTI);
		
		lblChucNangHienTai = new JLabel("");
		lblChucNangHienTai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChucNangHienTai.setBounds(125, 280, 161, 27);
		panelChiTiet.add(lblChucNangHienTai);

		setChucNangHienTai(-1);
	}
	public void setTableData(List<TienIchDTO> listTienIch){
		// Thêm dữ liệu từ listNV vào model
		modeltbTienIch.setRowCount(0);
		int stt = 1;
		for (TienIchDTO ti : listTienIch) {
			modeltbTienIch.addRow(new Object[]
					{stt++,ti.getMaTI(),ti.getTenTI()});
		}
		tbTienIch.setModel(modeltbTienIch);
	}
	public void updateViewTable() {
		busTienIch = new TienIchBUS();
		setTableData(busTienIch.getAllTienIch());
	}
	public void setChucNangHienTai(int newValue){
		this.chucNangHienTai = newValue;
		switch (chucNangHienTai) {
			case -1:{
				btnAddTienIch.setEnabled(false);
				btnSuaTienIch.setEnabled(false);
				btnDeleteTienIch.setEnabled(false);
				btnLuuTienIch.setEnabled(false);
				btnHuyTienIch.setEnabled(false);
				lblChucNangHienTai.setText("");
				tfTenTienIch.setEnabled(true);
				errTenTI.setText("");
				updateViewTable();
				break;
			}
			case 0: {
				btnAddTienIch.setEnabled(false);
				btnSuaTienIch.setEnabled(true);
				btnDeleteTienIch.setEnabled(true);
				btnLuuTienIch.setEnabled(false);
				btnHuyTienIch.setEnabled(true);
				lblChucNangHienTai.setText("Xem Chi Tiết");
				lblChucNangHienTai.setForeground(new Color(100, 149, 237));
				tfTenTienIch.setEnabled(false);
				errTenTI.setText("");
				break;
			}
			case 1: {
				btnAddTienIch.setEnabled(true);
				btnSuaTienIch.setEnabled(false);
				btnDeleteTienIch.setEnabled(false);
				btnLuuTienIch.setEnabled(false);
				btnHuyTienIch.setEnabled(true);
				lblChucNangHienTai.setText("Thêm Tiện Ích");
				lblChucNangHienTai.setForeground(new Color(50, 205, 50));
				tfTenTienIch.setEnabled(true);
				errTenTI.setText("");
				break;
			}
			case 2: {
				btnAddTienIch.setEnabled(false);
				btnSuaTienIch.setEnabled(false);
				btnDeleteTienIch.setEnabled(false);
				btnLuuTienIch.setEnabled(true);
				btnHuyTienIch.setEnabled(true);
				lblChucNangHienTai.setText("Sửa Tiện Ích");
				lblChucNangHienTai.setForeground(new Color(96, 96, 96));
				tfTenTienIch.setEnabled(true);
				errTenTI.setText("");
				break;
			}
			case 3: {
				btnAddTienIch.setEnabled(false);
				btnSuaTienIch.setEnabled(false);
				btnDeleteTienIch.setEnabled(true);
				btnLuuTienIch.setEnabled(false);
				btnHuyTienIch.setEnabled(true);
				lblChucNangHienTai.setText("Xóa Tiện Ích");
				lblChucNangHienTai.setForeground(new Color(255, 99, 71));
				tfTenTienIch.setEnabled(false);
				errTenTI.setText("");
				break;
			}
		}

	}
	public int getChucNangHienTai(){ return chucNangHienTai;}
	public String createID(){
		int index = busTienIch.getTienIchCount()+1;
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		Date dateNow = new Date();
		String str_dateNow = dateFormat.format(dateNow);
		return String.format("TI%s%04d",str_dateNow,index);
	}
}
