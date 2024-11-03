# absolute megay hacky use the normal build, let it fail and than build the real image with the output
./gradlew dockerImageNative
docker build --push -f Dockerfile.native -t goafabric/pdf-test:1.0.0 .
docker run -i --rm -p 50900:50900 goafabric/pdf-test:1.0.0