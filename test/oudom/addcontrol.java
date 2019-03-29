/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oudom;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JPopupMenu;

/**
 *
 * @author chano
 */
public class addcontrol {
    
    public static void popupmenu(JPopupMenu JP,List<String>Data){
        Data.forEach((tm) -> {
            JP.add(tm);
        });
    }
    
}
