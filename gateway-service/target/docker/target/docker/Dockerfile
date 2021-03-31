FROM java:8
VOLUME /tmp
COPY gateway-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8771
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]