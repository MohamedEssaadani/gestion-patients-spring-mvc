package  com.essaadani.springmvc.repositories;

import com.essaadani.springmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Page<Patient> findByNomContains(String motCle, Pageable pageable);
}
