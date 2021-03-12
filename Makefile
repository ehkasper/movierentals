run:
	./gradlew clean build
	docker build . -t api
	docker run -p 8080:8080 -t api