From amitaarya17/docker-maven-chrome
COPY . /app
WORKDIR /app
CMD mvn -B package --file pom.xml