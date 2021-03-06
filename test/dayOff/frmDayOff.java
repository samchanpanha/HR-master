/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dayOff;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import myClass.IdAndName;
import myClass.clFunction;
import myClass.dataCon;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nemesis
 */
public class frmDayOff extends JInternalFrame {

    /**
     * @return the map
     */
    public static Map<String, String> getMap() {
        return map;
    }

    /**
     * @param aMap the map to set
     */
    public static void setMap(Map<String, String> aMap) {
        map = aMap;
    }

    /**
     * @return the modelCbEmployee
     */


    /**
     * Creates new form frmAction
     */
    public frmDayOff() {
        initComponents();
        
        
        
        
       
        
        clFunction.changeLookTable(jTableDayOff);
        
        this.modeldayOff=(DefaultTableModel)jTableDayOff.getModel();
        
        
        modelCbEmployee=(DefaultComboBoxModel)cbEmployee.getModel();
        
        clDayOff.getModelCbEmployee(modelCbEmployee);
        
        cbEmployee.setSelectedIndex(-1);
        
        clDayOff.getModelDayOffList(modeldayOff);
        
        prepareForm();
        
        
        
    }

    DefaultTableModel modeldayOff;
    private DefaultComboBoxModel  modelCbEmployee;
    
    
    
    
    void changeButtonContext(){
        if(jTableDayOff.getSelectedRowCount()==1){
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
    
    
    
    void prepareForm(){
        clFunction.setReadOnlyTextField(txtId);
        
        txtId.setText(clFunction.getLastId("actions"));
        
        clFunction.changeLookTable(jTableDayOff);
        
        jTableDayOff.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            if (!lse.getValueIsAdjusting()) {
                changeButtonContext();
            }
        });
    }
    
    
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
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        dStart = new controls.JDateTimePicker();
        dEnd = new controls.JDateTimePicker();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDayOff = new javax.swing.JTable();
        cbEmployee = new controls.SubJComboBox();
        jLabel6 = new javax.swing.JLabel();
        panelDay = new controls.panelDay();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Day Off");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setText("ID:");

        txtId.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setText("Day Start:");

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

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setText("Day End:");

        dStart.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        dStart.setFormatPattern("dd/MM/yyyy");

        dEnd.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        dEnd.setFormatPattern("dd/MM/yyyy");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel5.setText("Employee:");

        jTableDayOff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Employee", "Start Date", "End Date", "Day"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableDayOff);

        cbEmployee.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel6.setText("Day:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtId)
                                    .addComponent(dEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dStart, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                                    .addComponent(cbEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(133, 133, 133)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(panelDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(20, 20, 20)
                        .addComponent(btnEdit)
                        .addGap(20, 20, 20)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(cbEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dStart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        
    }//GEN-LAST:event_formWindowOpened

    boolean checkData(){
        
        Date dateStart=dStart.getValue();
        Date dateEnd=dEnd.getValue();
        
        
        if(clFunction.checkIntervalHourAndMinute(dateStart, dateEnd,86400000,7,true)){
            JOptionPane.showMessageDialog(this, "Please check Date start and Date end", "",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        if(cbEmployee.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(this, "Please select an employee", "",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(panelDay.getSelectedDayCount()==0){
            JOptionPane.showMessageDialog(this, "Please select at least a day", "",JOptionPane.ERROR_MESSAGE);
           return false;
        }
        
        
        return true;
    }
    
    
    
    String formatPattern="yyyy/MM/dd";
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        if(btnAdd.getText().equals("Cancel")){
            clearForm();
            btnAdd.setText("Add");
            jTableDayOff.clearSelection();
            return;
        }
        
        
        if(!checkData()){
            return;
        }
        
        
        
        
        int []daysNum=panelDay.getSelectedDayNum();
        
        String stDaysNum="";
        
        for(int d : daysNum){
            stDaysNum+=d+" ";
        }
        
        stDaysNum.trim();
        
        
        
        
        IdAndName employee=(IdAndName)cbEmployee.getSelectedItem();
        
        String stStart=dStart.getStringValue(formatPattern);
        
        String stEnd=dEnd.getStringValue(formatPattern);
        
        
        if(clDayOff.checkIfOverlapDB(stStart, stEnd, employee.getId(),"1=1")){
            return;
        }
        
        
        //insert into scheduleDayOff(startDate,endDate,dayId,empId) values(?,?,?,?)
        if(clDayOff.insert(stStart,stEnd,stDaysNum,employee.getId())){
         
            String []daysName=panelDay.getSelectedDayName();
            String st="";
            for(String s: daysName){
                st+=s+" ";
            }
            
            st.trim();
            
            String formatPattern="dd/MM/yyyy";
            stStart=dStart.getStringValue(formatPattern);
            stEnd=dEnd.getStringValue(formatPattern);
            
            
            Object [] obj={txtId.getText(),employee,stStart,stEnd,st};
            
            modeldayOff.addRow(obj);
            
            clearForm();
            
            
            
        }
        
        
        
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        
        if(!checkData()){
            return;
        }
        
        
        
        
        int []daysNum=panelDay.getSelectedDayNum();
        
        String stDaysNum="";
        
        for(int d : daysNum){
            stDaysNum+=d+" ";
        }
        
        stDaysNum.trim();
        
        
        
        
        IdAndName employee=(IdAndName)cbEmployee.getSelectedItem();
        
        String stStart=dStart.getStringValue(formatPattern);
        
        String stEnd=dEnd.getStringValue(formatPattern);
        

        
        
        if(clDayOff.checkIfOverlapDB(stStart, stEnd, employee.getId(),"SceheduleDayOff<>"+txtId.getText())){
            return;
        }



        if(clDayOff.update(txtId.getText(),employee.getId(),stStart,stEnd,stDaysNum)){
         
            String []daysName=panelDay.getSelectedDayName();
            String st="";
            for(String s: daysName){
                st+=s+" ";
            }
            
            st.trim();
            
            
            
            Object [] obj={txtId.getText(),employee,stStart,stEnd,st};
            
            
            
            for(int i=0;i<jTableDayOff.getColumnCount();i++){
                modeldayOff.setValueAt(obj[i],selectedRowIndex, i);
            }
            
            
            
            
            
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if(clDayOff.delete(txtId.getText())){
            modeldayOff.removeRow(selectedRowIndex);
            
            
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        txtId.setText(clFunction.getLastId("sceheduledayoffs"));
        
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
            java.util.logging.Logger.getLogger(frmDayOff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDayOff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDayOff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDayOff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDayOff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private controls.SubJComboBox cbEmployee;
    private controls.JDateTimePicker dEnd;
    private controls.JDateTimePicker dStart;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDayOff;
    private controls.panelDay panelDay;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables

    int selectedRowIndex=-1;
    
    private static Map<String, String> map = new HashMap<String, String>();
    
    
    void getDataForEdit(){
        
        String formatPattern="dd/MM/yyyy";
        
        selectedRowIndex=jTableDayOff.getSelectedRow();
        
        txtId.setText(jTableDayOff.getValueAt(selectedRowIndex, 0)+"");
        
        cbEmployee.setSelectedItem(modeldayOff.getValueAt(selectedRowIndex, 1));
        
        
        
        Date dateStart=clFunction.formatStringTodate(modeldayOff.getValueAt(selectedRowIndex, 2)+"", formatPattern);
        Date dateEnd=clFunction.formatStringTodate(modeldayOff.getValueAt(selectedRowIndex, 3)+"", formatPattern);
        
        
        dStart.setValue(dateStart);
        dEnd.setValue(dateEnd);
        
        panelDay.clearCheck();
        
        String[] dayName=(modeldayOff.getValueAt(selectedRowIndex, 4)+"").split(" ");
        
        for(String day: dayName){
            panelDay.setSelectedOnSpecificDay(day, true);
        }
        
    }

    private void clearForm() {
       
        txtId.setText(clFunction.getLastId("SceheduleDayOffs"));
        cbEmployee.setSelectedIndex(-1);
        panelDay.clearCheck();
        
        dStart.setValue(new Date());
        dEnd.setValue(new Date());
    }
}
