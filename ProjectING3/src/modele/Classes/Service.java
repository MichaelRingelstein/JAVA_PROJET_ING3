/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.Classes;

/**
 *
 * @author adrie
 */
public class Service {
    
    private String code;
    private String nom;
    private char batiment;
    private int directeurId;
    
    public Service(String code, String nom, char batiment, int directeurId)
    {
        this.code = code;
        this.nom = nom;
        this.batiment = batiment;
        this.directeurId = directeurId;
    }
    
    public String getCode(){ return this.code;}
    public String getNom() { return this.nom;}
    public char getBatiment(){return this.batiment;}
    public int getDirecteurId(){return this.directeurId;}
    
}
