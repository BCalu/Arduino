package test;

import crud.CRUD;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Arduino {
    /* Instanciar Arduino */
    private PanamaHitek_Arduino ARD = new PanamaHitek_Arduino();
    
    /* El puerto donde estara conectado el Arduino. */
    private String PORT_NAME;
    
    /* Cantidad de bits por segundo */
    private final int DATA_RATE = 9600;
    
    /* Sentencia SQL */
    private String sentencia;
    
    private final Grafico xy = new Grafico();
    
    /* Datos */
    private Date fecha;
    private final DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private float AccX, AccY, AccZ, Accx2, Accy2, Accz2, Accx3, Accy3, Accz3,
            Accx4, Accy4, Accz4;
    private String line;
    private StringTokenizer tokens;
    private int contador = 1;
    
    /* Instanciar evento receptor de bits (mensaje) */
    private jssc.SerialPortEventListener EVENT = new jssc.SerialPortEventListener() {
        @Override
        /* Que hara cuando reciba el mensaje */
        public void serialEvent(jssc.SerialPortEvent spe) {
            /*
            if(getContador() == 1){
                prepararGrafico();
                setContador(contador+1);
            }
            */
            try {
                if(ARD.isMessageAvailable()){
                    line = ARD.printMessage();
                    System.out.println(line);
                    actualizarGrafico();
                    setContador(contador+1);
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(SerialTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    
    public void prepararGrafico(){
        getXy().crearGraficoXY();
        getXy().mostrarGrafico();
    }
    
    //ARREGLAR
    public void actualizarGrafico(){
        tokenizar(getLine());
        if(xy.getSeries().contains(xy.getAccx())){
            System.out.println("TIENE ACCX");
            getXy().agregarASerie(getXy().getAccx(), getContador(), getAccX());
        }
        if(xy.getSeries().contains(xy.getAccx2())){
            System.out.println("TIENE ACCX2");
        }
        if(xy.getSeries().contains(xy.getAccx3())){
            System.out.println("TIENE ACCX3");
        }
        if(xy.getSeries().contains(xy.getAccx4())){
            System.out.println("TIENE ACCX4");
        }
    }
    
    public void tokenizar(String linea){
        int cont=1;
        setTokens(new StringTokenizer(linea, ","));
        while (getTokens().hasMoreTokens()){
            if(cont == 1)
                setAccX(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 2)
                setAccY(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 3)
                setAccZ(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 4)
                setAccx2(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 5)
                setAccy2(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 6)
                setAccz2(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 7)
                setAccx3(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 8)
                setAccy3(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 9)
                setAccz3(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 10)
                setAccx4(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 11)
                setAccy4(Float.parseFloat(getTokens().nextToken()));
            else if (cont == 12)
                setAccz4(Float.parseFloat(getTokens().nextToken()));
            cont++;
        }
        setFecha(new Date(System.currentTimeMillis()));
        //System.out.println(formato.format(fecha));
        //prepareSqlString();
    }
    
    /* Prepara la sentencia SQL y agrega a la BDD */
    public void prepareSqlString(){      
        setSQL("INSERT INTO test (Fecha, AccX, AccY, AccZ, accx2, accy2, accz2, accx3, accy3, accz3, accx4, accy4, accz4) "
                + "VALUES ('" + getFormato().format(getFecha()) + "', " + getAccX() + ", " + getAccY() + ", " + getAccZ() + 
                ", " + getAccx2() + ", " + getAccy2() + ", " + getAccz2() + ", " + getAccx3() + ", " + getAccy3() + ", " + 
                getAccz3() + ", " + getAccx4() + ", " + getAccy4() + ", " + getAccz4() + ")");
        //System.out.println(SQL);
        CRUD.sendQuery(getSQL());
        //System.out.println("INSERTADO");
    }

    /**
     * @return the ARD
     */
    public PanamaHitek_Arduino getARD() {
        return ARD;
    }

    /**
     * @param ARD the ARD to set
     */
    public void setARD(PanamaHitek_Arduino ARD) {
        this.ARD = ARD;
    }

    /**
     * @return the PORT_NAME
     */
    public String getPORT_NAME() {
        return PORT_NAME;
    }
    
    /**
     * @param PORT_NAME the PORT_NAME to set
     */
    public void setPORT_NAME(String PORT_NAME) {
        this.PORT_NAME = PORT_NAME;
    }

    /**
     * @return the DATA_RATE
     */
    public int getDATA_RATE() {
        return DATA_RATE;
    }

    /**
     * @return the SQL
     */
    public String getSQL() {
        return getSentencia();
    }

    /**
     * @param SQL the SQL to set
     */
    public void setSQL(String SQL) {
        this.sentencia = SQL;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the formato
     */
    public DateFormat getFormato() {
        return formato;
    }

    /**
     * @return the AccX
     */
    public float getAccX() {
        return AccX;
    }

    /**
     * @param AccX the AccX to set
     */
    public void setAccX(float AccX) {
        this.AccX = AccX;
    }

    /**
     * @return the AccY
     */
    public float getAccY() {
        return AccY;
    }

    /**
     * @param AccY the AccY to set
     */
    public void setAccY(float AccY) {
        this.AccY = AccY;
    }

    /**
     * @return the AccZ
     */
    public float getAccZ() {
        return AccZ;
    }

    /**
     * @param AccZ the AccZ to set
     */
    public void setAccZ(float AccZ) {
        this.AccZ = AccZ;
    }

    /**
     * @return the Accx2
     */
    public float getAccx2() {
        return Accx2;
    }

    /**
     * @param Accx2 the Accx2 to set
     */
    public void setAccx2(float Accx2) {
        this.Accx2 = Accx2;
    }

    /**
     * @return the Accy2
     */
    public float getAccy2() {
        return Accy2;
    }

    /**
     * @param Accy2 the Accy2 to set
     */
    public void setAccy2(float Accy2) {
        this.Accy2 = Accy2;
    }

    /**
     * @return the Accz2
     */
    public float getAccz2() {
        return Accz2;
    }

    /**
     * @param Accz2 the Accz2 to set
     */
    public void setAccz2(float Accz2) {
        this.Accz2 = Accz2;
    }

    /**
     * @return the Accx3
     */
    public float getAccx3() {
        return Accx3;
    }

    /**
     * @param Accx3 the Accx3 to set
     */
    public void setAccx3(float Accx3) {
        this.Accx3 = Accx3;
    }

    /**
     * @return the Accy3
     */
    public float getAccy3() {
        return Accy3;
    }

    /**
     * @param Accy3 the Accy3 to set
     */
    public void setAccy3(float Accy3) {
        this.Accy3 = Accy3;
    }

    /**
     * @return the Accz3
     */
    public float getAccz3() {
        return Accz3;
    }

    /**
     * @param Accz3 the Accz3 to set
     */
    public void setAccz3(float Accz3) {
        this.Accz3 = Accz3;
    }

    /**
     * @return the Accx4
     */
    public float getAccx4() {
        return Accx4;
    }

    /**
     * @param Accx4 the Accx4 to set
     */
    public void setAccx4(float Accx4) {
        this.Accx4 = Accx4;
    }

    /**
     * @return the Accy4
     */
    public float getAccy4() {
        return Accy4;
    }

    /**
     * @param Accy4 the Accy4 to set
     */
    public void setAccy4(float Accy4) {
        this.Accy4 = Accy4;
    }

    /**
     * @return the Accz4
     */
    public float getAccz4() {
        return Accz4;
    }

    /**
     * @param Accz4 the Accz4 to set
     */
    public void setAccz4(float Accz4) {
        this.Accz4 = Accz4;
    }

    /**
     * @return the line
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * @return the tokens
     */
    public StringTokenizer getTokens() {
        return tokens;
    }

    /**
     * @param tokens the tokens to set
     */
    public void setTokens(StringTokenizer tokens) {
        this.tokens = tokens;
    }

    /**
     * @return the EVENT
     */
    public jssc.SerialPortEventListener getEVENT() {
        return EVENT;
    }

    /**
     * @param EVENT the EVENT to set
     */
    public void setEVENT(jssc.SerialPortEventListener EVENT) {
        this.EVENT = EVENT;
    }

    /**
     * @return the contador
     */
    public int getContador() {
        return contador;
    }

    /**
     * @param contador the contador to set
     */
    public void setContador(int contador) {
        this.contador = contador;
    }

    /**
     * @return the sentencia
     */
    public String getSentencia() {
        return sentencia;
    }

    /**
     * @return the xy
     */
    public Grafico getXy() {
        return xy;
    }
}
