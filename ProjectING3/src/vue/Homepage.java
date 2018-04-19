/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author micha
 */
public class Homepage extends JFrame {
    
    private JPanel pan0 = new JPanel();
    private JPanel pan = new JPanel();
    private JPanel pan2 = new JPanel();
    private JPanel pan3 = new JPanel();
    private String db;
    private String use;
    private String pw;
    private JTextField db_name;
    private JTextField user_name;
    private JTextField pass_word;
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
        buttonContinue.addActionListener(new Login());
        
        pan0.setLayout(new BoxLayout(pan0, BoxLayout.LINE_AXIS));
        pan0.add(new JLabel("Base de donnée : "));
        this.db_name = new JTextField(20);
        pan0.add(db_name);
        pan.setLayout(new BoxLayout(pan, BoxLayout.LINE_AXIS));
        pan.add(new JLabel("Nom d'utilisateur :"));
        this.user_name = new JTextField(20);
        pan.add(user_name);
        pan2.setLayout(new BoxLayout(pan2, BoxLayout.LINE_AXIS));
        pan2.add(new JLabel("Mot de passe :"));
        this.pass_word = new JTextField(20);
        pan2.add(pass_word);
        pan3.add(pan0);
        pan3.add(pan);
        pan3.add(pan2);
        
        this.getContentPane().add(auth, BorderLayout.NORTH);
        this.getContentPane().add(pan3, BorderLayout.CENTER);
        this.getContentPane().add(buttonContinue, BorderLayout.SOUTH);
        this.setVisible(true);
        
        
        
        
    }
    
    
    private void closeHomePage()
    {
        this.setVisible(false);
    }
    
    class Login implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           
            db = db_name.getText();
            use = user_name.getText();
            pw = pass_word.getText();
            new MainPage(db, use, pw);
            closeHomePage();
            
            
        }
    }
    
    
}
