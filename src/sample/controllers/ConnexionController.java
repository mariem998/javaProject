package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ConnexionController {

    public static User userLogedIn;

    @FXML
    private TextField cnx_email;

    @FXML
    private Label cnx_error;

    @FXML
    private Label cnx_error_passwd;

    @FXML
    private Label logged_user;

    @FXML
    private Button cnx_inscri;

    @FXML
    private PasswordField cnx_passwd;

    @FXML
    private Button cnx_submit;

    private Set<String> privileges;


    public void goToInscriptionScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent, "inscription.fxml", "inscription");
    }


    public void LoginButtonOnAction(ActionEvent actionEvent) {
        if (cnx_email.getText().trim().isEmpty() || cnx_passwd.getText().trim().isEmpty()) {
            cnx_error.setText("Veuillez remplir tous les champs");

        } else {

            cnx_error.setText("");
            signInUser(actionEvent);


        }
    }

    public void signInUser(ActionEvent actionEvent) {
        Connection connectNow = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String email = cnx_email.getText();
        String password = cnx_passwd.getText();

        try {
            preparedStatement = connectNow.prepareStatement("SELECT email,passwd,username,nom,prenom,id FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User not found. Please try again!");
                alert.show();
            } else {
                while (resultSet.next()) { //??
                    String retrievedPassword = resultSet.getString("passwd");//récupérer le mdp du BD
                    String UserName = resultSet.getString("username"); //récupérer le nom d'utilisateur
                    String nom = resultSet.getString("nom"); //récupérer le nom d'utilisateur
                    String prenom = resultSet.getString("prenom"); //récupérer le nom d'utilisateur
                    int userID = Integer.parseInt(resultSet.getString("id")); //récupérer le nom d'utilisateur

                    if (retrievedPassword.equals(password)) {
                        System.out.println("Logged in");
                        UserSession.userConnected = new User(userID, UserName);
                        Tools.changeScene(actionEvent, "LoggedIn.fxml", "Articles");
                        System.out.println(UserName);
                    } else {
                        System.out.println("wrong password");
                        cnx_error_passwd.setText("Password incorrect");

                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
