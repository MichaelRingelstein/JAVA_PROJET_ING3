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
       private JTabbedPane menu;
       private JTabbedPane onglet_search;
       private JPanel onglet_reporting;
       private JTabbedPane onglet_update;

   

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
        
        //onglets du panneau recherche 
        String tab_onglet_search[] = {"docteur", "infirmier", "malade", "service", "chambre"};
       
        
        //instanciation de l'onglet search
        this.onglet_search = new JTabbedPane(JTabbedPane.LEFT);
        this.onglet_reporting = new JPanel();
        this.onglet_update = new JTabbedPane(JTabbedPane.LEFT);
        for(int i = 0; i < tab_onglet_search.length; i++)
        {
            this.onglet_search.add(tab_onglet_search[i],new SearchPanel(tab_onglet_search[i], this.conn));
        }
        this.onglet_reporting.add(new Reporting(conn));
          
        this.onglet_update.add("Ajouter",new AddPanel(this.conn));
        this.onglet_update.add("Modifier",new UpdatePanel(this.conn));
        this.onglet_update.add("Supprimer",new DeletePanel(this.conn));
        
       
        
        //instanciation du menu à plusieurs onglets 
        menu = new JTabbedPane(JTabbedPane.LEFT);
        menu.add("Recherche",this.onglet_search);
        menu.add("Reporting",this.onglet_reporting);
        menu.add("Mise à Jour", this.onglet_update);
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
