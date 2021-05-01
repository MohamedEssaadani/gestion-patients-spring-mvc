package com.essaadani.springmvc.web;

import com.essaadani.springmvc.entities.Medecin;
import com.essaadani.springmvc.entities.Patient;
import com.essaadani.springmvc.repositories.MedecinRepository;
import com.essaadani.springmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class PatientsController {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    MedecinRepository medecinRepository;

    @GetMapping(path = "/index")
   public String index(){
        return "index";
    }

    @GetMapping(path = "/patients")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name="size", defaultValue = "5") int size,
                           @RequestParam(name="keyword", defaultValue = "") String keyword){
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));

        model.addAttribute("patients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);

        return "patients";
    }

    @GetMapping(path="/deletePatient")
    public String deletePatient(Long id, String keyword, int page, int size){
        patientRepository.deleteById(id);

        return "redirect:/patients?page="+page+"&keyword="+keyword+"&size="+size;
    }

    @GetMapping(path="/formPatient")
    public String formPatient(Model model){
        List<Medecin> medecinList = medecinRepository.findAll();
        model.addAttribute("medecinList", medecinList);
        model.addAttribute("patient", new Patient());
        model.addAttribute("mode", "new");
        //Ss
        return "formPatient";
    }

    @PostMapping(path = "/savePatient")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            return "formPatient";

        patientRepository.save(patient);

        model.addAttribute("patient", patient);

        return "confirmation";
    }
    
    @GetMapping("/editPatient")
    public String editPatient(@RequestParam Long id, Model model){
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        model.addAttribute("mode", "edit");

        List<Medecin> medecinList = medecinRepository.findAll();
        model.addAttribute("medecinList", medecinList);

        return "formPatient";
    }
}
