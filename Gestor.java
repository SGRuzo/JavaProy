import java.util.*;

// ---------------------------
// VISTA (View) - Versión Mejorada
// ---------------------------
interface Vista {
    void mostrarMenuInicial();
    void mostrarLogin();
    void mostrarRegistro();
    void mostrarMenuSinFamilia();
    void mostrarMenuUnirseCrearFamilia();
    void mostrarMenuPrincipal();
    void mostrarMenuProductos();
    void mostrarMenuListaProductos();
    void mostrarMenuFiltros();
    void mostrarMenuCategorias(List<String> categorias);
    void mostrarMenuSubcategorias(List<String> subcategorias);
    void mostrarMenuOrden();
    void mostrarDetalleProducto(Producto producto);
    void mostrarListaCompra(ListaCompra lista);
    void mostrarMensaje(String mensaje);
    void mostrarError(String error);
    String obtenerEntrada(String prompt);
    int obtenerOpcionNumerica(String prompt, int min, int max);
    double obtenerPrecio(String prompt);
}

class Producto {
    private int id;
    private String nombre;
    private String marca;
    private double precio;
    private double puntuacionMedia;
    private String categoria;
    private String subcategoria;
    private List<String> supermercados;
    private String codigoBarras;

    // Constructor
    public Producto(int id, String nombre, String marca, double precio, double puntuacionMedia,
                    String categoria, String subcategoria, List<String> supermercados, String codigoBarras) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.puntuacionMedia = puntuacionMedia;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.supermercados = supermercados;
        this.codigoBarras = codigoBarras;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getMarca() { return marca; }
    public double getPrecio() { return precio; }
    public double getPuntuacionMedia() { return puntuacionMedia; }
    public String getCategoria() { return categoria; }
    public String getSubcategoria() { return subcategoria; }
    public List<String> getSupermercados() { return supermercados; }
    public String getCodigoBarras() { return codigoBarras; }

    // Setters
    public void setPrecio(double precio) { this.precio = precio; }
    public void setPuntuacionMedia(double puntuacionMedia) { this.puntuacionMedia = puntuacionMedia; }

}

class ListaCompra {
    private String nombre;
    private Map<Integer, Integer> productosCantidad; // productId -> quantity
    private boolean completada;

    public ListaCompra(String nombre) {
        this.nombre = nombre;
        this.productosCantidad = new HashMap<>();
        this.completada = false;
    }

    // Getters
    public String getNombre() { return nombre; }
    public Map<Integer, Integer> getProductosCantidad() { return productosCantidad; }
    public boolean isCompletada() { return completada; }

    // Methods
    public void agregarProducto(int productoId, int cantidad) {
        productosCantidad.put(productoId, productosCantidad.getOrDefault(productoId, 0) + cantidad);
    }

    public void marcarCompletada() {
        this.completada = true;
    }
}

class Modelo {
    public Producto obtenerProductoPorId(int id) {
        // Implement your logic to get product by ID
        return null; // placeholder
    }
    public class Usuario {
        private String email;
        private String password;
        private String nombre;
        private int familiaId; // 0 si no tiene familia

        // Constructor
        public Usuario(String email, String password, String nombre) {
            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                throw new IllegalArgumentException("Email inválido");
            }
            if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[A-Z].*")) {
                throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres, un número y una mayúscula");
            }
            this.email = email;
            this.password = password;
            this.nombre = nombre;
            this.familiaId = 0;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getNombre() {
            return nombre;
        }

        public int getFamiliaId() {
            return familiaId;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setFamiliaId(int familiaId) {
            this.familiaId = familiaId;
        }
    }
    public class UnidadFamiliar {
        private int id;
        private String codigoAcceso;
        private List<Integer> miembrosIds;
        private List<Integer> listasCompraIds;

        public UnidadFamiliar(int id, String codigoAcceso, int creadorId) {
            this.id = id;
            this.codigoAcceso = codigoAcceso;
            this.miembrosIds = new ArrayList<>();
            this.miembrosIds.add(creadorId);
            this.listasCompraIds = new ArrayList<>();
        }

        // Métodos para añadir/eliminar miembros, gestionar listas...
    }
}

class VistaConsolaMejorada implements Vista {
    private Scanner scanner = new Scanner(System.in);
    private Modelo modelo;

    public VistaConsolaMejorada(Modelo modelo) {
        this.modelo = modelo;
    }
    public boolean validarEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public boolean validarContrasena(String contrasena) {
        return contrasena.length() >= 8 &&
                contrasena.matches(".*\\d.*") &&
                contrasena.matches(".*[A-Z].*");
    }

    @Override
    public void mostrarMenuInicial() {
        System.out.println("\n--- GESTOR DE COMPRAS FAMILIARES ---");
        System.out.println("1. Iniciar cuenta");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    public void mostrarLogin() {
        System.out.println("\n--- INICIAR SESIÓN ---");
    }

    @Override
    public void mostrarRegistro() {
        System.out.println("\n--- REGISTRO DE CUENTA ---");
    }

    @Override
    public void mostrarMenuSinFamilia() {
        System.out.println("\n--- ACCIONES DISPONIBLES ---");
        System.out.println("1. Unirse a unidad familiar");
        System.out.println("2. Crear nueva unidad familiar");
        System.out.println("3. Cerrar sesión");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    public void mostrarMenuUnirseCrearFamilia() {
        System.out.println("\n--- UNIRSE O CREAR FAMILIA ---");
        System.out.println("1. Ingresar código de unidad familiar (o 'N' si no tienes)");
        System.out.println("2. Crear nueva unidad familiar");
        System.out.println("3. Volver");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    public void mostrarMenuPrincipal() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Ver lista de compra");
        System.out.println("2. Ver productos");
        System.out.println("3. Configuración");
        System.out.println("4. Cerrar sesión");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    public void mostrarMenuProductos() {
        System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
        System.out.println("0. Volver al menú principal");
        System.out.println("1. Ver todos los productos");
        System.out.println("2. Añadir nuevo producto");
        System.out.println("3. Filtrar productos");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    public void mostrarMenuListaProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        System.out.println("0. Volver atrás");
        System.out.println("1. Filtrar resultados");
        System.out.println("2. Ordenar resultados");
        System.out.println("3. Seleccionar producto");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    public void mostrarMenuFiltros() {
        System.out.println("\n--- FILTRAR PRODUCTOS ---");
        System.out.println("0. Volver atrás");
        System.out.println("1. Por categoría");
        System.out.println("2. Por marca");
        System.out.println("3. Por supermercado");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    public void mostrarMenuCategorias(List<String> categorias) {
        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i));
        }
        System.out.println("0. Volver atrás");
        System.out.print("Seleccione categoría: ");
    }

    @Override
    public void mostrarMenuSubcategorias(List<String> subcategorias) {
        System.out.println("\n--- SUBCATEGORÍAS ---");
        for (int i = 0; i < subcategorias.size(); i++) {
            System.out.println((i + 1) + ". " + subcategorias.get(i));
        }
        System.out.println("0. Volver atrás");
        System.out.print("Seleccione subcategoría: ");
    }

    @Override
    public void mostrarMenuOrden() {
        System.out.println("\n--- ORDENAR POR ---");
        System.out.println("0. Volver atrás");
        System.out.println("1. Precio total - Más barato primero");
        System.out.println("2. Precio total - Más caro primero");
        System.out.println("3. Precio por unidad - Más barato primero");
        System.out.println("4. Precio por unidad - Más caro primero");
        System.out.println("5. Nombre (A-Z)");
        System.out.println("6. Nombre (Z-A)");
        System.out.println("7. Marca (A-Z)");
        System.out.println("8. Marca (Z-A)");
        System.out.print("Seleccione opción: ");
    }

    @Override
    public void mostrarDetalleProducto(Producto producto) {
        System.out.println("\n--- DETALLE DE PRODUCTO ---");
        System.out.println("Nombre: " + producto.getNombre());
        System.out.println("Marca: " + producto.getMarca());
        System.out.println("Precio: €" + producto.getPrecio() + " (Clic para ver histórico)");
        System.out.println("Puntuación: " + obtenerEstrellas(producto.getPuntuacionMedia()));
        System.out.println("Categoría: " + producto.getCategoria());
        System.out.println("Subcategoría: " + producto.getSubcategoria());
        System.out.println("Supermercados: " + String.join(", ", producto.getSupermercados()));
        System.out.println("Código de barras: " + producto.getCodigoBarras());
        System.out.println("\n1. Editar precio  2. Ver histórico precios  3. Ver puntuaciones  0. Volver");
    }

    private String obtenerEstrellas(double puntuacion) {
        int estrellasLlenas = (int) puntuacion;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(i < estrellasLlenas ? "★" : "☆");
        }
        return sb.toString() + " (" + puntuacion + "/5)";
    }

    @Override
    public void mostrarListaCompra(ListaCompra lista) {
        System.out.println("\n--- LISTA DE COMPRA: " + lista.getNombre() + " ---");
        System.out.println("------------------------------------------------------");
        System.out.printf("| %-20s | %-15s | %-10s | %-8s |\n",
                "Producto", "Marca", "Precio", "Super");
        System.out.println("------------------------------------------------------");

        for (Map.Entry<Integer, Integer> entry : lista.getProductosCantidad().entrySet()) {
            Producto p = modelo.obtenerProductoPorId(entry.getKey());
            if (p != null) {
                System.out.printf("| %-20s | %-15s | €%-9.2f | %-8s |\n",
                        p.getNombre(), p.getMarca(), p.getPrecio(),
                        p.getSupermercados().get(0));
            }
        }
        System.out.println("------------------------------------------------------");
        System.out.println("1. Añadir producto  2. Generar automática  3. Marcar completada  0. Volver");
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println("> " + mensaje);
    }

    @Override
    public void mostrarError(String error) {
        System.out.println("ERROR: " + error);
    }

    @Override
    public String obtenerEntrada(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public int obtenerOpcionNumerica(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= min && opcion <= max) {
                    return opcion;
                } else {
                    mostrarError("Opción inválida. Debe ser entre " + min + " y " + max);
                }
            } catch (NumberFormatException e) {
                mostrarError("Entrada inválida. Por favor ingrese un número.");
            }
        }
    }

    @Override
    public double obtenerPrecio(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                mostrarError("Entrada inválida. Por favor ingrese un número (ej: 2.99).");
            }
        }
    }
}
public class Gestor {
    private Vista vista;
    private Modelo modelo;
    private boolean sesionActiva;
    private boolean familiaAsignada;

    public Gestor() {
        this.modelo = new Modelo();
        this.vista = new VistaConsolaMejorada(modelo);
        this.sesionActiva = false;
        this.familiaAsignada = false;
    }

    public void iniciar() {
        while (true) {
            if (!sesionActiva) {
                mostrarMenuInicial();
            } else if (!familiaAsignada) {
                mostrarMenuSinFamilia();
            } else {
                mostrarMenuPrincipal();
            }
        }
    }

    private void mostrarMenuInicial() {
        vista.mostrarMenuInicial();
        int opcion = vista.obtenerOpcionNumerica("", 1, 3);

        switch (opcion) {
            case 1:
                iniciarSesion();
                break;
            case 2:
                registrarUsuario();
                break;
            case 3:
                System.exit(0);
        }
    }

    private void iniciarSesion() {
        vista.mostrarLogin();
        // Lógica de inicio de sesión
        String usuario = vista.obtenerEntrada("Usuario: ");
        String contrasena = vista.obtenerEntrada("Contraseña: ");

        // Aquí iría la validación con el modelo
        sesionActiva = true; // Simulamos login exitoso
        vista.mostrarMensaje("Sesión iniciada correctamente");
    }

    private void registrarUsuario() {
        vista.mostrarRegistro();
        // Lógica de registro
        String usuario = vista.obtenerEntrada("Nuevo usuario: ");
        String contrasena = vista.obtenerEntrada("Nueva contraseña: ");

        // Aquí iría el registro en el modelo
        sesionActiva = true; // Simulamos registro exitoso
        vista.mostrarMensaje("Usuario registrado e iniciado sesión");
    }

    private void mostrarMenuSinFamilia() {
        vista.mostrarMenuSinFamilia();
        int opcion = vista.obtenerOpcionNumerica("", 1, 3);

        switch (opcion) {
            case 1:
                unirseAFamilia();
                break;
            case 2:
                crearFamilia();
                break;
            case 3:
                cerrarSesion();
                break;
        }
    }

    private void unirseAFamilia() {
        vista.mostrarMenuUnirseCrearFamilia();
        int opcion = vista.obtenerOpcionNumerica("", 1, 3);

        if (opcion == 1) {
            String codigo = vista.obtenerEntrada("Ingrese código de familia (o 'N' si no tiene): ");
            if (!codigo.equalsIgnoreCase("N")) {
                // Lógica para unirse a familia
                familiaAsignada = true;
                vista.mostrarMensaje("Te has unido a la familia exitosamente");
            }
        } else if (opcion == 2) {
            crearFamilia();
        }
    }

    private void crearFamilia() {
        // Lógica para crear nueva familia
        familiaAsignada = true;
        vista.mostrarMensaje("Nueva familia creada exitosamente");
    }

    private void cerrarSesion() {
        sesionActiva = false;
        familiaAsignada = false;
        vista.mostrarMensaje("Sesión cerrada correctamente");
    }

    private void mostrarMenuPrincipal() {
        vista.mostrarMenuPrincipal();
        int opcion = vista.obtenerOpcionNumerica("", 1, 4);

        switch (opcion) {
            case 1:
                gestionarListaCompra();
                break;
            case 2:
                gestionarProductos();
                break;
            case 3:
                mostrarConfiguracion();
                break;
            case 4:
                cerrarSesion();
                break;
        }
    }

    private void gestionarListaCompra() {
        // Implementar lógica para gestionar lista de compra
        ListaCompra lista = new ListaCompra("Mi lista");
        vista.mostrarListaCompra(lista);
        // Más lógica aquí...
    }

    private void gestionarProductos() {
        vista.mostrarMenuProductos();
        int opcion = vista.obtenerOpcionNumerica("", 0, 3);

        if (opcion == 1) {
            // Mostrar todos los productos
            vista.mostrarMenuListaProductos();
            // Más lógica aquí...
        } else if (opcion == 2) {
            // Añadir nuevo producto
        } else if (opcion == 3) {
            // Filtrar productos
            vista.mostrarMenuFiltros();
            // Más lógica aquí...
        }
    }

    private void mostrarConfiguracion() {
        // Implementar lógica de configuración
        vista.mostrarMensaje("Configuración (no implementado aún)");
    }

    public static void main(String[] args) {
        Gestor aplicacion = new Gestor();
        aplicacion.iniciar();
    }
}
