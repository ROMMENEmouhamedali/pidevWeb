package com.epicoders.metier.Services;

import com.epicoders.metier.DB_Connection.ConnexionSingleton;
import com.epicoders.metier.Entities.Event;
import com.epicoders.metier.Entities.User;
import com.epicoders.metier.Interfaces.Imetier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetierCataloguesImpl implements Imetier {
    /*
    User Management
     */
    @Override
    public void addUser(User u) {
        Connection conn = ConnexionSingleton.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement
                    ("insert into USER(FIRSTNAME,LASTNAME,USERNAME,EMAIL,PHONENUMBER,USERROLE, password) values(?,?,?,?,?,?,?)");
            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getUserName());
            ps.setString(4,u.getEmail());
            ps.setInt(5,u.getPhoneNumber());
            ps.setString(6, u.getUserRole().toString());
            ps.setString(7, u.getPassword());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException es) {
            es.printStackTrace();
        }

    }
    public boolean authenticate(String email,String password ) {
        Connection conn = ConnexionSingleton.getConnection();
        try {
            PreparedStatement psCheckEmail = conn.prepareStatement
                    ("select * from USER where email= '" + email + "' ");

            ResultSet resultSet = psCheckEmail.executeQuery();
            while (resultSet.next()) {
                if(resultSet != null){
                    PreparedStatement psCheckPassword = conn.prepareStatement
                            ("select * from USER where email= '" + email + "' and password = '"+password+"' ");
                    ResultSet resultSetFinalCheck = psCheckPassword.executeQuery();
                    while (resultSetFinalCheck.next()){
                        if(resultSetFinalCheck != null){
                            User us = new User();
                            us.setEmail(resultSetFinalCheck.getString("email"));
                            us.setPassword(resultSetFinalCheck.getString("password"));
                            if(resultSet != null && resultSetFinalCheck != null) {
                                System.out.println(us);
                            }

                        }
                    }
                }
                return  true;
            }
            return false;
        } catch (SQLException es) {
            es.printStackTrace();
        }

        return false;
    }


    /*
    Event management
     */

    @Override
    public void addEvent(Event e) {
        Connection conn = ConnexionSingleton.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement
                    ("insert into EVENT(NAME_EVENT,PRICE_EVENT,DESCRIPTION_EVENT,STARTDATE_EVENt) values(?,?,?,?)");
            ps.setString(1, e.getNameEvent());
            ps.setInt(2, e.getPriceEvent());
            ps.setString(3, e.getDescriptionEvent());
            ps.setDate(4,e.getStartDateEvent());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException es) {
            es.printStackTrace();
        }

    }


}
