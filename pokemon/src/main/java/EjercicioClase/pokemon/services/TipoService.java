package EjercicioClase.pokemon.services;

import EjercicioClase.pokemon.entities.Tipo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public interface TipoService {
    Tipo obtenerTipoPorNombre(String nombre) throws IOException, URISyntaxException;
}
