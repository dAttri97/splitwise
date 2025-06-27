package com.attri.splitwise.controller;

import com.attri.splitwise.dao.entity.User;
import com.attri.splitwise.dao.service.IUserDao;
import com.attri.splitwise.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/splitwise/user/v1")
public class UserController extends AbstractBaseController {
    @Autowired
    private IUserDao userDao;

    @GetMapping
    public ResponseEntity<BaseResponse<User>> getUser(@RequestParam Long userId) {
        log.info("Fetching user details {}", userId);
        User user = userDao.findById(userId).orElse(null);
        log.info("User details fetched: {}", user);
        return createResponse(BaseResponse.builder(user).build());
    }

    @PostMapping
    public ResponseEntity<BaseResponse<User>>  createUser(@RequestParam String name,
                                                          @RequestParam String email,
                                                          @RequestParam String mobileNumber) {
        log.info("Creating user with name: {}, email: {}", name, email);
        final User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .email(email)
                .name(name)
                .mobileNumber(mobileNumber)
                .build();
        final User savedUser = userDao.save(user);
        log.info("User created successfully: {}", savedUser);
        return createResponse(BaseResponse.builder(savedUser).build());
    }
}
