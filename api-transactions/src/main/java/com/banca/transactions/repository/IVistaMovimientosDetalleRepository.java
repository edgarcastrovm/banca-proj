package com.banca.transactions.repository;

import com.banca.utils.db.view.VistaMovimientosDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface IVistaMovimientosDetalleRepository extends JpaRepository<VistaMovimientosDetalle, Integer> {
    List<VistaMovimientosDetalle> findByClienteAndFechaBetween(
            String cliente,
            Instant fechaInicio,
            Instant fechaFin);

    List<VistaMovimientosDetalle> findByNumeroCuentaAfterAndFechaBetween(String numeroCuenta, Instant inicio, Instant fin);
}