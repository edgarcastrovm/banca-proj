[Home](../README.md)
## INSTALACIÓN

La instalción del componente es relativamente facíl siempre y cuando se tenga instalado y configurado todos los requisitos.

La instalción esta basada en componentes docker por ende no necesitamos dedicar esfuersos a configurar servidores

#### 1) Clonar repositorio
```shell
git clone https://github.com/edgarcastrovm/banca-proj.git
cd banca-proj
```

#### 2) Ingresar al proyecto
```shell
cd banca-proj
```

#### 3) Ingresar al directorio deploy

```shell
cd deploy
```

#### 4) Dar permisos de ejecución al archivo instalador
```shell
chmod u+x install.sh
```
#### 5) Ejecutar instalador

```shell
./install.sh
```
***La instalación llema un poco de tiempo por favor sea paciente***


#### 6) Validar que los servicios esten arriba

http://localhost:8081/clientes/hc

http://localhost:8082/cuentas/hc

http://localhost:8082/movimientos/hc

## IMPORTANTE
**Docker** puede instalarse de 2 maneras por lo tanto si tenemos **Docker Desktop** tenga en cuenta que el comando **docker-compose** no funcionara, así que que para esos casos tendrá
que editar el archivo **install.sh** y adecuar el comando correspondiente.

[Home](../README.md)