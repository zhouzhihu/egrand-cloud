#以什么镜像为基
FROM openjdk:8-jre
VOLUME /tmp
COPY target/egrand-cloud-ram-server-1.1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=dev","-jar","/app.jar"]