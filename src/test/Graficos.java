package test;

import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.data.general.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
/*
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
*/
public class Graficos {
    public JFreeChart chart;
    public ChartFrame frame;
    
    public void PieChart(){
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("one", new Integer(10));
        pieDataset.setValue("two", new Integer(20));
        chart = ChartFactory.createPieChart("PieChart", pieDataset, true, true, true);
        //PiePlot P = (PiePlot) chart.getPlot();
        frame = new ChartFrame("PieChart", chart);
        frame.setVisible(true);
        frame.setSize(600, 500);
    }
    
    public void LineChart(){
        DefaultCategoryDataset DS = new DefaultCategoryDataset();
        DS.setValue(10, "Valor", "ACCX");
        DS.setValue(20, "Valor", "ACCY");
        DS.setValue(30, "Valor", "ACCZ");
        chart = ChartFactory.createLineChart("LineChart", "Parametros", "Valores", DS);
        frame = new ChartFrame("Resultados", chart);
        frame.setVisible(true);
        frame.setSize(800, 600);
    }
    
    public void XYChart(){
        /* Almacena las series del grafico */
        XYSeriesCollection coleccion = new XYSeriesCollection();
        
        final XYSeries accx = new XYSeries("ACCX");
        accx.add(1, 2);
        accx.add(2, 1);
        accx.add(3, 4);
        
        final XYSeries accy = new XYSeries("ACCY");
        accy.add(3, 3);
        accy.add(2, 2);
        
        final XYSeries accz = new XYSeries("ACCZ");
        accz.add(4, 1);
        accz.add(1, 4);
        accz.add(5, 2);
        accz.add(3, 6);
        
        coleccion.addSeries(accx);
        coleccion.addSeries(accy);
        coleccion.addSeries(accz);
        
        chart = ChartFactory.createXYLineChart("Datos Arduino", "Tiempo", "Valor Dato", coleccion);
        frame = new ChartFrame("Resultados", chart);
        frame.setVisible(true);
        frame.setSize(800, 600);
    }
}
