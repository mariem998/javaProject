package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String dataBaseName = "javaproject";
    private final String dataBaseUsename = "root";
    private final String dataBasePassword = "";
    private final String dataBaseUrl = "jdbc:mysql://localhost/";
    private static Connection databaseConnection;


    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(dataBaseUrl + dataBaseName, dataBaseUsename, dataBasePassword);
            System.out.println("Connected !!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in connecting !!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in connecting !!");
        }
    }

    public static Connection getConnection() {
        if (databaseConnection == null) {
            new DBConnection();
        }

        return databaseConnection;
    }





}