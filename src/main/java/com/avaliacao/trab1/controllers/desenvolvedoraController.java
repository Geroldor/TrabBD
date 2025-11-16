package com.avaliacao.trab1.controllers;

import com.avaliacao.trab1.models.desenvolvedora;
import com.avaliacao.trab1.models.desenvolvedora;
import com.avaliacao.trab1.repository.desenvolvedoraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/desenvolvedora")
public class desenvolvedoraController {

    @Autowired
    private desenvolvedoraDAO desenvolvedoraDAO;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("desenvolvedora", new desenvolvedora());
        model.addAttribute("listaDesenvolvedoras", desenvolvedoraDAO.listarTodas());
        return "cadastrar_desenvolvedora";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute desenvolvedora desenvolvedora, RedirectAttributes redirectAttributes) {
        if (desenvolvedoraDAO.existePorNome(desenvolvedora.getNomeDesenvolvedora())) {
            redirectAttributes.addFlashAttribute("erro", "Desenvolvedora já cadastrada!");
            return "redirect:/desenvolvedora/form";
        }

        desenvolvedoraDAO.salvar(desenvolvedora);
        redirectAttributes.addFlashAttribute("sucesso", "Desenvolvedora salva com sucesso!");
        return "redirect:/desenvolvedora/form";
    }

}

