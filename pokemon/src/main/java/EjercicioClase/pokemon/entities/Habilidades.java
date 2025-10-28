package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Habilidades {
    private String habilidad, habilidadOculta;

    public Habilidades() {
        super();
    }

    @Override
    public String toString() {
        return "Habilidades:\n" +
                "\tHabilidad= " + habilidad + '\n' +
                "\tHabilidad Oculta= " + habilidadOculta + '\n';
    }
}
