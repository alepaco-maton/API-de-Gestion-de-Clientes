# Utilizamos una imagen base que incluya JDK 17+
FROM openjdk:17-alpine

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo WAR de la aplicación al contenedor
COPY target/demo-0.0.1-SNAPSHOT.war /app

# Exponemos el puerto en el que la aplicación Spring Boot escucha (generalmente 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.war"]