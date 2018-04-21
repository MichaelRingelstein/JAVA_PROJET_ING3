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
    protected int numero;
    protected String nom;
    protected String prenom;
    protected int phone_number;
    protected String adresse;
    protected String mutuelle;
    
     public Malade(int numero, String nom, String prenom, int phone, String adresse, String mutuelle){
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.phone_number = phone;
        this.adresse = adresse;  
        this.mutuelle = mutuelle;
    }
}
