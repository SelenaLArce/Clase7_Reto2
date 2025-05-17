package Clase7_Reto2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AnalizadorLogs {

    public static void main(String[] args) {
        // Ruta. Revisar get.
        // Correcto primero
        Path rutaLog = Paths.get("src/Solucion_Reto_1/errores.log"); 
        // Luego con error
        Path rutaError = Paths.get("src/Solucion_Reto_1/registro_fallos.txt");

        int totalLineas = 0;
        int conteoErrores = 0;
        int conteoAdvertencias = 0;

        // try-with-resources (hay que cerrar) y BufferedReader (es parecido a input)
        try (BufferedReader lector = Files.newBufferedReader(rutaLog)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                totalLineas++;
                if (linea.contains("ERROR")) {conteoErrores++;}
                if (linea.contains("ADVERTENCIA")) {conteoAdvertencias++;}
            }

            // Mostrar 
            System.out.println("Análisis completado:");
            System.out.println("Total de líneas analizadas: " + totalLineas);
            System.out.println("ERROR: " + conteoErrores);
            System.out.println("ADVERTENCIA: " + conteoAdvertencias);

            double porcentajeError = (totalLineas > 0) ? ((double) conteoErrores / totalLineas) * 100 : 0;
            double porcentajeAdvertencia = (totalLineas > 0) ? ((double) conteoAdvertencias / totalLineas) * 100 : 0;

            System.out.printf("Líneas con Errores: ", porcentajeError);
            System.out.printf("Líneas con Advertencias", porcentajeAdvertencia);

        } catch (IOException e) {
            System.out.println("Error al procesar logs: " + e.getMessage());

            // BufferedWriter error
            try (BufferedWriter escritor = Files.newBufferedWriter(rutaError)) {
                escritor.write("Se produjo un error al leer el archivo 'errores.log': " + e.getMessage());
            } catch (IOException ex) {
                System.out.println("No se pudo escribir en el archivo el número de fallos.");
            }
        }
    }
}
