/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recruitment;


import CMS.DB;
import CMS.JDateChooserEditor;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javaapplication21.AutoComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author panha
 */
public final class Apply extends javax.swing.JFrame {

    /**
     * Creates new form Apply
     */
    public Apply() {
        initComponents();
        ShowPositionApply();
        CreateColunm(tbapply,mod,"ID", "Name", "Gender", "Date Of Birth", "Address", "Language", "Degree", "Skill", "Phone Number", "Block", "Email", "Position For Apply",  "Status", "RecruitID", "PositionID");
        c.FormartDate(txtbirth,txtdateInOn,txtDateFrom,txtDateTo,txtdateform_re,txtdateto_re);
        ShowSkill();
        c.HideColunmsTable(tbapply, 14,13);
        cbstatus.setSelectedIndex(0);
        cbblock.setSelectedIndex(0);
        cbgender.setSelectedIndex(0);
        tbIntervieDetails.setRowHeight(20);
        tbInterview.setRowHeight(20);
    }
    DB c = new DB();
    String Sql_inwee=null;
    String Sql_inw = null;
    String Sql_inwd = null;
    String Sql=null;
    List<String> tempList= new ArrayList<>();
    List<String> duplicates= new ArrayList<>();
    JTextField txtpid = new JTextField();
    JTextField txtrid = new JTextField();
    JTextField txtInvuID =new JTextField();
    JTextField txtInvuVID = new JTextField();
    JTextField emid = new JTextField();
    JTextField resid = new JTextField();
    JTextField staffid = new JTextField();
    JTextArea txtskill = new JTextArea();
    
    DefaultTableModel mod ;
    DefaultTableModel modin ;
    DefaultTableModel modind ;
    DefaultTableModel modrein ;
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    AutoComboBox ab = new AutoComboBox();
    JComboBox cbStatusCome;
    JComboBox cblock;
    int id=0;
    int number_of_rows ;
    String interview_id;
    String interview_details_id;
    boolean check =false;

//------------------------------------------------------------------------------    
    void SUMCOLUMNS(){
//        int total = 0;
//    for (int i = 0; i < tbapply.getRowCount(); i++){
//        int amount = Integer.parseInt((String) tbapply.getValueAt(i, 15));
//        total += amount;
        mod=(DefaultTableModel)tbapply.getModel();
              number_of_rows = mod.getRowCount();
    //}
  //  System.out.println(number_of_rows);
    }
    
    void CreateColunm(JTable tb,DefaultTableModel m,Object... col){
      
       m = (DefaultTableModel)tb.getModel();
       tb.setModel(m);
        for (Object ob : col) {
           m.addColumn(ob);
        }
        tb.setRowHeight(20);
    }
    void ShowSkill(){
        Sql="SELECT  Skill\n" +
            "FROM skills;";
        c.CreateSkill(jpskill, txtskill, Sql);
    }
    void ShowPositionApply(){
        Sql = "SELECT v.Position From vu_position_for_apply v;";
        c.DisplayName(cbApplyPosition, Sql);
        cbApplyPosition.setSelectedIndex(-1);
        
    }
    void ShowIDPositionApply(){
      
        String query ="SELECT\n" +
                "v.PositionId,\n" +
                "v.RecruitId,\n" +
                "v.DateCreated,\n" +
                "v.EndDate\n" +
                "FROM\n" +
                "vu_position_for_apply v\n" +
                "WHERE v.Position = '"+cbApplyPosition.getSelectedItem()+"';";       
        c.Vu_id_Date(query,txtpid ,txtrid,lbcdate, lbedate);
    //    System.out.println(txtpid.getText() +" "+ txtrid.getText() +" "+ lbcdate.getText() +" "+ lbedate.getText());
    }
    
    void LASTIDINTERVIEW(){
      
        c.Value_ID(txtInvuID);
    }
    void LASTIDINTERVIEWEE(){
      
        c.Value_ID(txtInvuVID);
    }
    
    void INSERT(){
        mod = (DefaultTableModel)tbapply.getModel();
        tbapply.setModel(mod);
        String name = txtname.getText();
        String gender = cbgender.getSelectedItem().toString();
        String address = txtaddress.getText();
        String skill = txtskill.getText();
        String language = txtlanguage.getText();
        String degree = txtdegree.getText();
        String tel = txttel.getText();
        String birthDate = formater.format(txtbirth.getDate());
        
        String status = cbstatus.getSelectedItem().toString();
        String block = cbblock.getSelectedItem().toString();
        String email = txtemail.getText();
        String positionApply = cbApplyPosition.getSelectedItem().toString();
        String paID = txtpid.getText();
        String rID = txtrid.getText();
        id++;
        String[] row = {id+"",name,gender,birthDate,address,language,degree,skill,tel,block,email,positionApply,status,rID,paID};
        mod.addRow(row);
       
        Uncheck();
        CANCEL();
        SUMCOLUMNS();
      
       
    }
    void UPDATE(){
        int i = tbapply.getSelectedRow();
        TableModel m = tbapply.getModel();
        String name = txtname.getText();
        String gender = cbgender.getSelectedItem().toString();
        String address = txtaddress.getText();
        String skill = txtskill.getText();
        String language = txtlanguage.getText();
        String degree = txtdegree.getText();
        String tel = txttel.getText();
        String birthDate = formater.format(txtbirth.getDate());    
        String status = cbstatus.getSelectedItem().toString();
        String block = cbblock.getSelectedItem().toString();
        String email = txtemail.getText();
        String positionApply = cbApplyPosition.getSelectedItem().toString();
        String paID = txtpid.getText();
        String rID = txtrid.getText();
        m.setValueAt(name, i, 1);
        m.setValueAt(gender, i, 2);
        m.setValueAt(birthDate, i, 3);
        m.setValueAt(address, i, 4);
        m.setValueAt(language, i, 5);
        m.setValueAt(degree, i, 6);
        m.setValueAt(skill, i, 7);
        m.setValueAt(tel, i, 8);
        m.setValueAt(block, i, 9);
        m.setValueAt(email, i, 10);
        m.setValueAt(positionApply, i, 11);
        m.setValueAt(status, i, 12);
        m.setValueAt(rID, i, 13);
        m.setValueAt(paID, i, 14);
        Uncheck();
        CANCEL();
        
    }
    void DELETE(){
        int index[] = tbapply.getSelectedRows();
        for (int i = 0; i < index.length; i++) {
            mod.removeRow(index[i] - i);
        }
         SUMCOLUMNS();
    }
    void CANCEL(){
        c.clearText(txtname,txtemail,txtdegree,txtlanguage,txttel,txtpid,txtrid);
        c.ClearTextArea(txtaddress);
        c.ClearCombobox(cbApplyPosition,cbblock,cbgender,cbstatus);
        lbcdate.setText("");
        lbedate.setText("");
        c.ClearJdateChooser(txtbirth);
    }
    void CLEAR(){
        c.clearText(txtname,txtemail,txtdegree,txtlanguage,txttel,txtpid,txtrid);
        c.ClearTextArea(txtaddress);
        c.ClearCombobox(cbApplyPosition,cbblock,cbgender,cbstatus);
        lbcdate.setText("");
        lbedate.setText("");
        c.ClearJdateChooser(txtbirth,txtdateInOn);
        c.ClearTable(tbapply);
        id=0;
    }
   
    void SAVE(){
        //insert to Interview Fist
        //Then Insert to Interviewee 
        //Then Insert to InterviewDetails
        try {
           
                Sql_inw = "INSERT INTO interviews\n" +
           "(TotalNumberOfInterview, DateOn, EmID)\n" +
           "VALUES('"+String.valueOf(number_of_rows) +"', '"+formater.format(txtdateInOn.getDate())+"', 1);";
           c.Query(Sql_inw);
           LASTIDINTERVIEW();
            
           for (int i = 0; i < tbapply.getRowCount(); i++) {
                    
                    TableModel m =tbapply.getModel();
                //name,gender,birthDate,address,language,degree,skill,tel,block,email,positionApply,status,rID,paID
                    String name = m.getValueAt(i, 1).toString();
                    String gender = m.getValueAt(i, 2).toString();
                    String birthDate = m.getValueAt(i, 3).toString(); 
                    String address = m.getValueAt(i, 4).toString();
                    String language = m.getValueAt(i, 5).toString();
                    String degree = m.getValueAt(i, 6).toString();
                    String skill = m.getValueAt(i, 7).toString();
                    String tel = m.getValueAt(i, 8).toString();  
                    String block = m.getValueAt(i, 9).toString();
                    String email = m.getValueAt(i, 10).toString(); 
                    String status = m.getValueAt(i, 12).toString();
                    String  r=   m. getValueAt(i, 13)+"";
                    String  p = m.getValueAt(i, 14)+"";
                   
                 
                    Sql_inwee="CALL InsertIW('"+name+"','"+gender+"','"+address+"','"+tel+"','"+status+"',"
                    + "'"+block+"','"+degree+"','"+language+"','"+skill+"','"+birthDate+"','"+email+"')";
                     c.Query(Sql_inwee);     
                    LASTIDINTERVIEWEE();
 
                    
                     Sql_inwd = "call insert_indetails('"+txtInvuID.getText()+"', '"+txtInvuVID.getText()+"','"+r+"','"+p+"');";
                     c.Query(Sql_inwd);      
                    
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this, e.getMessage());
        }
       
      
        
        
     //  CLEAR(); 
    }
    void SELECTEDROWSTABLE(){
        int i = tbapply.getSelectedRow();
        TableModel m = tbapply.getModel();
        Uncheck();
        //name,gender,birthDate,address,language,degree,skill,tel,block,email,positionApply,IntervuOn,status,paID,rID
        txtname.setText(m.getValueAt(i, 1).toString());
        cbgender.setSelectedItem(m.getValueAt(i, 2).toString());
        c.FormartDateInTable(txtbirth, tbapply, 3);
        txtaddress.setText(m.getValueAt(i, 4).toString());
        txtlanguage.setText(m.getValueAt(i, 5).toString());
        txtdegree.setText(m.getValueAt(i, 6).toString());
      
         txtskill.setText(m.getValueAt(i, 7).toString());
                    String editorText = txtskill.getText();
                    editorText = editorText.replaceAll (","," ");
                    txtskill.setText(editorText);
            linesToLinesAndWord(txtskill.getText()+"");
            RemoveWordTheSame(txtskill.getText());
            txtskill.setText(null);
            tempList.forEach((string) -> {
                txtskill.append(string+" ");
                  });
        txttel.setText(m.getValueAt(i, 8).toString());
        cbblock.setSelectedItem(m.getValueAt(i, 9).toString());
        txtemail.setText(m.getValueAt(i, 10).toString());
        cbApplyPosition.setSelectedItem(m.getValueAt(i, 11).toString());       
        cbstatus.setSelectedItem(m.getValueAt(i, 12).toString());
      //  JOptionPane.showMessageDialog(this, m.getValueAt(i, 13).toString()+" "+m.getValueAt(i, 14).toString());
        
        ShowIDPositionApply();
        
    }
   
   void Uncheck(){
         for (int i = 0; i < DB.Checkboxall.size(); i++) {
            DB.Checkboxall.get(i).setSelected(false);
        }
   }
    public  List<List<String>> linesToLinesAndWord(String lines) {
    List<List<String>> wordlists = new ArrayList<>();
    List<String> lineList = Arrays.asList(lines.split("\n"));
    
    for (String line : lineList) {
      //   System.out.println(line+"");
        wordlists.add(Arrays.asList(line.trim().split(" ")));    
    }      
    DB.Checkboxall.forEach((JCheckBox cb) -> {
            String name = cb.getName();
             for (List<String> wordlist : wordlists) {
               for (String string : wordlist) {
                      if(name == null ? string == null : name.equals(string)){
                        cb.setSelected(isActive());      
                     }   
                }
    }
          });  
    return wordlists;
}
    
    public  List<List<String>> RemoveWordTheSame(String lines) {
    List<List<String>> wordlists = new ArrayList<>();
    List<String> lineList = Arrays.asList(lines.split("\n"));
    
    for (String line : lineList) {
      //   System.out.println(line+"");
        wordlists.add(Arrays.asList(line.trim().split(" ")));    
    }
    
    for (List<String> wordlist : wordlists) {
          for (String dupWord : wordlist) {
                    if (!tempList.contains(dupWord)) {
                        tempList.add(dupWord);
                    }else{
                        duplicates.add(dupWord);
                    }
                }
    }
        int k = 0;
      while (k < tempList.size())
      {
         if (tempList.get(k).equals(""))
            tempList.remove(k);
         k++;
      }
     
    return wordlists;
}
//------------------------------------------------------------------------------
//Tab Interview 
    void SEARCHINTERVIEW(){
        if (txtDateFrom.getDate()==null && txtDateTo.getDate()==null) {
            String DateNow  =   formater.format(System.currentTimeMillis());
           // JOptionPane.showMessageDialog(this, DateNow);
            Sql ="SELECT * FROM vu_interview WHERE DateOn = '"+DateNow+"'";
            c.showDataInTable(tbInterview, Sql, modin);
            c.ClearTable(tbIntervieDetails);
        }
        else if (txtDateFrom.getDate()!=null && txtDateTo.getDate()==null){
             Sql ="SELECT * FROM vu_interview WHERE DateOn = '"+formater.format(txtDateFrom.getDate())+"'";
        c.showDataInTable(tbInterview, Sql, modin);
         c.ClearTable(tbIntervieDetails);
        }
        else {
              Sql ="SELECT * FROM vu_interview WHERE DateOn between '"+formater.format(txtDateFrom.getDate())+"' "
                      + "And  '"+formater.format(txtDateTo.getDate())+"'";
        c.showDataInTable(tbInterview, Sql, modin);
         c.ClearTable(tbIntervieDetails);
        } 
       
    }

    void SELECTEDTABLEINTERVIEW(){
        int i = tbInterview.getSelectedRow();
        TableModel m = tbInterview.getModel();
        interview_id = m.getValueAt(i, 0).toString();  
        SHOWINTERVIEWDETAILS();
        modind = (DefaultTableModel)tbIntervieDetails.getModel();
        tbIntervieDetails.setModel(modind);
        modind.addColumn("Choose Staff");
        modind.addColumn("ReinterDate");
        modind.addColumn("Check");
        modind.addColumn("StaffID");
      
       COMBOBOX_INTERVIEWDETAILS();
       c.HideColunmsTable(tbIntervieDetails,12, 8,7,6,5);
       SELECTEDCOMBOBOX(cbStatusCome,cblock);
    }
    void MOVECOLUMNS(JTable tb ,int ... i){
        for (int j : i) {
              tb.moveColumn(tbIntervieDetails.getColumnCount() - 1, j);
        }
    }
    
    void SHOWINTERVIEWDETAILS(){
       
        Sql="SELECT * FROM vu_interview_details WHERE InterviewID = '"+interview_id+"';";
        c.showDataInTable(tbIntervieDetails, Sql, modind);
    }    
    
    void SELECTEDTABLEINTERVIEWDETAIL(){
        int i = tbIntervieDetails.getSelectedRow();
        TableModel m = tbIntervieDetails.getModel();
        
    }
    
    void COMBOBOX_INTERVIEWDETAILS(){
        
        String[] item = {"come","not come"};
        cbStatusCome = new JComboBox(item);
        TableColumn countryColumn = tbIntervieDetails.getColumnModel().getColumn(3); 
	countryColumn.setCellEditor(new DefaultCellEditor(cbStatusCome));
        String[] bk = {"none","blacklist"};
         cblock = new JComboBox(bk);
        TableColumn tbm = tbIntervieDetails.getColumnModel().getColumn(2); 
	tbm.setCellEditor(new DefaultCellEditor(cblock));
        
        Sql="SELECT\n" +
            "interviewees.`Name`\n" +
            "FROM\n" +
            "employees\n" +
            "INNER JOIN interviewees ON employees.IntervieweeId = interviewees.IntervieweeId";
        c.DisplayName(ab, Sql);
        TableColumn tbms = tbIntervieDetails.getColumnModel().getColumn(9); 
	tbms.setCellEditor(new DefaultCellEditor(ab));
        tbIntervieDetails.getColumnModel().getColumn(10).setCellEditor(new JDateChooserEditor(new JCheckBox()));
         TableColumn tcs = tbIntervieDetails.getColumnModel().getColumn(11);
        tcs.setCellEditor(tbIntervieDetails.getDefaultEditor(Boolean.class));
        tcs.setCellRenderer(tbIntervieDetails.getDefaultRenderer(Boolean.class));
    }
    
    void ADDDATE_INTERVIEWDETAILS(JTable table,int i){
        TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {
              SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
              @Override
              public Component getTableCellRendererComponent(JTable table,
                      Object value, boolean isSelected, boolean hasFocus,
                      int row, int column) {
                  if( value instanceof Date) {
                      value = f.format(value);
                  }
                  return super.getTableCellRendererComponent(table, value, isSelected,
                          hasFocus, row, column);
              }
          };
          table.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);
    }
    
    void ShowEmployeeID(){
        
        try {
              Sql="SELECT\n" +
                "employees.EmpId\n" +
                "FROM\n" +
                "employees\n" +
                "INNER JOIN interviewees ON employees.IntervieweeId = interviewees.IntervieweeId WHERE interviewees.`Name` = '"+ab.getSelectedItem()+"'";
            c.DisplayId(Sql, emid);
            System.out.println(emid.getText());
        } catch (Exception e) {
        }
      
    }
    void SELECTEDCOMBOBOX(JComboBox... cb ){
        for (JComboBox ab : cb) {
             ab.addPopupMenuListener(new PopupMenuListener() {
         @Override
         public void popupMenuCanceled(PopupMenuEvent e) {
            System.out.println("popupMenu canceled");
         }
         @Override
         public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
               binsertreinter.setEnabled(true);
         }
         @Override
         public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
//            if (ab.getModel().getSize() <= 1) {
//               // lazily populate combobox
//               for (int i=0; i<100; i++) {
//                  ab.insertItemAt("Item #" + i, 0);
//               }
//               ab.validate();
//            }
//            System.out.println("popupMenu will become visible");
           
         }
      }); 
        }
    }
   
    //-------------------------------------------------------------------------
    //Reinter
    
    void SHOWREINTER(){
         if (txtdateform_re.getDate()==null && txtdateto_re.getDate()==null) {
            String DateNow  =   formater.format(System.currentTimeMillis());
           // JOptionPane.showMessageDialog(this, DateNow);
            Sql ="SELECT * FROM vu_Reinters WHERE InterviewOn = '"+DateNow+"'";
            c.showDataInTable(tbreinterstaff, Sql, modrein);
           
        }
        else if (txtdateform_re.getDate()!=null && txtdateto_re.getDate()==null){
             Sql ="SELECT * FROM vu_Reinters WHERE InterviewOn = '"+formater.format(txtdateform_re.getDate())+"'";
              c.showDataInTable(tbreinterstaff, Sql, modrein);
        
        }
        else {
              Sql ="SELECT * FROM vu_Reinters WHERE InterviewOn between '"+formater.format(txtdateform_re.getDate())+"' "
                      + "And  '"+formater.format(txtdateto_re.getDate())+"'";
                  c.showDataInTable(tbreinterstaff, Sql, modrein);     
        } 
         StatusForTableReinter();
      //   c.HideColunmsTable(tbreinterstaff, 1,4,5,6,7);
    }
    
    void StatusForTableReinter(){
        String[] item = {"Very Good","Good","Normal","Bad"};
        AutoComboBox acb = new AutoComboBox();
        acb.setKeyWord(item);
        TableColumn tbms = tbreinterstaff.getColumnModel().getColumn(3); 
	tbms.setCellEditor(new DefaultCellEditor(acb));
        
        String[] items = {"Passed","Failed"};
        AutoComboBox acbs = new AutoComboBox();
        acbs.setKeyWord(items);
        TableColumn tbmss = tbreinterstaff.getColumnModel().getColumn(4); 
	tbmss.setCellEditor(new DefaultCellEditor(acbs));
        
        Sql="SELECT\n" +
            "interviewees.`Name`\n" +
            "FROM\n" +
            "employees\n" +
            "INNER JOIN interviewees ON employees.IntervieweeId = interviewees.IntervieweeId";
        c.DisplayName(acb_emname, Sql);
        TableColumn tcb = tbreinterstaff.getColumnModel().getColumn(10); 
	tcb.setCellEditor(new DefaultCellEditor(acb_emname));
    }
    
    void ShowStaffID(){
        
        try {
              Sql="SELECT\n" +
                "employees.EmpId\n" +
                "FROM\n" +
                "employees\n" +
                "INNER JOIN interviewees ON employees.IntervieweeId = interviewees.IntervieweeId WHERE interviewees.`Name` = '"+acb_emname.getSelectedItem()+"'";
            c.DisplayId(Sql, staffid);
            System.out.println(staffid.getText());
        } catch (Exception e) {
        }
      
    }
    
    void btnsave_reinter(){
        int i = tbreinterstaff.getRowCount();
        TableModel m = tbreinterstaff.getModel();
        try {
            for (int j = 0; j < i; j++) {
                String test =m.getValueAt(j, 4).toString();
                
             
//                              
                           
                          
                try {
                       Sql="UPDATE reinters\n" +
                       "SET  Status='"+m.getValueAt(j, 3)+"', `result`='"+m.getValueAt(j, 4)+"'" +
                       "WHERE ReInterId="+m.getValueAt(j, 0)+";";
                       c.Query(Sql);

                       String query="UPDATE reinterstaffs \n" +
                                   "SET  EmpID='"+m.getValueAt(j, 9)+"' \n" +
                                   "WHERE ReInterStaffID='"+m.getValueAt(j, 11)+"';";
                       c.Query(query);
                     
                       if (test=="Passed") {
                                 String w ="UPDATE `interviewees` SET `Status` = 'New' WHERE `IntervieweeId` ='"+ m.getValueAt(j, 5).toString()+"' "; 
                                 c.Query(w);
                              }
                          else{
                                String iw = "UPDATE interviewees\n" +
                                    "SET  Status='none'\n" +
                                    "WHERE IntervieweeId='"+m.getValueAt(j, 5).toString()+"';";
                                 c.Query(iw);
                          }
                            
                         
                } catch (Exception e) {
                }
                
               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
       SHOWREINTER();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        acb_emname = new javaapplication21.AutoComboBox();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpApply = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbgender = new javaapplication21.AutoComboBox();
        txtname = new javax.swing.JTextField();
        txtlanguage = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtbirth = new com.toedter.calendar.JDateChooser();
        txtemail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbblock = new javaapplication21.AutoComboBox();
        cbstatus = new javaapplication21.AutoComboBox();
        jLabel12 = new javax.swing.JLabel();
        txttel = new javax.swing.JTextField();
        txtdegree = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpskill = new javax.swing.JPanel();
        cbApplyPosition = new javaapplication21.AutoComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtdateInOn = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        lbedate = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbcdate = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbapply = new javax.swing.JTable();
        bcancel = new javax.swing.JButton();
        binsert = new javax.swing.JButton();
        bsave = new javax.swing.JButton();
        bupdate = new javax.swing.JButton();
        bdelete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaddress = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jLabel15 = new javax.swing.JLabel();
        txtDateFrom = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        txtDateTo = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbInterview = new javax.swing.JTable();
        bSearch = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbIntervieDetails = new javax.swing.JTable();
        binsertreinter = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbreinterstaff = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtdateform_re = new com.toedter.calendar.JDateChooser();
        txtdateto_re = new com.toedter.calendar.JDateChooser();
        btnSearchReinter = new javax.swing.JButton();
        btnRe_save = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        acb_emname.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                acb_emnamePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Apply Form");

        jpApply.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Name :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Gender :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Address :");

        cbgender.setKeyWord(new String[] {"Male", "Female"});

        txtname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtname.setText("a");

        txtlanguage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtlanguage.setText("d");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Language :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Degree :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Tel :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Date Of Birth :");

        txtbirth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtbirthMouseReleased(evt);
            }
        });

        txtemail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtemail.setText("ertyg");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Email :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Block :");

        cbblock.setKeyWord(new String[] {"none", "block"});

        cbstatus.setKeyWord(new String[] {"none", "block"});

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Status :");

        txttel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttel.setText("3456789098765");

        txtdegree.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtdegree.setText("f");

        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Skill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jpskill.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jpskill);

        cbApplyPosition.setKeyWord(new String[] {"none", "block"});
        cbApplyPosition.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbApplyPositionPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Position For Apply :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Date Expire :");

        txtdateInOn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtdateInOnMouseReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Date Interview :");

        lbedate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbedate.setText("Date Expire");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Date Create :");

        lbcdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbcdate.setText("Date Create");

        jPanel1.setAutoscrolls(true);
        jPanel1.setPreferredSize(new java.awt.Dimension(1320, 380));

        tbapply.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbapply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbapplyMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbapply);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1361, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bcancel.setText("CANCEL");
        bcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcancelActionPerformed(evt);
            }
        });

        binsert.setText("INSERT");
        binsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binsertActionPerformed(evt);
            }
        });

        bsave.setText("SAVE");
        bsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsaveActionPerformed(evt);
            }
        });

        bupdate.setText("UPDATE");
        bupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdateActionPerformed(evt);
            }
        });

        bdelete.setText("DELETE");
        bdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdeleteActionPerformed(evt);
            }
        });

        txtaddress.setColumns(20);
        txtaddress.setRows(5);
        txtaddress.setText("a");
        jScrollPane2.setViewportView(txtaddress);

        jpApply.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(cbgender, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(txtname, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(txtlanguage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(txtbirth, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(txtemail, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(cbblock, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(cbstatus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(txttel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(txtdegree, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(cbApplyPosition, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(txtdateInOn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(lbedate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(lbcdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(bcancel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(binsert, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(bsave, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(bupdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(bdelete, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jpApply.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jpApplyLayout = new javax.swing.GroupLayout(jpApply);
        jpApply.setLayout(jpApplyLayout);
        jpApplyLayout.setHorizontalGroup(
            jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1361, Short.MAX_VALUE)
            .addGroup(jpApplyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpApplyLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtlanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(binsert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpApplyLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cbgender, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(txtdegree, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cbblock, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(bsave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpApplyLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpApplyLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbApplyPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpApplyLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbcdate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdateInOn, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lbedate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(bcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jpApplyLayout.setVerticalGroup(
            jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpApplyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(binsert, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbgender, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdegree, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbblock, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bsave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpApplyLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpApplyLayout.createSequentialGroup()
                        .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpApplyLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpApplyLayout.createSequentialGroup()
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(cbApplyPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpApplyLayout.createSequentialGroup()
                        .addComponent(bupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(bdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpApplyLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpApplyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpApplyLayout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpApplyLayout.createSequentialGroup()
                                .addComponent(lbcdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txtdateInOn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbedate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bcancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Apply", jpApply);

        jPanel2.setAutoscrolls(true);

        jDesktopPane2.setBackground(new java.awt.Color(255, 255, 255));

        jDesktopPane3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setText("FORM");

        jLabel17.setText("TO");

        tbInterview.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbInterview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInterviewMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbInterview);

        bSearch.setText("SEARCH");
        bSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchActionPerformed(evt);
            }
        });

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Interview Details");

        tbIntervieDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbIntervieDetails.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tbIntervieDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIntervieDetailsMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbIntervieDetails);

        binsertreinter.setText("Save");
        binsertreinter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binsertreinterActionPerformed(evt);
            }
        });

        jDesktopPane3.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(txtDateFrom, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(txtDateTo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(bSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jScrollPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(binsertreinter, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane3Layout = new javax.swing.GroupLayout(jDesktopPane3);
        jDesktopPane3.setLayout(jDesktopPane3Layout);
        jDesktopPane3Layout.setHorizontalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(txtDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(bSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(binsertreinter, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 361, Short.MAX_VALUE))
        );
        jDesktopPane3Layout.setVerticalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane3Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(58, 58, 58))
                    .addGroup(jDesktopPane3Layout.createSequentialGroup()
                        .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(binsertreinter, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addComponent(jScrollPane5)))
        );

        jDesktopPane2.setLayer(jDesktopPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane3)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane3)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );

        jTabbedPane1.addTab("Interview", jPanel2);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbreinterstaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbreinterstaff.setRowHeight(25);
        jScrollPane6.setViewportView(tbreinterstaff);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("TO");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("FORM");

        btnSearchReinter.setText("Search");
        btnSearchReinter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchReinterActionPerformed(evt);
            }
        });

        btnRe_save.setText("Save");
        btnRe_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRe_saveActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtdateform_re, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtdateto_re, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnSearchReinter, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnRe_save, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel22)
                        .addGap(26, 26, 26)
                        .addComponent(txtdateform_re, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(txtdateto_re, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnSearchReinter, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRe_save, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 713, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdateto_re, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtdateform_re, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRe_save, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(btnSearchReinter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        jTabbedPane1.addTab("Reinter", jPanel3);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Date Create");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Date Time :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(620, 620, 620)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        binsertreinter.setEnabled(false);
       
        tbIntervieDetails.setRowSelectionAllowed(false);
        ab.addPopupMenuListener(new PopupMenuListener() {
         @Override
         public void popupMenuCanceled(PopupMenuEvent e) {
            System.out.println("popupMenu canceled");
         }
         @Override
         public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                ShowEmployeeID();
                int id =tbIntervieDetails.getSelectedRow();
                TableModel m = tbIntervieDetails.getModel();
                m.setValueAt(emid.getText(), id, 12);
                binsertreinter.setEnabled(true);
         }
         @Override
         public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
           
         }
      }); 
       
    }//GEN-LAST:event_formWindowOpened

    private void txtbirthMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbirthMouseReleased

    }//GEN-LAST:event_txtbirthMouseReleased

    private void txtdateInOnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdateInOnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdateInOnMouseReleased

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed
       SAVE();
    }//GEN-LAST:event_bsaveActionPerformed

    private void cbApplyPositionPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbApplyPositionPopupMenuWillBecomeInvisible
       ShowIDPositionApply();
    }//GEN-LAST:event_cbApplyPositionPopupMenuWillBecomeInvisible

    private void binsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binsertActionPerformed
       INSERT();
    }//GEN-LAST:event_binsertActionPerformed

    private void tbapplyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbapplyMouseClicked
        SELECTEDROWSTABLE();
    }//GEN-LAST:event_tbapplyMouseClicked

    private void bupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bupdateActionPerformed
        UPDATE();
    }//GEN-LAST:event_bupdateActionPerformed

    private void bdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdeleteActionPerformed
       DELETE();
    }//GEN-LAST:event_bdeleteActionPerformed

    private void bcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcancelActionPerformed
       CANCEL();
    }//GEN-LAST:event_bcancelActionPerformed

    private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSearchActionPerformed
        SEARCHINTERVIEW();
    }//GEN-LAST:event_bSearchActionPerformed

    private void tbInterviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInterviewMouseClicked
        SELECTEDTABLEINTERVIEW();
    }//GEN-LAST:event_tbInterviewMouseClicked

 
    private void tbIntervieDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIntervieDetailsMouseClicked
         
       
    }//GEN-LAST:event_tbIntervieDetailsMouseClicked

    private void binsertreinterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binsertreinterActionPerformed
        //    binsertreinter.setEnabled(false);
          try {
                TableModel m = tbIntervieDetails.getModel();
                 int i = tbIntervieDetails.getRowCount();
                  boolean boo=Boolean.TRUE;
                for (int j=0;j< i;j++) {
                     String u="CALL update_interviewee('"+m.getValueAt(j, 2)+"','"+m.getValueAt(j, 8)+"')";
                     c.Query(u);
                     String h="CALL update_interview_details('"+m.getValueAt(j, 3)+"','"+m.getValueAt(j, 0)+"') ";
                     c.Query(h);
                    try {
                         if ((boolean)m.getValueAt(j, 11)==boo) {
                             if (m.getValueAt(j, 5)!=null && m.getValueAt(j, 10)!=null) {
                                 String q="{CALL insert_reinter('"+m.getValueAt(j, 5)+"','"+m.getValueAt(j, 10)+"','"+m.getValueAt(j, 8)+"')}";
                                c.Query(q);
                                c.Value_ID(resid);      
                                String s ="INSERT INTO `reinterstaffs`(`ReInterID`, `EmpID`) VALUES ('"+resid.getText()+"', '"+m.getValueAt(j, 12)+"');";
                                c.Query(s);
                                
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog(this, "You Forgot Choose Staff For Reinter Or Forgot Choose Date For Meet!!");
                                 break;
                             }    
                    }
                    } catch (Exception e) {
                    }
                   
                }    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_binsertreinterActionPerformed

    private void btnSearchReinterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchReinterActionPerformed
       SHOWREINTER();
    }//GEN-LAST:event_btnSearchReinterActionPerformed

    private void acb_emnamePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_acb_emnamePopupMenuWillBecomeInvisible
        try {
                ShowStaffID();
                int id =tbreinterstaff.getSelectedRow();
                TableModel m = tbreinterstaff.getModel();
                m.setValueAt(staffid.getText(), id, 9);
               
        } catch (Exception e) {
        }
       
    }//GEN-LAST:event_acb_emnamePopupMenuWillBecomeInvisible

    private void btnRe_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRe_saveActionPerformed
       btnsave_reinter();
    }//GEN-LAST:event_btnRe_saveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Apply.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Apply.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Apply.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Apply.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Apply().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaapplication21.AutoComboBox acb_emname;
    private javax.swing.JButton bSearch;
    private javax.swing.JButton bcancel;
    private javax.swing.JButton bdelete;
    private javax.swing.JButton binsert;
    private javax.swing.JButton binsertreinter;
    private javax.swing.JButton bsave;
    private javax.swing.JButton btnRe_save;
    private javax.swing.JButton btnSearchReinter;
    private javax.swing.JButton bupdate;
    private javaapplication21.AutoComboBox cbApplyPosition;
    private javaapplication21.AutoComboBox cbblock;
    private javaapplication21.AutoComboBox cbgender;
    private javaapplication21.AutoComboBox cbstatus;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JDesktopPane jpApply;
    private javax.swing.JPanel jpskill;
    private javax.swing.JLabel lbcdate;
    private javax.swing.JLabel lbedate;
    private javax.swing.JTable tbIntervieDetails;
    private javax.swing.JTable tbInterview;
    private javax.swing.JTable tbapply;
    private javax.swing.JTable tbreinterstaff;
    private com.toedter.calendar.JDateChooser txtDateFrom;
    private com.toedter.calendar.JDateChooser txtDateTo;
    private javax.swing.JTextArea txtaddress;
    private com.toedter.calendar.JDateChooser txtbirth;
    private com.toedter.calendar.JDateChooser txtdateInOn;
    private com.toedter.calendar.JDateChooser txtdateform_re;
    private com.toedter.calendar.JDateChooser txtdateto_re;
    private javax.swing.JTextField txtdegree;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtlanguage;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txttel;
    // End of variables declaration//GEN-END:variables
}
