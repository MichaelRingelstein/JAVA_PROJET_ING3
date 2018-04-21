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
public class Chambre {
    
    private String service;
    private int n_chambre;
    private int surveillant; 
    private int n_lit;
    
    public Chambre(String service, int n_chambre, int surveillant, int n_lit)
    {
        this.service = service;
        this.n_chambre = n_chambre;
        this.surveillant = surveillant;
        this.n_lit = n_lit;
    }
    
    public String getService(){ return this.service;}
    public int getNChambre(){return this.n_chambre;}
    public int getSurveillant(){return this.surveillant;}
    public int getNLit(){ return this.n_lit;}
    
}
