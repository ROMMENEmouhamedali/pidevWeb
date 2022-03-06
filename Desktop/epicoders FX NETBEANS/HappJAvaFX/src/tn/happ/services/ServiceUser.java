package tn.happ.services;

/*
import com.example.happfxfinal.Model.Collaborator;
import com.example.happfxfinal.Model.Enumerations.CollaboratorType;
import com.example.happfxfinal.Model.User;
import com.example.happfxfinal.DB_Connection.ConnexionSingleton;
import com.example.happfxfinal.Services.PermissionServices;
*/

import tn.happ.DB_Connection.ConnexionSingleton;
import tn.happ.ExceptionHandler.ExceptionSqlError;
import tn.happ.Model.Collaborator;
import tn.happ.Model.Ennumerations.CollaboratorType;
import tn.happ.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ServiceUser extends PermissionServices implements UsersInterface {
    Connection conn;
    PreparedStatement ps;


    public ServiceUser() {
        this.conn = ConnexionSingleton.getConnection();

    }

    /*******    user management            ************/
    @Override
    public void addUser(User u) {
        try {
            ps = conn.prepareStatement
                    ("insert into USER(FIRSTNAME,LASTNAME,USERNAME,EMAIL,PHONENUMBER,USERROLE, password) values(?,?,?,?,?,?,?)");
            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getUserName());
            ps.setString(4, u.getEmail());
            ps.setInt(5, u.getPhoneNumber());
            ps.setString(6, u.getUserRole().toString());
            ps.setString(7, u.getPassword());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException es) {
            es.printStackTrace();
        }
    }




    @Override
    public void deleteUser(User u) throws ExceptionSqlError {
        try {
            ps = conn.prepareStatement("DELETE FROM User WHERE ID_USER='" + u.getIdUser() + "' ");
            int check = ps.executeUpdate();
            if (check == 0) {
                throw new ExceptionSqlError("User not found");
            }
        } catch (SQLException | ExceptionSqlError e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void updateUser(User u) {
        try {
            ps = conn.prepareStatement("UPDATE USER set FIRSTNAME=?,LASTNAME=?,USERNAME=?," +
                    "EMAIL=?,PHONENUMBER=? WHERE ID_USER= ? ");
            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getUserName());
            ps.setString(4, u.getEmail());
            ps.setInt(5, u.getPhoneNumber());
            ps.setInt(6, u.getIdUser());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addCollaborator(Collaborator c) {
        if(isAdmin(231)) {
            try {
                ps = conn.prepareStatement
                        ("insert into USER(FIRSTNAME,LASTNAME,USERNAME,EMAIL,PHONENUMBER,USERROLE, password, COLLABORATORTYPE) values(?,?,?,?,?,?,?,?)");
                ps.setString(1, c.getFirstName());
                ps.setString(2, c.getLastName());
                ps.setString(3, c.getUserName());
                ps.setString(4, c.getEmail());
                ps.setInt(5, c.getPhoneNumber());
                ps.setString(6, c.getUserRole().toString());
                ps.setString(7, c.getPassword());

                String enumType = c.getCollaboratorType().toString();
                switch (enumType) {
                    case "EMPLOYEE":
                        ps.setString(8, CollaboratorType.EMPLOYEE.toString());
                        break;
                    case "EVENTMANAGER":
                        ps.setString(8, CollaboratorType.EVENTMANAGER.toString());
                        break;
                    case "HRMANAGER":
                        ps.setString(8, CollaboratorType.HRMANAGER.toString());
                        break;
                    case "CATERINGMANAGER":
                        ps.setString(8, CollaboratorType.CATERINGMANAGER.toString());
                        break;
                    case "RECIPTIONIST":
                        ps.setString(8, CollaboratorType.RECEPTIONIST.toString());
                        break;
                    case "ASSISTANTCHEF":
                        ps.setString(8, CollaboratorType.ASSISTANTCHEF.toString());
                        break;

                    default:
                        enumType = "Invalid role";
                        break;

                }
                ps.executeUpdate();
                ps.close();

            } catch (SQLException es) {
                es.printStackTrace();
            }
        }
    }

    @Override
    public void deleteCollaborator( int id) throws SQLException, ExceptionSqlError {
        if (isAdmin(231)) {
            try {
                ps = conn.prepareStatement("DELETE FROM User WHERE ID_USER='" + id + "' ");
                int check = ps.executeUpdate();
                if (check == 0) {
                    throw new ExceptionSqlError("User not found");
                }
            } catch (SQLException | ExceptionSqlError e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void updateCollaborator(Collaborator c) {
        if (isAdmin(231)) {
            try {
                ps = conn.prepareStatement("UPDATE USER set FIRSTNAME=?,LASTNAME=?,USERNAME=?," +
                        "EMAIL=?,PHONENUMBER=? WHERE ID_USER= ? ");
                ps.setString(1, c.getFirstName());
                ps.setString(2, c.getLastName());
                ps.setString(3, c.getUserName());
                ps.setString(4, c.getEmail());
                ps.setInt(5, c.getPhoneNumber());
                ps.setInt(6, c.getIdUser());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}




