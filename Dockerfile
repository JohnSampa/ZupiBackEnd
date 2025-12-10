## Estágio 1: BUILD
# Usa uma imagem especializada que já inclui Maven e JDK 21
FROM maven:3.9-jdk-21 AS build

# Define o diretório de trabalho
WORKDIR /app

# Otimização de Cache: Copia o pom.xml e baixa as dependências primeiro.
# Se o pom.xml não mudar, o Docker usa o cache aqui, acelerando o build.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e compila
COPY src .
RUN mvn clean install -DskipTests


## Estágio 2: PRODUÇÃO (Runtime)
# Usa uma imagem segura, leve e que contém apenas o JRE 21 (mais leve que o JDK)
FROM eclipse-temurin:21-jre-jammy

# Expõe a porta que a aplicação irá usar
EXPOSE 8080

# Define o usuário que rodará a aplicação (boa prática de segurança)
USER nonroot:nonroot

# Copia o JAR do estágio de build para o estágio final
# O caminho do JAR deve ser /app/target/... pois definimos WORKDIR /app no build
COPY --from=build /app/target/zupibackend-0.0.1-SNAPSHOT.jar app.jar

# Define o comando que será executado ao iniciar o container
ENTRYPOINT ["java","-jar", "/app.jar"]