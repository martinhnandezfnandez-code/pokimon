package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;
/**
 * Clase que construye todas las habilidades del pokemon
 */
@Setter
@Getter
public class Habilidades {
    private Habilidad habilidad, habilidad2, habilidadOculta;

    public Habilidades() {
        super();
    }

    @Override
    public String toString() {
        String response = "\tHabilidad= " + habilidad.toString() + '\n';

        if (habilidad2 != null) {
            response += "\tHabilidad 2= " + habilidad2 + '\n';
        }

        response += "\tHabilidad oculta= " + habilidadOculta.toString() + '\n';

        return response;
    }
}