package com.banca.transactions.service;

import com.banca.transactions.mapper.ApiMapper;
import com.banca.transactions.repository.ICuentaRepository;
import com.banca.transactions.repository.IMovimientoRepository;
import com.banca.utils.ApiResponse;
import com.banca.utils.db.entity.Cuenta;
import com.banca.utils.db.entity.Movimiento;
import com.banca.utils.db.entity.Persona;
import com.banca.utils.dto.MovimientoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoService {
    @Autowired
    private IMovimientoRepository movimientoRepository;
    @Autowired
    private ICuentaRepository cuentaRepository;
    private final ApiMapper apiMapper;

    public MovimientoService(ApiMapper apiMapper) {
        this.apiMapper = apiMapper;
    }


    public ApiResponse<?> findAll() {
        var movimientos = movimientoRepository.findAll();
        if(movimientos == null) {
            return ApiResponse.error(HttpStatus.NOT_FOUND,"Movimiento no encontrado");
        }
        return ApiResponse.success("OK",movimientos);
    }

    public ApiResponse<?> findById(Integer id) {
        Movimiento movimiento = movimientoRepository.findById(id).orElse(null);
        if(movimiento == null) {
            return ApiResponse.error(HttpStatus.NOT_FOUND,"Movimiento no encontrado");
        }
        return ApiResponse.success("OK",movimiento);
    }

    @Transactional
    public ApiResponse<?> save(MovimientoDto movimientoDto) {
        //El monto no puede ser 0
        if (movimientoDto.getValor().compareTo(BigDecimal.ZERO)==0) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST,"El monto no puede ser 0");
        }
        Movimiento movimiento = null;
        try {
            movimiento =  apiMapper.movimientoDtoToMovimiento(movimientoDto);
        }catch (Exception e) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST,"Error:" + e.getMessage());
        }
        BigDecimal saldo = movimiento.getCuenta().getSaldoActual();
        BigDecimal valor = movimientoDto.getValor();
        //Sin fondos
        if(saldo.compareTo(BigDecimal.ZERO)<=0) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST,"Saldo no dispopnible");
        }
        BigDecimal diferencia = saldo.add(valor);
        //Saldo insuficiente
        if(diferencia.compareTo(BigDecimal.ZERO)<0) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST,"Saldo es insuficiente");
        }
        movimiento.setSaldoAnterior(saldo);
        movimiento.setSaldoPosterior(saldo.add(valor));

        Cuenta cuenta = movimiento.getCuenta();
        cuenta.setSaldoActual(saldo.add(valor));
        cuentaRepository.save(cuenta);
        movimientoRepository.save(movimiento);
        return ApiResponse.success("OK",movimiento);
    }

    public ApiResponse<?> update(Integer id, MovimientoDto movimientoDto) {
        Movimiento movimientoToUpdate = null;
        try {
            movimientoToUpdate = movimientoRepository.findById(id).orElse(null);
        }catch (Exception e) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST,"Error:" + e.getMessage());
        }
        if (movimientoToUpdate == null) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST,"Movimiento no encontrado");
        }
        movimientoToUpdate.setFecha(movimientoDto.getFecha());
        movimientoToUpdate.setTipoMovimiento(movimientoDto.getTipoMovimiento());
        movimientoToUpdate.setValor(movimientoDto.getValor());
        movimientoToUpdate.setSaldoAnterior(movimientoDto.getSaldoAnterior());
        movimientoToUpdate.setSaldoPosterior(movimientoDto.getSaldoPosterior());
        movimientoToUpdate.setDescripcion(movimientoDto.getDescripcion());

        movimientoRepository.save(movimientoToUpdate);

        return ApiResponse.success("OK",movimientoToUpdate);
    }

    public ApiResponse<?> partialUpdate(Integer id, Map<String, Object> movimientoMap) {
        Movimiento movimientoToUpdate = null;
        try {
             movimientoToUpdate = movimientoRepository.findById(id).orElse(null);
        }catch (Exception e) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST,"Error:" + e.getMessage());
        }
        if (movimientoToUpdate == null) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST,"Movimiento no encontrado");
        }
        movimientoToUpdate = apiMapper.movimientoHasMapToMovimiento(movimientoToUpdate, movimientoMap);
        if (movimientoToUpdate == null) {
            return ApiResponse.error(HttpStatus.BAD_REQUEST,"Movimiento no encontrado");
        }
        movimientoRepository.save(movimientoToUpdate);
        return ApiResponse.success("OK",movimientoToUpdate);
    }
}
