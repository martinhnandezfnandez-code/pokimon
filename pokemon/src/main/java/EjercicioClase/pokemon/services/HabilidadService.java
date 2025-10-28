package EjercicioClase.pokemon.services;

import EjercicioClase.pokemon.entities.Habilidad;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public interface HabilidadService {
    Habilidad obtenerHabilidad(String jsonCompleto) throws JsonProcessingException;
    Habilidad obtenerHabilidadPorNombre(String nombreHab) throws IOException, URISyntaxException;
}
