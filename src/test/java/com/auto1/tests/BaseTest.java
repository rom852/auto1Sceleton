package com.auto1.tests;


import com.auto1.configuration.BaseConfiguration;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@ContextConfiguration(classes = {BaseConfiguration.class})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class})
public class BaseTest {

    public static final String USER_1 = "user1";
    public static final String CAR_1 = "car1";
    public static final String BAD_REQUEST_ERROR = "Bad Request";


    public static String generateRandomName(String prefix) {
        return prefix + "_" + RandomStringUtils.random(8, false, true);
    }
}
