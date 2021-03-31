FROM java:8
VOLUME /tmp
COPY second-provider-1.0-SNAPSHOT.jar app.jar
EXPOSE 8767
EXPOSE 8768
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]