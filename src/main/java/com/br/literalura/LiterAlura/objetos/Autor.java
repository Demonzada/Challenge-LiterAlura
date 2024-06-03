package com.br.literalura.LiterAlura.objetos;

import com.br.literalura.LiterAlura.models.AutorDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomeAutor;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Livro> livrosAutor = new ArrayList<>();

    private Integer anoNascimento;
    private Integer anoMorte;

    public Autor() {}

    public Autor(AutorDTO autorDTO) {
        this.nomeAutor = autorDTO.nomeAutor();
        this.anoNascimento = autorDTO.anoNascimento();
        this.anoMorte = autorDTO.anoMorte();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Livro> getLivrosAutor() {
        return livrosAutor;
    }

    public void setLivrosAutor(List<Livro> livrosAutor) {
        this.livrosAutor = livrosAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    @Override
    public String toString() {
        String livrosStr = livrosAutor.stream()
                .map(Livro::getTitulo)
                .collect(Collectors.joining(", "));

        return "Autor: " + nomeAutor + "\n" +
                "Livros do Autor: " + livrosStr + "\n" +
                "Ano de nascimento: " + anoNascimento + "\n" +
                "Ano da sua morte: " + anoMorte + "\n" + "\n";
    }
}
