/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Classes.Docteur;
import modele.Classes.Employe;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class DocteurDAO extends EmployeDAO{
    
    public DocteurDAO(Connexion con) {
        super(con);
    }
    
    
    public boolean add_docteur(Docteur d)
    {
        //on ajoute le docteur en tant qu'employé
        super.add_employe(d);
        
        //on ajoute le docteur en tant que médecin
        boolean b = false;
        String sql = "INSERT INTO docteur VALUES (" + d.getId() + ",'" + d.getSpecialite() + "');";
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
    
    
    public boolean update_docteur(Docteur d)
    {
        //on mets à jour le docteur comme un employe
        super.update_employe(d);
        
        //on met à jour le docteur dans la table docteur
        boolean b = false;
        String sql = "UPDATE docteur SET specialite = '" + d.getSpecialite() + "' WHERE numero = " + d.getId()+ ";";
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
    
    
    public boolean delete_docteur(Docteur d)
    {
        //on supprime le docteur en tant qu'employe
        super.delete_employe(d);
        
        //on supprime en tant que docteur
        boolean b = false;
        String sql = "DELETE FROM docteur WHERE numero = " + d.getId() + ";";
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
    
}
