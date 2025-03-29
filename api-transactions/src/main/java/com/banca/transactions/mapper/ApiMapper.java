package com.banca.transactions.mapper;

import com.banca.transactions.constants.Constants;
import com.banca.transactions.repository.ICuentaRepository;
import com.banca.utils.ApiClient;
import com.banca.utils.db.entity.Cliente;
import com.banca.utils.db.entity.Cuenta;
import com.banca.utils.db.entity.Movimiento;
import com.banca.utils.dto.CuentaDto;
import com.banca.utils.dto.MovimientoDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

@Component
public class ApiMapper {
    ApiClient apiClient;

    private final ICuentaRepository cuentaRepository;

    public ApiMapper(ICuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
        apiClient = new ApiClient(Constants.API_CUSTOMER);
    }

    public Cuenta CuentaDtoToCuenta(CuentaDto cuentaDto) {
        Cliente cliente = getClienteById(cuentaDto.getClienteId());
        if (cliente == null) {
            return null;
        }
        Cuenta cuenta = new Cuenta();
        cuenta.setId(cuentaDto.getId());
        cuenta.setCliente(cliente);
        cuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDto.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
        cuenta.setSaldoActual(cuentaDto.getSaldoActual());
        cuenta.setEstado(cuentaDto.getEstado());
        cuenta.setFechaApertura(cuentaDto.getFechaApertura());

        return cuenta;
    }

    public Cuenta CuentaHasMapToCuenta(Cuenta cuenta, Map<String, Object> cuentaMap) {
        cuentaMap.forEach((key, value) -> {
            switch (key) {
                case "clienteId":
                    Cliente cliente = getClienteById((Integer) value);
                    cuenta.setCliente(cliente);
                    break;
                case "numeroCuenta":
                    cuenta.setNumeroCuenta((String) value);
                    break;
                case "tipoCuenta":
                    cuenta.setTipoCuenta((String) value);
                    break;
                case "saldoInicial":
                    cuenta.setSaldoInicial((BigDecimal) value);
                    break;
                case "saldoActual":
                    cuenta.setSaldoActual((BigDecimal) value);
                    break;
                case "fechaApertura":
                    cuenta.setFechaApertura(LocalDate.parse((String) value));
                    break;
                case "estado":
                    cuenta.setEstado((boolean) value);
                    break;
                default:
                    break;

            }
        });
        if (cuenta.getCliente() == null) {
            return null;
        }
        return cuenta;
    }

    public Cliente getClienteById(Integer clienteId) {

        ResponseEntity<Cliente> response = apiClient.makeRequest(
                HttpMethod.GET,
                Constants.API_CUSTOMER_CLIENTE + "/" + clienteId,
                null,
                Cliente.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            //System.err.println("Error: " + response.getStatusCode());
            return null;
        }
    }

    public Movimiento movimientoDtoToMovimiento(MovimientoDto movimientoDto) {
        Cuenta cuenta = cuentaRepository.findById(movimientoDto.getCuentaId()).orElse(null);
        if (cuenta == null) {
            return null;
        }
        Movimiento movimiento = new Movimiento();
        movimiento.setId(movimientoDto.getId());
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(movimientoDto.getFecha());
        movimiento.setTipoMovimiento(movimientoDto.getTipoMovimiento());
        movimiento.setValor(movimientoDto.getValor());
        movimiento.setSaldoAnterior(movimientoDto.getSaldoAnterior());
        movimiento.setSaldoPosterior(movimientoDto.getSaldoPosterior());
        return movimiento;
    }

    public Movimiento movimientoHasMapToMovimiento(Movimiento movimiento , Map<String, Object> movimientoMap){
        movimientoMap.forEach((key, value) -> {
            switch (key) {
                case "id":
                    movimiento.setId((Integer) value);
                    break;
                case "cuentaId":
                    movimiento.setCuenta(cuentaRepository.findById((Integer) value).orElse(null));
                    break;
                case "fecha":
                    movimiento.setFecha(Instant.parse(value.toString()));
                    break;
                case "tipoMovimiento":
                    movimiento.setTipoMovimiento(String.valueOf(value));
                    break;
                case "valor":
                    movimiento.setValor((BigDecimal) value);
                    break;
                case "saldoAnterior":
                    movimiento.setSaldoAnterior((BigDecimal) value);
                    break;
                case "saldoPosterior":
                    movimiento.setSaldoPosterior((BigDecimal) value);
                    break;
                case "descripcion":
                    movimiento.setDescripcion((String) value);
                    break;
                default:
                    break;
            }
        });
        if (movimiento.getCuenta() == null) {
            return null;
        }
        return movimiento;
    }

}
