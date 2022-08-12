package com.auto1.api;

import com.auto1.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;


@Slf4j
public class UserApi extends BaseApi {


    public ResponseEntity<User> createUser(String email, String name) {
        User user = User.builder()
                .email(email)
                .name(name)
                .build();
        return postRequest(baseUrl + "/api/user", user, User.class);
    }


    public ResponseEntity<String> deleteUser(String userId) {
        return deleteRequest(baseUrl + "/api/user/" + userId);
    }
}
