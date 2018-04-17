/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author micha
 */
public class Homepage extends JFrame {
    
    private JPanel pan = new JPanel();
    private JPanel pan2 = new JPanel();
    private JPanel pan3 = new JPanel();
    public JButton buttonContinue = new JButton();
    private JLabel auth = new JLabel("Authentification");
    
    public Homepage()
    {
        this.setTitle("Gestion Centre Hospitalier");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        buttonContinue.setText("Continue");
        
        pan.setLayout(new BoxLayout(pan, BoxLayout.LINE_AXIS));
        pan.add(new JLabel("Nom d'utilisateur :"));
        pan.add(new JTextField(20));
        pan2.setLayout(new BoxLayout(pan2, BoxLayout.LINE_AXIS));
        pan2.add(new JLabel("Mot de passe :"));
        pan2.add(new JTextField(20));
        pan3.add(pan);
        pan3.add(pan2);
        
        this.getContentPane().add(auth, BorderLayout.NORTH);
        this.getContentPane().add(pan3, BorderLayout.CENTER);
        this.getContentPane().add(buttonContinue, BorderLayout.SOUTH);
        this.setVisible(true);
        
        
    }
}
