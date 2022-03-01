package tn.happ.view;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tn.happ.DB_Connection.ConnexionSingleton;
import tn.happ.Model.Platter;
import tn.happ.Model.Product;
import tn.happ.Model.SendMail;
import tn.happ.services.ProductService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductFXMLController implements Initializable {
    @FXML
    private ImageView logoImage;
    @FXML
    private ImageView logoImage1;
    @FXML
    private Button Mailbtn;
    @FXML
    private Button refresh;
    @FXML
    private Button exportbtn;
    @FXML
    private TableColumn<Platter, String> colDescriptionPlatter;

    @FXML
    private TableColumn<Platter, String> colImagePlatter;

    @FXML
    private TableColumn<Platter, String> colIngredient;

    @FXML
    private TableColumn<Platter, String> colNamePlatter;

    @FXML
    private TableColumn<Platter, Integer> colNbPlatter;
    @FXML
    private TableColumn<Platter, Float> colPricePlatter;
    @FXML
    private TableColumn<Platter, String> colTypePlatter;
    @FXML
    private TableColumn<Platter, Integer> colidPlatter;
    @FXML
    private TableColumn<Platter, Integer> colUser;
    @FXML
    private TableColumn<Product, Integer> colQuantityProd;

    @FXML
    private TableColumn<Product, String> colRefProduct;

    @FXML
    private TableColumn<Product, String> colSuppN;

    @FXML
    private TableColumn<Product, Float> colUnitPrice;

    @FXML
    private TableColumn<Product, Integer> colid;
    @FXML
    private TableView<Product> tvProduct;

    @FXML
    private TableView<Platter> tvPlatter;
    @FXML
    private TextField keywordTextField;
    @FXML
    private Button showImage;
    @FXML
    private Button ImpressionBtn;
    @FXML
    private Label IdProductLabel;
    @FXML
    private Label IdPlatterLabel;

    @FXML
    private Label DescriptionPlatterLabel;

    @FXML
    private Label ImagePlatterLabel;

    @FXML
    private Label IngredientsPlatterLabel;

    @FXML
    private Label NamePlatterLabel;

    @FXML
    private Label PlatterPriceLabel;

    @FXML
    private Label PricePlatterLabel1;

    @FXML
    private Label RefProductLabel;

    @FXML
    private Label SupplierNameLabel;

    @FXML
    private Label TypePlatterLabel;

    @FXML
    private Label UnitPriceProductLabel;

    @FXML
    private Label UserLabel;

    @FXML
    private Button deleteBtnPlatter;

    @FXML
    private Button deleteBtnProduct;

    @FXML
    private ImageView imagePlatter;

    @FXML
    private Button importBTN;

    @FXML
    private Button insertBtnPlatter;

    @FXML
    private Button insertBtnProduct;

    @FXML
    private Label nbPlatterLabel;

    @FXML
    private ListView<String> platterListView;

    @FXML
    private ListView<String> productListView;

    @FXML
    private TextField textIFileName;
    @FXML
    private TextField textIdProduct;
    @FXML
    private TextField textIdPlatter;
    @FXML
    private TextField textNbPlatter;
    @FXML
    private TextField textDescriptionPlatter;
    @FXML
    private TextField textIngredient;

    @FXML
    private TextField textNamePlatter;

    @FXML
    private TextField textPlatterCrud;

    @FXML
    private TextField textPricePlatter;

    @FXML
    private TextField textProductCRUD;

    @FXML
    private TextField textProductPrice;

    @FXML
    private TextField textProductQuantity;

    @FXML
    private TextField textProductRef;

    @FXML
    private TextField textProductSupp;

    @FXML
    private TextField textTypePlatter;

    @FXML
    private TextField textUser;

    @FXML
    private Button updateBtnPlatter;

    @FXML
    private Button updateBtnProduct;
    @FXML
    private ComboBox<String> comboTypePlatter;
    @FXML
    private ComboBox<String> comboboxUserPlatter;

    ObservableList<Product> productList = GetProductList();
    ObservableList<Platter> platterList = GetPlatterList();

    private int selectedIndex = -1;
    private int selectedIndex2 = -1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> typeP = FXCollections.observableArrayList("Breakfast", "Lunch", "Dinner");
        comboTypePlatter.setItems(typeP);

        try {
            showListView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showTableViewProduct();
        showTableViewPlatter();
        Animation();
        //exportToExcel = new Button ('Export to excel');

        ConnexionSingleton connectNow = new ConnexionSingleton();
        Connection conn = connectNow.getConnection();
//
        exportbtn.setOnAction((actionEvent -> {
       exportbtn.setFont(Font.font("Sansserif",15));
            String query = "select * from product";
            try {

                Statement pst = conn.createStatement();
                ResultSet rs = pst.executeQuery(query);

//
//
                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("List of Products");
                XSSFRow header = sheet.createRow(0);
                header.createCell(0).setCellValue("IdProduct");
                header.createCell(1).setCellValue("RefProduct");
                header.createCell(2).setCellValue("SupplierName");
                header.createCell(3).setCellValue("UnitPriceProduct");
                 header.createCell(4).setCellValue("QuantityProduct");
//
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256*25);//256-character width
//
                sheet.setZoom(150); //scale(150%)
//



                int index = 1;
                while (rs.next()) {
                    XSSFRow row = sheet.createRow(index);
                    
                    row.createCell(0).setCellValue(rs.getInt("IdProduct"));
                    row.createCell(1).setCellValue(rs.getString("RefProduct"));
                    row.createCell(2).setCellValue(rs.getString("SupplierName"));
                    row.createCell(3).setCellValue(rs.getFloat("UnitPriceProduct"));
                    row.createCell(4).setCellValue(rs.getInt("QuantityProduct"));

                    index++;

                }
                FileOutputStream fileOut = new FileOutputStream ("ListOfProducts.xlsx");
                wb.write(fileOut);
                fileOut.close();
//
//

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());

            } catch (IOException ex) {
                System.err.println(ex.getMessage());

            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Products details exported to excel Sheet");
            alert.show();
            //pst.close();
            //rs.close();

        }));
    }
    @FXML
    void selectUserPlatter(ActionEvent event) {

    }

    public void showListView() throws SQLException {
        String connectQuery = "select * from product";
        String connectQueryPlatter = "select * from platter";
        String connectUserPlatter = "select * from user";

        ConnexionSingleton connectNow = new ConnexionSingleton();
        Connection conn = connectNow.getConnection();


        Statement statement = conn.createStatement();
        Statement statement2 = conn.createStatement();
        Statement statement3 = conn.createStatement();

        ResultSet queryOutput = statement.executeQuery(connectQuery);
        ResultSet queryOutputPlatter = statement2.executeQuery(connectQueryPlatter);
        ResultSet queryOutputUserPlatter =statement3.executeQuery(connectUserPlatter);

        while ((queryOutput.next()) && (queryOutputPlatter.next())&&(queryOutputUserPlatter.next())) {

            Integer id = queryOutput.getInt("IdProduct");

            productListView.getItems().add(String.valueOf((id)));

            Integer IdPlatter = queryOutputPlatter.getInt("IdPlatter");

            platterListView.getItems().add(String.valueOf(IdPlatter));

            Integer idUser=queryOutputUserPlatter.getInt("ID_USER");
            String nameUser=queryOutputUserPlatter.getString("USERNAME");

            ArrayList<String> listUsers= new ArrayList<>() ;
            listUsers.add(nameUser);

            ObservableList<String> listUsersReservation= FXCollections.observableList(listUsers);

            comboboxUserPlatter.getItems().add(String.valueOf(idUser));


        }
    }


    @FXML
    void insertActionProduct(ActionEvent event) {
        String input1 = textProductRef.getText();
        String input2 = textProductSupp.getText();
        float input3 = Float.parseFloat(textProductPrice.getText());
        int input4 = Integer.valueOf(textProductQuantity.getText());
        int inputId = Integer.valueOf(textIdProduct.getText());
        try {
            String sql = "insert into product ( IdProduct,RefProduct, SupplierName,  UnitPriceProduct, QuantityProduct)"
                    + "values (?,?,?,?,?)";

            ConnexionSingleton connectNow = new ConnexionSingleton();
            Connection conn = connectNow.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, inputId);
            statement.setString(2, input1);
            statement.setString(3, input2);
            statement.setFloat(4, input3);
            statement.setInt(5, input4);
            int rowInsert = statement.executeUpdate();
            if (rowInsert > 0) {
                JOptionPane.showMessageDialog(null, "Successful Insert");

                productListView.getItems().add(String.valueOf(inputId));
                textIdProduct.clear();
                textProductRef.clear();
                textProductSupp.clear();
                textProductPrice.clear();
                textProductQuantity.clear();
            } else {
                System.out.println("Error Insert");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }


    @FXML
    void updateActionProduct(ActionEvent event) {

        String input1 = textProductRef.getText();
        String input2 = textProductSupp.getText();
        float input3 = Float.valueOf(textProductPrice.getText());
        int input4 = Integer.valueOf(textProductQuantity.getText());
        //int input5=Integer.valueOf(textIdProduct);
        String inputid = textIdProduct.getText();

        Integer selectedItem = Integer.valueOf(productListView.getSelectionModel().getSelectedItem());

        try {
            String sql = "UPDATE product SET  RefProduct=?, SupplierName=?,UnitPriceProduct=?,QuantityProduct=?  WHERE IdProduct=?";

            ConnexionSingleton connectNow = new ConnexionSingleton();
            Connection conn = connectNow.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, input1);
            statement.setString(2, input2);
            statement.setFloat(3, input3);
            statement.setInt(4, input4);
            statement.setInt(5, selectedItem);

            int rowUpdate = statement.executeUpdate();
            if (rowUpdate > 0) {
                JOptionPane.showMessageDialog(null, "Successful Update");
                //Sure mno les 2 lignes ili louta yajoutili 7aja nektbha fl textproductRef
                String inputId = textIdProduct.getText();
                productListView.getItems().remove(selectedIndex);
                productListView.getItems().add(selectedIndex, inputId);
                textIdProduct.clear();
                textProductRef.clear();
                textProductSupp.clear();
                textProductPrice.clear();
                textProductQuantity.clear();
            } else {
                System.out.println("Error Update");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }


    @FXML
    void deleteActionProduct(ActionEvent event) {
        String input = textIdProduct.getText();
        String selectedItem = productListView.getSelectionModel().getSelectedItem().toString();
        try {

            String connectQuery = "DELETE FROM product WHERE IdProduct=" + input + " ";

            ConnexionSingleton connectNow = new ConnexionSingleton();
            Connection conn = connectNow.getConnection();


            Statement st = conn.createStatement();//executerbla requette et ramener la reponse du SGBD
            st.executeUpdate(connectQuery);

            JOptionPane.showMessageDialog(null, "Successful Deleted");

            productListView.getItems().remove(selectedItem);
            textIdProduct.clear();
            //   statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Product> GetProductList() {
        ConnexionSingleton connectNow = new ConnexionSingleton();
        Connection conn = connectNow.getConnection();

        String req = "select * from product  ";
        ObservableList<Product> list = FXCollections.observableArrayList();
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                //rs.next();
                Product p = new Product();
                p.setIdProduct(rs.getInt("IdProduct"));
                p.setRefProduct(rs.getString("RefProduct")); // Remplir Platter
                p.setSupplierName(rs.getString("SupplierName"));
                p.setUnitPriceProduct(rs.getFloat("UnitPriceProduct"));
                p.setQuantityProduct(rs.getInt("QuantityProduct"));
                list.add(p);//insert platter in My list

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public ObservableList<Platter> GetPlatterList() {
        ConnexionSingleton connectNow = new ConnexionSingleton();
        Connection conn = connectNow.getConnection();

        String req = "select * from platter  ";
        ObservableList<Platter> list = FXCollections.observableArrayList();
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                Platter p = new Platter();
                p.setIdPlatter(rs.getInt(1)); // Remplir Platter
                p.setNamePlatter(rs.getString("NamePlatter"));
                p.setImagePlatter(rs.getString("ImagePlatter"));
                p.setIngredient(rs.getString("Ingredient"));
                p.setPricePlatter(rs.getFloat("PricePlatter"));
                p.setNbPlatter(rs.getInt("NbPlatter"));
                p.setDescriptionPlatter(rs.getString("DescriptionPlatter"));
                p.setTypePlatter(rs.getString("TypePlatter"));
            //    p.setUser(rs.getInt("ID_USER"));
                list.add(p);//insert platter in My list

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @FXML
    void getClickedItemProduct(MouseEvent event) {
        String selectedItem = productListView.getSelectionModel().getSelectedItem().toString();
        selectedIndex = productListView.getSelectionModel().getSelectedIndex();
        textIdProduct.setText(selectedItem);

    }

    //**********************************************Platter Management*************************************************************

    @FXML
    void insertActionPlatter(ActionEvent event) throws SQLException {

        ConnexionSingleton connectNow = new ConnexionSingleton();
        Connection conn = connectNow.getConnection();
        Integer inputPlatterId= Integer.valueOf(textIdPlatter.getText());
        String input1 = textNamePlatter.getText();
        //insert image in data base
        String image = textIFileName.getText();
        image = image.replace("\\","\\\\");
        String inputImagePlatter= textIFileName.getText();
        String input2 = textIngredient.getText();
        float input3 = Float.parseFloat(textPricePlatter.getText());
        int input4 = Integer.valueOf(textNbPlatter.getText());
        String inputDescriptionPlatter=textDescriptionPlatter.getText();
        String input5 =comboTypePlatter.getValue();
        Integer inputUserPlatterID= Integer.valueOf(comboboxUserPlatter.getValue());

        int inputId = Integer.valueOf(textIdPlatter.getText());
        String req = "select * from platter where NAMEPLATTER='" + input1 + "' ";
        // st = null;
        ArrayList<String> listOfNames= new ArrayList<>();
        Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                String name = rs.getString("NamePlatter");

                listOfNames.add(name);
            }
                if (listOfNames.size() == 0) {

                    String sql = "insert into platter ( IdPlatter,NamePlatter, ImagePlatter, ingredient, PricePlatter, NbPlatter, DescriptionPlatter,TypePlatter,fkUserPlatter) " +
                            "values(?,?,?,?,?,?,?,?,?)";


                    PreparedStatement statement = conn.prepareStatement(sql);

                    statement.setInt(1, inputPlatterId);
                    statement.setString(2, input1);
                    statement.setString(3, inputImagePlatter);
                    statement.setString(4, input2);

                    statement.setFloat(5, input3);
                    statement.setInt(6, input4);
                    statement.setString(7, input5);
                    statement.setString(8, inputDescriptionPlatter);
                    statement.setInt(9, inputUserPlatterID);

                    int rowInsert = statement.executeUpdate();
                    if (rowInsert > 0) {
                        JOptionPane.showMessageDialog(null, "PLATTER ADDED SUCCESSFULLY!");

                        platterListView.getItems().add(String.valueOf(inputPlatterId));
                        textIdPlatter.clear();
                        textNamePlatter.clear();
                        textIFileName.clear();
                        textIngredient.clear();
                        textPricePlatter.clear();
                        textNbPlatter.clear();
                        textDescriptionPlatter.clear();
                        
                    }

                }
        else
        JOptionPane.showMessageDialog(null,"U can not add Platter because it exists");


    }









    @FXML
    void updateActionPlatter(ActionEvent event) {

        // Integer inputReservationId= Integer.valueOf(reservationIdfield.getText());
        Integer inputPlatterId= Integer.valueOf(textIdPlatter.getText());
        String input1 = textNamePlatter.getText();
        String inputImagePlatter= textIFileName.getText();
        String input2 = textIngredient.getText();
        float input3 = Float.parseFloat(textPricePlatter.getText());
        int input4 = Integer.valueOf(textNbPlatter.getText());
        String inputDescriptionPlatter=textDescriptionPlatter.getText();
        String input5 =comboTypePlatter.getValue();
        Integer inputUserPlatterID= Integer.valueOf(comboboxUserPlatter.getValue());

        Integer selectedItemPlatter= Integer.valueOf(platterListView.getSelectionModel().getSelectedItem());

        try {

            String connectQuery ="UPDATE platter SET  NamePlatter=?, ImagePlatter=?,Ingredient=?,PricePlatter=? ,NbPlatter=?,DescriptionPlatter=? ,TypePlatter=?,fkUserPlatter=?  WHERE IdPlatter=?";
            ;

            ConnexionSingleton connectNow = new ConnexionSingleton();
            Connection conn = connectNow.getConnection();

            PreparedStatement statement = conn.prepareStatement(connectQuery);


          //  statement.setInt(1, inputPlatterId);
            statement.setString(1, input1);
            statement.setString(2, inputImagePlatter);
            statement.setString(3, input2);

            statement.setFloat(4, input3);
            statement.setInt(5, input4);
            statement.setString(6, input5);
            statement.setString(7, inputDescriptionPlatter);
            statement.setInt(8, inputUserPlatterID);


            statement.setInt(9, selectedItemPlatter);


            int rowInsert=statement.executeUpdate();

            if(rowInsert>0)
            {

                JOptionPane.showMessageDialog(null,"Platter updated successfully");
                String inputCrudPlatter = textIdPlatter.getText();
                platterListView.getItems().remove(selectedIndex2);
                platterListView.getItems().add(selectedIndex2, inputCrudPlatter);
                textIdPlatter.clear();
                textNamePlatter.clear();
                textIFileName.clear();
                textIngredient.clear();
                textPricePlatter.clear();
                textNbPlatter.clear();
                textDescriptionPlatter.clear();



                statement.close();

            }
            else{
                System.out.println("Error Insert");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteActionPlatter(ActionEvent event) {
        String input = textIdPlatter.getText();
        String selectedItem2 = platterListView.getSelectionModel().getSelectedItem().toString();
        try {

            String connectQuery = "DELETE FROM platter WHERE IdPlatter=" + input + " ";

            ConnexionSingleton connectNow = new ConnexionSingleton();
            Connection conn = connectNow.getConnection();


            Statement st = conn.createStatement();//executerbla requette et ramener la reponse du SGBD
            st.executeUpdate(connectQuery);

            JOptionPane.showMessageDialog(null, "Successful Deleted");

            platterListView.getItems().remove(selectedItem2);
            textIdPlatter.clear();
            textNamePlatter.clear();
            textIFileName.clear();
            textIngredient.clear();
            textPricePlatter.clear();
            textNbPlatter.clear();
            textDescriptionPlatter.clear();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void getClickedItemPlatter(MouseEvent event) {
        String selectedItem2 = platterListView.getSelectionModel().getSelectedItem().toString();
        selectedIndex2 = platterListView.getSelectionModel().getSelectedIndex();
        textIdPlatter.setText(selectedItem2);
    }

    @FXML
    void importBTNFct(ActionEvent event) {

        JFileChooser filechooser = new JFileChooser();
        FileNameExtensionFilter fnwf = new FileNameExtensionFilter("PNG JPG AND JPEG", "png", "jpeg", "jpg");
        filechooser.addChoosableFileFilter(fnwf);
        int load = filechooser.showOpenDialog(null);
        if (load == filechooser.APPROVE_OPTION) {
            File f = filechooser.getSelectedFile();
            String path = f.getAbsolutePath();
            textIFileName.setText(path);
            ImageIcon ii = new ImageIcon(path);
            Image img = ii.getImage().getScaledInstance(85, 85, java.awt.Image.SCALE_SMOOTH);
            JLabel imagePlatter = new JLabel("");
            imagePlatter.setIcon(new ImageIcon(img));

        }

    }

    @FXML
    void showImage(ActionEvent event) {
        String image = textIFileName.getText();
        image = image.replace("\\", "\\\\");
    }


    public void showTableViewProduct() {

        //the table column is the one you annotate above
        colid.setCellValueFactory(new PropertyValueFactory<>("IdProduct"));
        colRefProduct.setCellValueFactory(new PropertyValueFactory<>("RefProduct"));
        colSuppN.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPriceProduct"));
        colQuantityProd.setCellValueFactory(new PropertyValueFactory<>("QuantityProduct"));
        tvProduct.setItems(productList);
        AdvancedSearch();
    }
    public void showTableViewPlatter() {

        //the table column is the one you annotate above
        colidPlatter.setCellValueFactory(new PropertyValueFactory<>("IdPlatter"));
        colNamePlatter.setCellValueFactory(new PropertyValueFactory<>("NamePlatter"));
        colImagePlatter.setCellValueFactory(new PropertyValueFactory<>("ImagePlatter"));
        colIngredient.setCellValueFactory(new PropertyValueFactory<>("Ingredient"));
        colPricePlatter.setCellValueFactory(new PropertyValueFactory<>("PricePlatter"));
        colNbPlatter.setCellValueFactory(new PropertyValueFactory<>("NbPlatter"));
        colDescriptionPlatter.setCellValueFactory(new PropertyValueFactory<>("DescriptionPlatter"));
        colTypePlatter.setCellValueFactory(new PropertyValueFactory<>("TypePlatter"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("ID_USER"));
        tvPlatter.setItems(platterList);

    }

    public void AdvancedSearch() {//initial filtered List
        FilteredList<Product> filteredData = new FilteredList<>(productList);

        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Product -> {
//if no search value then display all recoras or whatever records it current have no changes
                if (newValue.isEmpty()  || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (Product.getRefProduct().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Means we found a match in ProductRef
                } else if (Product.getSupplierName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true; // Means we found a match in SupplierName
                } else if (Product.getUnitPriceProduct().toString().indexOf(searchKeyword) > -1) {
                    return true; // Means we found a match in PriceProduct
                }/* else if(Product.getQuantityProduct().toString().indexOf(searchKeyword)  > -1){
                return true;
            }*/ else
                    return false;//no match found
            });
        });
        SortedList<Product> sortedData = new SortedList<>(filteredData);
        //Bind sorted result with table view
        sortedData.comparatorProperty().bind(tvProduct.comparatorProperty());
        //Apply filtered and sorted data to the table View
        tvProduct.setItems(sortedData);
    }

    @FXML
    void Impression(ActionEvent event) {
      /*  MessageFormat header = new MessageFormat("Product List");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try{
            // tvProduct.print(JTable.PrintMode.NORMAL,header,footer);
         //  TvProduct=tvProduct
            TvProduct.print(JTable.PrintMode.NORMAL,header,footer);
        }catch(java.awt.print.PrinterException e){
            System.err.format("Error of Impression",e.getMessage());*/
        }
    @FXML
    void Mailing(ActionEvent event) throws Exception {

        SendMail.sendMail("hanichef1@gmail.com");
    }

    public void Animation()
    {
        // Traduire
        TranslateTransition translate = new TranslateTransition ();
        translate.setNode (logoImage);
        translate.setDuration (Duration.millis (3000));
        translate.setCycleCount (TranslateTransition.INDEFINITE);
        translate.setByX (50);
        translate.setByY (-10);
        translate.setAutoReverse (true);
        translate.play ();
        // image 2
        TranslateTransition translate1 = new TranslateTransition ();
        translate1.setNode (logoImage1);
        translate1.setDuration (Duration.millis (3000));
        translate1.setCycleCount (TranslateTransition.INDEFINITE);
        translate1.setByX (50);
        translate1.setByY (-10);
        translate1.setAutoReverse (true);
        translate1.play ();
    }

    @FXML
    void refresh(ActionEvent event) {
        ProductService productService= new ProductService();
        tvProduct.setItems((ObservableList<Product>) productService.displayAllProduct());
    }
    

    }




