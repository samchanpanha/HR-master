/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myClass;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Nemesis
 */
public class foregroundTable extends JTable{


    
    @Override
    public Component prepareRenderer(TableCellRenderer tcr, int i, int i1) {
       
        Component com=super.prepareRenderer(tcr, i, i1);
        
        String needUpdate=this.getModel().getValueAt(i, this.getModel().getColumnCount()-1)+"";
        
        if(needUpdate.equals("1")){
            com.setForeground(Color.RED);
        }else{
            com.setForeground(Color.BLACK);
        }
        
        
        return com;
    }
    
    

    
    
    
}
