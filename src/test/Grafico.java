package test;

import java.util.ArrayList;
import org.jfree.chart.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafico {
    private JFreeChart chart;
    private ChartFrame frame;
    private final XYSeriesCollection coleccion = new XYSeriesCollection();
    private final ArrayList<XYSeries> series = new ArrayList<>();
    private final XYSeries acc1 = new XYSeries("ACC1");
    private final XYSeries acc2 = new XYSeries("ACC2");
    private final XYSeries acc3 = new XYSeries("ACC3");
    private final XYSeries acc4 = new XYSeries("ACC4");

    public void crearGraficoXY(){
        //Agregar las series a la coleccion
        if(!series.isEmpty()){
            for(XYSeries x : getSeries()){
                getColeccion().addSeries(x);
                //System.out.println("AGREGADO");
            }
        }
        this.setChart(ChartFactory.createXYLineChart("Datos Arduino", "Tiempo", "Valor Dato", this.getColeccion()));
    }
    
    public void mostrarGrafico(){
        this.setFrame(new ChartFrame("Resultados", this.getChart()));
        this.getFrame().setSize(800, 600);
        this.getFrame().setLocationRelativeTo(null);
        this.getFrame().setVisible(true);
        
    }
    
    public void agregarASerie(XYSeries serie, float i, float valor){
        serie.add(i, valor);
    }
    
    /**
     * @return the chart
     */
    public JFreeChart getChart() {
        return chart;
    }

    /**
     * @param chart the chart to set
     */
    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    /**
     * @return the frame
     */
    public ChartFrame getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(ChartFrame frame) {
        this.frame = frame;
    }

    /**
     * @return the coleccion
     */
    public XYSeriesCollection getColeccion() {
        return coleccion;
    }

    /**
     * @return the series
     */
    public ArrayList<XYSeries> getSeries() {
        return series;
    }

    /**
     * @return the acc1
     */
    public XYSeries getAcc1() {
        return acc1;
    }

    /**
     * @return the acc2
     */
    public XYSeries getAcc2() {
        return acc2;
    }

    /**
     * @return the acc3
     */
    public XYSeries getAcc3() {
        return acc3;
    }

    /**
     * @return the acc4
     */
    public XYSeries getAcc4() {
        return acc4;
    }
}