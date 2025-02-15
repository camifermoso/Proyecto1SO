/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FUNCTIONS;

import EDD.LinkedList;
import EDD.Nodo;
import EDD.Queue;
import OBJECTS.Process;

/**
 *
 * @author cristiandresgp
 */
/**
 * Implementación de los algoritmos de planificación de procesos.
 */
public class SchedulingAlgorithms {
    private static int cpuCount = 2; // Default CPUs, updated before simulation starts

    public static void setCPUCount(int count) {
        cpuCount = count;
    }

    public static int getCPUCount() {
        return cpuCount;
    }

    // First-Come, First-Served (FCFS)
    public static Process FCFS(Queue readyQueue) {
        return (Process) readyQueue.dequeue();
    }

    // Round Robin
    public static Process RoundRobin(Queue readyQueue, int quantum) {
        if (readyQueue.isEmpty()) return null;
        Process process = (Process) readyQueue.dequeue();
        
        if (process.getExecutedInstructions() + quantum < process.getTotalInstructions()) {
            process.setProgramCounter(process.getProgramCounter() + quantum);
            readyQueue.enqueue(process); // Se reenvía a la cola
        }
        return process;
    }

    // Shortest Process Next (SPN)
    public static Process SPN(Queue readyQueue) {
        LinkedList tempList = new LinkedList();
        while (!readyQueue.isEmpty()) {
            tempList.addLast(readyQueue.dequeue());
        }
        
        Process shortest = null;
        Nodo current = tempList.getHead();
        while (current != null) {
            Process p = (Process) current.getElement();
            if (shortest == null || p.getTotalInstructions() < shortest.getTotalInstructions()) {
                shortest = p;
            }
            current = current.getNext();
        }
        
        tempList.removeFirst();
        while (!tempList.isEmpty()) {
            readyQueue.enqueue(tempList.removeFirst());
        }
        return shortest;
    }

    // Shortest Remaining Time (SRT)
    public static Process SRT(Queue readyQueue, Process currentProcess) {
        LinkedList tempList = new LinkedList();
        while (!readyQueue.isEmpty()) {
            tempList.addLast(readyQueue.dequeue());
        }
        
        Process shortestRemaining = null;
        Nodo current = tempList.getHead();
        while (current != null) {
            Process p = (Process) current.getElement();
            if (shortestRemaining == null || (p.getTotalInstructions() - p.getExecutedInstructions()) < (shortestRemaining.getTotalInstructions() - shortestRemaining.getExecutedInstructions())) {
                shortestRemaining = p;
            }
            current = current.getNext();
        }
        
        tempList.removeFirst();
        while (!tempList.isEmpty()) {
            readyQueue.enqueue(tempList.removeFirst());
        }
        return shortestRemaining;
    }

    // Highest Response Ratio Next (HRRN)
    public static Process HRRN(Queue readyQueue, int currentTime) {
        LinkedList tempList = new LinkedList();
        while (!readyQueue.isEmpty()) {
            tempList.addLast(readyQueue.dequeue());
        }
        
        Process highestRatioProcess = null;
        double highestRatio = -1;
        Nodo current = tempList.getHead();
        while (current != null) {
            Process p = (Process) current.getElement();
            double responseRatio = (currentTime - p.getProcessID() + p.getTotalInstructions()) / (double) p.getTotalInstructions();
            if (responseRatio > highestRatio) {
                highestRatio = responseRatio;
                highestRatioProcess = p;
            }
            current = current.getNext();
        }
        
        tempList.removeFirst();
        while (!tempList.isEmpty()) {
            readyQueue.enqueue(tempList.removeFirst());
        }
        return highestRatioProcess;
    }
}
