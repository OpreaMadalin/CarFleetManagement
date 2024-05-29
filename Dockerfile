FROM openjdk:8-jre-alpine

RUN mkdir /project

COPY ./target/carFleetManagement-0.0.1-SNAPSHOT.jar /project/

WORKDIR /project

RUN ls

CMD MONGO_USER="madalinoprea" MONGO_PASSWORD="pass" MONGO_CLUSTER="cluster0.9oiuuhp.mongodb.net" MONGO_DB_NAME="devDB" java -jar carFleetManagement-0.0.1-SNAPSHOT.jar