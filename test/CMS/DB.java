/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMS;


import Recruitment.Insert_Interviewee;
import static com.oracle.jrockit.jfr.FlightRecorder.isActive;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication21.AutoComboBox;
import javafx.embed.swing.JFXPanel;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


/**
 *
 * @author panha
 */
public class DB {

    /**
     * @return the id
     */
  
public static Connection con = ConMysql.getDBConnection(); 
Statement st;
ResultSet rs;
PreparedStatement pst;
public static List<JCheckBox> Checkboxall;
public String filename=null;
public byte[] pimage=null;


public void Query(String tb) {
    
     try{
        
           con.setAutoCommit(false);
            String sql = tb ;
            st = con.createStatement();
           st.executeUpdate(sql);
        //   JOptionPane.showConfirmDialog(null, "EXECUTE SUCCESS");  
           con.setAutoCommit(true);
        }catch(Exception e){
         try {
             con.rollback();
         } catch (SQLException ex) {
             Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
         }
            JOptionPane.showMessageDialog(null, e.getMessage());
       
        }
}

public void Value_ID(JTextField txt){
    try {  
        
        con.setAutoCommit(false);
        st = con.createStatement();
        rs = st.executeQuery("SELECT LAST_INSERT_ID() ");  
        rs.first();
        txt.setText(rs.getString(1));
        con.setAutoCommit(true);
        st.close();
        rs.close();
    }
       catch (Exception ex) { 
        try {
            con.rollback();
        } catch (SQLException ex1) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex1);
        }
    JOptionPane.showMessageDialog(null,"ERROR");
}    
}
 public  void Add_Pic(String id ,String sql){    
     try{
              InputStream img = new FileInputStream(new File(filename));     
              String   UpdateQuery = sql;
              PreparedStatement     ps =DB.con.prepareStatement(UpdateQuery);
                    ps.setBlob(1, img);
                    ps.setString(2, id);
                    ps.executeUpdate();                
                //    JOptionPane.showMessageDialog(null, "SUCCESSFULL");     
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
}


public void bro(JLabel pic){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        filename = f.getAbsolutePath();
        ImageIcon icon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(pic.getWidth(), pic.getHeight(),Image.SCALE_SMOOTH));
        pic.setIcon(icon);
        try {
            File image = new File(filename);
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for(int readnum ; (readnum=fis.read(buf))!=-1;){
                baos.write(buf, 0, readnum);
            }
            pimage=baos.toByteArray();
        } catch (Exception e) {
        }
    }
public void showDataInTable(JTable tb,String q,DefaultTableModel dm)
{
        try {
           con.setAutoCommit(false);
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
           con.setAutoCommit(true);
           rs.close();
           st.close();

       } catch (Exception ex) { 
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex1);
            }
          JOptionPane.showMessageDialog(null, ex.getMessage());
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
    JOptionPane.showMessageDialog(null, ex.getMessage());
}    
}

public void showpic(String sql,JLabel label){
    try{
               con.setAutoCommit(false);
                st = con.createStatement();
               rs = st.executeQuery(sql);
                if(rs.next()){
//                    byte[] img = rs.getBytes("Image");
//                    //Resize The ImageIcon
//                    ImageIcon image = new ImageIcon(img);
//                    Image im = image.getImage();
//                    Image myImg = im.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
//                    ImageIcon newImage = new ImageIcon(myImg);
//                    label.setIcon(newImage);
                      Blob aBlob = rs.getBlob("Image");
                      InputStream is = aBlob.getBinaryStream(1, aBlob.length());
                      BufferedImage imag=ImageIO.read(is);
                      Image image = imag.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
                      ImageIcon icon =new ImageIcon(image);
                      label.setIcon(icon); 
                }
                con.setAutoCommit(true);
                rs.close();
                st.close();
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
}

public void ShowCombobox_Item(String s,JComboBox cb ){
     try {  
            con.setAutoCommit(false);
            pst = con.prepareStatement(s);  
            rs = pst.executeQuery();  
                if(rs.first()){
                    do{
                       String name = rs.getString(1);
                       cb.addItem(name);

                    }while(rs.next());
                }
       
        con.setAutoCommit(true);
        rs.close();
        pst.close();
    }
       catch (Exception ex) { 
    JOptionPane.showMessageDialog(null,ex.getMessage());
}
}
public void ShowCombobox_Item_ID(String s,JComboBox cb ,JTextField txtid){
     try {  
     
        pst = con.prepareStatement(s);
        rs = pst.executeQuery();  
       
            while (rs.next()){
               txtid.setText(rs.getString(1));   
         }
         
     
     
    }
       catch (Exception ex) { 
    JOptionPane.showMessageDialog(null, ex.getMessage());
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
      date.setText(year+"/"+month+"/"+day+" "+hour+":"+minute+":"+second);
      time.setText(hour+":"+minute+":"+second);
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
//            cm.addItem("");
//            cm.removeAllItems();
            cm.setSelectedIndex(-1);
            
        }
    }

public void ClearJdateChooser(JDateChooser... dc){
    for (JDateChooser c : dc) {
        c.setDate(null);
    }
}

public void ClearTextArea(JTextArea... area){
    for (JTextArea a : area) {
        a.setText(null);
    }
}

public void ClearTable(JTable tb){
        while (tb.getRowCount() > 0) {
	((DefaultTableModel) tb.getModel()).removeRow(0);
        }
    }
    
public boolean check(String id,JTable tb,DefaultTableModel m){
        for(int i=0;i<tb.getRowCount();i++){
            if(id == null ? m.getValueAt(i, 0).toString() == null : id.equals(m.getValueAt(i, 0).toString())){     
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
     
public void CreateSkill(JPanel p ,JTextArea txt,String sql){
        
            List<String> SKILL = new ArrayList<>();
            Checkboxall = new ArrayList<>();
            try { 
                
             pst = con.prepareStatement(sql);  
             rs = pst.executeQuery();  
               while (rs.next()) {  
                   String skill =rs.getString(1);
                   SKILL.add(skill);
               } 
            for(int i = 0; i<SKILL.size(); i++){
            JCheckBox box = new JCheckBox(SKILL.get(i),false);
            box.setName(SKILL.get(i));
            box.setActionCommand(String.valueOf(i));
            
            
            
            box.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                       String b =box.getText();
                        txt.append(" "+b+" ");
                    }
                    if (e.getStateChange() == ItemEvent.DESELECTED) {
                             String b =box.getText();
                        if (txt.getText().contains(b)) {
                            // I think I need to write the code to here which will
                            // delete the text
                            String editorText = txt.getText();
                            editorText = editorText.replaceAll (b, " ");
                            txt.setText(editorText);
                        }
                    }
                }
            });
          //  p.setLayout(new FlowLayout((int) TOP_ALIGNMENT));
            Checkboxall.add(box);
            p.add(box);
            p.revalidate();
            p.repaint();
          
            } 
            }
               catch (Exception ex) { 
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
           
            System.out.println("Worked!!!!!!!!");
     }


     
public void ChangeName(JTable table, int col_index, String col_name){
            table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
      }
      
public void HideColunmsTable(JTable tb ,int... i){
    for (int j : i) {
        TableColumn    hideC = tb.getColumnModel().getColumn(j);
           tb.getColumnModel().removeColumn(hideC);
    }
           
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
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
public void TextOnlyNumber(JTextField... txtnums){
           for (JTextField txtnum : txtnums) {
                 txtnum.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                      char c = e.getKeyChar();
                      if (!(Character.isDigit(c) ||
                         (c == KeyEvent.VK_BACK_SPACE) ||
                         (c == KeyEvent.VK_DELETE) )) {
                        txtnum.getToolkit().beep();
                        e.consume();
                      }
                        //`~!@#$%^&*()_+=\\|\"':;?/>.<,
        //                String badchars  = "`~!@#$%^&*()_+=\\|\"':;?/>.<, ";
        //                 char c = e.getKeyChar();
        //
        //                 if((Character.isLetter(c) && !e.isAltDown()) 
        //                           || badchars.indexOf(c) > -1) {
        //                            e.consume();
        //                            return;
        //                        }
        //             if(c == '-' && txtnum.getDocument().getLength() > 0) 
        //                e.consume();
             
              }
           
          });
     }
           
    }
    
public void TextOnlyCharacters(JTextField... txtchars){
     
    for (JTextField txtchar : txtchars) {
        txtchar.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {

//                if(!(Character.isLetter(evt.getKeyChar()))){
//                       evt.consume();
//                   }
                    char c=evt.getKeyChar();
                    if(!(Character.isAlphabetic(c) ||  (c==KeyEvent.VK_BACK_SPACE)||  c==KeyEvent.VK_DELETE ))
                        evt.consume();
           
            }
        });
    }
    
    }
    
public void TableMouseReleased(java.awt.event.MouseEvent evt ,JTable tb,JPopupMenu jpm) {                                     
       if (evt.isPopupTrigger())
        {
            JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            int column = source.columnAtPoint( evt.getPoint() );
 
            if (! source.isRowSelected(row))
                source.changeSelection(row, column, false, false);
            else
                tb.setComponentPopupMenu(jpm);
                     jpm.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }     

public void FormartDate(JDateChooser... dateChooser){
    for (JDateChooser jDateChooser : dateChooser) {
         jDateChooser.setDateFormatString("yyyy-MM-dd");
    }
    
}

public void FormartDateInTable(JDateChooser chooser ,JTable tb ,int row){
    int i = tb.getSelectedRow();
    TableModel m = tb.getModel();
    Date date;
    try {
        date = new SimpleDateFormat("yyyy-MM-dd").parse(m.getValueAt(i, row).toString());
        chooser.setDate(date);
    } catch (ParseException ex) {
        Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
    }
   
}

public void SetCurrentDateNow(JDateChooser... choosers){
     Date date = new Date();
     String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
     System.out.println(modifiedDate);
     for (JDateChooser chooser : choosers) {
         chooser.setDate(date);
         FormartDate(chooser);
    }
    
}


}
