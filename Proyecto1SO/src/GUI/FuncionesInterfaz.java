/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author cristiandresgp
 */
public class FuncionesInterfaz {
    private static final Bienvenida bienvenida= new Bienvenida(); 
    private static final Estadisticas estadisticas= new Estadisticas();
    private static final Graficos graficos= new Graficos();
    private static Simulacion simulacion;


    public static Bienvenida getBienvenida() {
        return bienvenida;
    }
    
    public static void openBienvenida() {
        getBienvenida().setVisible(true);
    }

    public static Estadisticas getEstadisticas() {
        return estadisticas;
    }
    
    public static void openEstadisticas() {
        getEstadisticas().setVisible(true);
    }

    public static Graficos getGraficos() {
        return graficos;
    }
    
    public static void openGraficos() {
        getGraficos().setVisible(true);
    }

    public static Simulacion getSimulacion() {
        return simulacion;
    }
    public static void openSimulacion(int numCPUs) {
    Simulacion simulacion = new Simulacion(numCPUs);
    simulacion.setVisible(true);
}

    
    public static void VolverMenu(){
       //Muestro el menu principal
        getSimulacion().setVisible(true);
        //Hago que las ventanas abiertas ya no se muestren
        getEstadisticas().setVisible(false);
        getGraficos().setVisible(false);
   }
    
    
    
    
}
