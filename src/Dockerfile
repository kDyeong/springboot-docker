FROM openjdk:17-jdk-slim 
ADD /build/libs/*.jar myboard.jar 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myboard.jar"]