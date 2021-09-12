# bubbloom

## Getting Started
Install and configure docker in your environment. After that, you can run the below commands from the root directory of this project.

```
docker-compose build
docker-compose up
```
If you want to start containers in the detached mode, run the following commands instead.
Without the `-d` option all the containers will be started and stopped at the same time.
Using the `-d` option allows you to handle the containers separately using docker commands.

```
docker-compose build
docker-compose up -d
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

Note: The body needs to have all the propertiesgit commi including unchanged ones.
Otherwise, those properties will be updated with default values such as null, 0, etc.

### Delete
Method: `DELETE`  
Path: `http://<address>:<port>/events/<id>`  
Body: empty