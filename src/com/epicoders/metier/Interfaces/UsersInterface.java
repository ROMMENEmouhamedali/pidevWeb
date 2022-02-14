package com.epicoders.metier.Interfaces;

import com.epicoders.metier.Entities.Event;
import com.epicoders.metier.Entities.User;

public interface Imetier {

    /*
    User Management
     */

    public void addUser (User u);

    /*
    Event Management
     */
    public void addEvent(Event e);



}
