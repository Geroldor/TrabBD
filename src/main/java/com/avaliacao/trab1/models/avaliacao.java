package com.avaliacao.trab1.models;

import java.time.LocalDate;

public class avaliacao {
    private int idUsuario;
    private int idJogo;
    private LocalDate dataAvaliacao;
    private int notaHistoria;
    private int notaTrilha;
    private int notaGrafico;
    private int notaGameplay;
    private int notaDificuldade;
    private boolean recomenda;
    private String comentario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public int getNotaHistoria() {
        return notaHistoria;
    }

    public void setNotaHistoria(int notaHistoria) {
        this.notaHistoria = notaHistoria;
    }

    public int getNotaTrilha() {
        return notaTrilha;
    }

    public void setNotaTrilha(int notaTrilha) {
        this.notaTrilha = notaTrilha;
    }

    public int getNotaGrafico() {
        return notaGrafico;
    }

    public void setNotaGrafico(int notaGrafico) {
        this.notaGrafico = notaGrafico;
    }

    public int getNotaGameplay() {
        return notaGameplay;
    }

    public void setNotaGameplay(int notaGameplay) {
        this.notaGameplay = notaGameplay;
    }

    public int getNotaDificuldade() {
        return notaDificuldade;
    }

    public void setNotaDificuldade(int notaDificuldade) {
        this.notaDificuldade = notaDificuldade;
    }

    public boolean isRecomenda() {
        return recomenda;
    }

    public void setRecomenda(boolean recomenda) {
        this.recomenda = recomenda;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
