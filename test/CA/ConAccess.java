package CA;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author panha
 */
public class ConAccess {
     public static Connection getDBConnection() {
                Connection conn=null;
                try {
                  Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                           String part ="F:\\program file(x86)\\ZKTeco\\att2000.mdb";
                           // C:\\databaseFileName.accdb" - location of your database 
                           String url = "jdbc:ucanaccess://"+part;
                           // specify url, username, pasword - make sure these are valid 
                            conn = DriverManager.getConnection(url);
                }
                catch (Exception e){
                    e.getMessage();
                }
            return conn;
       
    }


    public static void close(Connection con)
    {
        if (con != null)
        {
            try
            {
                con.close();
            }
            catch (SQLException e)
            {
            }
        }
    }
}
