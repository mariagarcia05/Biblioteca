package gestionBiblioteca;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class DAOGenericoBiblioteca <T>{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion_biblioteca");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Class<T> clase;

    public DAOGenericoBiblioteca(Class<T> clase) {

        this.clase = clase;
    }


    public void addClase(T t) {
        tx.begin();
        em.persist(t);
        tx.commit();
    }

    public void updateClase(T t) {
        tx.begin();
        em.merge(t);
        tx.commit();
    }

    public void removeClase(T t) {
        tx.begin();
        em.remove(t);
        tx.commit();
    }
    public void removeClaseId(int id) {
        T objeto = em.find(clase,id);
        tx.begin();
        em.remove(objeto);
        tx.commit();
    }

    public T getClase(int id) {
        return em.find(clase,id);
    }

    public List<T> getAllClase() {
        String name = clase.getName();
        return em.createQuery("select e From "+ name+ " e",clase).getResultList();
    }

    public List<Prestamo> obtenerPrestamosDeUsuario(int usuarioId) {
        return em.createQuery("SELECT p FROM Prestamo p WHERE p.usuario.id = :usuarioId", Prestamo.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }




}
