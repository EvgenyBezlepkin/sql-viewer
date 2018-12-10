/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeTask;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Евгений
 */
public class JDialogSet extends JDialog{

     App app;
     AppListener appl;
     JDialog dialog;
     JTextField url;
     JTextField user;
     JTextField password;
     JTextField dbname;
     JList list;
     JButton btn;
     JButton tryCon;
     DefaultListModel<String> dlm;
    
    public JDialogSet(App a) {
        this.app = a;
        
        swingGUI(app);
        
        appl = new AppListener(this, app);
        
        dialog.setVisible(true);
}    

    private void swingGUI(App a) {
    
        dialog = new JDialog(a, true);
        dialog.setBounds(200, 200, 300, 200);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.X_AXIS));
        url = new JTextField("jdbc:mysql://127.0.0.1:3306/j30");
        user = new JTextField("root");
        password = new JTextField("password");
        user = new JTextField("root");
        list = new JList();
        btn = new JButton("OK");
        tryCon = new JButton("Try Connect");
        panel.add(url, BorderLayout.NORTH);
        panelCenter.add(user);
        panelCenter.add(password);
        panel.add(panelCenter, BorderLayout.WEST);
        panel.add(list, BorderLayout.CENTER);
        panelSouth.add(tryCon);
        panelSouth.add(btn);
        panel.add(panelSouth, BorderLayout.SOUTH);
        dialog.add(panel);
    }


    }
    

