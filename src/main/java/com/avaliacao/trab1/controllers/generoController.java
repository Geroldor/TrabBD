package com.avaliacao.trab1.controllers;

import com.avaliacao.trab1.models.genero;
import com.avaliacao.trab1.repository.generoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/genero")
public class generoController {

    @Autowired
    private generoDAO generoDAO;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("genero", new genero());
        model.addAttribute("listaGeneros", generoDAO.listarTodos());
        return "cadastrar-genero";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute genero genero, RedirectAttributes redirectAttributes) {
        if (generoDAO.existePorNome(genero.getNomeGenero())) {
            redirectAttributes.addFlashAttribute("erro", "Genero já cadastrado!");
            return "redirect:/genero/form";
        }

        generoDAO.salvar(genero);
        redirectAttributes.addFlashAttribute("sucesso", "Genero salvo com sucesso!");
        return "redirect:/genero/form";
    }
}
