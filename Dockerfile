FROM amazoncorretto:17.0.3-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app
CMD java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$PORT -jar /app
