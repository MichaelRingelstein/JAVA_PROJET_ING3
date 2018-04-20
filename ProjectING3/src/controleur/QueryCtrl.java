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
import javax.swing.JTable;
import modele.Classes.Docteur;
import modele.Connexion;
import modele.Classes.PairString;

/**
 *
 * @author adrie
 */
public class QueryCtrl {
    
    
    ArrayList<String> res;
    String req_field;
    String where_condition;
    /**
     * Constructeur de requête en fonction des paramètres
     * @param connex //la connexion à la bdd
     * @param table // le ou les tables dans lesquelles rechercher
     * @param fields // les champs sélectionnés
     * @param where //condition sur les champs 
     */
    public QueryCtrl(Connexion connex, String table, ArrayList<String> fields, ArrayList<PairString> where)
    {
        this.buildField(fields);
        this.build_where(where, table);
        this.buildQuery(connex, table);           
    }
    
    
    private void buildField(ArrayList<String> fields)
    {
        this.req_field = "";  
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
    }
    
    
    private void build_where(ArrayList<PairString> where, String table)
    {
        this.where_condition = "";
        if(table == "employe, docteur")
        {
            this.where_condition += " WHERE employe.numero = docteur.numero ";
        }
        else if(table == "employe, infirmier")
        {
            this.where_condition += " WHERE employe.numero = infirmier.numero ";
        }
        else if(table == "service, docteur, employe")
        {
            this.where_condition += " WHERE service.directeur = docteur.numero AND employe.numero = docteur.numero ";
        }
        else
        {
            this.where_condition += " WHERE ";    
        }
        if(!where.isEmpty())
        {
            
            for(int i = 0; i < where.size(); i++)
            {
                String s = where.get(i).getR();
                 System.out.print(s);
                if(1 < where.size())
                {
                    System.out.print(s);
                    if(i+1 == where.size())
                    {
                        //System.out.println(where_condition += where.get(i).getL());
                        where_condition += where.get(i).getL() + " = '" + s + "'; ";
                    }
                    else
                    {
                        where_condition += where.get(i).getL() + " = '" + s + "' AND "; // on sépare les tables par une virgule s'il y a plusieurs tables
                        System.out.println(where_condition);
                    }
                }    
                else
                {
                    where_condition += where.get(i).getL() + " = '" + s + "';" ; //on ne met pas de virgule pour une table
                }
            }
        }
        
    }
    
    public String result(ArrayList<String> f)
    {
        String ret = "<html>";
            for(int i = 0; i < res.size(); i++)
            {
                ret += "<tr><td>" + res.get(i) + "</td></td>";
            }   
        ret += "</html>";
        return ret;
    }

    private void buildQuery(Connexion connex, String table) {
        try 
        {
            if(this.where_condition.isEmpty())
            {
                res = new ArrayList<>();
                res = connex.remplirChampsRequete("SELECT "+req_field+ " FROM "+ table); 
                System.out.print(table);
            }
            else if(!this.where_condition.isEmpty())
            {
                res = new ArrayList<>();
                res = connex.remplirChampsRequete("SELECT "+req_field+ " FROM "+ table + where_condition);        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
}
