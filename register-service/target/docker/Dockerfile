FROM java:8
VOLUME /tmp
COPY register-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8761
EXPOSE 8762
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]