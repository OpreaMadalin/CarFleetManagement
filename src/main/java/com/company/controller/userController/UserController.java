package com.company.controller.userController;

import com.company.controller.databaseController.MongoController;
import com.company.model.user.UserResponseBody;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @GetMapping("/getUsers")
    public List<UserResponseBody> getUsers() {

        MongoController mc = new MongoController();
        ArrayList<Document> users = mc.getUsers();
        return users.stream()
                .map(user -> {
                    String id = user.get("_id").toString();
                    String username = user.get("username").toString();
                    String password = user.get("password").toString();
                    return new UserResponseBody(id, username, password);
                })
                .collect(Collectors.toList());
    }
}
