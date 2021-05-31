#run native image
docker pull goafabric/callee-service-quarkus:1.0.0-SNAPSHOT && docker run --name calle-service-quarkus --rm -p8080:8080 goafabric/callee-service-quarkus:1.0.0-SNAPSHOT
#-Xmx64m
