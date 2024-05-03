package GUI.QuanLyDatPhong;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import GUI.Home.HomeForm;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormChiTietThueDichVu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textBox1;
	private JTable tbDichVu;
	private JTable tbDSDV;
	
	
	
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
		setBounds(411, 27, 830, 798);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách dịch vụ hiện có");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 10, 247, 34);
		add(lblNewLabel);
		
		textBox1 = new JTextField();
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
		
		JComboBox cbbDichVu = new JComboBox();
		cbbDichVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbDichVu.setModel(new DefaultComboBoxModel(new String[] {"", "Ăn uống", "Chăm sóc sắc đẹp", "Tổ chức tiệc", "Giải trí"}));
		cbbDichVu.setSelectedIndex(0);
		cbbDichVu.setBounds(396, 52, 144, 34);
		add(cbbDichVu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 373, 799, 328);
		add(scrollPane);
		
		tbDichVu = new JTable();
        tbDichVu.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "STT", "MÃ DỊCH VỤ", "TÊN DỊCH VỤ", "NGÀY SỬ DỤNG", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN", "SỬA", "XÓA"
                }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        });

        // Set background color, font, and alignment for column names
        JTableHeader header = tbDichVu.getTableHeader();
        header.setBackground(new Color(30, 144, 255)); // Change to the desired color
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD).deriveFont(Font.BOLD, Font.PLAIN)); // Use the desired font style
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        // Set font and alignment for table data
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tbDichVu.setDefaultRenderer(Object.class, centerRenderer);
		scrollPane.setViewportView(tbDichVu);
		
		JButton btnLuuTrangThai = new JButton("Lưu trạng thái");
		btnLuuTrangThai.setForeground(new Color(255, 255, 255));
		btnLuuTrangThai.setBackground(new Color(0, 128, 0));
		btnLuuTrangThai.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLuuTrangThai.setBounds(550, 725, 223, 40);
		add(btnLuuTrangThai);
		
		JLabel lblNewLabel_1 = new JLabel("Danh sách dịch vụ đang xử lý");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 329, 316, 34);
		add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 96, 799, 223);
		add(scrollPane_1);
		
		tbDSDV = new JTable();
		tbDSDV.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "TÊN DỊCH VỤ", "LOẠI DỊCH VỤ", "GIÁ"
                }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        });

        // Set background color, font, and alignment for column names
        JTableHeader header1 = tbDSDV.getTableHeader();
        header1.setBackground(new Color(30, 144, 255)); // Change to the desired color
        header1.setForeground(Color.WHITE);
        header1.setFont(header1.getFont().deriveFont(Font.BOLD).deriveFont(Font.BOLD, Font.PLAIN)); // Use the desired font style
        ((DefaultTableCellRenderer) header1.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        // Set font and alignment for table data
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment(SwingConstants.CENTER);
        tbDSDV.setDefaultRenderer(Object.class, centerRenderer1);
        scrollPane_1.setViewportView(tbDSDV);
	}
}
