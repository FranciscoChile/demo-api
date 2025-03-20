# Archivos incluídos
1. Código fuente
2. Archivo Postman para pruebas
3. Dockerfile para depliegue
4. Makefile para generar archivo Jar

# Prerequisitos
1. Tener Java 21 Corretto instalado
2. Docker Desktop

# Generación ejecutable y despliegue
1. Ejecutar archivo Makefile, primero dar permisos de ejecución (asumiendo máquina Linux)
```
chmod +x Makefile
```
luego
```
./Makefile
```

2. Ejecutar Docker compose (Docker crea la base datos, Spring boot crea la tabla)
```
docker-compose up -d
```

4. Para usar el archivo Postman se debe apuntar a la IP local, no usar localhost.



Swagger disponible en http://localhost:8005/swagger-ui.html
