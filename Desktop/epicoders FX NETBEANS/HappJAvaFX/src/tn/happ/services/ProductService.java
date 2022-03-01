package tn.happ.services;


import tn.happ.Model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static tn.happ.services.Session.UserSession.CURRENT_USER;

public class ProductService extends PermissionServices implements ProductInterface {
    static int CurrentUserId;

    public ProductService() {
        CurrentUserId = CURRENT_USER.getUser_LoggedIn().getIdUser();
    }

    @Override
    public void addProduct(Product p) {
        if (isCollaborator(CurrentUserId) && isCateringManager(CurrentUserId)) {
            try {
                String req2 = "insert into product ( RefProduct, SupplierName,  UnitPriceProduct, QuantityProduct)"
                        + "values (?,?,?,?)"; //dynamic insert

                PreparedStatement pst = conn.prepareStatement(req2);
                pst.setString(1, p.getRefProduct()); //index =1 ==> First NAme
                pst.setString(2, p.getSupplierName());
                pst.setFloat(3, p.getUnitPriceProduct());
                pst.setInt(4, p.getQuantityProduct());

                pst.executeUpdate(); //we didn't put executeUpdate(req2) because we put req2 int prepareStatement
                System.out.println("Product addedd successfully");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());

            }
        }
    }

    @Override
    public List<Product> displayAllProduct() {
        List<Product> myList = new ArrayList<>();
        try {
            String rq3 = "Select * from product ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(rq3);
            while (rs.next()) {
                Product p = new Product(); //create Product
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

    @Override
    public void deleteProduct(int IdProduct) {
        if (isCollaborator(CurrentUserId) && isCateringManager(CurrentUserId)) {

            try {
                String sql = "DELETE from product where IdProduct= '" + IdProduct + "' ";
                Statement st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println(" Product DELETED SUCCESSFULLY !");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    @Override
    public void UpdateProduct(Product p) {
        if (isCollaborator(CurrentUserId) && isCateringManager(CurrentUserId)) {

            try {
                String sql = "UPDATE product SET  RefProduct=?, SupplierName=?,UnitPriceProduct=?,QuantityProduct=?  WHERE IdProduct=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, p.getRefProduct());
                pst.setString(2, p.getSupplierName());
                pst.setFloat(3, p.getUnitPriceProduct());
                pst.setFloat(4, p.getQuantityProduct());
                pst.setInt(5, p.getIdProduct());

                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("PRODUCT MODIFIEd!");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

