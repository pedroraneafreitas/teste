# Define a imagem base
FROM openjdk:16-alpine
# Copia o arquivo JAR do seu projeto para dentro do container
COPY out/artifacts/rent_jar/rent.jar rent.jar
# Expõe a porta do seu projeto
EXPOSE 8080
# Define o comando de inicialização do seu projeto
CMD ["java", "-jar", "rent.jar"]