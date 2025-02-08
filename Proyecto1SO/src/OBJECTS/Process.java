/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.concurrent.Semaphore;

/**
 *
 * @author cristiandresgp 
 */
public class Process implements Runnable{
    public enum Priority { ALTA, MEDIA, BAJA } // Definir niveles de prioridad
<<<<<<< HEAD
    
    private static final Semaphore cpuSemaphore = new Semaphore(1);
    
=======
    public enum ProcessState { READY, RUNNING, BLOCKED, TERMINATED }

>>>>>>> f826293505a601f85ddc40e917b27c0d9f2a7757
    private int processID;
    private String name;
    private int totalInstructions;
    private int executedInstructions;
    private boolean isCPUBound; // true = CPU-bound, false = I/O-bound
    private int exceptionCycle; // Cada cuántos ciclos genera una excepción
    private int exceptionDuration; // Cuántos ciclos tarda en resolverse
    private Priority priority;
<<<<<<< HEAD
    private JTable table;
=======
    private ProcessState state;
    private int programCounter;
    private int memoryAddressRegister;
>>>>>>> f826293505a601f85ddc40e917b27c0d9f2a7757

    public Process(int processID, String name, int totalInstructions, boolean isCPUBound, 
                   int exceptionCycle, int exceptionDuration, Priority priority, JTable table) {
        this.processID = processID;
        this.name = name;
        this.totalInstructions = totalInstructions;
        this.executedInstructions = 0;
        this.isCPUBound = isCPUBound;
        this.exceptionCycle = exceptionCycle;
        this.exceptionDuration = exceptionDuration;
        this.priority = priority;
<<<<<<< HEAD
        this.table = table;
    }

    public void run() {
        while (!isCompleted()) {
            try {
                if (isCPUBound) {
                    executeInstruction();
                } else {
                    if (executedInstructions > 0 && executedInstructions % exceptionCycle == 0) {
                        handleException();
                    } else {
                        executeInstruction();
                    }
                }

                updateTable("Running");
                Thread.sleep(500);

            } catch (InterruptedException e) {
                updateTable("Interrupted");
                return;
            }
        }
        updateTable("Completed");
=======
        this.state = ProcessState.READY;
        this.programCounter = 0;
        this.memoryAddressRegister = 0;
>>>>>>> f826293505a601f85ddc40e917b27c0d9f2a7757
    }

    public void executeInstruction() {
        if (executedInstructions < totalInstructions) {
            executedInstructions++;
<<<<<<< HEAD
            updateTable("Executing");
=======
            programCounter++;
        }
        if (executedInstructions >= totalInstructions) {
            this.state = ProcessState.TERMINATED;
>>>>>>> f826293505a601f85ddc40e917b27c0d9f2a7757
        }
    }

    private void handleException() throws InterruptedException {
        updateTable("Blocked");
        Thread.sleep(exceptionDuration * 500);
    }

    public boolean isCompleted() {
        return executedInstructions >= totalInstructions;
    }

    private void updateTable(String status) {
        if (table == null) return;

        SwingUtilities.invokeLater(() -> {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                if ((int) model.getValueAt(i, 0) == processID) {
                    model.setValueAt(status, i, 2);
                    return;
                }
            }
            model.addRow(new Object[]{processID, name, status});
        });
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


