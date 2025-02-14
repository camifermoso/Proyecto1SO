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
import java.util.concurrent.Semaphore;

public class Scheduler {
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
        this.readyQueue = new Queue(); // Cola única de listos
        this.terminatedQueue = new Queue(); // Cola de terminados
        this.algorithm = algorithm;
        this.quantum = quantum;
    }

    public void addProcess(Process p) {
        readyQueue.enqueue(p);
    }


public Process getNextProcess(Process currentProcess, int currentTime) {
    try {
        schedulerSemaphore.acquire(); // 🔒 Bloqueo del semáforo para acceso seguro
        if (readyQueue.isEmpty()) return null;

        Process nextProcess = null;
        switch (algorithm) {
            case "FCFS":
                nextProcess = SchedulingAlgorithms.FCFS(readyQueue);
                break;
            case "RoundRobin":
                nextProcess = SchedulingAlgorithms.RoundRobin(readyQueue, quantum);
                break;
            case "SPN":
                nextProcess = SchedulingAlgorithms.SPN(readyQueue);
                break;
            case "SRT":
                nextProcess = SchedulingAlgorithms.SRT(readyQueue, currentProcess);
                break;
            case "HRRN":
                nextProcess = SchedulingAlgorithms.HRRN(readyQueue, currentTime);
                break;
        }

        if (nextProcess != null && nextProcess.isExecuting()) {
            return null; // No devolver un proceso ya en ejecución
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

public void moveProcessToBlocked(Process process) {
    process.setState(Process.ProcessState.BLOCKED);
    System.out.println("Proceso " + process.getName() + " movido a bloqueados.");
}



}
