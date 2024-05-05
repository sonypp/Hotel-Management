package GUI.ThongKe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import BUS.HoaDonBUS;

import javax.swing.*;
import java.awt.*;
import java.time.YearMonth;

public class BieuDoThongKeThangLoad extends JPanel {

    private final Font fontTitle = new Font("Arial", Font.BOLD, 14);
    private final Font font = new Font("Arial", Font.PLAIN, 12);

    public BieuDoThongKeThangLoad(int thang, int nam) {
        setLayout(new BorderLayout());

        // Assuming hd is an instance of a class that handles data retrieval
        HoaDonBUS hd = new HoaDonBUS();

        // Creating a dataset for chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Creating chart title
        String chartTitle = "Biểu đồ thống kê tiền phòng và dịch vụ theo tháng " + thang + " năm " + nam;
        JFreeChart chart = ChartFactory.createLineChart(chartTitle, "Ngày", "Số tiền", dataset);

        // Customizing chart title
        TextTitle title = new TextTitle(chartTitle, fontTitle);
        chart.setTitle(title);

        // Customizing chart appearance
        chart.getLegend().setItemFont(font);
        chart.getCategoryPlot().getDomainAxis().setLabelFont(font);
        chart.getCategoryPlot().getRangeAxis().setLabelFont(font);

        // Adding chart to panel
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);

        // Getting the number of days in the selected month and year
        YearMonth yearMonth = YearMonth.of(nam, thang);
        int daysInMonth = yearMonth.lengthOfMonth();

        // Filling dataset with data for each day of the month
        for (int i = 1; i <= daysInMonth; i++) {
            String ngay = String.valueOf(i);
            String thangStr = String.valueOf(thang);
            String namStr = String.valueOf(nam);
            int tongTienPhong = hd.TongTienPhongTrongMotNgay(ngay, thangStr, namStr);
            int tongTienDichVu = hd.TongTienDichVuTrongMotNgay(ngay, thangStr, namStr);

            dataset.addValue(tongTienPhong, "Tiền phòng", ngay);
            dataset.addValue(tongTienDichVu, "Tiền dịch vụ", ngay);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Biểu đồ thống kê tháng");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new BieuDoThongKeThangLoad(5, 2024)); // Example: May 2024
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
