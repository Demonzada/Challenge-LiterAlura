package com.br.literalura.LiterAlura.repositorios;

import com.br.literalura.LiterAlura.models.LivroDTO;
import com.br.literalura.LiterAlura.objetos.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioLivros extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.idioma ILIKE :idioma")
    List<Livro> livrosIdioma(String idioma);
}
