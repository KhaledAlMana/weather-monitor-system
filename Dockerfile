FROM openjdk:17.0.2-jdk
ARG COMPONENT=${COMPONENT}
COPY ${COMPONENT}/build/libs/component.jar  app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]