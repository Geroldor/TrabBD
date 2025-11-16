package com.avaliacao.trab1.repository;

import com.avaliacao.trab1.models.plataforma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class plataformaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(plataforma plataforma) {
        String sql = "INSERT INTO t1.plataforma (nome_plataforma) VALUES (?)";
        jdbcTemplate.update(sql, plataforma.getNomePlataforma());
    }

    public List<plataforma> listarTodas() {
        String sql = "SELECT * FROM t1.plataforma";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(plataforma.class));
    }

    public plataforma buscarPorId(int id) {
        String sql = "SELECT * FROM t1.plataforma WHERE id_plataforma = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(plataforma.class), id);
    }

    public boolean existePorNome(String nome) {
        String sql = "SELECT COUNT(*) FROM t1.plataforma WHERE LOWER(nome_plataforma) = LOWER(?)";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nome);
        return count != null && count > 0;
    }

}
