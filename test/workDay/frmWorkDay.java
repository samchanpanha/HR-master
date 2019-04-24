/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workDay;

import myClass.IdAndName;
import myClass.clFunction;
import myClass.dataCon;
import myClass.myTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nemesis
 */
public class frmWorkDay extends javax.swing.JFrame {

    /**
     * Creates new form frmCustomer
     */
    public frmWorkDay() {
        initComponents();
        
        try {
            dataCon.connectToDB();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
        
        
        
        
        prepareForm();        
        
        modelWorkDay=(DefaultTableModel)jTableWorkDay.getModel();
       
        
        clWorkDay.getTimeShiftList((DefaultComboBoxModel)cbTimeShift.getModel());
        
        
        
    }
    
    
    void prepareForm(){
        txtId.setText(clFunction.getLastId("workdays"));
        
        clFunction.setReadOnlyTextField(txtId);
        clFunction.setReadOnlyTextField(txtWorkHour);
        
        clFunction.changeLookTable(jTableWorkDay);
        
        changeButtonContext();
        jTableWorkDay.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            if (!lse.getValueIsAdjusting()) {
                changeButtonContext();
            }
        });
        
        clWorkDay.getDayList((DefaultComboBoxModel)cbDayStart.getModel());
        
        clWorkDay.getDayList((DefaultComboBoxModel)cbDayEnd.getModel());
        
        addListener();
        
        
    }
    
    
    void addListener(){
        
        cbDayStart.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                getWorkHour();
            }
        });
        
        
        cbDayEnd.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                getWorkHour();
            }
        });
        
        addDocumnetListener(spinTimeStart);
        addDocumnetListener(spinTimeEnd);
        
        addDocumnetListener(spinBreakStart);
        addDocumnetListener(spinBreakEnd);
        
    }
    
    
    void addDocumnetListener(JSpinner spinner){
        ((DateEditor)(spinner.getEditor())).getTextField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                getWorkHour();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                getWorkHour();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               getWorkHour();
            }
        });
    }
    
    
    
    void getWorkHour(){
       
        
        
        Date dStart=(Date)spinTimeStart.getValue();
        Date dEnd=(Date)spinTimeEnd.getValue();
        
        dStart=getTime(dStart);
        dEnd=getTime(dEnd);
        
        
        
        long diffInMillies = Math.abs(dEnd.getTime() - dStart.getTime());
        

        dStart=(Date)spinBreakStart.getValue();
        dEnd=(Date)spinBreakEnd.getValue();
        
        dStart=getTime(dStart);
        dEnd=getTime(dEnd);
        
        
       
        
        diffInMillies -=  Math.abs(dEnd.getTime() - dStart.getTime());
        
        
        int days=Integer.parseInt(((IdAndName)(cbDayEnd.getSelectedItem())).getId())-Integer.parseInt(((IdAndName)(cbDayStart.getSelectedItem())).getId())+1;
        
        long totalMillies=days*diffInMillies;
        
        workHour = (totalMillies / (1000*60*60));
        long minute = (totalMillies / (1000*60)) % 60;
        
        DecimalFormat df=new DecimalFormat("00");
        
        txtWorkHour.setText(df.format(workHour)+":"+ df.format(minute));
        
    }
    
    long workHour;
    
    Date getTime(Date d){
        Calendar c=Calendar.getInstance();
        
        
        Calendar cDate=Calendar.getInstance();
        cDate.setTime(d);
        
        c.set(Calendar.HOUR_OF_DAY,cDate.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE,cDate.get(Calendar.MINUTE));
//        System.out.println(c.getTime());
        return c.getTime();
        
    }
    
    void changeButtonContext(){
        if(jTableWorkDay.getSelectedRowCount()==1){
            btnAdd.setText("Cancel");
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
            
            getDataForEdit();
            
        }else{
            btnAdd.setText("Add");
            
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
            
            
        }
    }
    
    
    int selectedRowIndex=-1;
    void getDataForEdit(){
        
        
        
        selectedRowIndex=jTableWorkDay.getSelectedRow();
        
        txtId.setText(jTableWorkDay.getValueAt(selectedRowIndex, 0)+"");
        
        txtDescription.setText(jTableWorkDay.getValueAt(selectedRowIndex, 1)+"" );
        
        cbDayStart.setSelectedItem(jTableWorkDay.getValueAt(selectedRowIndex, 2));
        
        spinTimeStart.setValue( ((myTime)jTableWorkDay.getValueAt(selectedRowIndex, 3)).getDate());
        
        cbDayEnd.setSelectedItem(jTableWorkDay.getValueAt(selectedRowIndex, 4));
        
        spinTimeEnd.setValue(((myTime)jTableWorkDay.getValueAt(selectedRowIndex, 5)).getDate() );
        
        spinBreakStart.setValue(((myTime)jTableWorkDay.getValueAt(selectedRowIndex, 6)).getDate());
        
        spinBreakEnd.setValue(((myTime)jTableWorkDay.getValueAt(selectedRowIndex, 7)).getDate() );
        
        txtWorkHour.setText(jTableWorkDay.getValueAt(selectedRowIndex, 8)+"" );
        
        cbTimeShift.setSelectedItem(jTableWorkDay.getValueAt(selectedRowIndex, 9));
        
        
        
    }
    
    
    
   
    DefaultTableModel modelWorkDay;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableWorkDay = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        spinTimeStart = new javax.swing.JSpinner();
        cbDayStart = new controls.SubJComboBox();
        cbDayEnd = new controls.SubJComboBox();
        spinTimeEnd = new javax.swing.JSpinner();
        txtWorkHour = new javax.swing.JTextField();
        spinBreakStart = new javax.swing.JSpinner();
        spinBreakEnd = new javax.swing.JSpinner();
        cbTimeShift = new controls.SubJComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Work day");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setText("ID:");

        txtId.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setText("Day start:");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setText("Day end:");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel5.setText("Time start:");

        jTableWorkDay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Description", "Day start", "Time start", "Day end", "Time end", "Break time start", "Break time end", "Work hour", "Time shift"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableWorkDay);

        btnAdd.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel6.setText("Time end:");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel9.setText("Break time start:");

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel10.setText("Break time end:");

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel11.setText("Work hour:");

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel12.setText("Time shift:");

        spinTimeStart.setFont(new java.awt.Font("Roboto", 0, 18));
        spinTimeStart.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1553706000000L), null, null, java.util.Calendar.HOUR_OF_DAY));
        spinTimeStart.setEditor(new javax.swing.JSpinner.DateEditor(spinTimeStart, "HH:mm"));

        cbDayStart.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        cbDayEnd.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        spinTimeEnd.setFont(new java.awt.Font("Roboto", 0, 18));
        spinTimeEnd.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1553706000000L), null, null, java.util.Calendar.HOUR_OF_DAY));
        spinTimeEnd.setEditor(new javax.swing.JSpinner.DateEditor(spinTimeEnd, "HH:mm"));

        txtWorkHour.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        txtWorkHour.setText("00:00");

        spinBreakStart.setFont(new java.awt.Font("Roboto", 0, 18));
        spinBreakStart.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1553706000000L), null, null, java.util.Calendar.HOUR_OF_DAY));
        spinBreakStart.setEditor(new javax.swing.JSpinner.DateEditor(spinBreakStart, "HH:mm"));

        spinBreakEnd.setFont(new java.awt.Font("Roboto", 0, 18));
        spinBreakEnd.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1553706000000L), null, null, java.util.Calendar.HOUR_OF_DAY));
        spinBreakEnd.setEditor(new javax.swing.JSpinner.DateEditor(spinBreakEnd, "HH:mm"));

        cbTimeShift.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel7.setText("Description:");

        txtDescription.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(spinTimeStart, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbDayEnd, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                    .addComponent(spinTimeEnd, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                    .addComponent(txtDescription)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(82, 82, 82)
                                .addComponent(cbDayStart, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spinBreakStart, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spinBreakEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbTimeShift, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                        .addComponent(txtWorkHour, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel9)
                                    .addComponent(cbDayStart, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinBreakStart, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spinTimeStart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel10)
                                        .addComponent(spinBreakEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(87, 87, 87)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spinTimeEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel11)
                                            .addComponent(cbDayEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel12)
                                            .addComponent(cbTimeShift, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtWorkHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        clWorkDay.getWorkDayList(modelWorkDay);
    }//GEN-LAST:event_formWindowOpened

    
    boolean checkData(){
        getWorkHour();
        
            
        
        if(txtWorkHour.getText().contains("-")||workHour<30){
            JOptionPane.showMessageDialog(this, "Please check work hour", "",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        if(jTableWorkDay.getSelectedRowCount()!=1){
            
            if(!checkData()) return;

            String id=txtId.getText();
            IdAndName dayStart=(IdAndName)cbDayStart.getSelectedItem();


            IdAndName dayEnd=(IdAndName)cbDayEnd.getSelectedItem();

            myTime timeStart=new myTime((Date)spinTimeStart.getValue());

            myTime timeEnd=new myTime((Date)spinTimeEnd.getValue());

            myTime breakStart=new myTime((Date)spinBreakStart.getValue());

            myTime breakEnd=new myTime((Date)spinBreakEnd.getValue());


            String workHour=txtWorkHour.getText().substring(0,txtWorkHour.getText().indexOf(':'));
            String workMinute=txtWorkHour.getText().substring(txtWorkHour.getText().indexOf(':')+1,txtWorkHour.getText().length());


           

            IdAndName timeShift=(IdAndName)cbTimeShift.getSelectedItem();

            String stTimeStart=timeStart.toString();

            String stTimeEnd=timeEnd.toString();

            String stBreakStart=breakStart.toString();

            String stBreakEnd=breakEnd.toString();


            String [] st={txtDescription.getText(),dayStart.getId(),dayEnd.getId(),stTimeStart ,stTimeEnd,stBreakStart,stBreakEnd,workHour,workMinute,timeShift.getId()};


            
            if(clWorkDay.insert(st)){


                Object [] obj={id,txtDescription.getText(),dayStart,timeStart,dayEnd,timeEnd,breakStart,breakEnd,workHour+":"+workMinute,timeShift};

                modelWorkDay.addRow(obj);

                clearForm();


                JOptionPane.showMessageDialog(null, "Inserted successful", "",JOptionPane.INFORMATION_MESSAGE);
            }
            
                 
            
        }
        else{
            jTableWorkDay.clearSelection();
            clearForm();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String workDayId=modelWorkDay.getValueAt(selectedRowIndex, 0)+"";
        
        if(clWorkDay.delete(workDayId)){
            
            modelWorkDay.removeRow(selectedRowIndex);
            clearForm();
            JOptionPane.showMessageDialog(null, "Deleted successful", "",JOptionPane.INFORMATION_MESSAGE);
            
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        if(!checkData()) return;
        
        String id=txtId.getText();
        IdAndName dayStart=(IdAndName)cbDayStart.getSelectedItem();


        IdAndName dayEnd=(IdAndName)cbDayEnd.getSelectedItem();

        myTime timeStart=new myTime((Date)spinTimeStart.getValue());

        myTime timeEnd=new myTime((Date)spinTimeEnd.getValue());

        myTime breakStart=new myTime((Date)spinBreakStart.getValue());

        myTime breakEnd=new myTime((Date)spinBreakEnd.getValue());


        String workHour=txtWorkHour.getText().substring(0,txtWorkHour.getText().indexOf(':'));
        String workMinute=txtWorkHour.getText().substring(txtWorkHour.getText().indexOf(':')+1,txtWorkHour.getText().length());




        IdAndName timeShift=(IdAndName)cbTimeShift.getSelectedItem();

        String stTimeStart=timeStart.toString();

        String stTimeEnd=timeEnd.toString();

        String stBreakStart=breakStart.toString();

        String stBreakEnd=breakEnd.toString();


        String [] st={txtDescription.getText(),dayStart.getId(),dayEnd.getId(),stTimeStart ,stTimeEnd,stBreakStart,stBreakEnd,workHour,workMinute,timeShift.getId()};



        if(clWorkDay.update(id,st)){


            Object [] obj={id,txtDescription.getText(),dayStart,timeStart,dayEnd,timeEnd,breakStart,breakEnd,workHour+":"+workMinute,timeShift};

           

           
            
            
            for(int i=1;i<modelWorkDay.getColumnCount();i++){
                 modelWorkDay.setValueAt(obj[i], selectedRowIndex, i);
            }


            JOptionPane.showMessageDialog(null, "Updated successful", "",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEditActionPerformed

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
            java.util.logging.Logger.getLogger(frmWorkDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmWorkDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmWorkDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmWorkDay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmWorkDay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private controls.SubJComboBox cbDayEnd;
    private controls.SubJComboBox cbDayStart;
    private controls.SubJComboBox cbTimeShift;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableWorkDay;
    private javax.swing.JSpinner spinBreakEnd;
    private javax.swing.JSpinner spinBreakStart;
    private javax.swing.JSpinner spinTimeEnd;
    private javax.swing.JSpinner spinTimeStart;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtWorkHour;
    // End of variables declaration//GEN-END:variables

    private void clearForm() {
        txtId.setText(clFunction.getLastId("workdays"));
        cbDayStart.setSelectedIndex(0);
        cbDayEnd.setSelectedIndex(0);
        
        Calendar c=Calendar.getInstance();
        c.set(c.get(Calendar.YEAR) , c.get(Calendar.MONTH), c.get(Calendar.DATE), 0 , 0);
        
        
        
        spinTimeStart.setValue(c.getTime());
        
        spinTimeEnd.setValue(c.getTime());
        
        spinBreakStart.setValue(c.getTime());
        
        spinBreakEnd.setValue(c.getTime());
        
        
        cbDayEnd.setSelectedIndex(0);
        
        txtDescription.setText("");
        
        txtWorkHour.setText("00:00");
        
        cbTimeShift.setSelectedIndex(0);
    }
}
