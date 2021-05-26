package Database;

import java.sql.DriverManager;

public class Connection {
    private final String url = "jdbc:mysql://localhost:3306/biblioteca_app";
    private final String user = "root";
    private final String password = "";
    private java.sql.Connection connection = null;
    private static Connection instance = null;

    private Connection(){
        try{
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to database.");
                connection = conn;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getInstance(){
        if (instance == null)
            instance = new Connection();
        return instance;
    }

    public java.sql.Connection getConnection(){
        return connection;
    }
}
