package sample;


import java.util.Set;

public final class UserSession {


    public static User userConnected;
    public Set<String> privileges;


    public void cleanUserSession() {
        userConnected = null;// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userConnected.getUsername() + '\'' +
                "nom='" + userConnected.getNom() + '\'' +
                "prenom='" + userConnected.getPrenom() + '\'' +
                "id='" + userConnected.getId() + '\'' +
                //", privileges=" + privileges +
                '}';
    }

}
