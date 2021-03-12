# MovieRental API

#### Requirements
* Java 11
* Docker

Running:

1. `$ make run`

This will expose your local 8080 port. Thus, if you need to make requests you can use:
```
localhost:8080
```

#### Using the API
You can create your own user:
```
$ curl -X POST -H "Content-Type: application/json" http://localhost:8080/users -d '{"username": "username", "password": "password"}'
```

I've left a user created in the database, in case you want to use it. Use it like this:
```
$  curl -H "Content-Type: application/json" -u "user1:123456" http://localhost:8080/movies
```

If you want to take out a movie, execute the following API:
```
$ curl -X POST -H "Content-Type: application/json" -u "user1:123456" http://localhost:8080/rentals/takeout -d '{"movie_id": "1"}'
```

Use the response id to return that rental. Like this:
```
$ curl -X POST -H "Content-Type: application/json" -u "user1:123456" http://localhost:8080/rentals/return -d '{"rental_id": "1"}'
```

## Next Steps
* Due to Spring Security restrictions and the usage of an in-memory database, you can't use the user you created to authenticate as of now.
* Controllers got many responsibilities, I hope this won't be as important due to the simplicity of the architecture and logic. But as the size increases it'd be necessary to group that logic into their services
* Usually, unit tests are a great way to guide development. However, in this case, I'm sticking to framework architecture so I focused on integration tests and created them prior the development to make sure it's functional