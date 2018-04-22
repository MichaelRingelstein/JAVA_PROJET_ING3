/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.ChambreDAO;
import controleur.ServiceDAO;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.Classes.Chambre;
import modele.Classes.Service;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class ServiceForm extends JPanel{
    
    Service s;
    ServiceDAO s_dao;
    
    JTextField code_f;
    JTextField nom_f;
    JTextField batiment_f;
    JTextField directeurId_f;
     JTextField[][] cells;
    
    
    public ServiceForm(int i, Connexion con) throws SQLException
    {
       
       //on implemente le gestionnaire d'employe
        s_dao = new ServiceDAO(con);
        
        
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
        
        ArrayList<Service> tab_doc = s_dao.getAllService();
         cells = new JTextField[tab_doc.size()][4];
         JPanel p =  new JPanel();
         //p.setSize(800, 900);
         p.setLayout(new GridLayout(tab_doc.size(), 4));
        for(int i = 0; i < tab_doc.size(); i++)
        {
            cells[i][0] = new JTextField(tab_doc.get(i).getCode());
            cells[i][0].setEditable(false);
            p.add(cells[i][0]);
            cells[i][1] = new JTextField(tab_doc.get(i).getNom());
             
             p.add(cells[i][1]); 
             cells[i][2] = new JTextField(String.valueOf(tab_doc.get(i).getBatiment()));
              
             p.add(cells[i][2]);
             cells[i][3] = new JTextField(String.valueOf(tab_doc.get(i).getDirecteurId()));
              
             p.add(cells[i][3]);
             
             MyButton modifier = new MyButton("Modifier", i);
             p.add(modifier);
             modifier.addActionListener(new UpdateListener());
        }
        this.add(p);
    }
    
    
    
     private void formDelete() throws SQLException {
        
         ArrayList<Service> tab_doc = s_dao.getAllService();
         cells = new JTextField[tab_doc.size()][4];
         JPanel p =  new JPanel();
         //p.setSize(800, 900);
         p.setLayout(new GridLayout(tab_doc.size(), 4));
        for(int i = 0; i < tab_doc.size(); i++)
        {
            cells[i][0] = new JTextField(tab_doc.get(i).getCode());
            cells[i][0].setEditable(false);
            p.add(cells[i][0]);
            cells[i][1] = new JTextField(tab_doc.get(i).getNom());
             
             p.add(cells[i][1]); 
             cells[i][2] = new JTextField(String.valueOf(tab_doc.get(i).getBatiment()));
              
             p.add(cells[i][2]);
             cells[i][3] = new JTextField(String.valueOf(tab_doc.get(i).getDirecteurId()));
              
             p.add(cells[i][3]);
             
             MyButton modifier = new MyButton("Supprimer", i);
             p.add(modifier);
             modifier.addActionListener(new DeleteListener());
        }
        this.add(p);
    }
    
    
     private void formAdd() {
        
        JPanel p =  new JPanel();
        JLabel label_code_service = new JLabel("code_service");
        this.code_f = new JTextField(3);
        JLabel label_nom = new JLabel("nom_service");
        this.nom_f = new JTextField(15);
        JLabel label_batiment = new JLabel("batiment_service");
        this.batiment_f = new JTextField(5);
        JLabel label_directeur = new JLabel("directeur _service");
        this.directeurId_f = new JTextField(5);
        JButton valider = new JButton("Valider");
        valider.addActionListener(new AddListener());
        p.setLayout(new GridLayout(6,1));
        p.add(label_code_service);
        p.add(code_f);
        p.add(label_nom);
        p.add(nom_f);
        p.add(label_batiment);
        p.add(batiment_f);
        p.add(label_directeur);
        p.add(directeurId_f);
        p.add(valider);
        this.add(p);
    }

    private void formSearch() {
        JPanel p =  new JPanel();
        JLabel label_code_service = new JLabel("code_service");
        this.code_f = new JTextField(3);
        JLabel label_nom = new JLabel("nom_service");
        this.nom_f = new JTextField(15);
        JLabel label_batiment = new JLabel("batiment_service");
        this.batiment_f = new JTextField(5);
        JLabel label_directeur = new JLabel("directeur _service");
        this.directeurId_f = new JTextField(5);
        JButton valider = new JButton("Valider");
        valider.addActionListener(new UpdateListener());
        p.setLayout(new GridLayout(6,1));
        p.add(label_code_service);
        p.add(code_f);
        p.add(label_nom);
        p.add(nom_f);
        p.add(label_batiment);
        p.add(batiment_f);
        p.add(label_directeur);
        p.add(directeurId_f);
        p.add(valider);
        this.add(p);
    }

    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //on récupère les informations du formulaire
            String code_service = code_f.getText();
            String nom = nom_f.getText();
            char batiment = batiment_f.getText().charAt(0);
            int directeurId = Integer.parseInt(directeurId_f.getText());
            
            //à partir des informations, on créé un objet
            s = new Service(code_service, nom, batiment, directeurId);
            if(s_dao.add_service(s))
            {
                System.out.println("Chambre ajouté avec succes");
            }
            else
            {
                System.out.print("Erreur dans l'ajout de la Chambre ");
            }
            
            
        }
        
    }
    
    
     class UpdateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            MyButton mb = (MyButton)e.getSource();
            //on récupère les informations du formulaire
            String code_service = cells[mb.getId()][0].getText();
            String nom = cells[mb.getId()][1].getText();
            char batiment = cells[mb.getId()][2].getText().charAt(0);
            int directeurId = Integer.parseInt(cells[mb.getId()][3].getText());
            
            //à partir des informations, on créé un objet
            s = new Service(code_service, nom, batiment, directeurId);
            if(s_dao.update_service(s))
            {
                System.out.println("Service modifié avec succes");
            }
            else
            {
                System.out.print("Erreur dans la modification du service ");
            }
            
            
        }
        
    }
     
     
     class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            MyButton mb = (MyButton)e.getSource();
            //on récupère les informations du formulaire
            String code_service = cells[mb.getId()][0].getText();
            String nom = cells[mb.getId()][1].getText();
            char batiment = cells[mb.getId()][2].getText().charAt(0);
            int directeurId = Integer.parseInt(cells[mb.getId()][3].getText());
            
            //à partir des informations, on créé un objet
            s = new Service(code_service, nom, batiment, directeurId);
            if(s_dao.delete_service(s))
            {
                System.out.println("Service supprimé avec succes");
            }
            else
            {
                System.out.print("Erreur dans la suppression du service ");
            }
            
            
        }
        
    }
    
    
}
