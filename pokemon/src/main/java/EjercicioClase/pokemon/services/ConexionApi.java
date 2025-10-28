package EjercicioClase.pokemon.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;


@Service
public interface ConexionApi {
    String getPokeJson(String nombre) throws IOException, URISyntaxException;
}
