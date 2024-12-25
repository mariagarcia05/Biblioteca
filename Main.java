package gestionBiblioteca;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.setDni("12345678A");
        usuario.setNombre("Maria Garcia");
        usuario.setEmail("meri@gmail.com");
        usuario.setPassword("12345");
        usuario.setTipo("normal");

        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setEstado("Disponible");

        Libro libro = new Libro();
        libro.setIsbn("978-3-16-148410-0");
        libro.setTitulo("El Gran Libro");
        libro.setAutor("Autor Desconocido");

        ejemplar.setIsbn(libro);

        GestionBiblioteca gestionBiblioteca = new GestionBiblioteca();

        System.out.println("Intentando registrar préstamo...");
        boolean prestamoRegistrado = gestionBiblioteca.registrarPrestamo(usuario, ejemplar);
        System.out.println("Préstamo registrado: " + prestamoRegistrado);

        System.out.println("Intentando registrar devolución...");
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setEjemplar(ejemplar);
        prestamo.setFechaInicio(LocalDate.now().minusDays(16));

        boolean devolucionRegistrada = gestionBiblioteca.registrarDevolucion(prestamo);
        System.out.println("Devolución registrada: " + devolucionRegistrada);

        System.out.println("Estado final del usuario: " + usuario.getPenalizacionHasta());
        System.out.println("Estado final del ejemplar: " + ejemplar.getEstado());
    }
}
