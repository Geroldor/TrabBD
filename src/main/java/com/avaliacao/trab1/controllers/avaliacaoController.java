package com.avaliacao.trab1.controllers;

import com.avaliacao.trab1.models.avaliacao;
import com.avaliacao.trab1.models.usuario;
import com.avaliacao.trab1.repository.jogoDAO;
import com.avaliacao.trab1.repository.avaliacaoDAO;
import com.avaliacao.trab1.repository.usuarioDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/avaliacao")
public class avaliacaoController {

    @Autowired
    private avaliacaoDAO avaliacaoDAO;

    @Autowired
    private jogoDAO jogoDAO;

    @GetMapping("/form")
    public String mostrarFormulario(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("avaliacao", new avaliacao());
        model.addAttribute("jogos", jogoDAO.listarTodos());
        return "cadastrar-avaliacao";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute avaliacao avaliacao, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        usuario usuarioLogado = (usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/login";
        }

        avaliacao.setIdUsuario(usuarioLogado.getIdUsuario());
        avaliacao.setDataAvaliacao(LocalDate.now());

        if (avaliacaoDAO.avaliacaoExiste(avaliacao.getIdUsuario(), avaliacao.getIdJogo())) {
            model.addAttribute("idJogo", avaliacao.getIdJogo());
            return "avaliacao-ja-existe";
        }

        try {
            avaliacaoDAO.salvar(avaliacao);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Avaliação cadastrada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao cadastrar avaliação.");
        }

        return "redirect:/avaliacao/form";
    }

    @GetMapping("/editar/{idJogo}")
    public String editarAvaliacao(@PathVariable("idJogo") int idJogo, HttpSession session, Model model) {
        usuario usuarioLogado = (usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/usuario/login"; // redireciona para login se não estiver logado
        }

        int idUsuario = usuarioLogado.getIdUsuario();
        avaliacao avaliacaoExistente = avaliacaoDAO.buscarPorUsuarioEJogo(idUsuario, idJogo);
        model.addAttribute("avaliacao", avaliacaoExistente);
        return "editar-avaliacao";
    }


    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute avaliacao avaliacao, RedirectAttributes redirectAttributes) {
        try {
            avaliacao.setDataAvaliacao(LocalDate.now()); // atualiza a data
            avaliacaoDAO.atualizar(avaliacao);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Avaliação atualizada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao atualizar avaliação.");
        }
        return "redirect:/avaliacao/form";
    }

}
