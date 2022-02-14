package com.epicoders.metier.Interfaces;

import com.epicoders.metier.Entities.Event;
import com.epicoders.metier.Entities.User;

public interface UsersInterface {



    public void addUser (User u);
    public boolean authenticate(String email,String password );





}
