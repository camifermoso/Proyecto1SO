/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

import GUI.main;
import GUI.Simulacion;
import static OBJECTS.Process.ProcessState.RUNNING;
import java.util.concurrent.Semaphore;

public class CPU extends Thread {
    private final int cpuId;  // ID de CPU
    private Process currentProcess; // Proceso
    private boolean busy; // Estado de CPU
    private final Semaphore cpuSemaphore; // Semaforo de exclusion mutua
    private volatile boolean running; // Al iniciar la CPU siempre esta running, este es el estado de ejecucion de la CPU
    private final Scheduler scheduler;
    private Clock clock;
    private Simulacion gui;
    
    public CPU(int cpuId, Scheduler scheduler, Clock clock, Simulacion gui) {
        this.cpuId = cpuId;
        this.currentProcess = null; // No se asigna ningun proceso inicialmente
        this.busy = true; // Al inicio esta activo
        this.running = true;
        this.cpuSemaphore = new Semaphore(1); //Inicializacion del semaforo
        this.scheduler = scheduler;
        this.gui = gui;
    }
    
    // Cuando se asigna un proceso distinto es necesario que se vean los cambios en la interfaz grafica
    private void assignProcessInterfaceUpdate(Process process) {
        // x debe actualizar lo que sale en la interfaz con updateCPUDisplay();
        if (cpuId == 1) {
           gui.actualizarCPU1();
        } else if (cpuId == 2) {
            gui.actualizarCPU2();
        } else {
            gui.actualizarCPU3();
        }
    }
    
    // Cuando se termina un proceso es necesario que se vean los cambios en la interfaz grafica
    private void terminateProcessInterfaceUpdate() {
        // cuando se termina un proceso, en la interfaz de esa CPU se debe mostrar como que ningun proceso se esta ejecutando en ese momento
        if (cpuId == 1) {
            gui.liberarCPU1();
        } else if (cpuId == 2) {
            gui.liberarCPU2();
        } else {
            gui.liberarCPU3();
        }
      currentProcess = null;
    }
        
    //Ejecuta la logica de la CPU
    @Override
    public void run() {
        //CPU se esta ejecutando`
        while (running) {
            try {
                // Se adquiere el semáforo antes de acceder a la cola de listos
                cpuSemaphore.acquire();
                try {
                    // Verifica si hay procesos en la cola de listos
                    if (!scheduler.getReadyQueue().isEmpty()) {
                        Process process = scheduler.getNextProcess(currentProcess, GUI.Simulacion.clock.getCurrentCycle());
                        if (process != null && !process.isExecuting()) {
                            process.setExecuting(true); // Evita que otros CPUs ejecuten el mismo proceso
                            currentProcess = process;
                            assignProcessInterfaceUpdate(process);
                            gui.actualizarColaListos();
                            System.out.println("CPU " + cpuId + " está ejecutando el proceso " + process.getName());
                        }
                    } else {
                        System.out.println("CPU " + cpuId + ": La cola de procesos está vacía");
                    }
                } finally {
                    cpuSemaphore.release(); // Libera el semáforo en cualquier caso
                }

                // Ejecuta el proceso si hay uno asignado
                if (currentProcess != null) {
                    runProcess();
                } else {
                    try {
                        Thread.sleep(1000); // Espera si no hay procesos asignados
                    } catch (InterruptedException e) {
                        System.out.println("CPU " + cpuId + " fue interrumpida durante el sueño");
                        Thread.currentThread().interrupt();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("CPU " + cpuId + " fue interrumpida al intentar adquirir el semáforo");
                Thread.currentThread().interrupt();
            }
        }
    }
    
    // Ejecuta el proceso actual
    private void runProcess() {
        try {
            currentProcess.setState(RUNNING);
            assignProcessInterfaceUpdate(currentProcess);

            int cycleDuration = GUI.Simulacion.clock.getCurrentCycle();
            Thread.sleep(cycleDuration * 1000L);  // Simula la ejecución de instrucciones en un ciclo

            currentProcess.setProgramCounter(currentProcess.getProgramCounter() + 1);
            currentProcess.setMemoryAddressRegister(currentProcess.getMemoryAddressRegister() + 1);
            assignProcessInterfaceUpdate(currentProcess);

            // Verifica si el proceso ha terminado
            if (currentProcess.isCompleted()) {
                scheduler.getTerminatedQueue().enqueue(currentProcess);
                terminateProcessInterfaceUpdate();
                currentProcess = null;
            }
        } catch (InterruptedException e) {
            System.out.println("CPU " + cpuId + " fue interrumpida mientras ejecutaba el proceso");
            Thread.currentThread().interrupt();
        }
    }  

    public void stopCPU() {
        running = false;
        interrupt(); // Ensures the thread exits if it's sleeping
    }
    
    
    // Ver si la CPU esta activa
    public boolean isBusy() {
        return busy;
    }
    
    public void setBusy(boolean busy) {
        this.busy = busy;
    }
    
  // Getters and Setters
   
    public Process getCurrentProcess() {
        return currentProcess;
    }

    public int getCpuId() {
        return cpuId;
    }
    
    //String 
    @Override
    public String toString() {
        return "CPU{" +
               "id=" + cpuId +
               ", proceso=" + (currentProcess != null ? currentProcess.getName() : "Ningun proceso ha sido asignado") +
               ", activo=" + busy +
               '}';
    }
}
