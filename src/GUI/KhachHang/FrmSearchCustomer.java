package GUI.KhachHang;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import DTO.KhachHangDTO;
import GUI.Home.HomeForm;

import java.awt.*;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.*;

public class FrmSearchCustomer extends JFrame {
    private JPanel panel1;
    private JLabel label1;
    private JTable tbKH;
    private DefaultTableModel model;
    private List<KhachHangDTO> list;
    public KhachHangDTO khachHang;
	public static int DialogResult;

    public FrmSearchCustomer(List<KhachHangDTO> list) {
        khachHang = new KhachHangDTO();
        this.list = list;
        initializeComponents();
        HienThiDSKhachHang(list);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1172, 380);
        this.setVisible(true);
        DialogResult = JOptionPane.CANCEL_OPTION;
    }
    
    private void initializeComponents() {
    	panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        label1 = new JLabel("DANH SÁCH KHÁCH HÀNG TÌM THẤY");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, 16f));
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        label1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.add(label1);
        
        model = new DefaultTableModel();
        tbKH = new JTable(model);
        tbKH.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel1.setBackground(Color.WHITE);
        panel1.setLayout(new BorderLayout());

        tbKH.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"STT", "Mã khách hàng", "Tên khách hàng", "Giới tính", "CMND", "Số điện thoại", "Quê quán", "Quốc tịch", "Ngày sinh"}
        ));
        JScrollPane scrollPane = new JScrollPane(tbKH);
        panel1.add(scrollPane, BorderLayout.CENTER);

        label1.setBackground(Color.WHITE);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label1.setForeground(Color.BLUE);
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        label1.setText("DANH SÁCH KHÁCH HÀNG TÌM THẤY");
        panel1.add(label1, BorderLayout.NORTH);
        
        tbKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKH_CellContentClick(evt);
            }
        });

        getContentPane().add(panel1);
        pack();
    }

    public void HienThiDSKhachHang(List<KhachHangDTO> list) {
    	model = (DefaultTableModel) tbKH.getModel();
        model.setRowCount(0);
        int stt = 0;
        for (KhachHangDTO x : list) {
            String gioiTinh = (x.getGioiTinh() == 0) ? "Nam" : "Nữ";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            model.addRow(new Object[]{
                ++stt,
                x.getMaKH(),
                x.getTenKH(),
                gioiTinh,
                x.getCMND(),
                x.getSDT(),
                x.getQueQuan(),
                x.getQuocTich(),
                dateFormat.format(x.getNgaySinh())
            });

        }
        tbKH.clearSelection();
    }

    private void tbKH_CellContentClick(java.awt.event.MouseEvent evt) {
        khachHang = new KhachHangDTO();
        khachHang = list.get(tbKH.getSelectedRow());
        DialogResult = JOptionPane.OK_OPTION;
    }
}
