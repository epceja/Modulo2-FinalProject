package chatProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class DBConnection {

    //***** Agregado por mí.
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static final String URL = "jdbc:mysql://192.168.1.78:3306/chat_db";
    private static final String USER = "eperezc";
    private static final String PASSWORD = "enrique";
    //*****

    private Connection connection;

    public DBConnection() {
        try {
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "user", "password");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);  //****
        } catch (SQLException e) {
            LOGGER.severe("Falló la conexión a la Base de Datos" + e.getMessage()); //****
        }
    }

    public void query(String sql, Consumer<ResultSet> consumer) {
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            consumer.accept(rs);
        } catch (SQLException e) {
            LOGGER.severe("Falló al ejecutar el query en la Base de Datos" + e.getMessage());
        }
    }

    public void update(String sql) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Falló al actualizar la BD" + e.getMessage());
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        return stmt.executeQuery();
    }

    public void executeUpdate(String sql, Consumer<Integer> consumer) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int result = stmt.executeUpdate();
            consumer.accept(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
