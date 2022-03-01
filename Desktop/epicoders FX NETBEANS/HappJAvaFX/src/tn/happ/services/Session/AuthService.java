package tn.happ.services.Session;


//import com.example.happfxfinal.DB_Connection.ConnexionSingleton;
import tn.happ.DB_Connection.ConnexionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    PreparedStatement ps;
    Connection conn;

    public AuthService() {
        this.conn = ConnexionSingleton.getConnection();
    }

    public boolean authenticate(String email, String password) {

        try {
            ps = conn.prepareStatement
                    ("select * from USER where email= '" + email + "' ");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if (resultSet != null) {
                    System.out.println("email exist");
                    PreparedStatement psCheckPassword = conn.prepareStatement
                            ("select * from USER where email= '" + email + "' and password = '" + password + "' ");
                    ResultSet resultSetFinalCheck = psCheckPassword.executeQuery();

                    while (resultSetFinalCheck.next()) {

                        if (resultSetFinalCheck != null) {

                            AuthResponseDTO _this = new AuthResponseDTO();
                            _this.setIdUser((resultSetFinalCheck.getInt("id_user")));
                            _this.setFirstName(resultSetFinalCheck.getString("firstname"));
                            _this.setLastName(resultSetFinalCheck.getString("lastname"));
                            _this.setEmail(resultSetFinalCheck.getString("email"));
                            _this.setUserName(resultSetFinalCheck.getString("username"));
                            _this.setUserRole(resultSetFinalCheck.getString("USERROLE"));
                            _this.setCollaboratorType((resultSetFinalCheck.getString("COLLABORATORTYPE")));

                            UserSession.getSameInstance(_this);
                            if (resultSet != null && resultSetFinalCheck != null) {
                                System.out.println(_this);
                            }
                        }
                    }
//                    System.out.println("bad credentials");
                }
                return false;
            }
            System.out.println("email does not exist");
            return false;

        } catch (SQLException es) {
            es.printStackTrace();
        }

        return false;
    }
}

