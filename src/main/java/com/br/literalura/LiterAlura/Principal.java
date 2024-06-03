package com.br.literalura.LiterAlura;

import com.br.literalura.LiterAlura.models.LivroDTO;
import com.br.literalura.LiterAlura.models.ResultsDTO;
import com.br.literalura.LiterAlura.objetos.Autor;
import com.br.literalura.LiterAlura.objetos.Livro;
import com.br.literalura.LiterAlura.repositorios.RepositorioAutores;
import com.br.literalura.LiterAlura.repositorios.RepositorioLivros;
import com.br.literalura.LiterAlura.services.ConversorDeDados;
import com.br.literalura.LiterAlura.services.Http;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private Http http = new Http();
    private ConversorDeDados conversorDeDados = new ConversorDeDados();
    private RepositorioLivros repositorioLivros;
    private RepositorioAutores repositorioAutores;

    public Principal(RepositorioLivros repositorioLivros, RepositorioAutores repositorioAutores) {
        this.repositorioLivros = repositorioLivros;
        this.repositorioAutores = repositorioAutores;
    }

    public void menu() {
        var menu = """
                *****************************************************
                Seja bem vindo ao menu dos Livros
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                
                0 - Sair
                *****************************************************""";

        var opcao = -1;

        while(opcao != 0) {
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            
            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivroRegistrado();
                    break;
                case 3:
                    listarAutoresRegistrado();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosemIdioma();
                    break;
                default:
                    System.out.println("Opção não localizada");
                    break;
                case 0:
                    System.out.println("Saindo...");


            }
        }
    }

    private void buscarLivroPorTitulo() {
        ResultsDTO dados = getDados();
        List<LivroDTO> livroDTOS = new ArrayList<>();
        livroDTOS = dados.LivroDTO();

        if (livroDTOS.size() > 0) {
            Livro livro = new Livro(livroDTOS.get(0));
            System.out.println(livro);
            repositorioLivros.save(livro);

        } else {
            System.out.println("Livro não encontrado");
        }
    }


    private void listarLivroRegistrado() {
        List<Livro> livros = repositorioLivros.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrado() {
        List<Autor> autores = repositorioAutores.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Qual ano você quer ver?");
        var ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = repositorioAutores.vivoAte(ano);
        autores.forEach(System.out::println);
    }

    private void listarLivrosemIdioma() {
        System.out.println("Qual idioma você quer ver?");
        var idioma = leitura.nextLine();

        List<Livro> livros = repositorioLivros.livrosIdioma(idioma);
        livros.forEach(System.out::println);
    }

    private ResultsDTO getDados() {
        System.out.println("Digite o título do livro");
        var titulo = leitura.nextLine();

        var dados = http.obterDados("https://gutendex.com/books/?search=" + titulo.replace(" ", "+"));
        ResultsDTO resultsDTO = conversorDeDados.converterDados(dados, ResultsDTO.class);
        return resultsDTO;
    }
}
