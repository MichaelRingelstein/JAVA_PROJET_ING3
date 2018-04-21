/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.Classes;

/**
 *
 * @author micha
 */
public class Docteur extends Employe{
    
    protected String specialite;
    
    public Docteur(int id, String nom, String prenom, String phone, String adresse, String specialite) {
        super(id, nom, prenom, phone, adresse);
        this.specialite = specialite;
    }
    
    public String getSpecialite()
    {
        return this.specialite;
    }
    
}
