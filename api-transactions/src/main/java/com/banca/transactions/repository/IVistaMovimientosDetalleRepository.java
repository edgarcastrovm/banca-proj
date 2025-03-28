package com.banca.transactions.repository;

import com.banca.utils.db.view.VistaMovimientosDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVistaMovimientosDetalleRepository extends JpaRepository<VistaMovimientosDetalle, Integer> {
}