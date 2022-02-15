/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happ.services;

import com.happ.utils.ConnexionSingleton;
import happ.entity.AssistantChef;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class AssistantChefCRUD {
    Connection cnx2;
    public AssistantChefCRUD()
    {
        cnx2= ConnexionSingleton.getInstance().getCnx();
    }
    ///Static insert
    /*public void addAssistantChef() {
     try {
         String req="insert into assistantchef (FirstNameAC,LastNameAC,SexeAC,EmailAC,PhoneNumberAC,DepartmentAC)"
                 + "values ('Hani','Kik','H','hani.kik@agmail.com','123355','Cold Area')"; //Static Insert
     
           Statement st=  cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Assistant Chef addedd successfully");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        } 
    */
///Dynamic Insert
  public void addAssistantChef(AssistantChef AC) {
        try {
            String req2="insert into assistantchef (FirstNameAC,LastNameAC,SexeAC,EmailAC,PhoneNumberAC,DepartmentAC)"
                 + "values (?,?,?,?,?,?)"; //dynamic insert
        
            PreparedStatement pst =  cnx2.prepareStatement(req2);
            pst.setString(1,AC.getFirstNameAC()); //index =1 ==> First NAme 
            pst.setString(2,AC.getLastNameAC());
            pst.setString(3,AC.getSexeAC());
            pst.setString(4,AC.getEmailAC());
            pst.setString(5,AC.getPhoneNumberAC());
            pst.setString(6,AC.getDepartmentAC());
            pst.executeUpdate(); //we didn't put executeUpdate(req2) because we put req2 int prepareStatement
            System.out.println("Assistant Chef addedd successfully");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
  }
  
  

   

  public List <AssistantChef> displayAllAssistantChef(){
               List <AssistantChef> myList= new ArrayList<>();
    try {
          String rq3 = "Select * from AssistantChef ";
          Statement st= cnx2.createStatement();
          ResultSet rs= st.executeQuery(rq3);
          while(rs.next()){
              AssistantChef ac= new AssistantChef(); //create AssistantChef
              ac.setIdAC(rs.getInt(1)); // Remplir Assistantchef
              ac.setFirstNameAC(rs.getString("FirstNameAc"));
              ac.setLastNameAC(rs.getString("LastNameAC"));
              ac.setSexeAC(rs.getString("SexeAC"));
              ac.setEmailAC(rs.getString("EmailAC"));
              ac.setPhoneNumberAC(rs.getString("PhoneNumberAC"));
              ac.setDepartmentAC(rs.getString("DepartmentAC"));
              myList.add(ac);//insert AssistantChef in My list
     
          }
          
      } catch (SQLException ex) {
            System.err.println(ex.getMessage());
      }
      return myList;
  }

      
        
  /*public boolean UpdateAssistantChef(AssistantChef AC) {
        String qry = "UPDATE assistantchef SET FirstNameAC = '"+AC.getFirstNameAC()+"', LastNameAC = '"+AC.getLastNameAC()+"',SexeAC ='"+AC.getSexeAC()+
                "',EmailAC = '"+AC.getEmailAC()+"',PhoneNumberAC ='"+AC.getPhoneNumberAC()+
                "', DepartmentAC = '"+AC.getDepartmentAC()+"'  WHERE IdAC = "+AC.getIdAC();
        try {
            Statement st= cnx2.createStatement();
            
            if (st.executeUpdate(qry) > 0) {
               System.out.println("Assistant Chef updated successfully");

                return true;
            }
            
        } catch (SQLException ex) {
                        System.err.println(ex.getMessage());

        }
        return false;
    }
  
*/  


  /*public boolean UpdateAssistantChef(AssistantChef AC){
         String sql = "UPDATE assistantchef SET FirstNameAC=?, LastNameAC=?,SexeAC=? ,EmailAC=? ,PhoneNumberAC=? , DepartmentAC=? WHERE IdAC=?";
 try{
PreparedStatement pst = cnx2.prepareStatement(sql);
            pst.setString(1,AC.getFirstNameAC()); //index =1 ==> First NAme 
            pst.setString(2,AC.getLastNameAC());
            pst.setString(3,AC.getSexeAC());
            pst.setString(4,AC.getEmailAC());
            pst.setString(5,AC.getPhoneNumberAC());
            pst.setString(6,AC.getDepartmentAC());
            pst.setInt(7,AC.getIdAC());
            
int rowsUpdated = pst.executeUpdate();
if (rowsUpdated > 0) {
    System.out.println("ASSISTANT CHEF MODIFIED");
}
 } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    
        }
    return false;
     }
  */
  
  public void UpdateAssistantChef(){
        
        try{
            String sql = "UPDATE assistantchef SET FirstNameAC=? WHERE IdAC=?";
                    PreparedStatement pst = cnx2.prepareStatement(sql);
                    pst.setString(1,"aa"); //index =1 ==> First NAme 
             pst.setInt(2,12);
           pst.executeUpdate();
           System.out.println("ASSISTANT CHEF MODIFIED SUCCESSFULLY !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
       public void deleteAssistantChef(int IdAC){
        String sql = "DELETE from assistantchef where IdAC= '"+IdAC+"' "; 
        try{
                    Statement st= cnx2.createStatement();
           st.executeUpdate(sql);
           System.out.println("ASSISTANT CHEF DELETED SUCCESSFULLY !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
       
       /*      
    public void delete(AssistantChef AC) {
        try {
            String req4="delete from assistantchef where IdAC="+AC.getIdAC();
            AssistantChef ac=displayById(AC.getIdAC());
           Statement st=  cnx2.createStatement();
            
             if(ac!=null)
                 {
                    st.executeUpdate(req4);
                System.out.println("Assistant Chef deleted successfully");
                 }
             } catch (SQLException ex) {
                     System.err.println(ex.getMessage());
                                       }
       /* else
        System.out.println("n'existe pas");
        } 
        */
    

    private AssistantChef displayByIdAssistantChef(int IdAC) {
String req5="select * from assistantchef where IdAC ="+IdAC;
           AssistantChef ac =new AssistantChef();
        try {
             Statement st= cnx2.createStatement();
          ResultSet rs= st.executeQuery(req5);
           while(rs.next()){
            //rs.next();
           //if(rs.next()){
                ac.setIdAC(rs.getInt("IdAC")); // Remplir Assistantchef
              ac.setFirstNameAC(rs.getString("FirstNameAc"));
              ac.setLastNameAC(rs.getString("LastNameAC"));
              ac.setSexeAC(rs.getString("SexeAC"));
              ac.setEmailAC(rs.getString("EmailAC"));
              ac.setPhoneNumberAC(rs.getString("PhoneNumberAC"));
              ac.setDepartmentAC(rs.getString("DepartmentAC"));
              System.out.println("HEyyy");
           }  
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return ac;   
    }
  }
  


