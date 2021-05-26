package Database;

import models.Autor;
import models.Carte;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CRUD_Carte {
    private static java.sql.Connection connection = Connection.getInstance().getConnection();

    public static void adaugaCarte(Carte c) {
        try {
            String sql = "INSERT INTO carte (titlu, nume_autor, pret) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, c.getTitlu());
            statement.setString(2, c.getAutor().getNume());
            statement.setInt(3, c.getPret());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Cartea a fost adaugata cu succes.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static HashMap<Carte, Integer> getCarti() {
        HashMap<Carte, Integer> carti = new HashMap<>();
        try {
            String sql = "SELECT * FROM carte c join autor a on (c.nume_autor = a.nume)";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet cartiSql = statement.executeQuery();
            while (cartiSql.next()) {
                String titlu = cartiSql.getString("titlu");
                int pret = cartiSql.getInt("pret");
                String numeAutor = cartiSql.getString("nume_autor");
                double rating = cartiSql.getDouble("rating");
                carti.put(new Carte(titlu, new Autor(numeAutor, rating), pret), 0);
            }
            return carti;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static void updatePret(String nume, int pret) {
        try {
            String sql = "UPDATE carte SET pret = ? WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pret);
            statement.setString(2, nume);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Pretul a fost actualizat.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void stergeCarte(String nume) {
        try {
            String sql = "DELETE FROM carte WHERE nume = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Cartea a fost stersa din baza de date.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
