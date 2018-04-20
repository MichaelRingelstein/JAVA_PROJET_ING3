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
    JLabel stat1;
    JLabel label1;
    JLabel stat2;
    JLabel label2;
    JLabel stat3;
    JButton button1;
    JButton button2;
    JList list1;
    JList list2;
    JList list3a;
    JList list3b;
    //Liste des noms de nos conteneurs pour la pile de cartes
    String[] listContent = {"CARD_1", "CARD_2", "CARD_3"};
    int indice = 0;
    Connexion conn;
    ArrayList<String> specialites;
    ArrayList<String> chambres;
    ArrayList<String> maladeService;
    ArrayList<Integer> nbmalades;
    ArrayList<Integer> nbdocteurs;
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
        button2 = new JButton();
        specialites = new ArrayList<String>();
        specialites.clear();
        this.setSize(600,600);
        

        stat1.setText("Répartition des spécialités des Docteurs");
        button1.setText("Ouvrir graphique");
        
        
        list1 = new JList(this.specialites.toArray(new String[0]));
        JScrollPane scrollpane1 = new JScrollPane(list1);
        
        card1.setLayout(new BorderLayout());
        card1.setBackground(Color.gray);
        card1.add(stat1, BorderLayout.NORTH);
        //card1.add(scrollpane1, BorderLayout.CENTER);
 
        this.specialites = this.listSPecialites();
        this.nbdocteurs = this.nbDocteurs(this.specialites);
        PieChart medecins = new PieChart("Medecins par spécialite",specialites, nbdocteurs);
        card1.add(medecins, BorderLayout.CENTER);

        
        // ce bout de code est censé mettre un arrayList sous forme de tableau (pb format d'affichage)
        stat2.setText("Liste des lits libres sous forme (Numero chambre) (Service) (Numero lit)");
        this.chambres = this.getChambresLibres();
        list2 = new JList(this.chambres.toArray(new String[0]));
        JScrollPane scrollpane2 = new JScrollPane(list2);
        
        
        card2.setLayout(new BorderLayout());
        card2.setBackground(Color.gray);
        card2.add(stat2, BorderLayout.NORTH);
        card2.add(scrollpane2, BorderLayout.CENTER);
        
        stat3.setText("Nombre de malades par service");
        //array d'array contenant les codes de services et le nb de malades par service
        maladeService = this.getNomsService();
        //System.out.println(maladeService);
        nbmalades = getNbMaladesService(maladeService);
        //System.out.println(nbmalades);
        
        PieChart maladesChart = new PieChart("Malades par service",maladeService, nbmalades);

        /*list3a = new JList(this.maladeService.get(0).toArray(new String[0]));
        JScrollPane scrollpane3a = new JScrollPane(list3a);
        list3b = new JList(this.maladeService.get(1).toArray(new String[0]));
        JScrollPane scrollpane3b = new JScrollPane(list3b);*/
        list3a = new JList(this.maladeService.toArray(new String[0]));
        JScrollPane scrollpane3a = new JScrollPane(list3a);
        
        card3.setLayout(new BorderLayout());
        card3.setBackground(Color.gray);
        card3.add(stat3, BorderLayout.NORTH);
        card3.add(maladesChart, BorderLayout.CENTER);
        //card3.add(scrollpane3b, BorderLayout.CENTER);
        
        
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
    
    
    //requete retournant un tableau avec le nom de chaque service
    public ArrayList<String> getNomsService() throws SQLException
    {
        //requete pr connaitre le code de tous les services possibles
        ArrayList<String> codes = this.conn.remplirChampsRequete("SELECT DISTINCT code_service FROM hospitalisation");
        ArrayList<String> test = new ArrayList<String>();
        for (int i = 0; i < codes.size(); i++) {

            test.add(codes.get(i).toString());
        }
        
        return test;
    }
    
    //requete retournant un tableau avec le nb de malades par service dans l'ordre du tableau des noms de services
    public  ArrayList<Integer> getNbMaladesService(ArrayList<String> test) throws SQLException
    {

        int g;
        ArrayList<Integer> arl = new ArrayList<Integer>();
        for (int i = 0; i < test.size(); i++) {
            temp = (this.conn.remplirChampsRequete("SELECT COUNT(lit) FROM hospitalisation WHERE code_service='"+test.get(i)+"'")).toString();
            //System.out.println(temp.getClass().getName());
            g = myParseInt(temp);
            arl.add(g);
        }
        return arl;
    }
    
    // permet de passer un String de la forme "[10]" en int 
    public  Integer myParseInt(String temp)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        String temp2="";
        int a = 0;
        for (int j = 0; j < temp.length(); j++) {
                if(temp.charAt(j)!='[' && temp.charAt(j)!=']')
                {
                    temp2 += temp.charAt(j);
                }
                
            }
        try {
            a = decimalFormat.parse(temp2).intValue();
        } catch (ParseException ex) {
            Logger.getLogger(Reporting.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (a);
        
    }
   
    // requete qui permet d'obtenir la liste des chambres et lits non occupés dans l'hopital (marche bien)
    public ArrayList<String> getChambresLibres() throws SQLException
    {
        ArrayList<String> a = this.conn.remplirChampsRequete(
             "SELECT DISTINCT h.no_chambre, h.code_service, h.lit \n" +
"            FROM chambre AS c, hospitalisation AS h \n" +
"            WHERE c.no_chambre != h.no_chambre \n" +
"            AND c.code_service != h.code_service");
        //System.out.println(a);
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
        specialite = specialite.trim();
        ArrayList<String> a = this.conn.remplirChampsRequete("SELECT COUNT(specialite) FROM docteur WHERE specialite='"+specialite+"'");
        System.out.println("SELECT COUNT(specialite) FROM docteur WHERE specialite='"+specialite+"'");
        String b = a.toString();
        return b;
        
    }
    
    //On demande le nb de docteur pr chaque spécialité
    public ArrayList<String> listSPecialites() throws SQLException
    {
        //this.conn.searchDocteurSpecialise("cardiologue");
        ArrayList<String> specialites = new ArrayList<String>();
        
        specialites = this.conn.remplirChampsRequete("SELECT DISTINCT specialite FROM docteur");
        //System.out.println(specialites);
        
        //System.out.println(specialites);
        
        return specialites;
    }
    
    public ArrayList<Integer> nbDocteurs(ArrayList<String> specialites) throws SQLException
    {
        ArrayList<Integer> nbDocteurs = new ArrayList<Integer>();
        String a;
        for (int i = 0; i < specialites.size(); i++) {
            a = specialites.get(i);
            nbDocteurs.add(this.myParseInt(this.getnbSpecialite(a)));
            //System.out.println(specialites.get(i));
        }
        System.out.println(nbDocteurs);
        return nbDocteurs;
    }
}
