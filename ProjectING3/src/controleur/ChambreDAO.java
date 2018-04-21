/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Classes.Chambre;
import modele.Classes.Malade;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class ChambreDAO {
    
    private Connexion connex;
    public ChambreDAO(Connexion con)
    {
        this.connex = con;
    }
    
    
    public boolean add_chambre(Chambre ch)
    {
        boolean b = false;
        String sql = "INSERT INTO chambre VALUES (" + ch.getService() + ",'" + ch.getNChambre()+ "','" + ch.getSurveillant() + "','" + ch.getNLit() + "');";
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
    
    
    public boolean update_chambre(Chambre ch)
    {
        boolean b = false;
        String sql = "UPDATE chambre SET code_service = '" + ch.getService()+ "', no_chambre = '" + ch.getNChambre() + "', surveillant = '" + ch.getSurveillant() + "', nb_lits = '" + ch.getNLit() + "' WHERE no_chambre = " + ch.getNChambre()+ "' AND code_servie = '" + ch.getService() + "';";
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
    
    
    public boolean delete_chambre(Chambre e)
    {
        boolean b = false;
        String sql = "DELETE FROM chambre WHERE code_servie = " + e.getService() + "' AND no_chambre = '" + e.getNChambre() +"';";
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
