package tn.happ.services;

/*
import com.example.happfxfinal.Services.PermissionServices;
*/
import tn.happ.services.PermissionServices;

import java.sql.SQLException;
import tn.happ.ExceptionHandler.ExceptionSqlError;
import tn.happ.Model.Event;
import static tn.happ.services.Session.UserSession.CURRENT_USER;

public class ServicesEvent extends PermissionServices implements EventsInterfaces {
    static int CurrentUserId;

    public ServicesEvent() {
        CurrentUserId = CURRENT_USER.getUser_LoggedIn().getIdUser();
    }

    @Override
    public void addEvent(Event e) {

        if (isCollaborator(CurrentUserId) && isEventManager(CurrentUserId)) {

            try {
                ps = conn.prepareStatement
                        ("insert into EVENT(NAME_EVENT,PRICE_EVENT,DESCRIPTION_EVENT,STARTDATE_EVENt) values(?,?,?,?)");
                ps.setString(1, e.getNameEvent());
                ps.setInt(2, e.getPriceEvent());
                ps.setString(3, e.getDescriptionEvent());
                ps.setDate(4, e.getStartDateEvent());

                ps.executeUpdate();
                ps.close();

            } catch (SQLException es) {
                es.printStackTrace();
            }
        }

    }


    public void deleteEvent(Event e) {

        if (isCollaborator(CurrentUserId) && isEventManager(CurrentUserId)) {
            Event _event = new Event();
            try {
                ps = conn.prepareStatement
                        ("DELETE FROM EVENT WHERE ID_EVENt='" + _event.getIdEvent() + "' ");
                int check = ps.executeUpdate();
                if (check == 0) {
                    throw new ExceptionSqlError("Event not found");
                }

            } catch (SQLException | ExceptionSqlError es) {
                System.out.println(es.getMessage());
            }
        }

    }

    @Override
    public void updateEvent(Event e) {
        if (isCollaborator(CurrentUserId) && isEventManager(CurrentUserId)) {
            try {

                ps = conn.prepareStatement("UPDATE EVENT set NAME_EVENT=?,PRICE_EVENT=?,DESCRIPTION_EVENT=?," +
                        "STARTDATE_EVENT=? WHERE ID_EVENT= ? ");

                ps.setString(1, e.getNameEvent());
                ps.setInt(2, e.getPriceEvent());
                ps.setString(3, e.getDescriptionEvent());
                ps.setDate(4, e.getStartDateEvent());

                ps.setInt(5, e.getIdEvent());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException es) {
                es.printStackTrace();
            }
        }
    }

    @Override
    public Event findEventById(Event e) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

    
