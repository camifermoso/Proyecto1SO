/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author cristiandresgp
 */
public class Pila{
    private Nodo peak;
    private int size;

    public Pila() {
        this.peak = null;
        this.size = 0;
    }

    public Nodo getPeak() {
        return peak;
    }

    public void setPeak(Nodo head) {
        this.peak = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void push(Object element) {
        Nodo nodo = new Nodo(element);
        if (isEmpty()) {
            setPeak(nodo);
        } else {
           nodo.setNext(getPeak());
           setPeak(nodo);
        }
        size++;
    }

    public Object pop(){
        if (isEmpty()) {
            System.out.println("El stack está vacío");
        } else {
            Nodo pointer = getPeak();
            setPeak(pointer.getNext());
            pointer.setNext(null);
            size--;
            return pointer.getElement();
        }
        return null;
    }
    
    
    public void print() {
        Nodo pointer = getPeak();
        while (pointer != null) {
            System.out.println("[ "+pointer.getElement() + " ]");
            pointer = pointer.getNext();
        }
    }
    
    public boolean isEmpty() {
        return getPeak() == null;
    }
    
    public void reverse(){
        if (!isEmpty()) {
            Object poppedElement = pop();
            reverse();
            insertAtBottom(poppedElement);
        }
        
    
    }
    
    private void insertAtBottom(Object element) {
        if (isEmpty()) {
            push(element);
        } else {
            Object poppedElement = pop();
            insertAtBottom(element);
            push(poppedElement);
        }
    }
        
}
