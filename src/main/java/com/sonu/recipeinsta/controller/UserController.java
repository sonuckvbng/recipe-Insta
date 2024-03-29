package com.sonu.recipeinsta.controller;


import com.sonu.recipeinsta.dto.requestdto.UserEmailRequestDto;
import com.sonu.recipeinsta.dto.requestdto.UserRequestDto;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.UserAlreadyExistException;
import com.sonu.recipeinsta.exception.UserNotFoundException;
import com.sonu.recipeinsta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping(name = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getUsers() throws UserNotFoundException {
        return userService.getUsers();
    }

    @PostMapping("/user")
    public User saveUsers(@RequestBody UserRequestDto userRequestDto) throws UserAlreadyExistException, UserNotFoundException {
        return userService.saveUser(userRequestDto);
    }

    @GetMapping("/user/get-user-by-email")
    public User getUserByEmailId(@RequestBody UserEmailRequestDto userEmailRequestDto) throws UserNotFoundException {
        return userService.getUserByEmailId(userEmailRequestDto.getEmailId());
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUserById(@PathVariable(name = "userId")Long userId) throws UserNotFoundException {
           return userService.deleteUserById(userId);
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable(name = "userId")Long userId) throws UserNotFoundException {
        return userService.getUserById(userId);
    }

    @GetMapping("user/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        return userService.findUserByJwt(jwt);
    }
}
