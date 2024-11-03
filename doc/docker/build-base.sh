docker buildx create --name mybuilder --use && docker buildx build --platform linux/amd64,linux/arm64 -t \
goafabric/quarkus-ubi-awt:8.7 -f ./Dockerfile --push . ; docker buildx stop mybuilder && docker buildx rm mybuilder
