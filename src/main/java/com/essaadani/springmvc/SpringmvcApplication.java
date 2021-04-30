package com.essaadani.springmvc;

import com.essaadani.springmvc.entities.Medecin;
import com.essaadani.springmvc.entities.Patient;
import com.essaadani.springmvc.repositories.MedecinRepository;
import com.essaadani.springmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringmvcApplication implements CommandLineRunner {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    MedecinRepository medecinRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

      /* medecinRepository.save(new Medecin(null, "Es-saadani", new Date(), "General"));
        medecinRepository.save(new Medecin(null, "Oukil", new Date(), "Dentiste"));
        medecinRepository.save(new Medecin(null, "Naji", new Date(), "General"));
        medecinRepository.save(new Medecin(null, "Alami", new Date(), "Psychiatre"));

        patientRepository.save(new Patient(null, "Mohamed", new Date(), 9887, false, medecinRepository.findById(44L).get()));
        patientRepository.save(new Patient(null, "Adam", new Date(), 6666, false, medecinRepository.findById(44L).get()));
        patientRepository.save(new Patient(null, "Hicham", new Date(), 22222, false, medecinRepository.findById(44L).get()));
        patientRepository.save(new Patient(null, "Saloi", new Date(), 8758, false, medecinRepository.findById(44L).get()));
        patientRepository.save(new Patient(null, "Khalid", new Date(), 8758, true, medecinRepository.findById(44L).get()));
        patientRepository.findAll().forEach(p -> {
            System.out.println(p.toString());
        });*/
    }
}
