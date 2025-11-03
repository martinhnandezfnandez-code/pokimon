package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Tipo {
    private String nombre;
    private List<String> dobleDanioRecibido;
    private List<String> mitadDanioRecibido;
    private List<String> noDanioRecibido;

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

        if (tipo.getDobleDanioRecibido() != null) {
            this.dobleDanioRecibido = tipo.getDobleDanioRecibido();
        }

        if (tipo.getMitadDanioRecibido() != null) {
            this.mitadDanioRecibido = tipo.getMitadDanioRecibido();
        }

        if (tipo.getNoDanioRecibido() != null) {
            this.noDanioRecibido = tipo.getNoDanioRecibido();
        }
    }

    @Override
    public String toString() {
        return "Tipo " +
                "nombre= " + nombre + '\n';

    }
}
