package com.banca.utils.dto.data;

import com.banca.utils.db.view.VistaMovimientosDetalle;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CuentaResumen {
    private String numeroCuenta;
    private BigDecimal saldoActual;
    private List<MovimientoDetalle> movimientos;
}
