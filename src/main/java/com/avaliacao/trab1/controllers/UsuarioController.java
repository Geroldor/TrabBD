package com.avaliacao.trab1.controllers;

import com.avaliacao.trab1.models.usuario;
import com.avaliacao.trab1.repository.usuarioDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private usuarioDAO usuarioDAO;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new usuario());

        return "cadastrar-usuario";
    }


    @PostMapping("/salvar")
    public String salvar(@ModelAttribute usuario usuario, RedirectAttributes redirectAttributes) {
        boolean emailExistente = usuarioDAO.existePorEmail(usuario.getEmail());
        boolean nickExistente = usuarioDAO.existePorNick(usuario.getNick());

        if (emailExistente || nickExistente) {
            if (emailExistente) {
                redirectAttributes.addFlashAttribute("erro", "Já existe um usuário com este e-mail.");
            }
            if (nickExistente) {
                redirectAttributes.addFlashAttribute("erro", "Já existe um usuário com este nick.");
            }
            return "redirect:/usuario/form";
        }

        usuarioDAO.salvar(usuario);
        redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
        return "redirect:/usuario/form";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("usuario", new usuario());
        return "login";
    }

    @PostMapping("/login")
    public String realizarLogin(@RequestParam String email, @RequestParam String senha,
                                HttpSession session, RedirectAttributes redirectAttributes) {
        usuario usuarioLogado = usuarioDAO.buscarPorEmailESenha(email, senha);

        if (usuarioLogado != null) {
            session.setAttribute("usuarioLogado", usuarioLogado);
            redirectAttributes.addFlashAttribute("sucesso", "Login realizado com sucesso!");
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("erro", "E-mail ou senha inválidos.");
            return "redirect:/usuario/login";
        }
    }

}