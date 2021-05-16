# Microservices-v1
Repository containing projects and files for learning microservices using Spring Boot and Spring Cloud

1. Establishing Communication between Microservices
2. Centralized Microservice Configuration with Spring Cloud Config Server
3. Using Spring Cloud Bus to exchange messages about Configuration updates
4. Simplify communication with other Microservices using Feign REST Client
6. Implement client side load balancing with Ribbon
7. Implement dynamic scaling using Eureka Naming Server and Ribbon
8. Implement API Gateway with Zuul
9. Implement Distributed tracing with Spring Cloud Sleuth and Zipkin
10.Implement Fault Tolerance with Hystrix

Ports
| Application |	Port |
| ----------- | ---- |
| Limits Service | 8080, 8081, ... |
| Spring Cloud Config Server |	8888 |
| Currency Exchange Service |	8000, 8001, 8002, .. |
| Currency Conversion Service |	8100, 8101, 8102, ... |
| Netflix Eureka Naming Server |	8761 |
| Netflix Zuul API Gateway Server |	8765 |
| Zipkin Distributed Tracing Server |	9411 |

Zipkin Installation
Quick Start Page

https://zipkin.io/pages/quickstart

Downloading Zipkin Jar


https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkin-server&v=LATEST&c=exec

Command to run
```
RABBIT_URI=amqp://localhost java -jar zipkin-server-2.12.9-exec.jar 
```
