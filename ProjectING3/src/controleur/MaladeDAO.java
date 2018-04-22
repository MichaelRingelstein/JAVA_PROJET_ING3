/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import modele.Classes.Docteur;
import modele.Classes.Employe;
import modele.Classes.Malade;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class MaladeDAO {
    
    private Connexion connex;
    public MaladeDAO(Connexion con)
    {
        this.connex = con;
    }
    
    
    public boolean add_malade(Malade m, String c, String d)
    {
        boolean b = false;
        String sql = "INSERT INTO malade VALUES (" + m.getId() + ",'" + m.getNom()+ "','" + m.getPrenom() + "','" + m.getAdresse() + "','" + m.getTel() + "','" + m.getMutuelle() + "');";
        String c_num[] = c.split(",");
        System.out.print(c_num[0]);
        String hosp = "INSERT INTO hospitalisation VALUES (" + m.getId() + ", '" + c_num[0] + "', " + c_num[1] + ", " + c_num[2] + ");";
      
        String d_num[] = d.split(",");
        String doc ="INSERT INTO soigne VALUES (" + d_num[0] + ", " + m.getId() + ");";
        try
        {
            connex.executeUpdate(sql);
            connex.executeUpdate(hosp);
            connex.executeUpdate(doc);
            b = true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
    }
    
    
    public boolean update_malade(Malade m)
    {
        boolean b = false;
        String sql = "UPDATE malade SET nom = '" + m.getNom()+ "', prenom = '" + m.getPrenom() + "', adresse = '" + m.getAdresse() + "', tel = '" + m.getTel() + "', mutuelle = '" + m.getMutuelle() + "' WHERE numero = " + m.getId()+ ";";
        try
        {
            connex.executeUpdate(sql);
            b = true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
        
    }
    
    
    public boolean delete_malade(Malade e)
    {
        boolean b = false;
        String sql = "DELETE FROM malade WHERE numero = " + e.getId() + ";";
        try
        {
            connex.executeUpdate(sql);
            b = true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
        
    }
    
    
    
    public ArrayList<Malade> getAllMal() throws SQLException
    {
        String sql = "SELECT * FROM malade";
        ResultSet result = connex.resultServerRequest(sql);
        
        ArrayList<Malade> temp = new ArrayList<Malade>();
        while(result.next())
        {
            temp.add(new Malade(result.getInt("numero"), result.getString("nom"), result.getString("prenom"), result.getString("tel"), result.getString("adresse"), result.getString("mutuelle")));
        }
        
        return temp;
    }
    
    
    public ArrayList<String> getChambresLibres() throws SQLException
    {
        ArrayList<String> a = this.connex.remplirChampsRequete(
             "SELECT DISTINCT c.code_service, c.no_chambre, h.lit" +
"            FROM chambre AS c, hospitalisation AS h " +
"            WHERE c.no_chambre != h.no_chambre " +
"            AND c.code_service != h.code_service");
        //System.out.println(a);
        String temp = "";
        for(int i=0; i<a.size(); i++)
        {
            temp += a.get(i) + "\n";
        }
        System.out.println(temp);
        return a;
    }
    
    
     public ArrayList<String> getDocs() throws SQLException
    {
        ArrayList<String> a = this.connex.remplirChampsRequete(
             "SELECT docteur.numero, nom, prenom, specialite FROM docteur, employe WHERE employe.numero = docteur.numero");
        //System.out.println(a);
        String temp = "";
        for(int i=0; i<a.size(); i++)
        {
            temp += a.get(i) + "\n";
        }
        System.out.println(temp);
        return a;
    }
    
    
}
