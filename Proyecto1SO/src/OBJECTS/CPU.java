package OBJECTS;

import GUI.Simulacion;
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
    
    public CPU(int cpuId, Scheduler scheduler, Clock clock, Simulacion gui) {
        this.cpuId = cpuId;
        this.currentProcess = null; 
        this.busy = true;
        this.running = true;
        this.cpuSemaphore = new Semaphore(1);
        this.scheduler = scheduler;
        this.clock = clock;
        this.gui = gui;
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
    
@Override
public void run() {
    while (running) {
        try {
            cpuSemaphore.acquire();
            try {
                if (currentProcess == null && !scheduler.getReadyQueue().isEmpty()) {
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

        while (!currentProcess.isCompleted()) {
            synchronized (clock) { 
                clock.wait(); // 🔹 Esperar exactamente un tick antes de ejecutar la instrucción
            }

            currentProcess.executeInstruction(); // 🔹 Ejecutar una instrucción por tick

            System.out.println("[DEBUG] CPU " + cpuId + " ejecutando " + currentProcess.getName() + 
                " | PC: " + currentProcess.getProgramCounter() + 
                " | MAR: " + currentProcess.getMemoryAddressRegister());

            assignProcessInterfaceUpdate(currentProcess);
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
