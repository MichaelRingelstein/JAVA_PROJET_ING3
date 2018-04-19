/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class QueryCtrl {
    
    
    ArrayList<String> res;
    
    /**
     * Constructeur de requête en fonction des paramètres
     * @param connex
     * @param table
     * @param fields 
     */
    public QueryCtrl(Connexion connex, String table, ArrayList<String> fields)
    {
        String req_field = "";  
                for(int i = 0; i < fields.size(); i++)
                {
                    if(1 < fields.size())
                    {
                        if(i+1 == fields.size())
                        {
                            req_field += fields.get(i) + " ";
                        }
                        else
                        {
                            req_field += fields.get(i) + ", "; // on sépare les tables par une virgule s'il y a plusieurs tables
                        }
                    }    
                    else
                    {
                        req_field += " " + fields.get(i); //on ne met pas de virgule pour une table   
                    }
                }
                System.out.print(req_field);
        try {
            res = new ArrayList<>();
            res = connex.remplirChampsRequete("SELECT "+req_field+ " FROM "+ table);
        } catch (SQLException ex) {
            Logger.getLogger(QueryCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public String result()
    {
        String ret = "";
        for(int i = 0; i < res.size(); i++)
        {
            ret += "<html>" + res.get(i) + "<br>";
        }
        ret += "<html>";
        return ret;
    }
    
}
