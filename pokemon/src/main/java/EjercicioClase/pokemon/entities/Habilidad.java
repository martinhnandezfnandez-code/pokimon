package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que construye la habilidad con su nombre y description
 */
@Getter
@Setter
public class Habilidad {
    private String nombre, descripcion;

    public Habilidad() {
        super();
    }

    public Habilidad(Habilidad habilidad) {
        if (habilidad.getNombre() != null) {
            this.nombre = habilidad.getNombre();
        }

        if (habilidad.getDescripcion() != null) {
            this.descripcion = habilidad.getDescripcion();
        }
    }

    /**
     * @return devuelve String de con el nombre y la descripción de ella
     */
    @Override
    public String toString() {
        return "\t" + nombre + "\t Description = " + descripcion + '\n';
    }
}
