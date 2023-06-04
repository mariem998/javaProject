package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Categories;
import sample.DBConnection;
import sample.Tools;
import sample.services.CategoriesService;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjouterCategorieController implements Initializable {


    @FXML
    private TextField cat;

    @FXML
    private TableView<Categories> CategoriesExist;

    @FXML
    private TableColumn<Categories, String> CategoryTableColumn;

    @FXML
    private Button add_articles;

    private ObservableList<Categories> categories = FXCollections.observableArrayList();

    private  CategoriesService categoriesService = new CategoriesService();

    public void AjoutCategorie(ActionEvent actionEvent) {

        actionEvent.consume();
        String categorie = cat.getText();
        Connection connectNow = DBConnection.getConnection();
        PreparedStatement psInsert;

        try {

            int id= Tools.getLastID("SELECT id_categorie FROM categories ORDER BY id_categorie DESC LIMIT 1","id_categorie")+1;

            psInsert = connectNow.prepareStatement("insert into categories values ('" + id + "','" + categorie + "')");
            psInsert.executeUpdate();
            System.out.println("Ajout effectuer avec succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }


    public void initialize(URL location, ResourceBundle resources) {

        displayCategoriesElements();
        Tools.TestRole(add_articles);

    }

    private void displayCategoriesElements() {
        // Initialize table view list, display empty rows
        categories.clear();
        categories.addAll(categoriesService.findAll());
        CategoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        CategoriesExist.setItems(categories);
    }

    public void goToAddCategoriesScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent,"AjouterCategories.fxml", "Ajouter Categorie");
    }
    public void goToAddArticlesScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent, "AjouterArticle.fxml", "Ajouter article");
    }

    public void goToAddAbonnementScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent, "Abonnement.fxml", "Ajouter Categorie");
    }

    public void goToArticlesScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent,"LoggedIn.fxml", "Articles");
    }



}
