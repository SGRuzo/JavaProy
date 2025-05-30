// VistaAutenticacion.java - Interfaz de usuario para autenticación
package vista;

import modelo.entidades.Usuario;
import java.util.Scanner;

/**
 * Clase que maneja la interacción con el usuario para:
 * - Mostrar menús de autenticación
 * - Capturar datos de inicio de sesión
 * - Capturar datos de registro
 */
public class VistaAutenticacion {

    /**
     * Muestra el menú principal de autenticación.
     */
    public void mostrarMenuAutenticacion() {
        System.out.println("\n--- MENÚ DE AUTENTICACIÓN ---");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.print("Seleccione opción: ");
    }

    /**
     * Solicita credenciales de inicio de sesión.
     *
     * @param scanner Objeto para leer entrada
     * @return Array con [nombreUsuario, contraseña]
     */
    public String[] solicitarCredenciales(Scanner scanner) {
        System.out.println("\n--- INICIO DE SESIÓN ---");
        System.out.print("Nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        return new String[]{usuario, contrasena};
    }

    /**
     * Solicita datos para registro de nuevo usuario.
     *
     * @param scanner Objeto para leer entrada
     * @return Objeto Usuario con los datos proporcionados
     */
    public Usuario solicitarDatosRegistro(Scanner scanner) {
        System.out.println("\n--- REGISTRO DE NUEVO USUARIO ---");
        System.out.print("Nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        return new Usuario(usuario, contrasena, email);
    }
}