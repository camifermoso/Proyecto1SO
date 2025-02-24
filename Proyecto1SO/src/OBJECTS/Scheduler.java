/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

/**
 *
 * @author cristiandresgp
 */
import FUNCTIONS.SchedulingAlgorithms;
import EDD.Queue;
import static GUI.Simulacion.clock;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Scheduler {
    private Queue listaProcesos; // Estructura auxiliar para guardar los procesos admitidos
    private final Queue readyQueue;
    private final Queue terminatedQueue;
    private String algorithm;
    private int quantum;
    private static int processCounter = 1; // Contador global de procesos
    private static int nextAvailableMemoryAddress = 0;  // Comienza en 0 y se incrementa

    private final Semaphore schedulerSemaphore = new Semaphore(1, true);

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public static int getProcessCounter() {
        return processCounter;
    }

    public static void setProcessCounter(int processCounter) {
        Scheduler.processCounter = processCounter;
    }

    public static int getNextAvailableMemoryAddress() {
        return nextAvailableMemoryAddress;
    }

    public static void setNextAvailableMemoryAddress(int nextAvailableMemoryAddress) {
        Scheduler.nextAvailableMemoryAddress = nextAvailableMemoryAddress;
    }
    
    public Scheduler(String algorithm, int quantum) {
        this.listaProcesos = new Queue();
        this.readyQueue = new Queue(); // Cola única de listos
        this.terminatedQueue = new Queue(); // Cola de terminados
        this.algorithm = algorithm;
        this.quantum = quantum;
    }

    public void addProcess(Process p, int currentTime) {
    if (p.getArrivalTime() == 0) {
        p.setArrivalTime(currentTime);
    }
        listaProcesos.enqueue(p); // Guardar en la estructura auxiliar
        readyQueue.enqueue(p);
        System.out.println("Proceso agregado: " + p.getName());
        System.out.println(" Estado de la cola de listos antes de obtener un proceso: " + readyQueue.toString());

    }



public Process getNextProcess(Process currentProcess, int currentTime) {
    try {
        schedulerSemaphore.acquire(); // 🔒 Bloqueo del semáforo para acceso seguro

        if (readyQueue.isEmpty()) {
            System.out.println("[INFO] No hay procesos en la cola de listos.");
            return null;
        }

        Process nextProcess = null;
        switch (algorithm) {
            case "FCFS":
                nextProcess = SchedulingAlgorithms.FCFS(readyQueue);
                break;
            case "RoundRobin":
                nextProcess = SchedulingAlgorithms.RoundRobin(readyQueue, quantum);
                break;
            case "SJF":
                nextProcess = SchedulingAlgorithms.SPN(readyQueue);
                break;
            case "SRT":
                nextProcess = SchedulingAlgorithms.SRT(readyQueue, currentProcess);
                break;
            case "HRRN":
                nextProcess = SchedulingAlgorithms.HRRN(readyQueue, currentTime);
                break;
        }

        if (nextProcess != null) {
            System.out.println("[DEBUG] Se seleccionó el proceso: " + nextProcess.getName());
        } else {
            System.out.println("[INFO] No se pudo seleccionar un proceso de la cola de listos.");
        }

        return nextProcess;
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return null;
    } finally {
        schedulerSemaphore.release(); // 🔓 Liberación del semáforo
    }
}







    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }
    
    public Queue getReadyQueue() {
        return readyQueue;
    }
    
    public Queue getTerminatedQueue() {
        return terminatedQueue;
    }
    

    public static int getNextProcessID() {
    return processCounter++;
}

    public static int getNextMemoryAddress() {
        
    int address = nextAvailableMemoryAddress;
    nextAvailableMemoryAddress += 200;  // Asignamos un bloque de 200 unidades a cada proceso
    return address;
}
    
    public void terminateProcess(Process process) {
    process.setState(Process.ProcessState.TERMINATED);
    terminatedQueue.enqueue(process);
}


public void guardarProcesosEnTXT(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("NOMBRE,INSTRUCCIONES,CPU_BOUND,CICLO_EXCEP,DURACION_EXCEP,PRIORIDAD\n");

            Queue tempQueue = new Queue();
            while (!listaProcesos.isEmpty()) {
                Process process = (Process) listaProcesos.dequeue();
                writer.write(
                    process.getName() + "," +
                    process.getTotalInstructions() + "," +
                    process.isCPUBound() + "," +
                    process.getExceptionCycle() + "," +
                    process.getExceptionDuration() + "," +
                    process.getPriority() + "\n"
                );
                tempQueue.enqueue(process); // Mantener los procesos en la estructura
            }
            listaProcesos = tempQueue;

            System.out.println("[INFO] Procesos guardados en " + filePath);
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo guardar el archivo: " + e.getMessage());
        }
    }


}
