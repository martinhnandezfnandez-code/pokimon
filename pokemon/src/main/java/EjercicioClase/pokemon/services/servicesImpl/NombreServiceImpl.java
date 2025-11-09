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

        if (formsArray != null) {
            JsonNode firstForm = formsArray.get(0);
            JsonNode nameNode = firstForm.get("name");

            if (nameNode != null) {
                nombre.setNombre(nameNode.asText());
            }
        }
        JsonNode gameIndices = rootNode.get("game_indices");
        if (gameIndices != null && gameIndices.isArray()) {
            for (JsonNode entry : gameIndices) {
                if (entry == null || entry.isNull()) continue;
                JsonNode versionNode = entry.get("version");
                if (versionNode == null || versionNode.isNull()) continue;
                JsonNode versionName = versionNode.get("name");
                if (versionName == null || versionName.isNull()) continue;

                if ("white-2".equals(versionName.asText())) {
                    JsonNode idx = entry.get("game_index");
                    if (idx != null && !idx.isNull()) {
                        // Si Nombre.setId espera String:
                        nombre.setId(idx.asText()); // conserva el valor tal cual el campo JSON
                        // Si setId espera int, usa:
                        // nombre.setId(idx.asInt());
                    }
                    break;
                }
            }
        }

        return nombre;
    }
}
