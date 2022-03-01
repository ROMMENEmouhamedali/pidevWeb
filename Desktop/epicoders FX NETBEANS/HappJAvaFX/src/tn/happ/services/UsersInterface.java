package tn.happ.services;
/*
import com.example.happfxfinal.Model.Collaborator;
import com.example.happfxfinal.Model.User;
import com.example.happfxfinal.ExceptionHandler.ExceptionSqlError;
*/
import tn.happ.ExceptionHandler.ExceptionSqlError;
import tn.happ.Model.Collaborator;
import tn.happ.Model.User;

import java.sql.SQLException;


public interface UsersInterface {

    public void addUser (User u);
    public void deleteUser(User u) throws SQLException, ExceptionSqlError;
    public void updateUser(User u);

    public void addCollaborator (Collaborator c);
    public void deleteCollaborator(int id) throws SQLException, ExceptionSqlError;
    public void updateCollaborator(Collaborator c);



}
