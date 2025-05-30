// ConexionBD.java - Manejo de la conexión a la base de datos
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase singleton para gestionar la conexión a la base de datos.
 * Proporciona métodos para inicializar, obtener y cerrar la conexión.
 */
public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/gestion_familiar";
    private static final String USUARIO = "app_user";
    private static final String CONTRASENA = "password_seguro";

    /**
     * Inicializa la conexión a la base de datos.
     * Debe llamarse al inicio de la aplicación.
     */
    public static void inicializar() {
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión establecida con la base de datos");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la BD: " + e.getMessage());
        }
    }

    /**
     * Obtiene la conexión activa a la base de datos.
     *
     * @return Objeto Connection para interactuar con la BD
     * @throws SQLException Si no hay conexión establecida
     */
    public static Connection obtenerConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            throw new SQLException("Conexión no inicializada");
        }
        return conexion;
    }

    /**
     * Cierra la conexión con la base de datos.
     * Debe llamarse al finalizar la aplicación.
     */
    public static void cerrar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión con la BD cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}