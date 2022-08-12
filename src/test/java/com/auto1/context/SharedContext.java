package com.auto1.context;

import com.auto1.models.Car;
import com.auto1.models.User;

import java.util.HashMap;

public class SharedContext {

    public HashMap<String, User> usersList;
    public HashMap<String, Car> carsList;


    public SharedContext() {
        this.usersList = new HashMap<>();
        this.carsList = new HashMap<>();

    }

}
