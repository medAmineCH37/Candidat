FROM openjdk:17
EXPOSE 8090
ADD target/Candidat-0.0.1-SNAPSHOT.jar candidat.jar
ENTRYPOINT ["java","-jar","candidat.jar"]