package PRO1.server;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String JDBC_URL = dotenv.get("DB_URL");
    private static final String USERNAME = dotenv.get("DB_USERNAME");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");
    public static void main(String[] args) {
        System.out.println("JDBC_URL: " + JDBC_URL);
        System.out.println("USERNAME: " + USERNAME);
        System.out.println("PASSWORD: " + (PASSWORD != null ? "****" : "null"));  // Mask password for security
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            System.out.println("✅ Connexion réussie à Supabase !");
        } catch (SQLException e) {
            System.err.println("❌ Échec de la connexion : " + e.getMessage());
        }
    }
}
