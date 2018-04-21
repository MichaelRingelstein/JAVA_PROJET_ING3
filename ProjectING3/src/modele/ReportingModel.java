/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import vue.Reporting;

/**
 *
 * @author micha
 */
public class ReportingModel {
    
    public Connexion conn;
    public ResultSet result;
    public ReportingModel(Connexion con){
        this.conn = con;
    }
    
    //requete retournant un tableau avec le nom de chaque service
    public ArrayList<String> getNomsService() throws SQLException
    {
        
        result = conn.resultServerRequest("SELECT DISTINCT code_service FROM hospitalisation");
        ArrayList<String> codes = new ArrayList<String>();
        while(result.next())
        {
            codes.add(result.getString("code_service"));
        }
        //requete pr connaitre le code de tous les services possibles
        //ArrayList<String> codes = this.conn.remplirChampsRequete("SELECT DISTINCT code_service FROM hospitalisation");
//        ArrayList<String> test = new ArrayList<String>();
//        for (int i = 0; i < codes.size(); i++) {
//
//            test.add(codes.get(i).toString());
//        }
        
        return codes;
    }
    
    //requete retournant un tableau avec le nb de malades par service dans l'ordre du tableau des noms de services
    public  ArrayList<Integer> getNbMaladesService(ArrayList<String> test) throws SQLException
    {
        String temp;
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
    public ArrayList<String> getChambresLibres(JLabel label2) throws SQLException
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
        label2.setText(temp);
        return a;
    }
    
     // requete à la base qui renvoie le nb de docteur à la spécialité demandée
    public String getnbSpecialite(String specialite) throws SQLException
    {
        specialite = specialite.trim();
        ArrayList<String> a = this.conn.remplirChampsRequete("SELECT COUNT(specialite) FROM docteur WHERE specialite='"+specialite+"'");
        //System.out.println("SELECT COUNT(specialite) FROM docteur WHERE specialite='"+specialite+"'");
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
//        System.out.println(nbDocteurs);
        return nbDocteurs;
    }
    
    
    public ArrayList<Float> salaireParService(ArrayList<String> services) throws SQLException
    {
        int counter = 0;
        ArrayList<Float> salaire = new ArrayList<Float>();
        ArrayList<Float> salairesMoyens = new ArrayList<Float>();
        float salaireMoyen = 0;
        for (int i = 0; i < services.size(); i++) {
            counter = 0;
             result = this.conn.resultServerRequest("SELECT * FROM infirmier WHERE code_service='"+services.get(i)+"'");
             while(result.next())
             {
                 salaire.add(result.getFloat("salaire"));
             }
             for (int j = 0; j < salaire.size(); j++) {
                salaireMoyen += salaire.get(j);
            }
             salaireMoyen = salaireMoyen/salaire.size();
             salairesMoyens.add(salaireMoyen);
        }
        System.out.println(salairesMoyens);
        return salairesMoyens;
    }
    
    public ArrayList<String> listRotations() throws SQLException
    {
        result = conn.resultServerRequest("SELECT DISTINCT rotation FROM infirmier");
        ArrayList<String> rotation = new ArrayList<String>();
        while(result.next())
        {
            rotation.add(result.getString("rotation"));
        }
//        System.out.println(rotation);
        return rotation;
    }
    
    public ArrayList<Integer> nbRotations(ArrayList<String> rotation) throws SQLException
    {
        int counter = 0;
        ArrayList<Integer> nombre = new ArrayList<Integer>();
        for (int i = 0; i < rotation.size(); i++) {
            counter = 0;
             result = this.conn.resultServerRequest("SELECT * FROM infirmier WHERE rotation='"+rotation.get(i)+"'");
             while(result.next())
             {
                 counter ++;
             }
             nombre.add(counter);
        }
        //System.out.println(nombre);
        return nombre;
    }
    
    public ArrayList<String> listeMutuelles() throws SQLException
    {
        result = conn.resultServerRequest("SELECT DISTINCT mutuelle FROM malade");
        ArrayList<String> mutuelles = new ArrayList<String>();
        while(result.next())
        {
            mutuelles.add(result.getString("mutuelle"));
        }
//        System.out.println(mutuelles);
        return mutuelles;
    }
    
    public ArrayList<Integer> nbMutuelles(ArrayList<String> mutuelles) throws SQLException
    {
        int counter = 0;
        ArrayList<Integer> nombre = new ArrayList<Integer>();
        for (int i = 0; i < mutuelles.size(); i++) {
            counter = 0;
             result = this.conn.resultServerRequest("SELECT * FROM malade WHERE mutuelle='"+mutuelles.get(i)+"'");
             while(result.next())
             {
                 counter ++;
             }
             nombre.add(counter);
        }
//        System.out.println(nombre);
        return nombre;
    }
}
