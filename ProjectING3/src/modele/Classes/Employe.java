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
public class Employe {

    public int id;
    protected String nom;
    protected String prenom;
    protected int phone_number;
    protected String adresse;
    
    public Employe(int id, String nom, String prenom, int phone, String adresse){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.phone_number = phone;
        this.adresse = adresse;        
    }
    
    
    public Employe()
    {
         this.id = 0;
        this.nom = null;
        this.prenom = null;
        this.phone_number = 0;
        this.adresse = null; 
    }
    
    
    public int getId()
    {
        return this.id;
    }
    
    public String getPrenom()
    {
        return this.prenom;
    }
    
    public String getNom()
    {
        return this.nom;
    }
    
    public int getPhone()
    {
        return this.phone_number;
    }
    
    public String getAdresse()
    {
        return this.adresse;
    }
}

