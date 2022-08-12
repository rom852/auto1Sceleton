package com.auto1.configuration;

import com.auto1.api.CarApi;
import com.auto1.api.UserApi;
import com.auto1.context.SharedContext;
import com.auto1.steps.CarSteps;
import com.auto1.steps.UserSteps;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application.properties")
public class BaseConfiguration {

    @Bean
    UserApi userApi() {
        return new UserApi();
    }

    @Bean
    UserSteps userSteps() {
        return new UserSteps();
    }

    @Bean
    CarApi carApi() {
        return new CarApi();
    }

    @Bean
    CarSteps carSteps() {
        return new CarSteps();
    }

    @Bean
    SharedContext sharedContext() {
        return new SharedContext();
    }
}
