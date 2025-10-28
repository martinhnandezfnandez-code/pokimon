package EjercicioClase.pokemon;

import EjercicioClase.pokemon.entities.Pokemon;
import EjercicioClase.pokemon.services.PokemonService;
import EjercicioClase.pokemon.services.servicesImpl.PokemonServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class PokemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokemonApplication.class, args);

    }

    @Bean
    CommandLineRunner commandLineRunner(PokemonService pokemonService) {
        return args -> {
            Scanner sc = new Scanner(System.in);

            System.out.println("Dame el nomnre del pokemon");
            String nombre = sc.nextLine();
            Pokemon pokemon = pokemonService.obtenerPokemonPorNombre(nombre);
            System.out.println("=====Pokemon=====");
            System.out.println("Nombre: " + pokemon.getNombre());
            System.out.println("Estadisticas: " + pokemon.getEstadistica().toString());
        };
    }

}
