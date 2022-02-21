/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happ.services;

import com.happ.utils.ConnexionSingleton;
import happ.entity.Product;
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
public class ProductServices {
    Connection cnx2;
    public ProductServices()
    {
        cnx2= ConnexionSingleton.getInstance().getCnx();
    }
   
///Dynamic Insert
  public void addProduct(Product p) {
        try {
            String req2="insert into product ( RefProduct, SupplierName,  UnitPriceProduct, QuantityProduct)"
                 + "values (?,?,?,?)"; //dynamic insert
        
            PreparedStatement pst =  cnx2.prepareStatement(req2);
            pst.setString(1,p.getRefProduct()); //index =1 ==> First NAme 
            pst.setString(2,p.getSupplierName());
            pst.setFloat(3,p.getUnitPriceProduct());
            pst.setInt(4,p.getQuantityProduct());
            
            pst.executeUpdate(); //we didn't put executeUpdate(req2) because we put req2 int prepareStatement
            System.out.println("Product addedd successfully");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
  }
  

  public List <Product> displayAllProduct(){
               List <Product> myList= new ArrayList<>();
    try {
          String rq3 = "Select * from product ";
          Statement st= cnx2.createStatement();
          ResultSet rs= st.executeQuery(rq3);
          while(rs.next()){
              Product p= new Product(); //create Product
              p.setIdProduct(rs.getInt(1)); // Remplir Product
              p.setRefProduct(rs.getString("RefProduct"));
              p.setSupplierName(rs.getString("SupplierName"));
              p.setUnitPriceProduct(rs.getFloat("UnitPriceProduct"));
              p.setQuantityProduct(rs.getInt("QuantityProduct"));
              myList.add(p);//insert Product in My list
     
          }
          
      } catch (SQLException ex) {
            System.err.println(ex.getMessage());
      }
      return myList;
  }

      //IdProduct, String RefProduct, String SupplierName, Float UnitPriceProduct, int QuantityProduct
        
 
  
   public void UpdateProduct(Product p){
         String sql = "UPDATE product SET  RefProduct=?, SupplierName=?,UnitPriceProduct=?,QuantityProduct=?  WHERE IdProduct=?";
 try{
PreparedStatement pst = cnx2.prepareStatement(sql);
pst.setString(1, p.getRefProduct());
pst.setString(2, p.getSupplierName());
pst.setFloat(3,p.getUnitPriceProduct());
pst.setFloat(4,p.getQuantityProduct());
pst.setInt(5,p.getIdProduct());
 
int rowsUpdated = pst.executeUpdate();
if (rowsUpdated > 0) {
    System.out.println("PRODUCT MODIFIEd!");
}
 } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 
     }


       public void deleteProduct(int IdProduct){
        String sql = "DELETE from product where IdProduct= '"+IdProduct+"' "; 
        try{
                    Statement st= cnx2.createStatement();
           st.executeUpdate(sql);
           System.out.println(" Product DELETED SUCCESSFULLY !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

   /*
        public void deleteProduct(Product p) {
        try {
            String req4="delete from product where IdProduct="+p.getIdProduct();
            Product prod=displayByIdProduct(p.getIdProduct());
           Statement st=  cnx2.createStatement();
            
             if(prod!=null)
                 {
                    st.executeUpdate(req4);
                System.out.println("Product deleted successfully");
                 }
             } catch (SQLException ex) {
                     System.err.println(ex.getMessage());
      /*                                 }
        else
        System.out.println("n'existe pas");
        } 
       
     
    

    private Product displayByIdProduct(int IdProduct) {
String req5="select * from product where IdProduct ="+IdProduct;
           Product p =new Product();
        try {
             Statement st= cnx2.createStatement();
          ResultSet rs= st.executeQuery(req5);
           while(rs.next()){
            //rs.next();
           //if(rs.next()){
                p.setIdProduct(rs.getInt("IdProduct")); // Remplir Product
              p.setRefProduct(rs.getString("RefProduct"));
              p.setSupplierName(rs.getString("SupplierName"));
              p.setUnitPriceProduct(rs.getFloat("UnitPriceProduct"));
              p.setQuantityProduct(rs.getInt("QuantityProduct"));
              System.out.println("HEyyy");
           }  
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    return p;   
    }
    */
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
        else
        System.out.println("n'existe pas");
        } 
        
*/