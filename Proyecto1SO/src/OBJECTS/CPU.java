/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

import java.util.concurrent.Semaphore;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author cristiandresgp
 */

public class CPU {
    private int cpuID;
    private Process currentProcess;
    private boolean isBusy;
    private static final Semaphore cpuSemaphore = new Semaphore(1);
    private JTextArea logArea; 

    public CPU(int cpuID, JTextArea logArea) {
        this.cpuID = cpuID;
        this.currentProcess = null;
        this.isBusy = false;
        this.logArea = logArea;
    }

        public synchronized boolean assignProcess(Process p) {
        if (!isBusy) {
            try {
                cpuSemaphore.acquire();  // Adquiere el semaforo para exclusion mutua
                this.currentProcess = p;
                this.isBusy = true;
                log("CPU " + cpuID + " assigned process " + p.getProcessID());

                // Lanza el proceso en un hilo separado
                Thread processThread = new Thread(p);
                processThread.start();
                
                return true;
            } catch (InterruptedException e) {
                log("Error assigning process " + p.getProcessID() + " to CPU " + cpuID);
                return false;
            }
        }
        return false; // Sucede si la CPU ya ocupada
    }

    public void executeCycle() {
        if (currentProcess != null) {
            try {
                currentProcess.executeInstruction(); 
                if (currentProcess.isCompleted()) { 
                    terminateProcess();
                }
            } catch (InterruptedException e) {
                log("Error during execution of process " + currentProcess.getProcessID() + ": " + e.getMessage());
            }
        }
    }


    public void terminateProcess() {
        if (currentProcess != null) {
            log("CPU " + cpuID + " finished process " + currentProcess.getProcessID());
            currentProcess = null;
            isBusy = false;
            cpuSemaphore.release(); // Libera el semaforo
        }
    }


    public boolean isBusy() {
        return isBusy;
    }


    public Process getCurrentProcess() {
        return currentProcess;
    }

    public int getCpuID() {
        return cpuID;
    }


    private void log(String message) {
        if (logArea != null) {
            SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
        }
    }
}