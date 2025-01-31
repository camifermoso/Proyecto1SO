/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

/**
 *
 * @author cristiandresgp
 */
public class Clock { //Administra el ciclo de reloj global.
    private int currentCycle;

    public Clock() {
        this.currentCycle = 0;
    }

    public void tick() {
        currentCycle++;
    }

    public int getCurrentCycle() {
        return currentCycle;
    }
    
    public void reset() { // solo para reiniciar la simulación!!
    currentCycle = 0;
}

}

