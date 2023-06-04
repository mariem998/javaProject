package sample;

import java.time.LocalDateTime;

public class Notifications {

    private int IDnotif;
    private int IDcategorie;
    private String message;
    private LocalDateTime dateCreation;

    public Notifications(int IDnotif, int IDabonnement, String message, LocalDateTime dateCreation) {
        this.IDnotif = IDnotif;
        this.IDcategorie = IDabonnement;
        this.message = message;
        this.dateCreation = dateCreation;
    }

    public int getIDnotif() {
        return IDnotif;
    }

    public void setIDnotif(int IDnotif) {
        this.IDnotif = IDnotif;
    }

    public int getIDabonnement() {
        return IDcategorie;
    }

    public void setIDabonnement(int IDabonnement) {
        this.IDcategorie = IDabonnement;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}
