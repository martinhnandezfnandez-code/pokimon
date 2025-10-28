package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.entities.Pokemon;
import EjercicioClase.pokemon.services.ConexionApi;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ConexionApiImpl implements ConexionApi {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Pokemon obtenerPokemonPorNombre(String nombre) {
        return null;
    }

    /**
     * @param pokemon Busca el pokemon que le digas para buscarlo en pokeapi
     */
    private Pokemon getFromApiPokemon(String pokemon) throws IOException {
        String url = "https://pokeapi.co/api/v2/pokemon/";
        url = url + pokemon;
        URL urlPokemon = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlPokemon.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String respuesta;
            while ((respuesta = reader.readLine()) != null) {
                response.append(respuesta);
            }
            reader.close();
            String jsonPokemon = response.toString();

        } else {
            throw new RuntimeException("Error de connexion con la api Codigo: " + responseCode);

        }

    }
    private List<Integer> obtenerStatBase(String jsonCompleto) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonCompleto);
        JsonNode statsArray = rootNode.get("stats");

        List<Integer> basesValues = new ArrayList<>();

        for (JsonNode stat : statsArray) {
            JsonNode base = stat.get("base_stat");
            basesValues.add(base.asInt());
        }

        return basesValues;
    }
}
