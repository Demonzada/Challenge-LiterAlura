package com.br.literalura.LiterAlura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO(@JsonAlias("name") String nomeAutor,
                       @JsonAlias("birth_year") Integer anoNascimento,
                       @JsonAlias("death_year") Integer anoMorte) {
}
