/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recruitment;

import CMS.DB;
import static com.oracle.jrockit.jfr.FlightRecorder.isActive;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author panha
 */
public class Study extends javax.swing.JInternalFrame {

    /**
     * Creates new form Study
     */
    public Study() {
        initComponents();
    }
    DB c = new DB();
    DefaultTableModel dm;
    String Sql =null;
    String dend =null;
    JTextArea txtstudyskill = new JTextArea();
    JTextField txtinid = new JTextField();
    JTextField txtstudytypeid = new JTextField();
    JTextField txtcateid = new JTextField();
    JTextField txtmajorid = new JTextField();
    JTextField studyid = new JTextField();
    List<String> tempList= new ArrayList<String>();
    List<String> duplicates= new ArrayList<String>();
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    
    
    private void ShowIntervieweeName(){
        Sql="SELECT Name FROM interviewees";
        c.DisplayName(cbInterName, Sql);
        cbInterName.setSelectedItem("");
    }
    private void ShowIdInterviewee(){
         Sql="SELECT IntervieweeId FROM interviewees WHERE Name ='"+cbInterName.getSelectedItem()+"'";
         c.DisplayId(Sql, txtinid);
    }
    private void ShowStudyType(){
        Sql="SELECT StudyRecordType\n" +
             "FROM studyrecordtypes;";
        c.DisplayName(cbstduyType, Sql);
        cbstduyType.setSelectedItem("");
    }
    private void ShowIdStudyType(){
        Sql="SELECT StudyRecordTypeId \n" +
             "FROM studyrecordtypes WHERE StudyRecordType = '"+cbstduyType.getSelectedItem()+"';";
        c.DisplayId(Sql, txtstudytypeid);
    }
    private void ShowCategory(){
        Sql="SELECT  CatName\n" +
             "FROM categorys;";
        c.DisplayName(cbCate, Sql);
        cbCate.setSelectedItem("");
    }
    private void ShowIdCategory(){
        Sql="SELECT CatID\n" +
             "FROM categorys WHERE CatName = '"+cbCate.getSelectedItem()+"';";
        c.DisplayId(Sql, txtcateid);
    }
    private void ShowMajor(){
        Sql="SELECT Major\n" +
            "FROM majors;";
        c.DisplayName(cbMajor, Sql);
        cbMajor.setSelectedItem("");
    }
    private void ShowIdMajor(){
        Sql="SELECT MajorId \n" +
            "FROM majors WHERE Major = '"+cbMajor.getSelectedItem()+"';";
        c.DisplayId(Sql, txtmajorid);
    }
    private void ShowStudy(){
        Sql="SELECT * FROM vu_study";
        c.showDataInTable(tbstudy, Sql, dm);
        c.ChangeName(tbstudy, 0, "ID");
    }
    private void ShowStudySkill(){
        Sql="SELECT Skill\n" +
             "FROM skills;";
        c.CreateSkill(jpstudy, txtstudyskill, Sql);
    }
    private void btnadd(){
       
        dend =formater.format(txtdateend.getDate());
        Sql="INSERT INTO studyrecords\n" +
            "(StudyRecordTypeId, MajorId, CatID, Description, Skill, EndYear, IntervieweeId)\n" +
            "VALUES('"+txtstudytypeid.getText()+"', '"+txtmajorid.getText()+"', '"+txtcateid.getText()+"',"
                + " '"+txtdes.getText()+"', '"+txtstudyskill.getText()+"', '"+dend+"', '"+txtinid.getText()+"');";
        c.Query(Sql);
        ShowStudy();
    }
    private void btnUpdate(){
        dend =formater.format(txtdateend.getDate());
        Sql="UPDATE studyrecords\n" +
            "SET StudyRecordTypeId="+txtstudytypeid.getText()+", MajorId="+txtmajorid.getText()+","
             + " CatID="+txtcateid.getText()+", Description='"+txtdes.getText()+"', "
        + "Skill='"+txtstudyskill.getText()+"', EndYear='"+dend+"', IntervieweeId="+txtinid.getText()+"\n" +
            "WHERE StudyRecordId="+studyid.getText()+";";
        c.Query(Sql);
        ShowStudy();
    }
    private void btndelete(){
        Sql="DELETE FROM studyrecords\n" +
            "WHERE StudyRecordId="+studyid.getText()+";";
        c.Query(Sql);
        ShowStudy();
    }
    private void SEARCH(){
        Sql = "CALL FindStudy('"+txtsearch.getText()+"')";
        if ("".equals(txtsearch.getText())) {
            ShowStudy();
        }
        else{
            c.showDataInTable(tbstudy, Sql, dm);
             c.ChangeName(tbstudy, 0, "ID");
        }
    }
    
    public  List<List<String>> linesToLinesAndWord(String lines) {
    List<List<String>> wordlists = new ArrayList<>();
    List<String> lineList = Arrays.asList(lines.split("\n")); 
    for (String line : lineList) {
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
    private void Uncheck(){
         for (int i = 0; i < DB.Checkboxall.size(); i++) {
            DB.Checkboxall.get(i).setSelected(false);
        }
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
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdes = new javax.swing.JTextArea();
        cbstduyType = new javaapplication21.AutoComboBox();
        cbInterName = new javaapplication21.AutoComboBox();
        txtdateend = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        scpwork = new javax.swing.JScrollPane();
        jpstudy = new javax.swing.JPanel();
        btninsert = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelte = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbstudy = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbCate = new javaapplication21.AutoComboBox();
        cbMajor = new javaapplication21.AutoComboBox();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(989, 908));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(979, 859));
        jPanel1.setMinimumSize(new java.awt.Dimension(979, 859));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Search :");

        txtsearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Interviewee Name :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Study :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Description :");

        txtdes.setColumns(20);
        txtdes.setRows(5);
        jScrollPane1.setViewportView(txtdes);

        cbstduyType.setAutocomplete(true);
        cbstduyType.setKeyWord(new String[] {});
        cbstduyType.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbstduyTypePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbInterName.setAutocomplete(true);
        cbInterName.setKeyWord(new String[] {});
        cbInterName.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbInterNamePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtdateend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtdateendMouseReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Date End :");

        scpwork.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "StudySkill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scpwork.setViewportView(jpstudy);

        btninsert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btninsert.setText("Insert");
        btninsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninsertActionPerformed(evt);
            }
        });

        btnupdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btndelte.setText("Delete");
        btndelte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelteActionPerformed(evt);
            }
        });

        tbstudy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbstudy.setRowHeight(25);
        tbstudy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbstudyMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbstudy);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Major :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Category :");

        cbCate.setAutocomplete(true);
        cbCate.setKeyWord(new String[] {});
        cbCate.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbCatePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbMajor.setAutocomplete(true);
        cbMajor.setKeyWord(new String[] {});
        cbMajor.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbMajorPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Study");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(462, 462, 462)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btninsert, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btndelte, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbInterName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbstduyType, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(scpwork, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtdateend, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtdateend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMajor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbInterName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(cbstduyType, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scpwork, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btninsert, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btndelte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(995, 898));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtdateendMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdateendMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdateendMouseReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        c.FormartDate(txtdateend);
        ShowIntervieweeName();
        ShowStudyType();
        ShowCategory();
        ShowMajor();
        ShowStudy();
        ShowStudySkill();
    }//GEN-LAST:event_formWindowOpened

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
            SEARCH();
    }//GEN-LAST:event_txtsearchKeyReleased

    private void cbInterNamePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbInterNamePopupMenuWillBecomeInvisible
        ShowIdInterviewee();
    }//GEN-LAST:event_cbInterNamePopupMenuWillBecomeInvisible

    private void cbstduyTypePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbstduyTypePopupMenuWillBecomeInvisible
       ShowIdStudyType();
    }//GEN-LAST:event_cbstduyTypePopupMenuWillBecomeInvisible

    private void cbCatePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbCatePopupMenuWillBecomeInvisible
        ShowIdCategory();
    }//GEN-LAST:event_cbCatePopupMenuWillBecomeInvisible

    private void cbMajorPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbMajorPopupMenuWillBecomeInvisible
       ShowIdMajor();
    }//GEN-LAST:event_cbMajorPopupMenuWillBecomeInvisible

    private void tbstudyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbstudyMouseClicked
            int i = tbstudy.getSelectedRow();
            TableModel m = tbstudy.getModel();
            Uncheck();
            studyid.setText(m.getValueAt(i, 0).toString());
            cbInterName.setSelectedItem(m.getValueAt(i, 1).toString());
            cbstduyType.setSelectedItem(m.getValueAt(i, 2).toString());
            cbCate.setSelectedItem(m.getValueAt(i, 3).toString());
            cbMajor.setSelectedItem(m.getValueAt(i, 4).toString());
            txtdes.setText(m.getValueAt(i, 5).toString());
            txtstudyskill.setText(m.getValueAt(i, 6).toString());
            String editorText = txtstudyskill.getText();
                    editorText = editorText.replaceAll (",", " ");
                    txtstudyskill.setText(editorText);
                    System.out.println(txtstudyskill.getText()+"");
           linesToLinesAndWord(txtstudyskill.getText()+"");
            RemoveWordTheSame(txtstudyskill.getText());
            txtstudyskill.setText(null);
            for (String string : tempList) {
                
                txtstudyskill.append(string+" ");
            }
            c.FormartDateInTable(txtdateend, tbstudy, 7);
            ShowIdInterviewee();
            ShowIdStudyType();
            ShowIdCategory();
            ShowIdMajor();
            
    }//GEN-LAST:event_tbstudyMouseClicked

    private void btninsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninsertActionPerformed
        btnadd();
    }//GEN-LAST:event_btninsertActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        btnUpdate();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndelteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelteActionPerformed
        btndelete();
    }//GEN-LAST:event_btndelteActionPerformed

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
            java.util.logging.Logger.getLogger(Study.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Study.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Study.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Study.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Study().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelte;
    private javax.swing.JButton btninsert;
    private javax.swing.JButton btnupdate;
    private javaapplication21.AutoComboBox cbCate;
    private javaapplication21.AutoComboBox cbInterName;
    private javaapplication21.AutoComboBox cbMajor;
    private javaapplication21.AutoComboBox cbstduyType;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpstudy;
    private javax.swing.JScrollPane scpwork;
    private javax.swing.JTable tbstudy;
    private com.toedter.calendar.JDateChooser txtdateend;
    private javax.swing.JTextArea txtdes;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
