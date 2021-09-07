# bubbloom

## Getting Started
Install and configure docker in your environment. After that, you can run the below commands from the root directory of this project.

```
Run docker-compose build
Run docker-compose up
```

## Sending Requests
### POST
You can create a new event by sending a POST request to `http://localhost:8080/events/` with a body like this:
```
{
    "id": 1,
    "title": "event1"
}
```

### GET
To get a list of events, send a GET request to `http://localhost:8080/events/`. The result would be something like this:
```
[
    {
        "id": 1,
        "title": "event1"
    },
    {
        "id": 2
        "title": "event2"
    }
]
```

### DELETE
To delete an event, send a DELETE request by specifying the id of the event as a path parameter. If you want to delete an event whose id is 1, for example, you send the following DELETE request: `http://localhost:8080/events/1`.
