package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD_Autor {
    private static java.sql.Connection connection = Connection.getInstance().getConnection();

    public static void adaugaAutor(String nume, double rating){
        try {
            String sql = "INSERT INTO autor (nume, rating) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            statement.setDouble(2, rating);
            int rows = statement.executeUpdate();
            if(rows > 0){
                System.out.println("Autorul a fost adaugat cu succes.");
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void afiseazaAutori(){
        try {
            String sql = "SELECT * FROM autor";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet autori = statement.executeQuery();
            while (autori.next()) {
                String name = autori.getString(2);
                double rating = autori.getDouble(3);
                System.out.println(name + " rating: " + rating);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void stergeAutor(String nume){
        try {
            String sql = "DELETE FROM autor WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Autorul a fost sters din baza de date.");
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void schimbaRating(String nume, double rating){
        try {
            String sql = "UPDATE autor SET rating = ? WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, rating);
            statement.setString(2, nume);
            int rows = statement.executeUpdate();
            if (rows > 0){
                System.out.println("Ratingul a fost actualizat.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
