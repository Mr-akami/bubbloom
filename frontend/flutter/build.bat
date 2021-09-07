echo build flutter web...

cd ./devContainer

echo wake up docker container. stop it after build automatically.
echo Clean and Build.
echo Only build sometimes fail.
docker-compose run flutter flutter clean web
docker-compose run --rm flutter flutter build web
