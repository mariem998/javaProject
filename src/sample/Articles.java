package sample;

import java.sql.Date;
import java.time.LocalDateTime;

public class Articles {


    private int idArticle;
    private String title;
    private String text;
    private int idCategory;
    private int idUser;
    private String imgPath;
    private LocalDateTime creationDate;
    public static Articles art;
    public Articles() {
    }



    public Articles(int idArticle, String text) {
        this.idArticle = idArticle;
        this.text = text;
    }

    public  Articles(int idArticle, String title, String text, int idCategory, int idUser, String imgPath, LocalDateTime creationDate) {
        this.idArticle = idArticle;
        this.title = title;
        this.text = text;
        this.idCategory = idCategory;
        this.idUser = idUser;
        this.imgPath = imgPath;
        this.creationDate = creationDate;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "idArticle=" + idArticle +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", idCategory=" + idCategory +
                ", idUser=" + idUser +
                ", imgPath='" + imgPath + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}