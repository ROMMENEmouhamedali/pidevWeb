package tn.happ.view;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tn.happ.DB_Connection.ConnexionSingleton;
import tn.happ.Model.Platter;
import tn.happ.Model.Product;
import tn.happ.Model.SendMail;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductFXMLController implements Initializable {
    @FXML
    private Text output;

    private long number1 = 0;
    private String operator = "";
    private boolean start = true;


    @FXML
    private Button PlayBTN, PauseBTN, ResetBTN;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaView;

    @FXML
    private Label PricePlatterLabel;
    @FXML
    private Button okBTN;
    @FXML
    private TextField textInformation;
    @FXML
    private Button  attachbtn;
    @FXML
    private Label quantityLabel;
    @FXML
    private ImageView logoImage;
    @FXML
    private ImageView imagePlatter;
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
    private Button deleteBtnPlatter;

    @FXML
    private Button deleteBtnProduct;


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
    private Button updateBtnPlatter;

    @FXML
    private Button updateBtnProduct;
    @FXML
    private ComboBox<String> comboTypePlatter;

    @FXML
    private ComboBox<String> comboboxProductPlatter;
    @FXML
    private ListView<String> platterListTS;
    @FXML
    private ListView<String> productListTS;
    @FXML
    private TextArea taInformation;

    ObservableList<Product> productList = GetProductList();
    ObservableList<Platter> platterList = GetPlatterList();

    private int selectedIndex = -1;
    private int selectedIndex2 = -1;
    private int selectedIndex3 = -1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> typeP = FXCollections.observableArrayList("Breakfast", "Lunch", "Dinner");
        comboTypePlatter.setItems(typeP);

        ObservableList<String> listPaella = FXCollections.observableArrayList("Tomato", "onion", "oil","pepper");


        try {
            showListView();
            showListViewPlatterProductTS();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        showTableViewProduct();
        showTableViewPlatter();
        Animation();
        ShowVideoPub();

        //EXCEL

        ConnexionSingleton connectNow = new ConnexionSingleton();
        Connection conn = connectNow.getConnection();

        exportbtn.setOnAction((actionEvent -> {
       exportbtn.setFont(Font.font("Sansserif",15));
            String query = "select * from product";
            try {

                Statement pst = conn.createStatement();
                ResultSet rs = pst.executeQuery(query);

                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("List of Products");
                XSSFRow header = sheet.createRow(0);

                header.createCell(0).setCellValue("RefProduct");
                header.createCell(1).setCellValue("SupplierName");
                header.createCell(2).setCellValue("UnitPriceProduct");
                 header.createCell(3).setCellValue("QuantityProduct");

                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256*25);//256-character width
                sheet.setZoom(150); //scale(150%)

                int index = 1;
                while (rs.next()) {
                    XSSFRow row = sheet.createRow(index);
                    

                    row.createCell(0).setCellValue(rs.getString("RefProduct"));
                    row.createCell(1).setCellValue(rs.getString("SupplierName"));
                    row.createCell(2).setCellValue(rs.getFloat("UnitPriceProduct"));
                    row.createCell(3).setCellValue(rs.getInt("QuantityProduct"));

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
        }));

    }


    public void showListView() throws SQLException {
        String connectQuery = "select * from product";
        String connectQueryPlatter = "select * from platter";


        ConnexionSingleton connectNow = new ConnexionSingleton();
        Connection conn = connectNow.getConnection();


        Statement statement = conn.createStatement();
        Statement statement2 = conn.createStatement();

        ResultSet queryOutput = statement.executeQuery(connectQuery);
        ResultSet queryOutputPlatter = statement2.executeQuery(connectQueryPlatter);

        while ((queryOutput.next()) && (queryOutputPlatter.next())) {

            String RefProd= queryOutput.getString("RefProduct");

            productListView.getItems().add(RefProd);

            String NamePlatter = queryOutputPlatter.getString("NamePlatter");

            platterListView.getItems().add(NamePlatter);

            comboboxProductPlatter.getItems().add(RefProd);
        }
    }
/************************************ Product Management ************************************************************************/

    @FXML
    void insertActionProduct(ActionEvent event) {

        String input1 = textProductRef.getText();
        String input2 = textProductSupp.getText();
        float input3 = Float.parseFloat(textProductPrice.getText());
        int input4 = Integer.valueOf(textProductQuantity.getText());


    try {
        String sql = "insert into product (RefProduct, SupplierName,  UnitPriceProduct, QuantityProduct)"
                + "values (?,?,?,?)";

        ConnexionSingleton connectNow = new ConnexionSingleton();
        Connection conn = connectNow.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        checkInput();

        statement.setString(1, input1);
        statement.setString(2, input2);
        statement.setFloat(3, input3);
        statement.setInt(4, input4);
        int rowInsert = statement.executeUpdate();

        if (rowInsert > 0) {

            JOptionPane.showMessageDialog(null, "Successful Insert");

            productListView.getItems().add(input1);
            textProductRef.clear();
            textProductSupp.clear();
            textProductPrice.clear();
            textProductQuantity.clear();

        } /*else {

            System.out.println("Error Insert");
            quantityLabel.setText("is not a valid integer");*/
        // JOptionPane.showMessageDialog(null,"error exists here");

    //}
    }catch (SQLException ex){
        System.err.println(ex.getMessage());
    }
        }


    @FXML
    void updateActionProduct(ActionEvent event) {

        String input1 = textProductRef.getText();
        String input2 = textProductSupp.getText();
        float input3 = Float.valueOf(textProductPrice.getText());
        int input4 = Integer.valueOf(textProductQuantity.getText());


        String selectedItem = productListView.getSelectionModel().getSelectedItem();

        try {
            String sql = "UPDATE product SET  RefProduct=?, SupplierName=?,UnitPriceProduct=?,QuantityProduct=?  WHERE RefProduct=?";

            ConnexionSingleton connectNow = new ConnexionSingleton();
            Connection conn = connectNow.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, input1);
            statement.setString(2, input2);
            statement.setFloat(3, input3);
            statement.setInt(4, input4);
            statement.setString(5, selectedItem);

            int rowUpdate = statement.executeUpdate();
            if (rowUpdate > 0) {
                JOptionPane.showMessageDialog(null, "Successful Update");
                //Sure mno les 2 lignes ili louta yajoutili 7aja nektbha fl textproductRef
               // String inputId = textIdProduct.getText();
                productListView.getItems().remove(selectedIndex);
                productListView.getItems().add(selectedIndex, input1);
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
        String input = textProductRef.getText();
        String selectedItem = productListView.getSelectionModel().getSelectedItem().toString();
        try {

            String connectQuery = "DELETE FROM product WHERE RefProduct='"+input+"' ";

            ConnexionSingleton connectNow = new ConnexionSingleton();
            Connection conn = connectNow.getConnection();


            Statement st = conn.createStatement();//executerbla requette et ramener la reponse du SGBD
            st.executeUpdate(connectQuery);

            JOptionPane.showMessageDialog(null, "Successful Deleted");

            productListView.getItems().remove(selectedItem);
            textProductRef.clear();

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



    @FXML
    void getClickedItemProduct(MouseEvent event) {
        String selectedItem = productListView.getSelectionModel().getSelectedItem().toString();
        selectedIndex = productListView.getSelectionModel().getSelectedIndex();
       // textIdProduct.setText(selectedItem);
        textProductRef.setText(selectedItem);
    }
    public void showTableViewProduct() {

        //the table column is the one you annotate above
        colRefProduct.setCellValueFactory(new PropertyValueFactory<>("RefProduct"));
        colSuppN.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPriceProduct"));
        colQuantityProd.setCellValueFactory(new PropertyValueFactory<>("QuantityProduct"));
        tvProduct.setItems(productList);
        AdvancedSearch();
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
                }else
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
    void Mailing(ActionEvent event) throws Exception {

        SendMail.sendMail("hanichef1@gmail.com");
    }

    //**********************************************Platter Management*************************************************************
    public void showTableViewPlatter() {

        //the table column is the one you annotate above
        colNamePlatter.setCellValueFactory(new PropertyValueFactory<>("NamePlatter"));
        colImagePlatter.setCellValueFactory(new PropertyValueFactory<>("ImagePlatter"));
        colIngredient.setCellValueFactory(new PropertyValueFactory<>("Ingredient"));
        colPricePlatter.setCellValueFactory(new PropertyValueFactory<>("PricePlatter"));
        colNbPlatter.setCellValueFactory(new PropertyValueFactory<>("NbPlatter"));
        colDescriptionPlatter.setCellValueFactory(new PropertyValueFactory<>("DescriptionPlatter"));
        colTypePlatter.setCellValueFactory(new PropertyValueFactory<>("TypePlatter"));
        tvPlatter.setItems(platterList);

    }
    @FXML
    void insertActionPlatter(ActionEvent event) throws SQLException {

        ConnexionSingleton connectNow = new ConnexionSingleton();
        Connection conn = connectNow.getConnection();
        String input1 = textNamePlatter.getText();

        //insert image in data base
        String image = textIFileName.getText();
        image = image.replace("\\","\\\\");
        String inputImagePlatter= textIFileName.getText();
        String input2= comboboxProductPlatter.getValue();
        float input3 = Float.parseFloat(textPricePlatter.getText());
        int input4 = Integer.valueOf(textNbPlatter.getText());
        String inputDescriptionPlatter=textDescriptionPlatter.getText();
        String input5 =comboTypePlatter.getValue();


        String req = "select * from platter where NAMEPLATTER='" + input1 + "' ";

        ArrayList<String> listOfNames= new ArrayList<>();
        Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                String name = rs.getString("NamePlatter");

                listOfNames.add(name);
            }
                if (listOfNames.size() == 0) {

                    String sql = "insert into platter ( NamePlatter, ImagePlatter, ingredient, PricePlatter, NbPlatter, DescriptionPlatter,TypePlatter) " +
                            "values(?,?,?,?,?,?,?)";


                    PreparedStatement statement = conn.prepareStatement(sql);


                    statement.setString(1, input1);
                    statement.setString(2, inputImagePlatter);
                    statement.setString(3, input2);

                    statement.setFloat(4, input3);
                    statement.setInt(5, input4);
                    statement.setString(6, input5);
                    statement.setString(7, inputDescriptionPlatter);

                    int rowInsert = statement.executeUpdate();
                    if (rowInsert > 0) {
                        JOptionPane.showMessageDialog(null, "PLATTER ADDED SUCCESSFULLY!");

                        platterListView.getItems().add(input1);
                        textNamePlatter.clear();
                        textIFileName.clear();
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

        String input1 = textNamePlatter.getText();
        String inputImagePlatter= textIFileName.getText();
        String input2 = comboboxProductPlatter.getValue();
        float input3 = Float.parseFloat(textPricePlatter.getText());
        int input4 = Integer.valueOf(textNbPlatter.getText());
        String inputDescriptionPlatter=textDescriptionPlatter.getText();
        String input5 =comboTypePlatter.getValue();

        String selectedItemPlatter= platterListView.getSelectionModel().getSelectedItem();

        try {

            String connectQuery ="UPDATE platter SET  NamePlatter=?, ImagePlatter=?,Ingredient=?,PricePlatter=? ,NbPlatter=?,DescriptionPlatter=? ,TypePlatter=?  WHERE NamePlatter=?";
            ;

            ConnexionSingleton connectNow = new ConnexionSingleton();
            Connection conn = connectNow.getConnection();

            PreparedStatement statement = conn.prepareStatement(connectQuery);



            statement.setString(1, input1);
            statement.setString(2, inputImagePlatter);
            statement.setString(3, input2);

            statement.setFloat(4, input3);
            statement.setInt(5, input4);
            statement.setString(6, input5);
            statement.setString(7, inputDescriptionPlatter);


            statement.setString(8, selectedItemPlatter);


            int rowInsert=statement.executeUpdate();

            if(rowInsert>0)
            {

                JOptionPane.showMessageDialog(null,"Platter updated successfully");
                String inputCrudPlatter = textNamePlatter.getText();
                platterListView.getItems().remove(selectedIndex2);
                platterListView.getItems().add(selectedIndex2, inputCrudPlatter);
                textNamePlatter.clear();
                textIFileName.clear();
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
        String input = textNamePlatter.getText();
        String selectedItem2 = platterListView.getSelectionModel().getSelectedItem().toString();
        try {

            String connectQuery = "DELETE FROM platter WHERE NamePlatter ='"+input+"' ";
            ConnexionSingleton connectNow = new ConnexionSingleton();
            Connection conn = connectNow.getConnection();


            Statement st = conn.createStatement();//executerbla requette et ramener la reponse du SGBD
            st.executeUpdate(connectQuery);

            JOptionPane.showMessageDialog(null, "Successful Deleted");

            platterListView.getItems().remove(selectedItem2);
            textNamePlatter.clear();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void getClickedItemPlatter(MouseEvent event) {
        String selectedItem2 = platterListView.getSelectionModel().getSelectedItem().toString();
        selectedIndex2 = platterListView.getSelectionModel().getSelectedIndex();
        textNamePlatter.setText(selectedItem2);
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
          //  System.out.println(path);
           /* ImageIcon ii = new ImageIcon(path);
            Image img = ii.getImage().getScaledInstance(85, 85, java.awt.Image.SCALE_SMOOTH);*/

        }
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
                list.add(p);//insert platter in My list

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
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


    public void checkInput() {
        int input4 = Integer.valueOf(textProductQuantity.getText());
        boolean check1 = Character.isDigit(input4);
        if (check1==false ) {
            quantityLabel.setText("is a valid integer");
        }
        else {
            quantityLabel.setText("is not a valid integer");
        }
    }

   // *********************************TechnicalSheet **********************************************
   public void showListViewPlatterProductTS() throws SQLException {
       String connectQuery = "select * from product";
       String connectQueryPlatter = "select * from platter";

       ConnexionSingleton connectNow = new ConnexionSingleton();
       Connection conn = connectNow.getConnection();


       Statement statement = conn.createStatement();
       Statement statement2 = conn.createStatement();

       ResultSet queryOutput = statement.executeQuery(connectQuery);
       ResultSet queryOutputPlatter = statement2.executeQuery(connectQueryPlatter);


       while ( (queryOutput.next()) && (queryOutputPlatter.next())) {


           String RefProd= queryOutput.getString("RefProduct");

           productListTS.getItems().add(RefProd);

           String Name = queryOutputPlatter.getString("NamePlatter");

           platterListTS.getItems().add(Name);

       }
   }

   void getClickedItemPlatterTS(MouseEvent event) {
       String selectedItem3 = platterListTS.getSelectionModel().getSelectedItem().toString();
       selectedIndex3 = platterListTS.getSelectionModel().getSelectedIndex();
       textInformation.setText(selectedItem3);
   }
    @FXML
    void handleOKbtn(ActionEvent event) {
        String selectedItem=platterListTS.getSelectionModel().getSelectedItem();
        if(selectedItem.equals("Paella") ) {
            taInformation.appendText(selectedItem + "\n");
            ObservableList<String> listPaella = FXCollections.observableArrayList("Tomato", "onion", "oil", "pepper");
            taInformation.appendText(String.valueOf(listPaella)+ "\n");
            PricePlatterLabel.setText(" 20 D");
        }else if (selectedItem.equals("Tortilla"))
            {
                taInformation.appendText(selectedItem +":"+ "\n");
                ObservableList<String> listTortilla = FXCollections.observableArrayList("Flour", "Eggs", "Onion","Tomato");
                taInformation.appendText(String.valueOf(listTortilla)+ "\n");
                PricePlatterLabel.setText(" 15 D");
            }else if (selectedItem.equals("Pasta Espagnola"))
        {
            taInformation.appendText(selectedItem + "\n");
            ObservableList<String> PastaEspagnola = FXCollections.observableArrayList("Spaghetti", "Salt", "Tomato","Onion");
            taInformation.appendText(String.valueOf(PastaEspagnola)+ "\n");
            PricePlatterLabel.setText(" 28 D");
        }else if (selectedItem.equals("PolloFlamenco"))
        {

            taInformation.appendText(selectedItem + "\n");
            ObservableList<String> listPolloFlamenco = FXCollections.observableArrayList("Rice", "Salt", "Olive Oil","Spinach");
            taInformation.appendText(String.valueOf(listPolloFlamenco)+ "\n");
            PricePlatterLabel.setText(" 35 D");

        }

        else
            JOptionPane.showMessageDialog(null, " this platter doesn't belong to our Speciality");


    }

    public void ShowVideoPub()
    {
        file = new File("C:\\Users\\ASUS\\Desktop\\epicoders FX NETBEANS\\HappJAvaFX\\src\\tn\\happ\\video\\pub.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    public void playMedia() {

        mediaPlayer.play();
    }
    public void pauseMedia() {

        mediaPlayer.pause();
    }

    public void resetMedia() {
//if i press to reset and then play the video you7l w yemchi ken sout
        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }



    public long calculate(long number1, long number2, String operator) {
    switch (operator) {
        case "+":
            return number1 + number2;
        case "-":
            return number1 - number2;
        case "*":
            return number1 * number2;
        case "/":
            if (number2 == 0)
                return 0;

            return number1 / number2;
    }

    System.out.println("Unknown operator - " + operator);
    return 0;
}

    @FXML
    private void processNumpad(ActionEvent event) {
        if (start) {
            output.setText("");
            start = false;
        }
        //the value of pressed Button example : 4
        String value = ((Button)event.getSource()).getText();
        //the Value of the previous pressed Button + new value of pressed button : 456
        output.setText(output.getText() + value);
    }

    @FXML
    private void processOperator(ActionEvent event) {
      /*  private long number1 = 0;
        private String operator = "";
        private boolean start = true;*/

        //the value of pressed Button
        String value = ((Button)event.getSource()).getText();

        if (!"=".equals(value)) {
            if (!operator.isEmpty()) // non vide
                return;

            operator = value;
            number1 = Long.parseLong(output.getText()); //nb1 should be long
            output.setText("");
        }
        else { // if("=".equals(value))
            if (operator.isEmpty()) //vide
                return;
            //calculation
            output.setText(String.valueOf(calculate(number1, Long.parseLong(output.getText()), operator)));
            operator = "";
            start = true;
        }
    }
    }




