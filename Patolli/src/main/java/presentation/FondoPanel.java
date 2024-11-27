package presentation;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author t1pas
 */
public class FondoPanel extends JPanel {

    private Image imagen;
    private final String RUTAFONDO;

    public FondoPanel(String rutaFondo) {
        this.RUTAFONDO=rutaFondo;
    }

    @Override
    public void paint(Graphics g) {
        imagen = new ImageIcon(getClass().getResource(RUTAFONDO)).getImage();

        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        setOpaque(false);

        super.paint(g);
    }
}
