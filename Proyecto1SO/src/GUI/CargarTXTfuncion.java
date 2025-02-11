/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import OBJECTS.Process;
import OBJECTS.Scheduler;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author cristiandresgp
 */
public class CargarTXTfuncion {
    public static void cargarDesdeArchivo(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de configuración");

        int seleccion = fileChooser.showOpenDialog(frame);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                boolean primeraLinea = true;

                while ((linea = br.readLine()) != null) {
                    if (primeraLinea) { // Omitir la cabecera
                        primeraLinea = false;
                        continue;
                    }

                    String[] datos = linea.split(",");
                    if (datos.length != 6) {
                        System.out.println("Formato incorrecto en línea: " + linea);
                        continue;
                    }

                    // Extraer datos del archivo
                    int processID = Scheduler.getNextProcessID();
                    String nombre = datos[0].trim();
                    int instrucciones = Integer.parseInt(datos[1].trim());
                    boolean isCPUBound = Boolean.parseBoolean(datos[2].trim());
                    int cicloExcep = Integer.parseInt(datos[3].trim());
                    int duracionExcep = Integer.parseInt(datos[4].trim());
                    Process.Priority prioridad = Process.Priority.valueOf(datos[5].trim().toUpperCase());

                    // Asignar una dirección de memoria única
                    int baseMemoryAddress = Scheduler.getNextMemoryAddress();

                    // Crear el proceso
                    Process nuevoProceso = new Process(
                        processID, nombre, instrucciones, isCPUBound, cicloExcep, duracionExcep, prioridad, baseMemoryAddress
                    );

                    // Agregar el proceso a la cola de listos
                    Simulacion.scheduler.addProcess(nuevoProceso);

                }

                br.close();
                JOptionPane.showMessageDialog(frame, "Procesos cargados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
