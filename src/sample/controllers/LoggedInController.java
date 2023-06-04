package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import sample.Articles;
import sample.Tools;
import sample.UserSession;
import sample.services.ArticlesService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {


    @FXML
    private Label logged_user;

    @FXML
    private TableView<Articles> categoryTableView;

    @FXML
    private TableColumn<Articles, String> imageTableColumn;

    @FXML
    private TableColumn<Articles, String> textTableColumn;

    @FXML
    private TableColumn<Articles, String> titleTabColumn;

    @FXML
    private Button add_articles;


    ObservableList<Articles> articles = FXCollections.observableArrayList();
    ObservableList<Articles> art = FXCollections.observableArrayList();

    TableRow<ObservableList<String>> Articlesrow = new TableRow<>();

    private ArticlesService articlesService = new ArticlesService();


    public void goToAddArticlesScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent, "AjouterArticle.fxml", "Ajouter article");
    }

    public void goToAddCategoriesScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent, "AjouterCategories.fxml", "Ajouter Categorie");
    }

    public void goToAddAbonnementScene(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(actionEvent, "Abonnement.fxml", "Ajouter Categorie");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logged_user.setText(UserSession.userConnected.getUsername());
        displayArticlesElements();
        Tools.TestRole(add_articles);

    }


    public Articles getSelectedArticleData(){
        Articles selectedArticle = categoryTableView.getSelectionModel().getSelectedItem();
//        int id =selectedArticle.getIdArticle();
//        Articles.art = articlesService.findById(id);
        return selectedArticle;
    }

    public void goToArticle(MouseEvent mouseEvent) throws IOException {

        String title = getSelectedArticleData().getTitle();
        Tools.goToArticlesScene(mouseEvent,"Article.FXML",title,getSelectedArticleData());
    }


    private void displayArticlesElements() {
        // Initialize table view list, display empty rows
        articles.clear();

        imageTableColumn.setCellValueFactory(new PropertyValueFactory<>("imgPath"));
        imageTableColumn.setCellFactory(new Callback<TableColumn<Articles, String>, TableCell<Articles, String>>() {
            @Override
            public TableCell<Articles, String> call(TableColumn<Articles, String> param) {
                TableCell<Articles, String> cell = new TableCell<Articles, String>() {
                    @Override
                    public void updateItem(String imgPath, boolean empty) {
                        HBox box = new HBox();
                        box.setSpacing(10);
                        VBox vbox = new VBox();
                        ImageView imageview = new ImageView();
                        imageview.setFitHeight(50);
                        imageview.setFitWidth(90);
                        File file;
                        if (imgPath == null) {
                            file = new File("./src/sample/images/login.png");
                        } else {
                            file = new File("./src/sample/images/ArticlesImages/" + imgPath);
                        }
                        BufferedImage bufferedImage;
                        try {
                            bufferedImage = ImageIO.read(file);
                            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                            imageview.setImage(image);
                        } catch (IOException e) {
                            System.err.println("Could not find image " + file.getPath());
                        }

                        box.getChildren().addAll(imageview, vbox);
                        // SETTING ALL THE GRAPHICS COMPONENT FOR CELL
                        setGraphic(box);
                    }
                };
                return cell;
            }

        });


        articles.addAll(articlesService.findAll());
        textTableColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        titleTabColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        categoryTableView.setItems(articles);
    }

    public static void main(String[] args)
    { LocalDateTime d=LocalDateTime.now();
        Articles articles=new Articles(18,"achraf","meryam xd",2, 1,"5293677-021648559491246.jpg",d);
        ArticlesService  articlesService=new ArticlesService();
        articlesService.update(articles);
    }

}
