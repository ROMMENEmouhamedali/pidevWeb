/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package happjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.happ.DB_Connection.ConnexionSingleton;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;


public class HappJAvaFX extends Application {
   Button button;
    Parent parent;
    Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("Happ Application");

       button =new Button("add button");


        try {
            parent = FXMLLoader.load(getClass().getResource("/tn/happ/view/ProductFXML.fxml"));
            Scene scene = new Scene(parent,800,700); //parent is the layout
            stage.setScene(scene);
            stage.show();
            ConnexionSingleton connectNow = new ConnexionSingleton();
            Connection conn=connectNow.getConnection();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }










    }

        
     


    
}
