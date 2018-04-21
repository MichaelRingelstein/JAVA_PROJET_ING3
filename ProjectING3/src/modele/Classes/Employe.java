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

    protected int numero;
    protected String nom;
    protected String prenom;
    protected String phone_number;
    protected String adresse;
    
    public Employe(int numero, String nom, String prenom, String phone, String adresse){
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.phone_number = phone;
        this.adresse = adresse;        
    }
    
    
    public Employe()
    {
        this.numero = 0;
        this.nom = null;
        this.prenom = null;
        this.phone_number = null;
        this.adresse = null; 
    }
    
    
    public int getNumero()
    {
        return this.numero;
    }
    
    public String getPrenom()
    {
        return this.prenom;
    }
    
    public String getNom()
    {
        return this.nom;
    }
    
    public String getPhone()
    {
        return this.phone_number;
    }
    
    public String getAdresse()
    {
        return this.adresse;
    }
}