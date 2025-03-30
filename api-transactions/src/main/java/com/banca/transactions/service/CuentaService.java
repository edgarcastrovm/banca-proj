package com.banca.transactions.service;

import com.banca.transactions.constants.Constants;
import com.banca.transactions.mapper.ApiMapper;
import com.banca.transactions.repository.ICuentaRepository;
import com.banca.utils.ApiClient;
import com.banca.utils.db.entity.Cliente;
import com.banca.utils.db.entity.Cuenta;
import com.banca.utils.dto.CuentaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CuentaService {

    @Autowired
    private ICuentaRepository cuentaRepository;
    private final ApiMapper mapper;

    public CuentaService(ApiMapper mapper) {
        this.mapper = mapper;
    }

    public List<Cuenta> findAll() {
        var cuentas = cuentaRepository.findAll();
        if (cuentas.isEmpty()) {
            return null;
        }
        return cuentas.stream().toList();
    }

    public Cuenta findById(Integer id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    public Cuenta save(CuentaDto cuentaDto) {
        Cuenta cuenta = mapper.CuentaDtoToCuenta(cuentaDto);
        if (cuenta == null) {
            return null;
        }
        return cuentaRepository.save(cuenta);
    }

    public Cuenta update(Integer id, CuentaDto cuentaDto) {
        Cuenta cuenta = mapper.CuentaDtoToCuenta(cuentaDto);
        if (cuenta == null) {
            return null;
        }
        cuenta.setId(id);
        return cuentaRepository.save(cuenta);
    }

    public Cuenta partialUpdate(Integer id, Map<String, Object> cuentaMap) {
        Cuenta cuenta = findById(id);
        if (cuenta == null) {
            return null;
        }
        Cuenta cuentaToUpdate = mapper.CuentaHasMapToCuenta(cuenta, cuentaMap);
        if (cuentaToUpdate == null) {
            return null;
        }
        return cuentaRepository.save(cuentaToUpdate);
    }
}
