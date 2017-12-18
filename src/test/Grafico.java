package test;

import org.jfree.chart.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafico {
    private JFreeChart chart;
    private ChartFrame frame;
    private final XYSeriesCollection coleccion = new XYSeriesCollection();
    private final XYSeries accx = new XYSeries("ACCX");
    private final XYSeries accx2 = new XYSeries("ACCX2");
    private final XYSeries accx3 = new XYSeries("ACCX3");
    private final XYSeries accx4 = new XYSeries("ACCX4");

    public void crearGraficoXY(){
        this.getAccx().add(0, 0);                  //Para que comience desde el origen
        this.getColeccion().addSeries(this.getAccx());
        this.getColeccion().addSeries(this.getAccx2());
        this.getColeccion().addSeries(this.getAccx3());
        this.getColeccion().addSeries(this.getAccx4());
        this.setChart(ChartFactory.createXYLineChart("Datos Arduino", "Tiempo", "Valor Dato", this.getColeccion()));
    }
    
    public void mostrarGrafico(){
        this.setFrame(new ChartFrame("Resultados", this.getChart()));
        this.getFrame().setVisible(true);
        this.getFrame().setSize(800, 600);
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
     * @return the accx
     */
    public XYSeries getAccx() {
        return accx;
    }

    /**
     * @return the accx2
     */
    public XYSeries getAccx2() {
        return accx2;
    }

    /**
     * @return the accx3
     */
    public XYSeries getAccx3() {
        return accx3;
    }

    /**
     * @return the accx4
     */
    public XYSeries getAccx4() {
        return accx4;
    }
}