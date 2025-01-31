/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author cristiandresgp
 */
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
    }
    public Object dequeue() {
        if (isEmpty()) {
            System.out.println("La cola esta vacía");
            return null;
        } else {
            Object element = head.getElement();
            head = head.getNext();
            if (head == null) {  
            tail = null;  // Si la cola queda vacía, tail también debe actualizarse
            }
            size--;
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

    
}