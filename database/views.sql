-- Vista para consultar movimientos con información detallada
CREATE VIEW vista_movimientos_detallados AS
SELECT
    m.movimiento_id,
    c.numero_cuenta,
    p.nombre AS cliente,
    m.fecha,
    m.tipo_movimiento,
    m.valor,
    m.saldo_anterior,
    m.saldo_posterior,
    m.descripcion
FROM
    Movimientos m
        JOIN
    Cuenta c ON m.cuenta_id = c.cuenta_id
        JOIN
    Cliente cl ON c.cliente_id = cl.cliente_id
        JOIN
    Persona p ON cl.persona_id = p.persona_id