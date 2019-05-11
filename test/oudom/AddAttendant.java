/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oudom;

import CA.AC;
import CA.ConAccess;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chim chanoudom
 */
public class AddAttendant extends javax.swing.JFrame {

    /**
     * Creates new form AddAttendant
     */
    public AddAttendant() {
        initComponents();
         txtspec.setEnabled(false);
        dm=(DefaultTableModel) tbattendant.getModel();
    }
AC c = new AC();
    DefaultTableModel dm;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        btndelete = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbattendant = new controls.SubJTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtDateStart = new controls.JDateTimePicker();
        txtenddate = new controls.JDateTimePicker();
        jLabel15 = new javax.swing.JLabel();
        btnShow = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        txtspec = new controls.SubJComboBox();
        jLabel16 = new javax.swing.JLabel();
        txtcondit = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        txtTimeStart = new controls.JDateTimePicker();
        jLabel18 = new javax.swing.JLabel();
        txttimeend = new controls.JDateTimePicker();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtemp = new controls.SubJComboBox();
        jLabel13 = new javax.swing.JLabel();
        txtDateCreate = new controls.JDateTimePicker();
        jPanel1 = new javax.swing.JPanel();
        lbltitle = new javax.swing.JLabel();

        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        jPopupMenu1.add(btndelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Retrive Date", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tbattendant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbattendant.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        tbattendant.setGridColor(new java.awt.Color(204, 204, 204));
        tbattendant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbattendantMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbattendant);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Date Start :");

        txtDateStart.setBackground(new java.awt.Color(255, 255, 255));
        txtDateStart.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        txtDateStart.setFormatPattern("yyyy/MM/dd");

        txtenddate.setBackground(new java.awt.Color(255, 255, 255));
        txtenddate.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        txtenddate.setFormatPattern("yyyy/MM/dd");

        jLabel15.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("To Date End :");

        btnShow.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        btnShow.setText("Show");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        txtspec.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        txtspec.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                txtspecPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                txtspecPopupMenuWillBecomeVisible(evt);
            }
        });
        txtspec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtspecActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Employee Name : ");

        txtcondit.setBackground(new java.awt.Color(255, 255, 255));
        txtcondit.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        txtcondit.setText("Enable");
        txtcondit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtconditActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Time Start :");

        txtTimeStart.setBackground(new java.awt.Color(255, 255, 255));
        txtTimeStart.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        txtTimeStart.setFormatPattern("HH:mm a");

        jLabel18.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("To Time End :");

        txttimeend.setBackground(new java.awt.Color(255, 255, 255));
        txttimeend.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        txttimeend.setFormatPattern("HH:mm a");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimeStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtenddate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txttimeend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcondit)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtspec, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtenddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDateStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtcondit, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txttimeend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimeStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtspec, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Schoolbook", 0, 14))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("ID :");

        txtID.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        txtID.setText("ID ");

        jLabel12.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Date Created :");

        txtemp.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Century Schoolbook", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Created By :");

        txtDateCreate.setBackground(new java.awt.Color(255, 255, 255));
        txtDateCreate.setFont(new java.awt.Font("Century Schoolbook", 0, 14)); // NOI18N
        txtDateCreate.setFormatPattern("yyyy/MM/dd");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDateCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtemp, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDateCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        lbltitle.setFont(new java.awt.Font("Century Schoolbook", 0, 24)); // NOI18N
        lbltitle.setForeground(new java.awt.Color(255, 255, 255));
        lbltitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitle.setText("Attendent");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltitle, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void DisplayCheckTime(){
        
        if (txtenddate.getValue().compareTo(txtDateStart.getValue())<0 && txttimeend.getValue().compareTo(txtTimeStart.getValue())<0) {
                JOptionPane.showMessageDialog(this,"Invalid Date And Time");
                return;
            }
        SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat TimeFormat=new SimpleDateFormat("HH:mm");
          String ds =formater.format(txtDateStart.getValue());
          ds="#"+ds+"#";
          String de =formater.format(txtenddate.getValue());
          de="#"+de+"#";
          String ts=TimeFormat.format(txtTimeStart.getValue());
          
          String te=TimeFormat.format(txttimeend.getValue());
          //System.out.println(ts+" "+te);
        try {
            
            
            if (txtcondit.isSelected()==true) {
               
                String sql ="SELECT USERID ,`Name` , CHECKTIME ,Switch(CHECKTYPE=\"I\",\"CheckIns\",CHECKTYPE=\"O\",\"CheckOut\") AS Check FROM CHECKINOUT LEFT JOIN USERINFO ON CHECKINOUT.USERID=USERINFO.USERID  WHERE CHECKINOUT.USERID='"+Stid+"' AND Format (CHECKINOUT.CHECKTIME,\"Short Date\") BETWEEN Format ("+ds+",\"Short Date\") AND Format ("+de+",\"Short Date\") AND Format([CHECKTIME],\"Short Time\") BETWEEN \""+ts+"\" AND \""+te+"\";";
                 c.showDataInTable(tbattendant, sql, dm);
            }
            else{
                
                String sql="SELECT CHECKINOUT.USERID ,`Name` , CHECKTIME ,Switch(CHECKTYPE=\"I\",\"CheckIns\",CHECKTYPE=\"O\",\"CheckOut\") AS Check FROM CHECKINOUT LEFT JOIN USERINFO ON CHECKINOUT.USERID=USERINFO.USERID  WHERE  Format (CHECKINOUT.CHECKTIME,\"Short Date\") BETWEEN Format ("+ds+",\"Short Date\") AND Format ("+de+",\"Short Date\")AND Format([CHECKTIME],\"Short Time\") BETWEEN \""+ts+"\" AND \""+te+"\";";
               // System.out.println(sql);
                c.showDataInTable(tbattendant, sql, dm);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
private void DisplayName(){
          if (txtcondit.isSelected()==true) {
            txtspec.setEnabled(true);
           String sql = "SELECT  Name FROM USERINFO;";
           c.DisplayName(txtspec, sql);
           txtspec.setSelectedItem("");
           txtcondit.setSelected(true);
          
        }
        else
        {
            txtspec.setEnabled(false);
            txtcondit.setSelected(false);
        }
    }
String Stid;
private void DisplayId(){
          try {
            String st = txtspec.getSelectedItem().toString();
//             System.out.println(st);
              String sql = "SELECT  USERID FROM USERINFO WHERE Name = '"+st+"';";
              Stid=c.DisplayId(sql);
            System.out.println(Stid);
        } catch (Exception e) {
        }
    }
    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
           DisplayCheckTime();
    }//GEN-LAST:event_btnShowActionPerformed

    private void txtconditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtconditActionPerformed
        DisplayName();
    }//GEN-LAST:event_txtconditActionPerformed

    private void txtspecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtspecActionPerformed
           //System.out.println(txtspec.getSelectedItem());
    }//GEN-LAST:event_txtspecActionPerformed

    private void txtspecPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_txtspecPopupMenuWillBecomeVisible
            
    }//GEN-LAST:event_txtspecPopupMenuWillBecomeVisible

    private void txtspecPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_txtspecPopupMenuWillBecomeInvisible
     DisplayId();
    }//GEN-LAST:event_txtspecPopupMenuWillBecomeInvisible
private String CheckDate(Date Date,String Nameuser){
    SimpleDateFormat f=new SimpleDateFormat("yyyy/MM/dd");
    String Dt = "";
    DefaultTableModel tm =new DefaultTableModel();
    tm.addColumn("DayStart");
    tm.addColumn("DayEnd");
    try {
        dataCon.connectToDB();
        dataCon.executeQry("Select DayStart,DayEnd\n" +
"from employees join workdays on employees.WorkDayId=workdays.WorkDayId \n" +
"join interviewees on employees.IntervieweeId=interviewees.IntervieweeId\n" +
"where Name=\""+Nameuser+"\" ;", tm);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
    Calendar c=Calendar.getInstance(TimeZone.getDefault());
    c.setTime(Date);
    int dow=c.get(Calendar.DAY_OF_WEEK);
    dow-=1;
    for (int i = 0; i < tm.getRowCount(); i++) {
        int s=(int) tm.getValueAt(i, 0);
        int e=(int) tm.getValueAt(i, 1);
        if(dow>=s&&dow<=e){
            Dt=f.format(Date);
        }else{
            Dt="Invalid";
        }
    }
    
        return Dt;
}
private void CreateStatement(String Name,Date date) throws ParseException{
    int Count=0;
    String DateInOut="";
    //count Data
    for (int i = 0; i < dm.getRowCount(); i++) {
        String n=(String) dm.getValueAt(i, 1);
        if(Name.equalsIgnoreCase(n)){
            Count++;
        }
    }
        if(Count>0){
                
    
    //Check Date
   DateInOut=CheckDate(date, Name);
    
    
    
    //Check Time With Timeshift and Status
    DefaultTableModel tm =new DefaultTableModel();

        try {
            String timeshift="";
        dataCon.connectToDB();
        dataCon.executeQry("Select timeshifts.Description\n" +
"from employees join workdays on employees.WorkDayId=workdays.WorkDayId \n" +
"join interviewees on employees.IntervieweeId=interviewees.IntervieweeId\n" +
"join timeshifts on workdays.TimeShiftId=timeshifts.TimeShiftId\n" +
"where Name=\"Vireak\" ;", timeshift);
            if ("FullTime"==timeshift) {
                    tm.addColumn("TimeStart");
                    tm.addColumn("BreakTimeStart");
                    tm.addColumn("BreakTimeEnd");
                    tm.addColumn("TimeEnd");        
                dataCon.executeQry("Select TimeStart,BreakTimeStart,BreakTimeEnd,TimeEnd\n" +
"from employees join workdays on employees.WorkDayId=workdays.WorkDayId \n" +
"join interviewees on employees.IntervieweeId=interviewees.IntervieweeId\n" +
"join timeshifts on workdays.TimeShiftId=timeshifts.TimeShiftId\n" +
"where Name=\""+Name+"\" ;", tm);
            }else{
                dataCon.executeQry("Select TimeStart,TimeEnd\n" +
"from employees join workdays on employees.WorkDayId=workdays.WorkDayId \n" +
"join interviewees on employees.IntervieweeId=interviewees.IntervieweeId\n" +
"join timeshifts on workdays.TimeShiftId=timeshifts.TimeShiftId\n" +
"where Name=\"Vireak\" ;", tm);
            }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
        SimpleDateFormat f=new SimpleDateFormat("HH:mm");
        Long nT,St1,St2;
            for (int i = 0; i <dm.getRowCount(); i++) {
                String nt=(String) dm.getValueAt(i, 1);
                String check=(String) dm.getValueAt(i, 3);
                if (Name.equalsIgnoreCase(nt)&&"CheckIns".equalsIgnoreCase(check)) {
                    String DT=(String) dm.getValueAt(i, 2);
                    Object []t=DT.split(" ");
                    
                    Date r=f.parse(t[1].toString());
                     nT=r.getTime();
  
                }
            }
            if (tm.getColumnCount()>=2) {
                for (int i = 0; i < tm.getRowCount(); i++) {
                    
                }
            }else{
                
            }
        }
        
    
    
}
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
//            for (int i = 0; i < tbattendant.getRowCount(); i++) {
//            
//        }
System.out.println(txtDateStart.getValue());
System.out.println(CheckDate(txtDateStart.getValue(),"Vireak"));
    }//GEN-LAST:event_btnSaveActionPerformed
private void deleterow(){
     while (tbattendant.getSelectedRowCount()>0) {                
                int id=tbattendant.getSelectedRow();
                //int i=(int) dm.getValueAt(id, 0);
                 dm.removeRow(id);
     }
}
    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        if(evt.getButton()==MouseEvent.BUTTON3 && tbattendant.getSelectedRowCount()>=1 ){
            jPopupMenu1.show(jScrollPane1, evt.getX(), evt.getY());}
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tbattendantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbattendantMouseClicked
                if(evt.getButton()==MouseEvent.BUTTON3 && tbattendant.getSelectedRowCount()>=1 ){
            jPopupMenu1.show(jScrollPane1, evt.getX(), evt.getY());}
    }//GEN-LAST:event_tbattendantMouseClicked

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
       deleterow();
    }//GEN-LAST:event_btndeleteActionPerformed
//SELECT CHECKINOUT.USERID, CHECKINOUT.CHECKTIME, CHECKINOUT.CHECKTYPE,
//Format([CHECKTIME],"Short Time") as 'Time'
//FROM CHECKINOUT
//WHERE Format([CHECKTIME],"Short Time")="09:04";
    
//    
//    SELECT CHECKINOUT.USERID, CHECKINOUT.CHECKTIME, CHECKINOUT.CHECKTYPE,
//Format([CHECKTIME],"Short Time") as 'Time'
//FROM CHECKINOUT
//WHERE Format([CHECKTIME],"Short Time") BETWEEN "09:04" AND "14:00" ;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddAttendant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddAttendant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddAttendant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddAttendant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddAttendant().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnShow;
    private javax.swing.JMenuItem btndelete;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbltitle;
    private controls.SubJTable tbattendant;
    private controls.JDateTimePicker txtDateCreate;
    private controls.JDateTimePicker txtDateStart;
    private javax.swing.JLabel txtID;
    private controls.JDateTimePicker txtTimeStart;
    private javax.swing.JCheckBox txtcondit;
    private controls.SubJComboBox txtemp;
    private controls.JDateTimePicker txtenddate;
    private controls.SubJComboBox txtspec;
    private controls.JDateTimePicker txttimeend;
    // End of variables declaration//GEN-END:variables
}