/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.EmployeDAO;
import controleur.MaladeDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.Classes.Employe;
import modele.Classes.Malade;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class MaladeForm extends JPanel{
    
    //notre employe dans lequel on va stocker les variables du formulaire
    Malade mal;
    MaladeDAO mal_dao;
    
    
    //les éléments du formulaire
    JTextField numero_f;
    JTextField nom_f;
    JTextField prenom_f;
    JTextField adresse_f;
    JTextField tel_f;
    JTextField mutuelle_f;
    
    
    public MaladeForm()
    {
       Dimension dim = new Dimension(500, 800);
       this.setPreferredSize(dim);
       this.setBackground(Color.red);
    }
    
    
    public MaladeForm(boolean update, Connexion con)
    {
        
        //on implemente le gestionnaire d'employe
        mal_dao = new MaladeDAO(con);
        
        
        //si on demande le formulaire de mise à jour
        if(update == true)
        {
            this.formUpdate();
        }
        //sinon on utilise le formulaire de recherche
        else
        {
            this.formSearch();
        }
        
    }

    private void formUpdate() {
        
        JPanel p =  new JPanel();
        JLabel label_numero = new JLabel("numero_patient");
        this.numero_f = new JTextField(10);
        JLabel label_nom = new JLabel("nom_patient");
        this.nom_f = new JTextField(10);
        JLabel label_prenom = new JLabel("prenom_patient");
        this.prenom_f = new JTextField(10);
        JLabel label_adresse = new JLabel("adresse_patient");
        this.adresse_f = new JTextField(15);
        JLabel label_tel = new JLabel("telephone_patient");
        this.tel_f = new JTextField(10);
        JLabel label_mutuelle = new JLabel("mutuelle_patient");
        this.mutuelle_f = new JTextField(10);
        JButton valider = new JButton("Valider");
        valider.addActionListener(new UpdateListener());
        p.setLayout(new GridLayout(9,1));
        p.add(label_numero);
        p.add(numero_f);
        p.add(label_nom);
        p.add(nom_f);
        p.add(label_prenom);
        p.add(prenom_f);
        p.add(label_adresse);
        p.add(adresse_f);
        p.add(label_tel);
        p.add(tel_f);
        p.add(label_mutuelle);
        p.add(mutuelle_f);
        p.add(valider);
        this.add(p);
    }

    private void formSearch() {
        JPanel p =  new JPanel();
        JLabel label_numero = new JLabel("numero_employe");
        this.numero_f = new JTextField(10);
        JLabel label_nom = new JLabel("nom_employe");
        this.nom_f = new JTextField(10);
        JLabel label_prenom = new JLabel("prenom_employe");
        this.prenom_f = new JTextField(10);
        JLabel label_adresse = new JLabel("adresse_employe");
        this.adresse_f = new JTextField(15);
        JLabel label_tel = new JLabel("telephone_employe");
        this.tel_f = new JTextField(10);
        JLabel label_mutuelle = new JLabel("mutuelle_patient");
        this.mutuelle_f = new JTextField(10);
        JButton valider = new JButton("Valider");
        valider.addActionListener(new UpdateListener());
        p.setLayout(new GridLayout(6,1));
        p.add(label_numero);
        p.add(numero_f);
        p.add(label_nom);
        p.add(nom_f);
        p.add(label_prenom);
        p.add(prenom_f);
        p.add(label_adresse);
        p.add(adresse_f);
        p.add(label_tel);
        p.add(tel_f);
        p.add(label_mutuelle);
        p.add(mutuelle_f);
        p.add(valider);
        this.add(p);
    }

    class UpdateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //on récupère les informations du formulaire
            int num = Integer.parseInt(numero_f.getText());
            String nom = nom_f.getText();
            String prenom = prenom_f.getText();
            String adresse = adresse_f.getText();
            String tel = tel_f.getText();
            String mutuelle = mutuelle_f.getText();
            
            //à partir des informations, on créé un objet
            mal = new Malade(num, nom, prenom, tel, adresse, mutuelle);
            if(mal_dao.add_malade(mal))
            {
                System.out.println("Patient ajouté avec succes");
            }
            else
            {
                System.out.print("Erreur dans l'ajout du patient ");
            }
            
            
        }
        
    }
    
    
}
