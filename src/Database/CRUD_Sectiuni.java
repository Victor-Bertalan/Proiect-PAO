package Database;

import models.Sectiune;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CRUD_Sectiuni {
    private static java.sql.Connection connection = Connection.getInstance().getConnection();

    public static void adaugaSectiune(String nume){
        try {
            String sql = "INSERT INTO sectiune (nune) VALUES (?)";
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

    public static List<Sectiune> getSectiuni(){
        List<Sectiune> sectiuni = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sectiune";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet sectiuniSql = statement.executeQuery();
            while (sectiuniSql.next()) {
                String nume = sectiuniSql.getString("nume");
                sectiuni.add(new Sectiune(nume, new HashSet<>()));
            }
            return sectiuni;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static void updateSectiune(String nume, String numeNou){
        try {
            String sql = "UPDATE sectiune SET nume = ? WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, numeNou);
            statement.setString(2, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Sectiunea a fost actualizat.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void stergeSectiune(String nume){
        try {
            String sql = "DELETE FROM sectiune WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Sectiunea a fost stersa din baza de date.");
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
