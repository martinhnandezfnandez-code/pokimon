package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Tipo {
    private String nombre;
    private List<String> dobleDañoRecibido;
    private List<String> mitadDañoRecibido;
    private List<String> noDañoRecibido;

    public Tipo() {
        super();
    }

    /**
     * Contructor de tipo
     */
    public Tipo(Tipo tipo) {
        if (tipo.getNombre() != null) {
            this.nombre = tipo.getNombre();
        }

        if (tipo.getDobleDañoRecibido() != null) {
            this.dobleDañoRecibido = tipo.getDobleDañoRecibido();
        }

        if (tipo.getMitadDañoRecibido() != null) {
            this.mitadDañoRecibido = tipo.getMitadDañoRecibido();
        }

        if (tipo.getNoDañoRecibido() != null) {
            this.noDañoRecibido = tipo.getNoDañoRecibido();
        }
    }

    @Override
    public String toString() {
        return "Tipo " +
                "nombre= " + nombre + '\n';

    }
}
