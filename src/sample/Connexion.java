package sample;
import java.sql.*;
public class Connexion {
    private Connection c =null;
    private Statement stmt =null;

    public Connexion() throws ClassNotFoundException, SQLException
    {
        String url = "jdbc:mysql://localhost:3306/projetjava?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        c = DriverManager.getConnection(url,"root","");
        stmt = c.createStatement();
    }
    public Statement getStmt() {
        return stmt;
    }
}

