demo-microservices
how to run:
1. run mvn package for demo-microservices to generate jar for each sub-module
2. See how /home/juan/docker/compose looks like on 192.168.0.24(driver)
3. update this file to /home/juan/docker/compose
4. copy Dockerfile and jar of each module to the directory /home/juan/docker/compose on 192.168.0.24(driver)
3. run: docker-compose up -d
4. check the following urls:
    - visit  http://192.168.0.24:8761/ (Eureka)
    - visit http://192.168.0.24:8769/swagger-ui.html (centric doc)
    - visit http://192.168.0.24:8771/first-provider/first/call_second (gateway)
    - visit http://192.168.0.24:9411/zipkin/ (zipkin)