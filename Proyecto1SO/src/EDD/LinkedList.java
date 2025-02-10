/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author camilafermosoiglesias
 */
public class LinkedList {
    private Nodo head;
    private int size;
    
    public LinkedList() {
        head = null;
    }
    
    // Getters and Setters
    
    public Nodo getHead() {
        return head;
    }
    
    public void setHead(Nodo head) {
        this.head = head;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    // Check if list is empty
    public boolean isEmpty() {
        return head == null;
    }
    
    // Methods
    public void addFirst(Object data) {
        Nodo newNode = new Nodo(data);
        
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }
    
    public void addLast(Object data) {
        Nodo newNode = new Nodo(data);
        
        if (isEmpty()) {
            head = newNode;
        } else {
            Nodo current = head;
            while (current.getNext() != null) {
                current = (Nodo) current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }
    
    public void addIndex(Object data, int index){
        Nodo newNode = new Nodo(data);
        
        if (isEmpty()) {
            setHead(newNode);
        } else {
            if (index < 0 || index > size) {
                System.out.println("Index Error");
            } else if (index == size) {
                addLast(data);
            } else if (index == 00) {
                addFirst(data);
            } else {
                Nodo pointer = getHead();
                int aux = 0;
                while (pointer.getNext() != null && aux < index - 1) {
                    pointer = (Nodo) pointer.getNext();
                    aux++;  
                }
                newNode.setNext((Nodo) pointer.getNext());
                pointer.setNext(newNode);
            }
            size++;
        }
    }
         
    public Object removeFirst(){
        if(isEmpty()) {
            return null;
        }
        
        Object data = head.getElement();
        head = (Nodo) head.getNext();
        size--;
        return data; 
        }
  
}