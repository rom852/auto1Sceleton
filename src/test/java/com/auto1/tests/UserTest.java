package com.auto1.tests;

import com.auto1.steps.UserSteps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserTest extends BaseTest{

    @Autowired
    UserSteps userSteps;

    @Test
    public void shouldBeAbleToAddAndDeleteUser() {
        userSteps.createNewUser(USER_1);
        userSteps.deleteUser(USER_1);
    }

    @Test
    public void shouldGetExceptionIfTryToDeleteAlreadyDeletedUser() {
        userSteps.createNewUser(USER_1);
        userSteps.deleteUser(USER_1);
        userSteps.tryToDeleteUserAndGetException(USER_1, BAD_REQUEST_ERROR);
    }

}
