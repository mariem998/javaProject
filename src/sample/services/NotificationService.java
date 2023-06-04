package sample.services;

import sample.DBConnection;
import sample.ICrudService;
import sample.Notifications;
import sample.Tools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class NotificationService implements ICrudService<Notifications> {

    private Connection dbConnection;

    public NotificationService() {
        this.dbConnection = DBConnection.getConnection();
    }

    @Override
    public void add(String param) {

    }

    public void addNotif(int catId) {

        PreparedStatement psInsert;
        try {
            LocalDate dateCreat = LocalDate.now();
            String msg = "Nouveau Article Ajouté";
            int id = Tools.getLastID("SELECT id_notif FROM notifications ORDER BY id_notif DESC LIMIT 1", "id_notif") + 1;
            psInsert = dbConnection.prepareStatement("INSERT INTO notifications (id_notif,id_categorie,message,dateCreation) values (?,?,?,?)");
            psInsert.setInt(1, id);
            psInsert.setInt(2, catId);
            psInsert.setString(3,msg);
            psInsert.setDate(4, Date.valueOf(dateCreat));
            psInsert.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Notifications notifications) {

    }

    @Override
    public Notifications findById(int id) {
        return null;
    }


    @Override
    public List findAll() {
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
