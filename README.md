# eshop-sa

- We used spring `spring-cloud-gateway` as an api gateway to our services. and also authentication is handled at gateway.
- We have used `spring-cloud-starter-netflix-eureka-server` as a service registry.
- We have used `spring-cloud-config-server` to have client-side support for externalized configuration.
- We have used `spring-cloud-starter-openfeign` for service to service communication and also for load balancing.
- Overall we have 8 services `api-gateway, eshop-account-service, eshop-spring-cloud-config-server, eureka-naming-server, order-service, payment-service, product-service, shipping-service, transaction-service`
- We also created `docker-compose` to run all our service.
- Working demo loom video link:- https://www.loom.com/share/40168e83d714442eb05cf808b6e3dca6