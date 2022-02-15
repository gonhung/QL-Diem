package View;

import DAO.DiemDAO;

import Model.Thongke;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ThongkeView extends JFrame {
    public ThongkeView(){
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new Dimension (560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ thống kê");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public JFreeChart createChart(){
        DiemDAO diemDAO = new DiemDAO ();
        List<Thongke> list  = diemDAO.getByDiem ();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(list!=null){
            for (Thongke cp:list) {
                dataset.addValue(cp.getSoluong (), "Số lượng học sinh", cp.getKetqua ());
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ THỐNG KÊ KẾT QUẢ THI",
                "Kết quả", "Số lượng học sinh",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        return barChart;
    }
}
