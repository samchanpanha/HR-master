/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author panha
 */
public class AC {
public  Statement st;
public  ResultSet rs;
public PreparedStatement pst;
public Connection con = ConAccess.getDBConnection();
    public void showDataInTable(JTable tb,String q,DefaultTableModel dm)
{
                try {
                    
                   st = con.createStatement();
                    rs = st.executeQuery(q);    
                   // get columns info
                   ResultSetMetaData rsmd = rs.getMetaData();
                   int columnCount = rsmd.getColumnCount();   
                   // for changing column and row model
                   dm = (DefaultTableModel) tb.getModel();    
                   // clear existing columns 
                   dm.setColumnCount(0);
                   // add specified columns to table
                   for (int i = 1; i <= columnCount; i++ ) {
                       dm.addColumn(rsmd.getColumnName(i));
                   }   
                   // clear existing rows
                   dm.setRowCount(0);
                   // add rows to table
                   while (rs.next()) {
                       String[] a = new String[columnCount];
                       for(int i = 0; i < columnCount; i++) {
                           a[i] = rs.getString(i+1);
                       }
                   dm.addRow(a);
                   }
                   dm.fireTableDataChanged();

                   // Close ResultSet and Statement
                   rs.close();
                   st.close();
               } catch (Exception ex) { 
                   JOptionPane.showMessageDialog(null,"ERROR");
               }
}
    
}
