FROM java:8
VOLUME /tmp
COPY config-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8763
EXPOSE 8764
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]