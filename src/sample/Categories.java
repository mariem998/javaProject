package sample;



public class Categories {


    private int id_categorie;
    private String libelle;

    public Categories(int id_categorie, String libelle) {
        this.id_categorie = id_categorie;
        this.libelle = libelle;
    }

    public Categories() {
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id_categorie=" + id_categorie +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
