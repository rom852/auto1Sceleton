package com.auto1.steps;

import com.auto1.api.UserApi;
import com.auto1.context.SharedContext;
import com.auto1.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;


import static com.auto1.tests.BaseTest.generateRandomName;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class UserSteps {

    @Autowired
    UserApi userApi;
    @Autowired
    SharedContext context;


    public void createNewUser(String userAlias) {
        String login = generateRandomName(userAlias);
        ResponseEntity<User> response = userApi.createUser(login + "@mail.com", login);
     //   assertEquals(201, response.getStatusCode().value(), "status code is wrong");
        User user = response.getBody();
        context.usersList.put(userAlias, user);
        log.info("User with alias {} and login {} was successfully created", userAlias, login);
    }

    public void deleteUser(String userAlias) {
        String userId = context.usersList.get(userAlias).getId();
        ResponseEntity<String> responsePRCreation = userApi.deleteUser(userId);
      //  assertEquals(204, responsePRCreation.getStatusCode().value(), "status code is wrong");
        log.info("User with alias {} was successfully deleted", userAlias);
    }

    public void deleteAllUsers() {
        for(String userAlias: context.usersList.keySet()){
            deleteUser(userAlias);
        }
    }

    public void tryToDeleteUserAndGetException(String userAlias, String errorMessage) {
        Exception exception = assertThrows(HttpClientErrorException.BadRequest.class,
                () -> deleteUser(userAlias));
        assertTrue(exception.getMessage().contains(errorMessage), "Wrong error message");
    }
}
