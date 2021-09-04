# bubbloom

## Getting Started
Install and configure docker in your environment. After that, you can run the below commands from the root directory of this project.

```
Run docker-compose build
Run docker-compose up
```
You can create a new event by sending a POST request to `http://localhost:8080/events/` with a body like this:
```
{"id":1}
```
To get a list of events, send a GET request to `http://localhost:8080/events/`. The result would be something like this:
```
[
    {
        "id": 1
    },
    {
        "id": 2
    }
]
```

## 