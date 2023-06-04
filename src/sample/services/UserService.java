package sample.services;

import sample.Articles;
import sample.DBConnection;
import sample.ICrudService;
import sample.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserService implements ICrudService<User> {

    private Connection dbConnection;

    public UserService() {
        this.dbConnection = DBConnection.getConnection();
    }


    @Override
    public void add(String param) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User findById(int id) {
        ResultSet rs;
        PreparedStatement preparedStatement;
        //List<Articles> SelectedArticle = new ArrayList<>();
        User user = new User();
        try {
            preparedStatement = dbConnection.prepareStatement("SELECT * FROM users where id = ?");
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println("suceeeeesss");
                //Articles article = new Articles();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setRole(rs.getString("role"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("email"));

            }
        } catch (SQLException e) {
            System.err.println("Error while fetching data...");
            e.printStackTrace();

        } finally {

            return user;
        }
    }

    @Override
    public List<User> findAll() {
        return null;
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
