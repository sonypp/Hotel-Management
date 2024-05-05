package GUI.QuanLyDatPhong;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import BUS.ChiTietThueDichVuBUS;
import BUS.ChiTietThuePhongBUS;
import BUS.DichVuBUS;
import BUS.PhongBUS;
import DTO.ChiTietThueDichVuDTO;
import DTO.ChiTietThuePhongDTO;
import DTO.DichVuDTO;
import DTO.PhongDTO;
import javax.swing.ScrollPaneConstants;

public class FormChiTietPhieuThue extends JPanel {
	private JPanel panel_1;
	private JTable tableRoom;
	private JTable tableService;
	private DefaultTableModel tbRoom;
	private DefaultTableModel tbService;
	ChiTietThuePhongBUS cttP = new ChiTietThuePhongBUS();
	PhongBUS phong = new PhongBUS();
	ChiTietThueDichVuBUS cttDV = new ChiTietThueDichVuBUS();
	DichVuBUS dichVu = new DichVuBUS();
	FormChiTietThue frmChiTietThue;
	String maCTT;
	private JLabel lbTotal;
	private JButton btnThanhToan;
	private JButton btnPrint;
	private JPopupMenu ctmnRoom;

	/**
	 * Create the application.
	 */
	public FormChiTietPhieuThue(String maCTT, int option, FormChiTietThue frmChiTietThue) {
		this.frmChiTietThue = frmChiTietThue;
		this.maCTT = maCTT;
		initialize();
		tbRoom = (DefaultTableModel) tableRoom.getModel();
		tbService = (DefaultTableModel) tableService.getModel();
		hienThiChiTietThuePhong();
		hienThiChiTietThueDichVu();
		if (option == 0) {
		    btnThanhToan.setVisible(true);
		    tableRoom.getColumnModel().getColumn(9).setPreferredWidth(30);
		    tableRoom.getColumnModel().getColumn(10).setPreferredWidth(30);
		    tableService.getColumnModel().getColumn(7).setPreferredWidth(30);
		    tableService.getColumnModel().getColumn(8).setPreferredWidth(30);
		} else {
		    btnThanhToan.setVisible(false);
		    tableRoom.getColumnModel().getColumn(9).setPreferredWidth(0);
		    tableRoom.getColumnModel().getColumn(9).setMinWidth(0);
		    tableRoom.getColumnModel().getColumn(9).setMaxWidth(0);
		    tableRoom.getColumnModel().getColumn(10).setPreferredWidth(0);
		    tableRoom.getColumnModel().getColumn(10).setMinWidth(0);
		    tableRoom.getColumnModel().getColumn(10).setMaxWidth(0);
		    tableService.getColumnModel().getColumn(7).setPreferredWidth(0);
		    tableService.getColumnModel().getColumn(7).setMinWidth(0);
		    tableService.getColumnModel().getColumn(7).setMaxWidth(0);
		    tableService.getColumnModel().getColumn(8).setPreferredWidth(0);
		    tableService.getColumnModel().getColumn(8).setMinWidth(0);
		    tableService.getColumnModel().getColumn(8).setMaxWidth(0);
		}
		tableRoom.clearSelection();
		tableService.clearSelection();
	}
	
	private void renderTongTien() {
	    int total = 0;
	    for (int i = 0; i < tbRoom.getRowCount(); i++) {
	        try {
	            total += Integer.parseInt(tbRoom.getValueAt(i, 8).toString().replace(",", "").split(" ")[0]);
	        } catch (Exception e) {
	            // Handle parsing exception
	        }
	    }
	    for (int i = 0; i < tbService.getRowCount(); i++) {
	        try {
	            total += Integer.parseInt(tbService.getValueAt(i, 6).toString().replace(",", "").split(" ")[0]);
	        } catch (Exception e) {
	            // Handle parsing exception
	        }
	    }
	    lbTotal.setText(String.format("%,d VNĐ", total));
	}

	private void hienThiChiTietThuePhong() {
	    DefaultTableModel model = (DefaultTableModel) tableRoom.getModel();
	    model.setRowCount(0); // Clear table rows
	    List<ChiTietThuePhongDTO> cttps = cttP.GetDSListCTTP(maCTT);
	    List<PhongDTO> phongs = phong.getListPhong_DTO();
	    int stt = 0;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    for (ChiTietThuePhongDTO cttp : cttps) {
	        for (PhongDTO phong : phongs) {
	            if (cttp.getMaP().equals(phong.getMaP())) {
	                String tinhTrang = "";
	                if (cttp.getTinhTrang() == 0) {
	                    tinhTrang = "Đang xử lý";
	                } else if (cttp.getTinhTrang() == 1) {
	                    tinhTrang = "Đang được thuê";
	                } else {
	                    tinhTrang = "Đã trả phòng";
	                }
	                String loaiHinhThue = "";
	                if (cttp.getLoaiHinhThue() == 0) {
	                    loaiHinhThue = "Theo Ngày";
	                } else if (cttp.getLoaiHinhThue() == 1) {
	                    loaiHinhThue = "Theo giờ";
	                } else {
	                    loaiHinhThue = "Khác";
	                }
	                String ngayTra = cttp.getNgayTra() != null ? dateFormat.format(cttp.getNgayTra()) : "";
	                String ngayCheckOut = cttp.getNgayCheckOut() != null ? dateFormat.format(cttp.getNgayCheckOut()) : "";
	                String giaThue = String.format("%,d VNĐ", cttp.getGiaThue());
	                model.addRow(new Object[] { ++stt, cttp.getMaP(), phong.getTenP(), tinhTrang, loaiHinhThue, dateFormat.format(cttp.getNgayThue()), ngayTra, ngayCheckOut, giaThue});
	                break; // Stop looping through phongs for this cttp
	            }
	        }
	        tableRoom.clearSelection();
	    }
	    renderTongTien();
	}
	
	private void hienThiChiTietThueDichVu() {
	    DefaultTableModel model = (DefaultTableModel) tableService.getModel();
	    model.setRowCount(0); // Clear table rows
	    List<ChiTietThueDichVuDTO> cttdvs = cttDV.GetListChiTietDichVu(maCTT);
	    List<DichVuDTO> dvs = dichVu.getListDichVu();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    int stt = 0;
	    for (ChiTietThueDichVuDTO cttdv : cttdvs) {
	        for (DichVuDTO dv : dvs) {
	            if (cttdv.getMaDV().equals(dv.getMaDV())) {
	                int thanhTien = cttdv.getGiaDV() * cttdv.getSoLuong();
	                model.addRow(new Object[] { ++stt, cttdv.getMaDV(), dv.getTenDV(), dateFormat.format(cttdv.getNgaySuDung()), cttdv.getSoLuong(), String.format("%,d VNĐ", cttdv.getGiaDV()), String.format("%,d VNĐ", thanhTien)});
	                break; // Stop looping through dvs for this cttdv
	            }
	        }
	    }
	    tableService.clearSelection();
	    renderTongTien();
	}
	
	private void layPhongToolStripMenuItem_Click(ActionEvent e) {
	    int selectedRowIndex = tableRoom.getSelectedRow();
	    if (selectedRowIndex >= 0) {
	        String tinhTrang = tableRoom.getValueAt(selectedRowIndex, 3).toString();
	        if (tinhTrang.equals("Đang xử lý")) {
	            String maP = tableRoom.getValueAt(selectedRowIndex, 1).toString();
	            String ngayThue = tableRoom.getValueAt(selectedRowIndex, 5).toString();
	            try {
	                Date ngayThueDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(ngayThue);
	                String ngayThueFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ngayThueDate);
	                cttP.UpdateTinhTrang(maCTT, maP, ngayThueFormatted, "1");
	                phong.SuaTinhTrang(maP, "1");
	                JOptionPane.showMessageDialog(null, "Lấy phòng cho khách thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	                hienThiChiTietThuePhong();
	            } catch (ParseException ex) {
	                ex.printStackTrace();
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Phòng này " + tinhTrang + " không thể lấy", "Thông báo", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	private void traPhongToolStripMenuItem_Click(ActionEvent e) {
	    int selectedRowIndex = tableRoom.getSelectedRow();
	    if (selectedRowIndex >= 0) {
	        String tinhTrang = tableRoom.getValueAt(selectedRowIndex, 3).toString();
	        if (tinhTrang.equals("Đang được thuê")) {
	            String maP = tableRoom.getValueAt(selectedRowIndex, 1).toString();
	            String ngayThue = tableRoom.getValueAt(selectedRowIndex, 5).toString();
	            try {
	                Date ngayThueDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(ngayThue);
	                String ngayThueFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ngayThueDate);
	                cttP.UpdateTinhTrang(maCTT, maP, ngayThueFormatted, "2");
	                if (!tableRoom.getValueAt(selectedRowIndex, 4).toString().equals("Khác")) {
	                    cttP.UpdateCheckOut(true, maCTT, maP, ngayThueFormatted, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), "0");
	                } else {
	                	var cttp = new ChiTietThuePhongBUS();
	                    var cttpList = cttp.GetDSListCTTP(maCTT);
	                    ChiTietThuePhongDTO cttpDTO = new ChiTietThuePhongDTO();
	                    for(var ctt : cttpList)
	                    {
	                    	if(ctt.getMaP() == maP && ctt.getNgayThue().compareTo(ngayThueDate) == 0)
	                    	{
	                    		cttpDTO = ctt;
	                    		return;
	                    	}
	                    }
	                    Date ngayTraDate = new Date();
	                    LocalDateTime ngayThueLocal = ngayThueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    	    		LocalDateTime ngayTraLocal = ngayTraDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	    	    		var duration = Duration.between(ngayTraLocal, ngayThueLocal);
	    	    		var day = Math.abs(duration.toDays());
	    	    		var hours = Math.abs(duration.toHours());
	    	    		double tmp = 0;
	    	    		switch ((int)hours)
	    	    		{
	    		    		case 1:
	    		    			tmp = cttpDTO.getGiaThue() * 0.2 + cttpDTO.getGiaThue() * day;
	    		    			break;
	    		    		case 2:
	    		    			tmp = cttpDTO.getGiaThue() * 0.1 + cttpDTO.getGiaThue() * day;
	    		    			break;
	    		    		default:
	    		    			tmp = cttpDTO.getGiaThue() * 0.05 + cttpDTO.getGiaThue() * day;
	    		    			break;
	    	    		}
	                }
	                phong.SuaTinhTrang(maP, "2");
	                JOptionPane.showMessageDialog(null, "Trả phòng cho khách thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
	                hienThiChiTietThuePhong();
	            } catch (ParseException ex) {
	                ex.printStackTrace();
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Phòng này " + tinhTrang + " không thể trả", "Thông báo", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	private void btnThanhToan_Click() {
	    int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn thanh toán phiếu thuê này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
	    if (option == JOptionPane.YES_OPTION) {
	        boolean dangCoPhongDuocThue = false;
	        for (int i = 0; i < tableRoom.getRowCount(); i++) {
	            if (tableRoom.getValueAt(i, 3).toString().equals("Đang được thuê")) {
	                dangCoPhongDuocThue = true;
	                break;
	            }
	        }
	        if (dangCoPhongDuocThue) {
	            JOptionPane.showMessageDialog(null, "Thanh toán không thành công vì còn phòng đang được thuê", "Thông báo", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        if (tableRoom.getRowCount() == 0 && tableService.getRowCount() == 0) {
	            JOptionPane.showMessageDialog(null, "Thanh toán không thành công vì phiếu thuê không thuê gì hết", "Thông báo", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        if (tableService.getRowCount() == 0) {
	            int countRow = 0;
	            for (int i = 0; i < tableRoom.getRowCount(); i++) {
	                if (tableRoom.getValueAt(i, 3).toString().equals("Đang xử lý")) {
	                    countRow++;
	                }
	            }
	            if (countRow == tableRoom.getRowCount()) {
	                JOptionPane.showMessageDialog(null, "Thanh toán không thành công vì còn phòng chưa được thuê", "Thông báo", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	        }
	        ThanhToan thanhToan = new ThanhToan(maCTT);
	        thanhToan.setVisible(true);
	        thanhToan.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosed(WindowEvent e) {
	                int select = frmChiTietThue.cbMaCTT.getSelectedIndex();
	                frmChiTietThue.cbMaCTT.setSelectedIndex(-1);
	                frmChiTietThue.cbMaCTT.setSelectedIndex(select);
	            }
	        });
	    }
	}
	
	private void tbService_CellContentClick() {
	    int index = 8;
	    if (tableService.getSelectedColumn() == index && tableService.getSelectedRow() >= 0) {
	        JOptionPane.showMessageDialog(null, "Bạn có muốn xóa dịch vụ thuê này", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
	        int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dịch vụ thuê này", "Cảnh báo", JOptionPane.YES_NO_OPTION);
	        if (ans == JOptionPane.YES_OPTION) {
	            cttDV.DeleteCTTDV(maCTT, tbService.getValueAt(tableService.getSelectedRow(), 1).toString(), tbService.getValueAt(tableService.getSelectedRow(), 3).toString());
	            JOptionPane.showMessageDialog(null, "Xóa dịch vụ này thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	            hienThiChiTietThueDichVu();
	        } else {
	            tableService.clearSelection();
	        }
	    }
	    int indexUpdate = 7;
	    if (tableService.getSelectedColumn() == indexUpdate && tableService.getSelectedRow() >= 0) {
	        formInput frmInput = new formInput();
	        frmInput.setVisible(true);
	        new Timer(100, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (frmInput.getDialogResult() == JOptionPane.YES_OPTION) {
	                    int soLuong = frmInput.getNumber();
	                    cttDV.SuaSoLuongCTTDV(maCTT, tbService.getValueAt(tableService.getSelectedRow(), 1).toString(), tbService.getValueAt(tableService.getSelectedRow(), 3).toString(), Integer.toString(soLuong));
	                    hienThiChiTietThueDichVu();
	                    JOptionPane.showMessageDialog(null, "Sửa dịch vụ này thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	                    var source = (Timer)e.getSource();
	                    source.stop();
	                } else if (frmInput.getDialogResult() == JOptionPane.CANCEL_OPTION) {
	                    tableService.clearSelection();
	                    var source = (Timer)e.getSource();
	                    source.stop();
	                }
	            }
	        }).start();

	    }
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(0, 0, 920, 815);
		setLayout(null);
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 920, 815);
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(10, 5, 900, 340);
		panel_1.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Danh sách phòng khách thuê");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 10, 890, 33);
		panel_2.add(lblNewLabel);
		
		JScrollPane scrpDSPhongKhachThue = new JScrollPane();
		scrpDSPhongKhachThue.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrpDSPhongKhachThue.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrpDSPhongKhachThue.setBounds(10, 50, 890, 280);
		panel_2.add(scrpDSPhongKhachThue);
		
		var tableRoomColumn = new String[] {
				"STT", "MÃ PHÒNG", "TÊN PHÒNG", "TÌNH TRẠNG", "LOẠI HÌNH THUÊ", "NGÀY THUÊ", "NGÀY TRẢ", "NGÀY CHECKOUT", "GIÁ PHÒNG", "SỬA", "XÓA"
			};
        String data[][] = {};
        tbRoom = new DefaultTableModel(data, tableRoomColumn);
        tableRoom = new JTable(tbRoom);
        tableRoom.setRowHeight(30);
        
        // Customize the table header
        JTableHeader tableHeader = tableRoom.getTableHeader();
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 30));
        tableHeader.setBackground(Color.MAGENTA);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setReorderingAllowed(false);
        
        // Set font style and alignment for column headers
        Font headerFont = new Font("Arial", Font.BOLD, 14);
        tableHeader.setFont(headerFont);
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) tableRoom.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setVerticalAlignment(SwingConstants.CENTER);
        headerRenderer.setText("<html><font color='white'>Name</font></html>"); // Change 'Name' to your column header text

        // Customize other properties of the table
        tableRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableRoom.setShowGrid(false);
        tableRoom.setDefaultEditor(Object.class, null);
        scrpDSPhongKhachThue.setViewportView(tableRoom);
		tableRoom.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            Point point = e.getPoint();
	            int row = tableRoom.rowAtPoint(point);
	            int column = tableRoom.columnAtPoint(point);
	            int index = 10;
	            if (column == index && row >= 0) {
	                int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phòng thuê này", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	                if (ans == JOptionPane.YES_OPTION) {
	                    if (tableRoom.getValueAt(row, 3).toString().equals("Đang xử lý")) {
	                        try {
								cttP.DeleteCTTP(maCTT, tableRoom.getValueAt(row, 1).toString(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("dd/mm/yyyy HH:mm:ss").parse(tableRoom.getValueAt(row, 5).toString())));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	                        JOptionPane.showMessageDialog(null, "Xóa phòng này thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	                        hienThiChiTietThuePhong();
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Phòng này " + tableRoom.getValueAt(row, 3).toString() + " không thể xóa", "Thông báo", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	            }
	            int indexUpdate = 9;
	            if (column == indexUpdate && row >= 0) {
	                ctmnRoom.show(tableRoom, point.x, point.y);
	            }
	        }
	    });
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(10, 355, 900, 340);
		panel_1.add(panel_2_1);
		
		JLabel lblDanhSchDch = new JLabel("Danh sách dịch vụ khách thuê");
		lblDanhSchDch.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDanhSchDch.setBounds(10, 10, 890, 33);
		panel_2_1.add(lblDanhSchDch);
		
		JScrollPane scrpDanhSachDichVuKhachThue = new JScrollPane();
		scrpDanhSachDichVuKhachThue.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrpDanhSachDichVuKhachThue.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrpDanhSachDichVuKhachThue.setBounds(10, 50, 890, 280);
		panel_2_1.add(scrpDanhSachDichVuKhachThue);
		
		var tableServiceColumn = new String[] {
				"STT", "MÃ DỊCH VỤ", "TÊN DỊCH VỤ", "NGÀY SỬ DỤNG", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN", "SỬA", "XÓA"
			};
        tbService = new DefaultTableModel(data, tableServiceColumn);
        tableService = new JTable(tbService);
        tableService.setRowHeight(30);
        
        // Customize the table header
        JTableHeader tableHeader1 = tableService.getTableHeader();
        tableHeader1.setPreferredSize(new Dimension(tableHeader1.getWidth(), 30));
        tableHeader1.setBackground(Color.MAGENTA);
        tableHeader1.setForeground(Color.WHITE);
        tableHeader1.setReorderingAllowed(false);
        
        // Set font style and alignment for column headers
        Font headerFont1 = new Font("Arial", Font.BOLD, 14);
        tableHeader1.setFont(headerFont1);
        DefaultTableCellRenderer headerRenderer1 = (DefaultTableCellRenderer) tableService.getTableHeader().getDefaultRenderer();
        headerRenderer1.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer1.setVerticalAlignment(SwingConstants.CENTER);
        headerRenderer1.setText("<html><font color='white'>Name</font></html>"); // Change 'Name' to your column header text

        // Customize other properties of the table
        tableService.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableService.setShowGrid(false);
        tableService.setDefaultEditor(Object.class, null);
		tableService.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tbService_CellContentClick();
            }
        });
		scrpDanhSachDichVuKhachThue.add(tableService);
		scrpDanhSachDichVuKhachThue.setViewportView(tableService);
		
		lbTotal = new JLabel("New label");
		lbTotal.setForeground(Color.RED);
		lbTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lbTotal.setBounds(363, 705, 142, 33);
		panel_1.add(lbTotal);
		
		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnThanhToan_Click();
			}
		});
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThanhToan.setBackground(new Color(87, 87, 255));
		btnThanhToan.setBounds(532, 705, 186, 33);
		panel_1.add(btnThanhToan);
		
		btnPrint = new JButton("In Phiếu Thuê");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPrint.setBackground(new Color(87, 87, 255));
		btnPrint.setBounds(728, 705, 167, 33);
		panel_1.add(btnPrint);
		
		// Create font for menu items
		Font menuItemFont = new Font("Segoe UI Semibold", Font.BOLD, 14);

		// Create "Lấy phòng" menu item
		JMenuItem layPhongMenuItem = new JMenuItem("Lấy phòng");
		layPhongMenuItem.setFont(menuItemFont);
		layPhongMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        layPhongToolStripMenuItem_Click(e);
		    }
		});

		// Create "Trả phòng" menu item
		JMenuItem traPhongMenuItem = new JMenuItem("Trả phòng");
		traPhongMenuItem.setFont(menuItemFont);
		traPhongMenuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        traPhongToolStripMenuItem_Click(e);
		    }
		});
		
		ctmnRoom = new JPopupMenu ();
		ctmnRoom.setPopupSize(20, 20);
		ctmnRoom.add(layPhongMenuItem);
		ctmnRoom.add(traPhongMenuItem);
		ctmnRoom.setName("ctmnRoom");
		ctmnRoom.setPreferredSize(new Dimension(161, 60));

	}
}
