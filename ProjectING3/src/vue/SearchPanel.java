/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.QueryCtrl;
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class SearchPanel extends JPanel{
    
    private JComboBox type_employe;
    private ArrayList<JCheckBox> check_box;
    private JButton recherche_button;
    private JPanel recherche_panel;
    private String table; 
    private Connexion conex;
    private JLabel result;
    private JScrollPane scroll_pan;
    
    /**
     * Constructeur par défaut du searchpanel avec peu de personnalisation
     */
    public SearchPanel()
    {
        
    }
    
    /**
     * Constructeur personnalisé avec en parametres des type pour créer une combo box
     * @param type 
     */
    public SearchPanel(String type, Connexion con)
    {
     
        this.table = type;
        this.conex = con;
        this.check_box = new ArrayList<>();
        this.recherche_panel = new JPanel();
        this.recherche_button = new JButton("Rechercher");
        this.recherche_button.addActionListener(new QueryListener());
        try {
            ArrayList<String> l = con.remplirChampsTable(table);
            for(int i = 0; i < l.size(); i++)
            {
              check_box.add(new JCheckBox(l.get(i)));
              this.recherche_panel.add(check_box.get(i));
            }
            
            this.recherche_panel.add(recherche_button);
            this.setLayout(new BorderLayout());
            this.add(recherche_panel, BorderLayout.NORTH);
            result = new JLabel();
            this.scroll_pan = new JScrollPane(result);
            this.scroll_pan.setBackground(Color.red);
            this.add(scroll_pan, BorderLayout.CENTER);
        } 
        catch (SQLException ex) {
            Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     
    /**
     * class ActionListener qui récupère les params de la requête
     */
    class QueryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            ArrayList<String> field_query = new ArrayList<>();
            for(int i = 0; i < check_box.size(); i++)
            {
                if(check_box.get(i).isSelected())
                {
                  field_query.add(check_box.get(i).getActionCommand());   
                }
            }
            
            QueryCtrl qc = new QueryCtrl(conex,table, field_query);
           //result.setText(qc.result()); 
           for(int i = 0; i < qc.result().size(); i++)
           {
                System.out.println(qc.result().get(i));
           }
        }
        
    }
    
}
