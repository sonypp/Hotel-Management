package GUI.QuanLyDatPhong;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BUS.*;
import DTO.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FormChiTietThue extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txt_TenKH;
	private JTextField txt_CMND;
	private JTextField txt_SDT;
	private JTextField txt_TenNV;
	private JTextField txt_NgayLapPhieu;
	private JTextField txtTienDatCoc;
	private JTextField txt_TinhTrang;
	public JComboBox cbMaCTT;
	
	ChiTietThueBUS cttBus = new ChiTietThueBUS();
	KhachHangBUS khachHang = new KhachHangBUS();
	NhanVienBUS nhanVien = new NhanVienBUS();
	
	Color pnClicked = new Color(230, 230, 250); // Lavender
	Color pnBorder = new Color(100, 149, 237); // CornflowerBlue
	Color white = Color.WHITE;
	private JPanel pnSERVICE;
	private JPanel pnROOM;
	private JPanel pnALL;
	private JPanel pnContent;

	/**
	 * Create the panel.
	 */
	public FormChiTietThue(int option, String maCTT) {
		setBounds(0, 0, 1251, 835);
		setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 27, 373, 798);
        add(panel);
        panel.setLayout(null);
        
        pnALL = new JPanel();
        pnALL.setBackground(new Color(255, 255, 255));
        pnALL.setBorder(new LineBorder(new Color(255, 169, 128), 8));
        pnALL.setBounds(0, 0, 373, 94);
        panel.add(pnALL);
        pnALL.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Chi tiết thuê");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel_1.setBounds(10, 10, 170, 34);
        pnALL.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Thông tin phiếu thuê");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
        lblNewLabel_1_1.setBounds(10, 50, 170, 34);
        pnALL.add(lblNewLabel_1_1);
        
        pnROOM = new JPanel();
        pnROOM.setLayout(null);
        pnROOM.setBorder(new LineBorder(new Color(255, 169, 128), 8));
        pnROOM.setBackground(Color.WHITE);
        pnROOM.setBounds(0, 85, 373, 94);
        panel.add(pnROOM);
        
        JLabel lbChiTietThue = new JLabel("Thuê phòng");
        lbChiTietThue.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lbChiTietThue.setBounds(10, 10, 170, 34);
        pnROOM.add(lbChiTietThue);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Thuê phòng mới cho khách hàng");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
        lblNewLabel_1_1_1.setBounds(10, 50, 257, 34);
        pnROOM.add(lblNewLabel_1_1_1);
        
        pnSERVICE = new JPanel();
        pnSERVICE.setLayout(null);
        pnSERVICE.setBorder(new LineBorder(new Color(255, 169, 128), 8));
        pnSERVICE.setBackground(Color.WHITE);
        pnSERVICE.setBounds(0, 174, 373, 94);
        panel.add(pnSERVICE);
        
        JLabel lblThuDchV = new JLabel("Thuê dịch vụ");
        lblThuDchV.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lblThuDchV.setBounds(10, 10, 170, 34);
        pnSERVICE.add(lblThuDchV);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Thuê dịch vụ mới khách hàng");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
        lblNewLabel_1_1_1_1.setBounds(10, 50, 257, 34);
        pnSERVICE.add(lblNewLabel_1_1_1_1);
        
        JLabel lblNewLabel_2 = new JLabel("Thông tin phiếu thuê");
        lblNewLabel_2.setForeground(new Color(255, 112, 43));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_2.setBounds(0, 269, 373, 43);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Mã chi tiết thuê:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(10, 322, 157, 28);
        panel.add(lblNewLabel_3);
        
        JLabel lblN = new JLabel("Họ tên khách hàng:");
        lblN.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN.setBounds(13, 391, 144, 36);
        panel.add(lblN);
        
        cbMaCTT = new JComboBox();
        cbMaCTT.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		cbMaCTT_ItemStateChanged();
        	}
        });
        cbMaCTT.setBounds(10, 360, 353, 21);
        panel.add(cbMaCTT);
        
        txt_TenKH = new JTextField();
        txt_TenKH.setBounds(10, 425, 353, 19);
        panel.add(txt_TenKH);
        txt_TenKH.setColumns(10);
        
        JLabel lblCmndcccd = new JLabel("CMND/CCCD");
        lblCmndcccd.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCmndcccd.setBounds(13, 454, 144, 43);
        panel.add(lblCmndcccd);
        
        txt_CMND = new JTextField();
        txt_CMND.setColumns(10);
        txt_CMND.setBounds(10, 489, 353, 19);
        panel.add(txt_CMND);
        
        JLabel lblN_1_1 = new JLabel("Số điện thoại:");
        lblN_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN_1_1.setBounds(13, 526, 144, 36);
        panel.add(lblN_1_1);
        
        txt_SDT = new JTextField();
        txt_SDT.setColumns(10);
        txt_SDT.setBounds(13, 556, 350, 19);
        panel.add(txt_SDT);
        
        JLabel lblN_1_2 = new JLabel("Họ tên nhân viên lập phiếu:");
        lblN_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN_1_2.setBounds(13, 582, 228, 36);
        panel.add(lblN_1_2);
        
        txt_TenNV = new JTextField();
        txt_TenNV.setColumns(10);
        txt_TenNV.setBounds(13, 612, 350, 19);
        panel.add(txt_TenNV);
        
        txt_NgayLapPhieu = new JTextField();
        txt_NgayLapPhieu.setColumns(10);
        txt_NgayLapPhieu.setBounds(13, 660, 350, 19);
        panel.add(txt_NgayLapPhieu);
        
        JLabel lblN_1_2_1 = new JLabel("Ngày lập phiếu");
        lblN_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN_1_2_1.setBounds(10, 628, 228, 33);
        panel.add(lblN_1_2_1);
        
        txtTienDatCoc = new JTextField();
        txtTienDatCoc.setColumns(10);
        txtTienDatCoc.setBounds(10, 717, 350, 19);
        panel.add(txtTienDatCoc);
        
        JLabel lblN_1_2_1_1 = new JLabel("Tiền đặt cọc");
        lblN_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN_1_2_1_1.setBounds(10, 684, 228, 36);
        panel.add(lblN_1_2_1_1);
        
        txt_TinhTrang = new JTextField();
        txt_TinhTrang.setColumns(10);
        txt_TinhTrang.setBounds(10, 769, 350, 19);
        panel.add(txt_TinhTrang);
        
        JLabel lblN_1_2_1_1_1 = new JLabel("Tình trạng xử lý");
        lblN_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblN_1_2_1_1_1.setBounds(10, 738, 228, 36);
        panel.add(lblN_1_2_1_1_1);
        
        pnContent = new JPanel();
        pnContent.setBounds(411, 27, 830, 798);
        add(pnContent);
        pnContent.setLayout(null);
        
        pnALL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ResetBackAndBorder();
                pnALL.setBackground(pnClicked);
                pnALL.setBackground(pnBorder);
                HienThiALL();
            }
        });

        pnROOM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ResetBackAndBorder();
                pnROOM.setBackground(pnClicked);
                pnROOM.setBackground(pnBorder);
                HienThiROOM();
            }
        });

        pnSERVICE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ResetBackAndBorder();
                pnSERVICE.setBackground(pnClicked);
                pnSERVICE.setBackground(pnBorder);
                HienThiSERVICE();
            }
        });
        
        HienThiALL();
        HienThiMaCTT();
        if (option == 0) {                
            for (int i = 0; i < cbMaCTT.getItemCount(); i++) {
                if (cbMaCTT.getItemAt(i).toString().contains(maCTT)) {
                    cbMaCTT.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            pnROOM.setVisible(false);
            pnSERVICE.setVisible(false);
            for (int i = 0; i < cbMaCTT.getItemCount(); i++) {
                if (cbMaCTT.getItemAt(i).toString().contains(maCTT)) {
                    cbMaCTT.setSelectedIndex(i);
                    break;
                }
            }
        }
	}
	
	private void ResetBackAndBorder() {
	    pnALL.setBackground(white);
	    pnROOM.setBackground(white);
	    pnSERVICE.setBackground(white);
	}

	private void HienThiMaCTT() {
	    cbMaCTT.removeAllItems();
	    List<ChiTietThueDTO> list = cttBus.getDSChiTietThue();
	    for (ChiTietThueDTO ctt : list) {
	        cbMaCTT.addItem(ctt.getMaCTT());
	    }
	}
	
	private void cbMaCTT_ItemStateChanged() {
        if (cbMaCTT.getSelectedIndex() == -1) {
            ResetInformation();
        } else {
            List<ChiTietThueDTO> cttList = cttBus.getDSChiTietThue();
            List<KhachHangDTO> khList = khachHang.GetDSKH();
            List<NhanVienDTO> nvList = nhanVien.getAllNhanVien();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            List<Object[]> info = cttList.stream()
                    .filter(ctt -> ctt.getXuLy() == 0)
                    .map(ctt -> {
                        KhachHangDTO kh = khList.stream().filter(k -> k.getMaKH().equals(ctt.getMaKH())).findFirst().orElse(null);
                        NhanVienDTO nv = nvList.stream().filter(n -> n.getMaNV().equals(ctt.getMaNV())).findFirst().orElse(null);
                        return new Object[] {
                            ctt.getMaCTT(),
                            (kh != null) ? kh.getTenKH() : "",
                            (nv != null) ? nv.getTenNV() : "",
                            (kh != null) ? kh.getCMND() : "",
                            dateFormat.format(ctt.getNgayLapPhieu()),
                            (kh != null) ? kh.getSDT() : "",
                            String.format("%,d VNĐ", ctt.getTienDatCoc()),
                            (ctt.getTinhTrangXuLy() == 0) ? "Chưa xử lý" : "Đã xử lý"
                        };
                    })
                    .collect(Collectors.toList());
            for (Object[] item : info) {
                if (item[0].equals(cbMaCTT.getSelectedItem())) {
                    txtTienDatCoc.setText((String) item[6]);
                    txt_CMND.setText((String) item[3]);
                    txt_NgayLapPhieu.setText((String) item[4]);
                    txt_SDT.setText((String) item[5]);
                    txt_TenKH.setText((String) item[1]);
                    txt_TenNV.setText((String) item[2]);
                    txt_TinhTrang.setText((String) item[7]);
                    break;
                }
            }
            ResetBackAndBorder();
            pnALL.setBackground(pnClicked);
            HienThiALL();
        }
    }

	private void HienThiALL() {
	    pnContent.removeAll();
	    if (txt_TinhTrang.getText().equals("Chưa xử lý")) {
	        FormChiTietPhieuThue frmCTT = new FormChiTietPhieuThue(cbMaCTT.getSelectedItem().toString(), 0, this);
	        pnContent.add(frmCTT);
	        frmCTT.setVisible(true);
	        pnROOM.setVisible(true);
	        pnSERVICE.setVisible(true);
	    } else {
	        FormChiTietPhieuThue frmCTT = new FormChiTietPhieuThue(cbMaCTT.getSelectedItem().toString(), 1, this);
	        pnContent.add(frmCTT);
	        frmCTT.setVisible(true);
	        pnROOM.setVisible(false);
	        pnSERVICE.setVisible(false);
	    }
	}

	private void HienThiROOM() {
	    pnContent.removeAll();
	    FormChiTietThuePhong frmCTT = new FormChiTietThuePhong(cbMaCTT.getSelectedItem().toString(), this);
	    pnContent.add(frmCTT);
	    frmCTT.setVisible(true);
	}

	public void renderAll() {
	    ResetBackAndBorder();
	    pnALL.setBackground(pnClicked);
	    HienThiALL();
	}

	private void HienThiSERVICE() {
	    pnContent.removeAll();
	    FormChiTietThueDichVu frmCTT = new FormChiTietThueDichVu(cbMaCTT.getSelectedItem().toString(), this);
	    pnContent.add(frmCTT);
	    frmCTT.setVisible(true);
	}

	private void ResetInformation() {
	    txtTienDatCoc.setText("");
	    txt_TinhTrang.setText("");
	    txt_CMND.setText("");
	    txt_NgayLapPhieu.setText("");
	    txt_SDT.setText("");
	    txt_TenKH.setText("");
	    txt_TenNV.setText("");
	}
}
