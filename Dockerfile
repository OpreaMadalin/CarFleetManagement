FROM openjdk:8-jre-alpine

RUN mkdir /project

COPY ./target/carFleetManagement-0.0.1-SNAPSHOT.jar /project/

WORKDIR /project

RUN ls

CMD AUTH_PRIVATE_KEY="GIlxokt5c1QMzN/xVGcsX+rgm29zDeESwmipWI9FrzDg/DHerXrRKM/q/VVFcQrXhYeEy/jpOSasL3mabYjPLQ==" AUTH_PUBLIC_KEY="4Pwx3q160SjP6v1VRXEK14WHhMv46TkmrC95mm2Izy0=" AUTH_SIGNATURE="mySignature" MONGO_USER="madalinoprea" MONGO_PASSWORD="K4a2E!4yfwDaz-F" MONGO_CLUSTER="cluster0.3qscp.mongodb.net" MONGO_DB_NAME="devDB" java -jar carFleetManagement-0.0.1-SNAPSHOT.jar