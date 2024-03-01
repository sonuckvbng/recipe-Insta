package com.sonu.recipeinsta.service;

import com.sonu.recipeinsta.dto.requestdto.UserRquestDto;
import com.sonu.recipeinsta.entity.User;
import com.sonu.recipeinsta.exception.UserAlreadyExistException;
import com.sonu.recipeinsta.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {


    List<User> getUsers();
    User saveUser(UserRquestDto userRquestDto) throws UserAlreadyExistException;

    User getUserByEmailId(String emailId);

    User getUserById(Long userId) throws UserNotFoundException;

    String deleteUserById(Long userId) throws UserNotFoundException;
}
