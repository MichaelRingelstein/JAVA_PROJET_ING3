/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.*;
import modele.Connexion;

/**
 *
 * @author micha
 */
public class Control {
    
    
    public static void main(String args[]) throws SQLException
    {
        String a = "hopital";
        String b = "root";
        String c = "";
        try {
            Connexion conn = new Connexion(a, b, c);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
