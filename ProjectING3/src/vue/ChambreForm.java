/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.ChambreDAO;
import controleur.MaladeDAO;
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
import modele.Classes.Malade;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class ChambreForm extends JPanel{
    
    Chambre ch;
    ChambreDAO ch_dao;
    
    JTextField code_service_f;
    JTextField n_chambre_f;
    JTextField surveillant_f;
    JTextField n_lit_f;
    JTextField[][] cells;
    int i;
    
    public ChambreForm(int i, Connexion con) throws SQLException
    {
       
       
       //on implemente le gestionnaire d'employe
        ch_dao = new ChambreDAO(con);
        
        
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
        
        ArrayList<Chambre> tab_doc = ch_dao.getAllCham();
         this.cells = new JTextField[tab_doc.size()][4];
         JPanel p =  new JPanel();
         //p.setSize(800, 900);
         p.setLayout(new GridLayout(tab_doc.size(), 4));
        for(i = 0; i < tab_doc.size(); i++)
        {
            cells[i][0] = new JTextField(tab_doc.get(i).getService());
            cells[i][0].setEditable(false);
            p.add(cells[i][0]);
            cells[i][1] = new JTextField(String.valueOf(tab_doc.get(i).getNChambre()));
             p.add(cells[i][1]); 
             cells[i][2] = new JTextField(String.valueOf(tab_doc.get(i).getSurveillant()));
             p.add(cells[i][2]);
             cells[i][3] = new JTextField(String.valueOf(tab_doc.get(i).getNLit()));
             p.add(cells[i][3]);
             
             MyButton modifier = new MyButton("Modifier", i);
             p.add(modifier);
             modifier.addActionListener(new UpdateListener());
        }
        this.add(p);
    }
    
    
    
    
    
    private void formDelete() throws SQLException {
        
         ArrayList<Chambre> tab_doc = ch_dao.getAllCham();
         this.cells = new JTextField[tab_doc.size()][4];
         JPanel p =  new JPanel();
         //p.setSize(800, 900);
         p.setLayout(new GridLayout(tab_doc.size(), 4));
        for(i = 0; i < tab_doc.size(); i++)
        {
            cells[i][0] = new JTextField(tab_doc.get(i).getService());
            p.add(cells[i][0]);
            cells[i][1] = new JTextField(String.valueOf(tab_doc.get(i).getNChambre()));
             p.add(cells[i][1]); 
             cells[i][2] = new JTextField(String.valueOf(tab_doc.get(i).getSurveillant()));
             p.add(cells[i][2]);
             cells[i][3] = new JTextField(String.valueOf(tab_doc.get(i).getNLit()));
             p.add(cells[i][3]);
             
             MyButton modifier = new MyButton("Supprimer", i);
             p.add(modifier);
             modifier.addActionListener(new DeleteListener());
        }
        this.add(p);
    }
    
    
    private void formAdd() {
        
        JPanel p =  new JPanel();
        JLabel label_code_service = new JLabel("code_service_chambre");
        this.code_service_f = new JTextField();
        JLabel label_numero = new JLabel("numero_chambre");
        this.n_chambre_f = new JTextField();
        JLabel label_surveillant = new JLabel("numero_surveillant");
        this.surveillant_f = new JTextField();
        JLabel label_n_lit = new JLabel("nombre de lits");
        this.n_lit_f = new JTextField();
        JButton valider = new JButton("Valider");
        valider.addActionListener(new AddListener());
        p.setLayout(new GridLayout(7,1));
        p.add(label_code_service);
        p.add(code_service_f);
        p.add(label_numero);
        p.add(n_chambre_f);
        p.add(label_surveillant);
        p.add(surveillant_f);
        p.add(label_n_lit);
        p.add(n_lit_f);
        p.add(valider);
        this.add(p);
    }

    private void formSearch() {
        JPanel p =  new JPanel();
        JLabel label_code_service = new JLabel("code_service_chambre");
        this.code_service_f = new JTextField(3);
        JLabel label_numero = new JLabel("numero_chambre");
        this.n_chambre_f = new JTextField(5);
        JLabel label_surveillant = new JLabel("numero_surveillant");
        this.surveillant_f = new JTextField(5);
        JLabel label_n_lit = new JLabel("nombre de lits");
        this.n_lit_f = new JTextField(5);
        JButton valider = new JButton("Valider");
        valider.addActionListener(new AddListener());
        p.setLayout(new GridLayout(6,1));
        p.add(label_code_service);
        p.add(code_service_f);
        p.add(label_numero);
        p.add(n_chambre_f);
        p.add(label_surveillant);
        p.add(surveillant_f);
        p.add(label_n_lit);
        p.add(n_lit_f);
        p.add(valider);
        this.add(p);
    }

    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //on récupère les informations du formulaire
            String code_service = code_service_f.getText();
            int n_chambre = Integer.parseInt(n_chambre_f.getText());
            int surveillant = Integer.parseInt(surveillant_f.getText());
            int n_lit = Integer.parseInt(n_lit_f.getText());
            
            //à partir des informations, on créé un objet
            ch = new Chambre(code_service, n_chambre, surveillant, n_lit);
            if(ch_dao.add_chambre(ch))
            {
                System.out.println("Chambre ajoutée avec succes");
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
            
            //on récupère les informations du formulaire
            MyButton mb = (MyButton)e.getSource();
            String code_service = cells[mb.getId()][0].getText();
            int n_chambre = Integer.parseInt(cells[mb.getId()][1].getText());
            int surveillant = Integer.parseInt(cells[mb.getId()][2].getText());
            int n_lit = Integer.parseInt(cells[mb.getId()][3].getText());
            
            //à partir des informations, on créé un objet
            ch = new Chambre(code_service, n_chambre, surveillant, n_lit);
            if(ch_dao.update_chambre(ch))
            {
                System.out.println("Chambre modifiée avec succes");
            }
            else
            {
                System.out.print("Erreur dans la modification de la Chambre ");
            }
            
            
        }
        
    }
     
     class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //on récupère les informations du formulaire
            MyButton mb = (MyButton)e.getSource();
            String code_service = cells[mb.getId()][0].getText();
            int n_chambre = Integer.parseInt(cells[mb.getId()][1].getText());
            int surveillant = Integer.parseInt(cells[mb.getId()][2].getText());
            int n_lit = Integer.parseInt(cells[mb.getId()][3].getText());
            
            //à partir des informations, on créé un objet
            ch = new Chambre(code_service, n_chambre, surveillant, n_lit);
            if(ch_dao.delete_chambre(ch))
            {
                System.out.println("Chambre supprimé avec succes");
            }
            else
            {
                System.out.print("Erreur dans la suppression de la Chambre ");
            }
            
            
        }
        
    }
    
    
}
