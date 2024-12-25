package gestionBiblioteca;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class GestionBiblioteca {

    private DAOGenericoBiblioteca<Usuario> daoUsuario = new DAOGenericoBiblioteca<>(Usuario.class);
    private DAOGenericoBiblioteca<Prestamo> daoPrestamo = new DAOGenericoBiblioteca<>(Prestamo.class);
    private DAOGenericoBiblioteca<Ejemplar> daoEjemplar = new DAOGenericoBiblioteca<>(Ejemplar.class);

    // Método para registrar préstamo
    public boolean registrarPrestamo(Usuario usuario, Ejemplar ejemplar) {
        if (!usuario.puedePedirPrestamo()) {
            return false;  // El usuario no puede realizar el préstamo
        }

        if (!ejemplar.estaDisponible()) {
            return false;  // El ejemplar no está disponible
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setEjemplar(ejemplar);
        prestamo.setFechaInicio(LocalDate.now());
        daoPrestamo.addClase(prestamo);
        return true;
    }

    // Método para registrar devolución
    public boolean registrarDevolucion(Prestamo prestamo) {
        if (prestamo.estaFueraDePlazo()) {
            // Penalización por devolución fuera de plazo
            int penalizacionDias = 15;
            prestamo.getUsuario().agregarPenalizacion(penalizacionDias);  // Correcto: acceder al usuario desde el préstamo
            daoUsuario.updateClase(prestamo.getUsuario());  // Actualizar usuario
        }

        // Actualizar estado de ejemplar a 'Disponible'
        Ejemplar ejemplar = prestamo.getEjemplar();
        ejemplar.setEstado("Disponible");
        daoEjemplar.updateClase(ejemplar);

        // Registrar fecha de devolución
        prestamo.setFechaDevolucion(LocalDate.now());
        daoPrestamo.updateClase(prestamo);
        return true;
    }

    // Método para obtener préstamos de un usuario
    public List<Prestamo> obtenerPrestamosUsuario(Usuario usuario) {
        return usuario.getPrestamos().stream()
                .filter(prestamo -> prestamo.getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }
}
