/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package com.happ.dao;

import com.happ.utils.ConnexionSingleton;
import happ.entity.AssistantChef;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
*/
/**
 *
 * @author ASUS
 */
/* public class AssistantChefDao implements Idao<AssistantChef>{
 private static AssistantChefDao instance;
    private Statement st;
    private ResultSet rs;

    public AssistantChefDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AssistantChefDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static AssistantChefDao getInstance(){
        if(instance==null) 
            instance=new AssistantChefDao();
        return instance;
    }
    
    @Override
    public void insert(AssistantChef o) {
        String req="insert into assistantchef (FirstNameAC,LastNameAC,SexeAC,EmailAC,PhoneNumberAC,Department) values ('"+o.getFirstNameAC()+"','"+o.getDepartment()+o.getLastNameAC()+"','"+o.getSexeAC()+"','"+o.getEmailAC()+"','"+o.getPhoneNumberAC()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(AssistantChefDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void delete(AssistantChef o) {
 String req="delete from assistantchef where IdAC="+o.getIdAC();
        AssistantChef ac=displayById(o.getIdAC());
        
          if(ac!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(AssistantChefDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");    }

    @Override
    public List<AssistantChef> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AssistantChef displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(AssistantChef os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
*/