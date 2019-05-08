/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Training;

import static crm.clCRM.insertDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import myClass.IdAndName;
import myClass.IdAndValue;
import myClass.clFunction;
import myClass.currentEmployee;
import myClass.dataCon;

/**
 *
 * @author Nemesis
 */
public class clTraining {
    static boolean success;
    static String sql;
    
    static PreparedStatement prepareStmt;
    static Statement stmt;
    static ResultSet rs;
    
    public static void getModelTrainingList(DefaultTableModel modelTrainingList){
        modelTrainingList.setRowCount(0);
        sql="select \n" +
        "	trainingId,\n" +
        "    DATE_FORMAT(dateCreated,'%d/%m/%Y') dateCreated,\n" +
        "    createdby idCreatedBy,\n" +
        "    i.name createdBy,\n" +
        "    DATE_FORMAT(dateStart,'%d/%m/%Y') dateStart,\n" +
        "    DATE_FORMAT(dateEnd,'%d/%m/%Y') dateEnd,\n" +
        "    tr.empId traineeId,\n" +
        "    trI.name trainee,\n" +
        "    tType.trainingTypeId,\n" +
        "    tType.TrainingType,\n" +
        "     duration,\n" +
        "     t.status,\n" +
        "     if(t.status='Pending' and dateEnd<=now(),1,0) needUpdate\n" +
        " from training t join employees e on t.createdBy=e.empId \n" +
        "	join interviewees i using(intervieweeId) \n" +
        "    join employees tr on tr.empId=t.empId \n" +
        "    join interviewees trI on trI.intervieweeId=tr.intervieweeID\n" +
        "    join trainingTypes tType on tType.TrainingTypeId=t.trainingTypeId"
        + " order by needUpdate desc;";
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
            
            String formatPattern="dd/MM/yyyy";
            
            if(rs.first()){
                
                do{
                    
                    
                    
                    String id=rs.getString(1);
                    
                    String stDateCreated=rs.getString(2);
                    
                    IdAndName creator=new IdAndName(rs.getString(3),rs.getString(4));
                    
                    
                    Date dStart=clFunction.formatStringTodate(rs.getString(5), formatPattern);
                   
                    Date dEnd=clFunction.formatStringTodate(rs.getString(6), formatPattern);
                    
                    
                    IdAndName trainee=new IdAndName(rs.getString(7),rs.getString(8));
                    
                    IdAndValue trainingType=new IdAndValue(rs.getString(9),rs.getString(10),rs.getString(11));
                    
                    String status=rs.getString(12);
                    
                    String needUpdate=rs.getString(13);
                    
                    Object[] obj={id,stDateCreated,creator,dStart,dEnd,trainee,trainingType,status,needUpdate};
                    
                    modelTrainingList.addRow(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public static boolean insert(String... data){
        success=true;
        sql="insert into training (DateStart,DateEnd,EmpId,TrainingTypeID,status,DateCreated,createdBy) values(?,?,?,?,?,now(),"+currentEmployee.getId()+")";
        
        
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
        sql="delete from training where trainingId="+id;
        
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
           
            
            prepareStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        
        return success;
    }
    
    
    public static boolean checkOverlapDb(String empId,String stStart,String stEnd,String where){
        boolean isOverlap=false;
        
        
        sql="select DATE_FORMAT(dateStart,'%d/%m/%Y') dateTimeStart ,DATE_FORMAT(dateEnd,'%d/%m/%Y') dateTimeEnd from training where not ('"+stStart+"'>= dateEnd or '"+stEnd+"' <= dateStart) and empId="+empId+" "+where;                       
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
            
            
            
            if(rs.first()){
                
                String st="";
                
                
                do{
                    st+=rs.getObject(1)+" - "+rs.getObject(2)+"\n";
                }while(rs.next());
                
                
                JOptionPane.showMessageDialog(null, "Date start and date end are overlapping with the following existed Training:\n"+st,"",JOptionPane.WARNING_MESSAGE);
                
                
                isOverlap=true;
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
        return isOverlap;
    }
    
    public static boolean update(String trainingId,String... data){
        success=true;
        
        //TrainingId,DateStart,DateEnd,EmpId,TrainingTypeID,DateCreated,createdBy,status

        sql="update training set DateStart=?,DateEnd=?,EmpId=?,TrainingTypeID=?,status=? where trainingId="+trainingId;
        
        
         try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            
            for(int i=0;i<data.length;i++){
                prepareStmt.setString(i+1,data[i]);
            }
            
            
            prepareStmt.execute();
            
            
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        return success;
        
    }
}
