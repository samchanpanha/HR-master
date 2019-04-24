/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recruitment;


import CMS.DB;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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
        c.FormartDate(txtbirth,txtdateInOn,txtDateFrom,txtDateTo);
        ShowSkill();
        c.HideColunmsTable(tbapply, 14,13);
        cbstatus.setSelectedIndex(0);
        cbblock.setSelectedIndex(0);
        cbgender.setSelectedIndex(0);
      
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
    JTextArea txtskill = new JTextArea();
    DefaultTableModel mod ;
    DefaultTableModel modin ;
    DefaultTableModel modind ;
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    int id=0;
    int number_of_rows ;
    String Interview_id;

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
        Interview_id = m.getValueAt(i, 0).toString();
        
        SHOWINTERVIEWDETAILS();
    }
    void SHOWINTERVIEWDETAILS(){
        Sql="SELECT * FROM vu_interview_details WHERE InterviewID = '"+Interview_id+"';";
        c.showDataInTable(tbIntervieDetails, Sql, modind);
    }    
    
    void UPDATEINTERVIEW(){
        
    }
    void UPDATEINTERVIEWDETAILS(){
        
    }
    void SELECTEDTABLEINTERVIEWDETAIL(){
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jPanel3 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

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
        jpApply.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Name :");
        jpApply.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, 28));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Gender :");
        jpApply.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, 28));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Address :");
        jpApply.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 70, 28));

        cbgender.setKeyWord(new String[] {"Male", "Female"});
        jpApply.add(cbgender, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 220, 31));

        txtname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtname.setText("a");
        jpApply.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 220, 28));

        txtlanguage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtlanguage.setText("d");
        jpApply.add(txtlanguage, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 220, 28));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Language :");
        jpApply.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 80, 28));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Degree :");
        jpApply.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 70, 28));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Tel :");
        jpApply.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 80, 28));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Date Of Birth :");
        jpApply.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 110, 28));

        txtbirth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtbirthMouseReleased(evt);
            }
        });
        jpApply.add(txtbirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 220, 28));

        txtemail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtemail.setText("ertyg");
        jpApply.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 100, 220, 28));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Email :");
        jpApply.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, 50, 28));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Block :");
        jpApply.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, 50, 28));

        cbblock.setKeyWord(new String[] {"none", "block"});
        jpApply.add(cbblock, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, 220, 28));

        cbstatus.setKeyWord(new String[] {"none", "block"});
        jpApply.add(cbstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 220, 28));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Status :");
        jpApply.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 50, 28));

        txttel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttel.setText("3456789098765");
        jpApply.add(txttel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 220, 28));

        txtdegree.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtdegree.setText("f");
        jpApply.add(txtdegree, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 220, 28));

        jScrollPane1.setBorder(null);
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Skill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jpskill.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jpskill);

        jpApply.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 320, 110));

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
        jpApply.add(cbApplyPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 140, 220, 28));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Position For Apply :");
        jpApply.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, 130, 28));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Date Expire :");
        jpApply.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, 110, 28));

        txtdateInOn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtdateInOnMouseReleased(evt);
            }
        });
        jpApply.add(txtdateInOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 220, 28));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Date Interview :");
        jpApply.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 110, 28));

        lbedate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbedate.setText("Date Expire");
        jpApply.add(lbedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 180, 220, 28));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Date Create :");
        jpApply.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 110, 28));

        lbcdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbcdate.setText("Date Create");
        jpApply.add(lbcdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 220, 28));

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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpApply.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 1360, 380));

        bcancel.setText("CANCEL");
        bcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bcancelActionPerformed(evt);
            }
        });
        jpApply.add(bcancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 180, 100, 30));

        binsert.setText("INSERT");
        binsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binsertActionPerformed(evt);
            }
        });
        jpApply.add(binsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 20, 100, 30));

        bsave.setText("SAVE");
        bsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsaveActionPerformed(evt);
            }
        });
        jpApply.add(bsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 60, 100, 30));

        bupdate.setText("UPDATE");
        bupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdateActionPerformed(evt);
            }
        });
        jpApply.add(bupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 100, 100, 30));

        bdelete.setText("DELETE");
        bdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdeleteActionPerformed(evt);
            }
        });
        jpApply.add(bdelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 140, 100, 30));

        txtaddress.setColumns(20);
        txtaddress.setRows(5);
        txtaddress.setText("a");
        jScrollPane2.setViewportView(txtaddress);

        jpApply.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 220, 70));

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
        jScrollPane5.setViewportView(tbIntervieDetails);

        jDesktopPane3.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(txtDateFrom, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(txtDateTo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(bSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jScrollPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane3Layout = new javax.swing.GroupLayout(jDesktopPane3);
        jDesktopPane3.setLayout(jDesktopPane3Layout);
        jDesktopPane3Layout.setHorizontalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(bSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(361, Short.MAX_VALUE))
            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jDesktopPane3Layout.setVerticalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane3Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addGap(58, 58, 58))
                    .addGroup(jDesktopPane3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bSearch)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1361, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
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
    private javax.swing.JButton bSearch;
    private javax.swing.JButton bcancel;
    private javax.swing.JButton bdelete;
    private javax.swing.JButton binsert;
    private javax.swing.JButton bsave;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JDesktopPane jpApply;
    private javax.swing.JPanel jpskill;
    private javax.swing.JLabel lbcdate;
    private javax.swing.JLabel lbedate;
    private javax.swing.JTable tbIntervieDetails;
    private javax.swing.JTable tbInterview;
    private javax.swing.JTable tbapply;
    private com.toedter.calendar.JDateChooser txtDateFrom;
    private com.toedter.calendar.JDateChooser txtDateTo;
    private javax.swing.JTextArea txtaddress;
    private com.toedter.calendar.JDateChooser txtbirth;
    private com.toedter.calendar.JDateChooser txtdateInOn;
    private javax.swing.JTextField txtdegree;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtlanguage;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txttel;
    // End of variables declaration//GEN-END:variables
}
