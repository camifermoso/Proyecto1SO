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
import OBJECTS.Process;
import java.awt.event.ActionEvent;

/**
 *
 * @author cristiandresgp
 */
public class Simulacion extends javax.swing.JFrame {
    public static Clock clock;
    public static Scheduler scheduler;
    public ExceptionHandler exceptionHandler;
    private boolean running = true; // Control del hilo de actualización
    public CPU cpu1, cpu2, cpu3;
    private boolean cpu3Active = true;

    /**
     * Constructor
     */
    public Simulacion(int numCPUs) {      
        initComponents();
        clock = new Clock();
        scheduler = new Scheduler("FCFS", 5); // Iniciar con política predeterminada
        exceptionHandler = new ExceptionHandler();
        customInit();  // Inicializa los elementos visuales
//        createCPUDisplays(); // Crea los paneles para los CPUs
        // Crear CPUs según la cantidad seleccionada
        cpu1 = new CPU(1, scheduler, clock, this);
        cpu2 = new CPU(2, scheduler, clock, this);
    
        if (numCPUs == 3) {
        cpu3 = new CPU(3, scheduler, clock, this);
        cpu3.start();
    } else {
        cpu3 = null; // No instanciar si se seleccionaron solo 2 CPUs
        jpanelcpu3.setVisible(false); // Ocultar el panel de CPU 3
    }

        cpu1.start();
        cpu2.start();
        clock.startClock(); // Inicia el reloj una vez que la interfaz está lista
        actualizarColaListos();

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
   
    public ExceptionHandler getExceptionHandler() {
    return exceptionHandler;
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
        
        // Definir opciones en los JComboBox
        tipoProceso.setModel(new DefaultComboBoxModel<>(new String[]{"I/O-bound", "CPU-bound"}));
        prioridadProceso.setModel(new DefaultComboBoxModel<>(new String[]{"ALTA", "MEDIA", "BAJA"}));

    }
    
    ////////
    ////////CPU
    ///////

    // Funciones para actualizar la interfaz de cada CPU
    public void actualizarCPU1() {
        cpu1nombre.setText(cpu1.getCurrentProcess() != null ? cpu1.getCurrentProcess().getName() : "Proceso");
        cpu1id.setText(cpu1.getCurrentProcess() != null ? String.valueOf(cpu1.getCurrentProcess().getProcessID()) : "No Disponible" );
        cpu1estado.setText(cpu1.getCurrentProcess() != null ? String.valueOf(cpu1.getCurrentProcess().getState()) : "No Disponible");
        cpu1pc.setText(cpu1.getCurrentProcess() != null ? String.valueOf(cpu1.getCurrentProcess().getProgramCounter()) : "No Disponible");
        cpu1mar.setText(cpu1.getCurrentProcess() != null ? String.valueOf(cpu1.getCurrentProcess().getMemoryAddressRegister()) : "No Disponible");
    }
    
    public void actualizarCPU2() {
        cpu2nombre.setText(cpu2.getCurrentProcess() != null ? cpu2.getCurrentProcess().getName() : "Proceso");
        cpu2id.setText(cpu2.getCurrentProcess() != null ? String.valueOf(cpu2.getCurrentProcess().getProcessID()) : "No Disponible" );
        cpu2estado.setText(cpu2.getCurrentProcess() != null ? String.valueOf(cpu2.getCurrentProcess().getState()) : "No Disponible");
        cpu2pc.setText(cpu2.getCurrentProcess() != null ? String.valueOf(cpu2.getCurrentProcess().getProgramCounter()) : "No Disponible");
        cpu2mar.setText(cpu2.getCurrentProcess() != null ? String.valueOf(cpu2.getCurrentProcess().getMemoryAddressRegister()) : "No Disponible");
    }   
    
    public void actualizarCPU3() {
        cpu3nombre.setText(cpu3.getCurrentProcess() != null ? cpu3.getCurrentProcess().getName() : "Proceso");
        cpu3id.setText(cpu3.getCurrentProcess() != null ? String.valueOf(cpu3.getCurrentProcess().getProcessID()) : "No Disponible" );
        cpu3estado.setText(cpu3.getCurrentProcess() != null ? String.valueOf(cpu3.getCurrentProcess().getState()) : "No Disponible");
        cpu3pc.setText(cpu3.getCurrentProcess() != null ? String.valueOf(cpu3.getCurrentProcess().getProgramCounter()) : "No Disponible");
        cpu3mar.setText(cpu3.getCurrentProcess() != null ? String.valueOf(cpu3.getCurrentProcess().getMemoryAddressRegister()) : "No Disponible");
    }   
    
    // Cuando se libera un proceso y la CPU esta libre, se debe actualizar la interfaz
    public void liberarCPU1() {
        cpu1nombre.setText("Sin proceso");
        cpu1id.setText("-");
        cpu1estado.setText("-");
        cpu1pc.setText("-");
        cpu1mar.setText("-");
    }
    
        public void liberarCPU2() {
        cpu2nombre.setText("Sin proceso");
        cpu2id.setText("-");
        cpu2estado.setText("-");
        cpu2pc.setText("-");
        cpu2mar.setText("-");
    }
        
        public void liberarCPU3() {
        if (cpu3 != null) {
        cpu3nombre.setText("Sin proceso");
        cpu3id.setText("-");
        cpu3estado.setText("-");
        cpu3pc.setText("-");
        cpu3mar.setText("-");
    }
}

    
    // cuando se termina un proceso, en la interfaz de esa CPU se debe mostrar como que ningun proceso se esta ejecutando en ese momento
    
//    private void configureCPUPanel(JPanel panel, String title) {
//        panel.setLayout(new GridLayout(6, 1)); // Organiza los labels en una columna
//        panel.removeAll(); // Elimina cualquier contenido anterior
//
//        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
//        JLabel processLabel = new JLabel(" Proceso en ejecución: -");
//        JLabel idLabel = new JLabel(" ID: -");
//        JLabel pcLabel = new JLabel(" PC: -");
//        JLabel marLabel = new JLabel(" MAR: -");
//        JLabel statusLabel = new JLabel(" Status: -");
//
//        panel.add(titleLabel);
//        panel.add(processLabel);
//        panel.add(idLabel);
//        panel.add(pcLabel);
//        panel.add(marLabel);
//        panel.add(statusLabel);
//
//        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        panel.revalidate();
//        panel.repaint();
//    }
    
        // Crea los paneles de los CPUs
//    private void createCPUDisplays() {
//    configureCPUPanel(jpanelcpu1, "CPU 1");
//    configureCPUPanel(jpanelcpu2, "CPU 2");
//    configureCPUPanel(jpanelcpu3, "CPU 3");
//    
//    jpanelcpu3.setVisible(cpu3Active); // Mostrar u ocultar CPU 3 según la configuración
//    }
    
    /**
     * Método para actualizar la interfaz de los CPUs
     */
    private void updateCPUsDisplay() {
    jpanelcpu1.setVisible(true);
    jpanelcpu2.setVisible(true);
    jpanelcpu3.setVisible(cpu3 != null); // Ocultar si cpu3 no existe
}

    
    
    ////////
    ////////PROCESOS
    ///////  
    private void crearProceso() {
    // Obtener valores de los campos
    String nombre = nombreProceso.getText().trim();
    int instrucciones = (int) totalInstrucciones.getValue();
    String tipo = (String) tipoProceso.getSelectedItem();
    int cicloExcep = (int) cicloExcepcion.getValue();
    int duracionExcep = (int) duracionExcepcion.getValue();
    String prioridadStr = (String) prioridadProceso.getSelectedItem();

    // Validar que el nombre no esté vacío y que las instrucciones sean mayores a 0
    if (nombre.isEmpty() || instrucciones <= 0 || instrucciones > 200) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un nombre y una cantidad válida de instrucciones.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Validar que si es I/O bound, tenga interrupciones o que sean validas
    if (tipo.equals("I/O-bound")) {
    if (cicloExcep == 0 || duracionExcep == 0 || cicloExcep>instrucciones || duracionExcep>instrucciones) {
        JOptionPane.showMessageDialog(this, "Debe ingresar ciclos de interrupciones válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }}

    // Convertir prioridad a Enum
    Process.Priority prioridad;
    switch (prioridadStr) {
        case "ALTA":
            prioridad = Process.Priority.ALTA;
            break;
        case "MEDIA":
            prioridad = Process.Priority.MEDIA;
            break;
        default:
            prioridad = Process.Priority.BAJA;
    }

    // Determinar si es CPU-bound o I/O-bound
    boolean isCPUBound = tipo.equals("CPU-bound");

    // Si es CPU-bound, la excepción siempre será 0
    if (isCPUBound) {
    cicloExcep = 0;
    duracionExcep = 0;
}
    int baseMemoryAddress = Scheduler.getNextMemoryAddress();

    // Crear un nuevo proceso
    Process nuevoProceso = new Process(
        Scheduler.getNextProcessID(), // Generar un ID único
        nombre,
        instrucciones,
        isCPUBound,
        cicloExcep,
        duracionExcep,
        prioridad,
        baseMemoryAddress
    );

    // Agregar el proceso a la cola de listos en Scheduler
    scheduler.addProcess(nuevoProceso);

    // Mostrar mensaje de éxito
    JOptionPane.showMessageDialog(this, "Proceso creado y agregado a la cola de listos.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

    // Limpiar los campos
    nombreProceso.setText("");
    totalInstrucciones.setValue(0);
    cicloExcepcion.setValue(0);
    duracionExcepcion.setValue(0);

    // Actualizar la interfaz
    actualizarColaListos();
}
    
    public void actualizarColaListos() {
    readyqueue.setText(scheduler.getReadyQueue().getAllProcesses());
}
    public void actualizarColaBloqueados() {
    blockedqueue1.setText(exceptionHandler.hasBlockedProcesses() ? exceptionHandler.getBlockedQueue().getBlockedProcesses() : "No hay procesos bloqueados");
}

public void actualizarColaTerminados() {
    exitqueue.setText(!scheduler.getTerminatedQueue().isEmpty() ? scheduler.getTerminatedQueue().getTerminatedProcesses() : "No hay procesos terminados");
}

    // metodo para manejar la habilitación/deshabilitación de los JSpinner
    private void actualizarEstadoExcepcion() {
    String tipo = (String) tipoProceso.getSelectedItem();
    
    boolean esIOBound = tipo.equals("I/O-bound");
    
    cicloExcepcion.setEnabled(esIOBound);
    duracionExcepcion.setEnabled(esIOBound);

    if (!esIOBound) {
        cicloExcepcion.setValue(0);
        duracionExcepcion.setValue(0);
    }
}


    /**
     * Método para detener el hilo antes de cerrar la ventana
     */
    @Override
    public void dispose() {
        running = false; // Detener el hilo de actualización
        super.dispose();
    }
    
    public void stopSimulation() {
    running = false; // Stop UI update loop
    cpu1.stopCPU();
    cpu2.stopCPU();
    cpu3.stopCPU();
}

    private void cpusActionPerformed(ActionEvent evt) {
    // Código que debe ejecutarse cuando se llama a cpusActionPerformed
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
        jpanelcpu1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cpu1mar = new javax.swing.JLabel();
        cpu1id = new javax.swing.JLabel();
        cpu1estado = new javax.swing.JLabel();
        cpu1pc = new javax.swing.JLabel();
        cpu1nombre = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        crear = new javax.swing.JButton();
        nombreProceso = new javax.swing.JTextField();
        totalInstrucciones = new javax.swing.JSpinner();
        tipoProceso = new javax.swing.JComboBox<>();
        cicloExcepcion = new javax.swing.JSpinner();
        duracionExcepcion = new javax.swing.JSpinner();
        prioridadProceso = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        estadisticas = new javax.swing.JButton();
        graficos = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        cargar = new javax.swing.JButton();
        jpanelcpu2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cpu2mar = new javax.swing.JLabel();
        cpu2id = new javax.swing.JLabel();
        cpu2estado = new javax.swing.JLabel();
        cpu2pc = new javax.swing.JLabel();
        cpu2nombre = new javax.swing.JLabel();
        jpanelcpu3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cpu3mar = new javax.swing.JLabel();
        cpu3nombre = new javax.swing.JLabel();
        cpu3id = new javax.swing.JLabel();
        cpu3estado = new javax.swing.JLabel();
        cpu3pc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("SIMULACIÓN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jLabel2.setText("COLA DE TERMINADOS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        exitqueue.setEditable(false);
        exitqueue.setColumns(20);
        exitqueue.setRows(5);
        exitqueue.setFocusable(false);
        jScrollPane1.setViewportView(exitqueue);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 590, 170));

        jLabel3.setText("POLÍTICA DE PLANIFICACION ACTUAL: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, -1, 20));

        readyqueue.setEditable(false);
        readyqueue.setColumns(20);
        readyqueue.setRows(5);
        readyqueue.setFocusable(false);
        jScrollPane2.setViewportView(readyqueue);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 590, 170));

        jLabel4.setText("COLA DE LISTOS");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        politicaPlanificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        politicaPlanificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                politicaPlanificacionActionPerformed(evt);
            }
        });
        jPanel1.add(politicaPlanificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 60, 180, -1));

        clockLabel.setText("CICLO DE RELOJ GLOBAL: 0");
        jPanel1.add(clockLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, -1, -1));
        jPanel1.add(tickDurationSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, -1, -1));

        jLabel5.setText("(duración en ms)");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, -1, -1));

        jLabel6.setText("COLA DE BLOQUEADOS");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        blockedqueue1.setEditable(false);
        blockedqueue1.setColumns(20);
        blockedqueue1.setRows(5);
        blockedqueue1.setFocusable(false);
        jScrollPane3.setViewportView(blockedqueue1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 590, 170));

        jpanelcpu1.setBorder(javax.swing.BorderFactory.createTitledBorder("CPU 1"));
        jpanelcpu1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel20.setText("Nombre del Proceso:");
        jpanelcpu1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel22.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel22.setText("ID:");
        jpanelcpu1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel24.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel24.setText("Estado:");
        jpanelcpu1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel26.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel26.setText("Estado PC:");
        jpanelcpu1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel28.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel28.setText("Estado MAR:");
        jpanelcpu1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        cpu1mar.setText("Estado MAR");
        jpanelcpu1.add(cpu1mar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        cpu1id.setText("Id");
        jpanelcpu1.add(cpu1id, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        cpu1estado.setText("Estado");
        jpanelcpu1.add(cpu1estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        cpu1pc.setText("Estado PC");
        jpanelcpu1.add(cpu1pc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        cpu1nombre.setText("Nombre");
        jpanelcpu1.add(cpu1nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        jPanel1.add(jpanelcpu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, 250, 130));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        crear.setText("CREAR");
        crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearActionPerformed(evt);
            }
        });
        jPanel2.add(crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 130, -1));

        nombreProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreProcesoActionPerformed(evt);
            }
        });
        jPanel2.add(nombreProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 100, -1));
        jPanel2.add(totalInstrucciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 60, -1));

        tipoProceso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tipoProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoProcesoActionPerformed(evt);
            }
        });
        jPanel2.add(tipoProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));
        jPanel2.add(cicloExcepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(288, 140, 70, -1));
        jPanel2.add(duracionExcepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 160, 70, -1));

        prioridadProceso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(prioridadProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, -1, -1));

        jLabel8.setText("Nombre del proceso:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel9.setText("Cantidad total de instrucciones:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel10.setText("Tipo de proceso:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel11.setText("Ciclos en el que ocurrirá una excepción:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel12.setText("Duración de la excepción:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel13.setText("Prioridad del proceso:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel7.setText("CREAR UN NUEVO PROCESO:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 330, 380, 270));

        guardar.setText("GUARDAR");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 570, -1, -1));

        estadisticas.setText("ESTADÍSTICAS");
        estadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadisticasActionPerformed(evt);
            }
        });
        jPanel1.add(estadisticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 610, -1, -1));

        graficos.setText("GRÁFICOS");
        graficos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graficosActionPerformed(evt);
            }
        });
        jPanel1.add(graficos, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 610, -1, -1));

        jLabel14.setText("(hacer guardar, graficos y stats)");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 610, -1, -1));

        cargar.setText("CARGAR");
        cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarActionPerformed(evt);
            }
        });
        jPanel1.add(cargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 530, -1, -1));

        jpanelcpu2.setBorder(javax.swing.BorderFactory.createTitledBorder("CPU 2"));
        jpanelcpu2.setToolTipText("");
        jpanelcpu2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel21.setText("Nombre del Proceso:");
        jpanelcpu2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel23.setText("ID:");
        jpanelcpu2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel25.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel25.setText("Estado:");
        jpanelcpu2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel27.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel27.setText("Estado PC:");
        jpanelcpu2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel29.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel29.setText("Estado MAR:");
        jpanelcpu2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        cpu2mar.setText("Estado MAR");
        jpanelcpu2.add(cpu2mar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        cpu2id.setText("Id");
        jpanelcpu2.add(cpu2id, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        cpu2estado.setText("Estado");
        jpanelcpu2.add(cpu2estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        cpu2pc.setText("Estado PC");
        jpanelcpu2.add(cpu2pc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        cpu2nombre.setText("Nombre");
        jpanelcpu2.add(cpu2nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        jPanel1.add(jpanelcpu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 190, 250, 130));

        jpanelcpu3.setBorder(javax.swing.BorderFactory.createTitledBorder("CPU 3"));
        jpanelcpu3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel15.setText("Nombre del Proceso:");
        jpanelcpu3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel16.setText("ID:");
        jpanelcpu3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel17.setText("Estado:");
        jpanelcpu3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel18.setText("Estado PC:");
        jpanelcpu3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel19.setText("Estado MAR:");
        jpanelcpu3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        cpu3mar.setText("Estado MAR");
        jpanelcpu3.add(cpu3mar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        cpu3nombre.setText("Nombre");
        jpanelcpu3.add(cpu3nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        cpu3id.setText("Id");
        jpanelcpu3.add(cpu3id, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        cpu3estado.setText("Estado");
        jpanelcpu3.add(cpu3estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        cpu3pc.setText("Estado PC");
        jpanelcpu3.add(cpu3pc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        jPanel1.add(jpanelcpu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 190, 250, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1484, Short.MAX_VALUE)
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

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar Procesos");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".txt")) {
            filePath += ".txt"; // Asegurar que el archivo tenga la extensión .txt
        }
        scheduler.guardarProcesosEnTXT(filePath);
        JOptionPane.showMessageDialog(this, "Procesos guardados correctamente en:\n" + filePath);
    }


    }//GEN-LAST:event_guardarActionPerformed

    private void estadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadisticasActionPerformed
        FuncionesInterfaz.openEstadisticas();
        this.setVisible(false);
    }//GEN-LAST:event_estadisticasActionPerformed

    private void graficosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graficosActionPerformed
        FuncionesInterfaz.openGraficos();
        this.setVisible(false);
    }//GEN-LAST:event_graficosActionPerformed

    private void nombreProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreProcesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreProcesoActionPerformed

    private void crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearActionPerformed
       for (ActionListener al : crear.getActionListeners()) {
    crear.removeActionListener(al);
}
        
        crear.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        crearProceso();
    }
    });

    }//GEN-LAST:event_crearActionPerformed

    private void tipoProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoProcesoActionPerformed
        tipoProceso.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        actualizarEstadoExcepcion();
    }
});

    }//GEN-LAST:event_tipoProcesoActionPerformed

    private void cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarActionPerformed
        CargarTXTfuncion.cargarDesdeArchivo(this);
        actualizarColaListos();
    }//GEN-LAST:event_cargarActionPerformed

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
            int defaultCPUs = 2; // Número de CPUs por defecto si no se especifica
            new Simulacion(defaultCPUs).setVisible(true);
    }
});

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea blockedqueue1;
    private javax.swing.JButton cargar;
    private javax.swing.JSpinner cicloExcepcion;
    private javax.swing.JLabel clockLabel;
    private javax.swing.JLabel cpu1estado;
    private javax.swing.JLabel cpu1id;
    private javax.swing.JLabel cpu1mar;
    private javax.swing.JLabel cpu1nombre;
    private javax.swing.JLabel cpu1pc;
    private javax.swing.JLabel cpu2estado;
    private javax.swing.JLabel cpu2id;
    private javax.swing.JLabel cpu2mar;
    private javax.swing.JLabel cpu2nombre;
    private javax.swing.JLabel cpu2pc;
    private javax.swing.JLabel cpu3estado;
    private javax.swing.JLabel cpu3id;
    private javax.swing.JLabel cpu3mar;
    private javax.swing.JLabel cpu3nombre;
    private javax.swing.JLabel cpu3pc;
    private javax.swing.JButton crear;
    private javax.swing.JSpinner duracionExcepcion;
    private javax.swing.JButton estadisticas;
    private javax.swing.JTextArea exitqueue;
    private javax.swing.JButton graficos;
    private javax.swing.JButton guardar;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpanelcpu1;
    private javax.swing.JPanel jpanelcpu2;
    private javax.swing.JPanel jpanelcpu3;
    private javax.swing.JTextField nombreProceso;
    private javax.swing.JComboBox<String> politicaPlanificacion;
    private javax.swing.JComboBox<String> prioridadProceso;
    private javax.swing.JTextArea readyqueue;
    private javax.swing.JSpinner tickDurationSpinner;
    private javax.swing.JComboBox<String> tipoProceso;
    private javax.swing.JSpinner totalInstrucciones;
    // End of variables declaration//GEN-END:variables
}
