/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author cristiandresgp
 */

import OBJECTS.Process;

public class Queue {
    private Nodo head, tail;
    private int size;
    
    public Queue() {
        this.head = this.tail = null;
        size = 0;
    }
    public Nodo getHead() {
        return head;
    }
    public void setHead(Nodo head) {
        this.head = head;
    }
    public Nodo getTail() {
        return tail;
    }
    public void setTail(Nodo tail) {
        this.tail = tail;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    
    public Object peek() {
    if (isEmpty()) {
        return null;
    }
    return head.getElement();
    }

    
    public void enqueue(Object element) {
        Nodo nodo = new Nodo(element);
        if (isEmpty()) {
            setHead(nodo);
            setTail(nodo);
        } else {
            getTail().setNext(nodo);
            setTail(nodo);
        }
        size++;
        System.out.println("Proceso agregado a la cola: " + element);
        System.out.println("Estado actual de la cola: " + this);
    }
//    
//    public Object dequeue() {
//        if (isEmpty()) {
//            System.out.println("La cola esta vacía");
//            return null;
//        } else {
//            Object element = head.getElement();
//            head = head.getNext();
//            if (head == null) {  
//            tail = null;  // Si la cola queda vacía, tail también debe actualizarse
//            }
//            size--;
//            return element;
//        }
//        
//    }
    
    public Object dequeue() {
        if (isEmpty()) {
            System.out.println("[ERROR] Se intentó extraer de una cola vacía.");
            return null;
        } else {
            Object element = head.getElement();
            head = head.getNext();
            if (head == null) {  
            tail = null;  // Si la cola queda vacía, tail también debe actualizarse
        }
        size--;
        System.out.println(" Proceso eliminado de la cola: " + element);
        System.out.println(" Estado actual de la cola: " + this);
        return element;
    }
}

    
    public boolean isEmpty() {
        return getHead() == null;
    }
    
    public void print() {
    Nodo pointer = head;
    while (pointer != null) {
        System.out.print("[ " + pointer.getElement() + " ] -> ");
        pointer = pointer.getNext();
    }
    System.out.println("NULL");
}
    
    public String getAllProcesses() {
    StringBuilder result = new StringBuilder();
    Nodo temp = head; // Empezar desde la cabeza de la cola

    while (temp != null) {
        if (temp.getElement() instanceof Process) { // Verificar que el elemento es un proceso
            Process p = (Process) temp.getElement();
            
            // Calcular el porcentaje de progreso
            double porcentaje = (p.getProgramCounter() / (double) p.getTotalInstructions()) * 100;
            
            result.append("ID: ").append(p.getProcessID())
                  .append(", Nombre: ").append(p.getName())
                    .append(", Tipo: ").append(p.getTipo())
                  .append(", Prioridad: ").append(p.getPriority())
                  .append(", Estado: ").append(p.getState())
                  .append(", PC: ").append(p.getProgramCounter())
                  .append(", MAR: ").append(p.getMemoryAddressRegister())
                  .append(", Progreso: ").append(String.format("%.2f", porcentaje)).append("%")
                  .append("\n");
        }
        temp = temp.getNext();
    }

    return result.toString();
}
    

    public String getBlockedProcesses() {
        StringBuilder result = new StringBuilder();
        Nodo temp = head;
    
        while (temp != null) {
            if (temp.getElement() instanceof Process) {
                Process p = (Process) temp.getElement();
                double porcentaje = (p.getExecutedInstructions() / (double) p.getTotalInstructions()) * 100;
    
                result.append("ID: ").append(p.getProcessID())
                      .append(", Nombre: ").append(p.getName())
                      .append(", Tipo: ").append(p.getTipo())
                      .append(", Prioridad: ").append(p.getPriority())
                      .append(", Estado: ").append(p.getState())
                      .append(", PC: ").append(p.getProgramCounter())
                      .append(", MAR: ").append(p.getMemoryAddressRegister())
                      .append(", Progreso: ").append(String.format("%.2f", porcentaje)).append("%\n");
            }
            temp = temp.getNext();
        }
        return result.toString();
    }
    
    public String getTerminatedProcesses() {
        StringBuilder result = new StringBuilder();
        Nodo temp = head;
    
        while (temp != null) {
            if (temp.getElement() instanceof Process) {
                Process p = (Process) temp.getElement();
                result.append("ID: ").append(p.getProcessID())
                      .append(", Nombre: ").append(p.getName())
                      .append(", Tipo: ").append(p.isCPUBound() ? "CPU-Bound" : "I/O-Bound")
                      .append(", Estado: ").append(p.getState())
                      .append(", Progreso: 100%\n"); // Siempre 100% porque está en Terminados
            }
            temp = temp.getNext();
        }
        return result.toString();
    }

    


    
}