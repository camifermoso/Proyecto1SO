/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import OBJECTS.Clock;
import OBJECTS.ExceptionHandler;
import OBJECTS.Scheduler;
import OBJECTS.CPU;

/**
 *
 * @author cristiandresgp
 */
public class Simulacion extends javax.swing.JFrame {
    private Clock clock;
    private Scheduler scheduler;
    private ExceptionHandler exceptionHandler;
    private boolean running = true; // Control del hilo de actualización
    private CPU cpu1, cpu2, cpu3;
    private boolean cpu3Active = true;

    /**
     * Constructor
     */
    public Simulacion() {      
        initComponents();
        clock = new Clock();
        scheduler = new Scheduler("FCFS", 5); // Iniciar con política predeterminada
        exceptionHandler = new ExceptionHandler();
        cpu1 = new CPU(1);
        cpu2 = new CPU(2);
        cpu3 = new CPU(3);
        customInit();  // Inicializa los elementos visuales
        createCPUDisplays(); // Crea los paneles para los CPUs
        clock.startClock(); // Inicia el reloj una vez que la interfaz está lista

        // Hilo para actualizar el ciclo de reloj en la interfaz
        new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                SwingUtilities.invokeLater(() -> {
                    clockLabel.setText("CICLO DE RELOJ GLOBAL: " + clock.getCurrentCycle());
                    updateCPUsDisplay();
                });
            }
        }).start();
    }
    
    /**
     * Inicializa los elementos personalizados
     */
    private void customInit() {
        tickDurationSpinner.setModel(new SpinnerNumberModel(1000, 100, 5000, 100));
        // Evitar que el usuario edite el valor manualmente
        JComponent editor = tickDurationSpinner.getEditor();
        JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setFocusable(false);
        tickDurationSpinner.setFocusable(false);
        
        tickDurationSpinner.addChangeListener(e -> {
            int newDuration = (int) tickDurationSpinner.getValue();
            clock.setTickDuration(newDuration);
        });
        
        // Configurar el JComboBox de política de planificación
        politicaPlanificacion.setModel(new DefaultComboBoxModel<>(new String[]{"FCFS", "SJF", "Round Robin", "SRT", "HRRN"}));
        politicaPlanificacion.addActionListener(e -> {
            String selectedPolicy = (String) politicaPlanificacion.getSelectedItem();
            scheduler.setAlgorithm(selectedPolicy);
            System.out.println("Política de planificación cambiada a: " + selectedPolicy);
        });

        // Configurar el JComboBox de CPUs disponibles
        cpus.setModel(new DefaultComboBoxModel<>(new String[]{"3", "2"}));
        cpus.addActionListener(e -> {
            int selectedCPUs = Integer.parseInt((String) cpus.getSelectedItem());
            cpu3Active = (selectedCPUs == 3);
            jpanelcpu3.setVisible(cpu3Active);
            System.out.println("Cantidad de CPUs activas: " + selectedCPUs);
        });
    }

    /**
     * Crea los paneles de los CPUs
     */
    private void createCPUDisplays() {
    configureCPUPanel(jpanelcpu1, "CPU 1");
    configureCPUPanel(jpanelcpu2, "CPU 2");
    configureCPUPanel(jpanelcpu3, "CPU 3");
    
    jpanelcpu3.setVisible(cpu3Active); // Mostrar u ocultar CPU 3 según la configuración
    }
    
    private void configureCPUPanel(JPanel panel, String title) {
    panel.setLayout(new GridLayout(6, 1)); // Organiza los labels en una columna
    panel.removeAll(); // Elimina cualquier contenido anterior

    JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
    JLabel processLabel = new JLabel(" Proceso en ejecución: -");
    JLabel idLabel = new JLabel(" ID: -");
    JLabel pcLabel = new JLabel(" PC: -");
    JLabel marLabel = new JLabel(" MAR: -");
    JLabel statusLabel = new JLabel(" Status: -");

    panel.add(titleLabel);
    panel.add(processLabel);
    panel.add(idLabel);
    panel.add(pcLabel);
    panel.add(marLabel);
    panel.add(statusLabel);

    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.revalidate();
    panel.repaint();
    }

    /**
     * Método para actualizar la interfaz de los CPUs
     */
    private void updateCPUsDisplay() {
        jpanelcpu1.setVisible(true);
        jpanelcpu2.setVisible(true);
        jpanelcpu3.setVisible(cpu3Active);
    }

    /**
     * Método para detener el hilo antes de cerrar la ventana
     */
    @Override
    public void dispose() {
        running = false; // Detener el hilo de actualización
        super.dispose();
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        exitqueue = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        readyqueue = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        politicaPlanificacion = new javax.swing.JComboBox<>();
        clockLabel = new javax.swing.JLabel();
        tickDurationSpinner = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        blockedqueue1 = new javax.swing.JTextArea();
        cpulabel = new javax.swing.JLabel();
        cpus = new javax.swing.JComboBox<>();
        jpanelcpu1 = new javax.swing.JPanel();
        jpanelcpu2 = new javax.swing.JPanel();
        jpanelcpu3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        estadisticas = new javax.swing.JButton();
        graficos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("SIMULACIÓN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jLabel2.setText("COLA DE TERMINADOS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        exitqueue.setEditable(false);
        exitqueue.setColumns(20);
        exitqueue.setRows(5);
        exitqueue.setFocusable(false);
        jScrollPane1.setViewportView(exitqueue);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 450, -1));

        jLabel3.setText("POLÍTICA DE PLANIFICACION ACTUAL: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, -1, -1));

        readyqueue.setEditable(false);
        readyqueue.setColumns(20);
        readyqueue.setRows(5);
        readyqueue.setFocusable(false);
        jScrollPane2.setViewportView(readyqueue);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 450, -1));

        jLabel4.setText("COLA DE LISTOS");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        politicaPlanificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        politicaPlanificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                politicaPlanificacionActionPerformed(evt);
            }
        });
        jPanel1.add(politicaPlanificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 490, 180, -1));

        clockLabel.setText("CICLO DE RELOJ GLOBAL: 0");
        jPanel1.add(clockLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, -1, -1));
        jPanel1.add(tickDurationSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 70, -1, -1));

        jLabel5.setText("(duración en ms)");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, -1, -1));

        jLabel6.setText("COLA DE BLOQUEADOS");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        blockedqueue1.setEditable(false);
        blockedqueue1.setColumns(20);
        blockedqueue1.setRows(5);
        blockedqueue1.setFocusable(false);
        jScrollPane3.setViewportView(blockedqueue1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 450, -1));

        cpulabel.setText("PROCESADORES DISPONIBLES:");
        jPanel1.add(cpulabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, -1, -1));

        cpus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cpus, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, -1, -1));

        jpanelcpu1.setBorder(javax.swing.BorderFactory.createTitledBorder("CPU 1"));

        javax.swing.GroupLayout jpanelcpu1Layout = new javax.swing.GroupLayout(jpanelcpu1);
        jpanelcpu1.setLayout(jpanelcpu1Layout);
        jpanelcpu1Layout.setHorizontalGroup(
            jpanelcpu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jpanelcpu1Layout.setVerticalGroup(
            jpanelcpu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(jpanelcpu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, -1, -1));

        jpanelcpu2.setBorder(javax.swing.BorderFactory.createTitledBorder("CPU 2"));
        jpanelcpu2.setToolTipText("");

        javax.swing.GroupLayout jpanelcpu2Layout = new javax.swing.GroupLayout(jpanelcpu2);
        jpanelcpu2.setLayout(jpanelcpu2Layout);
        jpanelcpu2Layout.setHorizontalGroup(
            jpanelcpu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jpanelcpu2Layout.setVerticalGroup(
            jpanelcpu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(jpanelcpu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, -1, -1));

        jpanelcpu3.setBorder(javax.swing.BorderFactory.createTitledBorder("CPU 3"));

        javax.swing.GroupLayout jpanelcpu3Layout = new javax.swing.GroupLayout(jpanelcpu3);
        jpanelcpu3.setLayout(jpanelcpu3Layout);
        jpanelcpu3Layout.setHorizontalGroup(
            jpanelcpu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jpanelcpu3Layout.setVerticalGroup(
            jpanelcpu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(jpanelcpu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 220, -1, -1));

        jLabel7.setText("CREAR UN NUEVO PROCESO:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 380, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("CREAR");
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 130, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 490, 260));

        guardar.setText("GUARDAR");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 590, -1, -1));

        estadisticas.setText("ESTADÍSTICAS");
        estadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadisticasActionPerformed(evt);
            }
        });
        jPanel1.add(estadisticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 590, -1, -1));

        graficos.setText("GRÁFICOS");
        graficos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graficosActionPerformed(evt);
            }
        });
        jPanel1.add(graficos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1195, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void politicaPlanificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_politicaPlanificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_politicaPlanificacionActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        FuncionesInterfaz.openGuardar();
        this.setVisible(false);
    }//GEN-LAST:event_guardarActionPerformed

    private void estadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadisticasActionPerformed
        FuncionesInterfaz.openEstadisticas();
        this.setVisible(false);
    }//GEN-LAST:event_estadisticasActionPerformed

    private void graficosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graficosActionPerformed
        FuncionesInterfaz.openGraficos();
        this.setVisible(false);
    }//GEN-LAST:event_graficosActionPerformed

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
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Simulacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea blockedqueue1;
    private javax.swing.JLabel clockLabel;
    private javax.swing.JLabel cpulabel;
    private javax.swing.JComboBox<String> cpus;
    private javax.swing.JButton estadisticas;
    private javax.swing.JTextArea exitqueue;
    private javax.swing.JButton graficos;
    private javax.swing.JButton guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpanelcpu1;
    private javax.swing.JPanel jpanelcpu2;
    private javax.swing.JPanel jpanelcpu3;
    private javax.swing.JComboBox<String> politicaPlanificacion;
    private javax.swing.JTextArea readyqueue;
    private javax.swing.JSpinner tickDurationSpinner;
    // End of variables declaration//GEN-END:variables
}
