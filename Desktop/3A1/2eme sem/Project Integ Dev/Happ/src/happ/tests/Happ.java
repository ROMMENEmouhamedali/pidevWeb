package happ.tests;


import com.happ.services.AssistantChefCRUD;
import com.happ.utils.ConnexionSingleton;
import happ.entity.AssistantChef;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ASUS
 */
public class Happ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       ConnexionSingleton cs = ConnexionSingleton.getInstance();
               ConnexionSingleton cs2 = ConnexionSingleton.getInstance();

       // System.out.println(cs.hashCode()+"-"+cs2.hashCode()); //cs & cs2 have the same hashcode so i have only 1 instance of my class ConnexionSingleton in memory
       AssistantChefCRUD ac=new AssistantChefCRUD();
              AssistantChef ac2=new AssistantChef("Ahlem","ZAgh","F","ahlem@gmmail.com","4566","hot area");
              AssistantChef ac3=new AssistantChef("Haifa","Toukebri","F","Haifa@gmmail.com","66","cold area");
              AssistantChef ac4=new AssistantChef("Samira","Chebbi","F","Samira@gmmail.com","789","Hot area");
               //AssistantChef ac5=new AssistantChef("bo","to","F","bo@gmmail.com","7899","Hot area");

        //   ac.addAssistantChef(); // static Insert
        
         //  ac.addAssistantChef2(ac2);//*****************Dynamic Insert********************************
      //   ac.addAssistantChef(ac4);
      
           System.out.println(ac.displayAllAssistantChef());//*************Display Assistant Chef******************
           
         // ac.deleteAssistantChef(9);/***************Delee Assistant Chef*********************
           ac.UpdateAssistantChef(); //*********Update assistant CHef **************************
            
    }
    
}
