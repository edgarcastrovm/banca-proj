package com.banca.utils.dto.data;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EstadoCuentaReporte {
    private String cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDateTime fechaGeneracion;
    private List<CuentaResumen> cuentas;
}
