package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import sample.controllers.LoggedInController;

import java.awt.*;
import java.awt.event.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.DBConnection.getConnection;

public class Tools {


    public static void changeScene(ActionEvent event, String FXMLFile, String title) throws IOException {

        Parent root = null;
        FXMLLoader artistScene = new FXMLLoader(Tools.class.getResource("views/"+FXMLFile));
       root = artistScene.load();
        Scene scene = new Scene(root);

        Stage Sc = new Stage();
        Sc.setScene(scene);
        Sc.setTitle(title);


        Sc.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    public static void goToArticlesScene(MouseEvent event, String FXMLFile, String title, Articles art) throws IOException {

        Parent root = null;
        FXMLLoader artistScene = new FXMLLoader(Tools.class.getResource("views/"+FXMLFile));
        root = artistScene.load();
        Scene scene = new Scene(root);

        Stage Sc = new Stage();
        Sc.setScene(scene);
        Sc.setTitle(title);


        Sc.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }




    public static int getLastID(String requete , String columnLabel){
        int LastID = 0;
        PreparedStatement LastIDget = null;
        ResultSet rs = null;
        Connection connectNow = getConnection();
        try {

            LastIDget = connectNow.prepareStatement(requete);
            rs = LastIDget.executeQuery();
            rs.next();
            LastID = rs.getInt(columnLabel);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return LastID;

    }

    public static void TestRole(Button button){
        if(UserSession.userConnected.getRole()== "Lecteur"){
            button.setVisible(false);
        }
        else{
            button.setVisible(true);
        }
    }


}
