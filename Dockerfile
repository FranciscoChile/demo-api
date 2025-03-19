FROM amazoncorretto:21.0.4-alpine3.18
# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app
# Copiamos el JAR generado en el contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
# Exponemos el puerto 8080 (el que usa Spring Boot por defecto)
EXPOSE 8080
# Comando para ejecutar la aplicación cuando el contenedor arranque
ENTRYPOINT ["java", "-jar", "app.jar"]