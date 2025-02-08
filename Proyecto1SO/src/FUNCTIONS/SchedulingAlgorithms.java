/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FUNCTIONS;

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

    /**
     * Implementación del algoritmo FCFS (First Come First Served).
     * Los procesos se atienden en el orden en que llegan a la cola.
     */
    public static Process FCFS(Queue readyQueue) {
        if (readyQueue.isEmpty()) return null;
        return (Process) readyQueue.dequeue();
    }
    
    /**
     * Métodos vacíos para evitar errores en el Scheduler.
     */
    public static Process RoundRobin(Queue readyQueue, int quantum) {
        return null;
    }
    
    public static Process SPN(Queue readyQueue) {
        return null;
    }
    
    public static Process SRT(Queue readyQueue, Process currentProcess) {
        return null;
    }
    
    public static Process HRRN(Queue readyQueue, int currentTime) {
        return null;
    }
}
