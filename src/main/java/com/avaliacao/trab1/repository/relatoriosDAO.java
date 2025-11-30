package com.avaliacao.trab1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class relatoriosDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 1. Jogos mais avaliados
    public List<Map<String, Object>> listarJogosMaisAvaliados() {
        String sql = """
        SELECT j.titulo, COUNT(*) AS total
        FROM t1.avaliacao a
        JOIN t1.jogo j ON a.id_jogo = j.id_jogo
        GROUP BY j.titulo
        ORDER BY total DESC
        LIMIT 10
    """;
        return jdbcTemplate.queryForList(sql);
    }

    // 2. Top 10 jogos melhor avaliados
    public List<Map<String, Object>> notaMediaPorJogo() {
        String sql = """
        SELECT j.titulo,
               ROUND(AVG((a.nota_historia + 
               a.nota_trilha + 
               a.nota_grafico + 
               a.nota_gameplay + 
               a.nota_dificuldade)
               /5.0), 2) AS media
        FROM t1.avaliacao a
        JOIN t1.jogo j ON a.id_jogo = j.id_jogo
        GROUP BY j.titulo
        ORDER BY media DESC
        LIMIT 10
    """;
        return jdbcTemplate.queryForList(sql);
    }

    // 3. Nota média por desenvolvedora
    public List<Map<String, Object>> notaMediaPorDesenvolvedora() {
        String sql = """
        SELECT d.nome_desenvolvedora,
               ROUND(AVG((a.nota_historia + a.nota_trilha + a.nota_grafico + a.nota_gameplay + a.nota_dificuldade)/5.0), 2) AS media
        FROM t1.avaliacao a
        JOIN t1.jogo j ON a.id_jogo = j.id_jogo
        JOIN t1.desenvolvedora d ON j.id_desenvolvedora = d.id_desenvolvedora
        GROUP BY d.nome_desenvolvedora
        ORDER BY media DESC
    """;
        return jdbcTemplate.queryForList(sql);
    }

    // 4. Distribuição de gêneros por plataforma
    public List<Map<String, Object>> distribuicaoGenerosPorPlataforma() {
        String sql = """
        SELECT p.nome_plataforma, g.nome_genero, COUNT(*) AS total
        FROM t1.jogo_has_genero jg
        JOIN t1.jogo j ON jg.id_jogo = j.id_jogo
        JOIN t1.genero g ON jg.id_genero = g.id_genero
        JOIN t1.plataforma p ON j.id_plataforma = p.id_plataforma
        GROUP BY p.nome_plataforma, g.nome_genero
        ORDER BY p.nome_plataforma, total DESC
    """;
        return jdbcTemplate.queryForList(sql);
    }

    // 5. Usuários que mais avaliaram
    public List<Map<String, Object>> usuariosQueMaisAvaliaram() {
        String sql = """
        SELECT u.nick, COUNT(*) AS total
        FROM t1.avaliacao a
        JOIN t1.usuario u ON a.id_usuario = u.id_usuario
        GROUP BY u.nick
        ORDER BY total DESC
        LIMIT 10
    """;
        return jdbcTemplate.queryForList(sql);
    }

    // 6. Porcentagem de recomendações por gênero
    public List<Map<String, Object>> porcentagemRecomendacoesPorGenero() {
        String sql = """
        SELECT g.nome_genero,
               ROUND(100.0 * SUM(CASE WHEN a.recomenda THEN 1 ELSE 0 END) / COUNT(*), 2) AS porcentagem
        FROM t1.jogo_has_genero jg
        JOIN t1.genero g ON jg.id_genero = g.id_genero
        JOIN t1.avaliacao a ON jg.id_jogo = a.id_jogo
        GROUP BY g.nome_genero
        ORDER BY porcentagem DESC
    """;
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> distribuicaoGenerosTop100() {
        String sql = """
        SELECT g.nome_genero AS genero, COUNT(*) AS quantidade
        FROM (
            SELECT j.id_jogo
            FROM t1.jogo j
            JOIN t1.avaliacao a ON a.id_jogo = j.id_jogo
            GROUP BY j.id_jogo
            ORDER BY AVG(
                a.nota_gameplay + a.nota_grafico + a.nota_trilha + a.nota_dificuldade + a.nota_historia
            ) DESC
            LIMIT 100
        ) AS top_jogos
        JOIN t1.jogo_has_genero jg ON jg.id_jogo = top_jogos.id_jogo
        JOIN t1.genero g ON g.id_genero = jg.id_genero
        GROUP BY g.nome_genero
        ORDER BY quantidade DESC;
    """;

        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> melhoresPorAtributo(String atributo) {
        String sql = String.format("""
        SELECT j.titulo, ROUND(AVG(a.%s), 2) AS media
        FROM t1.avaliacao a
        JOIN t1.jogo j ON a.id_jogo = j.id_jogo
        GROUP BY j.titulo
        ORDER BY media DESC
        LIMIT 10
    """, atributo);

        return jdbcTemplate.queryForList(sql);
    }
}
