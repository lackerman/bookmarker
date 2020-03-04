DOCKER_REGISTRY:=minimal-vertx
DOCKER_TAG:=latest

.PHONY: mvn-package minimal-jdk docker-image

mvn-package:
	@mvn package

docker-image: mvn-package minimal-jdk
	@docker build -t $(DOCKER_REGISTRY):$(DOCKER_TAG) .

minimal-jdk:
	@docker build -f Dockerfile.jdk -t openjdk:alpine-11-minimal .

test-jdk:
	@docker run --rm openjdk:alpine-11-minimal

run:
	mkdir -p out
	docker run --workdir=/tmp --volume=$(PWD)/out:/tmp --cpus=1 --memory=40m --rm -p=8080:8080 $(DOCKER_REGISTRY):$(DOCKER_TAG)
