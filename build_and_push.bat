call mvn clean install
docker build . -t marmiss/booking-service:1.0.0
docker push marmiss/booking-service:1.0.0