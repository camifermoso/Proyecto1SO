/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

import EDD.Queue;

/**
 *
 * @author cristiandresgp
 */
import EDD.Queue;

public class ExceptionHandler {
    private Queue blockedQueue;

    public ExceptionHandler() {
        this.blockedQueue = new Queue();
    }

    // Mueve un proceso a la cola de bloqueados
    public void blockProcess(Process p) {
        blockedQueue.enqueue(p);
    }

    // Libera un proceso de la cola de bloqueados
    public Process unblockProcess() {
        if (blockedQueue.isEmpty()) return null;
        return (Process) blockedQueue.dequeue();
    }

    public boolean hasBlockedProcesses() {
        return !blockedQueue.isEmpty();
    }
}


    

