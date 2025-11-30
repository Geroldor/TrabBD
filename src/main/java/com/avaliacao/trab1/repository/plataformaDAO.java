package com.avaliacao.trab1.repository;

import com.avaliacao.trab1.models.jogogenero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class jogogeneroDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(jogogenero jogogenero) {
        String sql = "INSERT INTO t1.jogo_has_genero (id_jogo, id_genero) VALUES (?, ?)";
        jdbcTemplate.update(sql, jogogenero.getIdJogo(), jogogenero.getIdGenero());
    }
    public List<Integer> buscarIdsGenerosPorJogo(int idJogo) {
        String sql = "SELECT id_genero FROM t1.jogo_has_genero WHERE id_jogo = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, idJogo);
    }

}
