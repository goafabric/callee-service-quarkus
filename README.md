# docker compose
go to /src/deploy/docker and do "./stack up"

# run native image
docker run --pull always --name calle-service-quarkus --rm -p50900:50900 goafabric/callee-service-quarkus:$(grep '^version=' gradle.properties | cut -d'=' -f2)

# run native image arm64
docker run --pull always --name calle-service-quarkus --rm -p50900:50900 goafabric/callee-service-quarkus-arm64v8:$(grep '^version=' gradle.properties | cut -d'=' -f2)