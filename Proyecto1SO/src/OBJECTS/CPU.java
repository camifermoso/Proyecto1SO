package OBJECTS;

import GUI.Simulacion;
import static OBJECTS.Process.Priority.ALTA;
import static OBJECTS.Process.ProcessState.RUNNING;
import java.util.concurrent.Semaphore;

public class CPU extends Thread {
    private final int cpuId;  // ID de CPU
    private Process currentProcess; // Proceso actual en ejecución
    private boolean busy; // Indica si la CPU está ocupada
    private final Semaphore cpuSemaphore; // Semáforo para control de concurrencia
    private volatile boolean running; // Indica si la CPU sigue en ejecución
    private final Scheduler scheduler;
    private Clock clock;
    private Simulacion gui;
    private ExceptionHandler exceptionhandler;
    
    public CPU(int cpuId, Scheduler scheduler, Clock clock, Simulacion gui, ExceptionHandler exceptionhandler) {
        this.cpuId = cpuId;
        this.currentProcess = null; 
        this.busy = true;
        this.running = true;
        this.cpuSemaphore = new Semaphore(1);
        this.scheduler = scheduler;
        this.clock = clock;
        this.gui = gui;
        this.exceptionhandler = exceptionhandler;
    }
    
    private void assignProcessInterfaceUpdate(Process process) {
        if (cpuId == 1) {
           gui.actualizarCPU1();
        } else if (cpuId == 2) {
            gui.actualizarCPU2();
        } else {
            gui.actualizarCPU3();
        }
    }
    
    private void terminateProcessInterfaceUpdate() {
        if (cpuId == 1) {
            gui.liberarCPU1();
        } else if (cpuId == 2) {
            gui.liberarCPU2();
        } else {
            gui.liberarCPU3();
        }
        currentProcess = null;
    }
    
    private void runOS(int cycles) {
        Process sistemaOp = new Process(0, "Sistema Operativo", 5, true, 0, 0, ALTA, 0);

        System.out.println("[DEBUG] CPU " + cpuId + " ejecutando Sistema Operativo por " + cycles + " ciclos.");

        if (cpuId == 1) {
            gui.soCPU1();
        } else if (cpuId == 2) {
            gui.soCPU2();
        } else {
            gui.soCPU3();
        }
        
        
        for (int i = 0; i < cycles; i++) {
            synchronized (clock) {
                try {
                    clock.wait(); // Espera un ciclo de reloj antes de continuar
                } catch (InterruptedException e) {
                    System.out.println("[ERROR] CPU " + cpuId + " interrumpida durante la ejecución del SO");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        System.out.println("[DEBUG] CPU " + cpuId + " terminó la ejecución del Sistema Operativo.");
        
        if (cpuId == 1) {
            gui.hideSO1();
        } else if (cpuId == 2) {
            gui.hideSO2();
        } else {
            gui.hideSO3();
        }
    }
    
@Override
public void run() {
    while (running) {
        exceptionhandler.checkBlockedProcesses(clock.getCurrentCycle());
        try {
            cpuSemaphore.acquire();
            try {

                if (currentProcess == null && !scheduler.getReadyQueue().isEmpty()) {
                    runOS(5); // Ejecutar SO por 5 ciclos antes de asignar un proceso nuevo
                    
                    Process process = scheduler.getNextProcess(currentProcess, clock.getCurrentCycle());
                    if (process != null && !process.isExecuting()) {
                        process.setExecuting(true);
                        currentProcess = process;
                        assignProcessInterfaceUpdate(process);
                        gui.actualizarColaListos();
                        System.out.println("[DEBUG] CPU " + cpuId + " asignó: " + process.getName());
                    }
                } else if (currentProcess != null) {
                    runProcess(); // Solo ejecutar si hay un proceso asignado
                }
                
            } finally {
                cpuSemaphore.release();
            }

        } catch (InterruptedException e) {
            System.out.println("[ERROR] CPU " + cpuId + " fue interrumpida");
            Thread.currentThread().interrupt();
        }
    }
}


    
    private void runProcess() {
    try {
        currentProcess.setState(RUNNING);
        assignProcessInterfaceUpdate(currentProcess);

        while (!currentProcess.isCompleted() || currentProcess==null) {
            synchronized (clock) { 
                clock.wait(); // Esperar exactamente un tick antes de ejecutar la instruccion
            }
            
            

            currentProcess.executeInstruction(); // Ejecutar una instruccion por tick

            System.out.println("[DEBUG] CPU " + cpuId + " ejecutando " + currentProcess.getName() + 
                " | PC: " + currentProcess.getProgramCounter() + 
                " | MAR: " + currentProcess.getMemoryAddressRegister());

            assignProcessInterfaceUpdate(currentProcess);
            
            if (!currentProcess.isCPUBound()){
                currentProcess.setCounter(currentProcess.getCounter()+1);
                
                if(currentProcess.isBlocked()){
                
                System.out.println("[DEBUG] Proceso bloqueado: " + currentProcess.getName());
                exceptionhandler.blockProcess(currentProcess);
                gui.actualizarColaBloqueados();
                terminateProcessInterfaceUpdate();
                currentProcess = null;
                
                
                // Liberar la CPU y asignar otro proceso
                    currentProcess = scheduler.getNextProcess(null, clock.getCurrentCycle());
                    if (currentProcess != null) {
                        assignProcessInterfaceUpdate(currentProcess);
                    }
                    return; // Salir del método para que la CPU vuelva a ejecutarse con el nuevo proceso
            
            }}
            
        }
        
        System.out.println("[DEBUG] Proceso terminado: " + currentProcess.getName());
        scheduler.terminateProcess(currentProcess);
        gui.actualizarColaTerminados();
        terminateProcessInterfaceUpdate();
        currentProcess = null;

    } catch (InterruptedException e) {
        System.out.println("[ERROR] CPU " + cpuId + " fue interrumpida mientras ejecutaba el proceso");
        Thread.currentThread().interrupt();
    }
}




    
    public void stopCPU() {
        running = false;
        interrupt();
    }
    
    public boolean isBusy() {
        return busy;
    }
    
    public void setBusy(boolean busy) {
        this.busy = busy;
    }
    
    public Process getCurrentProcess() {
        return currentProcess;
    }
    
    public int getCpuId() {
        return cpuId;
    }
    
    @Override
    public String toString() {
        return "CPU{" +
               "id=" + cpuId +
               ", proceso=" + (currentProcess != null ? currentProcess.getName() : "Ningun proceso ha sido asignado") +
               ", activo=" + busy +
               '}';
    }
}
