package com.avaliacao.trab1.repository;

import java.util.List;
import com.avaliacao.trab1.models.desenvolvedora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class desenvolvedoraDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(desenvolvedora desenvolvedora) {
        String sql = "INSERT INTO t1.desenvolvedora (nome_desenvolvedora) VALUES (?)";
        jdbcTemplate.update(sql, desenvolvedora.getNomeDesenvolvedora());
    }

    public List<desenvolvedora> listarTodas() {
        String sql = "SELECT * FROM t1.desenvolvedora";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(desenvolvedora.class));
    }

    public desenvolvedora buscarPorId(int id) {
        String sql = "SELECT * FROM t1.desenvolvedora WHERE id_desenvolvedora = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(desenvolvedora.class), id);
    }

    public boolean existePorNome(String nome) {
        String sql = "SELECT COUNT(*) FROM t1.desenvolvedora WHERE LOWER(nome_desenvolvedora) = LOWER(?)";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nome);
        return count != null && count > 0;
    }

}