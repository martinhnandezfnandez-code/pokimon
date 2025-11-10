package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Nombre;
import EjercicioClase.pokemon.services.NombreService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class NombreServiceImpl implements NombreService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Obtiene el nombre buscandolo en el Json
     *
     * @param jsonCompleto Json del nombre
     * @return String con el nombre del pokemon
     */
    @Override
    public Nombre obtenerNombre(String jsonCompleto) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonCompleto);
        JsonNode formsArray = rootNode.get("forms");
        Nombre nombre = new Nombre();

            JsonNode nombres = rootNode.get("name");
            JsonNode id = rootNode.get("id");
            nombre.setNombre(nombres.asText());
            nombre.setId(id.asText());

        return nombre;
    }
}
