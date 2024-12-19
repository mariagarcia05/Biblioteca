package empresaProyectos;

import Applicacion.Empleado1;
import Applicacion.Proyecto;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "empleado_proyecto")
public class EmpleadoProyecto {
    @EmbeddedId
    private EmpleadoProyectoId id;

    @MapsId("idEmp")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idEmp", nullable = false)
    private Empleado1 idEmp;

    @MapsId("idProy")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idProy", nullable = false)
    private Proyecto idProy;

    public EmpleadoProyectoId getId() {
        return id;
    }

    public void setId(EmpleadoProyectoId id) {
        this.id = id;
    }

    public Empleado1 getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Empleado1 idEmp) {
        this.idEmp = idEmp;
    }

    public Proyecto getIdProy() {
        return idProy;
    }

    public void setIdProy(Proyecto idProy) {
        this.idProy = idProy;
    }

}