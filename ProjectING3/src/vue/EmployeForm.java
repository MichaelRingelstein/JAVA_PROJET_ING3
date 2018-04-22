/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.EmployeDAO;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.Classes.Employe;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class EmployeForm extends JPanel{
    
    //notre employe dans lequel on va stocker les variables du formulaire
    Employe em;
    EmployeDAO em_dao;
    
    
    //les éléments du formulaire
    JTextField numero_f;
    JTextField nom_f;
    JTextField prenom_f;
    JTextField adresse_f;
    JTextField tel_f;
    
    
    public EmployeForm()
    {
       
    }
    
    
    public EmployeForm(int i, Connexion con)
    {
        
        //on implemente le gestionnaire d'employe
        em_dao = new EmployeDAO(con);
        
         if(i == 1)
        {
            this.formAdd();
        }
        //sinon on utilise le formulaire de recherche
        else if(i == 2)
        {
            this.formSearch();
        }
        else if(i == 3)
        {
            this.formUpdate();
        }
        else if(i== 4)
        {
            this.formDelete();
        }
        else
        {
            this.formAdd();
        }
        
    }

    private void formUpdate() {
        
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
        JButton valider = new JButton("Valider");
        valider.addActionListener(new AddListener());
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
        p.add(valider);
        this.add(p);
    }
    
    
    
    
    private void formDelete() {
        
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
        JButton valider = new JButton("Valider");
        valider.addActionListener(new AddListener());
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
        p.add(valider);
        this.add(p);
    }
    
    
    private void formAdd() {
        
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
        JButton valider = new JButton("Valider");
        valider.addActionListener(new AddListener());
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
        JButton valider = new JButton("Valider");
        valider.addActionListener(new AddListener());
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
        p.add(valider);
        this.add(p);
    }

    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //on récupère les informations du formulaire
            int num = Integer.parseInt(numero_f.getText());
            String nom = nom_f.getText();
            String prenom = prenom_f.getText();
            String adresse = adresse_f.getText();
            String tel = tel_f.getText();
            
            //à partir des informations, on créé un objet
            em = new Employe(num, nom, prenom, tel, adresse);
            if(em_dao.add_employe(em))
            {
                System.out.println("Employé ajouté avec succes");
            }
            else
            {
                System.out.print("Erreur dans l'ajout de l'employé ");
            }
            
            
        }
        
    }
    
    
    
}
