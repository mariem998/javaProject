package sample.services;

import sample.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbonnementService implements ICrudService {

    private Connection dbConnection;

    public AbonnementService() {
        this.dbConnection = DBConnection.getConnection();;
    }


    public void addAbn(int idCategorie) {
        ResultSet resultSet;
        PreparedStatement psInsert;

        try {
            int id = Tools.getLastID("SELECT id_abonnement FROM abonnements ORDER BY id_abonnement DESC LIMIT 1", "id_abonnement") + 1;
            int userId = UserSession.userConnected.getId();
            psInsert = dbConnection.prepareStatement("INSERT INTO abonnements (id_abonnement,id_user,id_categorie) values (?,?,?)");
            psInsert.setInt(1, id);
            psInsert.setInt(2, userId);
            psInsert.setInt(3, idCategorie);
            psInsert.executeUpdate();

            System.out.println("Ajouté avec succées");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void add(String param) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public List<Abonnements> findAll() {
        List<Abonnements> abonnements = new ArrayList<>();
        ResultSet rs;
        PreparedStatement preparedStatement;

        try {
            preparedStatement = dbConnection.prepareStatement("SELECT * FROM abonnements");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Abonnements tmpAbonnement = new Abonnements();
                tmpAbonnement.setId_abonnement(rs.getInt(1));
                tmpAbonnement.setId_user(rs.getInt(2));
                tmpAbonnement.setId_categorie(rs.getInt(3));
                abonnements.add(tmpAbonnement);
            }
        } catch (SQLException e) {
            System.err.println("Error while fetching data...");
            e.printStackTrace();
            abonnements = new ArrayList<>();
        } finally {
            return abonnements;
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
