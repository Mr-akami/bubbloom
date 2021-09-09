# bubbloom

## Getting Started
Install and configure docker in your environment. After that, you can run the below commands from the root directory of this project.

```
Run docker-compose build
Run docker-compose up
```

## CRUD operations for events
### Create
You can create a new event by sending a **POST** request to `http://localhost:8080/events/` with a body like this:
```
{
    "title": "event1"
}
```
You do not specify an id for the new event as a unique ID will be generated on the server side.
A reply message will be sent with data of the newly created event with its id:
```
{
    "id": 1,
    "title": "event1"
}
```

### Read
To get a list of all the events, send a **GET** request to `http://localhost:8080/events/`.
The result will look like this:
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
To get a specific event instead of getting the whole list, send a **GET** request specifying an id:
`http://localhost:8080/events/1`. You will then get a result like this:
```
{
    "id": 1,
    "title": "event1"
}
```

### Update
To update properties in an event, send a **PUT** request with the following information:
* Event ID in the path: `http://localhost:8080/events/1`
* Body with all the properties but the id. Note that you need to include unchanged properties as well.
Otherwise, those properties will be updated with default values such as null, 0, etc.

```
{
    "title": "new title1"
}
```


### Delete
To delete an event, send a **DELETE** request by specifying the id of the event as a path parameter.
If you want to delete an event whose id is 1, for example, you send the following DELETE request: `http://localhost:8080/events/1`.
