// MainApp.java - Punto de entrada principal
package principal;

import controlador.ControladorAutenticacion;
import controlador.ControladorFamilia;
import controlador.ControladorProducto;
import controlador.ControladorListaCompra;
import modelo.dao.ConexionBD;
import modelo.entidades.Usuario;
import modelo.entidades.UnidadFamiliar;
import vista.VistaAutenticacion;
import java.util.Scanner;

/**
 * Clase principal que inicia la aplicación.
 * Gestiona el flujo principal y la navegación entre módulos.
 */
public class MainApp {

    public static void main(String[] args) {
        // Inicializar conexión a base de datos
        ConexionBD.inicializar();

        Scanner scanner = new Scanner(System.in);
        ControladorAutenticacion controladorAuth = new ControladorAutenticacion(scanner);

        // Bucle principal de la aplicación
        while (true) {
            System.out.println("\n===== SISTEMA DE GESTIÓN FAMILIAR =====");

            // Paso 1: Autenticación de usuario
            Usuario usuarioActual = controladorAuth.iniciarSesion();
            if (usuarioActual == null) break;

            // Paso 2: Gestión de unidad familiar
            ControladorFamilia controladorFamilia = new ControladorFamilia(scanner, usuarioActual);
            UnidadFamiliar familiaActual = controladorFamilia.gestionarUnidadFamiliar();

            if (familiaActual != null) {
                // Inicializar controladores para la familia seleccionada
                ControladorProducto controladorProducto = new ControladorProducto(scanner, familiaActual);
                ControladorListaCompra controladorLista = new ControladorListaCompra(scanner, familiaActual);

                boolean salirFamilia = false;
                // Menú principal para la unidad familiar
                while (!salirFamilia) {
                    System.out.println("\n--- MENÚ PRINCIPAL [" + familiaActual.getNombre() + "] ---");
                    System.out.println("1. Gestión de Productos");
                    System.out.println("2. Listas de Compra");
                    System.out.println("3. Cambiar Familia");
                    System.out.println("4. Cerrar Sesión");
                    System.out.print("Seleccione opción: ");

                    int opcion = scanner.nextInt();
                    scanner.nextLine();  // Limpiar buffer

                    switch (opcion) {
                        case 1:
                            controladorProducto.gestionarProductos();
                            break;
                        case 2:
                            controladorLista.gestionarListasCompra();
                            break;
                        case 3:
                            salirFamilia = true;
                            break;
                        case 4:
                            salirFamilia = true;
                            usuarioActual = null;
                            break;
                        default:
                            System.out.println("Opción inválida. Intente nuevamente.");
                    }
                }
            }
        }

        // Cerrar recursos al salir
        scanner.close();
        ConexionBD.cerrar();
        System.out.println("¡Hasta pronto!");
    }
}