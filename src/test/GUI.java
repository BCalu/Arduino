package test;

import com.panamahitek.ArduinoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.Date;
import java.util.logging.SimpleFormatter;
import javax.swing.JComboBox;
import jssc.SerialPortException;

public class GUI extends javax.swing.JFrame {
    public Arduino ard;
    
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        jButton_Detener.setEnabled(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton_Exportar = new javax.swing.JButton();
        jComboBox_Registro1 = new javax.swing.JComboBox<>();
        jComboBox_Registro2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton_Graficar = new javax.swing.JButton();
        jButton_Detener = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jButton_ExportarDatos = new javax.swing.JButton();

        jFrame1.setMinimumSize(new java.awt.Dimension(400, 300));
        jFrame1.setSize(new java.awt.Dimension(400, 300));
        jFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Registro 1");
        jFrame1.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Seleccione los registros");
        jFrame1.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Registro 2");
        jFrame1.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jButton_Exportar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_Exportar.setText("Exportar");
        jButton_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExportarActionPerformed(evt);
            }
        });
        jFrame1.getContentPane().add(jButton_Exportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        jFrame1.getContentPane().add(jComboBox_Registro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 220, -1));

        jFrame1.getContentPane().add(jComboBox_Registro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 220, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú");
        setMaximumSize(new java.awt.Dimension(300, 300));
        setMinimumSize(new java.awt.Dimension(300, 300));
        setPreferredSize(new java.awt.Dimension(300, 350));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Eliga el n° de puerto COM");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Valores de vectores a graficar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "13" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jButton_Graficar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_Graficar.setText("Graficar");
        jButton_Graficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GraficarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Graficar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jButton_Detener.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_Detener.setText("Detener");
        jButton_Detener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DetenerActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Detener, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, -1, -1));

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBox1.setText("Acc1");
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBox2.setText("Acc2");
        getContentPane().add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        jCheckBox3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBox3.setText("Acc3");
        getContentPane().add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jCheckBox4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBox4.setText("Acc4");
        getContentPane().add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, -1));

        jButton_ExportarDatos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_ExportarDatos.setText("Exportar Datos");
        jButton_ExportarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExportarDatosActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_ExportarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GraficarActionPerformed
        ard = new Arduino();
        ard.setPORT_NAME("COM"+(String) jComboBox1.getSelectedItem());

        if(jCheckBox1.isSelected())
            ard.getXy().getSeries().add(ard.getXy().getAcc1());

        if(jCheckBox2.isSelected())
            ard.getXy().getSeries().add(ard.getXy().getAcc2());

        if(jCheckBox3.isSelected())
            ard.getXy().getSeries().add(ard.getXy().getAcc3());
        
        if(jCheckBox4.isSelected())
            ard.getXy().getSeries().add(ard.getXy().getAcc4());

        ard.prepararGrafico();
        jComboBox1.setEnabled(false);
        jButton_Graficar.setEnabled(false);
        jButton_Detener.setEnabled(true);
        jCheckBox1.setEnabled(false);
        jCheckBox2.setEnabled(false);
        jCheckBox3.setEnabled(false);
        jCheckBox4.setEnabled(false);
        
        try {
            ard.getARD().arduinoRX(ard.getPORT_NAME(), ard.getDATA_RATE(), ard.getEVENT());
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_GraficarActionPerformed

    private void jButton_DetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DetenerActionPerformed
        try {
            ard.getARD().killArduinoConnection();
        } catch (ArduinoException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        jComboBox1.setEnabled(true);
        jButton_Graficar.setEnabled(true);
        jButton_Detener.setEnabled(false);
        jCheckBox1.setEnabled(true);
        jCheckBox2.setEnabled(true);
        jCheckBox3.setEnabled(true);
        jCheckBox4.setEnabled(true);
    }//GEN-LAST:event_jButton_DetenerActionPerformed

    private void jButton_ExportarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExportarDatosActionPerformed
        cargarDatosComboBox(jComboBox_Registro1);
        cargarDatosComboBox(jComboBox_Registro2);
        jFrame1.setVisible(true);
        jFrame1.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jButton_ExportarDatosActionPerformed

    private void jButton_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExportarActionPerformed
        Date fechaInicial = null;
        Date fechaFinal = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String SfechaInicial = (String) jComboBox_Registro1.getSelectedItem();
        String SfechaFinal = (String) jComboBox_Registro2.getSelectedItem();
        try {
            fechaInicial = StringToDate(SfechaInicial, df);
            fechaFinal = StringToDate(SfechaFinal, df);
            //System.out.println(df.format(fechaInicial));
            //System.out.println(df.format(fechaFinal));
        } catch (ParseException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(compararFechas(fechaInicial, fechaFinal) == true){
            System.out.println("CREAR TXT");
            ResultSet rs = obtenerRegistros((String) jComboBox_Registro1.getSelectedItem(), (String) jComboBox_Registro2.getSelectedItem());
        }
        else{
            System.out.println("NEL :V");
        }        
    }//GEN-LAST:event_jButton_ExportarActionPerformed

    
    /**
     * Comprueba si la fecha final es antes, despues o igual a la fecha inicial
     * 
     * @param f1 fecha inicial
     * @param f2 fecha final
     * @return true si final es despues de inicial, false de caso contrario
     */
    public boolean compararFechas(Date f1, Date f2){
        if(f1 == null && f2 == null){
            return false;
        }
        return f2.after(f1) || f2.equals(f1);
    }
    
    /**
     * Carga las fechas de la BDD al JComboBox B
     * 
     * @param B JComboBox donde se almacenaran los datos
     */
    public void cargarDatosComboBox(JComboBox B){
        ResultSet valores = crud.CRUD.getQuery("SELECT Fecha FROM test");
        String fecha;
        try {
            while(valores.next()){
                fecha = valores.getString("Fecha");
                B.addItem(fecha);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Transforma el String Sdate a java.sql.Date
     * 
     * @param Sdate la fecha en formato String
     * @return java.sql.Date
     * @throws ParseException 
     */
    public Date StringToDate(String Sdate, DateFormat df) throws ParseException{
        java.util.Date Udate = df.parse(Sdate);
        //System.out.println(df.format(Udate));
        Date d = null;
        d = new Date(Udate.getTime());
        //System.out.println(df.format(d));
        return d;
    }
    
    /**
     * Obtiene los registros que estan entremedio de las dos fechas indicadas
     * 
     * @param r1 Registro 1
     * @param r2 Registro 2
     * @return El ResultSet con los registros
     */
    public ResultSet obtenerRegistros(String r1, String r2){
        ResultSet rs  = crud.CRUD.getQuery("SELECT Fecha from test "
                + "WHERE Fecha BETWEEN '"+r1+"' and '"+r2+"'");
        try {
            while(rs.next()){
                System.out.println(rs.getString("Fecha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void crearTXT(){
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Detener;
    private javax.swing.JButton jButton_Exportar;
    private javax.swing.JButton jButton_ExportarDatos;
    private javax.swing.JButton jButton_Graficar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox_Registro1;
    private javax.swing.JComboBox<String> jComboBox_Registro2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
