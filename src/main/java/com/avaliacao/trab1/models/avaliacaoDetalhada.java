package com.avaliacao.trab1.models;


import java.util.Date;

public class avaliacaoDetalhada {
    private int id_usuario;
    private String nick;
    private Date data_avaliacao;
    private int nota_historia;
    private int nota_trilha;
    private int nota_grafico;
    private int nota_gameplay;
    private int nota_dificuldade;
    private boolean recomenda;
    private String comentario;



    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Date getData_avaliacao() {
        return data_avaliacao;
    }

    public void setData_avaliacao(Date data_avaliacao) {
        this.data_avaliacao = data_avaliacao;
    }

    public int getNota_historia() {
        return nota_historia;
    }

    public void setNota_historia(int nota_historia) {
        this.nota_historia = nota_historia;
    }

    public int getNota_trilha() {
        return nota_trilha;
    }

    public void setNota_trilha(int nota_trilha) {
        this.nota_trilha = nota_trilha;
    }

    public int getNota_grafico() {
        return nota_grafico;
    }

    public void setNota_grafico(int nota_grafico) {
        this.nota_grafico = nota_grafico;
    }

    public int getNota_gameplay() {
        return nota_gameplay;
    }

    public void setNota_gameplay(int nota_gameplay) {
        this.nota_gameplay = nota_gameplay;
    }

    public int getNota_dificuldade() {
        return nota_dificuldade;
    }

    public void setNota_dificuldade(int nota_dificuldade) {
        this.nota_dificuldade = nota_dificuldade;
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
