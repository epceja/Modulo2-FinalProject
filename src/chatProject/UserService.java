package chatProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    private static DBConnection dbConnection = new DBConnection();

    public UserService() {
        dbConnection = new DBConnection();
    }

    // --- Recuperar todos los Usuarios
    public static void readUsers() {
        dbConnection.query("SELECT * FROM users", rs -> {
            try {
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String out = String.format("%s %s %s", username, password, email);
                    LOGGER.info(out);
                }
            } catch (SQLException e) {
                LOGGER.severe("Error reading users: " + e.getMessage());
            }
        });
    }

    // --- Guardar un Usuario en la BD
    public static void createUser(String username, String password, String email) {
        String sql = String.format("INSERT INTO users (username, password, email) VALUES ('%s', '%s', '%s')", username, password, email);
        dbConnection.update(sql);
        LOGGER.info("User created: " + username);
    }

    // --- Recuperar un Usuario usando el ID
    public static void readUserById(int id) {
        String sql = String.format("SELECT * FROM users WHERE id = %d", id);
        dbConnection.query(sql, rs -> {
            try {
                if (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String out = String.format("ID: %d, Username: %s, Password: %s, Email: %s", id, username, password, email);
                    LOGGER.info(out);
                } else {
                    LOGGER.info("User not found with ID: " + id);
                }
            } catch (SQLException e) {
                LOGGER.severe("Error reading user by ID: " + e.getMessage());
            }
        });
    }

    // --- Recuperar el Usuario por el username
    public static void readUserByUsername(String username) {
        String sql = String.format("SELECT * FROM users WHERE username = '%s'", username);
        dbConnection.query(sql, rs -> {
            try {
                if (rs.next()) {
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String out = String.format("Username: %s, Password: %s, Email: %s", username, password, email);
                    LOGGER.info(out);
                } else {
                    LOGGER.info("User not found with username: " + username);
                }
            } catch (SQLException e) {
                LOGGER.severe("Error reading user by username: " + e.getMessage());
            }
        });
    }

    public static void readUserByEmail(String email) {
        String sql = String.format("SELECT * FROM users WHERE email = '%s'", email);
        dbConnection.query(sql, rs -> {
            try {
                if (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String out = String.format("Email: %s, Username: %s, Password: %s", email, username, password);
                    LOGGER.info(out);
                } else {
                    LOGGER.info("User not found with email: " + email);
                }
            } catch (SQLException e) {
                LOGGER.severe("Error reading user by email: " + e.getMessage());
            }
        });
    }
}
