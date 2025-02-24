/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

import GUI.Simulacion;

/**
 *
 * @author cristiandresgp
 */
public class Clock { //Administra el ciclo de reloj global.
    private int currentCycle;
    private long tickDuration; // Duración del ciclo en milisegundos
    private boolean running;
    private ExceptionHandler exceptionHandler; 

    public Clock(ExceptionHandler exceptionHandler) {
        this.currentCycle = 0;
        this.tickDuration = 1000; // Por defecto, 1 segundo
        this.running = false;
        this.exceptionHandler = exceptionHandler;
    }

    public void tick() {
        currentCycle++;
    }

    public int getCurrentCycle() {
        return currentCycle;
    }
    
    public void reset() { // solo para reiniciar la simulación!!
    currentCycle = 0;
}
    public void setTickDuration(long tickDuration) {
        this.tickDuration = tickDuration;
    }
    
    public long getTickDuration() {
        return tickDuration;
    }
    
    public void startClock() {
    running = true;
    new Thread(() -> {
        while (running) {
            try {
                Thread.sleep(tickDuration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            tick(); // Incrementar el ciclo de reloj
            System.out.println("Ciclo de reloj: " + currentCycle);
            
            synchronized (this) {
                notifyAll(); // 🔹 Notificar a todas las CPUs que hay un nuevo ciclo de reloj
            }
            
            // ✅ Revisar procesos bloqueados después de cada ciclo de reloj
           exceptionHandler.checkBlockedProcesses(currentCycle);
        }
    }).start();
}



    
    public void stopClock() {
        running = false;
    }

}

