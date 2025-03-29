package com.banca.transactions.service;

import com.banca.transactions.mapper.ApiMapper;
import com.banca.transactions.repository.IMovimientoRepository;
import com.banca.utils.db.entity.Movimiento;
import com.banca.utils.db.entity.Persona;
import com.banca.utils.dto.MovimientoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoService {
    @Autowired
    IMovimientoRepository movimientoRepository;
    private final ApiMapper apiMapper;

    public MovimientoService(ApiMapper apiMapper) {
        this.apiMapper = apiMapper;
    }


    public List<Movimiento> findAll() {
        var movimientos = movimientoRepository.findAll();
        if (movimientos.isEmpty()) {
            return null;
        }
        return movimientos.stream().toList();
    }

    public Movimiento findById(Integer id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    public Movimiento save(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }
    public Movimiento update(Integer id, MovimientoDto movimientoDto) {
        Movimiento movimientoToUpdate = findById(id);
        if (movimientoToUpdate == null) {
            return null;
        }
        movimientoToUpdate.setFecha(movimientoDto.getFecha());
        movimientoToUpdate.setTipoMovimiento(movimientoDto.getTipoMovimiento());
        movimientoToUpdate.setValor(movimientoDto.getValor());
        movimientoToUpdate.setSaldoAnterior(movimientoDto.getSaldoAnterior());
        movimientoToUpdate.setSaldoPosterior(movimientoDto.getSaldoPosterior());
        movimientoToUpdate.setDescripcion(movimientoDto.getDescripcion());

        return movimientoRepository.save(movimientoToUpdate);
    }
    public Movimiento partialUpdate(Integer id, Map<String, Object> movimientoMap) {
        Movimiento movimientoToUpdate = findById(id);
        if (movimientoToUpdate == null) {
            return null;
        }
        movimientoToUpdate = apiMapper.movimientoHasMapToMovimiento(movimientoToUpdate, movimientoMap);
        if (movimientoToUpdate == null) {
            return null;
        }
        return movimientoRepository.save(movimientoToUpdate);
    }

}
