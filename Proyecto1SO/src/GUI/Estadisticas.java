/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import static GUI.Simulacion.clock;
import static GUI.Simulacion.scheduler;
import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author cristiandresgp
 */
public class Estadisticas extends javax.swing.JFrame {
    
    // Variables para estadisticas
    private boolean running = true;
    
    // General
    public static int procesostotales = 0;
    public static int procesoscpubound = 0;
    public static int procesosiobound = 0;
    public static int ciclosgeneral = 0;
    public static double throughputgeneral = 0.0;
    
    // SRT
    public static int ciclossrt = 0;
    public static int procesossrt = 0;
    public static int cpuboundsrt = 0;
    public static int ioboundsrt = 0;
    public static double throughputsrt = 0;
     
    // SJF
    public static int ciclossjf = 0;
    public static int procesossjf = 0;
    public static int cpuboundsjf = 0;
    public static int ioboundsjf = 0;
    public static double throughputsjf = 0;
    
    // FCFS
    public static int ciclosfcfs = 0;
    public static int procesosfcfs = 0;
    public static int cpuboundfcfs = 0;
    public static int ioboundfcfs = 0;
    public static double throughputfcfs = 0;

    // HRRN
    public static int cicloshrrn = 0;
    public static int procesoshrrn = 0;
    public static int cpuboundhrrn = 0;
    public static int ioboundhrrn = 0;
    public static double throughputhrrn = 0;
    
    // Round Robin
    public static int ciclosrr = 0;
    public static int procesosrr = 0;
    public static int cpuboundrr = 0;
    public static int ioboundrr = 0;
    public static double throughputrr = 0;
    
    public static int clockomg = 0;
    
    public Estadisticas() {
        initComponents();
        
            new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                SwingUtilities.invokeLater(() -> {
                updateStatisticsGUI();
                   

                });
            }
        }).start();
    }
    
    
    public void updateStatistics(){
        
    }

   


// No estamos usando este, era de prueba
    public void uupdateStatisticsGUI() {
    new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(100); // Pausa de 100 ms
                SwingUtilities.invokeLater(() -> {
        // General
        System.out.println("actualizando estadisticasss");
        procesosTerminados.setText(Integer.toString(procesostotales));
        procesosIOBound.setText(Integer.toString(procesoscpubound));
        procesosCPUBound.setText(Integer.toString(procesosiobound));
        ciclos.setText(Integer.toString(ciclosgeneral));
        throughput.setText(Double.toString(throughputgeneral));

        // SRT
        ciclosSRT.setText(Integer.toString(ciclossrt));
        procesosSRT.setText(Integer.toString(procesossrt));
        cpuboundSRT.setText(Integer.toString(cpuboundsrt));
        ioboundSRT.setText(Integer.toString(ioboundsrt));
        throughputSRT.setText(Double.toString(throughputsrt));
        
        //SJF
        ciclosSJF.setText(Integer.toString(ciclossjf));
        procesosSJF.setText(Integer.toString(procesossjf));
        cpuboundSJF.setText(Integer.toString(cpuboundsjf));
        ioboundSJF.setText(Integer.toString(ioboundsjf));
        throughputSJF.setText(Double.toString(throughputsjf));

        //FCFS
        ciclosFCFS.setText(Integer.toString(ciclosfcfs));
        procesosFCFS.setText(Integer.toString(procesosfcfs));
        cpuboundFCFS.setText(Integer.toString(cpuboundfcfs));
        ioboundFCFS.setText(Integer.toString(ioboundfcfs));
        throughputFCFS.setText(Double.toString(throughputfcfs));

        //HRRN
        ciclosHRRN.setText(Integer.toString(cicloshrrn));
        procesosHRRN.setText(Integer.toString(procesoshrrn));
        cpuboundHRRN.setText(Integer.toString(cpuboundhrrn));
        ioboundHRRN.setText(Integer.toString(ioboundhrrn));
        throughputHRRN.setText(Double.toString(throughputhrrn));

        //Round Robin
        ciclosRR.setText(Integer.toString(ciclosrr));
        procesosRR.setText(Integer.toString(procesosrr));
        cpuboundRR.setText(Integer.toString(cpuboundrr));
        ioboundRR.setText(Integer.toString(ioboundrr));
        throughputRR.setText(Double.toString(throughputrr));

                    
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
        }
    }).start();
}
    
// Esta es la actualizacion que estamos usando
    public void updateStatisticsGUI(){
        // General
        System.out.println("actualizandooo estadisticas");
        System.out.println("Procesos totales:" + procesostotales);
        System.out.println("Procesos CPU Bound:" + procesoscpubound);
        System.out.println("Procesos IO Bound:" + procesosiobound);
        
        procesosTerminados.setText("hola");
        procesosTerminados.setText(Integer.toString(procesostotales));
        procesosIOBound.setText(Integer.toString(procesoscpubound));
        procesosCPUBound.setText(Integer.toString(procesosiobound));
        ciclos.setText(Integer.toString(clockomg));
        throughput.setText(Double.toString(throughputgeneral));
       
        // SRT
        ciclosSRT.setText(Integer.toString(ciclossrt));
        procesosSRT.setText(Integer.toString(procesossrt));
        cpuboundSRT.setText(Integer.toString(cpuboundsrt));
        ioboundSRT.setText(Integer.toString(ioboundsrt));
        throughputSRT.setText(Double.toString(throughputsrt));
        
        //SJF
        ciclosSJF.setText(Integer.toString(ciclossjf));
        procesosSJF.setText(Integer.toString(procesossjf));
        cpuboundSJF.setText(Integer.toString(cpuboundsjf));
        ioboundSJF.setText(Integer.toString(ioboundsjf));
        throughputSJF.setText(Double.toString(throughputsjf));

        //FCFS
        ciclosFCFS.setText(Integer.toString(ciclosfcfs));
        procesosFCFS.setText(Integer.toString(procesosfcfs));
        cpuboundFCFS.setText(Integer.toString(cpuboundfcfs));
        ioboundFCFS.setText(Integer.toString(ioboundfcfs));
        throughputFCFS.setText(Double.toString(throughputfcfs));

        //HRRN
        ciclosHRRN.setText(Integer.toString(cicloshrrn));
        procesosHRRN.setText(Integer.toString(procesoshrrn));
        cpuboundHRRN.setText(Integer.toString(cpuboundhrrn));
        ioboundHRRN.setText(Integer.toString(ioboundhrrn));
        throughputHRRN.setText(Double.toString(throughputhrrn));

        //Round Robin
        ciclosRR.setText(Integer.toString(ciclosrr));
        procesosRR.setText(Integer.toString(procesosrr));
        cpuboundRR.setText(Integer.toString(cpuboundrr));
        ioboundRR.setText(Integer.toString(ioboundrr));
        throughputRR.setText(Double.toString(throughputrr));

    }

// Esta es otra prueba, no lo estamos usando
public void uuupdateStatisticsGUI() {
    // Ensure that the UI update happens on the Event Dispatch Thread
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            // General
            System.out.println("actualizandooo estadisticas");

            // Set the texts for your labels
            procesosTerminados.setText(Integer.toString(procesostotales));
            procesosIOBound.setText(Integer.toString(procesoscpubound));
            procesosCPUBound.setText(Integer.toString(procesosiobound));
            ciclos.setText(Integer.toString(ciclosgeneral));
            throughput.setText(Double.toString(throughputgeneral));

            // SRT
            ciclosSRT.setText(Integer.toString(ciclossrt));
            procesosSRT.setText(Integer.toString(procesossrt));
            cpuboundSRT.setText(Integer.toString(cpuboundsrt));
            ioboundSRT.setText(Integer.toString(ioboundsrt));
            throughputSRT.setText(Double.toString(throughputsrt));

            // SJF
            ciclosSJF.setText(Integer.toString(ciclossjf));
            procesosSJF.setText(Integer.toString(procesossjf));
            cpuboundSJF.setText(Integer.toString(cpuboundsjf));
            ioboundSJF.setText(Integer.toString(ioboundsjf));
            throughputSJF.setText(Double.toString(throughputsjf));

            // FCFS
            ciclosFCFS.setText(Integer.toString(ciclosfcfs));
            procesosFCFS.setText(Integer.toString(procesosfcfs));
            cpuboundFCFS.setText(Integer.toString(cpuboundfcfs));
            ioboundFCFS.setText(Integer.toString(ioboundfcfs));
            throughputFCFS.setText(Double.toString(throughputfcfs));

            // HRRN
            ciclosHRRN.setText(Integer.toString(cicloshrrn));
            procesosHRRN.setText(Integer.toString(procesoshrrn));
            cpuboundHRRN.setText(Integer.toString(cpuboundhrrn));
            ioboundHRRN.setText(Integer.toString(ioboundhrrn));
            throughputHRRN.setText(Double.toString(throughputhrrn));

            // Round Robin
            ciclosRR.setText(Integer.toString(ciclosrr));
            procesosRR.setText(Integer.toString(procesosrr));
            cpuboundRR.setText(Integer.toString(cpuboundrr));
            ioboundRR.setText(Integer.toString(ioboundrr));
            throughputRR.setText(Double.toString(throughputrr));
        }
    });
}

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        regresar = new javax.swing.JButton();
        General = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        procesosTerminados = new javax.swing.JLabel();
        procesosIOBound = new javax.swing.JLabel();
        procesosCPUBound = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ciclos = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        throughput = new javax.swing.JLabel();
        FCFS = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ciclosFCFS = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        procesosFCFS = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cpuboundFCFS = new javax.swing.JLabel();
        ioboundFCFS = new javax.swing.JLabel();
        throughputFCFS = new javax.swing.JLabel();
        SJF = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ciclosSJF = new javax.swing.JLabel();
        procesosSJF = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cpuboundSJF = new javax.swing.JLabel();
        ioboundSJF = new javax.swing.JLabel();
        throughputSJF = new javax.swing.JLabel();
        RoundRobin = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        ciclosRR = new javax.swing.JLabel();
        procesosRR = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cpuboundRR = new javax.swing.JLabel();
        ioboundRR = new javax.swing.JLabel();
        throughputRR = new javax.swing.JLabel();
        SRT = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        ciclosSRT = new javax.swing.JLabel();
        procesosSRT = new javax.swing.JLabel();
        cpuboundSRT = new javax.swing.JLabel();
        ioboundSRT = new javax.swing.JLabel();
        throughputSRT = new javax.swing.JLabel();
        HRRN = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        ciclosHRRN = new javax.swing.JLabel();
        procesosHRRN = new javax.swing.JLabel();
        cpuboundHRRN = new javax.swing.JLabel();
        ioboundHRRN = new javax.swing.JLabel();
        throughputHRRN = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("ESTADÍSTICAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));

        regresar.setText("REGRESAR");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });
        jPanel1.add(regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        General.setBackground(new java.awt.Color(204, 255, 255));
        General.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setText("General");
        General.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel3.setText("Procesos Terminados:");
        General.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setText("Procesos I/O Bound Terminados:");
        General.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel5.setText("Procesos CPU Bound Terminados:");
        General.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        procesosTerminados.setText("procesosTerminados");
        General.add(procesosTerminados, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        procesosIOBound.setText("procesosCPUBound");
        General.add(procesosIOBound, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        procesosCPUBound.setText("procesosIOBound");
        General.add(procesosCPUBound, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, 20));

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel14.setText("Ciclos:");
        General.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        ciclos.setText("ciclos");
        General.add(ciclos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel39.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel39.setText("Throughput:");
        General.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        throughput.setText("throughput");
        General.add(throughput, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

        jPanel1.add(General, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 380, 210));

        FCFS.setBackground(new java.awt.Color(204, 204, 255));
        FCFS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel6.setText("FCFS");
        FCFS.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel9.setText("Ciclos FCFS:");
        FCFS.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));

        ciclosFCFS.setText("ciclosFCFS");
        FCFS.add(ciclosFCFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel16.setText("Procesos ejecutados:");
        FCFS.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        procesosFCFS.setText("procesosFCFS");
        FCFS.add(procesosFCFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel12.setText("Procesos I/O Bound:");
        FCFS.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel13.setText("Procesos CPU Bound:");
        FCFS.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel15.setText("Throughput:");
        FCFS.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        cpuboundFCFS.setText("cpuboundFCFS");
        FCFS.add(cpuboundFCFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        ioboundFCFS.setText("ioboundFCFS");
        FCFS.add(ioboundFCFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        throughputFCFS.setText("throughputFCFS");
        FCFS.add(throughputFCFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jPanel1.add(FCFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, 270, 190));

        SJF.setBackground(new java.awt.Color(204, 204, 255));
        SJF.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel7.setText("SJF");
        SJF.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel18.setText("Ciclos SJF:");
        SJF.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel19.setText("Procesos Ejecutados:");
        SJF.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        ciclosSJF.setText("ciclosSJF");
        SJF.add(ciclosSJF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        procesosSJF.setText("procesosSJF");
        SJF.add(procesosSJF, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel17.setText("Procesos CPU Bound:");
        SJF.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel20.setText("Procesos I/O Bound:");
        SJF.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel21.setText("Throughput:");
        SJF.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        cpuboundSJF.setText("cpuboundSJF");
        SJF.add(cpuboundSJF, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        ioboundSJF.setText("ioboundSJF");
        SJF.add(ioboundSJF, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        throughputSJF.setText("throughputSJF");
        SJF.add(throughputSJF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jPanel1.add(SJF, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 270, 190));

        RoundRobin.setBackground(new java.awt.Color(204, 204, 255));
        RoundRobin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel8.setText("Round Robin");
        RoundRobin.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel22.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel22.setText("Ciclos Round Robin:");
        RoundRobin.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel23.setText("Procesos Ejecutados:");
        RoundRobin.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        ciclosRR.setText("ciclosRR");
        RoundRobin.add(ciclosRR, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        procesosRR.setText("procesosRR");
        RoundRobin.add(procesosRR, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        jLabel24.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel24.setText("Procesos CPU Bound:");
        RoundRobin.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel26.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel26.setText("Procesos I/O Bound:");
        RoundRobin.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel27.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel27.setText("Throughput:");
        RoundRobin.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        cpuboundRR.setText("cpuboundRR");
        RoundRobin.add(cpuboundRR, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        ioboundRR.setText("ioboundRR");
        RoundRobin.add(ioboundRR, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        throughputRR.setText("throughputRR");
        RoundRobin.add(throughputRR, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jPanel1.add(RoundRobin, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, 270, 190));

        SRT.setBackground(new java.awt.Color(204, 204, 255));
        SRT.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel10.setText("SRT");
        SRT.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        jLabel28.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel28.setText("Ciclos SRT:");
        SRT.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel29.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel29.setText("Procesos Ejecutados:");
        SRT.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel30.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel30.setText("Procesos CPU Bound:");
        SRT.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel31.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel31.setText("Procesos I/O Bound:");
        SRT.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel32.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel32.setText("Throughput:");
        SRT.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        ciclosSRT.setText("ciclosSRT");
        SRT.add(ciclosSRT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        procesosSRT.setText("procesosSRT");
        SRT.add(procesosSRT, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        cpuboundSRT.setText("cpuboundSRT");
        SRT.add(cpuboundSRT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        ioboundSRT.setText("ioboundSRT");
        SRT.add(ioboundSRT, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        throughputSRT.setText("throughputSRT");
        SRT.add(throughputSRT, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jPanel1.add(SRT, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 270, 190));

        HRRN.setBackground(new java.awt.Color(204, 204, 255));
        HRRN.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel11.setText("HRRN");
        HRRN.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        jLabel33.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel33.setText("Ciclos HRRN:");
        HRRN.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel34.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel34.setText("Procesos Ejecutados:");
        HRRN.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel35.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel35.setText("Procesos CPU Bound:");
        HRRN.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel36.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel36.setText("Procesos I/O Bound:");
        HRRN.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel37.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel37.setText("Throughput:");
        HRRN.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        ciclosHRRN.setText("ciclosHRRN");
        HRRN.add(ciclosHRRN, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        procesosHRRN.setText("procesosHRRN");
        HRRN.add(procesosHRRN, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        cpuboundHRRN.setText("cpuboundHRRN");
        HRRN.add(cpuboundHRRN, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        ioboundHRRN.setText("ioboundHRRN");
        HRRN.add(ioboundHRRN, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        throughputHRRN.setText("throughputHRRN");
        HRRN.add(throughputHRRN, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, -1, -1));

        jPanel1.add(HRRN, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 270, 190));

        jLabel38.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel38.setText("Políticas de Planificación");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1014, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        FuncionesInterfaz.VolverMenu();
    }//GEN-LAST:event_regresarActionPerformed

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
            java.util.logging.Logger.getLogger(Estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estadisticas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Estadisticas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FCFS;
    private javax.swing.JPanel General;
    private javax.swing.JPanel HRRN;
    private javax.swing.JPanel RoundRobin;
    private javax.swing.JPanel SJF;
    private javax.swing.JPanel SRT;
    private javax.swing.JLabel ciclos;
    private javax.swing.JLabel ciclosFCFS;
    private javax.swing.JLabel ciclosHRRN;
    private javax.swing.JLabel ciclosRR;
    private javax.swing.JLabel ciclosSJF;
    private javax.swing.JLabel ciclosSRT;
    private javax.swing.JLabel cpuboundFCFS;
    private javax.swing.JLabel cpuboundHRRN;
    private javax.swing.JLabel cpuboundRR;
    private javax.swing.JLabel cpuboundSJF;
    private javax.swing.JLabel cpuboundSRT;
    private javax.swing.JLabel ioboundFCFS;
    private javax.swing.JLabel ioboundHRRN;
    private javax.swing.JLabel ioboundRR;
    private javax.swing.JLabel ioboundSJF;
    private javax.swing.JLabel ioboundSRT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel procesosCPUBound;
    private javax.swing.JLabel procesosFCFS;
    private javax.swing.JLabel procesosHRRN;
    private javax.swing.JLabel procesosIOBound;
    private javax.swing.JLabel procesosRR;
    private javax.swing.JLabel procesosSJF;
    private javax.swing.JLabel procesosSRT;
    private javax.swing.JLabel procesosTerminados;
    private javax.swing.JButton regresar;
    private javax.swing.JLabel throughput;
    private javax.swing.JLabel throughputFCFS;
    private javax.swing.JLabel throughputHRRN;
    private javax.swing.JLabel throughputRR;
    private javax.swing.JLabel throughputSJF;
    private javax.swing.JLabel throughputSRT;
    // End of variables declaration//GEN-END:variables

}
