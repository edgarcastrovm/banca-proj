#!/bin/bash

work_dir=$(pwd)
path_customer=$work_dir/../api-customer
path_transaction=$work_dir/../api-transactions
path_lib=$work_dir/../lib-utils
path_jars=$work_dir/app-target
path_sql=$work_dir/../database/BaseDatos.sql
path_postgres=$work_dir/containers/postgres
echo "------------------------------------------------------------------------------------------------"
echo "------------------------------------ COMPIA DLL DE LAS TABLAS ----------------------------------"
cat $path_sql
cat $path_sql > $path_postgres/database-init.sql
cat $path_postgres/database-init.sql

echo "------------------------------------------------------------------------------------------------"
echo "------------------------------------ COMPILA LIBRERIA ------------------------------------------"
cd $path_lib
mvn clean install

echo "------------------------------------------------------------------------------------------------"
echo "------------------------------------ COMPILA API-CUSTOMER -------------------------------------"
cd $path_customer
mvn clean package
cp target/api-customer-0.0.1-SNAPSHOT.jar $path_jars/api-customer.jar

echo "------------------------------------------------------------------------------------------------"
echo "------------------------------------ COMPILA API-TRANSACTIONS ----------------------------------"
cd $path_transaction
mvn clean package
cp target/api-transactions-0.0.1-SNAPSHOT.jar $path_jars/api-transactions.jar

chmod 644 $path_jars/*.jar

echo "------------------------------------------------------------------------------------------------"
echo "------------------------------------ CONSTRUIR Y LEVANTAR LOS CONTENEDORES ---------------------"
cd $work_dir
docker-compose down #ELIMINAMOS CONTENEDORES ANTIGUOS PARA EVITAR ERRORES
docker-compose up --build -d
echo "Esperando a que los servicios inicien..."
sleep 35 #En promedio esto tarda en levantar los contenedores
docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
echo "------------------------------------------------------------------------------------------------"
echo "------------------------------------ INSTALACIÓN COMPLETA !!!!"
echo "------------------------------------------------------------------------------------------------"