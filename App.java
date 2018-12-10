
package HomeTask;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class App extends JFrame{

    static String querySet = "Select * from ";
    DefaultTableModel tableModel;
    JTable table;
    JMenu connect;
    JMenu query;
    JMenu close;
    static String urlSet, userSet, passwordSet, dbnameSet;
    AppListener app;
    
    App() throws SQLException {
        setBounds(100, 100, 700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        menuSet();
        
        JPanel panel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(0,0);
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        setContentPane(panel);

        setVisible(true);
        
        app = new AppListener(this);
        }
    
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new App();
                } catch (SQLException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });      
    }
    
    private void menuSet() {
        JMenuBar menuBar = new JMenuBar();
        connect = new JMenu("Connect");
        query = new JMenu("Query");
        close = new JMenu("Close");
        menuBar.add(connect);  
        menuBar.add(query);
        menuBar.add(close);
        this.setJMenuBar(menuBar);
    }
   
    protected void tableSet(String query, String str) throws SQLException {
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();
        
            try (Connection conn = (Connection) DriverManager.getConnection(urlSet, userSet, passwordSet);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query + str)) {
                
            tableModel = (DefaultTableModel) table.getModel();
            tableModel.setColumnCount(0);

            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                columnNamesVector.add( rs.getMetaData().getColumnName(i) );
            }
            while (rs.next()) {
                Vector row = new Vector();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add( rs.getObject(i) );
                }
                dataVector.add(row );
            }
            table.setModel(new DefaultTableModel(dataVector, columnNamesVector));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
}
   
    
}