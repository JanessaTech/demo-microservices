FROM java:8
VOLUME /tmp
COPY simple-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]