/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Training;

import TrainningType.clTrainingType;
import dayOff.clDayOff;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import myClass.FormatRenderer;
import myClass.IdAndName;
import myClass.IdAndValue;
import myClass.clFunction;
import myClass.currentEmployee;

/**
 *
 * @author Nemesis
 */
public class frmTrainingAdd extends javax.swing.JInternalFrame {

    /**
     * Creates new form trainingList
     */
    public frmTrainingAdd() {
        initComponents();
        modelTrainingList=(DefaultTableModel)jTableTrainingList.getModel();
        
        clFunction.changeLookTable(jTableTrainingList);
        
        
        
        
        DocumentListener doc=new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                changeDateValueOndEnd();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
               
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                
            }
        };
        
        dStart.addEventToSpinner(doc);
        
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        TableCellRenderer renderer = new FormatRenderer(format);
        
        
        
        TableColumnModel m = jTableTrainingList.getColumnModel();
        m.getColumn(1).setCellRenderer(renderer);
        m.getColumn(3).setCellRenderer(renderer);
        
        m.getColumn(4).setCellRenderer(renderer);
        jTableTrainingList.removeColumn(m.getColumn(m.getColumnCount()-1));
        
        
        jTableTrainingList.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            if (!lse.getValueIsAdjusting()) {
                changeButtonContext();
            }
        });
        
        
    }
    
    
    void changeButtonContext(){
        if(jTableTrainingList.getSelectedRowCount()==1){
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dStart = new controls.JDateTimePicker();
        dEnd = new controls.JDateTimePicker();
        cbEmployee = new controls.SubJComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbTrainingType = new controls.SubJComboBox();
        jLabel6 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        cbStatus = new controls.SubJComboBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTrainingList = new myClass.foregroundTable();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setText("ID:");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setText("Date Start:");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setText("Date End:");

        dStart.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        dStart.setFormatPattern("dd/MM/yyyy");
        dStart.setIgnoreSecond(true);
        dStart.setIgnoreTime(true);

        dEnd.setEnabled(false);
        dEnd.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        dEnd.setFormatPattern("dd/MM/yyyy");
        dEnd.setIgnoreSecond(true);
        dEnd.setIgnoreTime(true);

        cbEmployee.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel5.setText("Employee:");

        cbTrainingType.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbTrainingType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTrainingTypeActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel6.setText("Training Type:");

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

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending", "Pass", "Fail" }));
        cbStatus.setSelectedIndex(-1);
        cbStatus.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel7.setText("Status:");

        jTableTrainingList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date Created", "Created By", "DateStart", "DateEnd", "Trainee", "Training Type", "Status", "needUpdate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableTrainingList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbTrainingType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dStart, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addComponent(dStart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(12, 12, 12)
                        .addComponent(btnEdit)
                        .addGap(12, 12, 12)
                        .addComponent(btnDelete)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(cbEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTrainingType, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        txtId.setText(clFunction.getLastId("training"));
        
        DefaultComboBoxModel modelCBEmployee=(DefaultComboBoxModel)cbEmployee.getModel();
        clDayOff.getModelCbEmployee(modelCBEmployee);
        
        
        DefaultComboBoxModel modelTrainingType=(DefaultComboBoxModel)cbTrainingType.getModel();
        clTrainingType.getModelCbTrainingType(modelTrainingType);
        
        clTraining.getModelTrainingList(modelTrainingList);
        
        
        
    }//GEN-LAST:event_formComponentShown

    DefaultTableModel modelTrainingList;
    
    
    boolean checkData(){
        
        
        
        if(cbEmployee.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(this, "Please select an employee","",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(cbTrainingType.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(this, "Please select a trainingType","",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
         if(cbStatus.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(this, "Please choose a status","",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        if(btnAdd.getText().equals("Cancel")){
            btnAdd.setText("Add");
            clearForm();
            jTableTrainingList.clearSelection();
            return;
        }
        
        
        if(!checkData()){
            return;
        }
        
        
        
        IdAndName employee=(IdAndName)cbEmployee.getSelectedItem();
        
        String formatPattern="yyyy/MM/dd";
        
        String stDateStart=dStart.getStringValue(formatPattern);
        
        String stDateEnd=dEnd.getStringValue(formatPattern);
        
        if(clTraining.checkOverlapDb(employee.getId(), stDateStart, stDateEnd,";")){
            return;
        }
        
        //DateStart,DateEnd,EmpId,TrainingTypeID,status,DateCreated,createdBy
        txtId.setText(clFunction.getLastId("training"));
        
        
        
        
        IdAndValue traingingType=(IdAndValue)cbTrainingType.getSelectedItem();
        
        String status=cbStatus.getSelectedItem()+"";
        
        String []data={stDateStart,stDateEnd,employee.getId(),traingingType.getId(),status};
        
        
        //"ID", "Date Created", "Create By", "Date Start", "Date End", "Trainee", "Training Type", "Status"

        
        
        
        if(clTraining.insert(data)){
            
           
           
            
            String needUpdate=getNeedUpdate(status);
            
            
            
            Object []obj={txtId.getText(),new Date(),currentEmployee.getName(),dStart.getValue(),dEnd.getValue(),employee,traingingType,status,needUpdate};
            
            modelTrainingList.addRow(obj);
            clearForm();
        }
        
    }//GEN-LAST:event_btnAddActionPerformed

    
    String getNeedUpdate(String status){
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

//        Calendar cEnd=Calendar.getInstance();
//        cEnd.setTime(dEnd.getValue());
//        cEnd.set(Calendar.HOUR, 0);
//        cEnd.set(Calendar.MINUTE, 0);
//        cEnd.set(Calendar.SECOND, 0);
//        cEnd.set(Calendar.MILLISECOND, 0);
//        
//        System.out.println(c.getTime());
//        System.out.println(cEnd.getTime());
//        System.out.println("----");

//        boolean outdated=cEnd.getTime().before(c.getTime()) || cEnd.getTime() == c.getTime();
        
        boolean outdated=dEnd.getValue().before(c.getTime()) || dEnd.getValue() == c.getTime();
        
        
        String needUpdate;
        if(status.equals("Pending") && outdated){
            needUpdate="1";
        }else{
            needUpdate="0";
        }
        
        return needUpdate;
    }
    
    private void cbTrainingTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTrainingTypeActionPerformed
        
        changeDateValueOndEnd();
        
    }//GEN-LAST:event_cbTrainingTypeActionPerformed

    private void cbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRowIndex=jTableTrainingList.getSelectedRow();
        String id=modelTrainingList.getValueAt(selectedRowIndex, 0)+"";
        
        if(clTraining.delete(id)){
            modelTrainingList.removeRow(selectedRowIndex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        if(!checkData()){
            return;
        }
        
        int selectedRowIndex=jTableTrainingList.getSelectedRow();
        
        
        
        IdAndName employee=(IdAndName)cbEmployee.getSelectedItem();
        
        String formatPattern="yyyy/MM/dd";
        
        String stDateStart=dStart.getStringValue(formatPattern);
        
        String stDateEnd=dEnd.getStringValue(formatPattern);
        
        if(clTraining.checkOverlapDb(employee.getId(), stDateStart, stDateEnd,"and trainingId<>"+txtId.getText())){
            return;
        }
        
        
        IdAndValue traingingType=(IdAndValue)cbTrainingType.getSelectedItem();
        
        String status=cbStatus.getSelectedItem()+"";
        
        //update training set DateStart=?,DateEnd=?,EmpId=?,TrainingTypeID=?,status=? where trainingId=

        
        String []data={stDateStart,stDateEnd,employee.getId(),traingingType.getId(),status};
        
        String needUpdate=getNeedUpdate(status);
        
        
        if(clTraining.update(txtId.getText(), data)){
            //"ID", "Date Created", "Create By", "Date Start", "Date End", "Trainee", "Training Type", "Status"
            
            modelTrainingList.setValueAt(dStart.getValue(), selectedRowIndex, 3);
            modelTrainingList.setValueAt(dEnd.getValue(), selectedRowIndex, 4);
            modelTrainingList.setValueAt(employee, selectedRowIndex, 5);
            modelTrainingList.setValueAt(traingingType, selectedRowIndex, 6);
            modelTrainingList.setValueAt(status, selectedRowIndex, 7);
            
            modelTrainingList.setValueAt(needUpdate, selectedRowIndex, 8);
        }
        
        
        
    }//GEN-LAST:event_btnEditActionPerformed

    void changeDateValueOndEnd(){
        if(cbTrainingType.getSelectedIndex()==-1) return;
        
        Date dateStart=dStart.getValue();
        
        Calendar c=Calendar.getInstance();
        c.setTime(dateStart);
        
        IdAndValue obj= (IdAndValue)cbTrainingType.getSelectedItem();
        
        int month=Integer.parseInt(obj.getValue());
        
        c.add(Calendar.MONTH, month);
        
        dEnd.setValue(c.getTime());
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private controls.SubJComboBox cbEmployee;
    private controls.SubJComboBox cbStatus;
    private controls.SubJComboBox cbTrainingType;
    private controls.JDateTimePicker dEnd;
    private controls.JDateTimePicker dStart;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private myClass.foregroundTable jTableTrainingList;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables

    private void getDataForEdit() {
        int selectedRowIndex=jTableTrainingList.getSelectedRow();
        //"ID", "Date Created", "Create By", "Date Start", "Date End", "Trainee", "Training Type", "Status"

        
        txtId.setText(modelTrainingList.getValueAt(selectedRowIndex, 0)+"");
        
        dStart.setValue((Date)modelTrainingList.getValueAt(selectedRowIndex, 3));
        
        dEnd.setValue((Date)modelTrainingList.getValueAt(selectedRowIndex, 4));
        
        cbEmployee.setSelectedIndex(-1);
        
        cbEmployee.setSelectedItem(modelTrainingList.getValueAt(selectedRowIndex, 5));
        
        cbTrainingType.setSelectedItem(modelTrainingList.getValueAt(selectedRowIndex, 6));
        
        
        cbStatus.setSelectedItem(modelTrainingList.getValueAt(selectedRowIndex, 7)+"");
    }

    private void clearForm() {
        txtId.setText(clFunction.getLastId("training"));
        cbEmployee.setSelectedIndex(-1);
        cbTrainingType.setSelectedIndex(-1);
        cbStatus.setSelectedIndex(-1);
    }
}
