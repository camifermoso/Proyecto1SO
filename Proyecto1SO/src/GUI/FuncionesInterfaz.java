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
    private static final CargarTXT cargartxt =new CargarTXT();
    private static final Configuracion configuracion= new Configuracion();
    private static final Estadisticas estadisticas= new Estadisticas();
    private static final Graficos graficos= new Graficos();
    private static final Guardar guardar= new Guardar();
    private static final Simulacion simulacion = new Simulacion();

    public static Bienvenida getBienvenida() {
        return bienvenida;
    }
    
    public static void openBienvenida() {
        getBienvenida().setVisible(true);
    }

    public static CargarTXT getCargartxt() {
        return cargartxt;
    }
    
    public static void openCargartxt() {
        getCargartxt().setVisible(true);
    }

    public static Configuracion getConfiguracion() {
        return configuracion;
    }

    public static Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public static Graficos getGraficos() {
        return graficos;
    }

    public static Guardar getGuardar() {
        return guardar;
    }

    public static Simulacion getSimulacion() {
        return simulacion;
    }
    public static void openSimulacion() {
        getSimulacion().setVisible(true);
    }
    
    public static void VolverMenu(){
       //Muestro el menu principal
        getSimulacion().setVisible(true);
        //Hago que las ventanas abiertas ya no se muestren
        getCargartxt().setVisible(false);
        getConfiguracion().setVisible(false);
        getEstadisticas().setVisible(false);
        getGuardar().setVisible(false);
        getGraficos().setVisible(false);
   }
    
    
    
    
}
