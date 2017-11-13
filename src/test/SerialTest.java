/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import crud.CRUD;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialException;
import jssc.SerialPortException;


public class SerialTest {
    /* Instanciar Arduino */
    private PanamaHitek_Arduino ARD = new PanamaHitek_Arduino();
    /* Instancia BDD donde guardaremos los datos de Arduino */
    private CRUD DB = new CRUD();
    private String SQL;
    private int DATA;
    /* El puerto donde estara conectado el Arduino. */
    private static final String PORT_NAME = "COM6";
    /* Cantidad de bits por segundo */
    private static final int DATA_RATE = 9600;
    /* Instanciar evento receptor de bits */
    jssc.SerialPortEventListener EVENT = new jssc.SerialPortEventListener() {
        @Override
        /* Que hara cuando reciba el mensaje */
        public void serialEvent(jssc.SerialPortEvent spe) {
            try {
                if(ARD.isMessageAvailable()){
                    DATA = Integer.parseInt(ARD.printMessage());
                    System.out.println(DATA);
                    SQL = "INSERT INTO test (dato) VALUES ("+DATA+")";
                    DB.sendQuery(SQL);
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(SerialTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public void initialize() throws ArduinoException, SerialException {
        try {
            //Realizar la conexion con Arduino en modo de lectura
            ARD.arduinoRX(PORT_NAME, DATA_RATE, EVENT);
            DB.conectar();
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
}
