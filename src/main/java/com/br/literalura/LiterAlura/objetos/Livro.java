package com.br.literalura.LiterAlura.objetos;

import com.br.literalura.LiterAlura.models.LivroDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;
    private String idioma;
    private Integer qtdDownloads;

    public Livro() {
    }

    public Livro(LivroDTO LivroDTO) {
        this.titulo = LivroDTO.titulo();
        this.idioma = LivroDTO.idioma().get(0);
        this.qtdDownloads = LivroDTO.qtdDownloads();
        this.autor = new Autor(LivroDTO.autor().get(0));
        this.idioma = LivroDTO.idioma().get(0);

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getQtdDownloads() {
        return qtdDownloads;
    }

    public void setQtdDownloads(Integer qtdDownloads) {
        this.qtdDownloads = qtdDownloads;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + "\n" +
                "Autor: " + autor.getNomeAutor() + "\n" +
                "Idioma: " + idioma + "\n" +
                "Downloads: " + qtdDownloads + "\n"  + "\n";
    }
}
