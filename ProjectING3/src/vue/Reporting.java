/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.lang.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.TableModel;
import modele.*;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

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
    JPanel card4;
    JPanel card5;
    JPanel card6;
    
    JLabel stat1;
    JLabel label1;
    JLabel stat2;
    JLabel label2;
    JLabel stat3;
    JLabel stat4;
    JLabel stat5;
    JLabel stat6;
    
    JButton button1;
    JButton button2;
    JList list1;
    JList list2;
    JList list3a;
    JList list3b;
    //Liste des noms de nos conteneurs pour la pile de cartes
    String[] listContent = {"CARD_1", "CARD_2", "CARD_3", "CARD_4", "CARD_5", "CARD_6"};
    int indice = 0;
    Connexion conn;
    ArrayList<String> specialites;
    ArrayList<String> chambres;
    ArrayList<Integer> nbmalades;
    ArrayList<Integer> nbdocteurs;
    ArrayList<String> rotations;
    ArrayList<Integer> nbrotations;
    ArrayList<String> mutuelles;
    ArrayList<Integer> nbmutuelles;
    ArrayList<String> services;
    ArrayList<Float> salairesMoyens;
    ReportingModel model;
    
    
    public Reporting(Connexion conn) throws SQLException
    {
        this.conn = conn;
        content = new JPanel();
        card1 = new JPanel();
        card2 = new JPanel();
        card3 = new JPanel();
        card4 = new JPanel();
        card5 = new JPanel();
        card6 = new JPanel();
        
        stat1 = new JLabel();
        label1 = new JLabel();
        stat2 = new JLabel();
        label2 = new JLabel();
        stat3 = new JLabel();
        stat4 = new JLabel();
        stat5 = new JLabel();
        stat6 = new JLabel();
        
        button1 = new JButton();
        button2 = new JButton();
        specialites = new ArrayList<String>();
        specialites.clear();
        this.setSize(600,600);
        model = new ReportingModel(conn);
        

        stat1.setText("Répartition des spécialités des Docteurs");
        button1.setText("Ouvrir graphique");
        
        
        list1 = new JList(this.specialites.toArray(new String[0]));
        JScrollPane scrollpane1 = new JScrollPane(list1);
        
        card1.setLayout(new BorderLayout());
        card1.setBackground(Color.gray);
        card1.add(stat1, BorderLayout.NORTH);
        //card1.add(scrollpane1, BorderLayout.CENTER);
 
        this.specialites = model.listSPecialites();
        this.nbdocteurs = model.nbDocteurs(this.specialites);
        PieChart medecins = new PieChart("Medecins par spécialite",specialites, nbdocteurs);
        card1.add(medecins, BorderLayout.CENTER);

        
        // ce bout de code est censé mettre un arrayList sous forme de tableau (pb format d'affichage)
        stat2.setText("Liste des lits libres sous forme (Numero chambre) (Service) (Numero lit)");
        this.chambres = model.getChambresLibres(this.label2);
        list2 = new JList(this.chambres.toArray(new String[0]));
        JScrollPane scrollpane2 = new JScrollPane(list2);
        
        
        card2.setLayout(new BorderLayout());
        card2.setBackground(Color.gray);
        card2.add(stat2, BorderLayout.NORTH);
        card2.add(scrollpane2, BorderLayout.CENTER);
        
        stat3.setText("Nombre de malades par service");
        //array d'array contenant les codes de services et le nb de malades par service
        services = model.getNomsService();
        //System.out.println(maladeService);
        nbmalades = model.getNbMaladesService(services);
        //System.out.println(nbmalades);
        
        PieChart maladesChart = new PieChart("Malades par service",services, nbmalades);

        /*list3a = new JList(this.maladeService.get(0).toArray(new String[0]));
        JScrollPane scrollpane3a = new JScrollPane(list3a);
        list3b = new JList(this.maladeService.get(1).toArray(new String[0]));
        JScrollPane scrollpane3b = new JScrollPane(list3b);*/
        list3a = new JList(this.services.toArray(new String[0]));
        JScrollPane scrollpane3a = new JScrollPane(list3a);
        
        card3.setLayout(new BorderLayout());
        card3.setBackground(Color.gray);
        card3.add(stat3, BorderLayout.NORTH);
        card3.add(maladesChart, BorderLayout.CENTER);
        //card3.add(scrollpane3b, BorderLayout.CENTER);
        
        stat4.setText("Nombre d'infirmier par rotation");
        
        rotations = model.listRotations();
        nbrotations = model.nbRotations(rotations);
        PieChart rotationsChart = new PieChart("Nombre d'infirmiers par rotation",rotations, nbrotations);
        
        card4.setLayout(new BorderLayout());
        card4.setBackground(Color.gray);
        card4.add(stat4, BorderLayout.NORTH);
        card4.add(rotationsChart, BorderLayout.CENTER);
        
        
        stat5.setText("Nombre d'affiliés par mutuelle");
        
        mutuelles = model.listeMutuelles();
        nbmutuelles = model.nbMutuelles(mutuelles);
        PieChart mutuellesChart = new PieChart("Nombre d'affiliés par mutuelle",mutuelles, nbmutuelles);
        
        card5.setLayout(new BorderLayout());
        card5.setBackground(Color.gray);
        card5.add(stat5, BorderLayout.NORTH);
        card5.add(mutuellesChart, BorderLayout.CENTER);
        
        
        stat6.setText("Salaires moyens par service");
        
        System.out.println(services);
        salairesMoyens = model.salaireParService(services);
        BarChart salairesChart = new BarChart("Salaire moyen par service", services, salairesMoyens, "salaires");
        
        card6.setLayout(new BorderLayout());
        card6.setBackground(Color.gray);
        card6.add(stat6, BorderLayout.NORTH);
        card6.add(salairesChart, BorderLayout.CENTER);
        
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
        content.add(card4, listContent[3]);
        content.add(card5, listContent[4]);
        content.add(card6, listContent[5]);
        
        this.add(content, BorderLayout.CENTER);
        this.add(boutonPane, BorderLayout.NORTH);
    }
    
}
