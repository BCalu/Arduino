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
        //Realizar la conexion con Arduino en modo de lectura
        xy.agregarASerie(xy.getAccx(), 0, 2);
        xy.agregarASerie(xy.getAccx(), 1, 3);
        xy.agregarASerie(xy.getAccx(), 2, 4);
        xy.agregarASerie(xy.getAccx(), 3, 5);
        xy.agregarASerie(xy.getAccx2(), 0, 0);
        xy.getSeries().add(xy.getAccx());
        xy.getSeries().add(xy.getAccx2());
        xy.crearGraficoXY();
        for(XYSeries x : xy.getSeries()){
            System.out.println(x.getItemCount());
        }
        xy.vaciarLista();
        if(xy.getSeries().isEmpty()){
            System.out.println("SERIES ESTA VACIA POR LA CHUCHA");
        }
    }
    
    public static void main(String[] args) throws Exception {
            SerialTest main = new SerialTest();
            main.initialize();
            /*
            Thread t=new Thread() {
                    public void run() {
                            //the following line will keep this app alive for 1000 seconds,
                            //waiting for events to occur and responding to them (printing incoming messages to console).
                            try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
                    }
            };
            t.start();
            */
/*
            System.out.println("Recibiendo");
    }
    
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
