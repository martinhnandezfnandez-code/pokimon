package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Tipos;
import EjercicioClase.pokemon.services.TiposService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class TiposServiceImpl implements TiposService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Tipos obtenerTipos(String jsonCompleto) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonCompleto);
        JsonNode statsArray = rootNode.get("types");
        Tipos tipos = new Tipos();

        for (JsonNode stat : statsArray) {

            JsonNode nameType = stat.get("type");


            String nombreType = String.valueOf(nameType.get("name"));
            String nSlot = String.valueOf(stat.get("slot"));
            switch (nSlot) {
                case "1":
                    tipos.setTipoPrimario(nombreType.replace("\"", ""));
                    break;
                case "2":
                    tipos.setTipoSecundario(nombreType.replace("\"", ""));
                    break;
                default://a luis no le interesa
            }
        }

        return tipos;
    }
}
