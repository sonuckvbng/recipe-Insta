package com.sonu.recipeinsta.controller;

import com.sonu.recipeinsta.dto.requestdto.UserEmailRequestDto;
import com.sonu.recipeinsta.dto.requestdto.UserRquestDto;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping(name = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/user")
    public User saveUsers(@RequestBody UserRquestDto userRquestDto) throws UserAlreadyExistException {
        return userService.saveUser(userRquestDto);
    }

    @GetMapping("/user/get-user-by-email")
    public User getUserByEmailId(@RequestBody UserEmailRequestDto userEmailRequestDto){
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

}
