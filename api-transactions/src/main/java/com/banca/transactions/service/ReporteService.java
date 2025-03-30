package com.banca.transactions.service;

import com.banca.transactions.report.IReport;
import com.banca.transactions.report.ReportCsv;
import com.banca.transactions.report.ReportPdf;
import com.banca.transactions.repository.ICuentaRepository;
import com.banca.transactions.repository.IVistaMovimientosDetalleRepository;
import com.banca.utils.ApiResponse;
import com.banca.utils.db.entity.Cuenta;
import com.banca.utils.db.view.VistaMovimientosDetalle;
import com.banca.utils.dto.data.CuentaResumen;
import com.banca.utils.dto.data.EstadoCuentaReporte;
import com.banca.utils.dto.data.MovimientoDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ReporteService {
    @Autowired
    IVistaMovimientosDetalleRepository ivistaMovimientosDetalleRepository;
    @Autowired
    ICuentaRepository cuentaRepository;

    public ApiResponse<?> getReporte(HttpHeaders headers, Integer Integer, LocalDate fechaInicio, LocalDate fechaFin) {
        IReport report = getImplemetation(headers);
        if (report == null) {
            return ApiResponse.error(HttpStatus.METHOD_NOT_ALLOWED, "Función no definida");
        }

        if (fechaInicio.isAfter(fechaFin)) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST, "Rango de fechas incorrecto");
        }

        Instant inicio = fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant fin = fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant();

        List<Cuenta> cuentas = cuentaRepository.findAllByCliente_Id(Integer).stream().toList();
        EstadoCuentaReporte estadoCuentaReporte = new EstadoCuentaReporte();
        List<CuentaResumen> lstResumen = new ArrayList<CuentaResumen>();

        //Cabecera reporte
        estadoCuentaReporte.setCliente(cuentas.get(0).getCliente().getPersona().getNombre());
        estadoCuentaReporte.setFechaInicio(fechaInicio);
        estadoCuentaReporte.setFechaFin(fechaFin);
        estadoCuentaReporte.setFechaGeneracion(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        //Detalle de cuentas
        cuentas.forEach(cuenta -> {
            CuentaResumen resumen = new CuentaResumen();
            resumen.setNumeroCuenta(cuenta.getNumeroCuenta());
            resumen.setSaldoActual(cuenta.getSaldoActual());
            //Detalle de movimientos
            List<VistaMovimientosDetalle> movimientos = ivistaMovimientosDetalleRepository
                    .findByNumeroCuentaAfterAndFechaBetween(cuenta.getNumeroCuenta(), inicio, fin);
            List<MovimientoDetalle> detalles = new ArrayList<MovimientoDetalle>();
            movimientos.forEach(movimiento -> {
                MovimientoDetalle detalle =new MovimientoDetalle();
                detalle.setTipo(movimiento.getTipoMovimiento());
                detalle.setValor(movimiento.getValor());
                detalle.setDescripcion(movimiento.getDescripcion());
                detalle.setFecha(movimiento.getFecha());
                detalle.setSaldoPosterior(movimiento.getSaldoPosterior());
                detalle.setSaldoAnterior(movimiento.getSaldoAnterior());
                detalles.add(detalle);
            });
            resumen.setMovimientos(detalles);
            lstResumen.add(resumen);
        });
        estadoCuentaReporte.setCuentas(lstResumen);

        List<EstadoCuentaReporte> items = new ArrayList<EstadoCuentaReporte>();
        items.add(estadoCuentaReporte);
        var reporte = report.makeReport(getHeadersReport(), items);

        return ApiResponse.success("OK", reporte);
    }

    private HashMap<String, String> getHeadersReport() {
        HashMap<String, String> headesReport = new HashMap();
        headesReport.put("movimiento_id", "ID");
        headesReport.put("numero_cuenta", "NUMERO CUENTA");
        headesReport.put("cliente", "CLIENTE");
        headesReport.put("fecha", "FECHA");
        headesReport.put("tipo_movimiento", "TIPO MOVIMIENTO");
        headesReport.put("valor", "VALOR");
        headesReport.put("saldo_anterior", "SALDO ANTERIOR");
        headesReport.put("saldo_posterior", "SALDO POSTERIOR");
        headesReport.put("descripcion", "DESCRIPCION");
        return headesReport;
    }

    private IReport getImplemetation(HttpHeaders headers) {
        String type = headers.get("type-report").get(0).toString();
        if (type.equals("json")) {
            return new ReportCsv();
        } else if (type.equals("pdf")) {
            return new ReportPdf();
        }
        return null;
    }
}
