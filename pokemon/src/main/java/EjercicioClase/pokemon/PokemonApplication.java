package EjercicioClase.pokemon;

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
    CommandLineRunner commandLineRunner() {
        return args -> {
            Scanner sc = new Scanner(System.in);

            System.out.println("Dame el ");
        };
    }

}
