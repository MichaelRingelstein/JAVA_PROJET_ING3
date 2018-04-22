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
import modele.Classes.Docteur;
import modele.Classes.Infirmier;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class InfirmierDAO extends EmployeDAO{
    
    public InfirmierDAO(Connexion con) {
        super(con);
    }
    
    
     public boolean add_infirmier(Infirmier i)
    {
        //on ajoute le docteur en tant qu'employé
        super.add_employe(i);
        
        //on ajoute le docteur en tant que médecin
        boolean b = false;
        String sql = "INSERT INTO infirmier VALUES (" + i.getId() + ",'" + i.getCodeService() + "','" + i.getRotation() + "', '" + i.getSalaire() + "');";
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
    
    
    public boolean update_infirmier(Infirmier i)
    {
        //on mets à jour le docteur comme un employe
        super.update_employe(i);
        
        //on met à jour le docteur dans la table docteur
        boolean b = false;
        String sql = "UPDATE infirmier SET code_service = '" + i.getCodeService() + "', rotation = '"  + i.getRotation() + "', salaire = " + i.getSalaire() +  " WHERE numero = " + i.getId()+ ";";
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
    
    
    public boolean delete_infirmier(Infirmier i)
    {
        //on supprime le docteur en tant qu'employe
        super.delete_employe(i);
        
        //on supprime en tant que docteur
        boolean b = false;
        String sql = "DELETE FROM infirmier WHERE numero = " + i.getId() + ";";
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
    
    
    
    public ArrayList<Infirmier> getAllInf() throws SQLException
    {
        String sql = "SELECT * FROM employe, infirmier WHERE infirmier.numero = employe.numero";
        ResultSet result = conex.resultServerRequest(sql);
        
        ArrayList<Infirmier> temp = new ArrayList<Infirmier>();
        while(result.next())
        {
            temp.add(new Infirmier(result.getInt("numero"), result.getString("nom"), result.getString("prenom"), result.getString("tel"), result.getString("adresse"), result.getString("code_service"), result.getString("rotation"), result.getDouble("salaire")));
        }
        
        return temp;
    }
    
    
}
