package EjercicioClase.pokemon.entities;

import lombok.Getter;
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

    // Resultados del cálculo
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
     * Establece el tipo primario y recalcula debilidades/fortalezas
     */
    public void setTipoPrimario(Tipo tipoPrimario) {
        this.tipoPrimario = tipoPrimario;
        calculateDebilidadesFortalezas();
    }

    /**
     * Establece el tipo secundario y recalcula debilidades/fortalezas
     */
    public void setTipoSecundario(Tipo tipoSecundario) {
        this.tipoSecundario = tipoSecundario;
        calculateDebilidadesFortalezas();
    }

    /**
     * Calcula automáticamente las debilidades y fortalezas basándose en los tipos actuales
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

        debilidades.sort((a, b) -> Double.compare(b.getMultiplicador(), a.getMultiplicador()));
        fortalezas.sort((a, b) -> Double.compare(a.getMultiplicador(), b.getMultiplicador()));
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

        if (tipo.getNoDañoRecibido() != null &&
                tipo.getNoDañoRecibido().stream().anyMatch(t -> t.equalsIgnoreCase(tipoAtaque))) {
            return 0.0;
        }


        if (tipo.getDobleDañoRecibido() != null &&
                tipo.getDobleDañoRecibido().stream().anyMatch(t -> t.equalsIgnoreCase(tipoAtaque))) {
            return 2.0;
        }


        if (tipo.getMitadDañoRecibido() != null &&
                tipo.getMitadDañoRecibido().stream().anyMatch(t -> t.equalsIgnoreCase(tipoAtaque))) {
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
     * Agrega todos los tipos de las listas de un tipo al set
     */
    private void agregarTiposDeListasCompletas(Set<String> tipos, Tipo tipo) {
        if (tipo.getDobleDañoRecibido() != null) {
            tipos.addAll(tipo.getDobleDañoRecibido());
        }
        if (tipo.getMitadDañoRecibido() != null) {
            tipos.addAll(tipo.getMitadDañoRecibido());
        }
        if (tipo.getNoDañoRecibido() != null) {
            tipos.addAll(tipo.getNoDañoRecibido());
        }
    }

    /**
     * Obtiene solo las debilidades críticas (x4)
     */
    public List<DebilidadFortaleza> getDebilidadesCriticas() {
        return debilidades.stream()
                .filter(d -> d.getMultiplicador() >= 4.0)
                .collect(Collectors.toList());
    }
    public List<DebilidadFortaleza> getDebilidades() {
        return debilidades.stream()
                .filter(d -> d.getMultiplicador() == 2.0)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene solo las resistencias fuertes (x0.25)
     */
    public List<DebilidadFortaleza> getResistenciasFuertes() {
        return fortalezas.stream()
                .filter(f -> f.getMultiplicador() <= 0.25)
                .collect(Collectors.toList());
    }

    public List<DebilidadFortaleza> getResistencias() {
        return fortalezas.stream()
                .filter(f -> f.getMultiplicador() < 0.25)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene el multiplicador de daño sin nada especial
     */
    public double getMultiplicadorPorTipo(String tipo) {
        return multiplicadores.getOrDefault(tipo, 1.0);
    }

    /**
     * Verifica si el Pokémon es inmune a un tipo específico
     */
    public boolean esInmuneA(String tipo) {
        return inmunidades.contains(tipo);
    }

    /**
     * Verifica si el Pokémon es débil a un tipo específico
     */
    public boolean esDebilA(String tipo) {
        return multiplicadores.getOrDefault(tipo, 1.0) > 1.0;
    }

    /**
     * Verifica si el Pokémon resiste a un tipo específico
     */
    public boolean resisteA(String tipo) {
        return multiplicadores.getOrDefault(tipo, 1.0) < 1.0 &&
                multiplicadores.getOrDefault(tipo, 1.0) > 0;
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
            sb.append("  DEBILIDADES CRÍTICAS (x4):\n");
            for (DebilidadFortaleza d : criticasTemp) {
                sb.append(String.format("\t•%s\n", d.getTipo()));
            }
            sb.append("\n");
        }

        if (!getDebilidades().isEmpty()) {
            sb.append(" DEBILIDADES:\n");
            for (DebilidadFortaleza d : getDebilidades()) {
                sb.append(String.format("\t•%s\n", d.getTipo()));
            }
            sb.append("\n");
        }

        if (!getResistencias().isEmpty()) {
            sb.append("FORTALEZAS (Resistencias):\n");
            for (DebilidadFortaleza f : getResistencias()) {
                sb.append(String.format("\t•%s\n", f.getTipo()));
            }
            sb.append("\n");
        }


        List<DebilidadFortaleza> resistenciasFuertesTemp = getResistenciasFuertes();
        if (!resistenciasFuertesTemp.isEmpty()) {
            sb.append(" RESISTENCIAS FUERTES:\n");
            for (DebilidadFortaleza r : resistenciasFuertesTemp) {
                sb.append(String.format("\t•%s\n", r.getTipo()));
            }
            sb.append("\n");
        }


        if (!inmunidades.isEmpty()) {
            sb.append("INMUNIDADES (Sin daño):\n");
            for (String inmunidad : inmunidades) {
                sb.append(String.format("\t• %s\n", inmunidad));
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    /**
     * Clase interna para representar una debilidad o fortaleza
     */
    @Getter
    public static class DebilidadFortaleza {
        private final String tipo;
        private final double multiplicador;

        public DebilidadFortaleza(String tipo, double multiplicador) {
            this.tipo = tipo;
            this.multiplicador = multiplicador;
        }

        @Override
        public String toString() {
            return String.format("\n%s (x%.2f)", tipo, multiplicador);
        }
    }
}