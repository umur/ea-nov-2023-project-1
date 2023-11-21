docker build ./service-discovery -t servicediscovery
docker build ./api-gateway -t apigateway
<<<<<<< HEAD
docker build ./configuration-service -t configserver
docker build ./event-service -t eventservice
docker build ./user-service -t user-service

=======
docker build ./config-server -t configserver
docker build ./EventService -t eventservice
docker build ./user-service -t userservice
docker build ./chat-service -t chatservice
>>>>>>> dbfed9bed7e502e5b9c03827564e9f07bcd7d90a
