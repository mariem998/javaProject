package sample.controllers;

import javafx.event.ActionEvent;
import  javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.DBConnection;
import sample.Tools;
import sample.User;
import sample.UserSession;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;



public class InscriptionController {

    @FXML
    private RadioButton inscri_role_redacteur;

    @FXML
    private RadioButton inscri_role_lecteur;

    @FXML
    private Button inscri_cnx;

    @FXML
    private TextField inscri_email;

    @FXML
    private Label inscri_error;

    @FXML
    private TextField inscri_firstname;

    @FXML
    private TextField inscri_lastname;

    @FXML
    private PasswordField inscri_password;

    @FXML
    private Button inscri_submit;

    @FXML
    private Label inscri_success;

    @FXML
    private TextField inscri_username;



    public void registerButtonAction(ActionEvent actionEvent) {

        if(inscri_username.getText().trim().isEmpty() || inscri_password.getText().trim().isEmpty() || inscri_email.getText().trim().isEmpty() ){
            inscri_error.setText("Veuillez remplir tous les champs");
            inscri_success.setText("");

        }
        else {

            inscri_error.setText("");
            registerUser(actionEvent);


        }


    }


    public void goToConnexionScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent,"connexion.fxml", "connexion");
    }


    static boolean isValid(String email){
        boolean result = true;
        String regex="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public void registerUser(ActionEvent actionEvent) {
        Connection connectNow = DBConnection.getConnection();
        PreparedStatement psInsert;
        PreparedStatement psCheckUserExists;
        //PreparedStatement getLastID = null;
        ResultSet resultSet;
       // ResultSet rs = null;
        String firstname = inscri_firstname.getText();
        String lastname = inscri_lastname.getText();
        String username = inscri_username.getText();
        String email = inscri_email.getText();
        String password=inscri_password.getText();
        int id_user= Tools.getLastID("SELECT id FROM users ORDER BY id DESC LIMIT 1","id")+1;
        String role;
        if (inscri_role_lecteur.isSelected()){
            role="Lecteur";
        }
        else
            role = "Redacteur";
        //int inscri_role= Integer.parseInt(null);
        if (!isValid(email)){
            inscri_error.setText("email invalide");
        }

else {
            try {

                psCheckUserExists = connectNow.prepareStatement("SELECT * FROM users WHERE username = ? or email = ?");
                psCheckUserExists.setString(1,username);
                psCheckUserExists.setString(2,email);
                resultSet = psCheckUserExists.executeQuery();

                if(resultSet.isBeforeFirst()){
                    System.out.println("User already exists");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("You cannot use this username");
                    alert.show();
                }
                else {
                    inscri_success.setText("User has been registred successfully");
                    System.out.println(resultSet.isBeforeFirst());
                }
                psInsert = connectNow.prepareStatement("INSERT INTO users(id,nom, prenom, username, email, passwd,role) VALUES (?,?,?,?,?,?,?) ");
                psInsert.setInt(1,id_user);
                psInsert.setString(2,firstname);
                psInsert.setString(3,lastname);
                psInsert.setString(4,username);
                psInsert.setString(5,email);
                psInsert.setString(6,password);
                psInsert.setString(7,role);
                psInsert.executeUpdate();
                UserSession.userConnected = new User(id_user, username);


                Tools.changeScene(actionEvent,"LoggedIn.fxml", "Articles");
            }
            catch (SQLIntegrityConstraintViolationException e) {
                e.printStackTrace();

                e.getCause();
            } catch (SQLException | IOException e) {
                System.out.println("Error insertion");
                e.printStackTrace();
            }

        }


    }



}
