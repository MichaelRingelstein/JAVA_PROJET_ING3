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
import java.sql.SQLException;
import java.util.ArrayList;
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
    JTextField[][] cells;
    
    public InfirmierForm(int i, Connexion con) throws SQLException {
        super();
        //on implemente le gestionnaire de docteur
        inf_dao = new InfirmierDAO(con);
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
        
        ArrayList<Infirmier> tab_doc = inf_dao.getAllInf();
         cells = new JTextField[tab_doc.size()][8];
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
            cells[i][6] = new JTextField(tab_doc.get(i).getCodeService());
             p.add(cells[i][6]);
            cells[i][5] = new JTextField(tab_doc.get(i).getRotation());
             p.add(cells[i][5]);
            cells[i][7] = new JTextField(String.valueOf(tab_doc.get(i).getSalaire())); 
             p.add(cells[i][7]);
             cells[i][3] = new JTextField(tab_doc.get(i).getAdresse());
             p.add(cells[i][3]);
             cells[i][4] = new JTextField(tab_doc.get(i).getTel());
             p.add(cells[i][4]);
             
            MyButton modifier = new MyButton("Modifier", i);
            p.add(modifier);
            modifier.addActionListener(new UpdateListener());
        }
        this.add(p);
    }

    
    
    
    private void formDelete() throws SQLException {
        
       ArrayList<Infirmier> tab_doc = inf_dao.getAllInf();
         cells = new JTextField[tab_doc.size()][8];
         JPanel p =  new JPanel();
         //p.setSize(800, 900);
         p.setLayout(new GridLayout(tab_doc.size(), 8));
        for(int i = 0; i < tab_doc.size(); i++)
        {
            cells[i][0] = new JTextField(String.valueOf(tab_doc.get(i).getId()));
            cells[i][1] = new JTextField(tab_doc.get(i).getNom());
            p.add(cells[i][1]);
            cells[i][2] = new JTextField(tab_doc.get(i).getPrenom());
             p.add(cells[i][2]);
            cells[i][6] = new JTextField(tab_doc.get(i).getCodeService());
             p.add(cells[i][6]);
            cells[i][5] = new JTextField(tab_doc.get(i).getRotation());
             p.add(cells[i][5]);
            cells[i][7] = new JTextField(String.valueOf(tab_doc.get(i).getSalaire())); 
             p.add(cells[i][7]);
             cells[i][3] = new JTextField(tab_doc.get(i).getAdresse());
             p.add(cells[i][3]);
             cells[i][4] = new JTextField(tab_doc.get(i).getTel());
             p.add(cells[i][4]);
             
            MyButton modifier = new MyButton("Supprimer", i);
            p.add(modifier);
            modifier.addActionListener(new DeleteListener());
        }
        this.add(p);
    }
    
    
    
    private void formAdd() {
        
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
        valider.addActionListener(new AddListener());
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
    
    
    class AddListener implements ActionListener{

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
                System.out.println("Infirmier ajouté avec succes");
            }
            else
            {
                System.out.print("Erreur dans l'ajout de l'infirmier ");
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
            String rotation = (String)cells[mb.getId()][5].getText();
            String code_service = cells[mb.getId()][6].getText();
            double salaire = Double.parseDouble(cells[mb.getId()][7].getText());
            
            //à partir des informations, on créé un objet
            inf = new Infirmier(num, nom, prenom, tel, adresse, code_service, rotation, salaire);
            if(inf_dao.update_infirmier(inf))
            {
                System.out.println("Infirmier modifié avec succes");
            }
            else
            {
                System.out.print("Erreur dans la modification de l'infirmier ");
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
            String rotation = (String)cells[mb.getId()][5].getText();
            String code_service = cells[mb.getId()][6].getText();
            double salaire = Double.parseDouble(cells[mb.getId()][7].getText());
            
            //à partir des informations, on créé un objet
            inf = new Infirmier(num, nom, prenom, tel, adresse, code_service, rotation, salaire);
            if(inf_dao.delete_infirmier(inf))
            {
                System.out.println("Infirmier supprimé avec succes");
            }
            else
            {
                System.out.print("Erreur dans la suppression de l'infirmier ");
            }
            
            
        }
        
    }
    
    
}
