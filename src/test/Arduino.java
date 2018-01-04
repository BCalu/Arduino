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
    private float vec1, vec2, vec3, vec4;
    private String line;
    private StringTokenizer tokens;
    private int contador = 1;
    
    /* Instanciar evento receptor de bits (mensaje) */
    private jssc.SerialPortEventListener EVENT = new jssc.SerialPortEventListener() {
        @Override
        /* Que hara cuando reciba el mensaje */
        public void serialEvent(jssc.SerialPortEvent spe) {
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
    
    /**
     * Actualiza los valores de las variables dentro del grafico
     */
    public void actualizarGrafico(){
        tokenizar(getLine());
        
        //ACC1's
        if(xy.getSeries().contains(xy.getAcc1())){
            getXy().agregarASerie(getXy().getAcc1(), getContador(), getVec1());
            //System.out.println("VECTOR ACC1: "+getVec1());
        }
        
        //ACC2's
        if(xy.getSeries().contains(xy.getAcc2())){
            getXy().agregarASerie(getXy().getAcc2(), getContador(), getVec2());
            //System.out.println("VECTOR ACC2: "+getVec2());
        }
        
        //ACC3's
        if(xy.getSeries().contains(xy.getAcc3())){
            getXy().agregarASerie(getXy().getAcc3(), getContador(), getVec3());
            //System.out.println("VECTOR ACC3: "+getVec3());
        }
        
        //ACC4's
        if(xy.getSeries().contains(xy.getAcc4())){
            getXy().agregarASerie(getXy().getAcc4(), getContador(), getVec4());
            //System.out.println("VECTOR ACC4: "+getVec4());
        }
    }
    
    public void calcularVector1(){
        float coordenadaX = (float) Math.pow(getAccX(), 2);
        float coordenadaY = (float) Math.pow(getAccY(), 2);
        float coordenadaZ = (float) Math.pow(getAccZ(), 2);
        setVec1((float) Math.sqrt(coordenadaX + coordenadaY + coordenadaZ));
    }
    
    public void calcularVector2(){
        float coordenadaX2 = (float) Math.pow(getAccx2(), 2);
        float coordenadaY2 = (float) Math.pow(getAccy2(), 2);
        float coordenadaZ2 = (float) Math.pow(getAccz2(), 2);
        setVec2((float) Math.sqrt(coordenadaX2 + coordenadaY2 + coordenadaZ2));
    }
    
    public void calcularVector3(){
        float coordenadaX3 = (float) Math.pow(getAccx3(), 2);
        float coordenadaY3 = (float) Math.pow(getAccy3(), 2);
        float coordenadaZ3 = (float) Math.pow(getAccz3(), 2);
        setVec3((float) Math.sqrt(coordenadaX3 + coordenadaY3 + coordenadaZ3));
    }
    
    public void calcularVector4(){
        float coordenadaX4 = (float) Math.pow(getAccx4(), 2);
        float coordenadaY4 = (float) Math.pow(getAccy4(), 2);
        float coordenadaZ4 = (float) Math.pow(getAccz4(), 2);
        setVec4((float) Math.sqrt(coordenadaX4 + coordenadaY4 + coordenadaZ4));
    }
    
    public void calcularVectores(){
        calcularVector1();
        calcularVector2();
        calcularVector3();
        calcularVector4();
    }
    
    /**
     * Define el valor de las variables que manda el arduino
     * 
     * @param linea variables que manda el arduino
     */
    public void tokenizar(String linea){
        int cont=1;
        setTokens(new StringTokenizer(linea, ","));

        if(getTokens().countTokens() != 12){
            setVec1(0);
            setVec2(0);
            setVec3(0);
            setVec4(0);
            return;
        }
        
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
        calcularVectores();
        prepareSqlString();
    }
    
    /**
     * Prepara la sentencia SQL y agrega a la BDD
     */
    public void prepareSqlString(){      
        setSQL("INSERT INTO test (Fecha, AccX, AccY, AccZ, accx2, accy2, accz2, accx3, accy3, accz3, accx4, accy4, accz4) "
                + "VALUES ('" + getFormato().format(getFecha()) + "', " + getAccX() + ", " + getAccY() + ", " + getAccZ() + 
                ", " + getAccx2() + ", " + getAccy2() + ", " + getAccz2() + ", " + getAccx3() + ", " + getAccy3() + ", " + 
                getAccz3() + ", " + getAccx4() + ", " + getAccy4() + ", " + getAccz4() + ")");
        CRUD.sendQuery(getSQL());
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
        this.setSentencia(SQL);
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

    /**
     * @param sentencia the sentencia to set
     */
    public void setSentencia(String sentencia) {
        this.sentencia = sentencia;
    }

    /**
     * @return the vec1
     */
    public float getVec1() {
        return vec1;
    }

    /**
     * @param vec1 the vec1 to set
     */
    public void setVec1(float vec1) {
        this.vec1 = vec1;
    }

    /**
     * @return the vec2
     */
    public float getVec2() {
        return vec2;
    }

    /**
     * @param vec2 the vec2 to set
     */
    public void setVec2(float vec2) {
        this.vec2 = vec2;
    }

    /**
     * @return the vec3
     */
    public float getVec3() {
        return vec3;
    }

    /**
     * @param vec3 the vec3 to set
     */
    public void setVec3(float vec3) {
        this.vec3 = vec3;
    }

    /**
     * @return the vec4
     */
    public float getVec4() {
        return vec4;
    }

    /**
     * @param vec4 the vec4 to set
     */
    public void setVec4(float vec4) {
        this.vec4 = vec4;
    }
}
