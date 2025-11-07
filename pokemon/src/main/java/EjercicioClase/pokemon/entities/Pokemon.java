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
    private Habilidades habilidades;
    private Nombre id;


    public Pokemon(Estadisticas estadistica, String nombre, Tipos tipo, Habilidades habilidades, Nombre id) {
        this.estadistica = estadistica;
        this.nombre = nombre;
        this.tipos = tipo;
        this.habilidades = habilidades;
        this.id = id;

    }
}
