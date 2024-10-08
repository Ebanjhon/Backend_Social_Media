# Sử dụng một image có JDK
FROM openjdk:21-jdk

# Thiết lập thư mục làm việc
WORKDIR /app

# Copy file JAR vào container
COPY target/social-media-0.0.1-SNAPSHOT.jar app.jar

# Chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
