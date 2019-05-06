/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainningType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import myClass.IdAndValue;
import myClass.dataCon;

/**
 *
 * @author Nemesis
 */
public class clTrainingType {
    static boolean success;
    static String sql;
    
    static PreparedStatement prepareStmt;
    static Statement stmt;
    static ResultSet rs;
    
    
    
    
    public static boolean insert(String... data){
        success=true;
        sql="insert into trainingTypes  (trainingType,description,duration) values(?,?,?)";
        
        
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
    
    public static void getModelCbTrainingType(DefaultComboBoxModel modelCbTrainingType){
        modelCbTrainingType.removeAllElements();
        
        sql="select trainingTypeId,trainingType,Duration from trainingTypes;";
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    IdAndValue obj=new IdAndValue(rs.getString(1),rs.getString(2),rs.getString(3));
                    
                    modelCbTrainingType.addElement(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public static void getTrainingTypeList(DefaultTableModel modelTrainingType){
        
        sql="select * from trainingTypes";
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    String[] st=new String[4];
                    
                    for(int i=0;i<st.length;i++){
                        st[i]=rs.getString(i+1);
                    }
                    
                    modelTrainingType.addRow(st);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    static boolean delete(String id) {
        
        success=true;
        
        sql="delete from trainingTypes where trainingTypeId="+id+";";
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            
            
            prepareStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        }
        
        return success;
    }
    
    
    static boolean update(String id,String... data) {
        
        success=true;
      
        sql="update trainingTypes set trainingType=?,description=?,duration=? where trainingTypeId="+id+";";
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            
            for(int i=0;i<data.length;i++){
                prepareStmt.setString(i+1, data[i]);
            }
            
            prepareStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        }
        
        return success;
    }
    
   
}
