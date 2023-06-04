package sample.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import sample.*;
import sample.services.ArticlesService;
import sample.services.CategoriesService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ModifierArticleController implements Initializable {

    @FXML
    private TextField image_path;

    @FXML
    private TextField titre;

    @FXML
    private ComboBox<Object> CatList;

    @FXML
    private Label logged_user;

    @FXML
    private TextArea AddArticle_text;

    @FXML
	private Button add_articles;


    private CategoriesService categoriesService = new CategoriesService();
    String path = Articles.art.getImgPath();
    int catID =0;

    private ObservableList<Categories> categories = FXCollections.observableArrayList();
    int id = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
		logged_user.setText(UserSession.userConnected.getUsername());
        categories.addAll(categoriesService.findAll());
        for (Categories value : categories) {
            CatList.getItems().addAll(value.getLibelle());
        }
		Tools.TestRole(add_articles);

    }

    public int FindCategoryId(){
		String SelectedCategorie = (String) CatList.getValue();
		Connection connectNow = DBConnection.getConnection();
		PreparedStatement psGetCatID;
		ResultSet rsID;
		try {

			psGetCatID = connectNow.prepareStatement("SELECT id_categorie FROM categories where libelle = ?");
			psGetCatID.setString(1, SelectedCategorie);
			System.out.println("entry");
			rsID = psGetCatID.executeQuery();
			rsID.next();
			id = rsID.getInt("id_categorie");


			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	private static final String APP_IMG_PATH = "src/sample/images/ArticlesImages";
	private File selectedFile;

	ArticlesService articlesService = new ArticlesService();

	public void ChooseFile(ActionEvent actionEvent) throws IOException {
		FileChooser fc = new FileChooser();

		fc.setTitle("Choose Image");
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

		Window primaryStage = null;
		this.selectedFile = fc.showOpenDialog(primaryStage);
		if (this.selectedFile != null) {
			image_path.setText(".../" + this.selectedFile.getName());
		}
		System.out.println(selectedFile.getPath());
	}

    	private String copyImageAndGetName() {
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
	public void Modifier(ActionEvent actionEvent)  {
		actionEvent.consume();
		 String tit ;
		String txt ;
		String img ;
		if (AddArticle_text.getText().trim().isEmpty()){
			 txt = Articles.art.getTitle();
		}
		else{
			txt = AddArticle_text.getText();
		}

		if (titre.getText().trim().isEmpty()){
			tit = Articles.art.getTitle();
		}
		else{
			tit = titre.getText();
		}
		if (copyImageAndGetName().trim().isEmpty()){
			img = Articles.art.getImgPath();
		}
		else{
			img = copyImageAndGetName();
		}


		int idArticle = Articles.art.getIdArticle();
		int idUser = Articles.art.getIdUser();
		LocalDateTime d = Articles.art.getCreationDate();
		Articles ModifiedArticle = new Articles(idArticle,tit,txt,FindCategoryId(),idUser,img,d);
        System.out.println(ModifiedArticle);
		articlesService.update(ModifiedArticle);
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
