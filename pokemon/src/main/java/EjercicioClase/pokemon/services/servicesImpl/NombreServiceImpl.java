package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Nombre;
import EjercicioClase.pokemon.services.NombreService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class NombreServiceImpl implements NombreService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Nombre obtenerNombre(String jsonCompleto) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonCompleto);
        JsonNode formsArray = rootNode.get("forms");

        Nombre nombre = new Nombre();

        if (formsArray != null && formsArray.isArray() && formsArray.size() > 0) {
            JsonNode firstForm = formsArray.get(0);
            JsonNode nameNode = firstForm.get("name");

            if (nameNode != null) {
                nombre.setNombre(nameNode.asText());
            }
        }

        return nombre;
    }
}
