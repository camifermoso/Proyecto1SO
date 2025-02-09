/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

import GUI.main;
import java.util.concurrent.Semaphore;

public class CPU extends Thread {
    private final int cpuId;  // ID de CPU
    private Process currentProcess; // Proceso
    private boolean busy; // Estado de CPU
    private Semaphore cpuSemaphore = new Semaphore(1); // Semaforo de exclusion mutua
    private volatile boolean running; // Al iniciar la CPU siempre esta running
    private Process process;

    public CPU(int cpuId) {
        this.cpuId = cpuId;
        this.currentProcess = null; // No se asigna ningun proceso inicialmente
        this.busy = true; // Al inicio esta activo
        this.running = true;
        this.cpuSemaphore = cpuSemaphore;
    }
    
    // Cuando se asigna un proceso distinto es necesario que se vean los cambios en la interfaz grafica
    private void assignProcessInterfaceUpdate(Process process) {
        // x debe actualizar lo que sale en la interfaz con updateCPUDisplay();
        if (cpuId == 1) {
            //x
        } else if (cpuId == 2) {
            //x
        } else {
            //x
        }
    }

    
    //Run
    @Override
    public void run() {
        while (busy) {
            try {
                cpuSemaphore.acquire();
                // Es necesario que busque en la cola de procesos listos el proceso a ejecutar
                if (!main.readyQueue.isEmpty()) {
                    // El proceso no puede estar ejecutandose en otro CPU
                    process = main.readyQueue.searchNotExecuting();
                    if (process != null) {
                        process.setExecuting(true);
                        System.out.println("CPU: " + cpuId + "esta ejecutando el proceso " + process.getName());
                    }
                } else {
                    System.out.println("CPU " + cpuId + ": La cola de procesos esta vacia");
                }
                cpuSemaphore.release();

                if (process != null) {
                    runOS();
                    ejecutarProceso();
                } else {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("CPU " + cpuId + " fue interrumpida");
                Thread.currentThread().interrupt();
            }
        }
    }
    
    //Run Operative System
    
    //Le faltan varios cambios
    
   
//    private void runOS() throws InterruptedException {
//        Process processOS = new Process("SO", 3, "CPU bound", 0, 0);
//        processOS.setprocessID(0);
//        processOS.setState("RUNNING");
//        assignProcessInterfaceUpdate(processOS);
//
//        System.out.println("CPU " + cpuId + " ejecutando el SO por 3 ciclos...");
//        for (int i = 0; i < 3; i++) {
//            int duracionCiclo = main.mainWindow.getCicloDuracion();
//            Thread.sleep(duracionCiclo * 1000L);
//            processOS.setProgramCounter(processOS.getProgramCounter() + 1);
//            processOS.setMemoryAddressRegister(processOS.getMemoryAddressRegister() + 1);
//            assignProcessInterfaceUpdate(processOS);
//        }
//        terminateProcessInterfaceUpdate();
//    }
    
    // Esto le falta las politicas de planificacion
//    private void ejecutarProceso() throws InterruptedException {
//        process.setState("RUNNING");
//        main.readyQueue.remove(process);
//        process.setExecuting(false);
//        setProcess(process);
//
//        int duracionCiclo = main.mainWindow.getCicloDuracion();
//        switch (MainClass.politicaActual) {
//            case "Round Robin":
//                runRoundRobin(duracionCiclo);
//                break;
//            case "SRT":
//                runSRT(duracionCiclo);
//                break;
//            default:
//                runDefault(duracionCiclo);
//                break;
//        }
//    }
    
    
    //este execute cycle le faltan unos cambios
//    private void executeCycle() {
//        if (currentProcess == null || currentProcess.getState() == Process.ProcessState.BLOCKED) {
//            return;
//        }
//
//        currentProcess.executeInstruction();
//
//        if (currentProcess.isCompleted()) {
//            terminateProcessInterfaceUpdate();
//        }
//    }
    
    // Cuando se termina un proceso es necesario que se vean los cambios en la interfaz grafica
    private void terminateProcessInterfaceUpdate() {
        // cuando se termina un proceso, en la interfaz de esa CPU se debe mostrar como que ningun proceso se esta ejecutando en ese momento
        if (cpuId == 1) {
            //x
        } else if (cpuId == 2) {
            //x
        } else {
            //x
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
               ", proceso=" + (process != null ? process.getName() : "Ningun proceso ha sido asignado") +
               ", activo=" + busy +
               '}';
    }
}
