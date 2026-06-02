# Tahap 1: Build aplikasi menggunakan Maven & Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build file .jar tanpa menjalankan unit test
RUN mvn clean package -DskipTests

# Tahap 2: Jalankan aplikasi menggunakan JRE yang lebih ringan
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Ambil file jar dari Tahap 1 (sesuai nama artifak di pom.xml)
COPY --from=build /app/target/backend-1.0.0.jar app.jar
# Buka port 8080
EXPOSE 8080
# Perintah untuk menyalakan server
ENTRYPOINT ["java", "-jar", "app.jar"]
