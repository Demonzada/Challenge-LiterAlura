package com.br.literalura.LiterAlura.services;

public interface IConversorDeDados {
    <T> T converterDados(String json, Class<T> classe);
}
