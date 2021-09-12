# Event Service

## How to start the service
Run the below commands. 
```
docker-compose -p bubbloom build
docker-compose -p bubbloom up
```
The -p option sets the project name which also decides the prefixes of 
the images, the containers, the volume and the network used for the event service.
Using "bubbloom" for the project name makes it possible
for these containers to work with the other containers started by different compose files.