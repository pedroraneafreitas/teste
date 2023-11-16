# Define a imagem base
FROM openjdk:17-jdk-slim
# Copia o arquivo JAR do seu projeto para dentro do container
COPY out/artifacts/rent_jar/rent.it-0.0.1-SNAPSHOT.jar rent.jar
# Expõe a porta do seu projeto
EXPOSE 8080
# Define o comando de inicialização do seu projeto
CMD ["java", "-jar", "rent.jar"]