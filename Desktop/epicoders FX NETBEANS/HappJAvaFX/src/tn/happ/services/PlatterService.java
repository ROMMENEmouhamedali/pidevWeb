package tn.happ.services;

import tn.happ.Model.Platter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static tn.happ.services.Session.UserSession.CURRENT_USER;

public class PlatterService extends PermissionServices implements PlatterInterface {
    static int CurrentUserId;

    public PlatterService() {
        CurrentUserId = CURRENT_USER.getUser_LoggedIn().getIdUser();
    }

    @Override
    public void addPlatter(Platter p) {
        if (isCollaborator(CurrentUserId) && isAssistantChef(CurrentUserId)) {
            List<Platter> platter = displayByName(p.getNamePlatter());
            // System.out.println(platter.size());
            if (platter.size() == 0) {
                try {
                    ps = conn.prepareStatement
                            ("insert into platter(NamePlatter, ImagePlatter, ingredient, PricePlatter, NbPlatter, DescriptionPlatter,TypePlatter,ID_USER) " +
                                    "values(?,?,?,?,?,?,?,?)");
                    ps.setString(1, p.getNamePlatter()); //index =1 ==> First NAme
                    ps.setString(2, p.getImagePlatter());
                    ps.setString(3, p.getIngredient());
                    ps.setFloat(4, p.getPricePlatter());
                    ps.setInt(5, p.getNbPlatter());
                    ps.setString(6, p.getDescriptionPlatter());
                    ps.setString(7, p.getTypePlatter());
                 //   ps.setInt(8, p.getIdUser());
                    ps.executeUpdate();
                    System.out.println("Platter addedd successfully");

                    ps.close();

                } catch (SQLException es) {
                    es.printStackTrace();
                }
            } else System.out.println("you can not add platter");
        }
    }


    @Override
    public List<Platter> displayAllPlatter() {
        List<Platter> myList = new ArrayList<>();
        try {
            String rq3 = "Select * from Platter ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(rq3);
            while (rs.next()) {
                Platter p = new Platter(); //create Platter
                p.setIdPlatter(rs.getInt(1)); // Remplir Platter
                p.setNamePlatter(rs.getString("NamePlatter"));
                p.setImagePlatter(rs.getString("ImagePlatter"));
                p.setIngredient(rs.getString("Ingredient"));
                p.setPricePlatter(rs.getFloat("PricePlatter"));
                p.setNbPlatter(rs.getInt("NbPlatter"));
                p.setDescriptionPlatter(rs.getString("DescriptionPlatter"));
                p.setTypePlatter(rs.getString("TypePlatter"));
             //   p.setIdUser(rs.getInt("ID_USER"));
                myList.add(p);//insert platter in My list

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void deletePlatter(Platter p) {
        if (isCollaborator(CurrentUserId) && isAssistantChef(CurrentUserId)) {
            String sql = "DELETE from platter where IdPlatter= " + p.getIdPlatter();
            Platter platter = displayByID(p.getIdPlatter());
            if (platter != null)
                try {
                    Statement st = conn.createStatement();
                    st.executeUpdate(sql);
                    System.out.println("Platter DELETED SUCCESSFULLY !");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            else System.out.println("you can not delete :platter doesn't exist");

        }
    }

    @Override
    public void UpdatePlatter(Platter p) {
        if (isCollaborator(CurrentUserId) && isAssistantChef(CurrentUserId)) {
            String sql = "UPDATE platter SET  NamePlatter=?, ImagePlatter=?,Ingredient=?,PricePlatter=? ,NbPlatter=?,DescriptionPlatter=? ,TypePlatter=?  WHERE IdPlatter=?";
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, p.getNamePlatter());
                pst.setString(2, p.getImagePlatter());
                pst.setString(3, p.getIngredient());
                pst.setFloat(4, p.getPricePlatter());
                pst.setInt(5, p.getNbPlatter());
                pst.setString(6, p.getDescriptionPlatter());
                pst.setString(7, p.getTypePlatter());
                pst.setInt(8, p.getIdPlatter());
                //    pst.setInt(9, p.getCol());

                int rowsUpdated = pst.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("platter MODIFIEd!");
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    public List<Platter> displayByName(String NamePlatter) {
        List<Platter> myList = new ArrayList<>();

        String req = "select * from platter where NAMEPLATTER='" + NamePlatter + "' ";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                //rs.next();
                Platter p = new Platter();
                p.setIdPlatter(rs.getInt("IdPlatter")); // Remplir Platter
                p.setNamePlatter(rs.getString("NamePlatter"));
                p.setImagePlatter(rs.getString("ImagePlatter"));
                p.setIngredient(rs.getString("Ingredient"));
                p.setPricePlatter(rs.getFloat("PricePlatter"));
                p.setNbPlatter(rs.getInt("NbPlatter"));
                p.setDescriptionPlatter(rs.getString("DescriptionPlatter"));
                p.setTypePlatter(rs.getString("TypePlatter"));
               // p.setUser(ServiceUser.g//rs.getInt("ID_USER"));
                myList.add(p);//insert platter in My list

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public Platter displayByID(int IdPlatter) {
        String req = "select * from platter where IdPlatter='" + IdPlatter + "' ";
        Platter p = new Platter();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                //rs.next();
                p.setIdPlatter(rs.getInt("IdPlatter")); // Remplir Platter
                p.setNamePlatter(rs.getString("NamePlatter"));
                p.setImagePlatter(rs.getString("ImagePlatter"));
                p.setIngredient(rs.getString("Ingredient"));
                p.setPricePlatter(rs.getFloat("PricePlatter"));
                p.setNbPlatter(rs.getInt("NbPlatter"));
                p.setDescriptionPlatter(rs.getString("DescriptionPlatter"));
                p.setTypePlatter(rs.getString("TypePlatter"));
               // p.setIdUser(rs.getInt("ID_USER"));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return p;
    }

    public List<Platter> search(int rech) {
        List<Platter> myList = new ArrayList<>();
        String req="select * from platter where NbPlatter like '%"+rech+"%' ";
        //String req = "select * from platter where NbPlatter like '%" + 5 + "%' ";
        // String req="select * from platter where NbPlatter like CONCAT('%',?,'%')";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                //rs.next();
                Platter p = new Platter();
                p.setIdPlatter(rs.getInt("IdPlatter")); // Remplir Platter
                p.setNamePlatter(rs.getString("NamePlatter"));
                p.setImagePlatter(rs.getString("ImagePlatter"));
                p.setIngredient(rs.getString("Ingredient"));
                p.setPricePlatter(rs.getFloat("PricePlatter"));
                p.setNbPlatter(rs.getInt("NbPlatter"));
                p.setDescriptionPlatter(rs.getString("DescriptionPlatter"));
                p.setTypePlatter(rs.getString("TypePlatter"));
             //   p.setIdUser(rs.getInt("ID_USER"));
                myList.add(p);//insert platter in My list

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public  Platter OrderByNbPlatter()
    {
        String req="SELECT * FROM `platter` ORDER By NBPLATTER ASC;";
        //  SELECT * FROM `platter` ORDER By NBPLATTER DESC;

        Platter p = new Platter();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                //rs.next();
                p.setIdPlatter(rs.getInt("IdPlatter")); // Remplir Platter
                p.setNamePlatter(rs.getString("NamePlatter"));
                p.setImagePlatter(rs.getString("ImagePlatter"));
                p.setIngredient(rs.getString("Ingredient"));
                p.setPricePlatter(rs.getFloat("PricePlatter"));
                p.setNbPlatter(rs.getInt("NbPlatter"));
                p.setDescriptionPlatter(rs.getString("DescriptionPlatter"));
                p.setTypePlatter(rs.getString("TypePlatter"));
              //  p.setIdUser(rs.getInt("ID_USER"));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return p;
    }
}


