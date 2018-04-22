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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.Classes.Employe;
import modele.Classes.Infirmier;
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
    JComboBox lit_f;
    JComboBox doc_f;
    JTextField[][] cells;
    
    
    public MaladeForm()
    {
      
    }
    
    
    public MaladeForm(int i, Connexion con) throws SQLException
    {
        
        //on implemente le gestionnaire d'employe
        mal_dao = new MaladeDAO(con);
        
        
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

    private void formUpdate() throws SQLException {
        
        ArrayList<Malade> tab_doc = mal_dao.getAllMal();
         cells = new JTextField[tab_doc.size()][6];
         JPanel p =  new JPanel();
         //p.setSize(800, 900);
         p.setLayout(new GridLayout(tab_doc.size(), 5));
        for(int i = 0; i < tab_doc.size(); i++)
        {
            cells[i][0] = new JTextField(String.valueOf(tab_doc.get(i).getId()));
            cells[i][1] = new JTextField(tab_doc.get(i).getNom());
            p.add(cells[i][1]);
            cells[i][2] = new JTextField(tab_doc.get(i).getPrenom());
             p.add(cells[i][2]); 
             cells[i][3] = new JTextField(tab_doc.get(i).getAdresse());
             p.add(cells[i][3]);
             cells[i][4] = new JTextField(tab_doc.get(i).getTel());
             p.add(cells[i][4]);
             cells[i][5] = new JTextField(tab_doc.get(i).getMutuelle());
             p.add(cells[i][5]);
             
             MyButton modifier = new MyButton("Modifier", i);
             p.add(modifier);
             modifier.addActionListener(new UpdateListener());
        }
        this.add(p);
    }
    
    
    
    private void formDelete() throws SQLException {
        
        ArrayList<Malade> tab_doc = mal_dao.getAllMal();
         cells = new JTextField[tab_doc.size()][6];
         JPanel p =  new JPanel();
         //p.setSize(800, 900);
         p.setLayout(new GridLayout(tab_doc.size(), 6));
        for(int i = 0; i < tab_doc.size(); i++)
        {
            cells[i][0] = new JTextField(String.valueOf(tab_doc.get(i).getId()));
            cells[i][1] = new JTextField(tab_doc.get(i).getNom());
            p.add(cells[i][1]);
            cells[i][2] = new JTextField(tab_doc.get(i).getPrenom());
             p.add(cells[i][2]); 
             cells[i][3] = new JTextField(tab_doc.get(i).getAdresse());
             p.add(cells[i][3]);
             cells[i][4] = new JTextField(tab_doc.get(i).getTel());
             p.add(cells[i][4]);
             cells[i][5] = new JTextField(tab_doc.get(i).getMutuelle());
             p.add(cells[i][5]);
             
             MyButton modifier = new MyButton("Supprimer", i);
             p.add(modifier);
             modifier.addActionListener(new DeleteListener());
        }
        this.add(p);
    }
    
    private void formAdd() throws SQLException {
        
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
        valider.addActionListener(new AddListener());
        p.setLayout(new GridLayout(12,1));
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
        
        //liste des lits de libre 
        JLabel label_lit = new JLabel("lits libres");
        String[] lits = new String[mal_dao.getChambresLibres().size()]; 
        for(int i = 0; i < mal_dao.getChambresLibres().size(); i++)
        {
            lits[i] = mal_dao.getChambresLibres().get(i);
        }
        this.lit_f = new JComboBox(lits);
        
        p.add(label_lit);
        p.add(lit_f);
        
        
        
        //liste des médecins 
        JLabel label_doc = new JLabel("docteurs");
        String[] docs = new String[mal_dao.getDocs().size()]; 
        for(int i = 0; i < mal_dao.getDocs().size(); i++)
        {
            docs[i] = mal_dao.getDocs().get(i);
        }
        this.doc_f = new JComboBox(docs);
        
        p.add(label_doc);
        p.add(doc_f);
        
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

    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //on récupère les informations du formulaire
            int num = Integer.parseInt(numero_f.getText());
            String nom = nom_f.getText();
            String prenom = prenom_f.getText();
            String adresse = adresse_f.getText();
            String tel = tel_f.getText();
            String mutuelle = mutuelle_f.getText();
            String chambre = lit_f.getSelectedItem().toString();
            String doc = doc_f.getSelectedItem().toString();
            
            //à partir des informations, on créé un objet
            mal = new Malade(num, nom, prenom, tel, adresse, mutuelle);
            if(mal_dao.add_malade(mal, chambre, doc))
            {
                    System.out.println("Patient ajouté avec succes");
            }
            else
            {
                System.out.print("Erreur dans l'ajout du patient ");
            }
            
            
        }
        
    }
    
    
    class UpdateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //on récupère les informations du formulaire
            MyButton mb = (MyButton)e.getSource();
            int num = Integer.parseInt(cells[mb.getId()][0].getText());
            String nom = cells[mb.getId()][1].getText();
            String prenom = cells[mb.getId()][2].getText();
            String adresse = cells[mb.getId()][3].getText();
            String tel = cells[mb.getId()][4].getText();
            String mutuelle = cells[mb.getId()][5].getText();
            
            //à partir des informations, on créé un objet
            mal = new Malade(num, nom, prenom, tel, adresse, mutuelle);
            if(mal_dao.update_malade(mal))
            {
                System.out.println("Patient modifié avec succes");
            }
            else
            {
                System.out.print("Erreur dans la modification du patient ");
            }
            
            
        }
        
    }
    
    class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //on récupère les informations du formulaire
            MyButton mb = (MyButton)e.getSource();
            int num = Integer.parseInt(cells[mb.getId()][0].getText());
            String nom = cells[mb.getId()][1].getText();
            String prenom = cells[mb.getId()][2].getText();
            String adresse = cells[mb.getId()][3].getText();
            String tel = cells[mb.getId()][4].getText();
            String mutuelle = cells[mb.getId()][5].getText();
            
            //à partir des informations, on créé un objet
            mal = new Malade(num, nom, prenom, tel, adresse, mutuelle);
            if(mal_dao.delete_malade(mal))
            {
                System.out.println("Patient supprimé avec succes");
            }
            else
            {
                System.out.print("Erreur dans la suppression du patient ");
            }
            
            
        }
        
    }
    
    
}
