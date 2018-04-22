/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.JButton;

/**
 *
 * @author adrie
 */
public class MyButton extends JButton{
    
    private int id;
    
    public MyButton(String s, int i)
    {
        super(s);
        this.id = i;
    }
    
    public int getId(){return this.id;}
}
