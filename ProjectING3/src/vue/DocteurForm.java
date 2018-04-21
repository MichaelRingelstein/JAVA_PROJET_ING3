/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.DocteurDAO;
import controleur.EmployeDAO;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.Classes.Docteur;
import modele.Classes.Employe;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class DocteurForm extends EmployeForm{
    
    JComboBox specialite_f;
    DocteurDAO doc_dao;
    Docteur doc;
    
    public DocteurForm(int i, Connexion con) {
        super();
        //on implemente le gestionnaire de docteur
        doc_dao = new DocteurDAO(con);
        //si on demande le formulaire de mise à jour
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
        JLabel label_numero = new JLabel("numero_docteur");
        this.numero_f = new JTextField(10);
        JLabel label_nom = new JLabel("nom_docteur");
        this.nom_f = new JTextField(10);
        JLabel label_prenom = new JLabel("prenom_docteur");
        this.prenom_f = new JTextField(10);
        JLabel label_specialite = new JLabel("specialite_docteur");
        String[] tab = {"Traumatologue","Pneumologue","Cardiologue","Orthopediste", "Radiologue","Anesthesiste"};
        this.specialite_f = new JComboBox(tab);
        JLabel label_adresse = new JLabel("adresse_docteur");
        this.adresse_f = new JTextField(15);
        JLabel label_tel = new JLabel("telephone_docteur");
        this.tel_f = new JTextField(10);
        JButton valider = new JButton("Valider");
        valider.addActionListener(new UpdateListener());
        p.setLayout(new GridLayout(7,1));
        p.add(label_numero);
        p.add(numero_f);
        p.add(label_nom);
        p.add(nom_f);
        p.add(label_prenom);
        p.add(prenom_f);
        p.add(label_specialite);
        p.add(specialite_f);
        p.add(label_adresse);
        p.add(adresse_f);
        p.add(label_tel);
        p.add(tel_f);
        p.add(valider);
        this.add(p);
    }

    
    
    private void formDelete() {
        
        JPanel p =  new JPanel();
        JLabel label_numero = new JLabel("numero_docteur");
        this.numero_f = new JTextField(10);
        JLabel label_nom = new JLabel("nom_docteur");
        this.nom_f = new JTextField(10);
        JLabel label_prenom = new JLabel("prenom_docteur");
        this.prenom_f = new JTextField(10);
        JLabel label_specialite = new JLabel("specialite_docteur");
        String[] tab = {"Traumatologue","Pneumologue","Cardiologue","Orthopediste", "Radiologue","Anesthesiste"};
        this.specialite_f = new JComboBox(tab);
        JLabel label_adresse = new JLabel("adresse_docteur");
        this.adresse_f = new JTextField(15);
        JLabel label_tel = new JLabel("telephone_docteur");
        this.tel_f = new JTextField(10);
        JButton valider = new JButton("Valider");
        valider.addActionListener(new UpdateListener());
        p.setLayout(new GridLayout(7,1));
        p.add(label_numero);
        p.add(numero_f);
        p.add(label_nom);
        p.add(nom_f);
        p.add(label_prenom);
        p.add(prenom_f);
        p.add(label_specialite);
        p.add(specialite_f);
        p.add(label_adresse);
        p.add(adresse_f);
        p.add(label_tel);
        p.add(tel_f);
        p.add(valider);
        this.add(p);
    }
    
    
    
    
    private void formAdd() {
        
        JPanel p =  new JPanel();
        JLabel label_numero = new JLabel("numero_docteur");
        this.numero_f = new JTextField(10);
        JLabel label_nom = new JLabel("nom_docteur");
        this.nom_f = new JTextField(10);
        JLabel label_prenom = new JLabel("prenom_docteur");
        this.prenom_f = new JTextField(10);
        JLabel label_specialite = new JLabel("specialite_docteur");
        String[] tab = {"Traumatologue","Pneumologue","Cardiologue","Orthopediste", "Radiologue","Anesthesiste"};
        this.specialite_f = new JComboBox(tab);
        JLabel label_adresse = new JLabel("adresse_docteur");
        this.adresse_f = new JTextField(15);
        JLabel label_tel = new JLabel("telephone_docteur");
        this.tel_f = new JTextField(10);
        JButton valider = new JButton("Valider");
        valider.addActionListener(new UpdateListener());
        p.setLayout(new GridLayout(7,1));
        p.add(label_numero);
        p.add(numero_f);
        p.add(label_nom);
        p.add(nom_f);
        p.add(label_prenom);
        p.add(prenom_f);
        p.add(label_specialite);
        p.add(specialite_f);
        p.add(label_adresse);
        p.add(adresse_f);
        p.add(label_tel);
        p.add(tel_f);
        p.add(valider);
        this.add(p);
    }

    private void formSearch() {
        JPanel scpan = new JPanel();
        JPanel p =  new JPanel();
        JLabel label_numero = new JLabel("numero_docteur");
        this.numero_f = new JTextField(10);
        JLabel label_nom = new JLabel("nom_docteur");
        this.nom_f = new JTextField(10);
        JLabel label_prenom = new JLabel("prenom_docteur");
        this.prenom_f = new JTextField(10);
        JLabel label_specialite = new JLabel("specialite_docteur");
        String[] tab = {"Traumatologue","Pneumologue","Cardiologue","Orthopediste", "Radiologue","Anesthesiste"};
        this.specialite_f = new JComboBox(tab);
        JLabel label_adresse = new JLabel("adresse_docteur");
        this.adresse_f = new JTextField(15);
        JLabel label_tel = new JLabel("telephone_docteur");
        this.tel_f = new JTextField(10);
        JButton valider = new JButton("Valider");
        valider.addActionListener(new UpdateListener());
        p.setLayout(new GridLayout(6,1));
        p.add(label_numero);
        p.add(numero_f);
        p.add(label_nom);
        p.add(nom_f);
        p.add(label_prenom);
        p.add(prenom_f);
        p.add(label_specialite);
        p.add(specialite_f);
        p.add(label_adresse);
        p.add(adresse_f);
        p.add(label_tel);
        p.add(tel_f);
        scpan.setLayout(new BorderLayout());
        scpan.add(p, BorderLayout.EAST);
        
        
        
        scpan.add(valider);
        this.add(scpan);
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
            String specialite = (String)specialite_f.getSelectedItem();
            
            //à partir des informations, on créé un objet
            doc = new Docteur(num, nom, prenom, tel, adresse, specialite);
            if(doc_dao.add_docteur(doc))
            {
                System.out.println("Docteur ajouté avec succes");
            }
            else
            {
                System.out.print("Erreur dans l'ajout du docteur ");
            }
            
            
        }
        
    }
    
    
    
    
}
