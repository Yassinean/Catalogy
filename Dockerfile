# Stage 1: Build
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Copiez uniquement les fichiers nécessaires pour résoudre les dépendances en premier
COPY pom.xml . 
COPY src ./src

# Résolution des dépendances et construction du projet
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jdk-alpine AS runtime
WORKDIR /app

# Copiez le JAR généré depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Exposez le port sur lequel l'application écoute
EXPOSE 8082

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
