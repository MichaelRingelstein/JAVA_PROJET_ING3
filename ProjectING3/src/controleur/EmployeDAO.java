/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Classes.Employe;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class EmployeDAO {
    
    protected Connexion conex;
    
    public EmployeDAO(Connexion con)
    {
        this.conex = con;
    }
    
    
    public boolean add_employe(Employe e)
    {
        boolean b = false;
        String sql = "INSERT INTO employe VALUES (" + e.getId() + ",'" + e.getNom()+ "','" + e.getPrenom() + "','" + e.getAdresse() + "','" + e.getTel() + "');";
        try
        {
            conex.executeUpdate(sql);
            b = true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
    }
    
    
    public boolean update_employe(Employe e)
    {
        boolean b = false;
        String sql = "UPDATE employe SET nom = '" + e.getNom()+ "', prenom = '" + e.getPrenom() + "', adresse = '" + e.getAdresse() + "', tel = '" + e.getTel() + "' WHERE numero = " + e.getId()+ ";";
        try
        {
            conex.executeUpdate(sql);
            b = true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
        
    }
    
    
    public boolean delete_employe(Employe e)
    {
        boolean b = false;
        String sql = "DELETE FROM employe WHERE numero = " + e.getId() + ";";
        try
        {
            conex.executeUpdate(sql);
            b = true;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            b = false;
        }
        return b;
        
    }
    
    public ArrayList<Employe> getAll() throws SQLException
    {
        String sql = "SELECT * FROM employe";
        ResultSet result = conex.resultServerRequest(sql);
        
        ArrayList<Employe> temp = new ArrayList<Employe>();
        while(result.next())
        {
            temp.add(new Employe(result.getInt("numero"), result.getString("nom"), result.getString("prenom"), result.getString("tel"), result.getString("adresse")));
        }
        
        return temp;
    }
    
   
}
