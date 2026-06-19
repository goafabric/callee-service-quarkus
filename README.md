# docker compose
go to /src/deploy/docker and do "./stack up"

# run native image
container pull goafabric/callee-service-quarkus:$(grep '^version=' gradle.properties | cut -d'=' -f2)
"${(@z)${CRUNTIME:-docker run --pull always}}" --name calle-service-quarkus --rm -p 50900:50900 goafabric/callee-service-quarkus:$(grep '^version=' gradle.properties | cut -d'=' -f2)
