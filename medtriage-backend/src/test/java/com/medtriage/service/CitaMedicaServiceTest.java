package com.medtriage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.dto.CrearCitaDTO;
import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.model.CitaMedica;
import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.repository.CitaMedicaRepository;
import cr.ac.ucr.paraiso.ie.c4h845.medtriage_backend.service.CitaMedicaService;

@ExtendWith(MockitoExtension.class)
class CitaMedicaServiceTest {

    @Mock
    private CitaMedicaRepository citaMedicaRepository;

    @InjectMocks
    private CitaMedicaService citaMedicaService;

    @Test
    void crearCita_guardaCitaConCodigoYEstadoPendiente() {
        LocalDateTime fechaCita = LocalDateTime.of(2026, 10, 20, 9, 30);
        CrearCitaDTO dto = new CrearCitaDTO(
                1L,
                2L,
                "ALTA",
                "Dolor persistente",
                new BigDecimal("15000.00"),
                fechaCita);

        when(citaMedicaRepository.findAll()).thenReturn(Collections.emptyList());
        when(citaMedicaRepository.save(any(CitaMedica.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CitaMedica citaCreada = citaMedicaService.crearCita(dto);

        assertNotNull(citaCreada);
        assertEquals("CIT-2026-0001", citaCreada.getCodigoCita());
        assertEquals(1L, citaCreada.getPacienteId());
        assertEquals(2L, citaCreada.getDoctorId());
        assertEquals("ALTA", citaCreada.getNivelPrioridad());
        assertEquals("Dolor persistente", citaCreada.getMotivoConsulta());
        assertEquals("PENDIENTE", citaCreada.getEstado());
        assertEquals(new BigDecimal("15000.00"), citaCreada.getMontoConsulta());
        assertEquals(fechaCita, citaCreada.getFechaCita());
        verify(citaMedicaRepository).save(citaCreada);
    }
}
