package EjercicioClase.pokemon.services;

import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public interface ConexionApi {
    String getPokeJson(String nombre) throws IOException;
}
