FROM maven:3.9.0-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml .

RUN mvn -B dependency:go-offline

COPY src ./src

RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

RUN apk add --no-cache curl

COPY --from=builder /build/target/service-b-*.jar app.jar

RUN addgroup -g 1000 appuser && \
    adduser -D -u 1000 -G appuser appuser

RUN chown -R appuser:appuser /app

USER appuser

EXPOSE 8080 9002

ENTRYPOINT ["java", "-jar", "app.jar"]