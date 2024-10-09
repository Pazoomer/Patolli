package test;

import presentation.FrameInicio;

/**
 * Clase principal que inicia la aplicación Patolli.
 * Esta clase configura la apariencia de la interfaz de usuario
 * y muestra la ventana principal del juego.
 * 
 * @author t1pas
 */
public class Patolli {

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * Configura el look and feel de la interfaz de usuario y 
     * crea y muestra la ventana de inicio.
     *
     * @param args los argumentos de línea de comandos (no se utilizan en esta aplicación)
     */
    public static void main(String[] args) {
        try {
            // Configura el look and feel a Nimbus si está disponible
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            // Registra cualquier excepción que se produzca al configurar el look and feel
            java.util.logging.Logger.getLogger(FrameInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Inicia la ventana de inicio en el hilo de eventos
        java.awt.EventQueue.invokeLater(() -> {
            new FrameInicio().setVisible(true);
        });
    }
}