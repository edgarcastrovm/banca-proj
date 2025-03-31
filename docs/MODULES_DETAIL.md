[Home](../README.md)
## DETALLE MÓDULOS 
#### General
Los archivos application.properties reciben variables de entorno en las cuales se setean las configuraciones necesarias para su funcionamiento.

#### lib-utils
En esta librería almacenamos todas las entidades de base datos, DTOs y clases data para mapear los valores de las entidades a nuestras necesidades.

Tenemos una clase ApiClient la cual es un utilitario que nos sirve para consumir apis externas.

Tenemos una clase ApiResponse la cual se encarga de mapear las respuestas de nuesta api con un estandar codigo, mensaje, data.

```txt
lib-utils
├── pom.xml
└── src
    └── main
        ├── java
        │   └── com
        │       └── banca
        │           └── utils
        │               ├── ApiClient.java
        │               ├── ApiResponse.java
        │               ├── db
        │               │   ├── entity
        │               │   │   ├── Cliente.java
        │               │   │   ├── Cuenta.java
        │               │   │   ├── Movimiento.java
        │               │   │   └── Persona.java
        │               │   └── view
        │               │       └── VistaMovimientosDetalle.java
        │               └── dto
        │                   ├── CuentaDto.java
        │                   ├── data
        │                   │   ├── CuentaResumen.java
        │                   │   ├── EstadoCuentaReporte.java
        │                   │   └── MovimientoDetalle.java
        │                   └── MovimientoDto.java
        └── resources
            └── application.properties
```

#### api-customer
Este modulo está construido de forma básica Repositoriy, Service y Controller con respuestas JSON directas es decir mapeando directamente el objeto
```txt
api-customer
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── banca
    │   │           └── customer
    │   │               ├── config
    │   │               │   └── CorsConfig.java
    │   │               ├── controller
    │   │               │   └── ClienteController.java
    │   │               ├── CustomerApiApplication.java
    │   │               ├── repository
    │   │               │   ├── IClienteRepository.java
    │   │               │   └── IPersonaRepository.java
    │   │               └── service
    │   │                   └── ClienteService.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── banca
                    └── customer
                        └── CustomerApiApplicationTests.java

```

#### api-transactions
En este módulo nos expandimos mas creamos además de Repositorios, Service y Controller se mapear todas las respuestas a una entidad response en la cual se mapea un codigo de respuesta un mensaje y la data.

Para el tema de reporteria en los headers de la petición enviamos el tipo de reporte y dimamicamente de carga la implementación correspondiente para el tipo de reporte, actualmente solo esta implementado JSON

```txt
api-transactions
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── banca
    │   │           └── transactions
    │   │               ├── config
    │   │               │   └── ApiUrlProperties.java
    │   │               ├── constants
    │   │               │   └── Constants.java
    │   │               ├── controller
    │   │               │   ├── CuentaController.java
    │   │               │   ├── MovimientoController.java
    │   │               │   └── ReporteController.java
    │   │               ├── mapper
    │   │               │   └── ApiMapper.java
    │   │               ├── report
    │   │               │   ├── IReport.java
    │   │               │   ├── ReportCsv.java
    │   │               │   └── ReportPdf.java
    │   │               ├── repository
    │   │               │   ├── ICuentaRepository.java
    │   │               │   ├── IMovimientoRepository.java
    │   │               │   └── IVistaMovimientosDetalleRepository.java
    │   │               ├── service
    │   │               │   ├── CuentaService.java
    │   │               │   ├── MovimientoService.java
    │   │               │   └── ReporteService.java
    │   │               └── TransactionsApiApplication.java
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── banca
                    └── transactions
                        └── TransactionsApiApplicationTests.java

```

#### database
Aqui solo tenemos los script que contienen los DLL para la creación de tablas y vistas en db.

```txt
database
└── BaseDatos.sql
```

#### deploy
En este módulo consta toda la estructura y scripts para crear los contenedores y poder deployar la solución 

Aqui también se encuentra el api collection de postman **(REST-API BANCA.postman_collection.json)** para probar la solución via postman.

```txt
deploy
├── app-target
│   ├── api-customer.jar
│   └── api-transactions.jar
├── containers
│   ├── api-customer
│   │   └── Dockerfile
│   ├── api-transacctions
│   │   └── Dockerfile
│   └── postgres
│       ├── database-initial-data.sql
│       └── database-init.sql
├── docker-compose.yml
├── install.sh
└── REST-API BANCA.postman_collection.json
```

[Home](../README.md)