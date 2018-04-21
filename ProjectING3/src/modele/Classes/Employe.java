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

    private int id;
    private String nom;
    private String prenom;
    private String phone_number;
    private String adresse;
    
    public Employe(int id, String nom, String prenom, String phone, String adresse){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.phone_number = phone;
        this.adresse = adresse;        
    }
    
    public int getId(){return this.id;}
    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public String getTel(){return this.phone_number;}
    public String getAdresse(){return this.adresse;}
    
}

