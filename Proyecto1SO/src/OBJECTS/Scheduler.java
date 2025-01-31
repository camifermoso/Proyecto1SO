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
    private Queue readyQueue;
    private String algorithm;
    private int quantum;

    public Scheduler(String algorithm, int quantum) {
        this.readyQueue = new Queue(); // Cola única de listos
        this.algorithm = algorithm;
        this.quantum = quantum;
    }

    public void addProcess(Process p) {
        readyQueue.enqueue(p);
    }

    public Process getNextProcess(Process currentProcess, int currentTime) {
        if (readyQueue.isEmpty()) return null;

        switch (algorithm) {
            case "FCFS":
                return SchedulingAlgorithms.FCFS(readyQueue);
            case "RoundRobin":
                return SchedulingAlgorithms.RoundRobin(readyQueue, quantum);
            case "SPN":
                return SchedulingAlgorithms.SPN(readyQueue);
            case "SRT":
                return SchedulingAlgorithms.SRT(readyQueue, currentProcess);
            case "HRRN":
                return SchedulingAlgorithms.HRRN(readyQueue, currentTime);
            case "Feedback":
                Queue[] queues = {readyQueue}; // Si usamos múltiples colas, cambiar
                return SchedulingAlgorithms.Feedback(queues);
            default:
                return null;
        }
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}

// AGREGAR SchedulingAlgorithms EN FUNCTIONS (ALGORITMOS DE PLANIFICACION PERO HAY QUE SOLO ESCOGER 5 DE ESOS 6)

    
