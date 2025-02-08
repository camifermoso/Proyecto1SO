/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

/**
 *
 * @author cristiandresgp 
 */
public class Process {
    public enum Priority { ALTA, MEDIA, BAJA } // Definir niveles de prioridad
    public enum ProcessState { READY, RUNNING, BLOCKED, TERMINATED }

    private int processID;
    private String name;
    private int totalInstructions;
    private int executedInstructions;
    private boolean isCPUBound; // true = CPU-bound, false = I/O-bound
    private int exceptionCycle; // Cada cuántos ciclos genera una excepción
    private int exceptionDuration; // Cuántos ciclos tarda en resolverse
    private Priority priority;
    private ProcessState state;
    private int programCounter;
    private int memoryAddressRegister;

    public Process(int processID, String name, int totalInstructions, boolean isCPUBound, 
                   int exceptionCycle, int exceptionDuration, Priority priority) {
        this.processID = processID;
        this.name = name;
        this.totalInstructions = totalInstructions;
        this.executedInstructions = 0;
        this.isCPUBound = isCPUBound;
        this.exceptionCycle = exceptionCycle;
        this.exceptionDuration = exceptionDuration;
        this.priority = priority;
        this.state = ProcessState.READY;
        this.programCounter = 0;
        this.memoryAddressRegister = 0;
    }

    public void executeInstruction() {
        if (executedInstructions < totalInstructions) {
            executedInstructions++;
            programCounter++;
        }
        if (executedInstructions >= totalInstructions) {
            this.state = ProcessState.TERMINATED;
        }
    }

    public boolean isCompleted() {
        return executedInstructions >= totalInstructions;
    }

    public int getProcessID() {
        return processID;
    }

    public String getName() {
        return name;
    }

    public boolean isCPUBound() {
        return isCPUBound;
    }

    public int getExceptionCycle() {
        return exceptionCycle;
    }

    public int getExceptionDuration() {
        return exceptionDuration;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public int getMemoryAddressRegister() {
        return memoryAddressRegister;
    }

    public void setMemoryAddressRegister(int memoryAddressRegister) {
        this.memoryAddressRegister = memoryAddressRegister;
    }
}


