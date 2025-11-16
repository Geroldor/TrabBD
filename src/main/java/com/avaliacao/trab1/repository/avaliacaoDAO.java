package com.avaliacao.trab1.repository;

import java.util.List;
import com.avaliacao.trab1.models.avaliacao;
import com.avaliacao.trab1.models.avaliacaoDetalhada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class avaliacaoDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(avaliacao avaliacao) {
        String sql = "INSERT INTO t1.avaliacao (id_usuario, id_jogo, data_avaliacao, nota_historia, nota_trilha, nota_grafico, nota_gameplay, nota_dificuldade, recomenda, comentario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                avaliacao.getIdUsuario(),
                avaliacao.getIdJogo(),
                avaliacao.getDataAvaliacao(),
                avaliacao.getNotaHistoria(),
                avaliacao.getNotaTrilha(),
                avaliacao.getNotaGrafico(),
                avaliacao.getNotaGameplay(),
                avaliacao.getNotaDificuldade(),
                avaliacao.isRecomenda(),
                avaliacao.getComentario());
    }
    public List<avaliacaoDetalhada> listarAvaliacoesPorJogo(int idJogo) {
        String sql = """
        SELECT a.id_usuario, u.nick, a.data_avaliacao, a.nota_historia, a.nota_trilha,
        a.nota_grafico, a.nota_gameplay, a.nota_dificuldade, a.recomenda, a.comentario
        FROM t1.avaliacao a
        JOIN t1.usuario u ON a.id_usuario = u.id_usuario
        WHERE a.id_jogo = ?
        """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(avaliacaoDetalhada.class), idJogo);
    }

    public boolean avaliacaoExiste(int idUsuario, int idJogo) {
        String sql = "SELECT COUNT(*) FROM t1.avaliacao WHERE id_usuario = ? AND id_jogo = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idUsuario, idJogo);
        return count != null && count > 0;
    }

    public avaliacao buscarPorUsuarioEJogo(int idUsuario, int idJogo) {
        String sql = "SELECT * FROM t1.avaliacao WHERE id_usuario = ? AND id_jogo = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(avaliacao.class), idUsuario, idJogo);
    }

    public void atualizar(avaliacao avaliacao) {
        String sql = "UPDATE t1.avaliacao SET data_avaliacao = ?, nota_historia = ?, nota_trilha = ?, nota_grafico = ?, nota_gameplay = ?, nota_dificuldade = ?, recomenda = ?, comentario = ? WHERE id_usuario = ? AND id_jogo = ?";
        jdbcTemplate.update(sql,
                avaliacao.getDataAvaliacao(),
                avaliacao.getNotaHistoria(),
                avaliacao.getNotaTrilha(),
                avaliacao.getNotaGrafico(),
                avaliacao.getNotaGameplay(),
                avaliacao.getNotaDificuldade(),
                avaliacao.isRecomenda(),
                avaliacao.getComentario(),
                avaliacao.getIdUsuario(),
                avaliacao.getIdJogo());
    }



}
