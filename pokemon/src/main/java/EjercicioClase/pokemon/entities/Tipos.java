package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Constructor de clase de tipos
 */
@Setter
@Getter
public class Tipos {
    private String tipoPrimario, tipoSecundario;

    public Tipos() {
        super();
    }

    /**
     * @return Devuelve un String con los tipos o tipo basándonos en el pokemon
     */
    @Override
    public String toString() {
        String response = "\n\ttipoPrimario= " + tipoPrimario + '\n';

        if (tipoSecundario != null) {
            response += "\ttipoSegundario= " + tipoSecundario + "\n";
        }
        return response;
    }
}
