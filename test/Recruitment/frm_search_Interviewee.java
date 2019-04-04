/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recruitment;

import CMS.DB;
import javaapplication21.AutoComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author panha
 */
public class frm_search_Interviewee extends javax.swing.JFrame {

     JTextField txtskill = new JTextField();
     JTextField txtcate = new JTextField();
     JTextField txtmajor = new JTextField();
     DefaultTableModel dm;
    
     AutoComboBox ac = new AutoComboBox();
     DB c = new DB();
    /**
     * Creates new form frm_search_Interviewee
     */
     
    public frm_search_Interviewee() {
        initComponents();
      
    }
   void ShowSkill(){
       String sql ="SELECT Skill FROM skills";
         c.CreateSkill( jpskill ,txtskill,sql);
   }
   void ShowName(){
       String sql="SELECT Name From Interviewees GROUP BY Name";
       c.DisplayTextName(sql, txtname);
       //c.DisplayName(cbname, sql);
   }
   void ShowCategory(){
       String sql ="SELECT CatName FROM categorys";
         c.CreateSkill( jpcate ,txtcate,sql);
   }
   void ShowMajor(){
       String sql ="SELECT Major FROM Majors";
         c.CreateSkill( jpmajor ,txtmajor,sql);
   }
   void Changetablename(){
        c.ChangeName(tbdata,0,"ID");
        c.ChangeName(tbdata,8,"Skill");
        c.ChangeName(tbdata,11,"StudyRecord");
        c.ChangeName(tbdata,12,"Category");
        c.ChangeName(tbdata,14,"StudySkill");
        c.ChangeName(tbdata,19,"WorkSkill");
   }
   void ShowData(){
          String q = "i.IntervieweeId AS ID,\n" +
                    "	i.`Name`,\n" +
                    "	i.Gender,\n" +
                    "	i.Address,\n" +
                    "	i.Tel,\n" +
                    "	i.Blocked,\n" +
                    "	i.Degree,\n" +
                    "	i.`Language`,\n" +
                    "	i.Skill,\n" +
                    "	i.Dob ,\n" +
                    "	i.Email,\n" +
                    "	sp.StudyRecordType as StudyRecord,\n" +
                    "	c.CatName as Category,\n" +
                    "	m.Major,\n" +
                    "	s.Skill as StudySkill,\n" +
                    "	s.Description,\n" +
                    "	s.EndYear,\n" +
                    "	wp.WorkExperienceId,\n" +
                    "	p.Position,\n" +
                    "	wp.Skill as WorkSkill,\n" +
                    "	wp.DateStart,\n" +
                    "	wp.DateEnd,\n" +
                    "	wp.ExperienceOfYear ";
       
        String st = txtname.getText().toString()+" "+txtskill.getText()+" "+txtcate.getText()+" "+txtmajor.getText();
    
        String sql="SELECT \n" +
                    "  "+q+"  \n" +
                    "FROM\n" +
                    "    interviewees i\n" +
                    "        LEFT JOIN\n" +
                    "    studyrecords s ON i.IntervieweeId = s.IntervieweeId\n" +
                    "        LEFT JOIN\n" +
                    "    studyrecordtypes sp ON s.StudyRecordId = sp.StudyRecordTypeId\n" +
                    "        LEFT JOIN\n" +
                    "    categorys c ON s.CatID = c.CatID\n" +
                    "        LEFT JOIN\n" +
                    "    majors m ON s.MajorId = m.MajorId\n" +
                    "        LEFT JOIN\n" +
                    "    workexperiences wp ON i.IntervieweeId = wp.IntervieweeId\n" +
                    "        LEFT JOIN\n" +
                    "    positions p ON wp.PositionId = p.PositionID\n" +
                    "WHERE\n" +
                    "    MATCH (i.Name , i.Language , i.Degree , i.Email , i.Status , i.Skill) AGAINST ('"+st+"' IN BOOLEAN MODE)\n" +
                    "        OR MATCH (s.Description , s.Skill) AGAINST ('"+st+"' IN BOOLEAN MODE)\n" +
                    "        OR MATCH (wp.Description , wp.Skill) AGAINST ('"+st+"' IN BOOLEAN MODE)\n" +
                    "        OR MATCH (c.CatName) AGAINST ('"+st+"' IN BOOLEAN MODE)\n" +
                    "        OR MATCH (m.Major) AGAINST ('"+st+"' IN BOOLEAN MODE)\n" +
                    "ORDER BY "
                    + "MATCH (i.Name , i.Language , i.Degree , i.Email , i.Status , i.Skill) AGAINST ('"+st+"' IN BOOLEAN MODE) + "
                    + "MATCH (s.Description , s.Skill) AGAINST ('"+st+"' IN BOOLEAN MODE) + "
                    + "MATCH (wp.Description , wp.Skill) AGAINST ('"+st+"' IN BOOLEAN MODE);";
               if (!(st==null) && st!=" ") {
                    c.showDataInTable(tbdata, sql, dm);
                    Changetablename();
                    DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
                    tableRenderer.setHorizontalAlignment(JLabel.CENTER); //Aligning the table data centrally.
                    tbdata.setDefaultRenderer(Object.class, tableRenderer);
                    tbdata.setRowHeight(35);
               }
               else{
                  
                   JOptionPane.showMessageDialog(this, "no");
               }
              st=null;
               
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpskill = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbdata = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jpmajor = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jpcate = new javax.swing.JPanel();
        btnsearch = new javax.swing.JButton();
        txtname = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FROM SEARCH");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(533, 0, 198, 33));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Name : ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 80, 20));

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Skill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jpskill.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jpskill);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 350, 110));

        tbdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbdata);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 1420, 540));

        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Major", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jpmajor.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jpmajor);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, 350, 110));

        jScrollPane4.setViewportBorder(javax.swing.BorderFactory.createTitledBorder(null, "Category", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jpcate.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(jpcate);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 350, 110));

        btnsearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 70, 110, 30));

        txtname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 450, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        ShowSkill();
        ShowCategory();
        ShowMajor();
        ShowName();
    }//GEN-LAST:event_formWindowOpened

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
     
           ShowData();
        
    }//GEN-LAST:event_btnsearchActionPerformed
        
    

    
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
            java.util.logging.Logger.getLogger(frm_search_Interviewee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_search_Interviewee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_search_Interviewee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_search_Interviewee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_search_Interviewee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpcate;
    private javax.swing.JPanel jpmajor;
    private javax.swing.JPanel jpskill;
    private javax.swing.JTable tbdata;
    private javax.swing.JTextField txtname;
    // End of variables declaration//GEN-END:variables
}
