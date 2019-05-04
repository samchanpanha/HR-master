/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myClass;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Nemesis
 */
public class clFunction {
    public static String getLastId(String tableName){
        
        return dataCon.one_cell_value("SELECT `AUTO_INCREMENT`\n" +
        "FROM  INFORMATION_SCHEMA.TABLES\n" +
        "WHERE TABLE_SCHEMA = 'db_hrm'\n" +
        "AND   TABLE_NAME   = '"+tableName+"';")+"";
        
    }
    
    public static void setReadOnlyTextField(JTextField txt){
        txt.setEditable(false);
        txt.setBackground(Color.WHITE);
    }
    
    public static boolean checkIfTextFieldEmpty(JTextField... txt){
        
        for(JTextField t :txt){
            if(t.getText().equals("")){
                return true;
            }
            
        }
        
        return false;
    }
    
     public static void prepareDialog(JDialog dialog,JPanel panel,boolean isResizable){
        
        
        dialog.setResizable(isResizable);
        dialog.setModal(true);
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
    }
    
     
    public static void showInternalFrame(JDesktopPane pane,JInternalFrame internal){
        internal.setVisible(true);
       
        
        if(internal.isIcon()){
            try{
                internal.setIcon(false);
                
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            
        }
        
        if(internal.getParent()==null){
            prepareInternalFrame(internal);
            pane.add(internal);
        }
        
        
        
    }
     
    public static void prepareInternalFrame(JInternalFrame internal){
//        BasicInternalFrameUI ui = (BasicInternalFrameUI)internal.getUI();
//        Container north = (Container)ui.getNorthPane();
//        north.remove(0);
//        north.validate();
//        north.repaint();
        
        internal.setClosable(true);
        internal.setIconifiable(true);
        internal.setMaximizable(true);
        internal.setResizable(true);
        
        
        
        JComponent c = (BasicInternalFrameTitlePane)((BasicInternalFrameUI) internal.getUI()).getNorthPane();
        
        int titleHeight=23;
        c.setPreferredSize(new Dimension(c.getPreferredSize().width,titleHeight));
    }
     
     public static void addPopUpToControl(JPopupMenu popupMenu,JComponent component){
        
        
        MouseAdapter mouse=new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3){
                    popupMenu.show(component, e.getX(), e.getY());
                    
                }
            }
            
        };
        
        component.addMouseListener(mouse);
    }
    
    
    public static void changeLookTable(JTable tb){
        JTableHeader header = tb.getTableHeader();
        header.setPreferredSize(new Dimension(100, 30));
        
        Font f=new Font("Roboto",Font.PLAIN ,13);
        
        header.setFont(f);
        
        tb.setRowHeight(40);
    }
    
    public static void clearTextField(JTextField... txt){
        for(JTextField t :txt){
           t.setText("");
            
        }
    }
    
    public static String getFormattedDate(Date d,String formatPattern){
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);

        String dateString = format.format(d);
        return dateString;
    }
    
     public static Date formatStringTodate(String d,String formatPattern){
        Date date=null;
        try{
            date=new SimpleDateFormat(formatPattern).parse(d);  
        }catch(ParseException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        
        }
        
        return date;
    }
    
    
    static Date resetTime(Date d){
        Calendar c=Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        return c.getTime();
    }
    
    static Date resetSecond(Date d){
        Calendar c=Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        return c.getTime();
    }
     
    public static boolean checkIntervalHourAndMinute(Date dateStart,Date dateEnd,long number,long limiter,boolean ignoreTime){
        
        if(ignoreTime){
            dateStart=resetTime(dateStart);
            dateEnd=resetTime(dateEnd);
        }else{
            dateStart=resetSecond(dateStart);
            dateEnd=resetSecond(dateEnd);
        }
        
        long diffInMillies = (dateEnd.getTime() - dateStart.getTime())/number;
        
//        System.out.println(dateStart +"----"+ dateEnd);
//        
//        System.out.println(diffInMillies +" "+ limiter);
        

        return diffInMillies<limiter;
        
    }
    
    
    public static void resetAutoNumber(int startRow,DefaultTableModel model){
        
        for(int i=startRow;i<model.getRowCount();i++){
            model.setValueAt(i+1, i, 0);
        }
        
    }
    
   
    public static void updateTableModel(DefaultTableModel model,int selectedRow,String[] data){
        for(int i=1;i<model.getColumnCount();i++){
            model.setValueAt(data[i-1], selectedRow, i);
        }
    }
    
}
