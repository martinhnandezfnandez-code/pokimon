package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Tipo;
import EjercicioClase.pokemon.services.ConexionApi;
import EjercicioClase.pokemon.services.TipoService;
import EjercicioClase.pokemon.traductor.Traductor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Service
public class TipoServiceImpl implements TipoService {


    public static final int CONSULTA_TIPO_ID = 3;
    @Autowired
    private ConexionApi conexionApi;

    /**
     * Crador de clase que obtiene el tipo del pokemon por el nombre mandandolo al servicio de conexion api
     *
     * @return devuelve un String del Json del tipo del pokemon
     */
    @Override
    public Tipo obtenerTipoPorNombre(String nombre) throws IOException, URISyntaxException {
        String json = conexionApi.getPokeJsonByName(nombre, CONSULTA_TIPO_ID);

        return obtenerTipo(json);
    }

    /**
     * Busca y saca el nombre de los tipos en funcion del daño y los traduce
     *
     * @param json String del Json del tipo del pokemon
     * @return Una lista String de los tipos del pokemon en función de sus respectivos daños en español
     */
    private Tipo obtenerTipo(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        Tipo tipo = new Tipo();

        JsonNode nodoListadoDeNombres = root.get("names");
        for (JsonNode nodoEnCurso : nodoListadoDeNombres) {
            if (nodoEnCurso.get("language").get("name").asText().equals("es")) {
                tipo.setNombre(nodoEnCurso.get("name").asText());
                break;
            }
        }

        JsonNode danioDobleListadoRelaciones = root.get("damage_relations").get("double_damage_from");
        JsonNode danioMitadListadoRelaciones = root.get("damage_relations").get("half_damage_from");
        JsonNode danioNoListadoRelaciones = root.get("damage_relations").get("no_damage_from");
        List<String> nombreRelacionesList = new ArrayList<>();

        for (JsonNode relacion : danioDobleListadoRelaciones) {
            String nombreIngles = relacion.get("name").asText();
            nombreRelacionesList.add(Traductor.traducirIngles(nombreIngles));
        }
        tipo.setDobleDanioRecibido(nombreRelacionesList);

        nombreRelacionesList = new ArrayList<>();

        for (JsonNode relacion : danioMitadListadoRelaciones) {
            String nombreIngles = relacion.get("name").asText();
            nombreRelacionesList.add(Traductor.traducirIngles(nombreIngles));
        }
        tipo.setMitadDanioRecibido(nombreRelacionesList);

        nombreRelacionesList = new ArrayList<>();

        for (JsonNode relacion : danioNoListadoRelaciones) {
            String nombreIngles = relacion.get("name").asText();
            nombreRelacionesList.add(Traductor.traducirIngles(nombreIngles));
        }
        tipo.setNoDanioRecibido(nombreRelacionesList);

        return tipo;
    }
}
