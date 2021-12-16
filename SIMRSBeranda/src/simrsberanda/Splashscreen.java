/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simrsberanda;

/**
 *
 * @author pepep
 */
public class Splashscreen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        splash Splash = new splash();
        Splash.setVisible(true);
        login Login = new login();
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(22);
                Splash.loadnum.setText(Integer.toString(i)+"%");
                Splash.loadbar.setValue(i);
                if (i==100) 
                {
                    Splash.setVisible(false);
                    Login.setVisible(true);
                }
                
            }
        } catch (Exception e) {
        }
    }
    
}
