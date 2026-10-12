package cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.dto.ActualizarEstadoCitaDTO;
import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.dto.CrearCitaDTO;
import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.model.CitaMedica;
import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.service.CitaMedicaService;

@RestController
@RequestMapping("/api/v1/citas")
@CrossOrigin(origins = "http://localhost:4200")
public class CrearCitaDTOController {

    private final CitaMedicaService citaMedicaService;

    public CrearCitaDTOController(CitaMedicaService citaMedicaService) {
        this.citaMedicaService = citaMedicaService;
    }

    @GetMapping
    public List<CitaMedica> obtenerTodas() {
        return citaMedicaService.obtenerTodas();
    }

    @GetMapping("/prioridad/{prioridad}")
    public List<CitaMedica> obtenerPorPrioridad(@PathVariable String prioridad) {
        return citaMedicaService.obtenerPorPrioridad(prioridad);
    }

    @PostMapping
    public ResponseEntity<CitaMedica> crearCita(@RequestBody CrearCitaDTO dto) {
        CitaMedica citaCreada = citaMedicaService.crearCita(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaCreada);
    }

    @PatchMapping("/{id}/estado")
    public CitaMedica actualizarEstado(@PathVariable Long id, @RequestBody ActualizarEstadoCitaDTO dto) {
        if (dto == null || dto.getEstado() == null || dto.getEstado().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estado de la cita es requerido.");
        }
        return citaMedicaService.actualizarEstado(id, dto.getEstado());
    }
}
