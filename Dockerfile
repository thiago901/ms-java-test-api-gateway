# Use uma imagem base do OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

ENV brocker.queue.player.create.name=player-create
ENV brocker.queue.player.list.name=players-list 
ENV brocker.queue.player.show.name=player-show
ENV brocker.queue.player.delete.name=player-delete 
ENV brocker.queue.player.update.name=player-update
# Defina o diretório de trabalho no contêiner
WORKDIR /app

RUN ./mvnw package 
# Copie o arquivo JAR do seu aplicativo para o contêiner
COPY /target/api.gateway-0.0.1-SNAPSHOT.jar /app/api.gateway.jar

# Comando para executar o aplicativo Java
CMD ["java", "-jar", "api.gateway.jar"]

EXPOSE 8080
