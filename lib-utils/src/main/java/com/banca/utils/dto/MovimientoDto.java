package com.banca.utils.dto;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer cuentaId;
    private Instant fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldoAnterior;
    private BigDecimal saldoPosterior;
    private String descripcion;
}
