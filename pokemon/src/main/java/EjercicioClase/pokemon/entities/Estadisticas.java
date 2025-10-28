package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que define las estadisticas de un pokimon
 */
@Getter
@Setter
public class Estadisticas {
    private double hp, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad;

    public Estadisticas() {
        super();
    }

    /**
     * Metodo que escupe una cadena de texto con las estadísticas del pokemon
     * @return String con cadena de estadisticas
     */
    @Override
    public String toString() {
        return "\tHp=" + hp + "\n" +
                "\tAtaque=" + ataque + "\n" +
                "\tDefensa=" + defensa + "\n" +
                "\tAtaqueEspecial=" + ataqueEspecial + "\n" +
                "\tDefensaEspecial=" + defensaEspecial + "\n" +
                "\tVelocidad=" + velocidad;
    }
}