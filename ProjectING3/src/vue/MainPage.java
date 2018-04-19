/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.Control;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class MainPage extends JFrame {
       
        
       public Connexion conn;
       private Reporting report;
       private JTabbedPane menu;
       private JTabbedPane onglet_search;

   

    /**
     *
     * @param db_name
     * @param user_name
     * @param pass_word
     */
    public MainPage(String db_name, String user_name, String pass_word) throws SQLException 
    {
        
        //instanciation de la fenêtre
        this.setTitle("Hôpital Saint Bernard");
        this.setSize(1600, 1200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.connect(db_name, user_name, pass_word);
        
        String tab_onglet[] = {"docteur", "infirmier", "malade", "service", "chambre"};
        //this.report = new Reporting(this.conn);
        
        
        //instanciation de l'onglet search
        this.onglet_search = new JTabbedPane(JTabbedPane.LEFT);
        for(int i = 0; i < tab_onglet.length; i++)
        {
            this.onglet_search.add(tab_onglet[i],new SearchPanel(tab_onglet[i], this.conn));
        }
        
        
        //instanciation du menu à plusieurs onglets 
        menu = new JTabbedPane(JTabbedPane.LEFT);
        menu.add("Recherche",this.onglet_search);
        //menu.add("Reporting", report);
        this.setContentPane(menu);
           
    }
    
    
    private void connect(String db_name, String user_name, String pass_word)
    {
        try {
               this.conn = new Connexion(db_name, user_name, pass_word);
               System.out.println("Connected");
           } catch (SQLException ex) {
               Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
           } catch (ClassNotFoundException ex) {
               Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }
    
}
