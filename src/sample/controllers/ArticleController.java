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
import sample.*;
import sample.services.CategoriesService;
import sample.services.CommentairesService;
import sample.services.NotificationService;
import sample.services.UserService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ArticleController implements Initializable{

	@FXML
	private TableView<Commentaires> comments;

	@FXML
	private TableColumn<Commentaires, String> dateCom;

	@FXML
	private TableColumn<Commentaires, String> comment;

	@FXML
	private TableColumn<User, String> username;

	@FXML
	private Button BtnModifArt;

    @FXML
	private ImageView ArticleImage;

    @FXML
	private Label ArticleTitle;

    @FXML
	private Label ArticleDateTime;

    @FXML
	private Label ArticleText;

	@FXML
	private TextField commentaire;

	@FXML
	private Label logged_user;

	@FXML
	private Button add_articles;



	private CommentairesService commentairesService = new CommentairesService();
	private UserService userService = new UserService();
	//private ObservableList<String> listItems =FXCollections.observableArrayList();
	private ObservableList<Commentaires> commentaires = FXCollections.observableArrayList();


	private void displayCommentairesElements() {
		// Initialize table view list, display empty rows
		commentaires.clear();
		commentaires.addAll(commentairesService.findAll());
		for (Commentaires value : commentaires) {
			User user = userService.findById(value.getId_user());
			System.out.println(user.getUsername());
			//username.textProperty().setValue(user.getUsername());
			username.setCellValueFactory(new PropertyValueFactory<>("username"));

		}
		comment.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
		dateCom.setCellValueFactory(new PropertyValueFactory<>("date"));
		comments.setItems(commentaires);

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		displayCommentairesElements();
		Tools.TestRole(add_articles);
		logged_user.setText(UserSession.userConnected.getUsername());
		ArticleTitle.setText(Articles.art.getTitle());
		ArticleTitle.setText(Articles.art.getTitle());
		ArticleText.setText(Articles.art.getText());
		logged_user.setText(UserSession.userConnected.getUsername());
		if(UserSession.userConnected.getId()==Articles.art.getIdUser()){
			System.out.println("test visible");
			BtnModifArt.setVisible(true);
		}
		else{
			System.out.println("test false visible");
			BtnModifArt.setVisible(false);

		}
		String path = Articles.art.getImgPath();
		ArticleImage.setFitWidth(2000);


		File file;
		if (path == null) {
			 file = new File("./src/sample/images/login.png");
		} else {
			 file = new File("./src/sample/images/ArticlesImages/" + path);
		}
		  file = new File("./src/sample/images/ArticlesImages/"+path);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			ArticleImage.setImage(image);
			ArticleImage.fitHeightProperty();
			ArticleImage.fitWidthProperty();
		} catch (IOException e) {
			System.err.println("Could not find image " + file.getPath());
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = Articles.art.getCreationDate();
		String formattedDateTime = dateTime.format(formatter);
		System.out.println(formattedDateTime);
		ArticleDateTime.setText(formattedDateTime);
	}

	public void AjouterCommentaire(ActionEvent actionEvent) throws IOException {
		commentairesService.add(commentaire.getText());
			comment.setText("");
		//int x=Articles.art.getIdArticle();

		Tools.changeScene(actionEvent,"Article.FXML",Articles.art.getTitle());
    }

	public void goToModifArticle(ActionEvent actionEvent) throws IOException {
		Tools.changeScene(actionEvent, "ModifierArticle.fxml", "Modifier Article");
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
