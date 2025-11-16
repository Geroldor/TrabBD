package com.avaliacao.trab1.models;

import java.time.LocalDate;
public class jogo {
    private int idJogo;
    private String titulo;
    private LocalDate dataLancamento;
    private int idDesenvolvedora;
    private int idPlataforma;

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getIdDesenvolvedora() {
        return idDesenvolvedora;
    }

    public void setIdDesenvolvedora(int idDesenvolvedora) {
        this.idDesenvolvedora = idDesenvolvedora;
    }

    public int getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
    }
}
