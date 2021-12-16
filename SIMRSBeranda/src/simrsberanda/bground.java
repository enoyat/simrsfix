/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simrsberanda;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class bground  extends JPanel{
    private Image image;
    public bground() {
        image = new ImageIcon(getClass().getResource("bgcharlie.jpg")).getImage();
    }
    @Override
    protected void paintComponent(Graphics grap) {
            super.paintComponent(grap);
    Graphics2D gd = (Graphics2D) grap.create();
    gd.drawImage(image,0,0, getWidth(), getHeight(),null);
    gd.dispose();
    }
}
