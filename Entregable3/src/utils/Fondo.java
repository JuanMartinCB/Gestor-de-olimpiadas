package utils;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fondo extends JPanel {

    @Override
    public void paint(Graphics g) {

        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/fondo.png"));
        g.drawImage(imagen.getImage(),0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}