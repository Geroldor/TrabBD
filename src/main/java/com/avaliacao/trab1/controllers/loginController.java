package com.avaliacao.trab1.controllers;

import com.avaliacao.trab1.models.usuario;
import com.avaliacao.trab1.repository.usuarioDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class loginController {

    @Autowired
    private usuarioDAO usuarioDAO;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String email, @RequestParam String senha, HttpSession session, Model model) {
        usuario u = usuarioDAO.buscarPorEmailSenha(email, senha);
        if (u != null) {
            session.setAttribute("usuarioLogado", u);
            return "redirect:/"; // redireciona para a home
        } else {
            model.addAttribute("erro", "Email ou senha inválidos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
