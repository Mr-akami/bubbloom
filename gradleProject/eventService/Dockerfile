FROM gradle:7.0.2-jdk11 AS build
COPY --chown=gradle:gradle /settings.gradle.kts /home/gradle/eventService/
COPY --chown=gradle:gradle /build.gradle.kts /home/gradle/eventService/
COPY --chown=gradle:gradle /src /home/gradle/eventService/src
WORKDIR /home/gradle/eventService
RUN gradle build --no-daemon

FROM openjdk:11-jre
COPY --from=build /home/gradle/eventService/build/libs/eventService-1.0.jar /usr/app/eventService-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/eventService-1.0.jar"]