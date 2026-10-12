package cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.dto.CrearCitaDTO;
import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.model.CitaMedica;
import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.repository.CitaMedicaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class CitaMedicaService {
    private final CitaMedicaRepository citaMedicaRepository;

    public CitaMedicaService(CitaMedicaRepository citaMedicaRepository) {
        this.citaMedicaRepository = citaMedicaRepository;
    }

    @Transactional(readOnly = true)
    public List<CitaMedica> obtenerTodas() {
        return citaMedicaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CitaMedica> obtenerPorPrioridad(String prioridad){
        return citaMedicaRepository.findByNivelPrioridadOrderByFechaCitaDesc(prioridad);
    }

    @Transactional
    public CitaMedica crearCita(CrearCitaDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("La información de la cita es requerida.");
        }
        if (dto.getDoctorId() == null) {
            throw new IllegalArgumentException("El doctor es requerido para agendar la cita.");
        }
        if (dto.getFechaCita() == null) {
            throw new IllegalArgumentException("La fecha de la cita es requerida.");
        }

        String doctorId = String.valueOf(dto.getDoctorId());
        List<CitaMedica> citasDelDoctor = citaMedicaRepository.findAll().stream()
                .filter(cita -> cita.getDoctorId() != null)
                .filter(cita -> String.valueOf(cita.getDoctorId()).equals(doctorId))
                .filter(cita -> cita.getFechaCita() != null)
                .filter(cita -> cita.getFechaCita().equals(dto.getFechaCita()))
                .collect(Collectors.toList());

        if (!citasDelDoctor.isEmpty()) {
            throw new IllegalStateException("El doctor no está disponible en la fecha seleccionada.");
        }

        String codigoCita;
        while (true) {
            String codigoGenerado = generarCodigoCita();
            boolean existeCodigo = citaMedicaRepository.findAll().stream()
                    .anyMatch(cita -> codigoGenerado.equals(cita.getCodigoCita()));

            if (!existeCodigo) {
                codigoCita = codigoGenerado;
                break;
            }
        }

        CitaMedica citaMedica = new CitaMedica();
        citaMedica.setCodigoCita(codigoCita);
        citaMedica.setPacienteId(dto.getPacienteId());
        citaMedica.setDoctorId(dto.getDoctorId());
        citaMedica.setNivelPrioridad(dto.getNivelPrioridad());
        citaMedica.setMotivoConsulta(dto.getMotivoConsulta());
        citaMedica.setEstado("PENDIENTE");
        citaMedica.setMontoConsulta(dto.getMontoConsulta());
        citaMedica.setFechaCita(dto.getFechaCita());

        return citaMedicaRepository.save(citaMedica);
    }

    @Transactional
    public CitaMedica actualizarEstado(Long id, String nuevoEstado){
       CitaMedica citaMedica = citaMedicaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cita médica no encontrada con ID: " + id));

        citaMedica.setEstado(nuevoEstado);
        return citaMedicaRepository.save(citaMedica); 
    }

    private String generarCodigoCita() {
        int ultimoNumero = citaMedicaRepository.findAll().stream()
                .map(CitaMedica::getCodigoCita)
                .filter(codigo -> codigo != null && codigo.matches("CIT-2026-\\d{4}"))
                .map(codigo -> codigo.substring("CIT-2026-".length()))
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(0);

        return String.format("CIT-2026-%04d", ultimoNumero + 1);
    }
}