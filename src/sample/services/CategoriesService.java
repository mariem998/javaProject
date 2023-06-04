package sample.services;

import sample.Categories;
import sample.DBConnection;
import sample.ICrudService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesService implements ICrudService<Categories> {

    private Connection dbConnection;

    public CategoriesService() {
        this.dbConnection = DBConnection.getConnection();
    }

    @Override
    public void add(String param) {

    }

    @Override
    public void update(Categories categories) {

    }

    @Override
    public Categories findById(int id) {
        //ArrayList<Categories> SelectedCategorie = null;
        Categories SelectedCategorie = null;
        return SelectedCategorie ;
    }

    @Override
    public List<Categories> findAll() {
        List<Categories> categories = new ArrayList<>();
        ResultSet rs;
        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConnection.prepareStatement("SELECT * FROM categories");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Categories tmpCategorie = new Categories();
                tmpCategorie.setId_categorie(rs.getInt("id_categorie"));
                tmpCategorie.setLibelle(rs.getString("libelle"));
                categories.add(tmpCategorie);
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching data...");
            e.printStackTrace();
            categories = new ArrayList<>();
        } finally {
            return categories;
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