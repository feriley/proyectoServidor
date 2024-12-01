package com.example.proyectoServidor.dto;

/**
 * Clase genérica para encapsular respuestas de la API.
 *
 * @param <T> Tipo de contenido de la respuesta.
 */
public class GenericApiResponse<T> {
    private String message;
    private T content;

    public GenericApiResponse() {}

    public GenericApiResponse(String message, T content) {
        this.message = message;
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
