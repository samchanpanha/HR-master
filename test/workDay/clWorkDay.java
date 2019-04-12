/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workDay;

import myClass.IdAndName;
import myClass.clFunction;
import myClass.dataCon;
import myClass.myTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Nemesis
 */
public class clWorkDay {
    
    
    static String sql="";
    static boolean success=false;
    static PreparedStatement prepareStmt;
    static Statement stmt;
    static ResultSet rs;

    static void getWorkDayList(DefaultTableModel modelWorkDay) {
        
       sql="select workdayId,w.Description,d.dayId,d.day,Date_Format(timeStart,'%H:%i') 'timeStart',da.dayId,da.day,Date_Format(timeEnd,'%H:%i') 'timeEnd',Date_Format(breaktimeStart,'%H:%i') 'breakStart',Date_Format(BreakTimeEnd,'%H:%i') 'breakEnd',workHour,workMinute,TimeShiftId,timeshifts.description\n" +
"from workdays w join timeShifts using (timeshiftId) left join days d on w.DayStart=d.dayId left join days da on w.DayEnd=da.DayId;";
       
       try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                String workDayId;
                String description;
                IdAndName dayStart=new IdAndName();
                myTime timeStart=new myTime();
                IdAndName dayEnd=new IdAndName();
                myTime timeEnd=new myTime();
                myTime breakStart=new myTime();
                myTime breakEnd=new myTime();
                int workHour;
                int workMinute;
                
                IdAndName timeShift=new IdAndName();
                
                

                String timePattern="HH:mm";
                DecimalFormat df=new DecimalFormat("00");
                
                
                
                do{
                    
                    workDayId=rs.getString(1);
                    description=rs.getString(2);
                    
                    dayStart.IdAndName(rs.getString(3), rs.getString(4));
                    timeStart.myTime(clFunction.formatStringTodate(rs.getString(5), timePattern));
                    
                    
                    dayEnd.IdAndName(rs.getString(6), rs.getString(7));
                    timeEnd.myTime(clFunction.formatStringTodate(rs.getString(8), timePattern));
                    
                    breakStart.myTime(clFunction.formatStringTodate(rs.getString(9), timePattern));
                    breakEnd.myTime(clFunction.formatStringTodate(rs.getString(10), timePattern));
                    
                    workHour=rs.getInt(11);
                    
                    workMinute=rs.getInt(12);
                    
                    timeShift.IdAndName(rs.getString(13), rs.getString(14));
                    
                    

                    Object [] obj={workDayId,description,dayStart,timeStart,dayEnd,timeEnd,breakStart,breakEnd,df.format(workHour)+":"+ df.format(workMinute),timeShift};
                    
                    modelWorkDay.addRow(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
       
    }
    
    static boolean update(String id,String... data) {
        
        success=true;
        
        sql="update workdays set description=?,dayStart=?,dayEnd=?,timeStart=?,timeEnd=?,breakTimeStart=?,breakTimeEnd=?,WorkHour=?,WorkMinute=?,timeShiftId=? where workDayId="+id+";";
        
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
    
    
    static boolean delete(String workid) {
        
        success=true;
        
        sql="delete from workDays where workdayId="+workid+";";
        
        try{
            prepareStmt=dataCon.getCon().prepareStatement(sql);
            
            
            
            prepareStmt.execute();
            
        }catch(SQLException ex){
            success=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
            
        }
        
        return success;
    }
    
    
    
    static void getTimeShiftList(DefaultComboBoxModel modelCbTimeShift){
        sql="select timeShiftId,description from timeshifts;";
       
       try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    IdAndName obj=new IdAndName(rs.getString(1), rs.getString(2));
                    
                    
                    modelCbTimeShift.addElement(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    static void getDayList(DefaultComboBoxModel modelCbDay){
        sql="select dayId,day from days;";
       
       try {
            stmt=dataCon.getCon().createStatement();
            rs=stmt.executeQuery(sql);
           
            
            if(rs.first()){
                
                do{
                    
                    IdAndName obj=new IdAndName(rs.getString(1), rs.getString(2));
                    
                    
                    modelCbDay.addElement(obj);
                    
                }while(rs.next());
                        
            }
            
            rs.close();
            stmt.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static boolean insert(String... data){
        
        success=true;
        
        sql="insert into workdays(description,dayStart,dayEnd,timeStart,timeEnd,breakTimeStart,breakTimeEnd,workHour,workMinute,timeShiftId) values(?,?,?,?,?,?,?,?,?,?)";
        
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
}
