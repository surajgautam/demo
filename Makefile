build:
	mvn clean install -DskipTests

up:
	docker-compose up

test:
	mvn clean install
