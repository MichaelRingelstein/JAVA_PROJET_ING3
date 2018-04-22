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
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
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
    JTextField[][] cells;
    
    public DocteurForm(int i, Connexion con) throws SQLException {
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
    
    
    
    private void formUpdate() throws SQLException {
        
        ArrayList<Docteur> tab_doc = doc_dao.getAllDoc();
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
            cells[i][3] = new JTextField(tab_doc.get(i).getSpecialite());
            cells[i][3].setEditable(false);
             p.add(cells[i][3]);
            cells[i][4] = new JTextField(tab_doc.get(i).getAdresse());
             p.add(cells[i][4]);
            cells[i][5] = new JTextField(tab_doc.get(i).getTel()); 
             p.add(cells[i][5]);
             
            MyButton modifier = new MyButton("Modifier", i);
            p.add(modifier);
            modifier.addActionListener(new UpdateListener());
        }
        this.add(p);
    }

    
    
    private void formDelete() throws SQLException {
        
        ArrayList<Docteur> tab_doc = doc_dao.getAllDoc();
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
            cells[i][3] = new JTextField(tab_doc.get(i).getSpecialite());
             p.add(cells[i][3]);
            cells[i][4] = new JTextField(tab_doc.get(i).getAdresse());
             p.add(cells[i][4]);
            cells[i][5] = new JTextField(tab_doc.get(i).getTel()); 
             p.add(cells[i][5]);
             
            MyButton modifier = new MyButton("Supprimer", i);
            p.add(modifier);
            modifier.addActionListener(new DeleteListener());
        }    
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
        valider.addActionListener(new AddListener());
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
    
    
    class AddListener implements ActionListener{

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
            String specialite = (String)cells[mb.getId()][5].getText();
            
            //à partir des informations, on créé un objet
            doc = new Docteur(num, nom, prenom, tel, adresse, specialite);
            if(doc_dao.update_docteur(doc))
            {
                System.out.println("Docteur modifié avec succes");
            }
            else
            {
                System.out.print("Erreur dans la modification du docteur ");
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
            String specialite = (String)cells[mb.getId()][5].getText();
            
            //à partir des informations, on créé un objet
            doc = new Docteur(num, nom, prenom, tel, adresse, specialite);
            if(doc_dao.delete_docteur(doc))
            {
                System.out.println("Docteur supprimé avec succes");
            }
            else
            {
                System.out.print("Erreur dans la suppression du docteur ");
            }
            
            
        }
        
    }
    
    
    
    
}
