// Usuario.java - Entidad para representar un usuario

/**
 * Clase que representa a un usuario del sistema.
 * Almacena información básica de autenticación y perfil.
 */
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasenaHash;
    private String email;

    /**
     * Constructor para crear un nuevo usuario.
     *
     * @param nombreUsuario Nombre único para identificar al usuario
     * @param contrasenaHash Contraseña encriptada
     * @param email Correo electrónico del usuario
     */
    public Usuario(String nombreUsuario, String contrasenaHash, String email) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenaHash = contrasenaHash;
        this.email = email;
    }

    // Getters y Setters con comentarios Javadoc
    /**
     * @return ID único del usuario
     */
    public int getId() { return id; }

    /**
     * @param id Nuevo ID del usuario
     */
    public void setId(int id) { this.id = id; }

    // ... (resto de getters y setters)
}