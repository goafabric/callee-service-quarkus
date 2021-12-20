#docker compose
go to /src/deploy/docker and do "./stack up"

#run native image
docker pull goafabric/callee-service-quarkus:1.2.0 && docker run --name calle-service-quarkus --rm -p50900:50900 goafabric/callee-service-quarkus:1.2.0

#run native image arm64
docker run --name calle-service-quarkus --rm -p50900:50900 goafabric/callee-service-quarkus-arm64v8:1.2.0

