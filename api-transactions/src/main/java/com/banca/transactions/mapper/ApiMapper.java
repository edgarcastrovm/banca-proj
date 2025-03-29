package com.banca.transactions.mapper;

import com.banca.transactions.constants.Constants;
import com.banca.utils.ApiClient;
import com.banca.utils.db.entity.Cliente;
import com.banca.utils.db.entity.Cuenta;
import com.banca.utils.dto.CuentaDto;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class ApiMapper {
    ApiClient apiClient;

    public ApiMapper() {
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
}
