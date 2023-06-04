package sample.services;


import javafx.scene.control.Alert;
import sample.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import sample.User;


public class CommentairesService implements ICrudService<Commentaires> {

    private Connection dbConnection;

    public CommentairesService() {
        this.dbConnection = DBConnection.getConnection();
    }
    private UserService userService = new UserService();
    @Override
    public void add(String commentaire) {
        ResultSet resultSet;
        PreparedStatement psInsert;

        try {
            int id = Tools.getLastID("SELECT id_com FROM commentaires ORDER BY id_com DESC LIMIT 1", "id_com") + 1;
            int userId = UserSession.userConnected.getId();
            LocalDate date = LocalDate.now();
            int idArticle = Articles.art.getIdArticle();
            psInsert = dbConnection.prepareStatement("INSERT INTO commentaires (id_com,commentaire,dateCom,id_article,id_user) values (?,?,?,?,?)");
            psInsert.setInt(1, id);
            psInsert.setString(2, commentaire);
            psInsert.setDate(3, Date.valueOf(date));
            psInsert.setInt(4, idArticle);
            psInsert.setInt(5, userId);
            psInsert.executeUpdate();

            System.out.println("Ajouté avec succées");
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setContentText("Commentaire Ajouté");
//            alert.show();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(Commentaires commentaires) {
    }


    @Override
    public Commentaires findById(int id) {
        return null;
    }

    @Override
    public List<Commentaires> findAll() {
        List<Commentaires> commentaires = new ArrayList<>();
        ResultSet rs;
        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConnection.prepareStatement("SELECT * FROM commentaires");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Commentaires tmpCommentaires = new Commentaires();
                tmpCommentaires.setId(rs.getInt("id_com"));
                tmpCommentaires.setCommentaire(rs.getString("commentaire"));
                tmpCommentaires.setId_article(rs.getInt("id_article"));
                tmpCommentaires.setId_user(rs.getInt("id_user"));
                tmpCommentaires.setDate(rs.getDate(3));
                User user = userService.findById(tmpCommentaires.getId_user());
                tmpCommentaires.setUsername(user.getUsername());

                commentaires.add(tmpCommentaires);
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching data...");
            e.printStackTrace();
            commentaires = new ArrayList<>();

        } finally {

            return commentaires;

        }
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public boolean removeAll() {
        ResultSet rs;
        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConnection.prepareStatement("DELETE FROM commentaires");
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
