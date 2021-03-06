/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA;


import CMS.DB;
import static CMS.DB.con;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication21.AutoComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

public void Query(String tb) {
    
     try{
        
         
            String sql = tb ;
            st = con.createStatement();
           st.executeUpdate(sql);
       //    JOptionPane.showConfirmDialog(null, "EXECUTE SUCCESS");  
          
           st.close();
        }catch(SQLException e){
         try {
             con.rollback();
         } catch (SQLException ex) {
             Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
         }
            JOptionPane.showMessageDialog(null, e.getMessage());
       
        }
}

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
    
    public void DisplayName(AutoComboBox cb, String sql){
        try {
            cb.removeAllItems();
            st = con.createStatement();
            rs = st.executeQuery(sql);
                 while (rs.next()) {                
                String st =rs.getString(1).toString();
     
                cb.addItem(st);
            }
        } catch (Exception e) {
        }
    }
    
     public void DisplayId(String sql,JTextField txtid){
        try {
           
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();  
                  while (rs.next()) {  
                   txtid.setText(rs.getString(1)+"");
                  } 
        } catch (Exception e) {
        }
    }
     
     public void AccessQuery(String tb) {
    
     try{
        
           con.setAutoCommit(false);
            String sql = tb ;
            st = con.createStatement();
           st.executeUpdate(sql);
        //   JOptionPane.showConfirmDialog(null, "EXECUTE SUCCESS");  
           con.setAutoCommit(true);
        }catch(Exception e){
         try {
             con.rollback();
         } catch (SQLException ex) {
             Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
         }
            JOptionPane.showMessageDialog(null, e.getMessage());
       
        }
}
    
   
    
}
