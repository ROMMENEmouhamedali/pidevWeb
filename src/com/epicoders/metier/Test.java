package com.epicoders.metier;

import java.sql.Connection;
import java.sql.Date;

public class Test {

    public static void main(String[] args) {
        Connection conn = ConnexionSingleton.getConnection();
        MetierCataloguesImpl metier = new MetierCataloguesImpl();
        metier.addEvent(new Event("saturday night",120,"every saturday","19/02/2022"));

    }
}
