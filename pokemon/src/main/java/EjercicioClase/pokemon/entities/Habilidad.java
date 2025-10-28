package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Habilidad {
    private String nombre, descripcion;

    public Habilidad() {
        super();
    }

    public Habilidad(String nombre) {
        this.nombre = nombre;
    }

    public Habilidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Habilidad(Habilidad habilidad) {
        if (habilidad.getNombre() != null) {
            this.nombre = habilidad.getNombre();
        }

        if (habilidad.getDescripcion() != null) {
            this.descripcion = habilidad.getDescripcion();
        }
    }

    @Override
    public String toString() {
        return "\t Nombre = " + nombre + "\t descipcion = " + descripcion + '\n';
    }
}
