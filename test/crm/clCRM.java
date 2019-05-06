/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class clCRM {
    
    static String sql="";
    static boolean success=false;
    static PreparedStatement prepareStmt;
    static Statement stmt;
    static ResultSet rs;
    
    
    
    
    static void getCRMList(DefaultTableModel modelCRMList){
        sql="select OpportunityId,Description,DATE_FORMAT(dateCreated,'%d/%m/%Y') dateCreated,Priority, Done,e.EmpId, i.name 'employee',c.CustomerId,c.name 'customer' from opportunitys o join customers c on o.CustomerId=c.CustomerId join employees e on e.EmpId=o.empId join interviewees i on e.intervieweeId=i.intervieweeId;";        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    Object[] obj=new Object[7];
                    
                    for(int i=0;i<5;i++){
                        obj[i]=rs.getString(i+1);
                    }
                    
                    IdAndName employee=new IdAndName(rs.getString(6),rs.getString(7));
                    IdAndName customer=new IdAndName(rs.getString(8),rs.getString(9));
                    
                    obj[5]=employee;
                    obj[6]=customer;
                    
                    
                    modelCRMList.addRow(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
    public static void getModelCbEmployeeFree(DefaultComboBoxModel modelCbEmployee,Date dStart,Date dEnd){
        modelCbEmployee.removeAllElements();
        String formatPattern="yyyy-MM-dd HH:mm";
        
        String stStart=clFunction.getFormattedDate(dStart, formatPattern);  
        String stEnd=clFunction.getFormattedDate(dEnd, formatPattern);  
        sql="select e.empId,name \n" +
            "from employees e join interviewees i using (intervieweeId)\n" +
            "where e.EmpId not in(\n" +
            "	select o.EmpID from opportunitydetails o \n" +
            "		where approveBy is not null and (\n" +
            "        (dateStart between '"+stStart+"' and '"+stEnd+"') or \n" +
            "        (dateEnd between '"+stStart+"' and '"+stEnd+"') or \n" +
            "        ('"+stStart+"' between dateStart and dateEnd)));";
        System.out.println("--------");
        System.out.println(sql);
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    IdAndName employee=new IdAndName(rs.getString(1),rs.getString(2));
                    
                    modelCbEmployee.addElement(employee);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
        
    }
    
    
    static String formatPattern="dd/MM/yyyy HH:mm";
    public static void getModelCRMDetailForEdit(String opportunityId,DefaultTableModel modelCRMDetail){
        
        
        //"#", "Employee", "Action", "Date Start", "Date End", "Location", "Description"

        sql="select e.EmpId,i.Name,o.Actionid,a.ActionType,DATE_FORMAT(o.dateStart,'%d/%m/%Y %H:%i') dateStart,DATE_FORMAT(o.dateEnd,'%d/%m/%Y %H:%i') dateEnd,o.Location,o.descrption,ifnull(DATE_FORMAT(approveDate,'%d/%m/%Y %H:%i'),'') approveDate,approveBy,appInter.name\n" +
"from opportunityDetails o join actions a on o.Actionid=a.Actionid join employees e on e.EmpId=o.empId join interviewees i on e.intervieweeId=i.intervieweeId left join employees app on app.empId=o.approveBy left join interviewees appInter on app.IntervieweeId=appInter.IntervieweeId where OpportunityId="+opportunityId+";";
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                int autoNumber=1;
                do{
                    
                    Object[] obj=new Object[9];
                    
                    IdAndName employee=new IdAndName(rs.getString(1),rs.getString(2));
                    IdAndName action=new IdAndName(rs.getString(3),rs.getString(4));
                    
                    obj[0]=autoNumber+"";
                    obj[1]=employee;
                    obj[2]=action;
                    
                    
                    Date d=clFunction.formatStringTodate(rs.getString(5), formatPattern);
                    obj[3]=d;
                    
                    d=clFunction.formatStringTodate(rs.getString(6), formatPattern);
                    obj[4]=d;
                    
                    obj[5]=rs.getString(7);
                    obj[6]=rs.getString(8);
                    
                    if(rs.getString(9).equals("")){
                        obj[7]="";
                    }else{
                        obj[7]= clFunction.formatStringTodate(rs.getString(9), formatPattern);
                    }
                    
                    
                    IdAndName approver=new IdAndName(rs.getString(10),rs.getString(11));
                    
                    obj[8]=approver;
                    
                    modelCRMDetail.addRow(obj);
                    
                    autoNumber++;
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    
    public static void getModelCBAction(DefaultComboBoxModel modelCBAction){
       sql="select * from actions;";
       
       
        
        try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    IdAndName obj=new IdAndName(rs.getString(1),rs.getString(2));
                    modelCBAction.addElement(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    
    public static boolean insert(String opportunityId,String[] master,List<String[]> detail ){
        success=true;
        sql="INSERT INTO `opportunitys`(`Description`, `Priority`, `Done`, `EmpId`, `CustomerId`,`DateCreated`) "
                + "VALUES (?, ?, ?, ?, ?, now())";
        
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            for(int i=0;i<master.length;i++){
                prepareStmt.setString(i+1, master[i]);
            }
            
            
            
            prepareStmt.execute();
            
           
            
            insertDetail(opportunityId, detail);
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        
        return success;
        
        
    }
    
    public static boolean delete(String opportunityId){
        success=true;
        sql="delete from opportunitys where OpportunityId="+opportunityId;
        
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            prepareStmt.execute();
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        
        return success;
    }
    
    public static boolean insertDetail(String opportunityId, List<String[]> detail){
        success=true;
        
        try{
            for(String [] st : detail){
                
                //"#", "Employee", "Action", "Date Start", "Date End", "Location", "Description"
                
                sql="INSERT INTO `opportunitydetails`(`OpportunityId`,`EmpID`, `Actionid`,`dateStart`,`dateEnd`, `Location`, `descrption`) "
                    + "VALUES ('"+opportunityId+"', ?, ?, ?, ?, ?, ?)";
                
                
                prepareStmt=dataCon.getCon().prepareStatement(sql);
                
                for(int i=0;i<st.length-2;i++){
                    prepareStmt.setString(i+1, st[i]);
                }
                
                String detailId=clFunction.getLastId("opportunitydetails");
                
                prepareStmt.execute();

                
                if(!st[st.length-1].equals("")){
                    
                    sql="update opportunitydetails set approveDate='"+st[st.length-2]+"',approveBy="+st[st.length-1]+" where OpportunityDetailId="+detailId;
                    prepareStmt=dataCon.getCon().prepareStatement(sql);
                    prepareStmt.execute();
                    
                    
                   
                    
                }
                
            }
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        
        return success;
    }
    
    
    public static boolean update(String opportunityId,String[] master,List<String[]> detail ){
        success=true;
        sql="delete from opportunityDetails where OpportunityId="+opportunityId;
        
        
         try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            prepareStmt.execute();
            
            //"OpportunityId", "Description", "Date Created", "Priority", "Done", "Employee", "Customer"

            
            sql="update opportunitys set Description=?,Priority=?,done=?,CustomerId=? where OpportunityId="+opportunityId;
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            
            for(int i=0;i<master.length;i++){                
                prepareStmt.setString(i+1, master[i]);
            }
            prepareStmt.execute();
            
            insertDetail(opportunityId, detail);
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
        
        return success;
        
    }
    
}
