package com.banca.utils.dto.data;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class MovimientoDetalle {
    private Instant fecha;
    private String tipo;
    private BigDecimal valor;
    private BigDecimal saldoAnterior;
    private BigDecimal saldoPosterior;
    private String descripcion;

}
