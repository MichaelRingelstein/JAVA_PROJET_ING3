/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author adrie
 */
public class TextField_labelelised extends JTextField {
    
    private JLabel label;
    
    public TextField_labelelised(String label, int col)
    {
        super(col);
        this.label = new JLabel(label);
        super.add(this.label);
    }
    
    
    public JLabel getLabel()
    {
        return this.label;
    }
    
    public String getLabelText()
    {
        return this.label.getText();
    }
    
}
