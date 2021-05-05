package com.essaadani.springmvc.web;

import com.essaadani.springmvc.entities.Medecin;
import com.essaadani.springmvc.entities.Patient;
import com.essaadani.springmvc.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MedecinsController {

    @Autowired
    MedecinRepository medecinRepository;

    @GetMapping(path = "/medecins")
    public String medecins(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name="size", defaultValue = "5") int size,
                           @RequestParam(name="keyword", defaultValue = "") String keyword){
        Page<Medecin> pageMedecins = medecinRepository.findByNomContains(keyword, PageRequest.of(page, size));

        model.addAttribute("medecins", pageMedecins.getContent());
        model.addAttribute("pages", new int[pageMedecins.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);

        return "medecins";
    }

    @GetMapping(path="/deleteMedecin")
    public String deleteMedecin(Long id, String keyword, int page, int size){
        medecinRepository.deleteById(id);

        return "redirect:/medecins?page="+page+"&keyword="+keyword+"&size="+size;
    }
}
