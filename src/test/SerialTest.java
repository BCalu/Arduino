/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import crud.CRUD;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
//import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialException;
import jssc.SerialPortException;
import java.sql.Date;


public class SerialTest {
    /* Instanciar Arduino */
    private PanamaHitek_Arduino ARD = new PanamaHitek_Arduino();
    
    /* El puerto donde estara conectado el Arduino. */
    private static final String PORT_NAME = "COM7";
    
    /* Cantidad de bits por segundo */
    private static final int DATA_RATE = 9600;
    
    /* Instancia BDD donde guardaremos los datos de Arduino */
    private CRUD DB = new CRUD();
    
    /* Sentencia SQL */
    private String SQL;
    
    /* Datos */
    private Date fecha;
    private float AccX, AccY, AccZ, Accx2, Accy2, Accz2, Accx3, Accy3, Accz3,
            Accx4, Accy4, Accz4;
    public String line;
    public StringTokenizer tokens;
    private int cont;
    
    /* Instanciar evento receptor de bits */
    jssc.SerialPortEventListener EVENT = new jssc.SerialPortEventListener() {
        @Override
        /* Que hara cuando reciba el mensaje */
        public void serialEvent(jssc.SerialPortEvent spe) {
            try {
                if(ARD.isMessageAvailable()){
                    line = ARD.printMessage();
                    System.out.println(line);
                    PrepareSqlString(line);
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(SerialTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    /* Tokeniza la linea recibida por el arduino y agrega a la BDD */
    public void PrepareSqlString(String linea){
        tokens = new StringTokenizer(linea, ",");
        cont = 0;
        while (tokens.hasMoreTokens()){
            if(cont == 0)
                AccX = Float.parseFloat(tokens.nextToken());
            else if (cont == 1)
                AccY = Float.parseFloat(tokens.nextToken());
            else if (cont == 2)
                AccZ = Float.parseFloat(tokens.nextToken());
            else if (cont == 3)
                Accx2 = Float.parseFloat(tokens.nextToken());
            else if (cont == 4)
                Accy2 = Float.parseFloat(tokens.nextToken());
            else if (cont == 5)
                Accz2 = Float.parseFloat(tokens.nextToken());
            else if (cont == 6)
                Accx3 = Float.parseFloat(tokens.nextToken());
            else if (cont == 7)
                Accy3 = Float.parseFloat(tokens.nextToken());
            else if (cont == 8)
                Accz3 = Float.parseFloat(tokens.nextToken());
            else if (cont == 9)
                Accx4 = Float.parseFloat(tokens.nextToken());
            else if (cont == 10)
                Accy4 = Float.parseFloat(tokens.nextToken());
            else if (cont == 11)
                Accz4 = Float.parseFloat(tokens.nextToken());
        /* poner fecha */
        cont++;
        }
        SQL = "INSERT INTO test (AccX, AccY, AccZ, accx2, accy2, accz2, accx3, accy3, accz3, accx4, accy4, accz4) "
                + "VALUES ("+AccX+", "+AccY+", "+AccZ+", "+Accx2+", "+Accy2+", "+Accz2+", "+Accx3+", "+Accy3+", "+Accz3+", "+Accx4+", "+Accy4+", "+Accz4+")";
        //System.out.println(SQL);
        CRUD.sendQuery(SQL);
    }
    
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
