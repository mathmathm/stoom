# Maven build container
FROM maven:3.6.3-openjdk-11 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

FROM openjdk

#expose port 8080
EXPOSE 8080

CMD java -jar /data/stoom-0.0.1-SNAPSHOT.jar

COPY --from=maven_build /tmp/target/stoom-0.0.1-SNAPSHOT.jar /data/stoom-0.0.1-SNAPSHOT.jar
