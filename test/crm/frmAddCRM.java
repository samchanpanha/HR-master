/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm;


import controls.JDateTimePicker;
import customer.clCustomer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import myClass.FormatRenderer;
import myClass.IdAndName;
import myClass.clFunction;
import myClass.currentEmployee;


/**
 *
 * @author Nemesis
 */
public class frmAddCRM extends javax.swing.JPanel {

    /**
     * Creates new form frmAddCRM
     */
    public frmAddCRM(DefaultTableModel modelCRMList) {
        init();
        txtId.setText(clFunction.getLastId("Opportunitys"));
        
        this.modelCRMList=modelCRMList;
        
        if(!currentEmployee.getRole().equals("Admin")){
            lbApprove.setVisible(false);
            checkApprove.setVisible(false);
        }
        
        
        
        if(!currentEmployee.getRole().equals("Admin")){
            dAppoveDate.setEnabled(false);
            checkApprove.setEnabled(false);
        }
        
        modelCBEmployee=(DefaultComboBoxModel)cbEmployee.getModel();
        
        Date d=new Date();
        dStart.setValue(d);
        dEnd.setValue(d);
        
        DocumentListener docStart=new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                
                
                docInsertUpdate();
                
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                
            }
            private void docInsertUpdate(){
                Runnable thisInsertUpdate=()->{
                    
                    clCRM.getModelCbEmployeeFree(modelCBEmployee,dStart.getValue(),dEnd.getValue());
                    adjustDate("1");
                };
                SwingUtilities.invokeLater(thisInsertUpdate);
            }

        };
        
        DocumentListener docEnd=new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                
                
                docInsertUpdate();
                
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                
            }
            private void docInsertUpdate(){
                Runnable thisInsertUpdate=()->{
                    
                    clCRM.getModelCbEmployeeFree(modelCBEmployee,dStart.getValue(),dEnd.getValue());
                    adjustDate("2");
                };
                SwingUtilities.invokeLater(thisInsertUpdate);
            }

        };
        
        
        dStart.addEventToSpinner(docStart);
        
        dEnd.addEventToSpinner(docEnd);
        
        clCRM.getModelCbEmployeeFree(modelCBEmployee,dStart.getValue(),dEnd.getValue());
    }
    
    
    
    void adjustDate(String stControlChange){
        
        Date date1=dStart.getValue();
        Date date2=dEnd.getValue();
        
        
        switch (stControlChange){
            case "1":
                if(date1.after(date2)){
                    dEnd.setValue(date1);
                }
                break;
            case "2":
                if(date2.before(date1)){
                    dStart.setValue(date2);
                }
                
                break;
                
        }
        
    }
    
    void init(){
        
        initComponents();
        
      
        
        prepareForm();
        
        
    }
    
    public frmAddCRM(int selectedMaster,DefaultTableModel modelCRMList) {
       init();
       
       this.selectedMaster=selectedMaster;
       this.modelCRMList=modelCRMList;
       
       getMasterForEdit();
       
       
       
       
    }
    
    
    void getMasterForEdit(){
        String opportunityId=modelCRMList.getValueAt(selectedMaster, 0)+"";
        
        txtId.setText(opportunityId);
        
       

        IdAndName customer=(IdAndName)modelCRMList.getValueAt(selectedMaster,6);
        
        String masterDesc=modelCRMList.getValueAt(selectedMaster, 1)+"";
        
        String priority=modelCRMList.getValueAt(selectedMaster, 3)+"";
        
        String status=modelCRMList.getValueAt(selectedMaster, 4)+"";
        
        
        cbCustomer.setSelectedItem(customer);
        
        txtMasterDesc.setText(masterDesc);
        
        spinPriority.setValue(priority);
        
        cbStatus.setSelectedItem(status);
        
        clCRM.getModelCRMDetailForEdit(opportunityId, modelCRMDetail);
        
    }
    
    
    
    int selectedMaster=-1;
    
    DefaultComboBoxModel modelCBCustomer;
    
    DefaultComboBoxModel modelCBEmployee;
    DefaultComboBoxModel modelAction;
    DefaultTableModel modelCRMDetail;
    DefaultTableModel modelCRMList;
    
    
    
    
    void prepareForm(){
        
        
        
        
        
        
        
        
        modelAction=(DefaultComboBoxModel)cbAction.getModel();
        
        modelCBCustomer=(DefaultComboBoxModel)cbCustomer.getModel();
        
        
        
        
        
        
        
        clCRM.getModelCBAction(modelAction);
        clCustomer.getModelCBCustomerList(modelCBCustomer);
        
        
        
        cbAction.setSelectedIndex(-1);
        
        cbEmployee.setSelectedIndex(-1);
        
        cbCustomer.setSelectedIndex(-1);
        
        
        
        
        modelCRMDetail=(DefaultTableModel)jTableCRMDetail.getModel();
        
        clFunction.changeLookTable(jTableCRMDetail);
        
        
        jTableCRMDetail.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            if (!lse.getValueIsAdjusting()) {
                changeButtonContext();
            }
        });
        
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        TableCellRenderer renderer = new FormatRenderer(format);
        
        
        
        TableColumnModel m = jTableCRMDetail.getColumnModel();
        m.getColumn(3).setCellRenderer(renderer);
        m.getColumn(4).setCellRenderer(renderer);
        
        m.getColumn(7).setCellRenderer(renderer);
        
        String[] st = {"1","2","3"};
        SpinnerListModel modelSpinPriority = new SpinnerListModel(st);
        
        spinPriority.setModel(modelSpinPriority);
    }
    
     void changeButtonContext(){
        if(jTableCRMDetail.getSelectedRowCount()==1){
            btnAdd.setText("Cancel");
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
            
            getDataForEdit();
            
        }else{
            btnAdd.setText("Add");
            
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
            
            
            
            clearForm();
            
            
        }
    }
    

    int selectedRowIndex=-1;
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbEmployee = new controls.SubJComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        cbAction = new controls.SubJComboBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCRMDetail = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMasterDesc = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        spinPriority = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        cbCustomer = new controls.SubJComboBox();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLocation = new javax.swing.JTextArea();
        cbStatus = new javax.swing.JComboBox();
        lbApprove = new javax.swing.JLabel();
        checkApprove = new javax.swing.JCheckBox();
        dAppoveDate = new controls.JDateTimePicker();
        dStart = new controls.JDateTimePicker();
        dEnd = new controls.JDateTimePicker();

        txtId.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setText("ID:");

        btnAdd.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setText("Date Start:");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setText("Date End:");

        cbEmployee.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel5.setText("Employee:");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel6.setText("Location:");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel7.setText("Description:");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        cbAction.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel8.setText("Action:");

        jTableCRMDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Employee", "Action", "Date Start", "Date End", "Location", "Description", "Approve Date", "Approve By"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableCRMDetail);

        btnSave.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel9.setText("Description:");

        txtMasterDesc.setColumns(20);
        txtMasterDesc.setRows(5);
        jScrollPane4.setViewportView(txtMasterDesc);

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel10.setText("Priority:");

        spinPriority.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel11.setText("Status:");

        cbCustomer.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel12.setText("Customer:");

        txtLocation.setColumns(20);
        txtLocation.setRows(5);
        jScrollPane3.setViewportView(txtLocation);

        cbStatus.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending", "Success", "Fail" }));

        lbApprove.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        lbApprove.setText("Approve:");

        checkApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkApproveActionPerformed(evt);
            }
        });

        dAppoveDate.setEnabled(false);
        dAppoveDate.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        dStart.setIgnoreSecond(true);

        dEnd.setIgnoreSecond(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(44, 44, 44)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                    .addComponent(spinPriority)
                                    .addComponent(cbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(146, 146, 146)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(63, 63, 63)
                                        .addComponent(cbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(lbApprove))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbAction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(checkApprove)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(dAppoveDate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                    .addComponent(cbEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dStart, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(cbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(spinPriority, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel4))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbAction, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabel3)
                                        .addGap(95, 95, 95))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(dEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd))
                        .addGap(12, 12, 12)
                        .addComponent(btnEdit)
                        .addGap(12, 12, 12)
                        .addComponent(btnDelete)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dAppoveDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(checkApprove)
                            .addComponent(lbApprove))
                        .addGap(9, 9, 9)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        dStart.getAccessibleContext().setAccessibleName("dStart");
        dEnd.getAccessibleContext().setAccessibleName("dEnd");
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(cbCustomer.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(null, "Please select a customer","",JOptionPane.ERROR_MESSAGE);
            return ;
        }
        
        if(modelCRMDetail.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Please add at least a row","",JOptionPane.ERROR_MESSAGE);
            return ;
        }
        
        //"OpportunityId", "Description", "Date Created", "Priority", "Done", "Employee", "Customer"

        IdAndName customer=(IdAndName)cbCustomer.getSelectedItem();
        String masterDesc=txtMasterDesc.getText();
        
        try {
            spinPriority.commitEdit();
        } catch ( java.text.ParseException e ) { 
            
            System.out.println(e.getMessage());
        
        }
        String priority =  spinPriority.getValue()+"";
        
        String status = cbStatus.getSelectedItem()+"";
        
        
        
        
        //(`Description`, `Priority`, `Done`, `EmpId`, `CustomerId`)
        
       
        
        //(`EmpID`, `Actionid`,`dateStart`,`dateEnd`, `Location`, `descrption`,`OpportunityId`)
        
        List<String []> detail=new ArrayList<>();
        
        String [] d=new String[modelCRMDetail.getColumnCount()-1];
        
        IdAndName employee,action;
        
        String formatPattern="yyyy/MM/dd HH:mm";
        
        String stStart,stEnd,stApproveDate;
        
        
        
        
        for(int i=0;i<modelCRMDetail.getRowCount();i++){
            //"#", "Employee", "Action", "Date Start", "Date End", "Location", "Description"

            
            
            employee=(IdAndName)modelCRMDetail.getValueAt(i,1);
            action=(IdAndName)modelCRMDetail.getValueAt(i,2);
            
            stStart=clFunction.getFormattedDate((Date)modelCRMDetail.getValueAt(i,3) , formatPattern);
            stEnd=clFunction.getFormattedDate((Date)modelCRMDetail.getValueAt(i,4) , formatPattern);
            
            if((modelCRMDetail.getValueAt(i,7)+"").equals("")){
                stApproveDate="";
            }else{
                stApproveDate=clFunction.getFormattedDate((Date)modelCRMDetail.getValueAt(i,7) , formatPattern);
            }
            
            
            IdAndName approveBy=(IdAndName)modelCRMDetail.getValueAt(i,8);
            
            d[0]=employee.getId();
            d[1]=action.getId();
            
            d[2]=stStart;
            d[3]=stEnd;
            
            d[4] = modelCRMDetail.getValueAt(i,5)+"";
            d[5] = modelCRMDetail.getValueAt(i,6)+"";
            
            d[6] = stApproveDate;
            d[7] = approveBy.getId();
            
            
            detail.add(d);
            
            d=new String[modelCRMDetail.getColumnCount()-1];
        }
        
        String opportunityId;
        if(selectedMaster==-1){
            opportunityId=clFunction.getLastId("opportunitys");
        
            String stNow=clFunction.getFormattedDate(new Date(), this.formatPattern);
            
            String [] master={masterDesc,priority,status,currentEmployee.getId(),customer.getId()};
            
            if(clCRM.insert(opportunityId,master, detail)){



                Object [] obj={opportunityId,masterDesc,stNow,priority,status,currentEmployee.getName(),customer};


                modelCRMList.addRow(obj);

                JOptionPane.showMessageDialog(this, "Add successful", "",JOptionPane.INFORMATION_MESSAGE);
                
                
            }
        }else{
            
            

                    
            String [] master={masterDesc,priority,status,customer.getId()};
            opportunityId=modelCRMList.getValueAt(selectedMaster, 0)+"";
            
            if(clCRM.update(opportunityId,master, detail)){
                
                //"OpportunityId", "Description", "Date Created", "Priority", "Done", "Employee", "Customer"
                
                
                modelCRMList.setValueAt(masterDesc, selectedMaster, 1);
                modelCRMList.setValueAt(priority, selectedMaster, 3);
                modelCRMList.setValueAt(status, selectedMaster, 4);
                modelCRMList.setValueAt(customer, selectedMaster, 6);
                
                
                
                
                
                JOptionPane.showMessageDialog(this, "Update successful", "",JOptionPane.INFORMATION_MESSAGE);

            }
            
            
        }
        
        
        
        
        
    }//GEN-LAST:event_btnSaveActionPerformed

    
    String formatPattern="dd/MM/yyyy HH:mm";
    
    
    void clearForm(){
        cbEmployee.setSelectedIndex(-1);
        cbAction.setSelectedIndex(-1);
        dStart.setValue(new Date());
        dEnd.setValue(new Date());
        
        txtLocation.setText("");
        txtDescription.setText("");
        
        checkApprove.setSelected(false);
        
        checkApproveActionPerformed(null);
        
    }
    
    boolean checkOverlapDetail(){
        IdAndName employee=(IdAndName)cbEmployee.getSelectedItem();
        
        Date dateStart=dStart.getValue();
        Date dateEnd=dEnd.getValue();
        
        boolean isOverlap=false;
        
        Date dateStartRow,dateEndRow;
        
        
        IdAndName employeeRow;
        
        
        String overlapRowNum="";
        
        
        
        if(selectedRowIndex==-1){
            
            for(int i=0;i<modelCRMDetail.getRowCount();i++){
            
                dateStartRow=(Date)modelCRMDetail.getValueAt(i, 3);
                dateEndRow=(Date)modelCRMDetail.getValueAt(i, 4);




                if( !(dateStart.equals(dateEndRow) || dateStart.after(dateEndRow) || dateEnd.equals(dateStartRow) || dateEnd.before(dateStartRow))){

                    employeeRow=(IdAndName)modelCRMDetail.getValueAt(i, 1);

                    if(employee.getId().equals(employeeRow.getId())){
                        overlapRowNum+=modelCRMDetail.getValueAt(i, 0)+", ";
                        isOverlap=true;
                    }
                }

            }
            
        }else{
            
            int rowNum;
            
            for(int i=0;i<modelCRMDetail.getRowCount();i++){
            
                dateStartRow=(Date)modelCRMDetail.getValueAt(i, 3);
                dateEndRow=(Date)modelCRMDetail.getValueAt(i, 4);


                rowNum= Integer.parseInt(modelCRMDetail.getValueAt(i, 0) + "" );

                if( !(dateStart.equals(dateEndRow) || dateStart.after(dateEndRow) || dateEnd.equals(dateStartRow) || dateEnd.before(dateStartRow))
                        && (selectedRowIndex+1)!=rowNum ){

                    employeeRow=(IdAndName)modelCRMDetail.getValueAt(i, 1);

                    if(employee.getId().equals(employeeRow.getId())){
                        overlapRowNum+=modelCRMDetail.getValueAt(i, 0)+", ";
                        isOverlap=true;
                    }
                }

            }
            
        }
        
        
        
        if(!overlapRowNum.equals("")){
            overlapRowNum=overlapRowNum.substring(0,overlapRowNum.length()-2);
            JOptionPane.showMessageDialog(null, "Date start and Date end is overlap with the follwing row:\n"+overlapRowNum,"",JOptionPane.ERROR_MESSAGE);

        }
        
        
        
        return isOverlap;
    }
    
    
    boolean checkData(){
        
        
        
        
        if(cbEmployee.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(null, "Please select an employee","",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(cbAction.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(null, "Please select an action","",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(clFunction.checkIntervalHourAndMinute(dStart.getValue(), dEnd.getValue(), 6000,1,false)){
            JOptionPane.showMessageDialog(null, "Duration between date start and date end has to be greater or equal one minute","",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        if(checkOverlapDetail()){
            return false;
        }
        
        
        return true;
    }
    
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        if(btnAdd.getText().equals("Cancel")){
            jTableCRMDetail.clearSelection();
            selectedRowIndex=-1;
            return;
        }
        
        
        if(!checkData()){
            return;
        }
       
        int n=modelCRMDetail.getRowCount()+1;
        
        Object [] obj=prepareDetailData(n);
        
        modelCRMDetail.addRow(obj);
        
        clearForm();
    }//GEN-LAST:event_btnAddActionPerformed

    Object[] prepareDetailData(int n){
        IdAndName employee=(IdAndName)cbEmployee.getSelectedItem();
        IdAndName action=(IdAndName)cbAction.getSelectedItem();
        
        
        
        String location=txtLocation.getText().trim();
        
        String description=txtDescription.getText().trim();
        
        IdAndName approver;
        
        
        Object stApproveDate;
        
        
        if(checkApprove.isSelected()){
            stApproveDate=dAppoveDate.getValue();
            approver=new IdAndName(currentEmployee.getId(),currentEmployee.getName());
        }else{
            stApproveDate="";
            approver=new IdAndName("","");
        }
        
        
        Object [] obj={n,employee,action,dStart.getValue(),dEnd.getValue(),location,description,stApproveDate,approver};
         
        return obj;
        
    }
    
    
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        
        if(!checkData()){
            return;
        }
        
        int n=Integer.parseInt(modelCRMDetail.getValueAt(selectedRowIndex, 0)+"") ;
        
        Object [] obj=prepareDetailData(n);
        
        
        
        
        for(int i=0;i<modelCRMDetail.getColumnCount();i++){
            modelCRMDetail.setValueAt(obj[i],selectedRowIndex, i);
        }
        
        
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        modelCRMDetail.removeRow(selectedRowIndex);
        
        clFunction.resetAutoNumber(selectedRowIndex, modelCRMDetail);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void checkApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkApproveActionPerformed
       dAppoveDate.setEnabled(checkApprove.isSelected());
    }//GEN-LAST:event_checkApproveActionPerformed

    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private controls.SubJComboBox cbAction;
    private controls.SubJComboBox cbCustomer;
    private controls.SubJComboBox cbEmployee;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JCheckBox checkApprove;
    private controls.JDateTimePicker dAppoveDate;
    private controls.JDateTimePicker dEnd;
    private controls.JDateTimePicker dStart;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableCRMDetail;
    private javax.swing.JLabel lbApprove;
    private javax.swing.JSpinner spinPriority;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextArea txtLocation;
    private javax.swing.JTextArea txtMasterDesc;
    // End of variables declaration//GEN-END:variables

    private void getDataForEdit() {
        selectedRowIndex=jTableCRMDetail.getSelectedRow();
        
        IdAndName employee=(IdAndName)modelCRMDetail.getValueAt(selectedRowIndex, 1);
        cbEmployee.setSelectedItem(employee);
        
        IdAndName action=(IdAndName)modelCRMDetail.getValueAt(selectedRowIndex, 2);
        cbAction.setSelectedItem(action);
        
       
        
        dStart.setValue((Date)modelCRMDetail.getValueAt(selectedRowIndex, 3));
        
        dEnd.setValue((Date)modelCRMDetail.getValueAt(selectedRowIndex,4));
        
        
        txtLocation.setText(modelCRMDetail.getValueAt(selectedRowIndex, 5)+"");
        
        txtDescription.setText(modelCRMDetail.getValueAt(selectedRowIndex,6)+"");
        
        
        
        
        if((modelCRMDetail.getValueAt(selectedRowIndex,7)+"").equals("")){
            checkApprove.setSelected(false);
        }else{
            checkApprove.setSelected(true);
            dAppoveDate.setValue((Date)modelCRMDetail.getValueAt(selectedRowIndex,7));
        }
        checkApproveActionPerformed(null);
    }
}



