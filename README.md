# docker compose
go to /src/deploy/docker and do "./stack up"

# run native image
docker run --pull always --name calle-service-quarkus --rm -p50900:50900 goafabric/callee-service-quarkus:3.2.0-SNAPSHOT

# run native image arm64
docker run --pull always --name calle-service-quarkus --rm -p50900:50900 goafabric/callee-service-quarkus-arm64v8:3.2.0-SNAPSHOT