/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.panamahitek.ArduinoException;
import crud.CRUD;
import javax.sql.rowset.serial.SerialException;
import jssc.SerialPortException;

public class SerialTest {
    Grafico xy = new Grafico();
    Arduino ard = new Arduino();
    
    public void initialize() throws ArduinoException, SerialException, InterruptedException, SerialPortException {
        //Realizar la conexion con Arduino en modo de lectura
        CRUD.conectar();
        ard.getARD().arduinoRX(ard.getPORT_NAME(), ard.getDATA_RATE(), ard.getEVENT());
        
        /*
        xy.crearGraficoXY();
        xy.mostrarGrafico();
        espera();
        xy.agregarASerie(1, 3);
        espera();
        xy.agregarASerie(2, 14);
        espera();
        xy.agregarASerie(3, -5);
        espera();
        */
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
            System.out.println("Recibiendo");
    }
    
    public void espera() throws InterruptedException{
        Thread.sleep(2000);
    }
}
