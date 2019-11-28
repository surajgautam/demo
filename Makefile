build:
	mvn clean install -DskipTests

infra:
	docker-compose up

test:
	mvn clean install

run:
	cd service/  && mvn spring-boot:run
