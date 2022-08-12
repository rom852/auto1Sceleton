package com.auto1.tests;

import com.auto1.steps.CarSteps;
import com.auto1.steps.UserSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CarTest extends BaseTest {

    @Autowired
    UserSteps userSteps;
    @Autowired
    CarSteps carSteps;

    @Test
    public void addNewCarToUser() {
        userSteps.createNewUser(USER_1);
        carSteps.addCarForUser(USER_1, CAR_1);
        userSteps.deleteUser(USER_1);
    }

    @Test
    public void shouldBeAbleToEditCarModel() {
        userSteps.createNewUser(USER_1);
        carSteps.addCarForUser(USER_1, CAR_1);
        carSteps.editCarModel(CAR_1, "newModel");
        carSteps.assertCarModel(CAR_1, "newModel");
        userSteps.deleteUser(USER_1);
    }

    @Test
    public void shouldNotAddCarForDeletedUser() {
        userSteps.createNewUser(USER_1);
        userSteps.deleteUser(USER_1);
        carSteps.tryToAddCarAndGetException(USER_1, CAR_1, BAD_REQUEST_ERROR);
    }

    @Test
    public void shouldNotAddCarForNotExistingUser() {
        carSteps.tryToAddCarForNotExistingUserAndGetException(CAR_1, BAD_REQUEST_ERROR);
    }

    @AfterEach
    public void afterCarTests() {
        carSteps.deleteAllCars();
    }
}
