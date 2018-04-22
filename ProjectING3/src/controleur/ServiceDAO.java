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
import modele.Classes.Chambre;
import modele.Classes.Docteur;
import modele.Classes.Service;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class ServiceDAO {
    
    private Connexion connex;
    public ServiceDAO(Connexion con)
    {
        this.connex = con;
    }
    
    
    public boolean add_service(Service s)
    {
        boolean b = false;
        String sql = "INSERT INTO service VALUES ('" + s.getCode() + "','" + s.getNom()+ "','" + s.getBatiment() + "','" + s.getDirecteurId() + "');";
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
    
    
    public boolean update_service(Service s)
    {
        boolean b = false;
        String sql = "UPDATE service SET nom = '" + s.getNom() + "', batiment = '" + s.getBatiment() + "', directeur = '" + s.getDirecteurId() + "' WHERE code = '" + s.getCode()+ "';";
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
    
    
    public boolean delete_service(Service e)
    {
        boolean b = false;
        String sql = "DELETE FROM service WHERE code = '" + e.getCode() +"';";
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
    
    public ArrayList<Service> getAllService() throws SQLException
    {
        String sql = "SELECT * FROM service";
        ResultSet result = connex.resultServerRequest(sql);
        
        ArrayList<Service> temp = new ArrayList<Service>();
        while(result.next())
        {
            temp.add(new Service(result.getString("code"), result.getString("nom"), result.getString("batiment").charAt(0), result.getInt("directeur")));
        }
        
        return temp;
    }
    
    
}
