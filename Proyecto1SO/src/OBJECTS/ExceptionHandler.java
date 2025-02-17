/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;
/**
 *
 * @author cristiandresgp
 */
import EDD.Queue;
import static GUI.Simulacion.clock;
import static GUI.Simulacion.scheduler;

public class ExceptionHandler {
    private Queue blockedQueue;

    public Queue getBlockedQueue() {
        return blockedQueue;
    }

    public void setBlockedQueue(Queue blockedQueue) {
        this.blockedQueue = blockedQueue;
    }

    public ExceptionHandler() {
        this.blockedQueue = new Queue();
    }

    public void blockProcess(Process p) {
    p.setState(Process.ProcessState.BLOCKED);
    blockedQueue.enqueue(p);
    p.setBlockedStartTime(clock.getCurrentCycle()); // Guarda el ciclo en que se bloqueó
    p.setCounter(0);

}

public Process unblockProcess() {
    if (blockedQueue.isEmpty()) return null;
    Process p = (Process) blockedQueue.dequeue();
    p.setState(Process.ProcessState.READY); // Al desbloquear, debe pasar a READY
    return p;
}


    public boolean hasBlockedProcesses() {
        return !blockedQueue.isEmpty();
    }
    
    public void checkBlockedProcesses(int currentCycle) {
    if (!blockedQueue.isEmpty()) {
        Queue tempQueue = new Queue(); // Quitar los <Process> porque Queue no es genérica

        while (!blockedQueue.isEmpty()) {
            Object obj = blockedQueue.dequeue();
            if (!(obj instanceof Process)) {
                System.out.println("[ERROR] Se encontró un objeto en blockedQueue que no es un Process.");
                continue;
            }
            
            Process process = (Process) obj; // Casting seguro
            int blockedTime = currentCycle - process.getBlockedStartTime();

            if (blockedTime >= process.getExceptionDuration()) {
                System.out.println("[DEBUG] Proceso " + process.getName() + " desbloqueado después de " + blockedTime + " ciclos.");
                process.setState(Process.ProcessState.READY);
                scheduler.addProcess(process);
            } else {
                tempQueue.enqueue(process);
            }
        }
        blockedQueue = tempQueue; // Reasignar la cola
    }
}




}


    

