package EjercicioClase.pokemon.entities;

import lombok.Data;

/**
 * Clase que define que es un pokemon
 */
@Data
public class Pokemon {
    private String nombre;
    private Estadisticas estadistica;
    private Tipos tipos;

    public Pokemon() {
        super();
    }

    public Pokemon(Estadisticas estadistica, String nombre, Tipos tipo) {
        this.estadistica = estadistica;
        this.nombre = nombre;
        this.tipos = tipo;
    }
}
