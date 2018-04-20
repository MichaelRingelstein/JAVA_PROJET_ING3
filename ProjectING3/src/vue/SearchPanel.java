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
import java.awt.GridLayout;
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
import javax.swing.JTextField;
import modele.Classes.Docteur;
import modele.Classes.PairString;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class SearchPanel extends JPanel{
    
    private JComboBox type_employe;
    private ArrayList<TextField_labelelised> text_fields;
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
    public void pre_requetes()
    {
        //cette requete sélectionne les champs d'un docteur, on laisse la possibilité de rajouter des conditions à la fin  
        this.conex.ajouterRequete("SELECT ? FROM docteur AS d, employe AS e WHERE d.numero = e.numero AND 1");
        
        //cette requete sélectionne les champs d'un infirmier, on laisse la possibilité de rajouter des conditions à la fin
        this.conex.ajouterRequete("SELECT * FROM employe AS e, infirmier AS i WHERE e.numero = i.numero AND ?");
        
        //cett requete sélectionne les champs des où le docteur est le chef du service, on laisse une condition à la fin  
        this.conex.ajouterRequete("SELECT * FROM service AS s, docteur AS d, employe AS e WHERE s.directeur = d.numero AND d.numero = e.numero AND ? ");
        
        
    }
    
    /**
     * Constructeur personnalisé avec en parametres des type pour créer une combo box
     * @param type 
     */
    public SearchPanel(String type, Connexion con)
    {
     
        this.checkType(type);
        this.conex = con;
        this.pre_requetes();
        this.check_box = new ArrayList<>();
        this.text_fields = new ArrayList<>();
        this.recherche_panel = new JPanel();
        this.recherche_button = new JButton("Rechercher");
        this.recherche_button.addActionListener(new QueryListener());
        this.recherche_panel.setLayout(new GridLayout(15, 1));
        this.form();
        this.setLayout(new BorderLayout());
        this.add(recherche_panel, BorderLayout.NORTH);
        result = new JLabel();
        this.scroll_pan = new JScrollPane(result);
        this.add(scroll_pan, BorderLayout.CENTER);
        
        
    }
    
    
    private void checkType(String type)
    {
        if(type == "docteur")
        {
            this.table = "employe, docteur";
        }
        else if(type == "infirmier")
        {
            this.table = "employe, infirmier";
        }
        else if(type == "service")
        {
            this.table = "service, docteur, employe";
        }
        else
        {
            this.table = type;
        }
    }

    private void form() {
        
        
        //création des text fields
        try {
            ArrayList<String> l = conex.remplirChampsTable(table);
            for(int i = 0; i < l.size(); i++)
            {
              text_fields.add(new TextField_labelelised(l.get(i), 15));
              this.recherche_panel.add(this.text_fields.get(i).getLabel());
              this.recherche_panel.add(text_fields.get(i));
            }
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //création des checkboxes
        try {
            ArrayList<String> l = conex.remplirChampsTable(table);
            for(int i = 0; i < l.size(); i++)
            {
              check_box.add(new JCheckBox(l.get(i)));
              this.recherche_panel.add(check_box.get(i));
            }
           
        } 
        catch (SQLException ex) {
            Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.recherche_panel.add(recherche_button);
        
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
            ArrayList<PairString> where_query = new ArrayList<>();
            for(int i = 0; i < text_fields.size(); i++)
            {
                if(!text_fields.get(i).getText().isEmpty())
                {
                    
                    where_query.add(new PairString(text_fields.get(i).getLabelText(), text_fields.get(i).getText(), "s"));
                }
            }
            
            QueryCtrl qc = new QueryCtrl(conex,table, field_query, where_query);
            result.setText(qc.result(field_query));
          
        }
        
    }
    
}
