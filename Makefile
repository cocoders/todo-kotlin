test:
	docker-compose up -d
	docker-compose exec app sh -c "cd ./project && ./mvnw test"
