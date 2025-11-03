package EjercicioClase.pokemon.traductor;

import java.util.HashMap;
import java.util.Map;

/**
 * Constructor de clase de traduccion de tipos pokemon
 */
public class Traductor {

    private static final Map<String, String> diccionarioEN_ES;

    static {
        diccionarioEN_ES = new HashMap<>();

        diccionarioEN_ES.put("normal", "normal");
        diccionarioEN_ES.put("steel", "acero");
        diccionarioEN_ES.put("water", "agua");
        diccionarioEN_ES.put("bug", "bicho");
        diccionarioEN_ES.put("dragon", "dragón");
        diccionarioEN_ES.put("electric", "eléctrico");
        diccionarioEN_ES.put("ghost", "fantasma");
        diccionarioEN_ES.put("fire", "fuego");
        diccionarioEN_ES.put("fairy", "hada");
        diccionarioEN_ES.put("ice", "hielo");
        diccionarioEN_ES.put("fighting", "lucha");
        diccionarioEN_ES.put("grass", "planta");
        diccionarioEN_ES.put("psychic", "psíquico");
        diccionarioEN_ES.put("rock", "roca");
        diccionarioEN_ES.put("dark", "siniestro");
        diccionarioEN_ES.put("ground", "tierra");
        diccionarioEN_ES.put("poison", "veneno");
        diccionarioEN_ES.put("flying", "volador");
    }


    private Traductor() {
        super();
    }

    /**
     * Un tipo de pokemon en inglés lo traduce a español
     *
     * @param palabra String del tipo pokemon en ingles
     * @return String tipo pokemon en español
     */
    public static String traducirIngles(String palabra) {
        String palabraMinuscula = palabra.toLowerCase().trim();
        return diccionarioEN_ES.getOrDefault(palabraMinuscula,
                "Translation not available for: " + palabra);
    }
}