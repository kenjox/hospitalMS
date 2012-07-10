/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystal.validation;

/**
 *
 * @author Administrator
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.event.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class validators {

    public static boolean EmailValidator(String text){
        if (text.indexOf("@") > 0){
            if (text.lastIndexOf(".") < text.indexOf("@")){
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean validateNonDigitTextField(KeyEvent ke){
        char c = ke.getKeyChar();
        if(Character.isDigit(c)){
            Toolkit.getDefaultToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null,"please enter text only"," ",JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public static void validateDigitTextField(KeyEvent ke){
        char c = ke.getKeyChar();
        if(Character.isDigit(c)==false){
            Toolkit.getDefaultToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(null,"please enter Numbers only"," ",JOptionPane.WARNING_MESSAGE);
        }
    }
    
     //password encyrption method
    public static String getMD5(String message) {
		MessageDigest m;
		String hashtext = null;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(message.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);	
			hashtext = bigInt.toString(16);		
			while(hashtext.length() < 32 ){
				hashtext = "0"+hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}
		return hashtext;
	}
    
  
    
}
