/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ghmougui ons
 */
public class MyConnection {
    private  Connection myConx;
    private  String url="jdbc:mysql://localhost:3306/ecoartdb";
    private  String login="root";
    private  String pswd="";
    
    private static MyConnection instanceConx;

    private MyConnection() throws SQLException {
        myConx = DriverManager.getConnection(url, login, pswd);
    }
    
    
    public static MyConnection getInstance (){
        if(instanceConx==null)
            try {
                return new MyConnection();
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
        return instanceConx;
    }
    
    public Connection getConnection(){
        return myConx;   
    } 
    
}
