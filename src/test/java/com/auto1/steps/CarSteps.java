package com.auto1.steps;

import com.auto1.api.CarApi;
import com.auto1.context.SharedContext;
import com.auto1.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class CarSteps {

    @Autowired
    CarApi carApi;
    @Autowired
    SharedContext context;


    public void addCarForUser(String userAlias, String carAlias) {
        String userId = context.usersList.get(userAlias).getId();
        addCarForUserWithId(userId, carAlias);
    }

    public void deleteCar(String carAlias) {
        String carId = context.carsList.get(carAlias).getId();
        ResponseEntity<String> responsePRCreation = carApi.deleteCar(carId);
        //  assertEquals(204, responsePRCreation.getStatusCode().value(), "status code is wrong");
        log.info("Car with alias {} and id {} was deleted", carAlias, carId);
    }

    public void editCarModel(String carAlias, String model) {
        Car car = context.carsList.get(carAlias);
        Car updatedCar = carApi.editCar(car.getId(), car.getManufacture(), model, car.getImageUrl()).getBody();
       // context.carsList.put(carAlias, updatedCar);
        log.info("Car  {} model was changed from {} to {}", carAlias, car.getModel(), model);
    }

    public void assertCarModel(String carAlias, String model) {
        String carId = context.carsList.get(carAlias).getId();
        Car updatedCar = carApi.getCar(carId).getBody();
          assertEquals(model, updatedCar.getModel(), "car model is wrong");
    }

    public void deleteAllCars() {
        for(String carAlias: context.carsList.keySet()){
            deleteCar(carAlias);
        }
    }

    public void tryToAddCarAndGetException(String userAlias, String carAlias, String errorMessage) {
        Exception exception = assertThrows(HttpClientErrorException.BadRequest.class,
                () -> addCarForUser(userAlias, carAlias));
        assertTrue(exception.getMessage().contains(errorMessage), "Wrong error message");
    }

    public void tryToAddCarForNotExistingUserAndGetException(String carAlias, String errorMessage) {
        Exception exception = assertThrows(HttpClientErrorException.BadRequest.class,
                () -> addCarForNotExistingUser(carAlias));
        assertTrue(exception.getMessage().contains(errorMessage), "Wrong error message");
    }

    public void addCarForNotExistingUser(String carAlias) {
        UUID uuid = UUID.randomUUID();
        carApi.createCar(uuid.toString());
    }

    private void addCarForUserWithId(String userId, String carAlias) {
        carApi.createCar(userId);
        ResponseEntity<Car> response = carApi.createCar(userId);
        //   assertEquals(201, response.getStatusCode().value(), "status code is wrong");
        Car car = response.getBody();
        context.carsList.put(carAlias, car);
    }


}
