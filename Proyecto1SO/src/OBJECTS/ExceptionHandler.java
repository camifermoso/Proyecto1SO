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
import GUI.Simulacion;

public class ExceptionHandler {
    private Queue blockedQueue;
    private Scheduler scheduler;
    private Simulacion gui;  // ✅ Agregar referencia a la GUI
    
    public ExceptionHandler(Scheduler scheduler, Simulacion gui) {
        this.scheduler = scheduler;
        this.gui = gui;
        this.blockedQueue = new Queue(); // ✅ Inicializar la cola vacía
    }

    public Queue getBlockedQueue() {
        return blockedQueue;
    }

    public void setBlockedQueue(Queue blockedQueue) {
        this.blockedQueue = blockedQueue;
        this.blockedQueue = new Queue();
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
    if (blockedQueue == null || blockedQueue.isEmpty()) {
        return; // No hay procesos bloqueados
    }

    Queue tempQueue = new Queue();

    while (!blockedQueue.isEmpty()) {
        Object obj = blockedQueue.dequeue();
        if (!(obj instanceof Process)) {
            System.out.println("[ERROR] Se encontró un objeto en blockedQueue que no es un Process.");
            continue;
        }

        Process process = (Process) obj;
        int blockedTime = currentCycle - process.getBlockedStartTime();

        if (blockedTime >= process.getExceptionDuration()) {
            System.out.println("[DEBUG] Proceso " + process.getName() + " desbloqueado después de " + blockedTime + " ciclos.");
            process.setState(Process.ProcessState.READY);
            scheduler.addProcess(process); // ✅ Ahora sí se mueve a la cola de listos
        } else {
            tempQueue.enqueue(process); // ✅ Solo encolar si aún debe seguir bloqueado
        }
    }

    blockedQueue = tempQueue; // ✅ Solo quedan los procesos que siguen bloqueados

    // ✅ Actualizar la interfaz para reflejar cambios
    gui.actualizarColaListos();
    gui.actualizarColaBloqueados();
}





}


    

