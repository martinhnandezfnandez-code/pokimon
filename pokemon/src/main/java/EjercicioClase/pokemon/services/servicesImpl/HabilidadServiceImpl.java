package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Habilidad;
import EjercicioClase.pokemon.services.ConexionApi;
import EjercicioClase.pokemon.services.HabilidadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class HabilidadServiceImpl implements HabilidadService {

    public static final int CONSULTA_HABILIDAD_ID = 2;
    @Autowired
    private ConexionApi conexionApi;

    @Override
    public Habilidad obtenerHabilidad(String jsonCompleto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonCompleto);

        Habilidad habilidad = new Habilidad();

        // Obtener el nombre
        habilidad.setNombre(root.get("name").asText());

        // Obtener la descripción en español
        JsonNode entries = root.get("flavor_text_entries");

        for (JsonNode entry : entries) {
            if (entry.get("language").get("name").asText().equals("es")) {
                habilidad.setDescripcion(entry.get("flavor_text").asText().replace("\n", ""));
                break;
            }
        }

        return habilidad;
    }

    @Override
    public Habilidad obtenerHabilidadPorNombre(String nombreHab) throws IOException, URISyntaxException {
        String jsonHab = conexionApi.getPokeJsonByName(nombreHab, CONSULTA_HABILIDAD_ID);

        return obtenerHabilidad(jsonHab);
    }
}
