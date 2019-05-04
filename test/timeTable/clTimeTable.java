/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeTable;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import myClass.IdAndName;
import myClass.dataCon;

/**
 *
 * @author Nemesis
 */
public class clTimeTable {
    static String sql;
    
    
    static Statement stmt;
    static ResultSet rs;
    
    public static void getTimeTableList(String stFrom,String stTo,DefaultTableModel modelTimeTable){
        modelTimeTable.setRowCount(0);
        sql="select d.empId,i.name,c.customerId,c.name,DATE_FORMAT(d.dateStart,'%d/%m/%Y %H:%i') dateStart,DATE_FORMAT(d.dateEnd,'%d/%m/%Y %H:%i') dateEnd,location\n" +
"from opportunityDetails d join employees using (empId) join interviewees i using(intervieweeId) join opportunitys using(opportunityId) join customers c using(customerId) join actions using(actionId) \n" +
"where approveBy is not null and ActionType='Meeting' and date(dateStart)>='"+stFrom+"' and date(dateEnd)<='"+stTo+"'"; 
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            //"#", "Employee", "Customer", "Date Start", "Date End", "Location"

            
            if(rs.first()){
                int i=1;
                do{
                    
                    Object[] obj=new Object[6];
                    
                    obj[0]=i+"";
                    IdAndName employee=new IdAndName(rs.getString(1),rs.getString(2));
                    IdAndName customer=new IdAndName(rs.getString(3),rs.getString(4));
                    
                    obj[1]=employee;
                    obj[2]=customer;
                    
                    obj[3]=rs.getString(5);
                    obj[4]=rs.getString(6);
                    obj[5]=rs.getString(7);
                    
                    
                    
                    
                    
                    
                    modelTimeTable.addRow(obj);
                    
                    i++;
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
}
