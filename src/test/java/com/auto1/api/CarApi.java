package com.auto1.api;

import com.auto1.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;


@Slf4j
public class CarApi extends BaseApi {



    public ResponseEntity<Car> createCar(String userId) {
        Car car = Car.builder()
                .userId(userId)
                .imageUrl("imageUrl")
                .manufacture("manufacture")
                .model("model")
                .build();

        return postRequest(baseUrl + "/api/car", car, Car.class);
    }

    public ResponseEntity<String> deleteCar(String carId) {
        return deleteRequest(baseUrl + "/api/car/" + carId);

    }

    public ResponseEntity<Car> editCar(String carId, String manufacture, String model, String imageUrl) {
        Car car = Car.builder()
                .imageUrl(imageUrl)
                .manufacture(manufacture)
                .model(model)
                .build();
        return putRequest(baseUrl + "/api/car/"+ carId, car,  Car.class);
    }

    public ResponseEntity<Car> getCar(String carId) {
        return getRequest(baseUrl + "/api/car/" + carId, Car.class);
    }
}
