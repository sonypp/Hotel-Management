package GUI.NhanVien;
import DTO.NhanVienDTO;
import GUI.Home.HomeForm;
import GUI.Phong.ThemPhongPanel;
import BUS.NhanVienBUS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.SimpleFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class PanelStaff extends JPanel {
	public static int width = 1251;
	public static int height = 735;
	public static int[] lengthColumn = {50,120,300,100,120,120,100,150,150,250};
	private static String[] optionChucVu = {"Quản lý","Lễ tân","Kế Toán","Bếp"};
	private static String[] optionGioiTinh = {"Nam","Nữ"};
	private static final long serialVersionUID = 1L;
	private JPanel panelThuocTinh;
	private JPanel panelTitle;
	private JButton btnNhapMoi;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtEmail;
	private JComboBox<String> cbBoxChucVu;
	private JScrollPane scrollPaneDSNV;
	private JButton btnTimKiem;
	private JPanel panelDSNV_btn_1;
	private JButton btnNhapTep;
	private JButton btnXoaNV;
	private JButton btnXuatTep;
	private JButton btnThemNV;
	private JComboBox<String> cbBoxGioiTinh;
	private JButton btnSuaNV;
	private DefaultTableModel modelDSNV;
	private JTable tableDSNV;
	private List<NhanVienDTO> dataDSNV;
	private JDateChooser dateVaoLam_from;
	private NhanVienBUS busNhanVien;
	private JDateChooser dateNgaySinh_from;
	private JDateChooser dateNgaySinh_to;
	private JSpinner spinLuong1Ngay_min;
	private JSpinner spinLuong1Ngay_max;
	private JSpinner spinSoNgayPhep_min;
	private JSpinner spinSoNgayPhep_max;
	private JLabel lblIDNV;
	private JLabel lblTenNV;
	private JLabel lblEmail;
	private JLabel lblGioiTinh;
	private JLabel lblChucVu;
	private JLabel lblNgayVaoLam_from;
	private JLabel lblNgaySinh_from;
	private JLabel lblNgaySinh_to;
	private JLabel lblLuong1Ngay_min;
	private JLabel lblLuong1Ngay_max;
	private JLabel lblSoNgayPhep_min;
	private JLabel lblSoNgayPhep_max;
	private JLabel lblKetQuaTimKiem;

	private ExcelServiceStaff serviceStaff;
	private JDateChooser dateVaoLam_to;
	private JLabel lblNgayVaoLam_to;
	/**
	 * Create the panel.
	 */
	public PanelStaff() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(192, 192, 192)));
		setBounds(new Rectangle(0, 0, this.width, this.height));
		setBackground(new Color(245, 245, 245));
		setLayout(null);

		serviceStaff = new ExcelServiceStaff();
		
		panelThuocTinh = new JPanel();
		panelThuocTinh.setBackground(new Color(255, 255, 255));
		panelThuocTinh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panelThuocTinh.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelThuocTinh.setAutoscrolls(true);
		panelThuocTinh.setBounds(25, 76, 1200, 218);
		add(panelThuocTinh,BorderLayout.CENTER);
		panelThuocTinh.setLayout(null);
		
		JPanel panelThuocTinh_input = new JPanel();
		panelThuocTinh_input.setBackground(new Color(255, 255, 255));
		panelThuocTinh_input.setBounds(new Rectangle(0, 6, 1200, 164));
		panelThuocTinh.add(panelThuocTinh_input);
		
		lblIDNV = new JLabel("Mã Nhân Viên");
		lblIDNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtMaNV = new JTextField();
		txtMaNV.setPreferredSize(new Dimension(10, 25));
		txtMaNV.setForeground(new Color(0, 0, 0));
		txtMaNV.setMargin(new Insets(2, 8, 2, 6));
		txtMaNV.setBackground(new Color(248, 248, 255));
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaNV.setColumns(10);
		
		lblTenNV = new JLabel("Tên Nhân Viên");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtTenNV = new JTextField();
		txtTenNV.setPreferredSize(new Dimension(20, 25));
		txtTenNV.setBackground(new Color(248, 248, 255));
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenNV.setColumns(15);
		
		lblEmail = new JLabel("Email Nhân Viên");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(5, 25));
		txtEmail.setBackground(new Color(248, 248, 255));
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmail.setColumns(15);
		panelThuocTinh_input.setLayout(new FlowLayout(FlowLayout.LEADING, 22, 15));
		panelThuocTinh_input.add(lblIDNV);
		panelThuocTinh_input.add(txtMaNV);
		panelThuocTinh_input.add(lblTenNV);
		panelThuocTinh_input.add(txtTenNV);
		panelThuocTinh_input.add(lblEmail);
		panelThuocTinh_input.add(txtEmail);
		
		lblGioiTinh = new JLabel("Giới Tính");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblGioiTinh);

		cbBoxGioiTinh = new JComboBox<>(optionGioiTinh);
		cbBoxGioiTinh.setPreferredSize(new Dimension(80, 30));
		cbBoxGioiTinh.setMinimumSize(new Dimension(140, 30));
		cbBoxGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbBoxGioiTinh.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		cbBoxGioiTinh.setBackground(new Color(248, 248, 255));
		cbBoxGioiTinh.setSelectedIndex(-1);
		panelThuocTinh_input.add(cbBoxGioiTinh);
		
		lblChucVu = new JLabel("Chức Vụ");
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblChucVu);

		cbBoxChucVu = new JComboBox<>(optionChucVu);
		cbBoxChucVu.setPreferredSize(new Dimension(110, 30));
		cbBoxChucVu.setMinimumSize(new Dimension(80, 30));
		cbBoxChucVu.setBackground(new Color(248, 248, 255));
		cbBoxChucVu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbBoxChucVu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		cbBoxChucVu.setSelectedIndex(-1);
		panelThuocTinh_input.add(cbBoxChucVu);
		
		lblNgayVaoLam_from = new JLabel("Ngày Vào Làm từ  ");
		lblNgayVaoLam_from.setPreferredSize(new Dimension(118, 20));
		lblNgayVaoLam_from.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblNgayVaoLam_from);
		
		dateVaoLam_from = new JDateChooser();
		dateVaoLam_from.setDateFormatString("dd/MM/yyyy");
		dateVaoLam_from.getCalendarButton().setForeground(UIManager.getColor("activeCaption"));
		dateVaoLam_from.getCalendarButton().setHideActionText(true);
		dateVaoLam_from.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateVaoLam_from.setForeground(UIManager.getColor("Button.default.focusedBackground"));
		dateVaoLam_from.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateVaoLam_from.setBackground(UIManager.getColor("Button.default.focusedBackground"));
		dateVaoLam_from.getCalendarButton().setBackground(UIManager.getColor("Button.default.focusedBackground"));
		dateVaoLam_from.setPreferredSize(new Dimension(150, 30));
		panelThuocTinh_input.add(dateVaoLam_from);
		
		lblNgaySinh_from = new JLabel("Ngày Sinh từ   ");
		lblNgaySinh_from.setPreferredSize(new Dimension(90, 20));
		lblNgaySinh_from.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblNgaySinh_from);
		
		dateNgaySinh_from = new JDateChooser();
		dateNgaySinh_from.getCalendarButton().setHideActionText(true);
		dateNgaySinh_from.getCalendarButton().setForeground(UIManager.getColor("activeCaption"));
		dateNgaySinh_from.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateNgaySinh_from.getCalendarButton().setBackground((Color) null);
		dateNgaySinh_from.setPreferredSize(new Dimension(160, 30));
		dateNgaySinh_from.setForeground((Color) null);
		dateNgaySinh_from.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateNgaySinh_from.setDateFormatString("dd/MM/yyyy");
		dateNgaySinh_from.setBackground((Color) null);
		panelThuocTinh_input.add(dateNgaySinh_from);

		SpinnerNumberModel modelLuong1Ngay_min = new SpinnerNumberModel(0,0,5000000,1000);
		SpinnerNumberModel modelLuong1Ngay_max = new SpinnerNumberModel(0,0,5000000,1000);
		
		SpinnerNumberModel modelSoNgayPhep_min = new SpinnerNumberModel(0,0,30,1);
		SpinnerNumberModel modelSoNgayPhep_max = new SpinnerNumberModel(0,0,30,1);
		
		lblLuong1Ngay_min = new JLabel("Lương 1 ngày từ");
		lblLuong1Ngay_min.setMaximumSize(new Dimension(112, 20));
		lblLuong1Ngay_min.setMinimumSize(new Dimension(112, 20));
		lblLuong1Ngay_min.setPreferredSize(new Dimension(114, 20));
		lblLuong1Ngay_min.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblLuong1Ngay_min);
		
		spinLuong1Ngay_min = new JSpinner(modelLuong1Ngay_min);
		spinLuong1Ngay_min.setPreferredSize(new Dimension(160, 30));
		spinLuong1Ngay_min.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelThuocTinh_input.add(spinLuong1Ngay_min);
		lblSoNgayPhep_min = new JLabel("Số ngày nghỉ phép từ");
		lblSoNgayPhep_min.setPreferredSize(new Dimension(140, 20));
		lblSoNgayPhep_min.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblSoNgayPhep_min);
		
		spinSoNgayPhep_min = new JSpinner(modelSoNgayPhep_min);
		spinSoNgayPhep_min.setPreferredSize(new Dimension(80, 30));
		spinSoNgayPhep_min.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelThuocTinh_input.add(spinSoNgayPhep_min);
		
		lblNgayVaoLam_to = new JLabel("Ngày Vào Làm đến");
		lblNgayVaoLam_to.setPreferredSize(new Dimension(118, 20));
		lblNgayVaoLam_to.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblNgayVaoLam_to);
		
		dateVaoLam_to = new JDateChooser();
		dateVaoLam_to.getCalendarButton().setHideActionText(true);
		dateVaoLam_to.getCalendarButton().setForeground(UIManager.getColor("activeCaption"));
		dateVaoLam_to.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateVaoLam_to.getCalendarButton().setBackground((Color) null);
		dateVaoLam_to.setPreferredSize(new Dimension(150, 30));
		dateVaoLam_to.setForeground((Color) null);
		dateVaoLam_to.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateVaoLam_to.setDateFormatString("dd/MM/yyyy");
		dateVaoLam_to.setBackground((Color) null);
		panelThuocTinh_input.add(dateVaoLam_to);
		
		lblNgaySinh_to = new JLabel("Ngày Sinh đến");
		lblNgaySinh_to.setPreferredSize(new Dimension(90, 20));
		lblNgaySinh_to.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblNgaySinh_to);
		
		dateNgaySinh_to = new JDateChooser();
		dateNgaySinh_to.getCalendarButton().setHideActionText(true);
		dateNgaySinh_to.getCalendarButton().setForeground(UIManager.getColor("activeCaption"));
		dateNgaySinh_to.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateNgaySinh_to.getCalendarButton().setBackground((Color) null);
		dateNgaySinh_to.setPreferredSize(new Dimension(160, 30));
		dateNgaySinh_to.setForeground((Color) null);
		dateNgaySinh_to.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateNgaySinh_to.setDateFormatString("dd/MM/yyyy");
		dateNgaySinh_to.setBackground((Color) null);
		panelThuocTinh_input.add(dateNgaySinh_to);
		
		lblLuong1Ngay_max = new JLabel("Lương 1 ngày đến");
		lblLuong1Ngay_max.setPreferredSize(new Dimension(114, 20));
		lblLuong1Ngay_max.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblLuong1Ngay_max);
		
		spinLuong1Ngay_max = new JSpinner(modelLuong1Ngay_max);
		spinLuong1Ngay_max.setPreferredSize(new Dimension(160, 30));
		spinLuong1Ngay_max.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelThuocTinh_input.add(spinLuong1Ngay_max);
		
		lblSoNgayPhep_max = new JLabel("Số ngày nghỉ phép đến");
		lblSoNgayPhep_max.setPreferredSize(new Dimension(140, 20));
		lblSoNgayPhep_max.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelThuocTinh_input.add(lblSoNgayPhep_max);
		
		spinSoNgayPhep_max = new JSpinner(modelSoNgayPhep_max);
		spinSoNgayPhep_max.setPreferredSize(new Dimension(80, 30));
		spinSoNgayPhep_max.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelThuocTinh_input.add(spinSoNgayPhep_max);
		
		JPanel panelThuocTinh_btn = new JPanel();
		panelThuocTinh_btn.setBounds(911, 172, 262, 40);
		panelThuocTinh_btn.setBackground(new Color(255, 255, 255));
		panelThuocTinh.add(panelThuocTinh_btn);
		panelThuocTinh_btn.setLayout(new GridLayout(1, 2, 20, 5));
		
		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mess = "";
				if (errTenNV()) mess += "Tên Nhân Viên không được nhập số\n";
				if (errEmail()) mess += "Email không đúng định dạng\n";
				if (errNgaySinh()) mess += "Kiểm tra lại khoảng thời gian của Ngày Sinh\n";
				if (errNgayVaoLam()) mess += "Kiểm tra lại khoảng thời gian của Ngày Vào Làm\n";
				if (errLuong1Ngay()) mess += "Kiểm tra lại khoảng dữ liệu của Lương 1 Ngày\n";
				if (errSoNgayPhep()) mess += "Kiểm tra lại khoảng dữ liệu của Số Ngày Nghỉ Phép\n";
				if (isNullValue() && mess.isEmpty()){
					updateViewTable();
					lblKetQuaTimKiem.setText("Đã lọc theo dữ liệu tìm kiếm");
				} else if (!mess.isEmpty()){
					JOptionPane.showMessageDialog(null,mess,"Lỗi định dạng",JOptionPane.ERROR_MESSAGE);
				} else {

						List<NhanVienDTO> result = busNhanVien.findNhanVien(txtMaNV.getText(),txtTenNV.getText(),txtEmail.getText(),cbBoxGioiTinh.getSelectedIndex(),cbBoxChucVu.getSelectedIndex(),
								dateNgaySinh_from.getDate(),dateNgaySinh_to.getDate(),
								dateVaoLam_from.getDate(),dateVaoLam_to.getDate(),
								(int)spinLuong1Ngay_min.getValue(),(int)spinLuong1Ngay_max.getValue(),
								(int)spinSoNgayPhep_min.getValue(),(int)spinSoNgayPhep_max.getValue());
						setTableData(result);
						lblKetQuaTimKiem.setText("Đã lọc theo dữ liệu tìm kiếm");
				}
				btnNhapTep.setEnabled(false);
				btnXuatTep.setEnabled(false);

			}
		});
		btnTimKiem.setBorder(UIManager.getBorder("Button.border"));
		btnTimKiem.setForeground(new Color(240, 255, 255));
		btnTimKiem.setBackground(new Color(70, 130, 180));
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelThuocTinh_btn.add(btnTimKiem);

		btnNhapMoi = new JButton("Nhập Mới");
		btnNhapMoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetValueFind();
				updateViewTable();
				lblKetQuaTimKiem.setText("");
				btnNhapTep.setEnabled(true);
				btnXuatTep.setEnabled(true);
			}
		});
		btnNhapMoi.setBackground(new Color(255, 255, 255));
		btnNhapMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelThuocTinh_btn.add(btnNhapMoi);
		
		panelTitle = new JPanel();
		panelTitle.setForeground(new Color(250, 128, 114));
		panelTitle.setBackground(new Color(245, 245, 245));
		panelTitle.setBounds(25, 16, 1200, 68);
		add(panelTitle);
		panelTitle.setLayout(new GridLayout(0, 1, 1, 0));
		
		JLabel label_Title = new JLabel("QUẢN LÝ NHÂN VIÊN");
		label_Title.setBackground(new Color(0, 0, 0));
		label_Title.setForeground(new Color(70, 130, 180));
		label_Title.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_Title.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelTitle.add(label_Title);
		
		JLabel label_Sub = new JLabel("Vui lòng bấm vào bảng danh sách để chỉnh sửa");
		label_Sub.setFont(new Font("Tahoma", Font.ITALIC, 12));
		panelTitle.add(label_Sub);
		
		JPanel panelDSKH = new JPanel();
		panelDSKH.setBackground(new Color(255, 255, 255));
		panelDSKH.setBounds(25, 299, 1200, 440);
		add(panelDSKH);
		panelDSKH.setLayout(null);
		
		JPanel panelDSKH_btn = new JPanel();
		panelDSKH_btn.setBackground(new Color(255, 255, 255));
		panelDSKH_btn.setBounds(25, 27, 1150, 44);
		panelDSKH.add(panelDSKH_btn);
		panelDSKH_btn.setLayout(null);
		
		JLabel lblDSKH = new JLabel("DANH SÁCH NHÂN VIÊN");
		lblDSKH.setBounds(0, 0, 294, 27);
		lblDSKH.setVerticalAlignment(SwingConstants.TOP);
		lblDSKH.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblDSKH.setForeground(new Color(0, 0, 0));
		lblDSKH.setBackground(new Color(70, 130, 180));
		panelDSKH_btn.add(lblDSKH);
		
		lblKetQuaTimKiem = new JLabel("");
		lblKetQuaTimKiem.setForeground(new Color(70, 130, 180));
		lblKetQuaTimKiem.setBackground(new Color(255, 255, 255));
		lblKetQuaTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKetQuaTimKiem.setBounds(0, 24, 294, 20);
		panelDSKH_btn.add(lblKetQuaTimKiem);
		
		panelDSNV_btn_1 = new JPanel();
		panelDSNV_btn_1.setBounds(671, 0, 479, 44);
		panelDSNV_btn_1.setBackground(Color.WHITE);
		panelDSKH_btn.add(panelDSNV_btn_1);
		panelDSNV_btn_1.setLayout(new GridLayout(1, 2, 15, 5));
		
		btnThemNV = new JButton("Thêm Nhân Viên");
		btnThemNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame popupFrame = new JFrame("Thêm nhân viên");
				popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ActionListener closeFrame = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						popupFrame.dispose();
					}
				};
				PanelStaffInfo addNV = new PanelStaffInfo(busNhanVien.getNhanVienCount(),closeFrame);
				addNV.setPreferredSize(new Dimension(700, 680));
				popupFrame.getContentPane().add(addNV);
				popupFrame.pack();
				popupFrame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
				popupFrame.setVisible(true);
				popupFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if (addNV.getNhanvien() != null) {
							NhanVienDTO nv = addNV.getNhanvien();
							busNhanVien.addNhanVien(nv.getMaNV(), nv.getTenNV(), nv.getGioiTinh(), nv.getSoNgayPhep(), nv.getChucVu(),
									nv.getNgaySinh(),nv.getNgayVaoLam(),
									nv.getEmail(), nv.getLuong1Ngay());
							updateViewTable();
						}

					}
				});
			}
		});
		btnThemNV.setForeground(new Color(240, 255, 255));
		btnThemNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemNV.setBorder(UIManager.getBorder("Button.border"));
		btnThemNV.setBackground(new Color(50, 205, 50));
		panelDSNV_btn_1.add(btnThemNV);


		btnSuaNV = new JButton("Sửa Nhân Viên");
		btnSuaNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame popupFrame = new JFrame("Sửa Nhân Viên");
				popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ActionListener closeFrame = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						popupFrame.dispose();
					}
				};
				PanelStaffInfo updateNV = new PanelStaffInfo(-1,closeFrame);
				int selectedRow = tableDSNV.getSelectedRow();
				if (selectedRow == -1){
					JOptionPane.showMessageDialog(null,"Vui lòng chọn một nhân viên","Thông báo",JOptionPane.ERROR_MESSAGE);
				}
				else {
					Object[] selectedRowData = new Object[tableDSNV.getColumnCount() - 1];

					for (int i = 1; i < tableDSNV.getColumnCount(); i++) {
						if(i-1 == 2){//GioiTinh
							selectedRowData[i-1] = findOptionIndex((String)tableDSNV.getValueAt(selectedRow, i),optionGioiTinh);
						} else if (i-1 == 5){//ChucVu
							selectedRowData[i-1] = findOptionIndex((String)tableDSNV.getValueAt(selectedRow, i),optionChucVu);
						} else {
							selectedRowData[i-1] = tableDSNV.getValueAt(selectedRow, i);
						}
					}
					updateNV.setInfoNV(selectedRowData);
					updateNV.setPreferredSize(new Dimension(700, 680));
					popupFrame.getContentPane().add(updateNV);
					popupFrame.pack();
					popupFrame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
					popupFrame.setVisible(true);
					popupFrame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							if (updateNV.getNhanvien() != null) {
								NhanVienDTO nv = updateNV.getNhanvien();
								busNhanVien.updateNhanVien(nv.getMaNV(), nv.getTenNV(), nv.getGioiTinh(), nv.getSoNgayPhep(), nv.getChucVu(),
										nv.getNgaySinh(),nv.getNgayVaoLam(),
										nv.getEmail(), nv.getLuong1Ngay());
								updateViewTable();
							}
						}
					});
				}
			}
		});
		btnSuaNV.setForeground(new Color(240, 255, 255));
		btnSuaNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSuaNV.setBorder(UIManager.getBorder("Button.border"));
		btnSuaNV.setBackground(new Color(119, 136, 153));
		panelDSNV_btn_1.add(btnSuaNV);
		
		btnXoaNV = new JButton("Xóa Nhân Viên");
		btnXoaNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableDSNV.getSelectedRow();
				if (selectedRow == -1){
					JOptionPane.showMessageDialog(null,"Vui lòng chọn một nhân viên","Thông báo",JOptionPane.ERROR_MESSAGE);
				}
				else {
					String selectedMaNV = (String) tableDSNV.getValueAt(selectedRow,1);
					if (JOptionPane.showConfirmDialog(null,"Bạn có muốn xóa nhân viên này?","Thông báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						busNhanVien.deleteNhanVien(selectedMaNV);
					}
					updateViewTable();
				}
			}
		});
		btnXoaNV.setForeground(new Color(255, 255, 255));
		btnXoaNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaNV.setBackground(new Color(255, 69, 0));
		panelDSNV_btn_1.add(btnXoaNV);

		modelDSNV = new DefaultTableModel();
		modelDSNV.addColumn("STT");
		for (String col : NhanVienDTO.header) {
			modelDSNV.addColumn(col);
		}

		tableDSNV = new JTable(modelDSNV) {
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
		tableDSNV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableDSNV.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModelDSNV = tableDSNV.getColumnModel();
		for (int i = 0; i < lengthColumn.length; i++) {
			columnModelDSNV.getColumn(i).setPreferredWidth(lengthColumn[i]);
		}
		updateViewTable();
		tableDSNV.setSelectionBackground(new Color(112, 128, 144));
		tableDSNV.setSelectionForeground(new Color(255, 255, 255));
		tableDSNV.setBackground(new Color(248, 248, 255));
		tableDSNV.setFont(new Font("Tahoma", Font.PLAIN, 14));

        
		scrollPaneDSNV = new JScrollPane(tableDSNV);
		scrollPaneDSNV.setBounds(25, 83, 1150, 275);
		panelDSKH.add(scrollPaneDSNV);
		scrollPaneDSNV.setBackground(SystemColor.window);
		scrollPaneDSNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panelDSNV_btn_2 = new JPanel();
		panelDSNV_btn_2.setBackground(Color.WHITE);
		panelDSNV_btn_2.setBounds(913, 370, 262, 47);
		panelDSKH.add(panelDSNV_btn_2);
		panelDSNV_btn_2.setLayout(new GridLayout(1, 2, 20, 5));
		
		btnNhapTep = new JButton("Nhập Tệp");
		btnNhapTep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serviceStaff.chooseFile();
				serviceStaff.readFile();
				if (!serviceStaff.isReadFileSuccess()) return;

				//Khi đọc file thành công
				List<NhanVienDTO> dataFromFile = serviceStaff.getData();

				//Lấy ra các id trong dataFromFile
				List<String> maNVFromFile = new ArrayList<>();
				for (NhanVienDTO nv : dataFromFile) {
					maNVFromFile.add(nv.getMaNV());
				}

				//Kiểm tra, Lọc id trùng giữa data và dataFromFile
				List<String> maNVDupli = checkFileMaNVDupli(maNVFromFile);
				//Lúc này, maNVFromFile sẽ chỉ còn chứa những id không bị trùng
				maNVFromFile.removeAll(maNVDupli);

				//Xét trường hợp của các id không bị trùng
				for (String maNV : maNVFromFile) {
					for (NhanVienDTO nv: dataFromFile) {
						if (nv.getMaNV().equals(maNV)) {
							busNhanVien.addNhanVien(nv.getMaNV(), nv.getTenNV(), nv.getGioiTinh(), nv.getSoNgayPhep(), nv.getChucVu(),
									nv.getNgaySinh(), nv.getNgayVaoLam(),
									nv.getEmail(), nv.getLuong1Ngay());
						}
					}
				}
				//Xét trường hợp của từng id trùng
				for (String maNV : maNVDupli) {
					switch (showDialogOptionReadFile("Thông báo",
							String.format("Trong file có mã nhân viên %s trùng với dữ liệu sẵn có!\nVui lòng chọn cách xử lý!",maNV)))
					{
						case JOptionPane.YES_OPTION: {
							break;
						}
						case JOptionPane.NO_OPTION: {
							for (NhanVienDTO nv : dataFromFile) {
								if (nv.getMaNV().equals(maNV)){
									busNhanVien.updateNhanVien(nv.getMaNV(), nv.getTenNV(), nv.getGioiTinh(), nv.getSoNgayPhep(), nv.getChucVu(),
											nv.getNgaySinh(),nv.getNgayVaoLam(),
											nv.getEmail(), nv.getLuong1Ngay());
									break;
								}
							}
							break;
						}
						case JOptionPane.CANCEL_OPTION: {
							for (NhanVienDTO nv : dataFromFile) {
								if (nv.getMaNV().equals(maNV)){
									busNhanVien.addNhanVien(PanelStaffInfo.createID(new Date(),busNhanVien.getNhanVienCount()+1,nv.getGioiTinh()),
											nv.getTenNV(), nv.getGioiTinh(), nv.getSoNgayPhep(), nv.getChucVu(),
											nv.getNgaySinh(),new Date(),
											nv.getEmail(), nv.getLuong1Ngay());
									break;
								}
							}
							break;
						}
					}
				}

				JOptionPane.showMessageDialog(null,"Nhập tệp thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				updateViewTable();
			}
		});
		btnNhapTep.setForeground(new Color(240, 255, 255));
		btnNhapTep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNhapTep.setBorder(UIManager.getBorder("Button.border"));
		btnNhapTep.setBackground(new Color(123, 104, 238));
		panelDSNV_btn_2.add(btnNhapTep);
		
		btnXuatTep = new JButton("Xuất Tệp");
		btnXuatTep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serviceStaff.chooseFile();
				serviceStaff.setData(dataDSNV);
				serviceStaff.writeFile();

				JOptionPane.showMessageDialog(null,"Xuất tệp thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnXuatTep.setForeground(new Color(240, 255, 255));
		btnXuatTep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXuatTep.setBorder(UIManager.getBorder("Button.border"));
		btnXuatTep.setBackground(new Color(123, 104, 238));
		panelDSNV_btn_2.add(btnXuatTep);
	}
	public void setTableData(List<NhanVienDTO> listNhanVien){
		// Thêm dữ liệu từ listNV vào model
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		modelDSNV.setRowCount(0);
		int stt = 1;
		for (NhanVienDTO nv : listNhanVien) {
			modelDSNV.addRow(new Object[]
					{stt++,nv.getMaNV(), nv.getTenNV(), optionGioiTinh[nv.getGioiTinh()],formatter.format(nv.getNgaySinh()),formatter.format(nv.getNgayVaoLam()), optionChucVu[nv.getChucVu()], nv.getSoNgayPhep(),nv.getLuong1Ngay(),nv.getEmail()});
		}
	}
	public void updateViewTable(){
		busNhanVien = new NhanVienBUS();
		dataDSNV = busNhanVien.getAllNhanVien();
		setTableData(dataDSNV);
	}
	public void resetValueFind() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtEmail.setText("");
		cbBoxGioiTinh.setSelectedIndex(-1);
		cbBoxChucVu.setSelectedIndex(-1);
		spinLuong1Ngay_min.setValue(0);
		spinLuong1Ngay_max.setValue(0);
		spinSoNgayPhep_min.setValue(0);
		spinSoNgayPhep_max.setValue(0);
		dateNgaySinh_from.setDate(null);
		dateNgaySinh_to.setDate(null);
		dateVaoLam_from.setDate(null);
		dateVaoLam_to.setDate(null);
		lblSoNgayPhep_min.setForeground(new Color(0,0,0));
		lblSoNgayPhep_max.setForeground(new Color(0,0,0));
		lblLuong1Ngay_min.setForeground(new Color(0,0,0));
		lblLuong1Ngay_max.setForeground(new Color(0,0,0));
		lblNgaySinh_from.setForeground(new Color(0, 0, 0));
		lblNgaySinh_to.setForeground(new Color(0, 0, 0));
		lblNgayVaoLam_from.setForeground(new Color(0, 0, 0));
		lblNgayVaoLam_to.setForeground(new Color(0, 0, 0));
		lblEmail.setForeground(new Color(0, 0, 0));
		lblTenNV.setForeground(new Color(0,0,0));
	}
	public boolean errTenNV(){
		boolean isHasNum = false;
		if (!txtTenNV.getText().isEmpty()){
			isHasNum = txtTenNV.getText().matches( ".*\\d.*");
		}

		if(isHasNum){
			lblTenNV.setForeground(new Color(220, 20, 60));
		} else {
			lblTenNV.setForeground(new Color(0, 0, 0));
		}
		return isHasNum;
	}
	public boolean errEmail(){
		boolean isTrueEmail = true;
		if (!txtEmail.getText().isEmpty()){
			isTrueEmail = txtEmail.getText().matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
		}

		if(!isTrueEmail){
			lblEmail.setForeground(new Color(220, 20, 60));
		} else {
			lblEmail.setForeground(new Color(0, 0, 0));
		}
		return !isTrueEmail;
	}
	public boolean errNgaySinh(){
		boolean isTrueTime = true;
		if (dateNgaySinh_from.getDate() != null && dateNgaySinh_to.getDate() != null) {
			LocalDate dateFrom = dateNgaySinh_from.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate dateTo = dateNgaySinh_to.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			isTrueTime = dateFrom.isBefore(dateTo);
		}
		if(!isTrueTime){
			lblNgaySinh_from.setForeground(new Color(220, 20, 60));
			lblNgaySinh_to.setForeground(new Color(220, 20, 60));
		} else {
			lblNgaySinh_from.setForeground(new Color(0, 0, 0));
			lblNgaySinh_to.setForeground(new Color(0, 0, 0));
		}
		return !isTrueTime;
	}
	public boolean errNgayVaoLam(){
		boolean isTrueTime = true;
		if (dateVaoLam_from.getDate() != null && dateVaoLam_to.getDate() != null) {
			LocalDate dateFrom = dateVaoLam_from.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate dateTo = dateVaoLam_to.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			isTrueTime = dateFrom.isBefore(dateTo);
		}

		if(!isTrueTime){
			lblNgayVaoLam_from.setForeground(new Color(220, 20, 60));
			lblNgayVaoLam_to.setForeground(new Color(220, 20, 60));
		} else {
			lblNgayVaoLam_from.setForeground(new Color(0, 0, 0));
			lblNgayVaoLam_to.setForeground(new Color(0, 0, 0));
		}
		return !isTrueTime;
	}
	public boolean errLuong1Ngay(){
		int min = (int) spinLuong1Ngay_min.getValue();
		int max = (int) spinLuong1Ngay_max.getValue();
		boolean result = true;
		if (min > max && min*max != 0){
			lblLuong1Ngay_min.setForeground(new Color(220,20,60));
			lblLuong1Ngay_max.setForeground(new Color(220,20,60));
			result = false;
		}else {
			lblLuong1Ngay_min.setForeground(new Color(0,0,0));
			lblLuong1Ngay_max.setForeground(new Color(0,0,0));
		}
		return !result;
	}
	public boolean errSoNgayPhep(){
		int min = (int) spinSoNgayPhep_min.getValue();
		int max = (int) spinSoNgayPhep_max.getValue();
		boolean result = true;
		if (min > max && min*max != 0){
			lblSoNgayPhep_min.setForeground(new Color(220,20,60));
			lblSoNgayPhep_max.setForeground(new Color(220,20,60));
			result = false;
		}else {
			lblSoNgayPhep_min.setForeground(new Color(0,0,0));
			lblSoNgayPhep_max.setForeground(new Color(0,0,0));
		}
		return !result;
	}
	public boolean isNullValue() {
		return txtMaNV.getText().isEmpty() && txtTenNV.getText().isEmpty() && txtEmail.getText().isEmpty()
				&& (int)spinLuong1Ngay_min.getValue() == 0 && (int)spinLuong1Ngay_max.getValue() == 0
				&& (int)spinSoNgayPhep_min.getValue() == 0 && (int)spinSoNgayPhep_max.getValue() == 0
				&& dateNgaySinh_from.getDate() == null && dateNgaySinh_to.getDate() == null
				&& dateVaoLam_from.getDate() == null && dateVaoLam_to.getDate() == null
				&& cbBoxGioiTinh.getSelectedIndex() == -1 && cbBoxChucVu.getSelectedIndex() == -1;
	}
	public int findOptionIndex(String value, String[] option){
		boolean isFound = false;
		int i = 0;
		while(i < option.length && !isFound){
			if (value.equals(option[i])){
				isFound = true;
			}
			if (!isFound) {i++;}
		}
		if (!isFound) {i = -1;}
		return i;
	}

	public int showDialogOptionReadFile(String title, String message){
		return JOptionPane.showOptionDialog(
				null,
				message, title,
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				new String[]{"Keep Old", "Keep Current", "Add current like new"},
				"Keep Old"
		);
	}
	public List<String> checkFileMaNVDupli(List<String> maNVFromFile){
		Set<String> maNVSet = new LinkedHashSet<>();
		List<String> maNVDupli = new ArrayList<>();
		for (NhanVienDTO nv : dataDSNV) {
			maNVSet.add(nv.getMaNV());
		}

		for (String maNV : maNVFromFile) {
			if (!maNVSet.add(maNV)){
				maNVDupli.add(maNV);
			}
		}

		return maNVDupli;
	}
}

