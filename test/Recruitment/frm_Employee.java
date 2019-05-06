/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recruitment;

import CA.AC;
import CMS.DB;
import java.text.SimpleDateFormat;
import javaapplication21.AutoComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author panha
 */
public class frm_Employee extends JInternalFrame {

    /**
     * Creates new form frm_Employee
     */
    public frm_Employee() {
        initComponents();
        Displaycb();
        SHOW_INFORMATION();
        mc.FormartDate(dateofbirth,hiredate);
        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
    }
    AC ac = new AC();
    DB mc = new DB();
    String Sql=null;
    JTextField staffid =new JTextField();
    JTextField txtInID =new JTextField();
    JTextField txtAccountLoginID =new JTextField();
    JTextField txtDeptID =new JTextField();
    JTextField txtPositionID =new JTextField();
    JTextField txtEmployeeTypeID =new JTextField();
    JTextField txtWorkDayID =new JTextField();
    DefaultTableModel dm ;
   //pager owner phone
    
    int PriviligeNum =0;
    String gen = null;
    
    
    private void INSERT_TO_ACCESS(){
         SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
          String df =formater.format(dateofbirth.getDate());
          df="#"+df+"#";
          String dh =formater.format(hiredate.getDate());
          dh="#"+dh+"#";
    //BIRTHDAY, HIREDDAY,PAGER ,OPHONE, DEFAULTDEPTID,  `privilege`, `CardNo`,  `street`, `Gender`, `Name`, USERID,`Badgenumber`
          try {
                 Sql = "INSERT INTO USERINFO\n" +
                    "(  BIRTHDAY, HIREDDAY,PAGER ,OPHONE, `privilege`, `street`, `Gender`, `Name`, USERID,`Badgenumber`)\n" +
                    "VALUES("+df+","+dh+",'"+txtOfficeTel.getText()+"','"+txtTel.getText()+"',"+PriviligeNum+","
                   + "'"+txtadd.getText()+"','"+gen+"','"+cbName.getSelectedItem()+"',"+staffid.getText()+","+staffid.getText()+");";
                       ac.Query(Sql);
                   ac.Query("UPDATE USERINFO SET DEFAULTDEPTID = "+staffid.getText()+";");
               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
       
    }
    private void UPDATE_TO_ACCESS(){
         SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
          String df =formater.format(dateofbirth.getDate());
          df="#"+df+"#";
          String dh =formater.format(hiredate.getDate());
          dh="#"+dh+"#";
//          ,PAGER='"+txtOfficeTel.getText()+"', OPHONE = '"+txtTel.getText()+"', "
//             + "DEFAULTDEPTID = "+txtDeptID.getText()+",  `privilege`= "+PriviligeNum+",`CardNo`= '"+txtidCard.getText()+"', \n" +
//            "`street`= '"+txtadd.getText()+"', `Gender`= '"+gen+"', `Name`='"+cbName.getSelectedItem()+"'
            try {
                   Sql = "UPDATE USERINFO SET BIRTHDAY = "+df+", HIREDDAY ="+dh+" ,PAGER='"+txtOfficeTel.getText()+"', OPHONE = '"+txtTel.getText()+"'"
                    + ", DEFAULTDEPTID = "+txtDeptID.getText()+",  `privilege`= "+PriviligeNum+" "
                    + ", `CardNo`= '"+txtidCard.getText()+"'"
                    + ", `street`= '"+txtadd.getText()+"', `Gender`= '"+gen+"', `Name`='"+cbName.getSelectedItem()+"' WHERE USERID ="+txtAcNo.getText()+" ;";
                     ac.Query(Sql);
         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    }
    
    private void DELETE_TO_ACCESS(){
        ac.Query("DELETE * FROM USERINFO WHERE USERID ="+txtAcNo.getText()+";");
     
    }
    
    private void INSERT_TO_MYSQL(){
         SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
          String dh =formater.format(hiredate.getDate());
        
          try {
                    Sql = "INSERT INTO employees\n" +
               "(HiredDate, BasicSalary, BlockSalary, Status, Children, WorkDayId, UserId, DepartmentId, EmpTypeId, PositionID, IntervieweeId, Id_Card,Privilige,Office_Tel)\n" +
               "VALUES('"+dh+"','"+txtBasicsalary.getText()+"','"+cbBlocksalary.getSelectedItem()+"' , "
                       + "'"+cbStatus.getSelectedItem()+"', '"+txtChildren.getText()+"', '"+txtWorkDayID.getText()+"',"
                       + " '"+txtAccountLoginID.getText()+"', '"+txtDeptID.getText()+"', '"+txtEmployeeTypeID.getText()+"',"
                       + " '"+txtPositionID.getText()+"', '"+txtInID.getText()+"', '"+txtidCard.getText()+"',"+PriviligeNum+",'"+txtOfficeTel.getText()+"');";
               mc.Query(Sql);
               mc.Value_ID(staffid);
               String w ="UPDATE `interviewees` SET `Status` = 'Staff' WHERE `IntervieweeId` ='"+txtInID.getText() +"' "; 
               mc.Query(w);
               CLEARALL();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, e.getMessage());
        }
          
       
        
    }
    private void UPDATE_TO_MYSQL(){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String dh =formater.format(hiredate.getDate());
        try {
              Sql = "UPDATE employees\n" +
                "SET HiredDate='"+dh+"', BasicSalary='"+txtBasicsalary.getText()+"', BlockSalary='"+cbBlocksalary.getSelectedItem()+"', "
                        + "Status='"+cbStatus.getSelectedItem()+"', Children='"+txtChildren.getText()+"', "
                        + " WorkDayId='"+txtWorkDayID.getText()+"', UserId='"+txtAccountLoginID.getText()+"', DepartmentId='"+txtDeptID.getText()+"',"
                        + " EmpTypeId='"+txtEmployeeTypeID.getText()+"', PositionID='"+txtPositionID.getText()+"', IntervieweeId='"+txtInID.getText()+"', Id_Card='"+txtidCard.getText()+"',\n" +
                "Privilige="+PriviligeNum+" ,Office_Tel='"+txtOfficeTel.getText()+"'  WHERE EmpId='"+txtAcNo.getText()+"';";
              mc.Query(Sql);
              CLEARALL();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    }
    
    private void DELETE_TO_MYSQL(){
        mc.Query("DELETE FROM employees WHERE EmpId='"+txtAcNo.getText()+"';");
        CLEARALL();
    }
    
    private void SHOW_INFORMATION(){
        Sql="SELECT * FROM vu_employees";
        mc.showDataInTable(tbEmployee, Sql, dm);
        mc.HideColunmsTable(tbEmployee, 24,22,21,20,19,18,17);
    }
    
    private void DisplayComboboxName(String colname , String tbname ,AutoComboBox cb){
        
        Sql = "SELECT "+colname+" FROM "+tbname+";";
        mc.DisplayName(cb, Sql);
    }
    
    private void DisplayComboboxId(String colid , String tbname ,JTextField txt){
        Sql = "SELECT "+colid+" FROM "+tbname+"";
        mc.DisplayId(Sql, txt);
    }
    
    private void Displaycb(){
        //Columns Name , Table Name 
         DisplayComboboxName("`Name`", "vu_name_reinter_passed WHERE result = 'passed' AND\n" +
                             "`Status` = 'New'",cbName);
         DisplayComboboxName("users.Username", "users", cbAccountLogin);
         DisplayComboboxName("departments.Department", "departments", cbDept);
         DisplayComboboxName("positions.Position", "positions", cbPosition);
         DisplayComboboxName("emptype.EmpType", "emptype", cbEmployeeType);
         DisplayComboboxName("workdays.Description", "workdays", cbWorkDay);
         mc.ClearCombobox(cbName,cbAccountLogin,cbDept,cbPosition,cbEmployeeType,cbWorkDay);
    }
    
   private void SeletedTableEmployees(){
       try {
           CLEARALL();
           int i = tbEmployee.getSelectedRow();
           TableModel m = tbEmployee.getModel();
           txtAcNo.setText(m.getValueAt(i, 0).toString());
           cbName.setSelectedItem(m.getValueAt(i, 1).toString());
           cbGen.setSelectedItem(m.getValueAt(i, 2).toString());
           txtadd.setText(m.getValueAt(i, 3).toString());
           txtTel.setText(m.getValueAt(i, 4).toString());
           mc.FormartDateInTable(dateofbirth, tbEmployee, 5);
           txtidCard.setText(m.getValueAt(i, 6).toString());
           mc.FormartDateInTable(hiredate, tbEmployee, 7);
           txtBasicsalary.setText(m.getValueAt(i, 8).toString());
           cbBlocksalary.setSelectedItem(m.getValueAt(i, 9).toString());
           cbStatus.setSelectedItem(m.getValueAt(i, 10).toString());
           txtChildren.setText(m.getValueAt(i, 11).toString());
           cbPosition.setSelectedItem(m.getValueAt(i, 13).toString());
           cbWorkDay.setSelectedItem(m.getValueAt(i, 15).toString());
           cbAccountLogin.setSelectedItem(m.getValueAt(i, 16).toString());
           cbPrivilige.setSelectedIndex(Integer.parseInt(m.getValueAt(i, 22).toString()));
           txtOfficeTel.setText(m.getValueAt(i, 23).toString());
           cbEmployeeType.setSelectedItem(m.getValueAt(i, 14).toString());
           cbDept.setSelectedItem(m.getValueAt(i, 12).toString());
           CBWORK();
           CBEMTYPE();
           CBPOSITION();
           CBNAME();
           Account();
           CBDEPT();
           btnSave.setEnabled(false);
           btnUpdate.setEnabled(true);
           btnDelete.setEnabled(true);
           
       } catch (Exception e) {
          // JOptionPane.showMessageDialog(this, e.getMessage());
       }
   }
    private void CBNAME(){
          try {
             Sql = "SELECT IntervieweeId\n" +
                "FROM vu_name_reinter_passed \n" +
                "WHERE Name = '"+cbName.getSelectedItem()+"';";
          mc.DisplayId(Sql, txtInID);
        } catch (Exception e) {
        }
    }
    private void CBWORK(){
        try {
              Sql = "SELECT\n" +
                "workdays.WorkDayId\n" +
                "FROM\n" +
                "workdays WHERE workdays.Description = '"+cbWorkDay.getSelectedItem()+"'";
               mc.DisplayId(Sql, txtWorkDayID);
        } catch (Exception e) {
        }
    }
    private void Account(){
         try {
            Sql = "SELECT\n" +
                    "UserId\n" +
                    "FROM\n" +
                    "users WHERE Username = '"+cbAccountLogin.getSelectedItem()+"'";
            mc.DisplayId(Sql, txtAccountLoginID);
            
        } catch (Exception e) {
        }
    }
    private void CBDEPT(){
         try {
            Sql = "SELECT\n" +
                    "departments.DepartmentId\n" +
                    "FROM\n" +
                    "departments WHERE departments.Department = '"+cbDept.getSelectedItem()+"'" ;
            mc.DisplayId(Sql, txtDeptID);
          
        } catch (Exception e) {
        }
    }
    private void CBPOSITION(){
        try {
            Sql="SELECT\n" +
                "positions.PositionID\n" +
                "FROM\n" +
                "positions WHERE positions.Position = '"+cbPosition.getSelectedItem()+"'";
            mc.DisplayId(Sql, txtPositionID);
        } catch (Exception e) {
        }
    }
    
      private void CBEMTYPE(){
         try {
            Sql= "SELECT\n" +
                    "emptype.EmpTypeId\n" +
                    "FROM\n" +
                    "emptype WHERE emptype.EmpType ='"+cbEmployeeType.getSelectedItem()+"' ";
            mc.DisplayId(Sql, txtEmployeeTypeID);
        } catch (Exception e) {
        }
    }
      
     private void CLEARALL(){
         mc.ClearCombobox(cbName,cbAccountLogin,cbBlocksalary,cbDept,cbEmployeeType,cbGen,cbPosition,cbPrivilige,cbStatus,cbWorkDay);
         mc.clearText(txtAcNo,txtAccountLoginID,txtBasicsalary,txtChildren,txtDeptID,txtEmployeeTypeID,txtInID,txtOfficeTel,txtPositionID,txtTel,txtWorkDayID,txtidCard);
         mc.ClearTextArea(txtadd);
         mc.ClearJdateChooser(dateofbirth,hiredate);
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
        jLabel2 = new javax.swing.JLabel();
        txtAcNo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbName = new javaapplication21.AutoComboBox();
        jscEm = new javax.swing.JScrollPane();
        tbEmployee = new javax.swing.JTable();
        cbGen = new javaapplication21.AutoComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtidCard = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        dateofbirth = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtOfficeTel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbPrivilige = new javaapplication21.AutoComboBox();
        hiredate = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        txtBasicsalary = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbBlocksalary = new javaapplication21.AutoComboBox();
        jLabel16 = new javax.swing.JLabel();
        cbWorkDay = new javaapplication21.AutoComboBox();
        txtChildren = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cbAccountLogin = new javaapplication21.AutoComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbDept = new javaapplication21.AutoComboBox();
        jLabel20 = new javax.swing.JLabel();
        cbPosition = new javaapplication21.AutoComboBox();
        jLabel21 = new javax.swing.JLabel();
        cbStatus = new javaapplication21.AutoComboBox();
        jLabel22 = new javax.swing.JLabel();
        cbEmployeeType = new javaapplication21.AutoComboBox();
        jPanel1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtadd = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Employee");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("AC No :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Name :");

        cbName.setAutocomplete(true);
        cbName.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbNamePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        tbEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbEmployee.setRowHeight(23);
        tbEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEmployeeMouseClicked(evt);
            }
        });
        jscEm.setViewportView(tbEmployee);

        cbGen.setAutocomplete(true);
        cbGen.setKeyWord(new String[] {"Male", "Female"});
        cbGen.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbGenPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Gender :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Date of birth :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ID Card :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Address :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Office Tel :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Privilige :");

        cbPrivilige.setAutocomplete(true);
        cbPrivilige.setKeyWord(new String[] {"User", "Enroller", "Manager", "Suppervisor"});
        cbPrivilige.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbPriviligePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("HireDate :");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("BasicSalary :");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("BlockSalary :");

        cbBlocksalary.setAutocomplete(true);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("WorkDay :");

        cbWorkDay.setAutocomplete(true);
        cbWorkDay.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbWorkDayPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Children :");

        cbAccountLogin.setAutocomplete(true);
        cbAccountLogin.setKeyWord(new String[] {});
        cbAccountLogin.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbAccountLoginPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("UserAccount :");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Department :");

        cbDept.setAutocomplete(true);
        cbDept.setKeyWord(new String[] {});
        cbDept.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbDeptPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Position :");

        cbPosition.setAutocomplete(true);
        cbPosition.setKeyWord(new String[] {});
        cbPosition.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbPositionPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Status :");

        cbStatus.setAutocomplete(true);
        cbStatus.setKeyWord(new String[] {});

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Employee Type :");

        cbEmployeeType.setAutocomplete(true);
        cbEmployeeType.setKeyWord(new String[] {});
        cbEmployeeType.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbEmployeeTypePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        btnSave.setText("Insert");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnUpdate.setText("Save");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );

        txtadd.setColumns(20);
        txtadd.setRows(5);
        jScrollPane1.setViewportView(txtadd);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Tel :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(561, 561, 561)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(dateofbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5)
                                    .addComponent(txtidCard, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel4)
                                .addGap(35, 35, 35)
                                .addComponent(cbGen, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(txtAcNo))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(cbName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtOfficeTel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(hiredate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbBlocksalary, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtBasicsalary, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbPrivilige, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbWorkDay, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbDept, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbAccountLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtChildren, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbEmployeeType, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(79, 79, 79)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jscEm, javax.swing.GroupLayout.PREFERRED_SIZE, 1423, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(hiredate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(txtOfficeTel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(cbPrivilige, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtBasicsalary, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbBlocksalary, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbWorkDay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbEmployeeType, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(38, 38, 38))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(txtChildren, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(72, 72, 72)
                                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbAccountLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(2, 2, 2)
                                                        .addComponent(cbDept, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(76, 76, 76))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtAcNo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(cbGen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateofbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtidCard, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addComponent(jscEm, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbPriviligePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbPriviligePopupMenuWillBecomeInvisible
        switch (cbPrivilige.getSelectedIndex()) {
            case 0:
                PriviligeNum=0;
              
                break;
            case 1:
                PriviligeNum=1;
              
                break;
            case 2:
                PriviligeNum=2;
               
                break;
            case 3:
                PriviligeNum=3;
       
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cbPriviligePopupMenuWillBecomeInvisible

   
    private void cbNamePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbNamePopupMenuWillBecomeInvisible
      
       CBNAME();
       
    }//GEN-LAST:event_cbNamePopupMenuWillBecomeInvisible

    
    private void cbWorkDayPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbWorkDayPopupMenuWillBecomeInvisible
        CBWORK();
      
    }//GEN-LAST:event_cbWorkDayPopupMenuWillBecomeInvisible

   
    private void cbAccountLoginPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbAccountLoginPopupMenuWillBecomeInvisible
       Account();
    }//GEN-LAST:event_cbAccountLoginPopupMenuWillBecomeInvisible

    
    private void cbDeptPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbDeptPopupMenuWillBecomeInvisible
       CBDEPT();
    }//GEN-LAST:event_cbDeptPopupMenuWillBecomeInvisible

    
    private void cbPositionPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbPositionPopupMenuWillBecomeInvisible
        CBPOSITION();
    }//GEN-LAST:event_cbPositionPopupMenuWillBecomeInvisible

  
    private void cbEmployeeTypePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbEmployeeTypePopupMenuWillBecomeInvisible
        CBEMTYPE();
    }//GEN-LAST:event_cbEmployeeTypePopupMenuWillBecomeInvisible

    private void cbGenPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbGenPopupMenuWillBecomeInvisible
       switch(cbGen.getSelectedIndex()){
           case 0 :
               gen="M";
               break;
           case 1 :
               gen= "F";
               break;
            default:
                break;
       }
    }//GEN-LAST:event_cbGenPopupMenuWillBecomeInvisible

    private void tbEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEmployeeMouseClicked
       SeletedTableEmployees();
    }//GEN-LAST:event_tbEmployeeMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
       INSERT_TO_MYSQL();
       INSERT_TO_ACCESS();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
       UPDATE_TO_MYSQL();
      // UPDATE_TO_ACCESS();
       btnSave.setEnabled(true);
       btnUpdate.setEnabled(false);
       btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DELETE_TO_MYSQL();
        DELETE_TO_ACCESS();
        btnSave.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        CLEARALL();
        btnSave.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnClearActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        
    }//GEN-LAST:event_formComponentShown

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
            java.util.logging.Logger.getLogger(frm_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Employee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Employee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javaapplication21.AutoComboBox cbAccountLogin;
    private javaapplication21.AutoComboBox cbBlocksalary;
    private javaapplication21.AutoComboBox cbDept;
    private javaapplication21.AutoComboBox cbEmployeeType;
    private javaapplication21.AutoComboBox cbGen;
    private javaapplication21.AutoComboBox cbName;
    private javaapplication21.AutoComboBox cbPosition;
    private javaapplication21.AutoComboBox cbPrivilige;
    private javaapplication21.AutoComboBox cbStatus;
    private javaapplication21.AutoComboBox cbWorkDay;
    private com.toedter.calendar.JDateChooser dateofbirth;
    private com.toedter.calendar.JDateChooser hiredate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jscEm;
    private javax.swing.JTable tbEmployee;
    private javax.swing.JTextField txtAcNo;
    private javax.swing.JTextField txtBasicsalary;
    private javax.swing.JTextField txtChildren;
    private javax.swing.JTextField txtOfficeTel;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextArea txtadd;
    private javax.swing.JTextField txtidCard;
    // End of variables declaration//GEN-END:variables
}
