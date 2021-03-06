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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author panha
 */
public class WorkExpericence extends javax.swing.JInternalFrame {

    /**
     * Creates new form WorkExpericence
     */
    public WorkExpericence() {
        initComponents();
        
    }
    DB c = new DB();
    DefaultTableModel dm;
    String Sql=null;
    JTextField workid =new JTextField();
    JTextField txtinid = new JTextField();
    JTextField txtpid = new JTextField();
    JTextArea txtworkskill = new JTextArea();
    List<String> tempList= new ArrayList<String>();
    List<String> duplicates= new ArrayList<String>();
    
    private void ShowNameInterviewee(){
        Sql="SELECT Name FROM interviewees"; 
        c.DisplayName(cbInterName, Sql);
        cbInterName .setSelectedItem("");
    }
    private void ShowIdInterviewee(){
        Sql="SELECT IntervieweeId FROM interviewees WHERE Name ='"+cbInterName.getSelectedItem().toString()+"'";
        c.DisplayId(Sql, txtinid);
        System.out.println(txtinid.getText());
    }
    
    private void ShowPosition(){
        Sql="SELECT `Position`\n" +
            "FROM positions;";
        c.DisplayName(cbPositon, Sql);
        cbPositon.setSelectedItem("");
    }
    private void ShowPositonID(){
         Sql="SELECT PositionID \n" +
             "FROM positions WHERE Position = '"+cbPositon.getSelectedItem().toString()+"';";
        c.DisplayId(Sql, txtpid);
        
        System.out.println(txtpid.getText());
        
    }
    private void WorkSkill(){
        Sql="SELECT Skill\n" +
             "FROM skills;";
        c.CreateSkill(jpwork, txtworkskill, Sql);
    }
    private void ShowWork(){
        Sql="SELECT * FROM workexp";
        c.showDataInTable(tbwork, Sql, dm);
    }
    private void SEARCH(){
        Sql="select * from workexp where Name like '%"+txtsearch.getText().toString().trim()+"%';";
        if("".equals(txtsearch.getText())){
            ShowWork();
        }
        else{
            c.showDataInTable(tbwork, Sql, dm);
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
    private void INSERT(){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String dstart =formater.format(txtdatestart.getDate());
        String dend = formater.format(txtdateend.getDate());
        Sql="CALL ADDWEP('"+Integer.parseInt(txtpid.getText())+"','"+txtdes.getText()+"','"+txtworkskill.getText()+"',"
                + "'"+dstart+"','"+dend+"','"+Integer.parseInt(txtinid.getText())+"','"+Integer.parseInt(txtExYear.getText())+"')";
        c.Query(Sql);
        ShowWork();
    }
    private void UPDATE(){
        String pid = txtpid.getText();
        String des = txtdes.getText();
        String sk = txtworkskill.getText();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String dstart =formater.format(txtdatestart.getDate());
        String dend = formater.format(txtdateend.getDate());
        String inwe = txtinid.getText();
        String year = txtExYear.getText();
        String wid  = workid.getText();
        Sql="UPDATE `db_hrm`.`workexperiences` SET `PositionId` = "+Integer.parseInt(pid)+", `Description` = '"+des+"', \n" +
            "`Skill` = '"+sk+"', `DateStart` = '"+dstart+"',\n" +
            "`DateEnd` = '"+dend+"', `IntervieweeId` = "+Integer.parseInt(inwe)+", `ExperienceOfYear` = "+Integer.parseInt(year)+" \n" +
            "WHERE `WorkExperienceId` = "+Integer.parseInt(wid)+";";
        c.Query(Sql);
        ShowWork();
    }
    private void DELETE(){
        Sql="CALL deleteWEP("+Integer.parseInt(workid.getText())+")";
        c.Query(Sql);
        ShowWork();
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbInterName = new javaapplication21.AutoComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbPositon = new javaapplication21.AutoComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdes = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtExYear = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdatestart = new com.toedter.calendar.JDateChooser();
        txtdateend = new com.toedter.calendar.JDateChooser();
        scpwork = new javax.swing.JScrollPane();
        jpwork = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbwork = new javax.swing.JTable();
        btninsert = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelte = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(989, 908));
        setMinimumSize(new java.awt.Dimension(989, 908));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("WorkExpericence");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Interviewee Name :");

        cbInterName.setMaximumRowCount(10);
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Positon :");

        cbPositon.setMaximumRowCount(10);
        cbPositon.setAutocomplete(true);
        cbPositon.setKeyWord(new String[] {});
        cbPositon.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbPositonPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Description :");

        txtdes.setColumns(20);
        txtdes.setRows(5);
        jScrollPane1.setViewportView(txtdes);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Date Start :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ExperienceOfYear :");

        txtExYear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Date End :");

        txtdatestart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtdatestartMouseReleased(evt);
            }
        });

        txtdateend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtdateendMouseReleased(evt);
            }
        });

        scpwork.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "WorkSkill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scpwork.setViewportView(jpwork);

        tbwork.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbwork.setRowHeight(25);
        tbwork.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbworkMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbwork);

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

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Search :");

        txtsearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btninsert, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btndelte, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(285, 285, 285))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(343, 343, 343))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbInterName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtdatestart, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbPositon, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtdateend, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtExYear, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(scpwork, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbInterName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtdatestart, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbPositon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtdateend, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExYear, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(scpwork)
                        .addGap(10, 10, 10)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btninsert, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1005, 873));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtdatestartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdatestartMouseReleased

    }//GEN-LAST:event_txtdatestartMouseReleased

    private void txtdateendMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdateendMouseReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtdateendMouseReleased

    private void cbInterNamePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbInterNamePopupMenuWillBecomeInvisible
        if (cbInterName.getSelectedIndex()!=-1) {
               ShowIdInterviewee();
        }  
    }//GEN-LAST:event_cbInterNamePopupMenuWillBecomeInvisible

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
         ShowNameInterviewee();
         ShowPosition();
         WorkSkill();
         ShowWork();
         c.FormartDate(txtdatestart);
         c.FormartDate(txtdateend);
         c.TextOnlyCharacters(txtsearch);
         c.TextOnlyNumber(txtExYear);
         
    }//GEN-LAST:event_formWindowOpened

    private void cbPositonPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbPositonPopupMenuWillBecomeInvisible
           if (cbPositon.getSelectedIndex()!=-1) {
               ShowPositonID();
        }
    }//GEN-LAST:event_cbPositonPopupMenuWillBecomeInvisible

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        SEARCH();
    }//GEN-LAST:event_txtsearchKeyReleased

    private void tbworkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbworkMouseClicked
           int i = tbwork.getSelectedRow();
           TableModel m = tbwork.getModel();
        try {
            Uncheck();
           workid.setText(m.getValueAt(i, 0).toString());
           cbInterName.setSelectedItem(m.getValueAt(i, 1).toString());
           cbPositon.setSelectedItem(m.getValueAt(i, 2).toString());
           txtdes.setText(m.getValueAt(i, 3).toString());
           txtworkskill.setText(m.getValueAt(i, 4).toString());
                    String editorText = txtworkskill.getText();
                    editorText = editorText.replaceAll (",", " ");
                    txtworkskill.setText(editorText);
                    System.out.println(txtworkskill.getText()+"");
           linesToLinesAndWord(txtworkskill.getText()+"");
            RemoveWordTheSame(txtworkskill.getText());
            txtworkskill.setText(null);
            for (String string : tempList) {
                
                txtworkskill.append(string+" ");
            }
           c.FormartDateInTable(txtdatestart, tbwork, 5);
           c.FormartDateInTable(txtdateend, tbwork, 6);
           txtExYear.setText(m.getValueAt(i, 7).toString());
           ShowIdInterviewee();
           ShowPositonID();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
           
    }//GEN-LAST:event_tbworkMouseClicked

    private void btninsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninsertActionPerformed
        INSERT();
    }//GEN-LAST:event_btninsertActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        UPDATE();
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndelteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelteActionPerformed
        DELETE();
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
            java.util.logging.Logger.getLogger(WorkExpericence.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WorkExpericence.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WorkExpericence.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WorkExpericence.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WorkExpericence().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelte;
    private javax.swing.JButton btninsert;
    private javax.swing.JButton btnupdate;
    private javaapplication21.AutoComboBox cbInterName;
    private javaapplication21.AutoComboBox cbPositon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpwork;
    private javax.swing.JScrollPane scpwork;
    private javax.swing.JTable tbwork;
    private javax.swing.JTextField txtExYear;
    private com.toedter.calendar.JDateChooser txtdateend;
    private com.toedter.calendar.JDateChooser txtdatestart;
    private javax.swing.JTextArea txtdes;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables

    private void setLocationByPlatform(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
