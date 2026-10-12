package cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cita_medica")
public class CitaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_cita", nullable = false, unique = true, length = 30)
    private String codigoCita;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "nivel_prioridad", nullable = false, length = 20)
    private String nivelPrioridad;

    @Column(name = "motivo_consulta", nullable = false, length = 255)
    private String motivoConsulta;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "monto_consulta", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoConsulta;

    @Column(name = "fecha_cita", nullable = false)
    private LocalDateTime fechaCita;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Doctor doctor;

    public CitaMedica(Long id, String codigoCita, Long pacienteId, Long doctorId, String nivelPrioridad,
            String motivoConsulta, String estado, BigDecimal montoConsulta, LocalDateTime fechaCita) {
        this.id = id;
        this.codigoCita = codigoCita;
        this.pacienteId = pacienteId;
        this.doctorId = doctorId;
        this.nivelPrioridad = nivelPrioridad;
        this.motivoConsulta = motivoConsulta;
        this.estado = estado;
        this.montoConsulta = montoConsulta;
        this.fechaCita = fechaCita;
    }

    public CitaMedica() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "CitaMedica [id=" + id + ", codigoCita=" + codigoCita + ", pacienteId=" + pacienteId + ", doctorId="
                + doctorId + ", nivelPrioridad=" + nivelPrioridad + ", motivoConsulta=" + motivoConsulta + ", estado="
                + estado + ", montoConsulta=" + montoConsulta + ", fechaCita=" + fechaCita + "]";
    }
}
