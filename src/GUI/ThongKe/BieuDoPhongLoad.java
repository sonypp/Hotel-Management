package GUI.ThongKe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import BUS.HoaDonBUS;

import javax.swing.*;
import java.awt.*;

public class BieuDoPhongLoad extends JPanel {

    private final Font fontTitle = new Font("Arial", Font.BOLD, 14);
    private final HoaDonBUS hd = new HoaDonBUS(); // Assuming HoaDon is a class that handles data retrieval

    public BieuDoPhongLoad(String tungay, String denngay) {
        setLayout(new BorderLayout());

        DefaultPieDataset dataset = new DefaultPieDataset();
        int total = 0;
        int totalVip = 0;
        int totalThuong = 0;

        try {
            total = hd.TongLoaiPhong(tungay, denngay);
            totalVip = hd.TongLoaiPhongVip(tungay, denngay);
            totalThuong = hd.TongLoaiPhongThuong(tungay, denngay);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (total != 0) {
            dataset.setValue("Phòng Thường", totalThuong * 100 / total);
            dataset.setValue("Phòng VIP", totalVip * 100 / total);
        }

        JFreeChart chart = ChartFactory.createPieChart("Biểu đồ thống kê tương quan phòng Vip và Thường", dataset, true, true, false);
        chart.getTitle().setFont(fontTitle);

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Biểu đồ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new BieuDoPhongLoad("start_date", "end_date"));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
