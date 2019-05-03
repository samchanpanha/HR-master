/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recruitment;

import CMS.DB;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author panha
 */
public class Recruit extends javax.swing.JFrame {

    /**
     * Creates new form Recruit
     */
    public Recruit() {
        initComponents();
    }
    DB c = new DB();
    DefaultTableModel mod;
    DefaultTableModel modre;
    DefaultTableModel modred;
    String reID=null;
    String redID = null;
    String Sql = null;
    String query=null;
    JTextField txtpid = new JTextField();
    JTextField txtTimeshiftID = new JTextField();
    JTextField txtpid1 = new JTextField();
    JTextField txtTimeshiftID1= new JTextField();
    JTextField valueID = new JTextField();
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
    JLabel lbtime = new JLabel();
    Thread th ;
    String EmployeeID =null;
    int id=0;
    void ShowPosition(){
        Sql="SELECT  `Position`\n" +
        "FROM positions;";
        c.DisplayName(cbposition, Sql);
        cbposition.setSelectedIndex(-1);
    }
    void ShowIDPositon(){
        Sql="SELECT PositionID\n" +
        "FROM positions WHERE `Position` = '"+cbposition.getSelectedItem()+"';";
        c.DisplayId(Sql, txtpid);
    }
    
    void ShowTimeshift(){
        Sql="SELECT  Description\n" +
        "FROM timeshifts;";
        c.DisplayName(cbTimeshift, Sql);
        cbTimeshift.setSelectedIndex(-1);
        
    }
    void ShowIDTimeshift(){
       Sql="SELECT TimeShiftId\n" +
        "FROM timeshifts WHERE Description = '"+cbTimeshift.getSelectedItem()+"';";
       c.DisplayId(Sql, txtTimeshiftID);
    }
    
    void ADD(){
        mod = (DefaultTableModel) tbrecruit.getModel();
        id+=1;
         ShowIDPositon();
        ShowIDTimeshift();
        String num = txtNum.getText();
        String position = cbposition.getSelectedItem()+"";
        String timeshift = cbTimeshift.getSelectedItem()+"";
       
        String duties= txtduties.getText();
        String requirement = txtrequitment.getText();
        String positionID = txtpid.getText();
        String timeshiftID = txtTimeshiftID.getText();
        Object[] row = {id,position,num,timeshift,duties,timeshiftID,positionID,requirement}; 
        mod.addRow(row);
        ClearAddRecruit();
        
    }
    void UPDATE(){
        mod = (DefaultTableModel) tbrecruit.getModel();
       
        String num = txtNum.getText();
        String position = cbposition.getSelectedItem()+"";
        String timeshift = cbTimeshift.getSelectedItem()+"";
      
        String duties= txtduties.getText();
        String requirement = txtrequitment.getText();
        String positionID = txtpid.getText();
        String timeshiftID = txtTimeshiftID.getText();
        mod.setValueAt(position, tbrecruit.getSelectedRow(), 1);
        mod.setValueAt(num, tbrecruit.getSelectedRow(), 2);
        mod.setValueAt(timeshift, tbrecruit.getSelectedRow(), 3);
        mod.setValueAt(duties, tbrecruit.getSelectedRow(), 4);
        mod.setValueAt(requirement, tbrecruit.getSelectedRow(), 7);
        mod.setValueAt(positionID, tbrecruit.getSelectedRow(), 6);
        mod.setValueAt(timeshiftID, tbrecruit.getSelectedRow(), 5);
        ClearAddRecruit();
    }
    void  DELETE(){
        int index[] = tbrecruit.getSelectedRows();
        for (int i = 0; i < index.length; i++) {
            mod.removeRow(index[i] - i);
        }
    }
    void SAVE(){
        
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String cDate =formater.format(txtCdate.getDate());
        String eDate =formater.format(txtEdate.getDate());
        String des = txtdes.getText();
        
        String query ="INSERT INTO recruits\n" +
        "(DateCreated, EndDate, Description,cEmpId)\n" +
        "VALUES('"+cDate+"', '"+eDate+"', '"+txtdes.getText()+"', 1);";
        c.Query(query);
        ShowID();
        String RecruitID = valueID.getText();
        System.out.println(RecruitID);
       
        for (int i = 0; i < tbrecruit.getRowCount(); i++) {
           String n= tbrecruit.getValueAt(i, 2).toString();
            String du =tbrecruit.getValueAt(i, 4).toString();
            String re = tbrecruit.getValueAt(i, 7).toString();
            String poid =tbrecruit.getValueAt(i, 6).toString();
            String tid = tbrecruit.getValueAt(i, 5).toString();
          Sql="INSERT INTO recruitdetails\n" +
        "(RecruitId, NumberNeeded, PositionId, TimeShiftId, Duties, Requirements)\n" +
        "VALUES('"+RecruitID+"', '"+n+"', '"+poid+"', '"+tid+"', '"+du+"', '"+re+"');";
          c.Query(Sql);
        }
      
       c.ClearJdateChooser(txtCdate,txtEdate);
       c.clearText(txtNum,txtpid,txtTimeshiftID);
       c.ClearCombobox(cbTimeshift,cbposition);
       c.ClearTextArea(txtduties,txtrequitment);
       c.ClearTable(tbrecruit);
       
    }
    void SELECTEDROW(){
        int i = tbrecruit.getSelectedRow();
        TableModel m = tbrecruit.getModel();
        cbposition.setSelectedItem(m.getValueAt(i, 1).toString());
        txtNum.setText(m.getValueAt(i, 2).toString());
        cbTimeshift.setSelectedItem(m.getValueAt(i, 3).toString());
        txtduties.setText(m.getValueAt(i, 4).toString());
        txtrequitment.setText(m.getValueAt(i, 7).toString());
        txtpid.setText(m.getValueAt(i, 6).toString());
        txtTimeshiftID.setText(m.getValueAt(i, 5).toString());
        ShowIDPositon();
        ShowIDTimeshift();
    }
    void ShowID(){
        c.Value_ID(valueID);
    }
    void ClearAddRecruit(){
        c.clearText(txtNum,txtpid,txtTimeshiftID);
        c.ClearCombobox(cbTimeshift,cbposition);
        c.ClearTextArea(txtduties,txtrequitment);
    }
    
//View RecruitMent 
//-------------------------------------------------------------------------------------
     void ShowPosition1(){
        Sql="SELECT  `Position`\n" +
        "FROM positions;";
        c.DisplayName(cbposition1, Sql);
        cbposition1.setSelectedItem("");
    }
    void ShowIDPositon1(){
        Sql="SELECT PositionID\n" +
        "FROM positions WHERE `Position` = '"+cbposition1.getSelectedItem()+"';";
        c.DisplayId(Sql, txtpid1);
    }
    
    void ShowTimeshift1(){
        Sql="SELECT  Description\n" +
        "FROM timeshifts;";
        c.DisplayName(cbTimeshift1, Sql);
        cbTimeshift1.setSelectedItem("");
    }
    void ShowIDTimeshift1(){
       Sql="SELECT TimeShiftId\n" +
        "FROM timeshifts WHERE Description = '"+cbTimeshift1.getSelectedItem()+"';";
       c.DisplayId(Sql, txtTimeshiftID1);
    }
    void SearchRecruit(){
        if (txtDateFrom.getDate()==null && txtDateTo.getDate()==null) {
              Sql = "select * from vu_recruit  ; ";
              c.showDataInTable(tbre, Sql, modre);
        }
        else if (txtDateFrom.getDate()!=null && txtDateTo.getDate()==null){
             String f = formater.format(txtDateFrom.getDate());    
            Sql = "select * from vu_recruit WHERE DateCreated = '"+f+"' ; ";
        c.showDataInTable(tbre, Sql, modre);
        }
        else {
             String f = formater.format(txtDateFrom.getDate());
             String t = formater.format(txtDateTo.getDate());
             Sql = "select * from vu_recruit WHERE DateCreated between '"+f+"' AND '"+t+"'; ";
           c.showDataInTable(tbre, Sql, modre);
        } 
       c.HideColunmsTable(tbre, 7,5);
       c.ClearTable(tbred);
       ClearAll();
    }
    void SaveAll(){
        if (txtCdate1.getDate()!=null && txtEdate1.getDate()!=null && txtdes1.getText()!=null 
             && txtNum1.getText()==null && cbposition1.getSelectedIndex()==-1 && cbTimeshift1.getSelectedIndex()==-1  
             && txtduties1.getText()==null && txtrequirement1.getText()==null   ) {
       String CreateDate = formater.format(txtCdate1.getDate());
       String EndDate =formater.format(txtEdate1.getDate());
       String Description = txtdes1.getText();
       Sql = "UPDATE recruits\n" +
        "SET DateCreated='"+CreateDate+"', EndDate='"+EndDate+"', Description='"+Description+"', ModifiedDate='"+lbdatetime.getText()+"',"
        + " mEmpId='"+"1"+"'\n" +
        "WHERE RecruitId='"+reID+"';";
        c.Query(Sql);
        SearchRecruit();
        ClearAll();
        }
        else if (txtCdate1.getDate()!=null && txtEdate1.getDate()!=null && txtdes1.getText()!=null  ){
              String CreateDate = formater.format(txtCdate1.getDate());
       String EndDate =formater.format(txtEdate1.getDate());
       String Description = txtdes1.getText();
       Sql = "UPDATE recruits\n" +
        "SET DateCreated='"+CreateDate+"', EndDate='"+EndDate+"', Description='"+Description+"', ModifiedDate='"+lbdatetime.getText()+"',"
        + " mEmpId='"+"1"+"'\n" +
        "WHERE RecruitId='"+reID+"';";
       
       query="UPDATE recruitdetails\n" +
        "SET NumberNeeded='"+txtNum1.getText()+"', PositionId='"+txtpid1.getText()+"', TimeShiftId='"+txtTimeshiftID1.getText()+"',"
        + " Duties='"+txtduties1.getText()+"', Requirements='"+txtrequirement1.getText()+"'\n" +
        "WHERE RecruitDetailId='"+redID+"';";
       c.Query(Sql);
       SearchRecruit();
       c.Query(query);
       ShowRecriutDetails();
       ClearAll();
        } 

    }
    void SeletedTableRecruit(){
        int i = tbre.getSelectedRow();
        TableModel m = tbre.getModel();
        reID = m.getValueAt(i, 0).toString();
        c.FormartDateInTable(txtCdate1, tbre, 1);
        c.FormartDateInTable(txtEdate1, tbre, 2);
        txtdes1.setText(m.getValueAt(i, 3).toString());
        ShowRecriutDetails();
        c.HideColunmsTable(tbred, 1);
        c.ClearTextArea(txtduties1,txtrequirement1);
        c.ClearCombobox(cbposition1,cbTimeshift1);
        c.clearText(txtpid1,txtTimeshiftID1,txtNum1);
        
        
    }
    void SeletedTableRecruitDetails(){
        int i = tbred.getSelectedRow();
        TableModel m = tbred.getModel();
        redID = m.getValueAt(i,0).toString();
        cbposition1.setSelectedItem(m.getValueAt(i, 2).toString());
        cbTimeshift1.setSelectedItem(m.getValueAt(i, 3).toString());
        txtNum1.setText(m.getValueAt(i, 4).toString());
        txtduties1.setText(m.getValueAt(i, 5).toString());
        txtrequirement1.setText(m.getValueAt(i, 6).toString());
        ShowIDPositon1();
        ShowIDTimeshift1();
        
    }
    void ClearAll(){
        c.ClearTextArea(txtduties1,txtrequirement1,txtdes1);
        c.ClearCombobox(cbposition1,cbTimeshift1);
        c.clearText(txtpid1,txtTimeshiftID1,txtNum1);
        c.ClearJdateChooser(txtCdate1,txtEdate1);
    }
    void ShowRecriutDetails(){
        Sql = "SELECT * FROM vu_recruitdetails where RecruitId = '"+reID+"'";
        c.showDataInTable(tbred, Sql, modred);
    }
    
    void DeleteRecruit(){
        int index[] = tbre.getSelectedRows();
        TableModel m = tbre.getModel();
        for (int i : index) {
            System.out.println(m.getValueAt(i, 0).toString());
            Sql = "DELETE FROM recruits\n" +
            "WHERE RecruitId='"+m.getValueAt(i, 0).toString()+"';";
            c.Query(Sql);
        }
        SearchRecruit();
    }
    void DeleteRecruitDetails(){
        int index[] = tbred.getSelectedRows();
        TableModel m = tbred.getModel();
        for (int i : index) {
            System.out.println(m.getValueAt(i, 0).toString());
            Sql = "DELETE FROM recruitdetails\n" +
            "WHERE RecruitDetailId='"+m.getValueAt(i, 0).toString()+"';";
            c.Query(Sql);
        }
        ShowRecriutDetails();
        
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        poptbrecruit = new javax.swing.JPopupMenu();
        RecruitDelete = new javax.swing.JMenuItem();
        poptbrecruitdetails = new javax.swing.JPopupMenu();
        RecruitDetails = new javax.swing.JMenuItem();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCdate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtEdate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtNum = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbposition = new javaapplication21.AutoComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbTimeshift = new javaapplication21.AutoComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdes = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbrecruit = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtduties = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtrequitment = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnADD = new javax.swing.JButton();
        btnUPDATE = new javax.swing.JButton();
        btnDELETE = new javax.swing.JButton();
        btnSAVE = new javax.swing.JButton();
        btnCANCEL = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbre = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbred = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtCdate1 = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtEdate1 = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        txtNum1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbposition1 = new javaapplication21.AutoComboBox();
        cbTimeshift1 = new javaapplication21.AutoComboBox();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtdes1 = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtduties1 = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtrequirement1 = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtDateFrom = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        txtDateTo = new com.toedter.calendar.JDateChooser();
        btnSearch = new javax.swing.JButton();
        btnS = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lbdatetime = new javax.swing.JLabel();

        RecruitDelete.setText("Delete");
        RecruitDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecruitDeleteActionPerformed(evt);
            }
        });
        poptbrecruit.add(RecruitDelete);

        RecruitDetails.setText("Delete");
        RecruitDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecruitDetailsActionPerformed(evt);
            }
        });
        poptbrecruitdetails.add(RecruitDetails);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Name :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("RECRUITMENT");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Date :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Date Create :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("TimeShift :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("NumberNeeded :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Date End :");

        cbposition.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbpositionPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Position :");

        cbTimeshift.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbTimeshiftPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtdes.setColumns(20);
        txtdes.setFont(new java.awt.Font("Palatino Linotype", 0, 13)); // NOI18N
        txtdes.setRows(5);
        jScrollPane1.setViewportView(txtdes);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Description :");

        tbrecruit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Postion", "NumberNeeded", "Timeshift", "Duties", "shiftid", "PositionID", "Requirement", "Create", "Title 10"
            }
        ));
        tbrecruit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbrecruitMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbrecruit);

        txtduties.setColumns(20);
        txtduties.setFont(new java.awt.Font("Palatino Linotype", 0, 13)); // NOI18N
        txtduties.setRows(5);
        jScrollPane3.setViewportView(txtduties);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Duties :");

        txtrequitment.setColumns(20);
        txtrequitment.setFont(new java.awt.Font("Palatino Linotype", 0, 13)); // NOI18N
        txtrequitment.setRows(5);
        jScrollPane4.setViewportView(txtrequitment);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Requirements :");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnADD.setText("ADD");
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });
        jPanel2.add(btnADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 110, 40));

        btnUPDATE.setText("UPDATE");
        btnUPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUPDATEActionPerformed(evt);
            }
        });
        jPanel2.add(btnUPDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 110, 40));

        btnDELETE.setText("DELETE");
        btnDELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDELETEActionPerformed(evt);
            }
        });
        jPanel2.add(btnDELETE, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 110, 40));

        btnSAVE.setText("SAVE");
        btnSAVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSAVEActionPerformed(evt);
            }
        });
        jPanel2.add(btnSAVE, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 110, 40));

        btnCANCEL.setText("CANCEL");
        jPanel2.add(btnCANCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 110, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEdate, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbposition, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTimeshift, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1409, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(844, 844, 844)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbposition, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTimeshift, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtCdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        jTabbedPane1.addTab("Add New Recruitment", jPanel1);

        tbre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbreMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbreMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tbre);

        tbred.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbred.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbredMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbredMouseReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tbred);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Date Create :");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Date End :");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("NumberNeeded :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Position :");

        cbposition1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbposition1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbTimeshift1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbTimeshift1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("TimeShift :");

        txtdes1.setColumns(20);
        txtdes1.setFont(new java.awt.Font("Palatino Linotype", 0, 13)); // NOI18N
        txtdes1.setRows(5);
        jScrollPane7.setViewportView(txtdes1);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Description :");

        txtduties1.setColumns(20);
        txtduties1.setFont(new java.awt.Font("Palatino Linotype", 0, 13)); // NOI18N
        txtduties1.setRows(5);
        jScrollPane8.setViewportView(txtduties1);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Duties :");

        txtrequirement1.setColumns(20);
        txtrequirement1.setFont(new java.awt.Font("Palatino Linotype", 0, 13)); // NOI18N
        txtrequirement1.setRows(5);
        jScrollPane9.setViewportView(txtrequirement1);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Requirements :");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Date Create :");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Date End :");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnS.setText("Save");
        btnS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbposition1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTimeshift1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(144, 144, 144)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(btnS, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEdate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCdate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTimeshift1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbposition1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDateFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSearch)
                        .addComponent(btnS))
                    .addComponent(txtDateTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Show Recruitment And Edit ", jPanel3);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Date :");

        lbdatetime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbdatetime.setText("Date :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(535, 535, 535)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbdatetime, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbdatetime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1455, 933));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        ADD();
    }//GEN-LAST:event_btnADDActionPerformed

    private void btnDELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDELETEActionPerformed
       DELETE();
    }//GEN-LAST:event_btnDELETEActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        c.FormartDate(txtCdate,txtEdate,txtCdate1,txtEdate1,txtDateFrom,txtDateTo);
        
       // c.HideColunmsTable(tbrecruit,6,5);
        ShowPosition();
        ShowTimeshift();
//--------------------------------------------------------- 
        ShowPosition1();
        ShowTimeshift1();
        c.clock(th, lbdatetime, lbtime);
        
    }//GEN-LAST:event_formWindowOpened

    private void btnSAVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSAVEActionPerformed
        SAVE();
    }//GEN-LAST:event_btnSAVEActionPerformed

    private void tbrecruitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbrecruitMouseClicked
        SELECTEDROW();
    }//GEN-LAST:event_tbrecruitMouseClicked

    private void btnUPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUPDATEActionPerformed
       UPDATE();
    }//GEN-LAST:event_btnUPDATEActionPerformed

    private void cbpositionPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbpositionPopupMenuWillBecomeInvisible
        ShowIDPositon();
    }//GEN-LAST:event_cbpositionPopupMenuWillBecomeInvisible

    private void cbTimeshiftPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbTimeshiftPopupMenuWillBecomeInvisible
        ShowIDTimeshift();
    }//GEN-LAST:event_cbTimeshiftPopupMenuWillBecomeInvisible

    private void cbposition1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbposition1PopupMenuWillBecomeInvisible
        ShowIDPositon1();
    }//GEN-LAST:event_cbposition1PopupMenuWillBecomeInvisible

    private void cbTimeshift1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbTimeshift1PopupMenuWillBecomeInvisible
        ShowIDTimeshift1();
    }//GEN-LAST:event_cbTimeshift1PopupMenuWillBecomeInvisible

    private void tbreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbreMouseReleased
          c.TableMouseReleased(evt,tbre,poptbrecruit);
    }//GEN-LAST:event_tbreMouseReleased

    private void tbredMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbredMouseReleased
        c.TableMouseReleased(evt, tbred, poptbrecruitdetails);
    }//GEN-LAST:event_tbredMouseReleased

    private void RecruitDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecruitDeleteActionPerformed
        DeleteRecruit();
    }//GEN-LAST:event_RecruitDeleteActionPerformed

    private void RecruitDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecruitDetailsActionPerformed
        DeleteRecruitDetails();
    }//GEN-LAST:event_RecruitDetailsActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
       SearchRecruit();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbreMouseClicked
       SeletedTableRecruit();
    }//GEN-LAST:event_tbreMouseClicked

    private void btnSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSActionPerformed
       SaveAll();
    }//GEN-LAST:event_btnSActionPerformed

    private void tbredMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbredMouseClicked
       SeletedTableRecruitDetails();
    }//GEN-LAST:event_tbredMouseClicked

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
            java.util.logging.Logger.getLogger(Recruit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recruit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recruit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recruit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Recruit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem RecruitDelete;
    private javax.swing.JMenuItem RecruitDetails;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnCANCEL;
    private javax.swing.JButton btnDELETE;
    private javax.swing.JButton btnS;
    private javax.swing.JButton btnSAVE;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUPDATE;
    private javaapplication21.AutoComboBox cbTimeshift;
    private javaapplication21.AutoComboBox cbTimeshift1;
    private javaapplication21.AutoComboBox cbposition;
    private javaapplication21.AutoComboBox cbposition1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbdatetime;
    private javax.swing.JPopupMenu poptbrecruit;
    private javax.swing.JPopupMenu poptbrecruitdetails;
    private javax.swing.JTable tbre;
    private javax.swing.JTable tbrecruit;
    private javax.swing.JTable tbred;
    private com.toedter.calendar.JDateChooser txtCdate;
    private com.toedter.calendar.JDateChooser txtCdate1;
    private com.toedter.calendar.JDateChooser txtDateFrom;
    private com.toedter.calendar.JDateChooser txtDateTo;
    private com.toedter.calendar.JDateChooser txtEdate;
    private com.toedter.calendar.JDateChooser txtEdate1;
    private javax.swing.JTextField txtNum;
    private javax.swing.JTextField txtNum1;
    private javax.swing.JTextArea txtdes;
    private javax.swing.JTextArea txtdes1;
    private javax.swing.JTextArea txtduties;
    private javax.swing.JTextArea txtduties1;
    private javax.swing.JTextArea txtrequirement1;
    private javax.swing.JTextArea txtrequitment;
    // End of variables declaration//GEN-END:variables
}
