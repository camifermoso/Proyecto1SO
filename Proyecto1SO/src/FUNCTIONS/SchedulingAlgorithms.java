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

    // Implementación corregida de Round Robin
    public static Process RoundRobin(Queue readyQueue, int quantum) {
        if (readyQueue.isEmpty()) return null;
        
        Process process = (Process) readyQueue.dequeue(); // Sacar el primer proceso de la cola
        
        if (process.getExecutedInstructions() < process.getTotalInstructions()) {
            int remainingInstructions = process.getTotalInstructions() - process.getExecutedInstructions();
            int executionTime = Math.min(remainingInstructions, quantum);
            
            // Ejecutar el proceso por el tiempo asignado (quantum o hasta que termine)
            for (int i = 0; i < executionTime; i++) {
                process.executeInstruction();
            }
            
            // Si el proceso aún no ha terminado, se vuelve a encolar al final de la cola de listos
            if (!process.isCompleted()) {
                readyQueue.enqueue(process);
            }
        }
        
        return process; // Retorna el proceso que se ejecutó
    }

    // Shortest Process Next (SPN)
public static Process SPN(Queue readyQueue) {
    if (readyQueue.isEmpty()) return null;

    LinkedList tempList = new LinkedList();
    Process shortest = null;
    Nodo shortestNode = null;
    Nodo prev = null;
    Nodo prevShortest = null;

    // Mover los procesos de la cola a una lista temporal
    while (!readyQueue.isEmpty()) {
        Nodo newNode = new Nodo(readyQueue.dequeue());
        tempList.addLast(newNode.getElement());
    }

    // Buscar el proceso con menor número de instrucciones
    Nodo current = tempList.getHead();
    while (current != null) {
        Process p = (Process) current.getElement();
        if (shortest == null || p.getTotalInstructions() < shortest.getTotalInstructions()) {
            shortest = p;
            prevShortest = prev;
            shortestNode = current;
        }
        prev = current;
        current = current.getNext();
    }

    // Eliminar el nodo más corto de la lista temporal
    if (shortestNode != null) {
        if (prevShortest == null) {
            tempList.setHead(shortestNode.getNext()); // Si era el primero
        } else {
            prevShortest.setNext(shortestNode.getNext()); // Saltar el nodo más corto
        }
    }

    // Regresar los procesos restantes a la cola de listos
    current = tempList.getHead();
    while (current != null) {
        readyQueue.enqueue(current.getElement());
        current = current.getNext();
    }

    return shortest;
}


    public static Process SRT(Queue readyQueue, Process currentProcess) {
    if (readyQueue.isEmpty()) return currentProcess; // Si la cola está vacía, mantiene el proceso actual

    Process shortestRemaining = null;
    Nodo shortestNode = null;
    Nodo prev = null;
    Nodo prevShortest = null;
    Nodo current = readyQueue.getHead();
    
    // Encontrar el proceso con menor tiempo restante
    while (current != null) {
        Process p = (Process) current.getElement();
        int remainingTime = p.getTotalInstructions() - p.getExecutedInstructions();

        if (shortestRemaining == null || 
            remainingTime < (shortestRemaining.getTotalInstructions() - shortestRemaining.getExecutedInstructions())) {
            shortestRemaining = p;
            prevShortest = prev;
            shortestNode = current;
        }

        prev = current;
        current = current.getNext();
    }

    // Verificar si el proceso actual sigue siendo el más corto
    if (currentProcess != null &&
        (currentProcess.getTotalInstructions() - currentProcess.getExecutedInstructions()) <= 
        (shortestRemaining.getTotalInstructions() - shortestRemaining.getExecutedInstructions())) {
        return currentProcess;  // Mantiene el proceso actual si aún tiene menor tiempo restante
    }

    // Eliminar el proceso seleccionado de la cola de listos
    if (shortestNode != null) {
        if (prevShortest == null) {
            readyQueue.setHead(shortestNode.getNext());
        } else {
            prevShortest.setNext(shortestNode.getNext());
        }
    }

    return shortestRemaining; // Devuelve el nuevo proceso seleccionado
}



    // Highest Response Ratio Next (HRRN)
    public static Process HRRN(Queue readyQueue, int currentTime) {
    if (readyQueue.isEmpty()) return null;

    Nodo highestRRNode = null;
    Process highestRRProcess = null;
    double maxResponseRatio = -1;

    // Recorremos la cola de listos
    Nodo current = readyQueue.getHead();
    while (current != null) {
        Process p = (Process) current.getElement();
        
        int waitTime = currentTime - p.getArrivalTime();
        int executionTime = p.getTotalInstructions();
        
        double responseRatio = (double) (waitTime + executionTime) / executionTime;
        
        if (responseRatio > maxResponseRatio) {
            maxResponseRatio = responseRatio;
            highestRRProcess = p;
            highestRRNode = current;
        }
        
        current = current.getNext();
    }

    // Eliminamos el proceso de la cola de listos
    if (highestRRNode != null) {
        readyQueue.remove(highestRRProcess);
    }

    return highestRRProcess;
}



}
