// ControladorAutenticacion.java - Gestiona la autenticación de usuarios

import modelo.dao.UsuarioDAO;
import modelo.entidades.Usuario;
import vista.VistaAutenticacion;
import java.util.Scanner;

/**
 * Controlador para gestionar el proceso de autenticación:
 * - Registro de nuevos usuarios
 * - Inicio de sesión de usuarios existentes
 */
public class ControladorAutenticacion {
    private final Scanner scanner;
    private final VistaAutenticacion vista;
    private final UsuarioDAO usuarioDAO;

    /**
     * Constructor que inicializa dependencias.
     *
     * @param scanner Objeto para leer entrada del usuario
     */
    public ControladorAutenticacion(Scanner scanner) {
        this.scanner = scanner;
        this.vista = new VistaAutenticacion();
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Maneja el proceso completo de autenticación.
     *
     * @return Usuario autenticado o null si se selecciona salir
     */
    public Usuario iniciarSesion() {
        while (true) {
            vista.mostrarMenuAutenticacion();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer

            switch (opcion) {
                case 1: // Iniciar sesión
                    return iniciarSesionUsuario();
                case 2: // Registrarse
                    registrarUsuario();
                    break;
                case 3: // Salir
                    return null;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    /**
     * Realiza el proceso de inicio de sesión.
     *
     * @return Usuario autenticado o null si falla
     */
    private Usuario iniciarSesionUsuario() {
        // Obtener credenciales mediante la vista
        String[] credenciales = vista.solicitarCredenciales(scanner);
        String nombreUsuario = credenciales[0];
        String contrasena = credenciales[1];

        // Verificar credenciales con el modelo
        Usuario usuario = usuarioDAO.autenticar(nombreUsuario, contrasena);

        if (usuario != null) {
            System.out.println("\n¡Bienvenido, " + usuario.getNombreUsuario() + "!");
            return usuario;
        } else {
            System.out.println("Error: Credenciales incorrectas");
            return null;
        }
    }

    /**
     * Registra un nuevo usuario en el sistema.
     */
    private void registrarUsuario() {
        // Obtener datos del nuevo usuario mediante la vista
        Usuario nuevoUsuario = vista.solicitarDatosRegistro(scanner);

        // Intentar crear el usuario en la base de datos
        if (usuarioDAO.crearUsuario(nuevoUsuario)) {
            System.out.println("\n¡Usuario registrado con éxito!");
        } else {
            System.out.println("Error: No se pudo registrar el usuario");
        }
    }
}