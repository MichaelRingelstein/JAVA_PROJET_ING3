/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableModel;
import modele.*;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import modele.Connexion;
import vue.MainPage;

/**
 *
 * @author micha
 */
public class Reporting extends JPanel{

    
    CardLayout c = new CardLayout();
    JPanel content;
    JPanel card1;
    JPanel card2;
    JPanel card3;
    JLabel stat1;
    JLabel label1;
    JLabel stat2;
    JLabel label2;
    JLabel stat3;
    JButton button1;
    JList list1;
    JList list2;
    //Liste des noms de nos conteneurs pour la pile de cartes
    String[] listContent = {"CARD_1", "CARD_2", "CARD_3"};
    int indice = 0;
    Connexion conn;
    ArrayList<String> specialites;
    ArrayList<String> chambres;
    String temp = "";
    
    
    public Reporting(Connexion conn) throws SQLException
    {
        this.conn = conn;
        content = new JPanel();
        card1 = new JPanel();
        card2 = new JPanel();
        card3 = new JPanel();
        stat1 = new JLabel();
        label1 = new JLabel();
        stat2 = new JLabel();
        label2 = new JLabel();
        stat3 = new JLabel();
        button1 = new JButton();
        specialites = new ArrayList<String>();
        specialites.clear();
        
        
        stat1.setText("Répartition des spécialités des Docteurs");
        button1.setText("Open report");
        // en appuyant dur le bouton, on appelle la fonction permettant la demande à la BDD du nb de docteur par spécialité (pb format d'affichage)
        /*button1.addActionListener((e) -> {
            try {
                this.specialites = this.createReport();
                this.temp = Arrays.toString(this.specialites.toArray());  
                System.out.print(temp);
                label1.setText(temp);
                
            } catch (SQLException ex) {
                Logger.getLogger(Reporting.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
        this.specialites = this.createReport();
        list1 = new JList(this.specialites.toArray(new String[0]));
        JScrollPane scrollpane1 = new JScrollPane(list1);
        
        card1.setLayout(new BorderLayout());
        card1.setBackground(Color.gray);
        card1.add(stat1, BorderLayout.NORTH);
        card1.add(button1, BorderLayout.SOUTH);
        card1.add(scrollpane1, BorderLayout.CENTER);
        
        // ce bout de code est censé mettre un arrayList sous forme de tableau (pb format d'affichage)
        stat2.setText("Liste des lits libres sous forme (Numero chambre) (Service) (Numero lit)");
        this.chambres = this.getChambresLibres();
        list2 = new JList(this.chambres.toArray(new String[0]));
        JScrollPane scrollpane2 = new JScrollPane(list2);
        
        
        card2.setLayout(new BorderLayout());
        card2.setBackground(Color.gray);
        card2.add(stat2, BorderLayout.NORTH);
        card2.add(scrollpane2, BorderLayout.CENTER);
        
        card3.setLayout(new BorderLayout());
        card3.setBackground(Color.green);
        card2.add(stat2, BorderLayout.NORTH);
        
        this.setLayout(new BorderLayout());
        content.setLayout(c);
        
        JPanel boutonPane = new JPanel();
        
        JButton bouton = new JButton("Contenu suivant");
        //Définition de l'action du bouton
        bouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            //Via cette instruction, on passe au prochain conteneur de la pile
            c.next(content);
          }
        });
        
         JButton bouton2 = new JButton("Contenu précédent");
        //Définition de l'action du bouton2
        bouton2.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent event){
            //Via cette instruction, on passe au prochain conteneur de la pile
            c.previous(content);
          }
        });
        
        boutonPane.add(bouton2);
        boutonPane.add(bouton);
        
        content.add(card1, listContent[0]);
        content.add(card2, listContent[1]);
        content.add(card3, listContent[2]);
        
        this.add(content, BorderLayout.CENTER);
        this.add(boutonPane, BorderLayout.NORTH);
    }
    
   
    // reuqete qui permet d'obtenir la liste des chambres et lits non occupés dans l'hopital (marche bien)
    public ArrayList<String> getChambresLibres() throws SQLException
    {
        ArrayList<String> a = this.conn.remplirChampsRequete(
             "SELECT DISTINCT h.no_chambre, h.code_service, h.lit \n" +
"            FROM chambre AS c, hospitalisation AS h \n" +
"            WHERE c.no_chambre != h.no_chambre \n" +
"            AND c.code_service != h.code_service");
        System.out.println(a);
        String temp = "";
        for(int i=0; i<a.size(); i++)
        {
            temp += a.get(i) + "\n";
        }
        this.label2.setText(temp);
        return a;
    }
    
     // requete à la base qui recoit le nb de docteur à la spécialité demandée
    public String getnbSpecialite(String specialite) throws SQLException
    {
        ArrayList<String> a = this.conn.remplirChampsRequete("SELECT COUNT(specialite) FROM docteur WHERE specialite='"+specialite+"'");
        String b = a.toString();
        return b;
        
    }
    
    //On demande le nb de docteur pr chaque spécialité
    public ArrayList<String> createReport() throws SQLException
    {
        //this.conn.searchDocteurSpecialise("cardiologue");
        ArrayList<String> specialites = new ArrayList<String>();
        String nb1 = this.getnbSpecialite("cardiologue").toString() + " cardiologue";
        String nb2 = this.getnbSpecialite("traumatologue").toString() + " traumatologue";
        String nb3 = this.getnbSpecialite("pneumologue").toString() + " pneumologue";
        String nb4 = this.getnbSpecialite("orthopediste").toString() + " orthopediste";
        String nb5 = this.getnbSpecialite("radiologue").toString() + " radiologue";
        String nb6 = this.getnbSpecialite("anesthesiste").toString() + " anesthesiste";
        
        specialites.add(nb1);
        specialites.add(nb2);
        specialites.add(nb3);
        specialites.add(nb4);
        specialites.add(nb5);
        specialites.add(nb6);
        System.out.println(specialites);
        
        return specialites;
    }
}