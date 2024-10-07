
package utils;

import java.util.Random;

/**
 *
 * @author t1pas
 */
public class Utils {

    int[] resultados = new int[32];

    public Utils() {
        Inicilizar();
    }

    public void Inicilizar() {
        // Arreglo que contiene la cantidad de resultados en función de las probabilidades
        // 32 elementos en total (sumatoria de 1, 5, 10, 10, 5, 1)
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

    public int GenerarLanzamiento() {
        // Simular un lanzamiento aleatorio de los dados
        return resultados[new Random().nextInt(resultados.length)];

    }
    
     public static String GenerarCodigoSala(){
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
