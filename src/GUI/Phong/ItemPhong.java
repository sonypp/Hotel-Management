package GUI.Phong;

import javax.swing.*;

import DTO.PhongDTO;
import GUI.QuanLyDatPhong.PhieuDatPhong;
import GUI.QuanLyDatPhong.TaoPhieuDatPhong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import BUS.PhongBUS;

public class ItemPhong extends JPanel {
    private JLabel lbTT, lbGiaP, lbCTLP, lbLoaiP, lbTime, lbRealDay;
    private JPanel panel1;
    private JTextField txtMaCTT, txtTenKH, txtNgayTra;
    private Timer timerRealTime;
    private JMenuItem mItemBooking, mItemCleanRoom, mItemInfo;
    private PhongDTO phongDTO;
    private JLabel lbDay;
    private JLabel lblHienTrang;
    private JLabel temp;
    private JPanel panel1_1;
    private JLabel lblTnPhng_1;
    private JPanel panel;
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JTextField txtHoTen;
    private JTextField txtMaPhieuThue;
    private JTextField txtNgaytra;
    private JPanel pnTenPhong;
    private JLabel lblTnPhng_2;
    
    private PhongBUS phongBUS = new PhongBUS();

    public ItemPhong(PhongDTO phongDTO) {
        this.phongDTO = phongDTO;
        initializeComponents();
    }

    private void initializeComponents() {
        lbTT = new JLabel();
        lbTT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbRealDay = new JLabel();

        panel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int w = getWidth();
                int h = getHeight();
                var status = phongDTO.getTinhTrang();
                Color color1, color2;
                switch (status)
                {
                	case 0:
                		color1 = new Color(76, 175, 80);
                		color2 = new Color(0, 255, 0);
                		break;
                	case 1:
                		color1 = new Color(76, 175, 80);
                		color2 = new Color(0, 255, 0);
                		break;
                	default:
                		color1 = new Color(184, 134, 11);
                		color2 = new Color(255, 255, 153);
                		break;
                }
                GradientPaint gp = new GradientPaint(0, 0, color2, 0, h, color1);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
                g2d.dispose();
            }
        };
        panel1.setBackground(new Color(255, 255, 255));

        txtMaCTT = new JTextField();
        txtTenKH = new JTextField();
        txtNgayTra = new JTextField();

        mItemBooking = new JMenuItem("Đặt phòng");
        mItemInfo = new JMenuItem("Thông tin phòng");
        mItemCleanRoom = new JMenuItem("Dọn phòng");

        timerRealTime = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update real-time labels
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd/MM/yyyy"); // Include day of the week
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                lbDay.setText(getFormattedDate(dateFormat));
                lbTime.setText(timeFormat.format(new Date()));
            }
        });

        // Set initial values
        setupUI();
        refreshUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        panel1.setLayout(new BorderLayout());
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel infoPanel = new JPanel(new GridLayout(4, 2)); // Increased rows to accommodate lbRealTime
        infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        infoPanel.setOpaque(false);
        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.add(lbTT);
        lbLoaiP = new JLabel();
        lbLoaiP.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbLoaiP.setText("Loại phòng");
        infoPanel.add(lbLoaiP);
        lbCTLP = new JLabel();
        lbCTLP.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbCTLP.setText("CTLP");
        infoPanel.add(lbCTLP);

        lbDay = new JLabel();
        lbDay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbDay.setText("Day");
        infoPanel.add(lbDay);

        lblHienTrang = new JLabel();
        lblHienTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblHienTrang.setText("  " + ((phongDTO.getHienTrang() == 0)? "Mới" : "Cũ"));
        infoPanel.add(lblHienTrang);
        lbTime = new JLabel();
        lbTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbTime.setText("Time");
        infoPanel.add(lbTime); // Add lbRealTime to the infoPanel
        lbGiaP = new JLabel("  " + phongDTO.getGiaP() + " VNĐ");
        lbGiaP.setFont(new Font("Arial", Font.BOLD, 22));
        lbGiaP.setForeground(new Color(0, 0, 0));
        infoPanel.add(lbGiaP);

        panel1.add(infoPanel, BorderLayout.CENTER);
        
        temp = new JLabel("");
        temp.setForeground(new Color(255, 87, 34));
        temp.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(temp);

        setContextMenus(phongDTO.getTinhTrang());

        timerRealTime.start();

        add(panel1, BorderLayout.CENTER);
        
        panel1_1 = new JPanel() {
        	protected void paintComponent(Graphics g) {
        	}
        };
        panel1_1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel1_1.setBackground(Color.WHITE);
        panel1.add(panel1_1, BorderLayout.SOUTH);
        panel1_1.setLayout(new BorderLayout());
        
        JLabel lblTnPhng_1 = new JLabel("THÔNG TIN KHÁCH THUÊ");
        lblTnPhng_1.setBackground(new Color(255, 13, 13));
        lblTnPhng_1.setOpaque(true); // Set opaque to true
        lblTnPhng_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTnPhng_1.setForeground(Color.BLACK);
        lblTnPhng_1.setFont(new Font("Arial", Font.BOLD, 16));
        
        LineBorder lineBorder = new LineBorder(Color.BLACK, 2);
        
        lblTnPhng_1.setBorder(BorderFactory.createCompoundBorder(lineBorder, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        panel1_1.add(lblTnPhng_1, BorderLayout.NORTH);
        
        panel = new JPanel();
        panel.setBackground(new Color(255, 60, 60));
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel1_1.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(3, 2, 0, 0));
        
        lbl1 = new JLabel();
        lbl1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl1.setHorizontalAlignment(SwingConstants.TRAILING);
        lbl1.setText("Họ tên khách thuê:  ");
        panel.add(lbl1);
        
        txtHoTen = new JTextField();
        txtHoTen.setEditable(false);
        panel.add(txtHoTen);
        txtHoTen.setColumns(10);
        
        lbl2 = new JLabel();
        lbl2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl2.setHorizontalAlignment(SwingConstants.TRAILING);
        lbl2.setText("Mã phiếu thuê:  ");
        panel.add(lbl2);
        
        txtMaPhieuThue = new JTextField();
        txtMaPhieuThue.setEditable(false);
        txtMaPhieuThue.setColumns(10);
        panel.add(txtMaPhieuThue);
        
        lbl3 = new JLabel();
        lbl3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl3.setHorizontalAlignment(SwingConstants.TRAILING);
        lbl3.setText("Ngày trả:  ");
        panel.add(lbl3);
        
        txtNgaytra = new JTextField();
        txtNgaytra.setEditable(false);
        txtNgaytra.setColumns(10);
        panel.add(txtNgaytra);
        
        pnTenPhong = new JPanel();
        pnTenPhong.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel1.add(pnTenPhong, BorderLayout.NORTH);
        pnTenPhong.setLayout(new BoxLayout(pnTenPhong, BoxLayout.X_AXIS));
        pnTenPhong.setLayout(new BorderLayout(0, 0));
        
        lblTnPhng_2 = new JLabel(phongDTO.getTenP());
        lblTnPhng_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblTnPhng_2.setForeground(Color.WHITE);
        lblTnPhng_2.setFont(new Font("Arial", Font.BOLD, 20));
        pnTenPhong.add(lblTnPhng_2);
    }

    private void setContextMenus(int status) {
        mItemBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for booking menu item
                handleBooking();
            }
        });
        
        mItemInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for booking menu item
                showInfo();
            }
        });

        mItemCleanRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action for clean room menu item
                handleCleanRoom();
                refreshUI();
            }
        });

        panel1.setComponentPopupMenu(createPopupMenu(status));
    }

    private JPopupMenu createPopupMenu(int status) {
        JPopupMenu popupMenu = new JPopupMenu();
        switch(status) {
        case 0:
        	popupMenu.add(mItemBooking);
            popupMenu.add(mItemInfo);
        	break;
        case 1:
        	popupMenu.add(mItemBooking);
            popupMenu.add(mItemInfo);
        	break;
        default:
        	popupMenu.add(mItemInfo);
        	popupMenu.add(mItemCleanRoom);
        	break;
        }
        
        return popupMenu;
    }

    private void handleBooking() {
    	TaoPhieuDatPhong parent = (TaoPhieuDatPhong)getTaoPhieDatPhongForm(this);
    	if(parent.isValid)
    	{
	    	var phieuDatPhong = new PhieuDatPhong();
	    	var popupFrame = new JFrame("Đặt phòng");
	        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			popupFrame.setSize(1260, 900);
			popupFrame.getContentPane().add(phieuDatPhong);
			popupFrame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
	    	if(parent.rdbtnTheoNgay.isSelected())
	    	{
	    		var date = parent.dateNgayTra.getDate();
	    		var ngayThueDate = parent.dateNgayThue.getDate();
	    		var ngayTraDate = new Date(date.getYear(), date.getMonth(), date.getDay(), ngayThueDate.getHours(), ngayThueDate.getMinutes(), ngayThueDate.getSeconds());
	    		LocalDateTime ngayThueLocal = ngayThueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    		LocalDateTime ngayTraLocal = ngayTraDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    		var duration = Duration.between(ngayThueLocal, ngayTraLocal);
	    		int gia = 0;
	    		if(phongDTO.getHienTrang() == 0)
	    		{
	    			gia = (int) (duration.toDays() * phongDTO.getGiaP());
	    		}
	    		else
	    		{
	    			gia = (int) (duration.toDays() * phongDTO.getGiaP() / 2);
	    		}
	    		var data = phieuDatPhong.data;
	    		data.addRow(new Object[] {phongDTO.getMaP(), phongDTO.getTenP(), "Đang xử lý", "Theo ngày", ngayThueDate.toString(), ngayTraDate.toString(), "", gia});
	    	}
	    	else if(parent.rdbtnTheoGio.isSelected())
	    	{
	    		var ngayTraDate = parent.dateNgayTra.getDate();
	    		var ngayThueDate = parent.dateNgayThue.getDate();
	    		LocalDateTime ngayThueLocal = ngayThueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    		LocalDateTime ngayTraLocal = ngayTraDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    		var duration = Duration.between(ngayThueLocal, ngayTraLocal);
	    		double tmp = 0;
	    		switch ((int)duration.toHours())
	    		{
		    		case 1:
		    			tmp = phongDTO.getGiaP() * 0.2 + phongDTO.getGiaP() * duration.toDays();
		    			break;
		    		case 2:
		    			tmp = phongDTO.getGiaP() * 0.1 + phongDTO.getGiaP() * duration.toDays();
		    			break;
		    		default:
		    			tmp = phongDTO.getGiaP() * 0.05 + phongDTO.getGiaP() * duration.toDays();
		    			break;
	    		}
	    		if(phongDTO.getHienTrang() == 1)
	    		{
	    			tmp /= 2;
	    		}
	    		int gia = (int)tmp;
	    		var data = phieuDatPhong.data;
	    		data.addRow(new Object[] {phongDTO.getMaP(), phongDTO.getTenP(), "Đang xử lý", "Theo giờ", ngayThueDate.toString(), ngayTraDate.toString(), "", gia});
	    	}
	    	else
	    	{
	    		var ngayThueDate = parent.dateNgayThue.getDate();
	    		int gia = 0;
	    		if(phongDTO.getHienTrang() == 0)
	    		{
	    			gia = phongDTO.getGiaP();
	    		}
	    		else
	    		{
	    			gia = phongDTO.getGiaP() / 2;
	    		}
	    		var data = phieuDatPhong.data;
	    		data.addRow(new Object[] {phongDTO.getMaP(), phongDTO.getTenP(), "Đang xử lý", "Khác", ngayThueDate.toString(), "Chưa xác định", "", gia});
	    	}
	        
	    	phieuDatPhong.hienThiDanhSachPhongThue();
	    	popupFrame.setVisible(true);
	        phieuDatPhong.setVisible(true);
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Vui lòng chọn hình thức thuê và chọn thời gian phù hợp và bấm tìm kiếm", "Thông báo", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private Component getTaoPhieDatPhongForm(Component x)
    {
    	if(x instanceof TaoPhieuDatPhong)
    		return x;
    	return getTaoPhieDatPhongForm(x.getParent());
    }
    
    private void showInfo() {
        var pnThemPhong = new ThemPhongPanel(2, phongDTO);
        var popupFrame = new JFrame("Thông tin phòng");
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popupFrame.setSize(1140, 770);
		popupFrame.getContentPane().add(pnThemPhong);
		popupFrame.setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
		popupFrame.setVisible(true);
		pnThemPhong.setVisible(true);
    }

    private void handleCleanRoom() {
        phongBUS.donPhong(phongDTO.getMaP());
        this.phongDTO.setTinhTrang(0);
        JOptionPane.showMessageDialog(null, "Dọn phòng này thành công, hiện có thể đặt phòng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void refreshUI() {
        int status = phongDTO.getTinhTrang();
        lbTT.setText(getStatusString(phongDTO.getTinhTrang()));
        txtMaCTT.setText(phongDTO.getMaP());
        lbCTLP.setText(getRoomTypeString(phongDTO.getChiTietLoaiP()));
        lbLoaiP.setText(getRoomCategoryString(phongDTO.getLoaiP()));

        switch (status) {
            case 0:
            	pnTenPhong.setBackground(new Color(0, 130, 0));
            	panel1_1.setVisible(false);
                break;
            case 1:
//            	pnTenPhong.setBackground(new Color(0, 130, 0));
            	pnTenPhong.setBackground(new Color(139, 0, 0));
            	panel1_1.setVisible(true);
                break;
            case 2:
            	pnTenPhong.setBackground(new Color(184, 134, 11));
            	panel1_1.setVisible(false);
                break;
            default:
                break;
        }
    }

    private String getStatusString(int status) {
        switch (status) {
            case 0:
                return "  Phòng trống";
            case 1:
                return "  Phòng có khách";
            case 2:
                return "  Phòng chưa dọn";
            default:
                return "";
        }
    }

    private String getRoomTypeString(int type) {
        switch (type) {
            case 0:
                return "  Phòng đơn";
            case 1:
                return "  Phòng đôi";
            case 2:
                return "  Phòng gia đình";
            default:
                return "";
        }
    }

    private String getRoomCategoryString(int category) {
        return category == 0 ? "Phòng Vip" : "Phòng Thường";
    }
    
    private String getFormattedDate(SimpleDateFormat dateFormat) {
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    
}
