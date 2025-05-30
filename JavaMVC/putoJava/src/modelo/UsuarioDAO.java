// UsuarioDAO.java - Acceso a datos para usuarios
package modelo;

import modelo.entidades.Usuario;
import java.sql.*;

/**
 * Clase que implementa operaciones CRUD para usuarios:
 * - Autenticación
 * - Creación de nuevos usuarios
 * - Consulta de información
 */
public class UsuarioDAO {
    /**
     * Autentica a un usuario con sus credenciales.
     *
     * @param nombreUsuario Nombre de usuario
     * @param contrasena Contraseña sin encriptar
     * @return Usuario autenticado o null si falla
     */
    public Usuario autenticar(String nombreUsuario, String contrasena) {
        // Consulta SQL parametrizada para prevenir inyecciones
        String sql = "SELECT id, nombre_usuario, email FROM usuarios WHERE nombre_usuario = ? AND contrasena_hash = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            // Establecer parámetros
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasena); // En un caso real, usaríamos hash

            // Ejecutar consulta
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(
                            rs.getString("nombre_usuario"),
                            null, // No devolvemos la contraseña
                            rs.getString("email")
                    );
                    usuario.setId(rs.getInt("id"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en autenticación: " + e.getMessage());
        }
        return null;
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param usuario Objeto Usuario con los datos
     * @return true si se creó exitosamente, false en caso contrario
     */
    public boolean crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena_hash, email) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer parámetros
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContrasenaHash()); // En producción, usar hash
            stmt.setString(3, usuario.getEmail());

            // Ejecutar inserción
            int filasAfectadas = stmt.executeUpdate();

            // Obtener ID generado
            if (filasAfectadas > 0) {
                try (ResultSet claves = stmt.getGeneratedKeys()) {
                    if (claves.next()) {
                        usuario.setId(claves.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
        }
        return false;
    }
}