-- Creación de la base de datos
-- CREATE DATABASE sistema_bancario;

-- Creación de tablas

-- Tabla Persona
-- genero M: Masculino, F: Femenino, O: Otro
CREATE TABLE Persona (
    persona_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero CHAR(1) CHECK (genero IN ('M', 'F', 'O')),
    edad INTEGER CHECK (edad >= 18 AND edad <= 120),
    identificacion VARCHAR(20) UNIQUE NOT NULL,
    direccion TEXT,
    telefono VARCHAR(15)
);

-- Tabla Cliente
-- estado TRUE: Activo, FALSE: Inactivo
CREATE TABLE Cliente (
    cliente_id SERIAL PRIMARY KEY,
    persona_id INTEGER UNIQUE NOT NULL,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (persona_id) REFERENCES Persona(persona_id) ON DELETE CASCADE
);

-- Tabla Cuenta
-- estado TRUE: Activa, FALSE: Inactiva
CREATE TABLE Cuenta (
    cuenta_id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    tipo_cuenta VARCHAR(20) CHECK (tipo_cuenta IN ('Ahorros', 'Corriente')),
    saldo_inicial DECIMAL(15, 2) DEFAULT 0.00,
    saldo_actual DECIMAL(15, 2) DEFAULT 0.00,
    estado BOOLEAN DEFAULT TRUE,
    fecha_apertura DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id) ON DELETE CASCADE
);

-- Tabla Movimientos
CREATE TABLE Movimientos (
     movimiento_id SERIAL PRIMARY KEY,
     cuenta_id INTEGER NOT NULL,
     fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     tipo_movimiento VARCHAR(20) CHECK (tipo_movimiento IN ('CREDITO','DEBITO')),
     valor DECIMAL(15, 2) NOT NULL,
     saldo_anterior DECIMAL(15, 2) NOT NULL,
     saldo_posterior DECIMAL(15, 2) NOT NULL,
     descripcion TEXT,
     FOREIGN KEY (cuenta_id) REFERENCES Cuenta(cuenta_id) ON DELETE CASCADE
);

-- Índices para mejorar el rendimiento opcionales
-- CREATE INDEX id1_cliente_persona ON Cliente(persona_id);
-- CREATE INDEX id1_cuenta_cliente ON Cuenta(cliente_id);
-- CREATE INDEX id1_movimientos_cuenta ON Movimientos(cuenta_id);
-- CREATE INDEX id1_movimientos_fecha ON Movimientos(fecha);