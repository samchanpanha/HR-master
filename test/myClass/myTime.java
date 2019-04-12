/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myClass;

import static myClass.clFunction.getFormattedDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Nemesis
 */
public class myTime {



    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
    
    
    public myTime() {
    
    }

    public myTime(Date date) {
       
        this.date = date;
    }
    
    

    @Override
    public String toString() {
        
        
        return getFormattedDate(date,"HH:mm");
    }

    @Override
    public boolean equals(Object obj) {
        return this.date.equals(obj);
    }
    
    public void myTime(Date date) {
       
        this.date = date;
    }
    
}
