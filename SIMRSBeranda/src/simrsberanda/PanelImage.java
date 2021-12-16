/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simrsberanda;

/**
 *
 * @author macbook
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Akira
 */
public class PanelImage extends JPanel{
    private String url;

    public PanelImage(String url) {
        this.url = url;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image gambar = new ImageIcon(getClass().getResource(url)).getImage();

        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawImage(gambar, 0,0,getWidth(),getHeight(), this);
        g2.dispose();
    }
}