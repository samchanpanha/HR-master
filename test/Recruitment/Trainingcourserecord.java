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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author panha
 */
public class Trainingcourserecord extends javax.swing.JFrame {

    /**
     * Creates new form Training
     */
    public Trainingcourserecord() {
        initComponents();
    }
    DB c = new DB();
    DefaultTableModel dm;
    String Sql =null;
    JTextField txtinid = new JTextField();
    JTextField tranid = new JTextField();
    JTextField txttranid = new JTextField();
    JTextArea  txtTraningSkill = new JTextArea();
    List<String> tempList= new ArrayList<String>();
    List<String> duplicates= new ArrayList<String>();
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    
    private void ShowIntervieweeName(){
        Sql="SELECT Name FROM interviewees;";
        c.DisplayName(cbInterName, Sql);
        cbInterName.setSelectedItem("");
    }
    private void ShowIdInterviewee(){
        Sql="SELECT IntervieweeId FROM interviewees WHERE Name = '"+cbInterName.getSelectedItem()+"' ;";
        c.DisplayId(Sql, txtinid);
    }
    
    private void TrainingType(){
         Sql="SELECT TrainingCourseType\n" +
                "FROM trainingcoursetypes;";
         c.DisplayName(cbTrainingtype, Sql);
         cbTrainingtype.setSelectedItem("");
    }
    private void TraningId(){
         Sql="SELECT TrainingCourseTypeId FROM trainingcoursetypes WHERE TrainingCourseType = '"+cbTrainingtype.getSelectedItem()+"'";
         c.DisplayId(Sql, txttranid);
    }
    private void ShowTraning(){
         Sql="SELECT * FROM vu_traniningcour";
         c.showDataInTable(tbtraining, Sql, dm);
    }
    
    private void btnInsert(){
        String dstart = formater.format(txtdatestart.getDate());
        String dend = formater.format(txtdateend.getDate());
         Sql="INSERT INTO trainingcourserecords\n" +
    "(TrainingCourseTypeId, SkillTraining, Description, DateStart, DateEnd, IntervieweeId)\n" +
    "VALUES('"+txttranid.getText()+"', '"+txtTraningSkill.getText()+"', '"+txtdes.getText()+"', '"+dstart+"', '"+dend+"', '"+txtinid.getText()+"');";
         c.Query(Sql);
         ShowTraning();
    }
    private void btnUpdate(){
        String dstart = formater.format(txtdatestart.getDate());
        String dend = formater.format(txtdateend.getDate());
         Sql="UPDATE trainingcourserecords\n" +
    "SET TrainingCourseTypeId='"+txttranid.getText()+"', SkillTraining='"+txtTraningSkill.getText()+"', Description='"+txtdes.getText()+"', "
     + "DateStart='"+dstart+"', DateEnd='"+dend+"', IntervieweeId='"+txtinid.getText()+"'\n" +
    "WHERE TrainingCourseId="+tranid.getText()+";";
         c.Query(Sql);
         ShowTraning();
    }
    private void btnDelete(){
         Sql="DELETE FROM trainingcourserecords\n" +
        "WHERE TrainingCourseId="+tranid.getText()+";";
         c.Query(Sql);
         ShowTraning();
    }
    private void SEARCH(){
         Sql="CALL FindTraincour('"+txtsearch.getText()+"')";
         if ("".equals(txtsearch.getText())) {
            ShowTraning();
        }
         else{
             c.showDataInTable(tbtraining, Sql, dm);
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
        jLabel2 = new javax.swing.JLabel();
        cbInterName = new javaapplication21.AutoComboBox();
        txtsearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbTrainingtype = new javaapplication21.AutoComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdes = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtdatestart = new com.toedter.calendar.JDateChooser();
        txtdateend = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        scpwork = new javax.swing.JScrollPane();
        jptrain = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbtraining = new javax.swing.JTable();
        btninsert = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelte = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Search :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Interviewee Name :");

        cbInterName.setKeyWord(new String[] {"Male", "Female"});

        txtsearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Training :");

        cbTrainingtype.setKeyWord(new String[] {"Male", "Female"});

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Description :");

        txtdes.setColumns(20);
        txtdes.setRows(5);
        jScrollPane1.setViewportView(txtdes);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Date Start :");

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

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Date End :");

        scpwork.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TrainingSkill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scpwork.setViewportView(jptrain);

        tbtraining.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbtraining.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtrainingMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbtraining);

        btninsert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btninsert.setText("Insert");

        btnupdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnupdate.setText("Update");

        btndelte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btndelte.setText("Delete");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Training");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbInterName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbTrainingtype, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtdatestart, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtdateend, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(scpwork, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btninsert, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28)
                                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btndelte, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(231, 231, 231))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbInterName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTrainingtype, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdatestart, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdateend, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scpwork, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btninsert, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(952, 924));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtdatestartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdatestartMouseReleased

    }//GEN-LAST:event_txtdatestartMouseReleased

    private void txtdateendMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdateendMouseReleased
       
    }//GEN-LAST:event_txtdateendMouseReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        c.FormartDate(txtdateend);
        c.FormartDate(txtdatestart);
        ShowTraning();
        ShowIntervieweeName();
        TrainingType();
    }//GEN-LAST:event_formWindowOpened

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        SEARCH();
    }//GEN-LAST:event_txtsearchKeyReleased

    private void tbtrainingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbtrainingMouseClicked
            int i = tbtraining.getSelectedRow();
            TableModel m = tbtraining.getModel();
            tranid.setText(m.getValueAt(i, 0).toString());
            cbInterName.setSelectedItem(m.getValueAt(i, 1).toString());
            cbTrainingtype.setSelectedItem(m.getValueAt(i, 2).toString());
            Uncheck();
            txtTraningSkill.setText(m.getValueAt(i, 3).toString());
            String editorText = txtTraningSkill.getText();
                    editorText = editorText.replaceAll (",", " ");
                    txtTraningSkill.setText(editorText);
                    System.out.println(txtTraningSkill.getText()+"");
           linesToLinesAndWord(txtTraningSkill.getText()+"");
            RemoveWordTheSame(txtTraningSkill.getText());
            txtTraningSkill.setText(null);
            for (String string : tempList) {
                
                txtTraningSkill.append(string+" ");
            }
            txtdes.setText(m.getValueAt(i, 4).toString());
            c.FormartDateInTable(txtdatestart, tbtraining, 5);
            c.FormartDateInTable(txtdateend, tbtraining, 6);
            
    }//GEN-LAST:event_tbtrainingMouseClicked

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
            java.util.logging.Logger.getLogger(Trainingcourserecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trainingcourserecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trainingcourserecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trainingcourserecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Trainingcourserecord().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelte;
    private javax.swing.JButton btninsert;
    private javax.swing.JButton btnupdate;
    private javaapplication21.AutoComboBox cbInterName;
    private javaapplication21.AutoComboBox cbTrainingtype;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jptrain;
    private javax.swing.JScrollPane scpwork;
    private javax.swing.JTable tbtraining;
    private com.toedter.calendar.JDateChooser txtdateend;
    private com.toedter.calendar.JDateChooser txtdatestart;
    private javax.swing.JTextArea txtdes;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
