/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oudom;

import java.util.List;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Chim chanoudom
 */
public class method {
   private static Attenden_Details ad=null;
    public static void  rowfilter(List<String> Qur,JTable tb){
        DefaultTableModel tm=(DefaultTableModel) tb.getModel();
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<>(tm);
        tb.setRowSorter(tr);
        for (int i = 0; i < Qur.size(); i++) {
            tr.setRowFilter(RowFilter.regexFilter(Qur.get(i)));
        }
        
    }

    /**
     * @return the ad
     */
    public static Attenden_Details getAd() {
        return ad;
    }

    /**
     * @param aAd the ad to set
     */
    public static void setAd(Attenden_Details aAd) {
        ad = aAd;
    }


}
