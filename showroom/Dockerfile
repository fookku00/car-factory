FROM adoptopenjdk:11-jre-openj9

RUN mkdir -p /home/app

ADD target/showroom-0.0.1-SNAPSHOT.jar /home/app/app.jar
ADD application.yaml /home/app/application.yaml

WORKDIR /home/app

EXPOSE 8080

ENTRYPOINT exec java -jar  /home/app/app.jar --spring.config.location=/home/app/application.yaml