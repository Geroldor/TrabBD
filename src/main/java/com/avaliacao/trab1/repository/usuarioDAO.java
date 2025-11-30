package com.avaliacao.trab1.repository;

import java.util.List;
import com.avaliacao.trab1.models.usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
@Repository
public class usuarioDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(usuario usuario) {
        String sql = "INSERT INTO t1.usuario (nick, email, senha) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                usuario.getNick(),
                usuario.getEmail(),
                usuario.getSenha());
    }

    public usuario buscarPorEmailSenha(String email, String senha) {
        String sql = "SELECT * FROM t1.usuario WHERE email = ? AND senha = ?";
        List<usuario> usuarios = jdbcTemplate.query(sql,
                new Object[]{email, senha},
                new BeanPropertyRowMapper<>(usuario.class));
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }
    public List<usuario> listarTodos() {
        String sql = "SELECT * FROM t1.usuario";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(usuario.class));
    }

    public boolean existePorEmail(String email) {
        String sql = "SELECT COUNT(*) FROM t1.usuario WHERE LOWER(email) = LOWER(?)";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    public boolean existePorNick(String nick) {
        String sql = "SELECT COUNT(*) FROM t1.usuario WHERE LOWER(nick) = LOWER(?)";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nick);
        return count != null && count > 0;
    }

    public usuario buscarPorEmailESenha(String email, String senha) {
        String sql = "SELECT * FROM t1.usuario WHERE email = ? AND senha = ?";
        List<usuario> usuarios = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(usuario.class), email, senha);
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

}
