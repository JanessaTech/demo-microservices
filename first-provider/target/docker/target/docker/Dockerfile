FROM java:8
VOLUME /tmp
COPY first-provider-1.0-SNAPSHOT.jar app.jar
EXPOSE 8765
EXPOSE 8766
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]