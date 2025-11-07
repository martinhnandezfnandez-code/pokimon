package EjercicioClase.pokemon;

import EjercicioClase.pokemon.entities.Pokemon;
import EjercicioClase.pokemon.services.PokemonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class PokemonApplication {

    static void main(String[] args) {
        SpringApplication.run(PokemonApplication.class, args);

    }

    @Bean
    CommandLineRunner commandLineRunner(PokemonService pokemonService) {
        return args -> {
            Scanner sc = new Scanner(System.in);

            System.out.println("Dime el nombre del pokemon");
            String nombre = sc.nextLine();
            Pokemon pokemon = pokemonService.obtenerPokemonPorNombre(nombre);
            System.out.println("=======Pokemon=======");
            System.out.println("Nombre: " + pokemon.getId());
            System.out.println("Estadísticas: " + pokemon.getEstadistica().toString());
            System.out.println("Tipos: " + pokemon.getTipos().toString());
            System.out.println("Habilidades: \n" + pokemon.getHabilidades().toString());
        };
    }

}
