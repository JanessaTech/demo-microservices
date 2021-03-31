FROM fabric8/java-alpine-openjdk8-jre
VOLUME /tmp
COPY admin-service-1.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8888,suspend=n"
EXPOSE 8770
EXPOSE 8888
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]