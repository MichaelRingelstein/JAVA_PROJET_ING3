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
public class PairString {
    
    private String  left;
    private String  right;
    private String type;

    public PairString(String left, String right, String type)
    {
       this.left = left;
       this.right = right;
       this.type = type;
    }

     public String getL() { return left; }
     public String getR() { return right; }
     public String getType() { return type; }
    
}
