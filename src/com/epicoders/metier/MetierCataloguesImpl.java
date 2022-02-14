package com.epicoders.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MetierCataloguesImpl implements Imetier {
    @Override
    public void addEvent(Event e) {
        Connection conn = ConnexionSingleton.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement
                    ("insert into EVENT(NAME_EVENT,PRICE_EVENT,DESCRIPTION_EVENT,STARTDATE_EVENt) values(?,?,?,?)");
            ps.setString(1, e.getNameEvent());
            ps.setInt(2, e.getPriceEvent());
            ps.setString(3, e.getDescriptionEvent());
            ps.setString(4,e.getStartDateEvent());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException es) {
            es.printStackTrace();
        }

    }


}
