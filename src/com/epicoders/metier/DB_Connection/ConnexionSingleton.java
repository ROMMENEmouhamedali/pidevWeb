package com.epicoders.metier.DB_Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionSingleton {
    private static Connection connection;



    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/HAAP","root","");
            System.out.println("Creation d une connection");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}
