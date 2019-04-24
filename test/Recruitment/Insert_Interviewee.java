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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author panha
 */
public final class Insert_Interviewee extends javax.swing.JFrame {

    /**
     * Creates new form Insert_Interviewee
     */
    DB c =new DB();
    String InID=null;
    String stskill =null;
    JTextField id = new JTextField();
    JTextField interid = new JTextField();
    DefaultTableModel dm;
    JTextArea txtskill = new JTextArea();
    List<JCheckBox> call =new ArrayList<>();
    List<String> tempList= new ArrayList<>();
    List<String> duplicates= new ArrayList<>();

    
    public Insert_Interviewee() {
        initComponents();
     ShowSkill();
     VIEW();
    c.FormartDate(txtdate);
      
    }
    
    void ShowSkill(){
        String sql ="SELECT Skill FROM skills";
        c.CreateSkill( jpskill ,txtskill,sql);
        System.out.println(txtskill.getText()+"");
    }
     void viewid(){
        c.Value_ID(id);
        JOptionPane.showMessageDialog(this, id.getText()+"");
    }
    void btadd(){
      
        String name = txtname.getText();
        String gen = cbgender.getSelectedItem().toString();
        String adr = txtaddress.getText();
        String tel = txttel.getText();
        String sta = cbstatus.getSelectedItem().toString();
        String block = cbblock.getSelectedItem().toString();
        String de = txtdegree.getText();
        String lan = txtlanguage.getText();
        String sk = txtskill.getText();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String dod =formater.format(txtdate.getDate());
        String emaill = txtemail.getText();
        String sql="CALL InsertIW('"+name+"','"+gen+"','"+adr+"','"+tel+"','"+sta+"',"
                                    + "'"+block+"','"+de+"','"+lan+"','"+sk+"','"+dod+"','"+emaill+"')";
        c.Query(sql);
        viewid();
        String query ="UPDATE interviewees SET Image=? WHERE IntervieweeId = ?";
        c.Add_Pic(id.getText(), query);
       
        VIEW();
    }
  
    
    void btupdate(){
        try {
        String name = txtname.getText();
        String gen = cbgender.getSelectedItem().toString();
        String adr = txtaddress.getText();
        String tel = txttel.getText();
        String sta = cbstatus.getSelectedItem().toString();
        String block = cbblock.getSelectedItem().toString();
        String de = txtdegree.getText();
        String lan = txtlanguage.getText();
        String sk = txtskill.getText();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String dod =formater.format(txtdate.getDate());
        String emaill = txtemail.getText();
        String sql="UPDATE\n" +
                    "	db_hrm.interviewees\n" +
                    "SET\n" +
                    "	Name = '"+name+"',\n" +
                    "	Gender = '"+gen+"',\n" +
                    "	Address = '"+adr+"',\n" +
                    "	Tel = '"+tel+"',\n" +
                    "	Status = '"+sta+"',\n" +
                    "	Blocked = '"+block+"',\n" +
                    "	Degree = '"+de+"',\n" +
                    "	Language = '"+lan+"',\n" +
                    "	Skill = '"+sk+"',\n" +
                    "	Dob = '"+dod+"',\n" +
                    "	Email = '"+emaill+"'\n" +
                    "WHERE IntervieweeId ="+interid.getText()+";";
        if("".equals(c.filename)||c.filename==null){
            c.Query(sql);
             VIEW();
        }
        else{
             c.Query(sql);
         String query ="UPDATE interviewees SET Image=? WHERE IntervieweeId = ?";
         c.Add_Pic(interid.getText(), query);
         VIEW();
        }
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
       
    }
    void btdelete(){
        String sql="DELETE FROM interviewees\n" +
                   "WHERE IntervieweeId="+interid.getText()+";";
        c.Query(sql);
        VIEW();
    }
    void SEARCH(){
        String sql="SELECT IntervieweeId, Name, Gender, Address, Tel, Image, Status, Blocked, `Degree`, `Language`, Skill, Dob, Email\n" +
                    "FROM interviewees i where  MATCH (i.Name , i.Language , i.Degree , i.Email , i.Status , i.Skill)\n" +
                    "AGAINST ('"+txtsearch.getText()+"' IN BOOLEAN MODE);";
        if ("".equals(txtsearch.getText())) {
            VIEW();
        }
        else
        {
            c.showDataInTable(tbdata, sql, dm);
            c.HideColunmsTable(tbdata, 5);
            c.ChangeName(tbdata, 0, "ID");
        }    
    }
    
    void VIEW(){
       
         String sql="SELECT IntervieweeId AS ID, Name, Gender, Address, Tel,"
                    + " Image, Status, Blocked, `Degree`, `Language`,"
                    + " Skill, Dob, Email\n" +
                    "FROM interviewees;";
       c.showDataInTable(tbdata, sql, dm); 
       c.HideColunmsTable(tbdata, 5);
       c.ChangeName(tbdata, 0, "ID");
    }
    void CLEAR(){
        
    }
    public  List<List<String>> linesToLinesAndWord(String lines) {
    List<List<String>> wordlists = new ArrayList<>();
    List<String> lineList = Arrays.asList(lines.split("\n"));
    
    lineList.forEach((line) -> {
        //   System.out.println(line+"");
        wordlists.add(Arrays.asList(line.trim().split(" ")));
        });      
    DB.Checkboxall.forEach((JCheckBox cb) -> {
            String name = cb.getName();
            wordlists.forEach((List<String> wordlist) -> {
                wordlist.stream().filter((string) -> (name == null ? string == null : name.equals(string))).forEachOrdered((String _item) -> {
                    cb.setSelected(isActive());
                });
        });
          });  
    return wordlists;
}
    
    public  List<List<String>> RemoveWordTheSame(String lines) {
    List<List<String>> wordlists = new ArrayList<>();
    List<String> lineList = Arrays.asList(lines.split("\n"));
    
    lineList.forEach((line) -> {
        //   System.out.println(line+"");
        wordlists.add(Arrays.asList(line.trim().split(" ")));
        });
    
    wordlists.forEach((List<String> wordlist) -> {
        wordlist.forEach((dupWord) -> {
            if (!tempList.contains(dupWord)) {
                tempList.add(dupWord);
            }else{
                duplicates.add(dupWord);
            }
        });
        });
        int k = 0;
      while (k < tempList.size())
      {
         if (tempList.get(k).equals(""))
            tempList.remove(k);
         k++;
      }
     
    return wordlists;
}
   void Uncheck(){
         for (int i = 0; i < DB.Checkboxall.size(); i++) {
            DB.Checkboxall.get(i).setSelected(false);
        }
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpm = new javax.swing.JPopupMenu();
        mUpdate = new javax.swing.JMenuItem();
        mDelete = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jpinterviewee = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtdegree = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbblock = new javaapplication21.AutoComboBox();
        pic = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdata = new javax.swing.JTable();
        txttel = new javax.swing.JTextField();
        txtsearch = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        cbstatus = new javaapplication21.AutoComboBox();
        jLabel7 = new javax.swing.JLabel();
        btntran = new javax.swing.JButton();
        cbgender = new javaapplication21.AutoComboBox();
        txtdate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpskill = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtaddress = new javax.swing.JTextField();
        txtlanguage = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        btnwork = new javax.swing.JButton();
        btndtudy = new javax.swing.JButton();

        mUpdate.setText("UPDATE");
        mUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mUpdateActionPerformed(evt);
            }
        });
        jpm.add(mUpdate);

        mDelete.setText("DELETE");
        mDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mDeleteActionPerformed(evt);
            }
        });
        jpm.add(mDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INTERVIEWEE");

        jpinterviewee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Language :");
        jpinterviewee.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 80, 28));

        txtdegree.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpinterviewee.add(txtdegree, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 220, 28));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Degree :");
        jpinterviewee.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 70, 28));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Block :");
        jpinterviewee.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 50, 50, 28));

        cbblock.setKeyWord(new String[] {"none", "block"});
        jpinterviewee.add(cbblock, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, 220, 28));

        pic.setBackground(new java.awt.Color(255, 255, 255));
        jpinterviewee.add(pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 10, 140, 150));

        jButton1.setText("Choose Photo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jpinterviewee.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 170, 121, 31));

        tbdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbdata.setRowHeight(30);
        tbdata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdataMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbdataMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbdata);

        jpinterviewee.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 282, 1420, 490));

        txttel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpinterviewee.add(txttel, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 220, 28));

        txtsearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });
        jpinterviewee.add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 520, 28));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Tel :");
        jpinterviewee.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 80, 28));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Search :");
        jpinterviewee.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 50, 28));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Email :");
        jpinterviewee.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, 50, 28));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Status :");
        jpinterviewee.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 50, 28));

        txtemail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpinterviewee.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 90, 220, 28));

        cbstatus.setKeyWord(new String[] {"none", "block"});
        jpinterviewee.add(cbstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 220, 28));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Dob :");
        jpinterviewee.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 60, 28));

        btntran.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btntran.setText("Training");
        btntran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntranActionPerformed(evt);
            }
        });
        jpinterviewee.add(btntran, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 220, 100, 36));

        cbgender.setKeyWord(new String[] {"Male", "Female"});
        jpinterviewee.add(cbgender, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 220, 31));

        txtdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtdateMouseReleased(evt);
            }
        });
        jpinterviewee.add(txtdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, 220, 28));

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Skill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jpskill.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jpskill);

        jpinterviewee.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 320, 110));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Name :");
        jpinterviewee.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 70, 28));

        txtname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpinterviewee.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 220, 28));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Gender :");
        jpinterviewee.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 70, 28));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Address :");
        jpinterviewee.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 70, 28));

        txtaddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpinterviewee.add(txtaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 220, 58));

        txtlanguage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpinterviewee.add(txtlanguage, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 220, 28));

        btnInsert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });
        jpinterviewee.add(btnInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 100, 36));

        btnwork.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnwork.setText("WorkExperice");
        btnwork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnworkActionPerformed(evt);
            }
        });
        jpinterviewee.add(btnwork, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, 100, 36));

        btndtudy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btndtudy.setText("Study");
        btndtudy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndtudyActionPerformed(evt);
            }
        });
        jpinterviewee.add(btndtudy, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 220, 100, 36));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(554, 554, 554))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpinterviewee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpinterviewee, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    
      
    // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void tbdataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdataMouseClicked
              int i = tbdata.getSelectedRow();
              TableModel m = tbdata.getModel();
           
        try {
            interid.setText(m.getValueAt(i, 0).toString());
            txtname.setText(m.getValueAt(i,1 ).toString());
            cbgender.setSelectedItem(m.getValueAt(i,2 ).toString());
            txtaddress.setText(m.getValueAt(i,3 ).toString());
            txttel.setText(m.getValueAt(i,4 ).toString());
            cbstatus.setSelectedItem(m.getValueAt(i,6 ).toString());
            cbblock.setSelectedItem(m.getValueAt(i,7 ).toString());
            txtdegree.setText(m.getValueAt(i,8 ).toString());
            txtlanguage.setText(m.getValueAt(i,9 ).toString());
            
            String sle="SELECT Image From interviewees WHERE IntervieweeId = '"+interid.getText()+"'";
            c.showpic(sle, pic);
            System.out.println(m.getValueAt(i, 0).toString()+"");
            Uncheck();
            c.FormartDateInTable(txtdate, tbdata, 11);        
            txtskill.setText(m.getValueAt(i, 10).toString());
                    String editorText = txtskill.getText();
                    editorText = editorText.replaceAll (","," ");
                    txtskill.setText(editorText);
            linesToLinesAndWord(txtskill.getText()+"");

            RemoveWordTheSame(txtskill.getText());
            txtskill.setText(null);
            tempList.forEach((String string) -> {
                txtskill.append(string+" ");
                  });
           
            txtemail.setText(m.getValueAt(i,12 ).toString());
            
        } 
        catch (Exception ex) 
        {
          JOptionPane.showMessageDialog(null, ex.getMessage());
        }
             
              // System.out.println(m.getValueAt(i, 0).toString()+" "+m.getValueAt(i, 1).toString());
    }//GEN-LAST:event_tbdataMouseClicked

    private void tbdataMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdataMouseReleased
        c.TableMouseReleased(evt, tbdata, jpm);
    }//GEN-LAST:event_tbdataMouseReleased

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
            SEARCH();
    }//GEN-LAST:event_txtsearchKeyReleased

    private void mUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mUpdateActionPerformed
           btupdate();
    }//GEN-LAST:event_mUpdateActionPerformed

    private void txtdateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdateMouseReleased

    }//GEN-LAST:event_txtdateMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            c.bro(pic);
     
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btntranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntranActionPerformed
     
        
    }//GEN-LAST:event_btntranActionPerformed

    private void mDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mDeleteActionPerformed
        btdelete();
    }//GEN-LAST:event_mDeleteActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
          btadd();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnworkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnworkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnworkActionPerformed

    private void btndtudyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndtudyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btndtudyActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Insert_Interviewee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Insert_Interviewee().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btndtudy;
    private javax.swing.JButton btntran;
    private javax.swing.JButton btnwork;
    private javaapplication21.AutoComboBox cbblock;
    private javaapplication21.AutoComboBox cbgender;
    private javaapplication21.AutoComboBox cbstatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JPanel jpinterviewee;
    private javax.swing.JPopupMenu jpm;
    private javax.swing.JPanel jpskill;
    private javax.swing.JMenuItem mDelete;
    private javax.swing.JMenuItem mUpdate;
    private javax.swing.JLabel pic;
    private javax.swing.JTable tbdata;
    private javax.swing.JTextField txtaddress;
    private com.toedter.calendar.JDateChooser txtdate;
    private javax.swing.JTextField txtdegree;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtlanguage;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtsearch;
    private javax.swing.JTextField txttel;
    // End of variables declaration//GEN-END:variables
}
