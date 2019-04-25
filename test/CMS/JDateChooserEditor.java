/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMS;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author panha
 */
public class JDateChooserEditor extends DefaultCellEditor{
     public JDateChooserEditor(JCheckBox checkBox)
  {
    super(checkBox);

  }

  JDateChooser date;
     @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) 
  {

         date = new JDateChooser();
         date.setDateFormatString("yyyy-MM-dd");
         return date;
  }

     @Override
  public Object getCellEditorValue() 
  {
    return new String(((JTextField)date.getDateEditor().getUiComponent()).getText());
  }

  public boolean stopCellEditing()
  {
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}
