package sample.services;

import sample.Articles;
import sample.DBConnection;
import sample.ICrudService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticlesService implements ICrudService<Articles> {

    private Connection dbConnection;

    public ArticlesService() {
        this.dbConnection = DBConnection.getConnection();
    }

    @Override
    public void add(String param) {

    }
        @Override
    public void update(Articles articles) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dbConnection.prepareStatement("UPDATE articles SET `titre`='"+articles.getTitle()+"',`text`='"+articles.getText()+"',`imgPath`='"+articles.getImgPath()+"',`categorie_id`='"+articles.getIdCategory()+"',`user_id`='"+articles.getIdUser()+"',`dateTime`='"+articles.getCreationDate()+"' WHERE `id_article`='"+articles.getIdArticle()+"'");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

        @Override
    public Articles findById(int id) {
        ResultSet rs;
        PreparedStatement preparedStatement;
        Articles SelectedArticle = new Articles();
        try {
            preparedStatement = dbConnection.prepareStatement("SELECT * FROM articles where id_article = ?");
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                SelectedArticle.setCreationDate(rs.getTimestamp(7).toLocalDateTime());
                SelectedArticle.setIdArticle(rs.getInt("id_article"));
                SelectedArticle.setTitle(rs.getString("titre"));
                SelectedArticle.setText(rs.getString("text"));
                System.out.println(rs.getString("text"));
                SelectedArticle.setIdCategory(rs.getInt("categorie_id"));
                SelectedArticle.setIdUser(rs.getInt("user_id"));
                SelectedArticle.setImgPath(rs.getString("imgPath"));
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching data...");
            e.printStackTrace();

        } finally {

            return SelectedArticle;
        }

    }
        @Override
    public List<Articles> findAll() {
        List<Articles> articles = new ArrayList<>();
        ResultSet rs;
        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConnection.prepareStatement("SELECT * FROM articles");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Articles tmpArticles = new Articles();
                tmpArticles.setIdArticle(rs.getInt("id_article"));
                tmpArticles.setTitle(rs.getString("titre"));
                tmpArticles.setText(rs.getString("text"));
                tmpArticles.setIdCategory(rs.getInt("categorie_id"));
                tmpArticles.setIdUser(rs.getInt("user_id"));
                tmpArticles.setImgPath(rs.getString("imgPath"));
                //tmpArticles.setCreationDate(rs.getString("libelle"));

                articles.add(tmpArticles);
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching data...");
            e.printStackTrace();
            articles = new ArrayList<>();

        } finally {

            return articles;

        }

    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public boolean removeAll() {
        return false;
    }



}
