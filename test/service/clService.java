/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import myClass.IdAndName;
import myClass.clFunction;
import myClass.dataCon;

/**
 *
 * @author Nemesis
 */
public class clService {
    
    static String sql="";
    static boolean success=false;
    static PreparedStatement prepareStmt;
    static Statement stmt;
    static ResultSet rs;
    
    
    static void getServiceList(DefaultTableModel modelCRMList){
        sql="select serviceId,DATE_FORMAT(DateCreated,'%d/%m/%Y') dateCreated,Total,e.EmpId, i.name 'employee',c.CustomerId,c.name 'customer' from services s join customers c on s.CustomerId=c.CustomerId join employees e on e.EmpId=s.empId join interviewees i on e.intervieweeId=i.intervieweeId;";        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    

                    
                    Object[] obj=new Object[5];
                    
                    for(int i=0;i<3;i++){
                        obj[i]=rs.getString(i+1);
                    }
                    
                    IdAndName employee=new IdAndName(rs.getString(4),rs.getString(5));
                    IdAndName customer=new IdAndName(rs.getString(6),rs.getString(7));
                    
                    obj[3]=employee;
                    obj[4]=customer;
                    
                    
                    modelCRMList.addRow(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    static String formatPattern="dd/MM/yyyy HH:mm";
    public static void getModelServiceDetail(String serviceId,DefaultTableModel modelServiceDetail){
        
        
        //"#", "Service", "Price"

        sql="select s.ServiceTypeId,ServiceType,price from servicedetails s join servicetypes t on s.ServiceTypeId=t.ServiceTypeId where serviceId="+serviceId;
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                int autoNumber=1;
                double price;
                String stPrice;
                DecimalFormat df=new DecimalFormat("#,##0.00");
                do{
                    
                    Object[] obj=new Object[3];
                    
                    IdAndName service=new IdAndName(rs.getString(1),rs.getString(2));
                    price=Double.parseDouble(rs.getString(3));
                    stPrice=df.format(price);
                    
                    
                    obj[0]=autoNumber;
                    obj[1]=service;
                    obj[2]=stPrice;
                    
                    
                    modelServiceDetail.addRow(obj);
                    
                    autoNumber++;
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    
    
    
    
    public static boolean insert(String serviceId,String[] master,List<String[]> detail ){
        success=true;
        sql="INSERT INTO `services`(`DateCreated`, `Total`, `EmpId`, `CustomerId`,`ServiceId`) VALUES (now(), ?, ?, ?, '"+serviceId+"' )";
        
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            for(int i=0;i<master.length;i++){
                prepareStmt.setString(i+1, master[i]);
            }
            
            
            
            prepareStmt.execute();
            
           
            
            insertDetail(serviceId, detail);
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        
        return success;
        
        
    }
    
    public static boolean delete(String serviceId){
        success=true;
        sql="delete from services where serviceId="+serviceId;
        
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            prepareStmt.execute();
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        
        return success;
    }
    
    public static boolean insertDetail(String serviceId, List<String[]> detail){
        success=true;
        
        try{
            for(String [] st : detail){
                
                //"#", "Employee", "Action", "Date Start", "Date End", "Location", "Description"
                
                sql="INSERT INTO `hrm`.`servicedetails`(`ServiceId`, `ServiceTypeId`, `Price`) VALUES ('"+serviceId+"', ?, ?)";
                
                
                prepareStmt=dataCon.getCon().prepareStatement(sql);
                
                for(int i=0;i<st.length;i++){
                    prepareStmt.setString(i+1, st[i]);
                }
                
                 prepareStmt.execute();

            }
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        
        return success;
    }
    
    
    public static boolean update(String serviceId,String[] master,List<String[]> detail ){
        success=true;
        sql="delete from serviceDetails where serviceId="+serviceId;
        
        
         try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            prepareStmt.execute();
            
            //"ServiceId", "Date Created", "Total", "Created By", "Customer"

            
            sql="update services set total=?,CustomerId=? where serviceId="+serviceId;
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            
            for(int i=0;i<master.length;i++){                
                prepareStmt.setString(i+1, master[i]);
            }
            prepareStmt.execute();
            
            insertDetail(serviceId, detail);
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        return success;
        
    }
    
}
