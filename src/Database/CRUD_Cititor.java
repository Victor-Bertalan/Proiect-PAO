package Database;

import models.Cititor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CRUD_Cititor {
    private static java.sql.Connection connection = Connection.getInstance().getConnection();

    public static void adaugaCititor(String nume){
        try {
            String sql = "INSERT INTO cititor (nume) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            int rows = statement.executeUpdate();
            if(rows > 0){
                System.out.println("Sectiunea a fost adaugata cu succes.");
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Cititor> getCititori(){
        List<Cititor> cititori = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cititor";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet cititoriSql = statement.executeQuery();
            while (cititoriSql.next()) {
                String nume = cititoriSql.getString("nume");
                cititori.add(new Cititor(nume, new HashSet<>()));
            }
            return cititori;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static void updateCititor(String nume, String numeNou){
        try {
            String sql = "UPDATE cititor SET nume = ? WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, numeNou);
            statement.setString(2, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Cititorul a fost actualizat.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void stergeCititor(String nume){
        try {
            String sql = "DELETE FROM sectiune WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Cititorul a fost sters din baza de date.");
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
