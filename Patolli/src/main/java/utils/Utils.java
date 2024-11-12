package utils;

import java.util.Random;

/**
 * Clase de utilidades que proporciona funciones para la simulación de lanzamientos
 * de dados y la generación de códigos de sala.
 * 
 * La clase inicializa un arreglo de resultados que representa las probabilidades
 * de obtener diferentes resultados al lanzar dados, y permite simular estos lanzamientos.
 * También incluye un método para generar códigos de sala aleatorios.
 * 
 * @author t1pas
 */
public class Utils {

    // Arreglo que contiene los resultados posibles basados en probabilidades
    int[] resultados = new int[32];

    /**
     * Constructor de la clase Utils que inicializa el arreglo de resultados.
     */
    public Utils() {
        Inicilizar();
    }

    /**
     * Inicializa el arreglo de resultados basado en las probabilidades definidas.
     * El arreglo contiene 32 elementos que representan los diferentes resultados
     * posibles al lanzar los dados, con la siguiente distribución:
     * - 0: 1 vez
     * - 1: 5 veces
     * - 2: 10 veces
     * - 3: 10 veces
     * - 4: 5 veces
     * - 10: 1 vez
     */
    public void Inicilizar() {
        int index = 0;

        // Llenar el arreglo de acuerdo a las probabilidades
        for (int i = 0; i < 1; i++) { // Probabilidad de sacar 0 (1 vez)
            resultados[index++] = 0;
        }
        for (int i = 0; i < 5; i++) { // Probabilidad de sacar 1 (5 veces)
            resultados[index++] = 1;
        }
        for (int i = 0; i < 10; i++) { // Probabilidad de sacar 2 (10 veces)
            resultados[index++] = 2;
        }
        for (int i = 0; i < 10; i++) { // Probabilidad de sacar 3 (10 veces)
            resultados[index++] = 3;
        }
        for (int i = 0; i < 5; i++) { // Probabilidad de sacar 4 (5 veces)
            resultados[index++] = 4;
        }
        for (int i = 0; i < 1; i++) { // Probabilidad de sacar 5 -> 10 (1 vez)
            resultados[index++] = 10;
        }
    }

    /**
     * Genera un lanzamiento aleatorio basado en las probabilidades definidas.
     * 
     * @return Un entero que representa el resultado del lanzamiento.
     */
    public int GenerarLanzamiento() {
        
        return resultados[new Random().nextInt(resultados.length)];
    }

    /**
     * Genera un código de sala aleatorio de 4 letras mayúsculas.
     * 
     * @return Un String que representa el código de sala generado.
     */
    public static String GenerarCodigoSala() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Letras disponibles
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();

        // Generar 4 letras aleatorias
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(letras.length());
            codigo.append(letras.charAt(index));
        }

        return codigo.toString(); // Retornar el código generado
    }
}
