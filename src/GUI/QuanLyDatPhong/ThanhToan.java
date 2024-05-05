package GUI.QuanLyDatPhong;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.itextpdf.text.*;
import com.itextpdf.text.log.Level;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.pdf.*;

import BUS.*;
import DTO.*;
import GUI.Home.HomeForm;

import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ThanhToan extends JFrame {
    private JLabel label1;
    private JTextField txtTienThoiLai;
    private JTextField txtTienKhachDua;
    private JTextField TongThanhTien;
    private JButton btnThanhToan;
    private JLabel label13;
    private JPanel panel16;
    private JComboBox<String> cbPTTT;
    private JLabel label15;
    private JPanel panel13;
    private JTextField txtDatCoc;
    private JPanel panel10;
    private JTextField txtTotalService;
    private JLabel label8;
    private JPanel panel9;
    private JTextField txtTotalRoom;
    private JLabel label9;
	private JTextField txtGiamGia;
	private JComboBox CbPhuThu;
	private JTextField txtSL;
	private JTextField txtCMND;
	private JTextField txtTenKH;
	private JTable tableRoom;
	private JTable tableService;
	
	String maCTT;
	ChiTietThuePhongBUS cttP = new ChiTietThuePhongBUS();
	PhongBUS phong = new PhongBUS();
	ChiTietThueDichVuBUS cttDV = new ChiTietThueDichVuBUS();
	DichVuBUS dichVu = new DichVuBUS();
	KhachHangBUS kh = new KhachHangBUS();
	ChiTietThueBUS ctt = new ChiTietThueBUS();
	public int DialogResult;
	private DefaultTableModel tbRoom;
	private DefaultTableModel tbService;

    public ThanhToan(String maCTT) {
        setBackground(Color.WHITE);
        setBounds(100, 100, 1220, 740);
        getContentPane().setLayout(null);

        // Panel 1
        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 58, 867, 642);
        panel1.setBackground(Color.WHITE);
        panel1.setLayout(null);
        label1 = new JLabel("Danh sách phòng thuê");
        label1.setBounds(5, 0, 857, 20);
        label1.setAlignmentY(Component.TOP_ALIGNMENT);
        label1.setVerticalAlignment(SwingConstants.TOP);
        label1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
        label1.setForeground(new Color(0, 0, 0));
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        panel1.add(label1);
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        getContentPane().add(panel1);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 25, 857, 291);
        panel1.add(scrollPane);

        tableRoom = new JTable();
        tableRoom.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "STT", "TÊN PHÒNG", "LOẠI HÌNH THUÊ", "NGÀY THUÊ", "NGÀY TRẢ", "NGÀY CHECKOUT", "GIÁ PHÒNG" }
        ));
        tbRoom = (DefaultTableModel)tableRoom.getModel();

        // Set preferred column width for the first column
        tableRoom.getColumnModel().getColumn(0).setPreferredWidth(54);

        // Disable column reordering
        tableRoom.getTableHeader().setReorderingAllowed(false);

        // Set background color for column names
        JTableHeader header = tableRoom.getTableHeader();
        header.setBackground(Color.lightGray);

        // Custom renderer to set background color and text color for column names
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(130, 180, 180));
                label.setForeground(Color.WHITE);
                label.setFont(label.getFont().deriveFont(Font.BOLD)); // Set bold font
                label.setHorizontalAlignment(SwingConstants.CENTER); // Align text to center
                return label;
            }
        };

        // Apply the custom renderer to the table header
        tableRoom.getTableHeader().setDefaultRenderer(headerRenderer);

        // Add the table to the scroll pane
        scrollPane.setViewportView(tableRoom);



        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(5, 346, 857, 291);
        panel1.add(scrollPane_1);

        tableService = new JTable();
        tableService.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "STT", "TÊN DỊCH VỤ", "LOẠI DỊCH VỤ", "NGÀY SỬ DỤNG", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN" }
        ));
        tbService = (DefaultTableModel)tableService.getModel();

        // Set preferred column width for the first column (if needed)
         tableService.getColumnModel().getColumn(0).setPreferredWidth(54);

        // Disable column reordering
        tableService.getTableHeader().setReorderingAllowed(false);

        // Set background color for column names
        JTableHeader header1 = tableService.getTableHeader();
        header1.setBackground(Color.lightGray);

        // Custom renderer to set background color and text color for column names
        DefaultTableCellRenderer headerRenderer1 = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(130, 180, 180)); // Set background color
                label.setForeground(Color.WHITE); // Set text color
                label.setFont(label.getFont().deriveFont(Font.BOLD)); // Set bold font
                label.setHorizontalAlignment(SwingConstants.CENTER); // Align text to center
                return label;
            }
        };

        // Apply the custom renderer to the table header
        tableService.getTableHeader().setDefaultRenderer(headerRenderer1);


        // Add the table to the scroll pane
        scrollPane_1.setViewportView(tableService);


        
        JLabel lblDanhSchDch = new JLabel("Danh sách dịch vụ thuê");
        lblDanhSchDch.setVerticalAlignment(SwingConstants.TOP);
        lblDanhSchDch.setHorizontalAlignment(SwingConstants.LEFT);
        lblDanhSchDch.setForeground(Color.BLACK);
        lblDanhSchDch.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
        lblDanhSchDch.setAlignmentY(0.0f);
        lblDanhSchDch.setBounds(5, 324, 857, 20);
        panel1.add(lblDanhSchDch);

        // Panel 3
        JPanel panel3 = new JPanel();
        panel3.setBounds(867, 215, 333, 485);
        panel3.setBackground(Color.WHITE);
        panel3.setLayout(new BorderLayout());
        panel3.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        getContentPane().add(panel3);

        // GroupBox 2
        JPanel groupBox2 = new JPanel();
        groupBox2.setBackground(Color.WHITE);
        groupBox2.setLayout(new BorderLayout());
        groupBox2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "THÔNG TIN THANH TOÁN", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI Semibold", Font.BOLD, 10)));
        panel3.add(groupBox2, BorderLayout.CENTER);

        // Table Layout
        JPanel tableLayoutPanel3 = new JPanel();
        tableLayoutPanel3.setBackground(Color.WHITE);
        tableLayoutPanel3.setLayout(new GridLayout(11, 1));
        groupBox2.add(tableLayoutPanel3, BorderLayout.CENTER);
        
        panel9 = new JPanel();
        panel9.setBackground(Color.WHITE);
        panel9.setLayout(new BorderLayout());
        tableLayoutPanel3.add(panel9);
        
        // Adding components to panel9
        label9 = new JLabel("Tổng tiền phòng:             ");
        panel9.add(label9, BorderLayout.WEST);
        txtTotalRoom = new JTextField();
        txtTotalRoom.setEditable(false);
        panel9.add(txtTotalRoom, BorderLayout.CENTER);

        panel10 = new JPanel();
        panel10.setBackground(Color.WHITE);
        panel10.setLayout(new BorderLayout());
        tableLayoutPanel3.add(panel10);
        
        // Adding components to panel10
        label8 = new JLabel("Tổng tiền dịch vụ:            ");
        panel10.add(label8, BorderLayout.WEST);
        txtTotalService = new JTextField();
        txtTotalService.setEditable(false);
        panel10.add(txtTotalService, BorderLayout.CENTER);
        
        JPanel panel9_1 = new JPanel();
        panel9_1.setBackground(Color.WHITE);
        tableLayoutPanel3.add(panel9_1);
        panel9_1.setLayout(new BorderLayout());
        
        JLabel label9_1 = new JLabel("Tiền đặt cọc:                    ");
        panel9_1.add(label9_1, BorderLayout.WEST);
        
        txtDatCoc = new JTextField();
        txtDatCoc.setEditable(false);
        panel9_1.add(txtDatCoc, BorderLayout.CENTER);
        
        // Adding components to tableLayoutPanel5
        JPanel tableLayoutPanel5 = new JPanel(new GridLayout(1, 2));
        JPanel panel12 = new JPanel();
        panel12.setLayout(new BorderLayout());
        txtGiamGia = new JTextField();
        txtGiamGia.setEditable(false);
        JLabel label11 = new JLabel("Giảm giá:  ");
        panel12.add(label11, BorderLayout.WEST);
        panel12.add(txtGiamGia, BorderLayout.CENTER);
        tableLayoutPanel5.add(panel12);
        JPanel panel11 = new JPanel();
        panel11.setLayout(new BorderLayout());
        CbPhuThu = new JComboBox<>(new String[]{"", "0%", "5%", "10%", "15%", "20%", "25%", "30%", "35%", "40%", "45%", "50%", "55%", "60%", "65%", "70%", "75%", "80%", "85%", "90%", "95%", "100%"});
        CbPhuThu.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		CbPhuThu_SelectedIndexChanged();
        	}
        });
        
        JLabel label10 = new JLabel("Phụ thụ:  ");
        panel11.add(label10, BorderLayout.WEST);
        panel11.add(CbPhuThu, BorderLayout.CENTER);
        tableLayoutPanel5.add(panel11);
        tableLayoutPanel3.add(tableLayoutPanel5);

        JPanel panel14 = new JPanel();
        panel14.setBackground(Color.WHITE);
        panel14.setLayout(new BorderLayout());
        tableLayoutPanel3.add(panel14);
        
        // Adding components to panel14
        label13 = new JLabel("Tổng thành tiền:              ");
        panel14.add(label13, BorderLayout.WEST);
        TongThanhTien = new JTextField();
        TongThanhTien.setEditable(false);
        panel14.add(TongThanhTien, BorderLayout.CENTER);

        JPanel panel15 = new JPanel();
        panel15.setBackground(Color.WHITE);
        panel15.setLayout(new BorderLayout());
        tableLayoutPanel3.add(panel15);
        
        // Adding components to panel15
        JLabel label14 = new JLabel("Tiền khách đưa:               ");
        panel15.add(label14, BorderLayout.WEST);
        txtTienKhachDua = new JTextField();
        txtTienKhachDua.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                txtTienKhachDua_KeyPress(e);
            }
        });
        txtTienKhachDua.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                txtTienKhachDua_TextChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txtTienKhachDua_TextChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                txtTienKhachDua_TextChanged();
            }
        });
        panel15.add(txtTienKhachDua, BorderLayout.CENTER);

        // Panels in Table Layout
        JPanel panel17 = new JPanel();
        panel17.setBackground(Color.WHITE);
        panel17.setLayout(new BorderLayout());
        tableLayoutPanel3.add(panel17);

        panel16 = new JPanel();
        panel16.setBackground(Color.WHITE);
        panel16.setLayout(new BorderLayout());
        tableLayoutPanel3.add(panel16);
        
        JLabel pictureBox1 = new JLabel();
        //        pictureBox1.setIcon(new ImageIcon(getClass().getResource("/path/to/QRCode.png")));
        pictureBox1.setHorizontalAlignment(SwingConstants.CENTER);
        tableLayoutPanel3.add(pictureBox1);

        panel13 = new JPanel();
        panel13.setBackground(Color.WHITE);
        panel13.setLayout(new BorderLayout());
        tableLayoutPanel3.add(panel13);


        // Adding components to tableLayoutPanel3
        label13 = new JLabel("");
        panel13.add(label13, BorderLayout.WEST);
        btnThanhToan = new JButton();
        btnThanhToan.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		btnThanhToan_Click();
        	}
        });
        btnThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnThanhToan.setBackground(new Color(0, 255, 0));
        btnThanhToan.setText("Thanh toán");
        panel13.add(btnThanhToan, BorderLayout.CENTER);

        // Adding components to panel16
        label15 = new JLabel("Phương thức thanh toán:");
        panel16.add(label15, BorderLayout.WEST);
        cbPTTT = new JComboBox<>(new String[]{"Tiền mặt", "Chuyển khoản"});
        panel16.add(cbPTTT, BorderLayout.CENTER);

        // Adding components to panel17
        JLabel label16 = new JLabel("Tiền thối lại:                    ");
        panel17.add(label16, BorderLayout.WEST);
        txtTienThoiLai = new JTextField();
        txtTienThoiLai.setEditable(false);
        panel17.add(txtTienThoiLai, BorderLayout.CENTER);
        
        JPanel panel10_1 = new JPanel();
        panel10_1.setBackground(Color.WHITE);
        groupBox2.add(panel10_1, BorderLayout.NORTH);
        panel10_1.setLayout(new BorderLayout());

        // Label 4
        JPanel panel4 = new JPanel();
        panel4.setBounds(0, 0, 1200, 49);
        panel4.setBackground(Color.GREEN);
        panel4.setLayout(new BorderLayout(0, 0));
        JLabel label4 = new JLabel("THÔNG TIN THANH TOÁN");
        label4.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        label4.setForeground(Color.WHITE);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        panel4.add(label4);
        getContentPane().add(panel4);
        
        // Table Layout for groupBox1
        JPanel tableLayoutPanel2 = new JPanel();
        tableLayoutPanel2.setBackground(Color.WHITE);
        tableLayoutPanel2.setLayout(new GridLayout(3, 1));
        
        // GroupBox 1
        JPanel groupBox1 = new JPanel();
        groupBox1.setBounds(878, 61, 316, 142);
        getContentPane().add(groupBox1);
        groupBox1.setLayout(new BorderLayout());
        groupBox1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "THÔNG TIN KHÁCH HÀNG", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI Semibold", Font.BOLD, 10)));
        groupBox1.setBackground(Color.WHITE);
        groupBox1.add(tableLayoutPanel2, BorderLayout.CENTER);
        
        // Adding components to tableLayoutPanel2
        JPanel panel6 = new JPanel();
        panel6.setLayout(new BorderLayout());
        txtTenKH = new JTextField();
        txtTenKH.setEditable(false);
        JLabel label5 = new JLabel("Họ tên khách hàng:  ");
        panel6.add(label5, BorderLayout.WEST);
        panel6.add(txtTenKH, BorderLayout.CENTER);
        tableLayoutPanel2.add(panel6);
        JPanel panel7 = new JPanel();
        panel7.setLayout(new BorderLayout());
        txtCMND = new JTextField();
        txtCMND.setEditable(false);
        JLabel label6 = new JLabel("CMND/CCCD:           ");
        panel7.add(label6, BorderLayout.WEST);
        panel7.add(txtCMND, BorderLayout.CENTER);
        tableLayoutPanel2.add(panel7);
        JPanel panel8 = new JPanel();
        panel8.setLayout(new BorderLayout());
        txtSL = new JTextField();
        txtSL.setEditable(false);
        JLabel label7 = new JLabel("Số lần thuê trước:    ");
        panel8.add(label7, BorderLayout.WEST);
        panel8.add(txtSL, BorderLayout.CENTER);
        tableLayoutPanel2.add(panel8);
        
        tableRoom.clearSelection();
        tableService.clearSelection();
        CbPhuThu.setSelectedIndex(0);
        cbPTTT.setSelectedIndex(0);
        this.maCTT = maCTT;
        HienThiChiTietThueDichVu();
        HienThiChiTietThuePhong();
        List<ChiTietThueDTO> listCTT = ctt.getDSChiTietThue();
        List<KhachHangDTO> listKH = kh.GetDSKH();
        DialogResult = JOptionPane.CANCEL_OPTION;
        Optional<ChiTietThueDTO> firstCTT = listCTT.stream().findFirst();
        Optional<KhachHangDTO> firstKH = listKH.stream().findFirst();
        if (firstCTT.isPresent() && firstKH.isPresent()) {
            ChiTietThueDTO cttItem = firstCTT.get();
            KhachHangDTO khItem = firstKH.get();
            String tienDatCoc = String.format("%,d VNĐ", cttItem.getTienDatCoc());
            txtCMND.setText(khItem.getCMND());
            txtDatCoc.setText(tienDatCoc);
            txtTenKH.setText(khItem.getTenKH());
            int soLan = kh.SoLanThue(khItem.getMaKH());
            txtSL.setText(String.valueOf(soLan));
            String giamGia = "0%";
            if (soLan >= 5 && soLan < 10) {
                giamGia = "5%";
            } else if (soLan >= 10 && soLan < 15) {
                giamGia = "10%";
            } else if (soLan >= 15 && soLan < 20) {
                giamGia = "15%";
            } else if (soLan >= 20) {
                giamGia = "20%";
            }
            txtGiamGia.setText(giamGia);
        }

    }
    
    private void renderTongTien() {
        int total = 0;
        for (int i = 0; i < tbRoom.getRowCount(); i++) {
            try {
                total += Integer.parseInt(tbRoom.getValueAt(i, 6).toString().replace(",", "").split(" ")[0]);
            } catch (Exception e) {
                // Do nothing
            }
        }
        txtTotalRoom.setText(String.format("%,d VNĐ", total));
        
        total = 0;
        for (int i = 0; i < tbService.getRowCount(); i++) {
            try {
                total += Integer.parseInt(tbService.getValueAt(i, 6).toString().replace(",", "").split(" ")[0]);
            } catch (Exception e) {
                // Do nothing
            }
        }
        if (total > 0)
            txtTotalService.setText(String.format("%,d VNĐ", total));
        else
            txtTotalService.setText("0 VNĐ");
    }

    private void HienThiChiTietThuePhong() {
        tbRoom.setRowCount(0);
        List<ChiTietThuePhongDTO> cttps = cttP.GetDSListCTTP(maCTT);
        List<PhongDTO> phongs = phong.getListPhong_DTO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

        var items = cttps.stream()
                         .filter(cttp -> cttp.getTinhTrang() == 2)
                         .map(cttp -> {
                             PhongDTO phong = phongs.stream().filter(p -> p.getMaP().equals(cttp.getMaP())).findFirst().orElse(null);
                             if (phong != null) {
                                 String loaiHinhThue;
                                 if (cttp.getLoaiHinhThue() == 0)
                                     loaiHinhThue = "Theo Ngày";
                                 else if (cttp.getLoaiHinhThue() == 1)
                                     loaiHinhThue = "Theo giờ";
                                 else
                                     loaiHinhThue = "Khác";

                                 return new Object[] {
                                     phong.getTenP(),
                                     loaiHinhThue,
                                     dateFormat.format(cttp.getNgayThue()),
                                     (cttp.getNgayTra() != null) ? dateFormat.format(cttp.getNgayTra()) : null,
                                     (cttp.getNgayCheckOut() != null) ? dateFormat.format(cttp.getNgayCheckOut()) : null,
                                     String.format("%,d VNĐ", cttp.getGiaThue())
                                 };
                             }
                             return null;
                         })
                         .filter(Objects::nonNull)
                         .collect(Collectors.toList());

        int stt = 0;
        for (Object[] item : items) {
            tbRoom.addRow(new Object[] { ++stt, item[0], item[1], item[2], item[3], item[4], item[5] });
        }
        tableRoom.clearSelection();
        renderTongTien();
    }

    private void HienThiChiTietThueDichVu() {
        tbService.setRowCount(0);
        List<ChiTietThueDichVuDTO> cttdvs = cttDV.GetListChiTietDichVu(maCTT);
        List<DichVuDTO> dvs = dichVu.getListDichVu();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

        var items = cttdvs.stream()
                          .map(cttdv -> {
                              DichVuDTO dv = dvs.stream().filter(d -> d.getMaDV().equals(cttdv.getMaDV())).findFirst().orElse(null);
                              if (dv != null) {
                                  return new Object[] {
                                      cttdv.getMaDV(),
                                      dv.getTenDV(),
                                      dateFormat.format(cttdv.getNgaySuDung()),
                                      cttdv.getSoLuong(),
                                      String.format("%,d VNĐ", cttdv.getGiaDV()),
                                      String.format("%,d VNĐ", cttdv.getGiaDV() * cttdv.getSoLuong())
                                  };
                              }
                              return null;
                          })
                          .filter(Objects::nonNull)
                          .collect(Collectors.toList());

        int stt = 0;
        for (Object[] item : items) {
            tbService.addRow(new Object[] { ++stt, item[0], item[1], item[2], item[3], item[4], item[5] });
        }
        tableService.clearSelection();
        renderTongTien();
    }

    private void HienThiThongTin() {
        int totalPhong = 0;
        try {
            totalPhong = Integer.parseInt(txtTotalRoom.getText().replace(",", "").split(" ")[0]);
        } catch (NumberFormatException e) {
            // Do nothing
        }

        int totalDichVu = 0;
        try {
            totalDichVu = Integer.parseInt(txtTotalService.getText().replace(",", "").split(" ")[0]);
        } catch (NumberFormatException e) {
            // Do nothing
        }

        int tienDatCoc = Integer.parseInt(txtDatCoc.getText().replace(",", "").split(" ")[0]);
        int phuThuPercent = Integer.parseInt(CbPhuThu.getSelectedItem().toString().replace("%", ""));
        int phuThu = (totalPhong + totalDichVu) * phuThuPercent / 100;
        int giamGiaPercent = Integer.parseInt(txtGiamGia.getText().replace("%", ""));
        int giamGia = (totalPhong + totalDichVu) * giamGiaPercent / 100;
        int tongThanhTien = totalPhong + totalDichVu - tienDatCoc - giamGia + phuThu;
        TongThanhTien.setText(Integer.toString(tongThanhTien));
        txtTienThoiLai.setText(Integer.toString(tongThanhTien));
    }

    private void XuatFilePDF(String maHD) {
        Document document = new Document(PageSize.A4, 50, 50, 25, 25);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(maHD + ".pdf"));
            document.open();
            String fontPath = "fonts/ARIAL.TTF"; // Đường dẫn đến tệp font trong thư mục dự án của bạn
	        BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        com.itextpdf.text.Font font = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
	        com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(bf, 13, com.itextpdf.text.Font.BOLD | com.itextpdf.text.Font.UNDERLINE, BaseColor.GRAY);

            // Title
            Paragraph p = new Paragraph("KHÁCH SẠN LUXURY", fontTitle);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            // Address
            Paragraph p2 = new Paragraph("Địa chỉ: 273 An Dương Vương, Phường 3, Quận 5, Tp Hồ Chí Minh", font);
            p2.setAlignment(Element.ALIGN_CENTER);
            document.add(p2);
            // Hotline
            Paragraph p3 = new Paragraph("Hotline Booking: 0987654321", font);
            p3.setAlignment(Element.ALIGN_CENTER);
            document.add(p3);
            // Fax
            Paragraph p4 = new Paragraph("Fax: 0987654321", font);
            p4.setAlignment(Element.ALIGN_CENTER);
            document.add(p4);
            // Empty line
            Paragraph pEmpty = new Paragraph("  ", fontTitle);
            document.add(pEmpty);

            HoaDonBUS hd = new HoaDonBUS();
            ResultSet rs = hd.getHoaDon(maHD);

            // Information section title
            Paragraph p5 = new Paragraph("THÔNG TIN HÓA ĐƠN", fontTitle);
            p5.setAlignment(Element.ALIGN_LEFT);
            document.add(p5);
            // Information about the invoice
            if (rs.next()) {
                Paragraph pMaCTT = new Paragraph("Mã chi tiết thuê: " + rs.getString(2), font);
                pMaCTT.setAlignment(Element.ALIGN_LEFT);
                Paragraph pTenNV = new Paragraph("Nhân viên lập hóa đơn: " + rs.getString(3), font);
                pTenNV.setAlignment(Element.ALIGN_LEFT);
                Paragraph pNgayLapPhieu = new Paragraph("Ngày lâp hóa đơn: " + new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(rs.getTimestamp(4)), font);
                pNgayLapPhieu.setAlignment(Element.ALIGN_LEFT);
                document.add(pMaCTT);
                document.add(pTenNV);
                document.add(pNgayLapPhieu);
                // Empty line
                Paragraph pEmpty2 = new Paragraph("  ", fontTitle);
                document.add(pEmpty2);
            }

            Paragraph p6 = new Paragraph("THÔNG TIN PHÒNG THUÊ", fontTitle);
            p6.setAlignment(Element.ALIGN_LEFT);
            document.add(p6);
            // Empty line
            Paragraph pEmpty5 = new Paragraph("  ", fontTitle);
            document.add(pEmpty5);
            ResultSet rs2 = hd.getThuePhong(maHD);
            PdfPTable table = new PdfPTable(5);
            table.getDefaultCell().setPadding(3);
            table.setWidthPercentage(100);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell cell = new PdfPCell(new Paragraph("TÊN PHÒNG", font));
            cell.setBackgroundColor(new BaseColor(240, 240, 240));
            table.addCell(cell);

            PdfPCell cell2 = new PdfPCell(new Paragraph("LOẠI HÌNH THUÊ", font));
            cell2.setBackgroundColor(new BaseColor(240, 240, 240));
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Paragraph("NGÀY THUÊ", font));
            cell3.setBackgroundColor(new BaseColor(240, 240, 240));
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Paragraph("NGÀY CHECKOUT", font));
            cell4.setBackgroundColor(new BaseColor(240, 240, 240));
            table.addCell(cell4);

            PdfPCell cell5 = new PdfPCell(new Paragraph("GIÁ THUÊ", font));
            cell5.setBackgroundColor(new BaseColor(240, 240, 240));
            table.addCell(cell5);

            // Add data from ResultSet to the table
            while (rs2.next()) {
                PdfPCell cell11 = new PdfPCell(new Paragraph(rs2.getString(1), font));
                cell11.setBackgroundColor(new BaseColor(255, 255, 255));
                table.addCell(cell11);
                String loaiHT = "";
                if (rs2.getInt(2) == 0) {
                    loaiHT = "Theo ngày";
                } else if (rs2.getInt(2) == 1) {
                    loaiHT = "Theo giờ";
                } else {
                    loaiHT = "Khác";
                }
                PdfPCell cell12 = new PdfPCell(new Paragraph(loaiHT, font));
                cell12.setBackgroundColor(new BaseColor(255, 255, 255));
                table.addCell(cell12);

                PdfPCell cell13 = new PdfPCell(new Paragraph(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(rs2.getTimestamp(3)), font));
                cell13.setBackgroundColor(new BaseColor(255, 255, 255));
                table.addCell(cell13);

                PdfPCell cell14 = new PdfPCell(new Paragraph(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(rs2.getTimestamp(4)), font));
                cell14.setBackgroundColor(new BaseColor(255, 255, 255));
                table.addCell(cell14);

                PdfPCell cell15 = new PdfPCell(new Paragraph(String.format("%,d VNĐ", rs2.getInt(5)), font));
                cell15.setBackgroundColor(new BaseColor(255, 255, 255));
                table.addCell(cell15);
            }

            // Add the table to the document
            document.add(table);
            Paragraph pEmpty11 = new Paragraph("  ", fontTitle);
            document.add(pEmpty11);

            Paragraph p10 = new Paragraph("THÔNG TIN DỊCH VỤ THUÊ", fontTitle);
            p10.setAlignment(Element.ALIGN_LEFT);
            document.add(p10);
            Paragraph pEmpty10 = new Paragraph("  ", fontTitle);
            document.add(pEmpty10);
            ResultSet rs3 = hd.getDichVu(maHD);
            PdfPTable table1 = new PdfPTable(6);
            table1.getDefaultCell().setPadding(3);
            table1.setWidthPercentage(100);
            table1.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell cellDV1 = new PdfPCell(new Paragraph("TÊN DỊCH VỤ", font));
            cellDV1.setBackgroundColor(new BaseColor(240, 240, 240));
            table1.addCell(cellDV1);

            PdfPCell cellDV2 = new PdfPCell(new Paragraph("LOẠI DỊCH VỤ", font));
            cellDV2.setBackgroundColor(new BaseColor(240, 240, 240));
            table1.addCell(cellDV2);

            PdfPCell cellDV3 = new PdfPCell(new Paragraph("NGÀY SỬ DỤNG", font));
            cellDV3.setBackgroundColor(new BaseColor(240, 240, 240));
            table1.addCell(cellDV3);

            PdfPCell cellDV4 = new PdfPCell(new Paragraph("SỐ LƯỢNG", font));
            cellDV4.setBackgroundColor(new BaseColor(240, 240, 240));
            table1.addCell(cellDV4);

            PdfPCell cellDV5 = new PdfPCell(new Paragraph("ĐƠN GIÁ", font));
            cellDV5.setBackgroundColor(new BaseColor(240, 240, 240));
            table1.addCell(cellDV5);

            PdfPCell cellDV6 = new PdfPCell(new Paragraph("THÀNH TIỀN", font));
            cellDV6.setBackgroundColor(new BaseColor(240, 240, 240));
            table1.addCell(cellDV6);

            // Add data from ResultSet to the table
            while (rs3.next()) {
                PdfPCell cell11 = new PdfPCell(new Paragraph(rs3.getString(1), font));
                cell11.setBackgroundColor(new BaseColor(255, 255, 255));
                table1.addCell(cell11);

                PdfPCell cell12 = new PdfPCell(new Paragraph(rs3.getString(2), font));
                cell12.setBackgroundColor(new BaseColor(255, 255, 255));
                table1.addCell(cell12);

                PdfPCell cell13 = new PdfPCell(new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(rs3.getTimestamp(3)), font));
                cell13.setBackgroundColor(new BaseColor(255, 255, 255));
                table1.addCell(cell13);

                PdfPCell cell14 = new PdfPCell(new Paragraph(String.valueOf(rs3.getInt(4)), font));
                cell14.setBackgroundColor(new BaseColor(255, 255, 255));
                table1.addCell(cell14);

                PdfPCell cell15 = new PdfPCell(new Paragraph(String.format("%,d VNĐ", rs3.getInt(5)), font));
                cell15.setBackgroundColor(new BaseColor(255, 255, 255));
                table1.addCell(cell15);

                PdfPCell cell16 = new PdfPCell(new Paragraph(String.format("%,d VNĐ", rs3.getInt(6)), font));
                cell16.setBackgroundColor(new BaseColor(255, 255, 255));
                table1.addCell(cell16);
            }

            // Add the table to the document
            document.add(table1);
            document.close();
            File pdfFile = new File(maHD + ".pdf");
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("PDF file not found: " + maHD + ".pdf");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void txtTienKhachDua_KeyPress(KeyEvent e) {
    	char c = e.getKeyChar();
        if (!(Character.isDigit(c) || Character.isISOControl(c))) {
            e.consume();
        }
    }

    private void txtTienKhachDua_TextChanged() {
    	String text = txtTienKhachDua.getText().trim();
        if (text.isEmpty()) {
            txtTienThoiLai.setText(TongThanhTien.getText());
        } else {
            int total = Integer.parseInt(TongThanhTien.getText());
            int tienKhachDua = Integer.parseInt(text);
            txtTienThoiLai.setText(String.valueOf(tienKhachDua - total));
        }
    }

    private void btnThanhToan_Click() {
        int total = Integer.parseInt(TongThanhTien.getText());
        if (txtTienKhachDua.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tiền khách đưa", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtTienKhachDua.requestFocus();
            return;
        }
        int tienKhachDua = Integer.parseInt(txtTienKhachDua.getText());
        if (total > tienKhachDua) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tiền khách đưa phải lớn hơn hoặc bằng tổng thành tiền", "Thông báo", JOptionPane.ERROR_MESSAGE);
            txtTienKhachDua.requestFocus();
            return;
        }
        int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán hóa đơn này không?", "Thanh toán", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (ans == JOptionPane.YES_OPTION) {
            try {
                // Thêm hóa đơn mới
                HoaDonBUS hd = new HoaDonBUS();
                String maHD = "HD" + new SimpleDateFormat("ddMMyy", Locale.getDefault()).format(new Date()) + String.format("%05d", hd.SoLuongHD(LocalDate.now().toString()));

                String GiamGia = txtGiamGia.getText().replace("%", "");
                String phuThu = CbPhuThu.getSelectedItem().toString().replace("%", "");
                hd.ThemHoaDon(maHD, maCTT, Integer.valueOf(GiamGia), Integer.valueOf(phuThu), new Date(), cbPTTT.getSelectedIndex());

                // Sửa tình trạng xử lý của phiếu thuê
                ChiTietThueBUS ctt = new ChiTietThueBUS();
                ctt.SuaTinhTrangXuLy(maCTT);

                // Xóa các phòng không thuê
                List<ChiTietThuePhongDTO> cttTP = cttP.GetDSListCTTP(maCTT);
                ChiTietThuePhongBUS cttPhongBus = new ChiTietThuePhongBUS();
                for (ChiTietThuePhongDTO ct : cttTP) {
                    if (ct.getTinhTrang() == 0) {
                        cttPhongBus.DeleteCTTP(maCTT, ct.getMaP(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(ct.getNgayThue()));
                    }
                }

                XuatFilePDF(maHD);
                
                JOptionPane.showMessageDialog(null, "Thêm hóa đơn mới thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void CbPhuThu_SelectedIndexChanged() {
        HienThiThongTin();
    }
}
