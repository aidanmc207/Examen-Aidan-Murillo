package cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CrearCitaDTO {

    private Long pacienteId;
    private Long doctorId;
    private String nivelPrioridad;
    private String motivoConsulta;
    private BigDecimal montoConsulta;
    private LocalDateTime fechaCita;

    public CrearCitaDTO() {
    }

    public CrearCitaDTO(Long pacienteId, Long doctorId, String nivelPrioridad, String motivoConsulta,
            BigDecimal montoConsulta) {
        this(pacienteId, doctorId, nivelPrioridad, motivoConsulta, montoConsulta, null);
    }

    public CrearCitaDTO(Long pacienteId, Long doctorId, String nivelPrioridad, String motivoConsulta,
            BigDecimal montoConsulta, LocalDateTime fechaCita) {
        this.pacienteId = pacienteId;
        this.doctorId = doctorId;
        this.nivelPrioridad = nivelPrioridad;
        this.motivoConsulta = motivoConsulta;
        this.montoConsulta = montoConsulta;
        this.fechaCita = fechaCita;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getNivelPrioridad() {
        return nivelPrioridad;
    }

    public void setNivelPrioridad(String nivelPrioridad) {
        this.nivelPrioridad = nivelPrioridad;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public BigDecimal getMontoConsulta() {
        return montoConsulta;
    }

    public void setMontoConsulta(BigDecimal montoConsulta) {
        this.montoConsulta = montoConsulta;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }
}
