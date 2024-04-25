package GUI.QuanLyDatPhong;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BUS.PhongBUS;
import DTO.PhongDTO;
import GUI.Phong.ItemPhong;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.LayoutManager;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class TaoPhieuDatPhong extends JPanel {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private PhongBUS phongBUS = new PhongBUS();
	private Container rowPanel;
	private FlowLayout rowLayout;
	private Container pnPhong;
	private JRadioButton rdbtnTheoNgay;
	private JRadioButton rdbtnTheoGio;
	private JRadioButton rdbtnKhac;
	private JDateChooser dateNgayTra;

	/**
	 * Create the panel.
	 */
	public TaoPhieuDatPhong() {
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
        
        JComboBox cbbCTLP = new JComboBox();
        cbbCTLP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbCTLP.setModel(new DefaultComboBoxModel(new String[] {"", "Phòng đơn", "Phòng đôi", "Phòng gia đình"}));
        cbbCTLP.setBounds(147, 15, 127, 21);
        panel.add(cbbCTLP);
        
        JLabel lblLoaiPhong = new JLabel("Loại phòng:");
        lblLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblLoaiPhong.setBounds(286, 10, 89, 27);
        panel.add(lblLoaiPhong);
        
        JComboBox cbbLoaiPhong = new JComboBox();
        cbbLoaiPhong.setModel(new DefaultComboBoxModel(new String[] {"", "Vip", "Thường"}));
        cbbLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbLoaiPhong.setBounds(372, 14, 127, 21);
        panel.add(cbbLoaiPhong);
        
        JLabel lblTinhTrangPhong = new JLabel("Tình trạng phòng:");
        lblTinhTrangPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTinhTrangPhong.setBounds(511, 10, 127, 27);
        panel.add(lblTinhTrangPhong);
        
        JComboBox cbbTTPhong = new JComboBox();
        cbbTTPhong.setModel(new DefaultComboBoxModel(new String[] {"", "Trống", "Đang được thuê", "Chưa được dọn"}));
        cbbTTPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbTTPhong.setBounds(635, 14, 127, 21);
        panel.add(cbbTTPhong);
        
        JLabel lblGiaPhong = new JLabel("Giá phòng:");
        lblGiaPhong.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblGiaPhong.setBounds(774, 10, 75, 27);
        panel.add(lblGiaPhong);
        
        JComboBox cbbGiaPhong = new JComboBox();
        cbbGiaPhong.setModel(new DefaultComboBoxModel(new String[] {"", "Dưới 100,000VNĐ", "Từ 100,000VNĐ đến 500,000VNĐ", "Từ 500,000VNĐ đến 1 triệu VNĐ", "Trên 1 triệu VNĐ"}));
        cbbGiaPhong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        cbbGiaPhong.setBounds(855, 14, 198, 21);
        panel.add(cbbGiaPhong);
        
        JLabel lblHienTrang = new JLabel("Hiện trạng:");
        lblHienTrang.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblHienTrang.setBounds(1065, 10, 77, 27);
        panel.add(lblHienTrang);
        
        JComboBox cbbHienTrang = new JComboBox();
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
        		 if (rdbtnTheoNgay.isSelected())
        	        {
        	        	dateNgayTra.setDateFormatString("dd/MM/yyyy");
        	        }
        	}
        });
        buttonGroup.add(rdbtnTheoNgay);
        rdbtnTheoNgay.setBounds(6, 98, 94, 20);
        panel.add(rdbtnTheoNgay);
       
        
        rdbtnTheoGio = new JRadioButton("Theo giờ");
        buttonGroup.add(rdbtnTheoGio);
        rdbtnTheoGio.setBounds(107, 98, 94, 20);
        panel.add(rdbtnTheoGio);
        
        rdbtnKhac = new JRadioButton("Khác");
        buttonGroup.add(rdbtnKhac);
        rdbtnKhac.setBounds(213, 98, 69, 20);
        panel.add(rdbtnKhac);
        
        JButton btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(new Color(0, 128, 255));
        btnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnTimKiem.setBounds(869, 95, 117, 26);
        panel.add(btnTimKiem);
        
        JButton btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLamMoi.setBackground(new Color(0, 128, 128));
        btnLamMoi.setBounds(1038, 95, 117, 26);
        panel.add(btnLamMoi);
        
        JDateChooser dateNgayThue = new JDateChooser();
        dateNgayThue.getCalendarButton().setHideActionText(true);
        dateNgayThue.getCalendarButton().setForeground(UIManager.getColor("InternalFrame.activeTitleBackground"));
        dateNgayThue.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
        dateNgayThue.getCalendarButton().setBackground((Color) null);
        dateNgayThue.setPreferredSize(new Dimension(150, 30));
        dateNgayThue.setForeground((Color) null);
        dateNgayThue.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateNgayThue.setDateFormatString("dd/MM/yyyy HH:mm:ss");
        dateNgayThue.setBackground((Color) null);
        dateNgayThue.setBounds(372, 88, 167, 30);
        var date = new Date();
        date.setHours(7);
        date.setMinutes(0);
        date.setSeconds(0);
        dateNgayThue.setDate(date);
        panel.add(dateNgayThue);
        
        dateNgayTra = new JDateChooser();
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
        dateNgayTra.setBounds(635, 88, 167, 30);
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
        rowPanel = new JPanel();
        rowLayout = new FlowLayout(FlowLayout.LEFT);
        rowLayout.setHgap(40); // Set horizontal gap between components
        rowPanel.setLayout(rowLayout);
        pnPhong.add(rowPanel);

        var listPhongDTO = phongBUS.getListPhong_DTO();
        setListPhong(listPhongDTO);
        

	}
	
	private void setListPhong(List<PhongDTO> listPhongDTO)
	{
		for (int i = 0; i < listPhongDTO.size(); i++) {
            ItemPhong phong = new ItemPhong(listPhongDTO.get(i));
            phong.setPreferredSize(new Dimension(350, 250));
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
