package test;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class Graficos {
       
    public void test(){
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("one", new Integer(10));
        pieDataset.setValue("two", new Integer(20));
        JFreeChart chart = ChartFactory.createPieChart("PieChart", pieDataset, true, true, true);
        //PiePlot P = (PiePlot) chart.getPlot();
        ChartFrame frame = new ChartFrame("PieChart", chart);
        frame.setVisible(true);
        frame.setSize(600, 500);
    }
}
