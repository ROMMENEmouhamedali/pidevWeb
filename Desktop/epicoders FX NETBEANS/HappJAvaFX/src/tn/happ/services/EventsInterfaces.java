package tn.happ.services;

import java.sql.SQLException;
import tn.happ.ExceptionHandler.ExceptionSqlError;
import tn.happ.Model.Event;

public interface EventsInterfaces {
    public void addEvent(Event e);

    public void deleteEvent(Event e) throws SQLException, ExceptionSqlError;

    public void updateEvent(Event e);

    public Event findEventById(Event e) throws SQLException;
}
