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
    
    public Infirmier(int numero, String nom, String prenom, String phone, String adresse, String codeservice, String rotation, Float salaire) {
        super(numero, nom, prenom, phone, adresse);
    }
    
}
