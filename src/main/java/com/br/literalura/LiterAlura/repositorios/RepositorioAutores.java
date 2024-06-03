package com.br.literalura.LiterAlura.repositorios;

import com.br.literalura.LiterAlura.objetos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioAutores extends JpaRepository<Autor, Long> {


    @Query("SELECT a FROM Livro b JOIN b.autor a WHERE a.anoNascimento <= :ano and a.anoMorte >= :ano")
    List<Autor> vivoAte(int ano);
}
