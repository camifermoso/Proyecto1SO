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

public class Scheduler {
    private final Queue readyQueue;
    private final Queue terminatedQueue;
    private String algorithm;
    private int quantum;

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
        if (readyQueue.isEmpty()) return null;

        Process nextProcess;
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
            default:
                nextProcess = null;
        }
        
        // Si el proceso esta listo, moverlo a la cola de terminados
        if (nextProcess != null && nextProcess.isCompleted()) {
            nextProcess.setState(Process.ProcessState.TERMINATED);
            terminatedQueue.enqueue(nextProcess);
            return getNextProcess(null, currentTime); // Obtener otro si el actual está terminado
        }
        
        return nextProcess;
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
}
