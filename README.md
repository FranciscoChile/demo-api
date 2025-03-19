# Archivos incluídos
1. Código fuente
2. Archivo Postman para pruebas
3. Dockerfile para depliegue
4. Makefile para generar archivo Jar

# Prerequisitos
1. Tener Java 21 Corretto instalado

# Generación ejecutable y despliegue
1. Ejecutar archivo Makefile, primero dar permisos de ejecución (asumiendo máquina Linux)
```
chmod +x Makefile
```
luego
```
./Makefile
```

2. Ejecutar Docker build
```
docker build -t demo-api .
```

3. Ejecutar Docker run
```
docker run -d -p 8080:8080 demo-api
```
4. Para usar el archivo Postman se debe apuntar a la IP local, no usar localhost.



Swagger disponible en http://localhost:8005/swagger-ui.html
