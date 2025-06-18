package com.retolibros.retolibros.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResultado(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autorList,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Integer numeroDeDescargas
) {
}
