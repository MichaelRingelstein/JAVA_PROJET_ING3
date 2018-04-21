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
    
    
    public ServiceForm(boolean update, Connexion con)
    {
       Dimension dim = new Dimension(500, 600);
       this.setPreferredSize(dim);
       
       //on implemente le gestionnaire d'employe
        s_dao = new ServiceDAO(con);
        
        
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

    class UpdateListener implements ActionListener{

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
    
    
}
