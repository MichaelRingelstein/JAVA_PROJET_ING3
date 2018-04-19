/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import modele.*;
import modele.Connexion;
import vue.*;

/**
 *
 * @author micha
 */
public class Control{
    //setup les infos de la bdd
        private static String a = "hopital";
        private static String b = "root";
        private static String c = "";
    
    public static void main(String args[]) throws SQLException
    {
        
        
        /* Demande de connection à la base de donnée
        try {
            Connexion conn = new Connexion(a, b, c);
            System.out.println("Connected");
            conn.searchDocteurSpecialise("Cardiologue");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        Homepage a = new Homepage();

    }
    
    
}
