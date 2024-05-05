package GUI.ThongKe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import BUS.HoaDonBUS;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BieuDoThongKeNamLoad extends JPanel {

    private final Font fontTitle = new Font("Arial", Font.BOLD, 14);
    private final HoaDonBUS hd = new HoaDonBUS(); // Assuming HoaDonBUS is a class that handles data retrieval
    private final ChartPanel chartPanel;

    public BieuDoThongKeNamLoad(int nam) {
        setLayout(new BorderLayout());

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 1; i <= 12; i++) {
            dataset.addValue(hd.TongTienPhongTrongMotThang(String.valueOf(i), String.valueOf(nam)), "Tiền phòng", String.valueOf(i));
            dataset.addValue(hd.TongTienDichVuTrongMotThang(String.valueOf(i), String.valueOf(nam)), "Tiền dịch vụ", String.valueOf(i));
        }

        JFreeChart chart = ChartFactory.createBarChart("Biểu đồ thống kê tiền phòng và dịch vụ trong năm " + nam,
                "Tháng", "Số tiền", dataset);

        chart.getTitle().setFont(fontTitle);

        chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Biểu đồ thống kê năm");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new BieuDoThongKeNamLoad(2024)); // Replace 2024 with the desired year
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
