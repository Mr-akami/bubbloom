# bubbloom

## Getting Started
Install and configure docker in your environment. After that, you can run the below commands from the root directory of this project.

```
Run docker-compose build
Run docker-compose up
```

## CRUD operations for events
### Create
Method: `POST`  
Path: `http://<address>:<port>/events/`  
Body: example below:
```
{
    "title": "event1"
}
```
Note: You do not specify an id for the new event as a unique ID will be generated on the server side.


### Read
Method: `GET`  
Path: `http://<address>:<port>/events/<id>`  
Body: empty

### Read all
Method: `GET`  
Path: `http://<address>:<port>/events/`  
Body: empty

### Update
Method: `PUT`  
Path: `http://<address>:<port>/events/<id>`  
Body: example below:
```
{
    "title": "new title1"
}
```

Note: You need to include unchanged properties in the body including unchanged ones.
Otherwise, those properties will be updated with default values such as null, 0, etc.

### Delete
Method: `DELETE`  
Path: `http://<address>:<port>/events/<id>`  
Body: empty