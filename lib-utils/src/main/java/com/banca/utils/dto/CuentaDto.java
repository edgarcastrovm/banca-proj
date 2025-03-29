package com.banca.utils.dto;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer clienteId;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private BigDecimal saldoActual;
    private Boolean estado;
    private LocalDate fechaApertura;
}