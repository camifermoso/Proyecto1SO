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
    private boolean executing = false; // Indica si un proceso esta siendo ejecutado por una CPU o no
    private int programCounter;
    private int memoryAddressRegister;
    private int baseMemoryAddress;  // Dirección base en memoria del proceso
    private int blockedStartTime; // Ciclo en el que el proceso fue bloqueado



    public Process(int processID, String name, int totalInstructions, boolean isCPUBound, 
                   int exceptionCycle, int exceptionDuration, Priority priority, int baseMemoryAddress) {
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
        this.memoryAddressRegister = baseMemoryAddress;  // Inicializar MAR con la dirección base
        this.baseMemoryAddress = baseMemoryAddress;  // Guardar la dirección base del proceso
        
    }

    public void executeInstruction() {
        if (executedInstructions < totalInstructions) {
            executedInstructions++;
            memoryAddressRegister = baseMemoryAddress + programCounter;
            programCounter++;
        }
        if (executedInstructions >= totalInstructions) {
            this.state = ProcessState.TERMINATED;
        }
    }

    public int getBaseMemoryAddress() {
        return baseMemoryAddress;
    }

    public void setBaseMemoryAddress(int baseMemoryAddress) {
        this.baseMemoryAddress = baseMemoryAddress;
    }

    public int getTotalInstructions() {
        return totalInstructions;
    }

    public void setTotalInstructions(int totalInstructions) {
        this.totalInstructions = totalInstructions;
    }

    public int getExecutedInstructions() {
        return executedInstructions;
    }

    public void setExecutedInstructions(int executedInstructions) {
        this.executedInstructions = executedInstructions;
    }

    public boolean isIsCPUBound() {
        return isCPUBound;
    }

    public void setIsCPUBound(boolean isCPUBound) {
        this.isCPUBound = isCPUBound;
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
    
    public boolean isIOBound() {
    return !isCPUBound;
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

    public boolean isExecuting() {
        return executing;
    }

    public void setExecuting(boolean executing) {
        this.executing = executing;
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
    
    public int getBlockedStartTime() {
    return blockedStartTime;
}

public void setBlockedStartTime(int blockedStartTime) {
    this.blockedStartTime = blockedStartTime;
}

    public Object getTipo(){
    if (isCPUBound()) {
        return "CPU-Bound";
    }
    else {
        return "I/O-Bound";
    }}
}


