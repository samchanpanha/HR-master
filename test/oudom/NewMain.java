/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oudom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Chim chanoudom
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
       String myTime = "10:30:54";
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
    Date date = null;
    try {
        date = sdf.parse(myTime);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    String formattedTime = sdf.format(date);

    System.out.println(formattedTime);
    
    
 String startAfter = "07:01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        date=dateFormat.parse(startAfter);
        System.out.println(date.getTime());
    }
   
        

}
