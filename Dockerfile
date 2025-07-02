# Usamos una imagen con JDK 21 y Maven para build
FROM maven:3.9.4-eclipse-temurin-21 as build

WORKDIR /app

# Copiamos pom.xml y descargamos dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código y construimos el jar
COPY src ./src
RUN mvn clean package -DskipTests

# Segunda etapa: imagen para correr solo el jar, más ligera
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiamos el jar construido de la etapa build
COPY --from=build /app/target/Corporalia-0.0.1-SNAPSHOT.jar app.jar

# Puerto que expone Spring Boot (igual que en application.properties)
EXPOSE 8080

# Comando para arrancar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
