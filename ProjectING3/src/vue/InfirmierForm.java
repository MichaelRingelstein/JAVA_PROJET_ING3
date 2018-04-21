/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.DocteurDAO;
import controleur.InfirmierDAO;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.Classes.Docteur;
import modele.Classes.Infirmier;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class InfirmierForm extends EmployeForm{
    
    JComboBox rotation_f;
    JTextField code_service_f;
    JTextField salaire_f;
    InfirmierDAO inf_dao;
    Infirmier inf;
    
    public InfirmierForm(boolean update, Connexion con) {
        super();
        //on implemente le gestionnaire de docteur
        inf_dao = new InfirmierDAO(con);
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
        JLabel label_numero = new JLabel("numero_infirmier");
        this.numero_f = new JTextField(10);
        JLabel label_nom = new JLabel("nom_infirmier");
        this.nom_f = new JTextField(10);
        JLabel label_prenom = new JLabel("prenom_infirmier");
        this.prenom_f = new JTextField(10);
        JLabel label_service = new JLabel("code_service");
        this.code_service_f = new JTextField(3);
        JLabel label_rotation = new JLabel("rotation_infirmier");
        String[] tab = {"JOUR","NUIT"};
        this.rotation_f = new JComboBox(tab);
        JLabel label_salaire = new JLabel("salaire_infirmier");
        this.salaire_f = new JTextField(5);
        JLabel label_adresse = new JLabel("adresse_infirmier");
        this.adresse_f = new JTextField(15);
        JLabel label_tel = new JLabel("telephone_infirmier");
        this.tel_f = new JTextField(10);
        JButton valider = new JButton("Valider");
        valider.addActionListener(new UpdateListener());
        p.setLayout(new GridLayout(9,1));
        p.add(label_numero);
        p.add(numero_f);
        p.add(label_nom);
        p.add(nom_f);
        p.add(label_prenom);
        p.add(prenom_f);
        p.add(label_service);
        p.add(code_service_f);
        p.add(label_rotation);
        p.add(rotation_f);
        p.add(label_salaire);
        p.add(salaire_f);
        p.add(label_adresse);
        p.add(adresse_f);
        p.add(label_tel);
        p.add(tel_f);
        p.add(valider);
        this.add(p);
    }

    private void formSearch() {
        JPanel p =  new JPanel();
        JLabel label_numero = new JLabel("numero_infirmier");
        this.numero_f = new JTextField(10);
        JLabel label_nom = new JLabel("nom_infirmier");
        this.nom_f = new JTextField(10);
        JLabel label_prenom = new JLabel("prenom_infirmier");
        this.prenom_f = new JTextField(10);
        JLabel label_service = new JLabel("code_service");
        this.code_service_f = new JTextField(3);
        JLabel label_rotation = new JLabel("rotation_infirmier");
        String[] tab = {"JOUR", "NUIT"};
        this.rotation_f = new JComboBox(tab);
        JLabel label_salaire = new JLabel("salaire_infirmier");
        this.salaire_f = new JTextField(5);
        JLabel label_adresse = new JLabel("adresse_infirmier");
        this.adresse_f = new JTextField(15);
        JLabel label_tel = new JLabel("telephone_infirmier");
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
        p.add(label_service);
        p.add(code_service_f);
        p.add(label_rotation);
        p.add(rotation_f);
        p.add(label_salaire);
        p.add(salaire_f);
        p.add(label_adresse);
        p.add(adresse_f);
        p.add(label_tel);
        p.add(tel_f);
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
            String rotation = (String)rotation_f.getSelectedItem();
            String code_service = code_service_f.getText();
            double salaire = Double.parseDouble(salaire_f.getText());
            
            //à partir des informations, on créé un objet
            inf = new Infirmier(num, nom, prenom, tel, adresse, code_service, rotation, salaire);
            if(inf_dao.add_infirmier(inf))
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
