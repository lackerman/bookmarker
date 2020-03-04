FROM openjdk:alpine-11-minimal

EXPOSE 8080

COPY exec.sh /usr/bin/app
COPY target/bookmarker-1.0-SNAPSHOT-fat.jar /app/app.jar

RUN chmod +x /usr/bin/app

CMD [ "app" ]
