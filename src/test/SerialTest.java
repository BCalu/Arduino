package test;

import crud.CRUD;
import com.panamahitek.ArduinoException;
import javax.sql.rowset.serial.SerialException;
import jssc.SerialPortException;
import org.jfree.data.xy.XYSeries;

public class SerialTest {
    Grafico xy = new Grafico();
    Arduino ard = new Arduino(); 
    
    /*
    public void initialize() throws ArduinoException, SerialException, InterruptedException, SerialPortException {
        ard.getARD().arduinoRX(ard.getPORT_NAME(), ard.getDATA_RATE(), ard.getEVENT());
    }
    
    public static void main(String[] args) throws Exception {
            SerialTest main = new SerialTest();
            main.initialize();
            
            Thread t=new Thread() {
                    public void run() {
                            //the following line will keep this app alive for 1000 seconds,
                            //waiting for events to occur and responding to them (printing incoming messages to console).
                            try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
                    }
            };
            t.start();
            
            System.out.println("Recibiendo");
    }
    */
    /*
    public void espera() throws InterruptedException{
        Thread.sleep(500);
    }
    
    /*
    public void simulacion() throws InterruptedException{
        float contador=0;
        xy.crearGraficoXY();
        xy.mostrarGrafico();
        espera();
        while(true){
            xy.agregarASerie(xy.getAccx(), contador, (float) (Math.random() * 100));
            espera();    
            contador++;
        }
    }
    */
}
