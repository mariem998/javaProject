package sample;

public class Abonnements {

    private int id_abonnement;
    private int id_user;
    private int id_categorie;

    public Abonnements(int id_abonnement, int id_user, int id_categorie) {
        this.id_abonnement = id_abonnement;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
    }

    public Abonnements() {

    }

    public int getId_abonnement() {
        return id_abonnement;
    }

    public void setId_abonnement(int id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }
}
