package sample;


public class User {


    private int id;
    private String email;
    private String nom;
    private String Username;
    private String prenom;
    private String role;

    public User() {
    }

    private User(int id) {
        this.id = id;
    }

    public User(int id, String Username) {
        this.id = id;
        this.Username = Username;
    }

    public User(int id, String email, String nom, String username, String prenom, String role) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        Username = username;
        this.prenom = prenom;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

