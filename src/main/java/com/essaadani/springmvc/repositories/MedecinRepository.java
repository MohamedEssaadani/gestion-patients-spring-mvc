package  com.essaadani.springmvc.repositories;

import com.essaadani.springmvc.entities.Medecin;
import com.essaadani.springmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    public Page<Medecin> findByNomContains(String keyword, Pageable pageable);

}
