/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    
    public boolean add_malade(Malade m)
    {
        boolean b = false;
        String sql = "INSERT INTO malade VALUES (" + m.getId() + ",'" + m.getNom()+ "','" + m.getPrenom() + "','" + m.getAdresse() + "','" + m.getTel() + "','" + m.getMutuelle() + "');";
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
    
    
    public boolean update_malade(Malade m)
    {
        boolean b = false;
        String sql = "UPDATE malade SET nom = '" + m.getNom()+ "', prenom = '" + m.getPrenom() + "', adresse = '" + m.getAdresse() + "', tel = '" + m.getTel() + "','" + m.getMutuelle() + "' WHERE numero = " + m.getId()+ ";";
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
    
    
}
