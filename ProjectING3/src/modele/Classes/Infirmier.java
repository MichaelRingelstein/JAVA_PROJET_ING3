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
public class Infirmier extends Employe {
    
    String code_service;
    String  rotation;
    double salaire;
    
    public Infirmier(int id, String nom, String prenom, String phone, String adresse, String code_service, String rotation, double salaire) {
        super(id, nom, prenom, phone, adresse);
        this.code_service = code_service;
        this.rotation =  rotation;
        this.salaire = salaire;
    }
    
    
    public String getCodeService(){return this.code_service;}
    public String getRotation(){return this.rotation;}
    public double getSalaire(){return this.salaire;}
}
