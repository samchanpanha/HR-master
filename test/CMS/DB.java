/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMS;


import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javaapplication21.AutoComboBox;
import javafx.embed.swing.JFXPanel;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author panha
 */
public class DB {
Connection con = ConMysql.getDBConnection(); 
Statement st;
ResultSet rs;
PreparedStatement pst;


public static String path;

public void Query(String tb) {
    
     try{
        
           con.setAutoCommit(false);
            String sql = tb ;
            st = con.createStatement();
           st.executeUpdate(sql);
           JOptionPane.showConfirmDialog(null, "EXECUTE SUCCESS");  
           con.setAutoCommit(true);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
            try{
                
                st.close();
             
            }catch(SQLException e){

            }
        }
}

public  void Add_Pic(String p,JTextField txt,String sqls){    
    try {
       
      //  String querySetLimit = "SET GLOBAL max_allowed_packet=104857600;";  // 10 MB
        Statement stSetLimit = con.createStatement();
      //  stSetLimit.execute(querySetLimit);
        
        String sql = sqls;
        PreparedStatement statement = con.prepareStatement(sql);
        
      FileInputStream  inputStreams = new FileInputStream(new File(p));
        
        statement.setBlob(1, inputStreams);
        statement.setString(2,txt.getText()+"" );
        
        int row = statement.executeUpdate();
        if (row > 0) {
//            System.out.println("A contact was inserted with photo image.");
        JOptionPane.showMessageDialog(null,"EXECUTE SUCCESS");     
            }
            con.close();
            inputStreams.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }         
}
public ImageIcon ResizeImage(String imgPath ,JLabel lb){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(lb.getWidth(), lb.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
public void browes(JLabel lb){
//     b.addActionListener((ActionEvent e) -> {
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
         fileChooser.addChoosableFileFilter(filter);
         int result = fileChooser.showSaveDialog(null);
         if(result == JFileChooser.APPROVE_OPTION){
             File selectedFile = fileChooser.getSelectedFile();
              path = selectedFile.getAbsolutePath();
             lb.setIcon(ResizeImage(path,lb));
            
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
public void Value_ID(String sql,JLabel txt){
    try {  
        
        
       
        pst = con.prepareStatement(sql);  
     rs = pst.executeQuery();  
       while (rs.next()) {  
         String id = rs.getString(1);
           if (id=="") {
              txt.setText("1");
              return;
           }
       
         int i =0;
              i = Integer.parseInt(id)+1;
              txt.setText(i+"");

       } 
    }
       catch (Exception ex) { 
    JOptionPane.showMessageDialog(null,"ERROR");
}    
}

public void showpic(String sql,JLabel label){
    try{
               
                st = con.createStatement();
               rs = st.executeQuery(sql);
                if(rs.next()){
                    byte[] img = rs.getBytes("Image");



                    //Resize The ImageIcon
                    ImageIcon image = new ImageIcon(img);
                    Image im = image.getImage();
                    Image myImg = im.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    label.setIcon(newImage);
                }
                
                else{
                    JOptionPane.showMessageDialog(null, "No Data");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
}

public void ShowCombobox_Item(String s,JComboBox cb ,String Name){
     try {  
         
     pst = con.prepareStatement(s);  
     rs = pst.executeQuery();  
       while (rs.next()) {  
         
         String name = rs.getString(1);
         cb.addItem(name);
        
       } 
    }
       catch (Exception ex) { 
    JOptionPane.showMessageDialog(null,"ERROR");
}
}
public void ShowCombobox_Item_ID(String s,JComboBox cb ,JTextField txtid){
     try {  
     
        pst = con.prepareStatement(s);
     rs = pst.executeQuery();  
       while (rs.next()) {  
         txtid.setText(rs.getString(1));   
       } 
    }
       catch (Exception ex) { 
    JOptionPane.showMessageDialog(null,"ERROR");
}
}

public void Sum_Columns(JTable t,DefaultTableModel model,int c1,int c2,int c3){
        for (int i = 0; i < t.getRowCount(); i++) {
            double d1 = Double.valueOf((String) model.getValueAt(i, c1));
           double d2 = Double.valueOf((String) model.getValueAt(i, c2));
           double d3 = d1*d2;  
           
            model.setValueAt(d3, i, c3);
        }
   }
   public void X_Columns(JTable t,int c){
        double sum = 0;
        for(int i = 0; i < t.getRowCount(); i++)
        {
            sum = sum + Double.parseDouble(t.getValueAt(i, c).toString());
        }
   }
   
    public void  clock(Thread ck,JLabel date ,JLabel time){
      ck = new Thread(){
      @Override
      public void run(){
          try {
              while (true) {    
      Calendar cal = new GregorianCalendar();
      int day,month,year,hour,second,minute;
      day=cal.get(Calendar.DAY_OF_MONTH);
      month=cal.get(Calendar.MONTH)+1;
      year=cal.get(Calendar.YEAR);
      hour=cal.get(Calendar.HOUR);
      minute=cal.get(Calendar.MINUTE);
      second=cal.get(Calendar.SECOND);
      date.setText("Date :"+year+"/"+month+"/"+day);
      time.setText("Time : "+hour+":"+minute+":"+second);
                  sleep(1000);
              }
              
          } catch (Exception e) {
          }
      }
      
      };
      ck.start();    
  }
    
     public void clearText(JTextField... txt){
        for (JTextField txts : txt) {
            txts.setText("");
        }
    }
    
    public void ClearCombobox(JComboBox... cb){
        for (JComboBox cm : cb) {
            cm.addItem("");
            cm.removeAllItems();
            
        }
    }
    public void ClearTable(JTable tb,DefaultTableModel m){
        m=(DefaultTableModel)tb.getModel();
        tb.setModel(m);
        m.removeRow(0);
    }
    
      public boolean check(String id,JTable tb,DefaultTableModel m){
        for(int i=0;i<tb.getRowCount();i++){
            if(id==m.getValueAt(i, 0).toString()){     
                return true;
            }
        }
        return false;
    }
        public void DisplayName(AutoComboBox cb, String sql ){
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
     
     public void CreateSkill(JPanel p ,JTextField txt,String sql){
          //  String[] food = {"Pizza", "Burger", "Pasta", "Hot Dog", "etc","Pizza", "Burger", "Pasta", "Hot Dog", "etc"};
            List<String> SKILL = new ArrayList<>();
            //p.setLayout(new GridLayout());
            try {  
            
             pst = con.prepareStatement(sql);  
             rs = pst.executeQuery();  
               while (rs.next()) {  
                   String skill =rs.getString(1);
                   SKILL.add(skill);
//                 String name = rs.getString(1);
//                 cb.addItem(name);

               } 
                for(int i = 0; i<SKILL.size(); i++){
            JCheckBox box = new JCheckBox();

            box.setText(SKILL.get(i));
            box.setActionCommand(String.valueOf(i));
            box.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                       String b =box.getText();
                        txt.setText(" "+b+" ");
                    }
                    if (e.getStateChange() == ItemEvent.DESELECTED) {
                             String b =box.getText();
                        if (txt.getText().contains(b)) {
                            // I think I need to write the code to here which will
                            // delete the text
                            String editorText = txt.getText();
                            editorText = editorText.replaceAll (b, "");
                            txt.setText(editorText);
                        }
                    }
                }
            });
          //  p.setLayout(new FlowLayout((int) TOP_ALIGNMENT));
            p.add(box);
            p.revalidate();
            p.repaint();
            } 
            }
               catch (Exception ex) { 
            JOptionPane.showMessageDialog(null,"ERROR");
        }
           
            System.out.println("Worked!!!!!!!!");
     }
     
      public void ChangeName(JTable table, int col_index, String col_name){
            table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
      }
      
      public void HideColunmsTable(JTable tb ,TableColumn hideC,int i){
           hideC = tb.getColumnModel().getColumn(i);
           tb.getColumnModel().removeColumn(hideC);
      }
    public void DisplayTextName(String sql ,JTextField txt){
        try {
            String COMMIT_ACTION = "commit";
            txt.setFocusTraversalKeysEnabled(false);
            List<String>  keywords = new ArrayList<String>();
             st = con.createStatement();
             rs = st.executeQuery(sql);
                 while (rs.next()) {                
                String st =rs.getString(1).toString();
                keywords.add(st);
               
          }
                Autocompletes au = new Autocompletes(txt, keywords);
                txt.getDocument().addDocumentListener(au);

        // Maps the tab key to the commit action, which finishes the autocomplete
        // when given a suggestion
                txt.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
                txt.getActionMap().put(COMMIT_ACTION, au.new CommitAction());
           
        } catch (Exception e) {
        }
    }
}
