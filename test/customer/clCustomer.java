/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

import myClass.dataCon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import myClass.IdAndName;



/**
 *
 * @author Nemesis
 */
public class clCustomer {
    
    
    static String sql="";
    static boolean success=false;
    static PreparedStatement prepareStmt;
    static Statement stmt;
    static ResultSet rs;
    
    public static boolean insert(String... data){
        
        success=true;
        
        sql="insert into customers(name,gender,tel) values(?,?,?)";
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            
            for(int i=0;i<data.length;i++){
                prepareStmt.setString(i+1, data[i]);
            }
            
            prepareStmt.execute();
            
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        
        
        return success;
    }
    
    public static void getCustomerList(DefaultTableModel modelCustomer){
        
        sql="select customerid,name,gender,tel from customers";
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    String[] st=new String[4];
                    
                    for(int i=0;i<st.length;i++){
                        st[i]=rs.getString(i+1);
                    }
                    
                    modelCustomer.addRow(st);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }

    static boolean update(String id,String... data) {
        
        success=true;
        
        sql="update customers set name=?,gender=?,tel=? where customerid="+id+";";
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            
            for(int i=0;i<data.length;i++){
                prepareStmt.setString(i+1, data[i]);
            }
            
            prepareStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
            
        }
        
        return success;
    }
    
    static boolean delete(String id) {
        
        success=true;
        
        sql="delete from customers where customerid="+id+";";
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            
            
            
            prepareStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
            
        }
        
        return success;
    }
    
    public static void getModelCBCustomerList(DefaultComboBoxModel modelCBCustomer){
        
        sql="select customerid,name from customers";
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    IdAndName customer=new IdAndName(rs.getString(1),rs.getString(2));
                    
                    modelCBCustomer.addElement(customer);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
}
