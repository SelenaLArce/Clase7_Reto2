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

//Errores
//[2025-03-31 01:03:55] INFO - Inicio de ejecución de pruebas automáticas.
//[2025-03-31 01:04:01] ERROR - Falla en conexión con base de datos.
//[2025-03-31 01:04:12] WARNING - Tiempo de respuesta excedido en módulo de login.
//[2025-03-31 01:04:20] INFO - Prueba de carga completada exitosamente.
//[2025-03-31 01:04:25] ERROR - Excepción NullPointer en clase UsuarioService.
//[2025-03-31 01:04:35] WARNING - Uso de memoria elevado detectado.
//[2025-03-31 01:04:50] INFO - Pruebas de integración finalizadas sin errores críticos.
//[2025-03-31 01:05:05] ERROR - Timeout al conectar con API externa.
//[2025-03-31 01:05:15] INFO - Todos los módulos fueron validados.
//[2025-03-31 01:05:30] WARNING - Latencia alta durante pruebas de red.
