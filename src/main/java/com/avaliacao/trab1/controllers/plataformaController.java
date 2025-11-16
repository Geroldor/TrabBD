package com.avaliacao.trab1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.avaliacao.trab1.models.plataforma;
import com.avaliacao.trab1.repository.plataformaDAO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/plataforma")
public class plataformaController {

    private  plataformaDAO plataformaDAO;

    public plataformaController(plataformaDAO plataformaDAO) {
        this.plataformaDAO = plataformaDAO;
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("plataforma", new plataforma());
        model.addAttribute("listaPlataformas", plataformaDAO.listarTodas());
        return "cadastrar-plataforma";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute plataforma plataforma, RedirectAttributes redirectAttributes) {
        if (plataformaDAO.existePorNome(plataforma.getNomePlataforma())) {
            redirectAttributes.addFlashAttribute("erro", "Plataforma já cadastrada!");
            return "redirect:/plataforma/form";
        }

        plataformaDAO.salvar(plataforma);
        redirectAttributes.addFlashAttribute("sucesso", "Plataforma salva com sucesso!");
        return "redirect:/plataforma/form";
    }
}
