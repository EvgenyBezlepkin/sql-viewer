/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeTask;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Евгений
 */
public class AppListener{
App app;
JDialogSet jds;
JDialogQuery jdq;
    
    public AppListener(App a) {
        app= a;
        
        app.connect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    jds = new JDialogSet(app);
                    app.tableSet(App.querySet, App.dbnameSet);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
            app.close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                app.dispose();
            }});
            
            app.query.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                jdq = new JDialogQuery(app);
                app.tableSet(jdq.queryBtn.getText(), "");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }});
    }

    public AppListener(JDialogSet jds, App a) {
        this.jds = jds;
        this.app=a;
        
            jds.btn.addActionListener((ActionEvent e) -> {
            jds.dialog.dispose();
        });
        
        jds.tryCon.addActionListener((ActionEvent e) -> {
            App.urlSet = jds.url.getText();
            App.userSet = jds.user.getText();
            App.passwordSet = jds.password.getText();
            
            try 
            (Connection conn = (Connection) DriverManager.getConnection(App.urlSet, App.userSet, App.passwordSet);) {
            DatabaseMetaData dbmd = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);
            jds.dlm = new DefaultListModel<String>();
            while (rs.next()) {
                jds.dlm.add(0, rs.getString("TABLE_NAME"));
            }
            
            jds.list.setModel(jds.dlm);
            
            jds.list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent evt) {        
            if (!evt.getValueIsAdjusting()) {
            App.dbnameSet = jds.list.getSelectedValue().toString();
            }}});

        }   catch (SQLException ex) { 
                JOptionPane.showMessageDialog(null, ex);
            }          
        });
    } 
    
    
        public AppListener(JDialogQuery jdq, App a) {
        this.jdq = jdq;
        this.app=a;
    System.out.println("+");
        jdq.btn2.addActionListener((e) -> {
            System.out.println("_");
            jdq.dialogQuery.dispose();
        });
        
        }
}