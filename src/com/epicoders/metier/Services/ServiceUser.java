package com.epicoders.metier.Services;

import com.epicoders.metier.DB_Connection.ConnexionSingleton;
import com.epicoders.metier.Entities.Event;
import com.epicoders.metier.Entities.User;
import com.epicoders.metier.ExceptionHandler.ExceptionSqlError;
import com.epicoders.metier.Interfaces.UsersInterface;

import javax.management.InstanceNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceUser implements UsersInterface {

    PreparedStatement ps;

    @Override
    public void addUser(User u) {
        Connection conn = ConnexionSingleton.getConnection();
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
    public boolean authenticate(String email, String password) {
        Connection conn = ConnexionSingleton.getConnection();
        try {
            ps = conn.prepareStatement
                    ("select * from USER where email= '" + email + "' ");

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                if (resultSet != null) {
                    PreparedStatement psCheckPassword = conn.prepareStatement
                            ("select * from USER where email= '" + email + "' and password = '" + password + "' ");
                    ResultSet resultSetFinalCheck = psCheckPassword.executeQuery();
                    while (resultSetFinalCheck.next()) {
                        if (resultSetFinalCheck != null) {
                            User us = new User();
                            us.setEmail(resultSetFinalCheck.getString("email"));
                            us.setPassword(resultSetFinalCheck.getString("password"));
                            if (resultSet != null && resultSetFinalCheck != null) {
                                System.out.println(us);
                            }

                        }
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException es) {
            es.printStackTrace();
        }

        return false;
    }

    @Override
    public void deleteUser(int id) throws ExceptionSqlError {
        Connection conn = ConnexionSingleton.getConnection();


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

    @Override
    public User findUserById(int id) throws SQLException {
        Connection conn = ConnexionSingleton.getConnection();
        User _user = new User();
        ps = conn.prepareStatement("select * FROM user WHERE ID_USER ='" + id + "' ");

        ResultSet rs = ps.executeQuery();
        while(rs.next()){

                System.out.println(rs.getString("id_user"));

                _user.setIdUser(rs.getInt("id_user"));
                _user.setFirstName(rs.getString("firstname"));
                _user.setLastName(rs.getString("lastname"));
                _user.setUserName(rs.getString("username"));
                _user.setEmail(rs.getString("email"));
                _user.setPhoneNumber(rs.getInt("id_user"));
            }

         return _user;

    }

    @Override
    public void updateUser(User u) {
        Connection conn = ConnexionSingleton.getConnection();


        try {

            ps = conn.prepareStatement("UPDATE USER set FIRSTNAME=?,LASTNAME=?,USERNAME=?," +
                    "EMAIL=?,PHONENUMBER=? WHERE ID_USER= ? ");

            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getUserName());
            ps.setString(4, u.getEmail());
            ps.setInt(5, u.getPhoneNumber());


            ps.setInt(6,u.getIdUser());
            ps.executeUpdate();
            ps.close();
            }catch (SQLException e){
            e.printStackTrace();



        }

    }


}



