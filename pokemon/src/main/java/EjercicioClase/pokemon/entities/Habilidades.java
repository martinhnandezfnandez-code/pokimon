package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Habilidades {
    private String habilidad, habilidad2, habilidadOculta;

    public Habilidades() {
        super();
    }

    @Override
    public String toString() {
        String response = "\tHabilidad= " + habilidad + '\n';

        if (habilidad2 != null) {
            response += "\tHabilidad 2= " + habilidad2 + '\n';
        }

        response += "\tHabilidad oculta= " + habilidadOculta + '\n';

        return response;
    }
}