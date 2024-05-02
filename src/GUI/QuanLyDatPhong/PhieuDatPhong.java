package GUI.QuanLyDatPhong;

import java.awt.Color;
import java.util.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import BUS.ChiTietThueBUS;
import BUS.ChiTietThuePhongBUS;
import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import DTO.PhongDTO;
import GUI.Home.HomeForm;
import GUI.KhachHang.FrmSearchCustomer;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PhieuDatPhong extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaCTT;
	private JTextField txtTenNV;
	private JTextField txtNgayLapPhieu;
	private JTextField tfTTXL;
	private JTextField txtTienCoc;
	private JTextField txtMaKH;
	private JTextField txtHoTenKH;
	private JTextField txtCMND;
	private JTextField txtSDT;
	private JDateChooser dateNgaySinh;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtDuong;
	private JTextField txtPhuong;
	private JTextField txtQuan;
	private JTextField txtQuocTich;
	public JTable table;
	private JTextField txtTinhThanh;
	public DefaultTableModel data;
	private JLabel lblTongTien;
	private JLabel lblErrorTenKH;
	private JLabel lblErrorCMND;
	private JLabel lblErrorSDT;
	private JLabel lblErrorNgaySinh;
	private JLabel lblErrorDiaChi;
	private JLabel lblErrorQuocTich;
	private KhachHangBUS khBUS = new KhachHangBUS();
	private ChiTietThueBUS ctt = new ChiTietThueBUS();
	private ChiTietThuePhongBUS cttp = new ChiTietThuePhongBUS();
	private JRadioButton rbtnNam;
	private JRadioButton rbtnNu;
	private Timer timerRealTime;

	/**
	 * Create the panel.
	 */
	public PhieuDatPhong() {
		setBounds(0, 0, 1251, 835);
		setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        
        JLabel lbHeader = new JLabel("Tạo phiếu đặt phòng");
        lbHeader.setForeground(new Color(255, 91, 13));
        lbHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
        lbHeader.setBounds(10, 10, 278, 41);
        add(lbHeader);
        
        JPanel pnTTPhieuThue = new JPanel();
        pnTTPhieuThue.setBounds(10, 61, 1231, 133);
        add(pnTTPhieuThue);
        pnTTPhieuThue.setLayout(null);
        
        JLabel lbtextttpt = new JLabel("Thông tin phiếu thuê");
        lbtextttpt.setForeground(new Color(0, 108, 217));
        lbtextttpt.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbtextttpt.setBounds(6, 6, 279, 37);
        pnTTPhieuThue.add(lbtextttpt);
        
        JLabel lblNewLabel = new JLabel("Mã chi tiết thuê");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel.setBounds(6, 42, 172, 21);
        pnTTPhieuThue.add(lblNewLabel);
        
        txtMaCTT = new JTextField();
        txtMaCTT.setEnabled(false);
        txtMaCTT.setBounds(6, 75, 172, 26);
        pnTTPhieuThue.add(txtMaCTT);
        txtMaCTT.setColumns(10);
        
        JLabel lb = new JLabel("Nhân viên làm phiếu");
        lb.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lb.setBounds(242, 42, 172, 21);
        pnTTPhieuThue.add(lb);
        
        txtTenNV = new JTextField();
        txtTenNV.setEnabled(false);
        txtTenNV.setColumns(10);
        txtTenNV.setBounds(242, 75, 172, 26);
        pnTTPhieuThue.add(txtTenNV);
        
        JLabel lblNgyLpPhiu = new JLabel("Ngày lập phiếu");
        lblNgyLpPhiu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNgyLpPhiu.setBounds(503, 42, 172, 21);
        pnTTPhieuThue.add(lblNgyLpPhiu);
        
        txtNgayLapPhieu = new JTextField();
        txtNgayLapPhieu.setEnabled(false);
        txtNgayLapPhieu.setColumns(10);
        txtNgayLapPhieu.setBounds(503, 75, 172, 26);
        pnTTPhieuThue.add(txtNgayLapPhieu);
        
        JLabel lblNewLabel_1_1 = new JLabel("Tình trạng xử lý");
        lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(740, 42, 172, 21);
        pnTTPhieuThue.add(lblNewLabel_1_1);
        
        tfTTXL = new JTextField();
        tfTTXL.setEditable(false);
        tfTTXL.setText("Đang xử lý");
        tfTTXL.setColumns(10);
        tfTTXL.setBounds(740, 75, 172, 26);
        pnTTPhieuThue.add(tfTTXL);
        
        JLabel lblNewLabel_1 = new JLabel("Tiền đặt cọc");
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_1.setBounds(993, 42, 172, 21);
        pnTTPhieuThue.add(lblNewLabel_1);
        
        txtTienCoc = new JTextField();
        txtTienCoc.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
                if (!Character.isDigit(c) && !Character.isISOControl(c)) {
                    // Consume the event to prevent the character from being inputted
                    e.consume();
                }
        	}
        });
        txtTienCoc.setColumns(10);
        txtTienCoc.setBounds(993, 75, 172, 26);
        pnTTPhieuThue.add(txtTienCoc);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 206, 1231, 219);
        add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("Thông tin khách hàng");
        lblNewLabel_2.setForeground(new Color(0, 108, 217));
        lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNewLabel_2.setBounds(6, 0, 182, 35);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Mã khách hàng");
        lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3.setBounds(6, 35, 162, 24);
        panel.add(lblNewLabel_3);
        
        txtMaKH = new JTextField();
        txtMaKH.setEnabled(false);
        txtMaKH.setBounds(6, 71, 162, 26);
        panel.add(txtMaKH);
        txtMaKH.setColumns(10);
        
        JLabel lblNewLabel_3_1 = new JLabel("Họ tên khách hàng");
        lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_1.setBounds(247, 35, 162, 24);
        panel.add(lblNewLabel_3_1);
        
        txtHoTenKH = new JTextField();
        txtHoTenKH.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		checkTenKH();
        	}
        });
        txtHoTenKH.setEnabled(false);
        txtHoTenKH.setColumns(10);
        txtHoTenKH.setBounds(247, 71, 162, 26);
        panel.add(txtHoTenKH);
        
        JLabel lblNewLabel_3_2 = new JLabel("CMND/CCCD");
        lblNewLabel_3_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_2.setBounds(509, 35, 162, 24);
        panel.add(lblNewLabel_3_2);
        
        txtCMND = new JTextField();
        txtCMND.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtCMND_TextChanged();
        	}
        });
        txtCMND.setColumns(10);
        txtCMND.setBounds(509, 71, 162, 26);
        panel.add(txtCMND);
        
        JLabel lblNewLabel_3_3 = new JLabel("Số điện thoại");
        lblNewLabel_3_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_3.setBounds(746, 35, 162, 24);
        panel.add(lblNewLabel_3_3);
        
        txtSDT = new JTextField();
        txtSDT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtSDT_TextChanged(e);
        	}
        });
        txtSDT.setEnabled(false);
        txtSDT.setColumns(10);
        txtSDT.setBounds(746, 71, 162, 26);
        panel.add(txtSDT);
        
        JLabel lblNewLabel_3_4 = new JLabel("Ngày sinh");
        lblNewLabel_3_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_4.setBounds(1001, 35, 162, 24);
        panel.add(lblNewLabel_3_4);
        
        dateNgaySinh = new JDateChooser();
        dateNgaySinh.setDateFormatString("dd/MM/yyyy");
        dateNgaySinh.setEnabled(false);
        dateNgaySinh.setBounds(1001, 71, 162, 26);
        dateNgaySinh.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                	dateNgaySinh_ValueChanged();
                }
            }
        });
        panel.add(dateNgaySinh);
        
        JLabel lblNewLabel_3_5 = new JLabel("Giới tính");
        lblNewLabel_3_5.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5.setBounds(6, 121, 162, 24);
        panel.add(lblNewLabel_3_5);
        
        rbtnNam = new JRadioButton("Nam");
        rbtnNam.setEnabled(false);
        rbtnNam.setSelected(true);
        buttonGroup.add(rbtnNam);
        rbtnNam.setBounds(6, 169, 58, 20);
        panel.add(rbtnNam);
        
        rbtnNu = new JRadioButton("Nữ");
        rbtnNu.setEnabled(false);
        buttonGroup.add(rbtnNu);
        rbtnNu.setBounds(90, 169, 58, 20);
        panel.add(rbtnNu);
        
        JLabel lblNewLabel_3_5_1 = new JLabel("Địa chỉ");
        lblNewLabel_3_5_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1.setBounds(247, 126, 162, 24);
        panel.add(lblNewLabel_3_5_1);
        
        JLabel lblNewLabel_3_5_1_1 = new JLabel("Đường:");
        lblNewLabel_3_5_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1_1.setBounds(247, 169, 58, 24);
        panel.add(lblNewLabel_3_5_1_1);
        
        txtDuong = new JTextField();
        txtDuong.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtDuong_TextChanged(e);
        	}
        });
        txtDuong.setEnabled(false);
        txtDuong.setBounds(303, 167, 67, 26);
        panel.add(txtDuong);
        txtDuong.setColumns(10);
        
        JLabel lblNewLabel_3_5_1_2 = new JLabel("Phường/Thôn:");
        lblNewLabel_3_5_1_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1_2.setBounds(380, 169, 99, 24);
        panel.add(lblNewLabel_3_5_1_2);
        
        txtPhuong = new JTextField();
        txtPhuong.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtDuong_TextChanged(e);
        	}
        });
        txtPhuong.setEnabled(false);
        txtPhuong.setColumns(10);
        txtPhuong.setBounds(486, 167, 67, 26);
        panel.add(txtPhuong);
        
        JLabel lblNewLabel_3_5_1_2_1 = new JLabel("Quận/Huyện:");
        lblNewLabel_3_5_1_2_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1_2_1.setBounds(553, 169, 100, 24);
        panel.add(lblNewLabel_3_5_1_2_1);
        
        txtQuan = new JTextField();
        txtQuan.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtDuong_TextChanged(e);
        	}
        });
        txtQuan.setEnabled(false);
        txtQuan.setColumns(10);
        txtQuan.setBounds(651, 167, 67, 26);
        panel.add(txtQuan);
        
        JLabel lblNewLabel_3_5_1_2_1_1 = new JLabel("Quốc tịch:");
        lblNewLabel_3_5_1_2_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1_2_1_1.setBounds(956, 166, 76, 24);
        panel.add(lblNewLabel_3_5_1_2_1_1);
        
        txtQuocTich = new JTextField();
        txtQuocTich.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		checkQuocTich();
        	}
        });
        txtQuocTich.setEnabled(false);
        txtQuocTich.setColumns(10);
        txtQuocTich.setBounds(1042, 166, 135, 26);
        panel.add(txtQuocTich);
        
        JLabel lblNewLabel_3_5_1_2_1_2 = new JLabel("Tỉnh/Thành:");
        lblNewLabel_3_5_1_2_1_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_5_1_2_1_2.setBounds(722, 169, 100, 24);
        panel.add(lblNewLabel_3_5_1_2_1_2);
        
        txtTinhThanh = new JTextField();
        txtTinhThanh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtDuong_TextChanged(e);
        	}
        });
        txtTinhThanh.setEnabled(false);
        txtTinhThanh.setColumns(10);
        txtTinhThanh.setBounds(812, 167, 105, 26);
        panel.add(txtTinhThanh);
        
        lblErrorTenKH = new JLabel("Tên KH không được để trống");
        lblErrorTenKH.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblErrorTenKH.setForeground(new Color(255, 0, 0));
        lblErrorTenKH.setBounds(247, 96, 162, 20);
        panel.add(lblErrorTenKH);
        
        lblErrorCMND = new JLabel("CCCD/CMND không được để trống");
        lblErrorCMND.setForeground(Color.RED);
        lblErrorCMND.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblErrorCMND.setBounds(509, 96, 162, 20);
        panel.add(lblErrorCMND);
        
        lblErrorSDT = new JLabel("SĐT không được để trống");
        lblErrorSDT.setForeground(Color.RED);
        lblErrorSDT.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblErrorSDT.setBounds(746, 96, 162, 20);
        panel.add(lblErrorSDT);
        
        lblErrorNgaySinh = new JLabel("Ngày sinh không đủ 18 tuổi");
        lblErrorNgaySinh.setForeground(Color.RED);
        lblErrorNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblErrorNgaySinh.setBounds(1001, 96, 162, 20);
        panel.add(lblErrorNgaySinh);
        
        lblErrorDiaChi = new JLabel("Không được để trống vùng dữ liệu địa chỉ nào");
        lblErrorDiaChi.setForeground(Color.RED);
        lblErrorDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblErrorDiaChi.setBounds(303, 129, 368, 20);
        panel.add(lblErrorDiaChi);
        
        lblErrorQuocTich = new JLabel("Quốc tịch không được để trống");
        lblErrorQuocTich.setForeground(Color.RED);
        lblErrorQuocTich.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblErrorQuocTich.setBounds(1042, 189, 162, 20);
        panel.add(lblErrorQuocTich);
        
        JPanel pnTable = new JPanel();
        pnTable.setBounds(10, 441, 1231, 302);
        add(pnTable);
        pnTable.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 66, 1219, 230);
        pnTable.add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"STT","Mã phòng","Tình trạng","Loại hình thuê","Ngày thuê","Ngày trả","Ngày Checkout","Giá phòng","Xóa"
        	}
        ));
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel_4 = new JLabel("Danh sách phòng đang xử lý");
        lblNewLabel_4.setForeground(new Color(0, 108, 217));
        lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblNewLabel_4.setBounds(6, 16, 289, 34);
        pnTable.add(lblNewLabel_4);
        
        JButton btnThemPhong = new JButton("Thêm Phòng");
        btnThemPhong.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAddRoom_Clicked();
        	}
        });
        btnThemPhong.setBackground(new Color(77, 77, 255));
        btnThemPhong.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnThemPhong.setBounds(865, 771, 159, 41);
        add(btnThemPhong);
        
        JButton btnLuuPhieuThue = new JButton("Lưu Phiếu Thuê");
        btnLuuPhieuThue.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        	}
        });
        btnLuuPhieuThue.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLuuPhieuThue.setBackground(new Color(77, 77, 255));
        btnLuuPhieuThue.setBounds(1046, 771, 159, 41);
        add(btnLuuPhieuThue);
        
        lblTongTien = new JLabel("Tổng tiền");
        lblTongTien.setFont(new Font("Segoe UI", Font.ITALIC, 17));
        lblTongTien.setBounds(629, 771, 203, 41);
        add(lblTongTien);
        
        
        
        data = new DefaultTableModel();
        data.addColumn("MaP");
        data.addColumn("TenP");
        data.addColumn("TinhTrang");
        data.addColumn("LoaiHinhThue");
        data.addColumn("NgayThue");
        data.addColumn("NgayTra");
        data.addColumn("NgayCheckOut");
        data.addColumn("GiaThuc");
        
        timerRealTime = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update real-time labels
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                txtNgayLapPhieu.setText(getFormattedDate(dateFormat));
            }
        });
        txtTenNV.setText(HomeForm.nhanVien.getTenNV());
        
        txtCMND.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && !Character.isISOControl(c)) {
                    e.consume();
                }
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = 9;
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (col == index && row >= 0) {
                    int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phòng thuê này", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (ans == JOptionPane.YES_OPTION) {
                        ((DefaultTableModel)table.getModel()).removeRow(row);
                        hienThiDanhSachPhongThue();
                    } else {
                        table.clearSelection();
                    }
                }
            }
        });
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(249, 249, 249));
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });
	}
	
	private void txtCMND_TextChanged() {
	    txtHoTenKH.setText("");
	    txtSDT.setText("");
	    txtDuong.setText("");
	    txtPhuong.setText("");
	    txtQuan.setText("");
	    txtTinhThanh.setText("");
	    txtQuocTich.setText("");
	    txtMaKH.setText("");
	    if (txtCMND.getText().trim().length() >= 9) {
	        enableCustomerInfoFields(true);
	        resetFieldBackgroundColors(Color.WHITE);
	    } else {
	        enableCustomerInfoFields(false);
	        resetFieldBackgroundColors(Color.LIGHT_GRAY);
	        resetFields();
	    }
	    if (txtCMND.getText().trim().isEmpty()) {
	        lblErrorCMND.setVisible(true);
	    } else {
	        lblErrorCMND.setVisible(false);
	        var list = khBUS.GetDSKH();
	        var khachhangs = new ArrayList<KhachHangDTO>();
	        for (KhachHangDTO khachhang : list) {
	            if (khachhang.getCMND().equals(txtCMND.getText().trim())) {
	                khachhangs.add(khachhang);
	            }
	        }
	        if (!khachhangs.isEmpty()) {
	            FrmSearchCustomer frmSearch = new FrmSearchCustomer(khachhangs);
	            frmSearch.setVisible(true);
	            if (frmSearch.DialogResult == JOptionPane.OK_OPTION) {
	                KhachHangDTO selectedCustomer = frmSearch.khachHang;
	                txtHoTenKH.setText(selectedCustomer.getTenKH());
	                dateNgaySinh.setDate(selectedCustomer.getNgaySinh());
	                if (selectedCustomer.getGioiTinh() == 0) {
	                    rbtnNam.setSelected(true);
	                } else {
	                    rbtnNu.setSelected(true);
	                }
	                txtQuocTich.setText(selectedCustomer.getQuocTich());
	                txtMaKH.setText(selectedCustomer.getMaKH());
	                String[] queQuan = selectedCustomer.getQueQuan().split(",");
	                txtSDT.setText(selectedCustomer.getSDT());
	                txtDuong.setText(queQuan[0]);
	                txtPhuong.setText(queQuan[1]);
	                txtQuan.setText(queQuan[2]);
	                txtTinhThanh.setText(queQuan[3]);

	                resetFieldBackgroundColors(Color.LIGHT_GRAY);
	                enableCustomerInfoFields(false);
	            }
	        }
	    }
	}

    private void checkTenKH() {
        if (txtHoTenKH.getText().trim().length() == 0) {
            lblErrorTenKH.setVisible(true);
        } else {
            lblErrorTenKH.setVisible(false);
        }
    }

    private void checkQuocTich() {
        if (txtQuocTich.getText().trim().length() == 0) {
            lblErrorQuocTich.setVisible(true);
        } else {
            lblErrorQuocTich.setVisible(false);
        }
    }
    
    private void btnAddRoom_Clicked()
    {
    	FormSelectRoom frmSelectRoom = new FormSelectRoom(data);
    	var popupFrame = new JFrame("Thêm phòng");
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popupFrame.setSize(1260, 900);
		popupFrame.getContentPane().add(frmSelectRoom);
		popupFrame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
    	if (frmSelectRoom.DialogResult == JOptionPane.OK_OPTION)
    	{
    	    PhongDTO phong = (PhongDTO)frmSelectRoom.arr[0];
    	    try
    	    {
    	    	data.addRow(new Object[] {phong.getMaP(), phong.getTenP(), "Đang xử lý", frmSelectRoom.arr[1], Date.parse(frmSelectRoom.arr[2].toString()), Date.parse(frmSelectRoom.arr[3].toString()), "", Integer.parseInt(frmSelectRoom.arr[4].toString())});
    	    	
    	    }
    	    catch (Exception e)
    	    {
    	    	data.addRow(new Object[] {phong.getMaP(), phong.getTenP(), "Đang xử lý", frmSelectRoom.arr[1], Date.parse(frmSelectRoom.arr[2].toString()), frmSelectRoom.arr[3].toString(), "", Integer.parseInt(frmSelectRoom.arr[4].toString())});
    	    }
    	    hienThiDanhSachPhongThue();
    	}
    	popupFrame.setVisible(true);
    	frmSelectRoom.setVisible(true);
    }


	private void enableCustomerInfoFields(boolean enabled) {
	    txtHoTenKH.setEnabled(enabled);
	    txtSDT.setEnabled(enabled);
	    dateNgaySinh.setEnabled(enabled);
	    rbtnNam.setEnabled(enabled);
	    rbtnNu.setEnabled(enabled);
	    txtDuong.setEnabled(enabled);
	    txtPhuong.setEnabled(enabled);
	    txtQuan.setEnabled(enabled);
	    txtTinhThanh.setEnabled(enabled);
	    txtQuocTich.setEnabled(enabled);
	}

	private void resetFieldBackgroundColors(Color color) {
	    txtHoTenKH.setBackground(color);
	    txtSDT.setBackground(color);
	    dateNgaySinh.setBackground(color);
	    rbtnNam.setBackground(color);
	    rbtnNu.setBackground(color);
	    txtDuong.setBackground(color);
	    txtPhuong.setBackground(color);
	    txtQuan.setBackground(color);
	    txtTinhThanh.setBackground(color);
	    txtQuocTich.setBackground(color);
	}

	private void resetFields() {
	    txtHoTenKH.setText("");
	    txtSDT.setText("");
	    dateNgaySinh.setDateFormatString("");
	    rbtnNam.setSelected(false);
	    rbtnNu.setSelected(false);
	    txtDuong.setText("");
	    txtPhuong.setText("");
	    txtQuan.setText("");
	    txtTinhThanh.setText("");
	    txtQuocTich.setText("");
	    txtMaKH.setText("");
	}

	public void hienThiDanhSachPhongThue() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // Xóa các hàng hiện tại trong model
	    int stt = 0;
	    int total = 0;
	    
	    // Lặp qua các hàng của DefaultTableModel
	    for (int i = 0; i < model.getRowCount(); i++) {
	        try {
	            Object[] rowData = model.getDataVector().get(i).toArray();
	            stt++;
	            String gia = (String) rowData[7];
	            if (!gia.equals("Chưa tính")) {
	                total += Integer.parseInt(gia);
	            }
	            rowData[0] = stt; // Cập nhật STT
	            model.setValueAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rowData[5]), i, 5); // Cập nhật ngày thuê
	            model.setValueAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rowData[6]), i, 6); // Cập nhật ngày trả
	            model.setValueAt(String.format("%,d VNĐ", total), i, 7); // Cập nhật giá
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    lblTongTien.setText(String.format("%,d VNĐ", total)); // Cập nhật tổng tiền
	    table.clearSelection(); // Bỏ chọn các hàng trong bảng
	}
	
	private void txtDuong_TextChanged(java.awt.event.ActionEvent evt) {
	    if (txtDuong.getText().trim().isEmpty()
	            && txtPhuong.getText().trim().isEmpty()
	            && txtQuan.getText().trim().isEmpty()
	            && txtTinhThanh.getText().trim().isEmpty()) {
	        lblErrorDiaChi.setVisible(true);
	    } else {
	        lblErrorDiaChi.setVisible(false);
	    }
	}

	private void dateNgaySinh_ValueChanged() {
	    if (years(dateNgaySinh.getDate(), new java.util.Date()) >= 16) {
	        lblErrorNgaySinh.setVisible(false);
	    } else {
	        lblErrorNgaySinh.setVisible(true);
	    }
	}

	int years(java.util.Date start, java.util.Date end) {
	    java.util.Calendar calStart = java.util.Calendar.getInstance();
	    calStart.setTime(start);
	    java.util.Calendar calEnd = java.util.Calendar.getInstance();
	    calEnd.setTime(end);

	    int years = calEnd.get(java.util.Calendar.YEAR) - calStart.get(java.util.Calendar.YEAR) - 1;
	    if (calEnd.get(java.util.Calendar.MONTH) > calStart.get(java.util.Calendar.MONTH)
	            || (calEnd.get(java.util.Calendar.MONTH) == calStart.get(java.util.Calendar.MONTH)
	            && calEnd.get(java.util.Calendar.DAY_OF_MONTH) >= calStart.get(java.util.Calendar.DAY_OF_MONTH))) {
	        years++;
	    }
	    return years;
	}

	private boolean isValidPhone(String phone) {
	    phone = phone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
	    return phone.length() == 10 && phone.chars().allMatch(Character::isDigit);
	}

	private void txtSDT_TextChanged(java.awt.event.ActionEvent evt) {
	    String textSDT = txtSDT.getText();
	    boolean valid = isValidPhone(textSDT);
	    if (valid) {
	        lblErrorSDT.setVisible(false);
	    } else {
	        lblErrorSDT.setVisible(true);
	    }
	}
	
	private void buttonRounded2_Click(ActionEvent e) {
	    SimpleDateFormat formatter1 = new SimpleDateFormat("ddMMyy");
	    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    if (txtTienCoc.getText().trim().length() == 0) {
	        int ans = JOptionPane.showConfirmDialog(null, "Bạn có chắc tạo phiếu thuê mới này mà không nhập tiền cọc", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	        
	        if (ans == JOptionPane.YES_OPTION) {
	            if (txtCMND.getText().trim().length() == 0) {
	                lblErrorCMND.setVisible(true);
	            }
	            if (txtHoTenKH.getText().trim().length() == 0) {
	                lblErrorTenKH.setVisible(true);
	            }
	            if (years(dateNgaySinh.getDate(), new Date()) >= 16) {
	                lblErrorNgaySinh.setVisible(false);
	            } else {
	                lblErrorNgaySinh.setVisible(true);
	            }
	            String textSDT = txtSDT.getText();
	            boolean valid = isValidPhone(textSDT);
	            if (!valid) {
	                lblErrorSDT.setVisible(true);
	            }
	            if (txtDuong.getText().trim().length() == 0 && txtPhuong.getText().trim().length() == 0 && txtQuan.getText().trim().length() == 0 && txtTinhThanh.getText().trim().length() == 0) {
	                lblErrorDiaChi.setVisible(true);
	            } else {
	                lblErrorDiaChi.setVisible(false);
	            }
	            if (txtQuocTich.getText().trim().length() == 0) {
	                lblErrorQuocTich.setVisible(true);
	            }
	            if (lblErrorCMND.isVisible() || lblErrorDiaChi.isVisible() || lblErrorNgaySinh.isVisible() || lblErrorQuocTich.isVisible() || lblErrorSDT.isVisible() || lblErrorTenKH.isVisible()) {
	                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin phiếu thuê", "Thông báo lỗi chưa nhập dữ liệu", JOptionPane.ERROR_MESSAGE);
	            }

	            if (!lblErrorCMND.isVisible() && !lblErrorDiaChi.isVisible() && !lblErrorNgaySinh.isVisible() && !lblErrorQuocTich.isVisible() && !lblErrorSDT.isVisible() && !lblErrorTenKH.isVisible()) {
	                if (data.getRowCount() == 0) {
	                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng khách muốn thuê", "Thông báo lỗi chưa chọn phòng thuê", JOptionPane.ERROR_MESSAGE);
	                } else {
	                    String count = String.format("%05d", ctt.GetCountAll(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
	                    txtMaCTT.setText("CTT" + new SimpleDateFormat("ddMMyy").format(new Date()) + count);
	                    
	                    //Thêm khách hàng mới
	                    if (txtMaKH.getText().trim().length() == 0) {
	                        String ngaySinhFormatted = formatter1.format(dateNgaySinh.getDate());
	                        if (rbtnNam.isSelected()) {
	                            txtMaKH.setText("KH" + "0" + ngaySinhFormatted + String.format("%06d", khBUS.GetCountAllKH()));
	                            String diaChi = txtDuong.getText().trim() + "," + txtPhuong.getText().trim() + "," + txtQuan.getText().trim() + "," + txtTinhThanh.getText().trim();
	                            khBUS.InsertKhachHang(txtMaKH.getText().trim(), txtHoTenKH.getText().trim(), txtCMND.getText().trim(), "0", txtSDT.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""), diaChi, txtQuocTich.getText(), formatter2.format(dateNgaySinh.getDate()));
	                        } else {
	                            txtMaKH.setText("KH" + "1" + ngaySinhFormatted + String.format("%06d", khBUS.GetCountAllKH()));
	                            String diaChi = txtDuong.getText().trim() + "," + txtPhuong.getText().trim() + "," + txtQuan.getText().trim() + "," + txtTinhThanh.getText().trim();
	                            khBUS.InsertKhachHang(txtMaKH.getText().trim(), txtHoTenKH.getText().trim(), txtCMND.getText().trim(), "1", txtSDT.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""), diaChi, txtQuocTich.getText(), formatter2.format(dateNgaySinh.getDate()));
	                        }
	                    }

	                    timerRealTime.stop();
	                    if (txtTienCoc.getText().trim().length() == 0) {
	                        txtTienCoc.setText("0");
	                    }
	                    ctt.InsertCTT(txtMaCTT.getText().trim(), txtMaKH.getText().trim(), HomeForm.nhanVien.getMaNV(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), txtTienCoc.getText());

	                    for (int i = 0; i < data.getRowCount(); i++) {
	                        if (data.getValueAt(i, 3).toString().toUpperCase().equals("THEO NGÀY")) {
	                            cttp.InsertCTTP(true, txtMaCTT.getText().trim(), data.getValueAt(i, 0).toString().trim(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 4).toString().trim())), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 5).toString().trim())), "0", data.getValueAt(i, 7).toString());
	                        } else if (data.getValueAt(i, 3).toString().toUpperCase().equals("THEO GIỜ")) {
	                            cttp.InsertCTTP(true, txtMaCTT.getText().trim(), data.getValueAt(i, 0).toString().trim(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 4).toString().trim())), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 5).toString().trim())), "1", data.getValueAt(i, 7).toString());
	                        } else {
	                            cttp.InsertCTTP(false, txtMaCTT.getText().trim(), data.getValueAt(i, 0).toString().trim(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 4).toString().trim())), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 4).toString().trim())), "2", data.getValueAt(i, 7).toString());
	                        }
	                    }
	                    
	                    JOptionPane.showMessageDialog(null, "Thêm phiếu thuê mới thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	                    XuatFilePDF();
	                }
	            }
	        } else {
	            txtTienCoc.requestFocus();
	        }
	    } else {
	    	if (txtCMND.getText().trim().length() == 0) {
	            lblErrorCMND.setVisible(true);
	        }
	        if (txtHoTenKH.getText().trim().length() == 0) {
	            lblErrorTenKH.setVisible(true);
	        }
	        if (years(dateNgaySinh.getDate(), new Date()) >= 16) {
	            lblErrorNgaySinh.setVisible(false);
	        } else {
	            lblErrorNgaySinh.setVisible(true);
	        }
	        String textSDT = txtSDT.getText();
	        boolean valid = isValidPhone(textSDT);
	        if (!valid) {
	            lblErrorSDT.setVisible(true);
	        }
	        if (txtDuong.getText().trim().length() == 0 && txtPhuong.getText().trim().length() == 0 && txtQuan.getText().trim().length() == 0 && txtTinhThanh.getText().trim().length() == 0) {
	            lblErrorDiaChi.setVisible(true);
	        } else {
	            lblErrorDiaChi.setVisible(false);
	        }
	        if (txtQuocTich.getText().trim().length() == 0) {
	            lblErrorQuocTich.setVisible(true);
	        }

	        if (lblErrorCMND.isVisible() || lblErrorDiaChi.isVisible() || lblErrorNgaySinh.isVisible() || lblErrorQuocTich.isVisible() || lblErrorSDT.isVisible() || lblErrorTenKH.isVisible()) {
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin phiếu thuê", "Thông báo lỗi chưa nhập dữ liệu", JOptionPane.ERROR_MESSAGE);
	        }

	        if (!lblErrorCMND.isVisible() && !lblErrorDiaChi.isVisible() && !lblErrorNgaySinh.isVisible() && !lblErrorQuocTich.isVisible() && !lblErrorSDT.isVisible() && !lblErrorTenKH.isVisible()) {
	            if (data.getRowCount() == 0) {
	                JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng khách muốn thuê", "Thông báo lỗi chưa chọn phòng thuê", JOptionPane.ERROR_MESSAGE);
	            } else {
	            	String count = String.format("%05d", ctt.GetCountAll(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
	                txtMaCTT.setText("CTT" + new SimpleDateFormat("ddMMyy").format(new Date()) + count);

	                //Thêm khách hàng mới
	                if (txtMaKH.getText().trim().length() == 0) {
	                    if (rbtnNam.isSelected()) {
	                        txtMaKH.setText("KH" + "0" + formatter1.format(dateNgaySinh.getDate()) + String.format("%06d", khBUS.GetCountAllKH()));
	                        String diaChi = txtDuong.getText().trim() + "," + txtPhuong.getText().trim() + "," + txtQuan.getText().trim() + "," + txtTinhThanh.getText().trim();
	                        khBUS.InsertKhachHang(txtMaKH.getText().trim(), txtHoTenKH.getText().trim(), txtCMND.getText().trim(), "0", txtSDT.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""), diaChi, txtQuocTich.getText(), formatter2.format(dateNgaySinh.getDate()));
	                    } else {
	                        txtMaKH.setText("KH" + "1" + formatter1.format(dateNgaySinh.getDate()) + String.format("%06d", khBUS.GetCountAllKH()));
	                        String diaChi = txtDuong.getText().trim() + "," + txtPhuong.getText().trim() + "," + txtQuan.getText().trim() + "," + txtTinhThanh.getText().trim();
	                        khBUS.InsertKhachHang(txtMaKH.getText().trim(), txtHoTenKH.getText().trim(), txtCMND.getText().trim(), "1", txtSDT.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""), diaChi, txtQuocTich.getText(), formatter2.format(dateNgaySinh.getDate()));
	                    }
	                }

	                timerRealTime.stop();
	                if (txtTienCoc.getText().trim().length() == 0) {
	                    txtTienCoc.setText("0");
	                }
	                ctt.InsertCTT(txtMaCTT.getText().trim(), txtMaKH.getText().trim(), HomeForm.nhanVien.getMaNV(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), txtTienCoc.getText());

	                for (int i = 0; i < data.getRowCount(); i++) {
	                    if (data.getValueAt(i, 3).toString().toUpperCase().equals("THEO NGÀY")) {
	                        cttp.InsertCTTP(true, txtMaCTT.getText().trim(), data.getValueAt(i, 0).toString().trim(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 4).toString().trim())), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 5).toString().trim())), "0", data.getValueAt(i, 7).toString());
	                    } else if (data.getValueAt(i, 3).toString().toUpperCase().equals("THEO GIỜ")) {
	                        cttp.InsertCTTP(true, txtMaCTT.getText().trim(), data.getValueAt(i, 0).toString().trim(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 4).toString().trim())), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 5).toString().trim())), "1", data.getValueAt(i, 7).toString());
	                    } else {
	                        cttp.InsertCTTP(false, txtMaCTT.getText().trim(), data.getValueAt(i, 0).toString().trim(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 4).toString().trim())), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(data.getValueAt(i, 4).toString().trim())), "2", data.getValueAt(i, 7).toString());
	                    }
	                }
	                
	                JOptionPane.showMessageDialog(null, "Thêm phiếu thuê mới thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	                XuatFilePDF();
	            }
	        }
	    }
	}

	private void XuatFilePDF() {
	    Document document = new Document(PageSize.A4, 50, 50, 25, 25);
	    try {
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("CTT" + txtMaCTT.getText() + ".pdf"));
	        document.open();
	        String fontPath = "fonts/ARIAL.TTF"; // Đường dẫn đến tệp font trong thư mục dự án của bạn
	        BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        com.itextpdf.text.Font font = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
	        com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(bf, 13, com.itextpdf.text.Font.BOLD | com.itextpdf.text.Font.UNDERLINE, BaseColor.BLUE);
	        Paragraph p = new Paragraph("KHÁCH SẠN LUXURY", fontTitle);
	        p.setAlignment(Element.ALIGN_CENTER);
	        document.add(p);
	        Paragraph p2 = new Paragraph("Địa chỉ: 273 An Dương Vương, Phường 3, Quận 5, Tp Hồ Chí Minh", font);
	        p2.setAlignment(Element.ALIGN_CENTER);
	        document.add(p2);
	        Paragraph p3 = new Paragraph("Hotline Booking: 0987654321", font);
	        p3.setAlignment(Element.ALIGN_CENTER);
	        document.add(p3);
	        Paragraph p4 = new Paragraph("Fax: 0987654321", font);
	        p4.setAlignment(Element.ALIGN_CENTER);
	        document.add(p4);
	        Paragraph pEmpty = new Paragraph("  ", fontTitle);
	        document.add(pEmpty);

	        Paragraph p5 = new Paragraph("THÔNG TIN PHIẾU THUÊ", fontTitle);
	        p5.setAlignment(Element.ALIGN_LEFT);
	        document.add(p5);
	        Paragraph pMaCTT = new Paragraph("Mã chi tiết thuê: " + txtMaCTT.getText(), font);
	        pMaCTT.setAlignment(Element.ALIGN_LEFT);
	        Paragraph pTenNV = new Paragraph("Nhân viên lập phiếu: " + txtTenNV.getText(), font);
	        pTenNV.setAlignment(Element.ALIGN_LEFT);
	        Paragraph pNgayLapPhieu = new Paragraph("Ngày lập phiếu: " + txtNgayLapPhieu.getText(), font);
	        pNgayLapPhieu.setAlignment(Element.ALIGN_LEFT);
	        Paragraph pTienDatCoc = new Paragraph("Tiền đặt cọc: " + txtTienCoc.getText(), font);
	        pTienDatCoc.setAlignment(Element.ALIGN_LEFT);
	        document.add(pMaCTT);
	        document.add(pTenNV);
	        document.add(pNgayLapPhieu);
	        document.add(pTienDatCoc);
	        Paragraph pEmpty2 = new Paragraph("  ", fontTitle);
	        document.add(pEmpty2);

	        Paragraph p6 = new Paragraph("THÔNG TIN KHÁCH HÀNG", fontTitle);
	        p6.setAlignment(Element.ALIGN_LEFT);
	        document.add(p6);
	        Paragraph pTenKH = new Paragraph("Họ tên khách hàng: " + txtHoTenKH.getText(), font);
	        pTenKH.setAlignment(Element.ALIGN_LEFT);
	        Paragraph pCMND = new Paragraph("CMND/CCCD: " + txtCMND.getText(), font);
	        pCMND.setAlignment(Element.ALIGN_LEFT);
	        Paragraph pSDT = new Paragraph("Số điện thoại: " + txtSDT.getText(), font);
	        pSDT.setAlignment(Element.ALIGN_LEFT);
	        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	        Paragraph pNgaySinh = new Paragraph("Ngày sinh: " + dateFormatter.format(dateNgaySinh.getDate()), font);
	        pNgaySinh.setAlignment(Element.ALIGN_LEFT);
	        Paragraph pGioiTinh = new Paragraph("Giới tính: " + (rbtnNam.isSelected() ? "Nam" : "Nữ"), font);
	        pGioiTinh.setAlignment(Element.ALIGN_LEFT);
	        String diaChi = "";
	        if (txtDuong.getText().trim().length() != 0) {
	            diaChi += "Đường: " + txtDuong.getText().trim();
	        }
	        if (txtPhuong.getText().trim().length() != 0) {
	            if (!diaChi.equals("")) {
	                diaChi += ", ";
	            }
	            diaChi += "Phường/Thôn: " + txtPhuong.getText().trim();
	        }
	        if (txtQuan.getText().trim().length() != 0) {
	            if (!diaChi.equals("")) {
	                diaChi += ", ";
	            }
	            diaChi += "Quận/Huyện: " + txtQuan.getText().trim();
	        }
	        if (txtTinhThanh.getText().trim().length() != 0) {
	            if (!diaChi.equals("")) {
	                diaChi += ", ";
	            }
	            diaChi += "Tỉnh/Thành phố: " + txtTinhThanh.getText().trim();
	        }
	        Paragraph pDiaChi = new Paragraph("Địa chỉ: " + diaChi, font);
	        pDiaChi.setAlignment(Element.ALIGN_LEFT);
	        Paragraph pQuocTich = new Paragraph("Quốc tịch: " + txtQuocTich.getText(), font);
	        pQuocTich.setAlignment(Element.ALIGN_LEFT);
	        document.add(pTenKH);
	        document.add(pCMND);
	        document.add(pSDT);
	        document.add(pNgaySinh);
	        document.add(pGioiTinh);
	        document.add(pDiaChi);
	        document.add(pQuocTich);
	        Paragraph pEmpty3 = new Paragraph("  ", fontTitle);
	        document.add(pEmpty3);
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(100);
	        String[][] data = {
	                {"Khách hàng", "Nhân viên lập phiếu"},
	                {"(Ký và ghi rõ họ tên)", "(Ký và ghi rõ họ tên)"},
	        };
	        for (int i = 0; i < data.length; i++) {
	            for (int j = 0; j < data[i].length; j++) {
	                PdfPCell cell = new PdfPCell(new Phrase(data[i][j], font));
	                cell.setBorder(0);
	                table.addCell(cell);
	            }
	        }
	        document.add(table);
	        document.close();
	    } catch (DocumentException | IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private String getFormattedDate(SimpleDateFormat dateFormat) {
        Date date = new Date();
        return dateFormat.format(date);
    }
	
}
