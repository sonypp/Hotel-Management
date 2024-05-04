package GUI.QuanLyDatPhong;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.ChiTietThueBUS;
import BUS.ChiTietThuePhongBUS;
import BUS.PhongBUS;
import DTO.ChiTietThueDTO;
import DTO.ChiTietThuePhongDTO;
import DTO.PhongDTO;
import GUI.Phong.ItemPhong;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BookingNew extends JPanel {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private PhongBUS phongBUS = new PhongBUS();
	private Container rowPanel;
	private FlowLayout rowLayout;
	private Container pnPhong;
	private JComboBox cbbGiaPhong;
	public JRadioButton rdbtnTheoNgay;
	public JRadioButton rdbtnTheoGio;
	public JRadioButton rdbtnKhac;
	public JDateChooser dateNgayTra;
	public JDateChooser dateNgayThue;
	
	public boolean checkBooking = false;
	public boolean isValid = false;
	private JComboBox cbbCTLP;
	private JComboBox cbbLoaiPhong;
	private JComboBox cbbTTPhong;
	private JComboBox cbbHienTrang;

	/**
	 * Create the panel.
	 */
	public BookingNew() {
		setBounds(0, 0, 1251, 835);
		setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        
        JLabel lbheader = new JLabel("Tạo phiếu đặt phòng");
        lbheader.setForeground(new Color(255, 128, 0));
        lbheader.setFont(new Font("Tahoma", Font.BOLD, 19));
        lbheader.setBounds(10, 10, 262, 31);
        add(lbheader);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 51, 1231, 136);
        add(panel);
        panel.setLayout(null);
        
        JLabel lblChiTietLoaiPhong = new JLabel("Chi tiết loại phòng:");
        lblChiTietLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblChiTietLoaiPhong.setBounds(10, 10, 127, 27);
        panel.add(lblChiTietLoaiPhong);
        
        cbbCTLP = new JComboBox();
        cbbCTLP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbCTLP.setModel(new DefaultComboBoxModel(new String[] {"", "Phòng đơn", "Phòng đôi", "Phòng gia đình"}));
        cbbCTLP.setBounds(147, 15, 127, 21);
        panel.add(cbbCTLP);
        
        JLabel lblLoaiPhong = new JLabel("Loại phòng:");
        lblLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoaiPhong.setBounds(286, 10, 89, 27);
        panel.add(lblLoaiPhong);
        
        cbbLoaiPhong = new JComboBox();
        cbbLoaiPhong.setModel(new DefaultComboBoxModel(new String[] {"", "Vip", "Thường"}));
        cbbLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbLoaiPhong.setBounds(372, 14, 127, 21);
        panel.add(cbbLoaiPhong);
        
        JLabel lblTinhTrangPhong = new JLabel("Tình trạng phòng:");
        lblTinhTrangPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTinhTrangPhong.setBounds(511, 10, 127, 27);
        panel.add(lblTinhTrangPhong);
        
        cbbTTPhong = new JComboBox();
        cbbTTPhong.setModel(new DefaultComboBoxModel(new String[] {"", "Trống", "Đang được thuê", "Chưa được dọn"}));
        cbbTTPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbTTPhong.setBounds(635, 14, 127, 21);
        panel.add(cbbTTPhong);
        
        JLabel lblGiaPhong = new JLabel("Giá phòng:");
        lblGiaPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblGiaPhong.setBounds(774, 10, 75, 27);
        panel.add(lblGiaPhong);
        
        cbbGiaPhong = new JComboBox();
        cbbGiaPhong.setModel(new DefaultComboBoxModel(new String[] {"", "Dưới 100,000 VNĐ", "Từ 100,000 VNĐ đến 200,000 VNĐ", "Từ 200,000 VNĐ đến 500,000 VNĐ", "Từ 500,000 VNĐ đến 1 triệu VNĐ", "Từ 1,000,000 VND đến 5,000,000 VNĐ", "Trên 5,000,000 VNĐ"}));
        cbbGiaPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbGiaPhong.setBounds(855, 14, 198, 21);
        panel.add(cbbGiaPhong);
        
        JLabel lblHienTrang = new JLabel("Hiện trạng:");
        lblHienTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblHienTrang.setBounds(1065, 10, 77, 27);
        panel.add(lblHienTrang);
        
        cbbHienTrang = new JComboBox();
        cbbHienTrang.setModel(new DefaultComboBoxModel(new String[] {"", "Mới", "Cũ"}));
        cbbHienTrang.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbHienTrang.setBounds(1143, 14, 82, 21);
        panel.add(cbbHienTrang);
        
        JLabel lblTìnhTrangThue = new JLabel("Tình trạng thuê:");
        lblTìnhTrangThue.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTìnhTrangThue.setBounds(10, 62, 127, 21);
        panel.add(lblTìnhTrangThue);
        
        rdbtnTheoNgay = new JRadioButton("Theo ngày");
        rdbtnTheoNgay.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		checkBooking = true;
        		 if (rdbtnTheoNgay.isSelected())
        	        {
        	        	dateNgayTra.setDateFormatString("dd/MM/yyyy");
        	        	dateNgayTra.setEnabled(true);
        	        }
        	}
        });
        buttonGroup.add(rdbtnTheoNgay);
        rdbtnTheoNgay.setBounds(6, 98, 94, 20);
        panel.add(rdbtnTheoNgay);
       
        
        rdbtnTheoGio = new JRadioButton("Theo giờ");
        rdbtnTheoGio.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		checkBooking = true;
        		if(rdbtnTheoGio.isSelected())
        		{
        			dateNgayTra.setDateFormatString("dd/MM/yyyy HH:mm:ss");
        			dateNgayTra.setEnabled(true);
        		}
        	}
        });
        buttonGroup.add(rdbtnTheoGio);
        rdbtnTheoGio.setBounds(107, 98, 94, 20);
        panel.add(rdbtnTheoGio);
        
        rdbtnKhac = new JRadioButton("Khác");
        rdbtnKhac.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		checkBooking = true;
        		if(rdbtnKhac.isSelected())
        		{
        			dateNgayTra.setDateFormatString(" ");
        			dateNgayTra.setEnabled(false);
        		}
        	}
        });
        buttonGroup.add(rdbtnKhac);
        rdbtnKhac.setBounds(213, 98, 69, 20);
        panel.add(rdbtnKhac);
        
        JButton btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		search();
        	}
        });
        btnTimKiem.setBackground(new Color(0, 128, 255));
        btnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnTimKiem.setBounds(869, 95, 117, 26);
        panel.add(btnTimKiem);
        
        JButton btnLamMoi = new JButton("Làm mới");
        btnLamMoi.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		cbbCTLP.setSelectedIndex(0);
        		cbbGiaPhong.setSelectedIndex(0);
        		cbbHienTrang.setSelectedIndex(0);
        		cbbLoaiPhong.setSelectedIndex(0);
        		cbbTTPhong.setSelectedIndex(0);
        		checkBooking = false;
        		dateNgayThue.setDateFormatString("dd/MM/yyyy HH:mm:ss");
        		dateNgayTra.setDateFormatString("dd/MM/yyyy HH:mm:ss");
        		dateNgayTra.setEnabled(true);
        		dateNgayThue.setEnabled(true);
        		Enumeration<AbstractButton> elements = buttonGroup.getElements();
		        while (elements.hasMoreElements()) {
		            AbstractButton button = elements.nextElement();
		            button.setEnabled(true);
		        }
		        rdbtnTheoNgay.setSelected(false);
		        rdbtnTheoGio.setSelected(false);
		        rdbtnKhac.setSelected(false);
        		isValid = false;
        		setListPhong(phongBUS.getListPhong_DTO());
        	}
        });
        btnLamMoi.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLamMoi.setBackground(new Color(0, 128, 128));
        btnLamMoi.setBounds(1038, 95, 117, 26);
        panel.add(btnLamMoi);
        
        dateNgayThue = new JDateChooser();
        dateNgayThue.getCalendarButton().setHideActionText(true);
        dateNgayThue.getCalendarButton().setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
        dateNgayThue.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
        dateNgayThue.getCalendarButton().setBackground((Color) null);
        dateNgayThue.setPreferredSize(new Dimension(150, 30));
        dateNgayThue.setForeground((Color) null);
        dateNgayThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateNgayThue.setDateFormatString("dd/MM/yyyy HH:mm:ss");
        dateNgayThue.setBackground((Color) null);
        dateNgayThue.setBounds(372, 88, 187, 30);
        var date = new Date();
        date.setHours(7);
        date.setMinutes(0);
        date.setSeconds(0);
        dateNgayThue.setDate(date);
        panel.add(dateNgayThue);
        
        dateNgayTra = new JDateChooser();
        dateNgayTra.setDateFormatString("dd/MM/yyyy HH:mm:ss");
        dateNgayTra.getCalendarButton().setHideActionText(true);
        dateNgayTra.getCalendarButton().setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
        dateNgayTra.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
        dateNgayTra.getCalendarButton().setBackground((Color) null);
        dateNgayTra.setPreferredSize(new Dimension(150, 30));
        dateNgayTra.setForeground((Color) null);
        dateNgayTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
        if (!rdbtnTheoNgay.isSelected() && !rdbtnTheoGio.isSelected() && !rdbtnKhac.isSelected())
        {
        dateNgayTra.setDateFormatString("dd/MM/yyyy HH:mm:ss");
        }
        dateNgayTra.setBackground((Color) null);
        dateNgayTra.setBounds(635, 88, 187, 30);
        dateNgayTra.setDate(date);
        panel.add(dateNgayTra);
        
        JLabel lblNgyThu = new JLabel("Ngày thuê: ");
        lblNgyThu.setHorizontalAlignment(SwingConstants.TRAILING);
        lblNgyThu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNgyThu.setBounds(286, 91, 89, 27);
        panel.add(lblNgyThu);
        
        JLabel lblNgyTr = new JLabel("Ngày trả: ");
        lblNgyTr.setHorizontalAlignment(SwingConstants.TRAILING);
        lblNgyTr.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNgyTr.setBounds(549, 88, 89, 27);
        panel.add(lblNgyTr);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 199, 1231, 630);
        add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblSPhng = new JLabel("Sơ đồ phòng");
        lblSPhng.setBounds(6, 6, 196, 23);
        lblSPhng.setForeground(new Color(255, 128, 0));
        lblSPhng.setFont(new Font("Tahoma", Font.BOLD, 19));
        panel_1.add(lblSPhng);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 52, 1231, 568);
        panel_1.add(scrollPane);

        pnPhong = new JPanel();
        pnPhong.setLayout(new BoxLayout(pnPhong, BoxLayout.Y_AXIS)); // Use Y_AXIS for vertical arrangement
        scrollPane.setViewportView(pnPhong);

        // Create a JPanel to contain each row of panels
//        rowPanel = new JPanel();
//        rowLayout = new FlowLayout(FlowLayout.LEFT);
//        rowLayout.setHgap(40); // Set horizontal gap between components
//        rowPanel.setLayout(rowLayout);
//        pnPhong.add(rowPanel);

        var listPhongDTO = phongBUS.getListPhong_DTO();
        setListPhong(listPhongDTO);
        

	}
	
	private void search()
	{
		if(checkBooking)
		{
			Date now = new Date();
			if(dateNgayThue.getDate().before(now))
			{
				JOptionPane.showMessageDialog(this, "Ngày giờ thuê phải lớn hơn hoặc bằng ngày giờ hiện tại", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				//region Thuê theo ngày
				if(rdbtnTheoNgay.isSelected())
				{
					if(dateNgayTra.getDate().compareTo(dateNgayThue.getDate()) <= 0)
					{
						JOptionPane.showMessageDialog(this, "Ngày giờ trả phải lớn hơn ngày giờ thuê", "Thông báo", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						var cttPhong = new ChiTietThuePhongBUS();
						var ctthue = new ChiTietThueBUS();
						var phong = new PhongBUS();
						
						List<ChiTietThuePhongDTO> cttList = cttPhong.GetDSListCTTP();
						List<ChiTietThueDTO> ctThueList = ctthue.getDSChiTietThue();
						List<PhongDTO> phongList = phong.getListPhong_DTO();

						List<PhongDTO> cttFiltered = new ArrayList<>();
				        for (ChiTietThuePhongDTO cttp : cttList) {
				            for (ChiTietThueDTO ctt : ctThueList) {
				                if (cttp.getMaCTT().equals(ctt.getMaCTT())) {
				                	if(cttp.getNgayThue().compareTo(dateNgayTra.getDate()) <= 0) {
//				                		System.out.println("Ngay tra ctt: " + cttp.getNgayTra().getHours());
//				                		System.out.println("Ngay thue tim kiem: " + dateNgayThue.getDate().toString());
					                    if (cttp.getNgayTra().compareTo(dateNgayThue.getDate()) >= 0 || cttp.getNgayTra() == null) {
						                    cttFiltered.add(phongList.stream()
						                            .filter(room -> cttp.getMaP().equals(room.getMaP()) && room.getTinhTrang() == 0)
						                            .findFirst().orElse(null));
					                    }
				                    }
				                }
				            }
				        }
						List<PhongDTO> roomsValid = phongList.stream()
						        .filter(room -> room.getTinhTrang() == 0)
						        .filter(room -> cttFiltered.stream().noneMatch(ctt -> ctt.getMaP().equals(room.getMaP())))
						        .collect(Collectors.toList());
						
						int range[] = {0, Integer.MAX_VALUE};
						String selectedItem = (String) cbbGiaPhong.getSelectedItem();
						String[] parts = selectedItem.split(" ");
						if (cbbGiaPhong.getSelectedIndex() == 1) {
						    range[1] = Integer.parseInt(parts[1].replace(",", ""));
						} else if (cbbGiaPhong.getSelectedIndex() == 6) {
						    range[0] = Integer.parseInt(parts[1].replace(",", ""));
						} else if (cbbGiaPhong.getSelectedIndex() != 0) {
						    range[0] = Integer.parseInt(parts[1].replace(",", ""));
						    range[1] = Integer.parseInt(parts[4].replace(",", ""));
						}
						
						final int start = range[0], end = range[1];

						List<PhongDTO> listTmp = roomsValid.stream()
						        .filter(room -> cbbLoaiPhong.getSelectedIndex() == 0 || room.getLoaiP() == cbbLoaiPhong.getSelectedIndex() - 1)
						        .filter(room -> cbbCTLP.getSelectedIndex() == 0 || room.getChiTietLoaiP() == cbbCTLP.getSelectedIndex() - 1)
						        .filter(room -> cbbTTPhong.getSelectedIndex() == 0 || room.getTinhTrang() == cbbTTPhong.getSelectedIndex() - 1)
						        .filter(room -> cbbHienTrang.getSelectedIndex() == 0 || room.getHienTrang() == cbbHienTrang.getSelectedIndex() - 1)
						        .filter(room -> cbbGiaPhong.getSelectedIndex() == 0 || (cbbGiaPhong.getSelectedIndex() == 0 && room.getGiaP() >= start)
						                || (room.getGiaP() >= start && room.getGiaP() <= end))
						        .collect(Collectors.toList());


						dateNgayThue.setEnabled(false);
						dateNgayTra.setEnabled(false);
						Enumeration<AbstractButton> elements = buttonGroup.getElements();
				        while (elements.hasMoreElements()) {
				            AbstractButton button = elements.nextElement();
				            button.setEnabled(false);
				        }
				        dateNgayThue.setEnabled(false);
						dateNgayTra.setEnabled(false);
						setListPhong(listTmp);
						isValid = true;
					}
				}
				//endregion
				if (rdbtnTheoGio.isSelected()) {
				    if (dateNgayTra.getDate().compareTo(dateNgayThue.getDate()) <= 0) {
				        JOptionPane.showMessageDialog(this, "Ngày giờ trả phải lớn hơn ngày giờ thuê", "Thông báo", JOptionPane.ERROR_MESSAGE);
				    } else {
				        long diffInMillis = dateNgayTra.getDate().getTime() - dateNgayThue.getDate().getTime();
				        long diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillis);

				        if (diffInMillis > 0 && diffInHours < 1) {
				            JOptionPane.showMessageDialog(this, "Có lẽ bạn muốn thuê theo ngày vui lòng kiểm tra lại", "Thông báo", JOptionPane.WARNING_MESSAGE);
				        } else if (diffInMillis == 0 && diffInHours < 1) {
				            JOptionPane.showMessageDialog(this, "Vui lòng thuê ít nhất 1 giờ", "Thông báo", JOptionPane.ERROR_MESSAGE);
				        } else {
				            ChiTietThuePhongBUS cttPhong = new ChiTietThuePhongBUS();
				            ChiTietThueBUS ctThue = new ChiTietThueBUS();
				            PhongBUS phong = new PhongBUS();

				            List<ChiTietThuePhongDTO> cttList = cttPhong.GetDSListCTTP();
				            List<ChiTietThueDTO> ctThueList = ctThue.getDSChiTietThue();
				            List<PhongDTO> phongList = phong.getListPhong_DTO();

				            List<PhongDTO> cttFiltered = new ArrayList<>();
					        for (ChiTietThuePhongDTO cttp : cttList) {
					            for (ChiTietThueDTO ctt : ctThueList) {
					                if (cttp.getMaCTT().equals(ctt.getMaCTT())
					                    && (cttp.getNgayThue().compareTo(dateNgayTra.getDate()) <= 0
					                    && (cttp.getNgayTra().compareTo(dateNgayThue.getDate()) >= 0 || cttp.getNgayTra() == null))) {
					                    cttFiltered.add(phongList.stream()
					                            .filter(room -> cttp.getMaP().equals(room.getMaP()) && room.getTinhTrang() == 0)
					                            .findFirst().orElse(null));
					                }
					            }
					        }

				            // Filter and map phongList to get roomsValid
				            List<PhongDTO> roomsValid = phongList.stream()
				                    .filter(room -> room.getTinhTrang() == 0)
				                    .filter(room -> cttFiltered.stream().noneMatch(ctt -> ctt.getMaP().equals(room.getMaP())))
				                    .collect(Collectors.toList());

				            int[] range = { 0, Integer.MAX_VALUE };
				            String selectedItem = (String) cbbGiaPhong.getSelectedItem();
				            String[] parts = selectedItem.split(" ");
				            if (cbbGiaPhong.getSelectedIndex() == 1) {
				                range[1] = Integer.parseInt(parts[1].replace(",", ""));
				            } else if (cbbGiaPhong.getSelectedIndex() == 6) {
				                range[0] = Integer.parseInt(parts[1].replace(",", ""));
				            } else if (cbbGiaPhong.getSelectedIndex() != 0) {
				                range[0] = Integer.parseInt(parts[1].replace(",", ""));
				                range[1] = Integer.parseInt(parts[4].replace(",", ""));
				            }

				            final int start = range[0], end = range[1];

				            List<PhongDTO> listTmp = roomsValid.stream()
				                .filter(room -> cbbLoaiPhong.getSelectedIndex() == 0 || room.getLoaiP() == cbbLoaiPhong.getSelectedIndex() - 1)
				                .filter(room -> cbbCTLP.getSelectedIndex() == 0 || room.getChiTietLoaiP() == cbbCTLP.getSelectedIndex() - 1)
				                .filter(room -> cbbTTPhong.getSelectedIndex() == 0 || room.getTinhTrang() == cbbTTPhong.getSelectedIndex() - 1)
				                .filter(room -> cbbHienTrang.getSelectedIndex() == 0 || room.getHienTrang() == cbbHienTrang.getSelectedIndex() - 1)
				                .filter(room -> cbbGiaPhong.getSelectedIndex() == 0 || (cbbGiaPhong.getSelectedIndex() == 0 && room.getGiaP() >= start)
				                    || (room.getGiaP() >= start && room.getGiaP() <= end))
				                .collect(Collectors.toList());

				            dateNgayThue.setEnabled(false);
							dateNgayTra.setEnabled(false);
							Enumeration<AbstractButton> elements = buttonGroup.getElements();
					        while (elements.hasMoreElements()) {
					            AbstractButton button = elements.nextElement();
					            button.setEnabled(false);
					        }
					        dateNgayThue.setEnabled(false);
							dateNgayTra.setEnabled(false);
							setListPhong(listTmp);
							isValid = true;
				        }
				    }
				}
				//endregion
				//region Thuê chưa xác định ngày trả
				else if(rdbtnKhac.isSelected()) {
				    ChiTietThuePhongBUS cttPhong = new ChiTietThuePhongBUS();
				    ChiTietThueBUS ctThue = new ChiTietThueBUS();
				    PhongBUS phong = new PhongBUS();

				    List<ChiTietThuePhongDTO> cttList = cttPhong.GetDSListCTTP();
				    List<ChiTietThueDTO> ctThueList = ctThue.getDSChiTietThue();
				    List<PhongDTO> phongList = phong.getListPhong_DTO();

				    List<PhongDTO> cttFiltered = cttList.stream()
				            // Join cttList with ctThueList on MaCTT
				            .flatMap(cttp -> ctThue.getDSChiTietThue().stream()
				                    .filter(ctt -> cttp.getMaCTT().equals(ctt.getMaCTT()))
				                    .map(ctt -> new Object[]{cttp, ctt}))
				            // Join with phongList on MaP
				            .flatMap(arr -> phongList.stream()
				                    .filter(room -> ((ChiTietThuePhongDTO) arr[0]).getMaP().equals(room.getMaP()))
				                    .map(room -> new Object[]{arr[0], arr[1], room}))
				            // Filter by conditions
				            .filter(arr -> {
				                ChiTietThuePhongDTO cttp = (ChiTietThuePhongDTO) arr[0];
				                ChiTietThueDTO ctt = (ChiTietThueDTO) arr[1];
				                PhongDTO room = (PhongDTO) arr[2];
				                return ctt.getTinhTrangXuLy() == 0 &&
				                        (((cttp.getNgayThue().compareTo(dateNgayThue.getDate()) <= 0 &&
				                                (cttp.getNgayTra().compareTo(dateNgayThue.getDate()) >= 0 || cttp.getNgayTra() == null)))
				                                || cttp.getNgayThue().compareTo(dateNgayThue.getDate()) >= 0);
				            })
				            .map(arr -> (PhongDTO) arr[2]) // Map to PhongDTO
				            .collect(Collectors.toList());

				    // Filter and map phongList to get roomsValid
				    List<PhongDTO> roomsValid = phongList.stream()
				            .filter(room -> room.getTinhTrang() == 0)
				            .filter(room -> cttFiltered.stream().noneMatch(ctt -> ctt.getMaP().equals(room.getMaP())))
				            .collect(Collectors.toList());

				    int[] range = { 0, Integer.MAX_VALUE };
				    String selectedItem = (String) cbbGiaPhong.getSelectedItem();
				    String[] parts = selectedItem.split(" ");
				    if (cbbGiaPhong.getSelectedIndex() == 1) {
				        range[1] = Integer.parseInt(parts[1].replace(",", ""));
				    } else if (cbbGiaPhong.getSelectedIndex() == 6) {
				        range[0] = Integer.parseInt(parts[1].replace(",", ""));
				    } else if (cbbGiaPhong.getSelectedIndex() != 0) {
				        range[0] = Integer.parseInt(parts[1].replace(",", ""));
				        range[1] = Integer.parseInt(parts[4].replace(",", ""));
				    }

				    final int start = range[0], end = range[1];

				    List<PhongDTO> listTmp = roomsValid.stream()
				        .filter(room -> cbbLoaiPhong.getSelectedIndex() == 0 || room.getLoaiP() == cbbLoaiPhong.getSelectedIndex() - 1)
				        .filter(room -> cbbCTLP.getSelectedIndex() == 0 || room.getChiTietLoaiP() == cbbCTLP.getSelectedIndex() - 1)
				        .filter(room -> cbbTTPhong.getSelectedIndex() == 0 || room.getTinhTrang() == cbbTTPhong.getSelectedIndex() - 1)
				        .filter(room -> cbbHienTrang.getSelectedIndex() == 0 || room.getHienTrang() == cbbHienTrang.getSelectedIndex() - 1)
				        .filter(room -> cbbGiaPhong.getSelectedIndex() == 0 || (cbbGiaPhong.getSelectedIndex() == 0 && room.getGiaP() >= start)
				            || (room.getGiaP() >= start && room.getGiaP() <= end))
				        .collect(Collectors.toList());

				    dateNgayThue.setEnabled(false);
				    dateNgayTra.setEnabled(false);
				    Enumeration<AbstractButton> elements = buttonGroup.getElements();
			        while (elements.hasMoreElements()) {
			            AbstractButton button = elements.nextElement();
			            button.setEnabled(false);
			        }
			        dateNgayThue.setEnabled(false);
					dateNgayTra.setEnabled(false);
				    setListPhong(listTmp);
				    isValid = true;
				}
				//endregion

			}
		}
		else
		{
			int[] range = {0, Integer.MAX_VALUE}; // Khởi tạo một mảng lưu giá trị start và end
			if (cbbGiaPhong.getSelectedIndex() == 1) {
			    range[1] = Integer.parseInt(cbbGiaPhong.getSelectedItem().toString().split(" ")[1].replace(",", ""));
			} else if (cbbGiaPhong.getSelectedIndex() == 6) {
			    range[0] = Integer.parseInt(cbbGiaPhong.getSelectedItem().toString().split(" ")[1].replace(",", ""));
			} else if (cbbGiaPhong.getSelectedIndex() != 0) {
			    range[0] = Integer.parseInt(cbbGiaPhong.getSelectedItem().toString().split(" ")[1].replace(",", ""));
			    range[1] = Integer.parseInt(cbbGiaPhong.getSelectedItem().toString().split(" ")[4].replace(",", ""));
			}

			final int start = range[0];
			final int end = range[1];

			PhongBUS phong = new PhongBUS();
			List<PhongDTO> list = phong.getListPhong_DTO();
			List<PhongDTO> listTmp = list.stream()
			    .filter(room -> cbbLoaiPhong.getSelectedIndex() == 0 || room.getLoaiP() == cbbLoaiPhong.getSelectedIndex() - 1)
			    .filter(room -> cbbCTLP.getSelectedIndex() == 0 || room.getChiTietLoaiP() == cbbCTLP.getSelectedIndex() - 1)
			    .filter(room -> cbbTTPhong.getSelectedIndex() == 0 || room.getTinhTrang() == cbbTTPhong.getSelectedIndex() - 1)
			    .filter(room -> cbbHienTrang.getSelectedIndex() == 0 || room.getHienTrang() == cbbHienTrang.getSelectedIndex() - 1)
			    .filter(room -> cbbGiaPhong.getSelectedIndex() == 0 || (cbbGiaPhong.getSelectedIndex() == 6 && room.getGiaP() >= start)
			        || (room.getGiaP() >= start && room.getGiaP() <= end))
			    .collect(Collectors.toList());

			setListPhong(listTmp);


		}
	}
	
	private void setListPhong(List<PhongDTO> listPhongDTO)
	{
		pnPhong.removeAll();
		pnPhong.revalidate();
		pnPhong.repaint();
		rowPanel = new JPanel();
        rowLayout = new FlowLayout(FlowLayout.LEFT);
        rowLayout.setHgap(40); // Set horizontal gap between components
        rowPanel.setLayout(rowLayout);
        pnPhong.add(rowPanel);
		for (int i = 0; i < listPhongDTO.size(); i++) {
            ItemPhong phong = new ItemPhong(listPhongDTO.get(i));
            phong.setPreferredSize(new Dimension(350, 300));
            phong.setBackground(Color.GREEN);
            phong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            rowPanel.add(phong);

            // Create a new rowPanel after every third panel
            if ((i + 1) % 3 == 0 && i != 0) {
                rowPanel = new JPanel();
                rowPanel.setLayout(rowLayout);
                pnPhong.add(Box.createRigidArea(new Dimension(0, 40))); // Add vertical spacing
                pnPhong.add(rowPanel);
            }
        }
	}
}
