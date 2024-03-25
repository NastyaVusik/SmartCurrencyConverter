FROM openjdk:17 AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package


FROM openjdk:17
ARG JAR_FILE=/usr/app/build/libs/*-SNAPSHOT.jar
COPY --from=build $JAR_FILE /app/runner.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/runner.jar"]


#FROM openjdk:17
#CMD ["/.gradlew", "clean", "bootJar"]
#COPY build/libs/*-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar", "/app.jar"]