package com.epicoders.metier.Services;

import com.epicoders.metier.DB_Connection.ConnexionSingleton;
import com.epicoders.metier.Entities.Event;
import com.epicoders.metier.Interfaces.EventsInterfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicesEvent implements EventsInterfaces {

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
