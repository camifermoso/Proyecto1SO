/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

/**
 *
 * @author cristiandresgp
 */

public class ProcessControlBlock { // HAY QUE REVISAR EL TEMA DE LOS HILOS!! seguro hay que cambiar esto

    // Definir los posibles estados del proceso usando un enum para mayor seguridad (creo que solo hay estos en el proyecto, REVISAR) (ESTADO DE SALIDA VA??)
    public enum ProcessState { READY, RUNNING, BLOCKED }

    private int processID;
    private ProcessState state; 
    private int programCounter;
    private int cpuRegister;
    
    public ProcessControlBlock(int processID) {
        this.processID = processID;
        this.state = ProcessState.READY; // Inicialmente todos los procesos están en READY
        this.programCounter = 0;
        this.cpuRegister = 0;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public void incrementProgramCounter() {
        programCounter++;
    }

    public ProcessState getState() {
        return state;
    }

    public int getProcessID() {
        return processID;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public int getCpuRegister() {
        return cpuRegister;
    }

    public void setCpuRegister(int cpuRegister) {
        this.cpuRegister = cpuRegister;
    }
}

