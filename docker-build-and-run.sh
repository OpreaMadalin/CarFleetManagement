#!/bin/bash

docker stop carfleetmanagement-rest-container

rm -r ./target/
./mvnw package

docker build -t carfleetmanagement-rest-image .
docker rm carfleetmanagement-rest-container
docker run -p 8080:8080 --name carfleetmanagement-rest-container carfleetmanagement-rest-image