package com.br.literalura.LiterAlura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultsDTO(@JsonAlias("count") int qtdLocalizados,
                         @JsonAlias("results") List<LivroDTO> LivroDTO) {
}
