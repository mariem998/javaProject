package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.*;
import sample.services.AbonnementService;
import sample.services.CategoriesService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AbonnementController implements Initializable {

    @FXML
    private TableView<Categories> CategoriesExist;

    @FXML
    private TableColumn<Categories, String> CategoryTableColumn;

    @FXML
    private TableColumn<Categories, Integer> CategoryTableColumnId;

    private ObservableList<Categories> categories = FXCollections.observableArrayList();

    private CategoriesService categoriesService = new CategoriesService();

    private AbonnementService abonnementService = new AbonnementService();
    ObservableList<Abonnements> abonnements = FXCollections.observableArrayList();


    public void goToAddArticlesScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent, "AjouterArticle.fxml", "Ajouter article");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayCategoriesElements();
        addButtonToTable();
        abonnements.addAll(abonnementService.findAll());

    }
    private void displayCategoriesElements() {
        // Initialize table view list, display empty rows
        categories.clear();
        categories.addAll(categoriesService.findAll());
        CategoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        CategoryTableColumnId.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        CategoriesExist.setItems(categories);
        System.out.println(CategoryTableColumnId.getCellObservableValue(0));
 /*       for (Abonnements value : abonnements){
                if(value.getId_user()==UserSession.userConnected.getId() && value.getId_categorie()==){

                }
            }*/
    }

    private void addButtonToTable() {
        TableColumn<Categories, Void> colBtn = new TableColumn("Abonnement");

        Callback<TableColumn<Categories, Void>, TableCell<Categories, Void>> cellFactory = new Callback<TableColumn<Categories, Void>, TableCell<Categories, Void>>() {
            @Override
            public TableCell<Categories, Void> call(final TableColumn<Categories, Void> param) {
                final TableCell<Categories, Void> cell = new TableCell<Categories, Void>() {

                    private final Button btn = new Button("S'abonner");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Categories selectedCategory = getTableView().getItems().get(getIndex());
                            System.out.println(selectedCategory);
                            System.out.println("selectedData: "+selectedCategory);
                            int id = selectedCategory.getId_categorie();
                            System.out.println("selectedData: "+id);
                           abonnementService.addAbn(id);
                           btn.setDisable(true);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        CategoriesExist.getColumns().add(colBtn);

    }









}
