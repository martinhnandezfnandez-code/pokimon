package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

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
            response += "\tHabilidad 2= " + habilidad2.toString() + '\n';
        }

        response += "\tHabilidad oculta= " + habilidadOculta.toString() + '\n';

        return response;
    }
}