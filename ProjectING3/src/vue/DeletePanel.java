/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.Connexion;

/**
 *
 * @author adrie
 */
public class DeletePanel extends JPanel {
    
    JComboBox choix;
    Connexion conex;
    JPanel pan;
    JPanel form_pan;
    JLabel title;
    
    
    public DeletePanel(Connexion con)
    {
        this.conex = con;
        this.pan = new JPanel();
        this.pan.setBackground(Color.gray);
        this.title = new JLabel();
        this.form_pan = new JPanel();
        Dimension dim = new Dimension(500,800);
        this.form_pan.setSize(dim);
        this.setLayout(new BorderLayout());
        this.add(pan,BorderLayout.NORTH);
        this.add(form_pan, BorderLayout.CENTER);
        String tab_choix[] = {"Docteur", "Infirmier", "Malade", "Service", "Chambre"};
        this.choix = new JComboBox(tab_choix);
        pan.add(title);
        pan.add(choix);
        choix.addActionListener(new Choix_Listener());
    }
    
    
    class Choix_Listener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String)choix.getSelectedItem();
            System.out.print(s);
            form_pan.removeAll();
            //on teste le choix et on créé un formulaire différent pour chaque choix
                if("Docteur".equals(s))
                {
                    form_pan.removeAll();
                    title.setText("<html><h1>DOCTEUR</h1><br><html>");
                    DocteurForm d_form = new DocteurForm(4, conex);
                    form_pan.add(d_form);   
                }
                else if("Infirmier".equals(s))
                {
                    form_pan.removeAll();
                    title.setText("<html><h1>INFIRMIER</h1><br><html>");
                    InfirmierForm i_form = new InfirmierForm(4, conex);
                    form_pan.add(i_form);
                }
                else if("Malade".equals(s))
                {
                    form_pan.removeAll();
                    title.setText("<html><h1>PATIENT</h1><br><html>");
                    MaladeForm m_form = new MaladeForm(4, conex);
                    form_pan.add(m_form);
                }
                else if("Service".equals(s))
                {
                    form_pan.removeAll();
                    title.setText("<html><h1>SERVICE</h1><br><html>");
                    ServiceForm s_form = new ServiceForm(4, conex);
                    form_pan.add(s_form);
                }
                else if("Chambre".equals(s))
                {
                    form_pan.removeAll();
                    title.setText("<html><h1>CHAMBRE</h1><br><html>");
                    ChambreForm c_form = new ChambreForm(4, conex);
                    form_pan.add(c_form);
                }
        }
        
    }
    
}
