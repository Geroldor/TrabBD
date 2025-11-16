package com.avaliacao.trab1.repository;


import java.util.List;
import com.avaliacao.trab1.models.jogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class jogoDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(jogo jogo) {
        String sql = "INSERT INTO t1.jogo (titulo, data_lancamento, id_desenvolvedora, id_plataforma) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                jogo.getTitulo(),
                jogo.getDataLancamento(),
                jogo.getIdDesenvolvedora(),
                jogo.getIdPlataforma());
    }

    public List<jogo> listarTodos() {
        String sql = "SELECT * FROM t1.jogo";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(jogo.class));
    }

    public int obterUltimoIdInserido() {
        String sql = "SELECT currval(pg_get_serial_sequence('t1.jogo','id_jogo'))";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public jogo buscarPorId(int idJogo) {
        String sql = """
        SELECT j.*, d.nome_desenvolvedora, p.nome_plataforma
        FROM t1.jogo j
        JOIN t1.desenvolvedora d ON j.id_desenvolvedora = d.id_desenvolvedora
        JOIN t1.plataforma p ON j.id_plataforma = p.id_plataforma
        WHERE j.id_jogo = ?
    """;

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(jogo.class), idJogo);
    }

    public boolean jogoExisteTituloEPlat(String titulo, int idPlataforma) {
        String sql = "SELECT COUNT(*) FROM t1.jogo WHERE titulo = ? AND id_plataforma = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, titulo, idPlataforma);
        return count != null && count > 0;
    }

}
