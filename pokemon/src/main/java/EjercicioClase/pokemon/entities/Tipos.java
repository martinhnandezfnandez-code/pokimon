package EjercicioClase.pokemon.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Constructor de clase de tipos con cálculo automático de debilidades y fortalezas
 */
@Getter
@Setter
public class Tipos {
    private Tipo tipoPrimario;
    private Tipo tipoSecundario;

    private Map<String, Double> multiplicadores;
    private List<DebilidadFortaleza> debilidades;
    private List<DebilidadFortaleza> fortalezas;
    private List<String> inmunidades;

    public Tipos() {
        super();
        this.multiplicadores = new HashMap<>();
        this.debilidades = new ArrayList<>();
        this.fortalezas = new ArrayList<>();
        this.inmunidades = new ArrayList<>();
    }

    /**
     * Establece el tipo primario y calcula las debilidades y fortalezas
     */
    public void setTipoPrimario(Tipo tipoPrimario) {
        this.tipoPrimario = tipoPrimario;
        calculateDebilidadesFortalezas();
    }

    /**
     * Establece el tipo secundario y calcula las debilidades y fortalezas
     */
    public void setTipoSecundario(Tipo tipoSecundario) {
        this.tipoSecundario = tipoSecundario;
        calculateDebilidadesFortalezas();
    }

    /**
     * Calcula automáticamente las debilidades y fortalezas basándose en los tipos del pokemon
     */
    public void calculateDebilidadesFortalezas() {
        // Limpiar resultados anteriores
        multiplicadores.clear();
        debilidades.clear();
        fortalezas.clear();
        inmunidades.clear();

        if (tipoPrimario == null) {
            return;
        }

        Set<String> todosTipos = obtenerTodosTipos();

        for (String tipoAtaque : todosTipos) {
            double multiplicador = calcularMultiplicador(tipoAtaque);
            multiplicadores.put(tipoAtaque, multiplicador);

            if (multiplicador == 0) {
                inmunidades.add(tipoAtaque);
            } else if (multiplicador > 1.0) {
                debilidades.add(new DebilidadFortaleza(tipoAtaque, multiplicador));
            } else if (multiplicador < 1.0) {
                fortalezas.add(new DebilidadFortaleza(tipoAtaque, multiplicador));
            }
        }

        debilidades.sort((a, b) -> Double.compare(b.multiplicador(), a.multiplicador()));
        fortalezas.sort(Comparator.comparingDouble(DebilidadFortaleza::multiplicador));
        inmunidades.sort(String::compareTo);
    }

    /**
     * Calcula el multiplicador de daño para un tipo de ataque específico
     */
    private double calcularMultiplicador(String tipoAtaque) {
        double multiplicador1 = calcularMultiplicadorTipoSimple(tipoAtaque, tipoPrimario);
        double multiplicador2 = tipoSecundario != null ?
                calcularMultiplicadorTipoSimple(tipoAtaque, tipoSecundario) : 1.0;

        return multiplicador1 * multiplicador2;
    }

    /**
     * Calcula el multiplicador para un tipo
     */
    private double calcularMultiplicadorTipoSimple(String tipoAtaque, Tipo tipo) {

        if (tipo.getNoDanioRecibido() != null &&
                tipo.getNoDanioRecibido().stream().anyMatch(t -> t.equalsIgnoreCase(tipoAtaque))) {
            return 0.0;
        }


        if (tipo.getDobleDanioRecibido() != null &&
                tipo.getDobleDanioRecibido().stream().anyMatch(t -> t.equalsIgnoreCase(tipoAtaque))) {
            return 2.0;
        }


        if (tipo.getMitadDanioRecibido() != null &&
                tipo.getMitadDanioRecibido().stream().anyMatch(t -> t.equalsIgnoreCase(tipoAtaque))) {
            return 0.5;
        }

        return 1.0;
    }

    /**
     * Obtiene todos los tipos mencionados en ambos tipos
     */
    private Set<String> obtenerTodosTipos() {
        Set<String> tipos = new HashSet<>();

        agregarTiposDeListasCompletas(tipos, tipoPrimario);

        if (tipoSecundario != null) {
            agregarTiposDeListasCompletas(tipos, tipoSecundario);
        }

        return tipos;
    }

    /**
     * Agrega todos los tipos de las listas de un tipo de daño o inmunidad
     */
    private void agregarTiposDeListasCompletas(Set<String> tipos, Tipo tipo) {
        if (tipo.getDobleDanioRecibido() != null) {
            tipos.addAll(tipo.getDobleDanioRecibido());
        }
        if (tipo.getMitadDanioRecibido() != null) {
            tipos.addAll(tipo.getMitadDanioRecibido());
        }
        if (tipo.getNoDanioRecibido() != null) {
            tipos.addAll(tipo.getNoDanioRecibido());
        }
    }

    /**
     * Obtiene solo las debilidades críticas
     */
    public List<DebilidadFortaleza> getDebilidadesCriticas() {
        return debilidades.stream()
                .filter(d -> d.multiplicador() == 4.0)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene solo las debilidades normales
     */
    public List<DebilidadFortaleza> getDebilidades() {
        return debilidades.stream()
                .filter(d -> d.multiplicador() == 2.0)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene solo las resistencias fuertes
     */
    public List<DebilidadFortaleza> getResistenciasFuertes() {
        return fortalezas.stream()
                .filter(f -> f.multiplicador() == 0.25)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene solo las resistencias normales
     */
    public List<DebilidadFortaleza> getResistencias() {
        return fortalezas.stream()
                .filter(f -> f.multiplicador() == 0.5)
                .collect(Collectors.toList());
    }

    /**
     * @return Devuelve un String con los tipos y análisis completo de debilidades/fortalezas
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        sb.append("\n   Primario: ").append(tipoPrimario.getNombre()).append("\n");
        if (tipoSecundario != null) {
            sb.append("   Secundario: ").append(tipoSecundario.getNombre()).append("\n");
        }
        sb.append("\n");


        List<DebilidadFortaleza> criticasTemp = getDebilidadesCriticas();
        if (!criticasTemp.isEmpty()) {
            sb.append("  Debilidad x4:\n");
            for (DebilidadFortaleza d : criticasTemp) {
                sb.append(String.format("\t•%s\n", d.tipo()));
            }
            sb.append("\n");
        }

        if (!getDebilidades().isEmpty()) {
            sb.append(" Debilidad x2:\n");
            for (DebilidadFortaleza d : getDebilidades()) {
                sb.append(String.format("\t•%s\n", d.tipo()));
            }
            sb.append("\n");
        }

        if (!getResistencias().isEmpty()) {
            sb.append("Resistencias 1/2:\n");
            for (DebilidadFortaleza f : getResistencias()) {
                sb.append(String.format("\t•%s\n", f.tipo()));
            }
            sb.append("\n");
        }


        List<DebilidadFortaleza> resistenciasFuertesTemp = getResistenciasFuertes();
        if (!resistenciasFuertesTemp.isEmpty()) {
            sb.append("Resistencia 1/4:\n");
            for (DebilidadFortaleza r : resistenciasFuertesTemp) {
                sb.append(String.format("\t•%s\n", r.tipo()));
            }
            sb.append("\n");
        }


        if (!inmunidades.isEmpty()) {
            sb.append("Inmunidad:\n");
            for (String inmunidad : inmunidades) {
                sb.append(String.format("\t• %s\n", inmunidad));
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    /**
         * Clase para representar un tipo y su multiplicador al daño
         */
        public record DebilidadFortaleza(String tipo, double multiplicador) {

        /**
             * @return Devuelve un String con su tipo y su multiplicador
             */
            @NonNull
            @Override
            public String toString() {
                return String.format("\n%s (x%.2f)", tipo, multiplicador);
            }
        }
}