/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.Control;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class MainPage extends JFrame {
       
        
       public Connexion con;

   

    /**
     *
     * @param db_name
     * @param user_name
     * @param pass_word
     */
    public MainPage(String db_name, String user_name, String pass_word) 
    {
        Connexion conn;
           try {
               conn = new Connexion(db_name, user_name, pass_word);
               System.out.println("Connected");
               ArrayList<String> l = conn.remplirChampsRequete("SELECT * FROM employe AS e, docteur AS d WHERE e.numero = d.numero AND d.specialite = \"Cardiologue\"" );
               for(int i = 0; i < l.size(); i++)
               {

                  System.out.println(l.get(i));

               }
           } catch (SQLException ex) {
               Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
}
