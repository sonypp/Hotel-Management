package GUI.ThongKe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import BUS.HoaDonBUS;

import javax.swing.*;
import java.awt.*;

public class BieuDoLoaiPhongLoad extends JPanel {

    private final Font fontTitle = new Font("Arial", Font.BOLD, 14);
    private final HoaDonBUS hd = new HoaDonBUS(); // Assuming HoaDon is a class that handles data retrieval

    public BieuDoLoaiPhongLoad(String tungay, String denngay) {
        setLayout(new BorderLayout());

        DefaultPieDataset dataset = new DefaultPieDataset();
        int total = 0;
        int totalDon = 0;
        int totalDoi = 0;
        int totalGiaDinh = 0;

        try {
            total = hd.TongLoaiPhong(tungay, denngay);
            totalDon = hd.TongLoaiPhongDon(tungay, denngay);
            totalDoi = hd.TongLoaiPhongDoi(tungay, denngay);
            totalGiaDinh = hd.TongLoaiPhongGiaDinh(tungay, denngay);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (total != 0) {
            dataset.setValue("Phòng Đơn", totalDon * 100 / total);
            dataset.setValue("Phòng Đôi", totalDoi * 100 / total);
            dataset.setValue("Phòng Gia đình", totalGiaDinh * 100 / total);
        }

        JFreeChart chart = ChartFactory.createPieChart("Biểu đồ thống kê tương quan Chi tiết loại phòng", dataset, true, true, false);
        chart.getTitle().setFont(fontTitle);

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Biểu đồ loại phòng");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new BieuDoLoaiPhongLoad("start_date", "end_date"));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
