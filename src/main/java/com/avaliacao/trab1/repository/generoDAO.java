package com.avaliacao.trab1.repository;

import java.util.ArrayList;
import java.util.List;
import com.avaliacao.trab1.models.genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class generoDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(genero genero) {
        String sql = "INSERT INTO t1.genero (nome_genero) VALUES (?)";
        jdbcTemplate.update(sql, genero.getNomeGenero());
    }

    public List<genero> listarTodos() {
        String sql = "SELECT * FROM t1.genero";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(genero.class));
    }

    public genero buscarPorId(int id) {
        String sql = "SELECT * FROM t1.genero WHERE id_genero = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(genero.class), id);
    }

    public List<genero> buscarPorIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) return new ArrayList<>();
        String inSql = String.join(",", ids.stream().map(id -> "?").toArray(String[]::new));
        String sql = "SELECT * FROM t1.genero WHERE id_genero IN (" + inSql + ")";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(genero.class), ids.toArray());
    }

    public boolean existePorNome(String nome) {
        String sql = "SELECT COUNT(*) FROM t1.genero WHERE LOWER(nome_genero) = LOWER(?)";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nome);
        return count != null && count > 0;
    }
}
