## BANCA PROJECT
Proyecto para simulación de registro y movimientos transaccional bancario

### MÓDULOS
| Módulo                | Detalle                                                                                                            |
|-----------------------|--------------------------------------------------------------------------------------------------------------------|
| **lib-utils**         | Contiene las Entidades, DTO y funciones utilitarias para el proyecto                                               |
| **api-customer**      | API que administra el ingreso de clientes                                                                          |
| **api-transactions**  | Api que administra la creación de cuentas e ingreso de transacciones                                               |
| **database**          | Contiene los escripts para la creación de tablas y vistas en base de datos                                         |
| **deploy**            | Contiene los scripts para la creación de los contenedores, instalador en bash y collection de postman para pruebas |

***NOTA:*** La versión actual no cuenta con interceptos de errores, identificación de request y tampoco implementación de logs. Estas carteristicas sera implemntadas en futuras mejoras
***el producto es básico pero funcional***.

[Detalle módulos](./docs/MODULES_DETAIL.md)

### INSTALCIÓN 

| Requisito          | Detalle                                                                         |
|--------------------|---------------------------------------------------------------------------------|
| **Java**           | Se necesita java 17 o superior                                                  |
| **maven**          | Se necesita maven 3.9.9 o superior                                              |
| **Docker**         | Necesario para la creación de contenedores                                      |
| **docker-compose** | Necesario para la ejecución de multi-contenedores                               |
| **Windows WSL**    | Para sistemas windows se debe tener configurado el WSL con docker, java y maven |
| **Git**            | Opcional para clonar el repo                                                    |
| **Linux**          | Para sistemas Linux cualquier terminal de linea de comandos es suficiente       |

[Instalación bash](./docs/INSTALLATION.md)