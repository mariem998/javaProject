package sample;

import java.time.LocalDateTime;
import java.util.Date;

public class Commentaires {
    private int id;
    private String commentaire;
    private Date date;
    private int id_article;
    private int id_user;
    private String username;

    public Commentaires(int id, String commentaire, Date date, int id_article, int id_user,String username) {
        this.id = id;
        this.commentaire = commentaire;
        this.date = date;
        this.id_article = id_article;
        this.id_user = id_user;
        this.username = username;
    }

    public Commentaires() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
