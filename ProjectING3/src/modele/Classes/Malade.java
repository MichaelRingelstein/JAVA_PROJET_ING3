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
public class Malade {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String phone_number;
    protected String adresse;
    protected String mutuelle;
    
     public Malade(int id, String nom, String prenom, String phone, String adresse, String mutuelle){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.phone_number = phone;
        this.adresse = adresse;  
        this.mutuelle = mutuelle;
    }
     
    public int getId(){return this.id;}
    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public String getTel(){return this.phone_number;}
    public String getAdresse(){return this.adresse;}
    public String getMutuelle(){return this.mutuelle;}
}
