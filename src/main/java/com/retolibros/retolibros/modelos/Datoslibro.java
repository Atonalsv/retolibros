package com.retolibros.retolibros.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datoslibro(
        @JsonAlias("results") List<DatosResultado>  resultados) {
}
