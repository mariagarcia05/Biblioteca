package empresaProyectos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class EmpleadoProyectoId implements java.io.Serializable {
    private static final long serialVersionUID = 625825676561347875L;
    @Column(name = "idEmp", nullable = false)
    private Integer idEmp;

    @Column(name = "idProy", nullable = false)
    private Integer idProy;

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public Integer getIdProy() {
        return idProy;
    }

    public void setIdProy(Integer idProy) {
        this.idProy = idProy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmpleadoProyectoId entity = (EmpleadoProyectoId) o;
        return Objects.equals(this.idProy, entity.idProy) &&
                Objects.equals(this.idEmp, entity.idEmp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProy, idEmp);
    }

}