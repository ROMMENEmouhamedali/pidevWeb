package tn.happ.DB_Connection;




import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionSingleton {
    private static Connection connection;


     public ConnexionSingleton(){}
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/HAPP","root","");
            System.out.println("Connection was created");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}
