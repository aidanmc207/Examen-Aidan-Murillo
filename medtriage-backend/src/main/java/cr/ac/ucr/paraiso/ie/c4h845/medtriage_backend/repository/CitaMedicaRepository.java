package cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.repository;

import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {

	List<CitaMedica> findByNivelPrioridadOrderByFechaCitaDesc(String nivelPrioridad);
	
}
