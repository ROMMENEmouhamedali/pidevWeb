package com.epicoders.metier;

import com.epicoders.metier.DB_Connection.ConnexionSingleton;
import com.epicoders.metier.Entities.Enumerations.UserRole;
import com.epicoders.metier.Entities.Event;
import com.epicoders.metier.Entities.User;
import com.epicoders.metier.ExceptionHandler.ExceptionSqlError;
import com.epicoders.metier.Services.ServiceUser;
import com.epicoders.metier.Services.ServicesEvent;
import com.epicoders.metier.Utils.PasswordEncoder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Test {

    public static void main(String[] args) throws SQLException, ExceptionSqlError {
        Connection conn = ConnexionSingleton.getConnection();
        ServiceUser serviceUser = new ServiceUser();
        ServicesEvent servicesEvent = new ServicesEvent();
        /*
        User CRUD
         */
     //   serviceUser.addUser(new User("mouhamed ali","rommene","dalyrommene","mouhamedalirommene@hotmail.fr",20202020,UserRole.SIMPLE_USER, PasswordEncoder.encrypt("Dali")));

        serviceUser.addUser(new User("bbbbbb","abhga","agjaghja","aygayga@hotmail.fr",20202020,UserRole.ADMIN, PasswordEncoder.encrypt("ayou")));
        System.out.println("the user has been added");
        /*
        event CRUD
        */
        Date dateNow = Date.valueOf(LocalDate.now());
//        serviceUser.addUser(new User(" ffged","fdfdg","wd","mouhamegbgddalirommene@hotmail.fr",202025020, UserRole.SIMPLE_USER, PasswordEncoder.encrypt("Dali")));
//        servicesEvent.addEvent(new Event("saturday night",120,"every saturday",dateNow));
//        servicesEvent.addEvent(new Event("valentine's day",40,"While you can serve V-Day themed treats at your party." ,dateNow));
//        servicesEvent.addEvent(new Event("last saturday in february",40,"let s finish this month in the right way " ,dateNow));
//
//        servicesEvent.deleteEvent(90);


                User uuu = serviceUser.findUserById(130);
                uuu.setLastName("aaaa");
                serviceUser.updateUser(uuu);
//        serviceUser.authenticate("mouhamedalirommene@hotmail.fr", PasswordEncoder.encrypt("Dali"));

}}
