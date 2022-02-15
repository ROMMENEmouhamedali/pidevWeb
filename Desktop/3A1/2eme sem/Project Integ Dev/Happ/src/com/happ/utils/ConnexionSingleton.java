/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happ.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ConnexionSingleton {
    private String url="jdbc:mysql://127.0.0.1:3306/Happ";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static ConnexionSingleton instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private ConnexionSingleton() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection Created");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
   public static ConnexionSingleton getInstance(){
       
       if(instance==null) // Class ConnexionSingletion is not instantiated
           instance=new ConnexionSingleton();
       return instance;
   }
    
    
    
    
}
