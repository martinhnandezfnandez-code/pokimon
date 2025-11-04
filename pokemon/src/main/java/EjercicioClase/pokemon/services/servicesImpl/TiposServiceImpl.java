package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Tipo;
import EjercicioClase.pokemon.entities.Tipos;
import EjercicioClase.pokemon.services.TipoService;
import EjercicioClase.pokemon.services.TiposService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiposServiceImpl implements TiposService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TipoService tipoService;

    /**
     * Creador de clase para los Dos posibles tipos del pokemon
     */
    @Override
    public Tipos obtenerTipos(String jsonCompleto) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonCompleto);
        JsonNode statsArray = rootNode.get("types");
        Tipos tipos = new Tipos();

        for (JsonNode stat : statsArray) {

            JsonNode nameType = stat.get("type");


            String nombreType = String.valueOf(nameType.get("name")).replace("\"", "");
            String nSlot = String.valueOf(stat.get("slot"));


            Tipo tipo = new Tipo(tipoService.obtenerTipoPorNombre(nombreType));
            switch (nSlot) {
                case "1":
                    tipos.setTipoPrimario(tipo);
                    break;
                case "2":
                    tipos.setTipoSecundario(tipo);
                    break;
                default://a luis no le interesa
            }
        }

        return tipos;
    }
}
