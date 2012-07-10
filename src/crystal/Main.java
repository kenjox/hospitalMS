/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crystal;


import crystal.Users.Login;
import javax.swing.UIManager;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            System.err.println("Look and feel not found");
            
        }
      
        new Login().setVisible(true);
    }

}
