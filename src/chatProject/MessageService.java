package chatProject;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Logger;

public class MessageService {

    private static final Logger LOGGER = Logger.getLogger(MessageService.class.getName());

    private static DBConnection dbConnection = new DBConnection();

    public MessageService() {
        dbConnection = new DBConnection();
    }

    // --- Guardar un Mensaje en la BD
    public static void createMessage(String content) {
        String sql = String.format("INSERT INTO messages (content) VALUES ('%s')", content);
        dbConnection.update(sql);
        LOGGER.info("Message created: " + content);
    }

    // --- Recuperar un Mensaje usando el ID
    public static void readMessageById(int id) {
        String sql = String.format("SELECT * FROM messages WHERE id = %d", id);
        dbConnection.query(sql, rs -> {
            try {
                if (rs.next()) {
                    String content = rs.getString("content");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    String out = String.format("ID: %d, Contenido: %s, Fecha y Hora: %tH", id, content, timestamp);
                    LOGGER.info(out);
                } else {
                    LOGGER.info("Message not found with ID: " + id);
                }
            } catch (SQLException e) {
                LOGGER.severe("Error reading message by ID: " + e.getMessage());
            }
        });

    }

    // --- Recuperar los últimos 10 mensajes
    public static void readLastMessages(int cuantosMensajes) {
        dbConnection.query("SELECT * FROM users ORDER BY id DESC LIMIT " + cuantosMensajes, rs -> {
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



}
