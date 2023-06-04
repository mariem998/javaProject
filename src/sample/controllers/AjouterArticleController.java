package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import sample.DBConnection;
import sample.Tools;
import sample.UserSession;
import sample.services.NotificationService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class AjouterArticleController implements Initializable {

    @FXML
    private Button Ajouter;

    @FXML
    private TextField titre;

    @FXML
    private ComboBox<Object> cb;

    @FXML
    private Button chooseFile;

    @FXML
    private TextField image_path;

    @FXML
    private Label logged_user;

    @FXML
    private TextArea AddArticle_text;

    @FXML
    private Button add_articles;


    private static final String APP_IMG_PATH = "src/sample/images/ArticlesImages";
    private File selectedFile;
    private NotificationService notificationService = new NotificationService();



    public void ChooseFile(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();

        fc.setTitle("Choose Image");
        fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        Window primaryStage = null;
        this.selectedFile = fc.showOpenDialog(primaryStage);
        if (this.selectedFile != null) {
            image_path.setText(".../" + this.selectedFile.getName());
        }

        // image_path.setText(String.valueOf(selectedFile));
        System.out.println(selectedFile.getPath());
    }



    public void Ajout(ActionEvent actionEvent) {


        actionEvent.consume();
       // Object cat = cb.getSelectionModel().getSelectedItem();
        String tit = titre.getText();
        String txt = AddArticle_text.getText();

        Connection connectNow = DBConnection.getConnection();
        PreparedStatement psInsert;
        PreparedStatement psGetCatID;
        ResultSet rsID = null;
        String SelectedCategorie = null;
        SelectedCategorie = (String) cb.getValue();
        System.out.println( SelectedCategorie);
        int catID = 0;
        int userID = 0;
        String img = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try {
            psGetCatID = connectNow.prepareStatement("SELECT id_categorie FROM categories where libelle = ?");
            psGetCatID.setString(1, SelectedCategorie);
            rsID = psGetCatID.executeQuery();
            rsID.next();
            catID = rsID.getInt("id_categorie");
            userID = UserSession.userConnected.getId();
            img = copyImageAndGetName();

            psInsert = connectNow.prepareStatement("insert into articles values ('0','" + tit + "','" + txt + "','" + img + "','" + catID + "','" + userID + "','" + now + "')");
            psInsert.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Article Ajouté");
            alert.show();
            System.out.println("Ajout effectuer avec succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erreur Article");
            alert.show();
        }
        notificationService.addNotif(catID);

    }

    public String copyImageAndGetName() {
        if (this.selectedFile != null) {
            Path source = Paths.get(this.selectedFile.getPath());
            Path destination = Paths.get(APP_IMG_PATH);

            String[] fileNameSplitter = selectedFile.getName().split("\\.");

            

            String fileExtension = fileNameSplitter[fileNameSplitter.length - 1];
            StringBuilder fileName = new StringBuilder(fileNameSplitter[0]);
            for (int i = 1; i < fileExtension.length() - 2; i++) {
                fileName.append(fileNameSplitter[i]);
            }

            fileName.append(System.currentTimeMillis());
            fileName.append(".");
            fileName.append(fileExtension);

            try {
                Files.copy(source, destination.resolve(fileName.toString()),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.err.println("Error while copying file...");
                e.printStackTrace();
                return null;
            }
            return fileName.toString();
        }
        return null;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Connection connectNow = DBConnection.getConnection();
        ResultSet rs = null;
        //ResultSet rsCount = null;
        PreparedStatement preparedStatement = null;
        //PreparedStatement LengthStatement = null;

        try {
            logged_user.setText(UserSession.userConnected.getUsername());
            preparedStatement = connectNow.prepareStatement("SELECT libelle FROM categories");
            rs = preparedStatement.executeQuery();

//

            while (rs.next()) {

                cb.getItems().addAll(rs.getString("libelle"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    public void goToAddCategoriesScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent, "AjouterCategories.fxml", "Ajouter Categorie");
    }

    public void goToArticlesScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent,"LoggedIn.fxml", "Articles");
    }

    public void goToAddAbonnementScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent, "Abonnement.fxml", "Ajouter Categorie");
    }
}