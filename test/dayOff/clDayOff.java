/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dayOff;

import myClass.IdAndName;
import myClass.dataCon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nemesis
 */
public class clDayOff {
    static String sql="";
    static boolean success=false;
    static PreparedStatement prepareStmt;
    static Statement stmt;
    static ResultSet rs;
    
    
    static Map<String, String> map = new HashMap<String, String>();
    
    
    public static void getModelDayOffList(DefaultTableModel modelDayOff){
        sql="select SceheduleDayOff,date_format(StartDate,'%d/%m/%Y'), Date_Format(EndDate,'%d/%m/%Y'),dayId,e.EmpId,name from sceheduledayoffs s join employees e on e.empId=s.empId join interviewees i on i.IntervieweeId=e.IntervieweeId;";
        
        map.put("1","Monday");
        map.put("2","TuesDay");
        map.put("3","Wednesday");
        map.put("4","Thursday");
        map.put("5","Friday");
        map.put("6","Saturday");
        map.put("7","Sunday");
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                String id,dateStart,dateEnd,dayId;
                IdAndName employee;
                do{
                    
                    employee=new IdAndName(rs.getString(5),rs.getString(6));
                    
                    id=rs.getString(1);
                    
                    dateStart=rs.getString(2);
                    
                    dateEnd=rs.getString(3);
                    
                    dayId=rs.getString(4);
                    
                    
                    for(String key : map.keySet()){
                        dayId=dayId.replace(key, map.get(key));
                        
                        
                    }
                    
                    
                    Object [] obj={id,employee,dateStart,dateEnd,dayId};
                    
                    modelDayOff.addRow(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
    public static void getModelCbEmployee(DefaultComboBoxModel modelCbEmployee,String where){
       sql="select EmpId,name from employees e join interviewees i on e.IntervieweeId=i.IntervieweeId where " +where+ ";";
       
       
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    IdAndName obj=new IdAndName(rs.getString(1),rs.getString(2));
                    modelCbEmployee.addElement(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
   
    public static boolean checkIfOverlapDB(String stStart,String stEnd,String empId,String where){
        
       
     
        
        String sql="select startDate,endDate from sceheduledayoffs where not ('"+stStart+"'>= endDate or '"+stEnd+"' <= startDate) and empId="+empId+" and "+where+" ;";
        
        boolean isOverlap=false;
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                String st="";
                
                
                do{
                    st+=rs.getObject(1)+" - "+rs.getObject(2)+"\n";
                }while(rs.next());
                
                
                JOptionPane.showMessageDialog(null, "start date and end date are overlapping with the following existed dayOff:\n"+st);
                isOverlap=true;
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
        return isOverlap;
        
        
    }
    
    
    public static boolean insert(String... data){
        
        success=true;
        
        sql="insert into sceheduledayoffs(startDate,endDate,dayId,empId) values(?,?,?,?)";
        
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

    static boolean update(String dayOffId, String empid, String... data) {
       success=true;
        
        sql="update sceheduledayoffs set empId="+empid+", startDate=?,endDate=?,dayId=? where SceheduleDayOff="+dayOffId+";";
        
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
    
    static boolean delete(String dayOffId) {
       success=true;
        
        sql="delete from sceheduledayoffs where SceheduleDayOff="+dayOffId+";";
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            
            
            
            prepareStmt.execute();
            
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }

        return success;
    }
}
