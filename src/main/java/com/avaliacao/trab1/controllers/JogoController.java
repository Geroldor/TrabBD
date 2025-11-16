package com.avaliacao.trab1.controllers;

import com.avaliacao.trab1.models.*;
import com.avaliacao.trab1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/jogo")
public class JogoController {

    @Autowired
    private jogoDAO jogoDAO;

    @Autowired
    private desenvolvedoraDAO desenvolvedoraDAO;

    @Autowired
    private plataformaDAO plataformaDAO;

    @Autowired
    private avaliacaoDAO avaliacaoDAO;

    @Autowired
    private generoDAO generoDAO;

    @Autowired
    private jogogeneroDAO jogogeneroDAO;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jogo", new jogo());
        model.addAttribute("desenvolvedoras", desenvolvedoraDAO.listarTodas());
        model.addAttribute("plataformas", plataformaDAO.listarTodas());
        model.addAttribute("jogos", jogoDAO.listarTodos());
        model.addAttribute("generos", generoDAO.listarTodos());

        return "cadastrar-jogo";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute jogo jogo, @RequestParam("idsGeneros") List<Integer> idsGeneros, RedirectAttributes redirectAttributes) {
        if (jogoDAO.jogoExisteTituloEPlat(jogo.getTitulo(), jogo.getIdPlataforma())) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Já existe um jogo com este título para a mesma plataforma.");
            return "redirect:/jogo/form";
        }
        try {
            jogoDAO.salvar(jogo);
            int idJogoSalvo = jogoDAO.obterUltimoIdInserido();

            for (Integer idGenero : idsGeneros) {
                jogogenero link = new jogogenero();
                link.setIdJogo(idJogoSalvo);
                link.setIdGenero(idGenero);
                jogogeneroDAO.salvar(link);
            }

            redirectAttributes.addFlashAttribute("mensagemSucesso", "Jogo cadastrado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao cadastrar jogo.");
        }

        return "redirect:/jogo/form";
    }

    @GetMapping
    public String listarJogos(Model model) {
        List<jogo> jogos = jogoDAO.listarTodos();
        model.addAttribute("jogos", jogos);
        return "lista-jogos";
    }

    /*
    @GetMapping("/{id}")
    public String verAvaliacoes(@PathVariable("id") int idJogo, Model model) {
        List<avaliacaoDetalhada> avaliacoes = avaliacaoDAO.listarAvaliacoesPorJogo(idJogo);
        model.addAttribute("avaliacoes", avaliacoes);
        model.addAttribute("idJogo", idJogo);
        return "avaliacoes-jogo";
    }
    */

    @GetMapping("/{id}")
    public String verDetalhesDoJogo(@PathVariable("id") int idJogo, Model model) {
        jogo jogoSelecionado = jogoDAO.buscarPorId(idJogo);
        plataforma plat = plataformaDAO.buscarPorId(jogoSelecionado.getIdPlataforma());
        desenvolvedora desen = desenvolvedoraDAO.buscarPorId(jogoSelecionado.getIdDesenvolvedora());

        List<Integer> idsGeneros = jogogeneroDAO.buscarIdsGenerosPorJogo(idJogo);
        List<genero> gen = generoDAO.buscarPorIds(idsGeneros);

        List<avaliacaoDetalhada> avaliacoes = avaliacaoDAO.listarAvaliacoesPorJogo(idJogo);

        model.addAttribute("jogo", jogoSelecionado);
        model.addAttribute("plataforma", plat);
        model.addAttribute("desenvolvedora", desen);
        model.addAttribute("avaliacoes", avaliacoes);
        model.addAttribute("genero", gen);
        return "detalhes-jogo";
    }
}
