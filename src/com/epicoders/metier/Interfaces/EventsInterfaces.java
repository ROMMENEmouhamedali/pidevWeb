package com.epicoders.metier.Interfaces;

import com.epicoders.metier.Entities.Event;
import com.epicoders.metier.ExceptionHandler.ExceptionSqlError;

import java.sql.SQLException;

public interface EventsInterfaces {
    public void addEvent(Event e);
    public void deleteEvent(int id) throws SQLException, ExceptionSqlError;
}
