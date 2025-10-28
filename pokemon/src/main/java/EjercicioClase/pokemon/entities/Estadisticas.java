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

    public Estadisticas(double hp, double ataque, double defensa, double ataqueEspecial, double defensaEspecial, double velocidad) {
        this.hp = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
    }

    @Override
    public String toString() {
        return "\tHp=" + hp +
                "\tAtaque=" + ataque +
                "\tDefensa=" + defensa +
                "\tAtaqueEspecial=" + ataqueEspecial +
                ",\tDefensaEspecial=" + defensaEspecial +
                ",\tVelocidad=" + velocidad;
    }
}