/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Евгений
 */
public class JDialogQuery {
App app;
JTextField queryBtn;
JButton btn2;
AppListener appl;
JDialog dialogQuery;


    public JDialogQuery(App a) {
        this.app = a;

        dialogQuery = new JDialog(a, true);
        dialogQuery.setBounds(200, 200, 300, 100);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        queryBtn = new JTextField("Select * from " + App.dbnameSet);
        btn2 = new JButton("OK");
        panel.add(queryBtn);
        panel.add(btn2);
        dialogQuery.add(panel);
        
        appl = new AppListener(this, app);
        dialogQuery.setVisible(true);
        
    }
    
}
