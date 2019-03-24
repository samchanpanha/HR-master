/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author panha
 */
public class ConMysql {
     public static Connection getDBConnection() {
        String sURL="jdbc:mysql://localhost:3306/db_hrm";
        //String sURL="jdbc:mysql://192.xxx.2.s:3306/sss";
        String sUserName="root";
        String sPwd="";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(sURL, sUserName,sPwd);
            return conn;
            } catch (SQLException | ClassNotFoundException e) {
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
