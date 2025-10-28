package EjercicioClase.pokemon.services.servicesImpl;

import EjercicioClase.pokemon.services.ConexionApi;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;


@Service
public class ConexionApiImpl implements ConexionApi {


    /**
     * @param nombre Busca el pokemon que le digas para buscarlo en pokeapi
     */
    @Override
    public String getPokeJson(String nombre) throws IOException, URISyntaxException {
        String url = "https://pokeapi.co/api/v2/pokemon/";
        url = url + nombre;
        URI urlPokemon = new URI(url);

        HttpURLConnection connection = (HttpURLConnection) urlPokemon.toURL().openConnection();
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
            return response.toString();
        } else {
            throw new RuntimeException("Error de connexion con la api Código: " + responseCode);
        }
    }
}
