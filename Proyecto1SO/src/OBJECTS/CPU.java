/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

/**
 *
 * @author cristiandresgp
 */
public class CPU {
    private int cpuID;
    private Process currentProcess;
    private boolean isBusy;

    public CPU(int cpuID) {
        this.cpuID = cpuID;
        this.currentProcess = null;
        this.isBusy = false;
    }

    public void assignProcess(Process p) {
        this.currentProcess = p;
        this.isBusy = true;
    }

    public void executeCycle() {
        if (currentProcess != null) {
            currentProcess.executeInstruction();
            if (currentProcess.isCompleted()) {
                terminateProcess();
            }
        }
    }

    public void terminateProcess() {
        this.currentProcess = null;
        this.isBusy = false;
    }

    public boolean isBusy() {
        return isBusy;
    }
    
    public Process getCurrentProcess() {
    return currentProcess;
}
}

